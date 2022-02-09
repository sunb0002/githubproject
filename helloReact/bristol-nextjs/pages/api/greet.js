export default function handler(req, res) {
    // Next.js has included some prebuilt middleware like req.query, req.body
    const { query } = req;
    res.status(200).json({ text: "Hello!", query });
    // Access DB, call other API or CMS preview mode here.
}
