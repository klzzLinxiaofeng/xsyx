<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>教师听评课系统</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="听课总结" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							听课总结列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
							<a href="javascript:void(0)" class="a4" ><i class="fa fa-plus"></i>新增听课</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>科目：</span> <input type="text" />
								</div>
								<div class="select_div">
									<span>讲课人：</span> <select><option>罗志明</option><option>罗金淼</option><option>潘伟良</option></select>
								</div>
								<div class="select_div">
									<span>讲课时间：</span> <input type="text"  onclick="WdatePicker();" />
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>学期</th>
										<th>年级</th>
										<th>班级</th>
										<th>科目</th>
										<th>讲课人</th>
										<th>讲课日期</th>
										<th>记录创建时间</th>
										<th>课程标题</th>
										<th>课程内容</th>
										<th>点评</th>
										<th>经验</th>
										<th>反思与评价</th>
									</tr>
								</thead>
								<tbody >
									<tr>
										<td>2015第二学期</td>
										<td>三年级</td>
										<td>一班</td>
										<td>语文</td>
										<td>罗志明</td>
										<td>06-15</td>
										<td>06-18</td>
										<td>小蝌蚪找妈妈</td>
										<td>蝌蚪找妈妈的故事</td>
										<td>形象生动</td>
										<td>老师可以插个游戏来辅助教学</td>
										<td><a href="javascript:void(0)">小蝌蚪找妈妈反思与评价.doc</a></td>
									</tr>
									<tr>
										<td>2015第二学期</td>
										<td>四年级</td>
										<td>一班</td>
										<td>数学</td>
										<td>潘伟良</td>
										<td>06-18</td>
										<td>06-21</td>
										<td>一元一次方程</td>
										<td>解一元一次方程</td>
										<td>讲解详细</td>
										<td>多布置2道作业题</td>
										<td><a href="javascript:void(0)">一元一次方程反思与评价.doc</a></td>
									</tr>
									<tr>
										<td>2015第二学期</td>
										<td>一年级</td>
										<td>一班</td>
										<td>数学</td>
										<td>周津</td>
										<td>06-18</td>
										<td>06-19</td>
										<td>十以内加法</td>
										<td>解十以内的加法</td>
										<td>讲解详细生动</td>
										<td>玩游戏带动学生思考</td>
										<td><a href="javascript:void(0)">十以内加法反思与评价.doc</a></td>
									</tr>
								</tbody>
							</table>
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
	</div>
</body>
</html>