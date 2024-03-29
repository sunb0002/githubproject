import React from "react";

import Layout from "../../components/Layout";
import PostHeader from "../../components/PostHeader";
import { getPlayersData } from "../../lib/playerParser";

// "getStaticProps" get static dependencies for build time pre-rendering.
// "getServerSideProps" runs on every request, not on build time.
// "getStaticProps" and "getStaticPaths" runs only on the server-side. They won't even be included in the frontend JS bundle,
//      so we shouldn't make backend/API calls in them.
// For normal ClientSideRendering, just fetch data with fetch/axios/useSWR
export async function getStaticProps() {
    const allPlayers = getPlayersData();
    console.log("Rebuilding FirstPost at: ", new Date(), process.env.DB_PASS);
    return {
        props: {
            allPlayers,
        },
        // Incremental Static Regeneration (ISR)
        //      invalidate static/cache after x seconds
        revalidate: 5,
    };
}

const FirstPost = (props) => (
    <div>
        <PostHeader />
        <h2>First Post</h2>
        <Layout>
            {props.allPlayers.map((player, i) => (
                <div key={i}>{JSON.stringify(player)}</div>
            ))}
        </Layout>
    </div>
);

export default FirstPost;
