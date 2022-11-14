<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
.caozuo{padding-top:50px;}
.caozuo a{width:100px;height:27px;text-align:center;line-height:27px;display:block;color:#6D6E6F;background-color:#D0D0D0;margin-bottom:12px;}
.widget-head{border-bottom:0 none;}
.form-horizontal .control-label{width:80px;}
.form-horizontal .controls {
    margin-left: 100px;
}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<div class="widget-head">
						<h3>选择查询条件</h3>
					</div>
					<div class="widget-container">
						<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label">学生条件：</label>
								<div class="controls">
									<label style="float: left" class="radio"> <input
										type="radio" checked="" value="option1" name="optionsRadios">
										在校生
									</label> <label
										style="float: left; padding-top: 5px; margin-left: 5px;"
										class="radio"> <input type="radio" checked=""
										value="option2" name="optionsRadios"> 离校生
									</label> <label
										style="float: left; padding-top: 5px; margin-left: 5px;"
										class="radio"> <input type="radio" checked=""
										value="option3" name="optionsRadios"> 毕业生
									</label>
									<input type="text" value="" style="width:120px;margin:0 10px;" />至<input type="text" value="" style="width:120px;margin:0 10px;" />
								</div>
							</div>
						</form>
					</div>
					<div class="widget-head">
						<h3>选择显示字段</h3>
					</div>
					<div class="row-fluid" >
						<div class="span4" style="width:200px;margin-left:20px;">
							<select class="span12 select_left" multiple="multiple" style="height:250px;" >
								<option>证件类型</option>
								<option>身份证件有效期</option>
								<option>省内学校代码</option>
								<option>班级类型</option>
								<option>班内编号</option>
								<option>曾用名</option>
								<option>政治面貌</option>
								<option>户籍类别</option>
								<option>户籍设区/村</option>
								<option>户籍备注</option>
								<option>母亲姓名</option>
								<option>母亲单位职务</option>
								<option>父亲姓名</option>
								<option>父亲单位职务</option>
								<option>家庭镇/街</option>
							</select>
						</div>
						<div class="span2 caozuo" style="margin-left:5px;width:100px;">
							<a href="javascript:void(0)" class="add_1">添加  &gt; </a>
							<a href="javascript:void(0)" class="delete_1">&lt; 删除</a>
							<a href="javascript:void(0)" class="all_1">全选  &gt; </a>
							<a href="javascript:void(0)" class="delete_all">&lt; 清空 </a>
						</div>
						<div class="span4" style="width:200px;margin-left:5px;">
							<select class="span12 select_right" multiple="multiple" style="height:250px;" >
							</select>
						</div>
						<div class="span2 caozuo" style="margin-left:5px;width:100px;padding-top:87px;">
							<a href="javascript:void(0)" class="up_1">上移</a>
							<a href="javascript:void(0)" class="down_1">下移</a>
						</div>
					</div>
					<div class="form-actions" style="background-color:#297657;">
            	<div style="text-align:center;">
             		<button  class="btn btn-danger" type="button">导出</button>
             		<button  class="btn" type="button">取消</button>
            	</div>
            </div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$(".add_1").click(function(){
				$(".select_left").find("option:selected").appendTo(".select_right");
		});
		$(".delete_1").click(function(){
				$(".select_right").find("option:selected").appendTo(".select_left");
		});
		$(".all_1").click(function(){
			if($(".select_left option").length!=0){
				$(".select_left option").appendTo(".select_right");
			}
		});
		$(".delete_all").click(function(){
			if($(".select_right option").length!=0){
				$(".select_right option").appendTo(".select_left");
			}
		});
		$(".up_1").click(function(){
			if($(".select_right option:selected").length==1){
				$curr =$(".select_right").find("option:selected");
				var text_1=$curr.text();
				var text_2=$curr.prev().text();
				$curr.text(text_2);
				$curr.prev().text(text_1);
				$(".select_right option").attr("selected",false)
				$curr.prev().attr("selected",true);
			}
		});
		$(".down_1").click(function(){
			if($(".select_right option:selected").length==1){
				$curr =$(".select_right").find("option:selected");
				var text_1=$curr.text();
				var text_2=$curr.next().text();
				$curr.text(text_2);
				$curr.next().text(text_1);
				$curr.attr("selected",false)
				$curr.next().attr("selected",true);
			}
		});
	});
   		
	</script>
</body>
</html>
