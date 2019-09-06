const express = require('express');
const mongoose = require('mongoose');

const router = express.Router();
const MahouShoujo = require('../models/mahou-shoujo.js');
const GeneralResponse = require('../models/general-response.js');

mongoose.connect(
    'mongodb+srv://mami:***@clustermadoka-k6v6m.mongodb.net/test?retryWrites=true&w=majority',
    {
        dbName: 'sample_airbnb',
        useNewUrlParser: true
    }
).then(() => {
    console.log('Connection to the Atlas Cluster is successful!')
}).catch(err => console.error('Failed to connect to mongodb:', err));


router.get('/', (req, res, next) => {
    qbSays(res, getAllMagia);
});

router.get('/:name', (req, res, next) => {
    qbSays(res, getMagiaByName, req.params.name);
});

router.get('/post/:firstname', (req, res, next) => {
    qbSays(res, createMagia, req.params.firstname);
});

// router.get('/delete/:name', (req, res, next) => {
// });

async function qbSays(res, fn, param) {
    try {
        const result = await fn(param);
        console.log(`Qbey executed function: ${fn.name}() with params: ${param}, result is ${result}`);
        res.json(GeneralResponse.ok(result));
    } catch (err) {
        console.error("Encountered error:", err);
        res.status(500).json(GeneralResponse.error(err.toString()));
    }
}

function getAllMagia() {
    return MahouShoujo.find({}).exec();
}

function getMagiaByName(name) {
    const query = MahouShoujo.find({
        $or: [
            { "firstname": new RegExp(name, "i") },
            { "lastname": new RegExp(name, "i") }
        ]
    });
    return query.exec();
}

function createMagia(firstname) {
    const newMagia = new MahouShoujo({
        firstname,
        "lastname": `MagiaRecordMobile-${getRandom()}`,
        "class": "faker"
    });
    return newMagia.save();
}

function getRandom() {
    return Math.floor((Math.random() * 1000) + 1);
}

module.exports = router;

//TODO: 
//Delete
//Get with Mongoose Streaming
//ejs page with links
