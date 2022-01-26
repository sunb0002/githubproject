import "./appStyles.css";

import React from "react";
import styled from "styled-components";

import styles from "./appStyles.module.css";

export default function Stylesheet() {
    return (
        <div>
            <div className="error">Hard Mode</div>
            {/* In React, xxx.module.css will become localized/scoped css after compiling */}
            <div className={styles.success}>Easy Mode</div>
            <SomeDivWrapper>
                <div>{process.env.REACT_APP_EX_MODE}</div>
            </SomeDivWrapper>
        </div>
    );
}

const SomeDivWrapper = styled.div`
    color: yellow;
    font-weight: bold;
`;
