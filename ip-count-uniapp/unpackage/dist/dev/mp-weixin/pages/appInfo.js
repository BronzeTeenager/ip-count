"use strict";
var common_vendor = require("../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      userInfo: {},
      globalOption: {},
      globalData: {},
      appInfoInfoNum: 1,
      appToken: "",
      appInfoInfo: {
        "token": "",
        "appName": "",
        "notes": "",
        "todayIpNumber": 0,
        "yesterdayIpNumber": 0,
        "todayRequestNumber": 0,
        "yesterdayRequestNumber": 0,
        "ipCount": 0,
        "requestCount": 0
      }
    };
  },
  onShow() {
    this.setCurrentPage(this);
  },
  onLoad(option) {
    this.appToken = option.token;
    this.appInfoInfoApi();
    this.setCurrentPage(this);
    if (option) {
      this.setData({
        globalOption: this.getOption(option)
      });
    }
    this.init();
  },
  onPullDownRefresh() {
    this.appInfoInfoApi();
    common_vendor.index.stopPullDownRefresh();
  },
  methods: {
    async init() {
    },
    appInfoInfoApi() {
      this.$http.get(`http://127.0.0.1:9999/app/getOneApp?token=${this.appToken}`, {}, {
        "token": common_vendor.index.getStorageSync("token"),
        sign: "4503fcdaae0b69bbd9c98b3d5e301ef7"
      }).then((res) => {
        if (res.code != 0) {
          common_vendor.index.showToast({
            title: "\u767B\u5F55\u5931\u6548,\u8BF7\u91CD\u65B0\u767B\u5F55!",
            icon: "error",
            duration: 1500
          });
          setTimeout(function() {
            common_vendor.index.redirectTo({
              url: "/pages/login"
            });
          }, 1e3);
        } else if (res.code == 0) {
          this.appInfoInfo = res.data;
        }
      });
    }
  }
};
if (!Array) {
  const _easycom_diy_navbar2 = common_vendor.resolveComponent("diy-navbar");
  _easycom_diy_navbar2();
}
const _easycom_diy_navbar = () => "../components/diy-navbar/diy-navbar.js";
if (!Math) {
  _easycom_diy_navbar();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($data.appInfoInfo.appName),
    b: common_vendor.p({
      isFixed: true,
      bgColor: "green",
      isBack: true
    }),
    c: common_vendor.t($data.appInfoInfo.todayIpNumber),
    d: common_vendor.t($data.appInfoInfo.todayRequestNumber),
    e: common_vendor.t($data.appInfoInfo.yesterdayIpNumber),
    f: common_vendor.t($data.appInfoInfo.yesterdayRequestNumber),
    g: common_vendor.t($data.appInfoInfo.ipCount),
    h: common_vendor.t($data.appInfoInfo.requestCount)
  };
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-2b062286"], ["__file", "E:/uniapp/ip-count333/pages/appInfo.vue"]]);
wx.createPage(MiniProgramPage);
