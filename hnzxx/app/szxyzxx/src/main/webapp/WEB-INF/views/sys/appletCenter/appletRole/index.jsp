<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>
	<c:choose>
		<c:when test="${type=='school'}">学校权限管理</c:when>
		<c:otherwise>平台权限管理</c:otherwise>
	</c:choose>
</title>
<style>
.table thead tr th:first-child{
	padding-left: 33px;
}
.chzn-container-single .chzn-single{
	border-radius: 4px;
    border: solid 1px #dcdcdc;
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心 > 权限管理 >
		<span>
		<c:choose>
			<c:when test="${type=='school'}">学校权限管理</c:when>
			<c:otherwise>平台权限管理</c:otherwise>
		</c:choose>
	</span>
	</p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>
				<c:choose>
					<c:when test="${type=='school'}">学校权限管理</c:when>
					<c:otherwise>平台权限管理</c:otherwise>
				</c:choose>
			</p></div>
		</div>
		<%--<div class="input_select">--%>
			<%--<div class="select_div">--%>
				<%--<span>主应用：</span>--%>
				<%--<select class="span2"><option>定邦教育云</option></select>--%>
			<%--</div>--%>
		<%--</div>--%>
		<c:if test="${type=='school' && isSuperAdmin==true}" >
			<div class="input_select" style="padding: 5px;">
				<div class="select_div" style="float:left">
					<span style="float: left;line-height: 31px;">学校：</span>
					<select id="schoolSelector" onchange="list(this)"></select>
					<%--<input type="text">--%>
				</div>
				<%--<div class="btn_link" style="margin:5px 5px 0 0;">--%>
					<%--<button class="btn btn-blue" style="margin-top: 8px;">搜索</button>--%>
				<%--</div>--%>
			</div>
		</c:if>
		<div class="input_select" style="border-top: solid 1px #e4e8eb;border-bottom: solid 1px #e4e8eb;line-height: 27px;">
			<span style="color:#666666;">应用名称：</span>
			<input id="applet_name" type="text"> <button onclick="list()" class="btn btn-blue">查询</button>
			<button class="btn btn-peaGreen fr" onclick="showRole()">查看角色应用</button>
		</div>
		<table class="table">
			<thead>
				<tr><th>图标</th><th>名称</th><th>状态</th><th>适用角色</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody id="list_content">
			<jsp:include page="list.jsp"/>
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="list_content" />
				<jsp:param name="url" value="/sys/appletRole/index?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
</div>

<script>
    var i = 0;
    $(function () {
        //搜索enter
        $('body').on('keydown','#applet_name',function(event){
            if(event.keyCode==13){
                list();
            }
        })
        <c:choose>
        <c:when test="${type=='school' && isSuperAdmin==true}">
        $.SchoolSelector({
            "selector": "#schoolSelector",
            "isShowfirstOptionTitle": false,
            "afterHandler": function () {
                $("#schoolSelector option:first ").prop("selected", 'selected');
                <c:if test="${sub!='list'}">
                list();
                </c:if>
                // data.ownerId = $("#schoolSelector").val();
                // alert( $("#schoolSelector option ").eq(0).val());
            },
        });
        </c:when>
        <c:otherwise>
        list();
        </c:otherwise>
        </c:choose>
    })

	function qxsz(obj,appletId,ownerId) {
        var title = $(obj).parent().siblings("td:nth-child(2)").text();
        // var appletId=$(obj).data("appletId");
        // var appletId=id;
        $.initWinOnTopFromLeft_qyjx("权限设置 - "+ title, '${ctp}/sys/appletRole/editor?ownerId='+ownerId+'&appletId='+appletId+'', '390', '400');
    }

    var id = "list_content";
    var url = "/sys/appletRole/index?sub=list&dm=${param.dm}";
    var data = new Object();

    var i = 0;

    function list() {
        <c:if test="${type=='school' && isSuperAdmin==true}" >
        data.ownerId = $("#schoolSelector").val();
        console.log("ownerId   :"+data.ownerId);
        </c:if>
		data.appletName=$("#applet_name").val();
        data.type = "${type}";
        console.log("id:" + id);
        console.log("url:" + id);
        console.log("data:" + JSON.stringify(data));
        myPagination(id, data, url);
    }

    function showRole() {
        <c:choose>
        <c:when test="${type=='school' && isSuperAdmin==true}" >
        window.location.href = "${ctp}/sys/appletRole/showRole?ownerId=" + data.ownerId + "";
        </c:when>
        <c:otherwise>
        window.location.href = "${ctp}/sys/appletRole/showRole";
        </c:otherwise>
        </c:choose>
    }

</script>
</body>
</html>