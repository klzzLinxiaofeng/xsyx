<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<%-- <jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="欢迎模式" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include> --%>
		<div class="row-fluid">
			<div class="span12">
				<%@ include file="/views/embedded/bpCommonIndex.jsp" %>
				<%-- <jsp:include page="/views/embedded/bpCommonIndex.jsp"></jsp:include> --%>			
				<div>
					<div id="welcome_text_list_content">
						<%-- <jsp:include page="./list.jsp" /> --%>
					</div>
					<div class="clear"></div>
				</div>
	
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	/* $(function(){		
		alert('${sessionScope[sca:currentUserKey()].currentRoleCode}');		
		var currentRoleCode = '${sessionScope[sca:currentUserKey()].currentRoleCode}';
		if(currentRoleCode == "SCHOOL_LEADER"){
			$("#classMasterSearch").html("");
			$("#classMasterSearch").hide();		
			$.initCascadeSelector({
				"type" : "team",
				"teamCallback" : function($this) {
				}
			});		
		}else{
			$("#schoolManagerSearch").html("");		
			$("#schoolManagerSearch").hide();					
			var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
			$.BbxRoleTeamAccountSelector({
				   "selector" : "#bj",
				   "condition" : $requestData,
				   "selectedVal" : "",
				   "afterHandler" : function() {
					   search();
					}	
			   });
		}	
	});
	*/
	function search(){
		var teamId = $("#bj").val();
		if(teamId == ""){
			$.error("请选择班级");
			return;
		}	
		$("#welcome_text_list_content").load(
			'${ctp}/bbx/welcomeText/getWelcomeText',	
			{"teamId": teamId},
			function(){
	        	//loader.close();
	        }
		);
	} 
	
	// 执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/welcomeText/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					search();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft_bbx('创建', '${ctp}/bbx/welcomeText/creator', '700', '400');
	}
	
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/bpBwInfo/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/bpBwInfo/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	
</script>
</html>