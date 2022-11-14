<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/res/css/extra/zuoweibiao.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery.easyui.min.js"></script>
<title></title>
</head>
<style>
.install{
    height: 50px;float:left;width:100%;
}
.install button i{
	margin-right:8px;
}
.fa-pencil{
	margin-left:8px;
}
.install p{
    font-size: 12px;color: #333;line-height: 30px;text-align: center;margin-top:10px;
}
.setting{
    background: #f0f0f0;padding-bottom:20px;
}
.nj a.on{
    background: #0d7bd5;
}
.bj a.on{
    background: #0d7bd5;
}
.add{
    float: right;background:#47b9f4;width: 60px;line-height:30px;color:#fff;text-align: center;position: relative;top:-40px;right:16px;margin: 0 0 -20px 0px;border:0px;
}
.js a.on{
    background: #0d7bd5;
}
.position{
    width: 100%;padding-bottom: 30px;
}
.left{
    min-height: 433px;background: #fff;border:1px #d9dfe7 solid;margin: 0px;display: block;
}
.left span{
    text-align: center;
}
#className{
	margin: 0;
text-align: center;
padding-top: 140px;
}
.left span p{
    background: #428bca;font-size: 12px;width:26%;border-radius: 5px;-moz-border-radius: 5px;-webkit-border-radius: 5px;line-height: 45px;color: #fff;margin: auto;margin-top: 20px;
}
.left ul{
    margin:15px 0 0 0;width:100%;
}
.left ul li{
    text-align: center;padding:0 0 10px 0;height:30px;line-height: 35px;
}
.left ul li p{
	max-width:100px;
}
.left ul li a{
    background:#ccc;line-height: 30px;width: 6.6%;border-radius: 5px;-moz-border-radius: 5px;-webkit-border-radius: 5px;font-size: 12px;color: #fff;display: inline-block;margin-left: 2%;height: 30px;overflow: hidden;
}
.left ul li a{
    *display:inline;
}
.male{
    background: #47b9f4;
}
.female{
    background: #f64cae;
}
.you{
     min-height: 433px;width: 280px;background: #fff;border:1px #d9dfe7 solid;float: right;margin-right: 20px;
}
.you span{
    height:30px;background:#f5f5f5;float: left;width: 100%;border-bottom: 1px #d4d4d4 solid;
}
.you span p{
    color: #6d6e6f;font-size: 12px;margin: 0 0 0 10px;line-height: 30px;float: left;
}
.you span p.surplus{
    float: right;margin-right: 10px;
}
strong.number{
    color: #f44646;margin: 0 5px 0 5px;
}
.right{
    height: 400px;overflow-x: hidden;width: 100%;
}
.right ul{
	margin:0;padding:0;
}
.right p{
    color:#b3defa;display: block;overflow: hidden;text-overflow: ellipsis;margin:0;
}
.item{
    height: 40px;border-radius: 5px;color: #fff;font-size: 12px;text-align: center;display: block;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;
}
.item p{
	width:77px;
}
.assigned{
        overflow: hidden;height: 30px;margin-top:-3px;padding-top: 3px;text-align: center;text-overflow: ellipsis;
}
.right ul li{
    margin: 10px 0 0 10px;padding-top: 5px;width:77px;height:45px;
}
.right .item{
	float:left;
}
.white{
	border:0;
}
.set{
	max-width:100px;
}
.left ul li .d{
    margin-left: 4%;
}
.left ul li .d p{
	width:77px;
}
.left ul li span .l{
    margin: 0 1px 0 0;border-radius: 5px 0 0 5px;
}
.left ul li span .r{
    margin: 0;border-radius: 0  5px 5px 0;
}
.choice ul li{
	min-height: 50px;display: block;clear: both;float: left;width: 100%;
}
.choice{
	float: left;width:100%;
}
.position{
	float:left;
}
.bj{
	min-height:50px;
}
</style>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="查看座位表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							座位表
						</h3>
					</div>
					<div class="setting">
					<input type="hidden" id="classId" name="classId" vlalue="" />
					<input type="hidden" id="classroomType" name="classroomType" vlalue="" />
					<input type="hidden" id="seatId" name="seatId" vlalue="" />
    <div class="choice">
        <ul>
            <li id="bj" class="bj">
            	<a data-obj-id='${stu.teamId}' href="javascript:void(0)";'>${stu.teamName}</a>
            </li>
            <li id="js" class="js"></li>
            
        </ul>
    </div>
