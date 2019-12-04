import React, { Component } from 'react';

import FragmentDemo from './FragmentDemo';
import MemoComp from './MemoComp';
import PureComp from './PureComp';
import RefDemo from './RefDemo';

class Misc extends Component {
  render() {
    return (
      <div>
        <FragmentDemo />
        <PureComp />
        <MemoComp cheer="Watashi wa numba one!" />
        <RefDemo />
      </div>
    );
  }
}

export default Misc;
