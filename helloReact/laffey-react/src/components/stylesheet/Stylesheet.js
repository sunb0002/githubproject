import React from "react";
import "./appStyles.css";
import styles from "./appStyles.module.css";

function Stylesheet() {
  return (
    <div>
      <h2 className="error">Error</h2>
      {/* In React, xxx.module.css will become localized/scoped css after compiling */}
      <h2 className={styles.success}>Success</h2>
    </div>
  );
}

export default Stylesheet;
