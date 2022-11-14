<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <title>Title</title>
    <style>
        .bubble circle{
            stroke: black;
            stroke-width: 2px;
        }

        .bubble text{
            fill: black;
            font-size: 14px;
            font-family: arial;
            text-anchor: middle;
        }

    </style>
</head>
<body>
<h3>基本统计数字</h3>
<div class="number">
    <ul>
        <li><p>成绩</p><span id="stu_scr">暂无</span></li>
        <li><p>正答率</p><span id="stu_rig">暂无</span></li>
        <li><p>班级名次</p><span id="stu_bjmc">暂无</span></li>
        <li><p>年级名次</p><span id="stu_njmc">暂无</span></li>
    </ul>
</div>
</body>
<script>
    $(function(){
        getDateForD3_xsjbxx();
    })

    function getDateForD3_xsjbxx(){
        var studentId = $("#stu").val();
        if(studentId == ""){
            return;
        }
        initData();
        $.post("${ctp}/statistic/getStudentBasic", {"examId":${exam.id},"studentId":studentId}, function (returnData, status) {
            if ("success" === status) {
                if(returnData == ""){
                    return;
                }

                returnData = JSON.parse(returnData);
                if(typeof(returnData.score) == "number"){
                        $("#stu_scr").text(returnData.score);
                }
                if(typeof(returnData.rightAnswerCount) == "number" && typeof(returnData.answerCount) == "number"){
                	if(returnData.answerCount==0){
                	$("#stu_rig").text(0);
                	}else{
                    $("#stu_rig").text(Number((returnData.rightAnswerCount/returnData.answerCount)*100).toFixed(2));
                	}
                }
                if(typeof(returnData.teamRank) == "number"){
                    $("#stu_bjmc").text(returnData.teamRank);
                }
                if(typeof(returnData.gradeRank) == "number"){
                    $("#stu_njmc").text(returnData.gradeRank);
                }
            }
        });
    }

    function initData(){
        $("#stu_scr").text("暂无");
        $("#stu_rig").text("暂无");
        $("#stu_bjmc").text("暂无");
        $("#stu_njmc").text("暂无");
    }

    function changeData(){
        getDateForD3_xsjbxx();
    }
</script>

</html>
