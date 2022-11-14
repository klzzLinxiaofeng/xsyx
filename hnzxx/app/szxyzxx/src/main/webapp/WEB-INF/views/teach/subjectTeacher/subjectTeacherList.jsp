<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr>
	<td style="padding:0;border:0 none;display:none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
		<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
	</td> 
</tr>
<tr>
	<th>科目</th>
	<th>科任教师</th>
	<th  class="caozuo">操作</th>
</tr>
<c:forEach items="${subjectTeacherList}" var="st">
	<tr id="${st.id}">
		<td>${st.subjectName}</td>
		<td>${st.name}</td>
		<td class="caozuo">
			<button class="btn btn-blue"
				onclick="loadModifySubjectTeacherPage(${st.id});" type="button">编辑</button>
			<%-- <button class="btn btn-red" onclick="deleteSubjectTeacher(${st.id});"
				type="button">删除</button> --%>
		</td>
	</tr>
</c:forEach>
