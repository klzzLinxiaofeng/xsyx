<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css"
	rel="stylesheet">
<title>车辆</title>
<script type="text/javascript">
	$(function() {
		$(".sq_list .clsq ul li .caozuo .delete").click(function() {
			$(this).parent().parent().remove()
		})
	})
</script>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="车辆" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
							<li><a href="javascript:void(0)" class="on"
								onclick="getData('manager');">车辆管理</a></li>
<!-- 							<li><a href="javascript:void(0)" onclick="getData('count');">用车统计</a></li> -->
						</ul>
					</div>
					<div class="sq_list" id="sq_list">
						<div class="search_1">
							<input type="text" placeholder="车名/车牌号" id="ss"> <a
								class="sea_s"><i class="fa fa-search"></i></a>
						</div>
						<div class="entry">
							<ul>
								<li><a href="javascript:void(0)">全部车辆（<span id="stotal"></span>）</a></li>
							</ul>
							<button class="btn btn-info" onclick="loadCreatePage();">新建车辆</button>
						</div>

						<div class="clgl" id="vehicleManagement_list_content" style="margin-bottom:15px;">
							<jsp:include page="./list.jsp" />
						</div>
						<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
							<jsp:param name="id" value="vehicleManagement_list_content" />
							<jsp:param name="url"
								value="/oa/vehicleManagement/index?sub=list&dm=${param.dm}" />
							<jsp:param name="pageSize" value="${page.pageSize}" />
						</jsp:include>
						<div class="clear"></div>
					</div>

					<!-- 用车统计 -->
					<div class="sq_list" id="tj_list" style="display: none;">
						<div class="tj_1">
							<div class="time">
								<span>时间范围：</span> <select id="sj" class="chzn-select">
								</select>
							</div>
							<div class="people">
								<p class="p1">车辆负责人</p>
								<img
									src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
								<div class="name">
									<p>
										<b>刘艳青</b>
									</p>
									<p>后勤部</p>
								</div>
							</div>
						</div>
						<div class="tj_2">
							<p>
								<span>申请次数：</span><b class="b1" id="tatol">0</b>
							</p>
							<p>
								<span>待处理：</span><b class="b2" id="waitAudit">0</b>
							</p>
							<p>
								<span>已处理：</span><b class="b3" id="alerty">0</b>
							</p>
						</div>
						<div class="tj_3">
							<p class="top">申请明细</p>
							<table class="responsive table table-striped">
								<thead>
									<tr>
										<th>序号</th>
										<th>申请人</th>
										<th>部门</th>
										<th>联系电话</th>
										<th>申请车辆</th>
										<th>申请事由</th>
										<th>处理状态</th>
										<th>处理时间</th>
									</tr>
								</thead>
								<tbody id="tbodys">
								</tbody>
							</table>
						</div>
					</div>
					<!-- 					统计页面结束 -->

				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function search() {
		var val = {};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
		var id = "vehicleManagement_list_content";
		var url = "/oa/vehicleManagement/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/oa/vehicleManagement/creator',
				'700', '550');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/oa/vehicleManagement/editor?id='
				+ id, '700', '550');
	}

	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/oa/vehicleManagement/viewer?id='
				+ id, '700', '400');
	}

	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	function changeStatus(cardStatus, id) {
		$.confirm("确定执行此次操作？", function() {
			executeChange(cardStatus, id);
		});
	}

	$(function() {
		ss2();
		getCardNumber();
// 		getYear();
// 		dateChangeInit();
// 		init();
		$(".top_ul li a").click(function(){
			$(".top_ul li a").removeClass("on");
			$(this).addClass("on")
		});
			
	})
	
	//动态获取车的数量
	function getCardNumber(){
		var number = $("#tatol").val();
		$("#stotal").text(number);
	}

	//执行键盘空格键事件  （文本框中）
	function ss2() {
		$("#ss").keyup(
			function(e) {
					var val = {
						"ssword" : $("#ss").val()
					};
					var id = "vehicleManagement_list_content";
					var url = "/oa/vehicleManagement/index?sub=list&dm=${param.dm}";
					$.ajaxSetup({
						async: false
					});
					myPagination(id, val, url);
					getCardNumber();
			});
		
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/oa/vehicleManagement/" + id, {
			"_method" : "delete"
		}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					window.location.reload();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}

	// 	执行修改状态
	function executeChange(cardStatus, id) {
		$.post("${ctp}/oa/vehicleManagement/" + id, {
			"_method" : "put",
			"serviceCondition" : cardStatus
		}, function(data, status) {
			if ("success" === status) {
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					$.success("修改成功");
					window.location.reload();
				} else {
					$.error("修改失败", 1);
				}
			}
		});
	}

	function getData(type) {
		var val = {};
		var id = "vehicleManagement_list_content";
		var url = "";
		if (type === "manager") {
			url = "/oa/vehicleManagement/index?sub=list&dm=${param.dm}";
			myPagination(id, val, url);
			$("#tj_list").hide();
			$("#sq_list").show();
		} else {
			init();
			$("#sq_list").hide();
			$("#tj_list").show();
		}
	}

	//用车统计
	//统计的js月份分割
	function getYear() {
		var curenData = new Date();
		var curenYear = curenData.getFullYear();
		var dateSelect = $("#sj");
		for (var i = 1; i <= 12; i++) {
			var day = new Date(curenYear, i, 0);
			var daycount = day.getDate();
			var datas = convertData(i);
			var n = curenData.getMonth() + 1;
			if (n == i) {
				var opt = "<option selected='selected' value='"+ curenYear + "/" + i + "/1" + "-" + curenYear
				+ "/" + i + "/" + daycount +"'>"
						+ "本月（" + curenYear + "/" + i + "/1" + "-" + curenYear
						+ "/" + i + "/" + daycount + "）</option>";
			} else {
				var opt = "<option value='"+ curenYear + "/" + i + "/1" + "-" + curenYear
				+ "/" + i + "/" + daycount +"'>" + datas + "月（" + curenYear
						+ "/" + i + "/1" + "-" + curenYear + "/" + i + "/"
						+ daycount + "）</option>";
			}
			dateSelect.append(opt);
		}
		$(".chzn-select").chosen();
	}

	function convertData(num) {
		var cdata = [ "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一",
				"十二" ];
		return cdata[num - 1];
	}

	//触发下拉框事件
	function dateChangeInit() {
		$("#sj").change(
				"on",
				function() {
					var currentValue = $(this).val();
					var url = "${ctp}/oa/applycard/monthChange?curenMonth="
							+ currentValue
							+ "&auditType=wait&menu=sp&dm=${param.dm}";
					$.post(url, null, function(data) {
						var bodyHtml = "";
						var tatol = "";
						var wait = "";
						var alerty = "";
						$.each(data, function(index, value) {
							tatol = value.tatolAudit;
							wait = value.waitAudit;
							alerty = value.alertyAudit;
							index = index + 1;
							bodyHtml = bodyHtml + "<tr>";
							bodyHtml = bodyHtml + "<td>" + index + "</td>";
							bodyHtml = bodyHtml + "<td>" + value.proposerName
									+ "</td>";
							bodyHtml = bodyHtml + "<td>" + value.depetmrnt
									+ "</td>";
							bodyHtml = bodyHtml + "<td>" + value.phone
									+ "</td>";
							bodyHtml = bodyHtml + "<td>" + value.cardName + "【"
									+ value.plateNumber + "】" + "</td>";
							bodyHtml = bodyHtml + "<td>" + value.title
									+ "</td>";
							if (value.auditStatus == 1) {
								bodyHtml = bodyHtml + "<td>已处理</td>";
							} else {
								bodyHtml = bodyHtml + "<td>待处理</td>";
							}

							if (value.releaseDate == null) {
								bodyHtml = bodyHtml + "<td></td>";
							} else {
								var date = new Date(value.releaseDate);
								var m = 1;
								m = m + date.getMonth();
								var d = date.getFullYear() + "年" + m + "月"
										+ date.getDay() + "日";
								bodyHtml = bodyHtml + "<td>" + d + "</td>";
							}
							bodyHtml = bodyHtml + "</tr>";
						});
						$("#tatol").html("").append(tatol);
						$("#waitAudit").html("").append(wait);
						$("#alerty").html("").append(alerty);
						if (tatol == "") {
							$("#tatol").html("").append("0");
						}
						if (wait == "") {
							$("#waitAudit").html("").append("0");
						}
						if (alerty == "") {
							$("#alerty").html("").append("0");
						}
						$("#tbodys").html("").append(bodyHtml);
					}, 'json')
				});
	}

	function init() {
		var currentValue = $("#sj").val();
		var url = "${ctp}/oa/applycard/monthChange?curenMonth=" + currentValue
				+ "&auditType=wait&menu=sp&dm=${param.dm}";
		$.post(url, null, function(data) {
			var bodyHtml = "";
			var tatol = "";
			var wait = "";
			var alerty = "";
			$.each(data, function(index, value) {
				tatol = value.tatolAudit;
				wait = value.waitAudit;
				alerty = value.alertyAudit;
				index = index + 1;
				bodyHtml = bodyHtml + "<tr>";
				bodyHtml = bodyHtml + "<td>" + index + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.proposerName + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.depetmrnt + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.phone + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.cardName + "【"
						+ value.plateNumber + "】" + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.title + "</td>";
				if (value.auditStatus == 1) {
					bodyHtml = bodyHtml + "<td>已处理</td>";
				} else {
					bodyHtml = bodyHtml + "<td>待处理</td>";
				}

				if (value.releaseDate == null) {
					bodyHtml = bodyHtml + "<td></td>";
				} else {
					var date = new Date(value.releaseDate);
					var m = 1;
					m = m + date.getMonth();
					var d = date.getFullYear() + "年" + m + "月" + date.getDay()
							+ "日";
					bodyHtml = bodyHtml + "<td>" + d + "</td>";
				}
				bodyHtml = bodyHtml + "</tr>";
			});
			$("#tatol").html("").append(tatol);
			$("#waitAudit").html("").append(wait);
			$("#alerty").html("").append(alerty);
			if (tatol == "") {
				$("#tatol").html("").append("0");
			}
			if (wait == "") {
				$("#waitAudit").html("").append("0");
			}
			if (alerty == "") {
				$("#alerty").html("").append("0");
			}
			$("#tbodys").html("").append(bodyHtml);
		}, 'json')
	}
</script>
</html>