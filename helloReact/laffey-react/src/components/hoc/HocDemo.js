import React, { Component } from 'react';

import withCounter from './withCounter';

/**
 * Note: HOC相当于mixin ES6 classes
 *    HOC缺点是需要一些繁文缛节/额外layer，对于简单的code reuse，直接传递一个render prop(就是一个function as param)就好了
 */
class HocDemo extends Component {
  render() {
    return (
      <div>
        <GoldBulinMkII />
        <SilverBulinMkII />
      </div>
    );
  }
}

export default HocDemo;

class GoldBulin extends Component {
  render() {
    const { count, incrementCounter } = this.props;
    const btnStyle = {
      backgroundColor: "Gold"
    };
    return (
      <button style={btnStyle} onMouseOver={incrementCounter}>
        GoldBulin ~ {count}
      </button>
    );
  }
}
class SilverBulin extends Component {
  render() {
    const { count, incrementCounter } = this.props;
    const btnStyle = {
      backgroundColor: "Silver"
    };
    return (
      <button style={btnStyle} onClick={incrementCounter}>
        SilverBulin ~ {count}
      </button>
    );
  }
}

const GoldBulinMkII = withCounter(GoldBulin);
const SilverBulinMkII = withCounter(SilverBulin);
