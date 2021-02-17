import React, { useDebugValue, useEffect, useReducer, useState } from 'react';

function Z23() {

    const [flag, setFlag] = useState(false);
    const [count, setCount] = useNimi();

    useEffect(() => {
        console.log("Effect start!");
        return () => {
            console.log("Effect cleaned!");
        }
    });

    const count2Reducer = (state, action) => {
        // switch for action
        return state - 1;
    }
    const [count2, dispatch] = useReducer(count2Reducer, 0);


    const clickHandler = () => {
        setFlag(!flag);
        setCount(count + 1);
        dispatch({});
    }
    return (
        <div>
            <button onClick={clickHandler}>ClickNimi {flag && `*Married!*`} {`+${count} ${count2}`}</button>
        </div>
    )
}

function useNimi() {
    const [count, setCount] = useState(0);
    useDebugValue(count);
    return [count, setCount];
}

export default Z23;
