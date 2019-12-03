import React, { Component } from 'react';

class LifecycleB extends Component {
  constructor(props) {
    super(props);

    this.state = {
      name: "Warspite Retrofit 2",
      level: 0
    };

    console.log("LifecycleB constructor");
  }

  handleBtnClick = () => {
    this.setState(prevState => ({
      level: prevState.level + 1
    }));
  };

  // DO NOT cause side-effects here
  // MUST return true/false
  // Useful for performance optimization, but still rarely used
  shouldComponentUpdate(nextProps, nextState) {
    console.log("LifecycleB shouldComponentUpdate");
    return true;
  }

  render() {
    console.log("LifecycleB render");
    return (
      <div>
        {this.state.name}+{this.state.level}
        <button onClick={this.handleBtnClick}>More!</button>
      </div>
    );
  }

  // GOOD to cause side-effects here: avoid unwanted Ajax calls
  // snapshot will be available if we implement getSnapshotBeforeUpdate() method
  componentDidUpdate(prevProps, prevState, snapshot) {
    console.log("LifecycleB componentDidUpdate");
  }

  componentDidMount() {
    console.log("LifecycleB componentDidMount");
  }
}

export default LifecycleB;
