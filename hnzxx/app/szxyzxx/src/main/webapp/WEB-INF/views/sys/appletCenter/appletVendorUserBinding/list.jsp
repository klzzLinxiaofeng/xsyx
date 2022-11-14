<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<c:forEach items="${vendorBindingList}" var ="bind">
	<%-- <c:choose>
		<c:when test="${applet.lineType == 0}">  
			<tr data-id="${applet.ownerId}" id="${applet.ownerId}_tr"><td><i class="ck"></i></td><td><span class="tbbj "><!-- <i class="fa fa-table"></i> --><img src="${applet.iconUrl}"/></span></td><td class="applet_name">${applet.name }</td><td class="xj">${applet.lineTypeString }</td><td>${applet.registerTime }</td><td>${applet.registerTime }</td><td class="caozuo"><button class="btn btn-green" onclick="toRole(this,${applet.id},${applet.schoolId})">权限设置</button><button class="btn btn-orange" onclick="addApplet(this)">上架</button><button class="btn btn-red" onclick="delApplet(this);">删除</button></td></tr>
		</c:when>
		<c:otherwise> 
			<tr data-id="${applet.ownerId}" id="${applet.ownerId}_tr"><td><i class="ck"></i></td><td><span class="tbbj "><!-- <i class="fa fa-table"></i> --><img src="${applet.iconUrl}"/></span></td><td class="applet_name">${applet.name }</td><td class="sj">${applet.lineTypeString }</td><td>${applet.registerTime }</td><td>${applet.registerTime }</td><td class="caozuo"><button class="btn btn-green" onclick="toRole(this,${applet.id},${applet.schoolId})">权限设置</button><!-- <button class="btn btn-orange" onclick="addApplet(this)">上架</button> --><button class="btn btn-red" onclick="delApplet(this);">删除</button></td></tr>
		</c:otherwise>
	</c:choose> --%>
	<c:choose>
		<c:when test="${bind.id != null}">
			<tr data-id="${bind.userId}" id="${bind.userId}_tr"><td><i></i></td><td class="user_name">${bind.userName}</td><td class="user_role">${bind.roleString}</td><td class="u_name">${bind.name}</td><td class="bind_account">${bind.vendorUserName}</td>
			<td class="sj">已绑定</td><td class="caozuo"><button class="btn btn-green" onclick='check("${bind.vendorUserName}","${bind.userName}","${bind.vendorPassword}")'>查看</button><button class="btn btn-grey" onclick="unBind(this)">解除绑定</button></td></tr>
		</c:when>
		<c:otherwise>
			<tr data-id="${bind.userId}" id="${bind.userId}_tr"><td><i class="ck"></i></td><td class="user_name">${bind.userName}</td><td class="user_role">${bind.roleString}</td><td class="u_name">${bind.name}</td><td class="bind_account">${bind.vendorUserName}</td>
			<td>未绑定</td><td class="caozuo"><button class="btn btn-orange" onclick='band(this);'>绑定</button></td></tr>
		</c:otherwise> 
	</c:choose>
	<%-- <td class="caozuo"><button class="btn btn-green" onclick="toRole(this,${applet.id},${applet.schoolId})">权限设置</button><button class="btn btn-orange" onclick="addApplet(this)">上架</button><button class="btn btn-red" onclick="delApplet(this);">删除</button></td></tr> --%>
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
	
	//初始化角色
	var roles = $("#roles");
	roles.text("");
	roles.append('<option data-roleid="">请选择</option>');
	var roleList = ${roleListJson };
	for (var i = 0; i < roleList.length; i++) {
		if(roleList[i].id == '${roleId }'){
			roles.append("<option data-roleid="+roleList[i].id+" selected>"+ roleList[i].name +"</option>");
		}else {
			roles.append("<option data-roleid="+roleList[i].id+">"+ roleList[i].name +"</option>");
		}
	}
	
	
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
				parent.window.location.reload();
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
				parent.core_iframe.window.search();
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
				parent.core_iframe.window.search();
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

function band(obj){
	$('.bdzh .title1').hide();
	$('.bdzh .title').show();
	$('.bdzh .count input').val('');
	
	var userId = $(obj).parent().parent().attr("data-id");
	var vendorId = $("#appletVendor").find("option:selected").attr("value");
	
	var userName = $(obj).parent().parent().children("td:nth-child(2)").text();
	$('.bdzh .title span').text(userName);
	
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['350px', '280px'],
		  title: '绑定账号', //不显示标题
		  content: $('.bdzh'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['绑定','取消'],//按钮
		  btn1: function(index, layero){
			  banding(userId,vendorId);
		  },
		  btn2: function(index, layero){
			  layer.close();
		  }
	});
}
//执行绑定
function banding(userId,vendorId){
	var val = {};
	val.vendorId = vendorId;
	val.userId = userId;
	
	var count = $("#count").val();
	var pass = $("#pass").val();
	val.vendorUserName = count;
	val.vendorPassword = pass;
	$.post("${ctp}/sys/applet/binding/creator" , val,function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("绑定成功");
				//parent.core_iframe.window.search();
				search();
			} else if ("fail" === data) {
				$.error("绑定失败，系统异常", 1);
			}
		}
	});
}
function unBind(obj){
// 	$('.bdzh .title1').hide();
// 	$('.bdzh .title').show();
// 	$('.bdzh .count input').val('');
	var vendorName = $("#appletVendor").find("option:selected").text();
	$("#us_ven1").text(vendorName);
	var userName = $(obj).parent().parent().children("td:nth-child(2)").text();
	$("#us_name1").text(userName);
	
 	var userId = $(obj).parent().parent().attr("data-id");
 	var vendorId = $("#appletVendor").find("option:selected").attr("value");
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['350px', '195px'],
		  title: '解除账号绑定', //不显示标题
		  content: $('.bdzh_jcbd'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['解除绑定','取消'],//按钮
		  btn1: function(index, layero){
			  unBanding(userId,vendorId);
		  },
		  btn2: function(index, layero){
			  layer.close();
		  }
	});
}
//执行解除绑定
function unBanding(userId,vendorId){
	var val = {};
	val.vendorId = vendorId;
	val.userId = userId;
	
	$.post("${ctp}/sys/applet/binding/unBind" , val,function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("绑定成功");
				//parent.core_iframe.window.search();
				search();
			} else if ("fail" === data) {
				$.error("绑定失败，系统异常", 1);
			}
		}
	});
}

function check(bindUserName,userName,pass){
// 	$('.bdzh_ck .count input').val('');
	$("#count1").val(bindUserName);
	var vendorName = $("#appletVendor").find("option:selected").text();
	$("#us_ven").text(vendorName);
	$("#us_name").text(userName);
	$("#pass1,#pass_1").val(pass);
	
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['350px', '280px'],
		  title: '查看', //不显示标题
		  content: $('.bdzh_ck'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['关闭'],//按钮
		  btn1: function(index, layero){
			  layer.close();
		  },
	});
}
</script>