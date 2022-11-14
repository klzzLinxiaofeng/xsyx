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
					<form class="form-horizontal tan_form" id="research_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" class="span13" placeholder="" value="${research.name}" readonly="readonly"><span class="red">*</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									作者：
								</label>
								<div class="controls">
								<input type="text" id="author" name="author" class="span13" placeholder="" value="${research.author}" readonly="readonly">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									类别：
								</label>
								<div class="controls">
								<select name="type" disabled="disabled">类别：
									<c:choose>
										<c:when test="${research.type == 1}">
											<option value="1" selected="selected">科研成果</option>
											<option value="2">科技著作</option>
											<option value="3">科技论文</option>
											<option value="4">鉴定成果</option>
											<option value="5">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${research.type == 2}">
											<option value="1" >科研成果</option>
											<option value="2" selected="selected">科技著作</option>
											<option value="3">科技论文</option>
											<option value="4">鉴定成果</option>
											<option value="5">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${research.type == 3}">
											<option value="1" >科研成果</option>
											<option value="2" >科技著作</option>
											<option value="3" selected="selected">科技论文</option>
											<option value="4">鉴定成果</option>
											<option value="5">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${research.type == 4}">
											<option value="1" >科研成果</option>
											<option value="2" >科技著作</option>
											<option value="3" >科技论文</option>
											<option value="4" selected="selected">鉴定成果</option>
											<option value="5">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${research.type == 5}">
											<option value="1" >科研成果</option>
											<option value="2" >科技著作</option>
											<option value="3" >科技论文</option>
											<option value="4" >鉴定成果</option>
											<option value="5" selected="selected">专利成果</option>
											<option value="6">技术转让</option>
										</c:when>
										<c:when test="${research.type == 6}">
											<option value="1" >科研成果</option>
											<option value="2" >科技著作</option>
											<option value="3" >科技论文</option>
											<option value="4" >鉴定成果</option>
											<option value="5" >专利成果</option>
											<option value="6" selected="selected">技术转让</option>
										</c:when>
										<c:otherwise>
											<option value="1" >科研成果</option>
											<option value="2" >科技著作</option>
											<option value="3" >科技论文</option>
											<option value="4" >鉴定成果</option>
											<option value="5" >专利成果</option>
											<option value="6">技术转让</option>
										</c:otherwise>
									</c:choose>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									发布时间：
								</label>
								<div class="controls">
								<td><input type="text" id="publishDate" readonly="readonly" name="publishTime" class="span13" value = "<fmt:formatDate value="${research.publishDate}" pattern="yyyy-MM-dd" />" /></td>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									附件：
								</label>
								<div class="controls">
								<c:forEach items="${fileList }" var="file">
									${file.fileName }</br>
								</c:forEach>
								</div>
							</div>
							<div class="control-group">
							<label class="control-label">
									备注：
							</label>
								<div class="controls">
									<textarea cols="5" rows="5" readonly="readonly" name="remark" class="span13" re>${research.remark}</textarea>
								</div>
							</div>
						<%-- <div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${research.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">保存</button>
						</div> --%>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>