<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/engine.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/util.js"></script>  
<script type="text/javascript" src="${pageContext.request.contextPath}/dwr/interface/schoolInitDwr.js"></script>  
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title></title>
</head>
<body style="background-color: #e3e3e3;">
	<div class="sjcsh_xx_detail" style="background: #ffffff;padding: 0;border: none;margin: 0 20px 20px;">
		<p class="zhuyi">注：姓名相同的教师，一定要输入别名进行区分。会影响到任课安排的区分。</p>
		<div class="begin"> 
			<a href="javascript:void(0)" class="importTeacher" style="position:relative;">
				<p class="p1"><span class="drjs"></span></p>
				<p class="p2" style="margin-bottom: 13px;">导入教师</p>
				<input type="file" id="teacher" name="teacher" style="width:180px;height:180px;position:absolute;top:0;left:0;opacity:0" onchange="updateAndImport()">
			</a>
			<a href="${pageContext.request.contextPath}/res/excel/teacher_template.xls" class="jsdrmb">教师导入模板.xls</a>
		</div>
		<c:if test="${!empty value }">
			<p class="lsdrjl">
			<span><fmt:formatDate value="${value.data }" pattern="yyyy-MM-dd HH:mm"/></span>有一份导入记录 
			<a href="${pageContext.request.contextPath}/tmp/teacher/index">点击查看</a></p>
		</c:if>
	</div>
	<div class="tjing" style="display:none">
		<p class="word"></p>
		<p class="word1">正在导入，请稍等...</p>
		<div class="jdt_tu">
			<p style="width:0%"></p>
			<span>0%</span>
		</div>
	</div>
	<div class="tis_import_teacher" style="display:none;text-align: center;padding-top:36px;">
		<p>教师信息 <span>285</span>条</p>
		<p>警告数据 <span style="color: #fda32f;">10</span>条</p>
		<p>错误数据 <span style="color:#ff5252">10</span>条</p>
	</div>
	<div class="tis_import_student_parents" style="display:none;text-align: center;padding-top:36px;">
		<p style="color:#ff5252">无法导入教师信息.xls文件</p>
		<p style="color:#ff5252">请确定导入文件是否正确</p>
	</div>
	<div class="wjgs" style="display:none;text-align: center;padding-top:36px;">
		<p style="color:#ff5252">文件仅限于xls、xlsx格式</p>
		<p style="color:#ff5252">请确定导入文件是否正确</p>
	</div>
<script>
var userid = "${userid}";
var schoolid = "${schoolid}";
var schoolYear = "${schoolYear}";

var timer = null;
schoolInitDwr.onPageLoad(userid);

var incomplete = "Incomplete reply from server";
dwr.engine._errorHandler = function(message, ex) {
	if(incomplete==message) {
		console.log("连接中断......");
	}
};

function updateAndImport(){
	var file=dwr.util.getValue("teacher");
	
	if(!fileCheck()) {
		fileError();
		return false;
	}
	
	openProcess();
	schoolInitDwr.importTeacherByFile(file, file.value, schoolid, schoolYear, userid, "showMessage", function(result) {
		var obj = document.getElementById("teacher") ;   
		obj.outerHTML = obj.outerHTML; //重新初始化了file的html
		window.clearInterval(timer);
		var data = JSON.parse(result);
		var status = data.status;
		if(status=="EXCEL_HEADER_ERROR" || status=="FILE_SUFFIX_ERROR" || "EXCEL_DATA_NULL"==status) {
			layer.closeAll();
			errorInfo();
		} else if(status=="success") {
			successInfo(data.successCount, data.warnCount, data.errorCount);	
		}
	});
}

function fileError() {
	layer.open({
		type: 1,
		shade:  [0.01, '#fff'],
		shadeClose : true,
		offset: '20px',
		area: ['337px', '191px'],
		title: '提示', //不显示标题
		content: $('.wjgs'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		cancel: function(){
		  layer.close();
		},
		btn: ['确定'],//按钮
		btn1: function(index, layero){
		}
	});
	$('#teacher').val('')
}

function fileCheck() {
	var filepath = $("input[name='teacher']").val();
	var extStart = filepath.lastIndexOf(".");
	var ext = filepath.substring(extStart, filepath.length).toLowerCase();
	if (ext != ".xls" && ext != ".xlsx" ) {
		return false;
	} else {
		return true;
	}
}


function successInfo(successCount, warnCount, errorCount) {
	$(".tis_import_teacher").find("span").eq(0).text(successCount);
	$(".tis_import_teacher").find("span").eq(1).text(warnCount);
	$(".tis_import_teacher").find("span").eq(2).text(errorCount);
}

function errorInfo() {
	layer.open({
		  type: 1,
		  shade:  [0.01, '#fff'],
		  shadeClose : true,
		  offset: '20px',
		  area: ['337px', '191px'],
		  title: '导入学生与家长', //不显示标题
		  content: $('.tis_import_student_parents'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定'],//按钮
		  btn1: function(index, layero){
		  }
	});
}

function success() {
	layer.open({
		  type: 1,
		  shade:  [0.01, '#fff'],
		  shadeClose : true,
		  offset: '20px',
		  area: ['337px', '221px'],
		  title: '导入教师信息', //不显示标题
		  content: $('.tis_import_teacher'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定'],//按钮
		  btn1: function(index, layero){
			  $(window.parent.document).find("#iframe_sencond").attr("src", "${pageContext.request.contextPath}/tmp/teacher/index")
		  }
	});
}

function openProcess() {
	timer = window.setInterval('getProccess()',300); 
	layer.open({
		  type: 1,
		  closeBtn: 0,
		  shade:  [0.01, '#fff'],
		  shadeClose : false,
		  offset: '20px',
		  area: ['400px', '272px'],
		  title: '导入教师信息', //不显示标题
		  content: $('.tjing'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
	});
}

function getProccess() {
	schoolInitDwr.getProccess();
}

function showMessage(message) {
	var proccess = message + "%";
	$(".jdt_tu").children("p").css("width", proccess);
	$(".jdt_tu").children("span").text(proccess);
	$('.jdt_tu span').css('left',proccess);
	if("100"==message) {
		layer.closeAll();
		$(".jdt_tu").children("p").css("width", "0%");
		$(".jdt_tu").children("span").text("0%");
		success();
	}
}
</script>
</body>
</html>