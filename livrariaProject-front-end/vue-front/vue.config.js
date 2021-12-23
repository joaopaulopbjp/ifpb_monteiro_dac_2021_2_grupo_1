module.exports = {
    devServer: {
        proxy: 'http://localhost:8081'
    }
}

module.exports = {
    devServer: {
      proxy: {
        '^/user-login': {
          target: 'http://localhost:8081',
          ws: true,
          changeOrigin: true
        }
      }
    }
  }