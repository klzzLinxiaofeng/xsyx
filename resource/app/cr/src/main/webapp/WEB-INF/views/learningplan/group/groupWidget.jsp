<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>

<div class="ku_select">
	<div class="xdkm_div">
		<div class="xd_km">
			 <div class="xueduan">
				<label>年级：</label>
				<div class="xd" id="grade">
				</div>
				<div class="clear"></div>
			</div> 
			<div class="xueduan">
				<label>班级：</label>
				<div class="xd xdbj" id="team">
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</div>		
<script type="text/javascript">
$(function(){
	
			  
	$('.zk').click(function(){
	    $(this).hide();
	    $(this).parent().prev('#team').height('auto');
	    $(this).next('.sq').show();
	});

	$('.sq').click(function(){
		$(this).parent().prev('#team').height('90');
	    $(this).hide();
	    $(this).prev('.zk').show();
	}); 	
	
	$("body").on("click",".xdkm_div .xd_km .xueduan .xd a",function(){
		$(this).siblings().removeClass("btn-blue");
		$(this).addClass("btn-blue");
	})

	$.gradeWidget = function(type, afterHandler) {
		if(type==0) {
			$.getTeacherTeachByType();
		} else {
			$.getAllGrade();
		}
	}
	
	$.getAllGrade = function() {
		$.get("${ctp}/base/widget/getSchoolGrade", {}, function(data, status) {
			if ("success" === status) {
				var result = JSON.parse(data);
				var grade = "";
				// var grade = "<a href=\"javascript:void(0)\" onclick=\"getTeamByGradeId(0)\">全部</a>";
				for(var key in result) {
					grade += "<a href=\"javascript:void(0)\" onclick=\"getTeamByGradeId("+result[key]+")\">"+key+"</a>";
				}
				$("#grade").html(grade);
				$("#grade").children("a").eq(0).addClass('on')
				$("#grade").children("a").eq(0).click();
			}
		});
	}
	
	$.getTeacherTeachByType = function() {
		$.get("${ctp}/base/widget/getTeacherTeachByType", {"type":"grade"}, function(data, status) {
			if ("success" === status) {
				var result = JSON.parse(data);
				var grade = "";
				for(var key in result) {
					grade += "<a href=\"javascript:void(0)\" onclick=\"getTeacherTeachTeam("+key+")\">"+result[key]+"</a>";
				}
				$("#grade").html(grade);
				$("#grade").children("a").eq(0).click();
			}
		});
	}
/* 	点击年级加class=on */
	$('body').on('click','#grade a',function(){
		$('.nicescroll-rails').remove();
		$("#grade a").removeClass('on');
		$(this).addClass('on')
	})
	/* 	点击班级加class=on */
	$('body').on('click','#team a',function(){
		$('.nicescroll-rails').remove();
		$("#team a").removeClass('on');
		$(this).addClass('on')
	})
	
});

function getTeacherTeachTeam(gradeId){
	$.get("${ctp}/base/widget/getTeacherTeachByType", {"type":"team","gradeId":gradeId}, function(data, status) {
		if ("success" === status) {
			var result = JSON.parse(data);
			var team = "";
			for(var teamId in result) {
				team += "<a href='javascript:void(0)' class='getGroup' onclick='getGroup("+gradeId+","+teamId+")' gradeId="+gradeId+" teamId="+teamId+">"+result[teamId]+"</a>";
			}
			$("#team").html(team);
			$("#team").children("a").eq(0).addClass('on')
			$("#team").children("a").eq(0).click();
		}
	});
}
 function getTeamByGradeId (gradeId){
	$.get("${ctp}/base/widget/getTeamByGradeId", {"gradeId":gradeId}, function(data, status) {
		if ("success" === status) {
			var result = JSON.parse(data);
			var team = "";
			for(var teamName in result) {
				team += "<a href='javascript:void(0)' class='getGroup' onclick='getGroup("+gradeId+","+result[teamName]+")' gradeId="+gradeId+" teamId="+result[teamName]+">"+teamName+"</a>";
			}
			$("#team").html(team);
			$("#team").children("a").eq(0).addClass('on')
			$("#team").children("a").eq(0).click();
		}
	});
}

function getGroup(gradeId, teamId){
	$("#group_list").html('')
	$.post("${ctp}/learningPlan/getGroup", {"gradeId":gradeId, "teamId":teamId}, function(data,status){
		$("#group_list").html(data)
	});
}

</script>