<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/szxy/css/szxy_common.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/szxy/css/banji_info_manage.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<title>家长信息-导入结果</title>
<style>
.sjcsh_xx_detail ul li.on {
    border: solid 1px #e3e8ec;
    border-bottom: none;
}
.sjcsh_xx_detail ul li {
    height: 40px;
    line-height: 39px;
    border: 1px solid #ffffff;
}
.content_main {
    box-shadow: none;
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	
	<p class="top_link">首页 >  学生管理  >  家长信息列表 > 批量导入家长信息 > <span>查看导入结果</span></p>
	<div class="content_main white"  style="border-radius: 0;">
		<div>
			<p class="banji_info_zt banji_info_bg_lan" id="one"><span><i>2</i></span>导入结果</p>	
		</div>
		<div class="sjcsh_xx_detail" style="border-top:none">
			<div class="f_left">
				<ul>
					<li class="on" id="tii_daoru_ok">导入成功</li>
					<li id="tii_wrong_data">错误数据（89）</li>
				</ul>
			</div>
			<div class="f_right">
				<span>导入时间：2208/06/19 16:40</span>
			</div>
			
		</div>
		<div style="background-color: #ffffff;padding:20px;margin: 0 20px 20px;border: solid 1px #e3e8ec;">
			<div class="sjcsh_xx_table content_main">
				<table class="table">
			<thead>
				<tr><th>年级</th><th>班级</th><th>班内学号</th><th>学生姓名</th><th>监护人</th><th>手机号码</th><th>与学生关系</th><th>异常提示</th></tr>
			</thead>
			<tbody style="background-color: #fff1f1;">
				<tr><td>一年级</td><td>2班</td><td>20183101</td><td>王小明</td><td>刘先生</td><td>16757837676</td><td></td><td class="color_f44336">姓名重复，无别名</td></tr>
				<tr><td>一年级</td><td>2班</td><td>20183101</td><td>王小明</td><td>刘先生</td><td>16757837676</td><td></td><td class="color_f44336">已存在</td></tr>
				<tr><td>一年级</td><td>2班</td><td>20183101</td><td>王小明</td><td>刘先生</td><td>16757837676</td><td></td><td class="color_f44336">姓名重复，无别名</td></tr>
				<tr><td>一年级</td><td>2班</td><td>20183101</td><td>王小明</td><td>刘先生</td><td>16757837676</td><td></td><td class="color_f44336">手机号码重复</td></tr>
				<tr><td>一年级</td><td>2班</td><td>20183101</td><td>王小明</td><td>刘先生</td><td>16757837676</td><td></td><td class="color_f44336">已存在</td></tr>
				<tr><td>一年级</td><td>2班</td><td>20183101</td><td>王小明</td><td>刘先生</td><td>16757837676</td><td></td><td class="color_f44336">姓名重复，无别名</td></tr>
			</tbody>
		</table>
				<div class="page_div">
					<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
						<jsp:param name="id" value="studentAid_list_content" />
						<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
						<jsp:param name="pageSize" value="${page.pageSize}" />
					</jsp:include>
					<div class="clear"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
$('.sjcsh_xx_detail ul li').click(function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
});
</script>
</body>
</html>