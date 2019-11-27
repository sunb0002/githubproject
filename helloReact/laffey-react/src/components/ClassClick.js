import React, { Component } from "react";

class ClassClick extends Component {
  constructor(props) {
    super(props);

    this.state = {
      flag: true
    };
  }

  // 3 options to handle "this" binding

  // Option 1: bind in render()
  // <button onClick={this.clickHandler.bind(this)}></button>
  // or
  // <button onClick={() => this.clickHandler()}></button>
  // 利用Function.prototype.bind()把clickHanlder方法的this指定为当前this，或者用arrow function
  // 但是因为改动state/props导致触发render()的时候，
  // 每次render()都会给这个button返回一个brand-new handler
  // React的VirtualDOM会认为button change了所以re-render button
  // 更可怕的是如果有100个component instance，任意一个click都会re-render全部button的DOM
  // 性能差，不建议用
  // 详情看这里
  // https://www.freecodecamp.org/news/the-best-way-to-bind-event-handlers-in-react-282db2cf1530/

  // Option 2: bind in constructor()
  // (In constructor) this.clickHandler = this.clickHandler.bind(this);
  // <button onClick={this.clickHandler}></button>
  //  不管render()跑多少次，bind只发生一次，button的DOM不会re-render
  // 目前的官方推荐用法

  // Option 3: arrow function in render()
  // 直接用arrow function隔离this的scope，
  // 并且clickHandler写法是ES7的Class Initializer，相当于在constructor注册
  // ES7确定后取代Option 2

  clickHandler = () => this.setState(prevState => ({ flag: !prevState.flag }));
  //   clickHandler() {
  //     this.setState({message: "Bye Shikikan"});
  //   }

  getMessage() {
    return `${this.state.flag ? "Hello" : "Bye"} Shikikan`;
  }

  render() {
    return (
      <div>
        <span>{this.getMessage()}</span>
        <button onClick={this.clickHandler}>Event-text!</button>
      </div>
    );
  }
}

export default ClassClick;
