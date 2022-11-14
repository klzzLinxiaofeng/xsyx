<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>预览</title>
<style>
	
</style>
</head>
<body>
<div class="container-fluid dxa">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets white">
				<div class="widget-head">
					<h3>
						<i class="fa fa-asterisk"></i>编辑模式-{导学案标题}
						<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-info-circle"></i>编辑</a>
							<a onclick="loadCreatePage();" class="a2" href="javascript:void(0)"><i class="fa fa-file-text"></i>布置</a>
							</p>
					</h3>
				</div>
				<div class="content-widgets cr_grey">
					<div class="creat_dxa">
						<div class="c_dxa_left">
							<div class="three_creat" >
									<div class="c_top">
										<div class="folder_div on">
											<a href="javascript:void(0)" class="hide_div"></a>
											<p>
												<span>121</span><input type="text" value="自主预习">
											</p>
											<ul>
												<li class="a1" data-id="1"><a href="javascript:void(0)"><i></i><span
														style="">知识梳理</span><input value="知识梳理"
														style="display: none;"></a></li>
												<li class="a2" data-id="2"><a href="javascript:void(0)"><i></i><span
														style="">预习自测</span><input value="预习自测"
														style="display: none;"></a></li>
												<li class="a3" data-id="3"><a href="javascript:void(0)"><i></i><span
														style="">微课</span><input value="微课" style="display: none;"></a></li>
											</ul>
										</div>
										<div class="folder_div">
											<p>
												<span>121</span><input type="text" value="121">
											</p>
										</div>
									</div>
								</div>
						</div>
						<div class="c_dxa_right">
							<div class="right_ts_1"><i></i><p>请选中左侧分组或内容</p></div>
							<div class="nr_div">
								 <div class="zhishishuli" style="display: none" data-id="1" id="1">
									<div class="zssl_top">
										<span>知识梳理</span>
										<button class="btn btn-danger">保存</button>
									</div>
									<div class="zssl_bottom">
										<textarea rows="" cols=""></textarea>
									</div>
								</div>
								<div class="yuxizice" style="display: none" data-id="2" id="2">
									<div class="yxzc_top">
										<span>预习自测</span>
										<button class="btn btn-danger">更改</button>
										<button class="btn btn-primary">预览</button>
									</div>
									<div class="yxzc_bottom">
										<ul>
											<li>试卷名称：<span>1212</span></li>
											<li>教材版本：<span>23</span></li>
											<li>适用科目：<span>12</span></li>
											<li>适用年级：<span>121</span></li>
											<li>题目数量：<span>12</span></li>
										</ul>
									</div>
								</div>
								<div class="weike_" style="display:none" data-id="3" id="3">
									<div class="wk_top">
										<span>微课</span>
										<button class="btn btn-danger"><i class="fa fa-plus"></i>添加</button>
									</div>
									<div class="wk_bottom" >
										<p class="wk_tj">该子单元内已有 <span>2</span> 个微课</p>
										<div class="xkzy_list">
											<dl>
								                <dt>
								                	<a href="/cr/resource/Play?id=15877">
								                        <img src="http://cdn.test.studyo.cn/develop/ggzyk_6/2015-07/14a23ce4d18ad59f9c59da86ae7f634f_thumbImg.png">
								                    </a>
								                </dt>
								                <dd>
								                    <div class="item-msg">
								                        <div class="item-title">
								                            <span class="res-mp4 icon-file res-iconb"></span>
								                            <span style="overflow:hidden;width:338px;float:left;display:block; white-space:nowrap; text-overflow:ellipsis; word-wrap: normal;">
								                                <a href="/cr/resource/Play?id=15877" title="11.吆喝">11.吆喝</a> 播放页路径
								                            </span>
								                        </div>
								                        <span class="i1">教材目录：四年级英语下册  </span> 
								                        <div class="i1">上传时间：2015-07-11</div>
								                        <div class="cz_btn">
								                        	<button class="btn btn-primary">预览</button>
								                        	<button class="btn btn-danger">删除</button>
								                          </div>
								                    </div>
								                </dd>
								            </dl>
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
</div>
<div class="zzc"></div>
<script>
$(function(){
	//点击文件夹下面的单元
	$(".three_creat .c_top ").on("click",".folder_div ul li a",function(){
		$(".three_creat .c_top .folder_div ul li a").removeClass("light_blue on");
		$(this).addClass("on");
		$(".folder_div p").removeClass("current");
		$(".folder_div").removeClass("on");
		$(this).parent().parent().parent().addClass("on");
		$(".right_ts_1").hide();
		var div_id=$(this).parent().data("id");
		$(".nr_div").children().hide();
		$("#"+div_id).show();
	});
	//展开关闭
	$(".folder_div").each(function(){
		if($(this).children("ul").children("li").length>0){
			$(this).children(".hide_div").show();
		}
	});
	$("body").on("click",".hide_div",function(){
		$(this).siblings("ul").hide();
		$(this).removeClass("hide_div").addClass("show_div");
	});
	$("body").on("click",".show_div",function(){
		$(this).siblings("ul").show();
		$(this).removeClass("show_div").addClass("hide_div");
	});
});
</script>
</body>

</html>