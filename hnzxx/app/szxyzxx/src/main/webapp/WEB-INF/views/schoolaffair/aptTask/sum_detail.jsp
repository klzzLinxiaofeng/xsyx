<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href='${ctp}/res/css/extra/tongji.css' rel='stylesheet'/>
<title>考核查询</title>
<script>
	var deptFlag = 0;
	var personFlag = 0;
	var taskId = "${task.id}";
	$(function(){
// 		$.ajaxSetup({
// 			  async: false
// 		});
		$(".nav_1 ul li a").click(function(){
			var i=$(this).parent().index();
			$(".nav_1 ul li").removeClass("on");
			$(this).parent().addClass("on");
			$(".widget-container").hide();
			$(".widget-container").eq(i).show();
			if(i==1){
				$(".a1").eq(0).show();
				$(".a1").eq(1).hide();
			}
			var url = null;
			if(i == 1 ){
				if(deptFlag == 0){
					url = '${ctp}/schoolAffair/aptTask/sumDetailDept';
					var val = {};
					val.aptTaskId = taskId;
// 					val.scopeType = i;
					var loader = new loadDialog();
					loader.show();
					$("#dept_list_content").empty();
					$.post(url,val,function(data,status){
						if( "success" == status){
							data = eval("(" + data + ")");
							$.each(data,function(index,value){
								var num1 = value.dailyCount/value.departmentNum;
								var num2 = value.deductCount/value.departmentNum;
								var num3 = value.bonusCount/value.departmentNum;
								var num4 = (value.dailyCount - value.deductCount + value.bonusCount)/value.departmentNum;
								if(isNaN(num1)){
									num1 = 0;
								}
								if(isNaN(num2)){
									num2 = 0;									
								}
								if(isNaN(num3)){
									num3 = 0;
								}
								if(isNaN(num4)){
									num4 = 0;
								}
							
							

								$("#dept_list_content").append('<tr><td>' + (index + 1)  +'</td><td>' + value.departmentName + '</td><td>' + value.departmentNum + '</td><td>' + Math.round((num1)*100)/100 + '分</td><td>' + Math.round((num2)*100)/100  + '分</td><td>' + Math.round((num3)*100)/100 + '分</td><td>' + Math.round((num4)*100)/100 + '分</td></tr>');
							});
							$("#departmentNum").html(data.length);
							deptFlag = 1;
							initPrinter();
							loader.close();
						}else{
							loader.close();
						}
					});
				}
			}
			if(i == 2 && personFlag == 0){
				initPersonCount();
			}
			$("#teacherId").chosen();
			$("#targetTeacher").html("${list[0].teacherName}");
			initPrinter();
		});
		
		$(".detail").click(function(){
			var a1=$(this).parent().parent().parent().parent().parent(".a1")
			a1.hide();
			a1.next().show();
			
		});
		
		$("#teacherId").change(function(){
			initPersonCount();
		});
		initPrinter();
	});
	function initPrinter() {
		var index = $(".nav_1 ul li.on").index();
		$("#hide").hide();
		var $html = $("#data-table-" + index).html();
		var taskName = null;
		if(index == 0){
			taskName = "考核统计-全校统计";
		}else if(index == 1){
			taskName = "考核统计-部门统计";
		}else{
			taskName = "考核统计-个人统计";
		}
		$("#count_printer").printHtml_Preview({
			"taskName" : taskName,
			"htmlBody" : "<link href='${ctp}/res/plugin/falgun/css/table.css' rel='stylesheet'>"
			+ "<link href='${ctp}/res/css/extra/tongji.css' rel='stylesheet'/>" + $html
		});
		$("#hide").show();
	}
	function initPersonCount(){
		url = '${ctp}/schoolAffair/aptTask/sumDetailPerson';
		var val = {};
		val.aptTaskId = taskId;
		val.teacherId = $("#teacherId").val();
		var currentTeacherName = $("#teacherId").find("option[value='" + val.teacherId + "']").html();
		$("#targetTeacher").html(currentTeacherName);
		var loader = new loadDialog();
		loader.show();
		$("#person_list_content").empty();
		$.post(url,val,function(data,status){
			if( "success" == status){
				data = eval("(" + data + ")");
				var $appendHtml = null;
				var itemNum = 0;
				var totalCount = 0;
				$.each(data,function(index,value){
					if(index == 0){
						$appendHtml = '<td rowspan="' + value.taskItems.length + '">' + value.category + '</td>';
					}else{
						$appendHtml += '<tr><td rowspan="' + value.taskItems.length + '">' + value.category + '</td>';
					}
					$.each(value.taskItems,function(index2,value2){
						var num = value2.itemTotalNum == null ? 0 : value2.itemTotalNum;
						totalCount += num;
						if(index2 == 0){
							$appendHtml += '<td>' + value2.name + '</td><td>' + num + '分</td></tr>'; 
						}else{
							$appendHtml += '<tr><td>' + value2.name + '</td><td>' + num + '分</td></tr>';
						}
						itemNum++;
					});
				});
				$appendHtml = '<tr><td  rowspan="' + itemNum + '">日常</td>' + $appendHtml;
				$("#person_list_content").append($appendHtml);
				url2 = '${ctp}/schoolAffair/aptTask/sumDetailPersonBonus';
				$.post(url2,val,function(data2,status2){
					if("success"== status2){
						data2 = eval("(" + data2 + ")");
						$.each(data2,function(idx,value3){
							var bonusNum = value3.itemTotalNum == null ? 0 : value3.itemTotalNum;
							if(idx == 0){
// 								<td rowspan="' + data2.length + '">
								
								totalCount += bonusNum;
								$("#person_list_content").append('<tr><td>加分</td><td colspan="2">' + (value3.name == null ? '' : value3.name) + '</td><td>' + bonusNum + '分</td></tr>');
							}else{
								totalCount-=bonusNum;
								$("#person_list_content").append('<tr><td>减分</td><td colspan="2">' + (value3.name == null ? '' : value3.name) + '</td><td>' + bonusNum + '分</td></tr>');
							}
						});
						$("#person_list_content").append('<tr><td colspan="3">总计</td><td>' + totalCount + '分</td></tr>');
						initPrinter();
					}
				});
			}
			loader.close();
			personFlag = 1;
		});
	}
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="考核统计" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3 style="height:40px;">
							<div class="nav_1">
								<ul>
									<li class="on"><a href="javascript:void(0)">全校统计</a></li>
									<li><a href="javascript:void(0)">部门统计</a></li>
									<li><a href="javascript:void(0)">个人统计</a></li>
								</ul>
							</div>
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="history.back(-1);"><i class="fa  fa-undo"></i>返回</a>
							<a id="count_printer" class="a4" href="javascript:void();"><i class="fa fa-plus"></i>导出列表</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets" id="data-table-0">
						<div class="widget-container" >
							<p style="line-height:30px;text-align:center;font-size:30px;">${task.name}总表</p>
							<p class="main_p2">
								<span class="s1">周期：</span><span class="sp_green"><fmt:formatDate value="${task.startDate}" pattern="yyyy-MM-dd"/></span>至
								<span class="sp_green"><fmt:formatDate value="${task.finishDate}" pattern="yyyy-MM-dd"/></span>
								<span class="s1">考核人数：</span><span class="sp_blue">${empty userCount ? 0 : userCount}人</span>
								<span class="s1">考评人数：</span><span class="sp_yellow">${empty judgeCount ? 0 : judgeCount}人</span>
								<span class="s1">加分人数：</span><span class="sp_green1">${empty bonusCount ? 0 : bonusCount}人</span>
								<span class="s1">减分人数：</span><span class="sp_red">${empty deductCount ? 0 : deductCount}人</span>
							</p>
							<table class="responsive table table-striped table-bordered" >
								<thead>
									<tr>
