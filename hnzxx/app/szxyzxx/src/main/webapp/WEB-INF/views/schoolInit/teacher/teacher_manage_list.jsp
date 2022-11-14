<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<table class="table" style="border-top: 1px solid #dddddd;">
	<thead>
		<tr>
			<th><i class="ck" style="top: -11px;"></i></th>
			<th>序号</th>
			<th>名称</th>
			<th>工号</th>
			<th>卡号</th>
			<th>性别</th>
			<th>别名</th>
			<th>手机号码</th>
			<th>部门</th>
			<th>职务</th>
			<th class="caozuo">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${result }" var="teacher">
			<tr>
				<td><i class="${teacher.isDelete?'ck':'jinzhi' }" teacherid="${teacher.id }"></i></td>
				<td>${teacher.id }</td>
				<td>${teacher.name }</td>
				<td>${teacher.empCode }</td>
				<td>${teacher.empCard }</td>
				<td>${teacher.sex }</td>
				<td>${teacher.alias }</td>
				<td>${teacher.mobile }</td>
				<td>${teacher.departmentName }</td>
				<td>${teacher.position }</td>
				<td class="caozuo">
					<button class="btn btn-green" onclick="editTeacher(this, ${teacher.id})">编辑</button>
					<c:if test="${teacher.isDelete}">
						<button class="btn btn-red" onclick="deleteTeacher(${teacher.id});">删除</button>
					</c:if>
					<c:if test="${!teacher.isDelete }">
						<button class="btn btn-lightGray">删除</button>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>	