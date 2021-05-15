import React from 'react';

function MyButton(props) {
    var txt = props.txt;
    var clickHandler = function () { return alert("MyButton is clicked!"); };
    return React.createElement("button", { onClick: clickHandler }, txt || "Default text from my-lib");
}

export default MyButton;
