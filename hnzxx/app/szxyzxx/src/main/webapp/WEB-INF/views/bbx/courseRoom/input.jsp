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
	width:465px;
	min-height:100px;
}
ul li a{
	float: left;
    font-size: 14px;
    padding:2px 10px;
    margin-right: 15px;
    margin-bottom: 10px;
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
								年级：
							</label>
							<div class="controls">
							<input type="text" id="{gradeName}" name="gradeName" class="span13" placeholder="" value="${gradeName}" 
								disabled="disabled" readonly="readonly" />
							</div>
						</div>
							<div class="control-group">
								<label class="control-label">
									可用教室：
								</label>
								<div class="controls">
									<div class="controls-div">
										<ul>
											<c:forEach items="${roomList}" var="room">
												<li><a href="javascript:void(0)" data-room-code="${room.id}">${room.name}</a></li>						
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
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
	var checker;

	$(function(){
		$(".controls-div ul li a").on("click",function(){
			$(this).toggleClass("on");
		});
		
		checker = initValidator();
	});
	
	function initValidator() {
		return $("#course_form").validate({
			errorClass : "myerror",
			rules : {
				/* "classHourWeek" : {
					required : true
				},
				"planCount" : {
					required : true
				} */
			},
			messages : {
				
			}
		});
	}
	
	//保存或更新修改
	function saveOrUpdate() {
		if (checker.form()) {
			var loader = new loadLayer();
			var $requestData = {};
			var jsonArray = [];
			$(".controls-div ul li .on").each(function(){
				jsonArray.push($(this).attr("data-room-code"));
			});	
			var gradeId = $("#gradeId").val();
			var termCode = $("#termCode").val();		
			$requestData.roomIds = JSON.stringify(jsonArray);
			$requestData.gradeId = gradeId;
			$requestData.termCode = termCode;
			var url = "${ctp}/bbx/courseRoom/creator";
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