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
    const { children } = this.props;
    const { counter } = this.state;
    return (
      <div>
        <h1>Azur Lane~~</h1>
        {children}
        <button onClick={() => this.incrementCounter()}>Like!+{counter}</button>
      </div>
    );
  }
}

export default Welcome;
