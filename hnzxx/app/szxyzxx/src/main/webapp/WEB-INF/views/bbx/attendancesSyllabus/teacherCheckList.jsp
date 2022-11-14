<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
<title></title>
<style>
.check_con_bottom_list ul{ overflow:hidden; display:none;}
.tan_bottom{left:0;}
.check_con_title_right span{ cursor: pointer;}
.check_con_title_right span.backg_span{ color:#fff;}
.check_con_title_right span{ display:inline-block; float:left; width:auto; padding:0 10px; line-height:30px; color:#e0dddd; margin-left:10px;}
.check_con_bottom_list ul li span{border: 1px solid #00b2ff;
padding: 5px 0;background:none; cursor: pointer;}
.check_con_bottom_list ul li span.over{    background: #00b2ff; color: #fff;}
</style>
</head>
<div class="check_con_bottom_title">
    <div class="check_con_title_left">学生姓名</div>
    <div class="check_con_title_right">
     <span style="background:#00b2ff;">签到人数${turnOutList.size() }</span>
     <span style="background:#00b2ff;">请假人数${attendanceList.size() }</span>
     <span style="background:#ef401b;" class="backg_span">缺勤人数${absenceList.size() }</span>
    </div>
</div>
<div class="check_con_bottom_list">
    <ul style="">
    	<c:forEach items="${turnOutList}" var="list" >
	        <li><span id="${list.studentUserId }">${list.studentName }</span></li>
    	</c:forEach>
    </ul>
    <ul style="">
    	<c:forEach items="${attendanceList}" var="list">
	        <li><span id="${list.studentUserId }"  studentId="${list.id }">${list.studentName }</span></li>
    	</c:forEach>
    </ul>
    <ul style="display:block;" class='a_ul'>
    	<c:forEach items="${absenceList}" var="list" >
	        <li><span id="${list.studentUserId }"  studentId="${list.id }">${list.studentName }</span></li>
    	</c:forEach>
    </ul>
    
</div>
<div class="form-actions tan_bottom">
		<button class="btn btn-warning" type="button" onclick="retroactive('false');">补签</button>
		<button class="btn btn-warning" type="button" onclick="retroactive('true');">请假</button>
		<button class="btn btn-blue" type="button" onclick="$.closeWindow();">取消</button>
</div>

</html>
<script>
$('.check_con_title_right span').click(function(){
	if($(this).index()==0 || $(this).index()==1){
		$('.tan_bottom').hide()
	}else{
		$('.tan_bottom').show()
	}
	$('.check_con_title_right span').eq($(this).index()).addClass('backg_span').siblings().removeClass('backg_span')
	$('.check_con_bottom_list ul').eq($(this).index()).show().siblings().hide();
})
$('.check_con_bottom_list').find('ul.a_ul').on('click','span',function(){
	if($(this).hasClass('over')){
		$(this).removeClass('over')
	}else{
		$(this).addClass('over')
	}
})

function retroactive(isAttendance){
	//var week = $('.check_con_top_right').find('select option[selected=selected]').attr('value');
	var week = $("#week").val();
	var syllabusType = $("#syllabusType").val();
	//alert(isAttendance);
	//alert(week);
	//补签
	var ids = "";
	if($('.a_ul li .over').length!=0){
		$('.a_ul li .over').each(function(index,v){
			ids+=","+$(this).attr('studentId');
		})
		
		$.post("${ctp}/bbx/attendancesSyllabus/" + ids, {"_method" : "post","week":week,"syllabusType":syllabusType,"isAttendance":isAttendance}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("补签成功");
					search();
				} else if ("fail" === data) {
					$.error("补签失败，系统异常", 1);
				}
			}
		});
	}else{
		$.error('请选择学生')
	}
}

</script>