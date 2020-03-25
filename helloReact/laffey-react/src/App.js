import './App.css';

import React from 'react';

import Form from './components/form/Form';
import Greet from './components/Greet';
import HMSNameList from './components/HMSNameList';
import HocDemo from './components/hoc/HocDemo';
import LifecycleParent from './components/lifecycle/LifecycleParent';
import Misc from './components/misc/Misc';
import MobxDemo from './components/mobx/MobxDemo';
import RouterDemo from './components/router/RouterDemo';
import Stylesheet from './components/stylesheet/Stylesheet';
import Welcome from './components/Welcome';

function App() {
  return (
    <div className="App">
      {/* Laffey's greetings */}
      <Welcome>#azurlane_anime #azurlane_official</Welcome>
      <hr />

      {/* The Starter fleet */}
      <Greet name="Laffey" type="DD" />
      <Greet name="Javelin" type="DD" />
      <Greet name="Z23" type="DD" />
      <hr />

      {/* The Royal Navy */}
      <HMSNameList />
      <hr />

      <Stylesheet />
      <hr />

      <Form />
      <hr />

      <LifecycleParent />
      <hr />

      <HocDemo />
      <hr />

      <Misc />
      <hr />

      <MobxDemo />
      <hr />

      <RouterDemo />
      <hr />
    </div>
  );
}

export default App;
