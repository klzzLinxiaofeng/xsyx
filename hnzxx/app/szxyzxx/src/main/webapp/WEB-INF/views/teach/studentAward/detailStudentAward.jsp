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
	width: 35%;
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
					<form class="form-horizontal tan_form" id="studentaward_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									序号：
								</label>
								<div class="controls">
								<input  type="text"  style="width:220px;" readonly="readonly" id="id" name="id" class="span13"
									placeholder="" value="${studentaward.id}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									学年：
								</label>
								<div class="controls">
								<input  type="text"  style="width:220px;" readonly="readonly" id="schoolYear" name="schoolYear" class="span13"
									placeholder="" value="${studentaward.schoolYear}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									年级：
								</label>
								<div class="controls">
								<input  type="text"  style="width:220px;" readonly="readonly" id="gradeName" name="gradeName" class="span13"
									placeholder="" value="${studentaward.gradeName}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									班级：
								</label>
								<div class="controls">
								<input  type="text"  style="width:220px;" readonly="readonly" id="teamName" name="teamName" class="span13"
									placeholder="" value="${studentaward.teamName}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									班内编号：
								</label>
								<div class="controls">
								<input  type="text"  style="width:220px;" readonly="readonly" id="numInTeam" name="numInTeam" class="span13"
									placeholder="" value="${studentaward.numInTeam}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									学生：
								</label>
								<div class="controls">
								<input  type="text"  style="width:220px;" readonly="readonly" id="studentName" name="studentName" class="span13"
									placeholder="" value="${studentaward.studentName}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励内容：
								</label>
								<div class="controls">
								<%-- <input  type="text"  style="width:220px;" readonly="readonly" id="awardContent" name="awardContent" class="span13"
									placeholder="" value="${studentaward.awardContent}"> --%>
									
									<select id="awardContent" name="awardContent" disabled = "disabled" class="chzn-select" style="width:220px;" ></select>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励级别：
								</label>
								<div class="controls">
								<%-- <input  type="text"  style="width:220px;" readonly="readonly" id="awardLevel" name="awardLevel" class="span13"
									placeholder="" value="${studentaward.awardLevel}"> --%>
									
									<select id="awardLevel" name="awardLevel" class="chzn-select" style="width:220px;"  disabled = "disabled" ></select>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励名次：
								</label>
								<div class="controls">
								<input  type="text"  style="width:220px;" readonly="readonly" id="awardRanking" name="awardRanking" class="span13"
									placeholder="" value="${studentaward.awardRanking}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励类型：
								</label>
								<div class="controls">
								<%-- <input  type="text"  style="width:220px;" readonly="readonly" id="awardType" name="awardType" class="span13"
									placeholder="" value="${studentaward.awardType}"> --%>
									
									<select id="awardType" name="awardType" class="chzn-select" style="width:220px;"  disabled = "disabled"></select>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励日期：
								</label>
								<div class="controls">
								<input  type="text"  style="width:220px;" readonly="readonly" id="awardDay" name="awardDay" class="span13"
									placeholder="" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentaward.awardDay}"/>'>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									奖励单位：
								</label>
								<div class="controls">
								<input  type="text"  style="width:220px;" readonly="readonly" id="awardUnit" name="awardUnit" class="span13"
									placeholder="" value="${studentaward.awardUnit}">
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<%-- <input  type="text"  style="width:220px;" readonly="readonly" id="remark" name="remark" class="span13"
									placeholder="" value="${studentaward.remark}"> --%>
								<textarea rows="3" cols="" style="width:220px;" readonly="readonly" name="remark" id="remark"  placeholder="备注">${studentaward.remark}</textarea>
									
								</div>
								
							</div>
							
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${studentaward.id}" />
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

	
	//获奖类型 
		$.jcGcSelector("#awardType", {tc : "JY-HJLX"}, "${studentaward.awardType}", function() {
			$("#awardType").chosen();
		});
		//奖励方式
		$.jcGcSelector("#awardContent", {tc : "JY-JLFS"}, "${studentaward.awardContent}", function() {
			$("#awardContent").chosen();
		});
		//奖励级别
		$.jcGcSelector("#awardLevel", {tc : "JY-XSHJLB"}, "${studentaward.awardLevel}", function() {
			$("#awardLevel").chosen();
		});
});
	//退出当前弹出小窗口
	function back() {
		$.closeWindow();
	}
	
</script>
</html>