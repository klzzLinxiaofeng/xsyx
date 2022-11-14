<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%-- <tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr> --%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<div class="trends">
    <ul id="layer-photos-demo">
    <c:forEach items="${items}" var="item">
    	<li>
    		<span>广告信息</span>
    		<div class="entry">
    			<b>信息类型:&nbsp;&nbsp;
    				<c:choose>
   						<c:when test="${item.contentType == 1}">纯文本</c:when>
   						<c:when test="${item.contentType == 2}">纯图片</c:when>
   						<c:when test="${item.contentType == 3}">文本与图片</c:when>
   						<c:when test="${item.contentType == 4}">纯视频</c:when>
   						<c:when test="${item.contentType == 5}">文本与视频</c:when>
   						<c:when test="${item.contentType == 6}">图片与视频</c:when>
   						<c:when test="${item.contentType == 7}">文本、图片与视频</c:when>
   						<c:otherwise>无</c:otherwise>
					</c:choose>
				</b>
    			<br/>
                <b>文本信息:&nbsp;&nbsp;${item.content}</b>
                <p></p>
                <c:forEach items="${item.imageDetailList}" var="entity">
                	<img src="${entity.url }" >
                </c:forEach>
			</div>  
				
			<div class="revise">
      			<p><t:showTime createTime="${item.createDate }"></t:showTime></p>
     			<%-- <c:if test="${sessionScope[sca:currentUserKey()].currentRoleCode == 'CLASS_MASTER'}"> --%>
	      		<a href="javascript:void(0)" onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
	      		<a href="javascript:void(0)" onclick="loadEditPage('${item.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑</a>
            		<%-- </c:if> --%>
			</div>               
                
                
            

		</li>
    </c:forEach>
    </ul>
</div>

<script>
	layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	  layer.photos({
	    photos: '#layer-photos-demo',
	 	area: 'auto'
	  });
	}); 
</script>