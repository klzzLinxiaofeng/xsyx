<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/num0/jQuery.timeRange.js"></script>
<script src="${pageContext.request.contextPath}/res/js/common/jquery/ajaxfileupload.js"></script>
<title>年级课表安排设置</title>
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
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param name="title" value="年级课程表规划" />
			<jsp:param name="icon" value="icon-glass" />
			<jsp:param name="menuId" value="${param.dm}" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>年级课程表规划
							<p style="float: right;">
								<a href="javascript:void(0)" class="btn btn-success right" onclick="downloadData();" id="downloadExcel">
										下载年级课程表规划模版
								</a>
								<span class="btn btn-file right" style="background: #e69100;color: #fff;"> 
									<span class="fileupload-new">导入年级课程表规划</span>
									<input type="file" id="fileUpload" name="fileUpload" accept=".xls" onchange="fileUploadExcel();" />
								</span>
							</p>
						</h3>
					</div>
					<div class="content-widgets" style="margin-bottom:0;">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span style="padding-left: 30px;">学年：</span>
									<select id="xn" name="xn" class="chzn-select" style="width: 120px;"></select>
								</div>
								<div class="select_div">
									<span style="padding-left: 30px;">年级：</span>
									<select id="nj" name="nj" class="chzn-select" style="width: 120px;"></select>
								</div>
								<div class="select_div">
									<span style="padding-left: 30px;">学期： </span>
									<select id="xq" name="xq" class="chzn-select" style="width: 160px;"></select>
								</div>
								<button class="btn btn-primary" type="button" onClick="getSyllabus();">设置</button>						
								<div class="clear"></div>
							</div>
						</div>
					</div>
					<!-- <div class="termCode">
						<ul id="termCodeUl">

						</ul>
						<div class="clear"></div>
					</div> -->
					<div class="row-fluid" style="width: 98%;margin: 0px auto 15px;">
						<div id="kb_tb"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		initScheduleBtnClick();
		initScheduleBtnShowOrHide();
		initDelScheduleBtnClick();
		intlessonInput();
		$.initCascadeSelector({
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
		var termCode = $("#xq").val();
		if ("" === year || "undefind" === year) {
			$.error("请选择学年");
			return false;
		}
		if ("" === nj || "undefind" === nj) {
			$.error("请选择年级");
			return false;
		}
		if ("" === termCode || "undefind" === termCode) {
			$.error("请选择学期");
			return false;
		}
		var $requestData = {};
		$requestData.gradeId = nj;
		$requestData.termCode = termCode;
		$.get("${pageContext.request.contextPath}/bbx/bwGradeSyllabus/setting/editor", $requestData, function(data, status) {
			if ("success" === status) {
				$("#kb_tb").html("").html(data);
			}
		});

// 		$("#termCodeUl li").removeClass("active");
// 		$(obj).addClass("active");
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
					/* new_val = parseInt(new_val);
					if (new_val <= eveningMaxLessonCount) {
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
					} else {
						$this.val(eveningMaxLessonCount);
						new_val = eveningMaxLessonCount
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount + afternoonMaxLessonCount);
					} */
					
					new_val = parseInt(new_val);
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
					/* new_val = parseInt(new_val);
					if (new_val <= afternoonMaxLessonCount) {
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
					} else {
						$this.val(afternoonMaxLessonCount);
						new_val = afternoonMaxLessonCount
						initLessonTimes($this, new_val, 1 + moringMaxLessonCount);
					} */
					
					new_val = parseInt(new_val);
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
					$this.find(".edit").show();
					$this.find(".delete").show();
					event.stopPropagation();
				});

		$("#kb_tb").on("mouseleave", "table tbody tr td .kb_cell",
				function(event) {
					var $this = $(this);
					$this.find(".edit").hide();
					$this.find(".delete").hide();
					event.stopPropagation();
				});

		$("#kb_tb").on("mouseover", "table tbody tr td", function(event) {
			var $this = $(this);
			$this.find(".add").show();
			event.stopPropagation();
		});

		$("#kb_tb").on("mouseleave", "table tbody tr td", function(event) {
			var $this = $(this);
			$this.find(".add").hide();
			event.stopPropagation();
		});
	}

	function initScheduleBtnClick() {
		var $xqArray = null;
		$("#kb_tb").on(
				"click",
				"table tbody tr td .add",
				function(event) {
					var $this = $(this);
					//获取星期代码&&课节代码
					if ($xqArray == null || $xqArray.length <= 0) {
						$xqArray = $("#kb_tb table thead tr th");
					}
					
					var lesson = $this.parent().parent().find("td:first").attr(
							"data-kj");
					var dayOfWeek = $this.parent().attr("data-xq");
					
					var schoolYear = $("#xn").val();
					var gradeId = $("#nj").val();
					var termCode = $("#xq").val();
					var syllabusId = $("#syllabusId").val();
					$.initWinOnTopFromLeft("年级课表基础设置",
							"${pageContext.request.contextPath}/bbx/bwGradeLesson/rkap/creator?lesson="
									+ lesson + "&dayOfWeek=" + dayOfWeek
									+"&gradeSyllabusId="
									+ syllabusId, "600", "300", '100');
					event.stopPropagation();
				});

		$("#kb_tb").on(
				"click",
				"table tbody tr td .kb_cell .edit",
				function(event) {
					var $this = $(this);
					//获取星期代码&&课节代码
					if ($xqArray == null || $xqArray.length <= 0) {
						$xqArray = $("#kb_tb table thead tr th");
					}
					var lesson = $this.parent().parent().parent().find(
							"td:first").attr("data-kj");
					var dayOfWeek = $(
							$xqArray.get($this.parent().parent()
									.attr("data-xq"))).attr("data-xqdm");
					var schoolYear = $("#xn").val();
					var gradeId = $("#nj").val();
					var termCode = $("#xq").val();
					
					var syllabusId = $("#syllabusId").val();
					var lessonId = $this.parent().attr("data-kbdm");

					$.initWinOnTopFromLeft("年级课表基础设置",
							"${pageContext.request.contextPath}/bbx/bwGradeLesson/rkap/editor?lesson="
									+ lesson + "&dayOfWeek=" + dayOfWeek
									+ "&gradeSyllabusId="
									+ syllabusId + "&id=" + lessonId,
							"800", "300", '100');
					event.stopPropagation();
				});

	}

	function initDelScheduleBtnClick() {
		$("#kb_tb").on(
			"click",
			"table tbody tr td .kb_cell .delete",
		function(event) {
			var $this = $(this);
			var id = $this.parent().attr("data-kbDm");
			if ("" === id || id == null) {
				$.error("您还未添加信息");
			} else {
				$.confirm("确认执行此操作？", function() {
					$.post("${pageContext.request.contextPath}/bbx/bwGradeLesson/"
							+ id, {
						"_method" : "delete"
					}, function(data, status) {
						if ("success" === status) {
							if ("success" === data) {
								$.success("删除成功");
								var kbCell = $this.parent();
								kbCell.remove();
							} else if ("fail" === data) {
								$.error("删除失败，系统异常");
							}
						}
					});
				});
			}
			event.stopPropagation();
		});
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

	//保存或更新修改
	function editorSetting() {
		var checker = initValidator();
		if (checker.form()) {
			var $isSetted = $("#isSetted");
			var isSetted = $isSetted.val();
			var $requestData = formData2JSONObj("#setting_form");
			var $isSetDefaul = false;

			if ( "setting_true" === isSetted || "" === isSetted ) {
				syllabusId = "";
				saveOrUpdateSyllabusInfo(syllabusId, $requestData, $isSetted,
						$isSetDefaul);
			} else {
				syllabusId = $("#syllabusId").val();
				$.confirm("您修改了课表结构，系统将会清空数据，是否继续呢？", function() {
					saveOrUpdateSyllabusInfo(syllabusId, $requestData,
							$isSetted, $isSetDefaul);
				}, function() {
					// 					showSyllabusArrangementPane(syllabusId);
				}); 
			}
		}
	}

	function saveOrUpdateSyllabusInfo(syllabusId, $requestData, $isSetted,
			$isSetDefaul) {
		var $syllabusData = {};
		var url = "${pageContext.request.contextPath}/bbx/bwGradeSyllabus/creator";
		$syllabusData.days = $requestData.days;
		$syllabusData.daysPlan = $requestData.daysPlan;
		$syllabusData.lessonOfMorning = $requestData.lessonOfMorning;
		$syllabusData.lessonOfAfternoon = $requestData.lessonOfAfternoon;
		$syllabusData.lessonOfEvening = $requestData.lessonOfEvening;
		$syllabusData.gradeId = $("#nj").val();
		$syllabusData.schoolYear = $("#xn").val();
		$syllabusData.termCode = $("#xq").val();
		$syllabusData.lessonTimes = getLessonTimes();
		if ("" != syllabusId) {
			$syllabusData._method = "put";
			url = "${pageContext.request.contextPath}/bbx/bwGradeSyllabus/"
					+ syllabusId;
		}
		$.post(url, $syllabusData, function(data, status) {
			if ("success" === status) {
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					$("#syllabusId").val(data.responseData);	
					$.success("设置成功");
					showSyllabusArrangementPane(data.responseData);
					/* $isSetted.val("all_true");
					isSetted = "all_true";
					$("#syllabusId").val(data.responseData);
					if ($isSetDefaul) {
						$.alert("设置成功，当前设置将为当前年级课表默认值");
					} else {
						$.success("设置成功");
					}
					showSyllabusArrangementPane(data.responseData); */
				}
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
		requestData.syllabusId = syllabusId
		$.get("${pageContext.request.contextPath}/bbx/bwGradeSyllabus/rkap/list", requestData, function(data, status) {
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
	
	// 导出模板
	function downloadData() {
		var gradeId = $("#nj").val();
		var termCode =$("#xq").val(); 
		//alert(termCode);
		if ( gradeId == "" || gradeId == null ) {
			$.error("请选择年级");
			return;
		} 
		var url = "${ctp}/bbx/bwGradeSyllabus/downloadData?gradeId=" + gradeId + "&termCode=" + termCode;
		$("#downloadExcel").attr("href", url);
	}
	
	// 导入数据 
	function fileUploadExcel() {
		var gradeId = $("#nj").val();
		if ( gradeId == "" || gradeId == null ) {
			$.error("请选择年级");
			return;
		} 
		var termCode = $("#xq").val();
		if ( termCode == "" || termCode == null ) {
			$.error("请选择学期");
			return;
		} 
		
		var urlParamter = "${ctp}/bbx/bwGradeLesson/uploadData?gradeId=" + gradeId + "&termCode=" + termCode; 
    	//上传
	   	var loader = new loadLayer();
		var file = $("#fileUpload").val(); 
		loader.show();
		//执行上传文件操作的函数 
		$.ajaxFileUpload({
		//处理文件上传操作的服务器端地址(可以传参数,已亲测可用)   
		url : urlParamter, 
			secureuri : false, //是否启用安全提交,默认为false    
			fileElementId : 'fileUpload', //文件选择框的id属性   
			dataType : 'text', //服务器返回的格式,可以是json或xml等   
			success : function(data, status) { //服务器响应成功时的处理函数
				if(status=="success"){
					data = eval("("+data+")");
					getSyllabus(); // 获取班级课表信息
					$.each(data,function(key,value){
					if ( key === "ERROR" ) {
						$.alert(value);
					}
					if ( key === "SUCCESS" ) {
						$.success("导入完成");
					}
					});
					loader.close();
				}else{
					$.success("服务器异常");
				}
			}, 
			error : function(data, status, e) { //服务器响应失败时的处理函数
				$.error("上传失败，请重新上传");
				location.reload();
				loader.close();
			} 
		});
	}
</script>
</html>