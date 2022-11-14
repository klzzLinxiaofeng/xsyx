<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<tr>
	<td style="padding:0;border:0 none;display:none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />  
	</td> 
</tr>
<tr>
	<th style="width:300px">科目</th>
	<th style="width:300px">科任教师</th>
	<th>上课特色</th>
	<th  class="caozuo">操作</th>
</tr>
<c:forEach items="${courseTeacherTeacherList}" var="st">
	<tr id="${st.id}">
		<td>${st.subjectName}</td>
		<td>${st.teacherName}</td>
		<td><span class="skc">${st.characteristic}</span></td>
		<td class="caozuo">
			<%-- <button class="btn btn-blue" onclick="loadModifyCourseTeacherPage(${st.id});" type="button">编辑</button> --%>
			<button class="btn btn-blue" onclick="characteristic(${st.userId});" type="button">走班上课特色</button>
			<button class="btn btn-red" onclick="deleteCourseTeacher(${st.id});" type="button">删除</button>
		</td>
	</tr>
</c:forEach>
<style>
.skc{    
	overflow: hidden;
	text-overflow:ellipsis;
	white-space: nowrap;
    display: inline-block;
    width: 400px;}
</style>
