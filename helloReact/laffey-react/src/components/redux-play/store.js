import { applyMiddleware, combineReducers, createStore } from "redux";
import { create } from "redux-react-hook";
import thunk from "redux-thunk";

import { COUNTER_ACTION } from "./constants";

export const INIT_STATE = { count: 10 };
const countReducer = (state = INIT_STATE, action) => {
    switch (action.type) {
        case COUNTER_ACTION.ADD: {
            console.log("ADD triggered", state);
            return {
                ...state,
                count: state.count + 1,
            };
        }
        case COUNTER_ACTION.MINUS:
            return {
                ...state,
                count: state.count - 1,
            };
        default:
            return state;
    }
};
const anotherReducer = (state = {}, action) => {
    switch (action.type) {
        default:
            return state;
    }
};

// combineReducers expects 3 separate reducers and no shared state.
// need to use Slice/RTK if to share state between reducers
const rootReducer = combineReducers({
    COUNTER: countReducer,
    DUMMY: anotherReducer,
});

// use thunk middleware to handle function-type actions.
export function makeStore() {
    return createStore(rootReducer, applyMiddleware(thunk));
}
export const { StoreContext, useDispatch, useMappedState } = create();
