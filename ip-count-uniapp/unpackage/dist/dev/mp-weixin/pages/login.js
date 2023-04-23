"use strict";
var common_vendor = require("../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      userInfo: {},
      globalOption: {},
      globalData: {},
      userNameFocus: false,
      userName: "",
      passwordFocus: false,
      password: "",
      checkCodeFocus: false,
      checkCode: "",
      checkImg: ""
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
  methods: {
    async init() {
      this.CheckImgApi();
    },
    CheckImgApi(param) {
      let time = Date.now();
      this.$http.get(`http://127.0.0.1:9999/utils/checkCode?time=${time}`).then((res) => {
        this.checkImg = res.base64Img;
      });
    },
    loginApi(param) {
      let emailReg = /^\d{5,12}@[qQ][qQ]\.(com|cn)$/;
      if (!emailReg.test(this.userName)) {
        common_vendor.index.showToast({
          title: "\u8BF7\u8F93\u5165\u6B63\u786EQQ\u90AE\u7BB1!",
          icon: "error",
          duration: 1500
        });
        return;
      }
      let loginAndRegisterReg = /^\w{6,12}$/;
      if (!loginAndRegisterReg.test(this.password)) {
        common_vendor.index.showToast({
          title: "\u5BC6\u7801\u957F\u5EA6\u57286-12\u4E4B\u95F4!",
          icon: "error",
          duration: 1500
        });
        return;
      }
      let checkCodeReg = /^\w{4}$/;
      if (!checkCodeReg.test(this.checkCode)) {
        common_vendor.index.showToast({
          title: "\u8BF7\u8F93\u5165\u6B63\u786E\u9A8C\u8BC1\u7801!",
          icon: "error",
          duration: 1500
        });
        return;
      }
      this.$http.post("http://127.0.0.1:9999/user/login", {
        userName: this.userName,
        password: this.password,
        checkCode: this.checkCode
      }, {
        "Content-type": "application/json",
        sign: "4503fcdaae0b69bbd9c98b3d5e301ef7"
      }).then((res) => {
        if (res.code === 0) {
          common_vendor.index.showToast({
            title: "\u767B\u5F55\u6210\u529F!",
            icon: "success",
            duration: 1e3
          });
          common_vendor.index.setStorageSync("token", res.data.token);
          setTimeout(function() {
            common_vendor.index.redirectTo({
              url: "/pages/userHome"
            });
          }, 1e3);
        } else {
          common_vendor.index.showToast({
            title: res.msg,
            icon: "error",
            duration: 1500
          });
          this.checkCode = "";
          setTimeout(() => {
            this.CheckImgApi();
          }, 1e3);
        }
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: $data.userNameFocus,
    b: $data.userName,
    c: common_vendor.o(($event) => $data.userName = $event.detail.value),
    d: $data.passwordFocus,
    e: $data.password,
    f: common_vendor.o(($event) => $data.password = $event.detail.value),
    g: $data.checkCodeFocus,
    h: $data.checkCode,
    i: common_vendor.o(($event) => $data.checkCode = $event.detail.value),
    j: $data.checkImg,
    k: common_vendor.o((...args) => _ctx.navigateTo && _ctx.navigateTo(...args)),
    l: common_vendor.o((...args) => _ctx.navigateTo && _ctx.navigateTo(...args)),
    m: common_vendor.o((...args) => _ctx.navigateTo && _ctx.navigateTo(...args)),
    n: common_vendor.o((...args) => _ctx.navigateTo && _ctx.navigateTo(...args))
  };
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-18804380"], ["__file", "E:/uniapp/ip-count333/pages/login.vue"]]);
wx.createPage(MiniProgramPage);
