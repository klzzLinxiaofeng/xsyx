<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/bootstrap-fileupload.js"></script>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
</style>
</head>
<body>
	<div class="container-fluid">
	<div class="row-fluid ">
                <div class="span12">
                    <ul class="breadcrumb">
                        <li><a href="#"><i name="dashboard" class="fa fa-home"></i></a>添加学生</li>
                    </ul>
                </div>
        </div>
		<div class="row-fluid ">
			<div class="span12">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							批量导入数据（广州市第三中学）
							<p style="float: right;" class="btn_link">
								<a class="a4" href="javascript:void(0)"><i
									class="fa fa-mail-reply"></i>返回列表</a>
							</p>
						</h3>
					</div>
				</div>
				<div class="stepy-widget">
					<div class="widget-head clearfix bondi-blue">
						<div id="stepy_tabby1">
							<ul id="stepy_form-titles" class="stepy-titles">
							</ul>
						</div>
						<button href="javascript:void(0)" class="btn btn-warning finish" style="position:absolute;right:25px;top:11px;">保存</button>
					</div>
					<div class="widget-container gray ">
						<div class="form-container">
							<form id="stepy1" class="form-horizontal left-align form-well"
								novalidate="novalidate">
								<fieldset title="第1步">
									<legend style="display: none;">下载模板文件</legend>
									<div class="control-group">
										<p>下载Excel模板文件并按照说明要求填写内容</p>
										<a href="javascript:void(0)" class="btn-info btn"><i class="fa fa-download"></i>模板下载</a>
									</div>
								</fieldset>
								<fieldset title="第2步">
									<legend style="display: none;">上传文件导入</legend>
									<div class="control-group">
										<div class="controls">
                                        <div class="fileupload fileupload-new" data-provides="fileupload">
                                            <div class="input-append">
                                                <div class="uneditable-input span3">
                                                    <i class="icon-file fileupload-exists"></i><span class="fileupload-preview"></span>
                                                </div>
                                                <span class="btn btn-file"><span class="fileupload-new">Select file</span><span class="fileupload-exists">Change</span>
                                                <input type="file"/>
                                                </span><a href="#" class="btn fileupload-exists" data-dismiss="fileupload">Remove</a>
                                                
                                            </div>
                                            <span style="color:#009A00">请上传EXCEL文件</span>
                                        </div>
                                    </div>
									</div>
								</fieldset>
								<fieldset title="第3步">
									<legend style="display: none;">查看导入结果</legend>
									<div class="control-group">
										<div class="select_success">
											<ul><li><a href="javascript:void(0)"><i class="fa  fa fa-comments"></i>成功</a></li><li class="on"><a href="javascript:void(0)"><i class="fa  fa-envelope-o"></i>失败</a></li></ul>
										</div>
										<div class="select_table">
											<table class="table table-bordered responsive">
												<thead><tr><th>姓名</th><th>导入结果</th></tr></thead>
												<tbody><tr><td>广州第三中学</td><td>成功</td></tr></tbody>
											</table>
											<table class="table table-bordered responsive" style="display:none">
												<thead><tr><th>姓名</th><th>导入结果</th></tr></thead>
												<tbody><tr><td colspan="2">不存在失败数据</td></tr></tbody>
											</table>
										</div>
									</div>
								</fieldset>
								 <button href="javascript:void(0)" class="btn btn-warning finish" style="display:none;">保存</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
    $(function(){
        $('#stepy1').stepy({
            backLabel: 'Back',
            nextLabel: 'Next',
            block: true,
            description: true,
            legend: false,
            titleClick: true,
            titleTarget: '#stepy_tabby1'
            });
    });
    $(function(){
			$(".select_success li a").click(function(){
				
				$(".select_success li a").parent().removeClass("on");
				$(this).parent().addClass("on");
				var i=$(this).parent().index();
				$(".select_table table").hide();
				$(".select_table table").eq(i).show()
			})
		});
	</script>
</body>
</html>
