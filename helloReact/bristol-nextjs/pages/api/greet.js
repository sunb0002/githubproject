import { cors, runMiddleware } from "./middlewares/all";

export default async function handler(req, res) {
    // Next.js has included some prebuilt middleware like req.query, req.body
    try {
        await runMiddleware(req, res, cors);
    } catch (err) {
        res.status(500).json({ text: "jia lat", err });
        return;
    }

    const { query, method } = req;
    // Access DB, call other API or CMS preview mode here.
    res.status(200).json({ text: `Hello ${method}!`, query });
}
