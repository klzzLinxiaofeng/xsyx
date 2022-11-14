<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp"%>
<table class="table km_table">
	<tbody>
		<tr>
			<td class="t1">学段</td>
			<td id="stageCode_td">
				<c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="item" varStatus="sta">
					<a href="javascript:void(0)" <c:if test="${sta.index == 0}">class="on"</c:if> data-code="${item}"><jc:cache tableName="jc_stage" echoField="name" value="${item}" paramName="code"/></a>
				</c:forEach>
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
				<div class="fl_list" style="height: 24px;" id="versionCode_div">
				
				</div> 
<!-- 				<a class="zhankai">展开 <i class="fa fa-angle-up"></i></a> -->
			</td>
		</tr>
		<tr>
			<td class="t1">知识点版本</td>
			<td>
				<div class="fl_list" id="catalogVersion_div">
				
				</div> 
<!-- 				<a class="shouqi">收起 <i class="fa fa-angle-down"></i></a> -->
			</td>
		</tr>
	</tbody>
</table>
<script type="text/javascript">
	var stage;
	var subject;
	var version;
	var catalogId = "${param.catalogId}";
	if(catalogId == null && catalogId === "" && catalogId === "undefined"){
		catalogId = -1;
	}
	$(function(){
		initData();
		
		$(".km_table #stageCode_td").on("click","a",function(){
			$(this).addClass("on").siblings().removeClass("on");
			var curStage = $(this).attr("data-code");
			stage = curStage;
			changeSubjcet(stage); 
		});
		$(".km_table #subjectCode_td").on("click","a",function(){
			$(this).addClass("on").siblings().removeClass("on");
			var curSubject = $(this).attr("data-code");
			subject = curSubject;
			changeVersion(stage,subject);
		});
		$(".km_table #versionCode_div").on("click","a",function(){
			$(this).addClass("on").siblings().removeClass("on");
			var curVersion = $(this).attr("data-code");
			version = curVersion;
			changeVersion(stage,subject,version);
		});
		$(".km_table #catalogVersion_div").on("click","a",function(){
			$(this).addClass("on").siblings().removeClass("on");
			var curCatalogVersion = $(this).attr("data-code");
			
			ininTree(curCatalogVersion,catalogId);
		});
	});
	function initData(){
		initSubject();
	}
	function initSubject(){
		var stageCode = "${param.stageCode}";
		if(stageCode != null && stageCode !== "" && stageCode !== "undefined"){
			$(".km_table #stageCode_td a[data-code='" + stageCode + "']").addClass("on").siblings().removeClass("on");	
		}else{
			stageCode = $("#stageCode_td a.on").attr("data-code");
		}
		stage = stageCode;
		var condition = {};
		condition.stageCode = stageCode;
		$.getPjSubject(condition, function(data) {
			$("#subjectCode_td").empty();
			$.each(data, function(index, value) {
				if(index == 0){
					$("#subjectCode_td").append(
						'<a href="javascript:void(0);" data-code="' + value.code + '" class="on">' + value.name + '</a>'
					);
					subject = value.code;
				}else{
					$("#subjectCode_td").append(
							'<a href="javascript:void(0);" data-code="' + value.code + '">' + value.name + '</a>'	
					);
				}
			});
			var subjectCode = "${param.subjectCode}";
			if(subjectCode != null && subjectCode !== "" && subjectCode !== "undefined"){
		 		$(".km_table #subjectCode_td a[data-code='" + subjectCode + "']").addClass("on").siblings().removeClass("on");
		 		subject = subjectCode;
	// 			$(".km_table #subjectCode_td a[data-code='" + subjectCode + "']").trigger("click");	
			}
		});
		initVersion();
	}
	function initVersion(){
		var $request = {"tn" : "jc_textbook_version"};
		$.getDataFromJcCache($request,function(data){
			$("#versionCode_div").empty();
			$.each(data, function(index, value) {
				if(index == 0){
					$("#versionCode_div").append(
						'<a href="javascript:void(0);" data-code="' + value.id + '" class="on">' + value.name + '</a>'
					);
					version = value.code;
				}else{
					$("#versionCode_div").append(
							'<a href="javascript:void(0);" data-code="' + value.id + '">' + value.name + '</a>'		
					);
				}
			});
			var versionCode = "${param.versionCode}";
			if(versionCode != null && versionCode !== "" && versionCode !== "undefined"){
				$(".km_table #catalogVersion_div a[data-code='" + versionCode + "']").addClass("on").siblings().removeClass("on");
				version = versionCode;
			}
		});
		initCatalogVersion();
	}
	function initCatalogVersion(){
		var $requestData = {};
		var subjectCode = "${param.subjectCode}";
		if(subjectCode != null && subjectCode !== "" && subjectCode !== "undefined"){
	 		subject = subjectCode;
		}
		$requestData.stageCode = stage; 
		$requestData.subjectCode = subject; 
		$requestData.versionCode = version; 
		$.get("${ctp}/teach/knowledgeVersion/list/json", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$("#catalogVersion_div").empty();
				$.each(data, function(index, value) {
					if(index == 0){
						$("#catalogVersion_div").append(
							'<a href="javascript:void(0)" data-code="' + value.code + '" class="on">' + value.name + '</a>'
						);
						ininTree(value.code,catalogId);
					}else{
						$("#catalogVersion_div").append(
								'<a href="javascript:void(0)" data-code="' + value.code + '">' + value.name + '</a>'		
						);
					}
				});
			}
			var knowledgeVersionCode = "${param.knowledgeVersionCode}";
			if(knowledgeVersionCode != null && knowledgeVersionCode !== "" && knowledgeVersionCode !== "undefined"){
	 			$(".km_table #catalogVersion_div a[data-code='" + knowledgeVersionCode + "']").addClass("on").siblings().removeClass("on");	
// 				ininTree(knowledgeVersionCode,catalogId);
				
			}
		});
	}
	function changeSubjcet(curValue){
		var condition = {};
		condition.stageCode = curValue;
		$.getPjSubject(condition, function(data) {
			$("#subjectCode_td").empty();
			$.each(data, function(index, value) {
				if(index == 0){
					$("#subjectCode_td").append(
						'<a href="javascript:void(0);" data-code="' + value.code + '" class="on">' + value.name + '</a>'	
					);
					subject = value.code;
				}else{
					$("#subjectCode_td").append(
							'<a href="javascript:void(0);" data-code="' + value.code + '">' + value.name + '</a>'		
					);
				}
			});
			changeVersion(curValue,subject); 
		});
	}
	function changeVersion(curValue,curSubject){
		var $request = {"tn" : "jc_textbook_version"};
		$.getDataFromJcCache($request,function(data){
			$("#versionCode_div").empty();
			$.each(data, function(index, value) {
				if(index == 0){
					$("#versionCode_div").append(
						'<a href="javascript:void(0);" data-code="' + value.id + '" class="on">' + value.name + '</a>'	
					);
					version = value.code;
				}else{
					$("#versionCode_div").append(
							'<a href="javascript:void(0);" data-code="' + value.id + '">' + value.name + '</a>'		
					);
				}
			});
			changeCatalogVersion(curValue,curSubject,version); 
		});
	}
	function changeCatalogVersion(curValue,curSubject,curVersion){
		var $requestData = {};
		$requestData.stageCode = curValue; 
		$requestData.subjectCode = curSubject; 
		$requestData.versionCode = curVersion; 
		$.get("${ctp}/teach/knowledgeVersion/list/json", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$("#catalogVersion_div").empty();
				if(data.length > 0){
					$.each(data, function(index, value) {
						if(index == 0){
							$("#catalogVersion_div").append(
								'<a href="javascript:void(0)" data-code="' + value.code + '" class="on">' + value.name + '</a>'		
							);
							ininTree(value.code,catalogId);
						}else{
							$("#catalogVersion_div").append(
									'<a href="javascript:void(0)" data-code="' + value.code + '">' + value.name + '</a>'		
							);
						}
					});
				}
				ininTree(-1,catalogId);
			}
		});
	}
</script>