<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>发展评价卡</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="发展评价卡" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							发展评价卡
						</h3>
					</div>
					<div class="content-widgets" style="padding: 20px 20px 1px 20px;">
							<div class="check-rated">
							<div class="minutes-rated">
	                            <a href="javascript:void(0);" onclick="index()">发展评价卡查看</a>
	                            <a href="javascript:void(0);" class="see-rated">发展评价卡录入</a>
	                        </div>
                        <div class="card_detail">
									<div class="project-rated">
										<div class="content-widgets">
											<div class="widget-container" style="padding: 20px 0 0 0">
													<div class="select_b" id="sel_div">
														<div class="select_div" >
															<span>学年：</span> <select id="xn" name="xn" style="width:150px;"></select>
														</div>
													<c:choose>
														<c:when test="${param.type==1}">
															<div class="select_div">
																<span>学期：</span> <select id="xq" name="xq" class="chzn-select" style="width:150px;" onchange="getClass()"></select>
															</div>
															<div class="select_div">
																<span>班级：</span> <select id="teamId" name="teamId" class="chzn-select" style="width:150px;" onchange="search()"></select>
															</div>
														</c:when>
														<c:otherwise>
															<div class="select_div">
																<span>学期：</span> <select id="xq" name="xq" class="chzn-select" style="width:150px;" onchange="getMonth()"></select>
															</div>
															<div class="select_div">
																<span>年级：</span> <select id="nj" name="nj" class="chzn-select" style="width:150px;"></select>
															</div>
															<div class="select_div">
																<span>班级：</span> <select id="bj" name="bj" class="chzn-select" style="width:150px;" onchange="search()"></select>
															</div>
														</c:otherwise>
													</c:choose>
														<div class="select_div">
															<span>月次：</span> <input type="text" class="Wdate" id="d4" onFocus="WdatePicker({dateFmt:'yyyy年M月',minDate:begin,maxDate:end,onpicked:search})" style="width:150px;"/>
														</div>
														<p class="btn_link"
															style="float: right; margin: 10px 10px 0 0;">
															<a href="javascript:void(0)" class="a4" onclick="save();">保存</a>
														</p>
														<div class="clear"></div>
													</div>
											</div>
										</div>
										<div id="list">
										
										</div>
									</div>
								</div>
						</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	var termCodeNow = "${termCodeCurrent}";
	
	$(".minutes-rated a").click(function(){
	    $(".minutes-rated a").removeClass("see-rated");
	    $(this).addClass("see-rated");
	    var i=$(this).index();
	//     $(".project-rated").hide();
	//     $(".project-rated").eq(i).show();
	});
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
			"teamCallback" : function() {
				var s1 = $('#bj').val();
				if (bjId != s1) {
					search();
				}
				bjId = s1;
			}
		});
		//termCodeNow = $("#xq").val();
