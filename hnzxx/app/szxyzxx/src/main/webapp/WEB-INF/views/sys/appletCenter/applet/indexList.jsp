<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<c:forEach items="${appletVoList}" var ="applet">
					<c:choose>
						<c:when test="${applet.lineType == 0}">  
							<tr data-id="${applet.id}" id="${applet.id}_tr"><td><span class="tbbj "><!-- <i class="fa fa-table"></i> --><img src="${applet.iconUrl}"/></span></td><td class="applet_name">${applet.name }</td><td>${applet.appletKey }</td><td class="xj">${applet.lineTypeString }</td><td>${applet.registerTime }</td>
							<c:choose>
								<c:when test="${applet.sourceType == 1}"><td>官方</td></c:when>
								<c:otherwise><td>${applet.vendorName }</td></c:otherwise>
							</c:choose>
							<td class="caozuo"><button class="btn btn-blue" onclick="appletEdit(this);">编辑</button><button class="btn btn-green" onclick="toRole(this,${applet.id},0)">权限设置</button><button class="btn btn-peaGreen" onclick="appletManage(this);">管理</button><button class="btn btn-red">删除</button></td></tr>
						</c:when>
						<c:otherwise> 
							<tr data-id="${applet.id}" id="${applet.id}_tr"><td><span class="tbbj "><!-- <i class="fa fa-table"></i> --><img src="${applet.iconUrl}"/></span></td><td class="applet_name">${applet.name }</td><td>${applet.appletKey }</td><td class="sj">${applet.lineTypeString }</td><td>${applet.registerTime }</td>
							<c:choose>
								<c:when test="${applet.sourceType == 1}"><td>官方</td></c:when>
								<c:otherwise><td>${applet.vendorName }</td></c:otherwise>
							</c:choose>
							<td class="caozuo"><button class="btn btn-blue" onclick="appletEdit(this);">编辑</button><button class="btn btn-green" onclick="toRole(this,${applet.id},0)">权限设置</button><button class="btn btn-peaGreen" onclick="appletManage(this);">管理</button><button class="btn btn-red">删除</button></td></tr>
						</c:otherwise>
					</c:choose>
					<%-- <tr><td><span class="tbbj btn-orange"><i class="fa fa-table"></i></span></td><td>${applet.name }</td><td>${applet.appletKey }</td><td class="xj">${applet.lineTypeString }</td><td>${applet.registerTime }</td><td class="caozuo"><button class="btn btn-blue">编辑</button><button class="btn btn-green">权限设置</button><button class="btn btn-peaGreen">管理</button><button class="btn btn-red">删除</button></td></tr> --%>
</c:forEach>
<tr>
	<td style="padding:0;border:0 none; display:none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>

<script>
$(".btn-red").click(function(){
	var appletName = $(this).parent().siblings(".applet_name").text();
	$('.scts').children("p:first-child").children().text(appletName);
	var appletId = $(this).parent().parent().data("id");
	//alert($('.scts').children("p:first-child").children().text());
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['390px', '230px'],
		  title: '提示', //不显示标题
		  content: $('.scts'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定删除','取消'],//按钮
		  btn1: function(index, layero){
			  deleteApplet(layero,appletId);
		  }
	});
})
//执行删除
function deleteApplet(obj,id){
	$.post("${ctp}/sys/applet/" + id, {"_method" : "delete"},function(data, status) {
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

//编辑applet
function appletEdit(obj){
	var appletId = $(obj).parent().parent().data("id");
	window.location.href = "${ctp}/sys/applet/editor?id=" + appletId;
}
//管理applet
function appletManage(obj){
	var appletId = $(obj).parent().parent().data("id");
	window.location.href = "${ctp}/sys/applet/appletManage/editor?id=" + appletId;
}

function toRole(obj,appletId,ownerId){
	var title = $(obj).parent().siblings("td:nth-child(2)").text();
	//打开权限设置新窗口
	$.initWinOnTopFromLeft_qyjx("权限设置 - " + title, '${ctp}/sys/appletRole/editor?noList=true&ownerId='+ownerId+'&appletId='+appletId+'', '390', '400');
	
}

</script>