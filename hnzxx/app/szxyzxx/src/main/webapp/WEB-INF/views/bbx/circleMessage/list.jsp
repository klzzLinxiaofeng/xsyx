<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="circleId" name="circleId" value="${circleId}" />
<div>
            <div class="span12">
                <div class="content-widgets white" style="border:1px solid #fff;">
                    <div class="content-widgets">
                        <img src="${pageContext.request.contextPath}/res/css/bbx/images/icon.jpg" class="zp">
                        <p>动态总数：<span id="count" class="number" style="font-size:18px;font-family:'微软雅黑';color:#00A0E9">${count}</span> 条 <%-- &nbsp  照片：<span  class="number">${imgCount}</span> 张 --%></p>
                    </div>
                </div>
            </div>
            </div>
            <div class="trends">
                <ul id="layer-photos-demo">
                <c:forEach items="${items}" var="item">
                    <li>
                        <span>班级动态</span>
                        <div class="entry">
                            <p>${item.content}</p>
                            <c:forEach items="${item.imgUrls}" var="imgUrl">
                            <img src="${imgUrl}" >
                            </c:forEach>
                        </div>
                        <div class="revise">
	                        <p>${item.circleName}</p>
                            <p>${item.nickTime}</p>
                           <%--  <c:if test="${sessionScope[sca:currentUserKey()].currentRoleCode == 'CLASS_MASTER'}"> --%>
                           <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
                            <a href="javascript:void(0)" onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
                            </c:if>
                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
                            <a href="javascript:void(0)" onclick="loadEditPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑</a>
                            </c:if>
                           <%--  </c:if> --%>
                        </div>
                    </li>
                </c:forEach>
                </ul>
            </div>
<script>
	layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	  layer.photos({
	    photos: '#layer-photos-demo',
	 	area: 'auto',
	 	maxWidth:'700'
	  });
	}); 
</script>