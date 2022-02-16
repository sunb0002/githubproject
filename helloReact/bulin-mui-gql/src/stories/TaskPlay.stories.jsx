import { screen, userEvent } from "@storybook/testing-library";
import React from "react";
import { useState } from "react";

import Task from "./Task";

export default {
    component: Task,
    title: "Example/TaskPlay",
    argTypes: {
        btnBg: {
            description: "Background color of the button (Autoplay version~)",
            options: ["red", "green", "blue"],
            control: { type: "radio" },
        },
    },
    parameters: {
        actions: {
            handles: ["click button"],
        },
    },
};

const buildTemplate = (state) => (argTypes) => {
    const [title, setTitle] = useState("Play Title");
    const props = {
        task: {
            state,
            title,
        },
        setTitle,
        btnBg: argTypes.btnBg,
    };
    return <Task {...props} />;
};

export const Pinned = buildTemplate("TASK_PINNED");
Pinned.play = async () => {
    const inputElm = screen.getByRole("textbox");
    userEvent.clear(inputElm);
    await userEvent.type(inputElm, "New Title Lah", { delay: 300 });
    const buttonElm = screen.getByRole("button");
    userEvent.click(buttonElm);
};
