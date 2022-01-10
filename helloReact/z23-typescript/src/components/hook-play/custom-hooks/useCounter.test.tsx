import { renderHook } from '@testing-library/react-hooks';
import { act } from 'react-dom/test-utils';

import useCounter from './useCounter';

describe("should use counter", () => {
    it("should init", () => {
        const { result } = renderHook(() => useCounter());
        expect(result.current[0]).toBe(1);
        expect(typeof result.current[1]).toEqual("function");
    });

    it("should increment", () => {
        const { result } = renderHook(() => useCounter(23));
        act(() => result.current[1]());
        expect(result.current[0]).toBe(24);
    });

    test("should reset", () => {
        const { result, rerender } = renderHook(() => useCounter(23));
        act(() => {
            result.current[1]();
            result.current[1]();
        });
        expect(result.current[0]).toBe(25);
        act(() => result.current[2]());
        expect(result.current[0]).toBe(23);
    });
});
