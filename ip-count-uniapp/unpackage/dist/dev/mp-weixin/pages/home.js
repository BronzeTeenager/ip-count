"use strict";
var common_vendor = require("../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      userInfo: {},
      globalOption: {},
      globalData: {},
      userInfoNum: 1,
      appInfo: {},
      likeStr: ""
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
    this.appAllInfoApi();
    common_vendor.index.stopPullDownRefresh();
  },
  methods: {
    async init() {
      this.appAllInfoApi();
    },
    appAllInfoApi(param) {
      this.$http.get("http://127.0.0.1:9999/app/getAllApp", {}, {
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
          this.appInfo = res;
        }
      });
    },
    appInfoApi(appToken) {
      common_vendor.index.navigateTo({
        url: `/pages/appInfo?token=` + appToken
      });
    },
    selectLikeApi() {
      this.$http.get("http://127.0.0.1:9999/app/selectLikeAppName?str=" + this.likeStr, {}, {
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
          if (Object.keys(res.data).length == 0) {
            common_vendor.index.showToast({
              title: "\u6CA1\u6709\u8BE5\u9879\u76EEo!",
              icon: "error",
              duration: 2e3
            });
            setTimeout(() => {
              this.likeStr = "";
              this.appAllInfoApi();
            }, 1e3);
          }
          this.appInfo = res;
        }
      });
    },
    setAppName() {
      common_vendor.index.showModal({
        title: "\u8BF7\u8F93\u5165\u9879\u76EE\u540D\u79F0",
        showCancel: true,
        editable: true,
        success: (res) => {
          if (res.confirm) {
            if (res.content == "") {
              common_vendor.index.showToast({
                title: "\u8BF7\u8F93\u5165\u9879\u76EE\u540D\u79F0",
                icon: "error",
                duration: 1500
              });
            } else {
              setTimeout(() => {
                this.setAppNotes(res.content);
              }, 250);
            }
          }
        }
      });
    },
    setAppNotes(appName) {
      common_vendor.index.showModal({
        title: "\u8BF7\u8F93\u5165\u9879\u76EE\u5907\u6CE8(\u53EF\u7A7A)",
        showCancel: true,
        editable: true,
        success: (res) => {
          if (res.confirm) {
            this.$http.post("http://127.0.0.1:9999/app/add", {
              "appName": appName,
              "notes": res.content
            }, {
              "Content-type": "application/json",
              "token": common_vendor.index.getStorageSync("token"),
              "sign": "dfdsfsdfdsfsdf"
            }).then((resp) => {
              if (res.code === 0) {
                common_vendor.index.showToast({
                  title: resp.msg,
                  icon: "success",
                  duration: 1e3
                });
              } else {
                common_vendor.index.showToast({
                  title: resp.msg,
                  icon: "error",
                  duration: 1500
                });
              }
            });
          }
        }
      });
    },
    appLongtap(appToken) {
      console.log("12d1f2");
    }
  }
};
if (!Array) {
  const _easycom_diy_noticebar2 = common_vendor.resolveComponent("diy-noticebar");
  const _easycom_diy_sticky2 = common_vendor.resolveComponent("diy-sticky");
  (_easycom_diy_noticebar2 + _easycom_diy_sticky2)();
}
const _easycom_diy_noticebar = () => "../components/diy-noticebar/diy-noticebar.js";
const _easycom_diy_sticky = () => "../components/diy-sticky/diy-sticky.js";
if (!Math) {
  (_easycom_diy_noticebar + _easycom_diy_sticky)();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: $data.likeStr,
    b: common_vendor.o(($event) => $data.likeStr = $event.detail.value),
    c: common_vendor.o(($event) => $options.selectLikeApi()),
    d: common_vendor.p({
      color: "#07c160",
      bgColor: "#fff",
      leftIcon: "diy-icon-notification"
    }),
    e: common_vendor.p({
      h5NavHeight: "0",
      ["offset-top"]: "0"
    }),
    f: common_vendor.f($data.appInfo.data, (item, index, i0) => {
      return {
        a: common_vendor.t(item.appName),
        b: common_vendor.t(item.notes),
        c: common_vendor.t(item.requestCount),
        d: common_vendor.t(item.ipCount),
        e: item.token,
        f: common_vendor.o(($event) => $options.appInfoApi(item.token), item.token),
        g: common_vendor.o(($event) => $options.appLongtap(item.token), item.token)
      };
    }),
    g: common_vendor.o((...args) => _ctx.navigateTo && _ctx.navigateTo(...args)),
    h: common_vendor.o((...args) => _ctx.navigateTo && _ctx.navigateTo(...args)),
    i: common_vendor.o(($event) => $options.setAppName())
  };
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-86d91950"], ["__file", "E:/uniapp/ip-count333/pages/home.vue"]]);
wx.createPage(MiniProgramPage);
