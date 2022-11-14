<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%--<%@ include file="/views/embedded/plugin/uploadify.jsp"%>--%>

	<link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
	<script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<%@include file="/views/embedded/plugin/dept_selector_js.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<link href="${pageContext.request.contextPath}/res/css/extra/my.css"
	rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/extra/add.js"></script>
<style type="text/css">
.form-horizontal .controls #zp .img_1 {
	margin: 10px;
	position: relative;
	top: 0;
	width: 233px;
	height: 130px;
}

.form-horizontal .controls #zp .img_1 img {
	width: 233px;
	height: 130px;
}

.form-horizontal .controls #zp .img_1 a {
	position: absolute;
	font-size: 22px;
	font-weight: bold;
	color: #000;
	right: 0px;
	top: 0px;
	display: block;
	width: 16px;
	height: 16px;
	line-height: 16px;
	text-align: center;
	cursor: pointer;
}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.table tr input[disabled]{
	border:0 none;
	background-color:#fff;
}
.table tr td,table tr th{
	padding-left:10px;
	padding-right:10px;
}
</style>
<title>已报 修</title>
</head>
<body>
	<div class="container-fluid">
			<div class="control-group">
				<label class="control-label"><i class="fa fa-user"></i>维修人<span style="color:red">*</span></label>
				<div>
					<input type="text" name="accepterName" placeholder="请输入维修人员" disabled="disabled"
						class="span2 left-stripe {required : true,maxlength:10}" value="${AcceptRepari.accepterName}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><i class="fa fa-phone"></i>联系电话<span style="color:red">*</span></label>
				<div>
					<input type="text" name="phone" placeholder="请输入维修人员电话" disabled="disabled"
						class="span2 left-stripe {required : true,isTel:true}" value="${AcceptRepari.phone}">
				</div>
			</div>
			<div class="control-group">
				<label class="control-label"><i class="fa fa-user"></i>维修状态</label>
				<div>
					<div class="btn-group">
					<input id="state1" type="hidden" value="${status}">
						<select id="state" disabled="disabled" <c:if test="${status=='03' || status=='04'}">disabled="disabled"</c:if>>
<%--							<option value="01" <c:if test="${status==01}">selected="selected"</c:if>>待处理</option>--%>
<%--							<option value="02" <c:if test="${status==02}">selected="selected"</c:if>>处理中</option>--%>
							<option value="03" <c:if test="${status==03}">selected="selected"</c:if>>已处理</option>
							<option value="04" <c:if test="${status==04}">selected="selected"</c:if>>未修好</option>
						</select>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><i class="fa fa-comment-o"></i>维修说明</label>
				<div>
					<textarea rows="3" class="span6m {maxlength:100}" name="remark" disabled="disabled">${AcceptRepari.remark}</textarea>
				</div>
			</div>
		<div class="control-group">
			<label class="control-label"><i class="fa fa-comment-o"></i>是否耗材</label>
			<div class="controls" id="hao">
				<label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
					<input type="radio" name="isHaoCai" value="0"
						   Checked  >
					否 </label>
				<label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
					<input type="radio" name="isHaoCai"
						   value="1" >
					是 </label>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label"><font style="color: red">*</font>
				保修图片：
			</label>
			<div class="controls">
				<div>
					<input type="hidden" id="pictureUuid" name="pictureId" value="${AcceptRepari.pictureId}">
				</div>
				<div id="zpa" style="display:inline-block;">

				</div>

			</div>
			<div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
			<c:if test="${isCK==''||isCK==null}">
				<input type="hidden" disabled id="uploader" name="uploader">
			</c:if>
			<span id="tp_queue"></span>

			<div class="clear"></div>
		</div>

		<p class="tc" style="padding-top: 8px; border-top: #ccc 1px solid;">
			<c:if test="${status=='01' || status=='02' || status==''}">
				<button disabled class="btn btn-success" type="button"
					onclick="saveOrUpdate();" >发布</button>
			</c:if>
		</p>
	</div>
