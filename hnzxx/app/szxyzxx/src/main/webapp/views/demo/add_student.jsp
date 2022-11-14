<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js"></script>
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
. form-horizontal{position:relative;}
 /*    .form-horizontal .control-group{
    	width:45%;
    	display:inline-block;
    } */
    .table thead th{height:34px;line-height:34px;}
    legend + .control-group{margin-top:0;}
    
    table td .up_img {
    background: url("${pageContext.request.contextPath}/res/css/extra/images/up_img.jpg") no-repeat;
    display: block;
    height: 30px;
    width: 98px;
    position:absolute;
    top:100px;
    left:30px;
}
td input[type="text"],td select,td textarea{margin-bottom:0;width:100%;}
td input[type="text"],td textarea{border:0 none;width:95%;}
table{background-color:#fff;}
table td img{width:155px;height:187px;}
.red {
    color: red;
    padding: 0 5px;
}
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
							学校基本信息
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
								<fieldset title="基本信息">
									<legend style="display: none;">基本账户信息</legend>
									<table class="table table-bordered t_table">
										<tbody>
											<tr>
												<td><span class="red">*</span>姓名</td>
												<td><input type="text" id="name" name="name" value="" data-id="" placeholder="姓名" /></td>
												<td>英文名</td>
												<td><input type="text" id="englishName" name="englishName" value="" data-id="" placeholder="英文名" /></td>
												<td rowspan="7" style="width: 165px; position: relative"><img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg"><a href="javascript:void(0)" class="up_img"></a></td>
											</tr>
											<tr>
												<td><span class="red">*</span>用户名</td>
												<td><input type="text" id="userName" name="userName"  value="" data-id="" placeholder="用户名"/></td>
												<td><span class="red">*</span>学生类别</td>
												<td><input type="text" id="" name=""  value="" data-id="" placeholder="学生类别"/></td>
											</tr>
											<tr>
												<td>学籍号</td>
												<td><input name="name" type="text" /></td>
												<td>职务</td>
												<td><input name="name" type="text" /></td>
											</tr>
											<tr>
												<td>入学时间</td>
												<td><input name="name" type="text" /></td>
												<td>离校时间</td>
												<td><input name="name" type="text" /></td>
											</tr>
											<tr>
												<td>婚姻状况</td>
												<td>
													<select>
															<option>已婚</option>
															<option>未婚</option>
															<option>离异</option>
													</select>
												</td>
												<td>健康状况</td>
												<td>
													<select>
															<option>优秀</option>
															<option>良好</option>
															<option>差</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>血型</td>
												<td>
													<select>
														<option>A</option>
														<option>B</option>
														<option>AB</option>
														<option>0</option>
													</select>
												</td>
												<td>在读状况</td>
												<td>
													<select>
														<option>在读</option>
														<option>离校</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>港澳台侨外码</td>
												<td><input name="name" type="text" /></td>
												<td>性别</td>
												<td><label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
										<input type="radio" name="optionsRadios" value="option1" checked="">
										男 </label>
										<label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
										<input type="radio" name="optionsRadios" value="option2" checked="">
										女 </label></td>
											</tr>
										</tbody>
									</table>

								</fieldset>
								 <fieldset title="户籍信息">
								<legend style="display: none;">国籍、户口</legend>
									<table class="table table-bordered t_table">
										<tbody>
											<tr>
												<td>出生日期</td>
												<td><input name="name" type="text" /></td>
												<td>证件类型</td>
												<td><select>
												<option>身份证</option>
												<option>一卡通</option>
											</select></td>
											</tr>
											<tr>
												<td>证件号码</td>
												<td><input name="name" type="text" /></td>
												<td>国籍</td>
												<td><input name="name" type="text" /></td>
											</tr>
											<tr>
												<td>民族</td>
												<td><input name="name" type="text" /></td>
												<td>籍贯</td>
												<td><input name="name" type="text" /></td>
											</tr>
											<tr>
												<td>出生地</td>
												<td><input name="name" type="text" /></td>
												<td>户口性质</td>
												<td><input name="name" type="text" /></td>
											</tr>
											<tr>
												<td>户口所在地</td>
													<td><input name="name" type="text" /></td>
												<td>省份</td>
												<td>
													<select>
															<option>广东省</option>
															<option>湖北省</option>
															<option>广西省</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>城市</td>
												<td>
													<select>
														<option>深圳</option>
														<option>揭阳</option>
														<option>广州</option>
														<option>清远</option>
													</select>
												</td>
												<td>街道</td>
												<td>
													<select>
														<option>大石街道</option>
														<option>工业大道</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>现地址</td>
												<td><input name="name" type="text" /></td>
												<td>居住地址</td>
												<td><input name="name" type="text" /></td>
											</tr>
											<tr>
												<td>是否流动人口</td>
												<td colspan="3"><label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
										<input type="radio" name="optionsRadio" value="option3" checked="">
										是 </label>
										<label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
										<input type="radio" name="optionsRadio" value="option4" checked="">
										否 </label></td>
											</tr>
										</tbody>
									</table>
								</fieldset>
								<fieldset title="常用信息">
								<legend style="display: none;">特长。联系方式</legend>
									<table class="table table-bordered t_table">
										<tbody>
											<tr>
												<td>特长</td>
												<td><input name="name" type="text" /></td>
												<td>政治面貌</td>
												<td>
													<select>
														<option>少先队员</option>
														<option>团员</option>
														<option>党员</option>
													</select>
												</td>
											</tr>
											<tr>
												<td>宗教信仰</td>
												<td><input name="name" type="text" /></td>
												<td>是否独生子女</td>
												<td><label class="radio" style="float:left;padding-top: 5px; margin-left: 5px;">
										<input type="radio" name="dusheng" value="option3" checked="">
										是 </label>
										<label class="radio" style="float: left; padding-top: 5px; margin-left: 5px;">
										<input type="radio" name="dusheng" value="option4" checked="">
										否 </label></td>
											</tr>
											<tr>
												<td>电话</td>
												<td><input name="name" type="text" /></td>
												<td>手机</td>
												<td><input name="name" type="text" /></td>
											</tr>
											<tr>
												<td>邮件</td>
												<td colspan="3"><input name="name" type="text" /></td>
											</tr>
										</tbody>
									</table>
								</fieldset>
								<fieldset title="家庭成员">
								<legend style="display: none;">家长信息管理</legend>
									<table class="table  table-striped table-bordered responsive white">
                            <thead>
                            <tr>
                                <th>
                                    序号
                                </th>
                                <th>
                                   成员姓名
                                </th>
                                <th>
                                    与学生关系
                                </th>
                                <th>
                                    家长手机
                                </th>
                                <th>
                                    用户名
                                </th>
                                <th>
                                	操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td >
                                    1
                                </td>
                                <td>
                                    罗至宝
                                </td>
                                <td>
                                   父亲
                                </td>
                                <td>
                                    13800138000
                                </td>
                                <td>
                                    45545451
                                </td>
                                <td>
									<button type="button" class="btn btn-blue">编辑</button>
									<button type="button" class="btn btn-warning">删除</button>
								</td>
                            </tr>
                            <tr>
                                <td >
                                    2
                                </td>
                                <td>
                                    罗至宝
                                </td>
                                <td>
                                   父亲
                                </td>
                                <td>
                                    13800138000
                                </td>
                                <td>
                                    45545451
                                </td>
                                <td>
									<button type="button" class="btn btn-blue">编辑</button>
									<button type="button" class="btn btn-warning">删除</button>
								</td>
                            </tr>
                            </tbody>
                            </table>
                           <p class="btn_link">
						<a class="a3 add_family" href="javascript:void(0)"><i class="fa fa-plus"></i> &nbsp; 新增</a>
						</p>
						<div class="clear"></div>
								</fieldset>
								<fieldset title="备注">
								<legend style="display: none;">其他信息记录</legend>
									<div class="widget-container">
								<div class="control-group" style="width:100%;">
									<label class="control-label">Input Label</label>
									<div class="controls">
										<textarea rows="5" cols="80" style="width: 80%" class="tinymce-simple">
										</textarea>
									</div>
								</div>
						</div>
						<div class="clear"></div>
								</fieldset>
								<button href="javascript:void(0)" class="btn btn-warning finish" >保存</button>
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
    })
    $(function(){
		$(".t_table tr").each(function(){ 
			$(this).children("td:eq(0),td:eq(2)").css({"background-color":"#F4F4F4","text-align":"right","padding-right":"10px"});
		}); 
	})
    $(function(){
    	$(".btn_link .add_family").click(function(){
    		var i=$("table tbody > tr").length+1;
    		$("#stepy1-step-3").children("table").children("tbody").append("<tr><td>" +i+ "</td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><input type='text' /></td><td><button type='button' class='btn btn-blue'>编辑</button><button type='button' class='btn btn-warning'>删除</button></td></tr>")
    	});
    });
    $(function() {
		$('textarea.tinymce-simple').tinymce({
			script_url : '${pageContext.request.contextPath}/res/plugin/falgun/js/tiny_mce/tiny_mce.js',
			theme : "simple"
			});
		});
	
	</script>
</body>
</html>
