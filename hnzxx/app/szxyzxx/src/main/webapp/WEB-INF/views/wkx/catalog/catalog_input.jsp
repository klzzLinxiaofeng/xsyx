<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
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
					<form class="form-horizontal tan_form" id="microcatalog_form" action="javascript:void(0);">
 							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>标题：
								</label>
								<div class="controls">
									<input type="text" id="title" name="title" style="width: 470px;" class="span13" placeholder="" value="${microCatalog.title}">
									<label id="title_error"  generated="true" class="myerror" style="display:none">必填字段</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>内容：
								</label>
								<div class="textarea controls">
									<textarea id="content" name="content" style="width:90%;height:250px;visibility:hidden;">${microCatalog.content}</textarea>
									<label id="content_error"  generated="true" class="myerror" style="display:none">必填字段</label>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									是否启用：
								</label>
								<div class="controls">
<%-- 								<input type="text" id="pushState" name="pushState" class="span13" placeholder="" value="${microbanner.pushState}"> --%>
								是<input type="radio" id="pushState" name="pushState" <c:if test="${microCatalog.pushState=='0'}">checked='checked'</c:if> placeholder="" value="0">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									否<input type="radio" id="pushState2" name="pushState"
										 <c:if test="${microCatalog.pushState == '1' || empty microCatalog.pushState}">checked='checked'</c:if> 
									 placeholder="" value="1">
								</div>
							</div>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${microCatalog.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		
		KindEditor.ready(function(K) {
			editor = K.create('textarea[id="content"]', {
				resizeType : 1,
				allowPreviewEmoticons : false,
				uploadJson       : '${pageContext.request.contextPath}/uploader/common?editor=editor&jsessionId=' + '<%=request.getSession().getId()%>',
				filePostName     : 'file',
				allowImageUpload : true,
				fillDescAfterUploadImage : true,
// 				height           : 600,
// 				minWidth         : 455,
				items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print', 'template', 'code', 'cut', 'copy', 'paste',
				          'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				          'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				          'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
				          'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				          'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image',
				          'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
				          'anchor', 'link', 'unlink' ]
			});
// 			uploadbutton.fileBox.change(function(e) {
// 			        uploadbutton.submit();
			        
// 			});
// 			K('input[name=getHtml]').click(function(e) {
// 				alert(editor.html());
// 			});
// 			K('input[name=isEmpty]').click(function(e) {
// 				alert(editor.isEmpty());
// 			});
// 			K('input[name=getText]').click(function(e) {
// 				alert(editor.text());
// 			});
// 			K('input[name=selectedHtml]').click(function(e) {
// 				alert(editor.selectedHtml());
// 			});
// 			K('input[name=setHtml]').click(function(e) {
// 				editor.html('<h3>Hello KindEditor</h3>');
// 			});
// 			K('input[name=setText]').click(function(e) {
// 				editor.text('<h3>Hello KindEditor</h3>');
// 			});
// 			K('input[name=insertHtml]').click(function(e) {
// 				editor.insertHtml('<strong>插入HTML</strong>');
// 			});
// 			K('input[name=appendHtml]').click(function(e) {
// 				editor.appendHtml('<strong>添加HTML</strong>');
// 			});
// 			K('input[name=clear]').click(function(e) {
// 				editor.html('');
// 			});
		});
	});
	
	//保存或更新修改
	function saveOrUpdate() {
		var error_text = "";
		var title = $.trim($("#title").val());
		var content = $.trim(editor.html());
// 		if(title.trim() === ""){
// 			$("#title_error").show();
// 		}else{
// 			$("#title_error").hide();
// 		}
// 		var content = editor.html();
// 		if(content.trim() === ""){
// 			$("#content_error").show();
// 		}else{
// 			$("#content_error").hide();
// 		}
// 		if(error_text != ""){
// 			return;
// 		}
// 		if (error_text === "") {
// 			if(content.trim() === ""){
// 				return;
// 			}
         if(title===""){
        	$.error("标题不能为空");
        	return false;
         }
        
         if(content===""){
         	$.error("内容不能为空");
         	return false;
          }
         if(title.length>20){
         	$.error("标题不能超过20个字");
         	return false;
          }
         if(content.length>5000){
          	$.error("内容不能超过5000个字");
          	return false;
           }
			var loader = new loadLayer();
			var $id = $("#id").val();
			var pushState = $("input[name=pushState]:checked").val();
// 			var $requestData = formData2JSONObj("#microcatalog_form");
			var $requestData = {}; 
			$requestData.content = content;
			$requestData.title = title;
			$requestData.pushState = pushState;
			var url = "${ctp}/wkx/microcatalog/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/wkx/microcatalog/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	
</script>
</html>