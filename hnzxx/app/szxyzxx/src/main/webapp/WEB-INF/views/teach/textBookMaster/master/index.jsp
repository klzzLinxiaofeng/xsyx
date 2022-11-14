<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/catalog.jsp"%>
<%@ include file="/views/embedded/common.jsp"%>
<jsp:include page="/views/embedded/textBookCatalogScript.jsp"></jsp:include>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="select_div">
			<div id="dvTextBook" class="select_div">
				
				<%-- <span>学段:
				<select id="stageCode" name="stageCode" class="chzn-select"style="width:160px;" onchange="findTextBook('subjectCode');">
				<c:forEach items="${stageCodeMap}" var="map">
					<option value="${map.value }">${map.key }</option>
				</c:forEach>
				</select>
				</span>
				
				<span>科目:<select id="subjectCode" name="subjectCode" class="chzn-select"style="width:160px;"  onchange="findTextBook('publisherId');">
				<option value="">请选择</option>
				</select></span>
				
				
				
				<span>版本:<select id="publisherId" name="publisherId" class="chzn-select"style="width:160px;"  onchange="findTextBook('gradeCodeVolumn');">
				<option value="">请选择</option>
				</select></span>
				
				
				<span>年级册次:<select id="gradeCodeVolumn" name="gradeCodeVolumn" class="chzn-select"style="width:160px;">
				
				<option value="">请选择</option>
				</select></span> --%>
				
				
				 <jsp:include page="/views/embedded/textBookCatalog.jsp"></jsp:include>
				<!-- <button type="button" onclick="textBookCatalogListById('35','_dblclick','catalog_div1','a');">a列表</button> -->
				<!-- <button type="button" onclick="textBookCatalogListById('65','_dblclick','catalog_div2','b');">b列表</button>
				
				<button type="button" onclick="getLastSelectId();">id</button> -->
				
			</div>
			
		</div>
		 <div id="dvTextBookCatalog" class="select_div">
				
		 </div>
		<div class="blue" id="catalog_div1"></div>
		<div class="blue" id="catalog_div2"></div>
		
</body>
<script type="text/javascript">
	function getLastSelectId(){
		var value = $(dvTextBookCatalog).children("select:last-child").val();
		alert(value);
	}
	/* function catalog(div,type,catalogId,prefixId) {
		var stageCode='';
		var subjectCode='';
		var  gradeCode='';
		var volumn='';
		var publisherId='';
		
		textBookCatalogList(div,stageCode,gradeCode,subjectCode,volumn,publisherId,type,catalogId,'_dblclick',prefixId);
	}  */
	
 	function _dblclick(id){
		alert(id+":item.id");
	} 
/* 		function findTextBook(name) {
			 var selectVal = '#'+name;
			  $(selectVal).empty();
				 $.ajax({  
					type:"post",  
					url:"${ctp}/teach/textBookMaster/master/textBook",  
					data:{
						'stageCode':$('#stageCode').val(),
						'subjectCode':$('#subjectCode').val(),
						'gradeCode':$('#gradeCode').val(),
						'volumn':$('#volumn').val(),
						'publisherId':$('#publisherId').val(),
						'type':name 
					},
						
						
					success:function(data) {  
					var map =  eval("("+data+")");
					
					$.each(map,function(key,values){  
					$("<option value="+values+">"+key+"</option>").appendTo(selectVal);   
					});   
					}  
				});   
			} */
	 
</script>
</html>