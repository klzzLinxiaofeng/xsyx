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
.chzn-container .chzn-results{
	max-height:120px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
		<form action="${pageContext.request.contextPath}/teach/studentScore/downLoadScoreInfo" method="get"  id="downLoadForm" class="form-horizontal left-align form-well" novalidate="novalidate">						
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>学年：</label>
			<div class="controls">
				<select id="schoolYear" name="schoolYear" onchange="onChangeSchoolYear();" class="chzn-select"style="width:220px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>学期：</label>
			<div class="controls">
				<select id="termCode" name="termCode" class="chzn-select"style="width:220px;" ></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>年级：</label>
			<div class="controls">
				<select id="gradeId" name="gradeId" class="chzn-select" style="width:220px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>班级：</label>
			<div class="controls">
				<select id="teamId" name="teamId" class="chzn-select" style="width:220px;" onchange="onChangeTeam();" ></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">学生：</label>
			<div class="controls">
				<select id="studentId" name="studentId" class="chzn-select" style="width:220px;"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>考试类型：</label>
			<div class="controls">
				<select id="examType" name="examType" class="chzn-select"style="width:220px;" onchange="examCode();"></select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>考试名称：</label>
			<div class="controls">
				<select id="examName" name="examName" class="chzn-select" style="width:220px;"  onchange="subjectName();">
									<option value="">请选择</option>
									</select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><span class="red">*</span>科目：</label>
			<div class="controls">
				<select id="subjectCode" name="subjectCode" class="chzn-select" style="width:220px;">
										<option value="">请选择</option>
										
									</select>
			</div>
		</div>
		<input type="hidden" name = "canSave" id="canSave" value="${StudentScoreCondition.canSave}" >
		<input type="hidden" name = "examCodeFlag" id="examCodeFlag" value="${StudentScoreCondition.examCodeFlag}" >
		<input type="hidden" name = "subjectCodeFlag" id="subjectCodeFlag" value="${StudentScoreCondition.subjectCodeFlag}">
		<input type="hidden" name = "examTypeFlag" id="examTypeFlag" value="${StudentScoreCondition.examType}">

						<div class="form-actions tan_bottom">
								<button class="btn btn-warning" type="submit"
									onclick="downLoadModel();">导出模板数据</button>
						</div>
					</form>
					
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">

	
	
	
	
</script>
	<script>
	 var checker;
		
		var val = {};
		var checkerSelect;
		var checkerAdd;
		var initExamNameNum = 0;
		var initSubjectNum = 0;
		var initExamTypeNum = 0;
		var examCodeFlag = '${StudentScoreCondition.examCodeFlag}';
		var subjectCodeFlag = '${StudentScoreCondition.subjectCodeFlag}';
		var examTypeFlag = '${StudentScoreCondition.examTypeFlag}';
		var canSave='${StudentScoreCondition.canSave}';
		$(function() {
			
			var defOption = {
	        		"type" : "stu",
	    			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
	    			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
	    			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
	    			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
	    			 "isEchoSelected" : true,
	    			"yearSelectedVal" : "${StudentScoreCondition.schoolYear}", //要回显的学年唯一标识
	    			"gradeSelectedVal" : "${StudentScoreCondition.gradeId}", //要回显的年级唯一标识
	    			"teamSelectedVal" : "${StudentScoreCondition.teamId}", //要回显的班级唯一标识
	    			 "stuSelectedVal" : "${StudentScoreCondition.studentId}" //要回显的学生唯一标识  $("#teamId").val() $("#schoolYear").val()
	    			 /* "stuCallback" : function($this){
	    				 alert($("#teamId").val());
	    				 examType();
	    			 } */
	    		};
	        
	         if("${StudentScoreCondition.schoolYear == undefined}"){
	        	 
	        	 defOption = {
	             		"type" : "stu",
	         			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
	         			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
	         			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
	         			"stuSelectId" : "studentId"  //学生select标签ID 默认为stu
	         		
	         		};
	         }
	        
	        $.initCascadeSelector(defOption);

	       
		
			onChangeSchoolYear();
			examType();
			checker = initValidatorAdd();
			
		});

		function onChangeTeam(){
			
			examType();
		}
		
		function onChangeSchoolYear() {
			var xn = $("#schoolYear").val();
			if(xn == null || xn == ""){
				xn = '${sessionScope[sca:currentUserKey()].schoolYear}';
			 }
			
			$.getSchoolTerm({
				"schoolYear" : xn
			}, function(data, status) {
				var $xq = $("#termCode");
				
				$xq.html("");
				var termCurrent = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
				$.each(data, function(index, value) {

					if(termCurrent == value.code ){
						$xq.append("<option  selected='selected' value='" + value.code + "'>" + value.name + "</option>");
					}else{
						$xq.append("<option value='" + value.code + "'>" + value.name + "</option>");
					}
				});
			});

		}

		function getStudentName(studentId) {
			$.TeamStudentSelector({
				"selector" : "studentId",
				"teamId" : "${StudentScoreCondition.teamId}",
				"selectedVal" : studentId,
				"afterHandler" : function(data) {
					// 				alert(data.code+":"+data.name);
				}
				
			});
		}



		function initValidatorSelect() {
			return $("#downLoadForm").validate({
				errorClass : "myerror",
				rules : {
					"schoolYear" : {
						required : true
					},
					"termCode" : {
						required : true
					},
					"gradeId" : {
						required : true
					},
					"teamId" : {
						required : true
					},
					"examType" : {
						required : true
					},
					"examName" : {
						required : true
					},
					"subjectCode" : {
						required : true
					}
				},
				messages : {

				}
			});
		}

		
		function examType() {
			 var examTypeArray={};
			 if("${StudentScoreCondition.examType == undefined}"){
				 examTypeArray={
						  'schoolYear':$('#schoolYear').val(),
							'termCode':$('#termCode').val(),
							'gradeId':$('#gradeId').val(),
							'teamId':$('#teamId').val()
					
					 }
				}else if(initExamTypeNum == 0 && "${StudentScoreCondition.examType != undefined}"){
					examTypeArray={
							'schoolYear':'${StudentScoreCondition.schoolYear}',
							'termCode':'${StudentScoreCondition.termCode}',
							'gradeId':'${StudentScoreCondition.gradeId}',
							'teamId':'${StudentScoreCondition.teamId}',
							'examType':'${StudentScoreCondition.examType}'
					
					}
				}else{
					examTypeArray={
							  'schoolYear':$('#schoolYear').val(),
								'termCode':$('#termCode').val(),
								'gradeId':$('#gradeId').val(),
								'teamId':$('#teamId').val(),
								'examType':$('#examType').val()
						
						 }
				}
			
			 $("#examType").empty();
			 var examType = '${StudentScoreCondition.examType}';
			 $.ajax({  
				type:"post",  
				url:"${ctp}/teach/scoreSelect/examType",  
				data:examTypeArray,
				success:function(data) {  
				var map =  eval("("+data+")");
				
				$.each(map,function(key,values){
					if((examTypeFlag != undefined) && (examTypeFlag == 1) && "${StudentScoreCondition.examType != undefined}" && (examType == values)){
						$("<option value="+values+" selected='selected'>"+key+"</option>").appendTo("#examType");
						examTypeFlag = 0;
					}else{
						$("<option value="+values+" >"+key+"</option>").appendTo("#examType");
					}
				}  
				);   
				}  
			});  
			 
			 initExamTypeNum = Number(initExamTypeNum)+1;
			 examCode();
		}
		
		function examCode() {
			 var depExamCode={};
			 if("${StudentScoreCondition.examName == undefined}"){
				 depExamCode={
						 'schoolYear':$('#schoolYear').val(),
						'termCode':$('#termCode').val(),
						'gradeId':$('#gradeId').val(),
						'teamId':$('#teamId').val(),
						'examType':$('#examType').val()
						//'examName':$('#examName').val()
					 }
				}else if(initExamNameNum == 0 && "${StudentScoreCondition.examName != undefined}"){
					depExamCode={
							'schoolYear':'${StudentScoreCondition.schoolYear}',
							'termCode':'${StudentScoreCondition.termCode}',
							'gradeId':'${StudentScoreCondition.gradeId}',
							'teamId':'${StudentScoreCondition.teamId}',
							'examType':'${StudentScoreCondition.examType}'/* ,
							'examName':'${StudentScoreCondition.examName}' */
					}
				}else{
					depExamCode={
							 'schoolYear':$('#schoolYear').val(),
							'termCode':$('#termCode').val(),
							'gradeId':$('#gradeId').val(),
							'teamId':$('#teamId').val(),
							'examType':$('#examType').val()
							//'examName':$('#examName').val()
						 }
				}
			$("#examName").empty();
			var examName = '${StudentScoreCondition.examName}';
			
			$.ajax({
				type : "post",
				url : "${ctp}/teach/scoreSelect/termCode",
				data : depExamCode,
				success : function(data) {
					var map = eval("(" + data + ")");

					$.each(map, function(key, values) {
						
						if((examCodeFlag != undefined) && (examCodeFlag == 1) && "${StudentScoreCondition.examName != undefined}" && (examName == values)){
							$("<option value="+values+" selected='selected'>"+key+"</option>").appendTo("#examName");
							examCodeFlag = 0;
						}else{
							$("<option value="+values+" >"+key+"</option>").appendTo("#examName");
						}
					});
				}
			});
			initExamNameNum = Number(initExamNameNum)+1;
			subjectName();
		}

		function subjectName() {
			
			 var depsubjectName={};
			 if("${StudentScoreCondition.subjectCode == undefined}"){
				 depsubjectName={
						  'schoolYear':$('#schoolYear').val(),
							'termCode':$('#termCode').val(),
							'gradeId':$('#gradeId').val(),
							'teamId':$('#teamId').val(),
							'examType':$('#examType').val(),
							'examName':$('#examName').val()
					
					 }
				}else if(initSubjectNum == 0 && "${StudentScoreCondition.subjectCode != undefined}"){
					depsubjectName={
							'schoolYear':'${StudentScoreCondition.schoolYear}',
							'termCode':'${StudentScoreCondition.termCode}',
							'gradeId':'${StudentScoreCondition.gradeId}',
							'teamId':'${StudentScoreCondition.teamId}',
							'examType':'${StudentScoreCondition.examType}',
							'examName':'${StudentScoreCondition.examName}'/* ,
							'subjectCode':'${StudentScoreCondition.subjectCode}', */
					
					}
				}else{
					depsubjectName={
							  'schoolYear':$('#schoolYear').val(),
								'termCode':$('#termCode').val(),
								'gradeId':$('#gradeId').val(),
								'teamId':$('#teamId').val(),
								'examType':$('#examType').val(),
								'examName':$('#examName').val()
						
						 }
				}
			
			 $("#subjectCode").empty();
			 var subjectCode = '${StudentScoreCondition.subjectCode}';
			 $.ajax({  
				type:"post",  
				url:"${ctp}/teach/scoreSelect/subjectCode",  
				data:depsubjectName,
				success:function(data) {  
				var map =  eval("("+data+")");
				
				$.each(map,function(key,values){
					if((subjectCodeFlag != undefined) && (subjectCodeFlag == 1) && "${StudentScoreCondition.subjectCode != undefined}" && (subjectCode == values)){
						$("<option value="+values+" selected='selected'>"+key+"</option>").appendTo("#subjectCode");
						subjectCodeFlag = 0;
					}else{
						$("<option value="+values+" >"+key+"</option>").appendTo("#subjectCode");
					}
				}  
				);   
				}  
			});  
			 
			 initSubjectNum = Number(initSubjectNum)+1;
		} 
		
		
		/* function downLoadModel() {


			$('#examCodeFlag').val(1);
			$('#subjectCodeFlag').val(1);
			$('#canSave').val(1);
			$('#examTypeFlag').val(1);
			
			checkerSelect = initValidatorSelect();
			if(checkerSelect.form()){
				 with (document.getElementById("downLoadForm")) {
						method = "post";
						action = "${ctp}/teach/studentScore/downLoadScoreInfo";
						submit();
					} 
			}
		
			
		} */
		
	 function downLoadModel() {
			
			$('#examCodeFlag').val(1);
			$('#subjectCodeFlag').val(1);
			$('#canSave').val(1);
			$('#examTypeFlag').val(1);
			checkerSelect = initValidatorSelect();
			if (checkerSelect.form()) {
				    var loader = new loadLayer();
					var $requestData = formData2JSONObj("#downLoadForm");
					$requestData._method="post";
					var url = "${ctp}/teach/studentScore/downLoadScoreInfo";
					loader.show();
					$.post(url, $requestData, function(data, status) {
						
						if("success" === status) {
							//乱码问题尚未解决  执行到451行报错  执行不下去了
							$.success('下载成功');
							loader.close();
							
							data = eval("(" + data + ")");
							if("success" === data.info) {
								$.success('下载成功');
								if(parent.core_iframe != null) {
		 							parent.core_iframe.window.location.reload();
		 						} else {
		 							parent.window.location.reload();
		 						}
								$.closeWindow();
							} else {
								$.error(data.info);
								
							}
						}else{
							$.error("服务器异常");
						}
						
					});
					
					loader.close();
				}
			} 
			
		
		
	</script>
</html>