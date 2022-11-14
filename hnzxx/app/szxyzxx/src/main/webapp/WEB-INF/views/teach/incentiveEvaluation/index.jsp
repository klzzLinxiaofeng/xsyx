<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
<title>激励评价</title>
<script type="text/javascript">
$(function(){
	/* 切换选项卡 */
// 	$(".minutes-rated a").click(function(){
// 	    $(".minutes-rated a").removeClass("see-rated");
// 	    $(this).addClass("see-rated");
// 	    var i=$(this).index();
// 	    $(".project-rated").hide();
// 	    $(".project-rated").eq(i).show();
// 	});
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
         $("#"+id).removeClass("on");
        $(this).parent().remove();
    });
/*右边选中取消*/
     $(".all-class-students ul li").click(function(){
        $(this).toggleClass("on");
    });
     /*右边内容过来*/
     $(".evaluate-aleft a").click(function(){
        var k,l;
        for(k=0;k<$(".evaluate-option a").length;k++){
            if($(".evaluate-option a").eq(k).hasClass("evaluate-called")){
                var second_menu=$(".evaluate-projects").eq(k).children(".evaluate-action").children("a");
                for(l=0;l<second_menu.length;l++){
                    if(second_menu.eq(l).hasClass("evaluate-confirm")){
                        break;
                    }
                }
            break;
            }
        }
        var s_lenght=$(".all-class-students .on").length;
            $(".evaluate-projects").eq(k).children(".evaluate-div").children(".prize-student").eq(l).children("ul").empty();
        for(var m=0;m<s_lenght;m++){
            var touxiang=$(".all-class-students .on").eq(m).children("img").attr("src");
            var name=$(".all-class-students .on").eq(m).children("p").text();
            var id=$(".all-class-students .on").eq(m).attr('id');
            $(".evaluate-projects").eq(k).children(".evaluate-div").children(".prize-student").eq(l).children("ul").append("<li data-id="+id+"><img src='"+touxiang+"'><p>"+name+"</p><a href='javascript:void(0);' class='reveal'><img src='${pageContext.request.contextPath}/res/css/dygl/images/evaluate_delete.png'></a></li>");
        }
     })

    $(".evaluate-div").children(".prize-student").eq(0).show();
    $(".evaluate-option a").click(function(){
         $(".all-class-students ul li").removeClass("on");
        $(".evaluate-option a").removeClass("evaluate-called");
        $(this).addClass("evaluate-called");
        var i=$(this).index();
        $(".evaluate-projects").hide();
        $(".evaluate-projects").eq(i).show();
        var a_length=$(".evaluate-projects").eq(i).children(".evaluate-action").children("a").length;
        for(var o=0;o<a_length;o++){
            if($(".evaluate-projects").eq(i).children(".evaluate-action").children("a").eq(o).hasClass("evaluate-confirm")){
                $(".evaluate-projects").eq(i).find(".prize-student").eq(o).show();
                var s_shu=$(".evaluate-projects").eq(i).find(".prize-student").eq(o).children("ul").children("li").length;
                if(s_shu!=0){
                    for(var n=0;n<s_shu;n++){
                        var id=$(".evaluate-projects").eq(i).find(".prize-student").eq(o).children("ul").children("li").eq(n).data("id");
                        $("#"+id).addClass("on");
                    }
                }
            }
        }
    });
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

var bjId;
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


</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="激励评价" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>激励评价</h3>
					</div>
					<div class="content-widgets" style="padding: 20px 20px 1px 20px;">
						<div class="check-rated">
						<div class="minutes-rated">
                            <a href="javascript:void(0);" class="see-rated">激励评价查看</a>
                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
                            	<a href="javascript:void(0);" onclick="addEva()">激励评价录入</a>
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
										<div class="select_div"><span>学年：</span> <select id="xn" name="xn" style="width:150px;"></select></div>
											<c:choose>
													<c:when test="${param.type==1}">
														<div class="select_div"><span>学期：</span> <select id="xq" name="xq" style="width:150px;" onchange="getClass()"></select></div>
														<div class="select_div"><span>班级：</span><select id="teamId" name="teamId" onchange="search()" style="width:120px;"></select> </div>
													</c:when>
													<c:otherwise>
														<div class="select_div"><span>学期：</span> <select id="xq" name="xq" style="width:150px;" onchange="getMonth()"></select></div>
														<div class="select_div"><span>年级：</span> <select id="nj" name="nj" style="width:150px;"></select></div>
														<div class="select_div"><span>班级：</span><select id="bj" name="bj" onchange="search()" style="width:120px;"></select> </div>
													</c:otherwise>
												</c:choose>
													<div class="select_div">
														<span>月次：</span> <input type="text" class="Wdate" id="d4" onFocus="WdatePicker({dateFmt:'yyyy年M月',minDate:begin,maxDate:end,onpicked:search})" style="width:150px;margin-bottom:0"/>
													</div>
											<p class="btn_link"
												style="float: right; line-height: 47px;margin: 6px 10px 0 0;">
<!-- 												<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();"><i class="fa  fa-plus"></i> 导出</a> -->
												<a href="javascript:void(0)" class="a2" onclick="pjbb();"><i class="fa  fa-bar-chart"></i> 评价报表</a>
											</p>
											<div class="clear"></div>
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
	function pjbb(){
		var s=$('#isvip').val();
		window.location.href="${pageContext.request.contextPath}/teach/incentiveEvaluation/indexReport?type=${param.type}&manager="+s;
	}
	//跳转到添加评价页面
	function addEva(){
		window.location.href="${pageContext.request.contextPath}/teach/incentiveEvaluation/addEva?dm=${param.dm}&type=${param.type}";
	}

	function search(){
		var loader = new loadLayer();
		var year = $("#xn").val();
		var month = $("#d4").val();
		var termCode = $("#xq").val();
		var teamId = $("#bj").val();
		var teamId = null;
		var nj = null;
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
				"year":year,
				"month":month,
				"termCode":termCode,
				"teamId":teamId
		};
		var url = "${pageContext.request.contextPath}/teach/incentiveEvaluation/list";
		loader.show();
		$.post(url,$requestData,function(data,status){
			if(status === "success"){
				$("#list").html(data);
			}
			loader.close();
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
		});
		
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
		search();
		});
	}
	var begin;
	var end;
	//去拿到学期起始时间或结束时间
	function getMonth(){
		$("#d4").val("");
		var term=$('#xq').val();
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
	
</script>
</html>