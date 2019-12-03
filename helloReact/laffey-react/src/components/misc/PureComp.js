import React, { PureComponent } from 'react';

/**
 * A pure component implements shouldComponentUpdate() with a shallow comparison on props and state.
 * Shallow comparison means (1) Diff value for primitive type data (2) Diff ref for ref type data
 *
 * This boosts performance.
 * Just note that don't mutate ref-type state in pure component, as it won't work.
 *
 * Meanwhile, regular component has default shouldComponentUpdate() which always return true,
 * hence always executes render() on prop/state changes
 */
class PureComp extends PureComponent {
  render() {
    console.log("PureComp render");
    return <div>Saratoga chan also numba one!</div>;
  }
}

export default PureComp;
