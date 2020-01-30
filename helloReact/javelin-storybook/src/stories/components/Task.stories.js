import { action } from '@storybook/addon-actions';
import { text, withKnobs } from '@storybook/addon-knobs';
import React from 'react';

import Task from '../../components/Task';

export default {
  component: Task,
  title: "Task",
  excludeStories: /.*Data$/,
  decorators: [withKnobs]
};

export const Default = () => {
  return <Task task={{ ...taskData }} {...actionsData} />;
};

export const Pinned = () => (
  <Task task={{ ...taskData, state: "TASK_PINNED" }} {...actionsData} />
);

export const Archived = () => (
  <Task task={{ ...taskData, state: "TASK_ARCHIVED" }} {...actionsData} />
);

// Editable properties (using knobs-addon)
export const PinnedWithKnobs = () => {
  const knobText = text("Custom Task Title", `${taskData.title} 233`);
  return (
    <Task
      task={{ ...taskData, title: knobText, state: "TASK_PINNED" }}
      {...actionsData}
    />
  );
};

export const taskData = {
  id: "1",
  title: "Test Task",
  state: "TASK_INBOX",
  updatedAt: new Date(2018, 0, 1, 9, 0)
};

// Action listener (using action-addon)
export const actionsData = {
  onPinTask: action("onPinTask"),
  onArchiveTask: action("onArchiveTask")
};
