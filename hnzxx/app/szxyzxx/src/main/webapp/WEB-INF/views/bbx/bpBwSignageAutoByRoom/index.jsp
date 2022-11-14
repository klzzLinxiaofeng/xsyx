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
							<div class="s1 s1_bg" id="schoolManagerSearch">
								<span style="margin-top: -23px;">教室类型：</span>
								<select id="roomTypeCode" name="roomTypeCode" style="width:200px;" onchange="getRoomList()">
									<option value="">所有类型</option>
								</select>
							
								<span style="padding-left: 10px">教室：</span>
								<select id="room" name="room" style="width:200px;">
									<option value="">所有教室</option>
								</select>	
									
								<a class="btn btn-success"  href="javascript:void(0)" class="a4" onclick="searchData();">查询</a>		
								
								
								<a class="btn btn-warning" style="float: right;" href="javascript:void(0)" class="a4" 
									onclick="loadCreatePage();">发布</a>
								<a class="btn btn-warning" style="float: right;" href="javascript:void(0)" class="a4"  
									onclick="pushService();" id="pushService">同步推送</a>
							</div>
						</div>
					</div>	
				
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
							<%--<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">--%>
								<%--<jsp:param name="id" value="signageAuto_list_content" />--%>
								<%--<jsp:param name="url" value="/bbx/signageAutoByRoom/index?sub=list&dm=${param.dm}" />--%>
								<%--<jsp:param name="pageSize" value="${page.pageSize}" />--%>
							<%--</jsp:include>--%>
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
		getRoomType();	
	});

	function getRoomType(){
		var selector = $("#roomTypeCode");
		var url = "${pageContext.request.contextPath}" + "/bbx/RoomType/getRoomTypeList";
		$.get(url,function(data){
			$.each(data, function (index, obj) {
				selector.append("<option value='" + obj.code + "'>" + obj.name +"</option>" );
	        });
		},"json");
	}
	
	function getRoomList(){
		var roomTypeCode = $("#roomTypeCode").val();
		if(roomTypeCode == ""){
			return;
		}
		var selector = $("#room");
		selector.html("");
		selector.append("<option value=''>所有教室</option>");
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
	
	function searchData() {
		var val = {};
		var roomTypeCode = $("#roomTypeCode").val();
		var roomId = $("#room").val();
		val.roomTypeCode = roomTypeCode;
		val.roomId = roomId;
		var id = "signageAuto_list_content";
		var url = "/bbx/signageAutoByRoom/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	function pushService() {
		var roomTypeCode = $("#roomTypeCode").val();
		var roomId = $("#room").val();
		$.post("${pageContext.request.contextPath}/bbx/signageAutoByRoom/pushService", {roomTypeCode:roomTypeCode,roomId:roomId}, function(data, status) {
			if("success" === status) {
				if ( data.info === "success" ) {
					$.success("推送完成!");
				}
			} else {
				$.error("推送失败!");
			}
		},"json");	
	}

	//	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft_bbx('定时开关机设置 ', '${ctp}/bbx/signageAutoByRoom/creator', '800', '580');
	}

	//加载编辑对话框
	function loadEditPage(roomId) {
		$.initWinOnTopFromLeft_bbx('定时开关机设置', '${ctp}/bbx/signageAutoByRoom/editor?roomId=' + roomId, '800', '580');
	}

// 	删除对话框
	function deleteObj(obj, roomId) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, roomId);
		});
	}

	// 	执行删除
	function executeDel(obj, roomId) {
		$.post("${ctp}/bbx/signageAutoByRoom/delete" , {roomId:roomId}, function(data, status) {
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