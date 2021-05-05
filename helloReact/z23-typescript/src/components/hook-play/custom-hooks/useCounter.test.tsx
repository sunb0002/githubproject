import { renderHook } from '@testing-library/react-hooks';
import { act } from 'react-dom/test-utils';

import useCounter from './useCounter';

describe("should use counter", () => {
    it("should init", () => {
        const { result } = renderHook(() => useCounter());
        expect(result.current.count).toBe(1);
        expect(typeof result.current.increment).toEqual("function");
    });

    it("should increment", () => {
        const { result } = renderHook(() => useCounter(23));
        act(() => result.current.increment());
        expect(result.current.count).toBe(24);
    });

    test("should reset", () => {
        const { result, rerender } = renderHook(() => useCounter(23));
        act(() => {
            result.current.increment();
            result.current.increment();
        });
        expect(result.current.count).toBe(25);
        act(() => result.current.reset());
        expect(result.current.count).toBe(23);
    });
});
