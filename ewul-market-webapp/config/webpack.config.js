const path = require("path");
const src = path.resolve(__dirname, "../", "src", "main");
const ssrc = path.resolve(src, "webapp");
const modules = path.resolve(__dirname, "../", "node_modules");

const config = {
  entry: path.resolve(src, "webapp", "index.js"),
  output: "bundle.js",
  devServerPort: 9000
};

const VueLoaderPlugin = require("vue-loader/lib/plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  mode: "production",
  entry: config.entry,
  output: {
    path: src,
    filename: config.output
  },
  resolve: {
    extensions: [".js", ".vue", ".css", ".scss", ".json"],
    modules: [ssrc, modules]
  },
  module: {
    rules: [
      {
        test: /\.vue$/,
        loader: "vue-loader"
      },
      {
        test: /\.m?js$/,
        exclude: modules,
        use: {
          loader: "babel-loader",
          options: {
            presets: ["@babel/preset-env"]
          }
        }
      },
      {
        test: /\.css$/,
        use: ["style-loader", "css-loader"]
      },
      {
        test: /\.scss$/,
        use: [
          {
            loader: "style-loader"
          },
          {
            loader: "css-loader"
          },
          {
            loader: "postcss-loader",
            options: {
              plugins() {
                return [require("autoprefixer")];
              }
            }
          },
          {
            loader: "sass-loader"
          }
        ]
      }
    ]
  },
  plugins: [
    new VueLoaderPlugin(),
    new HtmlWebpackPlugin({
      title: "ewul-market-webapp"
    })
  ],
  performance: {
    maxEntrypointSize: 2000000,
    maxAssetSize: 2000000
  },
  devServer: {
    contentBase: ssrc,
    port: config.devServerPort,
    hot: true
  }
};
