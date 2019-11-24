import "./App.css";

import React from "react";

import Greet from "./components/Greet";
import logo from "./logo.svg";
import Welcome from "./components/Welcome";

function App() {
  return (
    <div className="App">
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header> */}

      {/* Laffey's greetings */}
      <Welcome>#azurlane_anime #azurlane_official</Welcome>
      <Greet name="Laffey" type="DD" />
      <Greet name="Javelin" type="DD" />
      <Greet name="Z23" type="DD" />
    </div>
  );
}

export default App;
