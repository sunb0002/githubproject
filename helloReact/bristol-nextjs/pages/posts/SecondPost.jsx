import React from "react";

import Layout from "../../components/Layout";
import PostHeader from "../../components/PostHeader";
import { getPlayersData } from "../../lib/playerParser";

// You cannot use getServerSideProps in non-page components.
// You cannot use getStaticProps or getStaticPaths (static) along with getServerSideProps (server-side).
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
