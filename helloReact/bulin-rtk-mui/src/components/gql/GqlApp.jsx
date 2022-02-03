import { ApolloProvider, useQuery } from "@apollo/client";
import React from "react";
import { useState } from "react";

import { gqlClient, PAST_LAUNCHES } from "./api";

export const GqlApp = () => (
    <ApolloProvider client={gqlClient}>
        <Rockets />
    </ApolloProvider>
);

const Rockets = () => {
    const [limit, setLimit] = useState(5);
    const { loading, error, data, refetch } = useQuery(PAST_LAUNCHES(limit), {
        variables: { limit }, // cache
        // pollInterval: 500,
        // https://www.apollographql.com/docs/react/data/queries/
    });
    const handleInputChange = ({ target: { value } }) => setLimit(value || 1);
    const handleBtnClick = () => refetch();

    if (loading) return <>Rockets Loading!</>;
    if (error) return <>{JSON.stringify(error)}</>;
    return (
        <div>
            Limit:{" "}
            <input value={limit} type="number" onChange={handleInputChange} />
            <button onClick={handleBtnClick}>Refetch Now</button>
            <div>
                {data.launchesPast.map(({ mission_name }, i) => (
                    <div key={i}>{mission_name}</div>
                ))}
            </div>
        </div>
    );
};
