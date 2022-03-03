import React, { Component, useMemo, useRef, useState } from "react";

class Form extends Component {
    constructor(props) {
        super(props);

        this.state = {
            taskname: "oil excavation",
            reward: "oil",
        };

        // Preserve the inital state
        this.baseState = this.state;
    }

    handleAnyChange = (key) => (event) => {
        this.setState({
            [key]: event.target.value,
        });
    };
    //   handleNameChange = event => {
    //     this.setState({
    //       taskname: event.target.value
    //     });
    //   };
    //   handleRewardChange = event => {
    //     this.setState({
    //       reward: event.target.value
    //     });
    //   };

    handleSubmit = () => {
        window.confirm(
            `Commission started~ ${this.state.taskname}=>${this.state.reward}`
        );
    };

    handleReset = () => {
        this.setState(this.baseState);
    };

    render() {
        return (
            <>
                <form>
                    <div>
                        <label>Commission: </label>
                        <input
                            type="text"
                            value={this.state.taskname}
                            onChange={this.handleAnyChange("taskname")}
                        />
                    </div>
                    <div>
                        <label>Reward: </label>
                        <select
                            value={this.state.reward}
                            onChange={this.handleAnyChange("reward")}
                        >
                            <option value="oil">OIL</option>
                            <option value="gold">GOLD</option>
                            <option value="loli">LOLI</option>
                        </select>
                    </div>
                    {/* 也可以用<form onSubmit={xxx}>, 但是xxx的最后要放event.preventDefault()防止刷新页面 */}
                    <button type="button" onClick={this.handleSubmit}>
                        Submit
                    </button>
                    <button type="reset" onClick={this.handleReset}>
                        Reset
                    </button>
                </form>

                <FunForm></FunForm>
            </>
        );
    }
}

export default Form;

// If we want to pass input value without using onChange,
// we need to either use uncontrolled form (and handle submit), or references
// useRef的另一个经典用法是always scroll to bottom after update
// dummyRef.current.scrollIntoView({behavior: 'smooth'});
const FunForm = () => {
    const inputRef = useRef("");
    const [text, setText] = useState("");
    const clickHandler = () => {
        setText(inputRef.current.value);
    };

    const expensiveCalculation = () => {
        console.log("Expensive! Use Memo!");
        return 0;
    };
    const [x] = useState(0);
    // When "text" state updates, this caculation also re-runs, even it doesn't depend on "text".
    // So we should useMemo, which specifies "x" as dependent state.
    // useMemo返回一个memoized值, useCallback返回一个memoized function，例如xxxHandler.
    // useCallback不仅帮助memoize，更重要是防止child comp always rerender when xxxHandler changes.
    // const totalX = expensiveCalculation(x); ❌
    const totalX = useMemo(() => expensiveCalculation(x), [x]); // ✅

    return (
        <div>
            Wishing Well: <input ref={inputRef} />
            <button onClick={clickHandler}>Make Wish!</button>
            <span style={{ color: "red" }}>
                {!!text ? `I wish for ~${text}~` : ""}{" "}
            </span>
        </div>
    );
};
