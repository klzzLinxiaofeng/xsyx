<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激励评价</title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css" rel="stylesheet">
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
        //console.log(id)
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
					<h3>
						激励评价
					</h3>
				</div>
				<div class="content-widgets" style="padding: 20px 20px 1px 20px;">
					<div class="check-rated">
					<div class="minutes-rated">
                           <a href="javascript:void(0);" onclick="index();">激励评价查看</a>
                           <a href="javascript:void(0);" class="see-rated">激励评价录入</a>
                  		</div>
				 		<div class="card_detail">


							<div class="project-rated">
                         <div class="content-widgets">
						<div class="widget-container" style="padding: 20px 0 0 0">
						<div class="select_b" id="sel_div">
							
						<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;"></select></div>
<!-- 						<div class="select_div"><span>学期：</span><select id="xq" name="xq" class="chzn-select" style="width:120px;" onchange="getDay()"></select></div> -->
<!-- 						<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select></div> -->
<!-- 						<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;"></select></div> -->
						<c:choose>
								<c:when test="${param.type==1}">
									<div class="select_div"><span>学期：</span><select id="xq" name="xq" class="chzn-select" style="width:140px;" onchange="getClass()"></select></div>
									<div class="select_div"><span>班级：</span><select id="teamId" name="teamId" class="chzn-select" style="width:120px;" onchange="search()"></select></div>
								</c:when>
								<c:otherwise>
									<div class="select_div"><span>学期：</span><select id="xq" name="xq" class="chzn-select" style="width:140px;" onchange="getDay()"></select></div>
									<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select></div>
									<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;" onchange="search()"></select></div>
								</c:otherwise>
						</c:choose>
						<div class="select_div"><span>日期：</span><input id="startDate" class="Wdate" type="text" 
							onFocus="time()" style="width:120px;height:28px;"/></div>
<!-- 							<button class="btn btn-primary" type="button" onClick="search()">查询</button> -->
						<p class="btn_link" style="float: right; margin: 10px 10px 0 0;">
							<a href="javascript:void(0)" class="a4" onclick="save()">保存</a>
						</p>
						<div class="clear"></div>
						</div>
						</div>
						</div>
						 <div id="addEvaList" class="evaluation-card">


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
	var start;
	var end;
	var teamId = null;
	var termCodeNow = "${termCodeCurrent}";
	
	function time(){
		WdatePicker({lang:'zh-cn',minDate:start,maxDate:end,onpicked:search})
	}
	
	function getDay(){
		var term=$('#xq').val();
		$("#xq").chosen();
	  	$('#startDate').val("");
	  	if("" === term || "undefind" === term){
	  		$.error("请选择学期");
	  		return false;
	  	}
	  	var $requestData = {};
	  	$requestData.code=$('#xq').val();
	  	$.get("${pageContext.request.contextPath}/teach/teamEvaluation/list/json", $requestData, function(data, status) {
	  		if("success" === status) {
	  			data = eval("(" + data + ")");
	  				start = data.begin;
	  				end = data.end;
	  				if(new Date().Format("yyyy-MM-dd")>end){
						$("#startDate").val(end);
					}else if(new Date().Format("yyyy-MM-dd")<start){
						$("#startDate").val(start);
					}else{
						$("#startDate").val(new Date().Format("yyyy-MM-dd"));
					}
	  		}
	  		var teamId = null;
			if($("#sel_div").find("#nj").length>0){
				teamId = $("#bj").val();
			}else{
				teamId = $("#teamId").val();
			}
			if(teamId != null && teamId !=""){
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
				
			},
			"teamCallback" : function() {
				if(teamId != $("#bj").val()){
					search();
					teamId = $("#bj").val();
				}
			}
		});	
		//termCodeNow = $("#xq").val();
		//getDay();
		/* 时间只能是数字加- */
		$(".Wdate").keyup(function(){
        	this.value = this.value.replace(/[^\-\d.]/g, "");
        });
	});
	


</script>
<script type="text/javascript">
	//跳转到查看页面
	function index(){
		window.location.href="${pageContext.request.contextPath}/teach/incentiveEvaluation/index?dm=${param.dm}&type=${param.type}";
	}

	//查询数据
	function search(){
		var loader = new loadLayer();
		var year = $("#xn").val();
		var termCode = $("#xq").val();
		var gradeId = $("#nj").val();
		var checkDate = $("#startDate").val();
		
		var teamId = null;
		if($("#sel_div").find("#nj").length>0){
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
		if ("" === gradeId || "undefind" === gradeId) {
			$.error("请选择年级");
			return false;
		}
		if ("" === checkDate || "undefind" === checkDate) {
			$.error("请选择日期");
			return false;
		}
		
		var $requestDate = {};
		$requestDate.year = year;
		$requestDate.termCode = termCode;
		$requestDate.gradeId = gradeId;
		$requestDate.teamId = teamId;
		$requestDate.checkDate = checkDate;		
		loader.show();
		
		var url  = "${pageContext.request.contextPath}/teach/incentiveEvaluation/getEvaList";
		if(termCode!="" && gradeId!="" && teamId!="" && checkDate!="" ){
			$.get(url,$requestDate,function(data,status){
				if(status === "success"){
					$("#addEvaList").html(data);
					if(checkDate>new Date().Format("yyyy-MM-dd")){
				    	$.alert("选择的时间已超过当前时间，请重新选择");
				    }
				}
				loader.close();
			})
			
		}else {
			$("#addEvaList").html("");
		}
	}
	
	//保存
	function save(){	
		var datas = new Array();
		var i = 0;
		$(".prize-student").each(function(){
			var itemId = $(this).children("input").val();
			$(this).children("ul").children("li").each(function(){
				datas[i] = new Array();
 				//var studentName = $(this).children("p").text();
				var studentId = $(this).data("id");
				datas[i][0] = itemId;
				datas[i][1] = studentId;
				i++;
 				//console.log(datas);
			});
		});
		
		var loader = new loadLayer();
		var termCode = $("#xq").val();
		var checkDate = $("#startDate").val();
		
		var teamId = null;
		if($("#sel_div").find("#nj").length>0){
			teamId = $("#bj").val();
		}else{
			teamId = $("#teamId").val();
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
		if ("" === checkDate || "undefind" === checkDate) {
			$.error("请选择日期");
			return false;
		}
		if(termCodeNow != termCode){
			$.error("已超过当前学年学期时间段，不可以重新评定");
			return false;
		}
		if(checkDate>new Date().Format("yyyy-MM-dd")){
	    	$.error("选择的时间已超过当前时间，请重新选择");
	    	return false;
	    }
		var itemDatas = JSON.stringify(datas);
		
		var $requestDate = {};
		$requestDate.termCode = termCode;
		$requestDate.teamId = teamId;
		$requestDate.checkDate = checkDate;
		$requestDate.itemDatas = itemDatas;
		
		//console.log($requestDate);
		//console.log($requestDate.itemDatas);
		
		loader.show();
		var url = "${pageContext.request.contextPath}/teach/incentiveEvaluation/setScores";
		$.post(url,$requestDate,function(data,status){
			if(status=="success"){
				data = eval("("+data+")");
				if(data.info === "success"){
					search();
					$.success("保存成功");
				}else if(data.info === "fail"){
					$.error("没有编辑权限");
				}else{
					$.error("保存失败");
				}
			}
			loader.close();
		})
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
		getDay();
	}
	
</script>


</html>