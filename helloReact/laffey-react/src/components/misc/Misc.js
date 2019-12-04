import React, { Component } from 'react';

import FragmentDemo from './FragmentDemo';
import MemoComp from './MemoComp';
import PureComp from './PureComp';

class Misc extends Component {
  render() {
    return (
      <div>
        <FragmentDemo />
        <PureComp />
        <MemoComp cheer="Watashi wa numba one!" />
      </div>
    );
  }
}

export default Misc;
