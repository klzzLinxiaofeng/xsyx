<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>课堂优化</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
<style>
.evaluate-action {
    width: 130px;
    height: 100%;
    float: left;
    background: #f4f4f4;
}
.evaluate-action a {
    line-height: 40px;
    width: 120px;
    color: #535353;
    font-size: 12px;
    border-bottom: 1px solid #acb0b1;
    float: left;
    padding-left: 10px;
}
a.evaluate-confirm {
    color: #0073d6;
    background: #fff;
    margin-left: 1px;
    width: 120px;
    font-weight: bold;
}
</style>
<script type="text/javascript">
$(function(){
/*鼠标移入左边浮现删除*/
$(".evaluate-projects ").on("mouseover mouseout","ul li",function(event){
 if(event.type == "mouseover"){
   $(this).find(".reveal").show();
 }else if(event.type == "mouseout"){
  $(this).find(".reveal").hide();
 }
})
/*删除这个li*/
    $(".evaluate-projects").on("click","ul li a",function(){
        var id=$(this).parent().data("id");
        console.log(id)
         $("#"+id).removeClass("on");
        $(this).parent().remove();
    });
/*右边选中取消*/
     $(".all-class-students ul li").click(function(){
        $(this).toggleClass("on");
    });
     /*右边内容过来*/
     $(".evaluate-aleft a").click(function(){
        var l;
        for(l=0;l<$(".evaluate-action a").length;l++){
            if($(".evaluate-action a").eq(l).hasClass("evaluate-confirm")){
                break;
            }
        }
        
        var s_lenght=$(".all-class-students .on").length;
            $(".prize-student").eq(l).children("ul").empty();
        for(var m=0;m<s_lenght;m++){
            var touxiang=$(".all-class-students .on").eq(m).children("img").attr("src");
            var name=$(".all-class-students .on").eq(m).children("p").text();
            var id=$(".all-class-students .on").eq(m).attr('id');
            $(".prize-student").eq(l).children("ul").append("<li data-id="+id+"><img src='"+touxiang+"'><p>"+name+"</p><a href='javascript:void(0);' class='reveal'><img src='${pageContext.request.contextPath}/res/css/dygl/images/evaluate_delete.png'></a></li>");
        }
     })

    $(".evaluate-div").children(".prize-student").eq(0).show();
    $(".evaluate-action a").click(function(){
        var j=$(this).index();
        $(this).siblings().removeClass("evaluate-confirm");
        $(this).addClass("evaluate-confirm");
        $(this).parent().next().children(".prize-student").hide();
        $(this).parent().next().children(".prize-student").eq(j).show();
        var s_shu=$(this).parent().next().children(".prize-student").eq(j).children("ul").children("li").length;
            $(".all-class-students ul li").removeClass("on");
        if(s_shu!=0){
            for(var n=0;n<s_shu;n++){
                var id=$(this).parent().next().children(".prize-student").eq(j).children("ul").children("li").eq(n).data("id");
                $("#"+id).addClass("on");
            }
        }
    })
});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="课堂优化" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							课堂优化
						</h3>
					</div>
					<div class="content-widgets"  style="padding: 20px 20px 1px 20px;">
							<div class="check-rated">
							<div class="minutes-rated">
	                            <a href="javascript:void(0);" class="see-rated">课堂优化查看</a>
	                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
	                            	<a href="javascript:void(0);" onclick="addEva()">课堂优化</a>
	                            </c:if>
	                            <c:if test="${param.type!=1}">
									<input type="hidden" id="isvip" value="yes">
								</c:if>
	                        </div>
                        <div class="card_detail">
							<div class="project-rated">
								<div class="content-widgets">
									<div class="widget-container" style="padding: 20px 0 0 0">
										<div class="select_b" id="sel_div">
											<div class="select_div" id="sel_div">
												<span>学年：</span> <select id="xn" name="xn" class="chzn-select" style="width:140px;"></select> 
											</div>
											<c:choose>
												<c:when test="${param.type==1}">
													<div class="select_div"><span style="padding-left:30px;">学期： </span> <select id="xq" name="xq" class="chzn-select" style="width:160px;" onchange="getClass()"></select></div>
													<div class="select_div"><span>班级：</span><select id="teamId" name="teamId" class="chzn-select" onchange="search()" style="width:120px;"></select> </div>
												</c:when>
												<c:otherwise>
													<div class="select_div"><span style="padding-left:30px;">学期： </span> <select id="xq" name="xq" class="chzn-select" style="width:160px;" onchange="week()"></select></div>
													<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select"  style="width:120px;"></select> </div>
													<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" onchange="search()" style="width:120px;"></select> </div>
												</c:otherwise>
											</c:choose>
											<div class="select_div">
												<span>周次：</span> <select id="select_week" style="width:240px;" onchange="search()"></select>
											</div>
											<div class="select_div">
												<span>学生：</span><input id="student" type="text" style="width:120px;margin:0" maxlength="10"/>
											</div>
											<button type="button" class="btn btn-primary" onclick="search()">查询</button>
											<p class="btn_link"
												style="float: right; line-height: 47px;margin: 5px 10px 0 0;">
