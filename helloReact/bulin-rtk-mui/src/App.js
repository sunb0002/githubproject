import "./App.css";

import { Mui1 } from "./components/misc/Mui1";
import { Mui2 } from "./components/misc/Mui2";
import { RtkApp } from "./components/rtk/RtkApp";

function App() {
    return (
        <div className="App">
            <Mui1 />
            <hr />
            <Mui2 />
            <hr />
            <RtkApp />
        </div>
    );
}

export default App;
