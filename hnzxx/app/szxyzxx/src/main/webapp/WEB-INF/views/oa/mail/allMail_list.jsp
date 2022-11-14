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
<div class="pailie">
	<p>
		共<span class="red">${allSum }</span>人
	</p>
	<!-- <div class="zm">
								<a href="javascript:void(0)">a</a>
								<a href="javascript:void(0)">b</a>
								<a href="javascript:void(0)">c</a>
								<a href="javascript:void(0)">d</a>
								<a href="javascript:void(0)">e</a>
								<a href="javascript:void(0)">f</a>
								<a href="javascript:void(0)">g</a>
								<a href="javascript:void(0)">h</a>
								<a href="javascript:void(0)">i</a>
								<a href="javascript:void(0)">j</a>
								<a href="javascript:void(0)">k</a>
								<a href="javascript:void(0)">l</a>
								<a href="javascript:void(0)">m</a>
								<a href="javascript:void(0)">n</a>
								<a href="javascript:void(0)">o</a>
								<a href="javascript:void(0)">p</a>
								<a href="javascript:void(0)">q</a>
								<a href="javascript:void(0)">r</a>
								<a href="javascript:void(0)">s</a>
								<a href="javascript:void(0)">t</a>
								<a href="javascript:void(0)">u</a>
								<a href="javascript:void(0)">v</a>
								<a href="javascript:void(0)">w</a>
								<a href="javascript:void(0)">x</a>
								<a href="javascript:void(0)">y</a>
								<a href="javascript:void(0)">z</a>
								<a href="javascript:void(0)" class="all">所有</a>
							</div> -->
</div>
<c:forEach items="${tdInfoList}" var="tdInfo">
	<li><img alt="头像"
		src="<avatar:avatar userId='${tdInfo.userId}' ></avatar:avatar>">
		<p class="name">${tdInfo.name }</p> <c:if
			test="${tdInfo.departmentName==''}">
			<c:choose>
				<c:when test="${tdInfo.position =='' || tdInfo.position ==null  }">
					<p class="bm">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
				</c:when>
				<c:otherwise>
					<p class="bm">${tdInfo.position}</p>
				</c:otherwise>
			</c:choose>
		</c:if> <c:if test="${tdInfo.departmentName!=''}">
			<c:choose>
				<c:when test="${tdInfo.position =='' || tdInfo.position ==null  }">
					<p class="bm">${tdInfo.departmentName}</p>
				</c:when>
				<c:otherwise>
					<p class="bm">${tdInfo.departmentName}/ ${tdInfo.position}</p>
				</c:otherwise>
			</c:choose>
		</c:if> <c:choose>
			<c:when test="${tdInfo.email==''}">
				<p class="p1">电子邮件：未设置</p>
			</c:when>
			<c:when test="${tdInfo.email==null }">
				<p class="p1">电子邮件：未设置</p>
			</c:when>
			<c:otherwise>
				<p class="p1">电子邮件：${tdInfo.email }</p>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${tdInfo.telephone=='' }">
				<p class="p1">办公电话：未设置</p>
			</c:when>
			<c:when test="${tdInfo.telephone==null }">
				<p class="p1">办公电话：未设置</p>
			</c:when>
			<c:otherwise>
				<p class="p1">办公电话：${tdInfo.telephone }</p>
			</c:otherwise>
		</c:choose> <c:choose>
			<c:when test="${tdInfo.mobile=='' }">
				<p class="p1">手机：未设置</p>
			</c:when>
			<c:when test="${tdInfo.mobile==null }">
				<p class="p1">手机：未设置</p>
			</c:when>
			<c:otherwise>
				<p class="p1">手机：${tdInfo.mobile }</p>
			</c:otherwise>
		</c:choose></li>
</c:forEach>

<c:if test="${tdInfoList.size()<=0}">
   <li style="width:98%;">
	<div class="empty">
		<p>搜索结果为空</p>
	</div>
   
   </li>
</c:if>

