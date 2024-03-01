const colors = require("colors") 
const express = require("express") 
const { JsonWebTokenError } = require("jsonwebtoken")
const app = express() 

 const http = require("http").Server(app) 
 const io = require("socket.io")(http)
 const nsp = io.of("/custom-nsp")
var users= {}
 io.on("connection", (socket) => {
    console.log("user joined")
    socket.on("new-user-joined" , (JSONdata) => {
    //    var data = JSON.parse(JSONdata) 
    //    console.log(data.name + " " + data.msg) 
     users[socket.id] = JSONdata.name 
      socket.broadcast.emit("user-joined", JSONdata )
     console.log(JSONdata)
    })

    socket.on("sendMSG", (msg) => {
        var obj = {
            name : users[socket.id], 
            msg : msg 
        }
        socket.broadcast.emit("receiveMSG" , obj )

    })
 })


 http.listen(80, () => {
    console.log("Listening!")
 })