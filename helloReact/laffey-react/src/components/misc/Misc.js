import React, { Component } from 'react';

import FragmentDemo from './FragmentDemo';
import PureComp from './PureComp';

class Misc extends Component {
  render() {
    return (
      <div>
        <FragmentDemo />
        <PureComp />
      </div>
    );
  }
}

export default Misc;
