<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title></title>
<style>
	.row-fluid .span4 {
	width: 227px;
}
.row-fluid .span3 {
	width: 120px;
}

.myerror {  
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}

</style>
</head>
<body style="background-color:#fff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0;">
				<div class="widget-container" style="padding:0 20px;">
					<form class="form-horizontal" id="user_form" style="padding-bottom:0;margin-bottom:0">
						<div class="control-group dxq_gl">
							<label class="control-label"><span style="color:#ff0000;">*</span>总学校名称：</label>
							<div class="controls">
							    <input type="hidden" id="id" name="id" value="${membership.id }"/>
								<input type="text" id="name"  name="name" class="span4 left_red {required : true,stringCheck:true,maxlength:50}"  value="${membership.name }" placeholder="请输入总学校名称" >
                            </div>
						</div>
						<div class="control-group dxq_gl">
							<label class="control-label">已关联学校：</label>
							<div class="controls">
								<ul>
							         <c:if test="${memberSchool.size()>0 }">
							           <c:forEach items="${memberSchool}" var="m">
							              <li id="${m.ownerId }"><p>${m.name }</p><a href="javascript:void(0)"></a></li>
							           
							           </c:forEach>
							        </c:if>	
 <!-- 									<li><p>广州迅云教育科技学校（测试）</p><a href="javascript:void(0)"></a></li> -->
								</ul>
							</div>
						</div>
						<div class="dxq_select">
							<div class="select_b">
								<div class="select_div"><span>筛选：</span><input type="text" placeholder="请输入学校名称" id="schoolName" /></div>
								<div class="select_div"><span>地址：</span><select class="span3" id="province" name="province"></select><select class="span3" id="city" name="city" ></select><select class="span3" id="district" name="district"></select></div>
								<button type="button" class="btn btn-primary" onclick="search();">搜索</button>
								<div class="clear"></div>
							</div>
							<div class="table1">
								<table class="table">
									<thead><tr><th style="width:80px;padding-left:20px;">序号</th><th>关联学校</th><th style="width:80px;">选择</th></tr></thead>
								</table>
								</div>
							<div class="table2">
								<table class="table">
									<tbody id="schoolTable">
										<!-- <tr><td>1</td><td>滨江一小西校区</td><td><input type="checkbox" /></td></tr>
										<tr><td>2</td><td>滨江二小西校区</td><td><input type="checkbox" /></td></tr>
										<tr><td>3</td><td>滨江三小西校区</td><td><input type="checkbox" /></td></tr>
										<tr><td>4</td><td>滨江四小西校区</td><td><input type="checkbox" /></td></tr>
										<tr><td>5</td><td>滨江五小西校区</td><td><input type="checkbox" /></td></tr>
										<tr><td>6</td><td>滨江六小西校区</td><td><input type="checkbox" /></td></tr> -->
									</tbody>
								</table>
								<div class="no_content" hidden></div>
							</div>
						</div>
						<div class="new_btn" hidden>
								<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
								<button class="btn btn-blue" type="button" onclick="$.closeWindow();">取消</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>
	
	
	$.initRegionSelector({
		sjSelector : "province",
		shijSelector : "city",
		qxjSelector : "district",
		isEchoSelected : true,
		sjSelectedVal : "",
		shijSelectedVal : "",
		qxjSelectedVal : ""
	});
	
	
	
	
	function search(){
		var province = $("#province").val();
		var city = $("#city").val();
		var district = $("#district").val();
		var name = $("#schoolName").val();
		var membershipId = $("#id").val();
		var url = "${pageContext.request.contextPath}/teach/membership/getSchoolList";
		$("#schoolTable").html("");
		$.ajax({
			url : url,
			data : {
				     "province":province,
				     "city":city,
				     "district":district,
				     "name":name,
				     "membershipId":membershipId
				    },
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
			 if (data.length != 0) {
						var schoolList = data.schoolList;
						var oneMembershipList = data.oneMembershipList;
						
					if(schoolList.length != 0){
						   $(".no_content").hide();
						   $(".new_btn").show();
							   if(oneMembershipList.length != 0){
								   for(var i=0;i<schoolList.length;i++){
									   var html= "<tr><td>"+(i+1)+"<input  type='hidden' value='"+schoolList[i].id+"'/></td><td>"+schoolList[i].name+"</td><td><input type='checkbox' value='"+schoolList[i].id+"' name='selectName' ";
									   for(var oi=0;oi<oneMembershipList.length;oi++){
										   if(oneMembershipList[oi].ownerId == schoolList[i].id){
											   html+="checked='checked'";
										   }
									   }
									   
									      html+=" /></td></tr>";
									   $("#schoolTable").append(html);
								   }
								   
						   }else{
							   for(var i=0;i<schoolList.length;i++){
								   $("#schoolTable").append("<tr><td>"+(i+1)+"<input  type='hidden' value='"+schoolList[i].id+"'/></td><td>"+schoolList[i].name+"</td><td><input type='checkbox' value='"+schoolList[i].id+"' name='selectName' /></td></tr>");
							   }
							   
						   }
							 
					}else{
						$(".no_content").show();
					}
			    }	
			}
				
			     });
			  }
	
	
		$(function(){
			search();
			/* 点击input选择 */
			$(".table").on("click","input",function(){
				var school_id=$(this).parent().siblings().children("input").val();
				var school_name=$(this).parent().prev().html();
				if($(this ).is(':checked' )){
				   $("<li id="+school_id+"><p>"+school_name+"</p><a href='javascript:void(0)'></a></li>").appendTo(".dxq_gl ul");
				}else{
					var l=$(".dxq_gl ul li").length;
					for(var i=0;i<l;i++){
						if($(".dxq_gl ul li").eq(i).attr("id")==school_id){
							$(".dxq_gl ul li").eq(i).remove();
						};
					};
				};
			});
			/* 关闭 */
			$(".dxq_gl ul").on("click","li a",function(){
				var select_id=$(this).parent().attr("id");
				var j=$(".table tbody tr").length;
				for(var i=0;i<j;i++){
					if($(".table tbody tr").eq(i).children("td:nth-child(1)").children("input").val()==select_id){
						$(".table tbody tr").eq(i).children().children("input").click();
					};
				}
			});
		});
		
		
		var checker;
		$(function() {
			checker = initValidator();
		});
		
		function initValidator() {
			return $("#user_form").validate({
				errorClass : "myerror",
				rules : {
					
				},
				messages : {
					
				}
			});
		}
		
		
		
		function saveOrUpdate(){
			
			if (checker.form()) {
				
			var loader = new loadLayer();
			
			var $requestData = formData2JSONObj("#user_form");
			
			var membershipId = $("#id").val();
			
			var name = $("#name").val();
			
			var obj=document.getElementsByName('selectName');  //选择所有name="'selectName'"的对象，返回数组    
			  //取到对象数组后，我们来循环检测它是不是被选中    
			  var ownerIds="";    
			  for(var i=0; i<obj.length; i++){    
			    if(obj[i].checked) ownerIds+=obj[i].value+",";  //如果选中，将value添加到变量s中    
			  }    
			
			  
			$requestData.membershipId = membershipId;
			$requestData.ownerIds = ownerIds;
			$requestData.name = name;
			
			var url = "${ctp}/teach/membership/edit";
			if(ownerIds===""){
				$.error("请选择学校!");
			}else{
				
				loader.show();
				$.post(url, $requestData, function(data, status) {
					if ("success" === status) {
						if ("success" === data) {
							$.success("保存成功");
							if (parent.core_iframe != null) {
								parent.core_iframe.window.location.reload();
							} else {
								parent.window.location.reload();
							}
							$.closeWindow();
						} else {
							$.error("保存失败");
						}
					} else {
						$.error("服务器异常");
					}
					loader.close();
				});
			}
			}
			
			
			
		}
		
		
			
		
	</script>
</body>
</html>