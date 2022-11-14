<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>菜单创建</title>
    <%@ include file="/views/embedded/common.jsp"%>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }
        .myerror {
            color: red !important;
            width:22%;
            display:inline-block;
            padding-left:10px;
        }
        .res-bot {
            border-bottom: 1px solid #e6e6e6;
            border-top: 1px solid #e6e6e6;
            margin-top: -2px;
            width:75%;
        }
        .res-bot div {
            background: none repeat scroll 0 0 #f2f2f2;
            border-top: 1px solid #fff;
            height: 3px;
            overflow: hidden;
        }
        .res-foot {
            background: url("${ctp}/res/css/extra/images/icon.gif") no-repeat -173px -448px;
            cursor: pointer;
            height: 24px;
            line-height: 20px;
            margin: -1px auto 0;
            text-align: center;
            width: 149px;
        }
        .fa{margin-right:5px;}
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets light-gray">
            <div class="widget-container">
                <form class="form-horizontal" id="menu_form">
                    <div class="control-group">
                        <label class="control-label"><font style="color:red">*</font>菜单名称</label>
                        <div class="controls">
                            <input type="text" id="name" name="name"
                                   class="span13" placeholder="请输入菜单名称, 不能为空" value="<c:if test='${parentMenu!=0}'>${menu.name}</c:if>">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color:red">*</font>排序号</label>
                        <div class="controls">
                            <input type="text" id="paixu" name="paixu"
                                   class="span13" placeholder="请输入排序号，必须为数字且不为空" value="<c:if test='${parentMenu!=0}'>${menu.paixu}</c:if>">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">父菜单</label>
                        <div class="controls">
                            <c:if test="${parentMenu!=0}">
                                <c:if test="${menu.id!=null}">
                                    <input type="text" class="span13" disabled="disabled" placeholder="" value="${menu.parentMenuName}">
                                    <input type="hidden" id="parentMenu" name="parentMenu" value="${menu.parentMenu}" />
                                </c:if>
                                <c:if test="${menu.id==null}">
                                    <input type="text" class="span13" id="parentMenu" disabled="disabled" placeholder="">
                                </c:if>
                            </c:if>
                            <c:if test="${parentMenu==0}">
                                <c:if test="${menu.id!=null}">
                                    <input type="text" class="span13" disabled="disabled" placeholder="" value="${menu.name}">
                                    <input type="hidden" id="parentMenu" name="parentCode" value="${menu.id}" />
                                </c:if>
                                <c:if test="${menu.id==null}">
                                    <input type="text" class="span13" id="parentMenu" disabled="disabled" placeholder="">
                                </c:if>
                            </c:if>

                        </div>
                    </div>

                    <div class="form-actions" style="padding-left:0;background:#eee;text-align:center;">
                        <input type="hidden" id="type" name="type" value="${type}"/>
                        <input type="hidden" id="knowId" name="knowId" value="${knowId}">
                        <input type="hidden" id="ssid" name="ssid" value="${menu.id}">
                        <button class="btn btn-info" type="button" onclick="saveOrUpdate();">确定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    //保存或更新修改
    function saveOrUpdate() {
        var val={};
        val.knowId=$("#knowId").val();

        val.paixu=$("#paixu").val();
        val.name=$("#name").val();
        var isTianjia=${parentMenu};
        var url="";
        if(isTianjia!=0){
            val.leven=($("#type").val()-1);
            val.parentMenu=$("#parentMenu").val();
            var id=$("#ssid").val();
            val.id=id;
            url="/knowLedge/updateMenu";
        }else{
            val.parentMenu=$("#parentMenu").val();
            val.leven=$("#type").val();
            url="/knowLedge/createMenu";
        }
        var str=panduan(val);
        if(str=="success"){
            $.post(url, val, function(status) {
                if("success" === status) {
                    if(isTianjia!=0){
                        $.success("修改成功");
                        qingchu();
                    }else{
                        $.success("创建成功");
                    }
                    getBack();
                    parent.layer.closeAll();
                }

            });
        }else{
            $.error(str);
        }

    }
    function panduan(obj) {
        if(obj.knowId==null || obj.knowId==""){
            return "课本标识不能为空";
        }
        if(${parentMenu==0}) {
            if (obj.leven == null || obj.leven == "") {
                return "层级不能为空";
            }
        }
        if(obj.paixu==null || obj.paixu==""){
            return "排序号不能为空";
        }if(obj.name==null || obj.name==""){
           return "知识点名称不能为空";
        }
        return  "success";

    }
    function getBack() {
        if(parent.core_iframe != null) {
            parent.core_iframe.window.location.reload();
        } else {
            parent.window.location.reload();
        }
        $.closeWindow();
    }
    function qingchu() {
        $("#knowId").val("");

       $("#paixu").val("");
        $("#name").val("");
        $("#type").val("");
        $("#parentMenu").val("");
        $("#ssid").val("");
    }
</script>
</html>