<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}


.row-fluid .span4 {
	width: 75%;
}
.myerror {
	color: red !important;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.controls-div{
	width:379px;
	min-height:100px;
}
ul li a{
	float: left;
    font-size: 14px;
    width: 79px;
    margin-right: 15px;
    margin-top: 10px;
    color: #fff;
    line-height: 30px;
    text-align: center;
    background:#a8a8a8;
    display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;
}
.on{
	background:#0d7bd5;
	color:#fff;
}
ul li a:hover{
	color:#FFF;
	background:#0d7bd5;
}


</style>
</head>
<body style="background-color: #ffffff !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<input type="hidden" id="gradeId" name="gradeId" value="${gradeId}" />
					<input type="hidden" id="termCode" name="termCode" value="${termCode}" />
					<form class="form-horizontal tan_form" id="course_form" action="javascript:void(0);">
							<div class="control-group">
								<label class="control-label">
									科目：
								</label>
								<div class="controls">
									<div class="controls-div">
										<ul>
											<c:forEach items="${subjectList}" var="subject">
												<li><a href="javascript:void(0)" data-subject-code="${subject.subjectCode }">${subject.subjectName }</a></li>						
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">
									每周学时：
								</label>
								<div class="controls">
								<input type="text" id="classHourWeek" name="classHourWeek" class="span13" placeholder="" value="${course.classHourWeek}">
								</div>
							</div>
							<%-- <div class="control-group">
								<label class="control-label">
									每科计划报名人数：
								</label>
								<div class="controls">
								<input type="text" id="planCount" name="planCount" class="span13" placeholder="" value="${course.planCount}">
								</div>
							</div> --%>
						<div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${course.id}" />
								<button class="btn btn-warning" type="button"
									onclick="saveOrUpdate();">确定</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	$(".controls-div ul li a").on("click",function(){
		$(this).toggleClass("on");
	})
});


	var checker;
	$(function() {
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#course_form").validate({
			errorClass : "myerror",
			rules : {
				"classHourWeek" : {
					required : true,
                    number : true
                },
				"planCount" : {
					required : true
				}
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $id = $("#id").val();
			var classHourWeek = $("#classHourWeek").val();
			var gradeId = $("#gradeId").val();
			var termCode = $("#termCode").val();
			//var planCount = $("#planCount").val();
			var $requestData = {};
			var jsonArray = [];
			$(".controls-div ul li .on").each(function(){
				jsonArray.push($(this).attr("data-subject-code"));
			});
			$requestData.subjectCode = JSON.stringify(jsonArray);
			$requestData.classHourWeek = classHourWeek;
			$requestData.gradeId = gradeId;
			$requestData.termCode = termCode;
			//$requestData.planCount = planCount;
			var url = "${ctp}/bbx/course/creator";
			if ("" != $id) {
				$requestData._method = "put";
				url = "${ctp}/bbx/course/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
							parent.core_iframe.search();
 						} else {
 							parent.core_iframe.search();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	}
	
</script>
</html>