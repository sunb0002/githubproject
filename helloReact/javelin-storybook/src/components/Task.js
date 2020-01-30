import PropTypes from 'prop-types';
import React from 'react';

export default function Task({
  task: { id, title, state },
  onArchiveTask,
  onPinTask
}) {
  return (
    <div className={`list-item ${state}`}>
      <label className="checkbox">
        <input
          type="checkbox"
          defaultChecked={state === "TASK_PINNED"}
          name="checked"
        />
        <span className="checkbox-custom" onClick={() => console.log(id)} />
      </label>

      <div className="title">
        <input
          type="text"
          value={title}
          readOnly={true}
          placeholder="Input title"
        />
      </div>

      <div className="actions" onClick={event => event.stopPropagation()}>
        {state !== "TASK_ARCHIVED" && (
          // eslint-disable-next-line jsx-a11y/anchor-is-valid
          <a href={null} onClick={() => console.warn(id)}>
            <span className={`icon-star`} />
          </a>
        )}
      </div>
    </div>
  );
}

// Define propTypes, like Typescript
Task.propTypes = {
  task: PropTypes.shape({
    id: PropTypes.string.isRequired,
    title: PropTypes.string.isRequired,
    state: PropTypes.string.isRequired
  }),
  onArchiveTask: PropTypes.func,
  onPinTask: PropTypes.func
};
