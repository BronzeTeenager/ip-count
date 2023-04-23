"use strict";
var common_vendor = require("./vendor.js");
var SESSION_KEY = "user_session_diygw_com";
var REDIRECT_SESSION_KEY = "redirect_session_diygw_com";
var Session = {
  getRedirecturl() {
    return common_vendor.index.getStorageSync(REDIRECT_SESSION_KEY) || null;
  },
  setRedirecturl(url) {
    common_vendor.index.setStorageSync(REDIRECT_SESSION_KEY, url);
  },
  getUser() {
    return common_vendor.index.getStorageSync(SESSION_KEY) || null;
  },
  setUser(session) {
    common_vendor.index.setStorageSync(SESSION_KEY, session);
  },
  clearUser() {
    common_vendor.index.removeStorageSync(SESSION_KEY);
  },
  getToken() {
    var userInfo = this.getUser();
    return userInfo ? userInfo.token : null;
  },
  getOpenId() {
    var userInfo = this.getUser();
    return userInfo ? userInfo.openid : null;
  },
  setValue(key, value) {
    common_vendor.index.setStorageSync(key, value);
  },
  getValue(key) {
    return common_vendor.index.getStorageSync(key) || null;
  }
};
exports.Session = Session;
