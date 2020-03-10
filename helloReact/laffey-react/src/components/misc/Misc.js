import React, { Component } from 'react';

import { UserProvider } from '../context/UserContext';
import FragmentDemo from './FragmentDemo';
import MemoComp from './MemoComp';
import MobxDemo from './mobx/MobxDemo';
import PortalDemo from './PortalDemo';
import PureComp from './PureComp';
import RefDemo from './RefDemo';

class Misc extends Component {
  render() {
    return (
      <div>
        <UserProvider value="manjuu">
          <FragmentDemo />
          <PureComp />
          <MemoComp cheer="Watashi wa numba one!" />
          <RefDemo />
          <PortalDemo />
        </UserProvider>

        <MobxDemo />
      </div>
    );
  }
}

export default Misc;
