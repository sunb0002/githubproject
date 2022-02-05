import Head from "next/head";
import Image from "next/image";
import Link from "next/link";
import Script from "next/script";
import React from "react";

const firstPost = () => (
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
            height={150}
            width={150}
            alt="Topaz"
        />
    </div>
);

export default firstPost;
