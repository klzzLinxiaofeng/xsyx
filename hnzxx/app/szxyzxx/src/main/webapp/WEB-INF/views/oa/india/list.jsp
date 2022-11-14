<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.currentPage}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${items}" var="item" varStatus="i">
	<tr id="${item.id}_tr">
				<td>${i.index+1}</td>
				<td>${item.title}</td>
				<td>${departmentName.get(i.index)}</td>
				<td>${item.userName}</td>
				<td>${teacher.mobile}</td>
				<td>
					<span class="red">
				 <c:if test="${item.indiaStatus==0}">
				            待处理...
				 </c:if>
				 <c:if test="${item.indiaStatus==1}">
				            进行中...
				 </c:if>
				 <c:if test="${item.indiaStatus==2}">
				            已完成
				 </c:if>
				  </span> 
				</td>
				<td>${item.remark}</td>
				<td>
				<p
				style='display: inline-block; margin-bottom: 0; width: 240px; overflow: hidden;'>
				  ${upload.get(i.index).realFileName}
				<%-- <a target="_blank" id="a"
					href='<entity:getHttpUrl uuid="${item.uploadFile}"/>'>${upload.get(i.index).realFileName}</a> --%>
				</p> 
			<input type="hidden" id="entityId" name="uploadFile" value="${item.uploadFile }"/>
				</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${item.createDate}" ></fmt:formatDate></td>
				<td>
				    <a target="_blank" id="a" href='<entity:getHttpUrl uuid="${item.uploadFile}"/>'><button class="btn"  type="button" onclick="download('${item.id}');"> 下载</button></a>
<%-- 				     <%-- <button class="btn" type="button" onclick="download('${item.id}');">下载</button> --%> 
				     <button class="btn" type="button" onclick="downloadPrint('${item.id}');">已下载打印</button>
				</td>
		<td class="caozuo">
		   <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
           </c:if>
           <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">		
			<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
		   </c:if>
		</td>
	</tr>
</c:forEach>
