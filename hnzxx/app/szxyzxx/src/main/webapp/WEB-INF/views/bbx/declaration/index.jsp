<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>班级宣言管理</title>
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
</head>
<body>
<div class="container-fluid">
	<div class="row-fluid">
		<div class="select_top">
			<div class="s1">
				<select id="teamId" name="teamId" onchange="search();"> </select>
			</div>
		</div>
	</div>
		<div class="row-fluid">
			<div class="bjxy">
				<div class="xy">
					<input type="text"  id="signature" placeholder="请输入班级宣言，少于50个字符!">
					<a href="javascript:void(0)" class="save" onclick="save();">保存</a>
				</div>
			<div class="xybj"></div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">

	   $.BbxRoleTeamAccountSelector({
		   "selector" : "#teamId",
		   "condition" : {roleType:"CLASS_MASTER"},
		   "selectedVal" : "",
		   "afterHandler" : function() {
			   search();
			}	
	   });

   //对当前登录人员管理的班级的班级宣言进行回选
   function search(){
	   var teamId = $("#teamId").val();
		   $.post("${ctp}/bbx/declaration/list", {"teamId":teamId}, function(data, status) {
				if ("success" === status) {
					data = eval("(" + data + ")");
					$("#signature").val(data.signature);
				}
			});
			
		
   }
   
   //对班级宣言进行保存
   function save(){
	   var teamId = $("#teamId").val();
	   var signature = $("#signature").val();
	   if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级!");
		}else  if(signature===""){
		   $.error("请设置班级宣言!");
		   return;
	   }else{
		   if(signature.length>50){
			   $.error("所设置的班级宣言超过50个字符，请重新设置!");
			   return;
		   }else{
			   
			   $.post("${ctp}/bbx/declaration/save", {"teamId":teamId,"signature" : signature}, function(data, status) {
					if ("success" === status) {
						if ("success" === data) {
							$.success("保存成功");
							
						} else {
							$.error("保存失败", 1);
						}
					}
				});
		   }
	   }
	   
	   
   }
   
   
   
</script>


</html>