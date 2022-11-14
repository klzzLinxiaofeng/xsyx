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
span{
/* background-color:#fc0; */
display:-moz-inline-box;
display:inline-block;
float:left;
font-weight: bold;
width:70px; 
}
</style>

</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid ">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>成绩录入</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							成绩录入
							
						</h3>
					</div>
				</div>
				<div class="stepy-widget">
					<div class="widget-head clearfix bondi-blue">
						<div id="stepy_tabby">
							<ul id="stepy_form-titles" class="stepy-titles">
							</ul>
						</div>
						<button href="javascript:void(0)" class="btn btn-warning finish" onclick="saveOrUpdate();" style="position:absolute;right:25px;top:11px;">保存</button>
					</div>
					<div class="widget-container gray ">
						<div class="form-container"> 
							<div id="tab">
								
								<fieldset title="选择条件">
									<legend style="display: none;">填写录入目标</legend>
									<div class="control-group">
										<jsp:include page="./option.jsp"></jsp:include>	
									</div>
								</fieldset>
								
								<fieldset title="成绩录入">
									<legend style="display: none;">录入成绩</legend>
									<div class="control-group">
									
										<jsp:include page="./addOrUpdate.jsp"></jsp:include>	
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
    $(function(){
        $('#tab').stepy({
            backLabel: false,
            nextLabel: false, 
            block: false, 
            description: false,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby'
            });
		
        $.initCascadeSelector({
			"type" : "stu",
			"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
			"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
			"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
			"stuSelectId" : "studentId",  //学生select标签ID 默认为stu
			/* "isEchoSelected" : true, */
			"yearSelectedVal" : "${StudentScoreCondition.schoolYear}", //要回显的学年唯一标识
			"gradeSelectedVal" : "${StudentScoreCondition.gradeId}", //要回显的年级唯一标识
			"teamSelectedVal" : "${StudentScoreCondition.teamId}", //要回显的班级唯一标识
			 "stuSelectedVal" : "${StudentScoreCondition.studentId}" //要回显的学生唯一标识  $("#teamId").val() $("#schoolYear").val()
		});

        
        //考试类型
		$.jcGcSelector("#examType", {tc : "JY-KSXS"}, "", function() {
			$("#examType").chosen();
		});
		
		
        
		checkerSelect = initValidatorSelect();
		checkerAdd = initValidatorAdd();
    });


    function getStudentName(studentId){
    	$.TeamStudentSelector({
			"selector":"studentId",
			"teamId":"${StudentScoreCondition.teamId}",
			"selectedVal":studentId,
			"afterHandler" : function(data) {
// 				alert(data.code+":"+data.name);
			}
		});
    }
    
	function search() {
		var schoolYear = $("#schoolYear").val();
		if (schoolYear != null && schoolYear != "") {
			val.schoolYear = schoolYear;
		}
		var termCode = $("#termCode").val();
		if (termCode != null && termCode != "") {
			val.termCode = termCode;
		}
		var gradeId = $("#gradeId").val();
		if (gradeId != null && gradeId != "") {
			val.gradeId = gradeId;
		}
		var teamId = $("#teamId").val();
		if (teamId != null && teamId != "") {
			val.teamId = teamId;
		}
		var studentId = $("#studentId").val();
		if (studentId != null && studentId != "") {
			val.studentId = studentId;
		}
		var examName = $("#examName").val();
		if (examName != null && examName != "") {
			val.examName = examName;
		}
		var subjectCode = $("#subjectCode").val();
		if (subjectCode != null && subjectCode != "") {
			val.subjectCode = subjectCode;
		}
		
  		 with (document.getElementById("selectForm")) {  
             method = "post";  
             action = "${ctp}/teach/studentScore/index";  
             submit();  
         }
  	
	}

	
	function initValidatorSelect() {
		return $("#studentscore_form").validate({
			errorClass : "myerror",
			rules : {
				"schoolYear":{
					required : true
				},
				"term":{
					required : true
				},
				"gradeId":{
					required : true
				},
				"examName":{
					required : true
				},
				"subjectCode":{
					required : true
				}
			},
			messages : {
				
			}
		});
	}
	
	function initValidatorAdd() {
		return $("#studentscore_form").validate({
			errorClass : "myerror",
			rules : {
				"score":{
					required : true
				}
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checkerAdd.form()) {
			var $requestData = formData2JSONObj("#studentscore_form");
			var url = "${ctp}/teach/studentScore/creator";
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('保存成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						
					}
				}else{
					$.error("保存失败");
				}
			});
		}
	}
	</script>
</body>
</html>
