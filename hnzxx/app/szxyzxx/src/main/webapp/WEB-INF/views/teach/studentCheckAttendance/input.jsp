<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加信息</title>
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
  .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    }
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="row-fluid">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal" id="addAttendance_form" action="javascript:void(0);">
				    <input type="hidden" id="schoolId" name="schoolId"
						value="${schoolId}" />
					
					     <div class="control-group">
							<label class="control-label"><span class="red">*</span>学年</label>
							<div class="controls">
							<select id="xn" name="schoolYearId" class="span4 chzn-select"></select>
							
							</div>
						</div>
					
					    <div class="control-group">
							<label class="control-label"><span class="red">*</span>年级 </label>
							<div class="controls">
								<select id="nj" name="gradeId" class="span4 chzn-select"></select>
							</div>
						</div>
						
						  <div class="control-group">
							<label class="control-label"><span class="red">*</span>班级</label>
							<div class="controls">
								<select id="bj" name="teamNumber" class="span4 chzn-select"></select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label chzn-select"><span class="red">*</span>姓名</label>
							<div class="controls" id="selectStu">
								<select id="stu" name="studentId"class="span4 chzn-select"></select>
							</div>
						</div>
						
	<!-- 						   考勤类别 ：0==事假 ; 1== 病假 ; 2== 缺课 ; 3== 旷课 ;  4== 迟到 ;  5== 早退 -->
	                      <div class="control-group">
	                        <label class="control-label"><span class="red">*</span>考勤类型</label>
	                         <div class="controls">
							   <select id="kq" name="attendanceType" class="chzn-select" style="width:227px;" onchange="getKq();">
							            <option value="0">事假</option>
										<option value="1">病假</option>
										<option value="2">缺课</option>
										<option value="3">旷课</option>
										<option value="4">迟到</option>
										<option value="5">早退</option>  
							   </select>
						     </div>
						 </div>
						 <div id="type">
						    
						 </div>
							
						 <div class="control-group">
						   <label class="control-label">备注  </label>
						      <div class="controls">
							     <textarea rows="3" cols="5" id="remark" name="remark" class="span4"></textarea>
							  </div>
						</div>	
							
						 <div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${studentCheckAttendance.id }" />
							<c:if test="${isCK == null || isCk == ''}">
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
<!-- 								<button class="btn" onclick="">取消</button> -->
							</c:if>
						</div>
                        
					</form>
				</div>
			</div>
		</div>
	</div>

</body>

<script type="text/javascript">
	  
	  
$(function() {
			$.initCascadeSelector({"type" : "stu"});
			getKq();
	})
	

function getKq(){
	var obj = document.getElementById("kq"); //selectId
	var index = obj.selectedIndex; //选中索引
	var kq = obj.options[index].value; //选中文本
	var div =$("#type");
	  div.html("");
	if(index==0 || index==1){
		div.append("<div class='control-group'>"
				+"<label class='control-label'><span class='red'>*</span>开始时间</label>"
				+"<div class='controls'>"
				+"<input type='text' id='beginDate' name='beginDate'"
				+"class='span4 left_red {required:true,date:true}' placeholder='请输入开始时间' onFocus='WdatePicker({maxDate:\"#F{$dp.$D(endDate)}\"});'"
				+"/>"
				+"</div>"
				+"</div>");
		div.append("<div class='control-group'>"
				+"<label class='control-label'><span class='red'>*</span>结束时间</label>"
				+"<div class='controls'>"
				+"<input type='text' id='endDate' name='endDate'"
				+"class='span4 left_red {required:true,date:true}' placeholder='请输入结束时间'   onFocus='WdatePicker({minDate:\"#F{$dp.$D(beginDate)}\"});'"
				+"  />"
				+"</div>"
				+"</div>");
		div.append("<div class='control-group'>"
				+"<label class='control-label'><span class='red'>*</span>天数</label>"
				+"<div class='controls'>"
				+"<input type='text' id='dayNumber' name='dayNumber'"
				+"class='span4' placeholder='请输入天数'/>"
				+"</div>"
				+"</div>");
	}else if(index==2 || index==3){
		div.append("<div class='control-group'>"
				+"<label class='control-label'><span class='red'>*</span>日期</label>"
				+"<div class='controls'>"
				+"<input type='text' id='beginDate' name='beginDate'"
				+"class='span4 left_red {required:true,date:true}' placeholder='请输入日期'"
				+"onclick='WdatePicker();' />"
				+"</div>"
				+"</div>");
		div.append("<div class='control-group'>"
				+"<label class='control-label'><span class='red'>*</span>节数</label>"
				+"<div class='controls'>"
				+"<input type='text' id='nodeNumber' name='nodeNumber'"
				+"class='span4' placeholder='请输入节数'/>"
				+"</div>"
				+"</div>");
	}else if(index==4 || index==5){
		div.append("<div class='control-group'>"
				+"<label class='control-label'><span class='red'>*</span>日期</label>"
				+"<div class='controls'>"
				+"<input type='text' id='beginDate' name='beginDate'"
				+"class='span4 left_red {required:true,date:true}' placeholder='请输入日期'"
				+"onclick='WdatePicker();' />"
				+"</div>"
				+"</div>");
		div.append("<div class='control-group'>"
				+"<label class='control-label'><span class='red'>*</span>次数</label>"
				+"<div class='controls'>"
				+"<input type='text' id='orderNumber' name='orderNumber'"
				+"class='span4' placeholder='请输入次数'/>"
				+"</div>"
				+"</div>");
	}
}


