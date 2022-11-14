<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="/views/embedded/taglib.jsp" %>
<%@ include file="/views/embedded/catalog.jsp" %>
<style>
	.km_select{
		margin-right:10px;
	}
	.km_select select{
		margin-left:10px;
	}
</style>
	<span class="km_select">&nbsp; 学段: &nbsp;
	<select id="stageCode" name="stageCode" class="chzn-select"style="width:160px;" onchange="findTextBook('subjectCode', 'res');">
	<c:forEach items="${stageCodeMap}" var="map">
		<option value="${map.value }">${map.key }</option>
	</c:forEach>
	</select>
	</span>
	
	<span class="km_select">&nbsp; 科目: &nbsp;<select id="subjectCode" name="subjectCode" class="chzn-select"style="width:160px;"  onchange="findTextBook('publisherId', 'res');">
	<option value="">请选择</option>
	</select></span>
	
	<span class="km_select">&nbsp; 版本: &nbsp;<select id="publisherId" name="publisherId" class="chzn-select"style="width:160px;"  onchange="findTextBook('gradeCodeVolumn', 'res');">
	<option value="">请选择</option>
	</select></span>
	
	<span class="km_select">册次:<select id="gradeCodeVolumn" name="gradeCodeVolumn" class="chzn-select"style="width:160px;" onchange="textbookCatalog();">
	
	<option value="">请选择</option>
	</select></span>
<script type="text/javascript">
  $(function() {
        findTextBook("stageCode", "res");
  });
  
  function textbookCatalog() {
        var gradeCodeVolumnValue = $("#gradeCodeVolumn").val();
        $("#volumnSign").val(gradeCodeVolumnValue);
        var array = gradeCodeVolumnValue.split("-");
        if (array.length == 2) {
        	//高中无gradeCode 默认为0
        	if($("#stageCode").val()=="4") {
        		array[0]="0";
        	}
        	
        	textBookCatalogListByCondition(
        		$("#stageCode").val(), 
        		$("#subjectCode").val(),
        		array[0], 
        		$("#publisherId").val(), 
        		array[1], 
        		"catalogCallback", 
        		"catalog_div",
        		"a",
        		"res_school");
        }
  }
  
  function findTextBook(name, type) {

		var selectVal = '#' + name;
		var isPublish=0;
		if("res"==type) {
			isPublish=0;
		}
		if(name=='subjectCode'){
			$('#publisherId').empty();
			$('#gradeCodeVolumn').empty();
			$("<option >请选择</option>").appendTo($('#publisherId'));
			$("<option >请选择</option>").appendTo($('#gradeCodeVolumn'));
			
			var stageCode = $('#stageCode').val();
		    var stagesize = $("#subjectCode option").size();
			
			if(stageCode=="" && stagesize>1) {
				$('#subjectCode').empty();
				$("<option >请选择</option>").appendTo($('#subjectCode'));
				return;
			}
		}else if(name=='publisherId'){
			$('#gradeCodeVolumn').empty();
			$("<option >请选择</option>").appendTo($('#gradeCodeVolumn'));
			
			var subjectCode = $('#subjectCode').val();
		    var publishersize = $("#publisherId option").size();
		    
		    if(subjectCode=="" && publishersize>1) {
				$('#publisherId').empty();
				$("<option >请选择</option>").appendTo($('#publisherId'));
				return;
			}

		}else if(name=='gradeCodeVolumn') {
			var publisherId = $('#publisherId').val();
		    var gradeCodeVolumnSize = $("#gradeCodeVolumn option").size();
		    
		    if(publisherId=="" && gradeCodeVolumnSize>1) {
				$('#gradeCodeVolumn').empty();
				$("<option >请选择</option>").appendTo($('#gradeCodeVolumn'));
				return;
			}
		}
		
		
		$("#dvTextBookCatalog").empty();
		$(selectVal).empty();
		$.ajax({
			type : "post",
			async:false,
			url : "${ctp}/teach/textBookMaster/master/textBook",
			data : {
				'stageCode' : $('#stageCode').val(),
				'subjectCode' : $('#subjectCode').val(),
				'gradeCode' : $('#gradeCode').val(),
				'volumn' : $('#volumn').val(),
				'publisherId' : $('#publisherId').val(),
				'type' : name,
				'isPublish' : isPublish
			},
			success : function(data) {
				var map = eval("(" + data + ")");
				$.each(map, function(key, values) {
					$("<option value="+values+">" + key + "</option>").appendTo(selectVal);
				});
			}
		});
		recode(name);
	}
  
  function recode(name) {
	 if(name=="subjectCode") {
		 $("#stageSign").val($("#stageCode").val());
	 } else if(name=="publisherId") {
		 $("#subjectSign").val($("#subjectCode").val());
	 } else if(name=="gradeCodeVolumn") {
		 $("#versionSign").val($("#publisherId").val());
	 }
  }
</script>