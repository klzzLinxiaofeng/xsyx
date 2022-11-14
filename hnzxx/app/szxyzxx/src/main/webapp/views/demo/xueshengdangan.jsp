<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生档案查看</title>
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
	/* text-editor begin*/
	$('textarea.tinymce-simple').tinymce({
		script_url : '${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js',
		theme : "simple"
		});
	/* text-editor end*/
	/* 基本档案  begin*/
	$(".xs_left_1 ul li a").click(function(){
		$(".xs_left_1 ul li").removeClass("on");
		$(this).parent().addClass("on");
		var i=$(this).parent().index();
		$(".xs_middle_1 .xsbj_div").hide();
		$(".xs_middle_1 .xsbj_div").eq(i).show();
	});
	/* 基本档案  end*/
	/* table第一行  begin*/
	$(".table-striped  tr").each(function(){
		$(this).children("").eq(0).css("padding-left","14px");
	});
	$(".table-striped tbody").each(function(){
		$(this).children("tr").eq(0).children().css("background-color","#DAF0FA"); 
	});
	/* table第一行 end*/
	var m=$(".xszc .dacx .da_select li").length;
	$(".xszc .dacx .da_select li a").click(function(){
		$(".xszc .dacx .da_select li").removeClass("on");
		$(this).parent().addClass("on");
		var i=$(this).parent().index();
		$(".xszc .da_div .xsda").hide();
		$(".xszc .da_div .xsda").eq(i).show();
	});
	$(".xszc .dacx .da_select .left").click(function(){
		var j=$('.xszc .dacx .da_select li[class|="on"]').index();
		if(j>0){
		var k=j-1;
		$(".xszc .dacx .da_select li").removeClass("on");
		$(".xszc .dacx .da_select li").eq(k).addClass("on");
		$(".xszc .da_div .xsda").hide();
		$(".xszc .da_div .xsda").eq(k).show();
		var left = $(".xszc .dacx .da_select ul" ).css("left");
		 var left1 = parseInt(left);
		if(left1<0){
			$(".xszc .dacx .da_select ul").css("left",left1+67)
		}
		}
	});
	$(".xszc .dacx .da_select .right").click(function(){
		var j=$('.xszc .dacx .da_select li[class|="on"]').index();
		if(j<m-1){
		var l=j+1;
		$(".xszc .dacx .da_select li").removeClass("on");
		$(".xszc .dacx .da_select li").eq(l).addClass("on");
		$(".xszc .da_div .xsda").hide();
		$(".xszc .da_div .xsda").eq(l).show();
		if(7<j){
			var left = $(".xszc .dacx .da_select ul" ).css("left");
			 var left1 = parseInt(left) - 67;
			$(".xszc .dacx .da_select ul").css("left",left1)
		}
		};
		
		
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
						<div class="xszc" style="width:877px;">
							<div class="dacx">
								<div class="da_select">
									<a class="left" href="javascript:void(0)"></a>
									<div class="se_middle">
										<ul style="left:0;">
										  <li class="on"><a href="javascript:void(0)">基本档案</a></li>
										  <li><a href="javascript:void(0)">学生资助</a></li>
										  <li><a href="javascript:void(0)">学生处分</a></li>
										  <li><a href="javascript:void(0)">学生奖励</a></li>
										  <li><a href="javascript:void(0)">学生德育</a></li>
										  <li><a href="javascript:void(0)">学生考勤</a></li>
										  <li><a href="javascript:void(0)">学生健康</a></li>
										  <li><a href="javascript:void(0)">学生留级</a></li>
										  <li><a href="javascript:void(0)">学生跳级</a></li>
										  <li><a href="javascript:void(0)">学生成绩</a></li>
										</ul>
									</div>
									<a class="right" href="javascript:void(0)"></a>
								</div>
								<div class="da_div">
									<div class="xsda" style="padding:9px 0 0 10px;width:670px;height:572px; display:block;">
										<div class="xs_left_1">
											<ul>
												<li class="on"><a href="javascript:void(0)">基本信息</a></li>
												<li><a href="javascript:void(0)">戶籍信息</a></li>
												<li><a href="javascript:void(0)">常用信息</a></li>
												<li><a href="javascript:void(0)">家庭成员</a></li>
												<li><a href="javascript:void(0)">备注</a></li>
											</ul>
										</div>
										<div class="xs_middle_1" style="border-right:0 none;border-bottom:0 none;height:573px;">
											<div class="xsbj_div" >
													<div class="control-group">
														<label class="control-label">姓名</label>
														<div class="controls">
															<input type="text" value="李明" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">英文名</label>
														<div class="controls">
															<input type="text" disabled="disabled" />
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
															<input type="radio" name="sex" disabled="disabled">女
															<input type="radio" name="sex" disabled="disabled">男
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">学生类别</label>
														<div class="controls">
															<input type="text" value="普通学生" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">学籍号</label>
														<div class="controls">
															<input type="text" value="G440702199901011234" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">职务</label>
														<div class="controls">
															<input type="text" value="班长" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">入学时间</label>
														<div class="controls">
															<input type="text" value="2014年9月1日" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">离校时间</label>
														<div class="controls">
															<input type="text" value="" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">在读状态</label>
														<div class="controls">
															<select disabled="disabled"><option>在读</option><option>休学</option></select>
														</div>
													</div>
											</div>
											<div class="xsbj_div" style="display:none">
													<div class="control-group">
														<label class="control-label">出生年月</label>
														<div class="controls">
															<input type="text" value="1995年7月31日" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">证件类型</label>
														<div class="controls">
															<select disabled="disabled"><option>身份证</option><option>学生证</option></select>
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
															<input type="text" value="中国" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">民族</label>
														<div class="controls">
															<input type="text" value="汉族" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">籍贯</label>
														<div class="controls">
															<select disabled="disabled"><option>浙江嘉兴</option><option>广东广州</option></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">出生地</label>
														<div class="controls">
															<select disabled="disabled"><option>广东广州</option><option>浙江嘉兴</option></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">户口性质</label>
														<div class="controls">
															<input type="text" value="城市" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">户口所在地</label>
														<div class="controls">
															<input type="text" value="浙江嘉兴" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">现地址</label>
														<div class="controls">
															<input type="text" value="浙江嘉兴" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">居住地址</label>
														<div class="controls">
															<input type="text" value="广东广州" disabled="disabled" />
														</div>
													</div>
											</div>
											<div class="xsbj_div" style="display:none">
													<div class="control-group">
														<label class="control-label">特长</label>
														<div class="controls">
															<input type="text" value="美术、音乐" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">政治面貌</label>
														<div class="controls">
															<select disabled="disabled"><option>少先队员</option><option>团员</option><option>党员</option></select>
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">宗教信仰</label>
														<div class="controls">
															<input type="text"  value="无" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">是否独生子女</label>
														<div class="controls">
															<input type="text"  value="否" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">电话</label>
														<div class="controls">
															<input type="text"  value="020-12345678" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">手机</label>
														<div class="controls">
															<input type="text"  value="13800138000" disabled="disabled" />
														</div>
													</div>
													<div class="control-group">
														<label class="control-label">邮件</label>
														<div class="controls">
															<input type="text"  value="js@163.com" disabled="disabled" />
														</div>
													</div>
											</div>
											<div class="xsbj_div" style="display:none;padding:0">
												<table class="responsive table table-striped">
													<thead>
														<tr><th>序号</th><th>成员姓名</th><th>与学生关系</th><th>家长手机</th></tr>
													</thead>
													<tbody>
														<tr>
															<td>1</td>
															<td>李立</td>
															<td>父亲</td>
															<td>13000067566</td>
														</tr>
														<tr>
															<td>2</td>
															<td>李莉</td>
															<td>母亲</td>
															<td>13000067567</td>
														</tr>
													</tbody>
												</table>
											</div>
											<div class="xsbj_div" style="display:none;">
												<textarea rows="5" cols="80" style="width: 100%" class="tinymce-simple">
												</textarea>
											</div>
										</div>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>贫困类别</th>
													<th>贫困原因</th>
													<th>助困形式</th>
													<th>家庭收入/人口</th>
													<th>资助金额</th>
													<th>资助日期</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>特困</td>
													<td>家庭收入低</td>
													<td>助学金</td>
													<td>1000</td>
													<td>1000</td>
													<td>2014-09-01</td>
												</tr>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>特困</td>
													<td>家庭收入低</td>
													<td>助学金</td>
													<td>1000</td>
													<td>1000</td>
													<td>2014-09-01</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>处分类型</th>
													<th>处分原因</th>
													<th>处分日期</th>
													<th>到期日期</th>
													<th>撤销日期</th>
													<th>是否撤销处分</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>警告</td>
													<td>道德败坏</td>
													<td>2015-03-25</td>
													<td>2015-03-25</td>
													<td>2015-03-25</td>
													<td>2014-09-01</td>
												</tr>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>记过</td>
													<td>打架</td>
													<td>2015-03-25</td>
													<td>2015-03-25</td>
													<td>2015-03-25</td>
													<td>2014-09-01</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>获奖内容</th>
													<th>获奖级别</th>
													<th>获奖名次</th>
													<th>获奖类型</th>
													<th>获奖日期</th>
													<th>颁奖单位</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>三好学生</td>
													<td>学校级</td>
													<td>无</td>
													<td>荣誉称号</td>
													<td>2015-03-25</td>
													<td>学校</td>
												</tr>
												<tr>
													<td>1</td>
													<td>2014-2014学年下学期</td>
													<td>三好学生</td>
													<td>学校级</td>
													<td>无</td>
													<td>荣誉称号</td>
													<td>2015-03-25</td>
													<td>学校</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>评价项目分类</th>
													<th>评价项目</th>
													<th>评价结果</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>身体心理素质</td>
													<td>心理素质</td>
													<td>优秀</td>
												</tr>
												<tr>
													<td>2</td>
													<td>2014-2014学年下学期</td>
													<td>学习品质/td>
													<td>行为习惯</td>
													<td>优秀</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>事假</th>
													<th>病假</th>
													<th>缺课</th>
													<th>旷课</th>
													<th>迟到</th>
													<th>早退</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>1次（4天）</td>
													<td>0次</td>
													<td>0次</td>
													<td>0次</td>
													<td>0次</td>
													<td>0次</td>
												</tr>
												<tr>
													<td>2</td>
													<td>2014-2014学年下学期</td>
													<td>0次</td>
													<td>2次（30天）</td>
													<td>0次</td>
													<td>0次</td>
													<td>0次</td>
													<td>0次</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>健康类型</th>
													<th>附件</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>健康或良好 健康或良好</td>
													<td></td>
												</tr>
												<tr>
													<td>2</td>
													<td>2014-2014学年下学期</td>
													<td>健康或良好 健康或良好</td>
													<td><span>附件.doc</span><button class="btn" style="float:right">下载</button></td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>所在班级</th>
													<th>留往班级</th>
													<th>留级日期</th>
													<th>留级原因</th>
													<th>留级状态</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>初一（1）班</td>
													<td>初一（2）班</td>
													<td>2014-12-12</td>
													<td>没有完成学业</td>
													<td>批注留级</td>
												</tr>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>初一（2）班</td>
													<td>初一（4）班</td>
													<td>2014-12-12</td>
													<td>没有完成学业</td>
													<td>批注留级</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;">
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>序号</th>
													<th>学期</th>
													<th>所在班级</th>
													<th>跳往班级</th>
													<th>跳级日期</th>
													<th>跳级原因</th>
													<th>跳级状态</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>初一（1）班</td>
													<td>初一（2）班</td>
													<td>2014-12-12</td>
													<td>已经完成学业</td>
													<td>批注跳级</td>
												</tr>
												<tr>
													<td>1</td>
													<td>2014-2014学年上学期</td>
													<td>初一（2）班</td>
													<td>初一（4）班</td>
													<td>2014-12-12</td>
													<td>已经完成学业</td>
													<td>批注跳级</td>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="xsda" style="display:none;padding-top:9px;">
									<div class="nj_div" >
										<select>
											<option>2013-2014学年上学期</option>
											<option>2013-2014学年下学期</option>
											<option>2014-2015学年上学期</option>
											<option>2014-2015学年下学期</option>
										</select>
									</div>
										<table class="responsive table table-striped">
											<thead>
												<tr>
													<th>成绩类型</th>
													<th>语文</th>
													<th>数学</th>
													<th>英语</th>
													<th>科学</th>
													<th>地理</th>
													<th>历史</th>
													<th>政治</th>
													<th>体育</th>
													<th>美术</th>
													<th>音乐</th>
													<th>计算机</th>
													<th>平均分</th>
													<th>总分</th>
													<th>班级排名</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>第一次月考</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td>100</td>
													<td>700</td>
													<td>1</td>
												</tr>
												<tr>
													<td>第二次月考</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td>100</td>
													<td>700</td>
													<td>1</td>
												</tr>
												<tr>
													<td>期中</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td>100</td>
													<td>700</td>
													<td>1</td>
												</tr>
												<tr>
													<td>第三次月考</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td>100</td>
													<td>700</td>
													<td>1</td>
												</tr>
												<tr>
													<td>第四次月考</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td>100</td>
													<td>700</td>
													<td>1</td>
												</tr>
												<tr>
													<td>期末</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td>100</td>
													<td></td>
													<td></td>
													<td></td>
													<td></td>
													<td>100</td>
													<td>700</td>
													<td>1</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							
							<div class="xs_right_1" style="height: 576px;">
								<img alt="头像" src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
								<p>姓名</p>
								<input type="text" disabled="disabled" value="李明">
								<p>学籍号</p>
								<input type="text" disabled="disabled" value="G440702199901011234">
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button">完整档案导出</button>
							<button class="btn btn-warning" type="button" >成绩单导出</button>
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