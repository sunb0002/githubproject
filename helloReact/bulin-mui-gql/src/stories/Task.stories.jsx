import React from "react";
import { useState } from "react";

import Task from "./Task";

export default {
    component: Task,
    title: "Example/Task",
    argTypes: {
        btnBg: {
            description: "Background color of the button (see 'Docs' tab)",
            table: {
                type: {
                    summary: "Something short",
                    detail: "Something really really long",
                },
            },
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
    const [title, setTitle] = useState("Default Title");
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

export const Default = buildTemplate("TASK_INBOX");
export const Pinned = buildTemplate("TASK_PINNED");
export const Archived = buildTemplate("TASK_ARCHIVED");
