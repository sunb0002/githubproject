import { useRouter } from "next/router";
import React from "react";

import Layout from "../../../components/Layout";
import PostHeader from "../../../components/PostHeader";

const DigitPost = () => {
    const router = useRouter();
    const { pid, commentId, admin: adminId } = router.query;
    return (
        <div>
            <PostHeader />
            <h2>Digit Post (Dynamic Route)</h2>
            <Layout>
                {`PID=${pid}, COMMENT_ID=${commentId}, ADMIN_ID=${adminId}`}
            </Layout>
        </div>
    );
};

export default DigitPost;
