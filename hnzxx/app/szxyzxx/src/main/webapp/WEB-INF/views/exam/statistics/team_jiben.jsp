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
<h3>基本统计数字</h3>
<div class="basic">
    <ul>
        <li><p>班级</p><span id="basic_team_name">暂无设置</span></li>
        <li><p>科目</p><span id="basic_team_subject">暂无设置</span></li>
        <li><p>考试日期</p><span id="basic_team_examDate">暂无设置</span></li>
        <li><p>任课教师</p><span id="basic_team_teacherName">暂无设置</span></li>
        <li><p>满分总数</p><span id="basic_team_fullScore">暂无设置</span></li>
        <li><p>及格分数</p><span id="basic_team_passScore">暂无设置</span></li>
        <li><p>应试人数</p><span id="basic_team_studentCount">暂无设置</span></li>
    </ul>
</div>
<div class="circle">
    <ul>
        <li><p>平均分</p><span id="basic_team_avg">暂无统计</span></li>
        <li><p>最高分</p><span id="basic_team_hight">暂无统计</span></li>
        <li><p>最低分</p><span id="basic_team_low">暂无统计</span></li>
        <li><p>年级排名</p><span id="basic_team_gradeRank">暂无统计</span></li>
        <li><p>标准差</p><span id="basic_team_sd">暂无统计</span></li>
        <li><p>极差</p><span id="basic_team_mov">暂无统计</span></li>
        <li><p>离差</p><span id="basic_team_mad">暂无统计</span></li>
    </ul>
</div>
</body>
<script type="text/javascript">
    $(function(){
        getTeamBaiscData();
    });
    function getMoth(str){  
        var oDate = new Date(str),
        oYear = oDate.getFullYear(), 
        oMonth = oDate.getMonth()+1,  
        oDay = oDate.getDate(),  
        oTime = getzf(oYear)+'-'+getzf(oMonth) +'-'+ getzf(oDay);//最后拼接时间  
        return oTime;  
    };  
    function getzf(num){  
        if(parseInt(num) < 10){  
            num = '0'+num;  
        }  
        return num;  
    }  
    function getTeamBaiscData(){
        $.post("${ctp}/statistic/teamBasic",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
//                 var mydate = new Date(returnData.examDate);
//                 var t = mydate.toLocaleString();
                t=getMoth(returnData.examDate);//使用方法 
                if(returnData.teamName != null){
                    $("#basic_team_name").text(returnData.teamName);
                }
                if(returnData.subjectName != null){
                    $("#basic_team_subject").text(returnData.subjectName);
                }
                if(returnData.examDate != null){
                    $("#basic_team_examDate").text(t);
                }
                if(returnData.teacherName != null){
                    $("#basic_team_teacherName").text(returnData.teacherName);
                }
                if(returnData.fullScore != null){
                    $("#basic_team_fullScore").text(returnData.fullScore);
                }
                if(returnData.passScore != null){
                    $("#basic_team_passScore").text(returnData.passScore);
                }
                if(returnData.studentCount != null){
                    $("#basic_team_studentCount").text(returnData.studentCount);
                }
                if(returnData.questionCount != null){
                    $("#basic_team_count").text(returnData.questionCount);
                }
                if(returnData.avgScore != null){
                    $("#basic_team_avg").text(returnData.avgScore);
                }
                if(returnData.hightScore != null){
                    $("#basic_team_hight").text(returnData.hightScore);
                }
                if(returnData.lowScore != null){
                    if(returnData.lowScore >= 0){
                        $("#basic_team_low").text(returnData.lowScore);
                    }else{
                        $("#basic_team_low").text(0);
                    }
                }
                if(returnData.gradeRank != null){
                    $("#basic_team_gradeRank").text(returnData.gradeRank);
                }
                if(returnData.sdScore != null){
                    $("#basic_team_sd").text(returnData.sdScore);
                }
                if(returnData.movValue != null){
                    $("#basic_team_mov").text(returnData.hightScore-returnData.lowScore);
                }
                if(returnData.madValue != null){
                    $("#basic_team_mad").text(returnData.madValue);
                }
            }
        },'json');
    }
</script>
</html>
