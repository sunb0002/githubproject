import classNames from "classnames";
import React from "react";
import { useRef } from "react";
import styled from "styled-components";

const StyledSpan = styled.span`
    &.TASK_ARCHIVED {
        color: gray;
    }
    &.TASK_PINNED {
        background-color: red;
        font-weight: bold;
    }
`;

const Task = ({ task: { state, title }, setTitle }) => {
    const inputRef = useRef();
    const handleClick = () => {
        setTitle(inputRef.current.value);
    };

    return (
        <div className={classNames("list-item", state)}>
            <label className="checkbox">
                <input
                    style={{ verticalAlign: "top" }}
                    type="checkbox"
                    defaultChecked={state === "TASK_ARCHIVED"}
                    name="checked"
                />
                <StyledSpan className={classNames("checkbox-custom", state)}>
                    {title}
                </StyledSpan>
            </label>

            <div className="title">
                <input
                    ref={inputRef}
                    type="text"
                    defaultValue={title}
                    placeholder="Input title"
                />
            </div>
            <button type="button" onClick={handleClick}>
                Change
            </button>
        </div>
    );
};

export default Task;
