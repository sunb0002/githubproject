import React from "react";
import { useDispatch, useMappedState } from "./store";

export default function ReduxComp() {
    const { COUNTER } = useMappedState((state) => state);
    const dispatch = useDispatch();
    const clickHandler = () => dispatch({ type: "ADD" });
    return (
        <div>
            Redux Component~
            <button id="redux-comp-id" onClick={clickHandler}>
                Victory Count: {COUNTER.count}
            </button>
        </div>
    );
}
