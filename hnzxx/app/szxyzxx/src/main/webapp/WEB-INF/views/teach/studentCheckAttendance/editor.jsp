<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改信息</title>
<style>
.row-fluid .span13 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}

.row-fluid .span4 {
	width: 227px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="row-fluid">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal" id="modifyAttendance_form" action="javascript:void(0);">
				    <input type="hidden" id="schoolId" name="schoolId"
						value="${schoolId}" />
	<!-- 						   考勤类别 ：0==事假 ; 1== 病假 ; 2== 缺课 ; 3== 旷课 ;  4== 迟到 ;  5== 早退 -->
	                      <div class="control-group">
	                        <label class="control-label">考勤类型</label>
	                         <div class="controls">
							   <select id="kq" name="attendanceType" class="chzn-select" style="width:227px;" onchange="getKq();">
							           
							            <option value="0"  <c:if test="${studentCheckAttendance.attendanceType==0}">selected</c:if>>事假</option>
										<option value="1"  <c:if test="${studentCheckAttendance.attendanceType==1}">selected</c:if>>病假</option>
										<option value="2"  <c:if test="${studentCheckAttendance.attendanceType==2}">selected</c:if>>缺课</option>
										<option value="3"  <c:if test="${studentCheckAttendance.attendanceType==3}">selected</c:if>>旷课</option>
										<option value="4"  <c:if test="${studentCheckAttendance.attendanceType==4}">selected</c:if>>迟到</option>
										<option value="5"  <c:if test="${studentCheckAttendance.attendanceType==5}">selected</c:if>>早退</option>  
							   </select>
						     </div>
						 </div>
						 
						 
						 <div class="control-group">
						   <label class="control-label">开始时间 </label>
						      <div class="controls">
							     <input type="text" id="beginDate" name="beginDate"
										class="span4" placeholder="<fmt:formatDate pattern="yyyy-MM-dd" value="${studentCheckAttendance.beginDate}"  ></fmt:formatDate>"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" />
							</div>
						 </div>
						 
						 <div class="control-group">
						   <label class="control-label">结束时间 </label>
						      <div class="controls">
							     <input type="text" id="endDate" name="endDate"
										class="span4" placeholder="<fmt:formatDate pattern="yyyy-MM-dd" value="${studentCheckAttendance.endDate}"  ></fmt:formatDate>"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}'})" />
							</div>
						 </div>
						 
						 <div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${studentCheckAttendance.id }" />
							<c:if test="${isCK == null || isCk == ''}">
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
								<button class="btn" >取消</button>
							</c:if>
						</div>
                        
					</form>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">

function getKq(){
	var obj = document.getElementById("kq"); //selectId
	var index = obj.selectedIndex; //选中索引
	var kq = obj.options[index].value; //选中文本
}


var checker;
$(function() {
	checker = initValidator();
});



function initValidator() {
	return $("#modifyAttendance_form").validate({
		errorClass : "myerror",
		rules : {
// 			  "attendanceType":{
// 		    	   required:true
// 		       },
		       
// 		       "createDate":{
// 		    	   required:true
// 		       }
	       
	       
		},
		messages : {
// 			"attendanceType":{
// 		    	   required:"请选择考勤类型"
// 		       },
		       
// 		       "createDate":{
// 		    	   required:"请选择时间"
// 		       }
		}
	});
}

//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#modifyAttendance_form");
			var url = "${pageContext.request.contextPath}/teach/studentCheckAttendance/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/teach/studentCheckAttendance/" + $id;
			}
			
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
							parent.parent.window.search();
						}
						$.closeWindow();
// 						parent.window.close();
					} 
				} else {
					$.error("保存失败");
				}
				loader.close();
			});
		}
	}


</script>
