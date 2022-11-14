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
			<p>
				<span>模板名称1</span>
				<input type="text" value="模板名称1" style="display: none" class="template_name" maxlength="8"/>
			</p>
			<p>
				<span>模板名称2</span>
				<input type="text" value="模板名称2" style="display: none" class="template_name" maxlength="8"/>
			</p>
			<p>
				<span>模板名称3</span>
				<input type="text" value="模板名称3" style="display: none" class="template_name" maxlength="8"/>
			</p>
		</div>
		<div class="template_content">
			<p>
				<a href="javascript:void(0)" onclick="del_template()">删除该模板</a>
			</p>
			<div class="bzdxa_class" style=" margin: 0;width: 100%;display:none;">
		        <table border="1" class="quanxian">
		        	<thead>
		        		 <tr>
			                 <th colspan="3">文件夹的名字1</th>
			             </tr>
		        	</thead>
		        	<tbody>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        	</tbody>
		        	<thead>
		        		 <tr>
			                 <th colspan="3">文件夹的名字1</th>
			             </tr>
		        	</thead>
		        	<tbody>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        	</tbody>
		        	<thead>
		        		 <tr>
			                 <th colspan="3">文件夹的名字1</th>
			             </tr>
		        	</thead>
		        	<tbody>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        	</tbody>
		        </table>
			  </div>
			  <div class="bzdxa_class" style=" margin: 0;width: 100%; display:none;" >
		        <table border="1" class="quanxian">
		        	<thead>
		        		 <tr>
			                 <th colspan="3">文件夹的名字2</th>
			             </tr>
		        	</thead>
		        	<tbody>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        	</tbody>
		        </table>
			  </div>
			  <div class="bzdxa_class" style=" margin: 0;width: 100%;display:none;">
		        <table border="1" class="quanxian">
		        	<thead>
		        		 <tr>
			                 <th colspan="3">文件夹的名字3</th>
			             </tr>
		        	</thead>
		        	<tbody>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        		<tr>
		        			<td>文件夹下边的单元的名字</td>
		        			<td>单元类型</td>
		        		</tr>
		        	</tbody>
		        </table>
			  </div>
		</div>
		
	</div>
	<div class="template_cz">
		<a href="javascript:void(0)" class="a1">确定</a>
		<a href="javascript:void(0)" class="a2" onclick="$.closeWindow()">取消</a>
	</div>
			

				
			

<script>
var i;

	$(function() {
		
		$('.template_list p').eq(0).click();
		//滚动条
		$(".template_list").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
		$(".bzdxa_class").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
		
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
			var newName = $(this).val();
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
			$(this).hide();
			$(this).prev('span').show();
			
			$(this).prev('span').text(newName);
		});
		
	function del_template(){
		$('.template_list p').eq(i).remove();
		$(".bzdxa_class").eq(i).remove();
		var len = $('.template_list p').length;
		if(i == len){
			$('.template_list p').eq(i-1).click();
		}else{
			$('.template_list p').eq(i).click();
		}
		
		
	}
</script>
</div>
</body>
</html>