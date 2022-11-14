<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<%@ include file="/views/embedded/common.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css"
	rel="stylesheet">
<title>车辆</title>
<style type="text/css">
.row-fluid .span4 {
	width: 220px;
}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
</style>
<script type="text/javascript">
	
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
							<li><a href="javascript:void(0)" class="on">申请用车</a></li>
						</ul>
					</div>
					<div class="yc_sq">
						<form class="form-horizontal" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">标题<span style="color:red">*</span>：</label>
								<div class="controls">
									<input type="text" name="title" class="span8 left_red {required:true,maxlength:40}"
										value="${applyCard.title}" placeholder="请输入标题，少于40个中文字符" />
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">联系电话<span style="color:red">*</span>：</label>
								<div class="controls">
									<input type="text" name="phone" value="${applyCard.phone}"
										class="span4 left_red {required:true,isTel:true}" placeholder="请输入正确的联系电话" />
								</div>
							</div>
							<c:if test="${depList.size() > 0}">
							<div class="control-group">
								<label class="control-label">部门<span style="color:red">*</span>：</label>
								<div class="controls">
									<select name="departmentId" id="departmentId" class="{}">
										<c:forEach items="${depList}" var="dep">
											<option value="${dep.departmentId}" <c:if test="${applyCard.departmentId==dep.departmentId}">selected="selected"</c:if> >${dep.departmentName}</option>
										</c:forEach>
									</select>
								</div>
							</div>
							</c:if>
							<div class="control-group">
								<label class="control-label">申请车辆<span style="color:red">*</span>：</label>
								<div class="controls">
									<div class="add_cheliang">
										<div class="a_1" 
											<c:if test="${applyCard.cardId != null && applyCard.cardId != ''}">style="display: none;"</c:if>
										>
											<a href="javascript:void(0)" class="add_car">添加</a>
										</div>
										<div class="a_2"
											<c:if test="${applyCard.cardId == null || applyCard.cardId == ''}">style="display: none;"</c:if>
										>
											<span id="c_name">${applyCard.cardName}</span> <a href="javascript:void(0)" class="edit_car">修改</a>
										</div>
										<div class="xzcl" style="display: none;">
											<div class="c_top">
												<div class="c1">
													<p>选择车辆</p>
													<a class="close_1" href="javascript:void(0)"><i
														class="fa  fa-times"></i></a>
												</div>
												<input type="text" placeholder="输入车辆名称或车牌号" id="seachText"/>
											</div>
											<div class="cl_list">
												<ul >
													<c:forEach items="${cardList}" var="list">
														<li>
															<div class="touxiang">
																<c:if test="${list.cover==null || list.cover==''}">
																	<img
																		src="${pageContext.request.contextPath}/res/images/cheliang.jpg">
																</c:if>
																<c:if test="${list.cover!=null && list.cover!=''}">
<%-- 																	<img src="${list.cover}"> --%>
																		<img src="<entity:getHttpUrl uuid='${list.cover}'/>">
																</c:if>
															</div>
															<div class="detail">
																<div class="p2">${list.cardName}</div>
																<div class="p3">
																	<i class="fa fa-users"></i>
																	<p class="p_div">荷载人数</p>
																	<span>${list.fullLoad}人</span>
																</div>
																<div class="p3">
																	<i class="fa  fa-money"></i>
																	<p class="p_div">车牌号</p>
																	<span class="chepai">${list.plateNumber}</span>
																</div>
																<div class="p3">
																	<i class="fa fa-exclamation-triangle"></i>
																	<p class="p_div">当前状态</p>
																	<span
																		class="${list.serviceCondition==0 ? 'keyong' : 'buke'}">
																		<c:if test="${list.serviceCondition==0}">可用</c:if> <c:if
																			test="${list.serviceCondition==1}">使用中</c:if> <c:if
																			test="${list.serviceCondition==2}">维修中</c:if>
																	</span>
																</div>
															</div> <a href="javascript:void(0)" data-id="${list.id}"
															class=" ${list.serviceCondition==0 ? 'ky' : 'bky'} btn-cz"></a>
															<div class="clear"></div>
														</li>
													</c:forEach>
												</ul>
											</div>
											<div class="cl_bottom">
												<button class="btn btn-blue">确定</button>
												<button class="btn btn-cancel">取消</button>
											</div>
										</div>
										
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">使用时间<span style="color:red">*</span>：</label>
								<div class="controls">
									<input type="text" name="beginDate" id="beginDate"
										value='<fmt:formatDate value="${applyCard.beginDate}" pattern="yyyy-MM-dd"/>'
										class="span4 left_red {required:true}" placeholder="开始时间"
										onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',minDate:new Date()});" /> &nbsp; 至 &nbsp; <input
										type="text" name="endDate" id="endDate"
										value='<fmt:formatDate value="${applyCard.endDate}" pattern="yyyy-MM-dd"/>'
										class="span4 left_red {required:true}" placeholder="结束时间"
										onclick="WdatePicker({minDate:'#F{$dp.$D(\'beginDate\')}'});" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">备注：</label>
								<div class="controls">
									<textarea name="remark" class="span8 {maxlength:200}">${applyCard.remark}</textarea>
								</div>
							</div>
							<div class="caozuo" style="text-align: center;">
								<button class="btn btn-success" type="button"
									onclick="savedata();">发布</button>
