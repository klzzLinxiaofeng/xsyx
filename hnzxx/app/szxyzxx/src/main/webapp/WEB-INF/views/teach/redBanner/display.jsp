<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<title>红旗公示</title>
</head>

<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="红旗公示" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							红旗公示
<%-- 							 <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}"> --%>
<!-- 							<p class="btn_link" style="float: right;"> -->
<!-- 								<a href="javascript:void(0)" onclick="save()" id="button_pd" class="a2">评定</a> -->
<!-- 								<a href="javascript:void(0)" onclick="" class="a3"><iclass="fa fa-plus"></i>导出</a>  -->
<!-- 								<a href="javascript:void(0)" onclick="set()" class="a4">设置评分标准</a> -->
<!-- 							</p> -->
<%-- 							</c:if> --%>
						</h3>
					</div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b" id="sel_div">

								<div class="select_div">
									<span>学年：</span><select id="xn" name="xn" class="chzn-select"
										style="width: 120px;"></select>
								</div>
								<div class="select_div">
									<span style="padding-left: 30px;">学期： </span> <select id="xq"
										name="xq" class="chzn-select" style="width: 160px;" onchange="getweek()"></select>
								</div>
								
								<c:choose>
									<c:when test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
										<div class="select_div">
											<span>年级：</span><select id="nj" name="nj" class="chzn-select" onchange="sure()"style="width: 120px;"></select>
										</div>
									</c:when>
									<c:otherwise>
										<div class="select_div">
											<span>年级：</span><select id="gradeId" name="gradeId" class="chzn-select" onchange="sure()"style="width: 120px;"></select>
										</div>
									</c:otherwise>
								</c:choose>
									<div class="select_div" style="display: none;">
											<span>班级：</span><select id="bj" name="bj" class="chzn-select" onchange=""style="width: 120px;"></select>
										</div>
<!-- 								<div class="select_div"> -->
<!-- 									<span>年级：</span><select id="nj" name="nj" class="chzn-select" onchange="sure()" -->
<!-- 										style="width: 120px;"></select> -->
<!-- 								</div> -->
								<div class="select_div">
									<span>周次：</span><select id="select_week" style="width: 240px;" onchange="sure()">
										<option value="">请选学期</option>
									</select>
								</div>
								 <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<p class="btn_link" style="float: right;margin:5px 5px 0 0">
									<a href="javascript:void(0)" onclick="save()" id="button_pd" class="a2">评定</a>
	<!-- 								<a href="javascript:void(0)" onclick="" class="a3"><i -->
	<!-- 									class="fa fa-plus"></i>导出</a>  -->
									<a href="javascript:void(0)" onclick="set()" class="a4">设置评分标准</a>
									 <input type="hidden" id="isvip" value="yes">
								</p>
								</c:if>
								<div class="clear"></div>
							</div>
								<div id="kb_tb"></div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
		var begin = "2015-03-14";
		var end = "2015-07-14";
		var termCodeNow;
		var isback = "${isback}";
		//去拿到学期起始时间或结束时间
