import logo from './logo.svg';
import './App.css';
import MyButton from 'sunb0002-my-lib';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        This is a button from my-lib:
        
        <MyButton />
        
      </header>
    </div>
  );
}

export default App;
