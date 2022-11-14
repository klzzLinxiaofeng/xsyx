<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
	 .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    	float:left;
    }
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
							<form id="modifyStudentAidForm"  class="form-horizontal left-align form-well" novalidate="novalidate">
								<input type="hidden" id="id" name="id" value="${studentPunish.id}"/>
								
									<div class="control-group">
										<label class="control-label">学年</label>
										<div class="controls">
											<input type="text" readonly="readonly" name="schoolYear" id="schoolYear" value="${studentPunish.schoolYear}" />
										</div>
									</div>
									
									<div class="control-group">
										<label class="control-label">年级</label>
										<div class="controls">
											<input type="text" readonly="readonly" name="gradeName" id="gradeName" value="${studentPunish.gradeName}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">班级</label>
										<div class="controls">
											<input type="text"  readonly="readonly" name="teamName" id="teamName" value="${studentPunish.teamName}" />
										</div>
									</div>
									<div class="control-group">
										<label class="control-label">姓名</label>
										<div class="controls">
											<input type="text"  readonly="readonly" name="studentName" id="studentName" value="${studentPunish.studentName}" />
										</div>
									</div>
									
									<div class="control-group">
								<label class="control-label">
									处分类型
								</label>
								<div class="controls">
								<input type="text"  readonly="readonly" id="punishType" name="punishType" class="span13"
									placeholder="处分类型" value="${studentPunish.punishType}" style="width:220px;"/>
								</div>
								
								</div>
								
								
								
							<div class="control-group">
								<label class="control-label">
									处分原因
								</label>
								<div class="controls">
								<select id="punishCause" name="punishCause" class="chzn-select" style="width:220px;"  disabled="disabled"></select>
								
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									处分日期
								</label>
								<div class="controls">
								<input type="text"  readonly="readonly" id="punishDay" name="punishDay" class="span13"
									placeholder="处分日期" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentPunish.punishDay}"></fmt:formatDate>'  style="width:220px;"/>
								</div>
								
							</div>
							
							
							
							<div class="control-group">
								<label class="control-label">
									到期日期
								</label>
								<div class="controls">
								<input type="text"  readonly="readonly" id="punishEndDay" name="punishEndDay" class="span13"
									placeholder="处分日期" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentPunish.punishEndDay}"></fmt:formatDate>'  style="width:220px;"/>
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									撤销日期
								</label>
								<div class="controls">
								
								<input type="text"  readonly="readonly" id="repealDay" name="repealDay" class="span13"
									placeholder="处分日期" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${studentPunish.repealDay}"></fmt:formatDate>' style="width:220px;"/>
								
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">
									是否撤销处分
								</label>
								<div class="controls">
								<%-- <input type="text"  readonly="readonly" id="isRepeal" name="isRepeal" class="span13"
									placeholder="是否撤销处分" value="${studentPunish.isRepeal}" style="width:220px;"/> --%>
									
									<select id="isRepeal"  readonly="readonly"  name="isRepeal" style="width:220px;">
									<option value="false">否</option>
									<option value="true">是</option>
									</select>
								</div>
								
							</div>
							
							<div class="control-group">
								<label class="control-label">
									备注
								</label>
								<div class="controls">
								<%-- <input type="text"  readonly="readonly" id="remark" name="remark" class="span13"
									placeholder="备注" value="${studentPunish.remark}" style="width:200px;"/> --%>
								<textarea rows="3" cols=""  readonly="readonly" name="remark" id="remark"  placeholder="备注">${studentPunish.remark}</textarea>
									
								
					
								</div>
								
							</div>
							
								
									<div class="form-actions tan_bottom">
								<button onclick="detailStudentPunish();" class="btn btn-warning" >退出</button>
						</div>
							</form>
						</div>
					</div>
				</div>
			</div>
	<script>
	$("#isRepeal option[value='${studentPunish.isRepeal}']").attr("selected","selected");
     $(function() {
    		//违纪类别
 		$.jcGcSelector("#punishCause", {tc : "JY-WJLB"}, "${studentPunish.punishCause}", function() {
 			$("#punishCause").chosen();
 		});
		});
    
    function detailStudentPunish(){
    	$.closeWindow();
    }
    

	</script>
</body>
</html>
