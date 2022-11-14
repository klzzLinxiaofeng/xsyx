<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新生注册</title>
<%@ include file="/views/embedded/common.jsp"%>
<!-- Load TinyMCE -->
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/jquery.tinymce.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/custom.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/ios-orientationchange-fix.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/plugin/uploadify/jquery.uploadify.min.js"></script>
<style>
input[type="radio"]{
	margin:0 5px;
	position:relative;
	top:-1px;
}
.form-horizontal{
	padding-bottom:0;
}
.xs_middle_1 .span1{
	width:70px;
}
.xs_middle_1 .span2{
	width:80px;
}
.xs_middle_1 .span3{
	width:100px;
}
</style>
<script>
	$(function() {
		/* text-editor */
		$('textarea.tinymce-simple').tinymce({
			script_url : '${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js',
			theme : "simple"
			});
		$(".xs_left_1 ul li a").click(function(){
			$(".xs_left_1 ul li").removeClass("on");
			$(this).parent().addClass("on");
			var i=$(this).parent().index();
			$(".xs_middle_1 .xsbj_div").hide();
			$(".xs_middle_1 .xsbj_div").eq(i).show();
		});
		$(".add_table").click(function(){
			var j=$(".xsbj_div .xs_table tbody>tr").length+1;
			$(".xsbj_div .xs_table tbody").append("<tr><td>" +j+ "</td><td><input type='text' class='span1' /></td><td><input type='text' class='span2' /></td><td><input type='text' class='span3' /></td><td><input type='text' class='span3' /></td><td><button type='button' class='btn btn-red'>删除</button></td></tr>")
		});
		
		//学生类别
	    var $requestData = {tc : "JY-XSLB","level":"1"};
		$.jcGcSelector("#studentType", $requestData, "${newStudent.studentType }", function() {
			
		});
		//证件类型
		$.jcGcSelector("#certificateType", {tc : "JY-SFZJLX"}, "${newStudent.idCardType }", function() {
			
		});
		//在读状态
		$.jcGcSelector("#state", {tc : "JY-XSDQZT"}, "${newStudent.readingState }", function() {
			
		});
		//国籍
		$.jcGcSelector("#nationality", {tc : "GB-GJ"}, "${newStudent.nationality }", function() {
			
		});
		//民族
		$.jcGcSelector("#nation", {tc : "GB-MZ"}, "${newStudent.race }", function() {
			
		});
		//户口性质
		$.jcGcSelector("#register", {tc : "GB-HKLB"}, "${newStudent.residenceType }", function() {
			
		});
		//政治面貌
		$.jcGcSelector("#political", {tc : "GB-ZZMM"}, "${newStudent.politicalStatus }", function() {
			
		});
		//宗教信仰
		$.jcGcSelector("#religiousBelief", {tc : "GB-ZJXY"}, "${newStudent.religion }", function() {
			
		});
		//性别
		$.jcGcSelector("#sex", {tc : "GB-XB"}, "${newStudent.sex }", function() {
			
		});
		
	});

	function saveOrUpdate(){
		var loader = new loadLayer();
		var $requestData = formData2JSONObj("#editNewStudentRegistForm");
		var url = "${pageContext.request.contextPath}/entrollStudent/newStudentRegist/updateNewStudentRegist";
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					$.success('保存成功');
					if(parent.core_iframe != null) {
							parent.core_iframe.window.location.reload();
						} else {
							parent.window.location.reload();
						}
					$.closeWindow();
				} else {
					$.error("保存失败");
				}
			}else{
				$.error("服务器异常");
			}
			loader.close();
		});
	}

</script>
</head>
<body style="background-color:#F3F3F3 !important">
	<div class="row-fluid" >
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<form class="form-horizontal tan_form" id="editNewStudentRegistForm" name="editNewStudentRegistForm" action="javascript:void(0);">
						<input type="hidden" id="id" name="id" value="${newStudent.id }" />
						<input type="hidden" id="studentId" name="studentId" value="${newStudent.studentId }" />
						<div class="xszc">
							<div class="xs_left_1">
								<ul>
									<li class="on"><a href="javascript:void(0)">基本信息</a></li>
									<li><a href="javascript:void(0)">戶籍信息</a></li>
									<li><a href="javascript:void(0)">常用信息</a></li>
