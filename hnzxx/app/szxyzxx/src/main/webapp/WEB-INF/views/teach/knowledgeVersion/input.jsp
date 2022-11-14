<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/message.css"> --%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.a{font: 500 12px/20px SimSun;color:#555555;margin:0 10px;display:inline-block;border-radius:5px;padding:0 10px;line-height:31px;}
.a.on{font-weight:bold;}
.a:hover{color:#fff;background:#e88a05;}
.chzn-container-multi .chzn-choices .search-field input{
	color: #666;
	background: transparent !important;
	border: 0 !important;
	font-family: sans-serif;
	font-size: 100%;
	height: 26px;
	padding: 5px;
	margin: 1px 0;
	outline: 0;
}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<form class="form-horizontal tan_form" id="knowledgeVersion_form" action="javascript:void(0);">
							<input type="hidden" id="id" value="${empty knowledgeVersion.id ? '' : knowledgeVersion.id}">
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>学段：
								</label>
								<div class="controls">
									<input type="hidden" id="stage">
									<input type="hidden" id="stageName" name="stageName" value="${knowledgeVersion.stageName}">
									<select onchange="customName();" id="stageCode" data-placeholder="请选择" name="stageCode" class="span13">
										<option value="">请选择</option>
										<c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="item">
											<option value="${item}" <c:if test="${knowledgeVersion.stageCode == item}">selected="selected"</c:if>>
												<jc:cache tableName="jc_stage" echoField="name" value="${item}" paramName="code"/>
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>学科：
								</label>
								<div class="controls">
									<input type="hidden" id="subjectName" name="subjectName" value="${knowledgeVersion.subjectName}">
									<select onchange="customName();" id="subjectCode" data-placeholder="请选择" name="subjectCode" class="span13">
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>版本：
								</label>
								<div class="controls">
									<input type="hidden" id="versionName" name="versionName" value="${knowledgeVersion.versionName}">
									<select onchange="customName();" id="versionId" class="span13" name="versionId">
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									<span style="color: red;">*</span>名称：
								</label>
								<div class="controls">
									<input type="text" id="name" name="name" class="span13" placeholder="名称" value="${knowledgeVersion.name}">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									说明：
								</label>
								<div class="controls">
								<input type="text" id="description" name="description" class="span13" placeholder="请对书籍版本描述" value="${knowledgeVersion.description}">
								</div>
							</div>
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label"> -->
<!-- 									<span style="color: red;">*</span>知识点： -->
<!-- 								</label> -->
<!-- 								<div class="controls"> -->
<!-- 									<select id="knowledges" name="knowledges" data-placeholder="请选择" multiple="multiple" style="height: 30px;" class="span13"> -->
<!-- 									</select> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<div class="control-group"> -->
<!-- 								<label class="control-label"> -->
<!-- 									新增知识点： -->
<!-- 								</label> -->
<!-- 								<div id="addCatalog" class="controls"> -->
<!-- 										<a href="javascript:addCatalog();"> -->
<!-- 											&nbsp;&nbsp;+&nbsp;&nbsp; -->
<!-- 										</a> -->
<!-- 										<input type="text" placeholder="请输入知识点" style="display: none;width: 310px;" id="newCatalog" class="span13"> -->
<!-- 										<a id="newA" href="javascript:saveNewCatalog();" style="display: none;color: maroon;" class="a on">确认新增</a> -->
<!-- 								</div> -->
<!-- 							</div> -->
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${knowledgeVersion.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var checker;
	$(function() {
		checker = initValidator();
		$("#subjectCode").change(function(){
			var subjectName = $("#subjectCode_chzn span").html();
			$("#subjectName").val(subjectName);
		});
		//初始化科目
		$.PjSubjectSelector({
			"selector" : "#subjectCode",
			"selectedVal" :  "${knowledgeVersion.subjectCode}",
			"condition" : {"stageCode" : "${knowledgeVersion.stageCode}"},
			"afterHandler" : function(selector) {
				customName();
			}
		});
		$("#stageCode").change(function(){
			 var stageCode = $("#stageCode").val();
			 $.PjSubjectSelector({
					"selector" : "#subjectCode",
					"condition" : {"stageCode" : stageCode},
					"selectedVal" :  "",
					"afterHandler" : function(selector) {
						selector.trigger("liszt:updated"); 
					}
				});
		 });
		//版本
		$.jcSelector("#versionId",{"tn" : "jc_textbook_version"}, "${knowledgeVersion.versionId}", function(data) {
			return {"val" : data.id, "title" : data.name};
		}, function() {
			$("#versionId").chosen();
			$("#versionId").trigger("liszt:updated"); 
		});
		
		//获取知识点
// 		 $.getKnowledgeCatalog({"id" : 1}, function(data, status) {
// 			for(var i = 0 ; i<data.length ; i++){
// 				$("#knowledges").append("<option value='" + data[i].id + "'>" + data[i].name + "</option>");
// 			}
// 			$("#knowledges").chosen();	
// 		});
		//学段初始化渲染
		$("#stageCode").chosen();
	});
	
	//自定义组合name
	function customName(){
		$("#name").val("");
		var stage = $("#stageCode_chzn span").text();
		var subject = $("#subjectCode_chzn span").text();
		var version = $("#versionId").find("option:checked").text();
		if(stage === "请选择"){
			stage = "";
		}else{
			$("#stageName").val(stage);
		}
		if(subject === "请选择"){
			subject = "";
		}else{
			$("#subjectName").val(subject);
		}
		if(version === "请选择"){
			version = "";
		}else{
			$("#versionName").val(version);
		}
		$("#name").val(stage + subject + version);
	}
	//校验
	function initValidator() {
		return $("#knowledgeVersion_form").validate({
			errorClass : "myerror",
			rules : {
				"name" : {
					required : true,
					maxlength: 50,
					remote : {
						type : "GET",
						async : false,
						url  : "${ctp}/teach/knowledgeVersion/checker",
						data : {
							'opera' : '${empty knowledgeVersion ? "save" : "update"}',
							'name' : function() {
								return encodeURI($("#name").val());
							},
							"id" : $("#id").val()
						}
					}
				},
				"stageCode" : {
					selectNone : true
				},
				"subjectCode" : {
					selectNone : true
				},
				"versionId" : {
					selectNone : true
				},
			},
			messages : {
				"name" : {
					remote: "名称已存在"
				}
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var stage = $("#stageCode_chzn span").text();
			var subject = $("#subjectCode_chzn span").text();
			var version = $("#versionId").find("option:checked").text();
			if("" === stage || "请选择" === stage){
				return;
			}
			if("" === subject || "请选择" === subject){
				return;
			}
			if("" === version || "请选择" === version){
				return;
			}
			var loader = new loadLayer();
			var $id = $("#id").val();
			var $requestData = formData2JSONObj("#knowledgeVersion_form");
			var url = "${ctp}/teach/knowledgeVersion/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/teach/knowledgeVersion/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>