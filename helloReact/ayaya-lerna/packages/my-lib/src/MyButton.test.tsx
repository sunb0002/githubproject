import { fireEvent, render } from '@testing-library/react';
import React from 'react';

import MyButton from './MyButton';

describe("MyButton should work", () => {
    test("should render", () => {
        const { getByText } = render(<MyButton />);
        const elmByText = getByText(/Default text/);
        expect(elmByText.nodeName).toEqual("BUTTON");
    });

    test("should alert", () => {
        jest.spyOn(window, "alert").mockImplementationOnce(() => {});
        const { container } = render(<MyButton />);
        fireEvent.click(container.querySelector("button")!);
        expect(window.alert).toBeCalledTimes(1);
    });
});
