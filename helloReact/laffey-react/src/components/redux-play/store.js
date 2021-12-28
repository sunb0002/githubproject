import { createStore } from "redux";
import { create } from "redux-react-hook";

export const INIT_STATE = { count: 10 };
const reducer = (state, action) => {
    switch (action.type) {
        case "ADD":
            return {
                ...state,
                count: state.count + 1,
            };
        case "MINUS":
            return {
                ...state,
                count: state.count - 1,
            };
        default:
            return state;
    }
};

export function makeStore() {
    return createStore(reducer, INIT_STATE);
}
export const { StoreContext, useDispatch, useMappedState } = create();
