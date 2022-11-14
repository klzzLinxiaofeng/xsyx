<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/textBookCatalogScript.jsp" %>
<%@ include file="/views/embedded/catalog.jsp" %>
<table class="table km_table">
	<tbody>
		<tr>
			<td class="t1">学段</td>
			<td id="stageCode_td">
			
			</td>
		</tr>
		<tr>
			<td class="t1">科目</td>
			<td id="subjectCode_td">
			</td>
		</tr>
		<tr>
			<td class="t1">版本</td>
			<td>
				<div class="fl_list" style="height: 24px;" id="publisherId_div">
				
				</div> 
<!-- 				<a class="zhankai">展开 <i class="fa fa-angle-up"></i></a> -->
			</td>
		</tr>
		<tr>
			<td class="t1">册次</td>
			<td>
				<div class="fl_list" id="gradeCode_div">
				
				</div> 
<!-- 				<a class="shouqi">收起 <i class="fa fa-angle-down"></i></a> -->
			</td>
		</tr>
	</tbody>
</table>
<script type="text/javascript">
	$(function(){
		
		var $stageCode = "${param.stageCode}";
		var $subjectCode = "${param.subjectCode}";
		var $gradeCodeParam = "${param.gradeCode}";
		var $versionCode = "${param.versionCode}";
		var $volumnCode = "${param.volumnCode}";
		
		
 		//alert($stageCode + "_" + $subjectCode + "_" + "_" + $versionCode + "_" + $volumnCode);
		$.getTextBook({
			"stageCode" : "",
			'subjectCode' : "",
			'gradeCodeVolumn' : "",
			'publisherId' : "",
			'type' : "stageCode"
		}, function(data) {
			$.each(data, function(key, value) {
				if(value != "") {
					$("#stageCode_td").append('<a href="javascript:void(0)" data-code="' + value + '">' + key + '</a>');
				}
			});
			
			if($stageCode != null && $stageCode != "") {
				var selectedStageCodeA = $("#stageCode_td").find("a[data-code='" + $stageCode + "']");
				if(selectedStageCodeA.length > 0) {
					selectedStageCodeA.click();
				} else {
					$("#stageCode_td a:first").click();
				}
			} else {
				$("#stageCode_td a:first").click();
			}
			
		});
		
		$("#stageCode_td").on("click", "a", function(event) {
			var $this = $(this);
			var stageCode = $this.attr("data-code");
			$.getTextBook({
				"stageCode" : stageCode,
				'subjectCode' : "",
				'gradeCodeVolumn' : "",
				'publisherId' : "",
				'type' : "subjectCode"
			}, function(data) {
				$("#subjectCode_td").html("")
				$.each(data, function(key, value) {
					if(value != "") {
						$("#subjectCode_td").append('<a href="javascript:void(0)" data-code="' + value + '">' + key + '</a>');
					}
				});
				
				if($subjectCode != null && $subjectCode != "") {
					var selectedSubjectA = $("#subjectCode_td").find("a[data-code='" + $subjectCode + "']");
					if(selectedSubjectA.length > 0) {
						selectedSubjectA.click();	
					} else {
						$("#subjectCode_td a:first").click();
					}
				} else {
					$("#subjectCode_td a:first").click();
				}
				
			});
			$("#stageCode_td a").removeClass("on");
			$this.addClass("on");
		});
		
		$("#subjectCode_td").on("click", "a", function(event) {
			var $this = $(this);
			var stageCode = $("#stageCode_td .on").attr("data-code");
			var subjectCode = $this.attr("data-code");
			$.getTextBook({
				"stageCode" : stageCode,
				'subjectCode' : subjectCode,
				'gradeCodeVolumn' : "",
				'publisherId' : "",
				'type' : "publisherId"
			}, function(data) {
				$("#publisherId_div").html("");
				$.each(data, function(key, value) {
					if(value != "") {
						$("#publisherId_div").append('<a href="javascript:void(0)" data-code="' + value + '">' + key + '</a>');
					}
				});
				
				if($versionCode != null && $versionCode != "") {
					var selectedVersionA = $("#publisherId_div").find("a[data-code='" + $versionCode + "']");
					if(selectedVersionA.length > 0) {
						selectedVersionA.click();	
					} else {
						$("#publisherId_div a:first").click();
					}
				} else {
					$("#publisherId_div a:first").click();
				}
				
			});
			$("#subjectCode_td a").removeClass("on");
			$this.addClass("on");
		});
		
		$("#publisherId_div").on("click", "a", function(event) {
			var $this = $(this);
			var stageCode = $("#stageCode_td .on").attr("data-code");
			var subjectCode = $("#subjectCode_td .on").attr("data-code");
			var publisherId = $this.attr("data-code");
			$.getTextBook({
				"stageCode" : stageCode,
				'subjectCode' : subjectCode,
				'gradeCodeVolumn' : "",
				'publisherId' : publisherId,
				'type' : "gradeCodeVolumn",
				"async" : false
			}, function(data) {
				$("#gradeCode_div").html("");
				var isExcuteFirstClick = true;
				$.each(data, function(key, value) {
					var array = value.split("-");
					$gradeCode = array[0];
					if($gradeCode == ""){
						$gradeCode = 0;
					}
					$volumn = array[1];

					if(array.length == 2){
						$("#gradeCode_div").append('<a href="javascript:void(0)" data-code="' + $gradeCode + '" data-volumn-code="' + $volumn + '">' + key + '</a>');
					}
					
					
				});
			});
			$("#publisherId_div a").removeClass("on");
			$this.addClass("on");
			if($gradeCode != null && $gradeCode != "") {
				var selectedGradeA = $("#gradeCode_div").find("a[data-code='" + $gradeCodeParam + "']:first");
				if(selectedGradeA.length > 0) {
					selectedGradeA.click();	
				} else {
					$("#gradeCode_div a:first").click();
				}
			} else {
				$("#gradeCode_div a:first").click();
			}
			
		});
		
		$("#gradeCode_div").on("click", "a", function() {
			var stageCode = $("#stageCode_td .on").attr("data-code");
			var subjectCode = $("#subjectCode_td .on").attr("data-code");
			var publisherId = $("#publisherId_div .on").attr("data-code");
			var $this = $(this);
			var gradeCode =  $this.attr("data-code");
			var volumn =  $this.attr("data-volumn-code");
// 			alert(stageCode + "_" + subjectCode + "_" + publisherId + "_" + gradeCode + "_" + volumn);
			textBookCatalogListByCondition(
					stageCode, 
					subjectCode,
					gradeCode, 
					publisherId, 
					volumn, "${param.callback}", "${param.catalog_div}", "a");
			$("#gradeCode_div a").removeClass("on");
			$this.addClass("on");
		});
	});
	
	
// 	alert("${param.catalog_div}");
// 	$("#${param.catalog_div}").on("click", "#acatalogList_div ul li a", function() {
// 		$("#acatalogList_div ul li a").removeClass("on");
// 		$(this).addClass("on");
		
// 	});
	
</script>