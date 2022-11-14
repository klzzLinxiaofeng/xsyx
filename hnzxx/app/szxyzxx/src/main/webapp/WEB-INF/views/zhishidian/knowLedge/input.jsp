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
                            <input value="${knowLedge.name}" type="text" id="name" name="name" class="span4" autocomplete="off">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            年级：
                        </label>
                        <div class="controls">
                            <select id="gradeId" name="gradeId" class="chzn-select"
                                    style="width:200px;">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学科：
                        </label>
                        <div class="controls">
                            <select id="subjectId" name="subjectId" class="chzn-select"
                                    style="width:200px;">
                                <c:if test="${knowLedge.subjectId!=null}">
                                    <c:if test="${knowLedge.subjectId==1}">
                                        <option value="1" selected>语文</option>
                                        <option value="2">数学</option>
                                        <option value="3">英语</option>
                                    </c:if>
                                    <c:if test="${knowLedge.subjectId==2}">
                                        <option value="1">语文</option>
                                        <option value="2" selected>数学</option>
                                        <option value="3">英语</option>
                                    </c:if>
                                    <c:if test="${knowLedge.subjectId==3}">
                                        <option value="1">语文</option>
                                        <option value="2">数学</option>
                                        <option value="3" selected>英语</option>
                                    </c:if>
                                </c:if>
                                <c:if test="${knowLedge.subjectId==null}">
                                    <option value="">请选择</option>
                                    <option value="1">语文</option>
                                    <option value="2">数学</option>
                                    <option value="3">英语</option>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${knowLedge.id}"/>
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

    $(function () {
        initSelect();
    })
    function initSelect() {
        //因查询年级不需学期，所以不需在学期填充后的回调中执行
        addOption('/huojiang/findByGrade', "gradeId", "id", "name",${knowLedge.gradeId})
    }
    function addOption(url, id, valProperty, namePropety,name, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                if(name==obj[valProperty]){
                    $("#" + id).append("<option selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }

            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }



    //保存或更新修改
    function saveOrUpdate() {
        var val={};
        var $id=$("#id").val();
        var url="";
        if($id===null || $id===""){
            url="${ctp}/knowLedge/create"
        }else{
            url="${ctp}/knowLedge/update"
            val.id=$id;
        }
        var loader = new loadLayer();
        var name=$("#name").val();
        var gradeId=$("#gradeId").val();
        var subjectId=$("#subjectId").val();
        if(name!=null && name!=""){
            val.name=name;
            if(gradeId!=null && gradeId!=""){
                val.gradeId=gradeId;
                if(subjectId!=null && subjectId!=""){
                    val.subjectId=subjectId;
                    loader.show();
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
                }else{
                    $.error("请选择科目")
                }

            }else{
                $.error("请选择年级")
            }

        }else{
            $.error("请输入名称")
        }

    }
    function getBack() {
        if(parent.core_iframe != null) {
            parent.core_iframe.window.location.reload();
        } else {
            parent.window.location.reload();
        }
        $.closeWindow();
    }
</script>
</body>
</html>
