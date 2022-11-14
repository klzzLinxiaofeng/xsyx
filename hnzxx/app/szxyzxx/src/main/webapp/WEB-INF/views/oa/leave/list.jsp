<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
 
<c:choose>
	<c:when test="${!empty items }">
		<c:forEach items="${items}" var="entry" varStatus="statu">
			<table class="table table-bordered table-hover" id="${entry.id}_tr">
				<thead>
					<tr class="warning">
					 <th width="9%">请假人</th>
						<th width="9%">请假类别</th>
						<th width="9%">请假天数</th>
						<th width="10%">开始时间</th>
						<th width="10%">结束时间</th>

						<th>请假事由</th>
						<th width="10%">审批人</th>
						<th width="10%">创建时间</th>
						<th width="10%">请假状态</th>
                        <th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					  <tr>
					<td>${entry.createname }</td>
						<td>${entry.leavetype }</td>
						<td>${entry.day }</td>
						<td>${entry.starttime }</td>
						<td>${entry.endtime }</td>
                      
						<td>${entry.leaveinfo }</td>
						<td>${entry.selApprName }</td>
						<td><fmt:formatDate value="${entry.createDate}" pattern="yyyy/MM/dd" /></td>
						<td> 正在审批中 </td>
                        <td> <button class="btn btn-red" type="button" onclick="deleteObj(this, '${entry.id}');">删除</button></td>

					</tr> 
				</tbody>
			</table>
 
		</c:forEach>
	</c:when>
	<c:otherwise>
		<table class="table table-bordered table-hover">
			<tr>
				<td colspan="10"><div align="center">暂无数据！</div>
				</td>
			</tr>
		</table>
	</c:otherwise>
</c:choose>
