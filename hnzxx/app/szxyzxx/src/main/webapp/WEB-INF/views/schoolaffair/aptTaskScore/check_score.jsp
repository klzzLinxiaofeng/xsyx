<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>考核列表</title>
<style>
.check_ordinal{
    width: 260px;
    margin-top: 10px;
    margin-right: 50px;
    margin-bottom: 30px;
    float: left;
}
.check_ordinal thead tr th{
    background:#f5f5f5;
    height: 35px;
    border:1px solid #ddd;
    line-height: 35px;
    text-align: left;
    padding-left: 10px;
}
.check_order tr{
    height: 30px;
    line-height: 25px;
}
tbody.check_order tr td{
    padding-left: 10px;
    border:1px solid #ddd;
    height: 25px;
    line-height: 25px;
}
tbody.check_order tr td input{
    width:55%;
    /*height: 10px;*/
    border-radius: 5px;
    background: #f8f8f8;
    margin:0;
}
</style>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="进行考核--日常考核" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							进行考核--日常考核列表
							<p class="btn_link" style="float: right;">
							<button class="btn btn-green" onclick="returnList();">返回</button>
							</p>
						</h3>
					</div>
					<jsp:useBean id="now" class="java.util.Date"/>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>日期：</span> <input type="text" id="checkTime" 
										value="<fmt:formatDate value="${empty checkTime ? (now > aptTask.finishDate ? aptTask.finishDate : now): checkTime}" pattern="yyyy-MM-dd"/>" 
										style="width:120px;height:15px;" 
										onclick="WdatePicker({onpicked:function(dp){onTimeChange();},minDate:'#F{$dp.$D(\'startDateValue\')}',maxDate:'#F{$dp.$D(\'finishDateValue\')}'});">
									<input type="hidden" id="startDateValue" name="startDateValue" value="<fmt:formatDate value="${aptTask.startDate}" pattern="yyyy-MM-dd"/>"/>
									<input type="hidden" id="finishDateValue" name="finishDateValue" value="<fmt:formatDate value="${now > aptTask.finishDate ? aptTask.finishDate : now}" pattern="yyyy-MM-dd"/>"/>
								</div>

								<div class="select_div">
									<span>分类：</span>
									<select id="category" name="category" class="chzn-select" style="width: 200px;margin:0;">
										<c:forEach items="${categorys}" var="ctg">
											<c:if test="${ctg != null}">
												<option <c:if test="${ctg == category}">selected="selected"</c:if> value="${ctg}">${ctg}</option>
											</c:if>
										</c:forEach>
									</select>
								</div>

								<div class="select_div">
									<span>名称：</span>
									<select id="name" name="name" class="chzn-select" style="width: 200px;margin:0;">
										<c:forEach items="${taskItems}" var="item">
											<option <c:if test="${item.id == taskItem.id}">selected="selected"</c:if> value="${item.id}">${item.name}</option>
										</c:forEach>
									</select>
								</div>
								<c:if test="${edit}">
									<div class="select_div" style="float: right;">
										<span style="color: blue;">给全体人员打分：</span>
										<input type="text" id="batchScore" style="width:70px;height:15px;border-radius: 5px;background: #f8f8f8;">
										<button type="button" class="btn btn-primary" style="margin-top: -10px;" onclick="batchScore();">打分</button>
									</div>
								</c:if>
								<div class="clear"></div>
							</div>
							<div class="table_list" id="apt_check_list" style="overflow-y:auto;">
								<jsp:include page="./check_list.jsp"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function search(){
			var val = {};
			var taskItemId = $("#name").val();
			if (taskItemId != null && taskItemId != "") {
				val.taskItemId = taskItemId;
			}
			var checkTime = $("#checkTime").val();
			if(checkTime != null && checkTime != ""){
				val.checkTime = checkTime;
			}
			var category = $("#category").val();
			var taskId = "${aptTask.id}";
			val.id = taskId;
			var url = "${ctp}/schoolAffair/aptTaskScore/checkEdit?sub=list&edit=${edit}&autoSearch=false&category=" + category;
			$.success("正在加载中，请耐心等待。。。");
			url = "${ctp}/schoolAffair/aptTaskScore/checkEdit";
			// location.href = url + "&taskItemId=" + taskItemId  + "&checkTime=" + checkTime + "&id=" + taskId;
			$.post(url,
					{
						"sub":"list",
						"edit":"${edit}",
						"autoSearch":false,
						"category":category,
						"taskItemId":taskItemId,
						"checkTime":checkTime,
						"id":taskId
					},
					function(data,status){
						$("#apt_check_list").html(data);
					});
			
	}
	
	function onTimeChange(){
		search();
	}
	
	$(function(){
		categoryChange();
		nameChange();
		initName($("#category").val()); 
	}); 
	
	function categoryChange(){
		$("#category").change(function(){
			var category = $(this).val();
			initName(category);
		});
	}
	
	function nameChange(){
		$("#name").change(function(){
			search();
		});
	}
	
	function initName(category){
		var taskId = "${aptTask.id}";
		var url = "${ctp}/schoolAffair/aptTaskScore/nameJson";
		$.post(url,{"category":category,"aptTaskId":taskId},function(data,status){
			if("success" === status){
				data = eval("(" + data + ")");
				$("#name").empty();
				var taskItemId = "${taskItem.id}"; 
				$.each(data,function(index,value){
					if(value.id == taskItemId){
						$("#name").append('<option selected="selected" value="' + value.id + '">' + value.name +'</option>');
					}else{
						$("#name").append('<option value="' + value.id + '">' + value.name +'</option>');	
					}
				});
				$("#name").trigger("liszt:updated");
				search();
				/* if('${autoSearch}' == "true"){
					search();
				} */
			}
		});
	}
	
	function returnList(){
		location.href = "${ctp}/schoolAffair/aptTaskScore/index";
	}
	
	function batchScore(){
		var val = {};
		var taskItemId = $("#name").val();
		if (taskItemId != null && taskItemId != "") {
			val.taskItemId = taskItemId;
		}
		var checkTime = $("#checkTime").val();
		if(checkTime != null && checkTime != ""){
			val.checkTime = checkTime;
		}
		var taskId = "${aptTask.id}";
		val.taskId = taskId;
		var score = $("#batchScore").val();
		if(score == "" || score == "undefined" || score == null){
			$.alert("请输入正确的分数，再打分");
			return;
		}else if(isDouble(score)){
			$.alert("请输入正确的分数，再打分");
			return;
		}else{
			val.score = score;
		}
		var taskItemScore = "${taskItem.score}";
		if(parseFloat(score) > parseFloat(taskItemScore)){
			$.alert("您填写的分数超过了该项的分值范围");
			$("#batchScore").val("");
			return;
		}
		var url = "${ctp}/schoolAffair/aptTaskScore/batchCreator";
		var loader = new loadDialog();
		loader.show();
		$.post(url,val,function(data,status){
			if("success" === status){
				$.success("打分成功");
				location.reload();
			}else{
				$.error("传入的参数有误");
			}
			loader.close();
		});
	}

</script>
</html>