var checker;
$(function() {
	checker = initValidator();
});



function initValidator() {
	return $("#addAttendance_form").validate({
		errorClass : "myerror",
		rules : {
			"schoolYearId":{
				required:true
			},
			
			"gradeId":{
				required:true
			},
			
			"teamNumber":{
				required:true
			},
			
			"studentId":{
				required:true
			},
			
			
			  "attendanceType":{
		    	   required:true
		       },
		       
		       "beginDate":{
		    	   required:true

		       },
		       "endDate":{
		    	   required:true
		       },
		       
		       "dayNumber":{
		    	   required:true,
		    	   remote : {
						async : false,
						type : "GET",
						url : "${pageContext.request.contextPath}/teach/studentCheckAttendance/ajax/checkDayNumber",
						data : {
							'dxlx' : 'inDayNumber',
							'inDayNumber' : function() {
								return $("#dayNumber").val();
							}
						}
					}
		       },
		       
		       "nodeNumber":{
		    	   required:true,
		    	   remote : {
						async : false,
						type : "GET",
						url : "${pageContext.request.contextPath}/teach/studentCheckAttendance/ajax/checkNodeNumber",
						data : {
							'dxlx' : 'inNodeNumber',
							'inNodeNumber' : function() {
								return $("#nodeNumber").val();
							}
						}
					}
		       },
		       "orderNumber":{
		    	   required:true,
		    	   remote : {
						async : false,
						type : "GET",
						url : "${pageContext.request.contextPath}/teach/studentCheckAttendance/ajax/checkOrderumber",
						data : {
							'dxlx' : 'inOrderNumber',
							'inOrderNumber' : function() {
								return $("#orderNumber").val();
							}
						}
					}
		       }
	       
		},
		messages : {
			"schoolYearId":{
				required:"学年必选"
				},
			"gradeId":{
					required:"年级必选"
				},
			"teamNumber":{
					required:"班级必选"
				},
			"studentId":{
					required:"姓名必选"
				},	
			"attendanceType":{
		    	   required:"请选择考勤类型"
		       },
		       
		       "beginDate":{
		    	   required:"开始时间必填"
		       },
		       "endDate":{
		    	   required:"结束时间必填"
		       },
		       "dayNumber":{
		    	   required:"天数必填"
		       },
		       "nodeNumber":{
		    	   required:"节数必填"
		       },
		       "orderNumber":{
		    	   required:"次数必填"
		       }
		}
	});
}

//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#addAttendance_form");
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
						}
						$.closeWindow();
					} 
				} else {
					$.error("保存失败");
				}
				loader.close();
			});
		}
	}


</script>