//  		$('#xq').on('change', getweek);
		
	function getweek(){
		var isClear = false;
		var term = $('#xq').val();
		var flag = $("#isvip").val();
		
		if ("" === term || "undefind" === term) {
			isClear = true;
		} else {
			var $requestData = {};
			$requestData.code = $('#xq').val();
			$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json", $requestData, function(data, status) {
				if ("success" === status) {
					data = eval("(" + data + ")");
					begin = data.begin;
					end = data.end;
// 					var today = new Date().Format("yyyy-MM-dd");
					var today = "${today}";
					
					$.getWeek({
						"selector" : "#select_week",
						"begin" : begin,
						"end" : end,
						"today" : today,
						"isClear" : isClear,
						"isSelectCurrentWeek" : true,
						"clearedOptionTitle" : "请选择学期",
					});
					if(flag != null || flag != ""){
						sure();
					}
				}
			});
		}
		
		if(isback == "true"){
			var modifyCheckDate = "${checkDate}";
			$('#select_week').parent().children().remove('div'); 
		    $('#select_week').removeClass();
			$("#select_week option").each(function(){
				if($(this).text() == modifyCheckDate){
					$(this).attr("selected", "selected");
				    $('#select_week').addClass("chzn-select"); 
				    $('#select_week').chosen(); 
				}
		  	})
		}
			
		if(flag == undefined){
			//班主任年级显示
			var schoolYear = $("#xn").val();
			var url = "${pageContext.request.contextPath}/teach/teamEvaluation/getGrade";
			$.post(url, {"schoolYear":schoolYear}, function(data,status) {
				var obj = eval("(" + data + ")");
				if(status == "success"){
					$("#gradeId").html("");
					for(var i=0; i<obj.length;i++){
						var opt = "<option value='"+obj[i].gradeId+"'>"+obj[i].gradeName+"</option>";
						$("#gradeId").append(opt);
					}
					if(obj.length == 0){
		 				$("#gradeId").append("<option value=''>请选择</option>");
					}
					sure();
				}
			});	
		}

	}
		

	
	$(function() {
		var modifyYear = "${year}";
		var modifyTermCode = "${termCode}";
		var modifyGradeId = "${gradeId}";
		var modifyTeamId = "${teamId}";
		var modifyCheckDate = "${checkDate}";
		
		if(isback=="true"){
			$.initCascadeSelector({
				"type" : "team",
				"selectOne":true,
				"isEchoSelected" : true,
				"yearSelectedVal" : modifyYear,
				"yearChangeCallback" : function(year) {
					if (year != "") {
						$.SchoolTermSelector({
							"selector" : "#xq",
							"condition" : {"schoolYear" : year},
							"selectedVal": modifyTermCode,
							"afterHandler" : function($this) {
								$this.change();
								$("#xq_chzn").remove();
								$this.show().removeClass("chzn-done").chosen();
							}
						});

					} else {
						$("#xq").val("");
						$("#xq_chzn").remove();
						$("#xq").show().removeClass("chzn-done").chosen();
					}
				},
				"gradeSelectedVal" : modifyGradeId
			});
			termCodeNow = modifyTermCode;
			
		}else{
			$.initCascadeSelector({
				"type" : "team",
				"selectOne":true,
				"yearChangeCallback" : function(year) {
					if (year != "") {
						$.SchoolTermSelector({
							"selector" : "#xq",
							"condition" : {
								"schoolYear" : year
							},
	
							"afterHandler" : function($this) {
								$this.change();
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
			termCodeNow = $("#xq").val();
	 		
		}

	});

	function sure() {
		var year = $("#xn").val();
		var nj = null;
		
		if($("#sel_div").find("#nj").length>0){
			nj = $("#nj").val();
			
		}else{
			nj = $("#gradeId").val();
			if(nj == ""){
				$.error("暂无班级数据信息，请联系管理员");
				return false;
			}
		}
		
		var termCode = $("#xq").val();
        var dm="${param.dm}";
		var checkDate = $("#select_week").val();
		if ("" != year && "" != nj && "" != termCode && checkDate != "") {

			var $requestData = {};

			$requestData.termCode = termCode;

			$requestData.gradeId = nj;
			$requestData.checkDate = checkDate;
			$requestData.dm=dm;
			var loader = new loadLayer();
			loader.show();
			$.post("${pageContext.request.contextPath}/teach/redBanner/list",
					$requestData, function(data, status) {

						if ("success" === status) {

							$("#kb_tb").html("").html(data);

						}
						loader.close();
					});
		}
	}
	function save() {
		var year = $("#xn").val();
		var nj = $("#nj").val();

		var termCode = $("#xq").val();
		var checkDate = $("#select_week").val();
		if (nj == "") {
			$.error("年级不能为空");
			return false;
		}
		if (termCode == "") {
			$.error("学期不能为空");
			return false;
		}
		if (checkDate == "") {
			$.error("周次不能为空");
			return false;
		}
		if(termCodeNow != termCode){
			$.error("已超过当前学年学期时间段，不可以重新评定");
			return false;
		}
			
		$.post("${pageContext.request.contextPath}/teach/redBanner/judgeWeek", 
				{"checkDate":checkDate}, function(data,status) {
					
	       	data = eval("(" + data + ")");
			if(data.info == "success"){
				
				var $requestData = {};
				$requestData.checkDate = checkDate;
				$requestData.gradeId = nj;
				$requestData.termCode = termCode;
				var loader = new loadLayer();
				loader.show();
				$.post("${pageContext.request.contextPath}/teach/redBanner/save?dm=${param.dm}",
						$requestData, function(data, status) {
		      
					if ("success" === status) {
		                $.success("评定成功");
						$("#kb_tb").html("").html(data);
						
					}else{
				
						$.error("评定失败");
					}
					loader.close();		
				});
			}else{
				$.error("只能评定当前周次和前两周");
				return;
			}
			
		});
			
		
	}
	function set() {
		var year = $("#xn").val();
		var url = "${pageContext.request.contextPath}/teach/redBanner/getScoreList?year="+year;
		$.initWinOnTopFromTop("设置评分标准", url, 450, 480);
	}
	function toTeamEva(teamId){
		var year = $("#xn").val();
		var term = $('#xq').val();
		var manager = $("#isvip").val();
		var checkDate = $("#select_week").val();
		var nj = $("#nj").val();
		if(manager!='yes'){
			nj= $("#gradeId").val();
		}
 		window.location.href="${pageContext.request.contextPath}/teach/redBanner/toTeamEva?dm=${param.dm}&year="+year+"&termCode="+term+"&checkDate="+checkDate+"&gradeId="+nj+"&manager="+manager+"&teamId="+teamId;
	}
	$(function(){
		sure();
	});
</script>
</html>