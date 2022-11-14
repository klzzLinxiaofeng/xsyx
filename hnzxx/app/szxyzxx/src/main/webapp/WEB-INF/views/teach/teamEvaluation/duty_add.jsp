<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<title>班级体评价</title>
<link href="${pageContext.request.contextPath}/res/css/dygl/class_collective.css" rel="stylesheet">
<script type="text/javascript">
$(function(){
    $(".points-content").eq(0).show();
    $(".points-content").eq(2).show();
    $(".minutes-rated a").click(function(){
        $(".minutes-rated a").removeClass("see-rated");
        $(this).addClass("see-rated");
        var i=$(this).index();
    });
    $(".plus-minus-rated a").click(function(){
        var j=$(this).index();
        $(this).siblings().removeClass("current-rated");
        $(this).addClass("current-rated");
        $(this).parent().parent().children(".points-content").hide();
        $(this).parent().parent().children(".points-content").eq(j).show();
    });
});
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="班级体评价" name="title" />
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
                                <a href="javascript:void(0)" onclick="toDuty();" class="a3" style="padding: 0 20px;">值日查看</a>
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
<%-- 								<c:if test="${gradeList.size() > 1 }"> --%>
									<div class="select_div">
										<span>年级：</span>
										<select id="grade" name="grade" onchange="search()" class="chzn-select" style="width:150px;">
											<c:forEach items="${gradeList}" var="grade">
												<option value="${grade.id }">${grade.name }</option>
											</c:forEach>
										</select>
									</div>
<%-- 								</c:if>   --%>
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
	$(function(){
		search();
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
	
	function search(){
		var loader = new loadLayer();
		var year = "${year}";
		var termCode = "${termCode}";
		var checkDate = "${checkDate}";
		var gradeId = null;
		if( ${gradeList.size() > 1}){
			gradeId = $("#grade").val();
		}else{
			gradeId = ${gradeList[0].id};
		}
		if ("" === gradeId || "undefind" === gradeId) {
			$.error("请选择年级");
			return false;
		}
		var $requestDate = {};
		$requestDate.gradeId = gradeId;
		$requestDate.termCode = termCode;
		$requestDate.checkDate = checkDate;
		$requestDate.year = year;
		loader.show();
		var url  = "${pageContext.request.contextPath}/teach/teamEvaluation/evaList?isOnDuty=true";
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
				}
				loader.close();
			})
			
		}else {
			$("#add_eva").html("");
		}
	}
	
	function search2(){
		var year = "${year}";
		var termCode = "${termCode}";
		var checkDate = "${checkDate}";
		var gradeId = null;
		if( ${gradeList.size() > 1}){
			gradeId = $("#grade").val();
		}else{
			gradeId = ${gradeList[0].id};
		}
		if ("" === gradeId || "undefind" === gradeId) {
			$.error("请选择年级");
			return false;
		}
		var $requestDate = {};
		$requestDate.gradeId = gradeId;
		$requestDate.termCode = termCode;
		$requestDate.checkDate = checkDate;
		$requestDate.year = year;
		var url  = "${pageContext.request.contextPath}/teach/teamEvaluation/dx_evaList?isOnDuty=true";
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
		var year = "${year}";
		var termCode = "${termCode}";
		var checkDate = "${checkDate}";
		var gradeId = null;
		if( ${gradeList.size() > 1}){
			gradeId = $("#grade").val();
		}else{
			gradeId = ${gradeList[0].id};
		}
		if ("" === gradeId || "undefind" === gradeId) {
			$.error("请选择年级");
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
	
	function list(id,name){
		var loader = new loadLayer();
		var year = "${year}";
		var termCode = "${termCode}";
		var checkDate = "${checkDate}";
		var gradeId = null;
		if( ${gradeList.size() > 1}){
			gradeId = $("#grade").val();
		}else{
			gradeId = ${gradeList[0].id};
		}
		if ("" === gradeId || "undefind" === gradeId) {
			$.error("请选择年级");
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
		window.location.href="${pageContext.request.contextPath}/teach/teamEvaluation/duty/index?dm=${param.dm}&isOnDuty=${isOnDuty}";
	}
	</script>
</html>