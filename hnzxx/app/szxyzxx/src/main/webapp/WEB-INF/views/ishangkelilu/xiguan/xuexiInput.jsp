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
                            评价：
                        </label>
                        <div class="controls">
                            <%--<div id="text" name="text" class="span4" contenteditable="true" style="background-color:#fff;">${homeWoke.text}</div>
                        --%>    <textarea  type="text" id="text" name="text" class="span4" autocomplete="off">${list.pingyu}</textarea>
                        </div>
                    </div>


                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${list.id}"/>
                        <input type="hidden" id="studentName" name="studentName" value="${list.studentName}"/>
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
        var $id = $("#id").val();
        var pingyu=$("#text").val();
        var studentName=$("#studentName").val();
        var url = "${ctp}/study/habits/StudentUpdate?id="+$id+"&pingyu="+pingyu+"&studentName="+studentName;
        loader.show();
        $.get(url, function (data) {
            if ("success" === data) {
                $.success('操作成功');
                $.closeWindow();
            } else {
                $.error("操作失败");
            }
            loader.close();
        });
    }
</script>

</body>
</html>

