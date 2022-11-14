<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<style type="text/css">
.sq_list .clsq ul li .detail_1{
	margin-left:0;
}
.sq_list .clsq ul li .caozuo_1{
	width:140px;
}
</style>
<table>
	<tr>
		<td style="padding:0;border:0 none;">
			<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
			<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
			<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
		</td>
	</tr>
</table>
<input type="hidden" value="${receiverType}" id="receiverType"/>
<div class="entry">
	<ul>
<%-- 		<li class="on"><a href="javascript:void(0)">全部通知（<span id="all_num">${totalSize}</span>）</a></li> --%>
	<c:forEach items="${schoolMembers}" var="item">
						<ul>

						<li><a href="javascript:void(0)" onclick="schoolSearch('${item.schoolId}',this);"<c:if test="${item.schoolId eq schoolId}">style="font-weight: bold;"</c:if>>${item.schoolName}(${item.noticeCount})</a></li>
						</ul>
						</c:forEach>

	</ul>

		<button class="btn btn-success" id="createButton" onclick="createNotice();">发通知</button>

</div>

<div class="f_wy">

<div class="clsq">
	<ul>
<c:forEach items="${items}" var="item">
		<li id="li_${item.id}">

			<div class="detail_1">
				<div class="p1"><a href="javascript:void(0)" onclick="showContent('${item.id}');">${item.title} </a>&nbsp;&nbsp;&nbsp;&nbsp;(${item.read_count}/${item.user_count})</div>

				<div class="p4">${item.poster_name}&nbsp;&nbsp;&nbsp;&nbsp;
				<fmt:formatDate value="${item.create_date}" pattern="yyyy 年  MM 月 dd 日 HH:mm"/>
				</div>
			</div>
			<div class="caozuo_1">
				<c:if test="${cuurenLogin==item.poster_id and receiverType=='own'}">
<%-- 					<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}"> --%>
<%-- 						<a class="edit" href="javascript:void(0)" onclick="editNotice('${item.id}');"><i class="fa fa-edit"></i>编辑</a> --%>
<%-- 					</c:if> --%>
					<a href="javascript:void(0)"  onclick="read(this,'${item.id}')"> 阅读情况 </a>
					<a class="delete" href="javascript:void(0)" onclick="deleteObj(this,'${item.id}')">删除</a>

				</c:if>



			</div>
			<div class="clear"></div>
		</li>
</c:forEach>
	</ul>
<c:if test="${items.size()<=0}">
		<div class="empty">
		    <c:choose>
		       <c:when test="${sub=='list' }">
					<p>搜索结果为空</p>
		       </c:when>
		       <c:otherwise>
		           <p>暂无通知</p>
		       </c:otherwise>
		    </c:choose>
		</div>
	</c:if>
</div>
</div>