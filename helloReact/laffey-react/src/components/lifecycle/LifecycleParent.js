import React, { Component } from 'react';

import ErrorBoundary from './ErrorBoundary';
import LifecycleA from './LifecycleA';
import LifecycleB from './LifecycleB';

class LifecycleParent extends Component {
  constructor(props) {
    super(props);

    this.state = {};

    console.log("LifecycleParent constructor");
  }

  render() {
    console.log("LifecycleParent render");
    return (
      <div>
        <LifecycleA />
        <LifecycleB />
        <button onClick={this.handleBtnClick}>Like!</button>
        <ErrorBoundary />
      </div>
    );
  }

  handleBtnClick = () => {
    // Also can: this.forceUpdate();
    this.setState({});
  };

  // Child state/prop change WILL NOT trigger Parent lifecycle (componentDidUpdate)
  //    But Parent state/prop change WILL trigger ALL-Child componentDidUpdate,
  //    though it doesnt necessarily re-render VirtualDOM (diff)
  // Note: except that Child is PureComponent, or customized shouldComponentUpdate()
  componentDidUpdate() {
    console.log("LifecycleParent componentDidUpdate");
  }

  componentDidMount() {
    console.log("LifecycleParent componentDidMount");
  }

  // Do cleanups like cancelling network requests, revmoing event listeners,
  //    cancelling subscriptions or invalidating timers
  // NO EFFECT to call setState() here
  componentWillUnmount() {
    console.log("LifecycleParent componentWillUnmount");
  }

  // error - The error that was thrown.
  // info - An object with a componentStack (stacktrace)
  componentDidCatch(error, info) {
    console.warn("LifecycleParent componentDidCatch", error, info);
  }
}

export default LifecycleParent;
