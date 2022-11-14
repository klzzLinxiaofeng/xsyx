<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>班集体评价</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<script type="text/javascript">
$(function(){
    $(".points-content").eq(0).show();
    $(".points-content").eq(2).show();
    $(".minutes-rated a").click(function(){
        $(".minutes-rated a").removeClass("see-rated");
        $(this).addClass("see-rated");
        var i=$(this).index();
//         $(".project-rated").hide();
//         $(".project-rated").eq(i).show();
        // if($(".project-rated").eq(i).children(".plus-minus-rated").children("a").eq(0).hasClass("current-rated")){
        //     $(".project-rated").eq(i).find(".points").eq(0).show();
        // }
    });
    $(".plus-minus-rated a").click(function(){
        var j=$(this).index();
        $(this).siblings().removeClass("current-rated");
        $(this).addClass("current-rated");
        $(this).parent().parent().children(".points-content").hide();
        $(this).parent().parent().children(".points-content").eq(j).show();
    });
});
//去拿到学期起始时间或结束时间



</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="班集体评价" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<input type="hidden" value="${dm}" id="dm">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							班集体评价
							<p class="btn_link" style="float: right;">
                                <a href="javascript:void(0)" onclick="toDuty();" class="a3" style="padding: 0 20px;">值日管理</a>
                            </p>
						</h3>
					</div>
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<div class="check-rated">
							<div class="minutes-rated">
                            <a href="javascript:void(0);" onclick="index()">班集体评价查看</a>
                            <a href="javascript:void(0);" class="see-rated">添加班集体评价</a>
                        </div>
                        <div class="card_detail">
							
                        <div class="project-rated">
                            <div class="content-widgets">
                        <div class="widget-container" style="padding:20px 0 0 0">
                            <div class="select_b">
                            	<div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select" style="width:150px;"></select> </div>
								<div class="select_div"><span>学期：</span><select id="xq" name="xq" onchange="getDay()" class="chzn-select" style="width:150px;"></select> </div>
								<div class="select_div"><span>年级：</span><select id="nj" name="nj" onchange="search()" class="chzn-select" style="width:150px;"></select> </div>
								<div style="display:none" class="select_div"><span>班级：</span><select id="bj" name="bj" class="chzn-select" style="width:120px;"></select> </div>
								<div class="select_div"><span>日期：</span><input id="startDate" class="Wdate" type=text" onFocus="WdatePicker({lang:'zh-cn',minDate:start,maxDate:end,onpicked:search})" style="width:150px; height:31px;    box-sizing: border-box;"/> </div>
								  <p class="btn_link" style="float: right;margin:5px 10px 0 0;">
									<a href="javascript:void(0);" class="a6" onclick="save()">保存</a>
									</p>
								<div class="clear"></div>
							</div>
                        </div>
                    </div>
                    <div class="luru_fl">
                    	<a href="javascript:void(0)" class="on pllr"><p><span></span>批量录入</p></a>
                    	<a href="javascript:void(0)" class="xmlr"><p><span></span>单项录入</p></a>
                    </div>
                    <div class="luru">
                    <div id="add_eva">
                   		 
                       </div>
                       <div id="one_eva" style="display:none">
                       
                       </div>
                       </div>
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
	var termCodeNow;
