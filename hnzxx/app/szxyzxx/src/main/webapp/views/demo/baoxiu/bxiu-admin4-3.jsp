<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-datetimepicker.min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
<title>报修</title>
<script type="text/javascript">
	 $(function () {
        $('#datetimepicker1').datetimepicker({
            language: 'pt-BR'
        });
  
	var editor;
			KindEditor.ready(function(K) {
				editor = K.create('textarea[name="content"]', {
					allowFileManager : true
				});
				K('input[name=getHtml]').click(function(e) {
					alert(editor.html());
				});
				K('input[name=isEmpty]').click(function(e) {
					alert(editor.isEmpty());
				});
				K('input[name=getText]').click(function(e) {
					alert(editor.text());
				});
				K('input[name=selectedHtml]').click(function(e) {
					alert(editor.selectedHtml());
				});
				K('input[name=setHtml]').click(function(e) {
					editor.html('<h3>Hello KindEditor</h3>');
				});
				K('input[name=setText]').click(function(e) {
					editor.text('<h3>Hello KindEditor</h3>');
				});
				K('input[name=insertHtml]').click(function(e) {
					editor.insertHtml('<strong>插入HTML</strong>');
				});
				K('input[name=appendHtml]').click(function(e) {
					editor.appendHtml('<strong>添加HTML</strong>');
				});
				K('input[name=clear]').click(function(e) {
					editor.html('');
				});
			});
		
	//收起展开
	$(".x-collapse").click(function(){
		if($(this).hasClass("fold-on")){				
				$(this).prev().find(".x-up").hide();
				$(this).text("展开↑").removeClass("fold-on");
				}	
			else{
				$(this).prev().find(".x-up").show();
				$(this).text("收起↓").addClass("fold-on")
			}
		});	
	});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="报修" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
		<div class="span12">
			<div class="content-widgets white">
				<div class="widget-head">
						<h3 class="x-head content-top"><a href="javascript:void(0);" class="on">申请报修</a><a href="javascript:void(0);" class="back right"><i class="fa fa-arrow-circle-left"></i>返回</a></h3>
				</div>
				<div class="space20"></div>
				<div class="x-main" >
					<div class="clearfix x-mright">
						<div class="list">
						<form class="form-horizontal">
								<div class="control-group">
									<label class="control-label">标题</label>
									<div>
										<input type="text" placeholder="请输入标题，少于40个中文字符" class="span6 left-stripe">
									</div>
								</div>
						<div class="x-up">
							<div class="control-group">
									<label class="control-label">图片</label>
									<div class="left">
										<div class="fileupload fileupload-new clearfix" data-provides="fileupload">
											<div class="fileupload-new thumbnail">
												<img src="${pageContext.request.contextPath}/res/images/w183.jpg" alt="img">
											</div>
											<div>
												<span class="btn btn-file" style="border-radius:0;"><span class="fileupload-new"><i class="fa fa-upload"></i>上传图片</span>
												<input type="file">
												</span>
											</div>
											<div class="space10"></div>
											<h6>建议尺寸：950像素*500像素</h6>
										</div>
									</div>
							</div>
							<div class="control-group">
									<label class="control-label">详情</label>
									<div class="left">
									<div class="textarea">
										<textarea name="content" style="width:800px;height:400px;visibility:hidden;">KindEditor</textarea>			
									</div>
									</div>
							</div>
							<div class="control-group">
									<label class="control-label">预约时间</label>
									<div>
										<div id="datetimepicker1" class="input-append date">
											<input data-format="dd/MM/yyyy hh:mm:ss" type="text" placeholder="留空是当前时间"><span class="add-on "><i data-time-icon="icon-time" data-date-icon="icon-calendar" class="fa fa-calendar"></i></span>
										</div>
							</div>
							</div>
						</div>
							<div class="control-group">
									<label class="control-label">维修地点</label>
									<div>
										<input type="text" placeholder="请说明设备地点，少于20个中文字符" class="span3 left-stripe">
									</div>
							</div>
							<div class="control-group">
									<label class="control-label">联系人</label>
									<div>
										<input type="text" placeholder="联系本人可不填" class="span2 left-stripe">
									</div>
							</div>
							<div class="control-group">
									<label class="control-label">联系电话</label>
									<div>
										<input type="text" placeholder="联系本人可不填" class="span3 left-stripe">
									</div>
							</div>
					</form>
					</div>
					<a href="javascript:void();" class="tc x-collapse fold-on">收起 ↓</a>
					<p class="tc" style="padding-top:8px;border-top: #ccc 1px solid;"><button class="btn btn-success" type="button" style="margin:0 15px;">发布</button><button class="btn btn-link" type="button">预览</button></p>
				  </div>
			</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>