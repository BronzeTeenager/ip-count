"use strict";
var common_vendor = require("../../../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      large: false,
      gameStyle: false,
      inputText: void 0,
      inputText2: void 0,
      returnData: void 0,
      showModal: false,
      messageType: 2,
      title: "\u8BF7\u8F93\u5165",
      normalDialog: false,
      onConfirm: void 0,
      onCancel: void 0
    };
  },
  props: {
    type: {
      type: String,
      default: "number"
    },
    maxLength: {
      type: Number,
      default: 9999
    },
    placeholder: {
      type: String,
      default: "\u8BF7\u8F93\u5165\u5185\u5BB9"
    },
    allowEmpty: {
      type: Boolean,
      default: false
    },
    multi: {
      type: Boolean,
      default: false
    },
    type2: {
      type: String,
      default: "number"
    },
    maxLength2: {
      type: Number,
      default: 9999
    },
    placeholder2: {
      type: String,
      default: "\u8BF7\u8F93\u5165\u5185\u5BB9"
    }
  },
  methods: {
    onChangeRadio(e) {
      this.messageType = e.target.value;
    },
    showInput(title, returnData, gameStyle, large) {
      console.log("\u663E\u793A\u8F93\u5165\u578B\u5BF9\u8BDD\u6846");
      this.gameStyle = gameStyle;
      this.normalDialog = false;
      this.returnData = returnData;
      this.title = title;
      this.showModal = true;
      this.large = large;
      this.inputText = "";
    },
    showMessage(title, content, returnData, onConfirm, onCancel, large) {
      console.log("\u663E\u793A\u6D88\u606F\u578B\u5BF9\u8BDD\u6846");
      this.normalDialog = true;
      this.title = title;
      this.returnData = returnData;
      this.content = content;
      this.showModal = true;
      this.onConfirm = onConfirm;
      this.onCancel = onCancel;
      this.large = large;
      this.inputText = "";
    },
    close() {
      console.log("\u8F93\u5165\u6846\u5173\u95ED");
      this.showModal = false;
      if (this.onCancel) {
        this.onCancel(this.returnData);
      }
    },
    confirm() {
      if (!this.inputText && !this.$props.allowEmpty && !this.normalDialog) {
        return;
      }
      let temp = this.inputText;
      let temp2 = this.inputText2;
      const data = { returnData: this.returnData, content: temp, content2: temp2 };
      console.error("\u7C7B\u578B=", this.messageType);
      if (this.gameStyle)
        data.mesType = this.messageType;
      if (this.onConfirm) {
        this.onConfirm(data);
      } else {
        this.$emit("callback", data);
      }
      this.showModal = false;
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: !$data.normalDialog && $data.showModal
  }, !$data.normalDialog && $data.showModal ? common_vendor.e({
    b: $data.showModal
  }, $data.showModal ? common_vendor.e({
    c: common_vendor.t($data.title),
    d: $data.gameStyle
  }, $data.gameStyle ? {
    e: common_vendor.o((...args) => $options.onChangeRadio && $options.onChangeRadio(...args))
  } : {}, {
    f: $props.type === "number"
  }, $props.type === "number" ? {
    g: $props.maxLength,
    h: $props.placeholder,
    i: $data.inputText,
    j: common_vendor.o(($event) => $data.inputText = $event.detail.value)
  } : {
    k: $props.maxLength,
    l: $props.placeholder,
    m: $data.inputText,
    n: common_vendor.o(($event) => $data.inputText = $event.detail.value)
  }, {
    o: $props.multi
  }, $props.multi ? common_vendor.e({
    p: $props.type2 === "number"
  }, $props.type2 === "number" ? {
    q: $props.maxLength2,
    r: $props.placeholder2,
    s: $data.inputText2,
    t: common_vendor.o(($event) => $data.inputText2 = $event.detail.value)
  } : {
    v: $props.maxLength2,
    w: $props.placeholder2,
    x: $data.inputText2,
    y: common_vendor.o(($event) => $data.inputText2 = $event.detail.value)
  }) : {}, {
    z: common_vendor.o((...args) => $options.close && $options.close(...args)),
    A: common_vendor.o((...args) => $options.confirm && $options.confirm(...args)),
    B: common_vendor.n($data.large ? "incontent-large" : "incontent")
  }) : {}) : $data.normalDialog && $data.showModal ? {
    D: common_vendor.t($data.title),
    E: _ctx.content,
    F: common_vendor.o((...args) => $options.close && $options.close(...args)),
    G: common_vendor.o((...args) => $options.confirm && $options.confirm(...args))
  } : {}, {
    C: $data.normalDialog && $data.showModal
  });
}
var Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/uniapp/ip-count333/uni_modules/luanqing-inputdialog/components/luanqing-inputdialog/luanqing-inputdialog.vue"]]);
wx.createComponent(Component);
