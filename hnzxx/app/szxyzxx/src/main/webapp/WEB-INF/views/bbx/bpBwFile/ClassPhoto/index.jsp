<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/extra/add.css">
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<%-- <jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="班级相册" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include> --%>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="widget-head">
						<div class="widget-head">
							<div class="select_top">
								<div class="s1 s1_bg">
									<select  id="teamId" class="span4 chzn-select" style="width: 120px;" onchange="search()"></select>	
									<div class="click">
										<button class="btn btn-warning" type="button" onclick="loadCreatePage();">上传班级图片</button>
									</div>
								</div>
							</div>
						</div>												
					</div>			
				</div>
			</div>
			
			<div>			
				<div id="bwFile_list_content">
					<jsp:include page="./list.jsp" />
				</div>
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="bwFile_list_content" />
					<jsp:param name="url" value="/bbx/bpBwFile/ClassPhoto/index?sub=list&dm=${param.dm}" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>	
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
	$.BbxRoleTeamAccountSelector({
		   "selector" : "#teamId",
		   "condition" : $requestData,
		   "selectedVal" : "",
		   "afterHandler" : function() {
			   search();
			}	
	   });
});

function search() {
	var val = {};
	var teamId = $("#teamId").val();
	if (teamId != null && teamId != "") {
		val.teamId = teamId;
	}
	var id = "bwFile_list_content";
	var url = "/bbx/bpBwFile/ClassPhoto/index?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
}

/* $(function(){
	$.ajax({
        url: "${pageContext.request.contextPath}/bbx/bpBwFile/ClassVideo/getTeamList",
        type: "GET",
        data: {
        },
        async: false,
        success: function(data) {
       	 var list = JSON.parse(data);
      		 $("#teamId").empty();
       	 if ( list.length > 0 ) {
       		 for(var item in list) {
       			 $("#teamId").append("<option value='"+list[item].id+"'>"+list[item].name+"</option>");
       		 }
       	 } else {
       		 $("#teamId").append("<option>该用户没有从属的班级</option>");
       	 }
        }
    }); 
}); */

/* $("#teamId").change(function(){
	var val = {};
	var teamId = $("#teamId").val();
	if (teamId != null && teamId != "") {
		val.teamId = teamId;
	}
	var id = "bwFile_list_content";
	var url = "/bbx/bpBwFile/ClassPhoto/index?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
	
}); */

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#teamId").val();
		$.initWinOnTopFromLeft_bbx('上传班级图片', '${ctp}/bbx/bpBwFile/ClassPhoto/creator?teamId='+teamId, '900', '700');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/bpBwFile/ClassPhoto/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/bpBwFile/ClassPhoto/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/bpBwFile/ClassPhoto/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
//	图片展示
	
	layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	    layer.photos({
	    	shade: 0.5,
	        photos: '#layer-photos-demo'
	    });
	});
</script>
</html>