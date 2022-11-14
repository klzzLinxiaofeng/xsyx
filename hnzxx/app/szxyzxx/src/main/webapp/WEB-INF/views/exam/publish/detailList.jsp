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
					<img alt="头像" src="<avatar:avatar userId='${ur.userId}'/>"><p class="name" style="margin:0 0 10px 0;">${ur.userName}</p>
					<div class="task"><span class="Jobcontent">试卷：${publishedMicro}</p></div>
				</div>
				<div class="field">
					<textarea disabled="disabled" rows="" cols="">${ur.content}</textarea>
				</div>
				<div class="sheet">
					<table border="1" style="border: solid thin #d4d4d4;background-color: #fff;">
						<tr style="background-color: #f2f2f2;">
							<td>附件</td>
							<td>操作</td>
						</tr>
						<c:forEach items="${resultList}" var="result">
							<tr>
								<td><p>${result.entityFile.fileName}</p></td>
								
								<td><a class="btn" href="javascript:download('${result.entityFile.id}')">下载</a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
						</div>
					</div>
				</div>
			</div>
			</div>
</body>
<script type="text/javascript">
	function download(entityId){
// 		$.initWinOnTopFromLeft('下载','${pageContext.request.contextPath}/homeworkpublish/downloadFile?entityId=' + entityId, '620','590');
		var url =  '${pageContext.request.contextPath}/exampublish/downloadFile?entityId=' + entityId;
		window.open(url,"_blank");
	}
</script>
</html>