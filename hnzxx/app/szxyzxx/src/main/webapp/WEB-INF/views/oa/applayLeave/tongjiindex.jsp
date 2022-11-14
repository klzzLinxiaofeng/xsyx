<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
	<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
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
		addData();
		
		$(".entry #allMenuId li a").click(function(){
			$(".entry ul li").removeClass("on");
			$(this).parent().addClass("on");
		});
		
		$('body').on('click','.shenhe .s_four .cancel,.shenhe .close_div',function(){
			$(this).parent().parent().hide();
			$(".zhezhao").hide();
		});
		
		$(".top_ul li a").click(function() {
			var l = $(this).parent().index();
			$(".top_ul li a").removeClass("on");
			$(this).addClass("on");
		});
		
		
		$("body").on('click','.pz button',function(){
			$(".pz button").removeClass("btn-warning");
			$(this).addClass("btn-warning");
		})

		//搜索
// 		ss();
		
		//月份下拉选
		//getYear();

		searchData();
	});

	function shenhe(id){
		$("#zhezhao"+id).show();
		$("#"+id).show();
		var h = document.documentElement.clientHeight;
		var w= document.documentElement.clientWidth;
		var h1 = (document.documentElement.clientHeight-455)/2;
		var w1= (document.documentElement.clientWidth-900)/2;
		$(".zhezhao").css({"width":w,"height":h});
		$(".shenhe").css({"left":w1,"top":h1});
	}
	
	
	function pass(id){
		$("#status"+id).val("0");
	}
	
	function noPass(id){
		$("#status"+id).val("1");
	}
	
	
	
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
		var url = "/oa/applayleave/index?sub=list&auditType="
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
		} else if (type == "deal") {
			$("#spStatus").val("deal");
			url = "/oa/applayleave/index?sub=list&auditType=deal&menu=sp&dm=${param.dm}";
			myPagination(id, val, url);
			$("#tj_list").hide();
			$("#sq_list").show();
		} else {
			searchData();
			$("#sq_list").hide();
			$("#tj_list").show();
		}
	}
	
// 	提交审批
	function submit(id) {
	var loader = new loadLayer();
	var approveState = $("#status"+id).val(); 
	if(approveState==""){
		$.alert("请选择是否通过！");
		return;
	}
	
	  var approveRemark = $("#approveRemark").val();
	  
	  if(approveRemark.length>120){
		  $.alert("最多120个中文字符");
		  return;
	  }
	  
	  	loader.show();
		$.post("${ctp}/oa/applayleave/" + id, {"_method" : "put","approveState" : approveState,"auditStatus":"1","approveRemark":approveRemark}, function(data, status) {
			if ("success" === status) {
			data = eval("(" + data + ")");
				if ("success" === data.info) {
					$.success("审批成功");
					window.location.reload();
				} else {
					$.error("审批失败", 1);
				}
				loader.close();
			}
		});
	}
	


	function convertData(num) {
		var cdata = [ "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一",
				"十二" ];
		return cdata[num - 1];
	}


	function searchData() {
		var currentValue = $("#date").val();
		var url = "${ctp}/oa/applayleave/monthChange?startTime=" + currentValue
				+ " 00:00:00&endTime="+currentValue+" 23:59:59&auditType=wait&menu=sp&dm=${param.dm}";
		$.post(url, null, function(data) {
			var bodyHtml = "";
			var renshu = "";
			var tianshu = "";
			if(data.length==0){
				bodyHtml = bodyHtml + "<tr><td colspan='6'><div align='center'>暂无数据！</div></td></tr>";
			}
			$.each(data, function(index, value) {
				renshu = value.renshu;
				tianshu = value.allDaySum;
				index = index + 1;
				bodyHtml = bodyHtml + "<tr>";
				bodyHtml = bodyHtml + "<td>" + index + "</td>";
				bodyHtml = bodyHtml + "<td>" + value.propserName
						+ "</td>";
				bodyHtml = bodyHtml + "<td>" + value.propserDepartmentName
						+ "</td>";
				bodyHtml = bodyHtml + "<td>" + value.mobile
						+ "</td>";
				bodyHtml = bodyHtml + "<td>" + value.ciSum
						+ "</td>";
						bodyHtml = bodyHtml + "<td>" + value.oneSum
						+ "</td>";
				bodyHtml = bodyHtml + "</tr>";
			});
			$("#renshu").html("").append(renshu);
			$("#tianshu").html("").append(tianshu);
			if (renshu == "") {
				$("#renshu").html("").append("0");
			}
			if (tianshu == "") {
				$("#tianshu").html("").append("0");
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
		var url = "/oa/applayleave/index?sub=list&auditType=" + auditType
				+ "&departmentId=" + departmentId + "&menu=sp&dm=${param.dm}";
		$.ajaxSetup({
			async : false
		});
		myPagination(id, val, url);
		$("#departId").val(departmentId);
		var dep = $("#departId").val();
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
			<jsp:param value="请假" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
							<li><a href="javascript:void(0)" class="on"
								onclick="getData('wait');">待审批</a></li>
							<li><a href="javascript:void(0)" onclick="getData('deal');">已审批</a></li>
							<li><a href="javascript:void(0)" onclick="getData('count');">请假统计</a></li>
						</ul>
					</div>
					<div class="wy_div">
						<div class="sq_list" id="sq_list">
							<div class="search_1">
								<input type="text" placeholder="标题/发布人/摘要" id="ss"> <a
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
										value="/oa/applayleave/index?sub=list&auditType=${auditType}&departmentId=${departmentId}&menu=sp&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
								<div class="clear"></div>
							</div>
						</div>
						<div class="sq_list" style="display: none" id="tj_list">
							<div class="tj_1">
								<div class="time">
									<span>请假日期：</span> <input type="text" id="date"  autocomplete="off"
															  onFocus="WdatePicker()"
															  style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="${nowDate}"
															  value="${nowDate}">

									<button type="button" class="btn btn-primary" onclick="searchData()">查询</button>
								</div>
							</div>
							<div class="tj_2">
								<p>
									<span>请假人次：</span><b class="b1" id="renshu">0</b>
								</p>
								<p>
									<span>请假天数：</span><b class="b2" id="tianshu">0</b>
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
											<th>请假次数</th>
											<th>请假天数</th>
										</tr>
									</thead>
									<tbody id="tbodys"></tbody>
								</table>
							</div>
						</div>
					</div>
						<!-- 统计页面结束 -->
				</div>
			</div>
		</div>
	</div>
	<div class="zhezhao" style="display:none"></div>
</body>
</html>