<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<style type="text/css">
.row-fluid .span4{
		width:220px;
	}
input[type="radio"]{
		margin:0 5px 0 0px;
		position:relative;
		top:-1px;
	}
.form-horizontal .controls #zp .img_1 {
	float: left;
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	height: 130px;
}
.form-horizontal .controls #zp .img_1 img {
	width: 233px;
	height: 130px;
}
.form-horizontal .controls #zp .img_1 a {
	position: absolute;
	font-size: 22px;
	font-weight: bold;
	color: #000;
	right: 0px;
	top: 0px;
	display: block;
	width: 16px;
	height: 16px;
	line-height: 16px;
	text-align: center;
	cursor: pointer;
}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.ke-container{display:inline-block}
.chzn-container{top:10px;}
#dept_seleted_id{display:inline-block;}
</style>

<style type="text/css">
.sq_list .clsq ul li .detail_1{
	margin-left:0;
}
</style>



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
					<form class="form-horizontal tan_form" id="schoolland_form" action="javascript:void(0);">
							
							<div class="control-group">
								<label class="control-label">
									名称：
								</label>
								<div class="controls">
								<input type="text" id="name" name="name" readonly="readonly" class="span13" placeholder="" value="${schoolLand.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									位置：
								</label>
								<div class="controls">
								<input type="text" id="address" name="address" readonly="readonly" class="span13" placeholder="" value="${schoolLand.address}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									土地产权 ：
								</label>
								<div class="controls">
								<select name="landPropertyRight" disabled="disabled">
									<option value="1">产权非属学校共同使用</option>
									<option value="2">学校独立产权</option>
									<option value="99">其他</option>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									占地面积：
								</label>
								<div class="controls" style="width: 160px">
								<input type="text" id="floorArea" name="floorArea" readonly="readonly" class="span13" placeholder="" value="${schoolLand.floorArea}">
								<strong>平方米</strong>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									占地用途:
								</label>
								<div class="controls">
								<select name="coverUse" disabled="disabled">
									<option value="1">房屋用途</option>
									<option value="2">体育场地</option>
									<option value="3">绿化用地</option>
									<option value="99">其他</option>
								</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									土地使用状况：
								</label>
								<div class="controls">
								<select name="landUseStatus" disabled="disabled">
									<option value="1">对外投资</option>
									<option value="2">被借用</option>
									<option value="3">租用</option>
									<option value="4">使用</option>
									<option value="5">担保</option>
									<option value="6">闲置</option>
									<option value="99">其他</option>
								</select>
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									备注：
								</label>
								<div class="controls">
								<input type="text" id="remark" name="remark" class="span13" placeholder="" value="${schoolland.remark}">
								</div>
							</div> --%>
							<div class="control-group">
							<label class="control-label">附件：</label>
							<div id="release_picture" style="margin-left: 180px;float: none;width: initial;">
							<ul>
							<c:forEach items="${fileList}" var="file">
								<li><img src="${file.url }" height="113" width="178"><a href="javascript:void(0);" id="${file.uuid}"></a></li>
							</c:forEach>
							</ul>
							<div class="clear"></div>
							</div>
							</div>
							<div class="control-group">
							<label class="control-label">
									备注：
							</label>
								<div class="controls">
									<textarea cols="5" rows="5" readonly="readonly" name="remark" class="span13">${schoolLand.remark}</textarea>
								</div>
							</div>
						<%-- <div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${schoolland.id}" />
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