<!-- 												<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-plus"></i> 导出</a>  -->
												<a href="javascript:void(0)" class="a2" onclick="pjbb()"><i class="fa  fa-bar-chart"></i> 评价报表</a>
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
	</div>

</body>
<script type="text/javascript">


function search(){

	var loader = new loadLayer();
	var year = $("#xn").val();
	var week = $("#select_week").val();
	var termCode = $("#xq").val();
	var manager = $("#isvip").val();
	var name = $("#student").val();
	var gradeId = $("#nj").val();
	var teamId = null;
	var nj = null;

	if(name !== ""){
		if(!/^[\u4e00-\u9fa5]+$/i.test(name)){
			$.error("请输入中文姓名");
			return false;
		}
	}

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
	if ("" === gradeId || "undefind" === gradeId) {
		$.error("请选择年级");
		return false;
	}
	if ("" === teamId || "undefind" === teamId) {
		$.error("请选择班级");
		return false;
	}
	if ("" === week || "undefind" === week) {
		$.error("请选择周次");
		return false;
	}
	var $requestData = {
			"year":year,
			"week":week,
			"termCode":termCode,
			"teamId":teamId,
			"manager":manager,
			"name":name
	};
	var url = "${pageContext.request.contextPath}/teach/classOptimizing/list?dm=${param.dm}";
	loader.show();
	$.post(url,$requestData,function(data,status){
		if(status === "success"){
			$("#list").html(data);
		}
		loader.close();
	});
}




var begin;
var end;
var bjId;
function week(){
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
			var today = new Date().Format("yyyy-MM-dd");
			$.getWeek({
				"selector" : "#select_week",
				"begin" : begin,
				"end" : end,
				"isClear" : false,
				"today" : today,
				"isSelectCurrentWeek" : true,
				"clearedOptionTitle" : "请选择学期"
			});
		}
		var teamId =null;
		if($("#sel_div").find("#nj").length>0){
			nj = $("#nj").val();
			teamId = $("#bj").val();
		}else{
			teamId = $("#teamId").val();
		}
		if(teamId!=""){
			search();
		}
	});
}


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
				}
				);
			} else {
				$("#xq").val("");
				$("#xq_chzn").remove();
				$("#xq").show().removeClass("chzn-done").chosen();
			}
			
		},"teamCallback" : function() {
			var s1 = $('#bj').val();
			if (bjId != s1) {
				search();
			}
			bjId = s1;
		}
	});	
// 	search();
});
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
	});
		week();
}

	//跳转到课堂优化页面
	function addEva(){
		window.location.href="${pageContext.request.contextPath}/teach/classOptimizing/addEva?dm=${param.dm}&type=${param.type}";
	}
	//"重新编辑"跳转页面
	function toEvaPage(year,termCode,gradeId,teamId,checkDate,checkRange,badBehaviour){
		if(gradeId != null && gradeId != ""){
			window.location.href="${pageContext.request.contextPath}/teach/classOptimizing/modifyEva?dm=${param.dm}&type=${param.type}&year="+year+
					"&termCode="+termCode+"&gradeId="+gradeId+"&teamId="+teamId+"&checkDate="+checkDate+"&checkRange="+checkRange+
					"&badBehaviour="+encodeURI(encodeURI(badBehaviour));
		}else{
			window.location.href="${pageContext.request.contextPath}/teach/classOptimizing/modifyEva?dm=${param.dm}&type=${param.type}&year="+year+
			"&termCode="+termCode+"&teamId="+teamId+"&checkDate="+checkDate+"&checkRange="+checkRange+"&badBehaviour="+encodeURI(encodeURI(badBehaviour));
		}
	}
	//跳转到评价报表页面
	function pjbb(){
		var s=$('#isvip').val();
		window.location.href="${pageContext.request.contextPath}/teach/classOptimizing/indexReport?dm=${param.dm}&manager="+s;
	}
</script>

</html>