// 		search();
	});
	var bjId;
	var begin;
	var end;
	//去拿到学期起始时间或结束时间
	function getMonth(){
		$("#d4").val("");
		var term=$('#xq').val();
		$("#xq").chosen();
		if("" === term || "undefind" === term){
			return false;
		}
		var $requestData = {};
		$requestData.code=$('#xq').val();
		$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json", $requestData, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				begin=data.begin;
				end=data.end;
				if (begin != '') {
					var date;
					var myarray;
					var today = new Date().Format("yyyy-MM-dd");
					if(today>begin&&today<end){
						myarray = today.split("-");
					}else{
						myarray = begin.split("-");
					}
					if (myarray[1].charAt(0) != '0') {
						date = myarray[0]+ "年"+ myarray[1]+ "月";
					} else {
						date = myarray[0]+ "年"+ myarray[1].charAt(1)+ "月";
					}
					$('#d4').val(date);
				}
			}
			var teamId = $("#bj").val();
			if(teamId!=""){
				search();
			}
		});
	}
	
	
	function search(){
		var loader = new loadLayer();
		var year = $("#xn").val();
		var month = $("#d4").val();
		var termCode = $("#xq").val();
		var teamId = $("#bj").val();
		if($("#sel_div").find("#nj").length>0){
			nj = $("#nj").val();
			teamId = $("#bj").val();
		}else{
			teamId = $("#teamId").val();
			$("#teamId").chosen();
			if(teamId==""){
				$.error("暂无班级数据信息，请联系管理员");
				return false;
			}
		}
		if ("" === year || "undefind" === year) {
			$.error("请选择学年");
			return false;
		}
		if ("" === termCode || "undefind" === termCode) {
			$.error("请选择学期");
			return false;
		}
		if ("" === teamId || "undefind" === teamId) {
			$.error("请选择班级");
				return false;
		}
		if ("" === month || "undefind" === month) {
			$.error("请选择月份");
			return false;
		}
		var $requestData = {
				"month":month,
				"termCode":termCode,
				"teamId":teamId,
				"year":year
		};
		var url = "${pageContext.request.contextPath}/teach/normalEvaluation/enterList";
		loader.show();
		$.post(url,$requestData,function(data,status){
			if(status === "success"){
				$("#list").html(data);
			}
			loader.close();
		});
	}
	
	function save(){
		var month = $("#d4").val();
		var termCode = $("#xq").val();
		var teamId = $("#teamId").val();
		if($("#sel_div").find("#nj").length>0){
			nj = $("#nj").val();
			teamId = $("#bj").val();
		}else{
			teamId = $("#teamId").val();
			if(teamId==""){
				$.error("暂无班级数据信息，请联系管理员");
				return false;
			}
		}
		var array = [];
		$(".data #studentId").each(function(){
			var studentId = $(this).val();
			var score = $(this).next().val();
			var response = {studentId:studentId,score:score};
			array.push(response);
		})
		var json = JSON.stringify(array);
		if ("" === termCode || "undefind" === termCode) {
			$.error("请选择学期");
			return false;
		}
		if ("" === teamId || "undefind" === teamId) {
			$.error("请选择班级");
			return false;
		}
		if ("" === month || "undefind" === month) {
			$.error("请选择月份");
			return false;
		}
		if(termCodeNow != termCode){
			$.error("已超过当前学年学期时间段，不可以重新评定");
			return false;
		}
		var $requestData = {
				"month":month,
				"termCode":termCode,
				"teamId":teamId,
				"json":json
		};
		var url = "${pageContext.request.contextPath}/teach/normalEvaluation/save";
		$.post(url,$requestData,function(data,status){
			if(status === "success"){
				data = eval("("+data+")");
				if(data.info === "success"){
					setTimeout(function () {
						$.success("保存成功");
					},500)
					search();
				}else if(data.info === "fail"){
					$.error("用户角色没有编辑权限");
				}
			}
		});
	}
	
	function getClass(){
		var schoolYear = $("#xn").val();
		var url = "${pageContext.request.contextPath}/teach/teamEvaluation/getTeam";
		$.post(url, {"schoolYear":schoolYear}, function(data,status) {
			var obj = eval("(" + data + ")");
			if(status == "success"){
				$("#teamId").html("");
				for(var i=0; i<obj.length;i++){
					var opt = "<option value='"+obj[i].teamId+"'>"+obj[i].teamName+"</option>";
					$("#teamId").append(opt);
				}
				if(obj.length == 0){
	 				$("#teamId").append("<option value=''>请选择</option>");
				}
				//$("#teamId").chosen();
				var selectObj = $("#teamId"); 
				selectObj.parent().children().remove('div'); 
				selectObj.removeClass(); 
				selectObj.addClass("chzn-select"); 
				selectObj.chosen(); 
			}
			getMonth();
		});
	}
	
	function index(){
		window.location.href="${pageContext.request.contextPath}/teach/normalEvaluation/index?type=${param.type}&dm=${param.dm}";
	}
</script>
</body>
</html>