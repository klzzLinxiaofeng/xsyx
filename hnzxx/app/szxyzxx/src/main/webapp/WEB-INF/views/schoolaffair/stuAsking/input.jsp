<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/uploadify.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 227px;
        }

        .myerror {
            color: red !important;
            width: 30%;
            display: inline-block;
            padding-left: 10px;
        }

        .control-group {
            width: 100%;
            margin-left: auto;
            margin-right: auto;
        }

        label {
            display: inline;
        }

        .form-horizontal .control-label {
            text-align: left;
            margin-left: 40px;
            width: 83px;
        }

        .form-horizontal .controls {
            margin-left: 10px !important;
        }
    </style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            审批编号：
                        </label>
                        <div class="controls">
                            <input type="text" id="number" name="number" class="span4" placeholder=""
                                   value="${publicClass.number}" style="width: 70%">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            班级：
                        </label>
                        <div class="controls">
                            <input type="text" id="teamName" name="teamName" class="span4" placeholder=""
                                   value="${publicClass.teamName}" style="width: 70%">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            姓名：
                        </label>
                        <div class="controls">
                            <input type="text" id="stuName" name="stuName" class="span4" placeholder=""
                                   data-id="${publicClass.stuName}" value="${publicClass.stuName}" style="width: 70%">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            提交时间：
                        </label>
                        <div class="controls">
                            <input type="text" id="createDate" name="createDate" class="span4"
                                   placeholder="xxxx-xx-xx" style="width: 70%"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd HH:ss" value="${publicClass.createDate}"></fmt:formatDate>'>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            请假事由：
                        </label>
                        <div class="controls">
                            <input type="text" id="remark" name="remark" class="span4" placeholder=""
                                   data-id="${publicClass.remark}" value="${publicClass.remark}" style="width: 70%">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            开始时间：
                        </label>
                        <div class="controls">
                            <input type="text" id="beginDate" name="beginDate" class="span4"
                                   placeholder="xxxx-xx-xx" style="width: 70%"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${publicClass.beginDate}"></fmt:formatDate>'>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            结束时间：
                        </label>
                        <div class="controls">
                            <input type="text" id="endDate" name="endDate" class="span4"
                                   placeholder="xxxx-xx-xx" style="width: 70%"
                                   value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${publicClass.endDate}"></fmt:formatDate>'>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            请假时长：
                        </label>
                        <div class="controls">
                            <input type="text" id="timeConsuming" name="timeConsuming" class="span4" placeholder=""
                                   data-id="${publicClass.timeConsuming}" value="${publicClass.timeConsuming} 小时" style="width: 70%">
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            班主任：
                        </label>
                        <div class="controls">
                            <input type="text" id="teacherName" name="teacherName" class="span4" placeholder=""
                                   data-id="${publicClass.teacherName}" value="${publicClass.teacherName}" style="width: 70%">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"><font style="color: red">*</font>
                            审批状态：
                        </label>
                        <div class="controls">
                            <c:if test="${publicClass.indiaStatus == 1}">审核中</c:if>
                            <c:if test="${publicClass.indiaStatus == 2}">已通过</c:if>
                            <c:if test="${publicClass.indiaStatus == 3}"><span style="color: red;">已驳回</span></c:if>
                        </div>
                    </div>

                    <c:if test="${publicClass.indiaStatus == 3}">
                        <div class="control-group">
                            <label class="control-label"><font style="color: red">*</font>
                                驳回理由：
                            </label>
                            <div class="controls">
                                <input type="text" id="rejected" name="rejected" class="span4" placeholder=""
                                       data-id="${publicClass.rejected}" value="${publicClass.rejected}" style="width: 70%">
                            </div>
                        </div>
                    </c:if>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">


    $(function () {

    });


</script>
</html>