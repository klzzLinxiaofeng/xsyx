<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <title>Title</title>
    <%--<%@ include file="/views/embedded/common.jsp"%>--%>
</head>
<body>
<h3>年级统计信息</h3>
<div class="grade-statistics">
    <ul>
        <li><p>平均分</p><span id="avg">0</span></li>
        <li><p>最高分</p><span id="high">0</span></li>
        <li><p>最低分</p><span id="low">0</span></li>
        <li><p>标准差</p><span id="sd">0</span></li>
    </ul>
</div>
<div class="class-statistics">
    <ul>
        <li><p>年级</p><span id="gradeName">暂无</span></li>
        <li><p>科目</p><span id="subjectName"> 暂无</span></li>
        <li><p>班级总数</p><span id="teamCount">0</span></li>
        <li><p>学生总数</p><span id="studentCount">0</span></li>
    </ul>
</div>
<div class="clear"></div>
</body>
<script type="text/javascript">
    $(function(){
        getGradeBaisc();
    });

    function getGradeBaisc(){
        $.post("${ctp}/statistic/gradeCountBasic",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                if(typeof(returnData["avgCount"]) == "number"){
                    $("#avg").text(Number(returnData["avgCount"]).toFixed(2));
                }
                if(typeof(returnData["hightCount"]) == "number"){
                    $("#high").text(returnData["hightCount"]);
                }
                if(typeof(returnData["lowCount"]) == "number"){
                    if(returnData["lowCount"] < 0){
                        $("#low").text(0);
                    }else{
                        $("#low").text(returnData["lowCount"]);
                    }
                }
                if(typeof(returnData["sdCount"]) == "number"){
                    $("#sd").text(Number(returnData["sdCount"]).toFixed(2));
                }
                if(typeof(returnData["teamCount"]) == "number"){
                    $("#teamCount").text(returnData["teamCount"]);
                }
                if(typeof(returnData["studentCount"]) == "number"){
                    $("#studentCount").text(returnData["studentCount"]);
                }
                $("#gradeName").text(returnData["gradeName"]);
                $("#subjectName").text(returnData["subjectName"]);
            }
        },'json');
    }
</script>
</html>