<!-- 								<button class="btn">预览</button> -->
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="cId" />
	<input type="hidden" id="id" value="${applyCard.id}" />
</body>
<script type="text/javascript">
$(function() {
	$(".yc_sq .a_1 a").click(function() {
		$(".xzcl").show();
		$("#seachText").val("");
		seachCardAuto();
	});
	$(".yc_sq .a_2 a").click(function() {
		$(".xzcl").show();
		$("#seachText").val("");
		seachCardAuto();
	});
	$(".xzcl .c_top .c1 .close_1,.xzcl .cl_bottom .btn-cancel").click(
			function() {
				$(".xzcl").hide();
			})
	$(".xzcl .cl_list ul li .ky").click(function() {
		$(".xzcl .cl_list ul li .btn-cz").removeClass("btn-click");
		$(this).addClass("btn-click");
		$("#cId").val($(this).attr("data-id"));
	});
	$(".xzcl .cl_bottom .btn-blue").click(function() {
						if ($(".xzcl .cl_list ul li .btn-cz").hasClass(
								"btn-click")) {
							var c_name = $(".btn-click").prev().children(
									".p2").text();
							var c_pai = $(".btn-click").prev().children()
									.children(".chepai").text();
							var cheliang = c_name + '' + '[' + c_pai + ']'
							$(".yc_sq .a_1").hide();
							$(".yc_sq .a_2").show();
							$(".yc_sq .a_2 span").text(cheliang);
							$(".xzcl").hide();
						}
						
					})
});
	var checker;
	$(function() {
		removeRep("departmentId");
// 		checker = initValidator();
		$("#departmentId").chosen();
		seachCard();
	});

	//去掉部门下拉选的重复值
	function removeRep(id){
       	var options = "";
       	$("#"+id).find("option").each(function(j,m){
       		if(options.indexOf($(m)[0].outerHTML) == -1){
       				options += $(m)[0].outerHTML; 
       			}        
       		});        
       	$("#"+id).html(options); 
	}
	
	//车辆的模糊匹配
	function seachCard(){
		$("#seachText").keyup(function(e){
		    if(!e) var e = window.event; 
		    var seachText = $("#seachText").val();
			$(".xzcl .cl_list ul li").each(function(index) {
				var cardName = $(this).children().children(".p2").text();
				var cardNumber = $(this).children().children(".p3").children(".chepai").text();
		    	if(cardName.indexOf(seachText)>=0 || cardNumber.indexOf(seachText)>=0){
		    		$(this).show();
		    	}else{
		    		$(this).hide();
		    	}
		 	 });
		 });
	}
	
	function seachCardAuto(){
		var seachText = $("#seachText").val();
		$(".xzcl .cl_list ul li").each(function(index) {
			var cardName = $(this).children().children(".p2").text();
			var cardNumber = $(this).children().children(".p3").children(".chepai").text();
	    	if(cardName.indexOf(seachText)>=0 || cardNumber.indexOf(seachText)>=0){
	    		$(this).show();
	    	}else{
	    		$(this).hide();
	    	}
	 	 });
	}
	
	function initValidator() {
		return $(".form-horizontal").validate({
			errorClass : "myerror",
			rules : {

			},
			messages : {

			}
		});
	}

	//保存或更新修改
	function savedata() {
		checker = initValidator();
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj(".form-horizontal");
			$requestData.cardId = $("#cId").val();
			var url = "${ctp}/oa/applycard/creator";
			var $id = $("#id").val();
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/applycard/" + $id;
			}
			if($("#c_name").text()=="" || $("#c_name").text()==null){
				$.alert("请选择你使用的车辆");
				return;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if ("success" === status) {
					data = eval("(" + data + ")");
					if ("success" === data.info) {
						$.success('操作成功');
						if (parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
						$.closeWindow();
					}else if("isApproval" === data.info){
						$.error("该申请已被审批，不能再次修改");
					} else {
						$.error("操作失败");
					}
				} else {
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
</script>
</html>