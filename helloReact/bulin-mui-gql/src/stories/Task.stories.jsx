import React from "react";
import { useState } from "react";

import Task from "./Task";

export default {
    component: Task,
    title: "Example/Task",
    argTypes: { onClick: { action: "clicked" } },
    parameters: {
        actions: {
            handles: ["click button"],
        },
    },
};

const buildTemplate = (state) => () => {
    const [title, setTitle] = useState("My Title");
    const props = {
        task: {
            state,
            title,
        },
        setTitle,
    };
    return <Task {...props} />;
};

export const Default = buildTemplate("TASK_INBOX");
export const Pinned = buildTemplate("TASK_PINNED");
export const Archived = buildTemplate("TASK_ARCHIVED");
