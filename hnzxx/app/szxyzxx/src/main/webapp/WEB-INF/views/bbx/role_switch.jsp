<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/res/css/bbx/bbx.css">
<title>角色切换</title>
</head>
<body>
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 0;">
					<div class="role">
						<h2>角色切换</h2>
						<div class="mediacy">
							<c:choose>
								<c:when test="${fn:length(roleCodes) > 0}">
									<c:forEach items="${roleCodes}" var="roleCode">
										<%-- 	<a href="${pageContext.request.contextPath}/bbx/main?roleCode=${roleCode}"> --%>
										<c:choose>
											<c:when test='${roleCode == "CLASS_MASTER"}'>
												<div class="bzr">
													<a href="${pageContext.request.contextPath}/bbx/main?currentRoleCode=${roleCode}"><img
														src="${pageContext.request.contextPath}/res/css/bbx/images/bzr.jpg"></a>
													<p>班主任</p>
												</div>
											</c:when>
											<c:when test='${roleCode == "SUBJECT_TEACHER"}'>
												<div class="js">
													<a href="${pageContext.request.contextPath}/bbx/main?currentRoleCode=${roleCode}"><img
														src="${pageContext.request.contextPath}/res/css/bbx/images/js.jpg"></a>
													<p>科任教师</p>
												</div>
											</c:when>
											<c:when test='${roleCode == "SCHOOL_MASTER"}'>
												<div class="xiaozhang">
													<a href="${pageContext.request.contextPath}/bbx/main?currentRoleCode=${roleCode}"><img 
														src="${pageContext.request.contextPath}/res/css/bbx/images/xz.jpg"></a>
													<p>校长</p>
												</div>
											</c:when>
											<c:when test='${roleCode == "SCHOOL_LEADER"}'>
												<div class="xiaozhang">
													<a href="${pageContext.request.contextPath}/bbx/main?currentRoleCode=${roleCode}"><img 
														src="${pageContext.request.contextPath}/res/css/bbx/images/xz.jpg"></a>
													<p>管理者</p>
												</div>
											</c:when>
											<c:when test='${roleCode == "STUDENT"}'>
												<div class="xiaozhang">
													<a href="${pageContext.request.contextPath}/bbx/main?currentRoleCode=${roleCode}"><img 
														src="${pageContext.request.contextPath}/res/css/bbx/images/xz.jpg"></a>
													<p>学生</p>
												</div>
											</c:when>
										</c:choose>
										<!-- 										</a> -->
									</c:forEach>
								</c:when>
								<c:otherwise>
									请将当前用户设置为任课教师、班主任、校长、管理者任一角色
								</c:otherwise>
							</c:choose>
						</div>
						<p class="zhu">注：点击对应角色头像进行切换</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>