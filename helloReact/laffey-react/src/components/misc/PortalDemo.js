import React from 'react';
import ReactDOM from 'react-dom';

function PortalDemo() {
  const portalRoot = document.getElementById("portal-modal-root");

  return ReactDOM.createPortal(<span>bulin~~</span>, portalRoot);
}

export default PortalDemo;
