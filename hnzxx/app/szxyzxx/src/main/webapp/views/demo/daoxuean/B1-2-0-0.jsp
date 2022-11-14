<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dxa/dxa.css" rel="stylesheet">
<title>编辑</title>
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
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-info-circle"></i>属性</a>
							<a onclick="loadCreatePage();" class="a4" href="javascript:void(0)"><i class="fa fa-eye"></i>预览</a>
							<a onclick="loadCreatePage();" class="a2" href="javascript:void(0)"><i class="fa fa-file-text"></i>布置</a>
							</p>
					</h3>
				</div>
				<div class="content-widgets cr_grey">
					<div class="creat_dxa">
						<div class="c_dxa_left">
							<div class="first_creat" ><a href="javascript:void(0)"><i></i>创建分组文件夹</a></div>
							<div class="second_creat" style="display:none">
								<p><span>自主预习</span></p>
								<div class="i_know"></div>
								<a href="javascript:void(0)">我知道了</a>
							</div>
							<div class="three_creat" style="display:none">
								<div class="c_top">
									<div class="folder_div on">
										<a href="javascript:void(0)" class="hide_div"></a>
										<p><span>自主预习</span><input type="text" value="自主预习"></p>
									</div>
								</div>
								<div class="c_bottom">
									<div class="creat_btn">
										<a href="javascript:void(0)" class="c_folde"><div class="btn_tishi">新建文件夹</div></a>
										<a href="javascript:void(0)" class="c_zhishi"><div class="btn_tishi">新建知识</div></a>
										<a href="javascript:void(0)" class="c_yuxi"><div class="btn_tishi">新建预习</div></a>
										<a href="javascript:void(0)" class="c_video"><div class="btn_tishi">新建微课</div></a>
									</div>
									<div class="cz_btn">
										<a href="javascript:void(0)" class="cz_up">上移</a>
										<a href="javascript:void(0)" class="cz_down">下移</a>
										<a href="javascript:void(0)" class="cz_rename">重命名</a>
										<a href="javascript:void(0)" class="cz_delete">删除</a>
									</div>
									<div class="not_click"></div>
									<button class="btn btn-danger tcgm">退出重命名</button>
								</div>
							</div>
						</div>
						<div class="c_dxa_right">
							<div class="right_ts" style="display:none"><i></i><p>请点击左侧分组下方创建单元内容</p></div>
							<div class="right_ts_1" style="display:none"><i></i><p>请选中左侧分组或内容</p></div>
							<div class="nr_div">
								<!-- <div class="zhishishuli" style="display: none">
									<div class="zssl_top">
										<span>知识梳理</span>
										<button class="btn btn-danger">保存</button>
									</div>
									<div class="zssl_bottom">
										<textarea rows="" cols=""></textarea>
									</div>
								</div>
								<div class="yuxizice" style="display: none">
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
								<div class="weike_" style="display:none">
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
								</div> -->
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
var i=0;
var h = document.documentElement.clientHeight;
$(function(){
	$(".zzc").height(h);
	$(".creat_dxa .c_dxa_left .first_creat a").click(function(){
		layer.confirm('<div class="form-horizontal" style="padding:0"><div class="control-group" style="margin-top:30px;"><label class="control-label">名称：</label><div class="controls"><input type="text" class="wjm_name"></div></div></div>', {
			  title: '新建文件夹',
			  area: ['420px', '240px'], //宽高
			  btn: ['确定','取消'], //按钮
			}, function(index){
				$(".creat_dxa .c_dxa_left .first_creat").hide();
				$(".creat_dxa .c_dxa_left .second_creat,.zzc").show();
				$(".right_ts").show();
				if($(".wjm_name").val()!=null){
					$(".creat_dxa .c_dxa_left .second_creat p").text($(".wjm_name").val());
					$(".creat_dxa .c_dxa_left .three_creat .folder_div").eq(0).children("p").children("span").text($(".wjm_name").val());
					$(".creat_dxa .c_dxa_left .three_creat .folder_div").eq(0).children("p").children("input").val($(".wjm_name").val());
				}
				layer.close(index);
			}, function(index){
				layer.close(index);
			});
	});
	$(".creat_btn a").click(function(){
		$(".right_ts").hide();
		$(".right_ts_1").show();
	});
	$(".creat_dxa .c_dxa_left .second_creat a").click(function(){
		$(".creat_dxa .c_dxa_left .second_creat,.zzc").hide();
		$(".creat_dxa .c_dxa_left .three_creat").show();
		
	})
	$(".three_creat .c_bottom .creat_btn a").click(function(){
		i=i+1;
	});
	$(".c_folde").click(function(){
		layer.confirm('<div class="form-horizontal" style="padding:0"><div class="control-group" style="margin-top:30px;"><label class="control-label">名称：</label><div class="controls"><input type="text" class="wjm_name"></div></div></div>', {
			  title: '新建文件夹',
			  area: ['420px', '240px'], //宽高
			  btn: ['确定','取消'], //按钮
			}, function(index){
				if($(".wjm_name").val()!=null){
					var wjj_name=$(".wjm_name").val()
					$("<div class='folder_div'><a href='javascript:void(0)' class='hide_div'></a><p><span>"+wjj_name+"</span><input type='text' value='"+wjj_name+"'></p></div>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top")
				}
				layer.close(index);
			}, function(index){
				layer.close(index);
			});
	})
	$(".c_zhishi").click(function(){
		if($(".creat_dxa .c_dxa_left .three_creat .c_top .on ul").length==0){
			$(".creat_dxa .c_dxa_left .three_creat .c_top .on").append("<ul></ul>")
		}
		$("<li class='a1' data-id="+i+"><a href='javascript:void(0)'><i></i><span style='display:none'>知识梳理</span><input value='知识梳理'></a></li>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top .on ul")
		$("input").focus();
// 		生成右边div
		var zsst = $(
				'<div class="zhishishuli" style="display: none" data-id=' + i + ' id=' + i + '>'+
				'<div class="zssl_top">'+
					'<span>知识梳理</span>'+
					'<button class="btn btn-danger">保存</button>'+
				'</div>'+
				'<div class="zssl_bottom">'+
					'<textarea rows="" cols=""></textarea>'+
				'</div>'+
			'</div>'
			);
		$(".nr_div").append(zsst);
	});
	$(".c_yuxi").click(function(){
		if($(".creat_dxa .c_dxa_left .three_creat .c_top .on ul").length==0){
			$(".creat_dxa .c_dxa_left .three_creat .c_top .on").append("<ul></ul>")
		}
		$("<li class='a2' data-id="+i+"><a href='javascript:void(0)'><i></i><span style='display:none'>预习自测</span><input value='预习自测'></a></li>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top .on ul")
		$("input").focus();
// 		生成右边div
		var yxzc = $(
				'<div class="yuxizice" style="display: none" data-id=' + i + ' id=' + i + '>'+
				'<div class="yxzc_top">'+
					'<span>预习自测</span>'+
					'<button class="btn btn-danger">更改</button>'+
					'<button class="btn btn-primary">预览</button>'+
				'</div>'+
				'<div class="yxzc_bottom">'+
					'<ul>'+
						'<li>试卷名称：<span>1212</span></li>'+
						'<li>教材版本：<span>23</span></li>'+
						'<li>适用科目：<span>12</span></li>'+
						'<li>适用年级：<span>121</span></li>'+
						'<li>题目数量：<span>12</span></li>'+
					'</ul>'+
				'</div>'+
			'</div>'	
			);
		$(".nr_div").append(yxzc);
	});
	$(".c_video").click(function(){
		if($(".creat_dxa .c_dxa_left .three_creat .c_top .on ul").length==0){
			$(".creat_dxa .c_dxa_left .three_creat .c_top .on").append("<ul></ul>")
		}
		$("<li class='a3' data-id="+i+"><a href='javascript:void(0)'><i></i><span style='display:none'>微课</span><input value='微课'></a></li>").appendTo(".creat_dxa .c_dxa_left .three_creat .c_top .on ul")
		$("input").focus();
// 		生成右边div
		var wk = $(
				'<div class="weike_" style="display:none" data-id=' + i + ' id=' + i + '>'+
				'<div class="wk_top">'+
					'<span>微课</span>'+
					'<button class="btn btn-danger"><i class="fa fa-plus"></i>添加</button>'+
				'</div>'+
				'<div class="wk_bottom" >'+
				'<p class="wk_tj">该子单元内已有 <span>0</span> 个微课</p>'+
				'<div class="xkzy_list">'+
				'</div>'+
				'</div>'+
				'</div>'
			);
		$(".nr_div").append(wk);
	});
	//单元input框失去焦点时
	$("body").on("blur",".folder_div ul li a input",function(){
		if($(".c_top").hasClass("rename")==false){
			var nr=$(this).val();
			$(this).prev().text(nr);
			$(this).prev().show()
			$(this).hide();
			if($(this).parent().hasClass("on")){
				$(".three_creat .c_top .folder_div ul li a").removeClass("on")
				$(this).parent().addClass("light_blue on");
			}
		}
	});
	//文件夹input框失去焦点时
	$("body").on("blur",".folder_div p input",function(){
		if($(".c_top").hasClass("rename")==false){
			var nr=$(this).val();
			$(this).prev().text(nr);
			$(this).prev().show()
			$(this).hide();
			$(this).parent().addClass("current");
		}
	});
	//点击文件夹下面的单元
	$(".three_creat .c_top ").on("click",".folder_div ul li a",function(){
		$(".three_creat .c_top .folder_div ul li a").removeClass("light_blue on");
		$(this).addClass("on");
		$(this).children("span").hide();
		$(this).children("input").show();
		if($(".c_top").hasClass("rename")==false){
			$("input").focus();
			}
		$(".folder_div p").removeClass("current");
		$(".folder_div").removeClass("on");
		$(this).parent().parent().parent().addClass("on");
		$(".right_ts_1").hide();
		var div_id=$(this).parent().data("id");
		$(".nr_div").children().hide();
		$("#"+div_id).show();
	});
	
	//点击文件夹名字
	$(".three_creat .c_top").on("click",".folder_div p",function(){
		$(".three_creat .c_top .folder_div").removeClass("on");
		$(this).parent().addClass("on");
		$(this).children("span").hide();
		$(this).children("input").show();
		$(".folder_div p").removeClass("current");
		if($(".c_top").hasClass("rename")==false){
		$("input").focus();
		}
		$(".three_creat .c_top .folder_div ul li a").removeClass("on light_blue");
		$(".right_ts_1").show();
		$(".nr_div").children().hide();
	})
	//上移
	$(".cz_up").click(function(){
		if($(".folder_div ul li a").hasClass("light_blue")){
			$(".folder_div ul li .light_blue").parent().prev().before($(".folder_div ul li .light_blue").parent());
		}
		if($(".folder_div.on p").hasClass("current")){
			$(".folder_div.on .current").parent().prev().before($(".folder_div.on .current").parent());
		}
	});
	//下移
	$(".cz_down").click(function(){
		if($(".folder_div ul li a").hasClass("light_blue")){
			$(".folder_div ul li .light_blue").parent().next().after($(".folder_div ul li .light_blue").parent());
		}
		if($(".folder_div.on p").hasClass("current")){
			$(".folder_div.on .current").parent().next().after($(".folder_div.on .current").parent());
		}
	});
// 	重命名
	$(".cz_rename").click(function(){
		$(".folder_div p input").show();
		$(".folder_div p span").hide();
		$(".folder_div ul li a input").show();
		$(".folder_div ul li a span").hide();
		$(".c_top").addClass("rename");
		$(".not_click,.tcgm").show();
	});
// 	退出重命名
	$(".tcgm").click(function(){
		$(".not_click,.tcgm").hide();
		$(".folder_div input").each(function(){
			var nr=$(this).val();
			$(this).prev().text(nr);
			$(this).prev().show()
			$(this).hide();
		});
		$(".c_top").removeClass("rename");
	});
	$(".cz_delete").click(function(){
		var div_id=$(".folder_div ul li .light_blue").parent().data("id");
		alert(div_id)
		$("#"+div_id).remove();
		$(".right_ts_1").show();
		$(".nr_div").children().hide();
		if($(".folder_div ul li a").hasClass("light_blue")){
			$(".folder_div ul li .light_blue").parent().remove();
		}
		if($(".folder_div.on p").hasClass("current")){
			$(".folder_div.on .current").parent().remove();
		}
	});
	//展开关闭
	$(".c_zhishi,.c_yuxi,.c_video").click(function(){
		if($(".folder_div.on").children("a").hasClass("hide_div")&&$(".folder_div.on").children("a").is(":hidden")){
			$(".folder_div.on").children(".hide_div").show();
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