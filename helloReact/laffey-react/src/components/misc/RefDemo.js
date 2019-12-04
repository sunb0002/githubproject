import React, { Component } from 'react';


class RefDemo extends Component {
  constructor(props) {
    super(props);
    this.inputRef = React.createRef();
  }

  handleBtnClick = () => {
    window.confirm(
      "Your goddess deserves all the attention: " + this.inputRef.current.value
    );
  };

  render() {
    return (
      <div>
        {/* "ref" is reserved keyword */}
        <input
          type="text"
          defaultValue="ichi ni san diego!"
          ref={this.inputRef}
        />
        <button onClick={this.handleBtnClick}>Attention!</button>
      </div>
    );
  }

  componentDidMount() {
    console.log("RefDemo inputRef:", this.inputRef);
    // Directly access DOM element
    this.inputRef.current.focus();
  }
}

export default RefDemo;
