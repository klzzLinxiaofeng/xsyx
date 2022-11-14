<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<body>
<div class="container-fluid">
        <div class="row-fluid" style="padding-bottom: 50px;">
            <div class="span12">
                <div class="content-widgets" style="margin-bottom:0">
						<div class="select_top">
						<div class="s1 s1_bg" id="classMasterSearch">
							<select id="bj" class="span4 chzn-select" style="width: 120px;"
								onchange="search()"></select>
							<input type="text" id="keyWord" placeholder="内容" style="margin-top: -15px;margin-left: 10px;">
							<button class="btn btn-success" style="margin-top: -23px;"
								id="sosuo" onclick="search()">搜索</button>
							<c:if test="${sessionScope[sca:currentUserKey()].currentRoleCode=='CLASS_MASTER'}">
                                <button class="btn btn-warning right" type="button" onclick="loadCreatePage();">添加动态</button>
                             </c:if>
							<!-- <div class="content">
								<div class="click">
									<button class="btn btn-warning" type="button"
										onclick="loadCreatePage();">添加动态</button>
								</div>
							</div> -->
						</div>
						<div class="s1 s1_bg" id="schoolManagerSearch">
							<div hidden>
								<select id="xn"></select>
							</div>
							<select id="nj" name="gradeId" style="width: 160px;"></select> 
							<select id="bj" name="teamId" style="width: 160px;"></select>
							<input type="text" id="keyWord" placeholder="内容" style="margin-top: -15px;margin-left: 10px;">
							<button class="btn btn-success" style="margin-top: -23px;"
								id="sosuo" onclick="search()">搜索</button>
                            <button style="" class="btn btn-warning right" type="button" onclick="loadCreatePage();">添加动态</button>
							<!-- <div class="content">
								<div class="click">
									<button class="btn btn-warning" type="button"
										onclick="loadCreatePage();">发布</button>
								</div>
							</div> -->
						</div>
						<%-- <div class="s1 s1_bg">
							<select id="teamId" onchange="switchTeam();" name="teamId" class="chzn-select" style="width: 120px;">
                        	</select>
							<div class="search">
							<input type="text" id="keyWord" placeholder="内容">
								<button class="btn btn-success" type="button" onclick="search();">搜索</button>
                                <c:if test="${sessionScope[sca:currentUserKey()].currentRoleCode=='CLASS_MASTER'}">
                                <button class="btn btn-warning" type="button" onclick="loadCreatePage();">添加动态</button>
                                </c:if>
							</div>
						</div> --%>
					</div>
                    <%-- <div class="content-widgets">
                        <img src="${pageContext.request.contextPath}/res/css/bbx/images/icon.jpg" class="zp">
                        <p>动态总数：<span  class="number">100</span> 条 &nbsp  照片：<span  class="number">260</span> 张</p>
                    </div> --%>
                </div>
            </div>
            				<div>
            				<div id="circleMessage_list_content">
									<%-- <jsp:include page="./list.jsp" /> --%>
							</div>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="circleMessage_list_content" />
								<jsp:param name="url" value="/bbx/circleMessage/index?sub=list&dm=${param.dm}&circleId=${circleId}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
							</div>
        </div>
    </div>
</body>
<script type="text/javascript">
	function search() {
		var val = {};
		var keyWord = $("#keyWord").val();
		if (keyWord != null && keyWord != "") {
			val.content = keyWord;
		}
		var selectedOption = $("#bj").find("option:selected");
		if(selectedOption.val()!=''){// 选择一个班级
			val.teamId = selectedOption.val();
			val.teamName = selectedOption.text();
		}else{// 全部班级
			$.error("请选择班级");
			return;
			/* var options = $("#bj").find("option");
			var teamIds = [];
			for (var i = 0; i < options.length; i++) {
				var teamId = options[i].value;
				if(teamId!=undefined && teamId!=''){
					teamIds.push(teamId);
				}
			}
			val.teamIds = teamIds.join(','); */
		}
		var id = "circleMessage_list_content";
		var url = "/bbx/circleMessage/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#bj").find("option:selected").val();
		if(teamId == "" || teamId == null){
			$.error("请选择班级");
			return;
		}
		if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级.", 1);
		}else
		$.initWinOnTopFromLeft_bbx('添加动态', '${ctp}/bbx/circleMessage/creator?teamId='+teamId, '730', '480');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft_bbx('编辑', '${ctp}/bbx/circleMessage/editor?id=' + id, '730', '480');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft_bbx('详情', '${ctp}/bbx/circleMessage/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/circleMessage/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$('#count').text($('#count').text()-1);
					search();
// 					$(obj).closest('li').remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	function switchTeam(){
		$("#keyWord").val('');
		search();
	}
	
	$(function(){
		//初始化年级班级下拉框	
	  	/* $.initNBSCascadeSelector({"type" : "team",
	  		gradeSelectId : "gradeId",
			teamSelectId : "teamId",
	  		selectOne : false,
	  		condition:{schoolId:'${sessionScope[sca:currentUserKey()].schoolId}'},
	  		condition:{schoolYear:'${sessionScope[sca:currentUserKey()].schoolYear}'},
	  		gradeFirstOptTitle : "全部年级",
			teamFirstOptTitle : "全部班级",
				gradeCallback : function() {alert(1);},
				teamCallback : function() {search();},

	  	}); */
	  	
// 	  	$(function(){
// 	 	   $.BbxRoleTeamAccountSelector({
// 	 		   "selector" : "#teamId",
// 	 		   "condition" : {roleType:"CLASS_MASTER"},
// 	 		   "selectedVal" : "",
// 	 		   "afterHandler" : function() {
// 	 			   search();
// 	 			}	
// 	 	   });
	 	   
// 	    });

	  	
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
		/* $.BbxRoleTeamAccountSelector({
			   "selector" : "#teamId",
			   "selectOne" : false,
			   "condition" : {roleType:"${sessionScope[sca:currentUserKey()].currentRoleCode}"},
			   "selectedVal" : "",
			   "firstOptionTitle" : "全部班级",
			   "afterHandler" : function() {
				   search();
				}	
		}); */
		// 给搜索框绑定回车事件
		$('#keyWord').keydown(function(e){
			if(e.which==13){
				e.preventDefault();//取消回车键原有事件。
				search();
			}
		}); 
	});
	
</script>
</html>