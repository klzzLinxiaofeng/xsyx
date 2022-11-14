<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<title>导学案</title>
</head>
<body>
<jsp:include page="../base/header.jsp"></jsp:include>
<input type="hidden" id="userId" value="${sessionScope[sca:currentUserKey()].userId}">
<input type="hidden" id="bkpermission" value="${acl:hasPermission(sessionScope[sca:currentUserKey()].userId, 'KE_JIAN_ZI_YUAN_SCHOOL', 0)}">
<div class="content_main">
	<div class="ku_select">
		<div class="xdkm_div">
			<jsp:include page="/views/embedded/plugin/gradeWidget.jsp"></jsp:include>
			<div class="search_div">
				<label>搜索：</label>
				<div class="ss">
					<input type="text" placeholder="试卷标题" id="title"/>
					<a href="javascript:void(0)" class="btn-blue" onclick="searchByTitle();">搜索</a>
				</div>
			</div>
		</div>
	</div>
	<div class="neirong_zs">
		<div class="nr_right" style="margin-left:0;">
			<div class="dxa_list" style="margin-bottom:20px;" id="taskList">
				<jsp:include page="./lp_task_list.jsp"></jsp:include>
			</div>
		</div>
		<div class="clear"></div>
	</div>
</div>
<div class="delete_div" style="display:none">
	<p style="margin-bottom:0;">删除将会删除<span class="red">罗定邦中学高三语文月考</span>在<span class="blue">七年级（一）班</span> 的任务，是否继续删除？</p>
</div>
</body>
<script type="text/javascript">
var param = null;

$(function() {
	$(".qyzj_header .qyzj_right ul li a").eq(2).addClass("btn-blue");
	
	var type = $("#bkpermission").val();
	if("true"==type) {
		type = 1;
	} else {
		type = 0;
	}
	
	var userId = $("#userId").val();
	
	$.gradeWidget(type, function(data) {
		param = data;
		listMyTask(param);
	})
})

function listMyTask(data) {
	myPagination("taskList", data, "/learningPlan/task/list");
}

function searchByTitle() {
	var title = $("#title").val();
	if(title!=null || ""!=title) {
		param["title"] = title;
	}
	listMyTask(param);
}

//导学案预览
function preview(id) {
	var url = "${pageContext.request.contextPath}/learningPlan/edit?type=view&id="+id;
	window.open(url, "预览-${title}");
}

//导学案任务删除

function deleteTask(id, obj) {
	var title=$(obj).parents("tr").children("td").eq(1).text();
	var team=$(obj).parents("tr").children("td").eq(3).text();
	$(".delete_div").find("span").eq(0).text(title);
	$(".delete_div").find("span").eq(1).text(team);
	layer.open({
		  type: 1,
		  shade: false,
		  area: ['350px', '210px'],
		  title: '注意', //不显示标题
		  content: $('.delete_div'), //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响
		  cancel: function(){
		    layer.close();
		  },
		  btn: ['确定','取消'],//按钮
		  btn1: function(index, layero){
			  var loader = new loadDialog();
		        loader.show();
				$.ajax({
		            url: "${pageContext.request.contextPath}/learningPlan/task/delete?id="+id,
		            type: "DELETE",
		            data: {},
		            async: true,
		            success: function(data) {
		        		loader.close(index);
		           		layer.close();
		           		$.success("删除成功");
		           		listMyTask(param);
		            }
		        });
			  },
			  btn2: function(index, layero){
				  layer.close();
			  }
		});
}

function activityDetail(id) {
	var url ="${pageContext.request.contextPath}/learningPlan/task/activity/index?taskId="+id;
	window.open(url, "学生小结");
}
function tj(id) {
// 	saveState();
    var  url= "${pageContext.request.contextPath}/statistics/lp/tj/index?taskId="+id;
// 	location.href="${pageContext.request.contextPath}/statistics/lp/tj/index?taskId="+id;
	window.open(url, "统计");
}
function exports(id){
	   var teamId=$(".team div .btn-blue").attr("data-id");
	   var val={};
	   val.taskId=id;
	   val.teamId=0;
	   val.type=4;
    $.ajax({
        url: "${pageContext.request.contextPath}/statistics/checkExport",
        type: "POST",
        data: val,
        async: false,
        success: function(data) {
          if(data==='noExam'){
         	 $.alert("导学案无试卷单元，无法导出");
          }else if(data==='fail'){
         	 $.alert("请统计后，再导出");
             }else{
             	window.location.href="${pageContext.request.contextPath}/statistics/export?taskId="+id+"&teamId="+0+"&type=4";
             }
        }
    });
}
</script>
</html>