"use weex:vue";

if (typeof Promise !== 'undefined' && !Promise.prototype.finally) {
  Promise.prototype.finally = function(callback) {
    const promise = this.constructor
    return this.then(
      value => promise.resolve(callback()).then(() => value),
      reason => promise.resolve(callback()).then(() => {
        throw reason
      })
    )
  }
};

if (typeof uni !== 'undefined' && uni && uni.requireGlobal) {
  const global = uni.requireGlobal()
  ArrayBuffer = global.ArrayBuffer
  Int8Array = global.Int8Array
  Uint8Array = global.Uint8Array
  Uint8ClampedArray = global.Uint8ClampedArray
  Int16Array = global.Int16Array
  Uint16Array = global.Uint16Array
  Int32Array = global.Int32Array
  Uint32Array = global.Uint32Array
  Float32Array = global.Float32Array
  Float64Array = global.Float64Array
  BigInt64Array = global.BigInt64Array
  BigUint64Array = global.BigUint64Array
};


(()=>{var w=Object.create;var g=Object.defineProperty;var y=Object.getOwnPropertyDescriptor;var S=Object.getOwnPropertyNames;var O=Object.getPrototypeOf,B=Object.prototype.hasOwnProperty;var k=(e,a)=>()=>(a||e((a={exports:{}}).exports,a),a.exports);var M=(e,a,t,n)=>{if(a&&typeof a=="object"||typeof a=="function")for(let s of S(a))!B.call(e,s)&&s!==t&&g(e,s,{get:()=>a[s],enumerable:!(n=y(a,s))||n.enumerable});return e};var N=(e,a,t)=>(t=e!=null?w(O(e)):{},M(a||!e||!e.__esModule?g(t,"default",{value:e,enumerable:!0}):t,e));var D=k((x,_)=>{_.exports=Vue});var I=Object.prototype.toString,p=e=>I.call(e),d=e=>p(e).slice(8,-1);function T(){return typeof __channelId__=="string"&&__channelId__}function v(e,a){switch(d(a)){case"Function":return"function() { [native code] }";default:return a}}function F(e,a,t){return T()?(t.push(a.replace("at ","uni-app:///")),console[e].apply(console,t)):t.map(function(s){let i=p(s).toLowerCase();if(["[object object]","[object array]","[object module]"].indexOf(i)!==-1)try{s="---BEGIN:JSON---"+JSON.stringify(s,v)+"---END:JSON---"}catch(l){s=i}else if(s===null)s="---NULL---";else if(s===void 0)s="---UNDEFINED---";else{let l=d(s).toUpperCase();l==="NUMBER"||l==="BOOLEAN"?s="---BEGIN:"+l+"---"+s+"---END:"+l+"---":s=String(s)}return s}).join("---COMMA---")+" "+a}function m(e,a,...t){let n=F(e,a,t);n&&console[e](n)}var b=(e,a)=>{let t=e.__vccOpts||e;for(let[n,s]of a)t[n]=s;return t};var C={data(){return{locale:"en",fallbackLocale:"en",localization:{en:{done:"OK",cancel:"Cancel"},zh:{done:"\u5B8C\u6210",cancel:"\u53D6\u6D88"},"zh-hans":{},"zh-hant":{},messages:{}},localizationTemplate:{}}},onLoad(){this.initLocale()},created(){this.initLocale()},methods:{initLocale(){if(this.__initLocale)return;this.__initLocale=!0;let e=(plus.webview.currentWebview().extras||{}).data||{};if(e.messages&&(this.localization.messages=e.messages),e.locale){this.locale=e.locale.toLowerCase();return}let a={chs:"hans",cn:"hans",sg:"hans",cht:"hant",tw:"hant",hk:"hant",mo:"hant"},t=plus.os.language.toLowerCase().split("/")[0].replace("_","-").split("-"),n=t[1];n&&(t[1]=a[n]||n),t.length=t.length>2?2:t.length,this.locale=t.join("-")},localize(e){let a=this.locale,t=a.split("-")[0],n=this.fallbackLocale,s=i=>{let l=Object.assign({},this.localization[i],(this.localizationTemplate||{})[i]);return m("log","at mixins/i18n.js:78","********** o :>> ",l),l};return s("messages")[e]||s(a)[e]||s(t)[e]||s(n)[e]||e}}},E={onLoad(){this.initMessage()},methods:{initMessage(){let{from:e,callback:a,runtime:t,data:n={},useGlobalEvent:s}=plus.webview.currentWebview().extras||{};this.__from=e,this.__runtime=t,this.__page=plus.webview.currentWebview().id,this.__useGlobalEvent=s,this.data=JSON.parse(JSON.stringify(n)),plus.key.addEventListener("backbutton",()=>{typeof this.onClose=="function"?this.onClose():plus.webview.currentWebview().close("auto")});let i=this,l=function(c){let h=c.data&&c.data.__message;!h||i.__onMessageCallback&&i.__onMessageCallback(h.data)};if(this.__useGlobalEvent)weex.requireModule("globalEvent").addEventListener("plusMessage",l);else{let c=new BroadcastChannel(this.__page);c.onmessage=l}},postMessage(e={},a=!1){let t=JSON.parse(JSON.stringify({__message:{__page:this.__page,data:e,keep:a}})),n=this.__from;if(this.__runtime==="v8")this.__useGlobalEvent?plus.webview.postMessageToUniNView(t,n):new BroadcastChannel(n).postMessage(t);else{let s=plus.webview.getWebviewById(n);s&&s.evalJS(`__plusMessage&&__plusMessage(${JSON.stringify({data:t})})`)}},onMessage(e){this.__onMessageCallback=e}}};var r=N(D());var R={content:{"":{flex:1,alignItems:"center",justifyContent:"center",backgroundColor:"#000000"}},barcode:{"":{position:"absolute",left:0,top:0,right:0,bottom:0,zIndex:1}},"set-flash":{"":{alignItems:"center",justifyContent:"center",transform:"translateY(80px)",zIndex:2}},"image-flash":{"":{width:26,height:26,marginBottom:2}},"image-flash-text":{"":{fontSize:10,color:"#FFFFFF"}}},o=plus.barcode,A={qrCode:[o.QR,o.AZTEC,o.MAXICODE],barCode:[o.EAN13,o.EAN8,o.UPCA,o.UPCE,o.CODABAR,o.CODE128,o.CODE39,o.CODE93,o.ITF,o.RSS14,o.RSSEXPANDED],datamatrix:[o.DATAMATRIX],pdf417:[o.PDF417]},L={[o.QR]:"QR_CODE",[o.EAN13]:"EAN_13",[o.EAN8]:"EAN_8",[o.DATAMATRIX]:"DATA_MATRIX",[o.UPCA]:"UPC_A",[o.UPCE]:"UPC_E",[o.CODABAR]:"CODABAR",[o.CODE39]:"CODE_39",[o.CODE93]:"CODE_93",[o.CODE128]:"CODE_128",[o.ITF]:"CODE_25",[o.PDF417]:"PDF_417",[o.AZTEC]:"AZTEC",[o.RSS14]:"RSS_14",[o.RSSEXPANDED]:"RSSEXPANDED"},P={mixins:[E,C],data:{filters:[0,2,1],backgroud:"#000000",frameColor:"#118ce9",scanbarColor:"#118ce9",enabledFlash:!1,flashImage0:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAABjklEQVRoQ+1ZbVHEQAx9TwE4ABTcOQAknANQAKcAUAAOAAXgAHAACsDCKQiTmbYDzJZtNt2bFrJ/m6+Xl2yyU2LmhzOPH/8PgIjcADirxNyapNoffMwMiMgzgMPBHmyCLySPLCoBwJKtAbJbYaBmD1yRvBwAtBMxl5DF+DZkiwCIyBLAzsgBbki+Wm2WAlCaL6zOMvKnJO+sNksB7ALQbO1ZHfbIv5FUVs2nCIB6EZETALdmj2mFY5I6X8ynGEADQllYmL1+VzBfnV/VvQB0aj45ARyQ/Ci14QLQsOBZLe5JaikWnzEA7AN4L4hgA2Dpyb76dANwsOCq/TZhASAYKGie0a7R1lDPI0ebtF0NUi+4yfdAtxr3PEMnD6BbD0QkNfACQO05EAwMuaBqDrIVycdmTpwDuP4R0OR7QFftVRP0g+49cwOQq4DJMxAAchmofY3m/EcJBQOZbTRKKJeBKKEoIePvpFRJ1VzmciUccyCa+C81cerBkuuB7sGTE/zt+yhN7AnAqxsAvBn06n8CkyPwMZKwm+UAAAAASUVORK5CYII=",flashImage1:"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAADAAAAAwBAMAAAClLOS0AAAAMFBMVEUAAAA3kvI3lfY2k/VAl+43k/U3k/Q4k/M3kvI3k/M4k/Q4lPU2lPU2k/Vdq843k/WWSpNKAAAAD3RSTlMAwD+QINCAcPBgUDDgoBAE044kAAAAdklEQVQ4y2OgOrD/DwffUSTkERIfyZXAtOMbca7iVoKDDSgSbAijJqBI8J2HiX9FM2s+TOITmgQrTEIATYIJJuEA5mJ68S+Gg/0hEi0YEoxQK2gs0WyPQyKBGYeEAhPtJRaw45AIccXpwVEJekuwQyQWMFAfAACeDBJY9aXa3QAAAABJRU5ErkJggg==",autoDecodeCharSet:!1,autoZoom:!0,localizationTemplate:{en:{fail:"Recognition failure","flash.on":"Tap to turn light on","flash.off":"Tap to turn light off"},zh:{fail:"\u8BC6\u522B\u5931\u8D25","flash.on":"\u8F7B\u89E6\u7167\u4EAE","flash.off":"\u8F7B\u89E6\u5173\u95ED"}}},onLoad(){let e=this.data,a=e.scanType;this.autoDecodeCharSet=e.autoDecodeCharSet,this.autoZoom=e.autoZoom;let t=[];Array.isArray(a)&&a.length&&a.forEach(n=>{let s=A[n];s&&(t=t.concat(s))}),t.length||(t=t.concat(A.qrCode).concat(A.barCode).concat(A.datamatrix).concat(A.pdf417)),this.filters=t,this.onMessage(n=>{this.gallery()})},onUnload(){this.cancel()},onReady(){setTimeout(()=>{this.cancel(),this.start()},50)},methods:{start(){this.$refs.barcode.start({sound:this.data.sound})},scan(e){o.scan(e,(a,t,n,s)=>{this.scanSuccess(a,t,n,s)},()=>{plus.nativeUI.toast(this.localize("fail"))},this.filters,this.autoDecodeCharSet)},cancel(){this.$refs.barcode.cancel()},gallery(){plus.gallery.pick(e=>{this.scan(e)},e=>{e.code!==(weex.config.env.platform.toLowerCase()==="android"?12:-2)&&plus.nativeUI.toast(this.localize("fail"))},{multiple:!1,system:!1,filename:"_doc/uniapp_temp/gallery/",permissionAlert:!0})},onmarked(e){var a=e.detail;this.scanSuccess(a.code,a.message,a.file,a.charSet)},scanSuccess(e,a,t,n){this.postMessage({event:"marked",detail:{scanType:L[e],result:a,charSet:n||"utf8",path:t||""}})},onerror(e){this.postMessage({event:"fail",message:JSON.stringify(e)})},setFlash(){this.enabledFlash=!this.enabledFlash,this.$refs.barcode.setFlash(this.enabledFlash)}}};function Q(e,a,t,n,s,i){return(0,r.openBlock)(),(0,r.createElementBlock)("scroll-view",{scrollY:!0,showScrollbar:!0,enableBackToTop:!0,bubble:"true",style:{flexDirection:"column"}},[(0,r.createElementVNode)("view",{class:"content"},[(0,r.createElementVNode)("barcode",{class:"barcode",ref:"barcode",autostart:"false",backgroud:e.backgroud,frameColor:e.frameColor,scanbarColor:e.scanbarColor,filters:e.filters,autoDecodeCharset:e.autoDecodeCharSet,autoZoom:e.autoZoom,onMarked:a[0]||(a[0]=(...l)=>i.onmarked&&i.onmarked(...l)),onError:a[1]||(a[1]=(...l)=>i.onerror&&i.onerror(...l))},null,40,["backgroud","frameColor","scanbarColor","filters","autoDecodeCharset","autoZoom"]),(0,r.createElementVNode)("view",{class:"set-flash",onClick:a[2]||(a[2]=(...l)=>i.setFlash&&i.setFlash(...l))},[(0,r.createElementVNode)("u-image",{class:"image-flash",src:e.enabledFlash?e.flashImage1:e.flashImage0,resize:"stretch"},null,8,["src"]),(0,r.createElementVNode)("u-text",{class:"image-flash-text"},(0,r.toDisplayString)(e.enabledFlash?e.localize("flash.off"):e.localize("flash.on")),1)])])])}var u=b(P,[["render",Q],["styles",[R]]]);var f=plus.webview.currentWebview();if(f){let e=parseInt(f.id),a="template/__uniappscan",t={};try{t=JSON.parse(f.__query__)}catch(s){}u.mpType="page";let n=Vue.createPageApp(u,{$store:getApp({allowDefault:!0}).$store,__pageId:e,__pagePath:a,__pageQuery:t});n.provide("__globalStyles",Vue.useCssStyles([...__uniConfig.styles,...u.styles||[]])),n.mount("#root")}})();
