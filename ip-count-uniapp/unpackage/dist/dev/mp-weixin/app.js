"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports[Symbol.toStringTag] = "Module";
var common_vendor = require("./common/vendor.js");
var common_Page = require("./common/Page.js");
var common_Tools = require("./common/Tools.js");
var common_HttpService = require("./common/HttpService.js");
var common_Session = require("./common/Session.js");
require("./common/Validate.js");
require("./common/Login.js");
require("./common/Pay.js");
require("./siteinfo.js");
require("./common/ServiceBase.js");
if (!Math) {
  "./pages/login.js";
  "./pages/register.js";
  "./pages/resetPassword.js";
  "./pages/userHome.js";
  "./pages/home.js";
  "./pages/appInfo.js";
  "./pages/Test.js";
  "./pages/webview.js";
}
const _sfc_main = {
  globalData: {
    userInfo: null,
    tabBar: [],
    homePage: "/pages/login",
    pages: ["/pages/Test", "/pages/login", "/pages/register", "/pages/resetPassword", "/pages/userHome", "/pages/home", "/pages/appInfo"]
  }
};
var App = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["__file", "E:/uniapp/ip-count333/App.vue"]]);
function createApp() {
  const app = common_vendor.createSSRApp(App);
  app.config.globalProperties.$tools = new common_Tools.Tools();
  app.config.globalProperties.$http = new common_HttpService.Service();
  app.config.globalProperties.$session = common_Session.Session;
  common_vendor.index.getSystemInfo({
    success: function(e) {
      app.config.globalProperties.StatusBar = e.statusBarHeight;
      let custom = wx.getMenuButtonBoundingClientRect();
      app.config.globalProperties.Custom = custom;
      app.config.globalProperties.CustomBar = custom.bottom + custom.top - e.statusBarHeight;
    }
  });
  app.mixin({
    methods: {
      setCurrentPage: common_Page.setCurrentPage,
      Validate: common_Page.Validate,
      setData: common_Page.setData,
      navigateTo: common_Page.navigateTo,
      showModal: common_Page.showModal,
      showToast: common_Page.showToast,
      getPickerChildren: common_Page.getPickerChildren,
      uploadImage: common_Page.uploadImage,
      getOption: common_Page.getOption
    }
  });
  return {
    app
  };
}
createApp().app.mount("#app");
exports.createApp = createApp;
