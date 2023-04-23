"use strict";
var common_vendor = require("../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      userInfo: {},
      globalOption: {},
      globalData: {},
      userInfoNum: 1,
      userInfo: {
        "appCount": "0",
        "money": "0",
        "grade": "",
        "ipCount": "",
        "appRequestCount": "",
        "userName": ""
      }
    };
  },
  onShow() {
    this.setCurrentPage(this);
  },
  onLoad(option) {
    this.setCurrentPage(this);
    if (option) {
      this.setData({
        globalOption: this.getOption(option)
      });
    }
    this.init();
  },
  onPullDownRefresh() {
    this.userInfoApi();
    common_vendor.index.stopPullDownRefresh();
  },
  methods: {
    async init() {
      this.userInfoApi();
    },
    openVipApi() {
      common_vendor.index.showToast({
        title: "\u52AA\u529B\u5F00\u53D1\u4E2D!",
        icon: "error",
        duration: 1500
      });
    },
    userInfoApi(param) {
      this.$http.get(`http://127.0.0.1:9999/user/userInfo`, {}, {
        "token": common_vendor.index.getStorageSync("token")
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
          this.userInfo = res.data;
        }
      });
    },
    exit() {
      common_vendor.index.showModal({
        title: "\u63D0\u793A",
        content: "\u60A8\u786E\u5B9A\u9700\u8981\u9000\u51FA\u767B\u5F55\u5417!",
        success: function(res) {
          if (res.confirm) {
            common_vendor.index.removeStorageSync("token");
            setTimeout(function() {
              common_vendor.index.redirectTo({
                url: "/pages/login"
              });
            }, 1e3);
          } else if (res.cancel)
            ;
        }
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.t($data.userInfo.userName),
    b: common_vendor.t($data.userInfo.grade),
    c: common_vendor.t($data.userInfo.appCount),
    d: common_vendor.t($data.userInfo.appRequestCount),
    e: common_vendor.t($data.userInfo.ipCount),
    f: common_vendor.o(($event) => $options.openVipApi()),
    g: common_vendor.t($data.userInfo.money),
    h: common_vendor.o(($event) => $options.exit()),
    i: common_vendor.o((...args) => _ctx.navigateTo && _ctx.navigateTo(...args)),
    j: common_vendor.o((...args) => _ctx.navigateTo && _ctx.navigateTo(...args))
  };
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-3e432fc3"], ["__file", "E:/uniapp/ip-count333/pages/userHome.vue"]]);
wx.createPage(MiniProgramPage);
