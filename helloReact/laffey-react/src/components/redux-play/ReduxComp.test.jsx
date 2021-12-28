import { fireEvent, render } from "@testing-library/react";
import React from "react";
import ReduxComp from "./ReduxComp";
import { useDispatch, useMappedState } from "./store";

// should mock entire module in this case
jest.mock("./store");

describe("should work", () => {
    let rendered;
    beforeEach(() => {
        useMappedState.mockImplementation(() => ({ count: 10 }));
        useDispatch.mockImplementation(() => function () {});
        rendered = render(<ReduxComp />);
    });

    afterEach(() => jest.resetAllMocks());

    test("should render", () => {
        const { getByText } = rendered;
        const btn = getByText(/Victory/);
        expect(btn.nodeName).toEqual("BUTTON");
    });

    test("should rerender upon click", () => {
        const { container } = rendered;
        const btn = container.querySelector("#redux-comp-id");
        expect(btn).toBeTruthy();

        fireEvent.click(btn);
        expect(useDispatch).toHaveBeenCalled();
    });
});
