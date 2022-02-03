import "./App.css";

import { GqlApp } from "./components/gql/GqlApp";
import { Mui1 } from "./components/misc/Mui1";
import { Mui2 } from "./components/misc/Mui2";

function App() {
    return (
        <div className="App">
            <Mui1 />
            <hr />
            <Mui2 />
            <hr />
            <GqlApp />
            <hr />
        </div>
    );
}

export default App;
