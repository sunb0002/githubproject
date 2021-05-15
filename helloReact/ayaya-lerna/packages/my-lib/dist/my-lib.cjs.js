'use strict';

function _interopDefault (ex) { return (ex && (typeof ex === 'object') && 'default' in ex) ? ex['default'] : ex; }

var React = _interopDefault(require('react'));

function MyButton(props) {
    var txt = props.txt;
    var clickHandler = function () { return alert("MyButton is clicked!"); };
    return React.createElement("button", { onClick: clickHandler }, txt || "Default text from my-lib");
}

module.exports = MyButton;
