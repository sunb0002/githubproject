import React, { lazy, Suspense } from "react";
import { BrowserRouter, Route, Switch, useHistory } from "react-router-dom";

const AppRouter = () => (
    <BrowserRouter>
        <Switch>
            <Route path="/lemalin/:torpedo" component={LeMalin} />
            <Route component={LeTriomphant} />
        </Switch>
        <br />
    </BrowserRouter>
);
export default AppRouter;

// Note1: Suspense+Lazy can be used for any code splitting, not only under router.
// Note2: we need to use Nested Routers instead of parallel or multiple router,
//  i.e not another BrowserRouter instance.
//  Otherwise there is no auto detection of route changes, nor relative path support.
function LeMalin(props) {
    const { match } = props;
    const history = useHistory();

    console.log("LeMalin-Lazy route params:", match);

    const goHome = () => history.push({ pathname: "/" });
    const NHai = lazy(() => import("./lazy-components/NingHai"));
    const PHai = lazy(() => import("./lazy-components/PingHai"));
    const Loading = <div>...loading...</div>;

    return (
        <div>
            <div>(Le Malin seems to be asleep)</div>
            <button onClick={goHome}>Home</button>
            <Suspense fallback={Loading}>
                <Switch>
                    <Route path="*/ninghai" component={NHai} />
                    <Route component={PHai} />
                </Switch>
                <br />
            </Suspense>
        </div>
    );
}

function LeTriomphant() {
    const history = useHistory();
    const goLeMalin = () => history.push({ pathname: "/lemalin/666" });
    return (
        <div>
            I'm Free Iris Le Fantasque-class destroyer Le Triomphant, greetings~{" "}
            <br />
            <button onClick={goLeMalin}>LeMalin</button>
        </div>
    );
}
