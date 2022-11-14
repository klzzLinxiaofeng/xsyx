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
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>学生成绩查询</li>
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
					<div class="widget-head clearfix"  style="background-color:#456A8C">
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
										
										<jsp:include page="./result.jsp"></jsp:include>	
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
            backLabel: "成绩查询页面",
            nextLabel: "结果展示页面", 
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
		
        
        
        //考试类型 ${ScoreCondition.examName}
        var examType='${ScoreCondition.examType}';
        if(${ScoreCondition.examType == undefined}){
        	examType="";
        } 
		$.jcGcSelector("#examType", {tc : "NEW_JY-KSLX"}, examType, function() {
			$("#examType").chosen();
		});
		
		onChangeSchoolYear();
    });
    /* var termCurrent = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
	if(termCurrent == value.code ){
		$xq.append("<option  selected='selected' value='" + value.code + "'>" + value.name + "</option>");
	}else{
		$xq.append("<option value='" + value.code + "'>" + value.name + "</option>");
	} */
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
				"studentId" : {
					required : true
				},
				"examType" : {
					required : true
				}
			},
			messages : {

			}
		});
	}
    
    
	function search() {
		
		
		checkerSelect = initValidatorSelect();
		 if(checkerSelect.form()){
			with (document.getElementById("selectForm")) {  
		          method = "post";  
		          action = "${ctp}/teach/scoreSelect/student/index";
		          submit();  
		      } 
		}
		
	}


	
	</script>
</body>
</html>
