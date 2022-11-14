<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/dxaxj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<title>导学案</title>
</head>
<body>
<jsp:include page="../base/header.jsp"></jsp:include>
<%-- <input type="hidden" id="unitId" value="${unitId }"> --%>

<input type="hidden" id="unitId" >

<div class="dxaxj">
	<p class="title">${title }- 小结单元汇总</p>
	<p class="btn-caozuo"><button class="btn btn-lightGray" onclick="javascript:window.close();">返回</button></p>
	<div class="xskt">
		<c:if test="${unitSize>0 }">
			<div class="xj_a">
				<a class="select_a on" href="javascript:void(0)" ></a>
				<div class="xs_list">
					<ul>
						<c:forEach items="${lpUnitList }" var="lpUnit">
							<li><a href="javascript:void(0)" title="${lpUnit.title }" unitId="${lpUnit.id }">${lpUnit.title }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</c:if>
	</div>
</div>
<c:if test="${unitSize>0 }">
	<div class="xj_list">
		<div class="all_thing" id="personalDetail" style="display:none">
			<p class="title">个人提交情况</p>
			<div class="tj_qingkuang">
		 		<div class="y_tj">已提交</div>
		 		<!-- <div class="w_tj">未提交</div>  --> 
				<p class="name">彭寿联</p>
			</div>
			<button class="btn btn-orange" onclick="getUserActivityList()">全部提交情况</button>
		</div>
		<div class="all_thing" id="allDetail">
			<p class="title">全部提交情况</p>
			<div id="container" style="width: 280px; height: 400px; margin: 0 auto"></div>
			<button class="btn btn-yellow" onclick="detail()">查看详情</button>
		</div> 
		<div id="xszj_list_id">
			<jsp:include page="./lp_task_activity_list.jsp" />
		</div>
	</div>
</c:if>
<c:if test="${unitSize==0 }">
	<div class="no_activity">该导学案下还没有小结单元哦~</div>
</c:if>
<script type="text/javascript">
$(function(){
	if($(".dxaxj .xskt .xj_a .select_a").length>0) {
		$(".dxaxj .xskt .xj_a .select_a").show();
		$(".dxaxj .xskt .xj_a .select_a").eq(0).click();
	}
	
	$(".dxaxj .xskt .xj_a .xs_list ul li a").click(function(){
		var title=$(this).text();
		var unitId=$(this).attr("unitId");
		$("#unitId").val(unitId);
		getUnitContent(unitId);
		$(".dxaxj .xskt .xj_a .select_a").text(title);
		$(".dxaxj .xskt .xj_a  .xs_list").hide();
	});
	
	$(".dxaxj .xskt .xj_a .xs_list ul li a").eq(0).click();
	
	$(".dxaxj .xskt .xj_a .select_a").text($(".dxaxj .xskt .xj_a .xs_list ul li a").eq(0).text());
	
	$(".dxaxj .xskt .xj_a .select_a").click(function(){
		$(".dxaxj .xskt .xj_a  .xs_list").toggle();
		return false;
	});
	
	$(document).click(function(){
		$(".dxaxj .xskt .xj_a  .xs_list").hide();
    })

})

function getUnitContent(unitId) {
	var taskId = "${taskId}";
	$.ajax({
	    url: "${pageContext.request.contextPath}/learningPlan/task/unit/get",
	    type: "POST",
	    data: {"unitId":unitId, taskId:taskId},
	    async: true,
	    success: function(data) {
	    	var info = JSON.parse(data);
	    	initChart(info.finishedCount, info.unfinishCount, info.usedCount)
	    	getUserActivityList(taskId, unitId);
	    }
	});
}

function getUserActivityList(taskId, unitId, userId, studentName) {
	if(taskId==null) {
		taskId = "${taskId}";
	}
// 	if(unitId==null) {
// 		unitId = "${unitId}";
// 	}
	if(unitId==null) {
		unitId = $("#unitId").val();
	}
	if(studentName!=null) {
		$(".xj_list .all_thing .tj_qingkuang .name").text(studentName);
	}
// 	$("#unitId").val(unitId);
	
	var data = {"taskId":taskId, "unitId":unitId, "userId":userId}
	var url = "/learningPlan/task/activity/list";
	
	var loader = new loadDialog();
    loader.show();
    myPagination("xszj_list_id", data, url,function() {
    	if(userId!=null) {
    		$("#personalDetail").show();
    		$("#allDetail").hide();
    	} else {
    		$("#allDetail").show();
    		$("#personalDetail").hide();
    	}
       	loader.close();
    });
}

function detail() {
	var unitId = $("#unitId").val();
	$.initWinOnTopFromLeft_qyjx("提交情况", '${pageContext.request.contextPath}/learningPlan/task/activity/detail?taskId=${taskId}&unitId='+unitId, '700', '420');
}

function review() {
	location.href="${pageContext.request.contextPath}/learningPlan/task/index";
}

function initChart(finishedCount, unfinishCount, totalCount) {
	$('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: 0,
            plotShadow: false
        },
        title: {
            text: '共'+totalCount+'人',
            align: 'center',
            verticalAlign: 'middle',
            y: 10,
            style:{
	            "color":"#62bff7",
	            "fontSize": "24px",
	            "fontWeight":"bold",
	            "fontFamily":"微软雅黑"
            }
        },
        tooltip: {
            pointFormat: ' <b>{point.y}</b>人'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    /*distance: -50,*/
                    style: {
                        fontWeight: 'bold',
                       /*  color: '#666', */
                        fontFamily:"微软雅黑" 
                    }
                },
               /* startAngle: -90,
                endAngle: 90,
                center: ['50%', '75%']*/
            }
        },
        credits:{
            enabled:false // 禁用版权信息
       },
        series: [{
            type: 'pie',
            name: '',
            innerSize: '70%',
            data: [
                {
                    name: '未提交'+unfinishCount+'人',
                    y: unfinishCount,
                    color:'#fe718b',
                    sliced: true,
                    selected: true,
                },
                 {
                    name: '已提交'+finishedCount+'人',
                    y: finishedCount,
                    color:'#108eff',
                }
            ]
        }]
    });
}
</script>
</body>
</html>