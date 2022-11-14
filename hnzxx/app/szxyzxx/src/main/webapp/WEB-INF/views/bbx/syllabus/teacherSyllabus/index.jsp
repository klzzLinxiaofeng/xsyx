<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
<style>
.biao-ul .item-right{
	margin-left:0;
}
.ke-table table th, .ke-table table td{
	border-left: none;
}
.left_span span{font-size: 13px;}
</style>
</head>
<body>
<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="content-widgets">
						<div class="select_top">
						<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
							<div class="s1 s1_bg" id="classMasterSearch">
								<input id="teachId" data-id="" type="text" style="width: 160px;" name="teachId" 
									value="" placeholder="教师姓名" />
								<div class="content">
									<div class="click">	
										<button class="btn btn-warning" type="button" onclick="search();">查看</button>							
									</div>
								</div>
							</div>
						</c:if>
						</div>
					</div>		
					<div class="content-widgets">
						<div id="syllabus_list_content"> 
							<jsp:include page="./syllabus.jsp" />
						</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">  
	$(function(){
		$.createMemberSelector({
			"inputIdSelector" : "#teachId",
			"enableBatch" : false
		});
		
		search();
	});
	
	function selectedHandler(data) {
		var ids = data.ids;
		var names = data.names;
		var windowName = data.windowName;
		$.closeWindowByName(windowName);
		$("#teachId_select").val(names[0]);
		$("#teachId").attr("data-id", ids[0]);
	}
	
    
	function search() {
		var val = {};
		var id = "syllabus_list_content";
		var url = "/bbx/teacherSyllabus/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	
	// 	加载创建对话框
	function loadCreatePage() {
		var teamId = $("#bj").find("option:selected").val()
		if(teamId == undefined || teamId=='' || teamId=='-1'){
			$.error("请选择一个班级!");
			return;
		}
		$.initWinOnTopFromLeft_bbx('调课通知', '${ctp}/clazz/teamSyllabus/creator?teamId='+teamId, '600', '400');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft_bbx('编辑通知', '${ctp}/clazz/teamSyllabus/editor?id=' + id, '600', '400');
	}
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/clazz/teamSyllabus/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#"+id+"_li").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	} 
	
	// 
	$('#syllabus_list_content').on('click','td .new_pj',function(){
		var syllabusLessonId = $(this).attr("data-kbdm");
		if($(this).hasClass('member')){
			//成员	
			$.initWinOnTopFromLeft('查看成员', 
				'${ctp}/bbx/teacherSyllabus/member/index?syllabusLessonId='+syllabusLessonId + "&name=" , '900', '650');
		}else if($(this).hasClass('evaluate')){
			//评价	
			$.initWinOnTopFromLeft('查看评价', '${ctp}/bbx/syllabusEvaluate/index?syllabusLessonId='+syllabusLessonId, '900', '650');
		}else{
			//考勤
			var syllabusType = $(this).attr("data-type");
			$.initWinOnTopFromLeft('查看考勤', 
				'${ctp}/bbx/attendancesSyllabus/teacher/input?lessonId='+syllabusLessonId + "&syllabusType=" + syllabusType, '700', '450');
		}
	})
</script>
</html>