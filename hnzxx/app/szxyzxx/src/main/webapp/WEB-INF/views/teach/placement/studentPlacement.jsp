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
.fenban_left .bottom{
    height: 600px;
    overflow-x: hidden;
}
.fenban_right .xs_div{
  width: 44.5%;
}
.fenban_right .hj_hc{
width:8%;
}
.fenban_right .hj_hc a{
	width:100%;
}
.fenban_right{
	padding-right: 20px;
}
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
								<a class="a5" href="javascript:void(0)" onclick="saveStudent();">保存</a>
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
<!-- 					<button class="btn btn-primary"><i class="fa fa-plus"></i>添加班级</button> -->
				</div>
				<div class="bottom">
					<div class="open"><a href="javascript:void(0)" class="school">${schoolName }</a></div>
					<div class="bj_rs">
						<ul class="ban_ul">
						<input type="hidden" id="teamList" <c:if test="${empty teamList }">value="true"</c:if>>
										 <c:choose>
										  	 <c:when test="${!empty teamList}">
										  	  		<c:forEach items="${teamList}" var="team" varStatus="s">
														<li><a <c:if test="${s.index==0}">class='on'</c:if> data_obj="${team.id}" href="javascript:void(0)" onclick="getCurrentTeam('${team.fullName }','${team.id}');">${team.fullName }</a></li>
													</c:forEach>
											 </c:when>
											 <c:otherwise>
											 
											</c:otherwise>
										  </c:choose>
							
