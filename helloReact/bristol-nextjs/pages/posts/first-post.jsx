import Head from "next/head";
import Image from "next/image";
import Link from "next/link";
import Script from "next/script";
import React from "react";

import Layout from "../../components/Layout";
import { getPlayersData } from "../../lib/playerParser";

// "getStaticProps" get static dependencies for build time pre-rendering
// "getServerSideProps" runs on every request instead of on build time.
// More request-based SSR functions: https://nextjs.org/docs/basic-features/pages#server-side-rendering
// For normal ClientSideRendering, just fetch data with fetch/axios/useSWR
export async function getStaticProps() {
    const allPlayers = getPlayersData();
    return {
        props: {
            allPlayers,
        },
    };
}

const firstPost = (props) => (
    <div style={{ textAlign: "center" }}>
        <Head>
            <title>Rance03 Post</title>
        </Head>
        <Script
            src="/scripts/3rd-party.js"
            strategy="lazyOnload"
            onLoad={() => console.log("Script loaded inline.")}
        />

        <h1>First Post</h1>
        <h2>
            <Link href="/">Back to Home</Link>
        </h2>
        <Image
            src="/images/rance03-topaz.jpg"
            height={180}
            width={180}
            alt="Topaz"
        />
        <Layout>Container Zone</Layout>
        {props.allPlayers.map((player, i) => (
            <div key={i}>{JSON.stringify(player)}</div>
        ))}
        <hr />
    </div>
);

export default firstPost;
