import React, { Component } from 'react';

import withCounter from './withCounter';

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
