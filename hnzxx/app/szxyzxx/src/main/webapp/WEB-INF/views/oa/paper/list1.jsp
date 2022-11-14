<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<table>
	<tr>
		<td style="padding: 0; border: 0 none;"><input type="hidden"
			id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
			type="hidden" id="totalPages" name="totalPages"
			value="${page.totalPages}" />
			<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" /></td>
	</tr>
</table>
<input type="hidden" value="" id="depart"/>
<input type="hidden" value="${type}" id="type" />
<div class="entry">
<c:if
		test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
		<button class="btn btn-success" onclick="loadCreatePage();"
			id="createButton">发公文</button>
</c:if>
	<ul>
		<li class="li on" ><a href="javascript:void(0)" onclick="departmentSearch('all',this);" data-id="all"> <c:if
					test="${type=='related' }">与我相关</c:if>
<%--			<c:iftest="${type=='department' }">部门公文</c:if> --%>
			<c:if test="${type=='all' }">全部公文</c:if> <c:if test="${type=='own' }">我发布的</c:if>
				（${empty totalCount ? '0' : totalCount}）
		</a></li>
		<c:if test="${type=='all' }">
<%--			<c:forEach items="${dList }" var="dt" varStatus="i">--%>
<%--				<input type="hidden" id="departId" />--%>
<%--				<input type="hidden" value="${dList.size() }" id="dListLength" />--%>
<%--				<li class="li li_${dt.id }"><a href="javascript:void(0)" data-id="${dt.id }"--%>
<%--					onclick="departmentSearch('${dt.id}',this);">${dt.name }（ <span--%>
<%--						class="people_num${i.index }"> <c:forEach--%>
<%--								items="${pdcList }" var="pdc">--%>
<%--								<c:if test="${dt.id==pdc.departmentId }">${pdc.count}</c:if>--%>
<%--							</c:forEach>--%>
<%--					</span> ）--%>
<%--				</a></li>--%>
<%--			</c:forEach>--%>
		</c:if>
	</ul>
	<div class="clear"></div>
</div>

<div class="clsq">
	<ul>
		<c:forEach items="${items}" var="item" varStatus="i">
			<li id="li_${item.id}">
				<div class="touxiang">
					<img
						src="<avatar:avatar userId='${item.posterId}' ></avatar:avatar>">
				</div> 
				 <c:choose>
				   <c:when test="${item.posterId==currentUser }">
				        <c:choose>
					<c:when test="${item.receiverType==0 }">
						<div class="caozuo_2 all">
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
							<a class="edit" href="javascript:void(0)"
								onclick="loadEditPage('${item.id}')"><i class="fa fa-edit"></i>编辑</a>
						</c:if>
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
							<a class="delete" href="javascript:void(0)"
								onclick="deleteObj(this, '${item.id}');"> 删除</a>
						</c:if>
						</div>
					</c:when>
					<c:otherwise>
						<div class="caozuo_2 one">
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
							<a class="edit" href="javascript:void(0)"
								onclick="loadEditPage('${item.id}')"><i class="fa fa-edit"></i>编辑</a>
						</c:if>
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
							<a class="delete" href="javascript:void(0)"
								onclick="deleteObj(this, '${item.id}');">删除</a>
						</c:if>
						</div>
					</c:otherwise>
				</c:choose>
				   </c:when>
				   <c:otherwise>
				        <c:choose>
					     <c:when test="${item.receiverType==0 }"><div class="caozuo_2 all"></div></c:when>
					     <c:otherwise><div class="caozuo_2 one"></div></c:otherwise>
					    </c:choose>
				   </c:otherwise>
				 </c:choose>
				<div class="detail_2">
					<div class="p1">
						<span>${item.posterName }</span>发布的公文
					</div>
<%-- 					   <input type="text" value="${item.isPublishOrReceiver}"/> --%>
					<div class="p2">
					  
						<a href="javascript:void(0)" class="p2"
						  <c:choose>
						     <c:when test="${item.isPublishOrReceiver==true }">onclick="loadViewerPage('${item.id}')"</c:when>
						     <c:otherwise>onclick="quanxian()"</c:otherwise>
						  </c:choose> 
						   <%-- <c:if test="${item.isPublishOrReceiver==true }">
							onclick="loadViewerPage('${item.id}')"
							</c:if> --%>
							>${item.title }</a>
					</div>
					<div class="p3" style="word-wrap:break-word;">${item.remark }</div>
					<c:if test="${not empty item.attachmentUuid }">
						<div class="p6">
							<i class="fa fa-paperclip"></i>
							<p>附件</p>
							
							<a target="_blank" id="a"  <c:if test="${item.isPublishOrReceiver==true }">href='<entity:getHttpUrl uuid="${item.attachmentUuid}"/>'</c:if>
								>${item.realFileName}</a>
						</div>
					</c:if>
					<div class="p4">
						${item.author }&nbsp;&nbsp;&nbsp;&nbsp;
						${item.publishDate }
						<div class="chayue">
							<i class="fa fa-eye"></i>查阅情况<span>${item.readNumber}/${item.receiverCount }</span>
						</div> 
					</div>
				</div>

			</li>
		</c:forEach>
	</ul>
	<c:if test="${items.size()<=0}">
		<div class="empty">
		    <c:choose>
		       <c:when test="${sub=='list1' }">
					<p>搜索结果为空</p>
		       </c:when>
		       <c:otherwise>
		           <p>暂无公文</p>
		       </c:otherwise>
		    </c:choose>
		</div>
	</c:if>
</div>
<script>
$(function() {
	addData();
})
function addData() {
	var dListLength = $("#dListLength").val();
	dListLength = dListLength + 0;
	for (var j = 0; j < dListLength; j++) {
		if ($.trim($(".people_num" + j).text()) == ''
				|| $.trim($(".people_num" + j).text()) == '0') {
			$(".people_num" + j).text('0');
		}
	}
}

function quanxian(){
	$.alert("您暂时没有权限对该公文进行查看!");
}

</script>