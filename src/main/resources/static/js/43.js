"use strict";(self["webpackChunktableorder4"]=self["webpackChunktableorder4"]||[]).push([[43],{6043:function(e,t,s){s.r(t),s.d(t,{default:function(){return le}});var l=s(6768),a=s(4232),i=s(5130);const n={class:"regi-wrap"},r={class:"container"},o={class:"order-list"},c=(0,l.Lk)("h2",{class:"order-list-ttl"},"注文リスト",-1),d={key:0,class:"order-list-content"},u={class:"table-name"},m={id:"orderItems"},h={class:"item-name"},k={class:"item-about"},b=["onClick"],v=(0,l.Lk)("i",{class:"fas fa-trash-alt"},null,-1),y=[v],L={key:1,class:"order-list-content"},p=(0,l.Lk)("h3",null,"お会計待ちの状態ではないか、選択されていません",-1),C=[p],g={class:"order-list-footer"},f={class:"total"},E={id:"totalItems"},X={id:"totalAmount"},P={key:0,class:"alter-btn"},_={class:"table-actions-wrapper"},$={class:"table-list"},A=(0,l.Lk)("h2",{class:"table-list-ttl"},"テーブル",-1),I=(0,l.Lk)("thead",null,[(0,l.Lk)("tr",null,[(0,l.Lk)("th",null,"テーブル番号"),(0,l.Lk)("th",null,"合計金額"),(0,l.Lk)("th",null,"ステータス")])],-1),T={key:0,id:"tableBody"},w=["onClick"],S={class:"table-name"},q={class:"table-total"},D={key:0,class:"billing"},M={key:1},W={class:"checkout-buttons"},Q={id:"paymentModal",class:"modal"},U={class:"modal-content"},j=(0,l.Lk)("h2",{class:"payment-ttl"},"支払い画面",-1),B={class:"payment-detail"},F={key:0,class:"totalPayment"},K={class:"total-label"},R={key:1},J={class:"payment-input"},O=(0,l.Lk)("label",{for:"amountReceived"},"お預かり金額 : ",-1),V={key:2,class:"changeDue"},x={key:3,class:"changeDue"},z={key:5,class:"confirmPayment disable"},G={id:"confirmationMessage",class:"confirmation-message"},H=(0,l.Lk)("br",null,null,-1),N=(0,l.Lk)("br",null,null,-1);function Y(e,t,s,v,p,Y){return(0,l.uX)(),(0,l.CE)("div",n,[(0,l.Lk)("div",r,[(0,l.Lk)("div",o,[c,p.orders.items?((0,l.uX)(),(0,l.CE)("div",d,[(0,l.Lk)("span",u,(0,a.v_)(p.orders.table),1),(0,l.Lk)("ul",m,[((0,l.uX)(!0),(0,l.CE)(l.FK,null,(0,l.pI)(p.orders.items,(e=>((0,l.uX)(),(0,l.CE)("li",{class:"item-list",key:e.id},[(0,l.Lk)("span",h,(0,a.v_)(e.name),1),(0,l.Lk)("span",k,"¥ "+(0,a.v_)(e.price)+" × "+(0,a.v_)(e.amount),1),(0,l.Lk)("span",{class:"trash-icon",onClick:t=>Y.removeItem(e)},y,8,b)])))),128))])])):((0,l.uX)(),(0,l.CE)("section",L,C)),(0,l.Lk)("div",g,[(0,l.Lk)("div",f,[(0,l.Lk)("span",E,"点数: "+(0,a.v_)(p.amount),1),(0,l.Lk)("span",X,"小計: ¥"+(0,a.v_)(p.bill),1)]),p.orders.items?((0,l.uX)(),(0,l.CE)("div",P,[p.orders.items.length<=0?((0,l.uX)(),(0,l.CE)("button",{key:0,class:"delete",onClick:t[0]||(t[0]=(...e)=>Y.resetPayment&&Y.resetPayment(...e))},"取引中止")):(0,l.Q3)("",!0)])):(0,l.Q3)("",!0)])]),(0,l.Lk)("div",_,[(0,l.Lk)("div",$,[A,(0,l.Lk)("table",null,[I,p.tables.length>0?((0,l.uX)(),(0,l.CE)("tbody",T,[((0,l.uX)(!0),(0,l.CE)(l.FK,null,(0,l.pI)(p.tables,((e,t)=>((0,l.uX)(),(0,l.CE)("tr",{key:t,class:(0,a.C4)("table-line table"+(t+1)),onClick:t=>Y.getAbout(e)},[(0,l.Lk)("td",S,(0,a.v_)(e.number),1),(0,l.Lk)("td",q,"¥"+(0,a.v_)(e.total),1),"お会計待ち"===e.status?((0,l.uX)(),(0,l.CE)("td",D,(0,a.v_)(e.status),1)):((0,l.uX)(),(0,l.CE)("td",M,(0,a.v_)(e.status),1))],10,w)))),128))])):(0,l.Q3)("",!0)])]),(0,l.Lk)("div",W,[(0,l.Lk)("button",{class:"checkout-btn",id:"checkoutBtn",onClick:t[1]||(t[1]=(...e)=>Y.showPayment&&Y.showPayment(...e))},"支払いへ進む")])])]),(0,l.Lk)("div",Q,[(0,l.Lk)("div",U,[(0,l.Lk)("span",{class:"close",onClick:t[2]||(t[2]=(...e)=>Y.closePayment&&Y.closePayment(...e))},"×"),j,(0,l.Lk)("div",B,[0!=p.bill?((0,l.uX)(),(0,l.CE)("p",F,[(0,l.eW)("合計金額 : "),(0,l.Lk)("span",K,"¥"+(0,a.v_)(p.bill),1)])):((0,l.uX)(),(0,l.CE)("p",R,"何も注文していません")),(0,l.Lk)("div",J,[O,(0,l.bo)((0,l.Lk)("input",{type:"number",id:"amountReceived","onUpdate:modelValue":t[3]||(t[3]=e=>p.received=e),min:"0"},null,512),[[i.Jo,p.received]])]),p.received-p.bill>=0?((0,l.uX)(),(0,l.CE)("p",V,"お釣り: ¥"+(0,a.v_)(p.received-p.bill),1)):((0,l.uX)(),(0,l.CE)("p",x,"お釣り: ¥0")),p.received-p.bill>=0?((0,l.uX)(),(0,l.CE)("button",{key:4,class:"confirmPayment",onClick:t[4]||(t[4]=(...e)=>Y.finishPayment&&Y.finishPayment(...e))},"確定")):((0,l.uX)(),(0,l.CE)("button",z,"確定"))]),(0,l.Lk)("div",G,[(0,l.Lk)("p",null,[(0,l.eW)(" お会計ありがとうございました。"),H,(0,l.eW)(" お会計金額 : ¥"+(0,a.v_)(p.bill),1),N,(0,l.eW)(" お釣り金額 : ¥"+(0,a.v_)(p.received-p.bill),1)])])])])])}var Z=s(4821),ee={data(){return{tables:[],orders:{},bill:0,amount:0,received:0,interval:null}},methods:{getTables(){(0,Z.A)("/api/master/regi/tables").then((e=>e.json())).then((e=>{this.tables=e}))},getAbout(e){this.amount=0,this.$el.querySelector(".payment-detail").classList.remove("hide"),this.$el.querySelector(".confirmation-message").classList.add("hide"),"お会計待ち"===e.status?((0,Z.A)(`/api/master/regi/order/detail/${e.id}`).then((e=>e.json())).then((e=>{this.orders=e,this.orders.items.forEach((e=>{this.amount+=e.amount})),this.bill=e.total})),this.received=0):(this.bill=0,this.orders={})},showPayment(){this.orders.table&&(this.$el.querySelector("#paymentModal").style.display="block")},closePayment(){this.$el.querySelector("#paymentModal").style.display=""},finishPayment(){const e=this.tables.filter((e=>e.number===this.orders.table))[0];e?(e.bill=this.received,(0,Z.A)("/api/master/regi/accounting","POST",e).then((e=>{200===e.status&&this.getTables()}))):console.log("テーブル名が見つかりませんでした。"),this.$el.querySelector(".payment-detail").classList.add("hide"),this.$el.querySelector(".confirmation-message").classList.remove("hide")},resetPayment(){const e=this.tables.filter((e=>e.number===this.orders.table))[0];e?(0,Z.A)(`/api/master/regi/accounting/${e.id}`,"PUT").then((e=>{200===e.status&&this.getTables()})):console.log("テーブル名が見つかりませんでした。")},removeItem(e){if(e){const t=e.amount,s=e.price*t,l=this.orders.items.filter((t=>t!=e));this.orders.items=l,this.amount-=t,this.bill-=s,(0,Z.A)(`/api/master/regi/items/${e.id}`,"DELETE")}}},mounted(){this.getTables(),this.interval=setInterval(this.getTables,5e3),this.$emit("setDisplay","regi")},beforeUnmount(){this.interval&&clearInterval(this.interval)},emits:["setDisplay"]},te=s(1241);const se=(0,te.A)(ee,[["render",Y]]);var le=se}}]);
//# sourceMappingURL=43.js.map