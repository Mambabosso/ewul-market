const path = require("path");
const src = path.resolve(__dirname, "../", "src", "main");

const config = {
  entry: path.resolve(src, "webapp", "index.js"),
  output: "bundle.js",
  devServerPort: 9000,
};

const VueLoaderPlugin = require("vue-loader/lib/plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  mode: "production",
  entry: config.entry,
  output: {
    path: src,
    filename: config.output,
  },
  module: {
    rules: [
      {
        test: /\.vue$/,
        loader: "vue-loader",
      },
      {
        test: /\.css$/,
        use: ["vue-style-loader", "style-loader", "css-loader"],
      },
      {
        test: /\.m?js$/,
        exclude: /node_modules/,
        use: {
          loader: "babel-loader",
          options: {
            presets: ["@babel/preset-env"],
          },
        },
      },
    ],
  },
  plugins: [
    new VueLoaderPlugin(),
    new HtmlWebpackPlugin({
      title: "ewul-market-webapp",
    }),
  ],
  performance: {
    maxEntrypointSize: 2000000,
    maxAssetSize: 2000000,
  },
  devServer: {
    contentBase: src,
    port: config.devServerPort,
    inline: true,
    hot: true,
  },
};
