import { observer } from 'mobx-react';
import React, { Component } from 'react';

import observableTodoStore from './AkashiStore';


const TodoList = observer(
  class TodoList extends Component {
    render() {
      const store = this.props.store;
      return (
        <div>
          {store.report}
          <ul>
            {store.todos.map((todo, idx) => (
              <TodoView todo={todo} key={idx} />
            ))}
          </ul>
          {store.pendingRequests > 0 ? <div>Loading...</div> : null}
          <button onClick={this.onNewTodo}>New Research</button>
          <small> (double-click a research to edit)</small>
        </div>
      );
    }

    onNewTodo = () => {
      this.props.store.addTodo(prompt("Enter a new research ship: "));
    };
  }
);

const TodoView = observer(
  class TodoView extends Component {
    render() {
      const todo = this.props.todo;
      return (
        <div onDoubleClick={this.onRename}>
          <input
            type="checkbox"
            checked={todo.completed}
            onChange={this.onToggleCompleted}
          />
          {todo.task}
          {todo.assignee ? <small>{todo.assignee.name}</small> : null}
        </div>
      );
    }

    onToggleCompleted = () => {
      const todo = this.props.todo;
      todo.completed = !todo.completed;
    };

    onRename = () => {
      const todo = this.props.todo;
      todo.task = prompt("Ship name", todo.task) || todo.task;
    };
  }
);

class MobxDemo extends Component {

  render() {
    return <div>
      <TodoList store={observableTodoStore} />
    </div>;
  }

  componentDidMount() {
    observableTodoStore.addTodo("Saint Louis");
  }

}

export default MobxDemo;
