<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>班级常规管理</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="宿舍卫生" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							宿舍卫生列表
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
									<span>时间：</span> <input type="text" onclick="WdatePicker();">
								</div>
								<div class="select_div">
									<span>所属大楼：</span> <select><option>海安</option><option>海景</option></select>
								</div>
								<div class="select_div">
									<span>楼层：</span> <select><option>1</option><option>2</option></select>
								</div>
								<div class="select_div">
									<span>房间号：</span> <select><option>101</option><option>102</option><option>103</option><option>104</option></select>
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr>
										<th>宿舍</th>
										<th>现有人数</th>
										<th>打扫人员</th>
										<th>宿舍评分</th>
										<th>说明</th>
									</tr>
								</thead>
								<tbody id="studentAid_list_content">
									<tr>
										<td>海安A105</td>
										<td>6</td>
										<td>罗致命、林萧洪、周晶</td>
										<td>优</td>
										<td>打扫很干净</td>
									</tr>
									<tr>
										<td>海安A106</td>
										<td>6</td>
										<td>何帆、萧洪、周晶</td>
										<td>良</td>
										<td>有纸屑</td>
									</tr>
									<tr>
										<td>海安A107</td>
										<td>6</td>
										<td>潘伟良、谭样、冠宏</td>
										<td>差</td>
										<td>没打扫</td>
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