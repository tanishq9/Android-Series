const express = require('express');
const cors = require("cors");
const bodyParser = require("body-parser");
const {PythonShell} = require('python-shell');

const app = express();

// Middleware for POST request data 
app.use(cors());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));

app.get("/", (req, res) => {
    res.send("Working");
});

app.post("/disease/diabetes",(req,res)=>{

    console.log(req.body);
    console.log(req.body.feat1);
    console.log(req.body.feat2);  
    console.log(req.body.feat3);  
    console.log(req.body.feat4);   
    console.log(req.body.feat5);   
    console.log(req.body.feat6);  
    console.log(req.body.feat7);   
    console.log(req.body.feat8);   

    let options = {
        "args":
                [req.body.feat1,req.body.feat2,req.body.feat3,req.body.feat4,
                req.body.feat5,req.body.feat6,req.body.feat7,req.body.feat8] 
        }
    
    PythonShell.run("predict.py", options, (err, result) => {
        if (err) {
            throw err;
        }
        output={
            "outcome":result[0]
        }
        console.log(output);
        res.json(output);
    });
});

app.listen(process.env.PORT || 4444,function(){
    console.log('Server started');
});

