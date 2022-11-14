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
<title>文印</title>
<style>
.chulizhong {
	color: #FFB129;
}

.yichuli {
	color: #A0D468;
}

.weichuli {
	color: #ED5565;
}
</style>
<script type="text/javascript">
	/*====Select Box====*/
	$(function() {
		
		  /* $(".entry ul li a").click(function() {
			$(".entry ul li").removeClass("on");
			$(this).parent().addClass("on");
			var k = $(this).parent().index();
		}); 
 */
		$(".top_ul li a").click(function() {
			var l = $(this).parent().index();
			$(".top_ul li a").removeClass("on");
			$(this).addClass("on");
		});
		//月份下拉选
		getYear();

		//onchange事件
		dateChangeInit();
	});

	function ss() {
		$.ajaxSetup({
			async : false
		});
		var departmentId = $("#departId").val();
		var val = {
			"searchWord" : $("#ss").val(),
			"departmentId" : departmentId
		};
		$("#dep").val(departmentId);
		var id = "wait";
		var url = "/office/applayIndia/index?sub=list&auditType="
				+ $("#spStatus").val() + "&menu=sp&dm=${param.dm}";
		myPagination(id, val, url);
		if ($("#dep").val() != "") {
			$(".entry_li").removeClass("on");
		}
		if (departmentId != '') {
			$(".li_" + departmentId).addClass("on");
		}
	}

	function getData(type) {
		var val = {};
		var id = "wait";
		var url = "";
		$.ajaxSetup({
			async : false
		});
		if (type === "wait") {
			window.location.reload();
			$("#spStatus").val("wait");
		} else if (type === "undisposed") {
			$("#spStatus").val("undisposed");
			url = "/office/applayIndia/index?sub=list&auditType=undisposed&menu=sp&dm=${param.dm}";
			myPagination(id, val, url);
			$("#tj_list").hide();
			$("#sq_list").show();
		} else if (type == "pending") {
			$("#spStatus").val("pending");
			url = "/office/applayIndia/index?sub=list&auditType=pending&menu=sp&dm=${param.dm}";
			myPagination(id, val, url);
			$("#tj_list").hide();
			$("#sq_list").show();
		} else if (type == "deal") {
			$("#spStatus").val("deal");
			url = "/office/applayIndia/index?sub=list&auditType=deal&menu=sp&dm=${param.dm}";
			myPagination(id, val, url);
			$("#tj_list").hide();
			$("#sq_list").show();
		} else {
			init();
			$("#sq_list").hide();
			$("#tj_list").show();
		}
	}

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
				var opt = "<option selected='selected' value='"+ curenYear + "/" + i + "/1  00:00:00" +
			"-" + curenYear + "/" + i + "/" + daycount +"  23:59:59'>"
						+ "本月（"
						+ curenYear
						+ "/"
						+ i
						+ "/1"
						+ "-"
						+ curenYear
						+ "/" + i + "/" + daycount + "）</option>";
			} else {
				var opt = "<option value='"+ curenYear + "/" + i + "/1  00:00:00" +
			"-" + curenYear + "/" + i + "/" + daycount +"  23:59:59'>"
						+ datas
						+ "月（"
						+ curenYear
						+ "/"
						+ i
						+ "/1"
						+ "-"
						+ curenYear + "/" + i + "/" + daycount + "）</option>";
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

	function checkTime(i){
	    if (i<10){
	        i = "0" + i;
	    }
	    return i;
	}
	
	//触发下拉框事件
	function dateChangeInit() {
		$("#sj").change(
				"on",
				function() {
					var currentValue = $(this).val();
					var url = "${ctp}/office/applayIndia/monthChange?curenMonth="
							+ currentValue
							+ "&auditType=wait&menu=sp&dm=${param.dm}";
					$.post(url, null, function(data) {
						var bodyHtml = "";
						var total = "";
						var daichuli = "";
						var chulizhong = "";
						var yichuli = "";
						var weichuli = "";
						if(data.length==0){
							bodyHtml = bodyHtml + "<tr><td colspan='7'><div align='center'>暂无数据！</div></td></tr>";
						}
						$.each(data, function(index, value) {
							total = value.totalCount;
							daichuli = value.daichuliCount;
							chulizhong = value.chulizhongCount;
							yichuli = value.yichuliCount;
							weichuli = value.weichuliCount;
							index = index + 1;
							bodyHtml = bodyHtml + "<tr>";
							bodyHtml = bodyHtml + "<td>" + index + "</td>";
							bodyHtml = bodyHtml + "<td>" + value.proposerName
									+ "</td>";
							bodyHtml = bodyHtml + "<td>" + value.departmentName
									+ "</td>";
							bodyHtml = bodyHtml + "<td>" + value.mobile
									+ "</td>";
							if(value.remark.length>40){
								bodyHtml = bodyHtml + "<td>" + value.remark.substring(0,40)+"..."
								+ "</td>";
							}else{
								bodyHtml = bodyHtml + "<td>" + value.remark
								+ "</td>";
							}
										
							if (value.indiaStatus ==0 ) {
								bodyHtml = bodyHtml + "<td><span class='blue_1'>待处理</span></td>";
							} else  if (value.indiaStatus ==1 ){
								bodyHtml = bodyHtml + "<td><span class='weichuli'>未处理</span></td>";
							}else  if (value.indiaStatus ==2 ){
								bodyHtml = bodyHtml + "<td><span class='chulizhong'>处理中</span></td>";
							}else  if (value.indiaStatus ==3 ){
								bodyHtml = bodyHtml + "<td><span class='yichuli'>已处理</span></td>";
							}

							if (value.treatDate == null) {
								bodyHtml = bodyHtml + "<td></td>";
							} else {
								var date = new Date(value.treatDate);
								var m = 1;
								m = m + date.getMonth();
								var d = date.getFullYear() + "年" + m + "月"
										+ date.getDay() + "日 "+checkTime(date.getHours())+":"+checkTime(date.getMinutes())+":"+checkTime(date.getSeconds());
								bodyHtml = bodyHtml + "<td>" + d + "</td>";
							}
							bodyHtml = bodyHtml + "</tr>";
						});
						 	$("#total").html("").append(total);
							$("#daichuli").html("").append(daichuli);
							$("#chulizhong").html("").append(chulizhong);
							$("#yichuli").html("").append(yichuli);
							$("#weichuli").html("").append(weichuli);
							if (total == "") {
								$("#total").html("").append("0");
							}
							if (daichuli == "") {
								$("#daichuli").html("").append("0");
							}
							if (chulizhong == "") {
								$("#chulizhong").html("").append("0");
							} 
							if (yichuli == "") {
								$("#yichuli").html("").append("0");
							}
							if (weichuli == "") {
								$("#weichuli").html("").append("0");
							} 
						$("#tbodys").html("").append(bodyHtml);
					}, 'json')
				});
	}

	function init() {
		var currentValue = $("#sj").val();
		var url = "${ctp}/office/applayIndia/monthChange?curenMonth=" + currentValue
				+ "&auditType=wait&menu=sp&dm=${param.dm}";
		$.post(url, null, function(data) {
			var bodyHtml = "";
			var total = "";
			var daichuli = "";
			var chulizhong = "";
			var yichuli = "";
			var weichuli = "";
			if(data.length==0){
				bodyHtml = bodyHtml + "<tr><td colspan='7'><div align='center'>暂无数据！</div></td></tr>";
			}
			$.each(data, function(index, value) {
				total = value.totalCount;
				daichuli = value.daichuliCount;
				chulizhong = value.chulizhongCount;
				yichuli = value.yichuliCount;
				weichuli = value.weichuliCount;
				index = index + 1;
				bodyHtml = bodyHtml + "<tr>";
				bodyHtml = bodyHtml + "<td>" + index + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.proposerName
						+ "</td>";
				bodyHtml = bodyHtml + "<td>" + value.departmentName
						+ "</td>";
				bodyHtml = bodyHtml + "<td>" + value.mobile
						+ "</td>";
				
				if(value.remark.length>40){
					bodyHtml = bodyHtml + "<td title='"+value.remark+"'>" + value.remark.substring(0,40)+"<strong>. . .</strong>"
					+ "</td>";
				}else{
					bodyHtml = bodyHtml + "<td>" + value.remark
					+ "</td>";
				} 
						
				if (value.indiaStatus ==0 ) {
					bodyHtml = bodyHtml + "<td><span class='blue_1'>待处理</span></td>";
				} else  if (value.indiaStatus ==1 ){
					bodyHtml = bodyHtml + "<td><span class='weichuli'>未处理</span></td>";
				}else  if (value.indiaStatus ==2 ){
					bodyHtml = bodyHtml + "<td><span class='chulizhong'>处理中</span></td>";
				}else  if (value.indiaStatus ==3 ){
					bodyHtml = bodyHtml + "<td><span class='yichuli'>已处理</span></td>";
				}

				if (value.treatDate == null) {
					bodyHtml = bodyHtml + "<td></td>";
				} else {
					var date = new Date(value.treatDate);
					var m = 1;
					m = m + date.getMonth();
					var d = date.getFullYear() + "年" + m + "月"
							+ date.getDate() + "日"+checkTime(date.getHours())+":"+checkTime(date.getMinutes())+":"+checkTime(date.getSeconds());
					bodyHtml = bodyHtml + "<td>" + d + "</td>";
				}
				bodyHtml = bodyHtml + "</tr>";
			});
			 $("#total").html("").append(total);
			$("#daichuli").html("").append(daichuli);
			$("#chulizhong").html("").append(chulizhong);
			$("#yichuli").html("").append(yichuli);
			$("#weichuli").html("").append(weichuli);
			if (total == "") {
				$("#total").html("").append("0");
			}
			if (daichuli == "") {
				$("#daichuli").html("").append("0");
			}
			if (chulizhong == "") {
				$("#chulizhong").html("").append("0");
			} 
			if (yichuli == "") {
				$("#yichuli").html("").append("0");
			}
			if (weichuli == "") {
				$("#weichuli").html("").append("0");
			} 
			$("#tbodys").html("").append(bodyHtml);
		}, 'json')
	}

	// 	需要查询处理 、未处理、处理中和 已处理 下的部门的人  可以在路径中加上当前点击的部门的相关参数就可以了
	function depentmentSeach(departmentId, obj) {
		if (departmentId === 'all') {
			departmentId = '';
		}
		var auditType = $("#spStatus").val();
		var val = {
			"searchWord" : $("#ss").val()
		}
		$("#dep").val(departmentId);
		var id = "wait";
		var url = "/office/applayIndia/index?sub=list&auditType=" + auditType
				+ "&departmentId=" + departmentId + "&menu=sp&dm=${param.dm}";
		$.ajaxSetup({
			async : false
		});
		myPagination(id, val, url);
		$("#departId").val(departmentId);
		var dep = $("#departId").val();
// 		$(".entry_li").removeClass("on");
		var i=$(obj).parent().index()/3;
		$(".entry_li").removeClass("on")
		$(".entry_li").eq(i).addClass("on");
		
		
	}
</script>
</head>
<body>
		<input type="hidden" id="dep" />
		<input type="hidden" value="wait" id="spStatus" />
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="文印" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
							<li><a href="javascript:void(0)" class="on"
								onclick="getData('wait');">待处理</a></li>
							<li><a href="javascript:void(0)"
								onclick="getData('undisposed');">已驳回</a></li>
<%--							<li><a href="javascript:void(0)"--%>
<%--								onclick="getData('pending');">处理中</a></li>--%>
							<li><a href="javascript:void(0)" onclick="getData('deal');">已同意</a></li>
<%--							<li><a href="javascript:void(0)" onclick="getData('count');">用章统计</a></li>--%>
						</ul>
					</div>
					<div class="wy_div">
						<div class="sq_list" id="sq_list">
							<div class="search_1">
								<input type="text" placeholder="标题/发布人" id="ss"> <a
									class="sea_s" onclick="ss();"><i class="fa fa-search"></i></a>
							</div>
							<div class="f_wy">
								<div class="clsq" id="wait">
									<jsp:include page="./tongjilist.jsp" />
								</div>
								<jsp:include page="/views/embedded/jqpagination.jsp"
									flush="true">
									<jsp:param name="id" value="wait" />
									<jsp:param name="url"
										value="/office/applayIndia/index?sub=list&auditType=${auditType}&departmentId=${departmentId}&menu=sp&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
								<div class="clear"></div>
							</div>
						</div>
						<div class="sq_list" style="display: none" id="tj_list">
							<div class="tj_1">
								<div class="time">
									<span>时间范围：</span> <select class="chzn-select" id="sj"></select>
								</div>
								<%-- <div class="people">
									<p class="p1">车辆负责人</p>
									<img
										src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
									<div class="name">
										<p>
											<b>刘艳青</b>
										</p>
										<p>后勤部</p>
									</div>
								</div> --%>
							</div>
							<div class="tj_2">
								<p>
									<span>文印申请数：</span><b class="b1" id="total">0</b>
								</p>
								<p>
									<span>待处理：</span><b class="b2" id="daichuli">0</b>
								</p>
								<p>
									<span>处理中：</span><b class="b4" id="chulizhong">0</b>
								</p>
								<p>
									<span>已处理：</span><b class="b3" id="yichuli">0</b>
								</p>
								<p>
									<span>未处理：</span><b class="b5" id="weichuli">0</b>
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
											<th>申请事由</th>
											<th>处理状态</th>
											<th>处理时间</th>
										</tr>
									</thead>
									<tbody id="tbodys"></tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>