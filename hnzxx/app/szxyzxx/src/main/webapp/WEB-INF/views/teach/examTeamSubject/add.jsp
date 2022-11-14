<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
	<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
	<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 75%;
        }

        .form-horizontal .control-group {
            width: 45%;
            display: inline-block;
            float: left;
        }

        .myerror {
            color: red !important;
            width: 100%;
            display: inline-block;
            padding-left: 10px;
        }
    </style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="examteamsubject_form" action="javascript:void(0);">

                    <div class="control-group">
                        <label class="control-label">
                            <span class="red">*</span>考试名称
                        </label>
                        <div class="controls">
                            <input type="text" id="examName" name="examName" class="span13"
                                   placeholder="考试名称，小于20个字" value="${examTeamSubjectVo.examName}" style="width:160px;">
                        </div>

                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            <span class="red">*</span>学年
                        </label>
                        <div class="controls">
                            <select id="schoolYear" disabled="disabled" name="schoolYear" class="chzn-select"
                                    style="width:160px;"></select>

                        </div>

                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            <span class="red">*</span>学期
                        </label>
                        <div class="controls">
                            <select id="term" name="term" class="chzn-select" style="width:160px;"></select>

                        </div>

                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            <span class="red">*</span>考试类型
                        </label>
                        <div class="controls">

                            <select id="examType" name="examType" class="chzn-select" style="width:160px;">
                                <option value="1">期中考试</option>
                                <option value="2">期末考试</option>
                                <option value="3">平时考试</option>
                                <option value="4">单元测试</option>
                            </select>

                            <%-- 	<input type="text" id="examType" name="examType" class="span13"
                                    placeholder="" value="${examTeamSubjectVo.examType}"  style="width:160px;">
                             --%>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            <span class="red">*</span>考试年级
                        </label>
                        <div class="controls">
                            <select id="gradeId" name="gradeId" class="chzn-select" style="width:160px;"></select>


                        </div>

                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            考试班级
                        </label>
                        <div class="controls">
                            <select id="teamIdCollection" name="teamIdCollection" class="chzn-select"
                                    style="width:160px;"></select>


                        </div>
                    </div>


                    <div class="control-group">
                        <label class="control-label">
                            <span class="red">*</span>考试科目
                        </label>
                        <div class="controls">
                            <select id="subjectCode" name="subjectCode" class="chzn-select"
                                    style="width:160px;"></select>
                            <%-- <input type="text" id="subjectCode" name="subjectCode" class="span13"
                                placeholder="" value="${examTeamSubjectVo.subjectCode}"  style="width:160px;"> --%>
                        </div>

                    </div>
					<div class="control-group">
						<label class="control-label"><font style="color: red">*</font>
							考试日期：
						</label>
						<div class="controls">
							<input type="text" id="preciseStartDate" name="preciseStartDate" class="span4" autocomplete="off"
								   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"
								   placeholder="xxxx-xx-xx"
								   value='<fmt:formatDate pattern="yyyy-MM-dd" value="${examTeamSubjectVo.preciseStartDate}"></fmt:formatDate>'>
						</div>
					</div>

                    <div class="control-group">
                        <label class="control-label">
                            <span class="red">*</span>题目数
                        </label>
                        <div class="controls">
                            <input type="text" id="examNumber" name="examNumber" class="span13"
                                   placeholder="请输入数字" value="${examTeamSubjectVo.examNumber}" style="width:160px;">
                            <%-- <input type="text" id="subjectCode" name="subjectCode" class="span13"
                                placeholder="" value="${examTeamSubjectVo.subjectCode}"  style="width:160px;"> --%>
                        </div>

                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${examTeamSubjectVo.id}"/>
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

        $.initCascadeSelector({
            "type": "team",
            "yearSelectId": "schoolYear",
            "gradeSelectId": "gradeId",
            "teamSelectId": "teamIdCollection",
            "gradeFirstOptTitle": "请选择",
            "teamFirstOptTitle": "全部"

        });
        onChangeSchoolYear();

       /* //考试类型，考试性质
        $.jcGcSelector("#examType", {tc: "NEW_JY-KSLX"}, "", function () {
            $("#examType").chosen();
        });
*/

        $.getPjSubject({}, function (data) {
            var $subjectCode = $("#subjectCode");
            $subjectCode.html("<option value=''>请选择</option>");
            $.each(data, function (index, value) {

                $subjectCode.append("<option value='" + value.code + "'>" + value.name + "</option>");
            });
        });

        checker = initValidator();
    });

    function onChangeSchoolYear() {
        var xn = $("#schoolYear").val();
        if (xn == null || xn == "") {
            xn = '${sessionScope[sca:currentUserKey()].schoolYear}';
        }

        $.getSchoolTerm({"schoolYear": xn}, function (data, status) {
            var $xq = $("#term");

            $xq.html("");
            $.each(data, function (index, value) {
                var termCurrent = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
                if (termCurrent == value.code) {
                    $xq.append("<option  selected='selected' value='" + value.code + "'>" + value.name + "</option>");
                } else {
                    $xq.append("<option value='" + value.code + "'>" + value.name + "</option>");
                }

            });
        });
    }

    function initValidator() {
        return $("#examteamsubject_form").validate({
            errorClass: "myerror",
            rules: {

                "schoolYear": {
                    required: true
                }, "term": {
                    required: true
                },
                "term": {
                    required: true
                },
                "gradeId": {
                    required: true
                },
                "teamIdCollection": {
                    required: false
                },
                "examName": {
                    required: true,
                    maxlength: 20
                },
                "examType": {
                    required: true
                },
                "subjectCode": {
                    required: true
                },
                "preciseStartDate": {
                    required: true
                },
                "preciseEndDate": {
                    required: true
                }
            },
            messages: {
                examName: "考试名称必填，且小于20个字"
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate() {
        if (checker.form()) {
            var $id = $("#id").val();

            var $requestData = formData2JSONObj("#examteamsubject_form");
            $requestData.teamIdCollection = $("#teamIdCollection").val();

            var url = "${ctp}/teach/examTeamSubject/add";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${ctp}/teach/examTeamSubject/" + $id;
            }
            var loader = new loadDialog();
            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    $.success('保存成功');
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        if (parent.core_iframe != null) {

                            parent.core_iframe.window.location.reload();
                        } else {

                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else {
                        $.error(data.info);
                    }
                } else {
                    $.error("服务器出问题了");
                }
                loader.close();
            });
        }
    }

</script>
</html>