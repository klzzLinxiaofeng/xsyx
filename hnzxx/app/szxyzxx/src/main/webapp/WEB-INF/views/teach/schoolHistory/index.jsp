<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title>${schoolList[0].name}历史沿革</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/data_statistics.css">
<script src="${pageContext.request.contextPath}/res/js/common/plugin/editor/kindeditor-min.js"></script>
<style>
	.title{
		    text-align: center;
		    border: 1px solid #999999;
		    margin: 20px 170px;
		    font-size: 18px;
		    padding: 10px;
		    font-weight: bold;
    		background: #fff;
	}
	.content .ke-container-default{
    	margin: 0 auto;
	}
	.tan_bottom {
		   background-image: none;
		   border-top: none;
	}
	
</style> 
</head>
<body>
<div>
	<form class="form-horizontal tan_form" id="studenthistory_form" action="javascript:void(0);">
	    <div class="title">
	    	${schoolList[0].name}历史沿革
	    </div>
	    
	    <div class="content">
	    	<%-- <input type="text" id="remark" name="remark" value="${schoolList[0].remark}"/> --%>
	    	<%-- <textarea id="remark" name="remark" rows="8" cols="120" style="height: 570px; margin: 0px;  width: 80% " class="tinymce-simple">${schoolList[0].remark}</textarea> --%>
	    	<textarea id="remark" name="remark" style="width:80%;height:600px;display: block;" class="tinymce-simple">${schoolList[0].remark}</textarea>
	    </div>
	    <div class="form-actions tan_bottom">
			<input type="hidden" id="id" name="id" value="${schoolList[0].id}" />
			<button class="btn btn-warning" type="button"
			onclick="saveOrUpdate();" style="background: #0088cc;">提交</button>
		</div>
	</form>    
</div>

</body>
<script type="text/javascript">
	
	/* //简单模式初始化
	var editor;
	KindEditor.ready(function(K) {
	    editor = K.create('textarea[name="remark"]', {
	        resizeType : 1,
	        allowPreviewEmoticons : false,
	        allowImageUpload : false,
	        items : [
	            'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
	            'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
	            'insertunorderedlist', '|', 'emoticons', 'image', 'link']
	    });
	}); */
	//默认模式
	 var editor;
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="remark"]', {
            allowFileManager : true ,
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

	//保存或更新修改
	function saveOrUpdate() {
		var loader = new loadLayer();
		//var $id = $("#id").val();
		var remark1 = $("#remark").prev().children(".ke-edit").children("iframe").contents().find("body").html();
		var $requestData = formData2JSONObj("#studenthistory_form");
		$requestData.remark = remark1;
		/* var url = "${ctp}/teach/employment/creator";
		if ("" != $id) {
			$requestData._method = "put";
			url = "${ctp}/teach/employment/" + $id;
		} */
		$requestData._method = "put";
		url = "${ctp}/school/history/edit"
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					window.location.reload();
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