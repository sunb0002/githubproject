import './App.css';

import React from 'react';

import GreetBtn from './components/GreetBtn';
import Z23 from './components/hook-play/Z23';
import logo from './logo.svg';

const App = () => {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />

        <GreetBtn />
        <Z23 />
      </header>
    </div>
  );
};

export default App;
