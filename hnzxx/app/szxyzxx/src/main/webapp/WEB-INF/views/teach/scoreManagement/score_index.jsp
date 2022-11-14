<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script>
<title>单科成绩统计</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon"/>
			<jsp:param value="班级单科成绩统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12"  style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							<span>${examTitle }</span>
							<p style="float:right;margin-bottom:0" class="btn_link">
<!-- 							<a class="a3" href="javascript:void(0)"><i class="fa fa-plus"></i>选择</a> -->
<!-- 							<a class="a1" href="javascript:void(0)"><i class="fa fa-print"></i>打印</a> -->
								<a class="a4" href="javascript:void(0)" onclick="reCount()"><i class="fa  fa-undo"></i>重新统计</a>
								<a href="javascript:void(0)" class="a4" onclick="goHistory()"><i class="fa fa-plus"></i>返回</a>
							</p>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="white cj_tj">
			<div class="nav">
				<ul>
					<li class="on"><a href="javascript:void(0)">统计</a></li>
					<li><a id="paiming" href="javascript:void(0)">排名</a></li>
<!-- 					<li><a href="javascript:void(0)">对比</a></li> -->
				</ul>
			</div>
			<input type="hidden" id="examId" value="${examStatVo.examId }">
			<div class="tj_main" style="">
				<div class="dkcj" >
					<div class="t_div" style="width:48%;">
						<p class="title">考试信息</p>
						<ul>
							<li><p class="p1">班级</p><p class="p2">${teamName }</p></li>
							<li><p class="p1">科目</p><p class="p2">${subjectName }</p></li>
							<li><p class="p1">考试日期</p><p class="p2"><fmt:formatDate value='${examDate }' /></p></li>
							<li><p class="p1">任课教师</p><p class="p2">${teacherName }</p></li>
							<li><p class="p1">满分分数</p><p id="fullScore" class="p2"><c:if test="${examStatVo.fullScore == null }">0.0</c:if>${examStatVo.fullScore }</p></li>
							<li><p class="p1">优秀分数</p><p id="highScore" class="p2"><c:if test="${examStatVo.highScore == null }">0.0</c:if>${examStatVo.highScore }</p></li>
							<li><p class="p1">良好分数</p><p id="lowScore" class="p2"><c:if test="${examStatVo.lowScore == null }">0.0</c:if>${examStatVo.lowScore }</p></li>
							<li><p class="p1">及格分数</p><p id="passScore" class="p2"><c:if test="${examStatVo.passScore == null }">0.0</c:if>${examStatVo.passScore }</p></li>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="t_div"style="width:48%;">
						<p class="title">基本统计</p>
						<ul>
							<li><p class="p1">平均分</p><p class="p2"><c:if test="${examStatVo.averageScore==null }">0.0</c:if>${examStatVo.averageScore }</p></li>
							<li><p class="p1">最高分</p><p class="p2">${examStatVo.max_score }</p></li>
							<li><p class="p1">最低分</p><p class="p2">${examStatVo.min_score }</p></li>
							<li><p class="p1">标准差</p><p class="p2"><c:if test="${examStatVo.sdScore==null }">0.0</c:if>${examStatVo.sdScore }</p></li>
							<li><p class="p1">考试人数</p><p class="p2">${examStatVo.studentCount }</p></li>
							<li><p class="p1">极差</p><p class="p2"><c:if test="${examStatVo.movValue==null }">0.0</c:if>${examStatVo.movValue }</p></li>
							<li><p class="p1">补考人数</p><p class="p2">${examStatVo.nextExamStudent }</p></li>
							<li><p class="p1">离差</p><p class="p2"><c:if test="${examStatVo.madValue==null }">0.0</c:if>${examStatVo.madValue }</p></li>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="t_div" id="c_zhu"style="width:48%;">
						<p class="title">三率统计</p>
						 <div id="container" style="width:99%;height:300px"></div>
					</div>
					<div class="t_div" id="c_bin"style="width:48%;">
						<p class="title">分段统计</p>
						<div id="container_bing" style="width:99%;height:300px"></div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="paiming" style="display:none">
					<div class="xz">
						<div class="left">
							<span>排序项：</span>
							<input type="radio" name="suru" data-obj-item="number" value="学号" /> 学号 &nbsp; &nbsp;
  							<input type="radio" name="suru" data-obj-item="name" value="姓名" /> 姓名 &nbsp; &nbsp;
  							<input type="radio" name="suru" data-obj-item="team_rank" value="名次" /> 名次 &nbsp; &nbsp;
						</div>
						<div class="right">
							<span>输出项：</span>
							<input type="checkbox" name="shuchu" value="班内学号" />班内学号 &nbsp; &nbsp;
							<input type="checkbox" name="shuchu" value="成绩" />成绩 &nbsp; &nbsp;
							<input type="checkbox" name="shuchu" value="等级" />等级 &nbsp; &nbsp;
							<input type="checkbox" name="shuchu" value="班级名次" />班级名次 &nbsp; &nbsp;
							<input type="checkbox" name="shuchu" value="年级名次" />年级名次 &nbsp; &nbsp;
							<button class="btn btn-primary" onclick="searchStudentRank()" >查询</button>
						</div>
						<div class="clear"></div>
					</div>
					<table class="responsive table table-striped" id="studentRank">
					</table>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function () {
		$(".cj_tj .nav ul li a").click(function(){
			$(".cj_tj .nav ul li ").removeClass("on");
			var i=$(this).parent().index();
			$(this).parent().addClass("on");
			$(".tj_main").children().hide();
			$(".tj_main").children().eq(i).show();
			if(i=='1'){
				var examId= $("#examId").val();
				var $requestDate = {};
				$requestDate.examId = examId;
				$requestDate.sortItem = "team_rank";
				$(".widget-head .a3").hide();
				$(".widget-head .a4").hide();
				$("#studentRank").load("${ctp}/teach/scoreManagement/getStudentRank",$requestDate)
			}else{
				$(".widget-head .a3").show();
				$(".widget-head .a4").show();
			}
		})
		var fullScore = $("#fullScore").html();
		var highScore = $("#highScore").html();
		var lowScore = $("#lowScore").html();
		var passScore = $("#passScore").html();
		if(fullScore=="0.0" && highScore=="0.0" && lowScore=="0.0" && passScore=="0.0"){
			$("#c_zhu").hide();
			$("#c_bin").hide();
			$.alert("请设置考试属性,数据无法统计...")
		}else{
			getZhuScore();//获得柱状图的数据
			getScoreRate();//获得饼状图的数据	
		}
	
	});	
	
	function getZhuScore(){
		var examId= $("#examId").val();
		var $requestDate = {};
		$requestDate.examId = examId;
		var url = "${ctp}/teach/scoreManagement/getZhuScore";
		$.post(url,$requestDate,function(data){
			getzhuData(data);
		},'json')
	}
	
	function getzhuData(data){
		//alert(JSON.stringify(data))
		 $('#container').highcharts({
		        chart: {
		            type: 'column'
		        },
		        title: {
		            text: null
		        },
		        subtitle: {
		            text: null
		        },  
		        credits: {
		            enabled: false
		       },
		       exporting:{ 
	               enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
	          },
		        xAxis: {
		            categories: [
		                '百分比'
		            ],
		        },
		        yAxis: {
		            min: 0,
		            allowDecimals:true,
		            title: {
		                text: null
		            },
		            stacking: 'percent',
		         	labels:{
		                    formatter: function(){
		                         return this.value+'%';
		                     },
		                 },
		        },
		        tooltip: {
		            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
		            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
		            footerFormat: '</table>',
		            shared: true,
		            useHTML: true
		        },
		        plotOptions: {
		            column: {
		                pointPadding: 0.2,
		                borderWidth: 0,
		                dataLabels: {
			                enabled: true
			            }
		            },
		            bar: {
		               	dataLabels: {
		                  enabled: true, // 在节点显示数据
		                  //color: '#000000',  // 设置节点显示数据字体的颜色
		                  formatter: function() {
		                          return this.point.y+'%';  // 重新设置节点显示数据
		                       	},
		              },
		            }
		        },
		        series: data
		    });
	}
	
	function getScoreRate(){
		var examId= $("#examId").val();
		var $requestDate = {};
		$requestDate.examId = examId;
		var url = "${ctp}/teach/scoreManagement/getScoreRate";
		$.post(url,$requestDate,function(data){
			getData(data);
		},'json')
	}
		
	function getData(datas){
		var data = [];
		for(var key in datas){
			data.push([key,datas[key]]);
		}
		
	    $('#container_bing').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: null
	        },
	        credits: {
	            enabled: false
	       },
	       exporting:{ 
               enabled:false //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示 
          },
	        tooltip: {
	    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    color: '#000000',
	                    connectorColor: '#000000',
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: 'Browser share',
	            data: data
	        }]
	    });
	}
	
	<%-- 
		@function 查询学生排序
		@date 2016年1月28日
	--%>
	function searchStudentRank(){
		var sortItem = $('input[type="radio"]:checked').attr("data-obj-item");
		if(sortItem===undefined){
			sortItem = team_rank;
		}
		var examId= $("#examId").val();
		var $requestDate = {};
		$requestDate.examId = examId;
		$requestDate.sortItem = sortItem;
		$("#studentRank").load("${ctp}/teach/scoreManagement/getStudentRank",$requestDate,function(){
			$(".cj_tj .paiming .xz .right input").each(function(){
				var i=$(this).index();
				if(i==1){
					i=i-1;
				}
			if(this.checked){
			$("thead th").eq(i).show();
				$("tbody tr").each(function(){
					$(this).children().eq(i).show();
				})
			}
			else{
				$("thead th").eq(i).hide();
				$("tbody tr").each(function(){
					$(this).children().eq(i).hide();
				})
			}
		})
		})
	}
	
	<%-- 
		@function 重新统计
		@date 2016年2月1日
	--%>
	function reCount(){
		var examId= $("#examId").val();
		var $requestDate = {};
		$requestDate.examId = examId;
		var url = "${ctp}/teach/scoreManagement/reCount";
		var loader = new loadLayer();
		loader.show();
		$.post(url, $requestDate, function(data, status){
			if ("success" === status) {
				if ("success" === data) {
					window.location.href="${ctp}/teach/scoreManagement/index?examId="+examId;
				}else{
					$.error("重新统计失败！！！");
				}
			}else{
				$.error("重新失败！！！");
			}
			loader.close();
		});
	}
	
	function goHistory(){
		window.location.href="${ctp}/teach/pjExam/entryManagement";
	}
	</script>
</body>
</html>
