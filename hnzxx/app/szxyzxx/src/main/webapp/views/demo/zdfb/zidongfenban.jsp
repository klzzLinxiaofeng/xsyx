<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">

</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon"/>
			<jsp:param value="自动分班" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<!-- <div class="row-fluid ">
			<div class="span12"  style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							<span>自动分班</span>
						</h3>
					</div>
				</div>
			</div>
		</div> -->
		<div class="row-fluid">
			<div class="zdfb">
				<div class="z_top">
					<p>未分班学生</p>
					<p>人数：405</p>
				</div>
				<div class="z_bottom">
					<table class="table table-bordered"  style="max-height:460px;overflow:auto;display:block">
						<thead>
							<tr>
								<th colspan="2">男生比例</th>
								<th colspan="2">女生比例</th>
							</tr>
							<tr style="background-color:#fff">
								<th colspan="2"><span class="nan">男</span>：162人 <span class="nan">比例</span>：40%</th>
								<th colspan="2"><span class="nv">女</span>：162人 <span class="nv">比例</span>：40%</th>
							</tr>
							<tr>
								<th style="width:226px;">序号</th>
								<th style="width:250px;">姓名</th>
								<th style="width:226px;">性别</th>
								<th style="width:250px;">学籍号</th>
							</tr>
						</thead>
						<thead>
							<tr class="none">
								<th style="width:226px;"></th>
								<th style="width:251px;"></th>
								<th style="width:227px;"></th>
								<th style="width:251px;"></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>1</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>2</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>3</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>4</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>5</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>6</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>1</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>2</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>3</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>4</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>5</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>6</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>1</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>2</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>3</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>4</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>5</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>6</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>1</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>2</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>3</td>
								<td>李作涵</td>
								<td><span class="nan">男</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>4</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>5</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
							<tr>
								<td>6</td>
								<td>李作涵</td>
								<td><span class="nv">女</span></td>
								<td>G330402200706250018</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="z_btn">
					<a href="javascript:void(0)">自动分班</a>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$(".fenban_left .bottom .bj_rs .ban_ul li a").click(function(){
			$(".fenban_left .bottom .bj_rs .ban_ul li a").removeClass("on");
			$(this).addClass("on");
		});
		$(".fenban_left .bottom").on("click",".open .school",function(event){
			event.stopPropagation();
			$(this).parent().next().hide();
			$(this).parent().removeClass("open").addClass("close_1")
		});
		$(".fenban_left .bottom").on("click",".close_1 .school",function(event){
			event.stopPropagation();
			$(this).parent().next().show();
			$(this).parent().removeClass("close_1").addClass("open")
		});
		var wid=$(".fenban_right").css("width");
		var w=(parseInt(wid)-140)*0.5;
		$(".fenban_right .xs_div").css("width",w);
	});
	</script>
</body>
</html>
