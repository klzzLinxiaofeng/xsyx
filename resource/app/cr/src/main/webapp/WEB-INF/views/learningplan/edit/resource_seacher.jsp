<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>试卷列表</title>
<style>
.course-box {
	float:left;
}
</style>
</head>
<body>
	<div class="container-fluid" style="padding:0">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white" style="border:0 none;margin:0">
					<div class="wrapper-right">
						<div id="discover-courses-rows">
							<div class="ud-coursecarousel course-list-wrapper collection">
								<div id="yourLike" style="margin-bottom: 10px;">
									<div class="courses-header" style="margin:10px 0">
										<jsp:include page="./textbook.jsp" />
									</div>
									<div class="rs_platformContainer_1">
										<div class="unit-area xkzy" id="catalog_div">
										</div>
										<div class="main">
											<div class="zy_list">
												<div class="xkzy_list" id="zy_list">
												</div>
												<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
													<jsp:param name="id" value="studentAid_list_content" />
													<jsp:param name="url" value="/learningPlan/searcher?dm=${param.dm}&resType=${resType}" />
													<jsp:param name="pageSize" value="${page.pageSize}" />
												</jsp:include>
												<div class="clear"></div>
											</div>
										</div>
										<div class="clear"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	
	function catalogCallback(code, obj) {
		$(obj).parents("#acatalogList_div").find("a").removeClass("on");
		$(obj).parent().addClass("on");
		search(code);
	}
	
	function search(data) {
		var val = {};
		if(data != null && data != "" && data != "-1") {
			val.catalogCode = data;	
		}
		
		val.stageCode = $("#stageCode").val();
		val.subjectCode = $("#subjectCode").val();
		val.versionCode = $("#publisherId").val();
		var gradeCodeVolumnValue = $("#gradeCodeVolumn").val();
        var array = gradeCodeVolumnValue.split("-");
        
        if (array.length == 2) {
        	val.gradeCode = array[0];
        	val.volumnCode = array[1];
        }
        
		val.personType="${personType}";
		val.resType ="${resType}";
		
		var id = "zy_list";
		var url = "/learningPlan/searcher?dm=${dm}&unitId=${unitId}";
		myPagination(id, val, url);
	}
	
	//播放微课(弹窗)
    function previewMicro(mid) {
        var mes = "预览";
      	$.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/learningPlan/viewer?id='+mid+"?canReturn=1", '1000', '620');
    }
	
  	//播放微课(不弹窗)
    function viewMicro(id, type) {
    	var url = "";
  		if(type==4) {
  			url = "${pageContext.request.contextPath}/learningPlan/paper/viewer?paperId="+id;
  		} else {
  			url = "${pageContext.request.contextPath}/learningPlan/viewer?canReturn=true&id="+id;
  		}
  		
		$(".weike").load(url, function() {
		});
	}
    
    $(function() {
    	if($("#stageSign").val()!="" && $("#subjectSign").val()!="" && $("#versionSign").val()!="" && $("#volumnSign").val()!="") {
    		$("#stageCode").val($("#stageSign").val());
    		findTextBook('subjectCode', 'res');
    		$("#subjectCode").val($("#subjectSign").val());
    		findTextBook('publisherId', 'res');
    		$("#publisherId").val($("#versionSign").val());
    		findTextBook('gradeCodeVolumn', 'res');
    		$("#gradeCodeVolumn").val($("#volumnSign").val());
    	} else {
    		//选中第一个学段
        	$("#stageCode option:nth-child(2)").prop("selected", 'selected');
        	findTextBook('subjectCode', 'res');
        	//选中第一个科目
	    	$("#subjectCode option:nth-child(2)").prop("selected", 'selected');	
	    	findTextBook('publisherId', 'res');
	    	//选中第一个版本
	    	$("#publisherId option:nth-child(2)").prop("selected", 'selected');
	    	findTextBook('gradeCodeVolumn', 'res');
	    	//选中第一个年级册次
	    	$("#gradeCodeVolumn option:nth-child(2)").prop("selected", 'selected');
    	}
    	textbookCatalog();
    })
</script>
</html>