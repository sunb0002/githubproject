import React from "react";

function HMSNameList() {
  const ships = [
    new Ship("Hood", "BB"),
    new Ship("Belfast", "CL"),
    new Ship("Formidable", "CV")
  ];

  return ships.map((s, index) => (
    <div key={index}>
      {index + 1}: {s.name}, {s.type}
    </div>
  ));
}

class Ship {
  constructor(name, type) {
    Object.assign(this, { name, type });
  }
}

export default HMSNameList;
