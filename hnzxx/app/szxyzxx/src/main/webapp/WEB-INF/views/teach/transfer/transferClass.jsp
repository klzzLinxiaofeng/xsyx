<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
			<jsp:param value="新生调班" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12"  style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							<span>新生调班</span>
							<p style="float:right;" class="btn_link">
								<a class="a5" href="javascript:void(0)" onclick="saveOrUpdate();">保存</a>
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
				</div>
				<div class="bottom">
					<div class="open"><a href="javascript:void(0)" class="school">${schoolName }</a></div>
					<div class="bj_rs">
						<ul class="ban_ul">
						<input type="hidden" id="teamList" <c:if test="${empty teamList }">value="true"</c:if>>
							<c:choose>
								<c:when test="${!empty teamList}">
									<c:forEach items="${teamList}" var="team" varStatus="s">
										<li><a <c:if test="${s.index==0}">class='on'</c:if>
											data_obj="${team.id}" href="javascript:void(0)"
											onclick="getCurrentTeam('${team.fullName }','${team.id}');">${team.fullName }</a></li>
									</c:forEach>
								</c:when>
								<c:otherwise>

								</c:otherwise>
							</c:choose>
						</ul>
<!-- 						<li class="li_div open_2">
							<a href="javascript:void(0)" class="nj">初一</a>
							
						</li> -->
					</div>
				</div>
			</div>
			<div class="fenban_right">
				<form id="transferClassForm0" name="transferClassForm0" action="javascript:void(0);" style="margin:0">
				<div class="xs_div">
					<div class="top"><p id="currentTeam">当前班级：初一（1）班</p><p id="allStuNum">班级人数：0</p></div>
					<input type="hidden" id="teamId0" name="teamId0" value="" />
					<div class="xs_list">
						<table class="table table-bordered responsive  white">
							<thead>
								<tr>
									<th>男生比例</th>
									<th>女生比例</th>
<!-- 									<th>成绩平均分</th> -->
								</tr>
								<tr>
									<th>
										<span class="nan">男</span>：<span id="manNum">0人</span>
										<span class="nv">比列</span>：<span id="manPercent">0%</span>
									</th>
									<th>
										<span class="nan">女</span>：<span id="wonNum">0人</span>
										<span class="nv">比列</span>：<span id="wonPercent">0%</span>
									</th>
<!-- 									<th style="position:relative"> -->
<!-- 										<div class="fs_div">语文:80  数学:80  英语:80 科学:80  历史:80  地理:80</div><a href="javascript:void(0)" style="position:absolute" class="op">展开</a>  -->
<!-- 									</th> -->
								</tr>
							</thead>
						</table>
						<div class="xuesheng">
						<table class="table table-bordered responsive white">
							<thead>
								<tr>
									<th style="width:34px;"><input type="checkbox" id="checkboxOut" name="checkboxOut" onclick="checkAll(0);"></th>
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
				 <div class="hj_hc">
					<a href="javascript:void(0)" onclick="changeOut();">换出  &gt;</a>
					<a href="javascript:void(0)" onclick="changeIn();">换进  &lt;</a>
				</div>
				<form id="transferClassForm1" name="transferClassForm1" action="javascript:void(0);" style="margin:0"> 
				<div class="xs_div">
					<div class="top">
						<p>调往班级：
						<select style="margin-bottom:0" id="teamId1" name="teamId1" onchange="getCurrentStudentInfo(this.value);">
<!-- 							   <option value="1">一</option> -->
<!-- 							   <option value="2">二</option> -->
<!-- 							   <option value="3">三</option> -->
<!-- 						   <c:forEach items="${teamList}" var="team_" varStatus="s"> -->
<!-- 							 <option value="${team_.id}">${team_.fullName }</option> -->
<!-- 						   </c:forEach> -->
						</select>
						</p>
						<p id="allStuNum1">班级人数：0</p>
					</div>
					<div class="xs_list">
						<table class="table table-bordered responsive  white">
							<thead>
								<tr>
									<th>男生比例</th>
									<th>女生比例</th>
<!-- 									<th>成绩平均分</th> -->
								</tr>
								<tr>
									<th>
										<span class="nan">男</span>：<span id="manNum1">0人</span>
										<span class="nv">比例</span>：<span id="manPercent1">0%</span>
									</th>
									<th>
										<span class="nan">女</span>：<span id="wonNum1">0人</span>
										<span class="nv">比例</span>：<span id="wonPercent1">0%</span>
									</th>
