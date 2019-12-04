import React, { Component } from 'react';

class InputRefComp extends Component {
  clickHandler = () => {
    console.log("This component is:", this._reactInternalFiber.elementType.name);
    alert("San Diego!");
  };

  render() {
    return (
      <button type="button" onClick={this.clickHandler}>
        Attention2!
      </button>
    );
  }
}

export default InputRefComp;
