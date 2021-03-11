import { useEffect } from 'react';

function ImportScript(url) {

    useEffect(() => {

        const elm = document.createElement("script");
        elm.src = url;
        elm.async = true;
        document.body.appendChild(elm);

        return () => {
            document.body.removeChild(elm);
        }

    }, [url]);

}

export default ImportScript;
