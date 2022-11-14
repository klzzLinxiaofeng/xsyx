<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:include page="/views/embedded/catalog_tree.jsp" />
<link href="${pageContext.request.contextPath}/res/css/extra/add_2.css">
<style>
.lesson-item{

}
</style>
<script type="text/javascript">
	var value_div;
	var setHeight = -1;
	var setWidth = -1;
	var clickHandler;
	//选择知识点，不需要提供赛选条件(结构形式)
	function createKnowledgeCatalogV2(selector,height,width,afterClick){
		value_div = selector;
		setHeight = height;
		setWidth = width;
		clickHandler = afterClick;
		$("#" + selector).append(
			'<div class="widget-container"><div class="select_b">' +
			'<div id="catalog_div" style="display:none;" class="select_b"></div>' + 
			'<div class="widget-container"><ul id="treeCreate" class="ztree"></ul></div>'
		);
// 		$("#stageCode").chosen();
		//学科初始化
		$.PjSubjectSelector({
			"selector" : "#subjectCode",
			"selectedVal" :  "",
			"afterHandler" : function(selector) {
				
			},"isUseChosen" : false
		});
		 $("#stageCode").change(function(){
			 var stageCode = $("#stageCode").val();
			 $.PjSubjectSelector2({
					"selector" : "#subjectCode",
					"condition" : {"stageCode" : stageCode},
					"selectedVal" :  "",
					"afterHandler" : function(selector) {
// 						selector.trigger("liszt:updated"); 
					},"isUseChosen" : false
				});
		 });
		//版本
		$.jcSelector("#versionCode",{"tn" : "jc_textbook_version"}, "", function(data) {
			return {"val" : data.id, "title" : data.name};
		}, function() {
// 			$("#versionCode").chosen();
		});
	}
	$(function(){
		$(".unit-link b").click(function(){
			alert("sdf");
		});
	});
	function dblclick(id,obj){
		$(".jspContainer .on").removeClass("on");
		$(obj).addClass("on");
		clickHandler(id);
	}
	function ininTree(knowledgeVersionCode,catalogId){
		if(knowledgeVersionCode === ""){
			return;
		}
		var $requestData = {};
		$requestData.knowledgeVersionCode = knowledgeVersionCode; 
		$.get("${ctp}/teach/catalog/list/parentJson", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$("#catalog_div").empty();
				var $divInit = "<div class='unit-area'><div class='hd'><h1 style='text-indent: 0;' id='version_name'></h1></div><div class='bd jspScrollable'><div class='jspContainer' style='100%'><div class='jspPane' id='catalog_name'><ul class='unit-list mb20' id='catalog_ul'</ul></div></div></div>";
				$("#catalog_div").append($divInit);
				if(setHeight != -1){
					$(".unit-area").css("height",setHeight);
					$(".jspContainer").css("height",setHeight-70);
				}
				if(setWidth != -1){
					$(".unit-area").css("width",setWidth);
				}
				$("#catalog_div").append("<div class='clear'></div>");
				$("#catalog_div").show();
				if(data.length > 0){
					$("#version_name").text(data[0].knowledgeVersionCode);
					var $init = '<li class="unit-item" id="' + -1 + '"><a href="javaScript:void(0);" class="unit-link strong ib" title="全部"><b onclick="dblclick(-1, this);">全部 </b></a></li>';
					$("#catalog_ul").append($init);
					$.each(data,function(index,value){
						var $li_init = '<li class="unit-item" id="' + value.id + '"><a href="javaScript:void(0);" class="unit-link strong ib" title="' + value.name + '"><b onclick="dblclick(' + value.id + ', this);">' + value.name + '</b></a></li>';
						$("#catalog_ul").append($li_init);
						initSonCatalog(value.id,catalogId);
					});
					$("#" + catalogId + " b").click();
				}
			}
		});
	}
	function initSonCatalog(parentId){
		if(parentId === ""){
			return;
		}
		var $requestData = {};
		$requestData.parentId = parentId; 
		$.get("${ctp}/teach/catalog/list/sonJson", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if(data.length > 0){
					var $ul_init = "<ul class='lesson-list' id='"+parentId+"catalog_ul'</ul>";
					$("#" + parentId).append($ul_init);
					$.each(data,function(index,value){
						var $li_Init = '<li class="lesson-item" id="' + value.id + '"><a href="javaScript:void(0);" class="lesson-link ib pl5 pt5" title="' + value.name + '"><b onclick="dblclick(' + value.id + ', this);">' + value.name + '</b></a></li>';
						$("#" + parentId + "catalog_ul").append($li_Init);
						initSonCatalog(value.id);
					});
				}
			}
		});
	}
</script>
