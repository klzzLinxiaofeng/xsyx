<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<table>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
</table>
<h4 class="title">全部申请(<span>${count}</span>)</h4>
<c:forEach items="${items}" var="item" varStatus="i">
	<div id="apply_${item.id}">
		<div class="clearfix">
			<div class="admin-thumb">
				<img src="<avatar:avatar userId='${item.proposerId}' ></avatar:avatar>"></img>
			</div>
			<div class="admin-meta admin-head">
				<ul>
					<li><b>${item.proposerName}</b> 发出的申请</li>
					<li><a href="#" class="title-a">${item.title}</a>
						<a href="#">${item.typeName}</a>
					</li>
				</ul>
			</div>
		</div>
		<div class="clearfix x-mright">
			<div class="x-load span12">
				<span <c:if test="${item.status==01}">class="active-on"</c:if>>
					<em class="on" onclick="sqRepari('${item.id}');" style="cursor:pointer">申请</em>
				</span>
				<span>
					<em <c:if test="${item.status==01 && item.isc==1}">  onclick="wxRepari('${item.id}');" </c:if> class="on"  style="cursor:pointer">维修</em>
				</span>
				<span>
					<em <c:if test="${item.appraise!=null && item.appraise!=''}">class="on"</c:if>
						<c:if test="${item.status!=01 && item.status!=02 && item.appraise!=null && item.appraise!=''}">
							 onclick="pjRepari('${item.id}');" style="cursor:pointer"
						</c:if>
					  >评价</em>
				</span>
				<span style="background:none;">
					<em <c:if test="${item.appraise!=null && item.appraise!=''}">class="on"</c:if>>完成</em>
				</span>
			</div>
			<div id="sq_div${item.id}" class="sq"></div>
			<div id="wx_div${item.id}" class="wx"></div>
			<div id="pj_div${item.id}" class="pj"></div>
		</div>
	</div>HuiYiController
</c:forEach>
<jsp:include page="./myJqpagination.jsp" flush="true">
	<jsp:param name="id" value="datas-id" />
	<jsp:param name="url" value="/oa/acceptrepari/applyIndex?approval=${approval}&sub=list&dm=${param.dm}" />
	<jsp:param name="pageSize" value="${page.pageSize}" />
</jsp:include>