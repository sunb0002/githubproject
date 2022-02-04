import { ApolloClient, gql, InMemoryCache } from "@apollo/client";

export const gqlClient = new ApolloClient({
    uri: "https://api.spacex.land/graphql/",
    cache: new InMemoryCache(),
});

// Use GraphQL variables, not hardcode/function string
// https://www.apollographql.com/docs/react/data/operation-best-practices/
export const PAST_LAUNCHES = gql`
    query PastLaunches($limit: Int!) {
        launchesPast(limit: $limit) {
            mission_name
            launch_date_local
            rocket {
                rocket_name
            }
        }
    }
`;
