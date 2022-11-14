<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/css/extra/add.css"
	rel="stylesheet">
<title>${sca:getDefaultSchoolName()}</title>
<style type="text/css">
table th, table td {
	min-height: 30px;
	line-height:30px;
}
table tr{
	height:37px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="任科教师管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>任课教师管理</h3>
						<div class="light_grey"></div>
						<div class="content-widgets">
							<!-- <div class="select_b">
							<div class="clear"></div>
						</div> -->
							<input type="hidden" id="schoolId" name="schoolId" value="${schoolId }" />
							<input type="hidden" id="gradeId" name="gradeId" />
							<input type="hidden" id="gradeName" name="gradeName" />
							<div class="select_c">
								<c:forEach items="${gradeList}" var="grade" varStatus="status">
									<a data-obj-id="${grade.id}" data-obj-name="${grade.name }" href="javascript:void(0)" 
										<c:if test='${status.index==0}'>class="on"</c:if>>${grade.fullName }</a>
								</c:forEach>
							</div>
							<div class="widget-container" id="code_Nation" style="min-height:29px;">
								<div style="position: relative; width: 100%;">
									<table class="table  table-striped responsive table-bordered"
										id="temasList">
										<tbody id="tbody"></tbody>
									</table>
									<div class="table1" style="position: absolute; top: 0px; left: 10%; width: 90%;overflow:auto;">
									<div class="table_width">
										<table class="table  table-striped responsive table-bordered" style="width:100%"
											id="courseTeacherList"
											>
											<tbody></tbody>
										</table>
									</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
	$(function() {
		$(".select_c a").click(function() {
			var $this = $(this);
			$(".select_c a").removeClass("on");
			$(this).addClass("on");
			var gradeId = $this.attr("data-obj-id");
			var gradeName = $this.attr("data-obj-name");
			$("#gradeId").val(gradeId);
			ajaxFunction(gradeId, gradeName, function(){
				$.createMemberSelectorByClass({
					"inputClassSelector" : ".btn",
					"enableBatch" : false
				});
				var l=$("#courseTeacherList tr th").length;
				var l1=$(".table").width();
				if(0.9*l1>112*l){
					$(".table_width").css("width","100%");
				}else{
				$(".table_width").css("width",112*l);
				}
			});
			
		})
		
		var gradeId = $(".select_c .on:first").attr("data-obj-id");
		var gradeName = $(".select_c .on:first").attr("data-obj-name");
		$("#gradeId").val(gradeId);
		$("#gradeName").val(gradeName);
	
		ajaxFunction(gradeId, gradeName, function(){
			$.createMemberSelectorByClass({
				"inputClassSelector" : ".btn",
				"enableBatch" : false
			});
			var l=$("#courseTeacherList tr th").length;
			var l1=$(".table").width();
			if(0.9*l1>112*l){
				$(".table_width").css("width","100%");
			}else{
			$(".table_width").css("width",112*l);
			}
		});
		
		//编辑新增
// 		$("#code_Nation").on(
// 				'click',
// 				'button',
// 				function() {
// 					var tit = $(this).attr("title");
// 					var jsonInfo = getAllInfo($(this));
// 					var teamId = jsonInfo.teamId;
// 					var subjectCode = jsonInfo.subjectCode;
// 					var subjectName = jsonInfo.subjectName;
// 					var teamName = jsonInfo.teamName;
// 					var teacherId = jsonInfo.teacherId;
// 					var teamTeacherId = jsonInfo.teamTeacherId;
// 					var schoolId = $("#schoolId").val();
// 					var gradeId = $(".select_c .on:first").attr("data-obj-id");
// 					var type = "";
// 					if (tit == "编辑") {
// 						type = "2";
// 						loadCreatePage(type, schoolId, gradeId, teamId,
// 								subjectName, subjectCode, teamName, teacherId,
// 								teamTeacherId);
// 					} else if (tit == "新增") {
// 						type = "1";
// 						loadCreatePage(type, schoolId, gradeId, teamId,
// 								subjectName, subjectCode, teamName, teacherId,
// 								teamTeacherId);
// 					}
// 					return false;
// 				});
	});

	function loadCreatePage(type, schoolId, gradeId, teamId, subjectName,subjectCode, teamName, teacherId, teamTeacherId) {
		if (type == "1") {
			$.initWinOnTopFromLeft(
							"新增任科教师",
							"${pageContext.request.contextPath}/teach/classRoomTeacher/addClassRoomTeacherPage?schoolId="
									+ schoolId
									+ "&gradeId="
									+ gradeId
									+ "&teamId="
									+ teamId
									+ "&subjectName="
									+ subjectName
									+ "&subjectCode="
									+ subjectCode + "&urlType=2", '800', '500');
		} else {
			$.initWinOnTopFromLeft(
							"编辑任科教师",
							"${pageContext.request.contextPath}/teach/classRoomTeacher/editClassRoomTeacherPage?schoolId="
									+ schoolId
									+ "&gradeId="
									+ gradeId
									+ "&teamId="
									+ teamId
									+ "&subjectName="
									+ subjectName
									+ "&subjectCode="
									+ subjectCode
									+ "&urlType=2"
									+ "&teacherId="
									+ teacherId
									+ "&teamTeacherId=" + teamTeacherId, '800',
							'500');
		}
	}

	//此方法以JSON格式返回点击表格后对应的科目ID跟班级ID等等数据
	function getAllInfo(elem) {
		var thisTr = elem.parents('tr'), 
			thisTd = elem.parents('td'), 
			trNum = $("#courseTeacherList  tr").index(thisTr), 
			tdNum = thisTr.find('td').index(thisTd);

		var subjectCode = $("#courseTeacherList  th").eq(tdNum).data('id'), 
			teamId = $("#temasList th").eq(trNum).data('tid'), 
			subjectName = $("#courseTeacherList  th").eq(tdNum).text(), 
			teamName = $("#temasList th").eq(trNum).text(), 
			teacherId = elem.data('tid') || "", 
			teamTeacherId = elem.data('ttid') || "";

		return {
			subjectCode : subjectCode,
			teamId : teamId,
			subjectName : subjectName,
			teamName : teamName,
			teacherId : teacherId,
			teamTeacherId : teamTeacherId
		};
	}

	function ajaxFunction(gradeId, gradeName, afterHandler) {
		//alert(gradeId+"=="+gradeName);
		var teamList = '${teamList}';
		var gradeSubjectList = '${gradeSubjectList}';
		var courseTeacherList = '${courseTeacherList}';

		var url = "${pageContext.request.contextPath}/teach/classRoomTeacher/getSubjectList";
		var aj = $.ajax({
			url : url,
			data : 'gradeId=' + gradeId,
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				teamList = data.teamList;
				gradeSubjectList = data.gradeSubjectList;
				courseTeacherList = data.courseTeacherList;
				loadTable(teamList, gradeSubjectList, courseTeacherList, gradeName);
				afterHandler();
			},
			error : function() {
				$.alert("请先添加年级！");
			}
		});
	}
	
	function loadTable(teamList, gradeSubjectList, courseTeacherList, gradeName) {

		var table0 = $("#code_Nation").find('table').eq(0), 
		    table1 = $("#code_Nation").find('table').eq(1);
		var nowTeam = gradeName;
		//设置班级表头
		table0.show();
		if (teamList.length == 0) {
			table0.hide();
			//$("#code_Nation").html('暂无班级数据');
 			table1.html('<tr><td colspan="15">暂无班级数据！</td></tr>');
 			//table1.css({"width":100%;"left":0});
 				/* table1.css("left","0");
 				table1.css("width","100%"); */
 				$(".table1").css("width","100%");
 				$(".table1").css("left","0");
			//table1.html('暂无班级数据');
			return false;
		} else {
			$(".table1").css("width","90%");
			$(".table1").css("left","10%");
			var teamTrs = '<tr><th>' + nowTeam + '</th></tr>';
			for (var i = 0, len = teamList.length; i < len; i++) {
				teamTrs += '<tr><th data-tid='+teamList[i].id+'>'
						+ teamList[i].name + '</th></tr>'
			}
			table0.html(teamTrs);
		}

		//设置科目表头
		if (gradeSubjectList.length == 0) {
			table0.hide();
			table1.html('<tr><td colspan="7">暂无科目数据！</td></tr>');
			$(".table1").css("left","0");
			$(".table1").css("width","100%");
			return false;
		} else {
			$(".table1").css("width","90%");
				$(".table1").css("left","10%");
			var subjectTrs = "<tr>";
			for (var i = 0, len = gradeSubjectList.length; i < len; i++) {
				if(gradeSubjectList[i].subjectName.length >= 6){
					subjectTrs += '<th title="' + gradeSubjectList[i].subjectName + '" data-id="'+gradeSubjectList[i].subjectCode+'">'
					+ gradeSubjectList[i].subjectName.substring(0,6) + '...</th>'
				}else{
					subjectTrs += '<th title="' + gradeSubjectList[i].subjectName + '" data-id="'+gradeSubjectList[i].subjectCode+'">'
					+ gradeSubjectList[i].subjectName + '</th>'
				}
				
			}
			subjectTrs += '</tr>';
		}
		
		table1.html(subjectTrs);

		//内容加载
		var subjectContent = '';
		for (var i = 0, leng = courseTeacherList.length; i < leng; i++) {
			subjectContent += '<tr>';
			table1.find("th").each(function(index, el) {
				var nowId = $(this).attr("data-id");
				nowFlag = false;
				for (var j = 0, tdLeng = courseTeacherList[i].length; j < tdLeng; j++) {
					if (nowId == courseTeacherList[i][j].subjectCode) {//遍历表头的data-id如果匹配则创建有名字的表格 
						nowFlag = true;
					var tName = courseTeacherList[i][j].name;
					if(tName.length > 4){
						tName = courseTeacherList[i][j].name.substring(0,3)+"...";
					}
						subjectContent += '<td style="width:110px;"><span title="'+courseTeacherList[i][j].name+'">'
								+ tName
								+ '</span><button style="float:right;margin-top:3px;" id="btn_'+ courseTeacherList[i][j].subjectCode + '_' + courseTeacherList[i][j].teamId +'" type="button" class="btn btn-info" title="编辑" data-ttid="'+courseTeacherList[i][j].id+'"  data-tid="'+courseTeacherList[i][j].teacherId+'"><i class="icon icon-edit"></i></button></td>';
						break;
					}
				}
				if (!nowFlag) {
					var tid = table0.find("tr").eq((i+1)).find("th").attr("data-tid");
					subjectContent += '<td style="width:110px;"><button style="float:right;margin-top:3px;" id="btn_'+ nowId + '_' + tid +'" type="button"  class="btn btn-success" title="新增"><i class="icon icon-plus"></i></button></td>';
				}
			});
			subjectContent += '</tr>';
		}
		table1.find("tbody").html(table1.find("tbody").html() + subjectContent);
	}
	
	function selectedHandler(data) {
		var loader = new loadLayer();
		loader.show();
		var $requestData = {};
		var $requestEditData = {};
		var $thisBtn = $("#" + data.btnId);
		var $btnSelect = $("#" + data.btnId + "_select"); 
		var tit = $btnSelect.attr("title");
		var jsonInfo = getAllInfo($thisBtn);
		$requestData.teamId = jsonInfo.teamId;
		$requestData.subjectCode = jsonInfo.subjectCode;
		$requestData.subjectName = jsonInfo.subjectName;
		var teamName = jsonInfo.teamName;
		$requestData.teacherId = data.ids[0];
		$requestEditData.teacherId = data.ids[0];
		$requestEditData.teamTeacherId = jsonInfo.teamTeacherId;
		$requestData.gradeId = $(".select_c .on:first").attr("data-obj-id");
		if (tit == "编辑") {
// 			var url = "${pageContext.request.contextPath}/teach/classRoomTeacher/updatClassRoomTeacher";
// 			saveOrUpdate(url,$requestEditData,tit,data);
			var url = "${pageContext.request.contextPath}/teach/classRoomTeacher/addOrModifyClassRoomTeacher";
			saveOrUpdate(url, $requestData,tit,data);
		} else if (tit == "新增") {
// 			var url = "${pageContext.request.contextPath}/teach/classRoomTeacher/addClassRoomTeacher";
			var url = "${pageContext.request.contextPath}/teach/classRoomTeacher/addOrModifyClassRoomTeacher";
			saveOrUpdate(url, $requestData,tit,data);
		}
		loader.close();
	}
	
	function saveOrUpdate(url,$requestData,tit,data){
		var loader = new loadLayer();
		var $btnSelect = $("#" + data.btnId + "_select"); 
		var gradeId = $("#gradeId").val();
		var gradeName = $("#gradeName").val();
		loader.show();
		$.post(url, $requestData, function(responseData, status) {
			if("success" === status) {
				if("success" === responseData) {
					$.success("保存成功");
					if("新增" === tit) {
						$btnSelect.before("<span>"+ data.names[0] + "</span>");
						$btnSelect.removeClass("btn-success").addClass("btn-info");
						$btnSelect.find("i").removeClass("icon-plus").addClass("icon-edit");
						$btnSelect.attr("title", "编辑");
					} else {
						$btnSelect.prev("span").remove();
						$btnSelect.before("<span>"+ data.names[0] + "</span>");
					}
					
					ajaxFunction(gradeId, gradeName, function(){
						$.createMemberSelectorByClass({
							"inputClassSelector" : ".btn",
							"enableBatch" : false
						});
					});
					
					$.closeWindowByName(data.windowName);
				} else {
					$.error("保存失败");
				}
			}else{
				$.error("服务器异常");
			}
			loader.close();
		});
	}

</script>
</html>
