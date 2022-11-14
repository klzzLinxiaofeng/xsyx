<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>大数据统计分析</title>
    <link rel="stylesheet" type="text/css" href="/res/css/statistics/data_statistics.css">
    <%@ include file="/views/embedded/common.jsp"%>
    <script type="text/javascript" src="/res/plugin/echarts/js/echarts.min.js"></script>
    <script src="/res/js/common/d3.v4.min.js" charset="utf-8"></script>
    <script src="https://img.hcharts.cn/highcharts/highcharts.js" charset="utf-8"></script>
</head>
<style>
    .student_select{position:absolute;left:10px;top:108px;display: none;color:#333;}
</style>
<script type="text/javascript">
    $(function(){
        $(".cartogram").hide();//隐藏wenben
        $(".cartogram:eq(0)").show();//显示第一个wenben
        $(".covariance a").click(function(){
            $(".covariance a").removeClass("switch");//移除样式
            $(this).addClass("switch");//添加样式
            var i=$(this).index();//获得下标
            $(".cartogram").hide();//隐藏wenben
            $(".cartogram:eq("+i+")").show();//显示第i个wenben
            if(i==2||i==4){
                $(".student_select").show();
            }else{
                $(".student_select").hide();
            }
            if(i==4){
            	changeData()
            }
        });

    });
</script>

<body>
<div class="manage">
    <div class="count">大数据统计分析
        <a href="javascript:void(0);" onclick="goBack()">返回</a>
    </div>
    <div class="covariance" style="position: relative">
        <a href="javascript:void(0);" class="switch">班级成绩分析</a>
        <a href="javascript:void(0);">年级成绩分析</a>
        <a href="javascript:void(0);">学习效果分析</a>
        <a href="javascript:void(0);">教学质量分析</a>
        <a href="javascript:void(0);">教学综合分析</a>
        <div class="student_select"><span style="line-height:25px;">学生：</span><select id="stu" class="chzn-select" onchange="changeData()" style="margin:0"></select></div>
    </div>
    <div class="clear"></div>

    <div class="cartogram">

        <%--班级基本信息统计--%>
        <h1>学年：${schoolYear} 试卷名称：${paper.title} 考试时间：<fmt:formatDate value="${exam.examDate}" pattern="yyyy/MM/dd HH:mm:ss" /> </h1>
        <div class="statistics1" style="min-height:466px;height:100%">
            <jsp:include page="team_jiben.jsp"></jsp:include>
        </div>

        <%--班级分数项对比图--%>
        <div class="statistics2">
            <jsp:include page="team_fenshuxiangdbt.jsp"></jsp:include>
        </div>
	<div class="clear"></div>
        <%--本班三率次数分布图--%>
        <div class="statistics1">
            <jsp:include page="team_bbslfbt.jsp"></jsp:include>
        </div>

        <%--本班得分段比例分布图--%>
        <div class="statistics2">
            <jsp:include page="team_bbdfdbl.jsp"></jsp:include>
        </div>

        <%--本班-年级 三率对比图--%>
        <div class="statistics1">
            <jsp:include page="team_grade_sldbt.jsp"></jsp:include>
        </div>

        <%--本班-年级得分段堆积对比图--%>
        <div class="statistics2">
            <jsp:include page="team_grade_dfdtj.jsp"></jsp:include>
        </div>
        <div class="clear"></div>
    </div>

   <div class="cartogram">
        <h1>学年：${schoolYear} 试卷名称：${paper.title} 考试时间：<fmt:formatDate value="${exam.examDate}" pattern="yyyy/MM/dd HH:mm:ss" />

        </h1>
        <div class="statistics1" style="height:auto;">
            <jsp:include page="grade_njtjxx.jsp"></jsp:include>
        </div>

        <div class="statistics2" style="height:auto;">
            <jsp:include page="grade_team_bjpjfpm.jsp"></jsp:include>
        </div>
        <div class="clear"></div>

        <%--全年级平均分雷达对比图--%>
        <div class="statistics1">
            <jsp:include page="grade_njpjflddb.jsp"></jsp:include>
        </div>

        <%--全年级最高分和最低分对比图--%>
        <div class="statistics2">
            <jsp:include page="grade_hight_low_dbt.jsp"></jsp:include>
        </div>

        <%--全年级优秀率对比图--%>
        <div class="statistics1">
            <jsp:include page="threeRatiosOfHigh.jsp"></jsp:include>
            <div class="clear"></div>
        </div>

        <%--全年级良好率对比图--%>
        <div class="statistics2">
            <jsp:include page="TreeRatiosOfLow.jsp"></jsp:include>
            <div class="clear"></div>
        </div>

        <%--全年级及格率对比图--%>
        <div class="statistics1">
            <jsp:include page="TreeRatiosOfPass.jsp"></jsp:include>
            <div class="clear"></div>
        </div>

        <%--全年级三率综合堆积对比图--%>
        <div class="statistics2">
            <jsp:include page="grade_slzhtjdbt.jsp"></jsp:include>
        </div>

        <%--全年级三率综合折线对比图--%>
        <div class="statistics1">
            <jsp:include page="grade_treeRid_zhexian.jsp"></jsp:include>
        </div>
        <div class="clear"></div>
    </div>

    <%--基本统计数字--%>
    <div class="cartogram">
        <h1>学年：${schoolYear} 试卷名称：${paper.title} 考试时间：<fmt:formatDate value="${exam.examDate}" pattern="yyyy/MM/dd HH:mm:ss" /></h1>
        <div style="float:right;padding:10px 10px 0;"></div>
        <div class="clear"></div>
        <div class="statistics1">
            <jsp:include page="student_team_jiben.jsp"></jsp:include>
        </div>

        <%--分数对比--%>
        <div class="statistics2">
            <jsp:include page="student_team_fsdb.jsp"></jsp:include>
        </div>

        <%--三率位置图--%>
        <div class="statistics1">
            <jsp:include page="team_student_slwzt.jsp"></jsp:include>
        </div>

        <%--教学评价 --学生单科 错题知识点统计情况--%>
        <div class="statistics2">
            <jsp:include page="team_student_ctzsdfb.jsp"></jsp:include>
        </div>

        <%--知识点 答题情况统计--%>
        <div class="statistics1">
            <jsp:include page="team_student_dtqktj.jsp"></jsp:include>
        </div>

        <%--认知度答题情况统计--%>
        <div class="statistics2">
            <jsp:include page="team_student_rzddtqktj.jsp"></jsp:include>
        </div>
        <div class="clear"></div>
    </div>

 <div class="cartogram">
        <h1>学年：${schoolYear} 试卷名称：${paper.title} 考试时间：<fmt:formatDate value="${exam.examDate}" pattern="yyyy/MM/dd HH:mm:ss" /></h1>
        

        <%--题目难度得分率统计--%>
        <div class="statistics1">
            <jsp:include page="teachingEvaluation_zzdnddfl.jsp"></jsp:include>
        </div>

        <%--题目认知度得分率统计--%>
        <div class="statistics2">
            <jsp:include page="teachingEvaluation_rzddfl.jsp"></jsp:include>
        </div>
        <%--知识点得分率统计--%>
        <div class="statistics1" style="width:92%;height:650px;">
            <jsp:include page="teachingEvaluation_zsddfl.jsp"></jsp:include>
        </div>

        <%--错题知识点分布统计--%>
        <div class="statistics1" style="width:92%;height:650px;">
            <jsp:include page="teachingEvaluation_ctzzdfb.jsp"></jsp:include>
        </div>
        <%--典型错题分析--%>
        <div class="statistics1" style="height:auto;">
            <jsp:include page="teachingEvaluation_dxctfx.jsp"></jsp:include>
        </div>

        <%--题目类型 得分率统计--%>
        <div class="statistics2">
            <jsp:include page="teachingEvaluation_tmlxdfl.jsp"></jsp:include>
        </div>

        <div class="clear"></div>
    </div>
    
    
    <div class="cartogram" style="min-height: 500px;">
        <h1>${examName} 个人答题情况分析</h1>
        <jsp:include page="examPerson.jsp"></jsp:include>
    </div>
</div>
<script type="text/javascript">
    $(function(){
        getStudent();
    })

    function changeData(){
        getDateForD3_xsjbxx();
        getDateForHighChar();
        getDateForD3Mine();
        getDateForDTQKTJ();
        getDateForRZDDTQKTJ();
        getDataForMine();
        getData();
    }

    function getStudent(){
        $.post("${ctp}/statistic/getStudent", {"examId":${exam.id}}, function (returnData, status) {
            if ("success" === status) {
                returnData = JSON.parse(returnData);
                $("#stu").html("");
                $("#stu").append("<option value=''>请选择</option>");
                $.each(returnData,function(n,value) {
                    $("#stu").append("<option value='"+value.id+"'>"+value.name+"</option>");
                });
            }
        });
    }

    //认知度名称修改，返回的是String类型的1，2，3，4，5，6
    function changeName(name,type){
        if(type == "1"){
            name = "识记";
        }else if(type == "2"){
            name = "理解";
        }else if(type == "3"){
            name = "应用";
        }else if(type == "4"){
            name = "探究";
        }else if(type == "5"){
            name = "综合";
        }else if(type == "6"){
            name = "评价";
        }
        
        return name;
    }

    //返回上一层页面
    function goBack(){
        window.location.href = "${ctp}/statistic/checkTeamTaskCount";
    }

</script>
</body>
</html>