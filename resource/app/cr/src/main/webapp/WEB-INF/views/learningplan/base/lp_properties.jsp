<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>导学案属性</title>
</head>
<body>
<div class="container-fluid dxa">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets white">
				<div class="content-widgets">
					<div class="widget-container" style="padding:20px 0 0;">
						<div class="form-horizontal" >
							<!-- 校本资源库 -->
							<div class="zy_div_res" style="display:none">
								<div class="controls" style="margin-left: 54px;margin-bottom:20px;">
                   					<jsp:include page="/views/embedded/textBookCatalog.jsp">
                   						<jsp:param value="res" name="type"/>
                   					</jsp:include>	
               					</div>
               					<div class="control-group" id="upload_textbookcatalog">
					                <label class="control-label">
					                     <span class="red">*</span>目录:
					                </label>
					                <div id="dvTextBookCatalog" class="select_div controls" style="margin-left:120px;">
					                </div>
					            </div>
               					<div class="control-group">
								<label class="control-label"><span class="red">*</span>标题:</label>
								<div class="controls">
									<input type="text" class="span4" id="res_title" maxlength="20" value="${learningPlan.title }"><p style="display:inline;margin-left:20px;color:#ccc">最大字数为<span style="color:#F55050;padding:0 5px">20</span>！</p>
	                            </div>
							</div>
							<div class="control-group">
								<label class="control-label">提要:</label>
								<div class="controls">
									<textarea class="span4" style="height: 177px; margin: 0px; width: 475px;" id="res_description" >${learningPlan.description }</textarea>
	                            </div>
							</div>
							<div class="control-group">
								<label class="control-label"></label>
								<div class="controls">
									<button class="btn btn-primary" onclick="modifyLearningPlan('res');">确定</button>
	                            </div>
							</div>
							</div>
							<!-- 个人资源库 -->
							<div class="zy_div" style="display:none">
							<div class="control-group">
								<label class="control-label"><span class="red">*</span>科目:</label>
								<div class="controls">
									<select class="span2" id="per_subjectCode">
										<c:forEach items="${subjectList }" var="subject">
											<option value="${subject.code }">${subject.name }</option>
										</c:forEach>
									</select>
	                            </div>
							</div>
               					<div class="control-group">
								<label class="control-label"><span class="red">*</span>标题:</label>
								<div class="controls">
									<input type="text" class="span4" id="title" maxlength="20" value="${learningPlan.title }"><p style="display:inline;margin-left:20px;color:#ccc">最大字数为<span style="color:#F55050;padding:0 5px">20</span>！</p>
	                            </div>
							</div>
							<div class="control-group">
								<label class="control-label">提要:</label>
								<div class="controls">
									<textarea class="span4" style="height: 177px; margin: 0px; width: 475px;" id="description">${learningPlan.description }</textarea>
	                            </div>
							</div>
							<div class="control-group">
								<label class="control-label"></label>
								<div class="controls">
									<button class="btn btn-primary" onclick="modifyLearningPlan('person');">确定</button>
	                            </div>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	var catalogEnd = 0;
	$(function(){
		var type = '${type}';
		if("res"==type) {
			$(".zy_div_res").show();
			initSelect();
		} else {
			$(".zy_div").show();
			$("#per_subjectCode").val('${learningPlan.subjectCode}');
		}
	});
	
	function initSelect(){
    	$("#stageCode").val('${cr.stageCode}');
    	findTextBook('subjectCode', 'res');
    	$("#subjectCode").val('${cr.subjectCode}');
    	findTextBook('publisherId', 'res');
    	$("#publisherId").val('${cr.versionCode}');
    	findTextBook('gradeCodeVolumn', 'res');
    	$("#gradeCodeVolumn").val('${cr.gradeCode}'+"-"+'${cr.volumnCode}');
    	
    	if('${cr.catalogCode}'!="") {
    		var level = '${level}'
			$.ajax({
				type : 'post',
				url : "${ctp}/teach/textBookMaster/master/resTextCatalogList/"+'${cr.catalogCode}',
				cache : false,
				data : {},
				dataType : 'json',
				success : function(data) {//roomList
					for(var i=0; i<=level; i++) {
						jQuery.each(data, function(index, item) {
							if (item.level==i) {
								if(item.selected==1) {
									if(i==0) {
										findResTextBookCatalog($("#gradeCodeVolumn"), '0', '0');
										$("#catalog1").val(item.id);
									} else {
										findResTextBookCatalog($("#catalog"+i), '1', i);
										$("#catalog"+(i+1)).val(item.id);
									}
								}
							}
						});
					}
				},
				error : function() {
					return;
				}
			});
    	}
    }
	
	function modifyLearningPlan(type) {
		var data = {};
		data["lpId"] = "${learningPlan.id}";
		data["id"] = "${cr.id}";
		data["type"] = type;
		var title = "";
		
        if("person"==type) {
        	title = $("#title").val();
			data["subjectCode"] = $("#per_subjectCode").val();
			data["description"] = $("#description").val();
		}else {
			title = $("#res_title").val();
			data["description"] = $("#res_description").val(); 
			data["version"] = $("#publisherId").val()
			data["stageCode"] = $("#stageCode").val();
	        data["stageName"] = $("select[id=stageCode] option:selected").text();
			data["subjectCode"] = $("#subjectCode").val();
			data["subjectName"] = $("select[id=subjectCode] option:selected").text();
	        data["versionName"] = $("select[id=publisherId] option:selected").text();
	        
	        var gradeCodeVolumnValue = $("#gradeCodeVolumn").val();
	        var array = gradeCodeVolumnValue.split("-");
	        if (array.length == 2) {
	        	data["gradeCode"] = array[0];
	        	data["volumn"] = array[1];
	        }
	        
	        var aa = $("#dvTextBookCatalog").children("select").length;
	        var catalogTemp;
	        for (var num = aa; num >= 1; num--) {
	            var catalogname = "catalog" + num;
	            catalogTemp = $("#" + catalogname).val();
	            if (Number(catalogTemp) > 0) {
	                catalogEnd = catalogTemp;
	                catalogEnd = $("#" + catalogname).find("option:selected").attr("data-code");
	                break;
	            } else {
	                catalogEnd = 0;
	            }
	        }
	        
	        data["catalogCode"] = catalogEnd;
	        
	        if (data["volumn"] == null || data["volumn"] == "") {
	            $.alert("请选择教材信息");
	            return;
	        }
	        if (data["catalogCode"] == null || data["catalogCode"] == "") {
	            $.alert("选择教材目录");
	            return;
	        }
		}

        title = title.replace(/(^\s*)|(\s*$)/g, "");
        
        if (title == null || title == "") {
            $.alert("请输入标题");
            return;
        }
        
        if (data["subjectCode"] == null || data["subjectCode"] == "") {
            $.alert("选择科目");
            return;
        }
        
        var loader = new loadDialog();
        loader.show();
        $.ajax({
            url: "${pageContext.request.contextPath}/learningPlan/modify",
            type: "POST",
            data: {
                "catalogResource": JSON.stringify(data),
                "title":title
            },
            async: true,
            success: function() {
            	$(".lpTitle",parent.document).text(title);
            	$.success("设置成功");
            	loader.close();	
            	$.closeWindow();
            }
        });
        
    }
</script>
</body>
</html>