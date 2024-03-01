const express = require("express")
const colors = require("colors") 
const path = require("path"); 
const app = express()
const http = require("http").Server(app) 
const io = require("socket.io")(http) 
const users = {} 


app.get("", (req, res) => {
   res.sendFile("C:/Users/vedant.rathore/Desktop/socket.io chat application/client-side/index.html")
});
io.on("connection", (socket) => {
    console.log("connected")
   socket.on("new-user-joined",(name) => {
    console.log(name)
    users[socket.id] = name 
    socket.broadcast.emit("user-joined",name) 
    console.log("heyheyheyh")
   })

   socket.on("send",(message) => {
    console.log("asgasgasdg")
    socket.broadcast.emit("receive",{
        msg : message ,
        name : users[socket.id]
    })
   })

   socket.on("disconnect",() => {
     socket.broadcast.emit("left", users[socket.id])
     delete users[socket.id]
   })

})  



http.listen(1234,() => {
    console.log("Listssesning".red.underline.bgBlue)
}) 