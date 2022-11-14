<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/dept_selector_js.jsp" %>
<title>学校列表</title>
<style>
.cjlr{
text-align:center;
}
.cjlr .d1{
	font: bold 18px/58px Microsoft YaHei;
}
.cjlr .d2{
	color:#6E6E6E;
	line-height:80px;
}
</style>
</head>
<body>
	<div class="container-fluid">
	<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="考试成绩录入" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							考试成绩录入列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a2" onclick="saveScore();"><i class="fa fa-plus"></i>保存</a>
<!-- 								<a href="javascript:void(0)" class="a1" onclick="loadCreatePage();"><i class="fa  fa-arrow-down"></i>模板下载</a> -->
<!-- 								<a href="javascript:void(0)" class="a3" onclick="loadCreatePage();"><i class="fa fa-plus"></i>从文件导入</a> -->
								<a href="javascript:void(0)" class="a4" onclick="goHistory()"><i class="fa fa-plus"></i>返回</a>
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学年：</span>
									<select id="schoolYear" disabled="disabled"></select>
								</div>
								<div class="select_div">
									<span>学期：</span>
									<select id="termCode" disabled="disabled" style="background-color: transparent;">
										<c:forEach items="${termList}" var="term">
											<option value="${term.code}" <c:if test="${termCode == term.code}">selected="selected"</c:if> >${term.name}</option>
										</c:forEach>
									</select>
								</div>
								<div class="select_div">
									<span>年级：</span>
									<select id="gradeId" disabled="disabled">
									</select>
								</div>
								<div class="select_div">
									<span>班级：</span>
									<select id="teamId" disabled="disabled">
									</select>
								</div>
								<div class="select_div">
									<span>科目：</span>
									<select id="subjectCode" <c:if test="${isModify == 'isModify'}">disabled="disabled"</c:if>>
										<option value="">请选择</option>
										<c:forEach items="${subjectList}" var="subject" varStatus="i">
											<option value="${subject.subjectCode}"
												<c:choose>
													<c:when test="${condition.subjectCode==subject.subjectCode}">selected="selected"</c:when>
													<c:when test="${i.index==0}">selected="selected"</c:when>
												</c:choose>
											>
												${subject.subjectName}
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="select_div">
									<span>类型：</span>
									<select id="examType" <c:if test="${isModify == 'isModify'}">disabled="disabled"</c:if>>
									</select>
								</div>
								<div class="select_div">
									<span>轮次：</span>
									<select id="examRound" <c:if test="${isModify == 'isModify'}">disabled="disabled"</c:if>>
										<option value="1" <c:if test="${condition.examRound=='1'}">selected="selected"</c:if> >1</option>
										<option value="2" <c:if test="${condition.examRound=='2'}">selected="selected"</c:if> >2</option>
										<option value="3" <c:if test="${condition.examRound=='3'}">selected="selected"</c:if> >3</option>
										<option value="4" <c:if test="${condition.examRound=='4'}">selected="selected"</c:if> >4</option>
										<option value="5" <c:if test="${condition.examRound=='5'}">selected="selected"</c:if> >5</option>
										<option value="6" <c:if test="${condition.examRound=='6'}">selected="selected"</c:if> >6</option>
										<option value="7" <c:if test="${condition.examRound=='7'}">selected="selected"</c:if> >7</option>
										<option value="8" <c:if test="${condition.examRound=='8'}">selected="selected"</c:if> >8</option>
										<option value="9" <c:if test="${condition.examRound=='9'}">selected="selected"</c:if> >9</option>
										<option value="10" <c:if test="${condition.examRound=='10'}">selected="selected"</c:if> >10</option>
									</select>
								</div>
							<div class="clear"></div>
							</div>
							
							<div class="cjlr">
								<div class="d1">录入成绩</div>
								<div id="studentScore">
									<jsp:include page="./studentScoreList.jsp" />
								</div>
							</div>
						</div>
					</div>
					</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var loader = new loadLayer();