<!-- 									<th style="position:relative"> -->
<!-- 										<div class="fs_div">语文:80  数学:80  英语:80 科学:80  历史:80  地理:80</div><a href="javascript:void(0)" style="position:absolute" class="op">展开</a>  -->
<!-- 									</th> -->
								</tr>
							</thead>
						</table>
						<div class="xuesheng">
						<table class="table table-bordered responsive white">
							<thead>
								<tr><th style="width:34px;"><input type="checkbox" id="checkboxIn" name="checkboxIn" onclick="checkAll(1);"></th>
								<th style="width:34px;">序号</th>
								<th style="width:62px;">姓名</th>
								<th style="width:34px;">性别</th>
								<th>学籍号</th>
							</tr>
							</thead>
							<tbody id="tbody1">

							</tbody>
						</table>
						</div>
					</div>
				</div>
			    </form>
				<div class="clear"></div>
			</div>
		</div>
		<div id="team1"></div>
		<div id="team2"></div>
	</div>
	<script>
	$(function(){
		$(".fenban_left .bottom .bj_rs .ban_ul li a").click(function(){
			$(".fenban_left .bottom .bj_rs .ban_ul li a").removeClass("on");
			$(this).addClass("on");
		});
		$(".fenban_left .bottom .bj_rs ul").on("click",".open_2 .nj",function(event){
			event.stopPropagation();
			$(this).next().hide();
			$(this).parent().removeClass("open_2").addClass("close_2")
		});
		$(".fenban_left .bottom .bj_rs ul").on("click",".close_2 .nj",function(event){
			event.stopPropagation();
			$(this).next().show();
			$(this).parent().removeClass("close_2").addClass("open_2")
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
			$("#teamId0").val(teamId);
			getCurrentTeam(teamName,teamId);
		}
		
	});
	
	function getCurrentTeam(teamName,id){
		cleanAllDIvAndSpan();
		$("#currentTeam").html("当前班级: "+teamName);
		$("#teamId0").val(id);
		//加载学生信息
		var url = "${pageContext.request.contextPath}/teach/transfer/currentTeamStudent";
		var aj = $.ajax({
			url : url,
			data : "teamId=" + id,
			type : "post",
			cache : false,
			dataType : "json",
			success : function(data) {
				loadSudentTable(data);
			},
			error : function() {
				//$.alert("异常1！");
			}
		});
		
		//加载调往班级列表
		var urlTemp = "${pageContext.request.contextPath}/teach/transfer/transferToClassTeamList";
		var ajX = $.ajax({
			url : urlTemp,
			data : "teamId=" + id,
			type : "post",
			cache : false,
			dataType : "json",
			success : function(data) {
				loadTransferClassTable(data);
			},
			error : function() {
				$.alert("异常！");
			}
		});
	}
	
	function loadTransferClassTable(data){
		var teamTemp = "";
		for(var i=0;i<data.length;i++){
			teamTemp+="<option value="+data[i].id+">"+data[i].fullName+"</option>";
		}
		$("#teamId1").html(teamTemp);
		//alert(teamTemp);
		var teamId1 = $("#teamId1").val();
 		getCurrentStudentInfo(teamId1);
	}
	
	function loadSudentTable(data){
		var dataList = data.studentVoList;
		var trTemp = "";
		for(var i=0;i<dataList.length;i++){
			var tt = i+1;
			var studentNumber = "";
			if(dataList[i].studentNumber == null){
				studentNumber = ""
			}
			trTemp+="<tr><td><input type='checkbox' id='studentCheckbox' name='studentCheckbox' value="+dataList[i].id+"></td><td>"+tt+"</td><td>"+dataList[i].name+"</td><td class='nan'>"+dataList[i].sex+"</td><td>"+studentNumber+"</td></tr>";
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
		$("#allStuNum").html("当前班级总人数:"+allStuNum);
		$("#tbody0").html(trTemp);
	}
	
	function getCurrentStudentInfo(teamId){
		//alert("==="+teamId);
		//加载学生信息
		cleanAllDIvAndSpan();
		var url = "${pageContext.request.contextPath}/teach/transfer/currentTeamStudent";
		var aj = $.ajax({
			url : url,
			data : "teamId=" + teamId,
			type : "post",
			cache : false,
			dataType : "json",
			success : function(data) {
				loadSudentTable_(data);
			},
			error : function() {
				//$.alert("异常！");
			}
		});
	}
	function loadSudentTable_(data){
		var dataList = data.studentVoList;
		var trTemp = "";
		for(var i=0;i<dataList.length;i++){
			var tt = i+1;
			var studentNumber = "";
			if(dataList[i].studentNumber == null){
				studentNumber = ""
			}
			trTemp+="<tr><td><input type='checkbox' id='studentCheckbox' name='studentCheckbox' value="+dataList[i].id+"></td><td>"+tt+"</td><td>"+dataList[i].name+"</td><td class='nan'>"+dataList[i].sex+"</td><td>"+studentNumber+"</td></tr>";
		}
		var allStuNum = data.allStuNum;
		var manNum = data.manNum;
		var wonNum = data.wonNum;
		var manPercent = data.manPercent;
		var wonPercent = data.wonPercent;
		$("#wonPercent1").html(wonPercent);
		$("#manPercent1").html(manPercent);
		$("#wonNum1").html(wonNum+"人");
		$("#manNum1").html(manNum+"人");
		$("#allStuNum1").html("当前班级总人数:"+allStuNum);
		$("#tbody1").html(trTemp);
	}
	
	//全选
	function checkAll(obj){
		if(obj==0){
			if($("#checkboxOut").prop("checked")==true){
				$("#tbody0 :checkbox").prop("checked", true);
			}else{
				$("#tbody0 :checkbox").prop("checked", false);
			}
		}else{
			if($("#checkboxIn").prop("checked")==true){
				$("#tbody1 :checkbox").prop("checked", true);
			}else{
				$("#tbody1 :checkbox").prop("checked", false);
			}
		}
		
	}
	
	//换出
	function changeOut(){
		var outTemp ="";
		$('#tbody0 input[name="studentCheckbox"]:checked').each(function(){
            var sfruit_=$(this).parent().parent();
            outTemp+="<tr>"+sfruit_.html()+"</tr>";
            removeOrAdd($(this).val(),"out");
        }); 
		
		 $("#tbody1").append(outTemp);
		
		 $('#tbody0 input[name="studentCheckbox"]:checked').each(function(){
			 $(this).closest('tr').remove();
	    }); 
		
	}
	
	//换进
	function changeIn(){
		var inTemp="";
		$('#tbody1 input[name="studentCheckbox"]:checked').each(function(){
            var sfruit=$(this).parent().parent();
            inTemp+="<tr>"+sfruit.html()+"</tr>";
            removeOrAdd($(this).val(),"in");
        });
		
		 $("#tbody0").append(inTemp);
		 
		 $('#tbody1 input[name="studentCheckbox"]:checked').each(function(){
			 $(this).closest('tr').remove();
	    });
	}
	
	//增加一个span标签
	function addSpan(val,type){
		var classHtml1 = "<div class='team1'><span id='"+ val +"' data-id='"+val+"'></span></div>";
		var classHtml2 = "<div class='team2'><span id='"+ val +"' data-id='"+val+"'></span></div>";
		if(type == "in"){
			$("#team1").append(classHtml1);
		}else{
			$("#team2").append(classHtml2);
		}
	}
	
	//移除一个span标签
	function removeSpan(val){
		$("#"+val).parent().remove();
	}
	
	//判断是移除还是添加操作
	function removeOrAdd(val,type){
		var flag = false;
		var id=0;
		var spanId = $(".team1");
		
		if(type == "in"){
			spanId = $(".team2");
		}
		
		$.each(spanId, function(index, value) {
			id = $(value).find("span").attr("data-id");
			if(id == val){
				flag = true;
			}
		});
		
		if(flag){
			removeSpan(val);
		}else{
			addSpan(val,type);
		}
	}
	
	//获取变动的学生ID
	function getIds(classId){
		var ids = "";
		$.each($("."+classId), function(index, value) {
			ids += ($(value).find("span").attr("data-id") + ",");
		});
		
		if (ids != "" && ids != "undefined") {
			ids = ids.substring(0, ids.length-1);
		}
		return ids;
	}
	
	//当换班级的时候，将上次的调换记录清空
	function cleanAllDIvAndSpan(){
		$("#team1").html("");
		$("#team2").html("");
	}
	
	//保存更新
	function saveOrUpdate(){
		var loader = new loadLayer();
		var id1 = getIds("team1");
		var id2 = getIds("team2");
		
// 		alert("班级"+$("#teamId0").val()+"的学生Id是："+id1 +"=====班级"+$("#teamId1").val()+"的学生Id是："+id2);
		//获取完数据的时候，清空一次span 防止再点击一次保存  再存一次上次的数据
		cleanAllDIvAndSpan();
// 		return;
// 		alert("如果能输出这句，请关掉程序，否则数据保存将错乱！！！");
		
		var teamId0 = $("#teamId0").val();
		var teamId1 = $("#teamId1").val();
		var url = "${pageContext.request.contextPath}/teach/transfer/saveOrUpdateStudentInfo?studentId0="+id1+"&teamId0="+teamId0+"&studentId1="+id2+"&teamId1="+teamId1;
		
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
