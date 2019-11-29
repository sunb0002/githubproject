import React from "react";

function HMSNameList() {
  const ships = [
    new Ship("Hood", "BB"),
    new Ship("Belfast", "CL"),
    new Ship("Formidable", "CV")
  ];

  return ships.map(({ name, type }, index) => (
    <div key={index}>
      {index + 1}: {name}, {type}
    </div>
  ));
}

class Ship {
  constructor(name, type) {
    Object.assign(this, { name, type });
  }
}

export default HMSNameList;
