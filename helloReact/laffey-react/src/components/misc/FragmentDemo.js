import React, { Component } from 'react';

import { UserContext } from '../context/UserContext';

function FragmentDemo() {
  return (
    <>
      <span>San Diego is numba one!</span>
      <FragmentDemoChild />
      {/* React context.consumer, after ReactV16.6 replaced by static-contextType */}
      {/* <UserConsumer>
        {val => console.warn(`Context consumer received value: ${val}`)}
      </UserConsumer> */}
    </>
  );
}

class FragmentDemoChild extends Component {
  static contextType = UserContext;

  render() {
    console.warn("Context consumer received value:", this.context);
    return <></>;
  }
}

export default FragmentDemo;
