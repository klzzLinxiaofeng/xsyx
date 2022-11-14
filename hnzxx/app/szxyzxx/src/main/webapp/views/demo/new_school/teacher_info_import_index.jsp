<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title></title>
</head>
<body style="background-color: #e3e3e3;">
	<div class="sjcsh_xx_detail" style="background: #ffffff;padding: 0;border: none;margin:0 20px 20px">
		<p class="zhuyi">注：姓名相同的教师，一定要输入别名进行区分。会影响到任课安排的区分。</p>
		<div class="begin"> 
			<a href="javascript:void(0)" class="importTeacher">
				<p class="p1"><span class="drjs"></span></p>
				<p class="p2" style="margin-bottom: 13px;">导入教师</p>
			</a>
			<a href="javascript:void(0)" class="jsdrmb" style="padding-bottom: 167px;">教师导入模板.xls</a>
			
		</div>
	</div>
<div class="tjing" style="display:none">
	<p class="word"></p>
	<p class="word1">正在导入，请稍等...</p>
	<div class="jdt_tu"><p style="width:20%"></p><span>20%</span></div>
</div>
<div class="tis_import_teacher" style="display:none;text-align: center;padding-top:36px;">
	<p>教师信息 <span> 285 </span> 条</p>
	<p>警告数据 <span style="color: #fda32f;"> 10 </span >条</p>
	<p>错误数据 <span style="color:#ff5252"> 10 </span> 条</p>
</div>
<div class="tis_import_student_parents" style="display:none;text-align: center;padding-top:36px;">
	<p style="color:#ff5252">无法导入教师信息.xls文件</p>
	<p style="color:#ff5252">请确定导入文件是否正确</p>
</div>
<script>
$('.importTeacher').click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['400px', '272px'],
		  title: '导入教师信息', //不显示标题
		  content: $('.tjing'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
	});
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['337px', '221px'],
		  title: '导入教师信息', //不显示标题
		  content: $('.tis_import_teacher'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['337px', '191px'],
		  title: '导入学生与家长', //不显示标题
		  content: $('.tis_import_student_parents'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
})
</script>
</body>
</html>