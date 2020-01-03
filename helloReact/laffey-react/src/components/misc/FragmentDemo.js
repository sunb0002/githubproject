import React from 'react';

import { UserConsumer } from '../context/UserContext';

function FragmentDemo() {
  return (
    // <React.Fragment></React.Fragment>
    <>
      <span>San Diego is numba one!</span>
      {/* React context.consumer */}
      <UserConsumer>
        {val => console.warn(`Context consumer received value: ${val}`)}
      </UserConsumer>
    </>
  );
}

export default FragmentDemo;
