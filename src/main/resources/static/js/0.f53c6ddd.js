(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[0],{"//Eu":function(e,t,s){"use strict";s.r(t);var r=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"ViewCourseStudents"},[s("app-course-header",{attrs:{course:e.course}}),s("br"),s("div",[s("div",{staticClass:"container-fluid"},[s("div",{staticClass:"table-responsive-md"},[s("table",{staticClass:"table table-sm"},[e._m(0),s("tbody",e._l(e.course.students,function(t,r){return s("tr",[s("th",{attrs:{scope:"row"}},[e._v(e._s(r+1))]),s("td",[e._v(e._s(t.firstName)+" "+e._s(t.lastName))]),s("td",[s("a",{attrs:{href:"mailto:"+t.email.toLowerCase()}},[e._v(e._s(t.email))])]),s("td",[e._v(e._s(t.hall)+" "+e._s(t.roomNumber))]),s("td",[e._v(e._s(t.program.name))])])}))])])])])],1)},a=[function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("thead",[s("tr",[s("th",{attrs:{scope:"col"}},[e._v("#")]),s("th",{attrs:{scope:"col"}},[e._v("Name")]),s("th",{attrs:{scope:"col"}},[e._v("Email")]),s("th",{attrs:{scope:"col"}},[e._v("Room")]),s("th",{attrs:{scope:"col"}},[e._v("Program")])])])}],c=s("xmWZ"),n=s("3Aqn"),o=s("qpph"),u=s("0yhX"),i=s("EdlT"),l=s("mrSG"),d=s("YKMj"),_=s("k9fL"),h=function(e){function t(){return Object(c["a"])(this,t),Object(u["a"])(this,Object(i["a"])(t).apply(this,arguments))}return Object(o["a"])(t,[{key:"course",get:function(){return this.$store.getters["GET_COURSE"]}}]),Object(n["a"])(t,e),t}(d["c"]);h=l["a"]([Object(d["a"])({components:{appCourseHeader:_["a"]}})],h);var p=h,f=p,v=(s("VHrm"),s("KHd+")),b=Object(v["a"])(f,r,a,!1,null,"71c578c2",null);t["default"]=b.exports},"1UOA":function(e,t,s){"use strict";var r=s("FGdC"),a=s.n(r);a.a},A8S8:function(e,t,s){},FGdC:function(e,t,s){},VHrm:function(e,t,s){"use strict";var r=s("A8S8"),a=s.n(r);a.a},k9fL:function(e,t,s){"use strict";var r,a=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"container-fluid"},[""===e.course.id?s("div",{staticClass:"jumbotron"},[s("h1",[e._v(e._s(e.header))])]):e._e(),""!==e.course.id?s("div",{staticClass:"alert alert-success",class:{"alert-danger":"student"===e.userRole?0===e.course.lecturers.length:0===e.course.students.length},attrs:{role:"alert"}},[s("h4",{staticClass:"alert-heading"},[e._v(" "+e._s(e.course.code)+" "+e._s(e.course.title)+" ")]),"student"===e.userRole?s("p",[e._v("\n            There "+e._s(1===e.course.lecturers.length?"is":"are")+" "+e._s(e.course.lecturers.length)+"\n            lecturer"+e._s(1===e.course.lecturers.length?"":"s")+" for this course")]):e._e(),"lecturer"===e.userRole?s("p",[e._v(e._s(e.course.students.length)+" students are registered this course")]):e._e(),s("hr"),s("p",{staticClass:"mb-0"},[e._v(" This is an "+e._s(e.course.semester)+" semester course ")])]):e._e()])},c=[],n=s("xmWZ"),o=s("3Aqn"),u=s("qpph"),i=s("0yhX"),l=s("EdlT"),d=s("mrSG"),_=s("YKMj"),h=s("37wR"),p=function(e){function t(){return Object(n["a"])(this,t),Object(i["a"])(this,Object(l["a"])(t).apply(this,arguments))}return Object(u["a"])(t,[{key:"userRole",get:function(){return this.$store.getters["login/USER_ROLE"]}}]),Object(o["a"])(t,e),t}(_["c"]);d["a"]([Object(_["b"])({default:"COURSE HEADER"}),d["b"]("design:type",String)],p.prototype,"header",void 0),d["a"]([Object(_["b"])({default:function(){return new h["a"]}}),d["b"]("design:type","function"===typeof(r="undefined"!==typeof Object&&Object)&&r||Object)],p.prototype,"course",void 0),p=d["a"]([_["a"]],p);var f=p,v=f,b=(s("1UOA"),s("KHd+")),m=Object(b["a"])(v,a,c,!1,null,"72c80539",null);t["a"]=m.exports}}]);
//# sourceMappingURL=0.f53c6ddd.js.map