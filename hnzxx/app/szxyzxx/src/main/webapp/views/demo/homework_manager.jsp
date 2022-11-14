<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>作业管理</title>
</head>
<style type="text/css">
.time_sz img{
	 border-radius:50%;width:40px;float: left;  margin-right: 15px;
}
.name{
	line-height: 40px;color:#444;float: left;margin:0px;
}
.task{
	float:right;
}
.task p{
	float:left;margin:0px;line-height: 40px;
}
.task span{
	line-height: 40px;
}
.Jobcontent{
	float:left;
}
.field{
	margin-right:15px;
}
.field textarea{
	width:100%;height:70px;resize:none;color:#444;
}
.sheet{
	margin-top:5px;
}
.sheet table{
	width:100%;color:#444;
}
.sheet table tr{
	height:30px;
}
.sheet table tr td{
	padding-left:10px;
}
.sheet table tr td p{
	margin:0px;
}
.sheet table tr td button{
	margin:0px;
}
</style>
<body style="background-color: #F3F3F3 !important">
<div class="container-fluid">
		<div class="row-fluid ">
			<div class="span12">
				<div style="margin-bottom: 0" class="content-widgets">
				<div style="padding: 20px 0 0;" class="widget-container">
				<div class="time_sz">
					<img alt="" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"><p class="name" style="margin:0 0 10px 0;">张三</p>
					<div class="task"><span class="Jobcontent">作业1：</span><p>一年级上册《数一数》作业</p></div>
				</div>
				<div class="field">
					<textarea rows="" cols="">《史记》是我国第一部纪传体通史，记述了从传说中的黄帝到汉武帝时期的历史，反映了当时的社会风貌，成为后世纪传体史书的典范。《史记》文笔简洁，语言生动，刻画人物栩栩如生，又是一部优秀的文学著作。</textarea>
				</div>
				<div class="sheet">
					<table border="1" style="border: solid thin #d4d4d4;background-color: #fff;">
						<tr style="background-color: #f2f2f2;">
							<td>附件</td>
							<td>操作</td>
						</tr>
						<tr>
							<td><p>文档1.doc</p></td>
							<td><button class="btn" onclick="javascript:void(0)" type="button">下载</button></td>
						</tr>
						<tr>
							<td><p>文档1.doc</p></td>
							<td><button class="btn" onclick="javascript:void(0)" type="button">下载</button></td>
						</tr>
					</table>
				</div>
				<div class="form-actions tan_bottom">
<!-- 				<button class="btn btn-warning" onclick="javascript:void(0)" type="button">保存</button> -->
<!-- 					<button class="btn" onclick="javascript:void(0)" type="button">取消</button>  -->
				</div>
						</div>
					</div>
				</div>
			</div>
			</div>
</body>
<script type="text/javascript">
	
</script>
</html>