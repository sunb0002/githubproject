import React from "react";

function FunctionClick(props) {

  const { parentHandler } = props;
  const clickHandler = () => {
    console.log("Button Clicked.");
    parentHandler();
  };

  return (
    <div>
      <button onClick={clickHandler}>Event-console!</button>
    </div>
  );
}

export default FunctionClick;
