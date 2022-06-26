(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["about"],{a55b:function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"pt-5"},[t.email?s("h1",[t._v("Hello, "+t._s(t.email))]):t._e(),s("form",{on:{submit:function(e){return e.preventDefault(),t.login(e)}}},[s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"email"}},[t._v("Email")]),s("input",{directives:[{name:"model",rawName:"v-model",value:t.email,expression:"email"}],staticClass:"form-control",attrs:{type:"text",id:"email","aria-describedby":"usernameHelp",placeholder:"Enter email"},domProps:{value:t.email},on:{input:function(e){e.target.composing||(t.email=e.target.value)}}}),s("small",{staticClass:"form-text text-muted",attrs:{id:"usernameHelp"}},[t._v("We'll never share your email with anyone else.")])]),s("div",{staticClass:"form-group"},[s("label",{attrs:{for:"exampleInputPassword1"}},[t._v("Password")]),s("input",{directives:[{name:"model",rawName:"v-model",value:t.password,expression:"password"}],staticClass:"form-control",attrs:{type:"password",id:"exampleInputPassword1",placeholder:"Password"},domProps:{value:t.password},on:{input:function(e){e.target.composing||(t.password=e.target.value)}}})]),s("button",{staticClass:"btn btn-primary mt-2",attrs:{type:"submit"}},[t._v("Submit")])])])},r=[],n={name:"Login",data:function(){return{email:"",password:""}},methods:{login:function(){var t=this;this.$axios.post("/api/users/login",{email:this.email,password:this.password}).then((function(e){localStorage.setItem("jwt",e.data.jwt),t.$router.push({name:"Articles"})}))}}},i=n,c=s("2877"),l=Object(c["a"])(i,a,r,!1,null,"0be1ac34",null);e["default"]=l.exports},c9a4:function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"subjects"},[s("h1",{staticClass:"mt-4"},[t._v("Subjects")]),s("div",{staticClass:"row"},[s("div",{staticClass:"col-4"},[s("table",{staticClass:"table"},[t._m(0),s("tbody",t._l(t.subjects,(function(e){return s("tr",{key:e.id,on:{click:function(s){t.selectedSubject=e}}},[s("th",{attrs:{scope:"row"}},[t._v(t._s(e.id))]),s("td",[t._v(t._s(t._f("capitalize")(e.title)))]),s("td",[t._v(t._s(t._f("shortText")(e.content)))])])})),0)])]),s("div",{staticClass:"col-6"},[t.selectedSubject?s("subject",{attrs:{subject:t.selectedSubject}}):t._e()],1)])])},r=[function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("thead",[s("tr",[s("th",{attrs:{scope:"col"}},[t._v("ID")]),s("th",{attrs:{scope:"col"}},[t._v("Title")]),s("th",{attrs:{scope:"col"}},[t._v("Content")])])])}],n=(s("fb6a"),s("fa7e")),i={components:{Subject:n["a"]},filters:{shortText:function(t){return t.length<30?t:t.slice(0,30)+"..."}},data:function(){return{selectedSubject:null,subjects:[]}},mounted:function(){var t=this;this.$axios.get("/api/subjects").then((function(e){t.subjects=e.data}))}},c=i,l=s("2877"),o=Object(l["a"])(c,a,r,!1,null,null,null);e["default"]=o.exports},ed05:function(t,e,s){"use strict";s.r(e);var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("subject",{attrs:{subject:t.subject}})],1)},r=[],n=s("fa7e"),i={name:"SingleSubject",components:{Subject:n["a"]},data:function(){return{subject:{}}},created:function(){var t=this;this.$axios.get("/api/articles/".concat(this.$route.params.id)).then((function(e){t.subject=e.data}))}},c=i,l=s("2877"),o=Object(l["a"])(c,a,r,!1,null,"00294332",null);e["default"]=o.exports},fa7e:function(t,e,s){"use strict";var a=function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",[s("h2",[t._v(" Predmet: "+t._s(t.subject.title)+" ")]),s("h5",[t._v("Opis:")]),s("p",[t._v(" "+t._s(t.subject.content)+" "),s("br"),s("br")])])},r=[],n={name:"Subject",props:{subject:{type:Object,required:!0}}},i=n,c=s("2877"),l=Object(c["a"])(i,a,r,!1,null,"6de02a1c",null);e["a"]=l.exports}}]);
//# sourceMappingURL=about.c1e7533c.js.map