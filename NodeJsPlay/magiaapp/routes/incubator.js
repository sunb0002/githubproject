const express = require('express');
const mongoose = require('mongoose');
const JSONStream = require('JSONStream');

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

router.get('/all', (req, res, next) => {
    streamAllMagia()
        .pipe(JSONStream.stringify())
        .pipe(res);
});

router.get('/get/:name', (req, res, next) => {
    qbSays(res, getMagiaByName, req.params.name);
});

router.get('/post/:firstname', (req, res, next) => {
    qbSays(res, createMagia, req.params.firstname);
});

router.get('/delete/:firstname', (req, res, next) => {
    qbSays(res, deleteMagia, req.params.firstname);
});

/**
 * 
 * @param {*} res 
 * @param {*} fn 
 * @param {*} param 
 */
async function qbSays(res, fn, param) {
    try {
        const result = await fn(param);
        console.log(`Qbey executed function: ${fn.name}() with params: ${param}, result is:`, result);
        res.json(GeneralResponse.ok(result));
    } catch (err) {
        console.error("Encountered error:", err);
        res.status(500).json(GeneralResponse.error(err.toString()));
    }
}

function getAllMagia() {
    return MahouShoujo.find({}).exec();
}

function streamAllMagia() {
    return MahouShoujo.find({}).cursor();
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

function deleteMagia(firstname) {
    const query = MahouShoujo.deleteOne({
        firstname
    });
    return query.exec();
}

function getRandom() {
    return Math.floor((Math.random() * 1000) + 1);
}

module.exports = router;

//TODO: 
//ejs page with links
