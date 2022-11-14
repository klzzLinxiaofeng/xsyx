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
<title>家长信息管理</title>

</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">首页 >  学生管理  >  家长信息列表 ><span>批量导入家长信息</span></p>
	
	<div class="content_main white"  style="border-radius: 0;">
		<div>
			<p class="banji_info_zt banji_info_bg_lan" id="one"><span><i>1</i></span>导入家长信息文件</p>
		</div>
		<div class="sjcsh_xx_detail" style="background: #ffffff;padding: 0;border: none;margin:0 20px 20px;">
			<div class="begin"> 
				<a href="javascript:void(0)" class="importTeacher" style="position: relative;">
					<p class="p1 student"><span class="drjs"></span></p>
					<p class="p2 student_word" style="margin-bottom: 13px;">选择上传文件</p>
					<input type="file" id="student" name="student" style="width:180px;height:180px;position:absolute;top:1px;left:0;opacity:0">
				</a>
				<div class="geiwomoban">
					<p>没有模板？</p>
					<div class="select_div">
						<span>年级：</span>
						<select class="span2"><option>一年级</option></select>
						<span>班级：</span>
						<select class="span2"><option>全部</option></select>
						<button class="btn btn-blue">导出模板</button>
					</div>
				</div>
			</div>
			<p class="lsdrjl"><span>2018-4-4 16:54</span> 有一份导入记录  <a href="javascript:void(0)">点击查看</a></p>
		</div>
	</div>
	<div class="tjing" style="display:none">
		<p class="word"></p>
		<p class="word1">正在导入，请稍等...</p>
		<div class="jdt_tu"><p style="width:20%"></p><span>20%</span></div>
	</div>
</div>

<script>
layer.open({
	  type: 1,
	  closeBtn: 0,
	  shade:  [0.01, '#fff'],
	  shadeClose : false,
	  area: ['400px', '272px'],
	  title: '导入家长信息', //不显示标题
	  content: $('.tjing'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
	  cancel: function(){
	    layer.close();
	  },
});
</script>
</body>
</html>