<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<body>
<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="widget-head">
						<div class="select_top">
							<div class="s1 s1_bg" id="schoolManagerSearch" style="margin-bottom: 15px">
								<div class="select_b">
									<div class="select_div"><span>学年：</span> <select id="xn" name="xn" style="width:150px;"></select></div>
									<div class="select_div"><span>学期：</span> <select id="xq" name="xq" style="width:150px;"></select></div>
<!-- 									<div class="select_div"><span>年级：</span> <select id="nj" name="nj" style="width:150px;"></select></div> -->
<!-- 									<div class="select_div" style="display: none;"><span>班级：</span> <select id="bj" name="bj" class="chzn-select" style="width:150px;"></select></div> -->
									<button type="button" class="btn btn-primary" onclick="search()">查询</button>
										<button class="btn btn-warning" type="button"
											onclick="loadCreatePage();">创建</button>
									<div class="clear"></div>
								</div>
							</div>
						</div>
					</div>
					<div class="content-widgets" >
						<div>
							<div id="courseConfig_list_content">
								<jsp:include page="./list.jsp" />
							</div>
							<div class="widget-container">
								<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
									<jsp:param name="id" value="courseConfig_list_content" />
									<jsp:param name="url" value="/bbx/courseConfig/index?sub=list&dm=${param.dm}" />
									<jsp:param name="pageSize" value="${page.pageSize}" />
								</jsp:include>
								<div class="clear"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>




<script type="text/javascript">

$(function() {
	
	$.initCascadeSelector({
		"type" : "team",
		"selectOne":true,
		"yearChangeCallback" : function(year) {
			if(year != "") {
				$.SchoolTermSelector({
					"selector" : "#xq",
					"condition" : {"schoolYear" : year},
					"afterHandler" : function($this) {
						search();
						$("#xq_chzn").remove();
						$this.show().removeClass("chzn-done").chosen();
					}
				});
			} else {
				$("#xq").val("");
				$("#xq_chzn").remove();
				$("#xq").show().removeClass("chzn-done").chosen();
			}
		}
	});
});


	function search() {
		var val = {};
		var termCode = $("#xq").val();
		if (termCode != null && termCode != "") {
			val.termCode = termCode;
		}
		var id = "courseConfig_list_content";
		var url = "/bbx/courseConfig/index?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	加载创建对话框
	function loadCreatePage() {
		var schoolYear = $("#xn").val();
		var termCode = $("#xq").val();
		$.initWinOnTopFromLeft('创建', '${ctp}/bbx/courseConfig/creator?termCode=' + termCode + "&schoolYear=" + schoolYear , '800', '700');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		$.initWinOnTopFromLeft('编辑', '${ctp}/bbx/courseConfig/editor?id=' + id, '700', '400');
	}
	
	function loadViewerPage(id) {
		$.initWinOnTopFromLeft('详情', '${ctp}/bbx/courseConfig/viewer?id=' + id, '700', '400');
	}
	
	function startCourseConfig(id){
		$.post("${ctp}/bbx/courseConfig/startCourseConfig", 
			{id:id}, 
			function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						search();
					} else {
						$.error(data.responseData);
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			}
		);
	}
	
	function stopCourseConfig(id){
		$.post("${ctp}/bbx/courseConfig/stopCourseConfig", 
			{id:id}, 
			function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						search();
					} else {
						$.error(data.responseData);
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			}
		);
	}
	
	function publishedResults(id){
		$.post("${ctp}/bbx/courseConfig/publishedResults", 
			{id:id}, 
			function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						search();
					} else {
						$.error(data.responseData);
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			}
		);
	}
	
	
	
	function loadStatisticsCourseStudentPage(id) {
		$.initWinOnTopFromLeft('科目组报名人数', '${ctp}/bbx/courseConfig/statisticsCourseStudent?id=' + id, '700', '600');
	}
	
	function loadStatisticsCourseGroupStudentPage(id) {
		$.initWinOnTopFromLeft('报名名单', '${ctp}/bbx/courseConfig/statisticsCourseGroupStudentByCoursesIds?id=' + id, '1000', '700');
	}
	
	function loadNotSignUpStudentPage(id) {
		$.initWinOnTopFromLeft('未报名名单', '${ctp}/bbx/courseConfig/notSignUpStudent?id=' + id, '700', '500');
	}
	
	function setLimitedNumPage(id) {
		$.initWinOnTopFromLeft('设置报名人数上限', '${ctp}/bbx/courseConfig/setLimitedNum?id=' + id, '700', '500');
	}
	
	
	
	// 	删除对话框
	function deleteObj(obj, id) {
		$.confirm("确定执行此次操作？此操作会删除该选课及所有相关课程数据！", function() {
			executeDel(obj, id);
		});
	}

	// 	执行删除
	function executeDel(obj, id) {
		var loader = new loadLayer();
		loader.show();
		$.post("${ctp}/bbx/courseConfig/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					loader.close();
					$.success("删除成功");
					$("#" + id + "_li").remove();
				} else if ("fail" === data) {
					loader.close();
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
</script>
</html>