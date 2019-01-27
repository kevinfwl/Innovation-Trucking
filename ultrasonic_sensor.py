#Libraries
import RPi.GPIO as GPIO
import time
import urllib2
import json
import math
import requests
 
#GPIO Mode (BOARD / BCM)
GPIO.setmode(GPIO.BCM)
 
#set GPIO Pins
GPIO_TRIGGER = 18
GPIO_ECHO = 24
GPIO_BUTTON = 25
 
#set GPIO direction (IN / OUT)
GPIO.setup(GPIO_TRIGGER, GPIO.OUT)
GPIO.setup(GPIO_ECHO, GPIO.IN)
GPIO.setup(GPIO_BUTTON, GPIO.IN);

def calibrate():
    li = [0, 0, 0, 0, 0]
    counter = 0
    
    # Calibrate the sensor by getting a few consistant readings
    while(counter < 5):
        li[counter] = distance()
        if(counter == 0):
            counter += 1
        else:
            if(li[counter] >= li[counter-1] - 0.1 and li[counter] <= li[counter - 1] + 0.1):
                counter +=1
            else:
                counter = 0
        time.sleep(0.1)
    return li[0]
        

def distance():
    # set Trigger to HIGH
    GPIO.output(GPIO_TRIGGER, True)
 
    # set Trigger after 0.01ms to LOW
    time.sleep(0.00001)
    GPIO.output(GPIO_TRIGGER, False)
    StartTime = time.time()
    StopTime = time.time()
 
    # save StartTime
    while GPIO.input(GPIO_ECHO) == 0:
        StartTime = time.time()
 
    # save time of arrival
    while GPIO.input(GPIO_ECHO) == 1:
        StopTime = time.time()
 
    # time difference between start and arrival
    TimeElapsed = StopTime - StartTime
    # multiply with the sonic speed (34300 cm/s)
    # and divide by 2, because there and back
    distance = (TimeElapsed * 34300) / 2
 
    return distance
 
if __name__ == '__main__':
    try:
	# the sensor should be calibrated at the beginning
        calibrated = calibrate()
        END_POINT = "https://deltahacksv-204a8.firebaseapp.com/api/cargo"
        
 
        while True:
	    #Calibrate button
            if (GPIO.input(GPIO_BUTTON) == 0):
                calibrated = calibrate()
            # the door has been opened
            if(math.fabs(calibrated-distance()) > 8):
                url = urllib2.urlopen(END_POINT + "/1")
                obj = json.load(url)
		# notify server that alarm is tripped
		if(obj["alarm"]):
                    obj["triggered"] = True
                    jsonObj = json.dumps(obj)
                    headers = {'Content-type': 'application/json'}
                    response = requests.post(url=END_POINT, data=jsonObj, headers=headers)
                    
		    #wait for the alarm to be turned off before you start monitoring
		    #then recalibrate
                    while obj["triggered"]:
        	        url = urllib2.urlopen(END_POINT + "/1")
               		obj = json.load(url)
                        time.sleep(10)
		    
                    calibrated = calibrate()
            
            time.sleep(1)
    except:
        GPIO.cleanup()

