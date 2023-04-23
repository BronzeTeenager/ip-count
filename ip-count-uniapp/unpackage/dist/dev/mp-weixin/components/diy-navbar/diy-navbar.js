"use strict";
var common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  data() {
    return {
      StatusBar: this.StatusBar,
      CustomBar: this.CustomBar
    };
  },
  name: "diy-navbar",
  computed: {
    style() {
      var StatusBar = this.StatusBar;
      var CustomBar = this.CustomBar;
      var bgImage = this.bgImage;
      var paddingLeft = this.isCapsule && (this.isBack || this.isHome) ? 10 : 0;
      var style = `height:${CustomBar}px;padding-top:${StatusBar}px;padding-left:${paddingLeft}px;padding-right:0px;`;
      if (this.bgImage) {
        style = `${style}background-image:url(${bgImage});`;
      }
      if (this.bgCustom) {
        let bgCustom = this.bgCustom;
        style = `${style}background-color:${bgCustom} !important;`;
      }
      if (this.color) {
        let color = this.color;
        style = `${style}color:${color} !important;`;
      }
      if (!this.isFixed) {
        style = `${style}position:relative !important;`;
      }
      return style;
    }
  },
  props: {
    bgColor: {
      type: String,
      default: ""
    },
    isBack: {
      type: [Boolean, String],
      default: false
    },
    isHome: {
      type: [Boolean, String],
      default: false
    },
    isFixed: {
      type: Boolean,
      default: false
    },
    isCapsule: {
      type: [Boolean, String],
      default: false
    },
    bgImage: {
      type: String,
      default: ""
    },
    bgCustom: {
      type: String,
      default: ""
    },
    backUrl: {
      type: String,
      default: ""
    },
    backdelta: {
      type: Number,
      default: 0
    },
    color: {
      type: String,
      default: ""
    }
  },
  methods: {
    BackPage() {
      if (this.backUrl) {
        common_vendor.index.redirectTo({
          url: this.backUrl
        });
      } else if (this.backdelta) {
        common_vendor.index.navigateBack({
          delta: this.backdelta
        });
      } else {
        common_vendor.index.navigateBack();
      }
    },
    BackHome() {
      if (getApp().globalData.homePage) {
        common_vendor.index.reLaunch({
          url: getApp().globalData.homePage
        });
      } else {
        common_vendor.index.showToast({
          icon: "none",
          title: "\u8BF7\u5148\u8BBE\u7F6E\u9996\u9875\u5730\u5740"
        });
      }
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: $props.isBack || $props.isHome
  }, $props.isBack || $props.isHome ? common_vendor.e({
    b: $props.isBack
  }, $props.isBack ? {
    c: common_vendor.o((...args) => $options.BackPage && $options.BackPage(...args))
  } : {}, {
    d: $props.isBack && $props.isHome
  }, $props.isBack && $props.isHome ? {
    e: common_vendor.s({
      borderColor: $props.isCapsule ? $props.color : "none"
    })
  } : {}, {
    f: $props.isHome
  }, $props.isHome ? {
    g: common_vendor.o((...args) => $options.BackHome && $options.BackHome(...args))
  } : {}, {
    h: common_vendor.n($props.isCapsule ? "diy-navbar-tool-solid" : ""),
    i: common_vendor.s({
      border: $props.isCapsule ? "1px solid " + $props.color : "none"
    }),
    j: common_vendor.s({
      top: $data.StatusBar + "px"
    })
  }) : {}, {
    k: common_vendor.s({
      top: $data.StatusBar + "px"
    }),
    l: common_vendor.s($options.style),
    m: common_vendor.n($props.bgImage != "" ? "none-bg text-white bg-img" : ""),
    n: common_vendor.n($props.bgColor),
    o: common_vendor.s({
      height: $data.CustomBar + "px",
      position: $props.isFixed ? "fixed !important" : "relative !important"
    }),
    p: $props.isFixed
  }, $props.isFixed ? {
    q: common_vendor.s({
      paddingTop: $data.CustomBar + "px"
    })
  } : {});
}
var Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__file", "E:/uniapp/ip-count333/components/diy-navbar/diy-navbar.vue"]]);
wx.createComponent(Component);
