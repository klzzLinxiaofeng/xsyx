<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	#daysPlansDiv input{
		margin:0 7px;
		position:relative;
		top:-1px;
	}
	.lessonDiv{
		margin:5px 0;
	}
	.form-horizontal .control-label{
		width:300px;
	}	
.form-horizontal .controls {
    margin-left: 320px;
}
</style>
<div class="row-fluid" id="setting_input_pane">
	<div class="span12">
		<div class="content-widgets" style="margin-bottom:0;">
			<div class="widget-container" style="background-color:#F8F8F8">
				<input type="hidden" id="syllabusId" name="syllabusId" value="${item.id}">
			</div>
		</div>
	</div>
</div>
<div class="row-fluid" style="margin-top: 15px;">
	<div id="syllabusArrangementPane"></div>
</div>
<script type="text/javascript">
	$(function() {
		var syllabusId = $("#syllabusId").val();
		if(syllabusId != "" && syllabusId != "undefind") {
			$("#open_close_btn").removeClass("close_sz").addClass("open_sz");
			$("#setting_input_pane").hide();
			showSyllabusArrangementPane(syllabusId);
		} else {
			$.error("请先设置好当前年级课程表的基础信息");
			$("#syllabusArrangementPane").html("");
		}
		$("#daysPlansDiv input").click(function(){
			var num=$("#days").val();
			if($(this).prop("checked") == true){
				$("#days").val(parseInt(num)+1);
			}else{
				$("#days").val(parseInt(num)-1);
			}
		})
	});
	
	/* function initValidator() {
		return $("#setting_form").validate({
			errorClass : "myerror",
			rules : {
				"days" : {
					required : true,
					digits : true
				},
				"lessonOfMorning" : {
					required : true,
					digits : true
				},
				"lessonOfAfternoon" : {
					required : true,
					digits : true
				},
				"lessonOfEvening" : {
					required : true,
					digits : true
				}
			},
			messages : {
			}
		});
	} */
</script>
