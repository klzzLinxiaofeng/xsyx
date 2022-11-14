<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/views/embedded/common.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/res/css/extra/add.css"
          rel="stylesheet">
    <script
            src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
    <title>${sca:getDefaultSchoolName()}</title>
    <style type="text/css">
        .tab-buttons {
            display: none;
        }
    </style>

</head>
<body>
<div class="container-fluid">
    <div class="row-fluid ">
        <div class="span12">
            <ul class="breadcrumb">
                <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>成绩录入</li>
            </ul>
        </div>
    </div>
    <div class="row-fluid ">
        <div class="span12">
            <div class="content-widgets white" style="margin-bottom: 0;">
                <div class="widget-head">
                    <h3>成绩录入</h3>

					<div class="student_right" style="margin-top: -30px;z-index: 100">
						<span>家长端小程序是否展示成绩排名</span>
						<a class="auto_open" id="btn_switch" onclick="changeInterrupteur()"></a>
					</div>
                </div>

            </div>
            <div class="stepy-widget">
                <div class="widget-head clearfix" style="background-color: #456A8C">
                    <div id="stepy_tabby">
                        <ul id="stepy_form-titles" class="stepy-titles">
                        </ul>
                    </div>


                    <button href="javascript:void(0)" class="btn btn-warning finish"
                            onclick="saveOrUpdate();"
                            style="position: absolute; right: 25px; top: 11px;">保存
                    </button>
                </div>
                <div class="widget-container gray " style="padding: 0">
                    <div class="form-container">
                        <div id="tab">

                            <fieldset title="选择条件">
                                <legend style="display: none;">填写录入目标</legend>
                                <div class="control-group">
                                    <jsp:include page="./option.jsp"></jsp:include>
                                </div>
                            </fieldset>

                            <fieldset title="成绩录入">
                                <legend style="display: none;">录入成绩</legend>
                                <div class="control-group">

                                    <jsp:include page="./addOrUpdate.jsp"></jsp:include>
                                </div>
                            </fieldset>

                            <button href="javascript:void(0)" class="btn btn-warning finish"
                                    style="display: none;">保存
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var checker;

    var val = {};
    var checkerSelect;
    var checkerAdd;
    var initExamNameNum = 0;
    var initSubjectNum = 0;
    var initExamTypeNum = 0;
    var examCodeFlag = '${StudentScoreCondition.examCodeFlag}';
    var subjectCodeFlag = '${StudentScoreCondition.subjectCodeFlag}';
    var examTypeFlag = '${StudentScoreCondition.examTypeFlag}';
    var canSave = '${StudentScoreCondition.canSave}';
    var flg = 0;
    $(function () {

        $('#tab').stepy({
            backLabel: "前往选择条件页面",
            nextLabel: "前往成绩录入页面",
            block: false,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby'
        });

        var defOption = {
            "type": "stu",
            "yearSelectId": "schoolYear", //学年select标签ID 默认为xn
            "gradeSelectId": "gradeId", //年级select标签ID 默认为nj
            "teamSelectId": "teamId",  //班级select标签ID 默认为bj
            "stuSelectId": "studentId",  //学生select标签ID 默认为stu
            "isEchoSelected": true,
            "yearSelectedVal": "${StudentScoreCondition.schoolYear}", //要回显的学年唯一标识
            "gradeSelectedVal": "${StudentScoreCondition.gradeId}", //要回显的年级唯一标识
            "teamSelectedVal": "${StudentScoreCondition.teamId}", //要回显的班级唯一标识
            "stuSelectedVal": "${StudentScoreCondition.studentId}" //要回显的学生唯一标识  $("#teamId").val() $("#schoolYear").val()
            /* "stuCallback" : function($this){
                alert($("#teamId").val());
                examType();
            } */
        };

        if (${StudentScoreCondition.schoolYear == undefined}) {

            defOption = {
                "type": "stu",
                "yearSelectId": "schoolYear", //学年select标签ID 默认为xn
                "gradeSelectId": "gradeId", //年级select标签ID 默认为nj
                "teamSelectId": "teamId",  //班级select标签ID 默认为bj
                "stuSelectId": "studentId"  //学生select标签ID 默认为stu
                /* "stuCallback" : function($this){
                    alert($("#teamId").val());

                } */
            };
        }

        $.initCascadeSelector(defOption);

        //考试类型
        /* var examType='
        ${StudentScoreCondition.examType}';
	        if(
        ${StudentScoreCondition.examType == undefined}){
	        	examType="";
	        } 
			$.jcGcSelector("#examType", {tc : "JY-KSXS"}, examType, function() {
				$("#examType").chosen();
			}); */

        onChangeSchoolYear();
        examType();
        checker = initValidatorAdd();


        var url = "${pageContext.request.contextPath}/teach/student/getAppletsInterrupteur?name=STU_SCORE_ORDER";
        $.get(url, function (data) {
            if (data == "true") {
                $(".student_right a").removeClass("auto_close").addClass("auto_open");
                $(".student_right span").html("家长端小程序是否展示成绩排名");
                flg = 1;
            } else {
                $(".student_right a").removeClass("auto_open").addClass("auto_close");
                $(".student_right span").html("家长端小程序是否展示成绩排名");
                flg = 0;
            }
        });

    });

    function onChangeTeam() {

        examType();
    }

    function onChangeSchoolYear() {
        var xn = $("#schoolYear").val();
        if (xn == null || xn == "") {
            xn = '${sessionScope[sca:currentUserKey()].schoolYear}';
        }

        $.getSchoolTerm({
            "schoolYear": xn
        }, function (data, status) {
            var $xq = $("#termCode");

            $xq.html("");
            var termCurrent = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
            $.each(data, function (index, value) {

                if (termCurrent == value.code) {
                    $xq.append("<option  selected='selected' value='" + value.code + "'>" + value.name + "</option>");
                } else {
                    $xq.append("<option value='" + value.code + "'>" + value.name + "</option>");
                }
            });
        });

    }

    function changeInterrupteur() {
        var boo = false;
        console.log(boo)
        flg = flg == 1 ? 0 : 1
        if (flg == 1) {
            boo = true;
        }


        var url = "${pageContext.request.contextPath}/teach/student/modifyAppletsInterrupteur?name=STU_SCORE_ORDER";
        $.post(url, {"boo": boo}, function (data) {
            if (data == "true") {
                $(".student_right a").removeClass("auto_close").addClass("auto_open");
                $(".student_right span").html("家长端小程序是否展示成绩排名");
                flg = 1;
            } else {
                $(".student_right a").removeClass("auto_open").addClass("auto_close");
                $(".student_right span").html("家长端小程序是否展示成绩排名");
                flg = 0;
            }
        });
    }

    function getStudentName(studentId) {
        $.TeamStudentSelector({
            "selector": "studentId",
            "teamId": "${StudentScoreCondition.teamId}",
            "selectedVal": studentId,
            "afterHandler": function (data) {
                // 				alert(data.code+":"+data.name);
            }

        });
    }

    function search() {


        $('#examCodeFlag').val(1);
        $('#subjectCodeFlag').val(1);
        $('#canSave').val(1);
        $('#examTypeFlag').val(1);

        checkerSelect = initValidatorSelect();
        if (checkerSelect.form()) {
            with (document.getElementById("selectForm")) {
                method = "post";
                action = "${ctp}/teach/studentScore/index";
                submit();
            }
        }


    }

    function initValidatorSelect() {
        return $("#selectForm").validate({
            errorClass: "myerror",
            rules: {
                "schoolYear": {
                    required: true
                },
                "termCode": {
                    required: true
                },
                "gradeId": {
                    required: true
                },
                "teamId": {
                    required: true
                },
                "examType": {
                    required: true
                },
                "examName": {
                    required: true
                },
                "subjectCode": {
                    required: true
                }
            },
            messages: {}
        });
    }

    function initValidatorAdd() {
        var deoption = "{";
        var result = "";
        var num = 0;
        $('input[class="spanScore"]').each(function (i, n) {
            if (i == 0) {

            } else {
                deoption = deoption + ",";
            }
            result = $(this).attr("name");
            var option = "";
            option = "\"" + result + "\"" + ":{\"required\" : false,\"number\":true,\"min\":0,\"max\":10000}";//number:true min:10
            deoption = deoption + option;

        });

        deoption = deoption + "}";


        var deoption3 = JSON.parse(deoption);


        return $("#studentscore_form").validate({
            errorClass: "myerror",
            rules: deoption3,
            messages: {}
        });


    }

    //保存或更新修改
    function saveOrUpdate() {
        if ($('#canSave').val() != 1) {
            $.error("请先输入成绩");
            return;
        }
        var loader = new loadLayer();
        if (checker.form()) {
            var $requestData = formData2JSONObj("#studentscore_form");
            var url = "${ctp}/teach/studentScore/creator";
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
                        $.error("保存失败");
                    }
                } else {
                    $.error("服务器异常");
                }
                loader.close();
            });
        }


    }


    function examType() {
        var examTypeArray = {};
        if (${StudentScoreCondition.examType == undefined}) {
            examTypeArray = {
                'schoolYear': $('#schoolYear').val(),
                'termCode': $('#termCode').val(),
                'gradeId': $('#gradeId').val(),
                'teamId': $('#teamId').val()

            }
        }/*else if(initExamTypeNum == 0&&${StudentScoreCondition.examType != undefined}){
					examTypeArray={
							'schoolYear':'${StudentScoreCondition.schoolYear}',
							'termCode':'${StudentScoreCondition.termCode}',
							'gradeId':'${StudentScoreCondition.gradeId}',
							'teamId':'${StudentScoreCondition.teamId}',
							'examType':'${StudentScoreCondition.examType}'
					
					}
				}*/ else {
            examTypeArray = {
                'schoolYear': $('#schoolYear').val(),
                'termCode': $('#termCode').val(),
                'gradeId': $('#gradeId').val(),
                'teamId': $('#teamId').val(),
                'examType': $('#examType').val()

            }
        }
        console.log(${StudentScoreCondition.examType});
        console.log(initExamTypeNum);
        $("#examType").empty();
        var examType = '${StudentScoreCondition.examType}';
        $.ajax({
            type: "post",
            url: "${ctp}/teach/scoreSelect/examType",
            data: examTypeArray,
            success: function (data) {
                var map = eval("(" + data + ")");

                $.each(map, function (key, values) {
                        if ((examTypeFlag != undefined) && (examTypeFlag == 1) && ${StudentScoreCondition.examType != undefined} && (examType == values)) {
                            $("<option value=" + values + " selected='selected'>" + key + "</option>").appendTo("#examType");
                            examTypeFlag = 0;
                        } else {
                            $("<option value=" + values + " >" + key + "</option>").appendTo("#examType");
                        }
                    }
                );
            }
        });

        initExamTypeNum = Number(initExamTypeNum) + 1;
        examCode();
    }

    function examCode() {
        var depExamCode = {};
        if (${StudentScoreCondition.examName == undefined}) {
            depExamCode = {
                'schoolYear': $('#schoolYear').val(),
                'termCode': $('#termCode').val(),
                'gradeId': $('#gradeId').val(),
                'teamId': $('#teamId').val(),
                'examType': $('#examType').val()
                //'examName':$('#examName').val()
            }
        } else if (initExamNameNum == 0 &&${StudentScoreCondition.examName != undefined}) {
            depExamCode = {
                'schoolYear': '${StudentScoreCondition.schoolYear}',
                'termCode': '${StudentScoreCondition.termCode}',
                'gradeId': '${StudentScoreCondition.gradeId}',
                'teamId': '${StudentScoreCondition.teamId}',
                'examType': '${StudentScoreCondition.examType}'/* ,
							'examName':'${StudentScoreCondition.examName}' */
            }
        } else {
            depExamCode = {
                'schoolYear': $('#schoolYear').val(),
                'termCode': $('#termCode').val(),
                'gradeId': $('#gradeId').val(),
                'teamId': $('#teamId').val(),
                'examType': $('#examType').val()
                //'examName':$('#examName').val()
            }
        }
        $("#examName").empty();
        var examName = '${StudentScoreCondition.examName}';

        $.ajax({
            type: "post",
            url: "${ctp}/teach/scoreSelect/termCode",
            data: depExamCode,
            success: function (data) {
                var map = eval("(" + data + ")");

                $.each(map, function (key, values) {

                    if ((examCodeFlag != undefined) && (examCodeFlag == 1) && ${StudentScoreCondition.examName != undefined} && (examName == values)) {
                        $("<option value=" + values + " selected='selected'>" + key + "</option>").appendTo("#examName");
                        examCodeFlag = 0;
                    } else {
                        $("<option value=" + values + " >" + key + "</option>").appendTo("#examName");
                    }
                });
            }
        });
        initExamNameNum = Number(initExamNameNum) + 1;
        subjectName();
    }

    function subjectName() {

        var depsubjectName = {};
        if (${StudentScoreCondition.subjectCode == undefined}) {
            depsubjectName = {
                'schoolYear': $('#schoolYear').val(),
                'termCode': $('#termCode').val(),
                'gradeId': $('#gradeId').val(),
                'teamId': $('#teamId').val(),
                'examType': $('#examType').val(),
                'examName': $('#examName').val()

            }
        } else if (initSubjectNum == 0 &&${StudentScoreCondition.subjectCode != undefined}) {
            depsubjectName = {
                'schoolYear': '${StudentScoreCondition.schoolYear}',
                'termCode': '${StudentScoreCondition.termCode}',
                'gradeId': '${StudentScoreCondition.gradeId}',
                'teamId': '${StudentScoreCondition.teamId}',
                'examType': '${StudentScoreCondition.examType}',
                'examName': '${StudentScoreCondition.examName}'/* ,
							'subjectCode':'${StudentScoreCondition.subjectCode}', */

            }
        } else {
            depsubjectName = {
                'schoolYear': $('#schoolYear').val(),
                'termCode': $('#termCode').val(),
                'gradeId': $('#gradeId').val(),
                'teamId': $('#teamId').val(),
                'examType': $('#examType').val(),
                'examName': $('#examName').val()

            }
        }

        $("#subjectCode").empty();
        var subjectCode = '${StudentScoreCondition.subjectCode}';
        $.ajax({
            type: "post",
            url: "${ctp}/teach/scoreSelect/subjectCode",
            data: depsubjectName,
            success: function (data) {
                var map = eval("(" + data + ")");

                $.each(map, function (key, values) {
                        if ((subjectCodeFlag != undefined) && (subjectCodeFlag == 1) && ${StudentScoreCondition.subjectCode != undefined} && (subjectCode == values)) {
                            $("<option value=" + values + " selected='selected'>" + key + "</option>").appendTo("#subjectCode");
                            subjectCodeFlag = 0;
                        } else {
                            $("<option value=" + values + " >" + key + "</option>").appendTo("#subjectCode");
                        }
                    }
                );
            }
        });

        initSubjectNum = Number(initSubjectNum) + 1;
    }
    //导出对话框
    function downLoadParentInfo() {
        $.initWinOnTopFromLeft("导出成绩模板", "${pageContext.request.contextPath}/teach/studentScore/downLoadScorePage", '600', '570');
    }
    //导出学科素养对话框
    function downLoadSubjectScore() {
        $.initWinOnTopFromLeft("导出学科素养成绩模板", "${pageContext.request.contextPath}/teach/studentScore/downLoadSubjectView", '600', '570');
    }

    //导入学科素养成绩对话框
    function uploadSubjectScore() {
        $.initWinOnTopFromLeft("学科素养成绩导入", "${pageContext.request.contextPath}/teach/studentScore/upLoadSubjectPage", '800', '700');
    }



    //导入对话框
    function uploadParentInfo() {
        $.initWinOnTopFromLeft("成绩导入", "${pageContext.request.contextPath}/teach/studentScore/upLoadScoreInfoPage", '800', '700');
    }


</script>
</body>
</html>
