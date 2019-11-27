import './App.css';

import React from 'react';

import Greet from './components/Greet';
import Welcome from './components/Welcome';

function App() {
  return (
    <div className="App">
      {/* Laffey's greetings */}
      <Welcome>#azurlane_anime #azurlane_official</Welcome>
      <Greet name="Laffey" type="DD" />
      <Greet name="Javelin" type="DD" />
      <Greet name="Z23" type="DD" />
    </div>
  );
}

export default App;
