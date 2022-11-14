<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
 
<c:choose>
	<c:when test="${!empty items }">
		<c:forEach items="${items}" var="entry" varStatus="statu">
			<table class="table table-bordered table-hover" id="${entry.id}_trs">
				<thead>
					<tr class="warning">
					 <th width="9%">会议名称</th>
						<th width="6%">会议级别</th>
						<th width="5%">主持人</th>
						<th width="8%">开始时间</th>
						<th width="8%">结束时间</th>
						<th width="15%">会议地点</th>
						
						<th width="8%">发起时间</th>
						<th width="5%">状态</th>
                        <th width="20%">操作</th>
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
                        
                        <c:if test="${!empty entry.summaryId }"> <button class="btn btn-red" type="button" onclick="showSummaryPage('${entry.summaryId}');">查看</button>
                          
                          <button class="btn btn-red" type="button" onclick="delSummaryPage(this,'${entry.summaryId}');">删除纪要</button>
                         </c:if>
                        
                        <c:if test="${empty entry.summaryId }"><button class="btn btn-red" type="button" onclick="loadSummaryPage('${entry.id}');">
                 发布纪要 </button></c:if>  
                           
                        
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
