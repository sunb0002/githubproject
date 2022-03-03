import React, { useEffect, useReducer, useState } from 'react';

function Z23() {

    const [time, setTime] = useState(1);
    useEffect(() => {
        const id = setInterval(() => setTime(time + 1), 1000);
        return () => clearInterval(id);
    }/* , [] */); // useEffect每次state变化导致render都会跑一次, 可以用[depend-list]适量优化


    const reducer = (state, action) => {
        // switch-case on action
        console.log(`Marry me Nimi! x ${state}`)
        return state + 1;
    }
    // useReducer很像useState, 99 is initial state.
    // 关键不同是reducer fn, 一般是因为要用到switch-case
    const [state, dispatch] = useReducer(reducer, 99);

    const clickHandler = () => {
        dispatch("add");
        setTime(1);
    }


    return (
        <div>
            <button onClick={clickHandler}>Click Z23 Nimi: {time}</button>
        </div>
    )
}


export default Z23;
