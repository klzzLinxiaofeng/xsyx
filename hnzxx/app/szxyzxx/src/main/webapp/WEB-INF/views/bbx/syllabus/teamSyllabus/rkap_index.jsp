<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/num0/jQuery.timeRange.js"></script>
<script
	src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<script
	src="${pageContext.request.contextPath}/res/js/common/jquery/ajaxfileupload.js"></script>
<title>课表安排</title>
<style type="text/css">
.table th, .table td {
	text-align: center;
}

.table thead {
	background-color: #418BCA;
	color: #fff;
}

.table .blue {
	background-color: #00859D;
}

.table tbody td {
	padding-bottom: 14px;
	position: relative;
	vertical-align: middle;
	width: 296px;
}

.table tbody td .edit {
	position: absolute;
	bottom: 0;
	color: #7c798f;
	right: 8px;
	cursor: pointer;
	font-weight: bold;
	line-height: 14px;
}

.table tbody td .add {
	position: absolute;
	bottom: 0;
	color: #7c798f;
	right: 12px;
	cursor: pointer;
	font-weight: bold;
	line-height: 14px;
}

.table tbody td .delete {
	position: absolute;
	bottom: 0;
	color: #7c798f;
	left: 8px;
	cursor: pointer;
	font-weight: bold;
	line-height: 14px;
}

.table tbody td .member {
	bottom: 0;
	color: #7c798f;
	cursor: pointer;
	font-weight: bold;
	line-height: 14px;
}

.table tbody td .evaluate {
	bottom: 0;
	color: #7c798f;
	cursor: pointer;
	font-weight: bold;
	line-height: 14px;
}

.table tbody td .attendance {
	bottom: 0;
	color: #7c798f;
	cursor: pointer;
	font-weight: bold;
	line-height: 14px;
}

.chzn-container {
	/* position: relative;
	top: 0px; */
	
}

.row-fluid .span13 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 34%;
	display: inline-block;
	padding-left: 10px;
}

