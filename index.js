const functions = require('firebase-functions');
var express = require('express');
var bodyParser = require('body-parser');
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
var counter = 0;
var app = express();

app.use(bodyParser.json());
var user = [{username: "Joy", password: 1999, loggedin: false, phone_number : 6476381868}, {username: "Kevin", password: "kevinfeng", loggedin: false, phone_number : 6476381868}];
var cargo = [{name: "DH5 Prize Cargo", id : "1", weight: "2 lbs", description : "This is the cargo containing prizes!", triggered : false, alarm : true, owner:"Joy"}];
var text = "";
var count = 0;

var twilio = require('twilio');
const accountSid = 'ACf35b8d7127398e2eb7df370e0335b6bb';
const authToken = '7ce9d9d1ecdef33544bccf22a5c9c0cc';
const client = require('twilio')(accountSid, authToken);
// var twilio = require('twilio');
// const accountSid = 'ACf35b8d7127398e2eb7df370e0335b6bb';
// const authToken = '7ce9d9d1ecdef33544bccf22a5c9c0cc';
// const client = require('twilio')(accountSid, authToken);

var pin = 0000;

//checks that login credentials are correct for login
app.post("/api/username-validation", (request, response)=> {
  var validated = false;
  for (let i = 0; i < user.length; i++) {
    if (request.body.username === user[i].username && request.body.password === user[i].password) {
      validated = true;
      user[i].loggedin = true;
    }
  }
  if (validated === false) {
  }
});

app.post("/api/user", (request, response) => {
	for (let i = 0; i < user.length; i++) {
	    if (request.body.username === user[i].username) {
	    	user[i].username = request.body.username;
	    	user[i].password = request.body.password;
	    	user[i].loggedin = request.body.loggedin;
	    	user[i].phone_number = request.body.phone_number;
	    }
  	} 
  	response.send({});
}); 


app.post("/api/cargo", (request, response) => {

	var twilioPromises = [];
	for (let i = 0; i < cargo.length; i++) {
	    if (request.body.id === cargo[i].id) {
	    	cargo[i].id = request.body.id;	
	    	cargo[i].name = request.body.name;
	    	cargo[i].description = request.body.description;
	    	cargo[i].weight = request.body.weight;
	    	cargo[i].triggered = request.body.triggered;
	    	cargo[i].alarm = request.body.alarm;
	    	if (cargo[i].triggered) {
	    		console.log("okay")
			    twilioPromises.push(client.messages
		      .create({from: '+12898022442', body: 'Joy, your cargo id:' + cargo[i].id + ' has been breached. Please check your Innovation Trucking account.', to: '+16476381868'})
		      .then(message => console.log(message.sid)));
	    	}
	    	cargo[i].owner = request.body.owner;
	    }
  	}

  	return Promise.all(twilioPromises)
  	.then(() => response.send())
  	.catch(err => response.send(err));
  	// response.send({body: request.body, cargo: cargo});
});

app.post("/api/create-cargo", (request, response) => {
	let car = request.body;
	counter++;
	car.id = counter;
  	cargo.push(request.body); 
}); 


//user is a new user
app.post("/api/username/newuser", (request, response)=> {
  user.push({ 
    username : request.body.username, 
    password: request.body.password, 
    loggedin: false
  });
})

//user wants to cancel the alarm
app.post("/api/:username/disable", (request, response)=> {
  for (let i = 0; i < user.length; i++) {
   if (request.params.username === user[i].username && user[i].triggered === true) {
      user[i].triggered = false;
      res.send("hit");
      req.send({});
    } 
  }
});


//user logs out 
app.post("/api/:username/loggout", (request, response)=> {
  for (let i = 0; i < user.length; i++) {
    if (request.params.username === user[i].username && user[i].loggedin=== true) {
    	user[i].loggedin = false;
      	req.send({});
    }
  }
});

//two-factor authorization
function makeid() {
  var seq = (Math.floor(Math.random() * 10000) + 10000).toString().substring(1);

  return parseInt(seq);
}

//get random pin
app.get('/api/authPin/', (req, res) =>{
	res.send(JSON.stringify({password : pin}));
});

app.post('/api/authPin/', (request, response) => {
	
});

//post verify pin
app.post('/api/verifyPin/:id', (req, res) => {
	console.log("hit");
	if (req.body.verify) {
		pin = makeid();
		let owner;
		cargo.forEach(car => {
			console.log("lol " + car.id + " " + req.params.id);
			if (car.id == req.params.id) {
				owner = car.owner;
			}
		});

		console.log(owner);
		let number;
		user.forEach(use => {
			if (use.username == owner) {
				number = use.phone_number;
			}
		})
		console.log(number);
		client.messages
		      .create({from: '+12898022442', body: 'The pin for shipment: ' + req.params.id + ' is ' + pin, to: '+1'+ number})
		      .then(message => console.log(message.sid))
		      .done();
	}
	res.send({});
});

//gets all users information
app.get('/api/user/:username', (req, res) => {
	for (let i = 0; i < user.length; i++) {
		if (req.params.username === user[i].username) {
			res.send(JSON.stringify(user[i]));
		}
	}
	req.send({});
});

//gets all cargo information
app.get('/api/cargo/:id', (req, res) => {
	for (let i = 0; i < cargo.length; i++) {
		console.log(cargo[i].id);
		if (req.params.id === cargo[i].id) {
			res.send(JSON.stringify(cargo[i]));
		}
	}
	req.send({});
});

//alarm is enabled or disabled depending on Nathaniel's code input
app.post('/api/:username/alarm-setup', (req, res, value) => {
	for (let i = 0; i < user.length; i++) {
		if (req.params.username === user[i].username) {
			user[i].alarm = value;
		}
	}
	req.send({});
});

exports.apps = functions.https.onRequest(app);
