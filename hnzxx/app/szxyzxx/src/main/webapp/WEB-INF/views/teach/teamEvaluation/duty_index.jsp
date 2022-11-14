<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>值日管理</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<style type="text/css">
.chosen{
	background: #daf0fb;
}
.select_b .select_div{
	margin: 7px 0 0 0;
}
.attendant{
    min-height: 600px;
    background: #fff;
}
.time-period{
    color: #535353;
    font-size: 12px;
    font-weight: bold;
    text-align: center;
    padding:25px 0 15px 0;
}
.personnel{
    margin: 0 25px;
    border-top: 1px #d4d4d4 solid;
    min-height: 200px;
    border-right: 1px #d4d4d4 solid;
}
.personnel ul li:first-child{
    height: 40px;
    line-height: 40px;
    background: #daf0fb;
    color: #4c708a;
    font-size: 12px;
}
.personnel ul li{
    height: 40px;
    line-height: 40px;
    text-align: center;
    border-left: 1px #d4d4d4 solid;
    border-bottom: 1px #d4d4d4 solid;
}
.confirm-staff{
    background:url(${pageContext.request.contextPath}/res/css/dygl/images/confirm_staff.png) no-repeat #99ffa5 75% 15px;
}
.restrict{
    float: left;
    width: 18.4%;
}
</style>
<script type="text/javascript">
$(function(){
	$(".personnel").on("click","ul li",function(){
        if($(this).hasClass("cannot")){
        }else{
            $(this).toggleClass("confirm-staff");
        }
    })
});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="值日管理" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							<c:choose>
								<c:when test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									班集体评价>>值日管理
								</c:when>
								<c:otherwise>
									班集体评价>>值日查看
								</c:otherwise>
							</c:choose>
							<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								<p class="btn_link" style="float: right;">
									<a href="javascript:void(0)" class="a2" onclick="toStatisticsIndex();"><i class="fa fa-bar-chart"></i>值日统计</a>
								</p>
							</c:if>
							<p class="btn_link" style="float: right;">
								<a href="javascript:void(0)"  class="a3" onclick="history.go(-1)"><i class="fa  fa-reply"></i>返回</a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
							<div class="check-rated">
                        <div class="card_detail">
                        <div class="project-rated">
                        <div class="content-widgets">
                        <div class="widget-container">
                            <div class="select_b">
                            <div class="select_div" style="display: none"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;" ></select> </div>
								<div class="select_div" style="display: none"><span>班级：</span><select id="bj" name="bj" class="chzn-select"  style="width:120px;"></select> </div>
								<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;" onchange="search()"></select> </div>
								<div class="select_div"><span>周次：</span><select id="select_week" style="width:260px;" onchange="search()"></select></div>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
									<div class="select_div" style="margin-left: 40px;width: 160px;"><span></span><input id="name" type=text" style="width:150px;height: 23px;"placeholder="请输入老师姓名进行查找"/> </div>
									<button type="button" class="btn btn-primary" onclick="find()">搜索</button>
									<p id="btn_save" class="btn_link" style="float: right;">
	                                	<a href="javascript:void(0)" onclick="save();" class="a4" style="padding: 0 20px;margin: 2px 15px 0 0;">保存</a>
	                                	<input type="hidden" id="isvip" value="yes">
	                           		</p>
								</c:if>
								<div class="clear"></div>
							</div>
                        </div>
                    </div>
                    <div class="clear"></div>
                        
                        </div>
                        <div class="attendant">

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
$(function(){
	//级联班级控件
	$.initCascadeSelector({
		"type" : "team",
		"selectOne":true
	});	
	//周次控件
	$.getWeek({
		"selector" : "#select_week",
		"begin" : "${beginDate}",
		"end" : "${endDate}",
		"isClear" : false,
		"today" : "${today}",
		"isSelectCurrentWeek" : true,
		"clearedOptionTitle" : "请选择学期"
	});
	search();
});

function clean(){
	$("ul li").each(function(){
        if($(this).hasClass("confirm-staff")){
            $(this).removeClass("confirm-staff");
        }
    });
	alert(${isOnDuty});
}

function find(){
	$(".personnel li").each(function(){
		$(this).removeClass("chosen");
	});
	var name = $("#name").val();
	var index = 0;
	if(name != ""){
		var reg = new RegExp(name);
		$(".personnel li").each(function(){
			if(!$(this).hasClass("cannot") && reg.test($(this).text())){
				$(this).addClass("chosen");
				index = $(this).index();
				var li = $($(".personnel ul:first")).children("li")[index];
				$(li).addClass("chosen");
			}
		});
	}
	
}

function search(){
	var loader = new loadLayer();
	var gradeId = $("#nj").val();
	var week = $("#select_week").val();
	if ("" === gradeId || "undefined" === gradeId) {
		$.error("请选择年级");
		return false;
	}
	if ("" === week || "undefined" === week) {
		$.error("请选择周次");
		return false;
	}
	var $requestData = {};
	$requestData.gradeId = gradeId;
	$requestData.week = week;
	loader.show();
	
	var manager=$('#isvip').val();
	var url = "";
	if(manager!='yes'){
		url = "${pageContext.request.contextPath}/teach/teamEvaluation/duty/view";
	}else{
		url = "${pageContext.request.contextPath}/teach/teamEvaluation/duty/list";
	}
	$.post(url, $requestData, function(data, status) {
		if("success" === status) {
			$(".attendant").html(data);
		}
		loader.close();
	});
}

function save(){
	var loader = new loadLayer();
	var $requestData = {};
	var gradeId = $("#nj").val();
	var beginDate = $($(".personnel ul:nth-child(2)").children("li")[0]).attr("data-date");
	var endDate = $($(".personnel ul:last").children("li")[0]).attr("data-date");
	var week = $("#select_week").val();
	
	var teacherData = "";
	$("ul li").each(function(){
        if($(this).hasClass("confirm-staff")){
        	var teacherId = $(this).attr("data-ti");
			var userId = $(this).attr("data-ui");
			var onDutyDate = $($(this).parent().children("li")[0]).attr("data-date");
			
			var teacher = "{";
			teacher += "teacherId:'"+teacherId;
			teacher += "',userId:'"+userId;
			teacher += "',onDutyDate:'"+onDutyDate;
			teacher += "'}";
			
			if(teacherData != ""){
				teacherData += "," + teacher;
			}else{
				teacherData = "[" + teacher;
			}
        }
    });
	if(teacherData != ""){
		teacherData += "]";
	}else{
		teacherData += "[]";
	}
	
	$requestData.teacherData = teacherData;
	$requestData.gradeId = gradeId;
	$requestData.beginDate = beginDate;
	$requestData.endDate = endDate;
	$requestData.week = week;
	loader.show();
	$.post("${pageContext.request.contextPath}/teach/teamEvaluation/duty/save", $requestData, function(data, status) {
		if("success" === status) {
			data = eval("("+data+")");
			if(data.info === "success"){
				$.success("保存成功");
			}else if(data.info === "fail"){
				$.error("保存失败");
			}else{
				
			}
		}
		loader.close();
	});
}

	function toStatisticsIndex(){
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/duty/statistics/index";
	}

</script>
</html>