import { useState } from 'react';

function useCounter(initVal = 1) {
    const [count, setCount] = useState(initVal);
    const increment = () => setCount((x) => x + 1);
    const reset = () => setCount(initVal);
    return { count, increment, reset };
}

export default useCounter;
