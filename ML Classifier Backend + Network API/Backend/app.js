const express = require('express');
let {PythonShell} = require('python-shell');

const app = express();

// Middleware for POST request data 
app.use(express.json());
app.use(express.urlencoded({extended:true}));




app.listen(4444,function(){
    console.log('Server started');
});

