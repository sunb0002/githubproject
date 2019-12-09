import React, { Component } from 'react';

import ButtonRefComp from './ButtonRefComp';
import ButtonRefComp2 from './ButtonRefComp2';

class RefDemo extends Component {
  constructor(props) {
    super(props);
    this.inputRef = React.createRef();
    this.componentRef = React.createRef();
    this.fwdComponentRef = React.createRef();
  }

  handleBtnClick = () => {
    // (1) Directly access DOM element
    this.inputRef.current.focus();
    // (2) Let parent control child with a component ref, and directly access child methods
    // (3) Let parent control grand-child with forward ref
    alert(
      `Your goddess deserves all the attention:
      1-${this.inputRef.current.value}
      2-${this.componentRef.current.cheer}
      3-${this.fwdComponentRef.current.value}`
    );
  };

  render() {
    return (
      <div>
        {/* "ref" is reserved keyword */}
        {/* "ref" cannot be attached to functional component */}
        <input
          type="text"
          defaultValue="Ichi Ni San Diego!"
          ref={this.inputRef}
        />
        <button onClick={this.handleBtnClick}>Attention1!</button>
        <ButtonRefComp ref={this.componentRef} />
        <ButtonRefComp2 ref={this.fwdComponentRef} />
      </div>
    );
  }

  componentDidMount() {
    console.log("RefDemo inputRef:", this.inputRef);
  }
}

export default RefDemo;
