<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新生注册</title>
<%@ include file="/views/embedded/common.jsp"%>
<!-- Load TinyMCE -->
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/jquery.tinymce.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/ios-orientationchange-fix.js"></script>
<style>
input[type="radio"]{
	margin:0 5px;
	position:relative;
	top:-1px;
}
.form-horizontal{
	padding-bottom:0;
}
.xs_middle_1 .span1{
	width:70px;
}
.xs_middle_1 .span2{
	width:80px;
}
.xs_middle_1 .span3{
	width:100px;
}
</style>
<script>
$(function() {
	/* text-editor */
	$('textarea.tinymce-simple').tinymce({
		script_url : '${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js',
		theme : "simple"
		});
	$(".xs_left_1 ul li a").click(function(){
		$(".xs_left_1 ul li").removeClass("on");
		$(this).parent().addClass("on");
		var i=$(this).parent().index();
		$(".xs_middle_1 .xsbj_div").hide();
		$(".xs_middle_1 .xsbj_div").eq(i).show();
	});
	$(".add_table").click(function(){
		var j=$(".xsbj_div .xs_table tbody>tr").length+1;
		$(".xsbj_div .xs_table tbody").append("<tr><td>" +j+ "</td><td><input type='text' class='span1' /></td><td><input type='text' class='span2' /></td><td><input type='text' class='span3' /></td><td><input type='text' class='span3' /></td><td><button type='button' class='btn btn-red'>删除</button></td></tr>")
	});
	});
</script>
</head>
<body style="background-color:#F3F3F3 !important">
	<div class="row-fluid" >
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal tan_form" id="" action="javascript:void(0);">
						<div class="xszc">
							<div class="xs_left_1">
								<ul>
									<li class="on"><a href="javascript:void(0)">基本信息</a></li>
									<li><a href="javascript:void(0)">戶籍信息</a></li>
									<li><a href="javascript:void(0)">常用信息</a></li>
									<li><a href="javascript:void(0)">家庭成员</a></li>
									<li><a href="javascript:void(0)">备注</a></li>
								</ul>
							</div>
							<div class="xs_middle_1">
								<div class="xsbj_div" >
										<div class="control-group">
											<label class="control-label">姓名</label>
											<div class="controls">
												<input type="text" value="李明" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">英文名</label>
											<div class="controls">
												<input type="text" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">用户名</label>
											<div class="controls">
												<input type="text" disabled="disabled" value="44010404101" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">性别</label>
											<div class="controls" style="padding-top:5px;">
												<input type="radio" name="sex">女
												<input type="radio" name="sex">男
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">学生类别</label>
											<div class="controls">
												<input type="text" value="普通学生" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">学籍号</label>
											<div class="controls">
												<input type="text" value="G440702199901011234" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">入学时间</label>
											<div class="controls">
												<input type="text" value="2014年9月1日" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">在读状态</label>
											<div class="controls">
												<select><option>在读</option><option>休学</option></select>
											</div>
										</div>
										<div class="control-group" style="margin-bottom:0">
											<label class="control-label">个人照片</label>
											<div class="controls" >
												<a href="javascript:void(0)" class="up_img"></a>
											</div>
										</div>
								</div>
								<div class="xsbj_div" style="display:none">
										<div class="control-group">
											<label class="control-label">出生年月</label>
											<div class="controls">
												<input type="text" value="1995年7月31日" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">证件类型</label>
											<div class="controls">
												<select><option>身份证</option><option>学生证</option></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">证件号码</label>
											<div class="controls">
												<input type="text" disabled="disabled" value="440702199901011234" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">国籍</label>
											<div class="controls" style="padding-top:5px;">
												<input type="text" value="中国" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">民族</label>
											<div class="controls">
												<input type="text" value="汉族" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">籍贯</label>
											<div class="controls">
												<select><option>浙江嘉兴</option><option>广东广州</option></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">出生地</label>
											<div class="controls">
												<select><option>广东广州</option><option>浙江嘉兴</option></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">户口性质</label>
											<div class="controls">
												<input type="text" value="城市" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">户口所在地</label>
											<div class="controls">
												<input type="text" value="浙江嘉兴" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">户口所在地</label>
											<div class="controls">
												<input type="text" value="浙江嘉兴" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">居住地址</label>
											<div class="controls">
												<input type="text" value="广东广州" />
											</div>
										</div>
								</div>
								<div class="xsbj_div" style="display:none">
										<div class="control-group">
											<label class="control-label">特长</label>
											<div class="controls">
												<input type="text" value="美术、音乐" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">政治面貌</label>
											<div class="controls">
												<select><option>少先队员</option><option>团员</option><option>党员</option></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">宗教信仰</label>
											<div class="controls">
												<input type="text"  value="无" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">是否独生子女</label>
											<div class="controls">
												<input type="text"  value="否" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">电话</label>
											<div class="controls">
												<input type="text"  value="020-12345678" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">手机</label>
											<div class="controls">
												<input type="text"  value="13800138000" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">邮件</label>
											<div class="controls">
												<input type="text"  value="js@163.com" />
											</div>
										</div>
								</div>
								<div class="xsbj_div" style="display:none">
									<table class="table table-bordered xs_table">
										<thead>
											<tr><th>序号</th><th>成员姓名</th><th>与学生关系</th><th>家长手机</th><th>用户名</th><th>操作</th></tr>
										</thead>
										<tbody>
											<tr>
												<td>1</td>
												<td><input type="text" class="span1"></td>
												<td><input type="text" class="span2"></td>
												<td><input type="text" class="span3"></td>
												<td><input type="text" class="span3"></td>
												<td><button class="btn btn-red" type="button">删除</button></td>
											</tr>
											<tr>
												<td>2</td>
												<td><input type="text" class="span1"></td>
												<td><input type="text" class="span2"></td>
												<td><input type="text" class="span3"></td>
												<td><input type="text" class="span3"></td>
												<td><button class="btn btn-red" type="button">删除</button></td>
											</tr>
										</tbody>
									</table>
									<a href="javascript:void(0)" class="btn btn-green add_table"><i class="fa fa-plus"></i>新增</a>
								</div>
								<div class="xsbj_div" style="display:none;">
									<textarea rows="5" cols="80" style="width: 100%" class="tinymce-simple">
									</textarea>
								</div>
							</div>
							<div class="xs_right_1">
								<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
								<p>姓名</p>
								<input type="text" disabled="disabled" value="李明">
								<p>学籍号</p>
								<input type="text" disabled="disabled" value="G440702199901011234">
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button"
								onclick="saveOrUpdate();">保存</button>
							<button class="btn" type="button" onclick="saveOrUpdate();">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
</script>
</html>