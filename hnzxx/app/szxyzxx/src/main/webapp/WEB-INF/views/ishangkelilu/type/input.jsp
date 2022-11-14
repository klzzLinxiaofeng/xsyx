<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2022/1/7
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
    <script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_time_form" action="javascript:void(0);">


                    <div class="control-group">
                        <label class="control-label">
                            名称：
                        </label>
                        <div class="controls">
                            <%--<div id="text" name="text" class="span4" contenteditable="true" style="background-color:#fff;">${homeWoke.text}</div>
                        --%>    <input  type="text" id="text" name="text" class="span4" value="${studyType.name}" autocomplete="off"></input>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            类型：
                        </label>
                        <select id="type" name="type">

                        </select>
                    </div>
                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${studyType.id}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    //保存或更新修改
    function saveOrUpdate() {
        var loader = new loadLayer();
        var text = $("#text").val();
        var $id = $("#id").val();
        var type=$("#type").val();
        var val={};
        val.name=text;
        val.type=type;
        var  url ="";
        loader.show();
        if($id!=null && $id!=""){
            val.id=$id;
            url="/studey/type/update";
        }else{
            url="/studey/type/create";
        }
        $.post(url, val, function (data, status) {
            if (status=="success") {
                if ("success" === data) {
                    $.success('操作成功');
                    getBack();
                    parent.layer.closeAll();
                } else {
                    $.error("操作失败");
                }
            }
            loader.close();
        });

    }
    function getBack() {
        if(parent.core_iframe != null) {
            parent.core_iframe.window.location.reload();
        } else {
            parent.window.location.reload();
        }
        $.closeWindow();
    }
    $(function () {
        chushihua();
    })
    function chushihua() {

        var type='${studyType.type}';
        var typeobject=$("#type");
        typeobject.append("");
        for(var i=0;i<2;i++) {
            if (i == 0) {
                if (type == '0') {
                    typeobject.append("<option value='0' selected>加分项</option>");
                } else {
                    typeobject.append("<option value='0'>加分项</option>");
                }
            }
            if (i == 1) {
                if (type == '1') {
                    typeobject.append("<option value='1' selected>减分项</option>");
                } else {
                    typeobject.append("<option value='1'>减分项</option>");
                }
            }
        }
    }
</script>
</body>
</html>
