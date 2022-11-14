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
			<jsp:param value="批量分配" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid ">
			<div class="span12" style="height: 43px;">
				<div class="content-widgets white" style="margin-bottom: 0;">
					<div class="widget-head">
						<h3>
						   
							<span>批量分配</span>
							<p style="float: right;" class="btn_link">
								<a class="a5" href="javascript:void(0)" onclick="saveOrUpdate();" id="save">保存</a>
							</p>
						</h3>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid white">
			<div class="fenban_left" style=" height: 400px; overflow-x: hidden;">
				<div class="top">
					<span>索引</span>
				</div>
				<div class="bottom">
					<div class="open">
						<a href="javascript:void(0)" class="school">${school.name }</a>
					</div>
					<div class="bj_rs">
						<ul class="ban_ul">
							<c:forEach items="${teamList}" var="team" varStatus="s">
								<input type="hidden" id="year" value="${team.schoolYear }" />
								<li><a <c:if test="${s.index==0}">class='on'</c:if>
									data_obj="${team.id}" href="javascript:void(0)"
									onclick="getCurrentTeam('${team.fullName }','${team.id}','');">${team.fullName }</a></li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
			<div class="fenban_right">
				<form id="transferClassForm0" name="transferClassForm0"
					action="javascript:void(0);" style="margin: 0">
					<div class="xs_div">
						<div class="top">
							<p id="currentTeam">当前班级：初一（1）班</p>
							<p id="allStuNum">
								班级人数：0 &nbsp;&nbsp;<span id="sexStuNum">男：0 </span>&nbsp;&nbsp;<span id="otherSexStuNum">女：0 </span>&nbsp;&nbsp;<span id="noSexStuNum">未知+未说明：0 </span>
							</p>
							<p id="sex0">
								性别筛选：<select id="sex" onchange="getSex();" style="width: 120px;">
									<!-- <option value="">请选择</option> -->
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
							</p>
						</div>
						<input type="hidden" id="teamId0" name="teamId0" value="" />
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
								入住宿舍： <select style="width: 120px;" id="floorCode"
									name="floorCode">
									<option value="">请选择</option>
									<c:forEach items="${floorCodeList }" var="floor">
										<option value="${floor.floorCode }">${floor.floorName }</option>
									</c:forEach>
								</select>&nbsp;&nbsp;&nbsp;&nbsp; <select style="width: 90px;"
									id="dormitoryCode" name="dormitoryCode"
									onchange="getDormitoryStudent(this.value);">
									<option value="">请选择</option>
								</select>
							</p>
							<p id="capacity">可住人数：</p>
							<p id="liveSex">入住性别：</p>
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
			$(".fenban_left .bottom .bj_rs .ban_ul li a").click(
					function() {
						$(".fenban_left .bottom .bj_rs .ban_ul li a")
								.removeClass("on");
						$(this).addClass("on");
					});
			$(".fenban_left .bottom .bj_rs ul").on(
					"click",
					".open_2 .nj",
					function(event) {
						event.stopPropagation();
						$(this).next().hide();
						$(this).parent().removeClass("open_2").addClass(
								"close_2")
					});
			$(".fenban_left .bottom .bj_rs ul").on(
					"click",
					".close_2 .nj",
					function(event) {
						event.stopPropagation();
						$(this).next().show();
						$(this).parent().removeClass("close_2").addClass(
								"open_2")
					});
			$(".fenban_left .bottom").on(
					"click",
					".open .school",
					function(event) {
						event.stopPropagation();
						$(this).parent().next().hide();
						$(this).parent().removeClass("open")
								.addClass("close_1")
					});
			$(".fenban_left .bottom").on(
					"click",
					".close_1 .school",
					function(event) {
						event.stopPropagation();
						$(this).parent().next().show();
						$(this).parent().removeClass("close_1")
								.addClass("open")
					});
			var wid = $(".fenban_right").css("width");
			var w = (parseInt(wid) - 140) * 0.5;
			$(".fenban_right .xs_div").css("width", w);

			var teamName = $(".ban_ul li a.on").text();
			$("#currentTeam").html("当前班级: " + teamName);
			var teamId = $(".ban_ul li a.on").attr("data_obj");
			$("#teamId0").val(teamId);
			var sex0 = $("#sex").val();
			getCurrentTeam(teamName, teamId, sex0);
			$("#floorCode").change(function() {
				$("#dormitoryCode").get(0).options.length = 1;
				getDormitoryCode();

			});
			var floorCode1 = $("#floorCode").val();
			var dormitoryCode1 = $("#dormitoryCode").val();
			getDormitoryStudent(floorCode1, dormitoryCode1);
