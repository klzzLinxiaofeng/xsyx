<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
    <title> <c:if test="${evType==1}">班主任评价</c:if><c:if test="${evType==2}">学科教师评价</c:if></title>
</head>
<style>
    .content_main{
        border-radius: 0 12px 12px 12px;
    }
</style>
<body style="background-color:#e3e3e3">
<div class="container-fluid">
    <%--<p class="top_link">首页 > <c:if test="${evType==1}">班主任评价</c:if><c:if test="${evType==2}">学科教师评价</c:if> > <span>评价记录</span></p>--%>
    <div class="content_main white">
        <div class="content_top">
            <div class="f_left"><p>评价记录</p></div>
            <div class="f_right">
                <button id="export" class="btn btn-green" onclick="daochu()"><i class="fa fa-external-link"></i>导出当前Excel表</button>
            </div>
        </div>
        <div class="input_select">
            <div class="select_div">
                <%--<select id="xn" name="xn" class="chzn-select" onchange="list()">--%>
                <select id="xn" name="xn" class="chzn-select">
                    <%--<option>学年</option>--%>
                </select>
            </div>
            <div class="select_div">
                <select id="xq" name="xq" class="chzn-select" onchange="getweek()">
                    <option>学期</option>
                </select>
            </div>
            <div class="select_div">
                <%--<select id="nj" name="nj" class="chzn-select" onchange="list()">--%>
                <select id="nj" name="nj" class="chzn-select">
                    <option>年级</option>
                </select></div>
            <div class="select_div">
                <%--<select id="bj" name="bj" class="chzn-select" onchange="list()">--%>
                <select id="bj" name="bj" class="chzn-select">
                    <option>班级</option>
                </select>
            </div>
                <div class="select_div" <c:if test="${evType==1}">style="display: none"  </c:if>>
                    <%--<select id="km" name="km" class="chzn-select" onchange="list()">--%>
                    <select id="km" name="km" class="chzn-select">
                        <option value="">所有科目</option>
                        <%--<option value="13">语文</option>--%>
                        <%--<option value="14">数学</option>--%>
                    </select>
                </div>

            <div class="select_div" style="height:32px;">
                <%--<select id="select_week" style="width: 240px;" onchange="list()">--%>
                <select id="select_week" style="width: 240px;">
                    <option value="">所有周</option>
                </select>
            </div>
            <div class="select_div">
                <span>关键字：</span>
                <input id="teacherName" type="text">
            </div>
            <div class="btn_link" style="float: right;margin:5px 5px 0 0">
                <button class="btn btn-blue" onclick="list()">搜索</button>
            </div>
        </div>
        <table class="table" id="list_table">
            <thead>
            <tr>
                <th>年级</th>
                <th>班级</th>
                <th>时间</th>
                <c:if test="${evType==1}"><th>班主任</th></c:if>
                <c:if test="${evType==2}"><th>科目</th><th>姓名</th></c:if>
                <th>评价人数</th>
                <th>综合评分↑</th>
                <c:if test="${evType==2}"><th>有效评分</th></c:if>
                <th class="caozuo">操作</th>
            </tr>
            </thead>
            <tbody id="list_content">
                <jsp:include page="list.jsp"/>
            </tbody>
        </table>
        <div class="no_teacher"></div>
        <div class="no_record"></div>
        <div class="page_div">
            <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                <jsp:param name="id" value="list_content"/>
                <jsp:param name="url" value="/assessment/teacher/index?sub=list&evType=${evType}&dm=${param.dm}"/>
                <jsp:param name="pageSize" value="${page.pageSize}"/>
            </jsp:include>
            <div class="clear"></div>
        </div>
    </div>
</div>
<script>
    var id="list_content";
    var url="/assessment/teacher/index?sub=list&evType=${evType}&dm=${param.dm}";
    var data=new Object();
    var i=0;

    $(function () {

        <c:if test="${evType==2}">
        var val1={};
        $.ajax({
            url: "${ctp}/assessment/statistics/subject",
            type: "POST",
            data: val1,
            async: false,
            success: function(data) {
                data = eval("(" + data + ")");
                for(var  z=1;z<data.length;z++){
                    $("#km").append("<option value='"+data[z].code+"'>"+data[z].name+"</option>");
                }
            }
        });
        </c:if>

        $.initCascadeSelector({
            "type" : "team",
            "selectOne":true,
            "yearChangeCallback" : function(year) {
                if (year != "") {
                    $.SchoolTermSelector({
                        "selector" : "#xq",
                        "condition" : {
                            "schoolYear" : year
                        },

                        "afterHandler" : function($this) {
                            $this.change();
                            $("#xq_chzn").remove();
                            $this.show().removeClass("chzn-done").chosen();
                        }
                    });

                } else {
                    $("#xq").val("");
                    $("#xq_chzn").remove();
                    $("#xq").show().removeClass("chzn-done").chosen();
                }
            }

        });

        <c:if test="${sub!='list'}">
            list();
        </c:if>


    })

    function getweek(){
        var isClear = false;
        var term = $('#xq').val();
        // var flag = $("#isvip").val();

        if ("" === term || "undefind" === term) {
            isClear = true;
        } else {
            var $requestData = {};
            $requestData.code = $('#xq').val();
            $.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json", $requestData, function(data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    begin = data.begin;
					var today = new Date().Format("yyyy-MM-dd");
                    end = data.end<=today? data.end:today;
                    $.getWeek({
                        "selector" : "#select_week",
                        "begin" : begin,
                        "end" : end,
                        "today" : today,
                        "isMonday":true,
                        "isClear" : isClear,
                        "isSelectCurrentWeek" : true,
                        "clearedOptionTitle" : "请选择学期",
                    },true);

                    // $("#select_week").options.add(new Option("所有周",""),1);

                    // if(flag != null || flag != ""){
                    //     sure();
                    // }
                }
            });
        }

        // if(i++>0){
        //     list();
        // }
    }

    function list() {
        data.name=$("#teacherName").val();
        data.schoolYear=$("#xn").val();
        data.termCode=$("#xq").val();
        data.gradeId=$("#nj").val();
        data.teamId=$("#bj").val();
        data.subjectCode=$("#km").val();
        data.week=$("#select_week").val();
        console.log("id:"+id);
        console.log("url:"+id);
        console.log("data:"+JSON.stringify(data));
        myPagination(id, data, url);
    }

    function daochu() {
        window.open("${ctp}/assessment/teacher/export?evType=${evType}&name=" + data.name + "&schoolYear=" + data.schoolYear + "&termCode=" + data.termCode + "&gradeId=" + data.gradeId + "&teamId=" + data.teamId + "&subjectCode=" + data.subjectCode + "&week=" + data.week + "");
    }

    // $.getWeek({
    //     "selector": "#select_week",
    //     "begin": "2017-02-01",
    //     "end": "2017-07-01",
    //     "today": "2018-03-28",
    //     "isClear": false,
    //     "isSelectCurrentWeek": true,
    //     "clearedOptionTitle": "请选择学期",
    // });

</script>
</body>
</html>