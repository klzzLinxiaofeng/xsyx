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
<title>报 修</title>
</head>
<body>
	<div class="container-fluid">
		<form class="form-horizontal" id="accept_form">
			<%--	已维修		--%>
			<c:choose>
				<c:when test="${status=='03' || status=='04'}">
						<div class="control-group">
							<label class="control-label"><i class="fa fa-user"></i>维修人</label>
							<div>
								<input type="text" name="accepterName"  disabled='disabled'
									class="span2" value="${AcceptRepari.accepterName}">
							</div>
						</div>
						<div class="control-group">
							<label class="control-label"><i class="fa fa-phone"></i>联系电话</label>
							<div>
								<input type="text" name="phone"  disabled='disabled'
									class="span2" value="${AcceptRepari.phone}">
							</div>
						</div>
				</c:when>
				<c:otherwise>
					<%--维修人员信息固定为当前登录用户--%>
					<input type="hidden" name="accepterName" value="${teacher.name}">
					<input type="hidden" name="phone" value="${teacher.mobile}">
				</c:otherwise>
			</c:choose>
			<div class="control-group">
				<label class="control-label"><i class="fa fa-user"></i>维修状态</label>
				<div>
					<div class="btn-group">
					<input id="state1" type="hidden" value="${status}">
						<select id="state" <c:if test="${status=='03' || status=='04'}">disabled="disabled"</c:if>>
							<option value="03" <c:if test="${status==03}">selected="selected"</c:if>>已处理</option>
							<option value="04" <c:if test="${status==04}">selected="selected"</c:if>>未修好</option>
						</select>
					</div>
				</div>
			</div>

			<div class="control-group">
				<label class="control-label"><i class="fa fa-comment-o"></i>维修说明</label>
				<div style="margin-left:100px;">
					<textarea rows="3" class="span6m {maxlength:100}" name="remark" <c:if test="${AcceptRepari.remark!=null}">disabled='disabled'</c:if> >${AcceptRepari.remark}</textarea>
				</div>
			</div>
				<div class="control-group">
					<label class="control-label"><i class="fa fa-comment-o"></i>是否耗材</label>
					<div class="controls" id="hao">
						<c:if test="${AcceptRepari.isHaoCai==null}">
							<label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
								<input type="radio" name="isHaoCai" value="0" Checked>
								否 </label>
							<label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
								<input type="radio" name="isHaoCai" value="1" >
								是 </label>
						</c:if>
						<c:if test="${AcceptRepari.isHaoCai!=null}">
							<label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
								<input type="radio" name="isHaoCai" value="0"
								<c:if test="${AcceptRepari.isHaoCai==0}"> Checked  </c:if>   <c:if test="${AcceptRepari.isHaoCai!=null}">disabled='disabled'</c:if>>
								否 </label>
							<label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
								<input type="radio" name="isHaoCai"  <c:if test="${AcceptRepari.isHaoCai!=null}"> Checked  </c:if>
									   value="1"  <c:if test="${!empty appConList}">disabled='disabled'</c:if> >
								是 </label>
						</c:if>

					</div>
				</div>
				<div class="control-group">
					<label class="control-label"><font style="color: red">*</font>
						维修图片：
					</label>
					<div class="controls">
						<div>
							<input type="hidden" id="pictureUuid" name="pictureUuid" value="${AcceptRepari.picture}">
						</div>
						<div id="zpa" style="display:inline-block;">
							<c:if test="${imgUrl!=null}">
								<div class="img_1"  style="margin: 5px;">
									<img style="width:233px;height:130px;" class="ims"  src="${imgUrl}"/>&nbsp;&nbsp;&nbsp;
								</div>
							</c:if>

						</div>

					</div>
					<div><span>支持jpg、gif、png、bmp格式，宽高3:4</span></div>
					<c:if test="${AcceptRepari.picture==null}">
						<input type="hidden" id="uploader" name="uploader" <c:if test="${imgUrl!=null}">disabled='disabled'</c:if>>
					</c:if>
					<span id="tp_queue"></span>

					<div class="clear"></div>
				</div>


				<input type="hidden" id="id" value="${AcceptRepari.id}" /> <input
				type="hidden" name="repariId" value="${applyId}" />
		</form>
		<p class="tc" style="padding-top: 8px; border-top: #ccc 1px solid;">
			<c:if test="${status=='01' || status=='02' || status==''}">
				<button class="btn btn-success" type="button"
					onclick="saveOrUpdate();">发布</button>
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
		//InputDisable();
		
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
	var ts = "已选择的老师："
	var teachName= "";
	function selectedHandler(data) {
	teachName= $("#teachName").text();
	ids = $("#teaId").val();
		$.each(data.ids, function(index, value) {
			if(ids.indexOf(value) == -1) {
				ids = value;
				teachName = data.names[index];
			}
		});
		$("#ts").text(ts);
		$("#teachName").text(teachName);
		$("#teaId").val(ids);
		$.success("设置成功");
		$.closeWindowByName(data.windowName);
	}
	
	//获取审核信息
	function getApprovalData(){
		var jsonStr = "";
		jsonStr +="[{id:'" + "${app.id}" + 
			"',teacherId:'" + $("#teaId").val() + 
			"',department:'" + "${app.department}" + 
			"',approvalResult:'" + "${app.approvalResult}" + 
			"',approvalExplain:'" + "${app.approvalExplain}" +
				"',picture:'" + $("#pictureUuid").val() +
				"',isHaoCai:'" + $('#hao input[name="isHaoCai"]:checked ').val() +
			"',approvalOrder:'1'}]";
		return jsonStr;
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var $requestData = formData2JSONObj("#accept_form");
			var tableData;
			$requestData.teacherId = $("#teaId").val();
			$requestData.jsonStr = getApprovalData();
			var mes = checkMes();
			
			if(mes != ""){
				$.alert(mes);
				return;
			}
			$
			$requestData.tableData = tableData2Json();
			
			var loader = new loadLayer();
			var $id = $("#id").val();
			var state = $("#state").val();
			
			$requestData.picture = $("#pictureUuid").val();
			if($requestData.picture == "undefined"){
				$requestData.picture = "";
			}
			var url = "${ctp}/oa/acceptrepari/creator?state="+state;
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/oa/acceptrepari/" + $id+"?state="+state;
			}
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
			'buttonText': '上传作业图片',
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

	/*//获取文件的缩略图路径
	function getFileUrls() {
		var imgs = $(".img_1");
		var urls = "";
		$.each(imgs, function(index, value) {
			urls += ($(value).find("img").attr("data-id") + ",");
		});
		if (urls != "" && urls != "undefined") {
			urls = urls.substring(0, urls.length - 1);
		}
		return urls;
	}
	
	function InputDisable(){
		var status1 = $("#state1").val();
		if(status1=='03' || status1=='04'){
			$(".form-horizontal").disable();
			$("#uploader").hide();
		}
	}
	*/
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