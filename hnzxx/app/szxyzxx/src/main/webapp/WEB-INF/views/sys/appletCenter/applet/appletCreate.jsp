<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_jspj.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/address.js" ></script>
<title>应用注册</title>
<style type="text/css">
label.myerror{
	display:inline-block;
	color:#ff0000;
}
</style>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">应用中心  >  应用管理  > 平台应用管理  >   <span>应用注册</span></p>
	<div class="content_main white">
		<div class="content_top">
			<div class="f_left"><p>应用注册</p></div>
			<button class="btn btn-lightGray" style="float: right;margin-top: 14px;margin-right: 10px;" onclick="back();"><i class="fa fa-arrow-left" ></i>返回</button>
		</div>
		
		<div class="apply_register">
			<form class="form-horizontal" id="applet_form">
				<div class="apply_register_left input_select fl">
					<div class="select_div" style="display: none;">
						<span>id：</span>
						<input type="text" id="id" name="id" value="${applet.id }">
					</div>
					<div class="select_div">
						<%-- <span>主应用：</span>
						<select class="span2" id="appKey" name="appKey" value="${applet.appKey }">
							<!-- <option>定邦教育云</option> -->
							<c:forEach items="${appList }" var ="app">
								<c:choose>
									<c:when test="${applet.appKey == app.appKey }"><option value="${app.appKey }" selected="selected">${app.name }</option></c:when>
									<c:otherwise><option value="${app.appKey }">${app.name }</option></c:otherwise>
								</c:choose>
							</c:forEach>
						</select> --%>
					</div>
					<div class="select_div">
						<span>应用名称：</span>
						<input type="text" id="name" name="name" value="${applet.name }">
						<span style="color:red ; text-align: left; width: 15px;">*</span>
					</div>
					<div class="select_div">
						<span>Key：</span>
						<input type="text" id="appletKey" name="appletKey" value="${applet.appletKey }"
						onkeyup="value=value.replace(/[\W]/g,'') "onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						<span style="color:red ;text-align: left; width: 15px;">*</span>
					</div>
					<div class="select_div">
						<span>Url：</span>
						<input type="text" id="url" name="url" value="${applet.url }">
					</div>
					<div class="select_div">
						<span>版本：</span>
						<input type="text" id="version" name="version" value="${applet.version }"
						onkeyup="value=value.replace(/[\u4E00-\u9FA5]/i,'')"onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))">
						<span style="color:red ; text-align: left; width: 15px;">*</span>
					</div>
					<div class="select_div">
						<span>适用类型：</span>
						<select class="span2" id="actType" name="actType" value="${applet.actType }" >
							<option value="0" <c:if test='${applet.actType == 0 }'>selected</c:if>>所有平台</option>
							<option value="1" <c:if test='${applet.actType == 1 }'>selected</c:if>>Web服务器端</option>
							<option value="2" <c:if test='${applet.actType == 2 }'>selected</c:if>>移动端</option>
						</select>
					</div>
					<div class="select_div">
						<span>来源类型：</span>
						<select class="span2" id="sourceType" name="sourceType" value="${applet.sourceType }" >
							<option value="1" data-vendorid="0" <c:if test='${applet.sourceType == 1 }'>selected</c:if>>官方</option>
							<%-- <option value="2" <c:if test='${applet.sourceType == 2 }'>selected</c:if>>第三方</option> --%>
							<c:forEach items="${appletVendorList }" var="appletVendor">
								<option value="2" data-vendorid="${appletVendor.id }" <c:if test='${applet.sourceType == 2 && applet.vendorId == appletVendor.id}'>selected</c:if>>${appletVendor.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="select_div">
						<span>说明：</span>
						<textarea  style="resize:none" id="description" name="description" value="${applet.description }">${applet.description }</textarea>
					</div>
				</div>
				<div class="apply_register_right input_select" style="margin-left:562px">
					<div class="select_div">
						<span style="float: left;margin-top: 10px;margin-right: 10px;">图标：</span>
						<a href="javascript:;" class="a-upload">
							<p><i class="fa fa-plus" ></i></p>
							<p>点击上传</p>
						    <input type="file" name="icon" id="icon" onchange="change(this)" value="${applet.icon }" src="${applet.icon }" >
							<img id="upImg" alt="" src="${applet.iconUrl }">
						</a>
					</div>
				</div>
			</form>
		</div>
		
		<div class="btn_cz" style="padding: 20px;border-top: solid 1px #e3e8ec;">
			<c:choose>
				<c:when test="${applet != null}">
				<button class="btn btn-blue" onclick="appletSaveOrUpdate();">保存</button></c:when>
				<c:otherwise>
				<button class="btn btn-blue" onclick="appletSaveOrUpdate();">注册</button></c:otherwise>
			</c:choose>
			
			<button class="btn btn-lightGray" onclick="back();">取消</button>
		</div>
	</div>
	

<script>

var checker;
$(function() {
	checker = initValidator();
});

function initValidator() {
	return $("#applet_form").validate({
		errorClass : "myerror",
		rules : {
			name :{
				required:true,
				minlength : 1,
				maxlength : 12
			},
			appletKey :{
				required:true
				//chinese : false
			},
			version :{
				required:true
				//chinese : false
			},
			/* url :{
				required:true
				//url:true
			}, */
			description :{
				maxlength : 200
			}
		},
		messages : {
			name : {
				required:"名称必填",
				maxlength:"字数超过上限"
			},
			appletKey :{
				required:"Key必填"
				//chinese:"只能为英文、数字"
			},
			version :{
				required:"版本必填"
				//chinese:"只能为应用、数字"
			},
			/* url : {
				required:"URL必填"
				//url:"请正确填写URL"
			}, */
			description :{
				maxlength : "200字以内"
			}
		}
	});
}

function back(){
	//window.history.back(-1);
	window.location.href = "${ctp}/sys/applet/index";
}

//保存或更新修改
function appletSaveOrUpdate() {
	if (checker.form()) {
		var loader = new loadLayer();
		var $id = $("#id").val();
		var icon = $("#icon").attr("value");
		//alert(icon);
		var $requestData = formData2JSONObj("#applet_form");
		var vendorId = $("#sourceType").find("option:selected").attr("data-vendorid");
		$requestData.vendorId = vendorId;
		$requestData.icon = icon;
		//去前后空格
		$requestData.url = $requestData.url.trim();
		$requestData.name = $requestData.name.trim();
		$requestData.appletKey = $requestData.appletKey.trim();
		$requestData.version = $requestData.version.trim();
		$requestData.description = $requestData.description.trim();
		//$.trim($requestData.url.trim());
		//console.log($requestData.url);
		var url = "${ctp}/sys/applet/creator";
		if ("" != $id) {
			$requestData._method = "put";
			url = "${ctp}/sys/applet/" + $id;
		}
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					window.location.href = "${ctp}/sys/applet/index";
					/* if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
					$.closeWindow(); */
				} else {
					$.error("Key值与已有数据重复,请重新输入");
				}
			}else{
				$.error("Key值与已有数据重复,请重新输入");
			}
			loader.close();
		});
	}
}

//图片
function change(obj){ 
	var fileForm = new FormData();
	fileForm.append("file", $(obj)[0].files[0]);
	console.log("${pageContext.session.id}");
	fileForm.append("jsessionId", "${pageContext.session.id}");
	uploadFile(fileForm);
}
function uploadFile(fileForm, id) {
	$.ajax({
		url: '${path}/uploader/common',
		//url: '${path}/file/upload',
	    type: 'POST',
	    cache: false,
	    data: fileForm,
	    processData: false,
	    contentType: false,
		success:function(result){
			if(result.state=="200"){
				showBusinessLicence(result.others, result.info, id);
				$("."+id).prev().find("label").hide();
				$("."+id).prev().find("input").removeClass("error");
				
			} else {
				//alert(result.err);
			}
			//console.log(result);
			var res = eval('(' + result + ')');
			//console.log(res);
			if(res.url == "undefined" || res.url == ""){
				//console.log(123);
			}else {
				console.log(res);
				var url = res.url;
				$("#icon").attr("value",res.uuid);
				$("#icon").attr("src",url);
				$("#upImg").attr("src",url)
			}
        }
	});
}


</script>
</body>
</html>