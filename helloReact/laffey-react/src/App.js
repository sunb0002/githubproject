import './App.css';

import React from 'react';

import Greet from './components/Greet';
import HMSNameList from './components/HMSNameList';
import Stylesheet from './components/stylesheet/Stylesheet';
import Welcome from './components/Welcome';

function App() {
  return (
    <div className="App">
      {/* Laffey's greetings */}
      <Welcome>#azurlane_anime #azurlane_official</Welcome>
      {/* The Starter fleet */}
      <hr />
      <Greet name="Laffey" type="DD" />
      <Greet name="Javelin" type="DD" />
      <Greet name="Z23" type="DD" />
      <hr />
      {/* The Royal Navy */}
      <HMSNameList />
      <hr />
      <Stylesheet />
      <hr />
    </div>
  );
}

export default App;
