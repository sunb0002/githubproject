import React from "react";

import useCounter from "./hook-play/custom-hooks/useCounter";

function Z46() {
  const { count, increment } = useCounter();

  const clickHandler = () => {
    increment();
  };

  return (
    <div>
      <button onClick={clickHandler}>Click Z46 Fitz: {count} </button>
    </div>
  );
}

export default Z46;
