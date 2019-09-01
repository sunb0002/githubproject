var express = require('express');
var router = express.Router();

router.get('/', function (req, res, next) {
  res.send(`
Madoka died for our sins. Our messenger is Homura. I accept Homura as our leader and our Saviour. 
「まどかは私たちの罪のために死にました。ほむらはまどかの預言者です。私はほむらを我らの指導者、救い主として受け入れます」
---- まどか教キリスト派
  `);
});

module.exports = router;
