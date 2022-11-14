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
.form-horizontal .control-group{
	width:45%;
	display:inline-block;
	float:left;
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
					<form class="form-horizontal tan_form" id="examteamsubject_form" action="javascript:void(0);">
					
							<div class="control-group">
								<label class="control-label">
									考试名称
								</label>
								<div class="controls">
								<input type="text" readonly="readonly" id="name" name="name" class="span13"
									placeholder="" value="${examTeamSubjectVo.examName}" style="width:160px;">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									学年
								</label>
								<div class="controls">
								
								<%-- <input type="text" readonly="readonly"  id="schoolYear" name="schoolYear" class="span13"
									placeholder="" value="${examTeamSubjectVo.schoolYear}" style="width:160px;"> --%>
								<select id="xn" disabled="disabled" name="schoolYear" class="chzn-select" style="width:160px;"></select>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									学期
								</label>
								<div class="controls">
								<select id="term" name="term" disabled="disabled"  class="chzn-select" style="width:160px;"></select>
								
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									考试类型
								</label>
								<div class="controls">
								<select id="examType" disabled="disabled" name="examType" class="chzn-select" style="width:160px;"></select>
								
								<%-- <input type="text" readonly="readonly"  id="type" name="type" class="span13"
									placeholder="" value="${examTeamSubjectVo.examType}"> --%>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									考试年级
								</label>
								<div class="controls">
								<select id="nj" disabled="disabled" name="gradeId" class="chzn-select" style="width:160px;"></select>
								<%-- <input type="text" readonly="readonly"  id="gradeId" name="gradeId" class="span13"
									placeholder="" value="${examTeamSubjectVo.gradeId}" style="width:160px;"> --%>
								
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
								考试班级
								</label>
								<div class="controls">
								<select id="bj" disabled="disabled" name="teamId" class="chzn-select" style="width:160px;"></select>
								<%-- <input type="text" readonly="readonly"  id="teamId" name="teamId" class="span13"
									placeholder="" value="${examTeamSubjectVo.teamId}" style="width:160px;"> --%>
								</div>
							</div>
							
							
							
							<div class="control-group">
								<label class="control-label">
									考试科目
								</label>
								<div class="controls">
								
								<select id="subjectCode" disabled="disabled" name="subjectCode" class="chzn-select" style="width:160px;">
				
								</select>
								<%-- <input type="text" readonly="readonly"  id="subjectCode" name="subjectCode" class="span13"
									placeholder="" value="${examTeamSubjectVo.subjectCode}" style="width:160px;"> --%>
								</div>
								
							</div>
							
							<%--<div class="control-group">
								<label class="control-label">
									是否在线
								</label>
								<div class="controls">
								
<!-- 								<select id="taskOnline" disabled="disabled" name="taskOnline" class="chzn-select" style="width:160px;"></select>
 -->								<input type="text" readonly="readonly"  id="taskOnlineName" name="taskOnlineName" class="span13"
									placeholder="" value="${examTeamSubjectVo.taskOnlineName}" style="width:160px;">
									
							   <!--  <select id="taskOnlineName" disabled="disabled" name="taskOnlineName" class="chzn-select" style="width:160px;">
								<option value="1">是</option>
								<option value="0">否</option>
								<option value="2">其他</option>
								</select> -->
									
								</div>
								
							</div>--%>
							
							<div class="control-group">
								<label class="control-label">
									考试时间
								</label>
								<div class="controls">
								<input type="text" readonly="readonly"  id="preciseStartDate" name="preciseStartDate" class="span13"
									placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${examTeamSubjectVo.preciseStartDate}"></fmt:formatDate>'  style="width:160px;">
									
									
								</div>
								
							</div>
							<%--<div class="control-group">
								<label class="control-label">
									考试结束时间
								</label>
								<div class="controls">
								<input type="text" readonly="readonly"  id="preciseEndDate" name="preciseEndDate" class="span13"
									placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${examTeamSubjectVo.preciseEndDate}"></fmt:formatDate>' style="width:160px;">
								</div>
								
							</div>--%>
							
							<div class="control-group">
								<label class="control-label">
									考试人数
								</label>
								<div class="controls">
								<input type="text"  readonly="readonly" id="TeamSum" name="TeamSum" class="span13"
									placeholder="" value="${examTeamSubjectVo.teamSum}" style="width:160px;">
								</div>
								
							</div>
						<div class="control-group">
							<label class="control-label">
								<span class="red">*</span>题目数
							</label>
							<div class="controls">
								<input type="text"  readonly="readonly" id="examNumber" name="examNumber" class="span13"
									   placeholder="请输入数字" value="${examTeamSubjectVo.examNumber}" style="width:160px;">
								<%-- <input type="text" id="subjectCode" name="subjectCode" class="span13"
                                    placeholder="" value="${examTeamSubjectVo.subjectCode}"  style="width:160px;"> --%>
							</div>

						</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${examTeamSubjectVo.id}" />
								<button class="btn btn-warning" type="button"
									onclick="back();">退出</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	$(function() {
		$.initCascadeSelector({
			"type" : "team",
			"isEchoSelected" : true,
			"yearSelectedVal" : "${examTeamSubjectVo.schoolYear}", //要回显的学年唯一标识
		   	"gradeSelectedVal" : "${examTeamSubjectVo.gradeId}", //要回显的年级唯一标识
		   	"teamSelectedVal" : "${examTeamSubjectVo.teamId}", //要回显的班级唯一标识
			
		});
		
		onChangeSchoolYear();
		//考试类型，考试性质
		$.jcGcSelector("#examType", {tc : "NEW_JY-KSLX"}, "${examTeamSubjectVo.examType}", function() {
			$("#examType").chosen();
		});
		
		
		 /* $.jcSelector("#subjectCode", {"tn" : "pj_subject"}, "${examTeamSubjectVo.subjectCode}", function(data) {
			return {"val" : data.code, "title" : data.name};
		}, function() {
			//$.alert("生成下拉列表后 会回调。。。。");
		});  */
		
 		$.getPjSubject({
			//"subjectType" : 1
		}, function(data) {
			var $subjectCode = $("#subjectCode");
			$subjectCode.html("<option value=''>请选择</option>");
			$.each(data, function(index, value) {
				if(value.code == '${examTeamSubjectVo.subjectCode}'){
					$subjectCode.append("<option value='" + value.code + "' selected='selected'>" + value.name + "</option>");
				}
				
			});
		}); 
		
	});
	
	 function onChangeSchoolYear(){
		 
		  $.getSchoolTerm({"schoolYear" : $("#xn").val(),
			  "code":"${examTeamSubjectVo.term}"}, 
			  function(data, status) {
				var $xq = $("#term");
				$xq.html("");
				$.each(data, function(index, value) {
					
					$xq.append("<option value='" + value.code + "'>" + value.name + "</option>");
				});
			});
	  }
	
	//退出
	function back() {
		$.closeWindow();
		
	}
	
</script>
</html>