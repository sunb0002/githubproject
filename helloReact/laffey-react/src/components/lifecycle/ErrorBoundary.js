import React, { Component } from 'react';

// Error Boundary:
// A class component that implements either one of the two lifecycle methods:
//      getDerivedStateFromError() - render a fallback UI [Render phase, no side-effect]
//      componentDidCatch() - log the error [Commit phase, side-effect allowed]
// http://projects.wojtekmaj.pl/react-lifecycle-methods-diagram/

// Note1: It will catch all errors from child components.
//      And all child components will be broken and replaced by fallback UI if any error happens.
//      Try to reuse the boundary to wrap each child component
// Note2: Under 'dev' mode, the React error UI will always display.
//      Need to mannually close the error UI to see the fallback UI.
// Note3: Error boundaries doesn't catch errors in event handlers,
//      async code or while doing server side rendering. Still need try/catch for them!
//      https://reactjs.org/docs/error-boundaries.html#how-about-event-handlers
//      "the event handlers don’t happen during rendering. So if they throw, React still knows what to display on the screen."

class ErrorBoundary extends Component {
  constructor(props) {
    super(props);

    this.state = {
      hasError: false
    };
  }

  static getDerivedStateFromError(error) {
    console.warn("ErrorBoundary getDerivedStateFromError:", error);
    return {
      hasError: true
    };
  }

  componentDidCatch(error, info) {
    console.warn("ErrorBoundary componentDidCatch:", error, info);
  }

  render() {
    if (this.state.hasError) {
      return <div>Sorry, Warspite will return!</div>;
    }
    return (
      <div>
        Warspite (Retrofit) loves our majesty!
        <ErrorBoundaryButton />
      </div>
    );
  }
}

function ErrorBoundaryButton() {
  // throw Error("ErrorBoundaryButton wants to throw an error!");
  return <div />;
}

export default ErrorBoundary;
