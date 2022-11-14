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
			<jsp:param value="班级形象" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include> --%>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="widget-head">
						<div class="widget-head">
							<div class="select_top">
								<div class="s1 s1_bg" id="classMasterSearch">
									<select id="bj" class="span4 chzn-select" style="width: 120px;"
										onchange="getTeamImage();"></select>
								</div>
								<div class="s1 s1_bg" id="schoolManagerSearch">
									<div hidden>
										<select id="xn"></select>
									</div>
									<select id="nj" name="gradeId" style="width: 160px;"></select>
									<select id="bj" name="teamId" style="width: 160px;"></select>
									<button class="btn btn-success" style="margin-top: -23px;"
										id="sosuo" onclick="getTeamImage();">搜索</button>
								</div>
							</div>
							<!-- <div class="select_top">
								<div class="s1 s1_bg" id="classMasterSearch">
									<select  id="bj" class="span4 chzn-select" style="width: 120px;" onchange="getTeamImage()"></select>							
								</div>
							</div> -->
						</div>
					</div>					
					<div class="light_grey"></div>				
					<div id="teamImage_content"></div>
				</div>				
			</div>			
		</div>
	</div>
</body>
<script type="text/javascript">
	/* $(function(){				
		var $requestData = {"roleType" : "${sessionScope[sca:currentUserKey()].currentRoleCode}"};
		$.BbxRoleTeamAccountSelector({
			   "selector" : "#bj",
			   "condition" : $requestData,
			   "selectedVal" : "",
			   "afterHandler" : function() {
				   getTeamImage();
				}	
		});
	}); */
	
	function getTeamImage(){
		var teamId = $("#bj").val();
		if(teamId == ""){
			$.error("请选择班级");
			return;
		}	
		$("#teamImage_content").load(
			'${ctp}/bbx/teamImage/getDescription',	
			{"teamId": teamId},
			function(){
	        	//loader.close();
	        }
		);
	}
	
	$(function(){		
		// alert('${sessionScope[sca:currentUserKey()].currentRoleCode}');		
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
					   getTeamImage();
					}	
			   });
		}	
	});
</script>
</html>