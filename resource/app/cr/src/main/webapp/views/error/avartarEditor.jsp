<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<img id="imgContent" src=""></img>
<input type="button" id="editor_btn" value="添加头像"></input>
<input type="hidden" id="zp_url" name="zp" >
</body>

<script type="text/javascript">
	(function($){
		$.createAvartarEditor = function (options) {
			var defaults = {
				"btn"      :  "#bzr",
				"zp_url"   :  "url",
				"img_tag"  :  "img_tag",
				"title"    :  "头像编辑",
				"width"    :  "750",
				"height"   :  "510",
				"top"      :  "20"
			};
			var settings = $.extend({}, defaults, options || {});
			var execute = function() {
				var btn = $(settings.btn);
				if(btn.length <= 0) {
					return;
				}
				btn.bind("click", function() {
					$.initWinOnCurFromTop(settings.title, 
							"${pageContext.request.contextPath}/common/avartar/editor?srcUrlTo=" + settings.zp_url + "&covertedTo=" + settings.img_tag, 
							settings.width, 
							settings.height, 
							settings.top);
					event.stopPropagation();
				});
			}
			execute();
		}
		
	})(jQuery); 
	
	$(function() {
		$.createAvartarEditor({
			"btn"      :  "#editor_btn",
			"zp_url"   :  "zp_url",
			"img_tag"  :  "imgContent"
		});
	});
</script>
</html>