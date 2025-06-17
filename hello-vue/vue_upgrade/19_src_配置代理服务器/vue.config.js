const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // eslint-disable
  lintOnSave: false,
  // 开启代理服务器，方式一
  // devServer: {
  //   proxy: 'http://localhost:15000'
  // }
  // 开启代理服务器，方式二
    devServer: {
      proxy: {
        '/api1': {
          target: 'http://localhost:15000',
          pathRewrite: {'^/api1': ''},
          ws: false,
          // 用于请求头中的值
          changeOrigin: true
        },
        '/api2': {
          target: 'http://localhost:15001',
          pathRewrite: {'^/api2': ''}
        }
      }
    }
})
