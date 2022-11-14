<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
 <%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<c:choose>
	<c:when test="${!empty items }">
		<c:forEach items="${items}" var="item" varStatus="statu">
			<table class="table table-bordered table-hover" id="${entry.id}_trp">
				<thead>
					<tr class="warning">
					<th width="9%">文件主题</th>
					 <th width="10%">发文单位</th>
						<th width="9%">重要程度</th>
						 	<th width="10%">附件</th>
						
						<th width="10%">发布时间</th>
							 
                        <th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					  <tr>
					<td>${item.title }</td>
						<td>${item.author }</td>
						 <td>${item.emergencyLevel }</td>
						  <td>
		    <c:if test="${!empty item.attachmentUuid}">
		    
		    <a target="_blank" id="a" href='<entity:getHttpUrl uuid="${item.attachmentUuid}"/>'> ${item.realFileName} </a>
		    </c:if>
				  
			 
			 
				</td>
						<td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
						  
                        <td class="caozuo"> 
                        
			<button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			 
				<button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
 
                        </td>


					</tr> 
				</tbody>
			</table>
 
		</c:forEach>
	</c:when>
	<c:otherwise>
		<table class="table table-bordered table-hover">
			<tr>
				<td colspan="8"><div align="center">暂无数据！</div>
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
