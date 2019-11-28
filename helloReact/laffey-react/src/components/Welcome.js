import React, { Component } from "react";

import ClassClick from "./ClassClick";
import FunctionClick from "./FunctionClick";

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

  parentCounter = () => console.log(`Parent counter is: ${this.state.counter}`);

  render() {
    const { children } = this.props;
    const { counter } = this.state;
    return (
      <div>
        <h1>Azur Lane~~</h1>
        <div>{children}</div>

        {/* 下面button的event binding性能很差，参考ClassClick.js的解释 */}
        <button onClick={() => this.incrementCounter()}>Like!+{counter}</button>

        {/* Child components */}
        <FunctionClick parentHandler={this.parentCounter} />
        <ClassClick />
        <ClassClick />
      </div>
    );
  }
}

export default Welcome;
