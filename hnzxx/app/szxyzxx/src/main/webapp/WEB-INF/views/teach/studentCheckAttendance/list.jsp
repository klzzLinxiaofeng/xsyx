<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td>
</tr>
<c:forEach items="${studentCheckAttendanceList}" var="sca" varStatus="i">
	<tr id="${sca.id}_tr">
				<td>${i.index+1+(page.currentPage - 1) * 10}<input id="xn" type="hidden" value="${sca.schoolYearId }"/></td>
				<td>${sca.teamName}</td>
				<td>${sca.studentName}</td>
				<td>
					<c:choose>
					 <c:when test="${sca.count0==0 }">
					     0次
					 </c:when>
					 <c:otherwise>
				 
						<a href="javascript:void(0);" onclick="addOne('${sca.id }','0');"> ${sca.count0}次
						     <c:forEach items="${studentCheckAttendanceList0}" var="sca0">
						       <c:if test="${sca.studentId==sca0.studentId }">
						         (${sca0.sum0 }天)
						       </c:if>
						     </c:forEach>
						    </a>
				 
					 </c:otherwise>
					</c:choose>
				
				</td>
				<td>
				    <c:choose>
					 <c:when test="${sca.count1==0 }">
					     0次
					 </c:when>
					 <c:otherwise>
				 
						<a href="javascript:void(0);" onclick="addOne('${sca.id }','1');">${sca.count1}次
					     <c:forEach items="${studentCheckAttendanceList1}" var="sca1">
					       <c:if test="${sca.studentId==sca1.studentId }">
					         (${sca1.sum0 }天)
					       </c:if>
					     </c:forEach>
					    </a>
				 
					 </c:otherwise>
					</c:choose>
				
				
				 </td>
				<td>
				
				   <c:choose>
					 <c:when test="${sca.count2==0 }">
					     0次
					 </c:when>
					 <c:otherwise>
				 
						<a href="javascript:void(0);" onclick="addOne('${sca.id }','2');"> ${sca.count2}次
					      <c:forEach items="${studentCheckAttendanceList2}" var="sca2">
					       <c:if test="${sca.studentId==sca2.studentId }">
					         <c:if test="${sca2.count2!=0 }">
						         (${sca2.sum1 }节)
					         </c:if>
					       </c:if>
					     </c:forEach>
					    </a>
				 
					 </c:otherwise>
					</c:choose>
				
				
				</td>
				<td>
				
				<c:choose>
					 <c:when test="${sca.count3==0 }">
					     0次
					 </c:when>
					 <c:otherwise>
				 
						<a href="javascript:void(0);" onclick="addOne('${sca.id }','3');"> ${sca.count3}次
						   <c:forEach items="${studentCheckAttendanceList3}" var="sca3">
						       <c:if test="${sca.studentId==sca3.studentId }">
						       <c:if test="${sca3.count2!=0 }">
						         (${sca3.sum1 }节)
						       </c:if>
						       </c:if>
						     </c:forEach>
						  </a>
				 
					 </c:otherwise>
					</c:choose>
				
				
				
				
				</td>
				<td>
				     <c:choose>
				      <c:when test="${sca.count4==0 }">
				        0次
				      </c:when>
				      <c:otherwise>
				      <a href="javascript:void(0);" onclick="addOne('${sca.id }','4');"> 
				       <c:forEach items="${studentCheckAttendanceList4}" var="sca4">
				         <c:if test="${sca.studentId==sca4.studentId }">
				           ${sca4.sum2 }次
				         </c:if>
				       </c:forEach>
					   </a>
				      </c:otherwise>
				     </c:choose>
				</td>
				<td>
				     <c:choose>
				      <c:when test="${sca.count5==0 }">
				        0次
				      </c:when>
				      <c:otherwise>
				      <a href="javascript:void(0);" onclick="addOne('${sca.id }','5');">
				       <c:forEach items="${studentCheckAttendanceList5}" var="sca5">
				         <c:if test="${sca.studentId==sca5.studentId }">
				           ${sca5.sum2 }次
				         </c:if>
				       </c:forEach>
					    </a>
				      </c:otherwise>
				     </c:choose>
				</td>
		<td class="caozuo">
			<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
			<button class="btn btn-green" type="button" onclick="loadViewerPage('${sca.id}');">详情</button>
			</c:if>
			<button class="btn btn-blue" type="button" onclick="loadCreatePages('${sca.id }');">添加</button>
		</td>
	</tr>

</c:forEach>

<script type="text/javascript">
//	加载创建对话框
function loadCreatePages(id) {
	$.initWinOnTopFromLeft('添加', '${pageContext.request.contextPath}/teach/studentCheckAttendance/creatorOther?id=' + id, '900', '500');
}

//针对某个人某个类型进行添加
function addOne(id,attendanceType){
	var xn = $("#xn").val();
	$.initWinOnCurFromLeft('查看', '${pageContext.request.contextPath}/teach/studentCheckAttendance/oneView?id=' + id+' &xn='+xn+' &attendanceType='+attendanceType, '600', '500');
	
	
}

</script>

