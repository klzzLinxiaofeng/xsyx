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
<c:choose>
	<c:when test="${items.size()>0 }">
	  <c:forEach items="${items}" var="item">
	    <li id="li_${item.id }">
			<span>${item.receiverName }</span>	
			<div class="entry" id="layer-photos-demo_li_${item.id}">
	            <p>${item.content}</p>
	            <p style="font-weight: normal;">触发时间:<fmt:formatDate value="${item.startTime}" pattern="yyyy年MM月dd日 HH:mm"/>-
		      		<fmt:formatDate value="${item.finishTime}" pattern="dd日 HH:mm"/></p>
	            <c:forEach items="${item.bbxNoticeFileVo }" var="tmfVo" >
				      <img class="bj" src="${tmfVo.fileUrl }"/>
				 </c:forEach>     
	        </div>
			
			
			      
			<%-- <div class="entry" id="layer-photos-demo_li_${item.id }">
				<p>${item.content }</p>
				<p style="font-weight: normal;padding-top: 10px;">触发时间:<fmt:formatDate value="${item.startTime}" pattern="yyyy年MM月dd日 HH:mm"/>-
		      		<fmt:formatDate value="${item.finishTime}" pattern="dd日 HH:mm"/></p>
				  <c:forEach items="${item.bbxNoticeFileVo }" var="tmfVo" >
				      <img class="bj" src="${tmfVo.fileUrl }"/>
				  </c:forEach>
			</div> --%>
			<div class="revise">
				<p>${item.createTime}<%-- <t:showTime createTime="${item.createDate }"></t:showTime> --%></p>
				<%-- <c:if test="${currentRole=='CLASS_MASTER' }"> --%>
				<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
					<a href="javascript:void(0)" onclick="deleteObj(this,'${item.id}')">
					  <i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除
					</a>
					</c:if>
				<%-- </c:if> --%>
				<%-- <a href="javascript:void(0)" onclick="loadScannerPage('${item.id}')" ><i class="fa fa-eye" style="font-size:16px;"></i> &nbsp;浏览</a> --%>
			</div>
		 </li>
      </c:forEach>
	</c:when>
	<c:otherwise>
	   <li>
		 <div class="empty">
			<p class="empty_text">暂无相关数据</p>
	     </div>
	   </li>
	</c:otherwise>
</c:choose>


<%-- <c:choose>
	<c:when test="${items.size()>0 }">
	  <c:forEach items="${items}" var="item">
	    <li id="li_${item.id }">
	      <c:choose>
	        <c:when test="${item.receiverType==5 }">
		      <span>班级通知</span>
	        </c:when>
	        <c:when test="${item.receiverType==4 }">
	           <span style="background:#07AA74;">年级通知</span>  
	        </c:when>
	        <c:otherwise>
	          <span style="background:#099BF0;">全校通知</span>  
	        </c:otherwise>
	      </c:choose>
			<div class="entry" id="layer-photos-demo_li_${item.id }">
				<p>${item.content }</p>
				<c:if test="${item.tmfVo.size()>0 }">
				  <c:forEach items="${item.tmfVo }" var="tmfVo" >
				   <c:choose>
				      <c:when test="${item.receiverType==5 }">
				          <img class="bj" src="${tmfVo.url }"/>
				      </c:when>
				      <c:when test="${item.receiverType==4 }">
				           <img class="nj" src="${tmfVo.url }"/>
				      </c:when>
				      <c:otherwise>
				            <img src="${tmfVo.url }"/>
				      </c:otherwise>
				   </c:choose>
				  
				  </c:forEach>
				</c:if> 
			</div>
			<div class="revise">
				<p><t:showTime createTime="${item.modifyDate }"></t:showTime></p>
				<a href="javascript:void(0)" onclick="deleteObj(this,'${item.id}')">
				  <i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除
				</a>
			    <a href="javascript:void(0)" onclick="loadEditPage('${item.id}')">
				  <i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑
				</a>
			</div>
		 </li>
      </c:forEach>
	</c:when>
	<c:otherwise>
	   <li>
		 <div class="empty">
			<p class="empty_text">暂无相关数据</p>
	     </div>
	   </li>
	</c:otherwise>
</c:choose> --%>
<script>
//	图片展示
	
layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	var s=${items.size()};
	for(var i=0;i<s;i++){
		var id=$(".trends ul li").eq(i).children(".entry").attr("id"); 		
    layer.photos({
        photos: '#'+id,
	 	area: 'auto',
	 	maxWidth:'700'
    });
	}
});  
</script>
