<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>

<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="select_top">
					<div class="s1 s1_bg" id="classMasterSearch">
						<select id="roomTypeCode" name="roomTypeCode" class="span4 chzn-select" style="width: 200px">
							<option value="">请选择</option>
						</select>
						<select id="room" name="room" class="span4 chzn-select" style="width: 200px">
							<option value="">请选择</option>
						</select>
						<button type="button" class="btn btn-primary" onclick="search()">查询</button>
						<div class="content">
							<div class="click">
								<button class="btn btn-warning" type="button"
									onclick="loadCreatePage();">发布</button>
							</div>
						</div>
					</div>	
			</div>
			
			<div>
            	<div id="advert_list_content">
					<jsp:include page="./list.jsp" />
				</div>
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="advert_list_content" />
					<jsp:param name="url" value="/bbx/advert/index?sub=list&dm=${param.dm}" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>


<script type="text/javascript">
	$.RoomType = function(selectId) {
		var selector = $(selectId);
		selector.html("");
		selector.append("<option value=''>请选择</option>");
		var initTypeCode = "${room.typeCode}";
		url = "${pageContext.request.contextPath}" + "/bbx/RoomType/getRoomTypeList";
		$.get(url,function(data){
			$.each(data, function (index, obj) {
				if(obj.code != "PU_TONG_JIAO_SHI"){
					if ( initTypeCode == obj.code ) {
						selector.append("<option value='" + obj.code + "' selected='selected'>" + obj.name +"</option>" );
					} else {
						selector.append("<option value='" + obj.code + "'>" + obj.name +"</option>" );
					}
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
			console.log(status);
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
	});
	


	function search() {
		var val = {};
		var roomId = $("#room").val();
		if (roomId != null && roomId != "") {
			val.roomId = roomId;
		}
		var id = "advert_list_content";
		var url = "/bbx/advert/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var roomId = $("#room").val();
		if ( "" != roomId && roomId != null ) {
			$.initWinOnTopFromLeft('创建', '${ctp}/bbx/advert/creator?roomId='+roomId, '900', '600');
			return;
		}
		var roomTypeCode = $("#roomTypeCode").val();
		if(roomTypeCode != "" && roomTypeCode != null){
			$.initWinOnTopFromLeft('创建', '${ctp}/bbx/advert/creator?roomTypeCode='+roomTypeCode, '900', '600');
			return;
		}
		// $.initWinOnTopFromLeft('创建', '${ctp}/bbx/advert/creator?roomId='+roomId, '900', '600');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/advert/editor?id=' + id, '900', '700');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/advert/viewer?id=' + id, '900', '700');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/bbx/advert/" + id, {"_method" : "delete"}, function(data, status) {
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
</script>
</html>