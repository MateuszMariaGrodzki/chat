const HtmlWebpackPlugin = require("html-webpack-plugin");
const path = require("path");

module.exports = {
  entry: "./src/index.tsx",
  devServer: {
    inline: true,
    port: 8000,
  },
  resolve: {
    extensions: [".ts", ".tsx", ".js"],
    alias: {
      "@api": path.resolve(__dirname, "src/api/"),
      "@common": path.resolve(__dirname, "src/common/"),
      "@components": path.resolve(__dirname, "src/components/"),
      "@config": path.resolve(__dirname, "src/config/"),
      "@hooks": path.resolve(__dirname, "src/hooks/"),
      "@pages": path.resolve(__dirname, "src/pages/"),
      "@providers": path.resolve(__dirname, "src/providers/"),
      "@types": path.resolve(__dirname, "src/types/"),
      "@utils": path.resolve(__dirname, "src/utils/"),
    },
  },
  output: {
    filename: "app.js",
  },
  module: {
    rules: [
      {
        test: /\.ts(x?)$/,
        exclude: /node_modules/,
        loader: "ts-loader",
      },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: "./index.html",
    }),
  ],
};