<!--     <div class="install"> -->
<!--         </div> -->
        <p id="className"></p>
    </div>
    <div class="position">
        <div class="left">
            <span><p>讲台</p></span>

            <ul id="seat">
            </ul>

        </div>
        
    </div>
				</div>
			</div>
		</div>
		
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$(".bj").on("click", "a", function(){
			$(".bj a").removeClass("on");
			$(this).addClass("on");
			getClass($(this).attr("data-obj-id"));
		});
		$("#bj").find("a:first").addClass("on");
		var classId = $(".bj .on:first").attr("data-obj-id");
		$("#classId").val(classId);
		ajaxClassFunction(classId, function(){});
		$(".js").on("click", "a", function(){
			$(".js a").removeClass("on");
			$(this).addClass("on");
			getClassroom($(this).attr("data-obj-id"));
		});
	});
	function getClass(classId, $this){
		$("#classId").val(classId);
		ajaxClassFunction(classId, function() {
			$(".bj .on").removeClass("on");
			$($this).addClass("on");
		});
	}
	function ajaxClassFunction(classId){
		var url = "${pageContext.request.contextPath}/teach/seatChart/getAjaxClassRoomList";
		var aj = $.ajax({
		    url: url,
		    data:'teamId=' + classId,    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadClassRoom(data);
		     },    
		     error : function() {
		         $.alert("异常！");    
		     }    
		}); 
	}
	function loadClassRoom(data){
		$("#seatId").val("");
		$("#seat").html("");
		$("#js").html("");
		$("#className").html("");
		$("#number").html("0");
		$("#student").html("");
		$(".add").css("background-color","#47b9f4");
		$(".add").removeAttr('disabled');
		if(data.length > 0) {
			$("#js").html("");
			$.each(data,function(index,value){
				$("#js").append('<a data-obj-id="' + value.classroomType + '" href="javascript:void(0);">'+value.classroomTypeName+'<i class="fa fa-pencil"></i></a>')
			});
			$("#js").find("a:first").addClass("on");
			var classroomType = $(".js .on:first").attr("data-obj-id");
			$("#classroomType").val(classroomType);
			var teamId = $("#classId").val();
			ajaxSeatChartFunction(classroomType,teamId, function(){});
		}else{
			removeButtonClass();
		}
	}
	function getClassroom(classroomType,$this){
		$("#classroomType").val(classroomType);
		var teamId = $("#classId").val();
		ajaxSeatChartFunction(classroomType,teamId, function() {
			$(".js .on").removeClass("on");
			$($this).addClass("on");
		});
	}
	
	function ajaxSeatChartFunction(classroomType,teamId){
		var url = "${pageContext.request.contextPath}/teach/seatChart/getAjaxSeatChart";
		var aj = $.ajax({
		    url: url,
		    data:{"classroomType":classroomType,"teamId":teamId},    
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data) {
		    	loadSeat(data);
		     },    
		     error : function() {
		         $.alert("异常！");    
		     }    
		});
	}
	
	function loadSeat(data){
		$("#seat").html("");
		$("#student").html("");
		$(".add").css("background-color","#47b9f4");
		$(".add").removeAttr('disabled');
		if(data!=null){
			if(data.isMainClassroom){
				$("#className").html(data.classroomName);
			}else{
				$("#className").html("("+data.classroomName+")"+data.floorName+"楼"+ data.classroomStorey +"层"+ data.classroomPosition +"");
			}
			$("#seatId").val(data.id);
			if(data.seatType=="1"){
				loadSingleSeat(data);
			}
			if(data.seatType=="2"){
				loadDoubleSeat(data)
			}
			loadSeatChartItems(data.id);//加载座位表
		}
	}
	
	function loadSingleSeat(data){
		var row = data.row;
		var col = data.col;
		var $a = "";
		var $ul = "";
		for(var x=0;x<row;x++){
			var $a = "";
			for(var i=0;i<col;i++){
				$a+='<a id='+ (x*10+i) +' class="drop"></a>';
			}
			var $li = "<li class='li'>"+ $a +"</li>";
			$ul+=$li;
		}
		$("#seat").append($ul);
	}
	
	function loadDoubleSeat(data){
		var row = data.row;
		var col = data.col;
		var $span = "";
		var $ul = "";
		for(var x=0;x<row;x++){
			var $span = "";
			for(var i=0;i<(col/2);i++){
				$span+='<span class="d"><a id='+ (x*10+(2*i)) +' class="drop l"></a><a id='+ (x*10+(2*i+1)) +' class="drop r"></a></span>';
			}
			var $li = "<li class='li'>"+ $span +"</li>";
			$ul+=$li;
		}
		$("#seat").append($ul);
	}
	function loadSeatChartItems(seatId){
		var url = "${pageContext.request.contextPath}/teach/seatChart/getSeatItem";
		$.post(url, {"seatId":seatId}, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				//alert(JSON.stringify(data));
				$.each(data,function(index,value){
					var x = value.positionX;
					var y = value.positionY;
					if(value.studentSex=="1"||value.studentSex=="9"||value.studentSex===null){
						var studentNumber = null;
						if(value.studentNumber===null){
							var studentNumber = "暂无学号";
						}else{
							var studentNumber = value.studentNumber;
						}
						$(".left .li:eq("+x+") a:eq("+y+")").append("<li data-obj-id=" + value.studentId + " data-obj-sex=" + value.studentSex + " data-obj-name=" + value.studentName + " class='male item'>"+value.studentName+"<p>("+studentNumber+")</p></li>");
						$(".left .li:eq("+x+") a:eq("+y+") li").addClass('assigned');
					}
					if(value.studentSex=="2"){
						var studentNumber = null;
						if(value.studentNumber===null){
							var studentNumber = "暂无学号";
						}else{
							var studentNumber = value.studentNumber;
						}
						$(".left .li:eq("+x+") a:eq("+y+")").append("<li data-obj-id=" + value.studentId + " data-obj-sex=" + value.studentSex + " data-obj-name=" + value.studentName + " class='female item'>"+value.studentName+"<p>("+studentNumber+")</p></li>");
						$(".left .li:eq("+x+") a:eq("+y+") li").addClass('assigned');
					}
				});
			}else{
				$.error("操作失败");
			}
		});
	}
</script>
</html>