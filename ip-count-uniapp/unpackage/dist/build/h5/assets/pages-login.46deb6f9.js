import{g as e,r as t,s as a,a as s,o as i,c as l,w as o,b as c,d,i as r,e as n,f as u,I as p,h as m}from"./index.9d56859b.js";import{_ as g,a as f,b as h,c as w}from"./icon2_QQ.7d2b315b.js";import{_ as y}from"./plugin-vue_export-helper.21dcd24c.js";var x=y({data:()=>({userInfo:{},globalOption:{},globalData:{},userNameFocus:!1,userName:"",passwordFocus:!1,password:"",checkCodeFocus:!1,checkCode:"",checkImg:""}),onShow(){this.setCurrentPage(this)},onLoad(e){this.setCurrentPage(this),e&&this.setData({globalOption:this.getOption(e)}),this.init()},methods:{async init(){this.$http.get("http://api.9api.top:9999/ip/ipCount?token=bCi9JFfusnJHuSjCQxtbVg=="),await this.checkTokenApi(),this.CheckImgApi(),this.userName=e("userName"),this.password=e("password")},heartbeatApi(){this.$http.get("http://api.9api.top:9999/online/count?appId=bCi9JFfusnJHuSjCQxtbVg=="),setInterval((()=>{this.$http.get("http://api.9api.top:9999/online/count?appId=bCi9JFfusnJHuSjCQxtbVg==")}),18e4)},async checkTokenApi(){if(""==e("token"))return;"0"==(await this.$http.get("http://api.9api.top:9999/utils/checkToken",{},{token:e("token")})).code&&(this.heartbeatApi(),t({url:"/pages/userHome"}))},CheckImgApi(e){let t=Date.now();this.$http.get(`http://api.9api.top:9999/utils/checkCode?time=${t}`).then((e=>{this.checkImg=e.base64Img}))},loginApi(e){if(!/^\d{5,12}@[qQ][qQ]\.(com|cn)$/.test(this.userName))return void a({title:"请输入正确QQ邮箱!",icon:"error",duration:1500});if(!/^\w{6,12}$/.test(this.password))return void a({title:"密码长度在6-12之间!",icon:"error",duration:1500});/^\w{4}$/.test(this.checkCode)?this.$http.post("http://api.9api.top:9999/user/login",{userName:this.userName,password:this.password,checkCode:this.checkCode},{"Content-type":"application/json",sign:"4503fcdaae0b69bbd9c98b3d5e301ef7"}).then((e=>{0===e.code?(a({title:"登录成功!",icon:"success",duration:1e3}),s("token",e.data.token),s("userName",this.userName),s("password",this.password),this.heartbeatApi(),setTimeout((function(){t({url:"/pages/userHome"})}),1e3)):(a({title:e.msg,icon:"error",duration:1500}),this.checkCode="",setTimeout((()=>{this.CheckImgApi()}),1e3))})):a({title:"请输入正确验证码!",icon:"error",duration:1500})}}},[["render",function(e,t,a,s,y,x){const k=r,b=n,C=u,_=p,v=m;return i(),l(b,{class:"container"},{default:o((()=>[c(b,{class:"flex diygw-col-24 flex-direction-column justify-center items-center flex-nowrap flex-clz"},{default:o((()=>[c(b,{class:"flex diygw-col-0 avatar-clz"},{default:o((()=>[c(b,{class:"diygw-avatar lg radius diygw-shadow bg-none"},{default:o((()=>[c(k,{mode:"aspectFit",class:"diygw-avatar-img radius",src:g})])),_:1})])),_:1})])),_:1}),c(b,{class:"diygw-col-24 logintitle-clz diygw-text-lg text-purple"},{default:o((()=>[d(" 登录页面 ")])),_:1}),c(b,{class:"diygw-form-item diygw-form-item-small diygw-col-24 userName-clz"},{default:o((()=>[c(b,{class:"input"},{default:o((()=>[c(C,{class:"diy-icon-my",style:{color:"#304ffe"}}),c(_,{class:"flex1",focus:y.userNameFocus,name:"userName","comfirm-type":"done",type:"text",modelValue:y.userName,"onUpdate:modelValue":t[0]||(t[0]=e=>y.userName=e),placeholder:"请输入QQ邮箱"},null,8,["focus","modelValue"])])),_:1})])),_:1}),c(b,{class:"diygw-form-item diygw-form-item-small diygw-col-24 password-clz"},{default:o((()=>[c(b,{class:"input"},{default:o((()=>[c(C,{class:"diy-icon-my",style:{color:"#304ffe"}}),c(_,{class:"flex1",focus:y.passwordFocus,name:"password","comfirm-type":"done",type:"text",modelValue:y.password,"onUpdate:modelValue":t[1]||(t[1]=e=>y.password=e),placeholder:"请输入密码"},null,8,["focus","modelValue"])])),_:1})])),_:1}),c(b,{class:"flex flex-wrap diygw-col-24 flex4-clz"},{default:o((()=>[c(b,{class:"diygw-form-item diygw-form-item-small diygw-col-15 checkCode-clz"},{default:o((()=>[c(b,{class:"input"},{default:o((()=>[c(C,{class:"diy-icon-my",style:{color:"#304ffe"}}),c(_,{class:"flex1",focus:y.checkCodeFocus,name:"checkCode","comfirm-type":"done",type:"text",modelValue:y.checkCode,"onUpdate:modelValue":t[2]||(t[2]=e=>y.checkCode=e),placeholder:"请输入验证码"},null,8,["focus","modelValue"])])),_:1})])),_:1}),c(k,{src:y.checkImg,onClick:e.navigateTo,"data-type":"CheckImgApi",class:"diygw-image diygw-col-0 checkImg-clz",style:{height:"45px !important",width:"135px"},mode:"widthFix"},null,8,["src","onClick"])])),_:1}),c(b,{class:"flex diygw-col-24 button-clz"},{default:o((()=>[c(v,{onClick:e.navigateTo,"data-type":"loginApi",class:"diygw-btn gradual-purple md radius flex-sub margin-xs button-button-clz"},{default:o((()=>[d("点击登录")])),_:1},8,["onClick"])])),_:1}),c(b,{class:"flex flex-wrap diygw-col-24 justify-between flex1-clz"},{default:o((()=>[c(b,{onClick:e.navigateTo,"data-type":"page","data-url":"/pages/register",class:"diygw-col-0 text-clz text-purple"},{default:o((()=>[d(" 注册账号 ")])),_:1},8,["onClick"]),c(b,{onClick:e.navigateTo,"data-type":"page","data-url":"/pages/resetPassword",class:"diygw-col-0 text1-clz text-purple"},{default:o((()=>[d(" 忘记密码 ")])),_:1},8,["onClick"])])),_:1}),c(b,{class:"flex flex-wrap diygw-col-24 justify-center items-center flex2-clz"},{default:o((()=>[c(b,{class:"flex diygw-col-0 line-clz"},{default:o((()=>[c(b,{class:"diygw-pzx",style:{"border-bottom":"1px solid #eee"}})])),_:1}),c(b,{class:"diygw-col-0 text2-clz text-grey"},{default:o((()=>[d(" 第三方授权登录 ")])),_:1}),c(b,{class:"flex diygw-col-0 line1-clz"},{default:o((()=>[c(b,{class:"diygw-pzx",style:{"border-bottom":"1px solid #eee"}})])),_:1})])),_:1}),c(b,{class:"flex diygw-col-24 justify-center"},{default:o((()=>[c(b,{class:"diygw-avatar md margin-md radius bg-none"},{default:o((()=>[c(k,{mode:"aspectFit",class:"diygw-avatar-img radius",src:f})])),_:1}),c(b,{class:"diygw-avatar md margin-md radius bg-none"},{default:o((()=>[c(k,{mode:"aspectFit",class:"diygw-avatar-img radius",src:h})])),_:1}),c(b,{class:"diygw-avatar md margin-md radius bg-none"},{default:o((()=>[c(k,{mode:"aspectFit",class:"diygw-avatar-img radius",src:w})])),_:1})])),_:1})])),_:1})}],["__scopeId","data-v-7a230268"]]);export{x as default};