<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<style>
  .empty p {
    color: #333333;
    font-family: "微软雅黑";
    font-size: 16px;
    margin: 0;
    padding-top: 188px;
    text-align: center;
}

.empty{
	background:url("../../res/css/extra/images/empty.png") no-repeat scroll 50% 50%;
    height: 290px;
}
  

</style>

<table>
	<tr>
		<td style="padding: 0; border: 0 none;"><input type="hidden"
			id="currentPage" name="currentPage" value="${page.currentPage}" />
			<input
			type="hidden" id="totalPages" name="totalPages"
			value="${page.totalPages}" />
			<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" /></td>
	</tr>
</table>
<input type="hidden" value="${type }" id="type"/>
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
 <ul>
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
					<p class="bm" title="${tdInfo.departmentName }">
							<c:choose>
								<c:when test="${fn:length(tdInfo.departmentName)>8}">
									<c:out value="${fn:substring(tdInfo.departmentName,0,8)}"></c:out>
													...
												</c:when>
								<c:otherwise>${tdInfo.departmentName }</c:otherwise>
							</c:choose>

<%-- 							${tdInfo.departmentName} --%>
						</p>
				</c:when>
				<c:otherwise>
					<p class="bm">
							<span title="${tdInfo.departmentName  }"><c:choose>
								<c:when test="${fn:length(tdInfo.departmentName)>8}">
									<c:out value="${fn:substring(tdInfo.departmentName,0,8)}"></c:out>
													...
												</c:when>
								<c:otherwise>${tdInfo.departmentName }</c:otherwise>
							</c:choose></span>
<%-- 							${tdInfo.departmentName}/ ${tdInfo.position} --%>
 								/
 							<span title="${tdInfo.position  }"><c:choose>
								<c:when test="${fn:length(tdInfo.position)>4}">
									<c:out value="${fn:substring(tdInfo.position,0,4)}"></c:out>
													...
												</c:when>
								<c:otherwise>${tdInfo.position}</c:otherwise>
							</c:choose></span>
<%--  								 ${tdInfo.position} --%>
				  </p>
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
</ul>
<c:if test="${tdInfoList.size()<=0}">
	<div class="empty">
		<p>搜索结果为空</p>
	</div>
   
</c:if>

