<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<style type="text/css">
p.title{
	display:inline-block; 
	margin: 0 50px 0 0;
	font-weight: normal;
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
<div>
	<div class="span12">
	    <div class="content-widgets white" style="border:1px solid #fff;">
	        <div class="content-widgets">
	            <img src="${pageContext.request.contextPath}/res/css/bbx/images/icon.jpg" class="zp">
	            <p>考试信息：<span id="count" class="number" style="font-size:18px;font-family:'微软雅黑';color:#00A0E9">${count}</span> 条 </p>
	        </div>
	    </div>
	</div>
</div>
<div class="trends">
	<ul id="layer-photos-demo">
	<c:choose>
	<c:when test="${not empty items}">
		<c:forEach items="${items}" var="item">
            <li>
                <span class="sign ban-rong">考试模式</span>
                <div class="entry">
                	<p class="title">考试场地:${item.examRoomName }</p>
                	<p class="title">考试科目:${item.subjectName }</p>
                	<p class="title">考试时间:<fmt:formatDate value="${item.startTime}" pattern="yyyy年MM月dd日 HH:mm"/>-<fmt:formatDate value="${item.finishTime}" pattern="yyyy年MM月dd日 HH:mm"/></p>
                	<p class="title">监考老师:${item.teacherName }</p>
                    <p style="font-weight: normal;overflow: hidden;text-overflow:ellipsis;white-space: nowrap; width:70%;">考场注意事项：${item.content}</p>
                </div>
                <div class="revise">
                	<p><t:showTime createTime="${item.createDate }"></t:showTime></p>
                	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
<%--                 	<a href="javascript:void(0)" onclick="bpDelete('${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除内容</a> --%>
                    <a href="javascript:void(0)" onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
                    </c:if>
                    <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
                    <a href="javascript:void(0)" onclick="loadEditPage('${item.id}');">
						<i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑
					</a>
					</c:if>
                </div>
            </li>
        </c:forEach>
        </c:when>
	<c:otherwise>
		<li>
		 <div class="empty">
			<p class="empty_text">搜索结果为空</p>
	     </div>
	  	 </li>
	</c:otherwise>
	</c:choose>
	</ul>
</div>
