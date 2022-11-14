<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/textBookCatalogScript.jsp" %>
<%@ include file="/views/embedded/catalog.jsp" %>
<style>
.sczy_title{
	width:200px;
	display:block;
	float:right;
	height:123px;
	border: 1px solid #89C6EC;
}
.sczy a.sczy_sc{
	border: 0;
    color: #fff;
    background: #eaa018;
    width: 120px;
    height: 35px;
    margin: 7px auto 10px;
    display: block;
    border-radius: 5px;
    font-weight: bold;
    font-size: 20px;
    font-family: '微软雅黑';
    line-height: 35px;
    text-align: center;
}
a.ziyuan{
	text-align: center;
    display: block;
    font-size: 18px;
    text-decoration: underline;
}
.sczy a b{
	font-size: 40px;
    float: left;
    position: relative;
    left: 4px;
    top: -4px;
}
.table_div{margin-right:220px;display:block}
.sczy a.sczy_sc{margin-top:30px;}
</style>
	<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].userId, dm, 0)&&personType eq 'res_school'}">
<div class="sczy_title">

</div>
</c:if>
<div class="table_div">
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
				<div class="fl_list" style="" id="publisherId_div">
				
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
</div>
<script type="text/javascript">
	$(function(){
		/*最右侧的上传按钮*/
		$(".sczy_title").html("");
		var personType = "${personType}";
		if(personType == "res_school" && personType != null && personType != "" && personType != "null"){
			<%--$(".sczy_title").append("<div class='sczy'><a class='sczy_sc' href='${pageContext.request.contextPath}/resource/uploadIndex?resType=&resourceType=res_school&dm=${dm}'><b>+</b>上传资源</a>"+--%>
			<%--"<a class='ziyuan' href='${pageContext.request.contextPath}/resource/myResource?index=index&personType=res_school&dm=${dm}'>我上传的</a></div>");--%>
            $(".sczy_title").html("");
//            $(".km_table").css("width","100%");
        }else{
			$(".sczy_title").html("");
			$(".km_table").css("width","100%");
		}
		
		var $stageCode = "${param.stageCode}";
		var $subjectCode = "${param.subjectCode}";
		var $gradeCode = "${param.gradeCode}";
		var $versionCode = "${param.versionCode}";
		var $volumnCode = "${param.volumnCode}";
		var personType="${personType}"
		var isPublish=1;
		if(personType=='res_school'){
			isPublish=0;
		}
		
		$.getTextBook({
			"stageCode" : "",
			'subjectCode' : "",
			'gradeCodeVolumn' : "",
			'publisherId' : "",
			'type' : "stageCode",
			'isPublish':isPublish
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
				'type' : "subjectCode",
				'isPublish':isPublish
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
				'type' : "publisherId",
				'isPublish':isPublish
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
				"async" : false,
				'isPublish':isPublish
			}, function(data) {
				$("#gradeCode_div").html("");
				var isExcuteFirstClick = true;
				$.each(data, function(key, value) {
					var array = value.split("-");
					var $grade = array[0];
					if($grade == ""){
						$grade = 0;
					}
					$volumn = array[1];

					if(array.length == 2){
						$("#gradeCode_div").append('<a href="javascript:void(0)" data-code="' + $grade + '" data-volumn-code="' + $volumn + '">' + key + '</a>');
					}
					
					
				});
			});
			$("#publisherId_div a").removeClass("on");
			$this.addClass("on");
			if($gradeCode != null && $gradeCode != "") {
				//alert($gradeCode)
				var selectedGradeA = $("#gradeCode_div").find("a[data-code='" + $gradeCode + "']:first");
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
					volumn, "${param.callback}", "${param.catalog_div}", "a","${personType}");
			$("#gradeCode_div a").removeClass("on");
			$this.addClass("on");
			  $('#label').html("").append($("#stageCode_td .on").text()+"<span>&nbsp;&nbsp;</span>"+">"+"<span>&nbsp;&nbsp;</span>"+ $("#subjectCode_td .on").text()+"<span>&nbsp;&nbsp;</span>"+">"+"<span>&nbsp;&nbsp;</span>"+ $("#publisherId_div .on").text()+"<span>&nbsp;&nbsp;</span>"+">"+"<span>&nbsp;&nbsp;</span>"+$("#gradeCode_div .on").text());
		});
	});
	
	
// 	alert("${param.catalog_div}");
// 	$("#${param.catalog_div}").on("click", "#acatalogList_div ul li a", function() {
// 		$("#acatalogList_div ul li a").removeClass("on");
// 		$(this).addClass("on");
		
// 	});
	
</script>