.select_b .select_div span {
	float: left;
	line-height: 31px;
	padding-left: 0px;
}
/* .select_b  button.btn{ float:left;}
.clearl{padding-top:5px;} */
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param name="title" value="班级走班课表" />
			<jsp:param name="icon" value="icon-glass" />
			<jsp:param name="menuId" value="${param.dm}" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>班级走班课表</h3>
					</div>
					<div class="content-widgets" style="margin-bottom:0;">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span style="padding-left: 30px;">学年：</span>
									<select id="xn" name="xn" class="chzn-select" style="width: 120px;"></select>
								</div>
								<div class="select_div">
									<span style="padding-left: 30px;">学期： </span>
									<select id="xq" name="xq" class="chzn-select" style="width: 160px;"></select>
								</div>
								<div class="select_div">
									<span style="padding-left: 30px;">年级：</span>
									<select id="nj" name="nj" class="chzn-select" style="width: 120px;"></select>
								</div>
								<div class="select_div">
									<span style="padding-left: 30px;">走班教室： </span>
									<select id="ks" name="ks" class="chzn-select" style="width: 120px;"></select>
								</div>	
								<button class="btn btn-primary" type="button"
									onClick="getSyllabus();">查询</button>	
								<div class="clearl"></div>
								
								
							</div>
						</div>
					</div>
					<div class="row-fluid" style="width: 98%;margin: 0px auto 15px;">
						<div id="kb_tb"></div>
					</div>
					<input style="display: none;" type="text" id="schoolId" name="schoolId" placeholder="" value="">
					<input style="display: none;" type="text" id="roomId" name="roomId" placeholder="" value="">
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		initScheduleBtnShowOrHide();
		initDetailScheduleBtnClick();
		intlessonInput();
		$.initCascadeSelector_zouban({
			"type" : "team",
			"yearChangeCallback" : function(year) {
				if(year != "") {
					$.SchoolTermSelector({
						"selector" : "#xq",
						"condition" : {"schoolYear" : year},
						"afterHandler" : function($this) {
							$("#xq_chzn").remove();
							$this.show().removeClass("chzn-done").chosen();
						}
					});
				} else {
					$("#xq").val("");
					$("#xq_chzn").remove();
					$("#xq").show().removeClass("chzn-done").chosen();
				}
			}
		});
	});
	

	function getSyllabus() {
		var year = $("#xn").val();
		var nj = $("#nj").val();
		var ks = $("#ks").val();
		var termCode = $("#xq").val();
		if ("" === year || "undefind" === year) {
			$.error("请选择学年");
			return false;
		}
		if ("" === nj || "undefind" === nj) {
			$.error("请选择年级");
			return false;
		}
		if ("" === ks || "undefind" === ks) {
			$.error("请选走班教室");
			return false;
		}
		if ("" === termCode || "undefind" === termCode) {
			$.error("请选择学期");
			return false;
		}
		
		var $requestData = {};
		$requestData.roomId = ks;
		$requestData.gradeId = nj;
		$requestData.termCode = termCode;
		$.get("${pageContext.request.contextPath}/bbx/teamSyllabusZouban/setting/editor", $requestData, function(data, status) {
			if ("success" === status) {
				$("#kb_tb").html("").html(data);
			}
		});
	}

	
	function intlessonInput() {		
		var moringMaxLessonCount = parseInt("${sca:getMorningMaxLessonCount()}");
		var afternoonMaxLessonCount = parseInt("${sca:getAfternoonMaxLessonCount()}");
		var eveningMaxLessonCount = parseInt("${sca:getEveningMaxLessonCount()}");
		
		var moring;
		var afternoon;
		var evening;
		
		$("#kb_tb").on("keyup", "#lessonOfMorning", function(event) {
			var num = $("#lessonOfMorning").val();
// 			if (event.keyCode >= 48 && event.keyCode <= 57) {
				var $this = $(this);
			if(num<=moringMaxLessonCount){
				var new_val = $this.val();
				moring = parseInt(new_val); 
				if (new_val != "") {
					new_val = parseInt(new_val);
					if (new_val <= moringMaxLessonCount) {
						initLessonTimes($this, new_val, 1);
					} else {
						$this.val(moringMaxLessonCount);
						new_val = moringMaxLessonCount;
						initLessonTimes($this, new_val, 1);
					} 		
				}
			}else{
				$this.val(moringMaxLessonCount);
				new_val = moringMaxLessonCount;
				initLessonTimes($this, new_val, 1);
			}
		});

		$("#kb_tb").on("keyup", "#lessonOfAfternoon", function(event) {
			var num = $("#lessonOfAfternoon").val();
			var $this = $(this);
			if(num<=afternoonMaxLessonCount){
				var new_val = $this.val();
				afternoon = parseInt(new_val);
				if (new_val != "") {
					new_val = parseInt(new_val);
					/* if (new_val <= afternoonMaxLessonCount) {
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
					} else {
						$this.val(afternoonMaxLessonCount);
						new_val = afternoonMaxLessonCount
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
					} */
					
					var lessonOfMorning = parseInt($("#lessonOfMorning").val());//加了一行
					if (new_val <= afternoonMaxLessonCount) {
						//////initLessonTimes($this, new_val, 1 + moringMaxLessonCount);					
						initLessonTimes($this, new_val, 1 + lessonOfMorning);
					} else {
						$this.val(afternoonMaxLessonCount);
						new_val = afternoonMaxLessonCount
						//initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
						initLessonTimes($this, new_val, 1 + lessonOfMorning);
					}
					
					
				}
			}else{
				$this.val(afternoonMaxLessonCount);
				new_val = afternoonMaxLessonCount
				initLessonTimes($this, new_val, 1 + moring);
			}
		});
		
		$("#kb_tb").on("keyup", "#lessonOfEvening", function(event) {
			var num = $("#lessonOfEvening").val();
			var $this = $(this);
			if(num<=eveningMaxLessonCount){
				var new_val = $this.val();
				evening = parseInt(new_val);
				if (new_val != "") {
					new_val = parseInt(new_val);
					/* if (new_val <= eveningMaxLessonCount) {
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
					} else {
						$this.val(eveningMaxLessonCount);
						new_val = eveningMaxLessonCount
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
					} */
					
					var lessonOfMorning = parseInt($("#lessonOfMorning").val());//加了一行
					var lessonOfAfternoon = parseInt($("#lessonOfAfternoon").val());//加了一行
					if (new_val <= eveningMaxLessonCount) {
						//initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
						initLessonTimes($this, new_val, 1 + lessonOfMorning + lessonOfAfternoon);
					} else {
						$this.val(eveningMaxLessonCount);
						new_val = eveningMaxLessonCount
						initLessonTimes($this, new_val, 1 + lessonOfMorning + lessonOfAfternoon);
						//initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
					}
				}
			}else{
				$this.val(eveningMaxLessonCount);
				new_val = eveningMaxLessonCount
				initLessonTimes($this, new_val, 1 + moring + afternoon);
			}
		});
		
		//鼠标粘贴事件
		$("#kb_tb").on("paste", "#lessonOfMorning", function(event) {
	        var num = undefined;
	        if (window.clipboardData && window.clipboardData.getData) { // IE
	        	num = window.clipboardData.getData('Text');
	          } else {
	        	  num = event.originalEvent.clipboardData.getData('Text');//e.clipboardData.getData('text/plain');
	          }
			var $this = $(this);
			if(num<=moringMaxLessonCount){
				var new_val = $this.val();
				if (new_val != "") {
					new_val = parseInt(new_val);
					if (new_val <= moringMaxLessonCount) {
						initLessonTimes($this, new_val, 1);
					} else {
						$this.val(moringMaxLessonCount);
						new_val = moringMaxLessonCount;
						initLessonTimes($this, new_val, 1);
					}
				}
			}else{
				$this.val(moringMaxLessonCount);
				new_val = moringMaxLessonCount;
				initLessonTimes($this, new_val, 1);
			}
			return false;
		});
		//鼠标粘贴事件
		$("#kb_tb").on("paste", "#lessonOfAfternoon", function(event) {
			var num = undefined;
	        if (window.clipboardData && window.clipboardData.getData) { // IE
	        	num = window.clipboardData.getData('Text');
          	} else {
        	  	num = event.originalEvent.clipboardData.getData('Text');//e.clipboardData.getData('text/plain');
          	}
			var $this = $(this);
			if(num<=afternoonMaxLessonCount){
				var new_val = $this.val();
				if (new_val != "") {
					new_val = parseInt(new_val);
					/* if (new_val <= afternoonMaxLessonCount) {
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
					} else {
						$this.val(afternoonMaxLessonCount);
						new_val = afternoonMaxLessonCount
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
					} */
					
					var lessonOfMorning = parseInt($("#lessonOfMorning").val());//加了一行
					if (new_val <= afternoonMaxLessonCount) {
						//initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
						initLessonTimes($this, new_val, 1 + lessonOfMorning);
					} else {
						$this.val(afternoonMaxLessonCount);
						new_val = afternoonMaxLessonCount
						//initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
						initLessonTimes($this, new_val, 1 + lessonOfMorning);
					}
				}
			}else{
				$this.val(afternoonMaxLessonCount);
				new_val = afternoonMaxLessonCount
				initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
			}
			return false;
		});
		//鼠标粘贴事件
		$("#kb_tb").on("paste", "#lessonOfEvening", function(event) {
			var num = undefined;
	        if (window.clipboardData && window.clipboardData.getData) { // IE
	        	num = window.clipboardData.getData('Text');
          	} else {
        	  	num = event.originalEvent.clipboardData.getData('Text');//e.clipboardData.getData('text/plain');
          	}
			var $this = $(this);
			if(num<=eveningMaxLessonCount){
				var new_val = $this.val();
				e = new_val;
				if (new_val != "") {
					new_val = parseInt(new_val);
					/* if (new_val <= eveningMaxLessonCount) {
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
					} else {
						$this.val(eveningMaxLessonCount);
						new_val = eveningMaxLessonCount
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
					} */
					
					var lessonOfMorning = parseInt($("#lessonOfMorning").val());//加了一行
					var lessonOfAfternoon = parseInt($("#lessonOfAfternoon").val());//加了一行
					if (new_val <= eveningMaxLessonCount) {
						//initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
						initLessonTimes($this, new_val, 1 + lessonOfMorning + lessonOfAfternoon);
					} else {
						$this.val(eveningMaxLessonCount);
						new_val = eveningMaxLessonCount
						initLessonTimes($this, new_val, 1 + lessonOfMorning + lessonOfAfternoon);
						//initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
					}
				}

			}else{
				$this.val(eveningMaxLessonCount);
				new_val = eveningMaxLessonCount
				initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
			}
		});
	}

	function initLessonTimes($this, new_val, start) {
		var old_val = $this.attr("data-val");
		start = start - 1;
		if ("" === old_val) {
			for (var i = 1 + start; i <= new_val + start; i++) {
// 				$this.parent().append("<div class='lessonDiv' data-lesson='" + i + "'><input id='start' type='text' name='start' class='span1 timeRange' placeholder='开始时间' value=''> 至 <input id='end' type='text' name='end' class='span1 timeRange' placeholder='结束时间' value=''></div>");
				$this.parent().append("<div class='lessonDiv' data-lesson='" + i + "'><input id='startAndEnd' readonly='readonly' type='text' name='startAndEnd' class='span2 timeRange' placeholder='开始-结束' value=''></div>");
				// 				start = start + 1;
			}
			$this.attr("data-val", new_val);
			return;
		}
		old_val = parseInt(old_val);
		if (new_val != old_val) {
			if (new_val > old_val) {
				for (var i = (old_val + 1) + start; i <= new_val + start; i++) {
// 					$this.parent().append("<div class='lessonDiv' data-lesson='" + i + "'><input id='start' type='text' name='start' class='span1 timeRange' placeholder='开始时间' value=''> 至 <input id='end' type='text' name='end' class='span1 timeRange' placeholder='结束时间' value=''></div>");
					$this.parent().append("<div class='lessonDiv' data-lesson='" + i + "'><input id='startAndEnd' readonly='readonly' type='text' name='startAndEnd' class='span2 timeRange' placeholder='开始-结束' value=''></div>");
				}
				$this.attr("data-val", new_val);
				return;
			} else {
				var deleteCount = old_val - new_val;
				for (var i = 1; i <= deleteCount; i++) {
					$this.parent().find("div:last").remove();
				}
				$this.attr("data-val", new_val);
			}
		}
	}

	function initScheduleBtnShowOrHide() {
		$("#kb_tb").on("mouseover", "table tbody tr td .kb_cell",
				function(event) {
					var $this = $(this);
					$this.find(".member").show();
					$this.find(".evaluate").show();
					$this.find(".attendance").show();
					event.stopPropagation();
				});

		$("#kb_tb").on("mouseleave", "table tbody tr td .kb_cell",
				function(event) {
					var $this = $(this);
					$this.find(".member").hide();
					$this.find(".evaluate").hide();
					$this.find(".attendance").hide();
					event.stopPropagation();
				});
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	function initDetailScheduleBtnClick() {
		$("#kb_tb").on(
			"click",
			"table tbody tr td .kb_cell .member",
			function(event) {
				var $this = $(this);
				var id = $this.parent().parent().attr("data-kbDm");
				if ("" === id || id == null) {
					$.error("您还未添加信息");
				} else {
					//var syllabusLessonId = $(this).attr("data-kbdm");
					//成员	
					$.initWinOnTopFromLeft('查看成员', 
						'${ctp}/bbx/teacherSyllabus/member/index?syllabusLessonId='+id + "&name=" , '900', '650');
				}
				event.stopPropagation();
			}
		);
		
		$("#kb_tb").on(
			"click",
			"table tbody tr td .kb_cell .evaluate",
			function(event) {
				var $this = $(this);
				var id = $this.parent().parent().attr("data-kbDm");
				if ("" === id || id == null) {
					$.error("您还未添加信息");
				} else {
					//var syllabusLessonId = $(this).attr("data-kbdm");
					//评价	
					$.initWinOnTopFromLeft('查看评价', '${ctp}/bbx/syllabusEvaluate/index?syllabusLessonId='+id, '900', '650');
				}
				event.stopPropagation();
			}
		);	
		
		$("#kb_tb").on(
			"click",
			"table tbody tr td .kb_cell .attendance",
			function(event) {
				var $this = $(this);
				var id = $this.parent().parent().attr("data-kbDm");
				var syllabusType = $this.attr("data-type");
				if ("" === id || id == null) {
					$.error("您还未添加信息");
				} else {
					//var syllabusLessonId = $(this).attr("data-kbdm");
					//考勤
					$.initWinOnTopFromLeft('查看考勤', 
						'${ctp}/bbx/attendancesSyllabus/teacher/input?lessonId='+id  + "&syllabusType=" + syllabusType, '700', '450');
				}
				event.stopPropagation();
			}
		);	
	}

	function initValidator() {
		return $("#setting_form").validate({
			errorClass : "myerror",
			rules : {
				"days" : {
					required : true
				},
				"daysPlan" : {
					required : true
				},
				"lessonOfMorning" : {
					required : true
				},
				"lessonOfAfternoon" : {
					required : true
				},
				"lessonOfEvening" : {
					required : true
				}
			},
			messages : {

			}

		});
	}


	//获取课节时间段安排json字符串
	function getLessonTimes() {
		var $lessonTimes = $(".lessonDiv");
		var lessonTimesArrayStr = "[";
		$.each($lessonTimes, function(index, value) {
			var $this = $(this);
			var lesson = $this.attr("data-lesson");
			var startAndEnd = $this.find("#startAndEnd").val();
			var startTime = "";
			var endTime = "";
			if(startAndEnd != null && startAndEnd != "") {
				startAndEnd = startAndEnd.split("-");
				startTime = startAndEnd[0];
				endTime = startAndEnd[1];
			}
			var lessonTimeStr = "{'lesson' : '" + lesson + "', 'startTime' : '" + startTime + "', 'endTime' : '" + endTime + "'}";
			lessonTimesArrayStr += (lessonTimeStr + ",");
		});
		return lessonTimesArrayStr.length > 1 ? lessonTimesArrayStr.substring(
				0, lessonTimesArrayStr.length - 1)
				+ "]" : "";
	}

	//回显节数时间段安排
	function echoLessonTimes(lessonTimesJson) {
// 		lessonTimesJson = "";
		
		var $lessonOfMorning = $("#lessonOfMorning");
		var $lessonOfAfternoon = $("#lessonOfAfternoon");
		var $lessonOfEvening = $("#lessonOfEvening");
		
		var $lessonOfMorningVal = $lessonOfMorning.val();

		if ($lessonOfMorningVal != "" && $lessonOfMorningVal != "undefind") {
			$lessonOfMorningVal = parseInt($lessonOfMorningVal);
		}

		var $lessonOfAfternoonVal = $lessonOfAfternoon.val();

		if ($lessonOfAfternoonVal != "" && $lessonOfAfternoonVal != "undefind") {
			$lessonOfAfternoonVal = parseInt($lessonOfAfternoonVal);
		}
		
		var afternoonStartLesson = $lessonOfMorningVal;

		var eveningStartLesson = $lessonOfMorningVal + $lessonOfAfternoonVal;
		
		var afternoonContainer = $lessonOfAfternoon.parent();

		var eveningContainer = $lessonOfEvening.parent();
		
		var container = $lessonOfMorning.parent();
		
		if (lessonTimesJson != "" && lessonTimesJson != "undefind") {
			var lessonTimes = eval("(" + lessonTimesJson + ")");
			$.each(lessonTimes, function(index, value) {
				var curCount = (index + 1);
				if (curCount > afternoonStartLesson && curCount <= eveningStartLesson) {
					container = afternoonContainer;
				} else if (curCount > eveningStartLesson) {
					container = eveningContainer;
				}
				container.append("<div class='lessonDiv' data-lesson='" + value.lesson + "'><input id='startAndEnd' readonly='readonly' type='text' name='startAndEnd' class='span2 timeRange' placeholder='开始-结束' value='" + value.startTime + "-" + value.endTime + "'></div>")
			});
		} else {
			var moringMaxLessonCount = parseInt("${sca:getMorningMaxLessonCount()}");
			var afternoonMaxLessonCount = parseInt("${sca:getAfternoonMaxLessonCount()}");
			var eveningMaxLessonCount = parseInt("${sca:getEveningMaxLessonCount()}");
			for(var i = 1; i <= $lessonOfMorningVal; i++) {
				container.append("<div class='lessonDiv' data-lesson='" + i + "'><input id='startAndEnd' readonly='readonly' type='text' name='startAndEnd' class='span2 timeRange' placeholder='开始-结束' value=''></div>");
			}
			container = afternoonContainer;
			for(var i = 1; i <= $lessonOfAfternoonVal; i++) {
				container.append("<div class='lessonDiv' data-lesson='" + (moringMaxLessonCount + i) + "'><input id='startAndEnd' readonly='readonly' type='text' name='startAndEnd' class='span2 timeRange' placeholder='开始-结束' value=''></div>");
			}
			var $lessonOfEveningVal = $lessonOfEvening.val();
			if ($lessonOfEveningVal != "" && $lessonOfEveningVal != "undefind") {
				$lessonOfEveningVal = parseInt($lessonOfEveningVal);
			}
			container = eveningContainer;
			for(var i = 1; i <= $lessonOfEveningVal; i++) {
				container.append("<div class='lessonDiv' data-lesson='" + (moringMaxLessonCount + afternoonMaxLessonCount + i) + "'><input id='startAndEnd' readonly='readonly' type='text' name='startAndEnd' class='span2 timeRange' placeholder='开始-结束' value=''></div>");
			}
		}
	}

	function echoDaysPlan($daysPlan) {

		var $daysPlanArray = $daysPlan.split(",");

		$.each($daysPlanArray, function(index, value) {
			$("#daysPlansDiv").find("input:checkbox[value='" + value + "']").attr("checked", "checked");

		});

	}

	//显示课表编辑版面
	function showSyllabusArrangementPane(syllabusId) {
		var requestData = {};
		requestData.syllabusId = syllabusId;
		$.get("${pageContext.request.contextPath}/bbx/teamSyllabusZouban/rkap/list", requestData, function(data, status) {
			$("#syllabusArrangementPane").html("").html(data);
		});
	}
	
	function showOrHide(obj) {
		var $this = $(obj);
		var parent = $this.parent();
		var clzz = parent.attr("class");
		if("open_sz" === clzz) {
			$("#setting_input_pane").show("slow");
			parent.removeClass("open_sz").addClass("close_sz");
		} else {
			$("#setting_input_pane").hide("slow");
			parent.removeClass("close_sz").addClass("open_sz");
		}
	}
	
	
	
	
	
	
	
	
</script>
</html>