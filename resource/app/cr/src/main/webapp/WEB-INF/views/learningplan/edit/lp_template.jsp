<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/qyjx/css/all.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<%@ include file="/views/embedded/common.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<title>导学案模板 </title>
</head>
<body>
	<div class="template">
		<div class="template_list">
			<c:forEach items="${templateVos}" var="template">
				<p>
					<span style="display: inline-block;width: 150px;">${template.title}</span> <input type="text"
						value="${template.title}" data-templateId="${template.id}" style="display: none" class="template_name"
						maxlength="8" />
				</p>
			</c:forEach>
		</div>

		<div class="template_content">
			<p>
				<a href="javascript:void(0)" id="deleteTemplate" onclick="del_template()">删除该模板</a>
			</p>
			<c:forEach items="${templateVos}" var="template">
				<div class="bzdxa_class"
					style="margin: 0; width: 100%; display: none;">
					<table border="1" class="quanxian">
						<c:forEach items="${template.lpCatelogVoList}" var="catelog">
							<thead>
								<tr>
									<th colspan="3">${catelog.title}</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${catelog.lpUnitList}" var="unit">
									<tr>
										<td>${unit.title }</td>
										<td>${unit.description}</td>
									</tr>
								</c:forEach>
							</tbody>
						</c:forEach>
					</table>
				</div>
			</c:forEach>
		</div>
	</div>

	</div>
	<div class="template_cz">
		<a href="javascript:applyTemplate()" class="a1">确定</a>
		<a href="javascript:void(0)" class="a2" onclick="$.closeWindow()">取消</a>
	</div>
			

				
			

<script>
var i;

	$(function() {
		
		$('.template_list p').eq(0).click();
		//滚动条
		$(".template_list").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
		$(".bzdxa_class").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
		
		templateIsEmpty();
		
	});
	
	$('.template_list p').click(function(){
		$(this).addClass('choose');
		$(this).siblings().removeClass('choose');
		i = $(this).index();
		$(".bzdxa_class").siblings('.bzdxa_class').hide();
		$(".bzdxa_class").eq(i).show();
	});
	
		//双击重命名
		$('.template_list p span').dblclick(function(){
			$(this).hide();
			$(this).next().show().select();
		});
		
		//保存模板名字
		$('.template_list p input').blur(function(){
			var $this=$(this);
			var newName = $(this).val().trim();
			if(newName==null || newName==""){
			    layer.confirm("请输入1~8个字符", {
						btn: '确定',
						cancel:function(){
							$('.template_list p input').focus();
							layer.closeAll();
						}},
						function () {
							$('.template_list p input').focus();
							layer.closeAll();
						});
			    return false;
			}
			$.ajax({
				url:"${pageContext.request.contextPath}/learningPlan/modifyTemplate",
				type:"POST",
				data:{"templateId":$(this).attr('data-templateId'),"title":newName},
				async:true,
				success:function(data){
					if(data=="success"){
// 						$.success("重命名成功！");
						$this.hide();
						$this.prev('span').show();
						$this.prev('span').text(newName);
					}else{
						$.error("重命名失败！");
					}
				},
				error:function(){
					$.error("重命名失败！");
				}
			});
		});
		
	function del_template(){
		var templateId=$('.template_list p').eq(i).children(".template_name").attr('data-templateId');
		$.ajax({
			url:"${pageContext.request.contextPath}/learningPlan/deleteTemplate",
			type:"POST",
			data:{"templateId":templateId},
			async:true,
			success:function(data){
				if(data=="success"){
					$.success("删除成功！");
					$('.template_list p').eq(i).remove();
					$(".bzdxa_class").eq(i).remove();
					var len = $('.template_list p').length;
					if(i == len){
						$('.template_list p').eq(i-1).click();
					}else{
						$('.template_list p').eq(i).click();
					}
					templateIsEmpty();
				}else{
					$.error("删除失败！");
				}
			},
			error:function(){
				$.error("删除失败！");
			}
		});
	}
	
	function applyTemplate(){
		var templateId=$('.template_list p').eq(i).children(".template_name").attr('data-templateId');
		$.ajax({
			url:"${pageContext.request.contextPath}/learningPlan/applyTemplate",
			type:"POST",
			data:{"templateId":templateId,"lpId":"${lpId}"},
			async:true,
			success:function(data){
				if(data=="success"){
					parent.location.href="${pageContext.request.contextPath}/learningPlan/edit?id=${lpId}";
					$.success("导入成功！");
				}else{
					$.error("导入失败！");
				}
			},
			error:function(){
				$.error("导入失败！");
			}
		});
	}
	
	function templateIsEmpty(){
		if($('.template_list p').length==0){
			parent.location.href="${pageContext.request.contextPath}/learningPlan/edit?id=${lpId}";
// 			$("#deleteTemplate").remove();
		}
	}
</script>
</div>
</body>
</html>