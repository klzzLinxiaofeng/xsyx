<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<table>
	<tr>
		<td style="padding: 0; border: 0 none;"><input type="hidden"
			id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
			type="hidden" id="totalPages" name="totalPages"
			value="${page.totalPages}" /></td>
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</tr>
</table>
<input type="hidden" value="${all}" id="all" />
<ul>
	<c:forEach items="${items}" var="item" varStatus="i">
		<li id="li_${item.id}">
			<div class="touxiang">
				<img
					src="<avatar:avatar userId='${item.proposerId}' ></avatar:avatar>">
			</div>
			<div class="detail">
				<c:if test="${not empty item.proposerName }">
					<div class="p1">
						<span>${item.proposerName }</span>发出的申请
					</div>
				</c:if>
				<div class="p2">
					${item.title }
					<c:choose>
						<c:when test="${item.indiaStatus==0 }">
							<span class="daichuli">[待处理]</span>
						</c:when>
						<c:when test="${item.indiaStatus==1 }">
							<span class="weichuli">[已驳回]</span>
						</c:when>
<%--						<c:when test="${item.indiaStatus==2 }">--%>
<%--							<span class="chulizhong">[处理中]</span>--%>
<%--						</c:when>--%>
						<c:when test="${item.indiaStatus==3 }">
							<span class="yichuli">[已同意]</span>
						</c:when>
					</c:choose>
				</div>
				
				<div class="p5" style="word-wrap:break-word;"  title="${item.remark}">
					 <c:choose>
						<c:when test="${fn:length(item.remark)>60}">
							<c:out value="${fn:substring(item.remark,0,60)}"></c:out>
							<strong>. . .</strong>
						</c:when>
						<c:otherwise>${item.remark}</c:otherwise>
					</c:choose> 
				</div>
				
				
				<%-- <div class="p5" style="word-wrap:break-word;">${item.remark }</div> --%>
				<c:if test="${not empty item.uploadId }">
					<div class="p6">
						<i class="fa fa-paperclip"></i>
						<p>附件</p>
						<a target="_blank" id="a"
							href='<entity:getHttpUrl uuid="${item.uploadId}"/>'>${item.realFileName}</a>
					</div>
				</c:if>
				<c:if test="${not empty item.departmentName }">
					<div class="p3">
						<i class="fa fa-flag"></i>
						<p class="p_div">部门</p>
						<span>${item.departmentName}</span>
					</div>
				</c:if>
				<c:if test="${not empty item.proposerName }">
					<div class="p3">
						<i class="fa fa-user"></i>
						<p class="p_div">联系人</p>
						<span>${item.proposerName }</span>
					</div>
				</c:if>
				<c:if test="${not empty item.mobile}">
					<div class="p3">
						<i class="fa  fa-phone"></i>
						<p class="p_div">联系电话</p>
						<span>${item.mobile}</span>
					</div>
				</c:if>
				<!-- <div class="p3">
					<i class="fa fa-truck"></i>
					<p class="p_div">提货方式</p>
					<span>自提</span>
				</div> -->
				<c:if test="${item.indiaStatus==1 }">
					<div class="p3">
						<i class="fa fa-comment-o"></i>
						<p class="p_div">驳回理由</p>
						<span>${item.nonTreatmentCause}</span>
					</div>

				</c:if>
				<div class="p4">
					<fmt:formatDate pattern="yyyy年MM月dd日  HH:mm:ss "
						value="${item.publishDate }"></fmt:formatDate>
				</div>
			</div>
			<div class="caozuo">
				<c:if test="${curenuser==item.proposerId }">
					<c:choose>
						<c:when test="${item.indiaStatus==0}">
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
							<a class="edit" href="javascript:void(0)"
								onclick="loadEditPage('${item.id}')"><i class="fa fa-edit"></i>编辑</a>
						</c:if>
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
							<a class="delete1" href="javascript:void(0)"
								onclick="deleteObj(this, '${item.id}');">删除</a>
						</c:if>
						</c:when>
						<c:when test="${item.indiaStatus==1 }">
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
							<a class="edit" href="javascript:void(0)"
								onclick="loadEditPage('${item.id}')"><i class="fa fa-edit"></i>编辑</a>
						</c:if>
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
							<a class="delete1" href="javascript:void(0)"
								onclick="deleteObj(this, '${item.id}');">删除</a>
						</c:if>
						</c:when>
						<c:when test="${item.indiaStatus==3 }">
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
							<a class="delete1" href="javascript:void(0)"
								onclick="deleteObj(this, '${item.id}');">删除</a>
						</c:if>
						</c:when>
					</c:choose>
				</c:if>
			</div>
			<div class="clear"></div>
		</li>
	</c:forEach>
	<c:if test="${items.size()<=0}">
		<div class="empty">
		    <c:choose>
		       <c:when test="${sub=='list' }">
					<p>搜索结果为空</p>
		       </c:when>
		       <c:otherwise>
		           <p>暂无用章</p>
		       </c:otherwise>
		    </c:choose>
		</div>
	</c:if>
</ul>



