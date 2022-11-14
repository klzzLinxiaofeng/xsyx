<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>班主任</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">首页  >  班主任评价  > <span>评价设置</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>评价设置</p></div>
		</div>
		<div class="pjsz grey">
			<div class="pjsz_1">
				<p class="p1">评价周期：</p>
				<p  class="p2"><span><input type="radio" name="pjzq">每日一评</span><span><input type="radio" name="pjzq">每周一评</span><span><input type="radio" name="pjzq">每月一评</span></p>
			</div>
			<div class="pjsz_1">
				<p class="p1">评价模式：</p>
				<p class="p2"><span><input type="radio" name="pjms">评五星模式</span><span><input type="radio" name="pjms">点赞模式</span></p>
			</div>
			<div class="pjsz_2" style="display:none">注：这部分暂时不允许点击</div>
		</div>
		<div class="pjsz">
			<div class="pjsz_1">
				<p class="p1">评价项目：</p>
				<p class="p2">
					<input type="text" placeholder="1">
					<input type="text" placeholder="2">
					<input type="text" placeholder="3">
					<input type="text" placeholder="4">
					<input type="text" placeholder="5">
					<input type="text" placeholder="6">
				</p>
			</div>
			<div class="tx_div">
				<a href="javascript:void(0)" style="z-index:9999"><i class="fa fa-question-circle"></i></a>
				<div class="ts_div1" style="display:none;z-index:1">
					<div class="ts_div">
						<p>注：每课一评，评价老师当天上课的效果与意见反馈。</p>
			            <p>防止无课评价的误操作。误操作的数据将不会纳入到教师评价的统计数据中。</p>
		            </div>
		            <p class="sjx"></p>
	            </div>
            </div>
			<div class="pjsz_2">注：评价项修改以后，下一个周期生效。</div>
		</div>
		<div class="pjsz">
			<button class="btn btn-yellow" style="width:100px;height:38px;margin:30px">保存</button>
		</div>
	</div>
</div>
<script>
	$(function(){
		$(".pjsz .tx_div a").hover(function(){
			$(this).next().show();
		},function(){
			$(this).next().hide()
		});
		$(".pjsz.grey input[type='radio']").attr("disabled", true);
	})
</script>
</body>
</html>