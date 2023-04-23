import{u as t,m as e,D as s,o as i,c as n,w as o,b as r,v as h,q as d,p as a,e as c}from"./index.9d56859b.js";import{_ as l}from"./plugin-vue_export-helper.21dcd24c.js";var f=l({name:"diy-sticky",props:{offsetTop:{type:[Number,String],default:0},index:{type:[Number,String],default:""},enable:{type:Boolean,default:!0},h5NavHeight:{type:[Number,String],default:44},className:{type:String,default:""},bgColor:{type:String,default:"#ffffff"},zIndex:{type:[Number,String],default:""}},data(){return{fixed:!1,height:"auto",stickyTop:0,elClass:this.$tools.guid(),left:0,width:"auto"}},watch:{offsetTop(t){this.initObserver()},enable(t){0==t?(this.fixed=!1,this.disconnectObserver("contentObserver")):this.initObserver()}},computed:{uZIndex(){return this.zIndex?this.zIndex:999999}},mounted(){this.initObserver()},methods:{initObserver(){this.enable&&(this.stickyTop=0!=this.offsetTop?t(this.offsetTop)+this.h5NavHeight:this.h5NavHeight,this.disconnectObserver("contentObserver"),this.getRect("."+this.elClass).then((t=>{this.height=t.height,this.left=t.left,this.width=t.width,this.$nextTick((()=>{this.observeContent()}))})))},getRect(t){return new Promise((s=>{e().in(this).select(t).boundingClientRect((t=>{s(t)})).exec()}))},observeContent(){this.disconnectObserver("contentObserver");const t=s(this,{thresholds:[.95,.98,1]});t.relativeToViewport({top:-this.stickyTop}),t.observe("."+this.elClass,(t=>{this.enable&&this.setFixed(t.boundingClientRect.top)})),this.contentObserver=t},setFixed(t){const e=t<this.stickyTop;e?this.$emit("fixed",this.index):this.fixed&&this.$emit("unfixed",this.index),this.fixed=e},disconnectObserver(t){const e=this[t];e&&e.disconnect()}},beforeDestroy(){this.disconnectObserver("contentObserver")}},[["render",function(t,e,s,l,f,p){const u=c;return i(),n(u,{class:""},{default:o((()=>[r(u,{class:a(["diy-sticky-wrap",[f.elClass,s.className]]),style:h({height:f.fixed?f.height+"px":"auto",backgroundColor:s.bgColor})},{default:o((()=>[r(u,{class:"diy-sticky",style:h({position:f.fixed?"fixed":"static",top:f.stickyTop+"px",left:f.left+"px",width:"auto"==f.width?"auto":f.width+"px",zIndex:p.uZIndex})},{default:o((()=>[d(t.$slots,"default",{},void 0,!0)])),_:3},8,["style"])])),_:3},8,["class","style"])])),_:3})}],["__scopeId","data-v-ab6c5fe0"]]);export{f as _};