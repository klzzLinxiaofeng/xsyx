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
</head>
<body>
<style>
.check_con{width: 90%; margin:0 auto; color: #444}
.check_con .check_con_top{ overflow: hidden; line-height: 30px; margin: 35px 0; margin-top:12px;}
.check_con_top .check_con_top_left{ float: left; line-height: 32px;}
.check_con_top .check_con_top_left span{line-height: 30px;display: inline-block;margin-right: 30px;}
.check_con_top .check_con_top_right{ float: right;overflow:hidden; line-height: 30px;}
.check_con_top .check_con_top_right>div{ float:left; margin-left: 10px; line-height: 32px;}
.check_con_top .check_con_top_right>div input{ line-height: 30px; }
.check_con_top .check_con_top_right>div select{ height: 30px; width: 140px}
.check_but{background: #e68923; border: none; color:#fff; padding:0 15px; cursor:pointer}
.check_con_bottom_title{border-bottom:1px solid #ebebeb; overflow: hidden; margin-bottom: 10px;}
.check_con_bottom_title .check_con_title_left{ float: left; font-weight: bold}
.check_con_bottom_title .check_con_title_right{float: right}
.check_con_bottom_title .check_con_title_right{ display: inline-block;}
.check_con_bottom_list ul li{ width: 25%; float: left; }
.check_con_bottom_list ul li span{text-align: center; background: #ebebeb;display: block; width: 90%; margin: 10px auto;}
#table_check{ width: 100%}
#table_check th{ font-weight: bold; border-bottom: 1px solid #ebebeb; text-align: left}
#table_check td{ line-height: 35px}
#table_check td:nth-child(2){ color: #0fa228}
#table_check td:nth-child(2).red{ color: red;padding:0;}
.check_con .check_con_top {overflow: hidden;line-height: 30px;margin: 15px 0;margin-top: 35px; margin-top: 12px;}
.check_con_bottom_list ul li{width:19.8%; padding:5px 0;
float: left;
background: #ddd;
text-align: center;
border: 1px solid #fff;
}
#name{ line-height:30px; padding-top:5px; padding-bottom:5px;}
.check_con_top .check_con_top_right > div{ margin:0; margin-right:10px;}
.check_con_bottom_title{border-bottom: 1px solid #ddd}
.check_con_top .check_con_top_right{float:left;}
</style>
<div class="check_con">
    <div class="check_con_top">
        <div class="check_con_top_right">
            <div></div>
            <div>
                <input type="text" id="name" name="name" placeholder="请输入学生姓名" />
            </div>
            <div>
                <input type="button"  value="查询" onclick="search()" class="check_but">
            </div>
        </div>
    </div>
    <input type="hidden" id="syllabusLessonId" value="${syllabusLessonId}"/>
    <div class="check_con_bottom">
    </div>
</div>
</body>
<script>
$(function(){
	search();
})

function search(){
	var loader = new loadLayer();
	//var week = $('.check_con_top_right option:selected').val();
	var syllabusLessonId = $("#syllabusLessonId").val();
	var name = $("#name").val();
	var url = "${ctp}/bbx/teacherSyllabus/member/index";
	loader.show();
	$(".check_con_bottom").load(
		url,
		{syllabusLessonId:syllabusLessonId, name:name, sub:"list"},
		function(data,status){
			/* if(status === "success"){			
			} */
			loader.close();
		}
	);
}
</script>
</html>