<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>星级个人</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="星级个人" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>星级个人</h3>

					</div>
					<div class="content-widgets" style="padding: 20px 20px 1px 20px;">
						<div class="check-rated">
							<div class="minutes-rated">

								<a href="javascript:void(0);" id="team" name="month"
									onclick="toIndex(this)"
									<c:if test="${datetype eq 'month' && usertype eq 'team'}">class="see-rated"</c:if>>班级之星(月)</a>
								<a href="javascript:void(0);" id="grade" name="month"
									onclick="toIndex(this)"
									<c:if test="${datetype eq 'month' && usertype eq 'grade'}">class="see-rated"</c:if>>年级之星(月)</a>
								<a href="javascript:void(0);" id="school" name="month"
									onclick="toIndex(this)"
									<c:if test="${datetype eq 'month' && usertype eq 'school'}">class="see-rated"</c:if>>校园之星(月)</a>
								<a href="javascript:void(0);" id="team" name="term"
									onclick="toIndex(this)"
									<c:if test="${datetype eq 'term' && usertype eq 'team'}">class="see-rated"</c:if>>班级之星(学期)</a>
								<a href="javascript:void(0);" id="grade" name="term"
									onclick="toIndex(this)"
									<c:if test="${datetype eq 'term' && usertype eq 'grade'}">class="see-rated"</c:if>>年级之星(学期)</a>
								<a href="javascript:void(0);" id="school" name="term"
									onclick="toIndex(this)"
									<c:if test="${datetype eq 'term' && usertype eq 'school'}">class="see-rated"</c:if>>校园之星(学期)</a>
							</div>
							<input id="usertype" type="hidden" value="${usertype}"> <input
								id="datetype" type="hidden" value="${datetype}"> <input
								id="dm" type="hidden" value="${param.dm}">
							<div class="card_detail">
								<div class="project-rated">
									<div class="content-widgets">
										<div class="widget-container" style="padding: 5px 0 0 0">
											<div class="select_b">
												<div class="select_div">
													<span>学年：</span> <select id="xn" name="xn"
														class="chzn-select" style="width: 120px;"></select>
												</div>
												<c:choose>
													<c:when
														test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
														<div class="select_div">
															<span class="bt">学期：</span> <select id="xq" name="xq"
																class="chzn-select" style="width: 150px;"
																onchange="find()"></select>
														</div>

														<div class="select_div"
															style="<c:choose><c:when test="${usertype eq 'grade'||usertype eq 'team'}">display:inline;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
															<span class="bt">年级：</span> <select id="nj" name="nj"
																class="chzn-select" style="width: 90px;"></select>
														</div>

														<div class="select_div"
															style="<c:choose><c:when test="${usertype eq 'team'}">display:inline;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
															<span  class="bt">班级：</span> <select id="bj" name="bj"
																class="chzn-select" onchange="sure()"
																style="width: 120px;"></select>
														</div>

													</c:when>
													<c:otherwise>
														<div class="select_div">
															<span>学期：</span> <select id="xq" name="xq"
																class="chzn-select" style="width: 150px;"
																onchange="getNext()"></select>
														</div>
														<div class="select_div"
															style="<c:choose><c:when test="${usertype eq 'grade'}">display:inline;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
															<span>年级：</span> <select id="gradeId" name="gradeId"
																class="chzn-select" onchange="sure()" style="width: 90px;"></select>
														</div>
														<div class="select_div"
															style="<c:choose><c:when test="${usertype eq 'team'}">display:inline;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
															<span>班级：</span> <select id="teamId" name="teamId"
																class="chzn-select" onchange="sure()"
																style="width: 120px;"></select>
														</div>
													</c:otherwise>
												</c:choose>

												<div class="select_div"
													style="<c:choose><c:when test="${datetype eq 'month'}">display:inline;</c:when><c:otherwise>display:none;</c:otherwise></c:choose>">
													<span>月份：</span><input type="text" class="Wdate" id="d4"
														style="width: 172px;" />
												</div>






												<p class="btn_link"
													style="float: right; line-height: 47px; margin-right: 10px;">
													<!-- 														<a href="javascript:void(0)" class="a3" onclick=""><i class="fa  fa-plus"></i> 导出</a>  -->
													<!-- 														<a href="javascript:void(0)" class="a3" onclick="">导出星级个人照片</a>  -->
													<c:if
														test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">


														<a href="javascript:void(0)" class="a2" onclick="save()"
															id="button_pd">评定</a>


														<a href="javascript:void(0)" class="a4" onclick="set()">设置</a>
														<input type="hidden" id="isvip" value="yes">
													</c:if>
												</p>
												<div class="clear"></div>

											</div>
											<div id="kb_tb"></div>
	

										</div>
									</div>
									<script type="text/javascript">
										var begin;
										var end;
										var bjId;
										var termCodeNow;
										$("#d4").on('focus', function() {
											WdatePicker({
												dateFmt : 'yyyy年M月',
												minDate : begin,
												maxDate : end,
												onpicked : sure
											});

										});

										$(function() {
											var usertype = $('#usertype').val();
											var xq = $('#xq').val();
											$.initCascadeSelector({
														"type" : "team",
														"selectOne" : true,
														"yearChangeCallback" : function(
																year) {
															if (year != "") {
																$.SchoolTermSelector({
																			"selector" : "#xq",
																			"condition" : {
																				"schoolYear" : year
																			},"afterHandler" : function($this) {
																				$this.change();
																				$("#xq_chzn").remove();
																				$this.show().removeClass("chzn-done").chosen();
																			}
																		});

															} else {
																$("#xq").val("");
																$("#xq").chosen();
																$("#xq_chzn").remove();
																$("#xq").show().removeClass("chzn-done").chosen();
															}
														},"teamCallback" : function() {
															var s1 = $('#bj').val();
															if (bjId != s1) {
																if (usertype != 'school') {

																	find();
																}

															}
															bjId = s1;
														}

													});
											termCodeNow = $("#xq").val();

										});

										function find() {

											var nj;
											var bj;
											var flag = $("#isvip").val();
											var usertype = $('#usertype').val();
											if (flag != null && flag != "") {

												nj = $('#nj').val();
												bj = $('#bj').val();
											} else {
												nj = $('#teamId').val();
												$("#teamId").chosen();
												bj = $('#gradeId').val();
											}
											var term = $('#xq').val();
											if ("" === term
													|| "undefind" === term) {
												return false;
											}
											$('#d4').val("");
											var $requestData = {};
											$requestData.code = $('#xq').val();
											$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json",$requestData,function(data,status) {
																if ("success" === status) {
																	data = eval("("+ data+ ")");
																	begin = data.begin;
																	end = data.end;
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
																		if (bj != ''&& nj != ''&& usertype == 'team') {
																			sure();
																		}
																		if (nj != ''&& usertype == 'grade') {
																			sure();
																		}
																		if (usertype == 'school') {
																			sure();
																		}

																	}
																}
															});

										}
										function toIndex(e) {

											var usertype = e.id;
											var datetype = e.name;
											var dm = $('#dm').val();
											window.location.href = "${pageContext.request.contextPath}/teach/starPerson/index?dm="+ dm+ "&usertype="+ usertype+ "&datetype="+ datetype;

										}
										function save() {
											var nj;
											var bj;
											var flag = $("#isvip").val();
                                            var year=$('#xn').val();
											var usertype = $('#usertype').val();
											var datetype = $('#datetype').val();
											var date = $("#d4").val();
											if (flag != null && flag != "") {
												nj = $('#nj').val();
												bj = $('#bj').val();
											} else {
												bj = $('#teamId').val();
												$("#teamId").chosen();
												nj = $('#gradeId').val();
											}

											var termCode = $("#xq").val();
											$("#xq").chosen();
											if (nj == ""
													&& usertype != 'school') {
												$.error("年级不能为空");
												return false;
											}
											if (termCode == "") {
												$.error("学期不能为空");
												return false;
											}
											if (date == ""
													&& datetype == 'month') {
												$.error("月份不能为空");
												return false;
											}
											if(termCodeNow != termCode){
												$.error("已超过当前学年学期时间段，不可以重新评定");
												return false;
											}
											var $requestData = {};

											$requestData.termCode = termCode;
											$requestData.gradeId = nj;
											$requestData.teamId = bj;
											$requestData.usertype = usertype;
											$requestData.datetype = datetype;
											$requestData.date = date;
											$requestData.year = year;
							
                                            if(datetype=='month'){
                                            	
											$.post("${pageContext.request.contextPath}/teach/starPerson/judgeMonth",{"date" : date},function(data,status) {
																data = eval("("+ data+ ")");
																if (data.info == "success") {

																
																	$.post("${pageContext.request.contextPath}/teach/starPerson/save",$requestData,function(data,status) {
																		             
																		            if ("success" === status) {
																		            	data = eval("(" + data + ")");	
																							if(data.info=='success'){
																								$.success("评定成功");
																								sure();	
																							}else if(data.info=='fail'){
																								$.error("暂无评定数据");
																							}else {
																								$.error("用户角色没有评定权限");
																							}


																						} else {

																							$.error("评定失败");
																						}
																					});

																} else {
																	$.error("只能评定过去月份");
																	return;
																}

															});
                                            }else{
                                            	
                                            	$.post("${pageContext.request.contextPath}/teach/starPerson/save",$requestData,function(data,status) {
										             
										            if ("success" === status) {
										            	data = eval("(" + data + ")");	
															if(data.info=='success'){
																$.success("评定成功");
																sure();	
															}else if(data.info=='fail'){
																$.error("暂无评定数据");
															}else {
																$.error("评定失败");
															}


														} else {

															$.error("评定失败");
														}
													});
                                            }

										}
										function sure() {
											$("#d4").blur();
											var nj;
											var bj;
											var flag = $("#isvip").val();
                                            var year=$('#xn').val();
                                     
											var usertype = $('#usertype').val();
											if (flag != null && flag != "") {
												nj = $('#nj').val();
												bj = $('#bj').val();
											} else {
												bj = $('#teamId').val();
												$("#teamId").chosen();
												nj = $('#gradeId').val();
											}

											var termCode = $("#xq").val();
											$("#xq").chosen();
											var date = $("#d4").val();
											var usertype = $('#usertype').val();
											var datetype = $('#datetype').val();
											
											if(usertype!='school'){
												
												if(nj==''){
													
													$.error("请选择年级");
													return false;
												}
												if(usertype=='team'){
													if(bj==''){
														$.error("请选择班级");
														return false;
													}
										
												}
											}
					
											var $requestData = {};
											$requestData.year = year;
											$requestData.termCode = termCode;
											$requestData.gradeId = nj;
											$requestData.teamId = bj;
											$requestData.usertype = usertype;
											$requestData.datetype = datetype;
											$requestData.date = date;
// 											layer.load(1);
											var url = "/teach/starPerson/list";
// 											myPagination("kb_tb", $requestData,url);
													$.post("${pageContext.request.contextPath}/teach/starPerson/list",
															$requestData, function(data, status) {

																if ("success" === status) {

																	$("#kb_tb").html("").html(data);
																	

																}
// 																layer.close(1);
															});

										}
										function set() {

											var term = $('#xq').val();
											if (term == '') {
												$.error("学期不能为空");
												return false;
											}
											var url = "${pageContext.request.contextPath}/teach/starPerson/getNum?termCode="+ term;
											$.initWinOnTopFromTop("设置评分标准",url, 450, 430);
										}
										function getClass() {
											var schoolYear = $("#xn").val();
											var url = "${pageContext.request.contextPath}/teach/teamEvaluation/getTeam";
											$.post(url,{"schoolYear" : schoolYear},function(data,status) {
																var obj = eval("("+ data+ ")");
																if (status == "success") {
																	$("#teamId").html("");
																	for (var i = 0; i < obj.length; i++) {
																		var opt = "<option value='"+obj[i].teamId+"'>"+ obj[i].teamName+ "</option>";
																		$("#teamId").append(opt);
																	}
																	if (obj.length == 0) {
																		$("#teamId").append("<option value=''>请选择</option>");
																	}
																	//$("#teamId").chosen();
																	var selectObj = $("#teamId"); 
																	selectObj.parent().children().remove('div'); 
																	selectObj.removeClass(); 
																	selectObj.addClass("chzn-select"); 
																	selectObj.chosen(); 
																	find();
																}

															});

										}
										function getGrade() {
											var schoolYear = $("#xn").val();
											var url = "${pageContext.request.contextPath}/teach/teamEvaluation/getGrade";
											$.post(url,{"schoolYear" : schoolYear},function(data,status) {
																var obj = eval("("+ data+ ")");
																if (status == "success") {
																	$("#gradeId").html("");
																	for (var i = 0; i < obj.length; i++) {
																		var opt = "<option value='"+obj[i].gradeId+"'>"
																				+ obj[i].gradeName
																				+ "</option>";
																		$("#gradeId").append(opt);
																	}
																	if (obj.length == 0) {
																		$("#gradeId").append("<option value=''>请选择</option>");
																	}
																	//$("#gradeId").chosen();
																	var selectObj = $("#gradeId"); 
																	selectObj.parent().children().remove('div'); 
																	selectObj.removeClass(); 
																	selectObj.addClass("chzn-select"); 
																	selectObj.chosen(); 
																	find();
																}
															});

										}
										function getNext() {
											var usertype = $('#usertype').val();
											if (usertype == 'team') {
												getClass();
											} else if (usertype == 'grade') {
												getGrade();
											} else {

												find();
											}

										}
									</script>
</body>
</html>