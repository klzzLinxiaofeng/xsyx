<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<html>
<head>
<title></title>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 227px;
}

.row-fluid .span5 {
	width: 30px;
	margin-right: 3px;
}  
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
				   <input type="hidden" value="${dormitoryDaycheck.id }" id="id" name="id"/>
				   <input type="hidden" value="${dormitoryDaycheck.dormitoryCode}" id="dDormitoryCode"/>
					<form class="form-horizontal tan_form" id="dormitorydaycheck_form" action="javascript:void(0);">
							<div class="control-group" hidden>
								<label class="control-label">
									<span class="red">*</span>学年 
								</label>
								<div class="controls">
								<select id="xn" name="schoolYearId" style="width: 227px;"  >
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>年级 
								</label>
								<div class="controls">
								<select id="nj" name="gradeId" style="width:227px;"  >
								</select>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>班级 
								</label>
								<div class="controls">
								<select id="bj" name="teamNumber" style="width: 227px;"  >
								</select>
								</div>
							</div>
							
							 <div class="control-group">
								<label class="control-label">
									<span class="red">*</span>日期
								</label>
								<div class="controls">
								<input type="text" id="createDate" name="createDate" class="span4" placeholder="请输入时间" value="<fmt:formatDate pattern="yyyy-MM-dd "
															value='${dormitoryDaycheck.createDate }'></fmt:formatDate>"  onclick="WdatePicker();" >
								</div>
							</div> 
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>检查类型
								</label>
								
							  <div class="controls">
								<select id="checkType" name="checkType" class="span4 chzn-select"  onchange="getCheckType();" >
								</select>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>宿舍楼号
								</label>
								<div class="controls">
								<select id="floorCode" name="floorCode" class="span4 chzn-select" onchange="getFloorCode();"  >
								   <option value="">请选择</option>
								 <c:forEach items="${dormitoryList}" var="dormitory">
								   <option value="${dormitory.floorCode}" <c:if test="${dormitory.floorCode==dormitoryDaycheck.floorCode }">selected</c:if>>${dormitory.floorName}</option>
								 </c:forEach>
								</select>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>寝室编号
								</label>
								<div class="controls"  id="dCode">
								    <select id="dormitoryCode" name="dormitoryCode" class="span4 chzn-select"  onchange="getDormitoryCode(floorCode);">
								       <option value="">请选择</option>
								    </select>
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									<span class="red">*</span>检查结果
								</label>
								<div class="controls">
								<input type="text" id="checkResult" name="checkResult" class="span4" placeholder="例如扣分或加分加上正负号" value="${dormitoryDaycheck.checkResult}" >
								</div>
							</div>
							
							
							<div class="control-group">
								<label class="control-label">
									检查说明
								</label>
								<div class="controls">
								    <textarea rows="3" cols="5" id="remark" name="remark" class="span4" >${dormitoryDaycheck.remark}</textarea>
								</div>
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${dormitoryDaycheck.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">保存</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	
	$(function() {
		$.jcGcSelector("#checkType", {"tc" : "XY-YH-RCJCLX"},"${dormitoryDaycheck.checkType}");
		$.initCascadeSelector({
			"type" : "team",
			"yearSelectId" : "xn", //学年select标签ID 默认为xn
			"gradeSelectId" : "nj", //年级select标签ID 默认为nj
			"teamSelectId" : "bj",  //班级select标签ID 默认为bj
 			"isEchoSelected" : true,
 			"yearSelectedVal" : "${sy.year}", //要回显的学年唯一标识
			"gradeSelectedVal" : "${dormitoryDaycheck.gradeId}", //要回显的年级唯一标识
			"teamSelectedVal" : "${dormitoryDaycheck.teamNumber}", //要回显的班级唯一标识
		});
		var floorCode = $('#floorCode option:selected').val();
		getDormitoryCode(floorCode);
			checker = initValidator();
	
	});
	
	function initValidator() {
		return $("#dormitorydaycheck_form").validate({
			errorClass : "myerror",
			rules : {
				"scholYearId":{
					required:true
				},
				"gradeId":{
					required:true
				},
				"teamNumber":{
					required:true
				},
				 "createDate":{
					required:true
				}, 
				"checkType":{
					required:true
				},
				"floorCode":{
					required:true
				},
				"dormitoryCode":{
					required:true
				},
				"checkResult":{
					required:true
				}
			},
			messages : {
				"scholYearId":{
					required:"学年必选"
				},
				"gradeId":{
					required:"年级必选"
				},
				"teamNumber":{
					required:"班级必选"
				},
				 "createDate":{
					required:"日期必填"
				}, 
				"checkType":{
					required:"检查类型必选"
				},
				"floorCode":{
					required:"宿舍楼号必选"
				},
				"dormitoryCode":{
					required:"寝室编号必选"
				},
				"checkResult":{
					required:"检查结果必填"
				}
			}
		});
	}
	

	
	function getFloorCode(){
		var obj = document.getElementById("floorCode"); //selectId
		var index = obj.selectedIndex; //选中索引
		var floorCode = obj.options[index].value; //选中文本
		$("#dormitoryCode").get(0).options.length=0;
		getDormitoryCode(floorCode);
		}

	function getCheckType(){
		var obj = document.getElementById("checkType"); //selectId
		var index = obj.selectedIndex; //选中索引
		var checkType = obj.options[index].value; //选中文本
		}

	function getDormitoryCode(floorCode){
		var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryCodeListAjax/getDormitoryCodeList";
				$.ajax({
					url : url,
					data : 'floorCode=' + floorCode,
					type : 'post',  
					cache : false,
					dataType : 'json',
					success : function(data) {
						if(data.length!=0){
							for(var i=0;i<data.length;i++){
								if(data[i].floorCode == floorCode){
									var  dDormitoryCode =$("#dDormitoryCode").val() ;
									if(data[i].dormitoryCode==dDormitoryCode){
										$("#dormitoryCode").append("<option value="+data[i].dormitoryCode+" selected>"+data[i].dormitoryCode+"</option>");
									}else{
										
										$("#dormitoryCode").append("<option value="+data[i].dormitoryCode+">"+data[i].dormitoryCode+"</option>");
									}
								}
							}
							
						}
						   }
						});
				}


//保存或更新修改
function saveOrUpdate() {
	if (checker.form()) {
		var loader = new loadLayer();
		var $requestData = formData2JSONObj("#dormitorydaycheck_form");
		var url = "${pageContext.request.contextPath}/schoolaffair/dormitorydaycheck/saveEditor";
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
					$.closeWindow();
				} else {
					$.error("操作失败");
				}
			}else{
				$.error("操作失败");
			}
			loader.close();
		});
	}
}
</script>
</html>