var init = 0;
$(function () {
	$(".ck_select a").click(function(){
		var i=$(this).index();
		$(".ck_select a").removeClass("on");
		$(this).addClass("on");
		$(".c_list").children().hide();
		$(".c_list").children().eq(i).show();
	})
	$("#termCode").chosen();
	$("#subjectCode").chosen();
	
	//学年下拉
	$.SchoolYearSelector({
		"selector" : "#schoolYear",
		"condition" : {},
		"selectedVal" : "${condition.schoolYear}",
		"afterHandler" : function(selector) {
		}
	});
	
	//年纪下拉
	$.GradeSelector({
		"selector" : "#gradeId",
		"condition" : {},
		"selectedVal" : "${condition.gradeId}",
		"afterHandler" : function(selector) {
		}
	});
	
	//班级下拉
	$.TeamSelector({
		"selector" : "#teamId",
		"condition" : {},
		"selectedVal" : "${condition.teamId}",
		"afterHandler" : function(selector) {
		}
	});
	
	removeExamRound();
	
	 $.jcGcSelector("#examType", {tc : "XY-JY-KSLX"}, "${condition.examType}", function() {
			
		},function(){
			$("#examType").chosen();
	});
	
	 $("#subjectCode,#examType,#examRound").change(function(e){
		 removeExamRound();
		 
		 //这里是因为 考试类型是生成的，所以一开始就有change事件，会导致一进来就弹出“请选择考试类型”实际上第一次进来不应该有change事件的
		 //所以一开始定义了一个初始值是0 如果是0 则不执行查询  等到这段代码执行完之后  初始值变成1 往后以改变都会执行查询
		 if(init != 0){
			 searchInChange();
		 }
		 init = 1;
		 
	 });
	 
});

//用于存放主赛选条件的选项值   不需要每个方法都写一次
function getCondition(){
	$condition = {
		"schoolYear" : $("#schoolYear").val(),
		"termCode" : $("#termCode").val(),
		"teamId" : $("#teamId").val(),
		"gradeId" : $("#gradeId").val(),
		"subjectCode" : $("#subjectCode").val(),
		"examType" : $("#examType").val(),
		"examRound" : $("#examRound").val()
	}
	return $condition; 
}

//判断主赛选条件是否已经选上   返回Boolean值  如果全部选中  true  否则   false
function checkSelect(){
	var canNext = true;
	var val = getCondition();
	if(val.schoolYear == ""){
		$.alert("请选择学年!!!");
		canNext = false;
	}
	if(val.termCode == ""){
		$.alert("请选择学期!!!");
		canNext = false;
	}
	if(val.gradeId == ""){
		$.alert("请选择年级!!!");
		canNext = false;
	}
	if(val.teamId == ""){
		$.alert("请选择班级!!!");
		canNext = false;
	}
	if(val.subjectCode == ""){
		$.alert("请选择科目!!!");
		canNext = false;
	}
	if(val.examType == ""){
		$.alert("请选择考试类型!!!");
		canNext = false;
	}
	if(val.examRound == ""){
		$.alert("请选择考试轮次!!!");
		canNext = false;
	}
	return canNext;
}

//用于下拉时 的查询操作
function searchInChange(){
	//判断条件是否全部选择
	if(!checkSelect()){
		return;
	}
	
	//获取查询的条件
	var $condition = getCondition();
	
	var tbodyId = "studentScore";
	var searchUrl = "/teach/pjExam/searchStudentScoreByCondition";
	myPagination(tbodyId, $condition, searchUrl);
}

//去掉期末、其中 的考试轮次
function removeExamRound(){
	var $condition = getCondition();
	if($condition.examType == 01 || $condition.examType == 02){
		$("#examRound").val(1);
		$("#examRound").attr("disabled", true);
		$("#examRound").trigger("liszt:updated"); 
		$("#examRound").chosen();
	} else {
		$("#examRound").attr("disabled", false);
		$("#examRound").trigger("liszt:updated"); 
		$("#examRound").chosen();
	}
}

//开始录入成绩
function inputScore(){
	//判断条件是否已经选完
	if(!checkSelect()){
		return;
	}
	//初始化数据表
	initData();
}

//清除成绩
function cleanScore(){
	var $requestData = {"examId" : $("#examId").val()}
	loader.show();
	$.post("${ctp}/teach/pjExam/clearExamScore", $requestData, function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("数据已清空！！！");
				searchInChange();//查询数据
			}else{
				$.error("数据清空失败！！！");
			}
		}else{
			$.error("数据清空失败！！！");
		}
		loader.close();
	});
}

