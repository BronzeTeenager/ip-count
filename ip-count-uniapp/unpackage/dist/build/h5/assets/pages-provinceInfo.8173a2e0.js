import{j as e,g as t,s as o,r as a,o as l,c as s,w as i,b as n,d as c,z as r,A as d,F as f,B as p,e as u,t as x}from"./index.9d56859b.js";import{_ as g}from"./diy-navbar.25c44f8b.js";import{r as h}from"./uni-app.es.8884db00.js";import{_ as w}from"./plugin-vue_export-helper.21dcd24c.js";var y=w({data:()=>({userInfo:{},globalOption:{},globalData:{},provinceInfoNum:1,appToken:"",provinceInfo:{code:0,msg:"",data:[{province:"",ipCount:0,requestCount:0}]}}),onShow(){this.setCurrentPage(this)},onLoad(e){this.appToken=e.token,this.setCurrentPage(this),e&&this.setData({globalOption:this.getOption(e)}),this.init()},onPullDownRefresh(){this.provinceInfoNum=1,this.provinceInfoApi(),e()},methods:{async init(){this.provinceInfoApi()},provinceInfoApi(e){this.$http.get("http://api.9api.top:9999/ip/getAllProvinceCount?token="+this.appToken,{},{token:t("token")}).then((e=>{0!=e.code?(o({title:"登录失效,请重新登录!",icon:"error",duration:1500}),setTimeout((function(){a({url:"/pages/login"})}),1e3)):0==e.code&&(0==Object.keys(e.data).length&&(o({title:"查询空 !",icon:"error",duration:2e3}),setTimeout((()=>{}),1e3)),this.provinceInfo=e)}))}}},[["render",function(e,t,o,a,w,y){const m=h(p("diy-navbar"),g),_=u;return l(),s(_,{class:"container"},{default:i((()=>[n(m,{isFixed:!0,bgColor:"green",isBack:!0},{backText:i((()=>[c(" 返回 ")])),content:i((()=>[c(" 地域分布 ")])),_:1}),n(_,{class:"flex flex-wrap diygw-col-24 flex-direction-column flex-clz"},{default:i((()=>[(l(!0),r(f,null,d(w.provinceInfo.data,((e,t)=>(l(),s(_,{key:t,class:"flex flex-wrap diygw-col-24 flex-direction-column flex3-clz"},{default:i((()=>[n(_,{class:"flex flex-wrap diygw-col-24 flex1-clz"},{default:i((()=>[n(_,{class:"diygw-col-24 text1-clz"},{default:i((()=>[c(" 地区 ")])),_:1}),n(_,{class:"diygw-col-13 text-clz"},{default:i((()=>[c(" IP量 ")])),_:1}),n(_,{class:"diygw-col-13 text2-clz"},{default:i((()=>[c(" 访问次数 ")])),_:1})])),_:1}),n(_,{class:"flex flex-wrap diygw-col-24 flex2-clz"},{default:i((()=>[n(_,{class:"diygw-col-24 text3-clz diygw-text-sm"},{default:i((()=>[c(x(e.province),1)])),_:2},1024),n(_,{class:"diygw-col-13 text4-clz diygw-text-sm"},{default:i((()=>[c(x(e.ipCount),1)])),_:2},1024),n(_,{class:"diygw-col-22 text5-clz diygw-text-sm"},{default:i((()=>[c(x(e.requestCount),1)])),_:2},1024),n(_,{class:"flex diygw-col-24 line-clz"},{default:i((()=>[n(_,{class:"diygw-pzx",style:{"border-bottom":"1px solid #00fc3c"}})])),_:1})])),_:2},1024)])),_:2},1024)))),128))])),_:1}),n(_,{class:"clearfix"})])),_:1})}],["__scopeId","data-v-2f05f67a"]]);export{y as default};
