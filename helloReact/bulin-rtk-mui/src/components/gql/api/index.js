import { ApolloClient, gql, InMemoryCache } from "@apollo/client";

export const gqlClient = new ApolloClient({
    uri: "https://api.spacex.land/graphql/",
    cache: new InMemoryCache(),
});

export const PAST_LAUNCHES = (limit = 1) => gql`
    query PastLaunches {
        launchesPast(limit: ${limit}) {
            mission_name
            launch_date_local
            rocket {
                rocket_name
            }
        }
    }
`;