// 	$('#xn').on('change',
// 		function(){
// 			$("xq").change();		
// 		}
// 	);
	
	function getDay(){
		var term=$('#xq').val();
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
	  		var nj = $('#nj').val();
	  		if(nj!=""){
		  		search();
		  		var flag = $(".dan_all").is(":hidden");
		  		if(flag){
			  		search2();
		  		}
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
				
			}
		});	
		termCodeNow = $("#xq").val();
		getDay();
		/* 时间只能是数字加- */
		$(".Wdate").keyup(function(){
        	this.value = this.value.replace(/[^\-\d.]/g, "");
        });
		$(".luru_fl a").click(function(){
			$(".luru_fl a").removeClass("on");
			$(this).addClass("on");
			var i=$(this).index();
			$(".luru").children().hide();
			$(".luru").children().eq(i).show();
		})
	});

	function index(){
		var dm=$('#dm').val();
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/index?dm="+dm;
	}
	
	function save(){
		if(!$("#add_eva").is(":hidden")){
			var reduce = new Array();
		    $(".reduce").children().each(function (index) {
		    	reduce[index] = new Array();
		    	$(this).find("input").each(function (j) {
		    		if($(this).val()!="-"){
		    			reduce[index][j] =   $(this).val();
		    		}else if($(this).val() == "-"){
		    			reduce[index][j] = "";
		    		}
		    	});
		    	
		    });
		    
		    var add = new Array();
		    $(".add").children().each(function (index) {
		    	add[index] = new Array();
		    	$(this).find("input").each(function (j) {
		    		if($(this).val()!="-"){
		    			add[index][j] =   $(this).val();
		    		}else if($(this).val() == "-"){
		    			add[index][j] = "";
		    		}
		    	});
		    	
		    });
		    var reduceScore = JSON.stringify(reduce);
			var addScore = JSON.stringify(add);
		}else{
			var id = $("#danx").val();
			var name = $("#itemName").val();
			var array = new Array();
			var i =0;
			$(".dx").find("input").each(function(){
				var score = $(this).val();
				array[i]=score;
				i++;
			})
			var json = JSON.stringify(array);
			
		}
		var gradeId = $("#nj").val();
		var termCode = $("#xq").val();
		var checkDate = $("#startDate").val();
		var year = $("#xn").val();
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
		var url = "${pageContext.request.contextPath}/teach/teamEvaluation/setScore";
		var $requestDate = {};
		$requestDate.reduceScore = reduceScore;
		$requestDate.addScore = addScore;
		$requestDate.gradeId = gradeId;
		$requestDate.termCode = termCode;
		$requestDate.checkDate = checkDate;
		$requestDate.json = json;
		$requestDate.id = id;
		$.post(url,$requestDate,function(data,status){
			if(status=="success"){
				data = eval("("+data+")");
				if(data.info === "success"){
						list(id,name);
						search();
					setTimeout(function () {
						$.success("保存成功");
					},500)
				}else if(data.info === "fail"){
					$.error("用户角色没有编辑权限");
				}else{
					
				}
				
			}
		})
		
	}
	
	function empty(){
		$("#add_eva").html("");
	}
	
	function search(){
		var loader = new loadLayer();
		var year = $("#xn").val();
		var termCode = $("#xq").val();
		var gradeId = $("#nj").val();
		var checkDate = $("#startDate").val();
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
		if ("" === checkDate || "undefind" === checkDate) {
			$.error("请选择日期");
			return false;
		}
		var $requestDate = {};
		$requestDate.gradeId = gradeId;
		$requestDate.termCode = termCode;
		$requestDate.checkDate = checkDate;
		$requestDate.year = year;
		loader.show();
		var url  = "${pageContext.request.contextPath}/teach/teamEvaluation/evaList";
		if(termCode!="" && gradeId!="" && checkDate!="" ){
			$.get(url,$requestDate,function(data,status){
				if(status === "success"){
					var flag = $(".dan_all").is(":hidden");
					var flag2 = $("#add_eva").is(":hidden");
					var id = $("#danx").val();
					var name = $("#itemName").val();
					if(flag2&&flag){
						list(id,name);
					}else{
						search2();
					}
					$("#add_eva").html(data);
					if(checkDate>new Date().Format("yyyy-MM-dd")){
				    	$.alert("选择的时间已超过当前时间，请重新选择");
				    }
					
				}
				loader.close();
			})
			
		}else {
			$("#add_eva").html("");
		}
	}
	
	function search2(){
		var year = $("#xn").val();
		var termCode = $("#xq").val();
		var gradeId = $("#nj").val();
		var checkDate = $("#startDate").val();
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
		if ("" === checkDate || "undefind" === checkDate) {
			$.error("请选择日期");
			return false;
		}
		var $requestDate = {};
		$requestDate.gradeId = gradeId;
		$requestDate.termCode = termCode;
		$requestDate.checkDate = checkDate;
		$requestDate.year = year;
		var url  = "${pageContext.request.contextPath}/teach/teamEvaluation/dx_evaList";
		if(termCode!="" && gradeId!="" && checkDate!="" ){
			$.get(url,$requestDate,function(data,status){
				if(status === "success"){
					$("#one_eva").html(data);
				}
				
			})
		}else {
			$("#one_eva").html("");
		}
	}
	
	function list(id,name){
		var loader = new loadLayer();
		var year = $("#xn").val();
		var termCode = $("#xq").val();
		var gradeId = $("#nj").val();
		var checkDate = $("#startDate").val();
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
		if ("" === checkDate || "undefind" === checkDate) {
			$.error("请选择日期");
			return false;
		}
		var $requestDate = {};
		$requestDate.gradeId = gradeId;
		$requestDate.termCode = termCode;
		$requestDate.checkDate = checkDate;
		$requestDate.year = year;
		$requestDate.id = id;
		$requestDate.name = name;
		loader.show();
		var url  = "${pageContext.request.contextPath}/teach/teamEvaluation/dx_list";
		if(termCode!="" && gradeId!="" && checkDate!="" ){
			$.post(url,$requestDate,function(data,status){
				if(status === "success"){
					$(".dan_one").html(data);
				}
			loader.close();
			})
		}else {
			$(".dan_one").html("");
		}
	}
	
	function toDuty(){
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/duty/index?dm=${param.dm}";
	}
	</script>
</html>