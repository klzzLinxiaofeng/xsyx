<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
input[type="checkbox"]{
	position:relative;
	top:-3px;
	margin-right:8px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<p class="all_select"><input type="checkbox" />全选</p>
					<div class="shuju_list">
						<ul>
							<li><input type="checkbox" />姓名</li>
							<li><input type="checkbox" />英文名</li>
							<li><input type="checkbox" />用户名</li>
							<li><input type="checkbox" />性别</li>
							<li><input type="checkbox" />学生类别</li>
							<li><input type="checkbox" />学籍号</li>
							<li><input type="checkbox" />职务</li>
							<li><input type="checkbox" />入学时间</li>
							<li><input type="checkbox" />离校时间</li>
							<li><input type="checkbox" />在读状态</li>
							<li><input type="checkbox" />个人照片</li>
							<li><input type="checkbox" />出生日期</li>
							<li><input type="checkbox" />证件类型</li>
							<li><input type="checkbox" />证件号码</li>
							<li><input type="checkbox" />国籍</li>
							<li><input type="checkbox" />民族</li>
							<li><input type="checkbox" />籍贯</li>
							<li><input type="checkbox" />出生地</li>
							<li><input type="checkbox" />户口性质</li>
							<li><input type="checkbox" />户口所在地</li>
							<li><input type="checkbox" />现地址</li>
							<li><input type="checkbox" />居住地址</li>
							<li><input type="checkbox" />特长</li>
							<li><input type="checkbox" />姓政治面貌</li>
							<li><input type="checkbox" />宗教信仰</li>
							<li><input type="checkbox" />是否独生子女</li>
							<li><input type="checkbox" />电话</li>
							<li><input type="checkbox" />手机</li>
							<li><input type="checkbox" />邮件</li>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="form-actions tan_bottom">
									<button  class="btn btn-danger" type="button">导出</button>
								</div>
					<!-- <div class="form-actions" style="background-color:#297657;">
		            	<div style="text-align:center;">
		             		<button  class="btn btn-danger" type="button">导出</button>
		             		<button  class="btn" type="button">取消</button>
		            	</div>
		            </div> -->
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$(" .all_select input").click(function(){
			$(".shuju_list ul li input").prop("checked",true);
		})
	})
	</script>
</body>
</html>
