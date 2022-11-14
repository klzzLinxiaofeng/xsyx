<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/new_add.css" rel="stylesheet">
<title>缺省角色菜单管理</title>
<style type="text/css">
#checkDiv label{
	display: block;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    padding:0 0 10px 15px;
}
#checkDiv{
	width: 309px;
    float: left;
    height: 550px;
    overflow-x: hidden;
    margin-bottom: 10px;
 	background-color: #fff;
 	padding-top:15px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param name="title" value="缺省角色菜单管理" />
			<jsp:param name="icon" value="icon-glass" />
			<jsp:param name="menuId" value="${param.dm}"  />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							缺省角色菜单管理
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新列表</a> 
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div id="checkDiv" >
								<label>
									<c:if test="${empty group}">
										<input id="checkAll" type="checkbox">全选
									</c:if>
								</label>
								<c:forEach items="${groupList}" var="group">
									<label>
										<input type="checkbox" name="ids" value="${group.id }" onclick="checkedGroup('${group.id}',this)">${group.name }
									</label>
								</c:forEach>
							</div>
							<div id="groupChecked" style="width:300px;height:565px;float:left;background-color: #fff;margin-left: 80px;overflow-x: hidden;"></div>
							<input type="button" value="同步" onclick="syncData()"/>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	var loader = new loadLayer("正在同步角色和菜单...",60000);
		$(function() {
			//全选
			$("#checkAll").on("click", function() {
				if(this.checked){
					$("#checkDiv input:checkbox[name='ids']").prop("checked", this.checked);
					$lab = $("#checkDiv input:checkbox[name='ids']")
					$("#groupChecked").empty();
					$lab.each(function(){
						var layer = "";
						layer = "<label data-id='"+$(this).val()+"'>";
						layer += $(this).parent().text()
						layer += "</label>";
						$(layer).appendTo($("#groupChecked"));
					});
				}else{
					$("#checkDiv input:checkbox[name='ids']").prop("checked", this.checked);
					$("#groupChecked").empty();
				}
			});
			
			//非超管
			schoolAdmin();
		});
		
		//非超管登录的时候
		function schoolAdmin(){
			if("${!empty group}"){
				var layer = "";
			 	layer = "<label data-id='${group.id}'>";
			 	layer += "${group.name}"
			  	layer += "</label>";
			  	$(layer).appendTo($("#groupChecked"));
			}
		}
		
		//点击事件
		function checkedGroup(id,obj){
			if(obj.checked){
			   var layer = "";
			   layer = "<label data-id='"+$(obj).val()+"'>";
			   layer += $(obj).parent().text()
			   layer += "</label>";
			   $(layer).appendTo($("#groupChecked"));
			}else{
				$("#groupChecked label[data-id='"+id+"']").remove();
			}
		}
		
		//获取需要同步的学校，可以进行批量，控制器支持接受逗号隔开的schoolId方式
		function getGroupId(){
			var groupIds = "";
			$lab = $('#groupChecked').children('label');
			$lab.each(function(){
				if(groupIds == ""){
					groupIds = $(this).data("id");
				}else{
					groupIds += "," + $(this).data("id");
				}
			});
			return groupIds;
		}
		
		//进行数据同步
		function syncData(){
			loader.show();
			var groupIds = getGroupId();
			if(groupIds == ""){
				$.alert("请选择学校");
				return;
			}
			var $requestData = {};
			$requestData.groupIds = groupIds;
			var url = "${pageContext.request.contextPath}/sys/user/syncDefRoleAndMenu";
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					data = eval("(" + data + ")");
					if(data.length > 0){
						var mes = "";
						for(var i = 0;i<data.length;i++){
							if(mes == ""){
								mes = data[i].name;
							}else{
								mes += "," + data[i].name;
							}
						}
						$.alert("本次操作失败的学校有：" + mes);
					}else{
						$.success('操作成功');
					}
				} else {
					$.error('操作失败');
				}
				loader.close();
			});
		}
	</script>
</html>