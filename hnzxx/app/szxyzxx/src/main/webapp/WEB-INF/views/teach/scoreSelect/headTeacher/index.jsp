<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">

.tab-buttons{
	display:none;
}
</style>

</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid ">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>班级成绩查询</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						
					</div>
				</div>
				<div class="stepy-widget">
					<div class="widget-head clearfix" style="background-color:#456A8C">
						<div id="stepy_tabby">
							<ul id="stepy_form-titles" class="stepy-titles">
							</ul>
						</div>
						<!-- <button href="javascript:void(0)" class="btn btn-warning finish" onclick="saveOrUpdate();" style="position:absolute;right:25px;top:11px;">保存</button> -->
					</div>
					<div class="widget-container gray ">
						<div class="form-container"> 
							<div id="tab">
								
								<fieldset title="选择条件">
									<legend style="display: none;">填写查询条件</legend>
									<div class="control-group">
								  
										<jsp:include page="./option.jsp"></jsp:include>	
									</div>
								</fieldset>
								
								<fieldset title="查询结果">
									<legend style="display: none;">查询结果</legend>
									<div class="control-group">
									
									 <c:choose>
									 <c:when test="${resultType==1}">
									 <jsp:include page="./resultSimple.jsp"></jsp:include>
									 </c:when>
									 <c:otherwise>
									 <jsp:include page="./resultComplex.jsp"></jsp:include>
									 </c:otherwise>
									 </c:choose>
									
											
									</div>
								</fieldset>
							
								 <button href="javascript:void(0)" class="btn btn-warning finish" style="display:none;">保存</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
	var val = {};
	var checkerSelect;
	var checkerAdd;
	var initExamNameNum = 0;
	var initSubjectNum = 0;
	var examCodeFlag = '${ScoreCondition.examCodeFlag}';
	var subjectCodeFlag = '${ScoreCondition.subjectCodeFlag}';
	
	var initExamTypeNum = 0;
	var examTypeFlag = '${ScoreCondition.examTypeFlag}';
    $(function(){
        $('#tab').stepy({
            backLabel: "前往成绩查询页面",
            nextLabel: "前往结果展示页面", 
            block: false, 
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby'
            });
		
        var defOption = {
        		"type" : "stu",
    			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
    			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
    			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
    			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
    			 "isEchoSelected" : true,
    			"yearSelectedVal" : "${ScoreCondition.schoolYear}", //要回显的学年唯一标识
    			"gradeSelectedVal" : "${ScoreCondition.gradeId}", //要回显的年级唯一标识
    			"teamSelectedVal" : "${ScoreCondition.teamId}", //要回显的班级唯一标识
    			 "stuSelectedVal" : "${ScoreCondition.studentId}" //要回显的学生唯一标识  $("#teamId").val() $("#schoolYear").val()
    		};
        
         if(${ScoreCondition.schoolYear == undefined}){
        	 
        	 defOption = {
             		"type" : "stu",
         			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
         			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
         			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
         			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
         		};
         }
        
        $.initCascadeSelector(defOption);
		
        /* //考试类型
        var examType='${ScoreCondition.examType}';
        if(${ScoreCondition.examType == undefined}){
        	examType="";
        } 
		$.jcGcSelector("#examType", {tc : "JY-KSXS"}, examType, function() {
			$("#examType").chosen();
		}); */
		
		
		onChangeSchoolYear();
		examType();
    });
    function onChangeTeam(){
    	
		examType();
	}
    function onChangeSchoolYear(){
    	var xn = $("#schoolYear").val();
    	if(xn == null || xn == ""){
    		xn = '${sessionScope[sca:currentUserKey()].schoolYear}';
		  }
		  $.getSchoolTerm({"schoolYear" : xn}, function(data, status) {
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
    
	function initValidatorSelect() {
		return $("#selectForm").validate({
			errorClass : "myerror",
			rules : {
				"schoolYear":{
					required : true
				},
				"termCode":{
					required : true
				},
				"gradeId":{
					required : true
				},
				"teamId":{
					required : true
				},
				"examType":{
					required : true
				},
				"examName":{
					required : true
				}
			},
			messages : {
				
			}
		});
	}
    
	function search() {
		$('#examCodeFlag').val(1);
		$('#subjectCodeFlag').val(1);
		$('#examTypeFlag').val(1);
		checkerSelect = initValidatorSelect();
		if(checkerSelect.form()){
			with (document.getElementById("selectForm")) {  
	             method = "post";  
	             action = "${ctp}/teach/scoreSelect/headTeacher/index";
	             submit();  
	         } 
		}
  		 
  	
	}

	
	function examType() {
		
		 var examTypeArray={};
		 if(${ScoreCondition.examType == undefined}){
			 examTypeArray={
					  'schoolYear':$('#schoolYear').val(),
						'termCode':$('#termCode').val(),
						'gradeId':$('#gradeId').val(),
						'teamId':$('#teamId').val()
				
				 }
			}/*else if(initExamTypeNum == 0&&${ScoreCondition.examType != undefined}){
				examTypeArray={
						'schoolYear':'${ScoreCondition.schoolYear}',
						'termCode':'${ScoreCondition.termCode}',
						'gradeId':'${ScoreCondition.gradeId}',
						'teamId':'${ScoreCondition.teamId}',
						'examType':'${ScoreCondition.examType}'
				
				}
			}*/else{
				examTypeArray={
						  'schoolYear':$('#schoolYear').val(),
							'termCode':$('#termCode').val(),
							'gradeId':$('#gradeId').val(),
							'teamId':$('#teamId').val(),
							'examType':$('#examType').val()
					
					 }
			}
		
		 $("#examType").empty();
		 var examType = '${ScoreCondition.examType}';
		 $.ajax({  
			type:"post",  
			url:"${ctp}/teach/scoreSelect/examType",  
			data:examTypeArray,
			success:function(data) {  
			var map =  eval("("+data+")");
			
			$.each(map,function(key,values){
				if((examTypeFlag != undefined)&&(examTypeFlag == 1)&&${ScoreCondition.examType != undefined}&&(examType == values)){
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
		$('#examName').val();
		 var depExamCode={};
		 if(${ScoreCondition.examName == undefined}){
			 depExamCode={
					 'schoolYear':$('#schoolYear').val(),
					'termCode':$('#termCode').val(),
					'gradeId':$('#gradeId').val(),
					'teamId':$('#teamId').val(),
					'examType':$('#examType').val(),
					//'examName':$('#examName').val()
				 }
			}else if(initExamNameNum == 0&&${ScoreCondition.examName != undefined}){
				depExamCode={
						'schoolYear':'${ScoreCondition.schoolYear}',
						'termCode':'${ScoreCondition.termCode}',
						'gradeId':'${ScoreCondition.gradeId}',
						'teamId':'${ScoreCondition.teamId}',
						'examType':'${ScoreCondition.examType}'/* ,
						'examName':'${ScoreCondition.examName}' */
				}
			}else{
				depExamCode={
						'schoolYear':$('#schoolYear').val(),
						'termCode':$('#termCode').val(),
						'gradeId':$('#gradeId').val(),
						'teamId':$('#teamId').val(),
						'examType':$('#examType').val(),
				}
			}

	    $("#examName").empty();
	    var examName = '${ScoreCondition.examName}';
		$.ajax({  
			type:"post",  
			url:"${ctp}/teach/scoreSelect/termCode",  
			data:depExamCode,
			success:function(data) {  
			var map =  eval("("+data+")");  
			
			$.each(map,function(key,values){
				
			if((examCodeFlag != undefined)&&(examCodeFlag == 1)&&${ScoreCondition.examName != undefined}&&(examName == values)){
				$("<option value="+values+" selected='selected'>"+key+"</option>").appendTo("#examName");
				examCodeFlag = 0;
			}else{
				$("<option value="+values+" >"+key+"</option>").appendTo("#examName");
			}
			
			}  
			);
			
			
			}  
		});
		initExamNameNum = Number(initExamNameNum)+1;
		subjectName();
	}
	
	function subjectName() {
		
		 var depsubjectName={};
		 if(${ScoreCondition.subjectCode == undefined}){
			 depsubjectName={
					  'schoolYear':$('#schoolYear').val(),
						'termCode':$('#termCode').val(),
						'gradeId':$('#gradeId').val(),
						'teamId':$('#teamId').val(),
						'examType':$('#examType').val(),
						'examName':$('#examName').val(),
						'type':'全部'  
				 }
			}else if(initSubjectNum == 0&&${ScoreCondition.subjectCode != undefined}){
				depsubjectName={
						'schoolYear':'${ScoreCondition.schoolYear}',
						'termCode':'${ScoreCondition.termCode}',
						'gradeId':'${ScoreCondition.gradeId}',
						'teamId':'${ScoreCondition.teamId}',
						'examType':'${ScoreCondition.examType}',
						'examName':'${ScoreCondition.examName}',
						//'subjectCode':'${ScoreCondition.subjectCode}',
						'type':'全部' 
				}
			}else{
				
				 depsubjectName={
						   'schoolYear':$('#schoolYear').val(),
							'termCode':$('#termCode').val(),
							'gradeId':$('#gradeId').val(),
							'teamId':$('#teamId').val(),
							'examType':$('#examType').val(),
							'examName':$('#examName').val(),
							'type':'全部'
					 }
			}
		
		 $("#subjectCode").empty();
		 var subjectCode = '${ScoreCondition.subjectCode}';
		 $.ajax({  
			type:"post",  
			url:"${ctp}/teach/scoreSelect/subjectCode",  
			data:depsubjectName,
			success:function(data) {  
			var map =  eval("("+data+")");
			
			$.each(map,function(key,values){
				if((subjectCodeFlag != undefined)&&(subjectCodeFlag == 1)&&${ScoreCondition.subjectCode != undefined}&&(subjectCode == values)){
					
					$("<option value='"+values+"' selected='selected'>"+key+"</option>").appendTo("#subjectCode");
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

	</script>
</body>
</html>
