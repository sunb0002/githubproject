import Head from "next/head";
import Image from "next/image";
import Link from "next/link";
import Script from "next/script";
import React from "react";

import TimeTag from "./TimeTag";

const PostHeader = () => (
    <div style={{ textAlign: "center" }}>
        <Head>
            <title>Rance03 Post</title>
        </Head>
        <Script
            src="/scripts/3rd-party.js"
            strategy="lazyOnload"
            onLoad={() => console.log("Script loaded inline.")}
        />

        <h2>
            <Link href="/">Back to Home</Link>
        </h2>
        <TimeTag />
        <br />
        <Image
            src="/images/rance03-topaz.jpg"
            height={180}
            width={180}
            alt="Topaz"
            priority
        />
        <hr />
    </div>
);

export default PostHeader;
