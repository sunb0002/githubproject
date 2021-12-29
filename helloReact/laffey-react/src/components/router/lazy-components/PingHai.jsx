import React from "react";
import { useHistory } from "react-router-dom";

const PingHai = () => {
    let history = useHistory();
    const clickHandler = () => history.push({ pathname: "./ninghai"});

    return (
        <div>
            PingHai Manju~
            <button onClick={clickHandler}>Toggle</button>
        </div>
    );
};

export default PingHai;
