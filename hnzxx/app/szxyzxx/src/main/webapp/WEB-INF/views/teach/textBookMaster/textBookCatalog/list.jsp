<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<div class="catalog_list">
	
	<c:set var="count" >0</c:set>
	<c:set var="sum" value="${fn:length(catalogList)}"></c:set>
	<ul>
	<c:forEach  items="${catalogList}" var="item" varStatus="status">
		 <c:if test="${item.level==0}">
		 <c:if test="${status.index==0}">
		
		 	<li><b class="title">${item.name}</b></li>
		</c:if>
		</c:if>	
		 
		 <c:if test="${item.level==1}">
		 <c:if test="${count==1}">
		 <c:set var="count" >0</c:set>
		 	</ul>
				<div class="clear"></div>
		   </li>
		</c:if>
		</c:if>	
		
		  <c:if test="${item.level==1}">
		 <c:if test="${count==0}"> 
		 <c:set var="count" >1</c:set>
		 	<li>
		 	<!-- close_1 -->
			 <p class="open_1"><b>${item.name}</b></p>
			<ul style="display:none">
		 </c:if>
		</c:if> 
		
		 <c:if test="${item.level >1}">
		
			<li>
			<p class="have"><b>${item.name}</b></p>
			<span>${item.page}</span>
			</li>
		
		</c:if>
			
		
			
	</c:forEach>		
	    <c:if test="${sum>1}"></c:if>
		 <c:if test="${item.level==1}">
		 <c:if test="${count==1}">
		 <c:set var="count" >0</c:set>
		 	</ul>
				<div class="clear"></div>
		   </li>
		</c:if>
		</c:if>	
</ul>
</div>
<script type="text/javascript">
$(function(){
	$(".catalog_list ul li").on("click",".close_1 b",function(){
		$(this).parent().removeClass("close_1").addClass("open_1");
		$(this).parent().next("ul").hide();
	});
	$(".catalog_list ul li").on("click",".open_1 b",function(){
		$(this).parent().removeClass("open_1").addClass("close_1");
		$(this).parent().next("ul").show();
	});
}); 
</script>