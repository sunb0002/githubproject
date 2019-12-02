import React, { Component } from 'react';

class Form extends Component {
  constructor(props) {
    super(props);

    this.state = {
      taskname: "oil excavation",
      reward: "oil"
    };

    // Preserve the inital state
    this.baseState = this.state;
  }

  //   handleNameChange = event => {
  //     this.setState({
  //       taskname: event.target.value
  //     });
  //   };
  //   handleRewardChange = event => {
  //     this.setState({
  //       reward: event.target.value
  //     });
  //   };
  handleAnyChange = key => event => {
    this.setState({
      [key]: event.target.value
    });
  };

  handleSubmit = () => {
    window.confirm(
      `Commission started~ ${this.state.taskname}=>${this.state.reward}`
    );
  };

  handleReset = () => {
    this.setState(this.baseState);
  };

  render() {
    return (
      <form>
        <div>
          <label>Commission: </label>
          <input
            type="text"
            value={this.state.taskname}
            onChange={this.handleAnyChange("taskname")}
          />
        </div>
        <div>
          <label>Reward: </label>
          <select
            value={this.state.reward}
            onChange={this.handleAnyChange("reward")}
          >
            <option value="oil">OIL</option>
            <option value="gold">GOLD</option>
            <option value="loli">LOLI</option>
          </select>
        </div>
        {/* 也可以用<form onSubmit={xxx}>, 但是xxx的最后要放event.preventDefault()防止刷新页面 */}
        <button type="button" onClick={this.handleSubmit}>
          Submit
        </button>
        <button type="reset" onClick={this.handleReset}>
          Reset
        </button>
      </form>
    );
  }
}

export default Form;
