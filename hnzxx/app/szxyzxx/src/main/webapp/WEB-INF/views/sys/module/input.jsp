<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<title>菜单创建</title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}
.myerror {
	color: red !important;
	width:22%;
	display:inline-block;
	padding-left:10px;
}
.res-bot {
    border-bottom: 1px solid #e6e6e6;
    border-top: 1px solid #e6e6e6;
    margin-top: -2px;
    width:75%;
}
.res-bot div {
    background: none repeat scroll 0 0 #f2f2f2;
    border-top: 1px solid #fff;
    height: 3px;
    overflow: hidden;
}
.res-foot {
    background: url("${ctp}/res/css/extra/images/icon.gif") no-repeat -173px -448px;
    cursor: pointer;
    height: 24px;
    line-height: 20px;
    margin: -1px auto 0;
    text-align: center;
    width: 149px;
}
.fa{margin-right:5px;}
</style>
</head>
<body>
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets light-gray">
				<div class="widget-container">
					<form class="form-horizontal" id="menu_form">
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>菜单编号</label>
							<div class="controls">
								<input type="text" id="code" name="code"
									class="span13" class="" placeholder="输入唯一编号, 不能为空" value="${module.code}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>菜单名称</label>
							<div class="controls">
								<input type="text" id="name" name="name"
									class="span13" placeholder="请输入菜单名称, 不能为空" value="${module.name}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">菜单路径</label>
							<div class="controls">
								<input type="text" id="accessUrl" name="accessUrl"
									class="span13" placeholder="请输入菜单路径" value="${module.accessUrl}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><font style="color:red">*</font>排序号</label>
							<div class="controls">
								<input type="text" id="listOrder" name="listOrder"
									class="span13" placeholder="请输入排序号，必须为数字且不为空" value="${module.listOrder}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">父菜单</label>
							<div class="controls">
								<input type="text" class="span13" disabled="disabled" placeholder="" value="${parent != null ? parent.name : module.parent.name}">
								<input type="hidden" id="parentCode" name="parentCode" value="${module.parentCode == null ? parent.code : module.parent.code}" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">启用</label>
							<div class="controls">
								<select class="span13" id="state" name="state">
									<option value="<%= AbandonedDefaultStatus.ENABLED %>">是</option>
									<option value="<%= AbandonedDefaultStatus.DISABLE %>">否</option>
								</select>
							</div>
						</div>
						
						<div class="control-group">
							<label class="control-label">描述</label>
							<div class="controls">
								<textarea id="description" name="description" class="span13" placeholder="" value="">${module.description}</textarea>
							</div>
						</div>
						
						<div class="control-group">
						<label class="control-label">菜单图标</label>
						<div class="controls" >
							<div style="height:118px;overflow:hidden;">
							<jsp:include page="/views/embedded/icon.jsp"></jsp:include>
							</div>
							<div class="res-bot">
					        	<div></div>
					    	</div>
					    <div style="width:75%"><div  class="icongif res-foot" data-isopen="open" id="catalogToggleButton"><i class="fa fa-angle-down"></i>打开</div></div>
						</div>
						
						</div>
						<div class="control-group">
							<label class="control-label">云资源图标</label>
							<div class="controls">
								<div class="row-fluid all-icons">
									<div>
										<ul class="yun_ul">
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_1">
												<i class="fa yun_1"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_2">
												<i class="fa yun_2"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_3">
												<i class="fa yun_3"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_4">
												<i class="fa yun_4"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_5">
												<i class="fa yun_5"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_6">
												<i class="fa yun_6"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_7">
												<i class="fa yun_7"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_8">
												<i class="fa yun_8"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_9">
												<i class="fa yun_9"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_10">
												<i class="fa yun_10"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_11">
												<i class="fa yun_11"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_12">
												<i class="fa yun_12"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_13">
												<i class="fa yun_13"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_14">
												<i class="fa yun_14"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  yun_15">
												<i class="fa yun_15"></i>
											</li>
										</ul>
									</div>
								</div>	
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">办公OA图标</label>
							<div class="controls">
								<div class="row-fluid all-icons">
									<div>
										<ul class="oa_ul">
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_1">
												<i class="fa oa_1"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_2">
												<i class="fa oa_2"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_3">
												<i class="fa oa_3"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_4">
												<i class="fa oa_4"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_5">
												<i class="fa oa_5"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_6">
												<i class="fa oa_6"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_7">
												<i class="fa oa_7"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_8">
												<i class="fa oa_8"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_9">
												<i class="fa oa_9"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_10">
												<i class="fa oa_10"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_11">
												<i class="fa oa_11"></i>
											</li>
											<li><input type="radio" name="icon" style="margin-top: 0;" value="fa  oa_12">
												<i class="fa oa_12"></i>
											</li>
										</ul>
									</div>
								</div>	
							</div>
						</div>
						<!-- <div class="control-group">
						<label class="control-label"></label>
						<div class="controls" style="text-align:center;width:525px;">
							<button type="button" class="btn btn-info btn-open" >展开</button>
							<button type="button" class="btn btn-info btn-close" style="display:none;">关闭</button>
							</div>
						</div> -->
						<div class="form-actions" style="padding-left:0;background:#eee;text-align:center;">
							<input type="hidden" id="id" name="id" value="${module.id}"/>
							<input type="hidden" id="level" name="level" value="${module != null ? module.level : (parent != null ? parent.level + 1 : 0)}">
							<button class="btn btn-info" type="button" onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	var caller = "${param.caller}";
	$(function() {
		$(".btn-open").click(function(){
			$(this).parent().parent().prev().children(".controls").css("height","600px");
			$(this).hide();
			$(this).next().show();
		});
		$(".btn-close").click(function(){
			$(this).parent().parent().prev().children(".controls").css("height","116px");
			$(this).hide();
			$(this).prev().show();
		}); 
	});
	$(function(){
		$("#catalogToggleButton").click(function(){
			if($(this).attr("data-isopen")=="open"){
				$(this).html("<i class='fa fa-angle-up'></i>关闭");
				$(this).attr("data-isopen","close");
				$(this).parent().prev().prev().css("height","588px");
			}else{
				$(this).html("<i class='fa fa-angle-down'></i>打开");
				$(this).attr("data-isopen","open");
				$(this).parent().prev().prev().css("height","116px");
			}
		});
	});
	$(function() {
		$("#state").val("${module.state}");
		$(".row-fluid .span4 .unstyled input:radio[value='${module.icon}']").prop("checked", true);
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#menu_form").validate({
			errorClass : "myerror",
			rules : {
				"code" : {
					required : true,
					accCheck : true,
					maxlength : 30,
					remote : {
						type : "GET",
						async : false,
						url  : "${pageContext.request.contextPath}/sys/module/checker",
						data : {
							'dxlx' : 'code',
							'code' : function() {
								return $("#code").val();
							},
							'id' : function() {
								return $("#id").val();
							}
						}
					}
				},
				"name" : {
					required : true,
					maxlength : 30,
				},
				"accessUrl" : {
					
				},
				"listOrder" : {
					required : true,
					digits : true
				}
			},
			messages : {
				"code" : {
					remote : "编号已存在"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $id = $("#id").val();
			var isUpdate = false;
			var $requestData = formData2JSONObj("#menu_form");
			var url = "${pageContext.request.contextPath}/sys/module/creator";
			
			if ("" != $id) {
				$requestData._method = "put";
				url = "${pageContext.request.contextPath}/sys/module/" + $id;
				isUpdate = true;
			}
			var loader = new loadLayer();
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
							if("tree" === caller) {
								var seletedNode = parent.core_iframe.window.zTree.getSelectedNodes()[0];
								if(isUpdate) {
									$.success("修改成功");
									seletedNode.name = $("#name").val();
									parent.core_iframe.window.zTree.updateNode(seletedNode, false);
								} else {
									$.success("创建成功");
									parent.core_iframe.window.zTree.reAsyncChildNodes(seletedNode, "refresh", true);
								}
							} else {
								parent.core_iframe.window.location.reload();
							}
						} else {
							if("tree" === caller) {
								var seletedNode = parent.window.zTree.getSelectedNodes()[0];
								if(isUpdate) {
									$.success("修改成功");
									seletedNode.name = $("#name").val();
									parent.window.zTree.updateNode(seletedNode, false);
								} else {
									$.success("创建成功");
									parent.window.zTree.reAsyncChildNodes(seletedNode, "refresh", true);
								}
							} else {
								parent.window.location.reload();
							}
						}
						$.closeWindow();
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