<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="platform.szxyzxx.web.common.contants.SysCacheHttpUrlAccessor"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="/views/embedded/common.jsp"%>
<style type="text/css">
.form-horizontal .control-group {
	margin-bottom: 0;
}

.form-horizontal .control_1 {
	margin-left: 0;
	float:left;
	min-height:280px;
}

select[multiple] {
	height: 251px;
}

.chzn-container .chzn-drop {
	height: 280px;
}

.clear {
	clear: both;
}
.chzn-container-single .chzn-single{
	display:none;
}
.chzn-container .chzn-drop{
	top:0;
}
.chzn-container .chzn-results .result-selected {
background-color: #3875d7;
color: #fff;
}
</style>
</head>
<body>
	<div class="row-fluid">
		<div class="span12">
			<form class="form-horizontal" style="margin-bottom: 0">
				<div class="control_1 span2" style="width:33%;">
					<div class="control-group">
						<select id="xn_select" class="span12 chzn-select">
						
						</select>
					</div>
				</div>
				<div class="control_1 span2" style="width:33%;">
					<div class="control-group">
						<select id="njxx_select" class="span12 chzn-select">
							<option></option>
						</select>
					</div>
				</div>
				<div class="control_1 span2" style="width:33%;">
					<div class="control-group">
						<select id="bjxx_select" class="span12 chzn-select" data-placeholder="Your Favorite of Bear">
							<option></option>
						</select>
					</div>
				</div>
				<div class="clear"></div>
				<div class="form-actions tan_bottom">
					<input type="hidden" id="id" name="id" value="${role.id}"/>
					<button class="btn btn-warning" type="button" onclick="saveOrUpdate();">确定</button>
				</div>
			</form>
			
		</div>
	</div>
	<script type="text/javascript">
		var index = parent.layer.getFrameIndex(window.name);
		$(function() {
			initXnSelector();
			initXnSelectorClick();
			initGradeSelectorClick();
			
			$(".btn-primary").click(function() {
				var bjxxSelectOption = $("#bjxx_select").find("option")
				var bjxxSelectedOp = $("#bjxx_select_chzn .chzn-results .result-selected");
				if(bjxxSelectedOp.length <= 0) {
					$.error("设置失败，您未选择班级");
					return;
				}
				var index = parseInt(bjxxSelectedOp.attr("id").replace("bjxx_select_chzn_o_", ""));
				var $option = $(bjxxSelectOption.get(index));
				var bjDm = $option.attr("value");
				var bjMc = bjxxSelectedOp.text();
				$("#${param.idTo}", window.parent.document).val(bjDm);
				$("#${param.titleTo}", window.parent.document).val(bjMc);
				$.success("设置成功");
				$.closeWindow();
			});
			
		});
		
		function initXnSelector() {
			var $xn_selector = $("#xn_select");
			$.getSchoolYear({}, function(data) {
				$.each(data, function(index, value) {
					$xn_selector.append("<option value='" + value.year + "'>" + value.name + "</option>");
				});
				$(".chzn-select").chosen();
				$(".chzn-container .chzn-drop").css("left", 0);
				Chosen.prototype.results_hide = function() {
					this.dropdown.css({
						"left" : "-0px"
					});
					return this.results_showing = false;
				};
			})
		}
		
		function initXnSelectorClick() {
			var gradeSelect = null
			$(".control_1").on("click", "#xn_select_chzn .chzn-drop .chzn-results li",function() {
				var xnSelectOption = $("#xn_select").find("option");
				var $this = $(this);
				var index = parseInt($this.attr("id").replace("xn_select_chzn_o_", ""));
				var $option = $(xnSelectOption.get(index));
				var schoolYear = $option.attr("value");
				var requestData = {"schoolYear" : schoolYear};
				if(gradeSelect == null) {
					gradeSelect = $("#njxx_select");
				}
				$.getGrade(requestData, function(data) {
					gradeSelect.html("");
					$.each(data, function(index, value) {
						gradeSelect.append( "<option value='" + value.id + "'>" + value.name + "</option>" );
					});
					gradeSelect.removeClass("chzn-done");
					gradeSelect.css("display", "block");
					$("#njxx_select_chzn").remove();
					gradeSelect.chosen();
					$(".chzn-container .chzn-drop").css("left", 0);
					Chosen.prototype.results_hide = function() {
						this.dropdown.css({
							"left" : "-0px"
						});
						return this.results_showing = false;
					};
					if(data.length <= 0) {
						$("#njxx_select_chzn .chzn-search input").css("left", "4px");
						$("#njxx_select_chzn .chzn-results").css("top", "30px");
					}
				});
			});
		}
		
		function initGradeSelectorClick() {
			var bjxxSelect = null
			$(".control_1").on("click", "#njxx_select_chzn .chzn-drop .chzn-results li",function() {
				var njxxSelectOption = $("#njxx_select").find("option");
				var $this = $(this);
				var index = parseInt($this.attr("id").replace("njxx_select_chzn_o_", ""));
				var $option = $(njxxSelectOption.get(index));
				var njDm = $option.attr("value");
				var requestData = {"gradeId" : njDm};
				if(bjxxSelect == null) {
					bjxxSelect = $("#bjxx_select");
				}
				
				$.getTeam(requestData, function(data) {
					bjxxSelect.html("");
					$.each(data, function(index, value) {
						bjxxSelect.append( "<option value='" + value.id + "'>" + value.name + "</option>" );
					});
					bjxxSelect.removeClass("chzn-done");
					bjxxSelect.css("display", "block");
					$("#bjxx_select_chzn").remove();
					bjxxSelect.chosen();
					$(".chzn-container .chzn-drop").css("left", 0);
					Chosen.prototype.results_hide = function() {
						this.dropdown.css({
							"left" : "-0px"
						});
						return this.results_showing = false;
					};
					if(data.length <= 0) {
						$("#bjxx_select_chzn .chzn-search input").css("left", "4px");
						$("#bjxx_select_chzn .chzn-results").css("top", "30px");
					}
				});
			});
		}
	</script>
</body>
</html>