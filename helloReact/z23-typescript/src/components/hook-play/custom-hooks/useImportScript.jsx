import { useEffect } from 'react';

function useImportScript(url) {

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

export default useImportScript;
