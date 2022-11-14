<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<input type="hidden" id="totalRows" name="totalPages" value="${page.totalRows}" />
<table class="table" style="border-top: 1px solid #dddddd;">
	<thead>
		<tr>
			<th><i class="ck" style="top: -11px;"></i></th>
			<th>序号</th>
			<th>班内学号</th>
			<th>教育云卡号</th>
			<th>食堂卡号</th>
			<th>学生姓名</th>
			<th>年级</th>
			<th>班级</th>
			<th>监护人</th>
			<th>监护人手机</th>
			<th class="caozuo">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${result }" var="student">
				<c:if test="${fn:length(student.parents)>0 }">
					<c:forEach items="${student.parents }" var="parent">
						<tr>
							<td><i class="${student.delete?'ck':'jinzhi' }" studentid="${student.id }"></i></td>
							<td>${student.id }</td>
							<td>${student.num}</td>
							<td>${student.empCard}</td>
							<td>${student.name }</td>
							<td>${student.gradeName }</td>
							<td>${student.teamName }</td>
							<td>${parent.name }</td>
							<td>${parent.mobile }</td>
							<td class="caozuo">
								<button class="btn btn-green"  onclick="editStudent(this, ${student.id })">编辑</button>
								<c:if test="${student.delete }">
									<button class="btn btn-red" onclick="deleteStudent(${student.id });">删除</button>
								</c:if>
								<c:if test="${!student.delete }">
									<button class="btn btn-lightGray">删除</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${fn:length(student.parents)==0 }">
					<tr>
						<td><i class="${student.delete?'ck':'jinzhi' }" studentid="${student.id }"></i></td>
						<td>${student.id }</td>
						<td>${student.num }</td>
						<td>${student.name }</td>
						<td>${student.gradeName }</td>
						<td>${student.teamName }</td>
						<td></td>
						<td></td>
						<td class="caozuo">
							<button class="btn btn-green"  onclick="editStudent(this,${student.id })">编辑</button>
							<c:if test="${student.delete }">
								<button class="btn btn-red" onclick="deleteStudent(${student.id });">删除</button>
							</c:if>
							<c:if test="${!student.delete }">
								<button class="btn btn-lightGray">删除</button>
							</c:if>
						</td>
					</tr>
				</c:if>
		</c:forEach>
	</tbody>
</table>