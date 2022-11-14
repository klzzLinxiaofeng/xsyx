<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
</head>

<style>
.name{
	display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;width:110px;
}
.texta{
	padding:0;
}
</style>
<body>
<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:0px;">
					<form class="form-horizontal" id="user_form">
						<input type="hidden" id="dutyDate" name="dutyDate" value="${dutyDate }" />
						<input type="hidden" id="teamId" name="teamId" value="${teamId }" />
						<div class="ziri-pop" style="height:303px;overflow:auto;">
							<div class="ziri-num" id="studentList">
<!-- 							    <span style="width:843px;color:#FF3300;font-size:18px;padding-left:84%; padding-bottom:60% ">最多选择6人</span> -->
							<c:forEach items="${studentVoList}" var="studentVo">
								<c:choose>
									<c:when test="${studentVo.userState=='0' }">
									<a href="javascript:void(0);" data-student-id="${studentVo.userId}" class="on">
										<span class="left stu-head"><img src="${studentVo.entityId}"/></span>
										<div class="left stu-info">
											<em class="name">${studentVo.name }</em>
											<p class="texta">值日次数：<b>${studentVo.sumNum } </b> 次</p>
										</div>
									</a>
									</c:when>
									<c:otherwise>
									<a href="javascript:void(0);" data-student-id="${studentVo.userId}" >
										<span class="left stu-head"><img src="${studentVo.entityId}"/></span>
										<div class="left stu-info">
											<em class="name">${studentVo.name }</em>
											<p class="texta" style="padding-top: 0;">值日次数：<b>${studentVo.sumNum } </b> 次</p>
										</div>
									</a>
									</c:otherwise>
								</c:choose>								
									
							</c:forEach>
								
							</div>
						</div>
						<div class="form-actions tan_bottom_1">
								<a href="javascript:void(0)" class="yellow" onclick="saveOrUpdate()">确定</a>
								<a href="javascript:void(0)" onclick="closeWin();">取消</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$(".ziri-num a").click(function(){
			$(this).toggleClass("on");
		})
	});
	
	//保存或更新修改
	function saveOrUpdate() {
		var jsonArray = [];
		var dutyDate = $("#dutyDate").val();
		var teamId = $("#teamId").val();
		var loader = new loadLayer();
		$("#studentList .on").each(function(){
			var curJson = {};
			curJson.studentId = $(this).attr("data-student-id");
			jsonArray.push(curJson);
		});
		var result = {};
		result.studentItems = jsonArray;
		var items = JSON.stringify(result);
		var $request = {};
		$request.items = items;
		$request.dutyDate = dutyDate;
		$request.teamId = teamId;
		//alert(JSON.stringify($request))
		var url = "${pageContext.request.contextPath}/clazz/teamDutyUser/saveDutyUser";
		if(jsonArray.length==0){
			$.error("还没选择值日生，请选择!");
		}
// 		else if(jsonArray.length>6){
// 			$.error("选择的值日生已超过6人，请重新选择!");
// 		}
		else{
			loader.show();
			 $.post(url, $request, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					loader.close();
					parent.core_iframe.search();
					$.closeWindow();
				}else{
					$.error("操作失败");
				}
			}); 
			
		}
	}
	
	function closeWin(){
		$.confirm("确定离开此页面？", function() {
			$.closeWindow();
		});
	}
</script>
</html>