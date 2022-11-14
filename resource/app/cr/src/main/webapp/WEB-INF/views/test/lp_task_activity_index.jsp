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
<title>A3-2-0小结</title>
</head>
<body>
<jsp:include page="./header.jsp"></jsp:include>
<div class="dxaxj">
	<p class="title">${title }- 小结单元汇总</p>
	<p class="btn-caozuo"><button class="btn btn-lightGray">返回</button></p>
	<div class="xskt">
		<div class="xj_a">
			<a class="select_a on" href="javascript:void(0)" ></a>
			<div class="xs_list">
				<ul>
					<c:if test="${fn:length(lpUnitList)>0 }">
						<c:forEach items="${lpUnitList }" var="lpUnit">
							<li><a href="javascript:void(0)" title="${lpUnit.title }" unitId="${lpUnit.id }">${lpUnit.title }</a></li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</div>
<div class="xj_list">
<div class="all_thing">
	<p class="title">全部提交情况</p>
	<div id="container" style="width: 280px; height: 400px; margin: 0 auto"></div>
	<button class="btn btn-orange">查看详情</button>
</div> 
<!-- <div class="all_thing"> -->
<!-- 	<p class="title">个人提交情况</p> -->
<!-- 	<div class="tj_qingkuang"> -->
<!--  		<div class="y_tj">已提交</div> -->
<!--  		<div class="w_tj">未提交</div>  -->
<!-- 		<p class="name">彭寿联</p> -->
<!-- 	</div> -->
<!-- 	<button class="btn btn-orange">全部提交情况</button> -->
<!-- </div> -->
<div  id="xszj_list_id">
	<jsp:include page="./lp_task_activity_list.jsp" />
</div>
</div>
<script type="text/javascript">
$(function(){
	$(".dxaxj .xskt .xj_a .xs_list a").click(function(){
		var i=$(this).index();
		var unitId = $(this).attr("unitId");
		getUserActivityList(unitId);
	})
	if($(".dxaxj .xskt .xj_a .select_a").length>0) {
		$(".dxaxj .xskt .xj_a .select_a").show();
		$(".dxaxj .xskt .xj_a .select_a").eq(0).click();
	}
	$(".dxaxj .xskt .xj_a .xs_list ul li a").eq(0).click();
	$(".dxaxj .xskt .xj_a .select_a").text($(".dxaxj .xskt .xj_a .xs_list ul li a").eq(0).text());
	$(".dxaxj .xskt .xj_a .xs_list ul li a").click(function(){
		var title=$(this).text();
		$(".dxaxj .xskt .xj_a .select_a").text(title);
		$(".dxaxj .xskt .xj_a  .xs_list").hide();
	});
	$(".dxaxj .xskt .xj_a .select_a").click(function(){
		$(".dxaxj .xskt .xj_a  .xs_list").toggle();
		return false;
	});
	$(document).click(function()
            {
		$(".dxaxj .xskt .xj_a  .xs_list").hide();
            })

})

function getUnitContent(unitId) {
	$.ajax({
	    url: "${pageContext.request.contextPath}/learningPlan/unit/get",
	    type: "POST",
	    data: {"unitId":unitId, taskId:"${taskId}"},
	    async: true,
	    success: function(data) {
	    	var info = JSON.parse(data);
	    	$(".jszj").children("p").eq(1).html(info.unitContent);
	    	$(".ckxq").children("a").attr("unitId",info.id);
	    	$(".ckxq").children("p").children(".c_green").text(info.finishedCount);
	    	$(".ckxq").children("p").children(".c_yellow").text(info.unfinishCount);
	    	getUserActivityList(info.id);
	    }
	});
}

function getUserActivityList(unitId) {
	var data = {"taskId":"${taskId}", "unitId":unitId}
	var url = "/learningPlan/task/activity/list";
	
	var loader = new loadDialog();
    loader.show();
    myPagination("xszj_list_id", data, url,function() {
       	loader.close();
    });
}

function detail(obj) {
	var unitId = $(obj).attr("unitid");
	$.initWinOnTopFromLeft("提交情况", '${pageContext.request.contextPath}/learningPlan/task/activity/detail?taskId=${taskId}&unitId='+unitId, '800', '635');
}

// 右边统计图
$(function () {
    $('#container').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: 0,
            plotShadow: false
        },
        title: {
            text: '共50人',
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
                    name: '未提交',
                    y: 10,
                    color:'#fe718b',
                    sliced: true,
                    selected: true,
                },
                 {
                    name: '已提交',
                    y: 40,
                    color:'#108eff',
                }
            ]
        }]
    });
});
</script>
</body>
</html>