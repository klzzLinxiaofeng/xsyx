<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp"%>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 75%;
        }

        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }
        .chzn-container .chzn-results{
            max-height:120px;
        }
    </style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid ">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <%--action="${pageContext.request.contextPath}/teach/studentScore/downLoadScoreInfo"--%>
                <form  method="get"  id="downLoadForm" class="form-horizontal left-align form-well" novalidate="novalidate">
                    <div class="control-group">
                        <label class="control-label">
                            学年：
                        </label>
                        <div class="controls">
                            <select id="xn" name="xn" class="span4"
                                    style="width:200px;" value="${literacyVo.xn}">

                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            学期：
                        </label>
                        <div class="controls">
                            <select id="xq" name="xq" class="span4"
                                    style="width:200px;" value="${literacyVo.xq}">

                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            年级：
                        </label>
                        <div class="controls">
                            <select id="nj" name="gradeId" class="span4"
                                    style="width:200px;" value="${literacyVo.gradeId}">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">
                            班级：
                        </label>
                        <div class="controls">
                            <select id="bj" name="teamId" class="span4"
                                    style="width:200px;" value="${literacyVo.teamId}">
                            </select>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label">
                            科目：
                        </label>
                        <div class="controls">
                            <select id="km" name="subjectId" class="span4"
                                    style="width:200px;" value="${literacyVo.subjectId}">
                            </select>
                        </div>
                    </div>
                    <input type="hidden" name = "canSave" id="canSave" value="${StudentScoreCondition.canSave}" >
                    <input type="hidden" name = "examCodeFlag" id="examCodeFlag" value="${StudentScoreCondition.examCodeFlag}" >
                    <input type="hidden" name = "subjectCodeFlag" id="subjectCodeFlag" value="${StudentScoreCondition.subjectCodeFlag}">
                    <input type="hidden" name = "examTypeFlag" id="examTypeFlag" value="${StudentScoreCondition.examType}">

                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" type="submit"
                                onclick="downLoadModeler();">导出模板数据</button>
                    </div>
                </form>

            </div>
        </div>
    </div>
</div>
</body>
<script>
    $(function () {
        initSelect();
    });

    function initSelect() {

        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name");
                $("#bj").html('<option value="">请选择</option>');
                $("#km").html('<option value="">请选择</option>');
                $("#nj").html('<option value="">请选择</option>');
            }
        })
    }


    //绑定下拉框改变事件
    $("#xn").change(function(){
        $("#nj").html('<option value="">请选择</option>');
        $("#xq").html('<option value="">请选择</option>');
        $("#bj").html('<option value="">请选择</option>');
        $("#km").html('<option value="">请选择</option>');
        //添加学期
        addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name")
        //添加年级
        addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
    })
    $("#xq").change(function(){
        $("#km").html('<option value="">请选择</option>');
        //添加科目
        addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "sublectId", "sublectName")
    })

    $("#nj").change(function(){
        $("#bj").html('<option value="">请选择</option>');

        $("#km").html('<option value="">请选择</option>');
        addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
        //添加科目
        addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")

    })
    $("#bj").change(function () {
        $("#km").html('<option value="">请选择</option>');
        //添加科目
        addOption("/literacy/findExamSubject?nj="+$("#nj").val(), "km", "subjectId", "subjectName")
    })

    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                $("#" + id).append("<option   value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }

    function initValidatorSelect() {
        return $("#downLoadForm").validate({
            errorClass : "myerror",
            rules : {
                "xn" : {
                    required : true
                },
                "xq" : {
                    required : true
                },
                "nj" : {
                    required : true
                },
                "km" : {
                    required : true
                }
            },
            messages : {

            }
        });
    }

    function downLoadModeler() {
        checkerSelect = initValidatorSelect();
        if (checkerSelect.form()) {
            var url = "/teach/studentScore/downLoadSubjectScore?xn="+$("#xn").val()+"&xq="+$("#xq").val()+"&gradeId="+$("#nj").val()+"&subjectId="+$("#km").val();
            var team=$("#bj").val();
            if(team!=null && team!=""){
                url+="&teamId="+team;
            }
          window.open(url);
        }
    }
</script>
</html>