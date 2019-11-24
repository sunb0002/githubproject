import React, { Component } from "react";

class Welcome extends Component {
  constructor() {
    super();
    this.state = {
      counter: 0
    };
  }

  incrementCounter() {
    this.setState(prevState => ({
      counter: prevState.counter + 1
    }));
  }

  render() {
    return (
      <div>
        <h1>Azur Lane~~</h1>
        {this.props.children}
        <button onClick={() => this.incrementCounter()}>
          Like!+{this.state.counter}
        </button>
      </div>
    );
  }
}

export default Welcome;
