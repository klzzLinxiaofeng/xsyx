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
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教材列表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="select_b">
			<div class="select_div">
				<span></span>

				<span>学段:
				<select id="stageCode" name="stageCode" class="chzn-select"style="width:160px;" onchange="findTextBook('subjectCode');">
				<c:forEach items="${stageCodeMap}" var="map">
					<option value="${map.value }">${map.key }</option>
				</c:forEach>
				</select>
				</span>
				<span>科目:<select id="subjectCode" name="subjectCode" class="chzn-select"style="width:160px;"  onchange="findTextBook('gradeCode');">
				<option value="">请选择</option>
				</select></span>
				<span>年级:<select id="gradeCode" name="gradeCode" class="chzn-select"style="width:160px;" onchange="findTextBook('volumn');">
				<option value="">请选择</option>
				</select></span>
				<span>册数:<select id="volumn" name="volumn" class="chzn-select"style="width:160px;" onchange="findTextBook('publisherId');">
				<option value="">请选择</option>
				</select></span>
				<span>版本:<select id="publisherId" name="publisherId" class="chzn-select"style="width:160px;">
				<option value="">请选择</option>
				</select></span>
				
			</div>
			<button type="button" class="btn btn-primary" onclick="search()">查询</button>
			<div class="clear"></div>
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
				'gradeCode':$('#gradeCode').val(),
				'volumn':$('#volumn').val(),
				'publisherId':$('#publisherId').val(),
				'type':name 
			},
				
				
			success:function(data) {  
			var map =  eval("("+data+")");
			
			$.each(map,function(key,values){  
			$("<option value="+values+">"+key+"</option>").appendTo(selectVal);   
			}  
			);   
			}  
		});   
	}

	function findTextBookCatalog(id) {
		alert('id:'+id);
		  $.ajax({
			    type: 'post',
			    url: "${ctp}/teach/textBookMaster/master/textBookCatalog",
			    cache: false,
			    data: {"id":id},
			    dataType: 'json',
			    success: function(data){//roomList
			    jQuery.each(data.textbookCatalogVoList, function(i,item){
			    	alert(i);
			    alert(item.id+","+item.name);
			    });
			    },
			    error: function(){
			    return;
			    }
			    });
	}
</script>
</html>