<!-- 									<li><a href="javascript:void(0)">家庭成员</a></li> -->
									<li><a href="javascript:void(0)">备注</a></li>
								</ul>
							</div>
							<div class="xs_middle_1">
								<div class="xsbj_div" >
										<div class="control-group">
											<label class="control-label">姓名</label>
											<div class="controls">
												<input type="text" id="name" name="name" value="${newStudent.name }" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">英文名</label>
											<div class="controls">
												<input type="text" id="englishName" name="englishName" value="${newStudent.englishName }"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">用户名</label>
											<div class="controls">
												<input type="text" id="username" name="username"  value="${userDetailInfo.username }" readonly="readonly"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">性别</label>
											<div class="controls" style="padding-top:5px;">
												<select id="sex" name="sex"></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">学生类别</label>
											<div class="controls">
												<select id="studentType" name="studentType"></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">学籍号</label>
											<div class="controls">
												<input type="text" id="studentNum" name="studentNum" value="${newStudent.studentNum }" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">入学时间</label>
											<div class="controls">
												<input type="text" id="enterDate" name="enterDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${newStudent.entrollSchoolDate }"></fmt:formatDate>" placeholder="入学时间" onclick="WdatePicker();"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">在读状态</label>
											<div class="controls">
												<select name="state" id="state" disabled="disabled"></select>
											</div>
										</div>
<!-- 										<div class="control-group" style="margin-bottom:0"> -->
<!-- 											<label class="control-label">个人照片</label> -->
<!-- 											<div class="controls" > -->
<!-- 												<a href="javascript:void(0)" class="up_img"></a> -->
<!-- 											</div> -->
<!-- 										</div> -->
								</div>
								<div class="xsbj_div" style="display:none">
										<div class="control-group">
											<label class="control-label">出生年月</label>
											<div class="controls">
												<input type="text" id="birthDate" name="birthDate" value="<fmt:formatDate pattern="yyyy-MM-dd" value="${newStudent.birthDate }"></fmt:formatDate>"  placeholder="入学时间" onclick="WdatePicker();"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">证件类型</label>
											<div class="controls">
												<select name="certificateType" id="certificateType"></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">证件号码</label>
											<div class="controls">
												<input type="text" id="certificateNum" name="certificateNum"  value="${newStudent.idCardNumber }" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">国籍</label>
											<div class="controls" style="padding-top:5px;">
												<select name="nationality" id="nationality"></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">民族</label>
											<div class="controls">
												<select name="nation" id="nation"></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">籍贯</label>
											<div class="controls">
												<input type="text" id="nativePlace" name="nativePlace" value="${newStudent.nativePlace }"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">出生地</label>
											<div class="controls">
												<input type="text" id="birthPlace" name="birthPlace" value="${newStudent.birthPlace }"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">户口性质</label>
											<div class="controls">
												<select name="register" id="register"></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">户口所在地</label>
											<div class="controls">
												<input type="text" id="registerPlace" name="registerPlace" value="${userDetailInfo.registerPlace}"/>
											</div>
										</div>
										
										<div class="control-group">
											<label class="control-label">居住地址</label>
											<div class="controls">
												<input type="text" id="liveAddress" name="liveAddress" value="${newStudent.resideAddress} "/>
											</div>
										</div>
								</div>
								<div class="xsbj_div" style="display:none">
										<div class="control-group">
											<label class="control-label">特长</label>
											<div class="controls">
												<input type="text" id="specialty" name="specialty" value="${userDetailInfo.specialty}" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">政治面貌</label>
											<div class="controls">
												<select id="political" name="political"></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">宗教信仰</label>
											<div class="controls">
												<select name="religiousBelief" id="religiousBelief"></select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">是否独生子女</label>
											<div class="controls">
												<select name="isOnlyChild" id="isOnlyChild">
													<option value="1" <c:if test="${newStudent.isOnlyChild eq '1'}">selected</c:if>>是</option>
													<option value="0" <c:if test="${newStudent.isOnlyChild eq '0'}">selected</c:if>>否</option>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">电话</label>
											<div class="controls">
												<input type="text" id="telephone" name="telephone" value="${newStudent.telephone}"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">手机</label>
											<div class="controls">
												<input type="text" id="cellPhone" name="cellPhone" value="${userDetailInfo.cellPhone}"/>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label">邮件</label>
											<div class="controls">
												<input type="text" id="email" name="email" value="${newStudent.email}"/>
											</div>
										</div>
								</div>

								<div class="xsbj_div" style="display:none;">
									<textarea rows="5" id="remark" name="remark" cols="80" style="width: 100%" class="tinymce-simple" >
									${userDetailInfo.remark}
									</textarea>
								</div>
							</div>
							<div class="xs_right_1">
								<c:if test="${empty userDetailInfo.entityId}">
									<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" />
								</c:if>
								<c:if test="${not empty userDetailInfo.entityId}">
									<img src='<entity:getHttpUrl uuid="${userDetailInfo.entityId}"/>'/>
								</c:if>
								
								<p>姓名</p>
								<input type="text" disabled="disabled" value="${userDetailInfo.name }">
								<p>学籍号</p>
								<input type="text" disabled="disabled" value="${userDetailInfo.studentNum }">
							</div>
						</div>
						<div class="form-actions tan_bottom">
							<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">保存</button>
<!-- 							<button class="btn" type="button" onclick="saveOrUpdate();">取消</button> -->
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>