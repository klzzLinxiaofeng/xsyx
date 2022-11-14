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
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/address.js" ></script>
<title>查看角色权限</title>
<style>
.input_select .select_div {
    float: none; 
}
.table thead tr th:nth-child(2){
	padding-left: 33px;
}

</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  权限管理  >
        <c:choose>
        <c:when test="${condition.ownerId!=0}">学校权限管理</c:when>
        <c:otherwise>平台权限管理</c:otherwise>
        </c:choose>
        ><span>查看角色应用</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>查看角色应用</p></div>
			<button class="btn btn-lightGray" style="float: right;margin-top: 14px;margin-right: 10px;" onclick="javascript:history.back()"><i class="fa fa-arrow-left" ></i>返回</button>
		</div>
		<div class="input_select">
			<div class="select_div">
				<span>角色：</span>
				<select id="roleSelector" class="span2" onchange="list()">
					<c:forEach items="${roles}" var="role">
						<option value="${role.code}">${role.name}</option>
					</c:forEach>
				</select>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<span>应用名称：</span>
				<input id="applet_name" type="text"> <button onclick="list()" class="btn btn-blue">查询</button>
			</div>
		</div>
		<div class="input_select" style="border-top: solid 1px #e4e8eb;border-bottom: solid 1px #e4e8eb;line-height: 27px;">
			<span style="color:#666666;">角色可使用的应用：</span>
				<button class="btn btn-red" onclick=" delBatch()">批量移除</button>
				<button class="btn btn-blue fr addApply" onclick="addApplet(${condition.ownerId})">添加应用</button>
		</div>
		<table class="table" style="margin-bottom: 0px; ">
			<thead>
				<tr>
                    <th><i class="ck" style="top: -11px;"></i></th>
                    <th>图标</th><th>名称</th><th>状态</th><th>操作</th></tr>
			</thead>
			<tbody id="list_content">
			<jsp:include page="list.jsp"/>
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="list_content" />
				<jsp:param name="url" value="${ctp}/sys/appletRole/showRole?sub=list&ownerId=${condition.ownerId}&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
		
		<%--<div class="btn_cz" style="padding: 20px;border-top: 1px solid #dddddd;margin-top: 0px;">--%>
			<%--<button class="btn btn-blue">保存</button>--%>
		<%--</div>--%>
		
	</div>
</div>

<script>
$('body').on('click','.table tbody i.ck',function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
        if( $('tbody i.ck').length!=$('tbody i.ck.on').length){
            $('.table thead i.ck').removeClass('on');
        }
    }else{
        $(this).addClass('on');
        if($('tbody i.ck').length==$('tbody i.ck.on').length){
            $('.table thead i.ck').addClass('on');
        }
    }
})

$('.table thead i.ck').click(function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
        $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $('.ck').addClass('on');
    }
});

$(function(){
    //搜索enter
    $('body').on('keydown','#applet_name',function(event){
        if(event.keyCode==13){
            list();
        }
    })

    $("#roleSelector option:first ").prop("selected", 'selected');
    <c:if test="${sub!='list'}">
    list();
    </c:if>
})

var id = "list_content";
var url = "${ctp}/sys/appletRole/showRole?sub=list&ownerId=${condition.ownerId}&dm=${param.dm}";
var data = new Object();

function list() {
    data.appletName=$("#applet_name").val();
    data.roleCode=$("#roleSelector").val();
    myPagination(id, data, url);
}
	
function del(appletIds) {
    <c:if test="${sub eq list}"></c:if>
        var condition={};
    	condition.ownerId=${condition.ownerId};
    	condition.roleCode=$("#roleSelector").val();
        $.ajax({
            url: "${ctp}/sys/appletRole/roleDel",
            type: "POST",
            data: {
                "roleCode":condition.roleCode,
                "ownerId":condition.ownerId,
                "appletIds":appletIds
            },
            traditional: true,
            async: true,
            success: function(data) {
                $('.table thead .ck.on').removeClass('on');
                $.success("移除成功");
                parent.core_iframe.window.list();
            }
        });
}

function delBatch() {
    var appletIds=[];
    $(".ck.on").each(function(index, element) {
        var value = $(this).data("appletid");
        if(value!=null && "" !=value) {
            appletIds.push(value);
        }
    })
    if(appletIds.length==0) {
        $.error("请勾选需要移除的应用");
        return false;
    }
    del(appletIds);
}

function delSingle(appletId) {
    var appletIds=[];
    appletIds.push(appletId);
    del(appletIds);
}

function addApplet(ownerId) {
    var roleCode=$("#roleSelector").val();
    var url = '${ctp}/sys/appletRole/showApplets?ownerId=' + ownerId + '&roleCode=' +roleCode;
    $.initWinOnTopFromLeft_qyjx("添加应用", url, '600', '500');
}
</script>
</body>
</html>