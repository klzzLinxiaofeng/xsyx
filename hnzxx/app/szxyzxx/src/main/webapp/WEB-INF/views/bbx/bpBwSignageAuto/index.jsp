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
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="content-widgets">
						<div class="select_top">
							<div class="s1 s1_bg" id="classMasterSearch">
								<select id="bj" class="span4 chzn-select" style="width: 120px;"
									onchange="search()"></select>
								<div class="content">
									<div class="click">
										<button class="btn btn-warning" type="button"
											onclick="loadCreatePage();">发布</button>
											
										<button class="btn btn-warning" type="button"
											onclick="pushService();">同步推送</button>
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
											
										<button class="btn btn-warning" type="button"
											onclick="pushService();">同步推送</button>
									</div>
								</div>
							</div>
						</div>	
					</div>	
				
					<!-- <div class="select_top">
						<div class="s1 s1_bg" id="schoolManagerSearch">
							<div hidden>
								<select id="xn"></select>
							</div>
							<span style="margin-top: -23px;">房型类型：</span>
							<select id="roomTypeCode" name="roomTypeCode" style="width:200px;">
								<option value="">请选择</option>
							</select>
							
							<span style="">房室:</span>
							<select id="room" name="room" style="width:200px;">
								<option value="">请选择</option>
							</select>				
							<a class="btn btn-warning" style="float: right;" href="javascript:void(0)" class="a4"
										onclick="loadCreatePage();">发布</a>
							<a class="btn btn-warning" style="float: right; display: none;" href="javascript:void(0)" class="a4" 
									onclick="pushService();" id="pushService">同步推送</a>
						</div>
					</div> -->
				
				</div>
				<div class="content-widgets white">
					<div class="content-widgets">
						<div class="widget-container">
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th width="8%">班级</th>
										<th width="10%">物理地址</th>
										<th width="8%">推送情况</th>
										<th width="9%">星期一</th>
										<th width="9%">星期二</th>
										<th width="9%">星期三</th>
										<th width="9%">星期四</th>
										<th width="9%">星期五</th>
										<th width="9%">星期六</th>
										<th width="9%">星期日</th>
										<th class="caozuo">操作</th>
									</tr>
								</thead>
								<tbody id="signageAuto_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="signageAuto_list_content" />
								<jsp:param name="url" value="/bbx/signageAuto/index?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
			</div>
			</div>
			
		</div>
	</div>
</body>
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

function search() {
	var val = {};
	var gradeId = $("#nj").val();
	var teamId = $("#bj").val();
	val.gradeId = gradeId;
	val.teamId = teamId;
	var id = "signageAuto_list_content";
	var url = "/bbx/signageAuto/index?sub=list&dm=${param.dm}";
	myPagination(id, val, url);
}

//	加载创建对话框
function loadCreatePage() {
	$.initWinOnTopFromLeft_bbx('定时开关机设置 ', '${ctp}/bbx/signageAuto/creator', '800', '580');
}


//加载编辑对话框
function loadEditPage(teamId) {
	$.initWinOnTopFromLeft_bbx('定时开关机设置', '${ctp}/bbx/signageAuto/editor?teamId=' + teamId, '800', '580');
}

function pushService() {
	var teamId = $("#bj").val();
	var gradeId = $("#nj").val();
	$.post("${pageContext.request.contextPath}/bbx/signageAuto/pushService", {teamId:teamId,gradeId:gradeId}, function(data, status) {
		if("success" === status) {
			if ( data.info === "success" ) {
				$.success("推送完成!");
			}
		} else {
			$.error("推送失败!");
		}
	},"json");
	
}



/* 
$.RoomType = function(selectId) {
	var selector = $(selectId);
	var initTypeCode = "${room.typeCode}";
	url = "${pageContext.request.contextPath}" + "/bbx/RoomType/getRoomTypeList";
	$.get(url,function(data){
		$.each(data, function (index, obj) {
			if ( initTypeCode == obj.code ) {
				selector.append("<option value='" + obj.code + "' selected='selected'>" + obj.name +"</option>" );
			} else {
				selector.append("<option value='" + obj.code + "'>" + obj.name +"</option>" );
			}
        });
	},"json");
}

$.Room = function(roomTypeCode) {
	var selector = $("#room");
	selector.html("");
	selector.append("<option value=''>请选择</option>");
	var initTypeCode = "${room.typeCode}";
	url = "${pageContext.request.contextPath}" + "/bbx/Room/getRoomListByRoomTypeCode";
	var $request = {};
	$request.typeCode = roomTypeCode;
	$.post(url, $request, function(data, status) {
		if("success" === status) {
			$.each(data, function (index, obj) {
				selector.append("<option value='" + obj.id + "'>" + obj.name +"</option>" );
	        });
		}
	},"json");
}

$(function() {
	$.RoomType("#roomTypeCode");
});

$("#roomTypeCode").change(function() {
	var roomTypeCode = $("#roomTypeCode").val();
	$.Room(roomTypeCode);
	if ( roomTypeCode == "PU_TONG_JIAO_SHI" ) {
		$("#pushService").show();
	}
	
	
});

$("#room").change(function() {
	search();
});


// 


$(function() {	  
	$.initCascadeSelector({
		"type" : "team",
		"teamCallback" : function($this) {
			// search();
		}
	});
});

	

	
	
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/signageAuto/viewer?id=' + id, '700', '350');
	}*/
	
	// 	删除对话框
	function deleteObj(obj, teamId) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, teamId);
		});
	}

	// 	执行删除
	function executeDel(obj, teamId) {
		$.post("${ctp}/bbx/signageAuto/delete" , {teamId:teamId}, function(data, status) {
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
</script>
</html>