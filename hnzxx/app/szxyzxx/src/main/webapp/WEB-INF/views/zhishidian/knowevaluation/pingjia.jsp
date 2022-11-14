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
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_time_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label">
                            评分：
                        </label>
                        <div class="controls">
                            <input value="${knowEvaluation.pingfen}" type="text" id="pingfen" name="pingfen" class="span4" autocomplete="off">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            评语：
                        </label>
                        <div class="controls">
                            <input value="${knowEvaluation.pingyu}" type="text" id="pingyu" name="pingyu" class="span4" autocomplete="off">
                        </div>
                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${knowEvaluation.id}"/>
                        <input type="hidden" id="knowMenuId" name="knowMenuId" value="${knowMenuId}"/>
                        <input type="hidden" id="studentId" name="studentId" value="${studentId}"/>
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
        var val={};
        var $id=$("#id").val();
        var url="/KnowEvaluation/createOrUpdate";
        if($id!=null || $id!=""){
            val.id=$id;
        }
        var knowMenuId=$("#knowMenuId").val();
        var studentId=$("#studentId").val();
        val.knowMenuId=knowMenuId;
        val.studentId=studentId;
        var pingfen=$("#pingfen").val();
        var pingyu=$("#pingyu").val();
        if(pingfen!=null && pingfen!=""){
            val.pingfen=pingfen;
            if(pingyu!=null && pingyu!=""){
                val.pingyu=pingyu;
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
                    });
            }else{
                $.error("请选择评语")
            }
        }else{
            $.error("请输入评分")
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
