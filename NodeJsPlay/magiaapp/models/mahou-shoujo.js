const mongoose = require('mongoose');

const mahoushoujoSchema = new mongoose.Schema({
    firstname: String,
    lastname: String,
    class: String
});

module.exports = mongoose.model('MahouShoujo', mahoushoujoSchema, 'mahou_shoujo');
