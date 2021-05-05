import './App.css';

import React from 'react';

import GreetBtn from './components/GreetBtn';
import useImportScript from './components/hook-play/custom-hooks/useImportScript';
import Z23 from './components/Z23';
import Z46 from './components/Z46';
import logo from './logo.svg';

const App = () => {
    useImportScript(
        "https://cdn.jsdelivr.net/gh/stevenjoezhang/live2d-widget@latest/autoload.js"
    );

    return (
        <div className="App">
            <header className="App-header">
                <img src={logo} className="App-logo" alt="logo" />

                <GreetBtn />
                <Z23 />
                <Z46 />
            </header>
        </div>
    );
};

export default App;
