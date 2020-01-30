import './App.css';

import React from 'react';

import Task from './components/Task';

function App() {
  const taskInput = {
    id: "001",
    title: "Javelin",
    state: "TASK_PINNED"
  };
  return (
    <div className="App">
      <Task task={taskInput} />
    </div>
  );
}

export default App;
