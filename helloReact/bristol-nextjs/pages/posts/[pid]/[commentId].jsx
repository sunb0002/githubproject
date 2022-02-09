import { useRouter } from "next/router";
import React, { useEffect } from "react";

import Layout from "../../../components/Layout";
import PostHeader from "../../../components/PostHeader";

// Only for build time. Define a list of "dynamic" paths to be statically generated.
// "getStaticPaths" is quite useful for "dynamic" paths like [pid].js
// "getStaticPaths" must be used along with "getStaticProps", which actually does nothing.
export async function getStaticPaths() {
    // These specified paths will be statically built when "npm run build"
    const paths = [
        { params: { pid: "PID1", commentId: "COM1" } },
        { params: { pid: "PID2", commentId: "COM3" } },
    ];
    return {
        paths,
        // fallback default=false, unspecified paths will go to default 404 page
        // if true, unspecified paths will be generated at request-time, which is slower at runtime
        //       but faster at build time (only pre-build high priority pages)
        fallback: true,
    };
}

export async function getStaticProps({ params }) {
    console.log(
        'Only see this log for non-statically built pages or "npm run dev".',
        params
    );
    return {
        props: {},
    };
}

const DigitPost = () => {
    useEffect(() => {
        console.log("These are non-async or non-server-side logs.");
    }, []);

    const router = useRouter();
    if (router.isFallback) return <h1>Loading Post!</h1>;

    const handleReload = () => window.location.reload(true); // hard refresh

    const { pid, commentId, admin: adminId } = router.query;
    return (
        <div>
            <PostHeader />
            <h2>Digit Post (Dynamic Route)</h2>
            <button onClick={handleReload}>Reload!</button>
            <Layout>
                {`PID=${pid}, COMMENT_ID=${commentId}, ADMIN_ID=${adminId}`}
            </Layout>
        </div>
    );
};

export default DigitPost;
