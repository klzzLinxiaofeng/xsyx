<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/uploadify.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/extra/add.css" >
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<style>
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.remove_fj{
	color:#666;
	margin-left:15px;
	font-size:16px;
	font-family:"微软雅黑";
	width:18px;
	height:18px;
	text-align:center;
	line-height:18px;
	display:inline-block;
}
.remove{
	margin-top: -35px;
}
.uploadify{
	position:absolute;
	opacity:0;
	left:0;
	top:0;
}
.uploadify-queue{width:80px;}
.fileName{
	width: 50px;
	height: 15px;
	overflow: hidden;
	display: inline-block;
}
.edit ul{
	padding:0;
}
 #a p{
	padding:0 0 10px 0;min-width:240px;
}
#a p a{
	font-size: 16px;
font-weight: bold;
}
.edit ul li{ width:100%;height:auto;}
.widget-container{ padding:0;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid" style="height:100%;">
	<div class="span12">
 		<div class="content-widgets" style="margin-bottom:0;height:100%;">
			<div class="widget-container" style="height:100%;">
				<form class="form-horizontal tan_form" id="bwexaminfo_form" action="javascript:void(0);">
					<div class="trend">
    					<div class="edit"  style="height:80%;">
    						<div class="control-group" style="padding:50 100px;">
								<span>选择科目组：
									<select id = "course">
										<c:forEach items="${list}" var="list">
											<option value ="${list.id }">${list.courseNames }</option>
										</c:forEach>
									</select>
								<div class="clear"></div>
							</div>	
						</div>
    					<div class="clear"></div>
    					<div class="form-actions tan_bottom">
							<input type="hidden" id="courseStudentId" value="${id }">
								<button class="btn btn-warning" type="button"
									onclick="changeEnroll();">确定</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
function changeEnroll(){
	var id = $("#courseStudentId").val();
	var passId = $("#course").find("option:selected").val();
	var loader = new loadLayer();
	var $requestData = {
				"passId":passId,
				"id":id
			};
	var url = "${pageContext.request.contextPath}/bbx/courseAdmission/changeEnroll";
	loader.show();
	$.post(url,$requestData,function(data,status){
		if(status === "success"){
			data = eval("(" + data + ")");
			if("success" === data.info) {
				$.success('操作成功');
				parent.core_iframe.search();
			}else {
				$.error("操作失败");
			}
			$.closeWindow();
		}
		loader.close();
	});
}

function outwindow(){
	var iddiv = $(window.parent.document).find('.layui-layer-shade').last().attr("times");
$(window.parent.document).find('#layui-layer-shade'+iddiv).remove();
$(window.parent.document).find('#layui-layer'+iddiv).remove();
}
</script>
</html>
