(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([[4],{"1UOA":function(e,t,s){"use strict";var r=s("FGdC"),c=s.n(r);c.a},FGdC:function(e,t,s){},"Sa+O":function(e,t,s){"use strict";s.r(t);var r=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"ViewCourse"},[s("app-course-header",{attrs:{course:e.course,userRole:e.userRole}}),s("div",{staticClass:"table-responsive-md"},[s("table",{staticClass:"table"},[e._m(0),s("tbody",e._l(e.course.lecturers,function(t,r){return s("tr",[s("th",{attrs:{scope:"row"}},[e._v(" "+e._s(r+1))]),s("td",[e._v(e._s(t.firstName)+" "+e._s(t.lastName))]),s("td",[s("a",{attrs:{href:"mailto:"+t.email}},[e._v(" "+e._s(t.email)+" ")])]),s("td",[e._v(" "+e._s(t.office))]),s("td",[e._v(" "+e._s(t.inOffice?"In Office":"Not Available"))])])}))])])],1)},c=[function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("thead",[s("tr",[s("th",{attrs:{scope:"col"}},[e._v("#")]),s("th",{attrs:{scope:"col"}},[e._v("Name")]),s("th",{attrs:{scope:"col"}},[e._v("email")]),s("th",{attrs:{scope:"col"}},[e._v("office")]),s("th",{attrs:{scope:"col"}},[e._v("location")])])])}],a=s("xmWZ"),n=s("3Aqn"),o=s("qpph"),u=s("0yhX"),i=s("EdlT"),l=s("mrSG"),d=s("YKMj"),_=s("k9fL"),f=function(e){function t(){return Object(a["a"])(this,t),Object(u["a"])(this,Object(i["a"])(t).apply(this,arguments))}return Object(o["a"])(t,[{key:"course",get:function(){return this.$store.getters["GET_COURSE"]}},{key:"userRole",get:function(){return this.$store.getters["login/USER_ROLE"]}}]),Object(n["a"])(t,e),t}(d["c"]);f=l["a"]([Object(d["a"])({components:{appCourseHeader:_["a"]}})],f);var h=f,p=h,v=(s("WqX3"),s("KHd+")),b=Object(v["a"])(p,r,c,!1,null,"02b86d94",null);t["default"]=b.exports},WqX3:function(e,t,s){"use strict";var r=s("olpt"),c=s.n(r);c.a},k9fL:function(e,t,s){"use strict";var r,c=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"container-fluid"},[""===e.course.id?s("div",{staticClass:"jumbotron"},[s("h1",[e._v(e._s(e.header))])]):e._e(),""!==e.course.id?s("div",{staticClass:"alert alert-success",class:{"alert-danger":"student"===e.userRole?0===e.course.lecturers.length:0===e.course.students.length},attrs:{role:"alert"}},[s("h4",{staticClass:"alert-heading"},[e._v(" "+e._s(e.course.code)+" "+e._s(e.course.title)+" ")]),"student"===e.userRole?s("p",[e._v("\n            There "+e._s(1===e.course.lecturers.length?"is":"are")+" "+e._s(e.course.lecturers.length)+"\n            lecturer"+e._s(1===e.course.lecturers.length?"":"s")+" for this course")]):e._e(),"lecturer"===e.userRole?s("p",[e._v(e._s(e.course.students.length)+" students are registered this course")]):e._e(),s("hr"),s("p",{staticClass:"mb-0"},[e._v(" This is an "+e._s(e.course.semester)+" semester course ")])]):e._e()])},a=[],n=s("xmWZ"),o=s("3Aqn"),u=s("qpph"),i=s("0yhX"),l=s("EdlT"),d=s("mrSG"),_=s("YKMj"),f=s("37wR"),h=function(e){function t(){return Object(n["a"])(this,t),Object(i["a"])(this,Object(l["a"])(t).apply(this,arguments))}return Object(u["a"])(t,[{key:"userRole",get:function(){return this.$store.getters["login/USER_ROLE"]}}]),Object(o["a"])(t,e),t}(_["c"]);d["a"]([Object(_["b"])({default:"COURSE HEADER"}),d["b"]("design:type",String)],h.prototype,"header",void 0),d["a"]([Object(_["b"])({default:function(){return new f["a"]}}),d["b"]("design:type","function"===typeof(r="undefined"!==typeof Object&&Object)&&r||Object)],h.prototype,"course",void 0),h=d["a"]([_["a"]],h);var p=h,v=p,b=(s("1UOA"),s("KHd+")),O=Object(b["a"])(v,c,a,!1,null,"72c80539",null);t["a"]=O.exports},olpt:function(e,t,s){}}]);
//# sourceMappingURL=4.ccbba21e.js.map