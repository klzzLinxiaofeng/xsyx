<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生统计</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/data_statistics.css">
<script src="${pageContext.request.contextPath}/res/plugin/echarts/js/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/echarts/js/jquery-2.1.1.js"></script>
<script src="${pageContext.request.contextPath}/res/js/common/regionSelect.js"></script>
<script type="text/javascript">
	var page = "${page}";
	var type = "${type}";
	$(function(){
	    $("body").on("click",".term",function(){
	        var checkArry = document.getElementsByName("cc");
	        for(var i = 0;i < checkArry.length;i++){
	            if(checkArry[i].checked == true){
	                $(".statistics:eq("+i+")").show();
	            }else{
	                $(".statistics:eq("+i+")").hide();
	            }
	        }
	    });
	    if(page == "school"){
	    	$("#project").hide();
		}else{
			$("#project").show();
		}
	});
</script>
</head>
<body>
<div class="manage">
    <div class="count">
    	学生统计
    	<!-- <a href="javascript:void(0);">导出</a> -->
    </div>
    <div class="covariance">
    	<div id="project" style="display: none">
       	<span style="width: 45px;height: 30px;float: left;margin-right: 10px;margin-left:20px;margin-top: 5px;line-height:60px;">地区：</span>
        <div class="project">
            <select id="province" name="province"></select>
            <select id="city" name="city"></select>
            <select id="district" name="district"></select>
        </div>
           	<button type="button" class="btn btn-primary" onclick="search()">查询</button>
        </div>
        <div class="clear"></div>
        <p>统计项</p>
        <div class="project">
<!--             <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>访问量</span></div> -->
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>人数统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>班级数统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>籍贯统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>健康状态统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>入学方式统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>就读方式统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>学生来源统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>男女比例统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>年龄统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>政治面貌统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>民族统计</span></div>
            <div class="term"><input type="checkbox" name="cc" checked="checked"/><span>户口性质统计</span></div>
        </div>
        <div class="clear"></div>
    </div>
	<div class="cartogram">
<!--         <div class="statistics"> -->
<!--             <h3>访问量</h3> -->
<!--             <div class="total"> -->
<!--                 <p>总访问量：60000</p> -->
<!--                 <p>学校总数：500</p> -->
<!--             </div> -->
<!--             <div id="yuan"></div> -->
<!--         </div> -->

        <div class="statistics">
            <h3>人数统计</h3>
            <div class="total">
                <p id="y_nop">学生总数：</p>
                <p id="x_nop">学校总数：</p>
            </div>
<!--             <div id="main"></div> -->
            <jsp:include page="./numberOfPeople.jsp" />
        </div>

        <div class="statistics">
            <h3>班级数统计</h3>
            <div class="total">
                <p id="y_tn">班级总数：</p>
                <p id="x_tn">学校总数：</p>
            </div>
<!--             <div id="classes"></div> -->
            <jsp:include page="./studentTeamNumber.jsp" />
        </div>

        <div class="statistics">
            <h3>籍贯统计</h3>
<!--             <div id="funnel"></div> -->
            <jsp:include page="./studentNativePlace.jsp" />
            <div id="native_explain" style="text-align:center;display:none;">
                <p style="margin:0px auto;font-family:微软雅黑;color:#999;">由于统计项过多，只显示前10个选项</p>
            </div>
        </div>

        <div class="statistics">
            <h3>健康状态统计</h3>
<!--             <div id="papers"></div> -->
            <jsp:include page="./studentHealthStatus.jsp" />
        </div>

        <div class="statistics">
            <h3>入学方式统计</h3>
<!--             <div id="entrance"></div> -->
           	<jsp:include page="./studentEnrollType.jsp" />
        </div>

        <div class="statistics">
            <h3>就读方式统计</h3>
<!--             <div id="marriage"></div> -->
            <jsp:include page="./studentAttendSchoolType.jsp" />
        </div>

        <div class="statistics">
            <h3>学生来源统计</h3>