// 			getCurrentStudentInfo(teamId);
		});

		//学生性别
		function getSex() {
			var obj = document.getElementById("sex"); //selectId
			var index = obj.selectedIndex; //选中索引
			var sex = obj.options[index].value; //选中文本
			var teamName = $(".ban_ul li a.on").text();
			var teamId = $(".ban_ul li a.on").attr("data_obj");
			getCurrentTeam(teamName, teamId, sex);

		}

		//寝室编号
		function getDormitoryCode() {
			var floorCode = $("#floorCode").val();
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryCodeListAjax/getDormitoryCodeList";
			$.ajax({
				url : url,
				data : 'floorCode=' + floorCode,
				type : 'post',
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data.length != 0) {
						for (var i = 0; i < data.length; i++) {
							if (data[i].floorCode == floorCode) {
								$("#dormitoryCode").append(
										"<option value="+data[i].dormitoryCode+">"
												+ data[i].dormitoryCode
												+ "</option>");
							}
						}

					}
				}
			});
		}

		function getDormitoryStudent(floorCode1, dormitoryCode1) {
			var year = $("#year").val();
			var floorCode = $("#floorCode").val();
			var dormitoryCode = $("#dormitoryCode").val();
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/ajax/currentDormitoryStudent";
			//加载宿舍学生信息
			var aj = $.ajax({
				url : url,
				data : {
					"floorCode" : floorCode,
					"dormitoryCode" : dormitoryCode,
					"year" : year
				},
				type : "post",
				cache : false,
				dataType : "json",
				success : function(data) {
					if (data.dormitory.sex == 1) {
						$("#liveSex").html("入住性别：男");
					} else if (data.dormitory.sex == 2) {
						$("#liveSex").html("入住性别：女");
					}

					$("#capacity")
							.html(
									"可住人数：" + data.size + "/"
											+ data.dormitory.capacity);

					loadSudentTable_(data);
				},
				error : function() {
					$.alert("异常!");
				}
			});

		}

		
		function getCurrentTeam(teamName, id, sex0) {
			var sex = $("#sex").val();
			$("#currentTeam").html("当前班级: " + teamName);
			$("#teamId0").val(id);
			//加载学生信息
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/ajax/currentTeamStudent";
			var aj = $.ajax({
				url : url,
				data : {
					"teamId" : id,
					"selectSex" : sex
				},
				type : "post",
				cache : false,
				dataType : "json",
				success : function(data) {
					loadSudentTable(data);
				},
				error : function() {
					$.alert("异常！");
				}
			});
		}

		function loadSudentTable(data) {
			var sex = $("#sex").val();
			var dataList = data.stuList;
			var trTemp = "";
			for (var i = 0; i < dataList.length; i++) {
				var tt = i + 1;
				trTemp += "<tr><td><input type='checkbox' id='studentCheckbox' name='studentCheckbox' value="+dataList[i].id+"></td><td>"
				+ tt + "</td><td>" + dataList[i].name + "</td>";
				if (sex == "1") {
					trTemp += "<td class='nan'>男</td>";
				}
		
				if (sex == "2") {
					trTemp += "<td class='nv'>女</td>";
				}
				if(dataList[i].studentNumber == null){
					trTemp += "<td>"+""+"</td></tr>";
				}else{
					trTemp += "<td>" + dataList[i].studentNumber + "</td></tr>";
				}
// 				trTemp += "<td>" + dataList[i].studentNumber + "</td></tr>";
				
			}
			//班级总人数
			var allStuNum = data.allStuNum;

			//根据性别查询到的人数
			var sexStuNum = data.sexStuNum;

			//不是根据性别查询到的人数
			var otherSexStuNum = data.otherSexStuNum;
			
			//没有标明性别的人数
			var noSexStuNum = allStuNum-sexStuNum-otherSexStuNum;

			$("#allStuNum").html("班级人数:" + allStuNum + "");

			if (sex == "1") {
				$("#allStuNum")
						.append(
								"<span id='sexStuNum'>&nbsp;&nbsp;&nbsp;男:"
										+ sexStuNum
										+ "</span><span id='otherSexStuNum'>&nbsp;&nbsp;&nbsp;女:"
										+ otherSexStuNum 
										+ "</span><span id='noSexStuNum'>&nbsp;&nbsp;&nbsp;未知+未说明:"
										+noSexStuNum
										+"</span>");
			} else if (sex == "2") {
				$("#allStuNum")
						.append(
								"<span id='sexStuNum'>&nbsp;&nbsp;&nbsp;女:"
										+ sexStuNum
										+ "</span><span id='otherSexStuNum'>&nbsp;&nbsp;&nbsp;男:"
										+ otherSexStuNum 
										+ "</span><span id='noSexStuNum'>&nbsp;&nbsp;&nbsp;未知+未说明:"
										+ noSexStuNum
										+"</span>");
			}

			$("#tbody0").html(trTemp);
		}

		
		function loadSudentTable_(data) {
			var dataList = data.personList;
			var trTemp = "";
			for (var i = 0; i < dataList.length; i++) {
				var tt = i + 1;
				trTemp += "<tr><td id='"+ dataList[i].studentId + "'><input type='checkbox' id='studentCheckbox' name='studentCheckbox' value="+dataList[i].studentId+"></td><td>"
						+ tt + "</td><td>" + dataList[i].studentName + "</td>";
				if (dataList[i].sex == "1") {
					trTemp += "<td class='nan'>男</td>";

				}

				if (dataList[i].sex == "2") {
					trTemp += "<td class='nv'>女</td>";
				}
				if(dataList[i].studentNumber == null){
					trTemp += "<td>"+""+"</td></tr>";
				}else{
					trTemp += "<td>" + dataList[i].studentNumber + "</td></tr>";
				}
// 				trTemp += "<td>" + dataList[i].studentNumber + "</td></tr>";
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
			var liveSexText = $("#liveSex").html();
			var text = liveSexText.split("");
			var liveSex1 = text[text.length - 1];

			var liveSex = "";
			if (liveSex1 == "男") {
				liveSex = "1";
			}

			if (liveSex1 == "女") {
				liveSex = "2";
			}

			var selectSex = $("#sex").val();

			if (liveSex1 == "：") {
				$.error("请选择入住宿舍!");
			} else {
				if (liveSex !== selectSex) {
					$.error("所分配的学生性别与所要入住的宿舍性别不符!");
				} else {
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
			var liveSexText = $("#liveSex").html();
			var text = liveSexText.split("");
			var liveSex1 = text[text.length - 1];

			var liveSex = "";
			if (liveSex1 == "男") {
				liveSex = "1";
			}

			if (liveSex1 == "女") {
				liveSex = "2";
			}

			var selectSex = $("#sex").val();
			var teamId = $(".ban_ul li a.on").attr("data_obj");
			if (liveSex !== selectSex) {
				$.error("两边性别不相符!");
			} else {
				var stuNum1 = $("#transferClassForm1 input[name=studentCheckbox]").length;
				var check = $("#transferClassForm1 input[name=studentCheckbox]");
				var studentId1 = new Array();
				for (var k = 0; k < stuNum1; k++) {
					if (check[k].checked == true) {
						var stVal1 = check[k].value;
						studentId1.push(stVal1);
					}

				}
				ajaxChangOut(studentId1, teamId);

			}

		}

		//验证所搬出的学生是否属于这个班级
		function ajaxChangOut(studentId1, teamId) {
			var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/ajax/checkStudentIn?studentId1="
					+ studentId1 + "& teamId=" + teamId;
			$.ajax({
				type : "GET",
				url : url,
				data : {
					"studentId1" : studentId1,
					"teamId" : teamId
				},
				success : function(data) {
					// 					 alert(JSON.stringify(data)) 
					var response = data.substring(data.length - 9,
							data.length - 2);
					var isTeacher = data.substring(2, data.length - 9);
					if ("success" == response) {
						$.error(isTeacher + "这些学生不属于当前班级,请重新选择!");
					} else {
						var inTemp = "";
						$('input[name="studentCheckbox"]:checked').each(
								function() {
									var sfruit = $(this).parent().parent();
									inTemp += "<tr>" + sfruit.html() + "</tr>";
								});
						$("#tbody0").append(inTemp);
						$('input[name="studentCheckbox"]:checked').each(
								function() {
									$(this).closest('tr').remove();
								});

						changInStatus = "2";
					}
				}
			});
		}

		//保存更新
		function saveOrUpdate() {
			var loader = new loadLayer();
			var year = $("#year").val();
			var floorCode = $("#floorCode").val();
			var dormitoryCode = $("#dormitoryCode").val();

			var capacityText = $("#capacity").html();
			var capacity1 = capacityText.split("/");
			var capacity = capacity1[capacity1.length - 1];

			var liveSexText = $("#liveSex").html();
			var text = liveSexText.split("");
			var liveSex1 = text[text.length - 1];

			var liveSex = "";
			if (liveSex1 == "男") {
				liveSex = "1";
			}

			if (liveSex1 == "女") {
				liveSex = "2";
			}

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
			if (changOutStatus == "" && changInStatus == "") {
				$.error("请选择入住或搬出学生!");
			} else if (studentId1.length > capacity) {
				$.error("所分配人数已超过宿舍容纳数!");

			} else if (studentId1.length <= capacity) {
				var url = "${pageContext.request.contextPath}/schoolaffair/dormitoryPerson/save/saveOrUpdateStudentInfo?year="
						+ year
						+ "&floorCode="
						+ floorCode
						+ "&dormitoryCode="
						+ dormitoryCode
						+ "&studentId0="
						+ studentId0
						+ "&studentId1=" + studentId1 + "&liveSex=" + liveSex;
						
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
