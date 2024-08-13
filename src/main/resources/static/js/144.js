"use strict";(self["webpackChunktableorder4"]=self["webpackChunktableorder4"]||[]).push([[144],{1144:function(t,e,n){n.r(e),n.d(e,{default:function(){return X}});var o=n(6768),c=n(4232);const r={class:"kitchen-grid"},a={class:"col-12 md:col-6 lg:col-3"},l={style:{display:"flex"}},s={key:0,class:"m-0",style:{"padding-right":"10px"}},i={key:1,class:"m-0",style:{color:"#F87954","padding-right":"10px"}},d={key:2,class:"m-0",style:{color:"#E91E63"}},u={style:{display:"flex","align-items":"center","justify-content":"space-between"}};function p(t,e,n,p,h,m){const v=(0,o.g2)("Button"),b=(0,o.g2)("Card");return(0,o.uX)(),(0,o.CE)("main",null,[(0,o.Lk)("div",r,[((0,o.uX)(!0),(0,o.CE)(o.FK,null,(0,o.pI)(h.order?.table_id,(t=>((0,o.uX)(),(0,o.CE)("div",a,[(0,o.bF)(b,null,{title:(0,o.k6)((()=>[(0,o.Lk)("span",{style:(0,c.Tr)({backgroundColor:t.table_color})},(0,c.v_)(t.table_name),5)])),content:(0,o.k6)((()=>[(0,o.Lk)("span",l,[1==t.count?((0,o.uX)(),(0,o.CE)("h1",s," 1 ")):t.count<=10?((0,o.uX)(),(0,o.CE)("h1",i,(0,c.v_)(t.count),1)):((0,o.uX)(),(0,o.CE)("h1",d,(0,c.v_)(t.count),1)),(0,o.Lk)("div",null,(0,c.v_)(t.item_name),1)]),(0,o.Lk)("span",u,[(0,o.bF)(v,{label:"調理完了",severity:"info",onClick:e=>m.cookingComplete(t.item_id)},null,8,["onClick"]),(0,o.Lk)("span",null,(0,c.v_)(m.getElapsedTime(t.date))+"経過",1)])])),_:2},1024)])))),256))])])}var h=n(7094),m=n(3320),v=function(t){var e=t.dt;return"\n.p-card {\n    background: ".concat(e("card.background"),";\n    color: ").concat(e("card.color"),";\n    box-shadow: ").concat(e("card.shadow"),";\n    border-radius: ").concat(e("card.border.radius"),";\n    display: flex;\n    flex-direction: column;\n}\n\n.p-card-caption {\n    display: flex;\n    flex-direction: column;\n    gap: ").concat(e("card.caption.gap"),";\n}\n\n.p-card-body {\n    padding: ").concat(e("card.body.padding"),";\n    display: flex;\n    flex-direction: column;\n    gap: ").concat(e("card.body.gap"),";\n}\n\n.p-card-title {\n    font-size: ").concat(e("card.title.font.size"),";\n    font-weight: ").concat(e("card.title.font.weight"),";\n}\n\n.p-card-subtitle {\n    color: ").concat(e("card.subtitle.color"),";\n}\n")},b={root:"p-card p-component",header:"p-card-header",body:"p-card-body",caption:"p-card-caption",title:"p-card-title",subtitle:"p-card-subtitle",content:"p-card-content",footer:"p-card-footer"},f=m.A.extend({name:"card",theme:v,classes:b}),k={name:"BaseCard",extends:h.A,style:f,provide:function(){return{$pcCard:this,$parentInstance:this}}},y={name:"Card",extends:k,inheritAttrs:!1};function g(t,e,n,c,r,a){return(0,o.uX)(),(0,o.CE)("div",(0,o.v6)({class:t.cx("root")},t.ptmi("root")),[t.$slots.header?((0,o.uX)(),(0,o.CE)("div",(0,o.v6)({key:0,class:t.cx("header")},t.ptm("header")),[(0,o.RG)(t.$slots,"header")],16)):(0,o.Q3)("",!0),(0,o.Lk)("div",(0,o.v6)({class:t.cx("body")},t.ptm("body")),[t.$slots.title||t.$slots.subtitle?((0,o.uX)(),(0,o.CE)("div",(0,o.v6)({key:0,class:t.cx("caption")},t.ptm("caption")),[t.$slots.title?((0,o.uX)(),(0,o.CE)("div",(0,o.v6)({key:0,class:t.cx("title")},t.ptm("title")),[(0,o.RG)(t.$slots,"title")],16)):(0,o.Q3)("",!0),t.$slots.subtitle?((0,o.uX)(),(0,o.CE)("div",(0,o.v6)({key:1,class:t.cx("subtitle")},t.ptm("subtitle")),[(0,o.RG)(t.$slots,"subtitle")],16)):(0,o.Q3)("",!0)],16)):(0,o.Q3)("",!0),(0,o.Lk)("div",(0,o.v6)({class:t.cx("content")},t.ptm("content")),[(0,o.RG)(t.$slots,"content")],16),t.$slots.footer?((0,o.uX)(),(0,o.CE)("div",(0,o.v6)({key:1,class:t.cx("footer")},t.ptm("footer")),[(0,o.RG)(t.$slots,"footer")],16)):(0,o.Q3)("",!0)],16)],16)}y.render=g;var C=n(5236),x=n(4821),E={components:{Card:y,Button:C.A},data(){return{interval:null,order:null}},mounted(){this.orderFetch(),this.interval=setInterval((()=>{this.orderFetch()}),5e3)},beforeUnmount(){this.interval&&clearInterval(this.interval)},methods:{orderFetch(){(0,x.A)("/api/kitchen/orders").then((t=>t.json())).then((t=>{console.log(t),this.order=t}))},itemFetch(){if(null!=this.order&&0!=this.order.table_id.length)for(const t of this.order.table_id)(0,x.A)(`/api/kitchen/items/${t.item_id}`).then((t=>t.json())).then((e=>t.itemDetail=e))},cookingComplete(t){(0,x.A)(`/api/kitchen/cooked/${t}`,"PUT"),this.orderFetch()},getElapsedTime(t){const e=new Date,n=new Date(t),o=e-n,c=Math.floor(o/1e3/60);if(c>=60){const t=Math.floor(c/60);return t+"時間"}return c+"分"}}},$=n(1241);const _=(0,$.A)(E,[["render",p]]);var X=_}}]);
//# sourceMappingURL=144.js.map