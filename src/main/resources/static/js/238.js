"use strict";(self["webpackChunktableorder4"]=self["webpackChunktableorder4"]||[]).push([[238],{5238:function(e,t,a){a.r(t),a.d(t,{default:function(){return at}});var o=a(6768);const i={class:"register-wrap"},l={class:"view-wrap"};function n(e,t,a,n,s,p){const m=(0,o.g2)("SidebarField"),r=(0,o.g2)("RouterView"),d=(0,o.g2)("PopupField");return(0,o.uX)(),(0,o.CE)("div",i,[(0,o.bF)(m),(0,o.Lk)("div",l,[(0,o.bF)(r,{ref:"displays",onSetDisplay:p.setDisplay,onSetData:p.setData,onSetMethod:p.setMethod,onOpenPop:p.openPop},null,8,["onSetDisplay","onSetData","onSetMethod","onOpenPop"])]),(0,o.bF)(d,{ref:"popup",popType:s.display},null,8,["popType"])])}const s={class:"sidebar-wrapper"},p=(0,o.Lk)("figure",{class:"logo-img"},[(0,o.Lk)("img",{src:"/assets/img/logo.png",alt:"OneOrder",id:"logo"})],-1),m={class:"sidebar-list"},r={class:"sidebar-item"},d={class:"sidebar-item"},c={class:"sidebar-item"},u={class:"sidebar-item"},y={class:"sidebar-item"};function b(e,t,a,i,l,n){const b=(0,o.g2)("RouterLink");return(0,o.uX)(),(0,o.CE)("div",s,[p,(0,o.Lk)("ul",m,[(0,o.Lk)("li",r,[(0,o.bF)(b,{class:"sidebar-btn",to:"/master/regi"},{default:(0,o.k6)((()=>[(0,o.eW)("会計")])),_:1})]),(0,o.Lk)("li",d,[(0,o.bF)(b,{class:"sidebar-btn",to:"/master/history"},{default:(0,o.k6)((()=>[(0,o.eW)("取引履歴")])),_:1})]),(0,o.Lk)("li",c,[(0,o.bF)(b,{class:"sidebar-btn",to:"/master/merchandice"},{default:(0,o.k6)((()=>[(0,o.eW)("商品一覧")])),_:1})]),(0,o.Lk)("li",u,[(0,o.bF)(b,{class:"sidebar-btn",to:"/master/category"},{default:(0,o.k6)((()=>[(0,o.eW)("カテゴリ一覧")])),_:1})]),(0,o.Lk)("li",y,[(0,o.bF)(b,{class:"sidebar-btn",to:"/master/table"},{default:(0,o.k6)((()=>[(0,o.eW)("テーブル一覧")])),_:1})])])])}var g={name:"SidebarField"},k=a(1241);const D=(0,k.A)(g,[["render",b]]);var h=D;const L={class:"popup-wrap"},f={key:0,id:"popup",class:"popup item-pop"},v={class:"popup-content"},C={key:0},w={key:1};function T(e,t,a,i,l,n){const s=(0,o.g2)("MerchPop"),p=(0,o.g2)("TablePop"),m=(0,o.g2)("CatPop");return(0,o.uX)(),(0,o.CE)("div",L,[n.isNeedPop()?((0,o.uX)(),(0,o.CE)("div",f,[(0,o.Lk)("div",v,[(0,o.Lk)("span",{class:"close-btn",onClick:t[0]||(t[0]=(...e)=>n.closePopup&&n.closePopup(...e))},"×"),a.popType.data?((0,o.uX)(),(0,o.CE)("h2",w,"編集")):((0,o.uX)(),(0,o.CE)("h2",C,"追加")),"items"===a.popType.name?((0,o.uX)(),(0,o.Wv)(s,{key:2,ref:"popView",item:a.popType.data},null,8,["item"])):"table"===a.popType.name?((0,o.uX)(),(0,o.Wv)(p,{key:3,ref:"popView",item:a.popType.data},null,8,["item"])):"category"===a.popType.name?((0,o.uX)(),(0,o.Wv)(m,{key:4,ref:"popView",item:a.popType.data},null,8,["item"])):(0,o.Q3)("",!0)])])):(0,o.Q3)("",!0)])}var F=a(5130);const P={class:"form-wrap"},X=(0,o.Lk)("label",{for:"tableName"},"テーブル名",-1),E=(0,o.Lk)("label",{for:"low-capacity"},"最低収容人数",-1),N=(0,o.Lk)("label",{for:"capacity"},"最大収容人数",-1),O={class:"form-row"},S=(0,o.Lk)("label",{for:"smoking"},"禁煙・喫煙",-1),V=(0,o.Lk)("option",{value:"禁煙"},"禁煙",-1),U=(0,o.Lk)("option",{value:"喫煙"},"喫煙",-1),q=[V,U],_=(0,o.Lk)("label",{for:"type"},"席タイプ",-1),I=(0,o.Lk)("option",{value:"テーブル"},"テーブル",-1),J=(0,o.Lk)("option",{value:"個室"},"個室",-1),x=(0,o.Lk)("option",{value:"カウンター"},"カウンター",-1),A=[I,J,x],$=(0,o.Lk)("p",null,"表示",-1),W=(0,o.Lk)("button",{type:"submit",class:"save-button"},"保存",-1),M=(0,o.Lk)("label",{for:"tableName"},"テーブル名",-1),j=(0,o.Lk)("label",{for:"low-capacity"},"最低収容人数",-1),B=(0,o.Lk)("label",{for:"capacity"},"最大収容人数",-1),R=["min"],K={class:"form-row"},Q=(0,o.Lk)("label",{for:"smoking"},"禁煙・喫煙",-1),G=(0,o.Lk)("option",{value:"禁煙"},"禁煙",-1),z=(0,o.Lk)("option",{value:"喫煙"},"喫煙",-1),H=[G,z],Y=(0,o.Lk)("label",{for:"type"},"席タイプ",-1),Z=(0,o.Lk)("option",{value:"テーブル"},"テーブル",-1),ee=(0,o.Lk)("option",{value:"個室"},"個室",-1),te=(0,o.Lk)("option",{value:"カウンター"},"カウンター",-1),ae=[Z,ee,te],oe=(0,o.Lk)("p",null,"表示",-1),ie=(0,o.Lk)("button",{type:"submit",class:"save-button"},"保存",-1);function le(e,t,a,i,l,n){return(0,o.uX)(),(0,o.CE)("div",P,[l.itemData?((0,o.uX)(),(0,o.CE)("form",{key:0,id:"editForm",onSubmit:t[7]||(t[7]=(0,F.D$)(((...e)=>n.updateData&&n.updateData(...e)),["prevent"]))},[X,(0,o.bo)((0,o.Lk)("input",{type:"text",id:"tableName",name:"tableName","onUpdate:modelValue":t[0]||(t[0]=e=>l.itemData.name=e),required:""},null,512),[[F.Jo,l.itemData.name]]),E,(0,o.bo)((0,o.Lk)("input",{type:"number",name:"lowcapacity",id:"low-capacity","onUpdate:modelValue":t[1]||(t[1]=e=>l.itemData.low_capacity=e)},null,512),[[F.Jo,l.itemData.low_capacity]]),N,(0,o.bo)((0,o.Lk)("input",{type:"number",name:"capacity",id:"capacity","onUpdate:modelValue":t[2]||(t[2]=e=>l.itemData.capacity=e)},null,512),[[F.Jo,l.itemData.capacity]]),(0,o.Lk)("div",O,[(0,o.Lk)("div",null,[S,(0,o.bo)((0,o.Lk)("select",{id:"smoking",name:"smoking","onUpdate:modelValue":t[3]||(t[3]=e=>l.itemData.smoking=e)},q,512),[[F.u1,l.itemData.smoking]])]),(0,o.Lk)("div",null,[_,(0,o.bo)((0,o.Lk)("select",{id:"type",name:"type","onUpdate:modelValue":t[4]||(t[4]=e=>l.itemData.type=e)},A,512),[[F.u1,l.itemData.type]])])]),$,l.itemData.display?((0,o.uX)(),(0,o.CE)("button",{key:0,type:"button",id:"display-toggle",class:"display-toggle active",onClick:t[5]||(t[5]=e=>n.btnToggle(l.itemData))},"ON")):((0,o.uX)(),(0,o.CE)("button",{key:1,type:"button",id:"display-toggle",class:"display-toggle",onClick:t[6]||(t[6]=e=>n.btnToggle(l.itemData))},"OFF")),W],32)):((0,o.uX)(),(0,o.CE)("form",{key:1,id:"editForm",onSubmit:t[15]||(t[15]=(0,F.D$)(((...e)=>n.sendData&&n.sendData(...e)),["prevent"]))},[M,(0,o.bo)((0,o.Lk)("input",{type:"text",id:"tableName",name:"tableName","onUpdate:modelValue":t[8]||(t[8]=e=>l.tableData.name=e),required:""},null,512),[[F.Jo,l.tableData.name]]),j,(0,o.bo)((0,o.Lk)("input",{type:"number",name:"lowcapacity",id:"low-capacity","onUpdate:modelValue":t[9]||(t[9]=e=>l.tableData.low_capacity=e),min:"1"},null,512),[[F.Jo,l.tableData.low_capacity]]),B,(0,o.bo)((0,o.Lk)("input",{type:"number",name:"capacity",id:"capacity",min:l.tableData.low_capacity,"onUpdate:modelValue":t[10]||(t[10]=e=>l.tableData.capacity=e)},null,8,R),[[F.Jo,l.tableData.capacity]]),(0,o.Lk)("div",K,[(0,o.Lk)("div",null,[Q,(0,o.bo)((0,o.Lk)("select",{id:"smoking",name:"smoking","onUpdate:modelValue":t[11]||(t[11]=e=>l.tableData.smoking=e)},H,512),[[F.u1,l.tableData.smoking]])]),(0,o.Lk)("div",null,[Y,(0,o.bo)((0,o.Lk)("select",{id:"type",name:"type","onUpdate:modelValue":t[12]||(t[12]=e=>l.tableData.type=e)},ae,512),[[F.u1,l.tableData.type]])])]),oe,l.tableData.display?((0,o.uX)(),(0,o.CE)("button",{key:0,type:"button",id:"display-toggle",class:"display-toggle active",onClick:t[13]||(t[13]=e=>n.btnToggle(l.tableData))},"ON")):((0,o.uX)(),(0,o.CE)("button",{key:1,type:"button",id:"display-toggle",class:"display-toggle",onClick:t[14]||(t[14]=e=>n.btnToggle(l.tableData))},"OFF")),ie],32))])}var ne=a(4821),se={name:"TablePop",data(){return{itemData:{},displayBool:!1,tempData:{},tableData:{}}},mounted(){this.tableData.name="",this.tableData.low_capacity=1,this.tableData.capacity=4,this.tableData.smoking="禁煙",this.tableData.type="テーブル",this.tableData.display=!1},methods:{setData(e){this.itemData=e?Object.assign({},this.itemData,e):null},btnToggle(e){e.display?e.display=!1:e.display=!0},sendData(){(0,ne.A)("/api/master/tables","POST",this.tableData),window.location.reload()},updateData(){(0,ne.A)("/api/master/tables","PUT",this.itemData),window.location.reload()}}};const pe=(0,k.A)(se,[["render",le]]);var me=pe,re=a(4232);const de={class:"form-wrap"},ce=(0,o.Lk)("label",{for:"itemName"},"商品名",-1),ue=(0,o.Lk)("label",{for:"category"},"カテゴリ",-1),ye=["value"],be=(0,o.Lk)("label",{for:"price"},"価格",-1),ge=(0,o.Lk)("label",{for:"itemImage"},"商品画像",-1),ke=(0,o.Lk)("input",{type:"file",id:"itemImage",name:"itemImage",accept:"image/*"},null,-1),De=(0,o.Lk)("p",null,"商品説明",-1),he=(0,o.Lk)("p",null,"表示",-1),Le=(0,o.Lk)("button",{type:"submit",class:"save-button"},"保存",-1),fe=(0,o.Lk)("p",{class:"error-msg"},null,-1),ve=(0,o.Lk)("label",{for:"itemName"},"商品名",-1),Ce=(0,o.Lk)("label",{for:"category"},"カテゴリ",-1),we=["value"],Te=(0,o.Lk)("label",{for:"price"},"価格",-1),Fe=(0,o.Lk)("label",{for:"itemImage"},"商品画像",-1),Pe=(0,o.Lk)("input",{type:"file",id:"itemImage",name:"itemImage",accept:"image/*"},null,-1),Xe=(0,o.Lk)("p",null,"商品説明",-1),Ee=(0,o.Lk)("p",null,"表示",-1),Ne=(0,o.Lk)("button",{type:"submit",class:"save-button"},"保存",-1),Oe=(0,o.Lk)("p",{class:"error-msg"},null,-1);function Se(e,t,a,i,l,n){return(0,o.uX)(),(0,o.CE)("div",de,[l.itemData?((0,o.uX)(),(0,o.CE)("form",{key:0,id:"editForm",method:"POST",onSubmit:t[6]||(t[6]=(0,F.D$)(((...e)=>n.updateData&&n.updateData(...e)),["prevent"]))},[ce,(0,o.bo)((0,o.Lk)("input",{type:"text",id:"itemName",name:"itemName","onUpdate:modelValue":t[0]||(t[0]=e=>l.itemData.itemName=e),required:""},null,512),[[F.Jo,l.itemData.itemName]]),ue,(0,o.bo)((0,o.Lk)("select",{id:"category",name:"category","onUpdate:modelValue":t[1]||(t[1]=e=>l.itemData.category_id=e),required:""},[((0,o.uX)(!0),(0,o.CE)(o.FK,null,(0,o.pI)(l.categorys,((e,t)=>((0,o.uX)(),(0,o.CE)("option",{value:e.id,key:t},(0,re.v_)(e.name),9,ye)))),128))],512),[[F.u1,l.itemData.category_id]]),be,(0,o.bo)((0,o.Lk)("input",{type:"number",id:"price",name:"price","onUpdate:modelValue":t[2]||(t[2]=e=>l.itemData.price=e),required:""},null,512),[[F.Jo,l.itemData.price]]),ge,ke,De,(0,o.bo)((0,o.Lk)("textarea",{name:"description","onUpdate:modelValue":t[3]||(t[3]=e=>l.itemData.desc=e),id:"desc",cols:"30",rows:"10",maxlength:"512"},null,512),[[F.Jo,l.itemData.desc]]),he,l.itemData.display?((0,o.uX)(),(0,o.CE)("button",{key:0,type:"button",id:"display-toggle",class:"display-toggle active",onClick:t[4]||(t[4]=e=>n.btnToggle(l.itemData))},"ON")):((0,o.uX)(),(0,o.CE)("button",{key:1,type:"button",id:"display-toggle",class:"display-toggle",onClick:t[5]||(t[5]=e=>n.btnToggle(l.itemData))},"OFF")),Le,fe],32)):((0,o.uX)(),(0,o.CE)("form",{key:1,id:"editForm",method:"POST",onSubmit:t[13]||(t[13]=(0,F.D$)(((...e)=>n.sendData&&n.sendData(...e)),["prevent"]))},[ve,(0,o.bo)((0,o.Lk)("input",{type:"text",id:"itemName",name:"itemName","onUpdate:modelValue":t[7]||(t[7]=e=>l.merchData.name=e),required:""},null,512),[[F.Jo,l.merchData.name]]),Ce,(0,o.bo)((0,o.Lk)("select",{id:"category",name:"category","onUpdate:modelValue":t[8]||(t[8]=e=>l.merchData.category_id=e),required:""},[((0,o.uX)(!0),(0,o.CE)(o.FK,null,(0,o.pI)(l.categorys,((e,t)=>((0,o.uX)(),(0,o.CE)("option",{value:e.id,key:t},(0,re.v_)(e.name),9,we)))),128))],512),[[F.u1,l.merchData.category_id]]),Te,(0,o.bo)((0,o.Lk)("input",{type:"number",id:"price",name:"price","onUpdate:modelValue":t[9]||(t[9]=e=>l.merchData.price=e),required:""},null,512),[[F.Jo,l.merchData.price]]),Fe,Pe,Xe,(0,o.bo)((0,o.Lk)("textarea",{name:"description","onUpdate:modelValue":t[10]||(t[10]=e=>l.merchData.desc=e),id:"desc",cols:"30",rows:"10",maxlength:"512"},null,512),[[F.Jo,l.merchData.desc]]),Ee,l.merchData.display?((0,o.uX)(),(0,o.CE)("button",{key:0,type:"button",id:"display-toggle",class:"display-toggle active",onClick:t[11]||(t[11]=e=>n.btnToggle(l.merchData))},"ON")):((0,o.uX)(),(0,o.CE)("button",{key:1,type:"button",id:"display-toggle",class:"display-toggle",onClick:t[12]||(t[12]=e=>n.btnToggle(l.merchData))},"OFF")),Ne,Oe],32))])}var Ve={name:"MerchPop",data(){return{itemData:{},displayBool:!1,tempData:{},categorys:[],merchData:{}}},props:{item:Object},mounted(){this.merchData.name="",this.merchData.category_id=null,this.merchData.price=0,this.merchData.display=!1,this.merchData.desc="",this.merchData.image="assets/img/noimage.png",this.$el.querySelector("#itemImage").value=null,(0,ne.A)("/api/master/categorys").then((e=>e.json())).then((e=>{this.categorys=e}))},methods:{setData(e){this.itemData=e?Object.assign({},this.itemData,e):null},btnToggle(e){e.display?e.display=!1:e.display=!0},sendData(){const e=document.querySelector("#itemImage"),t=e.files[0];if(t){this.merchData.image=`upload/img/${t.name}`;const e=new FormData;e.append("images",t,t.name);const a={method:"POST",body:e};fetch("/api/master/merchImage",a).then((e=>{if(413===e.status){const e=document.querySelector(".error-msg");e.textContent="画像ファイルの容量が大きすぎます",setTimeout((()=>{e.textContent=""}),3e3)}}))}(0,ne.A)("/api/master/merchandices","POST",this.merchData),window.location.reload()},updateData(){const e=document.querySelector("#itemImage"),t={id:this.itemData.id,name:this.itemData.itemName,category:this.itemData.category_id,price:this.itemData.price,display:this.itemData.display,image:this.itemData.itemImage,desc:this.itemData.desc},a=e.files[0];if(a){t.image="upload/img/"+a.name;const e=new FormData;e.append("images",a),e.append("oldImage",this.itemData.itemImage);const o={method:"PUT",body:e};fetch("/api/master/merchImage",o).then((e=>{if(413===e.status){const e=document.querySelector(".error-msg");e.textContent="画像ファイルの容量が大きすぎます",setTimeout((()=>{e.textContent=""}),3e3)}}))}(0,ne.A)("/api/master/merchandices","PUT",t),window.location.reload()}}};const Ue=(0,k.A)(Ve,[["render",Se]]);var qe=Ue;const _e={class:"form-wrap"},Ie=(0,o.Lk)("label",{for:"categoryName"},"カテゴリー名",-1),Je=(0,o.Lk)("br",null,null,-1),xe=(0,o.Lk)("p",null,"色",-1),Ae=(0,o.Lk)("p",null,"表示",-1),$e=(0,o.Lk)("button",{type:"submit",class:"save-button"},"保存",-1),We=(0,o.Lk)("label",{for:"categoryName"},"カテゴリー名",-1),Me=(0,o.Lk)("br",null,null,-1),je=(0,o.Lk)("label",{for:"categoryColor"},"色",-1),Be=(0,o.Lk)("p",null,"表示",-1),Re=(0,o.Lk)("button",{type:"submit",class:"save-button"},"登録",-1);function Ke(e,t,a,i,l,n){return(0,o.uX)(),(0,o.CE)("div",_e,[l.itemData?((0,o.uX)(),(0,o.CE)("form",{key:0,id:"editForm",onSubmit:t[4]||(t[4]=(0,F.D$)(((...e)=>n.updateData&&n.updateData(...e)),["prevent"]))},[Ie,(0,o.bo)((0,o.Lk)("input",{type:"text",id:"categoryName",name:"categoryName","onUpdate:modelValue":t[0]||(t[0]=e=>l.itemData.name=e),required:""},null,512),[[F.Jo,l.itemData.name]]),Je,xe,(0,o.bo)((0,o.Lk)("input",{type:"color",name:"categoryColor",id:"categoryColor","onUpdate:modelValue":t[1]||(t[1]=e=>l.itemData.color=e),required:""},null,512),[[F.Jo,l.itemData.color]]),Ae,l.itemData.display?((0,o.uX)(),(0,o.CE)("button",{key:0,type:"button",id:"display-toggle",class:"display-toggle active",onClick:t[2]||(t[2]=e=>n.btnToggle(l.itemData))},"ON")):((0,o.uX)(),(0,o.CE)("button",{key:1,type:"button",id:"display-toggle",class:"display-toggle",onClick:t[3]||(t[3]=e=>n.btnToggle(l.itemData))},"OFF")),$e],32)):((0,o.uX)(),(0,o.CE)("form",{key:1,id:"editForm",onSubmit:t[9]||(t[9]=(0,F.D$)(((...e)=>n.sendData&&n.sendData(...e)),["prevent"]))},[We,(0,o.bo)((0,o.Lk)("input",{type:"text",id:"categoryName",name:"categoryName","onUpdate:modelValue":t[5]||(t[5]=e=>l.catData.name=e),required:""},null,512),[[F.Jo,l.catData.name]]),Me,je,(0,o.bo)((0,o.Lk)("input",{type:"color",name:"categoryColor",id:"categoryColor","onUpdate:modelValue":t[6]||(t[6]=e=>l.catData.color=e),required:""},null,512),[[F.Jo,l.catData.color]]),Be,l.catData.display?((0,o.uX)(),(0,o.CE)("button",{key:0,type:"button",id:"display-toggle",class:"display-toggle active",onClick:t[7]||(t[7]=e=>n.btnToggle(l.catData))},"ON")):((0,o.uX)(),(0,o.CE)("button",{key:1,type:"button",id:"display-toggle",class:"display-toggle",onClick:t[8]||(t[8]=e=>n.btnToggle(l.catData))},"OFF")),Re],32))])}var Qe={name:"CatPop",data(){return{itemData:{},displayBool:!1,tempData:{},categorys:[],catData:{}}},props:{item:Object},mounted(){this.catData.name="",this.catData.color="#000000",this.catData.display=!1},methods:{setData(e){this.itemData=e?Object.assign({},this.itemData,e):null},btnToggle(e){e.display?e.display=!1:e.display=!0},sendData(){(0,ne.A)("/api/master/categorys","POST",this.catData),window.location.reload()},updateData(){(0,ne.A)("/api/master/categorys","PUT",this.itemData),window.location.reload()}}};const Ge=(0,k.A)(Qe,[["render",Ke]]);var ze=Ge,He={name:"PopupField",components:{TablePop:me,MerchPop:qe,CatPop:ze},props:{popType:Object},methods:{showPopup(){const e=this.$el.querySelector(".popup");this.popType.data?this.$refs.popView.setData(this.popType.data):this.$refs.popView.setData(null),e.style.display="block"},isNeedPop(){const e=["table","items","category"];return e.some((e=>e===this.popType.name))},closePopup(){this.$el.querySelector(".popup").style.display=""}}};const Ye=(0,k.A)(He,[["render",T]]);var Ze=Ye,et={name:"RegiMaster",components:{SidebarField:h,PopupField:Ze},data(){return{display:{name:"regi",data:null,method:"GET"}}},methods:{setDisplay(e){this.display.name=e},setData(e){this.display.data=e},setMethod(e){this.display.method=e},openPop(){this.$refs.popup.showPopup()},openPopWithData(e){this.setData(e),this.openPop()}}};const tt=(0,k.A)(et,[["render",n]]);var at=tt}}]);
//# sourceMappingURL=238.js.map