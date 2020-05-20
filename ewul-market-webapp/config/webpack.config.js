const path = require('path');
const src = path.resolve(__dirname, "../", "src", "main");

const config = {
    entry: path.resolve(src, "webapp", "index.js"),
    output: "bundle.js",
    devServerPort: 9000
};


const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
    mode: "production",
    entry: config.entry,
    output: {
        path: src,
        filename: config.output
    },
    plugins: [
        new HtmlWebpackPlugin({
            title: "ewul-market-webapp"
        })
    ],
    performance: {
        maxEntrypointSize: 500000,
        maxAssetSize: 500000
    },
    devServer: {
        contentBase: src,
        port: config.devServerPort,
        inline: true,
        hot: true
    }
};