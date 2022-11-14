<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<div class="top">
	<p class="left">
		<b>搜索结果</b>（${param.resourceCount}个）
	</p>
	<p class="right">
		排序：&#12288;
		<a class='${param.property == "FAV_COUNT" ? "on" : ""}' href="javascript:void(0)" data-sort="FAV_COUNT">收藏量<i class="fa ${param.property == 'FAV_COUNT' && param.ascending ? 'fa-arrow-down' : 'fa-arrow-up'}" ></i></a>
		<a class='${param.property == "CREATE_DATE" ? "on" : ""}' href="javascript:void(0)" data-sort="CREATE_DATE">最新<i class="fa ${param.property == 'CREATE_DATE' && param.ascending ? 'fa-arrow-down' : 'fa-arrow-up'}" ></i></a>
	</p>
	<div class="clear"></div>
</div>

<script type="text/javascript">
	$(".top .right a").on("click", function(event) {
		var $this = $(this);
		var val = {};
		val.title = "${param.title}";
		val.property = $this.attr("data-sort");
		val.ascending = $this.find("i").attr("class") === "fa fa-arrow-up";
        val.resType = "${param.resType}";
        val.personType="${personType}";
        val.stageCode = "${param.stageCode}";
		val.subjectCode = "${param.subjectCode}";
		val.versionCode = "${param.versionCode}";
		val.gradeCode = "${param.gradeCode}";
		val.volumnCode = "${param.volumnCode}";
		var id = "zy_list";
		var url = "/learningPlan/searcher";
		myPagination(id, val, url,function(){
            $("#zy_list .item-title a").each(function() {
                var ptext = $.trim($(this).text().toLowerCase());
                var rtext = "<font color=\"red\">" + val.title + "</font>";
                var ftext = ptext.replace(val.title, rtext);
                $(this).html(ftext);
            });
        });
	});
</script>