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
<title></title>
</head>
<body style="background-color: #ffffff;">
	<div class="sjcsh_xx_table content_main">
		<table class="table">
			<thead>
				<tr><th><i class="ck" style="top: -11px;"></i></th><th>序号</th><th>班内学号</th><th>学生姓名</th><th>年级</th><th>班级</th><th>监护人</th><th>监护人手机</th><th class="caozuo">操作</th></tr>
			</thead>
			<tbody>
				<tr><td><i class="ck"></i></td><td>1</td><td>20183101</td><td>王小明</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="caozuo"><button class="btn btn-green"  onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>2</td><td>20183101</td><td>李想</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="caozuo"><button class="btn btn-green"  onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>3</td><td>20183101</td><td>刘大旭</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="caozuo"><button class="btn btn-green"  onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>4</td><td>20183101</td><td>张不但</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="caozuo"><button class="btn btn-green"  onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>5</td><td>20183101</td><td>吴晓婷</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="caozuo"><button class="btn btn-green"  onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
				<tr><td><i class="ck"></i></td><td>6</td><td>20183101</td><td>秋衣</td><td>三年级</td><td>1班</td><td>刘先生</td><td>16757837676</td><td class="caozuo"><button class="btn btn-green"  onclick="editStudent()">编辑</button><button class="btn btn-red">删除</button></td></tr>
			</tbody>
		</table>
		<div class="page_div">
			<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
				<jsp:param name="id" value="studentAid_list_content" />
				<jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
				<jsp:param name="pageSize" value="${page.pageSize}" />
			</jsp:include>
			<div class="clear"></div>
		</div>
	</div>
<div class="tis_jxdr" style="display:none;text-align: center;padding-top:20px;">
	<p>继续导入将覆盖当前临时数据</p>
	<p>是否继续导入</p>
</div>
<div class="tis_error" style="display:none;text-align: center;padding-top:20px;">
	<p>修改的数据有误，无法保存</p>
	<p style="color:#ff5252">异常提示：手机号录入错误</p>
</div>
<div class="tis_warn" style="display:none;text-align: center;padding-top:20px;">
	<p>您修改的数据有冲突，是否保存</p>
	<p style="color:#ff5252">异常提示：学生信息已存在</p>
</div>
<script>
function editStudent(){
	$.initWinOnTopFromLeft_qyjx("编辑学生",  '${pageContext.request.contextPath}/views/demo/new_school/spi_edit.jsp', '458', '429');
}
$('.btn-red').click(function(){
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['337px', '191px'],
		  title: '继续导入提示', //不显示标题
		  content: $('.tis_jxdr'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['337px', '191px'],
		  title: '错误提醒', //不显示标题
		  content: $('.tis_error'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['重新编辑','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['337px', '191px'],
		  title: '警告提醒', //不显示标题
		  content: $('.tis_warn'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['继续保存','取消'],//按钮
		  btn1: function(index, layero){
			 
		  }
	});
});

$('.table tbody i.ck').click(function(){
	
    if($(this).hasClass('on')){
        $(this).removeClass('on');
       if( $('tbody i.ck').length!=$('tbody i.ck.on').length){
    	   $('.table thead i.ck').removeClass('on');
       }
    }else{
        $(this).addClass('on');
        if($('tbody i.ck').length==$('tbody i.ck.on').length){
        	$('.table thead i.ck').addClass('on');
        }
    }
});
$('.table thead i.ck').click(function(){
    if($(this).hasClass('on')){
        $(this).removeClass('on');
        $('.ck').removeClass('on');

    }else{
        $(this).addClass('on');
        $('.ck').addClass('on');
    }
});
</script>
</body>
</html>