//删除本科成绩
function deleteScore(){
	var $requestData = {"examId" : $("#examId").val()}
	loader.show();
	$.post("${ctp}/teach/pjExam/deletedExamScore", $requestData, function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("删除记录成功！！！");
				searchInChange();//查询数据
			}else{
				$.error("删除记录失败！！！");
			}
		}else{
			$.error("删除记录失败！！！");
		}
		loader.close();
	});
}

//保存成绩
function saveScore(){
	//检验分数是否是数字
	var mess = checkScoreIsNumber();
	if(mess != ""){
		$.alert("学生成绩中" + mess + "成绩错误,请修改！！！")
		return;
	}
	
	//修改未录入而直接保存时提示的保存失败
	if($("#examId").val() == "" || $("#examId").val() == null || $("#examId").val() == "undefined"){
		$.alert("请先点击‘开始录入’按钮进行初始化数据！");
		return;
	}
	//获取成绩的json
	var examStudent = tableDataToJson();

	loader.show();
	$.post("${ctp}/teach/pjExam/saveExamScore", {"examStudent" : examStudent,"_method" : "post","examId" : $("#examId").val()}, function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				searchInChange();//查询数据
				$.success("保存成功！！！");
			}else{
				$.error("保存失败！！！");
			}
		}else{
			$.error("保存失败！！！");
		}
		loader.close();
	});
}

//初始化数据表
function initData(){
	loader.show();
	var $condition = getCondition();
	$condition._method = "post";
	$.post("${ctp}/teach/pjExam/initData", $condition, function(data, status) {
		if ("success" === status) {
			if ("success" != data) {
				$.error("数据初始化失败！！！");
			}else{
				searchInChange();//查询数据
				$.success("初始化数据成功");
			}
		}else{
			$.error("数据初始化失败！！！");
		}
		loader.close();
	});
}

//将表格数据转成JSON数据
function tableDataToJson(){
    var tab =  $("#tBodyId").find("tr");
    var jsonT = "[";
    for(var i = 0; i < tab.length; i++){
    	
    	var studentId = tab.eq(i).data("id");
    	var score = tab.eq(i).find("td input").val();
    	var testType = tab.eq(i).find("td input[type='radio']:checked").val();
    	
    	if(studentId == ""){
    		studentId = null;
    	}
    	if(score == "" || score == null || score == "undefined"){
    		score = -1;
    	}
    	if(testType == ""){
    		testType = null;
    	}
    	
        if(i == 0){
          jsonT +="{studentId:" + studentId + ",score:" + score + ",testType:" + testType + "}"
        }else{
          jsonT +=",{studentId:" + studentId + ",score:" + score + ",testType:" + testType + "}"
        }
    }
    jsonT += "]";
    return jsonT;
}

//检查某行出错
function checkScoreIsNumber(){
	var tab =  $("#tBodyId").find("tr");
	var mess = "";
	var fullScore = $("#fullScore").val();
	
// 	用于校验当前输入的分数不能大于满分分数   2016-2-16
	if(fullScore == null || fullScore == ""){
		fullScore = 100;
	}
	
    for(var i = 0; i < tab.length; i++){
    	var num = 1 + i;
    	var studentName = tab.eq(i).find("td").eq(1).text();
    	var score = tab.eq(i).find("td input").val();
        if(score != "" && score != null && score != "undefined"){
        	score = parseInt(score);
        	fullScore = parseInt(fullScore);
        	if(isNaN(score) || score > fullScore || score < 0){
        		if(mess == ""){
        			mess += "第" + num + "行的" + studentName;
        		}else{
        			mess += ", 第" +  num + "行的" +  studentName;
        		}
        	}
        }
    }
    return mess;
}

//按下回车键的时候，将光标移动到下一个文本框
function nextFour(obj){
	var evt = window.event || arguments.callee.caller.arguments[0];
	if(evt.keyCode == 13){
		var _nextObj = $(obj).parent().parent().next().find("td input[name='score']");
		_nextObj.select();
	}
}

function goHistory(){
	window.location.href="${ctp}/teach/pjExam/entryManagement";
}
</script>
</html>