/**
 * 解决更新浏览器缓存问题
 */
let filePath = ''
let Timestamp = ''
//编译环境判断,判断是否H5环境
if (process.env.UNI_PLATFORM === 'h5') {
  filePath = 'static/js/'; //打包文件存放文件夹路径
  Timestamp = '.' + new Date().getTime();//时间戳
 
}
 
module.exports = {
  configureWebpack: { // webpack 配置 解决js缓存的问题
    output: { // 输出重构  打包编译后的 文件目录 文件名称 【模块名称.时间戳】
      filename: `${filePath}[name]${Timestamp}.js`,
      chunkFilename: `${filePath}[name]${Timestamp}.js`
    },
  },
}
