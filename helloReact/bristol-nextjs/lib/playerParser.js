import fs from "fs";
import path from "path";

const dataDir = path.join(process.cwd(), "public/data");

export function getPlayersData() {
    const fileNames = fs.readdirSync(dataDir);
    const players = fileNames.map((f, i) => {
        const fullPath = path.join(dataDir, f);
        const fileContent = fs.readFileSync(fullPath);
        return { ...JSON.parse(fileContent), id: i };
    });
    return players;
}
