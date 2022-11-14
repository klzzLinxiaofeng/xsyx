<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr>
	<td style="padding: 0; border: 0 none;"><input type="hidden"
		id="currentPage" name="currentPage" value="${page.currentPage}" /> <input
		type="hidden" id="totalPages" name="totalPages"
		value="${page.totalPages}" /></td>
</tr>
<c:choose >
  <c:when test="${teamVoList.size()>0 }">
	<c:forEach items="${teamVoList}" var="teamVo" varStatus="i">
		<tr id="${teamVo.team.id }_tr">
			<td>${i.index+1+(page.currentPage - 1) * 10}</td>
			<td>${teamVo.team.name }</td>
			<td>${teamVo.account }</td>
<!-- 			<td>40</td> -->
<!-- 			<td>7</td> -->
		<c:if test="${teamVo.openState=='0' }">
			<td ><span style="color:#616F77;">已开通</span></td>
			<td class="caozuo">
				<button class="btn btn-black1" onclick="closeTeamAccount(this,${teamVo.team.schoolId },'${teamVo.team.id }');">取消开通</button>
				 <c:if test="${teamVo.openState=='0' }">
				 	<button class="btn btn-blue1" onclick="revisePasswordPage(this,${teamVo.team.schoolId },'${teamVo.team.id }',0);">修改班级账号密码</button>
					<button class="btn btn-blue1" onclick="resetPassword(this,${teamVo.team.schoolId },'${teamVo.team.id }',0);">重置班级账号密码</button>
				 </c:if>
				 <c:if test="${teamVo.accessType=='1' }">
				    <button class="btn btn-blue1" onclick="resetPassword(this,${teamVo.team.schoolId },'${teamVo.team.id }',1);">重置屏幕权限密码</button>
				 </c:if>
			</td>
		</c:if>
		<c:if test="${teamVo.openState=='1'  }">
			<td>未开通</td> 
 			<td class="caozuo"><button class="btn btn-green1" onclick="loadCreatePage(this,${teamVo.team.schoolId },'${teamVo.team.id }');">开通</button></td> 
		</c:if>
 		
		</tr>
	</c:forEach>
  </c:when>
  <c:otherwise>
     <tr>
       <td colspan="6"><span style="text-align:center;">暂无数据!</span></td>
     </tr>
  </c:otherwise>
</c:choose>
<script type="text/javascript">
  $(function(){
	  $("#totalNum").text("${totalNum}");
	  $("#teamAccountNum").text("${teamAccountNum}");
	  $("#unTeamAccountNum").text("${unTeamAccountNum}");
  });


</script>
	