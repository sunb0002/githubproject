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
    console.log("LifecycleA componentDidMount, calling HTTP fetch.");

    // Fetch API
    // Note1: Fetch promises only reject with a TypeError when a network error occurs.
    //    Since 4xx and 5xx responses aren't network errors, there's nothing to catch.
    //    You'll need to throw an error yourself, checking "response.ok" or "response.status==404"
    // Note2: Fetch基本可以取代Axios，但是没有内置HTTP Interceptor。
    //    需要改fetch的prototype，或者用三方库"fetch-intercept"
    fetch("https://jsonplaceholder.typicode.com/todos/1")
      .then(resp => resp.json())
      .then(body => console.warn("Ajax fetched response body:", body))
      .catch(err => console.warn("Ajax fetch error! Details:", err));
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
