<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/res/qyjx/css/qyzj_dxa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>

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
		<p class="zk_sq" style="display:none;">
			<b class="zk" style="display: none">展开<i class="i1"></i></b> 
			<b class="sq"style="display: none">收起<i class="i2"></i></b>
		</p>
		<div class="clear"></div>
	</div>
	<div class="xueduan">
		<label>科目：</label>
		<div class="xd" id="subject">
		</div>
		<div class="clear"></div>
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
	
	var gradeWidgetHandler = null;
	
	$("body").on("click",".xdkm_div .xd_km .xueduan .xd a",function(){
		$(this).siblings().removeClass("btn-blue");
		$(this).addClass("btn-blue");
	})

	$.gradeWidget = function(type, afterHandler) {
		gradeWidgetHandler = afterHandler;
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
				var grade = "<a href=\"javascript:void(0)\" onclick=\"$.getTeamByGradeId(0)\">全部</a>";
				for(var key in result) {
					grade += "<a href=\"javascript:void(0)\" onclick=\"$.getTeamByGradeId("+result[key]+")\">"+key+"</a>";
				}
				$("#grade").html(grade);
				$("#grade").children("a").eq(0).click();
			}
		});
	}
	
	$.getTeamByGradeId = function(gradeId) {
		$('#team').height('auto');
		$.get("${ctp}/base/widget/getTeamByGradeId", {"gradeId":gradeId}, function(data, status) {
			if ("success" === status) {
				var result = JSON.parse(data);
				var team = "<a href=\"javascript:void(0)\" onclick=\"$.getSubjectByGradeId("+gradeId+",null)\">全部</a>";
				for(var key in result) {
					team += "<a href=\"javascript:void(0)\" onclick=\"$.getSubjectByGradeId("+gradeId+","+result[key]+")\">"+key+"</a>";
				}
				$("#team").html(team);
				//收縮 展開
				var team_content =  $('#team').height();
				console.log(team_content);
			    if(team_content>103){
			        $('#team').height('90');
			        $('p.zk_sq').show();
			        $('.sq').hide();
			        $('.zk').show();
			    }else{
			    	
			    	$('p.zk_sq').hide();
			    	$('.zk,.sq').hide();
			    }
				$("#team").children("a").eq(0).click();
			}
		});
	}
	
	$.getSubjectByGradeId = function(gradeId, teamId) {
		$.get("${ctp}/base/widget/getSubjectByGradeId", {"gradeId":gradeId}, function(data, status) {
			if ("success" === status) {
				var result = JSON.parse(data);
				var subject = "<a href=\"javascript:void(0)\" onclick=\"$.getSelectDate("+gradeId+","+teamId+",null)\">全部</a>";
				for(var key in result) {
					subject += "<a href=\"javascript:void(0)\" onclick=\"$.getSelectDate("+gradeId+","+teamId+","+key+")\">"+result[key]+"</a>";
				}
				$("#subject").html(subject);
				$("#subject").children("a").eq(0).click();
			}
		});
	} 
	
	$.getTeacherTeachByType = function() {
		$.get("${ctp}/base/widget/getTeacherTeachByType", {"type":"grade"}, function(data, status) {
			if ("success" === status) {
				var result = JSON.parse(data);
				var grade = "";
				for(var key in result) {
					grade += "<a href=\"javascript:void(0)\" onclick=\"$.getTeacherTeachTeam("+key+")\">"+result[key]+"</a>";
				}
				$("#grade").html(grade);
				$("#grade").children("a").eq(0).click();
			}
		});
	}
	
	$.getTeacherTeachTeam = function(gradeId) {
		$.get("${ctp}/base/widget/getTeacherTeachByType", {"type":"team","gradeId":gradeId}, function(data, status) {
			if ("success" === status) {
				var result = JSON.parse(data);
				var team = "";
				for(var key in result) {
					team += "<a href=\"javascript:void(0)\" onclick=\"$.getTeacherTeachSubject("+gradeId+","+key+")\">"+result[key]+"</a>";
				}
				$("#team").html(team);
				$("#team").children("a").eq(0).click();
			}
		});
	}
	
	$.getTeacherTeachSubject = function(gradeId, teamId) {
		$.get("${ctp}/base/widget/getTeacherTeachByType", {"type":"subject","gradeId":gradeId, "teamId":teamId}, function(data, status) {
			if ("success" === status) {
				var result = JSON.parse(data);
				var subject = "<a href=\"javascript:void(0)\" onclick=\"$.getSelectDate("+gradeId+","+teamId+",)\">全部</a>";
				for(var key in result) {
					subject += "<a href=\"javascript:void(0)\" onclick=\"$.getSelectDate("+gradeId+","+teamId+","+key+")\">"+result[key]+"</a>";
				}
				$("#subject").html(subject);
				$("#subject").children("a").eq(0).click();
			}
		});
	} 
	
	$.getSelectDate = function(gradeId, teamId, subjectCode) {
		var param = {"gradeId":gradeId, "teamId":teamId, "subjectCode":subjectCode};
		gradeWidgetHandler(param);
	}
	
});


</script>