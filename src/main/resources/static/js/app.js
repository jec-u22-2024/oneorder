(function(){"use strict";var e={4821:function(e,t,n){async function r(e="",t="GET",n){const r={method:t,headers:{"Content-Type":"application/json"}};n&&(n instanceof FormData?(console.log(n),r.body=n):r.body=JSON.stringify(n));const o=await fetch(e,r);return o}n.d(t,{A:function(){return r}})},3382:function(e,t,n){var r=n(5130),o=n(6768);function a(e,t,n,r,a,i){const c=(0,o.g2)("RouterView");return(0,o.uX)(),(0,o.Wv)(c,{table:a.table,onSetMasterTable:i.setMasterTable},null,8,["table","onSetMasterTable"])}var i={name:"App",data(){return{table:null}},methods:{setMasterTable(e){this.table=e,sessionStorage.setItem("table",this.table)}}},c=n(1241);const s=(0,c.A)(i,[["render",a]]);var u=s,l=n(8253),d=n(9890),p=n(1807),m=n(3848),f=n(514),h=n(3441),b=n(8364),v=n(1255),g=n(7276),y=n(9717),w=n(1387);const k={class:"welcomeMessage"},O=(0,o.Lk)("h2",{class:"welcome-ttl"},"Welcome To OneOrder",-1),A=(0,o.Lk)("p",{class:"welcome-desc"},[(0,o.eW)(" OneOrder は 居酒屋向けを前提としたモバイルオーダーシステムです。"),(0,o.Lk)("br"),(0,o.Lk)("br"),(0,o.eW)(" 手違いでの注文で問題が発生した場合や"),(0,o.Lk)("br"),(0,o.eW)(" 未成年の飲酒については OneOrder は一切の責任を負いません。"),(0,o.Lk)("br"),(0,o.eW)(" (適当な説明文です) ")],-1),T=(0,o.Lk)("p",{class:"error-text"},null,-1),L=(0,o.Lk)("p",{class:"welcome-ok"},"以上に問題がない方はOKを押してください。",-1);function C(e,t,n,r,a,i){const c=(0,o.g2)("RouterLink");return(0,o.uX)(),(0,o.CE)("section",k,[O,A,T,L,(0,o.bF)(c,{class:"welcome-btn",to:"order/main"},{default:(0,o.k6)((()=>[(0,o.eW)("OK")])),_:1})])}n(4603),n(7566),n(8721);var S=n(4821),E={name:"HomeField",data(){return{table:null}},mounted(){const e=new URL(window.location.href).searchParams;if(e.size>0||sessionStorage.getItem("table")){if(e.get("tb")){const t=e.get("tb");(0,S.A)(`/api/table/status/${t}`).then((e=>e.json())).then((e=>{if(e.error){const e=document.querySelector(".welcome-btn");e.remove();const t=document.createElement("h2");t.textContent="このテーブルは現在使用できません。",t.className="Unwelcome-msg",this.$el.appendChild(t)}else if("お会計待ち"===e.status){const e=document.querySelector(".welcome-btn");e.remove();const t=document.createElement("h2");t.textContent="このテーブルはお会計待ちの状態です。",t.className="Unwelcome-msg",this.$el.appendChild(t)}else"来店済み"!==e.status&&"空き状態"!==e.status||(this.table=e.table,this.$emit("setMasterTable",this.table))}))}}else{const e=this.$el.querySelector(".error-text");e.innerHTML+="テーブル情報が設定されていないため、このまま進めても注文はできません。<br>操作方法がわからない場合はホールスタッフを呼んでください。"}},emits:["setMasterTable"]};const M=(0,c.A)(E,[["render",C]]);var j=M;const P=(0,w.aE)({history:(0,w.LA)("/"),routes:[{path:"/",name:"home",component:j},{path:"/order",name:"orderMaster",component:()=>n.e(898).then(n.bind(n,5898)),children:[{path:"main",name:"OrderList",component:()=>n.e(251).then(n.bind(n,8251)),props:!0},{path:"cart",name:"CartField",component:()=>n.e(757).then(n.bind(n,5757)),props:!0},{path:"hist",name:"UserHistory",component:()=>n.e(738).then(n.bind(n,4738)),props:!0},{path:"accounting",name:"UserAccounting",component:()=>n.e(27).then(n.bind(n,2408))}]},{path:"/master",name:"regiMaster",component:()=>n.e(456).then(n.bind(n,4456)),children:[{path:"regi",name:"Regi",component:()=>n.e(536).then(n.bind(n,9536))},{path:"category",component:()=>n.e(678).then(n.bind(n,6678)),props:!0},{path:"merchandice",name:"ItemList",component:()=>n.e(666).then(n.bind(n,3666)),props:!0},{path:"table",name:"TableLayout",component:()=>n.e(919).then(n.bind(n,2919)),props:!0},{path:"history",name:"OrderHistory",component:()=>n.e(408).then(n.bind(n,1408)),props:!0}]},{path:"/kitchen",name:"KitchenMaster",component:()=>Promise.all([n.e(236),n.e(457)]).then(n.bind(n,6457)),children:[{path:"home",name:"HomeView",component:()=>Promise.all([n.e(236),n.e(499)]).then(n.bind(n,2499))},{path:"order",name:"KitchenOrder",component:()=>Promise.all([n.e(236),n.e(65)]).then(n.bind(n,65))}]},{path:"/:pathMatch(.*)*",name:"ErrorWindow",component:()=>n.e(34).then(n.bind(n,6034))}]});var N=P;const x=(0,r.Ef)(u),F={ripple:!0,theme:{preset:d.A,options:{darkModeSelector:".my-app-dark"}}};x.use(l.Ay,F),x.directive("ripple",y.A),x.use(N),x.component("TabsField",p.A),x.component("TabList",m.A),x.component("TabField",f.A),x.component("TabPanels",h.A),x.component("TabPanel",b.A),x.component("InputNumber",v.A),x.component("ScrollPanel",g.A),x.mount("#app")}},t={};function n(r){var o=t[r];if(void 0!==o)return o.exports;var a=t[r]={exports:{}};return e[r].call(a.exports,a,a.exports,n),a.exports}n.m=e,function(){var e=[];n.O=function(t,r,o,a){if(!r){var i=1/0;for(l=0;l<e.length;l++){r=e[l][0],o=e[l][1],a=e[l][2];for(var c=!0,s=0;s<r.length;s++)(!1&a||i>=a)&&Object.keys(n.O).every((function(e){return n.O[e](r[s])}))?r.splice(s--,1):(c=!1,a<i&&(i=a));if(c){e.splice(l--,1);var u=o();void 0!==u&&(t=u)}}return t}a=a||0;for(var l=e.length;l>0&&e[l-1][2]>a;l--)e[l]=e[l-1];e[l]=[r,o,a]}}(),function(){n.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return n.d(t,{a:t}),t}}(),function(){n.d=function(e,t){for(var r in t)n.o(t,r)&&!n.o(e,r)&&Object.defineProperty(e,r,{enumerable:!0,get:t[r]})}}(),function(){n.f={},n.e=function(e){return Promise.all(Object.keys(n.f).reduce((function(t,r){return n.f[r](e,t),t}),[]))}}(),function(){n.u=function(e){return"js/"+e+".js"}}(),function(){n.miniCssF=function(e){return"css/"+e+".css"}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){n.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){var e={},t="tableorder4:";n.l=function(r,o,a,i){if(e[r])e[r].push(o);else{var c,s;if(void 0!==a)for(var u=document.getElementsByTagName("script"),l=0;l<u.length;l++){var d=u[l];if(d.getAttribute("src")==r||d.getAttribute("data-webpack")==t+a){c=d;break}}c||(s=!0,c=document.createElement("script"),c.charset="utf-8",c.timeout=120,n.nc&&c.setAttribute("nonce",n.nc),c.setAttribute("data-webpack",t+a),c.src=r),e[r]=[o];var p=function(t,n){c.onerror=c.onload=null,clearTimeout(m);var o=e[r];if(delete e[r],c.parentNode&&c.parentNode.removeChild(c),o&&o.forEach((function(e){return e(n)})),t)return t(n)},m=setTimeout(p.bind(null,void 0,{type:"timeout",target:c}),12e4);c.onerror=p.bind(null,c.onerror),c.onload=p.bind(null,c.onload),s&&document.head.appendChild(c)}}}(),function(){n.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})}}(),function(){n.p="/"}(),function(){if("undefined"!==typeof document){var e=function(e,t,r,o,a){var i=document.createElement("link");i.rel="stylesheet",i.type="text/css",n.nc&&(i.nonce=n.nc);var c=function(n){if(i.onerror=i.onload=null,"load"===n.type)o();else{var r=n&&n.type,c=n&&n.target&&n.target.href||t,s=new Error("Loading CSS chunk "+e+" failed.\n("+r+": "+c+")");s.name="ChunkLoadError",s.code="CSS_CHUNK_LOAD_FAILED",s.type=r,s.request=c,i.parentNode&&i.parentNode.removeChild(i),a(s)}};return i.onerror=i.onload=c,i.href=t,r?r.parentNode.insertBefore(i,r.nextSibling):document.head.appendChild(i),i},t=function(e,t){for(var n=document.getElementsByTagName("link"),r=0;r<n.length;r++){var o=n[r],a=o.getAttribute("data-href")||o.getAttribute("href");if("stylesheet"===o.rel&&(a===e||a===t))return o}var i=document.getElementsByTagName("style");for(r=0;r<i.length;r++){o=i[r],a=o.getAttribute("data-href");if(a===e||a===t)return o}},r=function(r){return new Promise((function(o,a){var i=n.miniCssF(r),c=n.p+i;if(t(i,c))return o();e(r,c,null,o,a)}))},o={524:0};n.f.miniCss=function(e,t){var n={65:1,251:1,456:1,457:1,536:1,738:1,919:1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=r(e).then((function(){o[e]=0}),(function(t){throw delete o[e],t})))}}}(),function(){var e={524:0};n.f.j=function(t,r){var o=n.o(e,t)?e[t]:void 0;if(0!==o)if(o)r.push(o[2]);else{var a=new Promise((function(n,r){o=e[t]=[n,r]}));r.push(o[2]=a);var i=n.p+n.u(t),c=new Error,s=function(r){if(n.o(e,t)&&(o=e[t],0!==o&&(e[t]=void 0),o)){var a=r&&("load"===r.type?"missing":r.type),i=r&&r.target&&r.target.src;c.message="Loading chunk "+t+" failed.\n("+a+": "+i+")",c.name="ChunkLoadError",c.type=a,c.request=i,o[1](c)}};n.l(i,s,"chunk-"+t,t)}},n.O.j=function(t){return 0===e[t]};var t=function(t,r){var o,a,i=r[0],c=r[1],s=r[2],u=0;if(i.some((function(t){return 0!==e[t]}))){for(o in c)n.o(c,o)&&(n.m[o]=c[o]);if(s)var l=s(n)}for(t&&t(r);u<i.length;u++)a=i[u],n.o(e,a)&&e[a]&&e[a][0](),e[a]=0;return n.O(l)},r=self["webpackChunktableorder4"]=self["webpackChunktableorder4"]||[];r.forEach(t.bind(null,0)),r.push=t.bind(null,r.push.bind(r))}();var r=n.O(void 0,[504],(function(){return n(3382)}));r=n.O(r)})();
//# sourceMappingURL=app.js.map