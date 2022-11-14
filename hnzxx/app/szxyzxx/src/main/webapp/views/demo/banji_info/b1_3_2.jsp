<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/szxy/css/szxy_common.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/szxy/css/banji_info_manage.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/pinying.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/autocomplete/autocomplete.js"></script>
<title>新增班级管理者</title>

</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="banji_info_tk2_top">
	<span>姓名：</span>
	<input type="text" id="teacher_name">
	<button class="btn btn-blue" onclick="search_teacher()">查询</button>
</div>
<div class="banji_info_top border_top01">
	<ul>
		<li class="on" ><a href="javascript:void(0)">姓名A~Z</a></li>
		<li><a href="javascript:void(0)">部门</a></li>
	</ul>
</div>	
<div class="banji_info_tk2">
	<div class="banji_info_tk2_left f_left">
		<!-- <table>
			<thead id="H"><tr><th>H</th><th>部门</th></tr></thead>
			<tbody><tr><td><i class="yk"></i>马大大</td><td>数学组</td></tr></tbody>
		</table> -->
	</div>
	<div class="ssjg" style="display:none">
		
	</div>
	<div class="banji_info_tk2_right">
	</div>
</div>
<div class="banji_info_tk2" style="display:none;margin: 20px;">
	<div class="jspContainer">
		<ul class="un_ul">
			<li class="un-item">
				<div class="title"><a href="javacript:void(0)" class="li_open"></a><a href="javascript:void(0)" class="un-link">第一章 内容超长怎么办啊，我也很烦恼</a></div>
				<div class="second_ul">
					<ul class="le-list">
						<li>
						<div class="title"><a href="javacript:void(0)" class="li_open"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学</a></div>
							<div class="second_ul">
								<div class="choose">
									<p><i class="yk"></i>马发发2</p>
									<p><i class="yk"></i>马大大2</p>
									<p><i class="yk"></i>马飞飞2</p>
									<p><i class="yk"></i>马发发2</p>
									<p><i class="yk"></i>马大大1</p>
									<p><i class="yk"></i>马飞飞3</p>
									<div class="clear"></div>
								</div>
							</div>
						</li>
						<li>
						<div class="title"><a href="javacript:void(0)" class="li_open"></a><a href="javascript:void(0)" class="lesson-link">第一节 语文文学</a></div>
							<div class="second_ul">
								<div class="choose">
									<p><i class="yk"></i>马发发3</p>
									<p><i class="yk"></i>马大大3</p>
									<p><i class="yk"></i>马飞飞3</p>
									<p><i class="yk"></i>马发发2</p>
									<p><i class="yk"></i>马大大1</p>
									<p><i class="yk"></i>马飞飞3</p>
									<div class="clear"></div>
								</div>
							</div>
						</li>
						<li>
							<div class="title"><a href="javacript:void(0)" class="li_open"></a><a href="javascript:void(0)" class="lesson-link">第二节 文言文预读</a></div>
						</li>
						<li>
							<div class="title"><a href="javacript:void(0)" class="li_open"></a><a href="javascript:void(0)" class="lesson-link ">第三节 古代诗歌鉴赏之送汪伦情古诗词</a></div>
						</li>
					</ul>
				</div>
			</li>
		</ul>
	</div>
</div>
<div class="btn_cz">
	<button class="btn-lightGray1" style="cursor: not-allowed;" id="ojbk1">确定</button>
	<button class="btn  btn-blue" style="display:none" id="ojbk2" onclick="choosePerson()">确定</button>
	<button class="btn btn-lightGray" onclick="$.closeWindow()">取消</button>
</div>
<script>
$('body').on('click','.banji_info_top ul li',function(){
	$('.yk').removeClass('on')
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
	$('.banji_info_tk2').hide();
	$('.banji_info_tk2').eq($(this).index()).show();
})
//滚动条
$(".banji_info_tk2_left").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
$(".jspContainer").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
$(".ssjg").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});

