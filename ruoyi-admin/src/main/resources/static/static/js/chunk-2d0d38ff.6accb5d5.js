(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d0d38ff"],{"5cfa":function(e,t,r){"use strict";r.r(t);var a=function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"app-container"},[r("el-form",{directives:[{name:"show",rawName:"v-show",value:e.showSearch,expression:"showSearch"}],ref:"queryForm",attrs:{model:e.queryParams,size:"small",inline:!0}},[r("el-form-item",{attrs:{label:"部门名称",prop:"deptName"}},[r("el-input",{attrs:{placeholder:"请输入部门名称",clearable:""},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.handleQuery(t)}},model:{value:e.queryParams.deptName,callback:function(t){e.$set(e.queryParams,"deptName",t)},expression:"queryParams.deptName"}})],1),r("el-form-item",{attrs:{label:"状态",prop:"status"}},[r("el-select",{attrs:{placeholder:"部门状态",clearable:""},model:{value:e.queryParams.status,callback:function(t){e.$set(e.queryParams,"status",t)},expression:"queryParams.status"}},e._l(e.dict.type.sys_normal_disable,(function(e){return r("el-option",{key:e.value,attrs:{label:e.label,value:e.value}})})),1)],1),r("el-form-item",[r("el-button",{attrs:{type:"primary",icon:"el-icon-search",size:"mini"},on:{click:e.handleQuery}},[e._v("搜索")]),r("el-button",{attrs:{icon:"el-icon-refresh",size:"mini"},on:{click:e.resetQuery}},[e._v("重置")])],1)],1),r("el-row",{staticClass:"mb8",attrs:{gutter:10}},[r("el-col",{attrs:{span:1.5}},[r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["system:dept:add"],expression:"['system:dept:add']"}],attrs:{type:"primary",plain:"",icon:"el-icon-plus",size:"mini"},on:{click:e.handleAdd}},[e._v("新增")])],1),r("el-col",{attrs:{span:1.5}},[r("el-button",{attrs:{type:"info",plain:"",icon:"el-icon-sort",size:"mini"},on:{click:e.toggleExpandAll}},[e._v("展开/折叠")])],1),r("right-toolbar",{attrs:{showSearch:e.showSearch},on:{"update:showSearch":function(t){e.showSearch=t},"update:show-search":function(t){e.showSearch=t},queryTable:e.getList}})],1),e.refreshTable?r("el-table",{directives:[{name:"loading",rawName:"v-loading",value:e.loading,expression:"loading"}],attrs:{data:e.deptList,"row-key":"deptId","default-expand-all":e.isExpandAll,"tree-props":{children:"children",hasChildren:"hasChildren"}}},[r("el-table-column",{attrs:{prop:"deptName",label:"部门名称",width:"260"}}),r("el-table-column",{attrs:{prop:"orderNum",label:"排序",width:"200"}}),r("el-table-column",{attrs:{prop:"status",label:"状态",width:"100"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("dict-tag",{attrs:{options:e.dict.type.sys_normal_disable,value:t.row.status}})]}}],null,!1,2802338569)}),r("el-table-column",{attrs:{label:"创建时间",align:"center",prop:"createTime",width:"200"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("span",[e._v(e._s(e.parseTime(t.row.createTime)))])]}}],null,!1,3078210614)}),r("el-table-column",{attrs:{label:"操作",align:"center","class-name":"small-padding fixed-width"},scopedSlots:e._u([{key:"default",fn:function(t){return[r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["system:dept:edit"],expression:"['system:dept:edit']"}],attrs:{size:"mini",type:"text",icon:"el-icon-edit"},on:{click:function(r){return e.handleUpdate(t.row)}}},[e._v("修改")]),r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["system:dept:add"],expression:"['system:dept:add']"}],attrs:{size:"mini",type:"text",icon:"el-icon-plus"},on:{click:function(r){return e.handleAdd(t.row)}}},[e._v("新增")]),0!=t.row.parentId?r("el-button",{directives:[{name:"hasPermi",rawName:"v-hasPermi",value:["system:dept:remove"],expression:"['system:dept:remove']"}],attrs:{size:"mini",type:"text",icon:"el-icon-delete"},on:{click:function(r){return e.handleDelete(t.row)}}},[e._v("删除")]):e._e()]}}],null,!1,2965762180)})],1):e._e(),r("el-dialog",{attrs:{title:e.title,visible:e.open,width:"600px","append-to-body":""},on:{"update:visible":function(t){e.open=t}}},[r("el-form",{ref:"form",attrs:{model:e.form,rules:e.rules,"label-width":"80px"}},[r("el-row",[0!==e.form.parentId?r("el-col",{attrs:{span:24}},[r("el-form-item",{attrs:{label:"上级部门",prop:"parentId"}},[r("treeselect",{attrs:{options:e.deptOptions,normalizer:e.normalizer,placeholder:"选择上级部门"},model:{value:e.form.parentId,callback:function(t){e.$set(e.form,"parentId",t)},expression:"form.parentId"}})],1)],1):e._e()],1),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"部门名称",prop:"deptName"}},[r("el-input",{attrs:{placeholder:"请输入部门名称"},model:{value:e.form.deptName,callback:function(t){e.$set(e.form,"deptName",t)},expression:"form.deptName"}})],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"显示排序",prop:"orderNum"}},[r("el-input-number",{attrs:{"controls-position":"right",min:0},model:{value:e.form.orderNum,callback:function(t){e.$set(e.form,"orderNum",t)},expression:"form.orderNum"}})],1)],1)],1),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"负责人",prop:"leader"}},[r("el-input",{attrs:{placeholder:"请输入负责人",maxlength:"20"},model:{value:e.form.leader,callback:function(t){e.$set(e.form,"leader",t)},expression:"form.leader"}})],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"联系电话",prop:"phone"}},[r("el-input",{attrs:{placeholder:"请输入联系电话",maxlength:"11"},model:{value:e.form.phone,callback:function(t){e.$set(e.form,"phone",t)},expression:"form.phone"}})],1)],1)],1),r("el-row",[r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"邮箱",prop:"email"}},[r("el-input",{attrs:{placeholder:"请输入邮箱",maxlength:"50"},model:{value:e.form.email,callback:function(t){e.$set(e.form,"email",t)},expression:"form.email"}})],1)],1),r("el-col",{attrs:{span:12}},[r("el-form-item",{attrs:{label:"部门状态"}},[r("el-radio-group",{model:{value:e.form.status,callback:function(t){e.$set(e.form,"status",t)},expression:"form.status"}},e._l(e.dict.type.sys_normal_disable,(function(t){return r("el-radio",{key:t.value,attrs:{label:t.value}},[e._v(e._s(t.label))])})),1)],1)],1)],1)],1),r("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[r("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("确 定")]),r("el-button",{on:{click:e.cancel}},[e._v("取 消")])],1)],1)],1)},l=[],n=(r("14d9"),r("b775"));function s(e){return Object(n["a"])({url:"/system/dept/list",method:"get",params:e})}function o(e){return Object(n["a"])({url:"/system/dept/list/exclude/"+e,method:"get"})}function i(e){return Object(n["a"])({url:"/system/dept/"+e,method:"get"})}function d(e){return Object(n["a"])({url:"/system/dept",method:"post",data:e})}function m(e){return Object(n["a"])({url:"/system/dept",method:"put",data:e})}function p(e){return Object(n["a"])({url:"/system/dept/"+e,method:"delete"})}var c=r("ca17"),u=r.n(c),h=(r("542c"),{name:"Dept",dicts:["sys_normal_disable"],components:{Treeselect:u.a},data:function(){return{loading:!0,showSearch:!0,deptList:[],deptOptions:[],title:"",open:!1,isExpandAll:!0,refreshTable:!0,queryParams:{deptName:void 0,status:void 0},form:{},rules:{parentId:[{required:!0,message:"上级部门不能为空",trigger:"blur"}],deptName:[{required:!0,message:"部门名称不能为空",trigger:"blur"}],orderNum:[{required:!0,message:"显示排序不能为空",trigger:"blur"}],email:[{type:"email",message:"请输入正确的邮箱地址",trigger:["blur","change"]}],phone:[{pattern:/^1[3|4|5|6|7|8|9][0-9]\d{8}$/,message:"请输入正确的手机号码",trigger:"blur"}]}}},created:function(){this.getList()},methods:{getList:function(){var e=this;this.loading=!0,s(this.queryParams).then((function(t){e.deptList=e.handleTree(t.data,"deptId"),e.loading=!1}))},normalizer:function(e){return e.children&&!e.children.length&&delete e.children,{id:e.deptId,label:e.deptName,children:e.children}},cancel:function(){this.open=!1,this.reset()},reset:function(){this.form={deptId:void 0,parentId:void 0,deptName:void 0,orderNum:void 0,leader:void 0,phone:void 0,email:void 0,status:"0"},this.resetForm("form")},handleQuery:function(){this.getList()},resetQuery:function(){this.resetForm("queryForm"),this.handleQuery()},handleAdd:function(e){var t=this;this.reset(),void 0!=e&&(this.form.parentId=e.deptId),this.open=!0,this.title="添加部门",s().then((function(e){t.deptOptions=t.handleTree(e.data,"deptId")}))},toggleExpandAll:function(){var e=this;this.refreshTable=!1,this.isExpandAll=!this.isExpandAll,this.$nextTick((function(){e.refreshTable=!0}))},handleUpdate:function(e){var t=this;this.reset(),i(e.deptId).then((function(r){t.form=r.data,t.open=!0,t.title="修改部门",o(e.deptId).then((function(e){if(t.deptOptions=t.handleTree(e.data,"deptId"),0==t.deptOptions.length){var r={deptId:t.form.parentId,deptName:t.form.parentName,children:[]};t.deptOptions.push(r)}}))}))},submitForm:function(){var e=this;this.$refs["form"].validate((function(t){t&&(void 0!=e.form.deptId?m(e.form).then((function(t){e.$modal.msgSuccess("修改成功"),e.open=!1,e.getList()})):d(e.form).then((function(t){e.$modal.msgSuccess("新增成功"),e.open=!1,e.getList()})))}))},handleDelete:function(e){var t=this;this.$modal.confirm('是否确认删除名称为"'+e.deptName+'"的数据项？').then((function(){return p(e.deptId)})).then((function(){t.getList(),t.$modal.msgSuccess("删除成功")})).catch((function(){}))}}}),f=h,b=r("2877"),v=Object(b["a"])(f,a,l,!1,null,null,null);t["default"]=v.exports}}]);