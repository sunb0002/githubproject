import React, { Component } from 'react';

class ButtonRefComp extends Component {
  
  cheer = "San Diego!";

  clickHandler = () => {
    alert(this.cheer);
  };

  render() {
    return (
      <button type="button" onClick={this.clickHandler} value={this.cheer}>
        Attention2!
      </button>
    );
  }
}

export default ButtonRefComp;