<!--             <div id="job"></div> -->
            <jsp:include page="./studentSource.jsp" />
        </div>

        <div class="statistics">
            <h3>男女比例统计</h3>
<!--             <div id="proportion"></div> -->
             <jsp:include page="./sex.jsp" />
        </div>

        <div class="statistics">
            <h3>年龄统计</h3>
            <div style="height: 35px;margin: 18px 0 0 13px;">
<!--                 <p>年龄统计分析</p> -->
<!--                 <p>偏高<span>20%</span></p> -->
            </div>
<!--             <div id="age"></div> -->
			<jsp:include page="./age.jsp" />
        </div>

        <div class="statistics">
            <h3>政治面貌统计</h3>
<!--             <div id="face"></div> -->
            <jsp:include page="./politicalStatus.jsp" />
        </div>

        <div class="statistics">
            <h3>民族统计</h3>
<!--             <div id="nation"></div> -->
            <jsp:include page="./race.jsp" />
            <div id="race_explain" style="text-align:center;display:none;">
                <p style="margin:0px auto;font-family:微软雅黑;color:#999;">由于统计项过多，只显示前10个选项</p>
            </div>
        </div>

        <div class="statistics">
            <h3>户口性质统计</h3>
<!--             <div id="nature"></div> -->
            <jsp:include page="./residenceType.jsp" />
        </div>
    <div class="clear"></div>
    </div>
</div>
<div class="zzc" style="background-color:#000;position:fixed;left:0;top:0;z-index:998;width:100%;opacity: 0.7;display:none"></div>
<script type="text/javascript">
$(function(){
	$.initRegionLevelSelector({
		"lv" :"3",
		"sjSelector" : "province",
		"shijSelector" : "city",
		"qxjSelector" : "district",
	});
	search();
	
	var h=document.documentElement.clientHeight;
	$(".zzc").css("height",h);
	$(".statistics h3").click(function(){
		var index = $(this).parent().index();
		if($(this).parent().hasClass("click")){
	    	 $(this).parent().removeClass("click");
	    	 $(".zzc").hide();
		}else{
			$(this).parent().addClass("click");
			$(".zzc").show();
		}
		eval("myChart_"+index).resize();
	});
	
	$("h3").append("<span style='float: right;color: #91c7ae;'>点击标题栏放大/缩小</span>");
	$("h3 span").mouseover(function(){
		$(this).css("color","#FFEA49");
	});
	$("h3 span").mouseout(function(){
		$(this).css("color","#91c7ae");
	});
});

function search(){
	var areaCode = "";
	var province = $("#province").val();
	var city = $("#city").val();
	var district = $("#district").val();
	if(district != "" && district != null){
		areaCode = district;
	}else if(city != "" && city != null){
		areaCode = city;
	}else if(province != "" && province != null){
		areaCode = province;
	}
	
	getNumberOfPeopleData(areaCode);
	getTeamNumberData(areaCode);
	getNativePlaceData(areaCode);
	getHealthStatusData(areaCode); 
	getEnrollTypeData(areaCode); 
	getAttendSchoolTypeData(areaCode); 
	getStudentSourceData(areaCode); 
	getSexData(areaCode); 
	getAgeData(areaCode); 
	getPoliticalStatusData(areaCode); 
	getRaceData(areaCode); 
	getResidenceTypeData(areaCode); 
	
}


//访问量
var myChart = echarts.init(document.getElementById('yuan'));
option = {
    title : {
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a}{b} : {c} ({d}%)"
    },
    legend: {
        orient: 'vertical',
        left: 'left',
    },
    series : [
        {
            name: '',
            type: 'pie',
            radius : '55%',
            center: ['50%', '50%'],
            data:[
				{value:335, name:'一年级'},
				{value:310, name:'二年级'},
				{value:234, name:'三年级'},
				{value:135, name:'四年级'},
				{value:748, name:'五年级'},
				{value:948, name:'六年级'}
            ],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
            }
        }
    ]
};
myChart.setOption(option);

</script>
</body>
</html>