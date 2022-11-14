<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/tj_szsx.css" rel="stylesheet">
<title></title>
<style type="text/css">
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.tj_fens input{
    width: 50px;height:15px;
}
.tk_Bomb{
    width: 500px;height:400px;overflow-x: hidden;
}
#examRound_chzn{
	top:10px;
}
.tj_shux input{
	height:15px;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="tk_Bomb">
<form class="form-horizontal tan_form" id="examSet_form" action="javascript:void(0);">
    <div class="tk_tjdj">
    <h4>统计条件</h4>
    <div class="tk_leix">科目：<span class="tk_kemo">${subjectName }</span>&nbsp;&nbsp;班级：<span class="tk_nj">${teamName }</span></div>
    <div class="tj_fens">满分分数：<input type='text' name="fullScore" id="fullScore" value='${examStat.fullScore }' />&nbsp;&nbsp;优秀分数：<input type='text' name="highScore" id="highScore" value="${examStat.highScore }"><!-- <button class="bu">从模板读取</button> --></div>
    <div class="tj_fens">良好分数：<input type='text' name="lowScore" id="lowScore" value="${examStat.lowScore }" />&nbsp;&nbsp;及格分数：<input type='text' name="passScore" id="passScore" value="${examStat.passScore }"><!-- <button class="bu">保存到模板</button> --></div>
    <div class=""></div>
    </div>

    <i class="tk_xian"></i>

    <div class="tk_kssx">
    <h4>考试属性</h4>
    <div class="tj_shux">考试时间：<input type='text' name="examDate" placeholder="请输入时间" value="<fmt:formatDate value='${exam.examDate }'/>" onclick="WdatePicker();" />&nbsp;&nbsp;科任教师：<input value='<c:choose><c:when test="${ empty teacherName }">无</c:when><c:otherwise>${teacherName }</c:otherwise></c:choose>'  disabled='disabled' style="height:20px;"></div>
    <div class="tj_kslx">考试类型：<select id="examType" name="examType"></select>&nbsp;&nbsp;
    	考试轮次：
    	<select id="examRound" name="examRound" style="top:10px;">
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
    </div>
    <input type="hidden" id="examId" name="examId" value="${exam.id}" />
    <div class="form-actions tan_bottom" background-color: #eee; text-align: center;">
		<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
		<button class="btn" type="button" onclick="closeWin();">取消</button>
	</div>
</form>
</div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		//考试轮次回显
		$("#examRound").val("${exam.examRound}");
		$("#examType").change(function(e){
			 removeExamRound();
		});
		$.jcGcSelector("#examType", {tc : "XY-JY-KSLX"}, "${exam.examType}", function() {
		},function(){
			$("#examType").chosen();
		});
		checker = initValidator();
	});
	
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
	
	function initValidator() {
		return $("#examSet_form").validate({
			errorClass : "myerror",
			rules : {
				"fullScore":{
					required:true,
					isFloatGZero:true,
					max:200.0
				},
				"highScore":{
					required:true,
					isFloatGZero:true,
					max:200.0,
					lessThanFull:true
				},
				"lowScore":{
					required:true,
					isFloatGZero:true,
					max:200.0,
					lessThanHigh:true
				},
				"passScore":{
					required:true,
					isFloatGZero:true,
					max:200.0,
					lessThanLow:true
				},
				"examDate":{
					required:true
				},
				"examType":{
					selectNone : true
				},
				"examRound":{
					selectNone : true
				}
			},
			messages : {
				
			}
		});
	}
	
	<%--------------------校验---------------------------%>
	<%-- 
		@function highScore校验
		@date 2016年1月29日
	 --%>
	$.validator.addMethod("lessThanFull", function(value, element, param) {
		var result = true;
		var $this = $(element);
		var begin = $this.val();
	    var end = $("#fullScore").val();
	    if(begin - end>0){
	    	result = false;
	    }
	    return this.optional(element) || result;
	}, "优秀分数小于满分分数");
	
	<%-- 
		@function lowScore校验
		@date 2016年1月29日
	 --%>
	$.validator.addMethod("lessThanHigh", function(value, element, param) {
		var result = true;
		var $this = $(element);
		var begin = $this.val();
	    var end = $("#highScore").val();
	    if(begin - end>0){
	    	result = false;
	    }
	    return this.optional(element) || result;
	}, "良好分数小于优秀分数");
	
	<%-- 
		@function passScore校验
		@date 2016年1月29日
	 --%>
	$.validator.addMethod("lessThanLow", function(value, element, param) {
		var result = true;
		var $this = $(element);
		var begin = $this.val();
	    var end = $("#lowScore").val();
	    if(begin - end>0){
	    	result = false;
	    }
	    return this.optional(element) || result;
	}, "及格分数小于良好分数");
	<%--------------------校验---------------------------%>
	
	//保存
	function saveOrUpdate() {
		if (checker.form()) {
			$.ajax({
				url:"${ctp}/teach/scoreManagement/checkExam",
				type:"post",
				dataType:"json",
				data:{
					"examId":function(){return $("#examId").val();},
					"examType":function(){return $("#examType").val();},
					"examRound":function(){return $("#examRound").val();}
				},
				success:function(data) {
			    	if(data==false){
			    		$.alert('该类型的考试已经存在！');
			    	}else{
			    		var loader = new loadLayer();
						var $requestData = formData2JSONObj("#examSet_form");
						//alert(JSON.stringify($requestData));
						var url = "${ctp}/teach/scoreManagement/saveExamAttribute";
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
									$.closeWindow();
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
			});
		}
	}
	
	function closeWin(){
		$.confirm("确定离开此页面？", function() {
			$.closeWindow();
		});
	}
</script>
</html>