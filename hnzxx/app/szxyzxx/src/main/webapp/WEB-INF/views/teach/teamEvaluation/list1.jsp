<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/highcharts.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/Highcharts-4.0.3/js/modules/exporting.js"></script> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/sx_statistics.css">
<style>
	.project-rated{
		 margin: 0px 20px;
	}
	.table th, .table td{
		padding-left:20px;
	}
	.content-widgets p {
    margin-bottom: 0;
}
	.table{border:1px solid #ddd}
	.tr_bottom td{border-right:0 none}
</style>

 <div class="card_detail">

                        <div class="project-rated">
							<p>评价状况数据分析</p>
							<div class="points-content" style="display: block;">
                            <div class="points" style="position: relative;">
                            <table class="responsive table  reflective-evaluate" id="data-table">
                            <thead style="background:#daf0fb;">
                                <tr role="row" style="color:#4c708a;">
                                <!-- 做判断 -->
                                <c:if test="${object == 'jcx' }"><th style="width: 16%;">检查项</th></c:if>
                                <c:if test="${object == 'bj' }"><th style="width: 16%;">班级</th></c:if>
                                <c:if test="${object == 'nj' }"><th style="width: 16%;">年级</th></c:if>
                                    <th>扣分项分数</th>
                                   <th>加分项分数</th>
                                   <th style="width:12%;">总分数</th>
                                   <th style="width:8%;text-align:center;">扣分项占比</th>
                                   <th style="width:8%;text-align:center;">加分项占比</th>
                                </tr>
                            </thead>
                            <tbody id="module_list_content">
                           	<c:forEach items="${teamSummaryData}" var="t">
	                                <tr class="jtxx">
	                                    <td>${t.objectName}</td>
	                                   <td class="dScore" >${t.deductScore }</td> 
	                                    <td class="aScore">${t.addScore }</td> 
	                                    <td class="zfx">${t.totalScore}</td>
	                                    <td class="deduct" style="text-align: center">${t.deductRatio}</td>
	                                    <td class="add" style="text-align: center">${t.addRatio}</td>
	                                </tr>
		                         </c:forEach>
                                <tr class="tr_bottom" style="background-color: #f2f2f2;font-weight:bold;">
                                      <td><b>统计：</b></td>
                                      <td>扣分项<span><fmt:formatNumber type="number" value="${deductScore }" pattern="0.0" /></span></td> <!--  -->
                                       <td>加分项<span style="color: #2399dc;"><fmt:formatNumber type="number" value="${addScore }" pattern="0.0" /></span></td> 
                                      <td>总分项<span class="all_score"><fmt:formatNumber type="number" value="${totalScore }" pattern="0.0" /></span></td>
                                      <td></td>
                                      <td></td>
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
			var d = deduct.text();
			var ds = dScore.text();
			//var percent = a * 100 + "%";
			//deduct.find('.deduct_div .de').css("width",percent);
// 			var dper = Math.round(d*100)+"%";
			var dper = parseFloat(d*100).toFixed(1)+"%";
			dScore.html("<p style='width: 50px;overflow: hidden;float: left;line-height: 18px;font-weight: normal;margin: 0;'>"
							+ds+"</p><div class='deduct_div'><p style='width:"+dper+"'></p></div>");
			deduct.html(dper);
			
		})
		$(".jtxx").each(function(){
			var add = $(this).find(".add");
			var aScore = $(this).find(".aScore");
			var a = add.text();
			var as = aScore.text();
			//var percent = b * 100 + "%";
			//addRatio.find('.addRatio_div .ra').css("width",percent);
			var aper = Math.round(a*100).toFixed(1)+"%";
			aScore.html("<p style='width: 50px;overflow: hidden;float: left;line-height: 18px;font-weight: normal;'>"
					+as+"</p><div class='deduct_div'><p style='width:"+aper+"'></p></div>");
			add.html(aper);
			var num = $(this).find(".zfx").text();
			var num1 = $(this).find(".dScore p").text();
			var num2 = $(this).find(".aScore p").text();
			$(this).find(".zfx").text(parseFloat(num).toFixed(1));
			$(this).find(".dScore p").text(parseFloat(num1).toFixed(1));
			$(this).find(".aScore p").text(parseFloat(num2).toFixed(1));
		});
		var zfx_text=parseFloat($(".all_score").text()).toFixed(1);
		$(".all_score").text(zfx_text);
		
}); 
	
</script>












