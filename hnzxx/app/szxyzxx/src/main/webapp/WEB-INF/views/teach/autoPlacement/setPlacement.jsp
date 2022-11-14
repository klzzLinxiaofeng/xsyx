<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>分班设置</title>
<style>
	.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}
</style>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
		$("#stageCode").trigger("change");
	});
	function getStageName(){
		var stage_ =$("#stageCode").val();
		var noClassStuNum = $("#noClassStuNum").val();
		var url = "${pageContext.request.contextPath}/teach/autoPlacement/getGradeListByStageCode";
		var aj = $.ajax({
			url : url,
			data : 'stage_=' + stage_+'&noClassStuNum='+noClassStuNum,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				var gradeId = $("#gradeId");
				var perTeamNum = $("#perTeamNum");
				var lastNum = $("#lastNum");
				if(data.fullName==""){
					$.alert("该学段下没有年级，请添加年级");
					gradeId.html("");
				}else{
					var opt = "<option value='"+data.id+"'>"+data.fullName+"</option>";
					gradeId.html(opt);
				}
				var teamNum = $("#teamNum");
				if(data.teamCount==0){
// 					$.alert("该年级没有班级！请先添加班级！");
				}
				teamNum.val(data.teamCount);
				perTeamNum.val(data.perTeamNum);
				lastNum.val(data.lastNum);
			},
			error : function() {
				$.alert("请先添加年级！");
			}
		});
	}
//	实例化对象
//	 显示
//	 关闭
	function saveOrUpdate(){
		var noClassStuNum = $("#noClassStuNum").val();
		var loader = new loadLayer();
		var teamNum = $("#teamNum").val();
// 		$("#saveButton").attr("disabled",true);
		if (checker.form()) {
			if(teamNum=="0" || teamNum==0){
				$.alert("当前列表没有学生数据，无法分班");
				return;
			}
			var $requestData = formData2JSONObj("#autoPlacementform");
// 			var loader = new loadDialog(); 
	    	var url = "${pageContext.request.contextPath}/teach/autoPlacement/saveAutoPlavement1";
	    	loader.show();
	    	$.post(url, $requestData, function(data, status) {
	    		if("success" === status) {
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
	}
	
	 function initValidator() {
			return $("#autoPlacementform")
					.validate(
							{
								errorClass : "myerror",
								rules : {
									"gradeId" : {
										required : true
									},
									"teamNum" : {
										required : true
									},
									"perTeamNum":{
										required : true
									},
									"lastNum":{
										required : true
									}
								},
								messages : {
									"gradeId" : {
										required:"年级为必选项"
									},
									"teamNum":{
										required : "班级数量必填",
									},
									"perTeamNum":{
										required:"每个班人数必填"
									},
									"lastNum":{
										required : "剩余人数必填"
									}
								}
							});
		}
</script>
<style>
	.row-fluid .span4 {
	width: 227px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal" id="autoPlacementform" name="autoPlacementform">
						<input type="hidden" name="noClassStuNum" id="noClassStuNum" value="${noClassStuNum }"/>
						<input type="hidden" name="manNum" id="manNum" value="${manNum }"/>
						<input type="hidden" name="womNum" id="womNum" value="${womNum }"/>
						
						<input type="hidden" name="manPercentNot" id="manPercentNot" value="${manPercentNot }"/>
						<input type="hidden" name="wonPercentNot" id="wonPercentNot" value="${wonPercentNot }"/>
						
						<div class="control-group">
							<label class="control-label">学段:</label>
							<div class="controls">
<!-- 							<input type="text" id="" name="" class="span4" placeholder="学段, 不能为空" > -->
								<select id="stageCode" id="stageCode" name="stageCode" onchange="getStageName();" class="span4">
		                           	<c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="stage">
		                           		<c:if test="${stage != -1}">
			                           		<option value="${stage}">
			                           			<jc:cache tableName="jc_stage" echoField="name" value="${stage}" paramName="code"></jc:cache>
			                           		</option>
		                           		</c:if>
		                           	</c:forEach>
	                           	</select>
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">年级:</label>
							<div class="controls">
<!-- 								<input type="text" id="" name="" class="span4" placeholder="年级, 不能为空" > -->
								<select id="gradeId" name="gradeId" class="span4"></select>
                            </div>
						</div>
						<div class="control-group">
							<label class="control-label">班级个数</label>
							<div class="controls">
								<input type="text" id="teamNum" name="teamNum" readonly="readonly" class="span4" placeholder="班级个数, 不能为空">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">每班人数</label>
							<div class="controls">
								<input type="text" id="perTeamNum" name="perTeamNum" readonly="readonly" class="span4" placeholder="每班人数, 不能为空">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">剩余人数</label>
							<div class="controls">
								<input type="text" id="lastNum" name="lastNum" readonly="readonly" class="span4" placeholder="剩余人数" >
							</div>
						</div>
						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate();" id="saveButton">确定</button>
<!-- 								<button class="btn btn" type="button" onclick="saveOrUpdate(this);">取消</button> -->
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>