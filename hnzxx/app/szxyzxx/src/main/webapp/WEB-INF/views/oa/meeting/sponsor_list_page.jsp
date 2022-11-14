<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
 
<c:choose>
	<c:when test="${!empty items }">
		<c:forEach items="${items}" var="entry" varStatus="statu">
			<table class="table table-bordered table-hover" id="${entry.id}_trp">
				<thead>
					<tr class="warning">
					 <th width="9%">会议名称</th>
						<th width="9%">会议级别</th>
						<th width="9%">主持人</th>
						<th width="10%">开始时间</th>
						<th width="10%">结束时间</th>
						<th width="15%">会议地点</th>
						
						<th width="10%">发起时间</th>
						<th width="9%">状态</th>
                        <th width="12%">操作</th>
					</tr>
				</thead>
				<tbody>
					  <tr>
					<td>${entry.meetingName }</td>
						<td>${entry.type }</td>
						<td>${entry.zhuchi }</td>
						<td>${entry.starttime }</td>
						<td>${entry.endtime }</td>
                      
						<td>${entry.address }</td>
						
						<td><fmt:formatDate value="${entry.createDate}" pattern="yyyy/MM/dd" /></td>
						  <td>${entry.state }</td>
                        <td class="caozuo"> 
                        <c:if test="${entry.state=='未开始' }">
                        <button class="btn btn-red" type="button" onclick="loadEditPage('${entry.id}');">管理</button>
                        <button class="btn btn-red" type="button" onclick="deleteObj(this, '${entry.id}');">删除</button></c:if>
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
