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
                            学生姓名：
                        </label>
                        <div class="controls">
                            <input disabled style="display: none" value="${list.studentId}" type="text" id="studentId" name="name" class="span4" autocomplete="off">
                            <input disabled value="${list.stuName}" type="text" id="name" name="name" class="span4" autocomplete="off">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            体测数据：
                        </label>
                        <br/>
                        <div id="divss">
                            <c:forEach items="${list.indicatorsStudents}"  var="item" varStatus="i" >
                                <lable>${item.indicatorsName}:</lable>
                                <input disabled name="indicatorsId" value="${item.indicatorsId}" type="text" id="iss+${item.indicatorsId}" style="display: none"/>
                                <input disabled name="score" value="${item.score}" type="text" id="score+${item.score}"/><label>${item.danwei}</label>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            体测报告：
                        </label>
                        <div class="controls">
                            <input disabled value="${list.cervixReport}" type="text" id="cervixReport" name="cervixReport" class="span4" autocomplete="off">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            请假记录：
                        </label>
                        <div class="controls">
                            <span>请假${list.jiaDay}次,共${list.tianshu}天</span>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            医务室记录：
                        </label>
                        <div class="controls">
                            <input disabled value="${list.yiWu}" type="text" id="yiwu" name="yiwu" class="span4" autocomplete="off">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            体质健康报告：
                        </label>
                        <div class="controls">
                            <input disabled value="${list.healthReport}" type="text" id="healthReport" name="healthReport" class="span4" autocomplete="off">
                        </div>
                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${list.baogaoId}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    var checker;
    $(function () {
        /*        checker = initValidator();*/
    });
    //保存或更新修改
    function saveOrUpdate() {
            loader.close();
    }

</script>
</html>