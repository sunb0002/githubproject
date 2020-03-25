import React from 'react';
import { BrowserRouter, Route, Switch, useHistory } from 'react-router-dom';

const AppRouter = () => (
  <BrowserRouter>
    <Switch>
      <Route path="/lemalin" component={LeMalin} />
      <Route component={LeTriomphant} />
    </Switch>
    <br />
  </BrowserRouter>
);

export default AppRouter;

function LeMalin() {
  return <div>... (Le Malin seems to be asleep)</div>;
}

function LeTriomphant() {
  let history = useHistory();
  const clickHandler = () => history.push({ pathname: "/lemalin" });

  return (
    <div>
      I'm Free Iris Le Fantasque-class destroyer Le Triomphant, greetings~{" "}
      <br />
      <button onClick={clickHandler}>Toggle</button>
    </div>
  );
}
