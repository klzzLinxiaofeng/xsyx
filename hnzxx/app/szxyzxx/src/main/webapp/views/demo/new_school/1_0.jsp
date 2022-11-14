<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_newSchool.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/jqpagination/jqpagination_qyjx.css" rel="stylesheet">
<title>学年学期</title>
</head>
<body style="background-color:rgb(248, 248, 249)">
<div class="container-fluid">
	<p class="top_link">数据初始化 > <span class="s1">学年学期</span></p>
	<div class="content_main white">
		<div class="content_top" style="height: 68px;">
			<div class="f_left"><p class="p1" style="line-height: 35px;">罗定邦中学</p><span class="s2">学年/学期管理</span></div>
			<div class="f_right"><button class="btn btn-blue xjxn" style="margin: 18px 20px 0 0;">新建学年</button></div>
		</div>
		<table class="table xn_table">
			<thead>
				<tr><th>序号</th><th>名称</th><th>开始时间</th><th>结束时间</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td>1</td><td>2016-2017学年</td><td>2016-9-1</td><td>2017-7-30</td><td class="caozuo"><button class="btn btn-green" onclick="editSchoolYear(this)">编辑学年</button><button class="btn btn-blue xjxq">新建学期</button></td></tr>
				<tr><td></td><td>春季学期（下学期）</td><td>2017-3-1</td><td>2017-7-30</td><td class="caozuo"><button class="btn btn-peaGreen" onclick="editSchoolTerm(this)">编辑学期</button><button class="btn btn-lightGray setUpCurrent">设置为当前学期</button></td></tr>
				<tr><td></td><td>秋季学期（上学期）</td><td>2016-9-1</td><td>2017-1-20</td><td class="caozuo"><button class="btn btn-peaGreen" onclick="editSchoolTerm(this)">编辑学期</button><button class="btn btn-lightGray setUpCurrent" >设置为当前学期</button></td></tr>
				<tr><td>2</td><td>2016-2017学年</td><td>2016-9-1</td><td>2017-7-30</td><td class="caozuo"><button class="btn btn-green" onclick="editSchoolYear(this)">编辑学年</button><button class="btn btn-blue xjxq">新建学期</button></td></tr>
				<tr><td></td><td>春季学期（下学期）</td><td>2017-3-1</td><td>2017-7-30</td><td class="caozuo"><button class="btn btn-peaGreen" onclick="editSchoolTerm(this)">编辑学期</button><button class="btn btn-lightGray setUpCurrent">设置为当前学期</button></td></tr>
				<tr class="current"><td>当前学期</td><td>秋季学期（上学期）</td><td>2016-9-1</td><td>2017-1-20</td><td class="caozuo"><button class="btn btn-peaGreen" onclick="editSchoolTerm(this)">编辑学期</button><button class="btn btn-lightGray setUpCurrent">设置为当前学期</button></td></tr>
			</tbody>
		</table>
		
		<div class="next_1_0">
			<p style=" margin-bottom: 23px;color: #f44336;">请新建“学年”和“学期”，设置完成后点击“下一步”，为当前学期创建数据</p>
			<button class="btn btn-blue">下一步</button>
		</div>
	</div>
</div>
<div class="tck_xjxn" style="display:none">
	<div class="select_div">
		<span><i>*</i>开始时间：</span>
		<input type="text" placeholder="请输入开始时间 如2014-09-01" id="xn_start">
	</div>
	<div class="select_div">
		<span><i>*</i>结束时间：</span>
		<input type="text" placeholder="请输入结束时间 如2015-07-01" id="xn_end">
	</div>
	<div class="select_div">
		<span><i>*</i>学年名称：</span>
		<input type="text" placeholder="请输入学年名称 如2014-2015学年" id="xn_school_year_name">
	</div>