<!-- 										<th class="f"><span class="span1">项目</span><span class="span2">教师</span></th> -->
										<th>序号</th>
										<th>部门</th>
										<th>姓名</th>
										<th>日常项</th>
										<th>扣分项</th>
										<th>加分项</th>
										<th>总分</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list}" var="item" varStatus="sta">
										<tr>
											<td>${sta.index + 1}</td>
											<td>${item.departmentName}</td>
											<td>${item.teacherName}</td>
											<td>${empty item.dailyCount ? 0 : item.dailyCount}分</td>
											<td>${empty item.deductCount ? 0 : item.deductCount}分</td>
											<td>${empty item.bonusCount ? 0 : item.bonusCount}分</td>
											<td><fmt:formatNumber type="number" value="${item.dailyCount + item.bonusCount - item.deductCount}" />分</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="clear"></div>
						</div>
						<div class="widget-container" style="display:none"  id="data-table-1">
						<div class="a1">
							<p style="line-height:30px;text-align:center;font-size:30px">${task.name}表</p>
							<p class="main_p2">
								<span class="s1">周期：</span><span class="sp_green"><fmt:formatDate value="${task.startDate}" pattern="yyyy-MM-dd"/></span>至
								<span class="sp_green"><fmt:formatDate value="${task.finishDate}" pattern="yyyy-MM-dd"/></span>
								<span class="s1">考核人数：</span><span class="sp_blue">${userCount}人</span>
								<span class="s1">部门数：</span><span class="sp_yellow" id="departmentNum">${task.scopeType == '2' ? 1 : judgeCount}个</span>
								<span class="s1">加分人数：</span><span class="sp_green1">${empty bonusCount ? 0 : bonusCount}人</span>
								<span class="s1">减分人数：</span><span class="sp_red">${empty deductCount ? 0 : deductCount }人</span>
							</p>
							<table class="responsive table table-striped table-bordered" >
								<thead>
									<tr>
										<th>序号</th>
										<th>部门</th>
										<th>人数</th>
										<th>日常项平均分</th>
										<th>扣分项平均分</th>
										<th>加分项平均分</th>
										<th>总平均分</th>
									</tr>
								</thead>
								<tbody id="dept_list_content">
