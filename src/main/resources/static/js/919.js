"use strict";(self["webpackChunktableorder4"]=self["webpackChunktableorder4"]||[]).push([[919],{2919:function(t,e,l){l.r(e),l.d(e,{default:function(){return $}});var a=l(6768),s=l(4232);const i={class:"table-wrap"},n=(0,a.Lk)("h1",null,"テーブルレイアウト設定",-1),o={class:"button-container"},u={class:"grid"},d={id:"tableLayout"},k=(0,a.Lk)("thead",null,[(0,a.Lk)("tr",null,[(0,a.Lk)("th",null,"テーブル名"),(0,a.Lk)("th",null,"最大収容人数"),(0,a.Lk)("th",null,"禁煙・喫煙"),(0,a.Lk)("th",null,"席タイプ"),(0,a.Lk)("th",null,"表示"),(0,a.Lk)("th"),(0,a.Lk)("th")])],-1),c={key:0},b=["id"],r={class:"table-name"},p={class:"table-capacity"},h={class:"table-smoking"},L={class:"table-type"},m=["onClick"],y=["onClick"],C=["onClick"],g=["onClick"];function v(t,e,l,v,f,D){return(0,a.uX)(),(0,a.CE)("div",i,[n,(0,a.Lk)("div",o,[(0,a.Lk)("button",{class:"add-button",onClick:e[0]||(e[0]=(...t)=>D.add&&D.add(...t))},"追加する")]),(0,a.Lk)("div",u,[(0,a.Lk)("table",d,[k,f.tables.length>0?((0,a.uX)(),(0,a.CE)("tbody",c,[((0,a.uX)(!0),(0,a.CE)(a.FK,null,(0,a.pI)(f.tables,((t,e)=>((0,a.uX)(),(0,a.CE)("tr",{key:e,id:e},[(0,a.Lk)("td",r,(0,s.v_)(t.name),1),(0,a.Lk)("td",p,(0,s.v_)(t.low_capacity)+"名〜"+(0,s.v_)(t.capacity)+"名",1),(0,a.Lk)("td",h,(0,s.v_)(t.smoking),1),(0,a.Lk)("td",L,(0,s.v_)(t.type),1),(0,a.Lk)("td",null,[t.display?((0,a.uX)(),(0,a.CE)("button",{key:0,class:"display-btn",onClick:e=>D.toggleDisplay(t)},"表示",8,m)):((0,a.uX)(),(0,a.CE)("button",{key:1,class:"display-btn hide",onClick:e=>D.toggleDisplay(t)},"非表示",8,y))]),(0,a.Lk)("td",null,[(0,a.Lk)("i",{class:"fa-lg fa-solid fa-pen-to-square edit-button",onClick:e=>D.edit(t)},null,8,C)]),(0,a.Lk)("td",null,[(0,a.Lk)("i",{class:"fa-lg fa-solid fa-trash delete-button",onClick:e=>D.removeItem(t)},null,8,g)])],8,b)))),128))])):(0,a.Q3)("",!0)])])])}var f=l(4821),D={name:"TableLayout",data(){return{tables:[],keyVisible:!1}},mounted(){this.getTables(),this.$emit("setDisplay","table")},emits:["setDisplay","openPop","setData"],methods:{getTables(){(0,f.A)("/api/master/tables").then((t=>t.json())).then((t=>{this.tables=t}))},toggleDisplay(t){(0,f.A)(`/api/master/tables/${t.id}`,"PUT",t),setTimeout(this.getTables,100)},removeItem(t){(0,f.A)(`/api/master/tables/${t.id}`,"DELETE"),setTimeout(this.getTables,100)},edit(t){this.$emit("setData",t),this.$emit("openPop")},add(){this.$emit("setData",null),this.$emit("openPop")}}},E=l(1241);const T=(0,E.A)(D,[["render",v]]);var $=T}}]);
//# sourceMappingURL=919.js.map