import React, { ReactElement } from 'react';

interface Props {
    txt: string;
}

function MyButton(props: Props): ReactElement {
    const { txt } = props;
    const clickHandler = () => alert("MyButton is clicked!");

    return <button onClick={clickHandler}>{txt || "Default text from my-lib"}</button>;
}

export default MyButton;
