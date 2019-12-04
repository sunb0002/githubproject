import React, { Component } from 'react';

import InputRefComp from './InputRefComp';

class RefDemo extends Component {
  constructor(props) {
    super(props);
    this.inputRef = React.createRef();
    this.componentRef = React.createRef();
  }

  handleBtnClick = () => {
    // Directly access DOM element
    this.inputRef.current.focus();
    // Let parent control child with a component ref, and directly access child methods
    this.componentRef.current.clickHandler();
    alert(
      "Your goddess deserves all the attention: " + this.inputRef.current.value
    );
  };

  render() {
    return (
      <div>
        {/* "ref" is reserved keyword */}
        {/* "ref" cannot be attached to functional component */}
        <input
          type="text"
          defaultValue="ichi ni san diego!"
          ref={this.inputRef}
        />
        <button onClick={this.handleBtnClick}>Attention!</button>
        <InputRefComp ref={this.componentRef} />
      </div>
    );
  }

  componentDidMount() {
    console.log("RefDemo inputRef:", this.inputRef);
  }
}

export default RefDemo;
