<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib_lads.jsp"%>
<div class="top">
	<p class="left">
		<c:if test="${!param.isMicro}">
			<a href="javascript:void(0)" data-code="-1" class='${param.resType == null || param.resType == "" ? "on" : ""}'>全部资源<span><c:if test='${param.resType == null || param.resType == ""}'>（${param.resourceCount}）</c:if></span></a> 
<%--			<a href="javascript:void(0)" data-code="1" class='${param.resType != null && param.resType == "1" ? "on" : ""}'>微课<span><c:if test='${param.resType != null && param.resType == "1"}'>（${param.resourceCount}）</c:if></span></a> --%>
			<a href="javascript:void(0)" data-code="2" class='${param.resType != null && param.resType == "2" ? "on" : ""}'>课件<span><c:if test='${param.resType != null && param.resType == "2"}'>（${param.resourceCount}）</c:if></span></a> 
			<a href="javascript:void(0)" data-code="3" class='${param.resType != null && param.resType == "3" ? "on" : ""}'>作业<span><c:if test='${param.resType != null && param.resType == "3"}'>（${param.resourceCount}）</c:if></span></a>
			<a href="javascript:void(0)" data-code="4" class='${param.resType != null && param.resType == "4" ? "on" : ""}'>试卷<span><c:if test='${param.resType != null && param.resType == "4"}'>（${param.resourceCount}）</c:if></span></a> 
			<a href="javascript:void(0)" data-code="5" class='${param.resType != null && param.resType == "5" ? "on" : ""}'>教案<span><c:if test='${param.resType != null && param.resType == "5"}'>（${param.resourceCount}）</c:if></span></a>
			<a href="javascript:void(0)" data-code="6" class='${param.resType != null && param.resType == "6" ? "on" : ""}'>素材<span><c:if test='${param.resType != null && param.resType == "6"}'>（${param.resourceCount}）</c:if></span></a>
		</c:if> 
<!-- 		<a href="javascript:void(0)">其他</a> -->
	</p>
	<p class="right">
		排序：&#12288;
<%-- 		<a class='${param.property == "COMMENT_COUNT" ? "on" : ""}' href="javascript:void(0)" data-sort="COMMENT_COUNT"> 评价<i class="fa ${param.property == 'COMMENT_COUNT' && param.ascending ? 'fa-arrow-down' : 'fa-arrow-up'}" ></i></a> --%>
		<a class='${param.property == "FAV_COUNT" ? "on" : ""}' href="javascript:void(0)" data-sort="FAV_COUNT">收藏量<i class="fa ${param.property == 'FAV_COUNT' && param.ascending ? 'fa-arrow-down' : 'fa-arrow-up'}" ></i></a>
		<a class='${param.property == "CREATE_DATE" ? "on" : ""}' href="javascript:void(0)" data-sort="CREATE_DATE">最新<i class="fa ${param.property == 'CREATE_DATE' && param.ascending ? 'fa-arrow-down' : 'fa-arrow-up'}" ></i></a>
	</p>
	<div class="clear"></div>
</div>

<script type="text/javascript">
	$(".top .left a").on("click", function(event) {
		var $this = $(this);
		var val = {};
		val.stageCode = "${param.stageCode}";
		val.subjectCode = "${param.subjectCode}";
		val.versionCode = "${param.versionCode}";
		val.gradeCode = "${param.gradeCode}";
		val.volumnCode = "${param.volumnCode}";
		val.catalogCode = "${param.catalogCode}";
		var resType = $this.attr("data-code");
		if(resType != null && resType != "" && resType != "-1") {
			val.resType = $this.attr("data-code");
		}
		var id = "zy_list";
		var url = "/resource/searcher";
		myPagination(id, val, url);
	});
	
	
	$(".top .right a").on("click", function(event) {
		var $this = $(this);
		var val = {};
		val.stageCode = "${param.stageCode}";
		val.subjectCode = "${param.subjectCode}";
		val.versionCode = "${param.versionCode}";
		val.gradeCode = "${param.gradeCode}";
		val.volumnCode = "${param.volumnCode}";
		val.catalogCode = "${param.catalogCode}";
                val.isMicro = "${param.isMicro}";
		var resType = "${param.resType}";
		if(resType != null && resType != "" && resType != "-1") {
			val.resType = resType;
		}
		val.property = $this.attr("data-sort");
		val.ascending = $this.find("i").attr("class") === "fa fa-arrow-up";
		var id = "zy_list";
		var url = "/resource/searcher";
		myPagination(id, val, url);
	});
	
	
</script>