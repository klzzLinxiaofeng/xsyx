<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="${ctp}/res/css/uploadifive/uploadifive.css">
	<script src="${ctp}/res/js/uploadifive/jquery.uploadifive.min.js" type="text/javascript"></script>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
input[type="text"],select,textarea{margin-bottom:0;width:100%;}
input[type="text"],textarea{border:0 none;width:95%;}
table{background-color:#fff;}
table td img{width:155px;height:187px;}
table td .up_img {
    background: url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
    display: block;
    height: 30px;
    width: 98px;
    position:absolute;
    top:197px;
    left:30px;
}
.myerror {
		color: red !important;
		width: 34%;
		display: inline-block;
		padding-left: 10px;
	}
 .tan_bottom {
    background-color: transparent;
    background-image: linear-gradient(rgba(42, 118, 88, 0.9) 0px, rgba(42, 118, 88, 0.8) 100%);
    bottom: 0;
    margin-bottom: 0;
    min-height: 34px;
    padding: 10px 0;
    position: fixed;
    text-align: center;
    width: 100%;
}
table td img{position:relative;top:-20px;left:5px;}
.jsxx{
	background:#0093a8;
	height:45px;
	line-height:45px;
	margin-bottom: 15px;
}
.jsxx a{
	padding:0 20px;
	float:left;
	text-align:center;
	color:#fff;
	font-family: 微软雅黑;
	font-size: 18px;
	background:#0093a8;
	cursor:pointer;
}
.jsxx a.tianjia{
	color: #0093a8;
	background: #fff;
}
</style>
</head>
<body style="background-color:#F5F5F5">
	<div class="add_teacher">
		<p class="p1">修改教师</p>
		<p><span class="red">*</span>为必填项</p>
	</div>
	
	<div class="jsxx">
		<a hrft="javascript:void(0);" class="tianjia">基本信息</a>
		<a hrft="javascript:void(0);">辅助信息</a>
		<c:if test="${canShow == 'canShow'}">
			<a hrft="javascript:void(0);">工资明细</a>
		</c:if>
		<a hrft="javascript:void(0);">任教科目</a>
	</div>
	
	<form id="modifyTeacherForm" class="form-horizontal">
	<div class="jsnr">
	<input type="hidden" id="persionId" name="persionId" value="${teacherDetailInfo.persionId }" /> 
	<input type="hidden" id="userId" name="userId" value="${teacherDetailInfo.userId }" />
	<input type="hidden" id="teacherId" name="teacherId" value="${teacherDetailInfo.teacherId }" />
	<input type="hidden" id="schoolId" name="schoolId" value="${teacherDetailInfo.schoolId }">
	<table class="table table-bordered">
		<tbody>
			<tr>
				<!-- 别名 -->
				<input type="hidden" id="alias" name="alias" value="${teacherDetailInfo.alias }" placeholder="别名"/>
				<td><span class="red">*</span>姓名</td>
				<td><input type="text" id="name" name="name"  value="${teacherDetailInfo.name }" data-id="" placeholder="姓名"/></td>
				<td>英文名</td>
				<td><input type="text" id="englishName" name="englishName" value="${teacherDetailInfo.englishName }" data-id="" placeholder="英文名"/></td>
				<td rowspan="6" style="width:165px;position:relative">
<%-- 					<img src="<entity:getHttpUrl uuid="${teacherDetailInfo.entityId}"/>" id="imgId"/> --%>
					<c:choose>
						<c:when test="${not empty teacherDetailInfo.photoUuid}">
							<img src="<entity:getHttpUrl uuid='${teacherDetailInfo.photoUuid}'/>" id="imgId"/>
						</c:when>
						<c:otherwise>
							<img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg" id="imgId">
						</c:otherwise>
					</c:choose>
					<a href="javascript:void(0)" class="up_img"></a>
<!-- 					<input type="file" id="uploader" name="uploader"/> -->
<%-- 					<input type="hidden" id="entityId" name="entityId" value="${teacherDetailInfo.entityId}"/> --%>
					<input type="hidden" id="photoUuid" name="photoUuid" value="${teacherDetailInfo.photoUuid}"/>
				</td>
			</tr>
			<tr>
				<td>性别</td>
				<td>
					<select id="sex" name="sex"></select>
				</td>
				<td>出生日期</td>
				<td><input type="text" id="birthDate" name="birthDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${teacherDetailInfo.birthDate}"></fmt:formatDate>' onclick="WdatePicker();" data-id="" placeholder="出生日期"/></td>
			</tr>
			<tr>
				<td><span class="red">*</span>工号</td>
				<td><input type="text" id="jobNumber" name="jobNumber" value="${teacherDetailInfo.jobNumber }" data-id="" placeholder="工号"/></td>
				<td><!-- <span class="red">*</span> -->证件类型</td>
				<td><select id="certificateType" name="certificateType">
						
				    </select>
				</td>
			</tr>
			<tr>
				<td><span class="red">*</span>手机号码</td>
				<td><input type="text" id="mobile" name="mobile"  value="${teacherDetailInfo.mobile }" data-id="" placeholder="手机号码"/></td>
				<td><!-- <span class="red">*</span> -->证件号码</td>
				<td><input type="text" id="certificateNum" name="certificateNum"  value="${teacherDetailInfo.certificateNum }" data-id="" placeholder="证件号码"/></td>
			</tr>
			<tr>
				<td>参加工作时间</td>
				<td><input type="text" id="workBeginDate" name="workBeginDate" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${teacherDetailInfo.workBeginDate}"></fmt:formatDate>' onclick="WdatePicker();" data-id="" placeholder=""/></td>
				<td>国籍</td>
				<td>
					<select id="nationality"  name="nationality"></select>
				</td>
			</tr>
			<tr>
				<td>入职时间</td><td><input type="text" id="enrollDate" name="enrollDate" onclick="WdatePicker();" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${teacherDetailInfo.enrollDate}"></fmt:formatDate>' data-id="" placeholder=""/></td>
				<td>民族</td>
				<td>
					<select id="nation" name="nation">
					</select>
				</td>
			</tr>
			<tr>
				<td>离职时间</td>
				<td><input type="text" id="leaveDate" name="leaveDate" onclick="WdatePicker();" value='<fmt:formatDate pattern="yyyy-MM-dd" value="${teacherDetailInfo.leaveDate}"></fmt:formatDate>' data-id="" placeholder=""/></td>
				<td>籍贯</td>
				<td colspan="2"><input type="text" id="nativePlace" name="nativePlace" value="${teacherDetailInfo.nativePlace }" data-id="" placeholder="籍贯"/></td>
			</tr>
			<tr>
				<td>职务</td>
				<td><input type="text" id="position" name="position" value="${teacherDetailInfo.position }" data-id="" placeholder="职务"/></td>
				<td>出生地</td>
				<td colspan="2"><input type="text" id="birthPlace" name="birthPlace" value="${teacherDetailInfo.birthPlace }" data-id="" placeholder="出生地"/></td>
			</tr>
			<tr>
				<td>岗位职业</td>
				<td>
					<select id="occupationCode" name="occupationCode">
					</select>
				</td>
				<td>婚姻状况</td>
				<td colspan="2">
					<select id="marriage" name="marriage">
					</select>
				</td>
			</tr>
			<tr>
				<td>学历</td>
				<td>
					<select id="qualification" name="qualification">
					</select>
				</td>
				<td>政治面貌</td>
				<td colspan="2">
					<select id="political" name="political">
					</select>
				</td>
			</tr>
			<tr>
				<td>特长</td>
				<td><input type="text" id="specialty" name="specialty" value="${teacherDetailInfo.specialty }" data-id="" placeholder="特长"/></td>
				<td>宗教信仰</td>
				<td colspan="2">
					<select id="religiousBelief" name="religiousBelief">
					</select>
				</td>
			</tr>
			<tr>
				<td>岗位编制</td>
				<td><select id="postStaffing" name="postStaffing">
<!-- 						<option value="0">无</option> -->
<!-- 						<option value="1">有</option> -->
					</select>
				</td>
				<td>户口性质</td>
				<td colspan="2">
					<select id="register" name="register">
					</select>
				</td>
			</tr>
			<tr>
				<td>在职状态</td>
				<td><select id="jobState" name="jobState">
					</select>
				</td>
				<td>户口所在地</td>
				<td colspan="2"><input type="text" id="registerPlace" name="registerPlace" value="${teacherDetailInfo.registerPlace }" data-id="" placeholder="户口所在地"/></td>
			</tr>
			<tr>
				<td>办公室电话</td>
				<td><input type="text" id="telephone" name="telephone" value="${teacherDetailInfo.telephone }" data-id="" placeholder="办公室电话"/></td>
				<td>现地址</td>
				<td colspan="2"><input type="text" id="nowAddress" name="nowAddress"  value="${teacherDetailInfo.nowAddress }" data-id="" placeholder="现地址"/></td>
			</tr>
			<tr>
				<td>邮件</td>
				<td><input type="text" id="email" name="email"  value="${teacherDetailInfo.email }" data-id="" placeholder="邮件"/></td>
				<td>居住地址</td>
				<td colspan="2"><input type="text" id="liveAddress" name="liveAddress" value="${teacherDetailInfo.liveAddress }" data-id="" placeholder="居住地址"/></td>
			</tr>
			<tr>
				<td>备注</td>
				<td colspan="4"><textarea id="remark" name="remark">${teacherDetailInfo.remark }</textarea></td>
			</tr>
		</tbody>
	  </table>
	  </div>
	   <!-- 2016-12-08 新增教师扩展信息 -->
	 <div class="jsnr">
	 	<table class="table table-bordered">
		<tbody>
			<tr>
				<td>进入单位原因</td>
				<td><input type="text" class="{maxsize:100}" id="joinReason" name="joinReason"  value="${teacherAssist.joinReason}" data-id="" placeholder="请填写进入单位原因"/></td>
				<td>学位</td>
				<td><input type="text" class="{maxsize:10}" id="degree" name="degree" value="${teacherAssist.degree}" data-id="" placeholder="请填写学位"/></td>
			</tr>
			<tr>
				<td>身份</td>
				<td>
					<input type="text" class="{maxsize:10}" id="identity" name="identity" value="${teacherAssist.identity}" data-id="" placeholder="请填写身份"/>
				</td>
				<td>实任职务</td>
				<td><input type="text" class="{maxsize:10}" id="actualPosition" name="actualPosition" value="${teacherAssist.actualPosition}" data-id="" placeholder="请填写实任职务"/></td>
			</tr>
			<tr>
				<td>岗位类型</td>
				<td><input type="text" class="{maxsize:10}" id="postType" name="postType" value="${teacherAssist.postType}" data-id="" placeholder="请填写岗位类型"/></td>
				<td>工资待遇</td>
				<td><input type="text"  class="{maxsize:10}" id="wages" name="wages" value="${teacherAssist.wages}" data-id="" placeholder="请填写工资待遇"/>
				</td>
			</tr>
			<tr>
				<td>管理方式</td>
				<td><input type="text" class="{maxsize:10}" id="managementType" name="managementType"  value="${teacherAssist.managementType}" data-id="" placeholder="请填写管理方式"/></td>
				<td>当前职务任命时间</td>
				<td><input onfocus="WdatePicker({maxDate:'#F{$dp.$D(\'currentPositionPeriod\')}',dateFmt:'yyyy-MM'});" type="text" id="currentPositionDate" name="currentPositionDate" value= '<fmt:formatDate pattern="yyyy-MM" value="${teacherAssist.currentPositionDate}"></fmt:formatDate>'  data-id="" placeholder="请填写当前职务任命时间"/></td>
			</tr>
			<tr>
				<td>人员经费形式</td>
				<td><input type="text" class="{maxsize:10}" id="personnelFundsType" name="personnelFundsType" value="${teacherAssist.personnelFundsType}" data-id="" placeholder="请填写人员经费形式"/></td>
				<td>当前职务任命期限</td>
				<td>
					<input onfocus="WdatePicker({minDate:'#F{$dp.$D(\'currentPositionDate\',{m:+5})}',dateFmt:'yyyy-MM'});" type="text" id="currentPositionPeriod" name="currentPositionPeriod" value= '<fmt:formatDate pattern="yyyy-MM" value="${teacherAssist.currentPositionPeriod}"></fmt:formatDate>'  data-id="" placeholder="请填写当前职务任命期限"/>
				</td>
			</tr>
			<tr>
				<td>入党时间</td>
				<td><input type="text" id="joinPartyDate" name="joinPartyDate" onclick="WdatePicker({dateFmt:'yyyy-MM'});" value='<fmt:formatDate pattern="yyyy-MM" value="${teacherAssist.joinPartyDate}"></fmt:formatDate>' data-id="" placeholder="请填写入党时间"/>
				</td>
				<td>第一职务</td>
				<td>
					<input type="text"  class="{maxsize:10}" id="lowPost" name="lowPost" value="${teacherAssist.lowPost}" data-id="" placeholder="请填写第一职务"/>
				</td>
			</tr>
			<tr>
				<td>考核中断</td>
				<td><input type="text" class="{maxsize:10}" id="checkInterrupt" name="checkInterrupt" value="${teacherAssist.checkInterrupt}" data-id="" placeholder="请填写考核中断"/></td>
				<td>第一职务任命时间</td>
				<td colspan="2"><input type="text" id="lowPostDate" name="lowPostDate" onfocus="WdatePicker({dateFmt:'yyyy-MM'});" value='<fmt:formatDate pattern="yyyy-MM" value="${teacherAssist.lowPostDate}"></fmt:formatDate>' data-id="" placeholder="请填第一职务任命时间"/></td>
			</tr>
			<tr>
				<td>中断工龄</td>
				<td><input type="text" class="{maxsize:2,number:true}" id="interruptworkYears" name="interruptworkYears" value="${teacherAssist.interruptworkYears}" data-id="" placeholder="请填写中断工龄"/></td>
				<td>干部职务名称</td>
				<td colspan="2"><input type="text" class="{maxlength:10}" id="cadrePosts" name="cadrePosts" value="${teacherAssist.cadrePosts}" data-id="" placeholder="请填写干部职务名称"/></td>
			</tr>
			<tr>
				<td>工龄</td>
				<td>
					<input type="text" class="{maxsize:2,number:true}" id="workYears" name="workYears" value="${teacherAssist.workYears}" data-id="" placeholder="请填写工龄"/>
				</td>
				<td>职级分类</td>
				<td colspan="2">
					<input type="text" class="{maxsize:10}" id="rankType" name="rankType" value="${teacherAssist.rankType}" data-id="" placeholder="请填写职级分类"/>
				</td>
			</tr>
			<tr>
				<td>应计学龄</td>
				<td>
					<input type="text" class="{maxsize:2,number:true}" id="accruedAge" name="accruedAge" value="${teacherAssist.accruedAge}" data-id="" placeholder="请填写应计学龄"/>
				</td>
				<td>职级变动时间</td>
				<td colspan="2">
					<input type="text" id="rankChangeDate" name="rankChangeDate" onfocus="WdatePicker({dateFmt:'yyyy-MM'});" value='<fmt:formatDate pattern="yyyy-MM" value="${teacherAssist.rankChangeDate}"></fmt:formatDate>' data-id="" placeholder="请填写职级变动时间"/>
				</td>
			</tr>
			<tr>
				<td>第一学历</td>
				<td><input type="text" class="{maxsize:20}" id="firstDegree" name="firstDegree" value="${teacherAssist.firstDegree}" data-id="" placeholder="请填写第一学历"/></td>
				<td>专业技术职务</td>
				<td colspan="2">
					<input type="text" class="{maxsize:20}" id="technicalPosition" name="technicalPosition" value="${teacherAssist.technicalPosition}" data-id="" placeholder="请填写专业技术职务"/>
				</td>
			</tr>
			<tr>
				<td>第一学历毕业院校</td>
				<td><input type="text" class="{maxsize:50}" id="fristDegreeGraduateSchool" name="fristDegreeGraduateSchool" value="${teacherAssist.fristDegreeGraduateSchool}" data-id="" placeholder="请填写第一学历毕业院校"/>
				</td>
				<td>非领导标志</td>
				<td colspan="2">
					<input type="text" class="{maxsize:10}" id="nonLeadershipFlag" name="nonLeadershipFlag" value="${teacherAssist.nonLeadershipFlag}" data-id="" placeholder="请填写非领导标志"/>
				</td>
			</tr>
			<tr>
				<td>第一学历所学专业</td>
				<td><input type="text" class="{maxsize:50}" id="firstDegreeMajor" name="firstDegreeMajor" value="${teacherAssist.firstDegreeMajor}" data-id="" placeholder="请填写第一学历所学专业"/>
				</td>
				<td>职务岗位</td>
				<td colspan="2"><input type="text" class="{maxlength:20}" id="post" name="post" value="${teacherAssist.post}" data-id="" placeholder="请填写职务岗位"/></td>
			</tr>
			<tr>
				<td>最高学历</td>
				<td><input type="text" class="{maxsize:10}" id="highestDegree" name="highestDegree" value="${teacherAssist.highestDegree}" data-id="" placeholder="请填写最高学历"/></td>
				<td>考核结果</td>
				<td colspan="2">
					<select id="examinationResult" name="examinationResult">
						<option value="">请选择</option>
						<option value="0" <c:if test="${teacherAssist.examinationResult == 0}">selected="selected"</c:if>>优秀</option>
						<option value="1" <c:if test="${teacherAssist.examinationResult == 1}">selected="selected"</c:if>>称职</option>
						<option value="2" <c:if test="${teacherAssist.examinationResult == 2}">selected="selected"</c:if>>基本称职</option>
						<option value="3" <c:if test="${teacherAssist.examinationResult == 3}">selected="selected"</c:if>>不称职</option>
						<option value="4" <c:if test="${teacherAssist.examinationResult == 4}">selected="selected"</c:if>>不确定等次</option>
					</select>
				</td>
			</tr>
		</tbody>
	  </table>
	 </div>
	 
	  <!-- 2017-01-16 新增工资明细 -->
	 <div class="jsnr" style="margin-right: 3px;">
	 	<table class="table table-bordered">
		<tbody>
			
			
			
			<c:forEach  items="${teacherDetailInfo.salaryDetail}" var="item" varStatus="status" step="1">
				<tr>
					<td>${item.attrName1}</td>
					<td><b type="text" class="{maxsize:10}" <%-- value="" data-id="" placeholder="${item.value1}" --%>>${item.value1}</b></td>
					<td>${item.attrName2}</td>
					<td><b type="text" class="{maxsize:10}" <%-- value="" data-id="" placeholder="${item.value2}" --%>>${item.value2}</b></td>
				</tr>
			</c:forEach>
			
			
			<c:choose>
				<c:when test="${teacherDetailInfo.salaryDate != null}">
					<tr>
					<p>注:当前信息为${teacherDetailInfo.salaryDate}薪资信息</p>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
					<p>注:薪资信息不存在</p>
					</tr>
				</c:otherwise>
			</c:choose>
			
		</tbody>
	  </table>
	 </div>
		<!-- 2022-10-26 新增教师科目任职 -->
		<div class="jsnr" style="margin-right: 3px;">
			<button onclick="add('${teacherDetailInfo.teacherId}');" type="button" class="btn btn-green">添加科目</button>
			<table class="table table-bordered">
				<thead>
				<tr>
					<th>任课班级</th>
					<th>任课科目</th>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach  items="${teamTeachers}" var="item" varStatus="status" step="1">
					<tr id="${item.id}_tr">
						<td>${item.teamName}</td>
						<td>${item.subjectName}</td>
						<td><button onclick="deleteTeacher('${item.id}');" type="button" class="btn btn-red">删除</button></td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</div>
	</form>
	 <div class="form-actions tan_bottom">
		<button class="btn btn-warning" href="javascript:void(0)" onclick="saveTeacherInfo();">保存</button>
<!-- 		<button class="btn btn-warning">取消</button> -->
	</div> 
</body>
<script type="text/javascript">
	var checker="";
	function cancel(){
		$.closeWindow();
	}
	$.createAvartarEditor({
		"btn" : "#imgId,.up_img",
		"avatarSize" : "208,208",
		"avatarSizeLabel" : "208x208"
	});
	
	function selectedImgHandler(data) {
		$("#imgId").attr("src", data.imgUrl);
		$("#photoUuid").val(data.uuid)
	}
	
	$(function(){
		
		$(".jsnr").hide();//隐藏wenben
	    $(".jsnr:eq(0)").show();//显示第一个wenben
	    $(".jsxx a").click(function(){
	        $(".jsxx a").removeClass("tianjia");//移除样式
	        $(this).addClass("tianjia");//添加样式
	        var i=$(this).index();//获得下标
	        $(".jsnr").hide();//隐藏wenben
	        $(".jsnr:eq("+i+")").show();//显示第i个wenben
	    });
	    
		$("table tr").each(function(){ 
			$(this).children("td:eq(0),td:eq(2)").css({"background-color":"#F4F4F4","text-align":"right","padding-right":"10px"});
		}); 
		
		//学历
		$.jcGcSelector("#qualification", {tc : "JY-XL"}, "${teacherDetailInfo.qualification }", function() {
			
		});
		//在职状态
		$.jcGcSelector("#jobState", {tc : "JY-JZGDQZT"}, "${teacherDetailInfo.jobState }", function() {
			
		});
		//国籍
		$.jcGcSelector("#nationality", {tc : "GB-GJ"}, "${teacherDetailInfo.nationality }", function() {
			
		});
		//民族
		$.jcGcSelector("#nation", {tc : "GB-MZ"}, "${teacherDetailInfo.nation }", function() {
			
		});
		//户口性质
		$.jcGcSelector("#register", {tc : "GB-HKLB"}, "${teacherDetailInfo.register }", function() {
			
		});
		//政治面貌
		$.jcGcSelector("#political", {tc : "GB-ZZMM"}, "${teacherDetailInfo.political }", function() {
			
		});
		//宗教信仰
		$.jcGcSelector("#religiousBelief", {tc : "GB-ZJXY"}, "${teacherDetailInfo.religiousBelief }", function() {
			
		});
		//性别
		$.jcGcSelector("#sex", {tc : "GB-XB"}, "${teacherDetailInfo.sex }", function() {
			
		});
		//婚姻状况
		$.jcGcSelector("#marriage", {tc : "GB-HYZK"}, "${teacherDetailInfo.marriage }", function() {
			
		});
		
		//证件类型
		$.jcGcSelector("#certificateType", {tc : "JY-SFZJLX"}, "${teacherDetailInfo.certificateType }", function() {
			
		});
		
		//岗位职业
		$.jcGcSelector("#occupationCode", {tc : "JY-GWZY"}, "${teacherDetailInfo.occupationCode }", function() {
			
		});
		
		//岗位编制
		$.jcGcSelector("#postStaffing", {tc : "JY-BZLB"}, "${teacherDetailInfo.postStaffing }", function() {
			
		});
		uploadImageFile();
		checker =  initValidator();
		
	});
	
	function uploadImageFile(){
		$('#uploader').uploadifive({
			'auto': true,
			'fileObjName' : 'file',
			//'queueID': 'queue',
			'buttonText': '图片上传',
			'removeCompleted':true,
			'fileSizeLimit': 4 * 1024,
			'height' : 20,
			'width' : 66,
			'formData': {
				'jsessionId': '<%=request.getSession().getId()%>'
			},
			'uploadScript': '/uploader/common',
			'onUploadComplete': function (file,data) {
				var $jsonObj = eval("(" + data + ")");
				$("#photoUuid").val($jsonObj.uuid);
				$("#imgId").attr('src',$jsonObj.url);
			},
			onUpload:function (file) { //上传开始时触发（每个文件触发一次）
				$("#infoBox").prev("p").css("display", "none");
				$("#infoBox").css("display", "block");
			},
			onFallback : function() {
				alert("该浏览器无法使用!");
			},
		});




		/*var obj = $("#uploader").uploadify({
            swf: '${pageContext.request.contextPath}/res/js/common/plugin/uploadify/uploadify.swf',
            uploader: '${pageContext.request.contextPath}/uploader/common',
            formData: {'jsessionId': '<%=request.getSession().getId()%>'},
            fileObjName: 'file',
            fileTypeDesc: "图片上传",
            fileTypeExts: "*.jpg",
            method: 'post',
            multi: false, // 是否能选择多个文件
            auto: true, //Set to false if you do not want the files to automatically upload when they are added to the queue.
            removeTimeout: 1,
            queueSizeLimit: 1,
            fileSizeLimit: 4 * 1024,
            buttonText: "图片上传",
            requeueErrors: false, //If set to true, files that return errors during an upload are requeued and the upload is repeatedly tried.
            height: 20,
            width: 66,
            onUploadSuccess: function(file, data, response) {
           	  	var $jsonObj = eval("(" + data + ")");
           	 	$("#photoUuid").val($jsonObj.uuid);
           	 	$("#imgId").attr('src',$jsonObj.url); 
            },
            onUploadStart: function(file) { //上传开始时触发（每个文件触发一次）
                $("#infoBox").prev("p").css("display", "none");
                $("#infoBox").css("display", "block");
            },
            onUploadError: function(file, errorCode, errorMsg, errorString) {
            	$.alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
            }
        });
	*/
	}
	
	function saveTeacherInfo(){
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = formData2JSONObj("#modifyTeacherForm");
			
			currentPositionDate = $requestData.currentPositionDate;
			currentPositionPeriod = $requestData.currentPositionPeriod;
			joinPartyDate = $requestData.joinPartyDate;
			lowPostDate = $requestData.lowPostDate;
			rankChangeDate = $requestData.rankChangeDate;
			$requestData.name = $("#name").val();
			$requestData.mobile = $("#mobile").val();
			var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/; 
			if(currentPositionDate != "" && currentPositionDate != "undefined" && !currentPositionDate.match(reg)){
				$requestData.currentPositionDate = currentPositionDate + "-01";
			}
			if(currentPositionPeriod != "" && currentPositionPeriod != "undefined" && !currentPositionPeriod.match(reg)){
				$requestData.currentPositionPeriod = currentPositionPeriod + "-01";
			}
			if(joinPartyDate != "" && joinPartyDate != "undefined" && !joinPartyDate.match(reg)){
				$requestData.joinPartyDate = joinPartyDate + "-01";
			}
			if(lowPostDate != "" && lowPostDate != "undefined" && !lowPostDate.match(reg)){
				$requestData.lowPostDate = lowPostDate + "-01";
			}
			if(rankChangeDate != "" && rankChangeDate != "undefined" && !rankChangeDate.match(reg)){
				$requestData.rankChangeDate = rankChangeDate + "-01";
			}
			
	    	var url = "${pageContext.request.contextPath}/teach/teacher/updateTeacher";
	    	loader.show();
	    	$.post(url, $requestData, function(data, status) {
	    		if("success" === status) {
	    			data = eval("(" + data + ")");
	    			if("success" === data.info) {
	    				$.success("保存成功");
	    				if($requestData.jobState!="11"){
							$.confirm("当前教师已成功离职，请去海康门禁系统与食堂系统进行相应的离职操作", function() {
								if(parent.core_iframe != null) {
									parent.core_iframe.window.location.reload();
								} else {
									parent.window.location.reload();
								}
								$.closeWindow();
							});
						}else{
							if(parent.core_iframe != null) {
								parent.core_iframe.window.location.reload();
							} else {
								parent.window.location.reload();
							}
							$.closeWindow();
						}


	    			} else {
// 	    				$.error("保存失败");
	    				$.error(data.info);
	    			}
	    		}else{
	    			$.error("服务器异常");
	    		}
	    		loader.close();
	    	});
		}
	}
	
	function checkDate() {
		var result = true;
		var currentPositionDate = $("#currentPositionDate").val();
		var currentPositionPeriod = $("#currentPositionPeriod").val();
		if ( currentPositionDate !=="" && currentPositionPeriod !=="" ) {
			var currentDate = new Date(Date.parse(currentPositionDate.replace("-","/")));
			var currentPeriod = new Date(Date.parse(currentPositionPeriod.replace("-","/")));
			if ( currentDate > currentPeriod ) {
				result = false;
			}
		}
		return result;
	}
	
	function initValidator() {
    	return $("#modifyTeacherForm").validate({
				errorClass : "myerror",
				rules : {
					"name" : {
						required : true,
						minlength : 1,
						maxlength : 20,
						chinese:true,
						remote : {
							async : false,
							type : "POST",
							url : "${pageContext.request.contextPath}/teach/teacher/checker",
							data : {
								'dxlx' : 'name',
								'name' : function() {
									return $("#name").val();
								},
								'id' : function() {
									return $("#teacherId").val();
								}
							}
						}
					},
					"englishName" : {
						required : false,
						isEnglish:true
					},
					"mobile":{
						required : true,
						mobile:true,
						remote : {
							async : false,
							type : "GET",
							url : "${pageContext.request.contextPath}/teach/teacher/mobileChecker",
							data : {
								'dxlx' : 'mobile',
								'mobile' : function() {
									return $("#mobile").val();
								},
								'id' : function() {
									return $("#teacherId").val();
								}
							}
						}
					},
					"currentPositionDate":{
						
					},
					"jobNumber" : {
						required : true,
						maxlength : 10
					}
					
// 					"certificateNum":{
// 						required : true
// 					}
				},
				messages : {
					"name":{
						required:"名字不允许空",
						chinese:"只能为中文",
						remote : "教师姓名已存在"
					},
					"englishName":{
						isEnglish:"必须是英文"
					},
					"mobile":{
						mobile:"请输入合法的移动电话",
						remote : "手机号已存在"
					},
					"currentPositionDate":{
						currentPositionDate:"任命时间不能晚于任命期限"
					},
					"jobNumber":{
						maxlength : "不能超过10个字符"
					}
				}
			});
    	}
	// 	删除对话框
	function deleteTeacher(id) {
		$.confirm("确定执行此次操作？", function () {
			shanchu(id);
		});
	}
	function shanchu(id) {
		$.get("/teach/teacher/delete?teamTeacherId="+id,function (data) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
		})
	}

	function add(id) {
		$.initWinOnTopFromLeft('添加教师任课科目', '/teach/teacher/inputview?id='+id, '900', '650');
	}
</script>
</html>
 