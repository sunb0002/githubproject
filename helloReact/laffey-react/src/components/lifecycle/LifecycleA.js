import React, { Component } from 'react';

class LifecycleA extends Component {
  constructor(props) {
    super(props);

    this.state = {
      name: "Warspite Retrofit 1"
    };

    console.log("LifecycleA constructor");
  }

  // DO NOT cause side-effects here (Ajax calls, DOM operations)
  render() {
    console.log("LifecycleA render");
    return <div>{this.state.name}</div>;
  }

  // GOOD to cause side-effects
  // Similar to Angular "afterViewInit()"
  componentDidMount() {
    console.log("LifecycleA componentDidMount");
  }
}

export default LifecycleA;

// List of all React LifeCycle methods (with picture)
// https://levelup.gitconnected.com/componentdidmakesense-react-lifecycle-explanation-393dcb19e459
// React official: https://reactjs.org/docs/react-component.html
// Note: some methods like UNSAFE_componentWillUpdate are already expired.
//
// Mounting – Birth of your component
// Update – Growth of your component
// Unmount – Death of your component
