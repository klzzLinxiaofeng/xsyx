<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@include file="/views/embedded/knowledgeCatalogV2.jsp" %>
<script type="text/javascript">
	var value_div;
	var selectValueArr;
	var arrLength=0;
	//选择知识点，不需要提供赛选条件(下拉选形式)
	function createKnowledgeCatalogV1(selector,$requestData){
		value_div = selector;
		$("#" + selector).append(
			'<div class="widget-container"><div class="select_b">' +
			'<div class="select_div">' +
				'<span>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段：</span>' +
				'<select id="stageCode" onchange="initKnowledgeVersionV1()" data-placeholder="请选择" name="stageCode" class="">' +
					'<option value="">请选择</option>' +
					'<c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="item">' +
						'<option value="${item}">' +
							'<jc:cache tableName="jc_stage" echoField="name" value="${item}" paramName="code"/>' +
						'</option>' + 
					'</c:forEach>' +
				'</select>' +
			'</div>' +
			'<div class="select_div">' +
				'<span>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;科：</span>' +
				'<select id="subjectCode" onchange="initKnowledgeVersionV1()" data-placeholder="请选择" name="subjectCode" class="">' +
				'</select>' +
			'</div>' +
			'<div class="select_div">' +
				'<span>版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本：</span>' +
				'<select id="versionCode" onchange="initKnowledgeVersionV1()" class="" name="versionCode">' +
				'</select>' +
			'</div>' +
			'<div class="clear"></div>'	+
			'</div>' +
			'<div id="catalog_data" class="select_b">' +
			'<div class="select_div">' +
				'<span>知识点版本：</span>'   +
				'<select id="knowledgeVersion" onchange="initParentCatalog()" class="" name="knowledgeVersion">' +
				'</select>' +
			'</div>' +
			'<div class="select_div">' +
				'<span>父知识点：</span>'   +
				'<select id="knowledgeCatalog_0" onchange="getSonCatalog(this)" class="catalog_change" name="knowledgeCatalog">' +
				'</select>' +
			'</div>' +
			'</div>' + 
			'<div class="clear"></div>'
		);
// 		$("#stageCode").chosen();
		//学科初始化
		$.PjSubjectSelector({
			"selector" : "#subjectCode",
			"selectedVal" :  "",
			"afterHandler" : function(selector) {
				if($requestData == null ){
					$requestData = {};
				}
				var stageCode = $requestData.stageCode;
				var subjectCode = $requestData.subjectCode;
				var versionCode = $requestData.versionCode;
				var knowledgeVersionCode = $requestData.knowledgeVersionCode;
				var catalogIds = $requestData.catalogId;
				if(stageCode != null && stageCode !== "" && stageCode !== "undefined"){
					$("#stageCode option[value='" + stageCode + "']").attr("selected",true);
				}
				if(subjectCode != null && subjectCode !== "" && subjectCode !== "undefined"){
					$("#subjectCode option[value='" + subjectCode + "']").attr("selected",true);
				}
				if(versionCode != null && versionCode !== "" && versionCode !== "undefined"){
					$("#versionCode option[value='" + versionCode + "']").attr("selected",true);
				}
				if(catalogIds != null && catalogIds !== "" && catalogIds !== "undefined"){
					selectValueArr = catalogIds.split(",");
					arrLength = selectValueArr.length;
				}
				if(knowledgeVersionCode != null && knowledgeVersionCode !== "" && knowledgeVersionCode !== "undefined"){
					initKnowledgeVersionV1(knowledgeVersionCode);
				}
			},"isUseChosen" : false
		});
		 $("#stageCode").change(function(){
			 var stageCode = $("#stageCode").val();
			 $.PjSubjectSelector({
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
	//获取最终的知识点id
	function getKnowledgeCatalogId(){
		var $value = $("#" + value_div).attr("data-id");
		if($value === "undefined"){
			$value = "";
		}
		return $value;
	}
	function getAllKnowledgeCatalogId(afterHandler){
		var $value = $("#" + value_div).attr("data-id") + "";
		if($value === "undefined" || $value === ""){
			$value = "";
			afterHandler($value);
		}else{
			$.post("${ctp}/teach/catalog/parent/" + $value, {}, function(data, status) {
				if("success" === status) {
					afterHandler(data);
				}
			});
		}
	}
	function initKnowledgeVersionV1(knowledgeVersionCode){
		$("#catalog_data").show();
		var stage = $("#stageCode").val();
		var subject = $("#subjectCode").val();
		var version = $("#versionCode").val();
		var $requestData = {};
		$requestData.stageCode = stage; 
		$requestData.subjectCode = subject; 
		$requestData.versionCode = version;
		var url = "${ctp}/teach/knowledgeVersion/list/json";
		getData(url,$requestData,function(data){
			if(data.length > 0){
				$("#knowledgeVersion").empty();
				$.each(data,function(index,value){
					if(knowledgeVersionCode != null && knowledgeVersionCode !== "undefined" && knowledgeVersionCode !== ""){
						if(knowledgeVersionCode == value.code){
							$("#knowledgeVersion").append("<option selected='selected'  value='" + value.code + "'>" + value.name + "</option>")
						}else{
							$("#knowledgeVersion").append("<option  value='" + value.code + "'>" + value.name + "</option>")
						}
					}else{
						$("#knowledgeVersion").append("<option  value='" + value.code + "'>" + value.name + "</option>")
					}
				});
				if(knowledgeVersionCode === "undefined" || knowledgeVersionCode ===""){
					$("#knowledgeVersion").find("select *:first").attr("selected",true);
				}
			}else{
				$("#knowledgeVersion").empty();
				$("#knowledgeVersion").append("<option value='0'>请选择 </option>");
			}
// 			$("#knowledgeVersion").chosen();
// 			$("#knowledgeVersion").trigger("liszt:updated");
			initParentCatalog();
		});
	}
	//获取顶级知识点
	function initParentCatalog(){
		var knowledgeVersionCode = $("#knowledgeVersion").val() + "";
		if(knowledgeVersionCode === "undefined" || knowledgeVersionCode === ""){
			return;
		}
		var $requestData = {};
		$requestData.knowledgeVersionCode = knowledgeVersionCode;
		var url = "${ctp}/teach/catalog/list/parentJson"; 
		getData(url , $requestData, function(data) {
			if(data.length > 0){
				$("#knowledgeCatalog_0").empty();
				$("#knowledgeCatalog_0").append("<option value=''>请选择 </option>");
				$.each(data,function(index,value){
					if(arrLength != 0){
						if(selectValueArr[0] == value.id){
							$("#knowledgeCatalog_0").append("<option selected='selected' value='" + value.id + "'>" + value.name + "</option>")
						}else{
							$("#knowledgeCatalog_0").append("<option value='" + value.id + "'>" + value.name + "</option>")
						}
					}else{
						$("#knowledgeCatalog_0").append("<option value='" + value.id + "'>" + value.name + "</option>")
					}
				});
			}else{
				$("#knowledgeCatalog_0").empty();
 				$("#knowledgeCatalog_0").append("<option value='-1'>请选择 </option>");									
			}
// 			$("#knowledgeCatalog").chosen();
// 			$("#knowledgeCatalog").trigger("liszt:updated");
			$("#" + value_div).attr("data-id",$("#knowledgeCatalog_0 option:selected").val());
			getSonCatalog("#knowledgeCatalog_0");
		});
	}
	//获取子知识点并初始化下拉
	function getSonCatalog(obj){
		var parentId = $(obj).val();
		if(parentId === "" || parentId === "undefined"){
			var $id = $(obj).attr("id");
			if($id === "knowledgeCatalog_0"){
				$("#" + value_div).attr("data-id","");				
			}else{
				var curValue = $(obj).parent().prev().children("select").val();
				$("#" + value_div).attr("data-id",curValue);
			}
			$(obj).parent().nextAll().remove();
			return;
		}
		var $requestData = {};
		$requestData.parentId = parentId;
		var url = "${ctp}/teach/catalog/list/sonJson";
		getData(url, $requestData, function(data, status) {
			$(obj).parent().nextAll().remove();
			if(data.length > 0){
				$(obj).parent().parent().append(
					'<div class="select_div">' +
					'<span>子知识点：</span>'   +
					'<select onchange="getSonCatalog(this)" class="">'　+
					'</select>' +
					'</div>'
				);
				$(obj).parent().parent().find('select:last').attr("id" , "knowledgeCatalog_" + parentId);
				$("#knowledgeCatalog_" + parentId).append("<option value=''>请选择 </option>");
				$.each(data,function(index,value){
					$("#knowledgeCatalog_" + parentId).append("<option value='" + value.id + "'>" + value.name + "</option>")
				});
				if(arrLength != 0){
					$("#knowledgeCatalog_" + parentId).find("option[value='" + selectValueArr[selectValueArr.length - arrLength + 1] + "']").attr("selected",true);
					arrLength--;
				}
			}
			$("#" + value_div).attr("data-id",parentId);
			getKnowledgeCatalogId();
			$("#knowledgeCatalog_" + parentId).trigger("change");
		});
	}
	
	function getData(url,$requestData,afterHandler){
		$.get(url, $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				afterHandler(data);
			}
		});
	}
</script>
