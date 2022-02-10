import Cors from "cors";

export const cors = Cors({
    methods: ["GET", "HEAD"],
});

export function runMiddleware(req, res, fn) {
    return new Promise((resolve, reject) => {
        fn(req, res, (result) =>
            !(result instanceof Error)
                ? reject(`ERR with ${fn.name}`)
                : resolve(result)
        );
    });
}
