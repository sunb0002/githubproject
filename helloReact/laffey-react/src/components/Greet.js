import React from "react";

const Greet = props => (
  <h2>
    Azur Lane~~ {props.name}, {props.type || "Special"}
  </h2>
);

export default Greet;
