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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="room_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label">
									房间编号：
								</label>
								<div class="controls">
								<input type="text" id="code" readonly="readonly" name="code" class="span13" placeholder="" value="${room.code}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									建筑物名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" readonly="readonly" class="span13" placeholder="" value="${room.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间名称：
								</label>
								<div class="controls">
								<input type="text" id="roomName" name="roomName" readonly="readonly" class="span13" placeholder="" value="${room.roomName}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间用途：
								</label>
								<div class="controls">
								<select name="roomUse" disabled="disabled">
									<c:choose>
										<c:when test="${room.roomUse == 1 }">
										<option value="1" selected="selected">教室</option>
										<option value="2">实验室</option>
										<option value="3">物理实验室</option>
										<option value="4">生物实验室</option>
										<option value="99">其他实验室</option>
										</c:when>
										<c:when test="${room.roomUse == 2 }">
										<option value="1">教室</option>
										<option value="2" selected="selected">实验室</option>
										<option value="3">物理实验室</option>
										<option value="4">生物实验室</option>
										<option value="99">其他实验室</option>
										</c:when>
										<c:when test="${room.roomUse == 3 }">
										<option value="1">教室</option>
										<option value="2">实验室</option>
										<option value="3" selected="selected">物理实验室</option>
										<option value="4">生物实验室</option>
										<option value="99">其他实验室</option>
										</c:when>
										<c:when test="${room.roomUse == 4 }">
										<option value="1">教室</option>
										<option value="2">实验室</option>
										<option value="3" >物理实验室</option>
										<option value="4" selected="selected">生物实验室</option>
										<option value="99">其他实验室</option>
										</c:when>
										
										<c:when test="${room.roomUse == 99 }">
										<option value="1">教室</option>
										<option value="2">实验室</option>
										<option value="3" >物理实验室</option>
										<option value="4" >生物实验室</option>
										<option value="99" selected="selected">其他实验室</option>
										</c:when>
										<c:otherwise>
										<option value="1">教室</option>
										<option value="2">实验室</option>
										<option value="3" >物理实验室</option>
										<option value="4" >生物实验室</option>
										<option value="99" selected="selected">其他实验室</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间所处楼层：
								</label>
								<div class="controls">
								<input type="text" id="roomFloor" readonly="readonly" name="roomFloor" class="span13" placeholder="" value="${room.roomFloor}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间建筑面积：
								</label>
								<div class="controls" style="width: 160px"> 
								<input type="text" id="roomArea" name="roomArea" readonly="readonly" class="span13" placeholder="" value="${room.roomArea}">
								<strong>平方米</strong>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									房间使用面积：
								</label>
								<div class="controls" style="width: 160px">
								<input type="text" id="roomUseArea" name="roomUseArea" readonly="readonly" class="span13" placeholder="" value="${room.roomUseArea}">
								<strong>平方米</strong>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<textarea rows="5" cols="5" id="remark" name="remark" readonly="readonly" class="span13">${room.remark}</textarea>
								</div>
							</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>