import React from 'react';

/**
 * React.memo is a high-order component which takes a comp as iuput and return an enhanced new comp.
 *
 * Returns a "PureComponent".
 * Performance booster, avoid unwanted rendering.
 *
 * 

React.PureComponent是银
React.memo(...)是金
React.PureComponent是给ES6的类组件使用的
React.memo(...)是给函数组件使用的
React.PureComponent减少ES6的类组件的无用渲染
React.memo(...)减少函数组件的无用渲染

 */
function MemoComp(props) {
  console.log("MemoComp render.");
  return <div>{props.cheer}</div>;
}

export default React.memo(MemoComp);
