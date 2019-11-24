import React, { Component } from "react";

class Welcome extends Component {
  render() {
    return (
      <div>
        <h1>Azur Lane~~</h1>
        a.k.a {this.props.children}
      </div>
    );
  }
}

export default Welcome;
