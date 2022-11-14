<%@ page language="java"
	import="java.util.*, platform.education.user.contants.AclContants"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/views/embedded/taglib.jsp"%>
<%@include file="/views/embedded/plugin/jquery.jsp"%>
<%@include file="/views/embedded/plugin/layer.jsp"%>
<%@include file="/views/embedded/plugin/szxy_window_js.jsp"%>
<link
	href="${pageContext.request.contextPath}/res/css/common/qxgl/style.css"
	rel="stylesheet" />
<title>角色授权</title>
</head>
<body>
	<input type="hidden" name="roleId" id="roleId" value="${param.id}" />
	<div style="margin-bottom: 1px; height: 30px; line-height: 30px;">
		<span style="margin-left: 19px; position: relative; top: 1px;">
			<button class="btn"
				style="cursor: pointer; height: 24px; line-height: 18px;"
				onclick="displayAllMenu();">展开菜单</button>
			<button class="btn"
				style="cursor: pointer; height: 24px; line-height: 18px;"
				onclick="hideAllMenu();">收缩菜单</button>
		</span>
		<div style="float: right; line-height: 30px; margin-right: 4px;">
			<span style="font-size: 12px;">授权模式</span> <select id="grant_mode">
				<option value="mode1">模式一（默认）-单击复选按钮给菜单授予（禁用）对应权限</option>
				<option value="mode2">模式二-点击全选复选按钮给当前菜单授予（禁用）所有权限</option>
				<option value="mode3">模式三-点击全选复选按钮给当前菜单（所有子菜单）授予（禁用）所有权限</option>
				<option value="mode4">模式四-单击复选按钮给当前菜单（所有子菜单）授予（禁用）对应权限</option>
			</select>
		</div>
	</div>
	<div class="catalog">
		<div class="cata_list">
			<c:forEach items="${modules}" var="lv1" varStatus="lv1Status">
				<c:if test="${lv1Status.count == 1}">
					<ul>
				</c:if>
				<li>
					<div class="MLNR">
						<a href="javascript:void(0);" class="${fn:length(lv1.childrens) > 0 ? 'show' : ''}">${lv1.name}</a>
						<div data_m_code="${lv1.code}" class="CZ">
							<c:if test="${fn:length(lv1.childrens) > 0}">
								<span style="display: none">
									全选：<input class="mode3_quick_grant" type="checkbox">
								</span>
							</c:if>
							<span style="display: none">
								全选：<input class="mode2_quick_grant" type="checkbox"></span> 
								授权：
								<input class="crud_btn" data-type="<%=AclContants.CREATE%>" type="checkbox">增 
								<input class="crud_btn" data-type="<%=AclContants.READ%>" type="checkbox">查 
								<input class="crud_btn" data-type="<%=AclContants.UPDATE%>" type="checkbox">改 
								<input class="crud_btn" data-type="<%=AclContants.DELETE%>" type="checkbox">删
								<span class="SQ"> 启用：<input type="checkbox">
							</span>
						</div>
					</div> 
					<c:forEach items="${lv1.childrens}" var="lv2" varStatus="lv2Status">
						<c:if test="${lv2Status.count == 1}">
							<ul style="display: none">
						</c:if>
						<li>
							<div class="MLNR">
								<a href="javascript:void(0);" class="${fn:length(lv2.childrens) > 0 ? 'show' : ''}">${lv2.name}</a>
								<div data_m_code="${lv2.code}" class="CZ">
									<c:if test="${fn:length(lv2.childrens) > 0}">
										<span style="display: none">
											全选：<input class="mode3_quick_grant" type="checkbox">
										</span>
									</c:if>
									<span style="display: none">
										全选：<input class="mode2_quick_grant" type="checkbox"></span> 
										授权：
										<input class="crud_btn" data-type="<%=AclContants.CREATE%>" type="checkbox">增 
										<input class="crud_btn" data-type="<%=AclContants.READ%>" type="checkbox">查
										<input class="crud_btn" data-type="<%=AclContants.UPDATE%>" type="checkbox">改 
										<input class="crud_btn" data-type="<%=AclContants.DELETE%>" type="checkbox">删
										<span class="SQ"> 启用：<input type="checkbox">
									</span>
								</div>
							</div> 
							<c:forEach items="${lv2.childrens}" var="lv3" varStatus="lv3Status">
								<c:if test="${lv3Status.count == 1}">
									<ul style="display: none">
								</c:if>
								<li>
									<div class="MLNR">
										<a href="javascript:void(0);" class="${fn:length(lv3.childrens) > 0 ? 'show' : ''}">${lv3.name}</a>
										<div data_m_code="${lv3.code}" class="CZ">
											<c:if test="${fn:length(lv3.childrens) > 0}">
												<span style="display: none">
													全选：<input class="mode3_quick_grant" type="checkbox">
												</span>
											</c:if>
											<span style="display: none">
												全选：<input class="mode2_quick_grant" type="checkbox"></span> 
												授权：
												<input class="crud_btn" data-type="<%=AclContants.CREATE%>" type="checkbox">增 
												<input class="crud_btn" data-type="<%=AclContants.READ%>" type="checkbox">查
												<input class="crud_btn" data-type="<%=AclContants.UPDATE%>" type="checkbox">改 
												<input class="crud_btn" data-type="<%=AclContants.DELETE%>" type="checkbox">删
												<span class="SQ"> 启用：<input type="checkbox">
											</span>
										</div>
									</div>
									<c:forEach items="${lv3.childrens}" var="lv4" varStatus="lv4Status">
										<c:if test="${lv4Status.count == 1}">
											<ul>
										</c:if>
										<li>
											<div class="MLNR">
												<a href="javascript:void(0);" class="${fn:length(lv4.childrens) > 0 ? 'show' : ''}">${lv4.name}</a>
												<div data_m_code="${lv4.code}" class="CZ">
													<span style="display: none">
														全选：<input class="mode2_quick_grant" type="checkbox"></span> 
														授权：
														<input class="crud_btn" data-type="<%=AclContants.CREATE%>" type="checkbox">增 
														<input class="crud_btn" data-type="<%=AclContants.READ%>" type="checkbox">查
														<input class="crud_btn" data-type="<%=AclContants.UPDATE%>" type="checkbox">改 
														<input class="crud_btn" data-type="<%=AclContants.DELETE%>" type="checkbox">删
														<span class="SQ"> 启用：<input type="checkbox">
													</span>
												</div>
											</div>
										</li>
										<c:if test="${lv4Status.count == fn:length(lv3.childrens)}">
											</ul>
										</c:if>
									</c:forEach>
								</li>
								<c:if test="${lv3Status.count == fn:length(lv2.childrens)}">
									</ul>
								</c:if>
							</c:forEach>
						</li>
						<c:if test="${lv2Status.count == fn:length(lv1.childrens)}">
							</ul>
						</c:if>
					</c:forEach>
				</li>
				<c:if test="${lv1Status.count == fn:length(modules)}">
					</ul>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<script type="text/javascript">
	
		var grantMode = null;
	
		$(function() {
			grantMode = $("#grant_mode"); 
			$.ajaxSetup({
				cache : false
			});
			$(".show").click(
					function(event) {
						event.stopPropagation();
						if ($(this).parent().parent().children("ul").css(
								"display") == "none") {
							$(this).removeClass("show").addClass("hide");
							$(this).parent().parent().children("ul").show();
						} else {
							$(this).removeClass("hide").addClass("show");
							$(this).parent().parent().children("ul").hide();
						}
					});

			$(".hide").click(
					function(event) {
						event.stopPropagation();
						if ($(this).parent().parent().children("ul").css(
								"display") == "none") {
							$(this).removeClass("show").addClass("hide");
							$(this).parent().parent().children("ul").show();
						} else {
							$(this).removeClass("hide").addClass("show");
							$(this).parent().parent().children("ul").hide();
						}
					});
			initGrantBtn();
			initEnableBtn();
			showGranted();
			initGrantModeSelector();
			initAllSelectorBtn();
		});
		
		var loader = new loadDialog();
		//初始化crud授权复选按钮
		function initGrantBtn() {
			$(".catalog .cata_list ul li .CZ .crud_btn").on("click", function(event) {
				var $this = $(this);
				var $currentTopMenu = $this.parent().parent().parent();
				var mode = grantMode.val();
				loader.show();
				if("mode4" === mode) {
					var $currentTopMenuDm = $this.parent().attr("data_m_code");
					var zybsList = [];
					zybsList.push($currentTopMenuDm);
					var allSubMenu = $currentTopMenu.find("ul li .CZ");
					$.each(allSubMenu, function(index, value) {
						zybsList.push($(value).attr("data_m_code"));
					});
					var $requestData = {};
					$requestData.ztbs = $("#roleId").val();
					$requestData.zys = zybsList;
					$requestData.allow = $this.prop("checked");
					$requestData.per = $this.attr("data-type");
					$.get("${pageContext.request.contextPath}/sys/acl/grantor/batch/crud", $requestData, function(data, status) {
						if("success" === status) {
							if("success" === data) {
								$currentTopMenu.find(".CZ .crud_btn[data-type='" + $requestData.per + "']").prop("checked", $requestData.allow);
							} else {
								$.error("授权失败");
								$this.attr("checked", !$isAllowGrant);
							}
						} else {
							
						}
						loader.close();
					});
				} else {
					var $this = $(this);
					var $czParent = $this.parent();
					var $isAllowGrant = $this.prop("checked");
					var $code = $czParent.attr("data_m_code");
					var $ztbs = $("#roleId").val();
					var $permissionType = $this.attr("data-type");
					var $requestData = {};
					$requestData.per = $permissionType;
					$requestData.ztbs = $ztbs;
					$requestData.zybs = $code;
					$requestData.allow = $isAllowGrant;
					$.get("${pageContext.request.contextPath}/sys/acl/grantor/crud", $requestData, function(data, status) {
						if("success" === status) {
							if("fail" === data) {
								$.error("授权失败");
								$this.attr("checked", !$isAllowGrant);
							}
						}
						loader.close();
					});
				}
				event.stopPropagation();
			});
		}
		
		//初始化启用禁用复选按钮
		function initEnableBtn() {
			$(".catalog .cata_list ul li .CZ .SQ input:checkbox").on("click", function(event) {
				var $this = $(this);
				var $czParent = $this.parent().parent();
				var $isEnable = $this.prop("checked");
				var $ztbs = $("#roleId").val();
				var $code = $czParent.attr("data_m_code");
				var $requestData = {};
				$requestData.ztbs = $ztbs;
				$requestData.zybs = $code;
				$requestData.sfqy = $isEnable;
				$.get("${pageContext.request.contextPath}/sys/acl/grantor/enable", $requestData, function(data, status) {
					if("success" === status) {
						if("fail" === data) {
							$.error("操作失败");
							$this.attr("checked", !$isEnable);
						}
					}					
				});
				event.stopPropagation();
			});
		}
		
		//回显所有已授权的复选按钮
		function showGranted() {
			var $requestData = {};
			$requestData.roleId = $("#roleId").val();
			$.get("${pageContext.request.contextPath}/sys/acl/role/granted", $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					$.each(data, function(index, value) {
						var zybs = value.permissionCode;
						var zfbj = value.isDeleted;
						var sqzt = value.operation;
						var czrq = $(".CZ[data_m_code='" + zybs +"']");
						czrq.find(".SQ input:checkbox").prop("checked", zfbj == 0);
						initGrantedCrudBtn(czrq,"<%=AclContants.CREATE%>", sqzt);
						initGrantedCrudBtn(czrq,"<%=AclContants.READ%>", sqzt);
						initGrantedCrudBtn(czrq,"<%=AclContants.UPDATE%>", sqzt);
						initGrantedCrudBtn(czrq,"<%=AclContants.DELETE%>", sqzt);
						if (sqzt == 15) {
							czrq.find("span .mode2_quick_grant").prop("checked", "checked");
						}
					});
					$.each($(".mode3_quick_grant"), function(index, value) {
						var hasMode3TopCurrent = $(value).parent()
								.parent().parent();
						if (hasMode3TopCurrent.find(".CZ .crud_btn")
								.not("input:checked").length > 0) {
							$(value).prop("checked", false);
						} else {
							$(value).prop("checked", true);
						}
					});
				} else {

				}
			});
		}

		//根据授权状态值 勾选已经授权的对应复选按钮
		function initGrantedCrudBtn(crzq, operationType, sqzt) {
			var temp = 1;
			temp = temp << parseInt(operationType);
			temp &= sqzt;
			crzq.find("input[data-type='" + operationType + "']").prop("checked", temp != 0);
		}

		//初始化模拟选择下拉列表 change 事件
		function initGrantModeSelector() {
			$("#grant_mode").on("change", function() {
				var $this = $(this);
				var mode = $this.val();
				$(".mode2_quick_grant").parent().hide();
				$(".mode3_quick_grant").parent().hide();
				$("." + mode + "_quick_grant").parent().show("slow");
			});
		}

		//初始化模式二 模式三的全选按钮事件
		function initAllSelectorBtn() {
			$(".mode3_quick_grant").on("click", function() {
				loader.show();
				var $this = $(this);
				var $currentTopMenu = $this.parent().parent().parent().parent();
				var $currentTopMenuDm = $this.parent().parent().attr("data_m_code");
				var zybsList = [];
				zybsList.push($currentTopMenuDm);
				var allSubMenu = $currentTopMenu.find("ul li .CZ");
				$.each(allSubMenu, function(index, value) {
					zybsList.push($(value).attr("data_m_code"));
				});
				var $requestData = {};
				$requestData.ztbs = $("#roleId").val();
				$requestData.zys = zybsList;
				$requestData.allow = $this.prop("checked");
				$.get("${pageContext.request.contextPath}/sys/acl/grantor/batch/all", $requestData, function(data, status) {
					if ("success" === status) {
						if ("success" === data) {
							$currentTopMenu.find(".CZ .crud_btn").prop("checked", $requestData.allow);
							$currentTopMenu.find(".mode3_quick_grant").prop("checked", $requestData.allow);
						} else {
							$.error("授权失败");
							$this.attr("checked", !$isAllowGrant);
						}
					} else {

					}
					loader.close();
				});

			});

			$(".mode2_quick_grant").on("click", function() {
				loader.show();
				var $this = $(this);
				var $code = $this.parent().parent().attr("data_m_code");
				var $isAllowGrant = $this.prop("checked");
				var $ztbs = $("#roleId").val();
				var $requestData = {};
				$requestData.ztbs = $ztbs;
				$requestData.zybs = $code;
				$requestData.allow = $isAllowGrant;
				$.get("${pageContext.request.contextPath}/sys/acl/grantor/allper", $requestData, function(data, status) {
					if ("success" === status) {
						if ("success" === data) {
							$this.parent().parent().find(".crud_btn").prop("checked", $requestData.allow);
						} else {
							$.error("授权失败");
							$this.attr("checked", !$isAllowGrant);
						}
					} else {

					}
					loader.close();
				});
			});
		}

		function displayAllMenu() {
			$(".catalog .cata_list .show").removeClass("show").addClass("hide");
			$(".catalog .cata_list .MLNR").next().show();
		}

		function hideAllMenu() {
			$(".catalog .cata_list .hide").removeClass("hide").addClass("show");
			$(".catalog .cata_list .MLNR").next().hide();
		}
	</script>
</body>
</html>