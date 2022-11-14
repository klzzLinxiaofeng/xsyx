<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
 
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-calculator" name="icon" />
			<jsp:param value="批量调换" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12" style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
							<span>批量调换</span>
							<p style="float: right;" class="btn_link">
								<input type="hidden" id="year" value="${year}" /> <a class="a5"
									href="javascript:void(0)" onclick="saveOrUpdate();" id="save">保存</a>
							</p>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid white">
			<div class="fenban_right">
				<form id="transferClassForm0" name="transferClassForm0"
					action="javascript:void(0);" style="margin: 0">
					<div class="xs_div">
						<div class="top">
							<p>
								入住宿舍： <select style="width: 120px;" id="floorCode0"
									name="floorCode">
									<option value="">请选择</option>
									<c:forEach items="${floorCodeList }" var="floor">
										<option value="${floor.floorCode }">${floor.floorName }</option>
									</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;&nbsp; <select style="width: 90px;"
									id="dormitoryCode0" name="dormitoryCode"
									onchange="getDormitoryStudent0(this.value);">
									<option value="">请选择</option>
								</select>
							</p>
							<p id="capacity0">可住人数：</p>
							<p id="liveSex0">入住性别：</p>
						</div>
						<div class="xs_list">
							<div class="xuesheng">
								<table class="table table-bordered responsive white">
									<thead>
										<tr>
											<th style="width: 34px;"><input type="checkbox"
												id="checkboxOut" name="checkboxOut" onclick="checkAll(0);"></th>
											<th style="width: 34px;">序号</th>
											<th style="width: 62px;">姓名</th>
											<th style="width: 34px;">性别</th>
											<th>学籍号</th>
										</tr>
									</thead>
									<tbody id="tbody0">

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</form>
				<div class="hj_hc">
					<p>&nbsp;&nbsp;&nbsp;&nbsp;</p>
					<p>&nbsp;&nbsp;&nbsp;&nbsp;</p>
					<a href="javascript:void(0)" onclick="changeOut();">入住 &gt;</a> <a
						href="javascript:void(0)" onclick="changeIn();">搬出 &lt;</a>
				</div>
				<form id="transferClassForm1" name="transferClassForm1"
					action="javascript:void(0);" style="margin: 0">
					<div class="xs_div">
						<div class="top">
							<p>
								入住宿舍： <select style="width: 120px;" id="floorCode1"
									name="floorCode">
									<option value="">请选择</option>
									<c:forEach items="${floorCodeList }" var="floor">
										<option value="${floor.floorCode }">${floor.floorName }</option>
									</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;&nbsp; <select style="width: 90px;"
									id="dormitoryCode1" name="dormitoryCode"
									onchange="getDormitoryStudent1(this.value);">
									<option value="">请选择</option>
								</select>
							</p>
							<p id="capacity1">可住人数：</p>
							<p id="liveSex1">入住性别：</p>
						</div>
						<div class="xs_list">
							<div class="xuesheng">
								<table class="table table-bordered responsive white">
									<thead>
										<tr>
											<th style="width: 34px;"><input type="checkbox"
												id="checkboxIn" name="checkboxIn" onclick="checkAll(1);"></th>
											<th style="width: 34px;">序号</th>
											<th style="width: 62px;">姓名</th>
											<th style="width: 34px;">性别</th>
											<th>学籍号</th>
										</tr>
									</thead>
									<tbody id="tbody1">

									</tbody>
								</table>
							</div>
						</div>
					</div>
				</form>
				<div class="clear"></div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$("#floorCode0").change(function() {
				$("#dormitoryCode0").get(0).options.length = 1;
				getDormitoryCode0();

			});

			$("#floorCode1").change(function() {
				$("#dormitoryCode1").get(0).options.length = 1;
				getDormitoryCode1();

			});
			var floorCode0 = $("#floorCode0").val();
			var dormitoryCode0 = $("#dormitoryCode0").val();
			getDormitoryStudent0(floorCode0, dormitoryCode0);

			var floorCode1 = $("#floorCode1").val();
			var dormitoryCode1 = $("#dormitoryCode1").val();
			getDormitoryStudent1(floorCode1, dormitoryCode1);

		})

		//左边寝室编号
		function getDormitoryCode0() {
			var floorCode0 = $("#floorCode0").val();
			var floorCode1 = $("#floorCode1").val();
			var dormitoryCode1 = $("#dormitoryCode1").val();
			
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryCodeListAjax/getDormitoryCodeList";
			$.ajax({
				url : url,
				data : 'floorCode=' + floorCode0,
				type : 'post',
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data.length != 0) {
						for (var i = 0; i < data.length; i++) {
							if (data[i].floorCode == floorCode0) {
								if (floorCode0 == floorCode1) {
									if (data[i].dormitoryCode != dormitoryCode1) {
										$("#dormitoryCode0").append(
												"<option value="+data[i].dormitoryCode+">"
														+ data[i].dormitoryCode
														+ "</option>");
										}
									}else{
										$("#dormitoryCode0").append(
												"<option value="+data[i].dormitoryCode+">"
														+ data[i].dormitoryCode
														+ "</option>");
									}
								
							}
						}

					}
				}
			});
		}
		//右边寝室编号
		function getDormitoryCode1() {
			var floorCode0 = $("#floorCode0").val();
			var dormitoryCode0 = $("#dormitoryCode0").val();
			var floorCode1 = $("#floorCode1").val();
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryCodeListAjax/getDormitoryCodeList";
			$
					.ajax({
						url : url,
						data : 'floorCode=' + floorCode1,
						type : 'post',
						cache : false,
						dataType : 'json',
						success : function(data) {
							if (data.length != 0) {
								for (var i = 0; i < data.length; i++) {
									if (data[i].floorCode == floorCode1) {
										if (floorCode1 == floorCode0) {
											if (data[i].dormitoryCode != dormitoryCode0) {
												$("#dormitoryCode1")
														.append(
																"<option value="+data[i].dormitoryCode+">"
																		+ data[i].dormitoryCode
																		+ "</option>");
											}

										} else {
											$("#dormitoryCode1")
													.append(
															"<option value="+data[i].dormitoryCode+">"
																	+ data[i].dormitoryCode
																	+ "</option>");
										}
									}
								}

							}
						}
					});
		}

		//左边寝室对应的学生
		function getDormitoryStudent0(floorCode0, dormitoryCode0) {
			var year = $("#year").val();
			var floorCode0_ = $("#floorCode0").val();
			var dormitoryCode0_ = $("#dormitoryCode0").val();
			var url0 = "${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/ajax/currentDormitoryStudent";
			//加载宿舍学生信息
			var aj = $.ajax({
				url : url0,
				data : {
					"floorCode" : floorCode0_,
					"dormitoryCode" : dormitoryCode0_,
					"year" : year
				},
				type : "post",
				cache : false,
				dataType : "json",
				success : function(data) {

					if (data.dormitory.sex == 1) {
						$("#liveSex0").html("入住性别：男");
					} else if (data.dormitory.sex == 2) {
						$("#liveSex0").html("入住性别：女");
					}

					$("#capacity0")
							.html(
									"可住人数：" + data.size + "/"
											+ data.dormitory.capacity);

					loadSudentTable_0(data);
				},
				error : function() {
					$.alert("异常!");
				}
			});
		}

		//右边寝室对应的学生
		function getDormitoryStudent1(floorCode1, dormitoryCode1) {
			var year = $("#year").val();
			var floorCode1_ = $("#floorCode1").val();
			var dormitoryCode1_ = $("#dormitoryCode1").val();
			var url0 = "${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/ajax/currentDormitoryStudent";
			//加载宿舍学生信息
			var aj = $.ajax({
				url : url0,
				data : {
					"floorCode" : floorCode1_,
					"dormitoryCode" : dormitoryCode1_,
					"year" : year
				},
				type : "post",
				cache : false,
				dataType : "json",
				success : function(data) {

					if (data.dormitory.sex == 1) {
						$("#liveSex1").html("入住性别：男");
					} else if (data.dormitory.sex == 2) {
						$("#liveSex1").html("入住性别：女");
					}

					$("#capacity1")
							.html(
									"可住人数：" + data.size + "/"
											+ data.dormitory.capacity);

					loadSudentTable_1(data);
				},
				error : function() {
					$.alert("异常!");
				}
			});
		}

		//加载左边对应宿舍学生信息
		function loadSudentTable_0(data) {
			var dataList = data.personList;
			var trTemp = "";
			for (var i = 0; i < dataList.length; i++) {
				var tt = i + 1;
				trTemp += "<tr><td><input type='checkbox' id='studentCheckbox' name='studentCheckbox' value="+dataList[i].studentId+"></td><td>"
						+ tt + "</td><td>" + dataList[i].studentName + "</td>";
				if (dataList[i].sex == "1") {
					trTemp += "<td class='nan'>男</td>";
				}

				if (dataList[i].sex == "2") {
					trTemp += "<td class='nv'>女</td>";
				}

				trTemp += "<td>" + dataList[i].studentNumber + "</td></tr>";
			}
			$("#tbody0").html(trTemp);
		}

		//加载右边对应宿舍学生信息
		function loadSudentTable_1(data) {
			var dataList = data.personList;
			var trTemp = "";
			for (var i = 0; i < dataList.length; i++) {
				var tt = i + 1;
				trTemp += "<tr><td><input type='checkbox' id='studentCheckbox' name='studentCheckbox' value="+dataList[i].studentId+"></td><td>"
						+ tt + "</td><td>" + dataList[i].studentName + "</td>";
				if (dataList[i].sex == "1") {
					trTemp += "<td class='nan'>男</td>";
				}

				if (dataList[i].sex == "2") {
					trTemp += "<td class='nv'>女</td>";
				}

				trTemp += "<td>" + dataList[i].studentNumber + "</td></tr>";
			}
			$("#tbody1").html(trTemp);
		}

		//全选
		function checkAll(obj) {
			if (obj == 0) {
				if ($("#checkboxOut").prop("checked") == true) {
					$("#tbody0 :checkbox").prop("checked", true);
				} else {
					$("#tbody0 :checkbox").prop("checked", false);
				}
			} else {
				if ($("#checkboxIn").prop("checked") == true) {
					$("#tbody1 :checkbox").prop("checked", true);
				} else {
					$("#tbody1 :checkbox").prop("checked", false);
				}
			}

		}

		//入住标志
		var changOutStatus = "";
		//搬出标志
		var changInStatus = "";

		//入住
		function changeOut() {
			changOutStatus = "1";
			var liveSexText1 = $("#liveSex1").html();
			var text1 = liveSexText1.split("");
			var liveSex1_ = text1[text1.length - 1];
			
			var liveSexText0 = $("#liveSex0").html();
			var text0 = liveSexText0.split("");
			var liveSex0_ = text0[text0.length - 1];
			
			if (liveSex1_ == "：") {
				$.error("请选择要入住的宿舍 !");
			} else {
				if(liveSex1_!==liveSex0_){
					$.error("所要调换的宿舍性别不符!");
				}else{
				var outTemp = "";
				$('input[name="studentCheckbox"]:checked').each(function() {
					var sfruit_ = $(this).parent().parent();
					outTemp += "<tr>" + sfruit_.html() + "</tr>";
				});

				$("#tbody1").append(outTemp);
				$('input[name="studentCheckbox"]:checked').each(function() {
					$(this).closest('tr').remove();
				});
				}
				
				
			}

		}
		//搬出
		function changeIn() {
			changInStatus = "2";
			var liveSexText0 = $("#liveSex0").html();
			var text0 = liveSexText0.split("");
			var liveSex0_ = text0[text0.length - 1];
			
			var liveSexText1 = $("#liveSex1").html();
			var text1 = liveSexText1.split("");
			var liveSex1_ = text1[text1.length - 1];
			
			
			if (liveSex0_ == "：") {
				$.error("请选择要搬出的宿舍 !");
			} else {

				if(liveSex0_!==liveSex1_){
					$.error("两边性别不相符!");
				}else{
					
				var inTemp = "";
				$('input[name="studentCheckbox"]:checked').each(function() {
					var sfruit = $(this).parent().parent();
					inTemp += "<tr>" + sfruit.html() + "</tr>";
				});
				$("#tbody0").append(inTemp);

				$('input[name="studentCheckbox"]:checked').each(function() {
					$(this).closest('tr').remove();
				});
				}
			}

		}

		//保存更新
		function saveOrUpdate() {
			var loader = new loadLayer();
			var year = $("#year").val();
			var floorCode0 = $("#floorCode0").val();
			var dormitoryCode0 = $("#dormitoryCode0").val();
			var floorCode1 = $("#floorCode1").val();
			var dormitoryCode1 = $("#dormitoryCode1").val();
			var stuNum0 = $("#transferClassForm0 input[name=studentCheckbox]").length;
			var studentId0 = new Array();
			for (var i = 0; i < stuNum0; i++) {
				var stVal0 = $(
						"#transferClassForm0 input[name='studentCheckbox']:eq("
								+ i + ")").val();
				studentId0.push(stVal0);
			}
			var stuNum1 = $("#transferClassForm1 input[name=studentCheckbox]").length;
			var studentId1 = new Array();
			for (var k = 0; k < stuNum1; k++) {
				var stVal1 = $(
						"#transferClassForm1 input[name='studentCheckbox']:eq("
								+ k + ")").val();
				studentId1.push(stVal1);
			}

			var capacityText0 = $("#capacity0").html();
			var capacity0_ = capacityText0.split("/");
			var capacity0 = capacity0_[capacity0_.length - 1];

			var capacityText1 = $("#capacity1").html();
			var capacity1_ = capacityText1.split("/");
			var capacity1 = capacity1_[capacity1_.length - 1];

			var liveSexText0 = $("#liveSex0").html();
			var text0 = liveSexText0.split("");
			var liveSex0_ = text0[text0.length - 1];

			var liveSexText1 = $("#liveSex1").html();
			var text1 = liveSexText1.split("");
			var liveSex1_ = text1[text1.length - 1];

			 if (liveSex0_ !== liveSex1_) {
				$.error("所调换的宿舍性别不相同，请重新选择 !");
			} else if (studentId1.length > capacity1
					|| studentId0.length > capacity0) {
				$.error("所调换已超，请重新调换 !");
			} else if (changOutStatus == "" && changInStatus == "") {
				$.error("请选择入住或搬出学生!")
			} else if (studentId1.length <= capacity1
					|| studentId0.length <= capacity0) {
				var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/save/saveOrUpdate?year="
						+ year
						+ "&floorCode0="
						+ floorCode0
						+ "&dormitoryCode0="
						+ dormitoryCode0
						+ "&floorCode1="
						+ floorCode1
						+ "&dormitoryCode1="
						+ dormitoryCode1
						+ "&studentId0="
						+ studentId0
						+ "&studentId1="
						+ studentId1;
				loader.show();
				$.post(url, {}, function(data, status) {

					if ("success" === status) {
						if ("success" === data) {
							$.success("保存成功");
							if (parent.core_iframe != null) {
								parent.core_iframe.window.location.reload();
							} else {
								parent.window.location.reload();
							}
							$.closeWindow();
						} else {
							$.error("保存失败");
						}
					} else {
						$.error("服务器异常");
					}
					loader.close();
				});
			}

		}
	</script>
</body>
</html>
