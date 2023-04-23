"use strict";
var common_vendor = require("../../common/vendor.js");
const _sfc_main = {
  name: "diy-sticky",
  props: {
    offsetTop: {
      type: [Number, String],
      default: 0
    },
    index: {
      type: [Number, String],
      default: ""
    },
    enable: {
      type: Boolean,
      default: true
    },
    h5NavHeight: {
      type: [Number, String],
      default: 44
    },
    className: {
      type: String,
      default: ""
    },
    bgColor: {
      type: String,
      default: "#ffffff"
    },
    zIndex: {
      type: [Number, String],
      default: ""
    }
  },
  data() {
    return {
      fixed: false,
      height: "auto",
      stickyTop: 0,
      elClass: this.$tools.guid(),
      left: 0,
      width: "auto"
    };
  },
  watch: {
    offsetTop(val) {
      this.initObserver();
    },
    enable(val) {
      if (val == false) {
        this.fixed = false;
        this.disconnectObserver("contentObserver");
      } else {
        this.initObserver();
      }
    }
  },
  computed: {
    uZIndex() {
      return this.zIndex ? this.zIndex : 999999;
    }
  },
  mounted() {
    this.initObserver();
  },
  methods: {
    initObserver() {
      if (!this.enable)
        return;
      this.stickyTop = this.offsetTop != 0 ? common_vendor.index.upx2px(this.offsetTop) : 0;
      this.disconnectObserver("contentObserver");
      this.getRect("." + this.elClass).then((res) => {
        this.height = res.height;
        this.left = res.left;
        this.width = res.width;
        this.$nextTick(() => {
          this.observeContent();
        });
      });
    },
    getRect(selector) {
      return new Promise((resolve) => {
        const query = common_vendor.index.createSelectorQuery().in(this);
        query.select(selector).boundingClientRect((rect) => {
          resolve(rect);
        }).exec();
      });
    },
    observeContent() {
      this.disconnectObserver("contentObserver");
      const contentObserver = common_vendor.index.createIntersectionObserver(this, {
        thresholds: [0.95, 0.98, 1]
      });
      contentObserver.relativeToViewport({
        top: -this.stickyTop
      });
      contentObserver.observe("." + this.elClass, (res) => {
        if (!this.enable)
          return;
        this.setFixed(res.boundingClientRect.top);
      });
      this.contentObserver = contentObserver;
    },
    setFixed(top) {
      const fixed = top < this.stickyTop;
      if (fixed)
        this.$emit("fixed", this.index);
      else if (this.fixed)
        this.$emit("unfixed", this.index);
      this.fixed = fixed;
    },
    disconnectObserver(observerName) {
      const observer = this[observerName];
      observer && observer.disconnect();
    }
  },
  beforeDestroy() {
    this.disconnectObserver("contentObserver");
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: $data.fixed ? "fixed" : "static",
    b: $data.stickyTop + "px",
    c: $data.left + "px",
    d: $data.width == "auto" ? "auto" : $data.width + "px",
    e: $options.uZIndex,
    f: common_vendor.n($data.elClass),
    g: common_vendor.n($props.className),
    h: $data.fixed ? $data.height + "px" : "auto",
    i: $props.bgColor
  };
}
var Component = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-66d39684"], ["__file", "E:/uniapp/ip-count333/components/diy-sticky/diy-sticky.vue"]]);
wx.createComponent(Component);
