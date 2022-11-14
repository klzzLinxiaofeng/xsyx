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
<h3>班级平均分排名</h3>
<div class="ranking">
    <ul id="avgrank">
        <li><p>1</p><span>暂无信息</span></li>
    </ul>
</div>
<div class="clear"></div>
</body>
<script type="text/javascript">
    $(function(){
        getGradeBaisc1();
    });

    function getGradeBaisc1(){
        $.post("${ctp}/statistic/getGradeTeamRank",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                $("#avgrank").html("");
                $.each(returnData,function(n,value) {
                    if(typeof(value.rank) == "number"){
                        $("#avgrank").append("<li><p>" + value.rank + "</p><span>" + value.teamName + "</span></li>");
                    }else{
                        $("#avgrank").append("<li><p>" + 0 + "</p><span>" + value.teamName + "</span></li>");
                    }
                });
            }
        },'json');
    }
</script>
</html>
