<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<c:forEach items="${appletList}" var ="applet">
	<c:choose>
		<c:when test="${applet.lineType == 0}">  
			<tr data-id="${applet.ownerId}" id="${applet.ownerId}_tr"><td><i class="ck"></i></td><td><span class="tbbj "><!-- <i class="fa fa-table"></i> --><img src="${applet.iconUrl}"/></span></td><td class="applet_name">${applet.name }</td><td class="xj">${applet.lineTypeString }</td><td>${applet.registerTime }</td><td class="caozuo"><button class="btn btn-green" onclick="toRole(this,${applet.id},${applet.schoolId})">权限设置</button><button class="btn btn-orange" onclick="addApplet(this)">上架</button><button class="btn btn-red" onclick="delApplet(this);">删除</button></td></tr>
		</c:when>
		<c:otherwise> 
			<tr data-id="${applet.ownerId}" id="${applet.ownerId}_tr"><td><i class="ck"></i></td><td><span class="tbbj "><!-- <i class="fa fa-table"></i> --><img src="${applet.iconUrl}"/></span></td><td class="applet_name">${applet.name }</td><td class="sj">${applet.lineTypeString }</td><td>${applet.registerTime }</td><td class="caozuo"><button class="btn btn-green" onclick="toRole(this,${applet.id},${applet.schoolId})">权限设置</button><!-- <button class="btn btn-orange" onclick="addApplet(this)">上架</button> --><button class="btn btn-red" onclick="delApplet(this);">删除</button></td></tr>
		</c:otherwise>
	</c:choose>
</c:forEach>
<tr>
	<td style="padding:0;border:0 none; display:none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>

<script>
$(function() { 
	$("#sch").text( "${school.name}");
	$("#title_province").text( "${school.province}");
	$("#title_city").text( "${school.city}");
	$("#title_district").text( "${school.district}");
	$("#sch").attr("data-ownerId",'${school.id}');
	/* $("#sch").text( "${appletList[0].schoolName}");
	$("#title_province").text( "${appletList[0].province}");
	$("#title_city").text( "${appletList[0].city}");
	$("#title_district").text( "${appletList[0].district}");
	$("#sch").attr("data-ownerId",'${appletList[0].schoolId}'); */
	//$("#sch").attr("data-ownerId",'${appletList[0].ownerId}');
});

function addSchoolApplet(){
	var ownerIdArray = new Array();
	$('tbody i.ck.on').each(function(index){
		ownerIdArray[index] = $(this).parent().parent().data("id");
	});
	$.post("${ctp}/sys/applet/addSchoolApplet/" + '${appletId}' + "/" + ownerIdArray, {"_method" : "put"},function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("上架成功");
				//parent.window.location.reload();
				if(parent.core_iframe != null) {
						parent.core_iframe.window.reload();
				}else {
					parent.window.reload();
				}
			} else if ("fail" === data) {
				$.error("上架失败，系统异常", 1);
			}
		}
	});
}

function addApplet(obj){
	var ownerId = $(obj).parent().parent().data("id");
	$.post("${ctp}/sys/applet/school/upOwner/" + ownerId, {"_method" : "put"},function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("上架成功");
				//parent.window.location.reload();
				//parent.core_iframe.window.search();
				if(parent.core_iframe != null) {
					parent.core_iframe.window.search();
				}else {
					parent.window.search();
				}
			} else if ("fail" === data) {
				$.error("上架失败，系统异常", 1);
			}
		}
	});
}

function delApplet(obj){
	//ownerId 是 appletowner的id
	var ownerId = $(obj).parent().parent().data("id");
	$.post("${ctp}/sys/applet/school/deleteOwner/" + ownerId, {"_method" : "delete"},function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("删除成功");
				//parent.core_iframe.window.search();
				if(parent.core_iframe != null) {
					parent.core_iframe.window.search();
				}else {
					parent.window.search();
				}
			} else if ("fail" === data) {
				$.error("删除失败，系统异常", 1);
			}
		}
	});
}

function toRole(obj,appletId,ownerId){
	var title = $(obj).parent().siblings("td:nth-child(3)").text();
    $.initWinOnTopFromLeft_qyjx("权限设置 - "+ title, '${ctp}/sys/appletRole/editor?noList=true&ownerId='+ownerId+'&appletId='+appletId+'', '390', '400');
}

</script>