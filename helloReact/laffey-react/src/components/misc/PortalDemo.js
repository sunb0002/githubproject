import React from 'react';
import ReactDOM from 'react-dom';

import belfast from './assets/belfast.gif';

// Nice to put assets in each module
// If to use global absolute path for import, neet to tweek config:
// https://create-react-app.dev/docs/importing-a-component/#absolute-imports
function PortalDemo() {
  const portalRoot = document.getElementById("portal-modal-root");

  return ReactDOM.createPortal(
    <img alt="bulin belfast~" src={belfast} />,
    portalRoot
  );
}

export default PortalDemo;
