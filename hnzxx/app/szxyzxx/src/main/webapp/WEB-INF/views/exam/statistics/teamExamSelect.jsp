<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <%@ include file="/views/embedded/plugin/dept_selector_js.jsp"%>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="测试选择" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        测试选择
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div">
                                <span style="width:60px;text-align:right;display:inline-block">学&nbsp; &nbsp; &nbsp; &nbsp;校：</span>
                                <c:choose>
                                    <c:when test="${school == null}">
                                        <select class="span13" id="schoolId" name="schoolId"></select>
                                    </c:when>
                                    <c:otherwise>
                                            <select class="span13" id="schoolId" style="width: 150px" name="schoolId">
                                            <option value="${school.id}">
                                                ${school.name}
                                            </option>
                                        </select>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="clear"></div>
                            <div class="select_div">
                                <span>学年学期：</span>
                                <select id="schoolYear" onchange="changeSchoolYear()"></select>
                                <select id="schoolterm" style="float:right;margin-left:5px;"></select>
                            </div>
                            <div class="clear"></div>
                            <div class="select_div">
                                <span style="width:60px;text-align:right;display:inline-block">班&nbsp; &nbsp; &nbsp; &nbsp;级：</span>
                                <select id="nj"></select>
                                <select id="bj"></select>
                            </div>
                            <div class="clear"></div>
                            <div class="select_div">
                                <span>测试科目：</span>
                                <select id="subject"></select>
                            </div>
                          <!--   <div class="select_div">
                                <span>测试类型：</span>
                                <select id="examType"></select>
                            </div> -->
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <div class="clear"></div>
                        </div>

                        <div class = "tishi" style="line-height:30px;">请从下列查询结果中选择一个进行统计分析：</div>

                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>测试时间</th>
                                <th>科目</th>
                                <th>测试类型</th>
                                <th>考试人数</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="shop_list_content">
                                <jsp:include page="./teamExamSelectList.jsp" />
                            </tbody>
                        </table>

                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="shop_list_content" />
                            <jsp:param name="url" value="/schoolaffair/shop/index?sub=list&dm=${param.dm}" />
                            <jsp:param name="pageSize" value="${page.pageSize}" />
                        </jsp:include>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    $(function(){
        if(${school == null}){
            $.SchoolSelector({
                "selector" : "#schoolId",
                "selectedVal":${schoolId},
                "afterHandler":getSchoolYear()
            });
        }else{
            getSchoolYear();
        }

        $("#schoolId").change(function(){
            //获取学年
            getSchoolYear();
        });

        $("#schoolYear").change(function(){
            //获取学年、年级
            getSchoolTrem();
            getGrade();
        });

        $("#nj").change(function(){
            //获取班级
            getTeam();
        });

        $("#bj").change(function(){
            search();
        });

        setTimeout(function () {
            search();
        },500);
    });

        function getSchoolYear(){
            var schoolId = $("#schoolId").val();
            if(schoolId == null || schoolId == ""){
                schoolId = ${schoolId};
            }
            $("#schoolYear").empty();
            if(schoolId == ""){
                return;
            }
            var val = {};
            val.schoolId = schoolId;
            $.get("${ctp}/teach/schoolYear/list/json", val, function(data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    $.each(data, function(index, value) {
                        $("#schoolYear").append("<option value='" + value.year + "'>" + value.name + "</option>");
                    });
                    getSchoolTrem();
                    getGrade();
                }
            });
        }

        function getSchoolTrem(){
            var schoolId = $("#schoolId").val();
            if(schoolId == null || schoolId == ""){
                schoolId = ${schoolId};
            }
            var schoolyear = $("#schoolYear").val();
            $("#schoolterm").empty();
            if(schoolyear == ""){
                return;
            }
            var val = {};
            val.schoolId = schoolId;
            val.schoolYear = schoolyear;
            $.get("${ctp}/teach/schoolTerm/list/json", val, function(
                    data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    $.each(data, function(index, value) {
                        $("#schoolterm").append("<option value='" + value.code + "'>" + value.name + "</option>");
                    });
                }
            });
        }

        function getGrade(){
            var val = {};
            var schoolId = $("#schoolId").val();
            if(schoolId == null || schoolId == ""){
                schoolId = ${schoolId};
            }
            var schoolYear = $("#schoolYear").val();
            val.schoolId = schoolId;
            val.schoolYear = schoolYear;
            $("#nj").empty();
            $.get("${ctp}/teach/grade/list/json", val, function(data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    $.each(data, function(index, value) {
                        $("#nj").append("<option value='" + value.id + "'>" + value.name + "</option>");
                    });
                    getTeam();
                }
            });
        }

        function getTeam(){
            var val = {};
            var schoolId = $("#schoolId").val();
            if(schoolId == null || schoolId == ""){
                schoolId = ${schoolId};
            }
            var schoolYear = $("#schoolYear").val();
            var gradeId = $("#nj").val();
            val.schoolId = schoolId;
            val.schoolYear = schoolYear;
            val.gradeId = gradeId;
            $("#bj").empty();
            $.get("${ctp}/teach/team/list/json", val, function(data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    $.each(data, function(index, value) {
                        $("#bj").append("<option value='" + value.id + "'>" + value.name + "</option>");
                    });
                }
            });
        }

        //科目下拉
        $.PjSubjectSelector({
		    "selector" : "#subject"
        });

        $.jcGcSelector("#examType", {tc : "XY-JY-KSLX"}, "", function() {

        },function(){
            $("#examType").chosen();
        });

    function checkParam(){
        var schoolYear = $("#schoolYear").val();
        var schooltrem = $("#schooltrem").val();
        var bj = $("#bj").val();
        var nj = $("#nj").val();

        if(schoolYear == ""){
            $.alert("请选择学年！");
            return false;
        }
        if(schooltrem == ""){
            $.alert("请选择学期！");
            return false;
        }
        if(nj == ""){
            $.alert("请选择年级！");
            return false;
        }
        if(bj == ""){
            $.alert("请选择班级！");
            return false;
        }
    }

    function search() {
        var val = {};
        var schoolYear = $("#schoolYear").val();
        var schooltrem = $("#schooltrem").val();
        var bj = $("#bj").val();
        var nj = $("#nj").val();
        var subject = $("#subject").val();
        var type = "11";
        	//$("#examType").val();

        val.schoolYear = schoolYear;
        val.termCode = schooltrem;
        val.gradeId = nj;
        val.teamId = bj;
        val.subjectCode = subject;
        val.examType = type;

        var id = "shop_list_content";
        var url = "/statistic/getTeamTaskData";
        myPagination(id, val, url);
    }

    //跳转统计页面
    function staticPage(examId,teamId){
        window.location.href = "${ctp}/statistic/toStatisticPage?examId="+examId+"&teamId="+teamId;
    }

    function initGroupSelect(){
        var $requestData = {};
        $requestData.id = '${sessionScope[sca:currentUserKey()].groupId}';
        $.getGroup($requestData, function(data) {
            $("#schoolId").empty();
            if(data.length > 1){
                $("#schoolId").append("<option value=''>" + "请选择" + "</option>");
                $.each(data, function(index, value) {
                    $("#schoolId").append("<option value='" + value.id + "'>" + value.name + "</option>");
                });
            }else if(data.length == 1){
                $.each(data, function(index, value) {
                    $("#schoolId").append("<option value='" + value.id + "'>" + value.name + "</option>");
                });
            }else{
                $("#schoolId").append("<option value=''>" + '请选择' + "</option>");
            }
        });
    }
</script>
</html>