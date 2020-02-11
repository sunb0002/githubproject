import React, { Component } from 'react';

interface Props {}
interface State {
  flag?: boolean;
}

export default class GreetBtn extends Component<Props, State> {
  constructor(props: Props) {
    super(props);

    this.state = {
      flag: true
    };
  }

  clickHandler = () => {
    this.setState(prevState => ({ flag: !prevState.flag } as State));
  };

  render() {
    return (
      <div>
        <span>
          {this.state.flag
            ? "Guten tag, commander~ "
            : "My new skin, commander? "}
        </span>
        <button onClick={this.clickHandler}>ClickZ404</button>
      </div>
    );
  }
}
