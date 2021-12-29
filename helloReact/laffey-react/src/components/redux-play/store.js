import { combineReducers, createStore } from "redux";
import { create } from "redux-react-hook";

export const INIT_STATE = { count: 10 };
const countReducer = (state = INIT_STATE, action) => {
    switch (action.type) {
        case "ADD": {
            console.log("ADD triggered", state);
            return {
                ...state,
                count: state.count + 1,
            };
        }
        case "MINUS":
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
// combineReducers expects two separate reducers and no shared state.
const rootReducer = combineReducers({
    COUNTER: countReducer,
    DUMMY: anotherReducer,
});

export function makeStore() {
    return createStore(rootReducer);
}
export const { StoreContext, useDispatch, useMappedState } = create();
