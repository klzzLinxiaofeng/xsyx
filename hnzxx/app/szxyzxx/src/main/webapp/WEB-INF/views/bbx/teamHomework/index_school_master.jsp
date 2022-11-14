<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
<style type="text/css">
.container-fluid {
	padding-top: 20px;
}

.entry p {
	padding-top: 15px;
	padding-bottom: 10px;
	line-height: 24px;
}

.entry {
	background: url(${ctp }/res/css/bbx/images/context.jpg) 0px 0px;
}

.select_top {
	margin-top: 0px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<div class="widget-head">
							<div class="select_top">
								<div class="s1 s1_bg">
								       <span style="font-size: 16px;font-family: 微软雅黑;line-height: 35px;margin: 0 0 0 10px;">${team.name }</span>
								        <input id="teamId" value=${team.id }  type="hidden"/>
									<div class="content">
										<select id="xk" name="xn" class="chzn-select" style="margin:0;"></select> <input
											type="text" id="content" placeholder="内容" style="margin:0;">
										<div class="click">
											<button class="btn btn-success" type="button" id="sosuo"
												onclick="search()" >搜索</button>
											<button class="btn btn-primary" onclick="back()">返回</button>
											
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div>
				<div id="school_master_homework_content">
					<jsp:include page="./homework.jsp" />
				</div>
				<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
					<jsp:param name="id" value="school_master_homework_content" />
					<jsp:param name="url"
						value="/clazz/teamHomeWork/indexForSchoolMaster?sub=list&dm=${param.dm}" />
					<jsp:param name="pageSize" value="${page.pageSize}" />
				</jsp:include>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
				 search();
				 getSubject();

		$('#content').keydown(function(e){
			if(e.which==13){
				e.preventDefault();//取消回车键原有事件。
				$("#sosuo").click();
			}
		}); 
		//点击科目搜索
		$("#xk").on("change", function(){
			$("#sosuo").click();
		});
	});
	function getSubject(){
		/* $("#xk").html("");*/
		/* alert($("#team").val())  */
		var val = {
			teamId:$("#teamId").val()
		};
		$.get("${ctp}/clazz/teamHomework/subjectList", val, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$("#xk").html("");
				$("#xk").append("<option>全部学科</option>")
				$.each(data, function(index, value) {
					$("#xk").append("<option value='" + value.subjectCode + "'>" + value.subjectName + "</option>")
				});
			}
		});
		   
	}
	function search() {
		var val = {
				teamId:$("#teamId").val()
		};
		var subjectCode = $("#xk").find("option:selected").val()
		if (subjectCode != null && subjectCode != ""&&subjectCode != "全部学科") {
			val.subjectCode = subjectCode;
		}
		var content = $("#content").val();
		if (content != null && content != "") {
			val.content = content;
		}
		var id = "school_master_homework_content";
		var url = "/clazz/teamHomework/indexForSchoolMaster?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}



	//  加载浏览对话框
	function loadViewPage(homeworkId) {
		var teamId = $("#teamId").val();
		$.initWinOnTopFromLeft_bbx('浏览详情', '${ctp}/clazz/teamHomework/viewer?homeworkId=' + homeworkId+'&teamId='+teamId, '750', '350');
	}

	
	//返回对话框
	 function back(){
		 window.location.href="${ctp}/clazz/teamHomework/schoolMasterView";
	 }

</script>
</html>