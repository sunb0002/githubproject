import React, { Component } from 'react';

import AkashiStore from './AkashiStore';

class MobxDemo extends Component {
  render() {
    return <div></div>;
  }

  componentDidMount() {
    const observableTodoStore = new AkashiStore();
    observableTodoStore.addTodo("read MobX tutorial");
    observableTodoStore.addTodo("try MobX");
    observableTodoStore.todos[0].completed = true;
    observableTodoStore.todos[1].task = "try MobX in own project";
    observableTodoStore.todos[0].task = "grok MobX tutorial";
  }
}

export default MobxDemo;
