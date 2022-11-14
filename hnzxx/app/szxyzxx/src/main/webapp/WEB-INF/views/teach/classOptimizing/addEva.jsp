<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>课堂优化</title>
<link
	href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/dygl/dygl.css"
	rel="stylesheet">
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
        console.log(id);
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
                           <a href="javascript:void(0);" onclick="index();">课堂优化查看</a>
                           <a href="javascript:void(0);" class="see-rated">课堂优化</a>
                       </div>
					<div class="card_detail">
                        
                        
       <div class="project-rated">
			<div class="content-widgets">
				<div class="widget-container" style="padding: 20px 0 0 0">
					<div class="select_b" id="sel_div">
						<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:120px;"></select></div>
						<c:choose>
							<c:when test="${param.type==1}">
								<div class="select_div"><span>学期：</span><select id="xq" name="xq" class="chzn-select" style="width:150px;" onchange="getClass()"></select></div>
								<div class="select_div"><span>班级：</span><select id="teamId" name="teamId" class="chzn-select" style="width:120px;" onchange="search()"></select></div>
							</c:when>
							<c:otherwise>
								<div class="select_div"><span>学期：</span><select id="xq" name="xq" class="chzn-select" style="width:150px;" onchange="getDay()"></select></div>
								<div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select" style="width:120px;"></select></div>
								<div class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;" onchange="search()"></select></div>
							</c:otherwise>
						</c:choose>
						
						<div class="select_div"><span>日期：</span><input id="startDate" class="Wdate" type="text"  
							onFocus="time()" style="width:120px;height:28px;margin:0"/>

						</div>
						<div class="select_div"><span>节次：</span>
							<select id="jc" name="jc" class="chzn-select" style="width:100px;height:28px;margin:0" onchange="search()">
								<option value="">请选择</option>
								<option value="1" selected>第1节</option>
								<option value="2">第2节</option>
								<option value="3">第3节</option>
								<option value="4">第4节</option>
								<option value="5">第5节</option>
								<option value="6">第6节</option>
								<option value="7">第7节</option>
								<option value="8">第8节</option>
							</select>
						</div>
						<p class="btn_link" style="float: right; margin: 5px 10px 0 0;">
							<a href="javascript:void(0)" class="a4" onclick="save();">保存</a>
						</p>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		
			<div class="evaluation-card" style="margin: 0" id="addEvaList">
		
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
	var itemName = null;
	var termCodeNow = "${termCodeCurrent}";
	var isEdit = "${isEdit == null || isEdit == "" ? false : isEdit}";
	
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
	  		if(isEdit == "false" && teamId != null && teamId !=""){
	 		  	search();
	  		}
	  	});
	}

	$(function() {

		var modifyYear = "${param.year}";
		var modifyTermCode = "${param.termCode}";
		var modifyGradeId = "${param.gradeId}";
		var modifyTeamId = "${param.teamId}";
		var modifyCheckDate = "${param.checkDate}";
		var modifyCheckRange = "${param.checkRange}";
		
		if(modifyYear != null && modifyYear != "" 
			&& modifyTermCode != null && modifyTermCode != "" 
			&& modifyTeamId != null && modifyTeamId != "") {
			
			//由重新编辑按钮进入
			if(modifyGradeId != null && modifyGradeId != ""){
				//有年级（管理员、德育主任） 
				$.initCascadeSelector({
					"type" : "team",			
					"selectOne":true,
					"isEchoSelected" : true,
					"yearSelectedVal" : modifyYear,
					"yearChangeCallback" : function(year) {
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
					},
					"gradeSelectedVal" : modifyGradeId,
					"teamSelectedVal" : modifyTeamId
				});
				
			}else{
				//无年级（教师）
				$.initCascadeSelector({
					"type" : "team",			
					"yearSelectedVal" : modifyYear,
					"yearChangeCallback" : function(year) {
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
					}
				});
				var selectObj = $("#teamId"); 
				selectObj.parent().children().remove('div'); 
				selectObj.removeClass(); 
				$("#teamId option[value='"+modifyTeamId+"']").attr("selected","selected"); 
				selectObj.addClass("chzn-select"); 
				selectObj.chosen(); 
				
			}
			$("#startDate").val(modifyCheckDate);
 			$("#jc").val(modifyCheckRange);
 			search();
 			itemName = "${badBehaviour}";
 			
		} else {
			//切换页面进入
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
					if(teamId != $("#bj").val()){
						search();
						teamId = $("#bj").val();
					}
				}
			});	
		}
		//termCodeNow = $("#xq").val();
		
		//getDay();
		$(".Wdate").keyup(function(){
	    	this.value = this.value.replace(/[^\-\d.]/g, "");
	    });
		$("#jc").chosen();
	});

</script>
<script type="text/javascript">
	//跳转到查看页面
	function index(){
		window.location.href="${pageContext.request.contextPath}/teach/classOptimizing/index?dm=${param.dm}&type=${param.type}";
	}
	
	
	function search(){
		var loader = new loadLayer();
		var year = $("#xn").val();
		var termCode = $("#xq").val();
		var gradeId = $("#nj").val();
		var checkDate = $("#startDate").val();
		var checkRange = $("#jc").val();
		$("#jc").chosen();
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
		if ("" === checkRange || "undefind" === checkRange) {
			$.error("请选择节次");
			return false;
		}
		
		var $requestDate = {};
		$requestDate.year = year;
		$requestDate.termCode = termCode;
		$requestDate.gradeId = gradeId;
		$requestDate.teamId = teamId;
		$requestDate.checkDate = checkDate;
		$requestDate.checkRange = checkRange;
		loader.show();
		
		var url  = "${pageContext.request.contextPath}/teach/classOptimizing/getEvaList";
		if(termCode!="" && gradeId!="" && teamId!="" && checkDate!="" && checkRange!=""){
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
	
	
	function save(){
		var datas = new Array();
		var i = 0;
		$(".prize-student").each(function(){
			var itemId = $(this).children("input").val();
			$(this).children("ul").children("li").each(function(){
				datas[i] = new Array();
				var studentId = $(this).data("id");
				datas[i][0] = itemId;
				datas[i][1] = studentId;
				i++;
			});
		});
		
		var loader = new loadLayer();
		var termCode = $("#xq").val();
		var checkDate = $("#startDate").val();
		var checkRange = $("#jc").val();
		
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
		if ("" === checkRange || "undefind" === checkRange) {
			$.error("请选择节次");
			return false;
		}
		if(checkDate>new Date().Format("yyyy-MM-dd")){
	    	$.error("选择的时间已超过当前时间，请重新选择");
	    	return false;
	    }
		if(termCodeNow != termCode){
			$.error("已超过当前学年学期时间段，不可以重新评定");
			return false;
		}
		var itemDatas = JSON.stringify(datas);
		
		var $requestDate = {};
		$requestDate.termCode = termCode;
		$requestDate.teamId = teamId;
		$requestDate.checkDate = checkDate;
		$requestDate.itemDatas = itemDatas;
		$requestDate.checkRange = checkRange;
		
		loader.show();
		var url = "${pageContext.request.contextPath}/teach/classOptimizing/setScores";
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