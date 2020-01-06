import './appStyles.css';

import React from 'react';

import styles from './appStyles.module.css';

function Stylesheet() {
  return (
    <div>
      <h2 className="error">Hard Mode</h2>
      {/* In React, xxx.module.css will become localized/scoped css after compiling */}
      <h2 className={styles.success}>Easy Mode</h2>
    </div>
  );
}

export default Stylesheet;
