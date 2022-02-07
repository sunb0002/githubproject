import fs from "fs";
import path from "path";

const dataDir = path.join(process.cwd(), "public/data");

export const getAllDataFiles = () => {
    const fileNames = fs.readdirSync(dataDir);
    return fileNames.map((f) => path.join(dataDir, f));
};

export const getFileContent = (fullPath, index) => {
    const fileContent = fs.readFileSync(fullPath);
    return { ...JSON.parse(fileContent), id: index };
};

export const getPlayersData = () => {
    const fullPaths = getAllDataFiles();
    return fullPaths.map(getFileContent);
};
