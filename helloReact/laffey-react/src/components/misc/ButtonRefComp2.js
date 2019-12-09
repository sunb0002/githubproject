import React from 'react';

const ButtonRefComp2 = React.forwardRef((props, ref) => {
  const cheer = "San-Die-Sai-Go!";

  const clickHandler = () => {
    alert(cheer);
  };

  return (
    <button type="button" onClick={clickHandler} ref={ref} value={cheer}>
      Attention3!
    </button>
  );
});

export default ButtonRefComp2;
