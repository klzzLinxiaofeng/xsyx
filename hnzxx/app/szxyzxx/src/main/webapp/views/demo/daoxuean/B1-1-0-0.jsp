<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>备忘录</title>
</head>
<body>
<div class="container-fluid dxa">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets white">
				<div class="widget-head">
					<h3>
						<i class="fa fa-asterisk"></i>创建导学案
					</h3>
				</div>
				<div class="content-widgets">
					<div class="widget-container" style="padding:20px 0 0;">
						<div class="form-horizontal" >
							<div class="control-group">
								<label class="control-label">创建到:</label>
								<div class="controls">
									<p class="zy_radio"><input type="radio" name="zyk" value="school" /> 校本资源库</p>
	  								<p class="zy_radio"><input type="radio" name="zyk" value="personal" /> 个人资源库</p>
	                            </div>
							</div>
							<div class="zy_div">
								<div class="controls" style="margin-left: 65px;margin-bottom:20px;">
                   					<jsp:include page="/views/embedded/textBookCatalog.jsp"></jsp:include>	
               					</div>
               					<div class="control-group" id="upload_textbookcatalog">
					                <label class="control-label">
					                     <span class="red">*</span>目录
					                </label>
					                <div id="dvTextBookCatalog" class="select_div controls" style="margin-left:120px;">
					                </div>
					            </div>
               					<div class="control-group">
								<label class="control-label"><span class="red">*</span>标题:</label>
								<div class="controls">
									<input type="text" class="span4">
	                            </div>
							</div>
							<div class="control-group">
								<label class="control-label">提要:</label>
								<div class="controls">
									<textarea class="span4" style="height:80px;"></textarea>
	                            </div>
							</div>
							<div class="control-group">
								<label class="control-label"></label>
								<div class="controls">
									<button class="btn btn-primary">确定</button>
	                            </div>
							</div>
							</div>
							<div class="zy_div" style="display:none">
							<div class="control-group">
								<label class="control-label"><span class="red">*</span>科目:</label>
								<div class="controls">
									<select class="span1"></select>
	                            </div>
							</div>
               					<div class="control-group">
								<label class="control-label"><span class="red">*</span>标题:</label>
								<div class="controls">
									<input type="text" class="span4">
	                            </div>
							</div>
							<div class="control-group">
								<label class="control-label">提要:</label>
								<div class="controls">
									<textarea class="span4" style="height:80px;"></textarea>
	                            </div>
							</div>
							<div class="control-group">
								<label class="control-label"></label>
								<div class="controls">
									<button class="btn btn-primary">确定</button>
	                            </div>
							</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$(function(){
		$(".zy_radio input").click(function(){
			var i=$(this).parent().index();
			$(".zy_div").hide();
			$(".zy_div").eq(i).show();
		});
	});
</script>
</body>
</html>