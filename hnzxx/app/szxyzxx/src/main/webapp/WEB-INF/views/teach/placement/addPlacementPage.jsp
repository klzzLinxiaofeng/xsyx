<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">

</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon"/>
			<jsp:param value="新生分班" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12"  style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							<span>新生分班</span>
							<p style="float:right;" class="btn_link">
								<a class="a2" href="javascript:void(0)">保存</a>
							</p>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid white">
			<div class="fenban_left">
				<div class="top">
					<span>索引</span>
					<button class="btn btn-primary"><i class="fa fa-plus"></i>添加班级</button>
				</div>
				<div class="bottom">
					<div class="open"><a href="javascript:void(0)" class="school">${schoolName }</a></div>
					<div class="bj_rs">
						<ul class="ban_ul">
							<c:forEach items="${teamList}" var="team" varStatus="s">
								<li><a <c:if test="${s.index==0}">class='on'</c:if> href="javascript:void(0)" onclick="getCurrentTeam('${team.fullName }');">${team.fullName }</a></li>
							</c:forEach>
							<li><a href="javascript:void(0)">未分班新生（50）</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="fenban_right">
				<div class="xs_div">
					<div class="top"><p>当前学生：未分班新生</p><p>人数：50</p></div>
					<div class="xs_list">
						<table class="table table-bordered responsive  white">
							<thead>
								<tr><th>男女比例</th><th>民族比例</th></tr>
								<tr><th><span class="nan">男</span>：22%（11人）<span class="nv">女</span>：78%（39人）</th><th><span>汉</span>：78%（39人）<span class="shao">少数民族</span>：22%（11人）</th></tr>
							</thead>
						</table>
						<div class="xuesheng">
						<table class="table table-bordered responsive white table_left">
							<thead>
								<tr><th style="width:34px;"><input type="checkbox" id="checkboxOut" name="checkboxOut" onclick="checkAll(1);"></th>
								<th style="width:34px;">序号</th>
								<th style="width:62px;">姓名</th>
								<th style="width:34px;">性别</th>
								<th>学籍号</th>
							</tr>
							</thead>
							<tbody id="tbody1">
								<c:forEach items="${studentList}" var="student" varStatus="s">
									<tr>
										<td><input type="checkbox" id="studentCheckbox" name="studentCheckbox" value="${student.id }"></td>
										<td>${s.index }</td>
										<td>${student.name }</td>
										<c:if test="${student.sex==1 }">
											<td class="nan">男</td>
										</c:if>
										<c:if test="${student.sex==2 }">
											<td class="nv">女</td>
										</c:if>
										<td>${student.studentNumber }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</div>
					</div>
				</div>
				 <div class="hj_hc">
					<a href="javascript:void(0)" class="hj" onclick="changeOut();">换出  &gt;</a>
					<a href="javascript:void(0)" class="hc" onclick="changeIn();">换进  &lt;</a>
				</div> 
				<div class="xs_div">
					<div class="top">
						<p id="currentTeam">当前班级：初一（1）班</p>
						<p>人数：0</p>
					</div>
					<div class="xs_list">
						<table class="table table-bordered responsive  white table_right">
							<thead>
								<tr><th>男女比例</th><th>民族比例</th></tr>
								<tr><th><span class="nan">男</span>：50%（0人）<span class="nv">女</span>：50%（0人）</th><th><span>汉</span>：50%（0人）<span class="shao">少数民族</span>：50%（0人）</th></tr>
							</thead>
						</table>
						<div class="xuesheng">
						<table class="table table-bordered responsive white">
							<thead id="threadId">
								<tr>
									<th style="width:34px;"><input type="checkbox" id="checkboxIn" name="checkboxIn" onclick="checkAll(0);"></th>
									<th style="width:34px;">序号</th>
									<th style="width:62px;">姓名</th>
									<th style="width:34px;">性别</th>
									<th>学籍号</th>
								</tr>
							</thead>
							<tbody id="tbody0">
							</tbody>
						</table>
						</div>
					</div>
				</div>
				<div class="clear"></div>
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
		
		$("#currentTeam").html("当前班级: "+$(".ban_ul li a.on").text());
		
	});
	
	function getCurrentTeam(obj){
		$("#currentTeam").html("当前班级: "+obj);
	}
	
	//换出
	function changeOut(){
		var outTemp ="";
		$('input[name="studentCheckbox"]:checked').each(function(){
            var sfruit=$(this).parent().parent();
            outTemp+="<tr>"+sfruit.html()+"</tr>";
        });
		
		$("#tbody0").append(outTemp);
	}
	//换进
	function changeIn(){
		var inTemp="";
		
		$('input[name="studentCheckbox"]:checked').each(function(){
            var sfruit=$(this).parent().parent();
            inTemp+="<tr>"+sfruit.html()+"</tr>";
        });
		$("#tbody1").append(inTemp);
	}
	//全选
	function checkAll(obj){
		if(obj==1){
			if($("#checkboxOut").prop("checked")==true){
				$("#tbody1 :checkbox").prop("checked", true);
			}else{
				$("#tbody1 :checkbox").prop("checked", false);
			}
		}else{
			if($("#checkboxIn").prop("checked")==true){
				$("#tbody0 :checkbox").prop("checked", true);
			}else{
				$("#tbody0 :checkbox").prop("checked", false);
			}
		}
		
	}
	</script>
</body>
</html>
