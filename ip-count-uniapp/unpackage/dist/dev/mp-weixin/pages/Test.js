"use strict";
var common_vendor = require("../common/vendor.js");
const _sfc_main = {
  data() {
    return {};
  },
  methods: {
    callback(ss) {
      console.log(ss);
    },
    showInput(title, returnData, gameStyle, large) {
      console.log("\u663E\u793A\u8F93\u5165\u578B\u5BF9\u8BDD\u6846");
      this.gameStyle = gameStyle;
      this.normalDialog = true;
      this.returnData = returnData;
      this.title = title;
      this.showModal = false;
      this.large = large;
    },
    longClick() {
      this.$refs.textModal.showInput("\u6DFB\u52A0\u9879\u76EE");
    },
    setAppName() {
    }
  }
};
if (!Array) {
  const _easycom_luanqing_inputdialog2 = common_vendor.resolveComponent("luanqing-inputdialog");
  _easycom_luanqing_inputdialog2();
}
const _easycom_luanqing_inputdialog = () => "../uni_modules/luanqing-inputdialog/components/luanqing-inputdialog/luanqing-inputdialog.js";
if (!Math) {
  _easycom_luanqing_inputdialog();
}
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.o(($event) => $options.setAppName()),
    b: common_vendor.o(($event) => $options.longClick()),
    c: common_vendor.sr("textModal", "4b89cf98-0"),
    d: common_vendor.o($options.callback),
    e: common_vendor.p({
      multi: "true",
      placeholder: "\u8BF7\u8F93\u5165\u9879\u76EE\u540D\u79F0",
      placeholder2: "\u8BF7\u8F93\u5165\u9879\u76EE\u5907\u6CE8",
      type: "text",
      type2: "text"
    })
  };
}
var MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/uniapp/ip-count333/pages/Test.vue"]]);
wx.createPage(MiniProgramPage);
