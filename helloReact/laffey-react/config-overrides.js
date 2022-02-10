// webpack-bundle-analyzer踩坑:
// (1) CRA gives no access to webpack config. Need to use "react-app-rewired".
// (2) "react-app-rewired" has problem with .css import. Need to config "mini-css-extract-plugin".
// (3) "mini-css-extract-plugin">=v0.9 got bug with webpack, need to use v0.8.2.

const BundleAnalyzerPlugin =
    require("webpack-bundle-analyzer").BundleAnalyzerPlugin;
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

module.exports = function override(config, env) {
    config.plugins = [
        new MiniCssExtractPlugin(),
        new BundleAnalyzerPlugin({
            analyzerMode: "server", // or "disabled", then run command: https://segmentfault.com/a/1190000017716736
            generateStatsFile: true,
            statsOptions: { source: false },
        }),
    ];
    return config;
};
