<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
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
		<li id="li_${item.id}" style="height: 280px;">
			<div class="touxiang">

				<img
					src="<avatar:avatar userId='${item.propserId}' ></avatar:avatar>">
			</div>
			<div class="detail">
				<c:if test="${not empty item.propserName }">
					<div class="p1">
						<span>${item.propserName }</span>发出的申请
					</div>
				</c:if>
				<div class="p2">
					${item.title }
					<c:choose>
						<c:when test="${item.auditStatus==0 }">
							<span>[待审批]</span>
						</c:when>
						<c:otherwise>
							<span class="yichuli">[审批 <c:if
									test="${item.approveState!=null ||item.approveState!=''  }">
									<c:choose>
										<c:when test="${item.approveState==0 }">通过</c:when>
										<c:otherwise><span style="color:red">未通过</span></c:otherwise>
									</c:choose>
								</c:if> ]
							</span>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="p5" style="word-wrap:break-word;">${item.detail }</div>
				<div class="p3">
					<i class="fa fa-user"></i>
					<p class="p_div">申请人</p>
					<span>${item.propserName }</span>
				</div>
				<c:if test="${item.propserDepartmentId!=null }">
					<div class="p3">
						<i class="fa  fa-sitemap"></i>
						<p class="p_div">所在部门</p>
						<span>${item.propserDepartmentName }</span>
					</div>
				</c:if>
				<div class="p3">
					<i class="fa fa-clock-o"></i>
					<p class="p_div">请假时间</p>
					<span>${item.startDate } - ${item.endDate }共<b>${item.daySum }</b>天
					</span>
				</div>

				<c:choose>
					<c:when test="${item.auditStatus==0 }">
						<div class="p3">
							<i class="fa fa-eye"></i>
							<p class="p_div">审核人</p>
							<span>还没进行审核</span>
						</div>
						<div class="p3">
							<i class="fa fa-file-text-o"></i>
							<p class="p_div">审核结果</p>
							<span>待审批 </span>
						</div>
					</c:when>
					<c:otherwise>
						<div class="p3">
							<i class="fa fa-eye"></i>
							<p class="p_div">审核人</p>
							<span>${item.approveName }</span>
						</div>
						<div class="p3">
							<i class="fa fa-file-text-o"></i>
							<p class="p_div">审核结果</p>
							<span>审批 
									<c:choose>
										<c:when test="${item.approveState==0 }">通过</c:when>
										<c:otherwise>未通过</c:otherwise>
									</c:choose></span>
						</div>
					</c:otherwise>
				</c:choose>

				<div class="p4">
					<fmt:formatDate pattern="yyyy年MM月dd日  HH:mm:ss "
						value="${item.createDate }"></fmt:formatDate>
				</div>
			</div>
			<div class="caozuo">
				<c:if test="${curenuser==item.propserId }">
					<c:if test="${item.auditStatus==0 }">
					 <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
						<a class="edit" href="javascript:void(0)"
							onclick="loadEditPage('${item.id}','${item.propserDepartmentId }')"><i
							class="fa fa-edit"></i>编辑</a>
					 </c:if>
					 <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
						<a class="delete" href="javascript:void(0)"
							onclick="deleteObj(this, '${item.id}');">删除</a>
					</c:if>
					</c:if>
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
					<p>暂无请假</p>
				</c:otherwise>
			</c:choose>
		</div>
	</c:if>
</ul>