<!-- 							<li><a href="javascript:void(0)">未分班新生（50）</a></li> -->
						</ul>
					</div>
				</div>
			</div>
			<div class="fenban_right">
				<div class="xs_div">
					<div class="top"><p>当前学生：未分班新生</p><p>人数：${noClassStuNum }</p></div>
					<div class="xs_list">
						<table class="table table-bordered responsive  white">
							<thead>
								<tr>
									<th>男生比例</th>
									<th>女生比例</th>
								</tr>
								<tr>
									<th><span class="nan">男</span>：${manNum }人<span class="nv">比例</span>：${manPercent }</th>
									<th><span class="nv">女</span>：   ${womNum }人<span class="nv">比例</span>：${wonPercent }</th>
								</tr>
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
										<input type="hidden" name="studentId" id="studentId" value="${student.id }"/>
										<td><input type="checkbox" id="studentCheckbox" name="studentCheckbox" value="${student.id }"></td>
										<td>${s.index+1 }</td>
										<td>${student.name }</td>
										<c:if test="${student.sex==1 }">
											<td class="nan">男</td>
										</c:if>
										<c:if test="${student.sex==2 }">
											<td class="nv">女</td>
										</c:if>
										<c:if test="${student.sex==9 }">
											<td class="nan">未说明</td>
										</c:if>
										<c:if test="${student.sex==0 }">
											<td class="nv">未知</td>
										</c:if>
										<c:if test="${empty student.sex }">
											<td class="nv">无</td>
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
				<form id="studentPlacementForm" name="studentPlacementForm" action="javascript:void(0);" style="margin:0">
				<input type="hidden" id="teamId" name="teamId" value="" />
				<div class="xs_div">
					<div class="top">
						<p id="currentTeam">当前班级：初一（1）班</p>
						<p id="allStuNum">总人数：0</p>
					</div>
					<div class="xs_list">
						<table class="table table-bordered responsive  white table_right">
							<thead>
								<tr>
									<th>男生比例</th>
									<th>女生比例</th>
								</tr>
								<tr>
									<th>
										<span class="nan" >男</span>：<span id="manNum">0人</span>
										<span class="nv">比例</span>：<span id="manPercent">0%</span>
									</th>
									<th>
										<span class="nan">女</span>：<span id="wonNum">0人</span>
										<span class="nv">比例</span>：<span id="wonPercent">0%</span>
									</th>
								</tr>
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
				</form>
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
// 		$(".fenban_right .xs_div").css("width",w);
		
		var teamName = "";
		$("#currentTeam").html("当前班级: "+teamName);
		var teamList = $("#teamList").val();
		if(teamList == "true"){
			$.alert("请先添加班级!");
		}else{
			teamName = $(".ban_ul li a.on").text();
			$("#currentTeam").html("当前班级: "+teamName);
			var teamId = $(".ban_ul li a.on").attr("data_obj");
			$("#teamId").val(teamId);
			
			getCurrentTeam(teamName,teamId);
		}
		
	});
	
	function getCurrentTeam(teamName,id){
		$("#currentTeam").html("当前班级: "+teamName);
		$("#teamId").val(id);
		
		//加载学生信息
		var url = "${pageContext.request.contextPath}/teach/placement/teamStudentList";
		var aj = $.ajax({
			url : url,
			data : 'teamId=' + id,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				loadTeamTable(data);
			},
			error : function() {
				$.alert("异常！");
			}
		});
		
	}
	
	function loadTeamTable(data){
		var dataList = data.studentVoList;
		var trTemp = "";
		for(var i=0;i<dataList.length;i++){
			
			//2016-6-7 添加  解决页面出现没有学籍号的时候显示null字符串  开始
			var studentNumber = "";
			if(dataList[i].studentNumber != null){
				studentNumber = dataList[i].studentNumber;
			}
			//2016-6-7 添加  解决页面出现没有学籍号的时候显示null字符串  结束
			
			var nu = i+1;
			trTemp+="<tr><td><input type='checkbox'></td><td>"+nu+"</td><td>"+dataList[i].name+"</td><td class='nan'>"+dataList[i].sex+"</td><td>"+studentNumber+"</td></tr>";
		}
		var allStuNum = data.allStuNum;
		var manNum = data.manNum;
		var wonNum = data.wonNum;
		var manPercent = data.manPercent;
		var wonPercent = data.wonPercent;
		$("#wonPercent").html(wonPercent);
		$("#manPercent").html(manPercent);
		$("#wonNum").html(wonNum+"人");
		$("#manNum").html(manNum+"人");
		$("#allStuNum").html("总人数:"+allStuNum);
		$("#tbody0").html(trTemp);
	}
	
	//换出
	function changeOut(){
		var outTemp ="";
		$('input[name="studentCheckbox"]:checked').each(function(){
            var sfruit_=$(this).parent().parent();
            outTemp+="<tr>"+sfruit_.html()+"</tr>";
        }); 
		 $("#tbody0").append(outTemp);
		 $('input[name="studentCheckbox"]:checked').each(function(){
			 $(this).closest('tr').remove();
	    });
		
	}
	//换进
	function changeIn(){
		var inTemp="";
		$('input[name="studentCheckbox"]:checked').each(function(){
            var sfruit=$(this).parent().parent();
            inTemp+="<tr>"+sfruit.html()+"</tr>";
        });
		 $("#tbody1").append(inTemp);
		 $('input[name="studentCheckbox"]:checked').each(function(){
			 $(this).closest('tr').remove();
	    }); 
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
	
	function saveStudent(){
		var stuNum = $("#studentPlacementForm input[name=studentId]").length;
		if(stuNum==0){
			$.success("请转入要分班的学生");
		}else{
			submitStudent(stuNum);
		}
	}
	
	function submitStudent(stuNum){
		var loader = new loadLayer();
		var studentId = new Array();
		for(var i=0;i<stuNum;i++){
			var stVal = $("#studentPlacementForm input[name='studentId']:eq("+i+")").val();
			studentId.push(stVal)
		}
		
		var teamId = $("#teamId").val();
		var url = "${pageContext.request.contextPath}/teach/placement/addPlacementStudent?studentId="+studentId+"&teamId="+teamId;
		loader.show();
		$.post(url, {}, function(data, status) {
			if("success" === status) {
// 				data = eval("(" + data + ")");
				if("success" === data) {
					$.success("保存成功");
					if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
					$.closeWindow();
				} else {
					$.error("保存失败");
				}
			}else{
				$.error("服务器异常");
			}
			loader.close();
		});
	}
 </script>
</body>
</html>