//	目录树打开关闭
$(".jspContainer").on("click"," ul .li_open",function(){
	$(this).parent().next().show();
	$(this).removeClass("li_open").addClass("li_close");
});
$(".jspContainer").on("click"," ul .li_close",function(){
	$(this).parent().next().hide();
	$(this).removeClass("li_close").addClass("li_open");
});
$(".jspContainer ul .li_close").each(function(){
	if($(this).parent().next().length==0){
		$(this).hide();
	}
});
$(".jspContainer ul .li_open").each(function(){
	if($(this).parent().next().length==0){
		$(this).hide();
	}
});

$(function(){
	$('#teacher_name').focus();
	var zm = "";
	for(var i=0;i<26;i++){
		var zimu =['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
		zm = zm +"<a href='#"+zimu[i]+"'>"+zimu[i]+"</a>"
	}
	$('.banji_info_tk2_right').append(zm);
})

var obj=[];
var names = ['黄霞凤', '王作群','黄媛婷', '杨子东', '梁海婵', '林丽', '林雪花', '林雪明', '刘惠玲', '卢宽', '骆满凤', '麦春荣', '潘连梅', '苏小静', '吴成武', '吴朗清', '吴天丽', '吴月萍', '许羽荣', '许玉莹', '张柏', '张燕', '周平强', '周清玲', '周长春', '曾春燕', '陈怡璋', '冯志明', '高超凤', '黄勃学', '黄滇野', '黄奋', '黄时彦', '赖德宽', '劳腾辉', '雷小美', '李诗瑾', '林汉春', '莫赛丽', '王文辉', '冼晓娴',];
for(var i=0;i<names.length;i++){
   	obj.push({'name':names[i],'zimu':makePy(names[i])[0]})
}
//名字首字母排序	 
function down(x, y) {
       return (x.zimu < y.zimu) ? -1: 1
}
   
obj.sort(down);
 
 var zimuC =['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
 var zi=''
 
 //放进表格中
 for(var j =0;j<obj.length;j++){
 	if(zi!=obj[j].zimu.substring(0,1)){
 		zi =obj[j].zimu.substring(0,1) ;
 		$('.banji_info_tk2_left').append('<table class="table1"><thead id='+ zi +'><tr><th>'+ zi +'</th></tr></thead><tbody></tbody></table>');
 	}
	for(var i=0;i<26;i++){
	 	if(zimuC[i]==zi){
	 		var t2 = "<tr><td><i class='yk'></i><span>"+ obj[j].name +"</span></td></tr>"
	 		$('.table1:last-child tbody').append(t2)
	 	}
	 }
 }
//A~Z字母点击
$('body').on('click','.banji_info_tk2_right a',function(){
	$(this).siblings().removeClass('on');
	$(this).addClass('on');
// 	$(".banji_info_tk2_left").animate({
//         scrollTop: ($('#'+$(this).text()).offset().top-126) + "px"
//       }, {
//         duration: 500,
//         easing: "swing"
//       });
//       return false;
})


 //搜索enter
$('body').on('keydown','#teacher_name',function(event){
    if(event.keyCode==13){
    	search_teacher();
    }
})
//查询
function search_teacher(){
	$('.banji_info_top ul li').eq(0).click();
	var zimu="",aa2="",aa1="",aa3="";
	$('.ssjg table').remove();
	var teacher_name=$('#teacher_name').val();
		
		$("table tr td:first-child span").each(function(){
			
			var school_html=$(this).text();
			school_html = school_html.replace(/<b[^>]*>([^>]*)<\/b[^>]*>/ig,"$1");
	 		$(this).html(school_html);
	
			if(teacher_name==""){
				$('#ojbk1').show();
				$('#ojbk2').hide();
				$('.banji_info_tk2_left').show();
				$('.banji_info_tk2_right').show();
				$('.ssjg').hide();
			}else{
				if(school_html.indexOf(teacher_name)!=-1){
					var reg = new RegExp("("+teacher_name +")","ig");
					school_html = school_html.replace(reg,"<b style='color:red;font-weight: normal;'>$1</b>");
					$(this).html(school_html);
					/*  var aa1 = $(this).parents('.table1').find('thead').html();
					var aa2 = $(this).parents('tr').html();
					var aa3 = "<table><thead>"+ aa1 + "</thead><tbody>"+aa2 +"</tbody></table>";  */
					
				if(zimu!=$(this).parents('.table1').find('thead tr th:first-child').text()){
					zimu=$(this).parents('.table1').find('thead tr th:first-child').text();
					aa1 =  $(this).parents('.table1').find('thead').html();
					aa2 = "<tr>"+$(this).parents('tr').html()+"</tr>";
					aa3=aa3+"<table><thead>"+ aa1 + "</thead><tbody>"+aa2 +"</tbody></table>";
				}else{
					aa1 =  $(this).parents('.table1').find('thead').html();
					 aa2 = aa2 + "<tr>"+$(this).parents('tr').html()+"</tr>";
					 aa3 = "<table><thead>"+ aa1 + "</thead><tbody>"+aa2 +"</tbody></table>";
				} 
					
					$('.banji_info_tk2_left').hide();
					$('.banji_info_tk2_right').hide();
					$('.ssjg').show();
					
				} 
			}
			
		});
	
		
	$('.ssjg').append(aa3);
	
}
//部门选择
$('body').on('click','.second_ul .yk',function(){
	if($(this).hasClass('on')){
		$(this).removeClass('on')
	}else{
		$(this).addClass('on')
	}
	$(this).parent('p').siblings().find('.yk').removeClass('on');
	$(this).parents('li').siblings().find('.yk').removeClass('on');
	if($('.second_ul .yk.on').length>0){
		$('#ojbk1').hide();
		$('#ojbk2').show();
	}else{
		$('#ojbk1').show();
		$('#ojbk2').hide();
	}
	
})
//A~Z的选择
$('body').on('click','.banji_info_tk2_left .yk',function(){
	if($(this).hasClass('on')){
		$(this).removeClass('on')
	}else{
		$(this).addClass('on')
	}
	$(this).parents('table').siblings().find('.yk').removeClass('on');
	$(this).parents('tr').siblings().find('.yk').removeClass('on');
	if($('.banji_info_tk2_left .yk.on').length>0){
		$('#ojbk1').hide();
		$('#ojbk2').show();
	}else{
		$('#ojbk1').show();
		$('#ojbk2').hide();
	}
	
})
//搜索结果的选择
$('body').on('click','.ssjg .yk',function(){
	if($(this).hasClass('on')){
		$(this).removeClass('on')
	}else{
		$(this).addClass('on')
	}
	$(this).parents('table').siblings().find('.yk').removeClass('on');
	$(this).parents('tr').siblings().find('.yk').removeClass('on');
	if($('.ssjg .yk.on').length>0){
		$('#ojbk1').hide();
		$('#ojbk2').show();
	}else{
		$('#ojbk1').show();
		$('#ojbk2').hide();
	}
	
})
function choosePerson(){
	var a = $("#one",window.parent.parent.document);
	a.removeClass('banji_info_bg_lan');
	a.addClass('banji_info_bg_cheng');
	$("#two",window.parent.parent.document).addClass('banji_info_bg_lan');
	var b = $(".banji_info_tk1 .jinzhi",window.parent.parent.document);
	b.addClass('ck');
	b.removeClass('jinzhi');
	
	var i = $('.banji_info_top ul li.on').index();
	var tname = $('.banji_info_tk2').eq(i).find('.yk.on').parent().text();
	$("#teacherKuang",window.parent.parent.document).val(tname)
	
	$.closeWindow()
}

</script>
</body>
</html>