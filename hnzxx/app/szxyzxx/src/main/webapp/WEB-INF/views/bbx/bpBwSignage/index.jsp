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
				<div class="select_top">
					<div class="s1 s1_bg" id="schoolManagerSearch">
						<div hidden><select id="xn"></select></div>
						<select id="roomType" name="roomType" style="width:160px;" onchange="getRoomList()">
							<option value="">请选择</option>
						</select>
						<select id="roomId" name="roomId" style="width:160px;">
							<option value="">请选择</option>
						</select>
						<button class="btn btn-success" id="sosuo" onclick="search()" >查询</button>
						<button class="btn btn-success" id="sosuo" onclick="check()" >检查在线</button>
						<button class="btn btn-success" id="sosuo" onclick="clearCache()" >清除缓存</button>
						<div class="content">
							<div class="click">
								<button class="btn btn-warning" type="button"
										onclick="$.refreshWin();">刷新列表</button>
							</div>
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
								<th>设备ID</th>
								<th>设备号</th>
								<th>学校</th>
								<th>区域</th>
								<th>教室</th>
								<th>在线</th>
								<th>版本号</th>
								<!-- <th>创建时间</th>
                                <th>修改时间</th> -->
								<th class="caozuo" style="max-width: 250px;">操作</th>
							</tr>
							</thead>
							<tbody id="bwSignage_list_content">
							<jsp:include page="./list.jsp" />
							</tbody>
						</table>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="bwSignage_list_content" />
							<jsp:param name="url" value="/bbx/bpBwSignage/index?sub=list&dm=${param.dm}" />
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
    $(function() {
        getRoomTypeList();
        search();
    });

    function getRoomTypeList(){
        $.get(
            "${ctp}/bbx/RoomType/getRoomTypeList", {},
            function(data) {
                var obj = eval("("+data+")");
                for(var i=0; i<obj.length; i++){
                    //if(obj[i].code != "PU_TONG_JIAO_SHI"){
                    $("#roomType").append("<option value=\"" + obj[i].code + "\">" + obj[i].name +"</option>");
                    //}

                }
            }
        );
    }

    function getRoomList(){
        var roomType = $("#roomType").val();
        if(roomType == null || roomType == ""){
            return;
        }
        $.post(
            "${ctp}/bbx/Room/getRoomListByRoomTypeCode", {"typeCode": roomType},
            function(data) {
                var obj = eval("("+data+")");
                $("#roomId").html("<option value=\"\">请选择</option>");
                for(var i=0; i<obj.length; i++){
                    $("#roomId").append("<option value=\"" + obj[i].id + "\">" + obj[i].name +"</option>");
                }
            }
        );
    }


    function search() {
        var val = {};
        var roomType = $("#roomType").val();
        var roomId = $("#roomId").val();
        if (roomType != null && roomType != "") {
            val.roomType = roomType;
        }
        if (roomId != null && roomId != "") {
            val.roomId = roomId;
        }
        var id = "bwSignage_list_content";
        var url = "/bbx/bpBwSignage/index?sub=list&dm=${param.dm}";
        myPagination(id, val, url);
    }

    function check(){
        var loader = new loadLayer();
        var roomType = $("#roomType").val();
        var roomId = $("#roomId").val();
        if (roomType == null || roomType === "") {
            $.error("请选择房室类型!");
        }
        var $requestData = {};
        $requestData.roomType = roomType;
        $requestData.roomId = roomId;
        var url = "${ctp}/bbx/bpBwSignage/check";
        loader.show();
        $.post(url, $requestData, function(data, status) {
            if("success" === status) {
                $.success('操作成功');
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    loader.close();
                    search();
                } else {
                    $.error("操作失败");
                }
            }else{
                $.error("操作失败");
            }
        });
    }

    function clearCache(){
        var loader = new loadLayer();
        var roomType = $("#roomType").val();
        var roomId = $("#roomId").val();
        if (roomType == null || roomType === "") {
            $.error("请选择房室类型!");
        }
        var $requestData = {};
        $requestData.roomType = roomType;
        $requestData.roomId = roomId;
        var url = "${ctp}/bbx/bpBwSignage/clear";
        loader.show();
        $.post(url, $requestData, function(data, status) {
            if("success" === status) {
                $.success('操作成功');
                data = eval("(" + data + ")");
                if("success" === data.info) {
                    loader.close();
                } else {
                    $.error("操作失败");
                }
            }else{
                $.error("操作失败");
            }
        });
    }












    /* // 	加载创建对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('创建', '${ctp}/bbx/bpBwSignage/creator', '700', '290');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/bpBwSignage/editor?id=' + id, '700', '300');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/bpBwSignage/viewer?id=' + id, '700', '300');
	} */

    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function() {
            executeDel(obj, id);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/bbx/bpBwSignage/" + id, {"_method" : "delete"}, function(data, status) {
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