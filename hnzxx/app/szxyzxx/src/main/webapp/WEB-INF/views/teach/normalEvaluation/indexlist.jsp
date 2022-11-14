<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/sx_statistics.css">
<style>
	.project-rated{
		 margin: 0px 120px;
	}
</style>

 <div class="card_detail">

                        <div class="project-rated">
							<p>发展评价卡状况数据分析</p>
							<div class="points-content" style="display: block;">
                            <div class="points" style="position: relative;">
                            <table class="responsive table table-striped reflective-evaluate" id="data-table">
                            <thead style="background:#daf0fb;">
                                <tr role="row" style="color:#4c708a;">
                                <!-- 做判断 -->
                                <c:if test="${object == 'pjk'}"><th style="width: 8%;">评价卡</th></c:if>
                                <c:if test="${object == 'bj' }"><th style="width: 8%;">班级</th></c:if>
                                <c:if test="${object == 'nj' }"><th style="width: 8%;">年级</th></c:if>
                                   <th style="width:12%">总数量</th>
                                   <th style="width:8%">全年级占比</th>
                                   <th style="display: none;"></th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                           	<c:forEach items="${normalSummaryData}" var="n">
	                                <tr class="jtxx">
	                                    <td>${n.objectName}</td>
	                                    <td class="deduct">${n.count}</td><!-- 数量 -->
	                                    <td class="dScore_percent"></td><!-- 百分比 -->
	                                    <td class="dScore" style="display: none;" >${n.ratio }</td><!-- 小数 -->
	                                </tr>
		                         </c:forEach>
                                <tr class="">
                                      <td><b>统计</b><span></span></td>
                                      <td>总数量：<span id="sumSpan"></span></td>
                                      <td></td>
                                      <td style="display: none;"></td>
                                </tr>
                             </tbody>
                        </table>
                        </div>
                        </div>
                        </div>
                        </div>
                        
<script type="text/javascript">
$(function(){
	$(".jtxx").each(function(){
		var deduct = $(this).find(".deduct");
		var dScore = $(this).find(".dScore");
		var d = deduct.text();//数量
		var ds = dScore.text();
		var dper = Math.round(ds*100)+"%";
		$(this).find(".dScore_percent").html(dper);
		deduct.html("<p style='width: 50px;overflow: hidden;float: left;line-height: 18px;font-weight: normal;'>"
						+d+"</p><div class='deduct_div'><p style='width:"+dper+"'></p></div>");
	});
});
$(function(){
	var sum = 0;
	$(".jtxx").each(function(){
		var deduct = $(this).find(".deduct").text();
		var d = Number(deduct);
		sum += d;
	});
	$("#sumSpan").html(sum);
});
</script>












