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
						<select id="njxx_select" class="span12 chzn-select">
						
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
				<div class="control_1 span2" style="width:33%;">
					<div class="control-group">
						<select id="ryxx_select" class="span12 chzn-select" data-placeholder="Your Favorite of Bear">
							<option></option>
						</select>
					</div>
				</div>
				<div class="clear"></div>
			</form>
			<div style="text-align:center">
				<button class="btn btn-primary" type="button" style="margin:10px auto 0;">确认</button>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {
			initGrade();
			initGradeSelector();
			initClassSelector();
			
			$(".btn-primary").click(function() {
				var ryxxSelectOption = $("#ryxx_select").find("option")
				var ryxxSelectedOp = $("#ryxx_select_chzn .chzn-results .result-selected");
				if(ryxxSelectedOp.length <= 0) {
					$.error("设置失败，您未选择人员");
					return;
				}
				var index = parseInt(ryxxSelectedOp.attr("id").replace("ryxx_select_chzn_o_", ""));
				var $option = $(ryxxSelectOption.get(index));
				var ryDm = $option.attr("value");
				var ryMc = ryxxSelectedOp.text();
				$("#${param.idTo}", window.parent.document).val(ryDm);
				$("#${param.titleTo}", window.parent.document).val(ryMc);
				$.success("设置成功");
				$.closeWindow();
			});
			
		});
		
		function initGrade() {
			var currentUserXxDm = "${sessionScope[sca:currentUserKey()].xxDm}";
			var $requestData = {tn : "t_dm_gy_njxx"};
			if(currentUserXxDm != "") {
				$requestData.pn = "xxDm";
				$requestData.value = currentUserXxDm;
			}    
			$.createSelectV3("#njxx_select", $requestData, "", function(value) {
				return {'val' : value.njDm, 'title' : value.njMc};
			}, function() {
				$(".chzn-select").chosen();
				$(".chzn-container .chzn-drop").css("left", 0);
				Chosen.prototype.results_hide = function() {
					this.dropdown.css({
						"left" : "-0px"
					});
					return this.results_showing = false;
				};
			});
		}
		
		function initGradeSelector() {
			var bjxxSelect = null
			$(".control_1").on("click", "#njxx_select_chzn .chzn-drop .chzn-results li",function() {
				var njxxSelectOption = $("#njxx_select").find("option");
				var $this = $(this);
				var index = parseInt($this.attr("id").replace("njxx_select_chzn_o_", ""));
				var $option = $(njxxSelectOption.get(index));
				var njDm = $option.attr("value");
				var requestData = {"tn" : "t_dm_gy_bjxx", "pn" : "njDm", "value" : njDm};
				if(bjxxSelect == null) {
					bjxxSelect = $("#bjxx_select");
				}
				$.get("${pageContext.request.contextPath}" + "<%= SysCacheHttpUrlAccessor.CACHE_JC_FINDBYPARAM_URL%>", requestData, function(data, status) {
					if("success" === status) {
						data = eval("(" + data + ")");
						bjxxSelect.html("");
						$.each(data, function(index, value) {
							bjxxSelect.append( "<option value='" + value.bjDm + "'>" + value.bjMc + "</option>" );
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
					}
				});
			});
		}
		
		function initClassSelector() {
			var ryxxSelect = null
			$(".control_1").on("click", "#bjxx_select_chzn .chzn-drop .chzn-results li",function() {
				var bjxxSelectOption = $("#bjxx_select").find("option");
				var $this = $(this);
				var index = parseInt($this.attr("id").replace("bjxx_select_chzn_o_", ""));
				var $option = $(bjxxSelectOption.get(index));
				var bjDm = $option.attr("value");
				var requestData = {"bjDm" : bjDm};
				if(ryxxSelect == null) {
					ryxxSelect = $("#ryxx_select");
				}
				$.get("${pageContext.request.contextPath}/xsgz/xsXsxx/xsxxList", requestData, function(data, status) {
					if("success" === status) {
						data = eval("(" + data + ")");
						ryxxSelect.html("");
						$.each(data, function(index, value) {
							ryxxSelect.append( "<option value='" + value.xsxxDm + "'>" + value.xm + "</option>" );
						});
						ryxxSelect.removeClass("chzn-done");
						ryxxSelect.css("display", "block");
						$("#ryxx_select_chzn").remove();
						ryxxSelect.chosen();
						$(".chzn-container .chzn-drop").css("left", 0);
						Chosen.prototype.results_hide = function() {
							this.dropdown.css({
								"left" : "-0px"
							});
							return this.results_showing = false;
						};
						if(data.length <= 0) {
							$("#ryxx_select_chzn .chzn-search input").css("left", "4px");
							$("#ryxx_select_chzn .chzn-results").css("top", "30px");
						}
					}
				});
			});
		}
	</script>
</body>
</html>