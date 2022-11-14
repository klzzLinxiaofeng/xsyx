<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
	<script type="text/javascript" defer="defer" src="/res/plugin/My97DatePicker/WdatePicker.js"></script>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>订单列表</title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="食堂充值订单" name="title" />
			<jsp:param value="kkkkkk" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							食堂充值订单
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
									<span>姓名：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="姓名" value="">
								</div>

								<div class="select_div" >
									<span>支付状态：</span>
									<select id="payStatus" style="width: 120px;">
										<option value="">--全部--</option>
										<option value="1">成功</option>
										<option value="2">失败</option>
										<option value="0">未支付</option>
									</select>
								</div>

								<div class="select_div"  >
									<span>是否发送到食堂：</span>
									<select id="isSendSt" style="width: 120px;">
										<option value="">--全部--</option>
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
								</div>

								<div class="select_div">
									<span>支付开始日期：</span>
									<input type="text" readonly  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="startDate" style="margin-bottom: 0; padding: 6px; width: 100px;" value=""/>
								</div>
								<div class="select_div">
									<span>支付结束日期：</span>
									<input type="text" readonly  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="endDate" style="margin-bottom: 0; padding: 6px; width: 100px;" value=""/>
								</div>

								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<button type="button" class="btn btn-info" onclick="exports()">导出</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
										<th>序号</th>
										<th>姓名</th>
										<th>食堂卡号</th>
										<th>支付状态</th>
										<th>支付金额</th>
										<th>是否发送到食堂</th>
										<th>支付方式</th>
										<th>订单号</th>
										<th>下单时间</th>
										<th>支付时间</th>
									</tr>
								</thead>
								<tbody id="pay_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="pay_list_content" />
								<jsp:param name="url" value="/pay/st/list?sub=list" />
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
		var name = $("#name").val();
		var payStatus=$("#payStatus").val();
		var isSendSt=$("#isSendSt").val();
		var startDate=$("#startDate").val();
		var endDate=$("#endDate").val();

		val.empName = name;
		val.payStatus=payStatus;
		val.sendStatus=isSendSt;
		val.startDate=startDate;
		val.endDate=endDate;

		var id = "pay_list_content";
		var url = "/pay/st/list?sub=list";
		myPagination(id, val, url);
	}

	function exports(){
		var ind=layer.confirm("确定导出当前数据吗？",function(){
			var name = $("#name").val();
			var payStatus=$("#payStatus").val();
			var isSendSt=$("#isSendSt").val();
			var startDate=$("#startDate").val();
			var endDate=$("#endDate").val();
			layer.close(ind)
			location.href="/pay/st/export?empName="+name+"&payStatus="+payStatus+"&sendStatus="+isSendSt+"&startDate="+startDate+"&endDate"+endDate;
		})
	}

</script>
</html>