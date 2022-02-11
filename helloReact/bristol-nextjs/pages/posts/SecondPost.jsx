import React, { useEffect, useState } from "react";
import styled from "styled-components";

import Layout from "../../components/Layout";
import PostHeader from "../../components/PostHeader";
import { getPlayersData } from "../../lib/playerParser";
import { mySleep } from "../../lib/utils";

// You cannot export "getServerSideProps" from non-page components.
// "getServerSideProps" also runs purely at backend. It will not be compiled into UI bundle.
// Next.js UI bundle checker: https://next-code-elimination.vercel.app/
// 可以说"getServerSideProps"是半静态优化版的API Route
// You cannot use getStaticProps or getStaticPaths (static) along with "getServerSideProps" (by request).
export async function getServerSideProps({ res, query }) {
    // dev mode will overwrite to: "Cache-Control: no-cache, no-store, max-age=0, must-revalidate"
    res.setHeader(
        "Cache-Control",
        "maxage=2, stale-while-revalidate=59", // use a stale-while-revalidate value that will not overload your backend
        "public, s-maxage=10" // shared cache: like CDN/proxy
    );

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
    const [isLoading, setLoading] = useState(true);
    useEffect(async () => {
        console.log(
            "Exposed env variable:",
            process.env.NEXT_PUBLIC_ANALYTICS_HAHA
        );
        await mySleep(1000);
        setLoading(false);
    }, []);
    if (isLoading) return <StyledLoading />;

    const { msg, player } = props;
    return (
        <div>
            <PostHeader />
            <h2>Second Post</h2>
            <Layout>
                <StyledWrapper>
                    {JSON.stringify(player)}
                    <br />
                    {msg}
                </StyledWrapper>
            </Layout>
        </div>
    );
};

const StyledWrapper = styled.div`
    background-color: green;
`;

const Loading = ({ className }) => (
    <h1 className={className}>Loading (pure frontend)!</h1>
);
const StyledLoading = styled(Loading)`
    background-color: deeppink;
`;

export default SecondPost;
