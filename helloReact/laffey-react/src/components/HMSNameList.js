import React from "react";

function HMSNameList() {
  const ships = [
    new Ship("Hood", "BB"),
    new Ship("Belfast", "CL"),
    new Ship("Formidable", "CV")
  ];

  return ships.map(({ name, type }, index) => (
    <div key={index}>
      {/* Generally, using index as key is anti-pattern in React. 
      It may cause rendering problem because of VirtualDOM diff. */}
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