</div>
<div class="tck_xjxq" style="display:none">
	<div class="select_div">
		<span><i>*</i>国标学期名称：</span>
		<select class="span2"><option>秋季学期（上学期）</option></select>
	</div>
	<div class="select_div">
		<span><i>*</i>校内学期名称：</span>
		<input type="text" value="秋季学期（上学期）" id="xq_school_term_name">
	</div>
	<div class="select_div">
		<span><i>*</i>开始时间：</span>
		<input type="text" placeholder="请输入开始时间 如2014-09-01" id="xq_start">
	</div>
	<div class="select_div">
		<span><i>*</i>结束时间：</span>
		<input type="text" placeholder="请输入结束时间 如2015-07-01" id="xq_end">
	</div>
</div>
<script>
$(function(){
	$('tbody tr').each(function(){
		if($(this).children('td:first-child').text()!=''&& $(this).children('td:first-child').text()!='当前学期'){
			$(this).addClass('bm');
		}
	});
})
//设置为当前学期
$('body').on('click','.setUpCurrent',function(){
	$('tbody tr.current').children('td:first-child').text('');
	$('tbody tr.current').removeClass('current');
	$(this).parent().siblings('td:first-child').text('当前学期');
	$(this).parents('tr').addClass('current');
});
//新建学年
$(".xjxn").click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['406px', '291px'],
		  title: '新建学年', //不显示标题
		  content: $('.tck_xjxn'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			  
			var start =  $('#xn_start').val().trim();
			var end =  $('#xn_end').val().trim();
			var name =  $('#xn_school_year_name').val().trim();
			var cd = $('tbody .bm').length;
			var new_xn = '<tr class="bm"><td>'+ (cd+1) +'</td><td>'+ name +'</td><td>'+ start +'</td><td>'+ end +'</td><td class="caozuo"><button class="btn btn-green" onclick="editSchoolYear()">编辑学年</button><button class="btn btn-blue xjxq">新建学期</button></td></tr>';
			$('tbody tr:last-child').after(new_xn);
		  }
	});
})

//编辑学年
function editSchoolYear(obj){
	$('#xn_start').val($(obj).parent('td').siblings('td:nth-child(2)').text());
	$('#xn_end').val($(obj).parent('td').siblings('td:nth-child(3)').text());
	$('#xn_school_year_name').val($(obj).parent('td').siblings('td:nth-child(4)').text());
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['406px', '291px'],
		  title: '编辑学年', //不显示标题
		  content: $('.tck_xjxn'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			  
			$(obj).parent('td').siblings('td:nth-child(2)').text( $('#xn_start').val().trim());
			$(obj).parent('td').siblings('td:nth-child(3)').text($('#xn_end').val().trim());
			$(obj).parent('td').siblings('td:nth-child(4)').text($('#xn_school_year_name').val().trim());
		  }
	});
}

//新建学期
$('body').on('click','.xjxq',function(index){
	var $this=$(this);
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['422px', '341px'],
		  title: '新建学期', //不显示标题
		  content: $('.tck_xjxq'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			var start =  $('#xq_start').val().trim();
			var end =  $('#xq_end').val().trim();
			var name =  $('#xq_school_term_name').val().trim();
			var new_xq = '<tr><td></td><td>'+ name +'</td><td>'+ start +'</td><td>'+ end +'</td><td class="caozuo"><button class="btn btn-peaGreen">编辑学期</button><button class="btn btn-lightGray setUpCurrent">设置为当前学期</button></td></tr>';
			$this.parents('tr').after(new_xq);
		  }
	});
});

//编辑学期
function editSchoolTerm(obj){
	$('#xq_start').val($(obj).parent('td').siblings('td:nth-child(2)').text());
	$('#xq_end').val($(obj).parent('td').siblings('td:nth-child(3)').text());
	$('#xq_school_term_name').val($(obj).parent('td').siblings('td:nth-child(4)').text());
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['422px', '341px'],
		  title: '新建学期', //不显示标题
		  content: $('.tck_xjxq'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			  $(obj).parent('td').siblings('td:nth-child(2)').text( $('#xq_start').val().trim());
				$(obj).parent('td').siblings('td:nth-child(3)').text($('#xq_end').val().trim());
				$(obj).parent('td').siblings('td:nth-child(4)').text($('#xq_school_term_name').val().trim());
		  }
	});
}
</script>
</body>
</html>