<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	
	<div class="select_b">
		<div class="select_div">
			<div id="dvTextBook" class="select_div">
				
				<span>年级:<select id="gradeCode" name="gradeCode" class="chzn-select"style="width:160px;" onchange="findTextBook('subjectCode');">
				<c:forEach items="${gradeCodeMap}" var="map">
					<option value="${map.value }">${map.key }</option>
				</c:forEach>
				</select></span>
				<span>科目:<select id="subjectCode" name="subjectCode" class="chzn-select"style="width:160px;"  onchange="findTextBook('volumn');">
				<option value="">请选择</option>
				</select></span>
				
				<span>册数:<select id="volumn" name="volumn" class="chzn-select"style="width:160px;" onchange="findTextBook('publisherId');">
				<option value="">请选择</option>
				</select></span>
		
				<span>版本:<select id="publisherId" name="publisherId" class="chzn-select"style="width:160px;"  onchange="catalog('select','');">
				<option value="">请选择</option>
				</select></span>
				
				<%-- <jsp:include page="/views/embedded/textBookCatalog.jsp"></jsp:include> --%>
			</div>
			
		</div>
		<br/>
		<div id="catalogList_div" align="center">
		<br/>
		<br/>
		<br/>
	
			<ur  id="catalogList_ur">
				
			</ur>
		</div>
	</div>

</body>
<script type="text/javascript">
	
	function findTextBook(name) {
		 var selectVal = '#'+name;
		  $(selectVal).empty();
			 $.ajax({  
				type:"post",  
				url:"${ctp}/teach/textBookMaster/master/textBook",  
				data:{
					'stageCode':$('#stageCode').val(),
					'subjectCode':$('#subjectCode').val(),
					//'gradeCode':$('#gradeCode').val(),
					'gradeCodeVolumn':$('#gradeCodeVolumn').val(),
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
		}

	function InitSelection(){
		
		var subjectCode = $("#subjectCode").val();
		if (subjectCode != null && subjectCode != "") {
		
		}else{
			alert("请选择学科");
			return false;
		}
		var publisherId = $("#publisherId").val();
		if (publisherId != null && publisherId != "") {
		
		}else{
			alert("请选择版本");
			return false;
		}
		var gradeCode = $("#gradeCode").val();
		if (gradeCode != null && gradeCode != "") {
		
		}else{
			alert("请选择年级");
			return false;
		}
		var volumn = $("#volumn").val();
		if (volumn != null && volumn != "") {
		
		}else{
			alert("请选择册次");
			return false;
		}
		
		return  true;
	}
	
	function catalog(type,catalogId) {
		var stageCode=$('#stageCode').val();
		var subjectCode=$('#subjectCode').val();
		var  gradeCode=$('#gradeCode').val();
		var volumn=$('#volumn').val();
		var publisherId=$('#publisherId').val();
		
		textBookCatalogList(stageCode,gradeCode,subjectCode,volumn,publisherId,type,catalogId);
	} 
	
	 function textBookCatalogList(
			 stageCode,
			 gradeCode,
			 subjectCode,
			 volumn,
			 publisherId,
			 type,
			 catalogId) {

		 $("#catalogList_ur").remove();
		 
		var flag =  InitSelection();
		 if(!flag){
			 return;
		 }
		 var $ur = "<ur id='catalogList_ur'><br/><br/></ur>";
		 $("#catalogList_div").append($ur);
		 $.ajax({  
			type:"post",  
			url:"${ctp}/teach/textBookMaster/master/textBookCatalogList",  
			data:{
				'stageCode':stageCode,
				'subjectCode':subjectCode,
				'gradeCode':gradeCode,
				'volumn':volumn,
				'publisherId':publisherId,
				'catalogId':catalogId,
				 'type':type
			},
			success: function(data){
				
				var jsonObj=eval("("+data+")"); 
				
		        $.each(jsonObj, function (i, item) {
		        	if(i == 0){
		        		
		        	}else{
		        		
		        		var i = 0;
		        		var backSpace = "";
		        		var fontSize = 18;
		        		while(item.level > i){
		        			backSpace+=" ";
		        			i += 1;
		        			fontSize -= 2;
		        		}	
		        		
		        		var $li = "<li  style='font-size:"+fontSize+"px' >"
		        		$li+=item.level+"."+backSpace+item.name;
		        		$li+='</li>';
			        	$("#catalogList_ur").append($li);
		        	}
		        	
		        });      
	      },  
		});   
	} 
	
</script>
</html>