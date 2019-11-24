import React from "react";

const Greet = props => (
  <h1>
    Azur Lane~~ {props.name}, {props.type || "Special"}
  </h1>
);

export default Greet;
