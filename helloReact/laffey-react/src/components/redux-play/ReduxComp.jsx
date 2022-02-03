import React from "react";
import { useAsync } from "react-async-hook";

import { COUNTER_ACTION, mySleep } from "./constants";
import { useDispatch, useMappedState } from "./store";

export default function ReduxComp() {
    const { count = 1 } = useMappedState((state) => state.COUNTER);
    const dispatch = useDispatch();
    const clickHandler = () => dispatch({ type: COUNTER_ACTION.ADD });
    const clickHandler2 = () =>
        dispatch(async (disp) => {
            await mySleep(1000);
            disp({ type: COUNTER_ACTION.MINUS });
        });
    const asyncSleep = useAsync(mySleep, [2000]);

    return (
        <div>
            Redux Component~
            {asyncSleep.loading
                ? "[Loading...]"
                : "[Loaded!]"} VictoryCount: {count}
            <button id="redux-comp-id" onClick={clickHandler}>
                Sync Add
            </button>
            <button id="redux-comp-id-async" onClick={clickHandler2}>
                Async Minus
            </button>
        </div>
    );
}
