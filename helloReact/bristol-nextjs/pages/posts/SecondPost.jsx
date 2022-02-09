import React from "react";

import Layout from "../../components/Layout";
import PostHeader from "../../components/PostHeader";
import { getPlayersData } from "../../lib/playerParser";

// You cannot export "getServerSideProps" from non-page components.
// "getServerSideProps" also runs purely at backend. It will not be compiled into UI bundle.
// Next.js UI bundle checker: https://next-code-elimination.vercel.app/
// 可以说"getServerSideProps"是半静态优化版的API Route
// You cannot use getStaticProps or getStaticPaths (static) along with "getServerSideProps" (by request).
export async function getServerSideProps({ query }) {
    const allPlayers = getPlayersData();
    const { id = "0" } = query;
    const player = allPlayers.find((p) => p.id == id);
    return {
        props: {
            msg: "Rance03 is the best!",
            player,
        },
    };
}

const SecondPost = (props) => {
    const { msg, player } = props;
    return (
        <div>
            <PostHeader />
            <h2>Second Post</h2>
            <Layout>
                {JSON.stringify(player)}
                <br />
                {msg}
            </Layout>
        </div>
    );
};

export default SecondPost;