<%-- 									<jsp:include page="./sum_detail_dept.jsp"/> --%>
								</tbody>
							</table>
							<div class="clear"></div>
							</div>
<!-- 							<div class="a1" style="display:none"> -->
<%-- 							<p style="font-size:18px;line-height:30px;text-align:center;"><span id="targetTeacher">张三</span>${task.name}</p> --%>
<!-- 							<table class="responsive table table-striped table-bordered" > -->
<!-- 								<thead> -->
<!-- 									<tr> -->
<!-- 										<th class="f"><span class="span1">教师</span><span class="span2">日期</span></th> -->
<!-- 										<th>张三</th> -->
<!-- 									</tr> -->
<!-- 								</thead> -->
<!-- 								<tbody> -->
<!-- 									<tr> -->
<!-- 										<td>2015.1.1</td> -->
<!-- 										<td><a href="javascript:void(0)">奥林匹克大赛一等奖奖状</a></td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>2015.1.2</td> -->
<!-- 										<td></td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>2015.1.3</td> -->
<!-- 										<td></td> -->
<!-- 									</tr> -->
<!-- 									<tr> -->
<!-- 										<td>2015.1.4</td> -->
<!-- 										<td></td> -->
<!-- 									</tr> -->
<!-- 								</tbody> -->
<!-- 							</table> -->
<!-- 							<div class="clear"></div> -->
<!-- 							</div> -->
						</div>
						<div class="widget-container" style="display:none"  id="data-table-2">
							<div class="cx_div">
								<div id="hide" class="select_div" >
									<span>姓名： </span>
									<select id="teacherId">
										<c:forEach items="${list}" var="item">
										 	<option value="${item.teacherId}">${item.teacherName}</option>
										</c:forEach>
									</select>
								</div>
<!-- 								<button type="button" class="btn btn-primary" onclick="initPersonCount();">查询</button> -->
							</div>
							<p style="font-size:30px;line-height:30px;text-align:center;"><span id="targetTeacher"></span>${task.name}</p>
							<table class="responsive table table-striped table-bordered table_left" >
								<thead >
									<tr><th style="width:90px;"></th><th style="width:200px;"></th><th></th><th></th></tr>
								</thead>
								<tbody id="person_list_content">
								</tbody>
							</table>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>