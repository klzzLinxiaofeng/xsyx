<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="content-widgets">
	<div class="select_top">
		<div class="s1 s1_bg" id="classMasterSearch">
			<select id="bj" class="span4 chzn-select" style="width: 120px;"
				onchange="search()"></select>
			<div class="content">
				<div class="click">
					<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
					<button class="btn btn-warning" type="button"
						onclick="loadCreatePage();">发布</button>
					</c:if>
				</div>
			</div>
		</div>
		<div class="s1 s1_bg" id="schoolManagerSearch">
			<div hidden>
				<select id="xn"></select>
			</div>
			<select id="nj" name="gradeId" style="width: 160px;"></select> <select
				id="bj" name="teamId" style="width: 160px;"></select>
			<button class="btn btn-success" style="margin-top: -23px;" id="sosuo"
				onclick="search()">搜索</button>
			<div class="content">
				<div class="click">
					<button class="btn btn-warning" type="button"
						onclick="loadCreatePage();">发布</button>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
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
					   search();
					}	
			   });
		}	
	});
</script>
