<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.table input{
	margin:0;
	height:30px;
	line-height:30px;
	width:70px;
}
.table p{
	margin:0;
	font-size:12px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<!-- <form class="form-horizontal tan_form" id="performanceevaluationscore_form" action="javascript:void(0);">
							
					</form> -->
					<div class="kaohe">
					
					
						<div class="kh_left">
							<p>名单</p>
							<ul id="per">
							<input id="personIdTemp" type="hidden" value="">
							<c:forEach items="${personnelList}"  var="personnel">
								<li><a href="javascript:void(0)" onclick="checkScore('${personnel.taskId}', '${personnel.personnelId}');"><dota:fieldVal type="teacher" code="${personnel.personnelId }"></dota:fieldVal></a></li>
							</c:forEach>
							</ul>
						</div>
						<div class="kh_right">
							<table class="table table-bordered" id="table1">
								<thead><tr><th>考核项目</th><th>分数</th><th>批注</th></tr></thead>
								<tbody id="mytbody">
								<c:forEach items="${taskItemList}"  var="taskItem">
									<tr class="taskItem" id="${taskItem.id }" id="trid">
										<td><input id="taskId" type="hidden" value="${taskItem.taskId }">
											<input id="scoreId" type="hidden" value="0"><p>${taskItem.itemName} （${taskItem.startScore }-${taskItem.endScore }）</p><p>说明：${taskItem.description }</p></td>
										<td class="score"><input type="hidden" value="${taskItem.startScore };${taskItem.endScore }"><input id="score" type="text" value=""></td>
										<td><input id="remark" type="text" style="width:270px;"> </td>
									</tr>
								</c:forEach>
<!-- 									<tr><td><p>团队精神（0-10）</p><p>说明：明白什么是团队精神</p></td><td><input type="text"></td><td><input type="text" style="width:250px;"></td></tr> -->
								</tbody>
							</table>
						</div>
						<div class="clear"></div>
					</div>
					<div class="form-actions tan_bottom"
							style="padding-left: 0; background-color: #eee; text-align: center">
							<c:if test="${isCK == null || isCk == ''}">
								<button class="btn btn-warning" type="button" onclick="exit();">保存</button>
							</c:if>
						</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
		$("#per li:first a").attr("class","on");
		$("#per li:first a").click();
	});
	
	$(".score input").blur(function(){
		var score = parseInt($(this).val());
		var cur_td = $(this).parent();
		var score_range = cur_td.children().eq(0).val();
 		var score_range_arr = score_range.split(";");
		var start_score = parseInt(score_range_arr[0]);
		var end_score = parseInt(score_range_arr[1]);
		if(score<start_score||score>end_score) {
			$.alert("请输入("+start_score+"-"+end_score+")的分数");
			$(this).val("");
		}
		
		var reg = new RegExp("^[0-9]*$");
		if(!reg.test(score)){  
	        $.alert("请输入数字!");
	        $(this).val("");
	    } 
		
	});
	
	function initValidator() {
		return $("#performanceevaluationscore_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	function checkScore(taskId, evaluatedId) {
		var evaluatedIdTemp = $("#personIdTemp").val();
		if(evaluatedIdTemp != null && evaluatedIdTemp != ""){
			saveOrUpdate(evaluatedIdTemp);
		}
		
		$.each($(".taskItem"),function(index,value){
						$(this).find("#score").val("");
						$(this).find("#remark").val("");
						$(this).find("#scoreId").val(0);
				});
		$.post("${ctp}/personnel/performanceEvaluationScore/checkScore",{"_method" : "post","taskId": taskId, "evaluatedId": evaluatedId}, function(data, status) {
			if("success" === status) {
				data = eval ("(" + data +")");
				if(data.length > 0){
					for(var i = 0; i < data.length; i++){
						$.each($(".taskItem"),function(index,value){
							var itemId = data[i].taskItemId+"";
							var tr_id = $(value).attr("id");
								if(itemId === tr_id){
									$(this).find("#score").val(data[i].score);
	 								$(this).find("#remark").val(data[i].remark);
	 								$(this).find("#scoreId").val(data[i].id);
								}
						});
					}
				}
			}
		});
		
		$("#personIdTemp").val(evaluatedId);
	}
	
	function saveOrUpdate(evaluatedIdTemp) {
		
		var jsons = [];
		$(".taskItem").each(function(index, value){
			var json = {};
			json.evaluated = evaluatedIdTemp;
			json.taskItemId = $(value).attr("id");
			json.taskId = $(this).find("#taskId").val();;
			json.scoreId = $(this).find("#scoreId").val();
			json.score = $(this).find("#score").val();
			json.remark = $(this).find("#remark").val();
			jsons.push(json);
		});
		jsons = JSON.stringify(jsons);
		$.post( "${ctp}/personnel/performanceEvaluationScore/saveScore", {"_method" : "post","taskItems": jsons}, function(data, status){});
	}
	
	function exit(){
		$.confirm("确定保存并退出？", function() {
			var evaluatedIdTemp = $("#personIdTemp").val();
			saveOrUpdate(evaluatedIdTemp);
			if(parent.core_iframe != null) {
					parent.core_iframe.window.location.reload();
				} else {
					parent.window.location.reload();
				}
			$.closeWindow();
		});
	}
	
	$.ajaxSetup ({
     	async: false
    });
	
	$(function(){
		$(".kaohe .kh_left ul li a").click(function(){
			$(".kaohe .kh_left ul li a").removeClass("on");
			$(this).addClass("on");
			//$("kh_right tbody").remove('1')
			
// 			$("kh_right tbody").html('<tr><td><p>团队精神（0-10）</p><p>说明：明白什么是团队精神</p></td><td><input type="text"></td><td><input type="text" style="width:250px;"></td></tr>')
		});
	});
	
	
</script>
</html>