<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<title>学校列表</title>
<style>
.ck_select{
	background-color:#F2F2F2;
	height:40px;
	line-height:40px;
	border:1px solid #BFBFBF;
}
.ck_select a{
	width:95px;
	text-align:center;
	float:left;
	display:block;
	color:#999999;
}
.ck_select .on{
	background-color:#fff;
	color:#436B8E;
	border-left:1px solid #BFBFBF;
	border-right:1px solid #BFBFBF;
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
<!-- 								<a id="downLoadFile" href="javascript:void(0)" class="a4" onclick="downLoadFile();"><i class="fa fa-plus"></i>导出模板</a> -->
							</p>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>学年：</span>
									 <select id="schoolYear" name="schoolYear" class="chzn-select" style="width:200px;" onchange="onChangeSchoolYear();">
									  <option value="">请选择</option>
								    </select>
								</div>
								<div class="select_div">
									<span>学期：</span>
									<select id="termCode" name="term" style="width:160px;">
										<option value="">请选择</option>
									</select>
								</div>
								<div class="select_div">
									<span>年级：</span>
									<select id="gradeId" name="gradeId" class="chzn-select" style="width:120px;"></select>
								</div>
								<div class="select_div">
									<span>班级：</span>
									<select id="teamId" name="teamId" class="chzn-select" style="width:160px;">
									</select>
									<input type="hidden" id="initSelected" name="initSelected" value = "0">
								</div>
							<div class="clear"></div>
							</div>
						</div>
					</div>
					<div class="ck_select">
						<a href="javascript:void(0)" class="on" style="position:relative;left:-1px;">按类型查看</a><a href="javascript:void(0)">按科目查看</a>
					</div>
					<div class="c_list">
						<div class="content-widgets">
							<div class="widget-container">
								<div class="select_b">
									<div class="select_div">
										<span>测试类型：</span>
										<select id="examType"></select>
									</div>
									<div class="select_div">
										<span>轮次：</span>
										<select id="examRound">
											<option value="">请选择</option>
											<option value="1">1</option>
											<option value="2">2</option>
											<option value="3">3</option>
											<option value="4">4</option>
											<option value="5">5</option>
											<option value="6">6</option>
											<option value="7">7</option>
											<option value="8">8</option>
											<option value="9">9</option>
											<option value="10">10</option>
										</select>
									</div>
									<div class="select_div">
										<button type="button" class="btn btn-primary" onclick="search('type')"style="margin-top:0px;">查询</button>
									</div>
									<span style="float:right">
									<button type="button" class="btn btn-success" onclick="scoreRegistration()">录入成绩</button>
									</span>
								<div class="clear"></div>
								</div>
								<table class="responsive table table-striped">
									<thead>
										<tr><th>科目</th><th>考试时间</th><th>应试人数</th><th class="caozuo">操作</th></tr>
									</thead>
									<tbody id="score_list_subject">
										<jsp:include page="./showScoreListBySubject.jsp" />
									</tbody>
								</table>
							</div>
						</div>
						<div class="content-widgets" style="display:none">
							<div class="widget-container">
								<div class="select_b">
									<div class="select_div">
										<span>科目：</span>
										<select id="subjectCode">
										</select>
									</div>
									<div class="select_div">
										<button type="button" class="btn btn-primary" onclick="search('subject')">查询</button>
									</div>
									<span style="float:right">
									<button type="button" class="btn btn-success" onclick="scoreRegistration()">录入成绩</button>
									</span>
								<div class="clear"></div>
								</div>
								<table class="responsive table table-striped">
									<thead>
										<tr><th>考试类型</th><th>轮次</th><th>考试时间</th><th>考试人数</th><th class="caozuo">操作</th></tr>
									</thead>
									<tbody id="score_list_type">
										<jsp:include page="./showScoreListByType.jsp" />
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
var initTerm = 0;
var initSelected = 0;
var $condition = {};
var loader = new loadLayer();

$(function () {
	$(".ck_select a").click(function(){
		var i=$(this).index();
		$(".ck_select a").removeClass("on");
		$(this).addClass("on");
		$(".c_list").children().hide();
		$(".c_list").children().eq(i).show();
	})
	
	$.initCascadeSelector({
	    "type" : "team",
		"yearSelectId" : "schoolYear", //学年select标签ID 默认为xn
		"gradeSelectId" : "gradeId", //年级select标签ID 默认为nj
		"teamSelectId" : "teamId",  //班级select标签ID 默认为bj
		"selectOne" : true,
		"teamCallback" : function($this){
			getSubject();
		}
	});
	
	onChangeSchoolYear();
	
	initSelected = 0;

	removeExamRound();
	
	$("#examType").change(function(e){
		 removeExamRound();
	});
	
	$.jcGcSelector("#examType", {tc : "XY-JY-KSLX"}, "", function() {
		
	},function(){
		$("#examType").chosen();
	});
	 
});

//获取根据年级科目
function getSubject(){
	var sub = $("#subjectCode");
	$.post("${ctp}/teach/pjExam/getSubjectByGradeId", {"gradeId" : $("#gradeId").val()}, function(data, status) {
		if ("success" === status) {
			if(data.length == 0){
				sub.html("");
				sub.append("<option value=''>请选择</option>");
			}else{
				sub.html("");
				sub.append("<option value=''>请选择</option>");
				$.each(data, function(index, value) {
					sub.append("<option value='"+value.subjectCode+"'>"+value.subjectName+"</option>");
				});
			}
			sub.trigger("liszt:updated"); 
			sub.chosen();
		}else{
			$.error("数据清空失败！！！");
		}
	},'json');
}

//用于存放主赛选条件的选项值   不需要每个方法都写一次
function getCondition(){
	$condition = {
		"schoolYear" : $("#schoolYear").val(),
		"termCode" : $("#termCode").val(),
		"teamId" : $("#teamId").val(),
		"gradeId" : $("#gradeId").val(),
		"examType" : $("#examType").val(),
		"examRound" : $("#examRound").val(),
		"subjectCode" : $("#subjectCode").val()
	}
	return $condition; 
}

//模板下载
function downLoadFile(){
	var val = getCondition();
	if(!checkSelect()){
		return;
	}
	var url = "${ctp}/teach/pjExam/downLoadFile";
	url = url +"?schoolYear=" + val.schoolYear + "&termCode=" + val.termCode + "&teamId=" + val.teamId;
	$("#downLoadFile").attr("href", url);
}


//学年   学期   年级   班级  联动下拉列表
function onChangeSchoolYear(){
	 
	 var xn = $("#schoolYear").val();
	 var defaultTermForserch;
	 
	 if('${sessionScope[sca:currentUserKey()].schoolYear}' != $("#schoolYear").val()||
			'${sessionScope[sca:currentUserKey()].schoolTermCode}' != $("#termCode").val()		 
	 ){
		initSelected = Number(initSelected)+1;
		$("#initSelected").val(initSelected);
	 }
	 
	 if((xn == null || xn == "")&&initTerm != 0){
		 var $xq = $("#termCode");
		 $xq.html("");
		 $xq.append("<option value=''>请选择 </option>");
	 }else{
		 if((xn == null || xn == "")&&initTerm == 0){
				xn = '${sessionScope[sca:currentUserKey()].schoolYear}';
			}
		 var  defaultTerm = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
		 $.getSchoolTerm({"schoolYear" : xn}, function(data, status) {
				var $xq = $("#termCode");
				if(data.length == 0){
					$xq.html("");
					$xq.append("<option value=''>请选择 </option>");
				}else{
					$xq.html("");
					$.each(data, function(index, value) {
						var code = value.code;
						 if(defaultTerm == code && initTerm == 0){
						    	$xq.append("<option value='" + value.code + "'  selected='selected'>" + value.name + "</option>");
						    }else{
						    	$xq.append("<option value='" + value.code + "' '>" + value.name + "</option>");
						    }
						 $("#termCode").trigger("liszt:updated"); 
						 $("#termCode").chosen();
					});
					initTerm = Number(initTerm)+1;
				}
			});
	 }
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
	return canNext;
}

//查询按钮   点击之后  按科目及类型  进行查询
function search(searchType){
	//获取主查询条件
	var $condition = getCondition();
	
	//判断条件 没有选完的不能查询
	if(!checkSelect()){
		return;
	}
	
	var tbodyId = "";
	var searchUrl = "/teach/pjExam/searchByCondition";
	
	//判断查询类型，加入对应的条件
	if(searchType == "subject"){
		$condition.subjectCode = $("#subjectCode").val();
		
		if($condition.subjectCode == ""){
			$.alert("请选择考试科目");
			return;
		}
		
		$condition.searchType = "subject";
		tbodyId = "score_list_type";
	}else if(searchType == "type"){
		$condition.examType = $("#examType").val();
		$condition.examRound = $("#examRound").val();
		
		if($condition.examType == ""){
			$.alert("请选择考试类型");
			return;
		}
		if($condition.examRound == ""){
			$.alert("请选择考试轮次");
			return;
		}
		
		$condition.searchType = "type";
		tbodyId = "score_list_subject";
	}else{
		$.alert("查询类型出错。。。");
		return;
	}
	myPagination(tbodyId, $condition, searchUrl);
}

//成绩录入
function scoreRegistration(){
	//判断条件 没有选完的不能进入到录入界面 
	if(!checkSelect()){
		return;
	}
	
	//获取主查询条件
	var $condition = getCondition();
	$condition._method = "get";
	var condition = "?schoolYear=" + $condition.schoolYear + "&termCode=" + $condition.termCode + "&gradeId=" + $condition.gradeId + "&teamId=" + $condition.teamId;
	window.location.href="${ctp}/teach/pjExam/toInputScorePage" + condition;
}

//去掉期末、其中 的考试轮次
function removeExamRound(){
	var examType = $("#examType").val();
	if(examType == 01 || examType == 02){
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

//掉转考试属性设置  
//当该方法是类型页面里面进行设置的，subjectOrExamType=subjectCode(考试科目),examRound=null(空值)
//当该方法是类型页面里面进行设置的，subjectOrExamType=examType(考试类型：期考。。。),examRound=examRound(考试轮次)
function examProModify(subjectOrExamType,examRound,examId){
	//获取共同的主查询条件
	var $condition = getCondition();
	
	//当ExamRound=null(空值)
	if(examRound == null){
		$condition.subjectCode = subjectOrExamType;
		$condition.examType = $("#examType").val();
		$condition.examRound = $("#examRound").val();
	}else{
		$condition.subjectCode = $("#subjectCode").val();
		$condition.examType = subjectOrExamType;
		$condition.examRound = examRound;
	}
	
	<%-- 
		function:设置考试属性
		date：2016年1月28日
	--%>
	$.initWinOnTopFromLeft('设置属性', '${ctp}/teach/scoreManagement/setAttribute?examId='+examId, '503', '490');
}

//删除成绩记录
function deleteScore(obj,examId){
	if(examId == null || examId == "" || examId == "undefined"){
		$.alert("数据异常，删除失败！");
		return;
	}
	var $requestData = {"examId" : examId}
	loader.show();
	$.post("${ctp}/teach/pjExam/deletedExamScore", $requestData, function(data, status) {
		if ("success" === status) {
			if ("success" === data) {
				$.success("删除记录成功！！！");
				$(".tr_"+id).remove();
			}else{
				$.error("删除记录失败！！！");
			}
		}else{
			$.error("删除记录失败！！！");
		}
		loader.close();
	});
}

//去到修改的页面
function modifyExamStudent(examId){
	window.location.href="${ctp}/teach/pjExam/modifyExamScore?examId="+examId;
}

//查看统计
function scoreManagement(examId){
	window.location.href="${ctp}/teach/scoreManagement/index?examId="+examId;
}

</script>
</html>