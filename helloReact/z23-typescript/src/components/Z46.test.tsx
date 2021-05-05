import { fireEvent, render } from '@testing-library/react';
import React from 'react';

import useCounter from './hook-play/custom-hooks/useCounter';
import Z46 from './Z46';

jest.mock("./hook-play/custom-hooks/useCounter");

describe("should work", () => {
    const incrementMock = jest.fn().mockImplementation(() => {});
    const useCounterMock = useCounter as jest.Mock;

    beforeEach(() => {
        useCounterMock.mockReturnValue({
            count: 10,
            increment: incrementMock,
        });
    });

    afterEach(() => jest.resetAllMocks());

    test("should render", () => {
        const { container, getByText } = render(<Z46 />);

        const elm = container.querySelector("button");
        expect(elm).toBeTruthy();
        expect(elm?.textContent).toContain(10);

        const elmByText = getByText(/Z46/);
        expect(elmByText.nodeName).toEqual("BUTTON");
    });

    test("should render with mock", () => {
        useCounterMock.mockReturnValue({
            count: 23,
            increment: incrementMock,
        });

        const { container, debug } = render(<Z46 />);
        debug();

        fireEvent.click(container.querySelector("button")!);
        expect(incrementMock).toBeCalledTimes(1);
    });
});
