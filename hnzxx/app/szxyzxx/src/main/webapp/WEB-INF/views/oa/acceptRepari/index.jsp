<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
<title>报修</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="报修" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
		<div class="span12 white">
			<div class="content-widgets ">
				<div class="widget-head">
						<h3 class="x-head content-top">
							<c:if test="${empty approval}">
								<a href="javascript:void(0);" class="on" onclick="showbx()">全部报修</a>
								<a href="javascript:void(0);" onclick="showTj()">报修统计</a>
							</c:if>
						</h3>
				</div>
				<div id="contan">
				<div class="widget-container">
				  <div class="clearfix list-search-bar x-search">
					  <div class="select_b">
						  <div class="select_div">
							  <span style="position:relative;display:block;float:left;"></span>
							  <input type="text" size="16" placeholder="标题/发布人" class="input-medium" id="searchWord">
							  <i title="清空" class="fa fa-remove"></i>
							  <%--						</span>--%>
						  </div>

						<div class="select_div">
							<span>类别:  &nbsp;&nbsp;&nbsp;&nbsp;</span>
							<select name="typeName" id="typeName">
								<option value="">请选择</option> <!-- 这个option也可以写在ajax中-->
							</select>
						</div>
				  		<button type="button" class="btn" onclick="search();"><i class="fa fa-search"></i></button>
                    </div>
                  </div>
				</div>
				<div class="x-main">
					<div id="datas-id">
						<jsp:include page="./list.jsp" />
					</div>
<!-- 					将分页移动到list页面 -->
<%-- 					<jsp:include page="/views/embedded/jqpagination.jsp" flush="true"> --%>
<%-- 						<jsp:param name="id" value="datas-id" /> --%>
<%-- 						<jsp:param name="url" value="/oa/acceptrepari/applyIndex?approval=${approval}&sub=list&dm=${param.dm}" /> --%>
<%-- 						<jsp:param name="pageSize" value="${page.pageSize}" /> --%>
<%-- 					</jsp:include> --%>
				</div>
			</div>
			</div>
			
			<div id="tj" style="display: none;">
				<div class="widget-container clearfix x-tongji">
					<form class="form-horizontal span7 left" style="padding:0">
						<div>
							<label class="control-label">时间范围:</label>
							<div class="date">
									<select id="sj">
									</select>
							</div>
						</div>
					</form>
				</div>
				<div id="countDatas">
				
				</div>
			</div>
		</div>
	</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		// 获取类别
		$.jcGcSelector("#typeName", {"tc" : "GB-BXLX"});

		getYear();
		ChangeTimeCount();
	})
	
	function search() {
		var val = {
			"searchWord" : $("#searchWord").val(),
			"typeId" : $("#typeName option:selected").val()
		};
		var id = "datas-id";
		var url = "/oa/acceptrepari/applyIndex?sub=list&approval=${approval}&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		window.location.href="${ctp}/oa/applyrepair/creator";
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		window.location.href = "${ctp}/oa/applyrepair/editor?id=" + id;
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/oa/applyrepair/viewer?id=' + id, '700', '300');
	}
	
	// 	删除对话框
	function del(id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(id);
		});
	}

	// 	执行删除
	function executeDel(id) {
		$.post("${ctp}/oa/applyRepair/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#apply_" + id).remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	//申请
	function sqRepari(id){
		$.ajax({
			type : "post",
			url : "${ctp}/oa/applyrepair/myApply?applyId="+id,
			dataType : "html", 
			success : function(data) {
				$(".sq").empty();
				$(".wx").empty();
				$(".pj").empty();
				$("#wx_div"+id).append(data);
			}
		});
	}
	
	//维修
	function wxRepari(id){
		$.ajax({
			type : "post",
			url : "${ctp}/oa/acceptrepari/ownRepair?approval=${approval}&applyId="+id,
			dataType : "html", 
			success : function(data) {
				$(".sq").empty();
				$(".wx").empty();
				$(".pj").empty();
				$("#wx_div"+id).append(data);
			}
		});
	}
	
	//评价
	function pjRepari(id){
		$.ajax({
			type : "post",
			url : "${ctp}/oa/applyrepair/appraise?applyId="+id+"&isCK=isCK",
			dataType : "html", 
			success : function(data) {
				$(".sq").empty();
				$(".wx").empty();
				$(".pj").empty();
				$("#pj_div"+id).append(data);
			}
		});
	}
	
	function showTj(){
		var currentValue = $("#sj").val();
		$.ajax({
			type : "post",
			url : "${ctp}/oa/applyrepair/applyCount?selectMonth="+currentValue,
			dataType : "html", 
			success : function(data) {
				$("#countDatas").empty();
				$("#countDatas").append(data);
			}
		});
		$("#contan").hide();
		$("#tj").show();
	}
	
	function showbx(){
		$("#contan").show();
		$("#tj").hide();
	}
	
	function ChangeTimeCount(){
		$("#sj").change("on",function(){
			var currentValue = $(this).val();
			$.ajax({
				type : "post",
				url : "${ctp}/oa/applyrepair/applyCount?sub=list&selectMonth="+currentValue,
				dataType : "html",
				success : function(data) {
					$("#countDatas").empty();
					$("#countDatas").append(data);
				}
			});
		});
	}
	
	//统计的js月份分割
	function getYear(){
		var curenData = new Date();
		var curenYear = curenData.getFullYear();
		var dateSelect = $("#sj");
		for(var i = 1;i <= 12;i++){
			var  day = new Date(curenYear,i,0); 
			var daycount = day.getDate();
			var datas = convertData(i);
			var n = curenData.getMonth()+1;
			if(n==i){
				var opt = "<option selected='selected' value='"+ curenYear + "/" + i + "/1" +
				"-" + curenYear + "/" + i + "/" + daycount +"'>" +
				"本月（" + 
				curenYear + "/" + i + "/1" +
				"-" + curenYear + "/" + i + "/" + daycount +
				"）</option>";
			}else{
				var opt = "<option value='"+ curenYear + "/" + i + "/1" +
				"-" + curenYear + "/" + i + "/" + daycount +"'>" +
				datas + "月（" + 
				curenYear + "/" + i + "/1" +
				"-" + curenYear + "/" + i + "/" + daycount +
				"）</option>";
			}
			dateSelect.append(opt);
		}
		 $("#sj").chosen();
	}
	
	function convertData(num){
		var cdata = ["一","二","三","四","五","六","七","八","九","十","十一","十二"];
		return cdata[num-1];
	}
</script>
</html>