</body>
<script type="text/javascript">


	var checker;
	$(function() {
		checker = initValidator();
		uploadFile();
		$("#state").chosen();
		InputDisable();
		
		//教师筛选
		$.createMemberSelector({
			"inputIdSelector" : "#member_selector",
			"ry_type" : "teach",
			"enableBatch" : false,
			"layerOp" : {
				"shift" : "top",
				type : 2
			}
		});
		
		//教师筛选
		$.createMemberSelector({
			"inputIdSelector" : "#member_selector2",
			"ry_type" : "teach",
			"enableBatch" : false,
			"layerOp" : {
				"shift" : "top",
				type : 2
			}
		});
	});
	
	function initValidator() {
		return $("#accept_form").validate({
			errorClass : "myerror",
			rules : {
				
			},
			messages : {
				
			}
		});
	}
	
	//清除教师信息
	function cleanT(){
		$("#ts").text("aaa");
		$("#teaId").val("aaa");
		$("#teachName").text("aa");
	}
	
	//已选择教师
	var ts2 = "已选择的老师："
	var teachName2= "";
	function selectedHandler(data) {
	teachName2= $("#teachName2").text();
	ids = $("#teaId2").val();
		$.each(data.ids, function(index, value) {
			if(ids.indexOf(value) == -1) {
				ids = value;
				teachName2 = data.names[index];
			}
		});
// 		$("#member_selector2 button").val(ts2);
		$("#member_selector2").attr('value','重新选择');
		$("#teachName2").text(teachName2);
		$("#teaId2").val(ids);
		$.success("设置成功");
		$.closeWindowByName(data.windowName);
	}
	
	//获取审核信息
	function getApprovalData(){
		var jsonStr = "[";
		jsonStr +="{id:'" + $("#id1").val() + 
			"',teacherId:'" + $("#teaId").val() + 
			"',department:'" + $("#department1").val() + 
			"',approvalResult:'" + $('#rad1 input[name="approvalResult1"]:checked ').val() +
			"',approvalExplain:'" + $("#approvalExplain1").val() +
				"',pictureId:'" + $("#pictureUuid").val() +
				"',isHaoCai:'" + $('#hao input[name="isHaoCai"]:checked ').val() +
				"',approvalOrder:'1'}"
		jsonStr +=",{id:'" + $("#id2").val() + 
				"',teacherId:'" + $("#teaId2").val() + 
				"',department:'" + $("#department2").val() + 
				"',approvalResult:'" + $('#rad2 input[name="approvalResult2"]:checked ').val() + 
				"',approvalExplain:'" + $("#approvalExplain2").val() +
				"',pictureId:'" + $("#pictureUuid").val() +
				"',isHaoCai:'" + $('#hao input[name="isHaoCai"]:checked ').val() +
				"',approvalOrder:'2'}"
		jsonStr += "]";
		return jsonStr;
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = {};
			$requestData.acceptId = $("#AcceptRepari").val();
			$requestData.jsonStr = getApprovalData();
			$requestData._method = "put";
			var url = "${ctp}/oa/acceptrepari/addApproval";
			
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	function Change(obj) {
		var imgSrc = $(obj).attr("src");
		window.open(imgSrc);
	}

	function reMove(obj) {
		$(obj).parent().remove();
		var id=obj.getAttribute("id");
		$("#pictureUuid").val("");
	}

	function uploadFile() {
		$('#uploader').uploadifive({
			'auto': true,
			'fileObjName' : 'file',
			//'queueID': 'queue',
			'buttonText': '上传维修图片',
			removeCompleted:true,
			formData: {
				'jsessionId': '<%=request.getSession().getId()%>'
			},
			'uploadScript': '/uploader/common',
			'onUploadComplete': function (file,data) {
				var $jsonObj = eval("(" + data + ")");
				var img = '<div class="img_1"  style="margin: 5px;"><a id="'
						+ $jsonObj.uuid
						+ '" onclick="reMove(this);">取消</a><img style="width:233px;height:130px;" class="ims" onclick="change(this);" src="'
						+ $jsonObj.url
						+ '"/>&nbsp;&nbsp;&nbsp;</div>';
				$("#pictureUuid").val($jsonObj.uuid);

				$("#zpa").append(img);
			},
			onUpload:function (file) { //上传开始时触发（每个文件触发一次）
				$("#infoBox").prev("p").css("display", "none");
				$("#infoBox").css("display", "block");
			},
			onFallback : function() {
				alert("该浏览器无法使用!");
			},
		});
	}
	
	function InputDisable(){
		var status1 = $("#state1").val();
		if(status1=='03' || status1=='04'){
			$(".form-horizontal").disable();
			$("#uploader").hide();
		}
	}
	
	//添加过保配件
	function addRep(){
		var mes = checkMes();
		if(mes != ""){
			$.alert(mes);
			return;
		}
		addHtml();
	}
	
	function addHtml(){
		var body = $("#tBody");
		var tHtml = "<tr>"
		tHtml += "<td><input name='repName' style='width:100%' /></td>";
		tHtml += "<td><input name='unit' style='width:100%' /></td>";
		tHtml += "<td><input name='number' style='width:100%' /></td>";
		tHtml += "<td><input type='button' value='移除' onclick='deleteWarranty(this)'/></td>"
		tHtml += "</tr>";
		body.append(tHtml);
	}
	
	//去出所有空格
	function Trim(str){
       var result;
       result = str.replace(/(^\s+)|(\s+$)/g,"");
       result = result.replace(/\s/g,"");
       return result;
	}
	
	//判断非空   格式  是否同名   在添加配件和保存时调用
	function checkMes(){
		var mes = "";
		var tab =  $("#tBody").find("tr");
	    for(var i = 0; i < tab.length; i++){
	    	
	    	var repName = tab.eq(i).find("td input[name='repName']").val();
	    	repName =  Trim(repName);
	    	var unit = tab.eq(i).find("td input[name='unit']").val();
	    	var number = tab.eq(i).find("td input[name='number']").val();
	    	
	    	var index = parseInt(i)+1;
	    	//非空
	    	if(repName == "" || repName == "undefined"){
	    		mes += "请输入第" + index + "行的设配名称,";
	    	}
	    	
	    	if(unit == "" || unit == "undefined"){
	    		mes += "请输入第" + index + "行的设配单位,";
	    	}
	    	
	    	if(number == "" || number == "undefined"){
	    		mes += "请输入第" + index + "行的设配数量,";
	    	}
	    	
	    	//格式判断
			if(isNaN(number) || parseInt(number) < 1){
				mes += "第" +index + "行不是有效数字"
			}
	    	
	    	//是否同名判断
	    	for(var j = parseInt(i)+1; j < tab.length; j++){
	    		var nextIndex = parseInt(j)+1;
	    		var nextRepName = tab.eq(j).find("td input[name='repName']").val();
	    		nextRepName =  Trim(nextRepName);
	    		if(nextRepName == repName){
	    			mes += "第" + index + "行与第" + nextIndex + "行同名，请合并成一行";
	    		}
	    	}
	    	
	    }
	    return mes;
	}
	
	function tableData2Json(){
		var tab =  $("#tBody").find("tr");
	    var jsonT = "[";
	    for(var i = 0; i < tab.length; i++){
	    	
	    	var repName = tab.eq(i).find("td input[name='repName']").val();
	    	repName =  Trim(repName);
	    	var unit = tab.eq(i).find("td input[name='unit']").val();
	    	var number = tab.eq(i).find("td input[name='number']").val();
	    	
	        if(i == 0){
	          jsonT +="{name:'" + repName + "',unit:'" + unit + "',number:'" + number + "'}"
	        }else{
	          jsonT +=",{name:'" + repName + "',unit:'" + unit + "',number:'" + number + "'}"
	        }
	    }
	    jsonT += "]";
	    return jsonT;
	}
	
	function deleteWarranty(obj){
		$(obj).parent().parent().remove();
		$.success("移除成功！");
	}
	
</script>
</html>