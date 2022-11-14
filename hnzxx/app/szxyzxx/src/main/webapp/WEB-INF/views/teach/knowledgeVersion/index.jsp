<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<%-- <%@include file="/views/embedded/knowledgeCatalog.jsp" %> --%>
<%@ include file="/views/embedded/plugin/zTree.jsp"%>
<title></title>
<style type="text/css">
.catalog .dl a{
	float : right;
}
/* .table .sorting_asc,.table .sorting_desc{
	background-position:center left;
	padding-left:20px;
} */
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="知识点" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3 class="jc_top">
							知识点版本列表
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)" class="a3"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<a href="javascript:void(0)" class="a6"
									onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加版本</a>
								<a id="downLoadExcel" class="a2" href="javascript:downLoadExcel();"><i class="fa fa-plus"></i>导出知识点</a>
							</p>
						</h3>
					</div>
					<div class="widget-head">
						<div class="row-fluid">
<%-- 						<jsp:include page="/views/embedded/plugin/resource_catalogbox.jsp"> --%>
<%-- 							<jsp:param name="stageCode" value="" /> --%>
<%-- 							<jsp:param name="subjectCode" value="" /> --%>
<%-- 							<jsp:param name="versionCode" value="" /> --%>
<%-- 							<jsp:param name="knowledgeVersionCode" value="" /> --%>
<%-- 							<jsp:param name="catalogId" value="89" /> --%>
<%-- 						</jsp:include> --%>
						<div id="catalog"></div>
						<div class="widget-container">
<!-- 							<ul id="treeZylb" class="ztree"></ul> -->
						</div>
					</div>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>名称：</span>
									<input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="" value="">
								</div>
								<div class="select_div">
									<span>学段：</span>
									<select id="stageName" data-placeholder="请选择" name="stageName" class="span13">
										<option value="">请选择</option>
										<c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="item">
											<option value="${item}" <c:if test="${knowledgeVersion.stageCode == item}">selected="selected"</c:if>>
												<jc:cache tableName="jc_stage" echoField="name" value="${item}" paramName="code"/>
											</option>
										</c:forEach>
									</select>
								</div>
								<div class="select_div">
									<span>学科：</span>
									<select id="subjectName" data-placeholder="请选择" name="subjectName" class="span13">
									</select>
								</div>
								<div class="select_div">
									<span>版本：</span>
									<select id="versionName" class="span13" name="versionName">
									</select>
								</div>
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th><input type="checkbox" id="checkAll" value="0" name="all"/></th>
											<th>名称</th>
											<th>学科</th>
											<th>学段</th>
											<th>版本</th>
											<th>说明</th>
											<th>创建时间</th>
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="knowledgeVersion_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<div id="page_div">
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
									<jsp:param name="id" value="knowledgeVersion_list_content" />
									<jsp:param name="url" value="/teach/knowledgeVersion/index?sub=list&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
							</div>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function search() {
		var val = {
				"subjectCode" : $("#subjectName").val() == "" ? null : $("#subjectName").val(), 
				"stageCode" : $("#stageName").val() == "" ? null : $("#stageName").val(), 
				"versionId" : $("#versionName").val() == "" ? null : $("#versionName").val()
		};
		var name = $("#name").val();
		if (name != null && name != "") {
			val.name = name;
		}
// 		val = $.setProperty(val);//设置排序属性
		var id = "knowledgeVersion_list_content";
		var url = "/teach/knowledgeVersion/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}
	$(function(){
		$("#stageName").chosen({search_contains: true});
		initSubject();
		//版本
		$.jcSelector("#versionName",{"tn" : "jc_textbook_version"}, "", function(data) {
			return {"val" : data.id, "title" : data.name};
		}, function() {
			$("#versionName").chosen();
		});
		$("#stageName").change(function(){
			initSubject($(this).val());
		});
// 		createKnowledgeCatalogV2("catalog",600,400,function(id){
// 			alert(id);
// 		});
		search();
// 		var options = {
// 			"sortIndex" : [2,3,6],
// 			"sortColumn":["subject_code","stage_code","create_date"],
// 			"defaultIndex" : 6,
// 			"ascending" : true,
// 			"afterHandler" : function(){
// 				search();
// 			}
// 		};
// 		$.initSortingProperty(options);
	});
	function initSubject(stageCode){
		
		//学科初始化
		$.PjSubjectSelector({
			"selector" : "#subjectName",
			"condition" : {"stageCode" : stageCode},
			"selectedVal" :  "",
			"afterHandler" : function(selector) {
				selector.trigger("liszt:updated"); 
			}
		});
	}
	//导出
	function downLoadExcel(){
		var $code = "";
		var length = $("input[name='single']:checked").length;
		if(length == 0){
			$.error("请至少选择一个知识点导出！");
			return;
		}
		$("input[name='single']:checked").each(function(index,value){
			if($code === ""){
				$code = $(value).val();
			}else{
				$code += "," + $(value).val();
			}
		});
		var url = "${ctp}/teach/catalog/exportExcel?knowledgeVersionCode=" + $code;
		 location.href = url ;
	}
	// 	加载创建对话框
	function loadCreatePage() {
		$.initWinOnTopFromLeft('创建', '${ctp}/teach/knowledgeVersion/creator', '650', '460');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/teach/knowledgeVersion/editor?id=' + id, '650', '460');
	}
	
	function catalogManage(id) {
// 		$.initWinOnTopFromLeft('知识点管理', '${ctp}/teach/knowledgeVersion/manage?id=' + id);
		var url = "${ctp}/teach/knowledgeVersion/manage?id=";
		 url = url+id;
		 location.href = url ;
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/teach/knowledgeVersion/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>