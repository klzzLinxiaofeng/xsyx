<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<table>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr>
</table>
<div class="trends">
	<ul id="layer-photos-demo">
		<c:choose>
		<c:when test="${not empty items}">
		<c:forEach items="${items}" var="item">
            <li>
                <span class="sign ban-rong">校园信息</span>
                <div class="entry">
                	<h4><strong>${item.title}</strong></h4>
                    <p class="texta">${item.content}</p>
                    <c:forEach items="${item.imageUrlList}" var="imgUrl">
                    <img src="${imgUrl}" >
                    </c:forEach>
                </div>
                <div class="revise">
                	<p><t:showTime createTime="${item.createDate }"></t:showTime></p>
                    <a href="javascript:void(0)" onclick="deleteObj(this, '${item.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
                    <a href="javascript:void(0)" onclick="loadEditPage('${item.id}');">
						<i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑
					</a>
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
<script>
	layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	  layer.photos({
	    photos: '#layer-photos-demo',
	 	area: 'auto'
	  });
	}); 
</script>