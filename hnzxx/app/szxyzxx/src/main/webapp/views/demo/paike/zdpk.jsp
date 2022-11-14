<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>自动排课</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="自动排课" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							自动排课
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="pkrj"><a href="${pageContext.request.contextPath}/res/excel/zonda96.rar">下载自动排课软件</a></div>
							<div class="xz_div">
								<a href="${pageContext.request.contextPath}/res/excel/TeacherModel.xls" class="zd_1">导出教师信息</a>
								<a href="${pageContext.request.contextPath}/res/excel/ClassModel.xls" class="zd_2">导出班级信息</a>
								<a href="${pageContext.request.contextPath}/res/excel/CourseModel.xls" class="zd_3">导出学科信息</a>
								<a href="${pageContext.request.contextPath}/res/excel/LocationModel.xls" class="zd_4">导出场地信息</a>
								<a href="${pageContext.request.contextPath}/res/excel/class_plan.xls" class="zd_5">导出班级计划</a>
								<a href="${pageContext.request.contextPath}/res/excel/teach_plan.xls" class="zd_6">导出教师计划</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>