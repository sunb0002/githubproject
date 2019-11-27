import React from "react";

function FunctionClick() {
  const clickHandler = () => console.log("Button Clicked.");
  return (
    <div>
      <button onClick={clickHandler}>Event-console!</button>
    </div>
  );
}

export default FunctionClick;
