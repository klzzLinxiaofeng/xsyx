<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>试题库</title>
<style>
	.page{text-align:right}
	.page a{display:inline-block;block;width:30px;height:30px;border-radius:5px;line-height:30px;text-align:center;background-color:#eee;color:#333;}
	.page a.on{background-color:#138BDA;color:#fff;}
</style>
<script>
	$(function(){
		$(".page .left").click(function(){
			var i=$(".page .on").attr("data-id");
			if(i>=1){
				$(".page a").removeClass("on");
				$(".page a").eq(i).addClass("on");
				$(".table").hide();
				$(".table").eq(i-1).show();
			}
		});
		$(".page .right").click(function(){
			var i=$(".page .on").attr("data-id");
			if(i<=1){
				var k=parseInt(i)+1
				$(".page a").removeClass("on");
				$(".page .shu").eq(k).addClass("on");
				$(".table").hide();
				$(".table").eq(k).show();
			}
		});
		$(".page .one").click(function(){
			$(".page a").removeClass("on");
			$(this).addClass("on");
			$(".table").hide();
			$(".table").eq(0).show();
		});
		$(".page .two").click(function(){
			$(".page a").removeClass("on");
			$(this).addClass("on");
			$(".table").hide();
			$(".table").eq(1).show();
		});
		$(".page .three").click(function(){
			$(".page a").removeClass("on");
			$(this).addClass("on");
			$(".table").hide();
			$(".table").eq(2).show();
		});
	})
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="试题库" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							试题库列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								
								<div class="select_div">
									<span>科目：</span> <select><option>不限</option><option>语文</option><option>数学</option><option>英语</option></select>
								</div>
								<div class="select_div">
									<span>年级：</span> <select><option>不限</option><option>一年级</option><option>二年级</option><option>三年级</option></select>
								</div>
								<div class="select_div">
									<span>书名：</span> <select><option>不限</option></select>
								</div>
								<div class="select_div">
									<span>章：</span> <select><option>不限</option></select>
								</div>
								<div class="select_div">
									<span>节：</span> <select><option>不限</option></select>
								</div>
								<div class="select_div">
									<span>标题：</span> <input type="text">
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped">
								<thead>
									<tr>
										<th>标题</th>
										<th>创建时间</th>
										<th>年级</th>
										<th>科目</th>
										<th>版本</th>
										<th>册</th>
										<th>书名</th>
										<th>章</th>
										<th>节</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146ede542970bad&amp;pos=1">《狼》阅读理解2</a>
										</td>
										<td align="center">2014-07-01 01:49:23</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第六单元</td>
										<td align="center">30 狼</td>
										<td align="center">
										<!-- <a class="ress2 editfall" target="_blank" href="/common/paper/paperAction_createPaper.action?paperId=2c91829146d73f300146ede542970bad&amp;micoCourse=true">编辑</a>
											<a href="/common/paper/paperAction_deletePaper.action?paperId=2c91829146d73f300146ede542970bad" onclick="if (confirm('确定删除此试题吗?')) { return true; } else { return false; }"
											class="ress2 editfall">删除</a> -->
											<button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button>
											</td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146ede239330ba2&amp;pos=1">《狼》阅读理解1</a>
										</td>
										<td align="center">2014-07-01 01:46:04</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第六单元</td>
										<td align="center">30 狼</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146edd8e8220b8b&amp;pos=1">《松鼠》阅读理解</a>
										</td>
										<td align="center">2014-07-01 01:35:53</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第六单元</td>
										<td align="center">29 马</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146edd513e90b7a&amp;pos=1">《马》阅读理解</a>
										</td>
										<td align="center">2014-07-01 01:31:42</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第六单元</td>
										<td align="center">29 马</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146edb99e060b44&amp;pos=1">《华南虎》同步训练</a>
										</td>
										<td align="center">2014-07-01 01:01:43</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第六单元</td>
										<td align="center">28 华南虎</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146edaefe5b0b39&amp;pos=1">《斑羚飞渡》阅读理解</a>
										</td>
										<td align="center">2014-07-01 00:50:07</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第六单元</td>
										<td align="center">27 斑羚飞渡</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146ebd82eec0abb&amp;pos=1">《女娲补天》阅读理解</a>
										</td>
										<td align="center">2014-06-30 16:15:52</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第五单元</td>
										<td align="center">25 短文两篇</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146ebd3ff650aaf&amp;pos=1">《精卫填海》阅读理解</a>
										</td>
										<td align="center">2014-06-30 16:11:17</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第五单元</td>
										<td align="center">25 短文两篇</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146ebbae4b80a6d&amp;pos=1">《自强不息，迈出“壮行中华”第一步》阅读理解</a>
										</td>
										<td align="center">2014-06-30 15:43:52</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第五单元</td>
										<td align="center">23 登上地球之巅</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829146d73f300146ebb75a140a5e&amp;pos=1">《登上地球之巅》阅读理解2</a>
										</td>
										<td align="center">2014-06-30 15:40:00</td>
										<td align="center">七年级</td>
										<td align="center">语文</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">语文</td>
										<td align="center">第五单元</td>
										<td align="center">23 登上地球之巅</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									</tbody></table>
									<table class="responsive table table-striped" style="display:none">
								<thead>
									<tr>
										<th>标题</th>
										<th>创建时间</th>
										<th>年级</th>
										<th>科目</th>
										<th>版本</th>
										<th>册</th>
										<th>书名</th>
										<th>章</th>
										<th>节</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147cb7a27be0945&amp;pos=1">《相似三角形》选择题</a>
										</td>
										<td align="center">2014-08-13 02:28:06</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">数学</td>
										<td align="center">第二十七章 相似</td>
										<td align="center">27.2 相似三角形</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147cb6ff6690938&amp;pos=1">《相似三角形》选择题</a>
										</td>
										<td align="center">2014-08-13 02:16:58</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">下册</td>
										<td align="center">数学</td>
										<td align="center">第二十七章 相似</td>
										<td align="center">27.2 相似三角形</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147c98757ff07dc&amp;pos=1">《课题学习
												键盘上字母的排列规律》选择题</a></td>
										<td align="center">2014-08-12 17:23:16</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">数学</td>
										<td align="center">第二十五章 概率初步</td>
										<td align="center">25.4 课题学习 键盘上字母的排列规律</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147c96b981307b5&amp;pos=1">《用频率估计概率》选择题</a>
										</td>
										<td align="center">2014-08-12 16:52:57</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">数学</td>
										<td align="center">第二十五章 概率初步</td>
										<td align="center">25.3 用频率估计概率</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147c94273fa0772&amp;pos=1">《用列举法求概率》选择题</a>
										</td>
										<td align="center">2014-08-12 16:08:01</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">数学</td>
										<td align="center">第二十五章 概率初步</td>
										<td align="center">25.2 用列举法求概率</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147c94105e0075d&amp;pos=1">《随机事件与概率》选择题</a>
										</td>
										<td align="center">2014-08-12 16:06:27</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">数学</td>
										<td align="center">第二十五章 概率初步</td>
										<td align="center">25.1 随机事件与概率</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147c92cf8950686&amp;pos=1">《弧长和扇形面积》选择题</a>
										</td>
										<td align="center">2014-08-12 15:44:33</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">数学</td>
										<td align="center">第二十四章 圆</td>
										<td align="center">24.4 弧长和扇形面积</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147c92ae4a30678&amp;pos=1">《正多边形和圆》选择题</a>
										</td>
										<td align="center">2014-08-12 15:42:17</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">数学</td>
										<td align="center">第二十四章 圆</td>
										<td align="center">24.3 正多边形和圆</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147c8dfe8180514&amp;pos=1">《点、直线、圆和圆有关的位置关系》选择题</a>
										</td>
										<td align="center">2014-08-12 14:20:22</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">数学</td>
										<td align="center">第二十四章 圆</td>
										<td align="center">24.2 点、直线、圆和圆的位置关系</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c91829147c49f8a0147c8a9997b0498&amp;pos=1">《圆》选择题</a>
										</td>
										<td align="center">2014-08-12 13:21:03</td>
										<td align="center">九年级</td>
										<td align="center">数学</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">数学</td>
										<td align="center">第二十四章 圆</td>
										<td align="center">24.1 圆</td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									</tbody></table>
									<table class="responsive table table-striped" style="display:none;">
								<thead>
									<tr>
										<th>标题</th>
										<th>创建时间</th>
										<th>年级</th>
										<th>科目</th>
										<th>版本</th>
										<th>册</th>
										<th>书名</th>
										<th>章</th>
										<th>节</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e80146186091551ccf&amp;pos=1">《Unit
												6 In a Nature Park》选择题</a></td>
										<td align="center">2014-05-20 14:45:20</td>
										<td align="center">五年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 6 In a Nature Park</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e80146185c3e861ca9&amp;pos=1">《Unit5
												My New Room》选择题</a></td>
										<td align="center">2014-05-20 14:40:36</td>
										<td align="center">五年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 5 My New Room</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e8014618565d011c72&amp;pos=1">《Unit4
												what can you do 》选择题</a></td>
										<td align="center">2014-05-20 14:34:11</td>
										<td align="center">五年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 4 What Can You Do?</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e8014618517afe1c50&amp;pos=1">《Unit
												3 What's Your Favourite Food》选择题</a></td>
										<td align="center">2014-05-20 14:28:51</td>
										<td align="center">五年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 3 What's Your Favourite Food?</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e80146184cf4ff1bdc&amp;pos=1">《Unit
												2 My Days of the Week》选择题</a></td>
										<td align="center">2014-05-20 14:23:55</td>
										<td align="center">五年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 2 My Days of the Week</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e8014618499e1b1bbc&amp;pos=1">《Unit
												1 My New Teachers》选择题</a></td>
										<td align="center">2014-05-20 14:20:16</td>
										<td align="center">五年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 1 My New Teachers</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e801461606c1611970&amp;pos=1">《Unit
												1 My classroom》选择题</a></td>
										<td align="center">2014-05-20 03:47:59</td>
										<td align="center">四年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 1 My classroom</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e80146160bac0b19a3&amp;pos=1">《Unit
												2 My schoolbag》选择题</a></td>
										<td align="center">2014-05-20 03:53:22</td>
										<td align="center">四年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 2 My schoolbag</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e801461613fca719fd&amp;pos=1">《Unit
												3 My friends》选择题</a></td>
										<td align="center">2014-05-20 04:02:27</td>
										<td align="center">四年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 3 My friends</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
									<tr>
										<td align="center"><a class="ress2" target="_blank"
											href="http://jiaoxueyun.com/common/paper/paperAction_studyPaper.action?paperId=2c9182914608a5e8014616192e5e1a14&amp;pos=1">《Unit
												4 My home》选择题</a></td>
										<td align="center">2014-05-20 04:08:07</td>
										<td align="center">四年级</td>
										<td align="center">英语</td>
										<td align="center">人民教育出版社</td>
										<td align="center">上册</td>
										<td align="center">英语</td>
										<td align="center">Unit 4 My home</td>
										<td align="center"></td>
										<td align="center"><button class="btn btn-blue">编辑</button>
											<button class="btn btn-red">删除</button></td>
									</tr>
								</tbody>
							</table>
							<%-- <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="studentAid_list_content" />
								<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include> --%>
							<div class="page">
								<a class="left" href="javascript:void(0)"> &lt;</a>
								<a class="on one shu" href="javascript:void(0)" data-id="0"> 1 </a>
								<a class="two shu" href="javascript:void(0)" data-id="1"> 2 </a>
								<a class="three shu" href="javascript:void(0)" data-id="2"> 3 </a>
								<a class="right" href="javascript:void(0)"> &gt;</a>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>