import React from "react";
import ReduxComp from "./ReduxComp";
import { makeStore, StoreContext } from "./store";

const store = makeStore();

export default function ReduxApp() {
    return (
        <StoreContext.Provider value={store}>
            <ReduxComp />
        </StoreContext.Provider>
    );
}
