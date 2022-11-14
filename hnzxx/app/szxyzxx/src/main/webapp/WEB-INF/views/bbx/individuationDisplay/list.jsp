<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="circleId" name="circleId" value="${circleId}" />

<div class="trends">
    <ul id="layer-photos-demos">
	    <c:forEach items="${items}" var="item">
	        <li>
	            <span>个性化展示</span>
	            <div class="entry">
	                <p>标题：${item.title}</p>
	                <p style="font-weight: normal;">展示时间：<fmt:formatDate value="${item.startTime}" pattern="yyyy年MM月dd日 HH:mm"/>
	                	-<fmt:formatDate value="${item.finishTime}" pattern="MM月dd日 HH:mm"/></p>
                	
                	<p style="font-weight: normal;">是否每日循环:
                		<c:choose>
		                	<c:when test="${item.isCirculate == 'true'}">
		                		是
		                	</c:when>
		                	<c:when test="${item.isCirculate == 'false'}">
		                		否
		                	</c:when>
	               		</c:choose>  
                	</p>
	                <c:choose>
	                	<c:when test="${item.contentType == '1'}">
	                		<p style="font-weight: normal;">内容：${item.content}</p>
	                	</c:when>
	                	<c:when test="${item.contentType == '2'}">
	                		<c:forEach items="${item.imgUrls}" var="imgUrl">
	                			<img src="${imgUrl}" >
	                		</c:forEach>
	                	</c:when>
	                </c:choose>       
	            </div>
	            <div class="revise">
	             	<p><t:showTime createTime="${item.createDate}"></t:showTime></p>
	             	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
	                <a href="javascript:void(0)" onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
	                </c:if>
	                <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
	                <a href="javascript:void(0)" onclick="loadEditPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑</a>
	                </c:if>
	            </div>
	        </li>
	    </c:forEach>
    </ul>
</div>            
<script>
layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	  layer.photos({
	    photos: '#layer-photos-demos',
	 	area: 'auto'
	  });
	});
</script>