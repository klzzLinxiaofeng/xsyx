<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>屏幕权限管理</title>
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">

<style type="text/css">
.btn{
    font-weight: bold;margin: 10px auto 0px;display: block;width: 55px;
}
#digit{
  position: relative;
  top: -173px;
    max-height: 34px;
  margin: 0 16.5%;
  overflow: hidden;
  display: block;
}
</style>
</head>
<script type="text/javascript">

var clickId = "";
$(function(){
	$("#quanxian li").on("click",".limit",function(){
		$("#quanxian li .limit").removeClass('on');//创建类名
		var i=$(this).parent().index();
		clickId=i;
		if(i==2){
			$("#digit input").focus();
		}else{
			$("#digit input").val("");
		}
		$("#quanxian li .limit").each(function(){//遍历
			$(this).children('img').attr('src','${ctp}/res/css/bbx/images/wei.png');//清除样式
		});
		var imgSrc = $(this).children('img').attr('src','${ctp}/res/css/bbx/images/selected.png');//替换并添加样式
		$(this).addClass('on');//添加类名

});

	if($(".authority").width()<1210){
		$(".authority ul li").addClass("clear");
	}
	$(window).resize(function(){
	  if($(".authority").width()<1210){
			$(".authority ul li").addClass("clear");
		}
	else{
		$(".authority ul li").removeClass("clear");
	}
});
	
})

</script>
<body>
<div class="container-fluid">
		<div class="row-fluid ">
</div>
		<div class="row-fluid" style="margin-top:15px;">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<div class="select_top" style="margin:0">
							<div class="s1 s1_bg">
								<select id="teamId" name="teamId" class="chzn-select" style="width: 120px;" onchange="search();"></select>
							</div>
						</div>
					</div>
					<div class="content-widgets">
						<div class="authority">
							<ul id="quanxian">
								<li>
									<img src="${ctp}/res/css/bbx/images/open.jpg">
									<p>可自由编辑班级大屏展示端的内容</p>
									<div class="limit">
									   <img src="${ctp}/res/css/bbx/images/wei.png" id="quanx" >
									</div>
								</li>
								<li >
									<img src="${ctp}/res/css/bbx/images/no_open.jpg">
									<p>大屏展示端不可编辑或添加内容</p>
									<div class="limit">
									  <img src="${ctp}/res/css/bbx/images/wei.png"  id="quanx2">
									</div>
								</li>
								<li >
									<img src="${ctp}/res/css/bbx/images/password.jpg">
									<p>输入6位数字密码才能添加或编辑内容</p>
									<div class="limit">
										<img src="${ctp}/res/css/bbx/images/wei.png"  id="quanx3">
									</div>
									<div id="digit"><input placeholder="请输入6位数字密码" class="left_red {required:true,isDigits:true,maxlength:6}"  type="text" id="accessCode" name="accessCode"  onfocus="getFocus();"></div>
								</li>
							</ul>
							<div class="clear"></div>
						</div>

						<button type="button" class="btn btn-warning" onclick="save()">保存</button>

					</div>

				</div>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
   $(function(){
	   $.BbxRoleTeamAccountSelector({
		   "selector" : "#teamId",
		   "condition" : {roleType:"CLASS_MASTER"},
		   "selectedVal" : "",
		   "afterHandler" : function() {
			   search();
			}	
	   });
	   
   });

   
   
   //对当前登录人员管理的班级的屏幕权限进行回选
   var accessCode1 = "";
   function search(){
	   var teamId = $("#teamId").val();
	   $.post("${ctp}/bbx/screenPermission/list", {"teamId":teamId}, function(data, status) {
			if ("success" === status) {
				data = eval("(" + data + ")");
				if(data.accessType==="0"){
					clickId = "0";
					$("#digit input").val("");
					$("#quanx").attr('src','${ctp}/res/css/bbx/images/selected.png');
					$("#quanx2").attr('src','${ctp}/res/css/bbx/images/wei.png');
					$("#quanx3").attr('src','${ctp}/res/css/bbx/images/wei.png');
					
				}else if(data.accessType==="1"){
					clickId = "2";
					accessCode1 = data.accessCode;
					$("#digit input").val(data.accessCode);
					$("#quanx3").attr('src','${ctp}/res/css/bbx/images/selected.png');
					$("#quanx").attr('src','${ctp}/res/css/bbx/images/wei.png');
					$("#quanx2").attr('src','${ctp}/res/css/bbx/images/wei.png');
				}else if(data.accessType==="2"){
					clickId="1";
					$("#digit input").val("");
					$("#quanx2").attr('src','${ctp}/res/css/bbx/images/selected.png');
					$("#quanx").attr('src','${ctp}/res/css/bbx/images/wei.png');
					$("#quanx3").attr('src','${ctp}/res/css/bbx/images/wei.png');
				}
			}
		});
   }
   
   
   //点击输入密码框时获得焦点事件
   function getFocus(){
	   	clickId = "2";
		$("#quanx3").attr('src', '${ctp}/res/css/bbx/images/selected.png');
		$("#quanx").attr('src', '${ctp}/res/css/bbx/images/wei.png');
		$("#quanx2").attr('src', '${ctp}/res/css/bbx/images/wei.png');

	}

	//对屏幕权限进行保存
	function save() {
		var teamId = $("#teamId").val();
		var accessType = "";
		if (clickId == "0") {
			accessType = "0";
		} else if (clickId == "1") {
			accessType = "2";
		} else if (clickId == "2") {
			accessType = "1";
		}

		var accessCode = $("#accessCode").val();
		if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级!");
		}else if (accessType === "") {
			$.error("请选择操作权限!");
		} else {
             if(accessType==="1"){
            	 if(accessCode===""){
            		 $.error("请输入密码!")
            		 return;
            	 }else{
//             		 if(accessCode != accessCode1 ){
            			 var reg = new RegExp("^[0-9]*$"); 
                 		if(!reg.test(accessCode)){ 
                 			$.error("请输入数字0-9组成的数字作为密码 !");
                 			return;
                 			 } 
            			 if(accessCode.length>6){
            			 $.error("输入的密码已超过6个字符,请重新输入!");
            			 return;
            	     	 }
            			 
            			 if(accessCode.length<6){
                			 $.error("输入的密码少于6个字符,请重新输入!");
                			 return;
                	     	 }
//             		 }else{
	            		 $.post("${ctp}/bbx/screenPermission/save", {
	         				"teamId" : teamId,
	         				"accessType" : accessType,
	         				"accessCode" : accessCode
	         			}, function(data, status) {
	         				if ("success" === status) {
	         					if ("success" === data) {
	         						$.success("保存成功");
	
	         					} else {
	         						$.error("保存失败", 1);
	         					}
	         				}
	         			});
            		 }
            		 
//             	 }
             }else{
            	 $.post("${ctp}/bbx/screenPermission/save", {
      				"teamId" : teamId,
      				"accessType" : accessType,
      				"accessCode" : accessCode
      			}, function(data, status) {
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