<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>查看评语</title>
</head>
<body style="background-color: #F3F3F3 !important">
		<div class="row-fluid ">
			<div class="span12">
				<div style="margin-bottom: 0" class="content-widgets">
				<div style="padding: 20px 0 0;" class="widget-container">
				<div class="time_sz">
				<form class="form-horizontal">
				<div class="control-group">
				
					<label class="control-label">时间</label>
					<div class="controls">
						<input type="text"  id="" name="" class=""
									placeholder="修改日期" value='' onclick="WdatePicker();"  style="width:120px;background-color:#fff"/>
						<select style="width:60px; height: 32px;"><option>1</option><option>2</option><option>3</option></select> 点 <span style="padding:0 8px 0 18px;">至</span>
						<input type="text"  id="" name="" class=""
									placeholder="修改日期" value='' onclick="WdatePicker();"  style="width:120px;background-color:#fff"/>
						<select style="width:60px; height: 32px;"><option>1</option><option>2</option><option>3</option></select> 点
					</div>
				</div>
				</form>
				</div>
				<div class="form-actions tan_bottom">
				<button class="btn btn-warning" onclick="javascript:void(0)" type="button">保存</button>
					<button class="btn" onclick="javascript:void(0)" type="button">取消</button>
				</div>
						</div>
					</div>
				</div>
			</div>
</body>
<script type="text/javascript">
	
</script>
</html>