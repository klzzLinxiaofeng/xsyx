<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>

		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />

<c:set var="count" >0</c:set>
<c:forEach items="${textbookVoList}" var="item" varStatus = "status">
	<li id="${item.id}_tr">
		<div class="fengmian">
			<a href="" id="loadViewerPageImage${item.id}"
				onclick="loadViewerPage('${item.id}','loadViewerPageImage${item.id}');">
				<%-- <img src="${pageContext.request.contextPath}/res/images/cover_default_new.png"> --%>
				<c:if test="${empty  item.image}">
					<img src="${pageContext.request.contextPath}/res/images/cover_default_new.png" id="imgId" />
				</c:if> <c:if test="${not empty item.image}">
					<img src="${item.image}" id="imgId" />
				</c:if>
			</a>
		</div>
		<div class="intro">
			<p>
			<a href="javascript:void(0)" class="title"  id="loadViewerPageContent${item.id}"  onclick="loadViewerPage('${item.id}','loadViewerPageContent${item.id}');">
			<jc:cache tableName="jc_grade" echoField="name" value="${item.gradeCode}" paramName="code"></jc:cache>
			${item.volumn}
			(<jc:cache tableName="jc_subject" echoField="name" value="${item.subjectCode}" paramName="code"></jc:cache>)
			-${item.name}
			</a>
			<c:if test="${list == 'resList'}">
				<c:choose>
					<c:when test="${item.isSchool!=1}">
						<input type="checkbox" value="${item.id }" class="szxb">
					</c:when>
					<c:otherwise>
						<a href="javascript:void(0)"  style="color: black;" class="ysxb">||己设校本</a>
					</c:otherwise>
				</c:choose> 
<%-- 				<c:choose> --%>
<%-- 					<c:when test="${item.isSchool==1}"> --%>
<%-- 						<a href="javascript:void(0)" id="cacelSchool${item.id}" onclick="canelTextBookToSchool('${item.id }')" style="color: black;" class="ysxb">||己设校本</a> --%>
<%-- 					</c:when> --%>
<%-- 					<c:otherwise><a href="javascript:void(0)" id="setToSchool${item.id}" onclick="setTextBookToSchool('${item.id}')" class="swxb">||设为校本</a></c:otherwise> --%>
<%-- 				</c:choose>  --%>
			</c:if>
			</p>
			<span>作者：
				<c:forEach items="${item.writerMain}" var="writer">
					${writer.name} &nbsp;
				</c:forEach> </span> 
			<span>出版社：${item.publishName}
			</span> 

			<span>出版年：
			<c:if test="${empty item.publishDate}">
				
			</c:if>
			<c:if test="${not empty item.publishDate}">
				<fmt:formatDate value="${item.publishDate}" pattern="yyyy-MM-dd" />
			</c:if>
			</span>
			<c:if test="${list != 'resList'}">
				<span><a onclick="deleteObj(this, '${item.id}','${item.hasLink}');" href="javaScript:;"><font color="red">删除</font></a>|
				<c:choose>
			  	 	<c:when test="${item.isHidden}">
			  	 		<a onclick="changDisplay('${item.id}',0);" href="javaScript:;"><font color="#0d7bd5">隐藏</font></a>|
				 	</c:when>
				 	<c:otherwise>
				  		<a onclick="changDisplay('${item.id}',1);" href="javaScript:;"><font color="#0d7bd5">显示</font></a>|
				 	</c:otherwise>
			  	</c:choose>
		  </c:if>
		  <c:if test="${item.hasCatalog=='true'}">
				有目录|
			</c:if>
			<c:if test="${item.hasCatalog=='false'}">
				无目录|
			</c:if>
			<font color="#0d7bd5">
					<c:if test="${item.hasLink=='true'}">
						|有内容
					</c:if>
					<c:if test="${item.hasLink=='false'}">
						|无内容
					</c:if>
			</font> 
			</span>
		</div>
	</li>
</c:forEach>