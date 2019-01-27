var express = require('express')
var app = express()
var bodyParser = require('body-parser')
app.use(bodyParser.json())
var user = [{username: "Joy", password: "joyxiao", loggedin: false, alarm : true}, {username: "Kevin", password: "kevinfeng", loggedin: false, alarm : false}]
var text = "";

//checks that login credentials are correct for login
app.post("/api/username-validation", (request, response)=> {
  var validated = false;
  for (let i = 0; i < user.length; i++) {
    if (request.body.username === user[i].username && request.body.password === user[i].password) {
      validated = true;
      console.log("validated");
      user[i].loggedin = true;
    }
  }
  if (validated === false) {
    console.log("validation failed");
  }
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
app.post("/api/:username/alarm", (request, response)=> {
  for (let i = 0; i < user.length; i++) {
    if (request.params.username === user[i].username && user[i].alarm === true) {
      response.send(JSON.stringify({ username : user[i].loggedin }));
      user[i].alarm = false;
      console.log(user[i]);
      console.log("Alarm Cancelled");
    }
  }
});

//user logs out 
app.post("/api/:username/logout", (request, response)=> {
  for (let i = 0; i < user.length; i++) {
    if (request.params.username === user[i].username && user[i].loggedin=== true) {
      response.send(JSON.stringify({ username : user[i].loggedin }));
      user[i].loggedin = false;
      console.log(user[i]);
      console.log("Logout");
    }
  }
});

//two-factor authorization
function makeid() {
  var possible = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
  for (let i = 0; i < 10; i++)
    text += possible.charAt(Math.floor(Math.random() * possible.length));
  return text;
}

app.listen(3000, ()=> {
  console.log("server connected");
})