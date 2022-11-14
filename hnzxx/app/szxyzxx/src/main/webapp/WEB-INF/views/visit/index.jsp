<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
	<script type="text/javascript" defer="defer" src="/res/plugin/My97DatePicker/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>访客记录</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="访客记录" name="title" />
			<jsp:param value="kkkkkk" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							访客记录
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" onclick="$.refreshWin();"
									class="a3"><i class="fa  fa-undo"></i>刷新</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>访客姓名：</span>
									<input type="text" id="visitorName" name="visitorName" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="访客姓名" value="">
								</div>

								<div class="select_div">
									<span>被访人姓名：</span>
									<input type="text" id="visitUserName" name="visitUserName" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="被访人姓名" value="">
								</div>

								<div class="select_div" >
									<span>状态：</span>
									<select name="status" id="status" style="width: 120px;">
										<option value="">--全部--</option>
										<option value="0">待被访人审批</option>
										<option value="1">待管理员审批</option>
										<option value="2">被访人拒绝</option>
										<option value="4">管理员拒绝</option>
										<option value="3">待入校</option>
										<option value="5">已入校</option>
										<option value="6">已失效</option>
									</select>
								</div>

								<div class="select_div">
									<span>来访开始日期：</span>
									<input type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="beginDate" id="beginDate" style="margin-bottom: 0; padding: 6px; width: 100px;" value=""/>
								</div>
								<div class="select_div">
									<span>来访结束日期：</span>
									<input type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="endDate" id="endDate" style="margin-bottom: 0; padding: 6px; width: 100px;" value=""/>
								</div>

								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>序号</th>
										<th>访客姓名</th>
										<th>发起时间</th>
										<th>被访人</th>
										<th>来访时间</th>
										<th>来访目的</th>
										<th>状态</th>
									</tr>
								</thead>
								<tbody id="pay_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="pay_list_content" />
								<jsp:param name="url" value="/visit/list" />
								<jsp:param name="pageSize" value="${page.pageSize }" />
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	function search() {
		var val = {};
		var visitorName = $("#visitorName").val();
		var status=$("#status").val();
		var visitUserName=$("#visitUserName").val();
		var beginDate=$("#beginDate").val();
		var endDate=$("#endDate").val();

		val.status=status;
		val.beginDate=beginDate;
		val.endDate=endDate;
		val.visitorName=visitorName;
		val.visitUserName=visitUserName;

		var id = "pay_list_content";
		var url = "/visit/list";
		myPagination(id, val, url);
	}


</script>
</html>