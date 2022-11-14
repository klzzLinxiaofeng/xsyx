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
									<div class="select_div"><span>年级：</span> <select id="nj" name="nj" style="width:150px;"></select></div>
									<div class="select_div" style="display: none;"><span>班级：</span> <select id="bj" name="bj" class="chzn-select" style="width:150px;"></select></div>
									<button type="button" class="btn btn-primary" onclick="search()">查询</button>
									<div class="clear"></div>
								</div>
							</div>
						</div>
					</div>
					
					<div id="content">
						
					</div>
					<div class="clear"></div>
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

	function search(){
		var termCode = $("#xq").val();
		if(termCode == ""){
			$.error("请选择学期");
			return;
		}	
		var gradeId = $("#nj").val();
		if(gradeId == ""){
			$.error("请选择年级");
			return;
		}	
		//alert(termCode + "," + gradeId);
		/* var loader = new loadLayer();
		loader.show(); */
		$("#content").load(
			'${ctp}/bbx/courseConfig/getStaContentPage',	
			{"termCode": termCode, "gradeId": gradeId},
			function(){
	        	//loader.close();
	        }
		);
	} 
</script>
</html>