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
<input type="hidden" value="${all}" id="all"/>
<c:if test="${items.size()<=0}">
<div class="empty">
	<p>搜索结果为空</p>
</div>
</c:if>
<ul>
	<c:forEach items="${items}" var="list">
		<li id="${list.id}_li">
			<div class="touxiang">
				<img src="<avatar:avatar userId='${list.proposer}' ></avatar:avatar>"></img>
			</div>
			<div class="detail">
				<div class="p1">
					<span>${list.proposerName}</span>的申请
				</div>
				<div class="p2">
					${list.title}<span>
					[<c:if test="${list.auditStatus==0}">待审批</c:if>
					<c:if test="${list.auditStatus==1}"><span style="color: green;">审批通过</span></c:if>
					<c:if test="${list.auditStatus==4}"><span style="color: red;">审批不通过</span></c:if>]
					</span>
				</div>
				<div class="p3">
					<i class="fa fa-truck"></i>
					<p class="p_div">申请车辆</p>
					<span>${list.cardName}【${list.plateNumber}】</span>
				</div>
				<div class="p3">
					<i class="fa fa-user"></i>
					<p class="p_div">申请人</p>
					<span>${list.proposerName}</span>
				</div>
				<div class="p3">
					<i class="fa  fa-phone"></i>
					<p class="p_div">联系电话</p>
					<span>${list.phone}</span>
				</div>
				<div class="p3">
					<i class="fa fa-clock-o"></i>
					<p class="p_div">使用时间</p>
					<span>
						<fmt:formatDate value="${list.beginDate}" pattern="yyyy年MM月dd日"/>
						-
						<fmt:formatDate value="${list.endDate}" pattern="yyyy年MM月dd日" />
						 共<b>${list.tolDay}</b>天
					</span>
				</div>
				<div class="p3">
					<i class="fa fa-eye"></i>
					<p class="p_div">审核人</p>
					<span>
						<c:if test="${list.auditName==''||list.auditName==null}">
							暂时没审核哦
						</c:if>
						<c:if test="${list.auditName!=''&&list.auditName!=null}">
							${list.auditName}
						</c:if>
					</span>
				</div>
				<div class="p3">
					<i class="fa fa-file-text-o"></i>
					<p class="p_div">审核结果</p>
					<span>
						<c:if test="${list.auditStatus==0}">待审批</c:if>
						<c:if test="${list.auditStatus==1}">审批通过</c:if>
						<c:if test="${list.auditStatus==4}">审批不通过</c:if>
					</span>
				</div>
				<div class="p4"><fmt:formatDate value="${list.createDate}" pattern="yyyy年MM月dd日  HH : mm : ss"/></div>
			</div>
			<div class="caozuo">
				<c:if test="${list.proposer == curenuser && list.auditStatus==0}">
				<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
					<a class="edit" href="javascript:void(0)" onclick="loadEditPage('${list.id}');"><i class="fa fa-edit"></i>编辑</a>
					</c:if>
					<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
					<a class="delete" href="javascript:void(0)" onclick="deleteObj(this,'${list.id}');">删除</a>
					</c:if>
				</c:if>
			</div>
			<div class="clear"></div>
		</li>
	</c:forEach>
</ul>
