<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/res/css/extra/zuoweibiao.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/res/plugin/falgun/js/jquery.easyui.min.js"></script>
<title></title>
</head>
<style>
.install {
	height: 50px;
	float: left;
	width: 100%;
}

.install button i {
	margin-right: 8px;
}

.fa-pencil {
	margin-left: 8px;
}

.install p {
	font-size: 12px;
	color: #333;
	line-height: 30px;
	text-align: center;
	margin-top: 10px;
}

.setting {
	background: #f0f0f0;
	margin-top: 20px;
}

.nj a.on {
	background: #0d7bd5;
}

.bj a.on {
	background: #0d7bd5;
}

.add {
	float: right;
	background: #47b9f4;
	width: 60px;
	line-height: 30px;
	color: #fff;
	text-align: center;
	position: relative;
	top: -40px;
	right: 16px;
	margin: 0 0 -20px 0px;
	border: 0px;
}

.js a.on {
	background: #0d7bd5;
}

.position {
	width: 100%;
	padding-bottom: 30px;
}

.left {
	min-height: 433px;
	background: #fff;
	border: 1px #d9dfe7 solid;
	margin: 0px 329px 0 15px;
	display: block;
}

.left span {
	text-align: center;
}

.left span p {
	background: #428bca;
	font-size: 12px;
	width: 26%;
	border-radius: 5px;
	-moz-border-radius: 5px;
	-webkit-border-radius: 5px;
	line-height: 45px;
	color: #fff;
	margin: auto;
	margin-top: 20px;
}

.left ul {
	margin: 15px 0 0 0;
	width: 100%;
}

.left ul li {
	text-align: center;
	margin-bottom:10px;
	height: 30px;
	line-height: 35px;
}

.left ul li p {
	max-width: 100px;
}

.left ul li a {
	background: #ccc;
	line-height: 30px;
	width: 9.6%;
	font-size: 12px;
	color: #fff;
	display: inline-block;
	margin-left: 2%;
	height: 30px;
	overflow: hidden;
}

.left ul li a {
	*display: inline;
}

.male {
	background: #47b9f4;
}

.female {
	background: #f64cae;
}

.you {
	min-height: 433px;
	width: 288px;
	background: #fff;
	border: 1px #d9dfe7 solid;
	float: right;
	margin-right: 20px;
}

.you span {
	height: 30px;
	background: #f5f5f5;
	float: left;
	width: 100%;
	border-bottom: 1px #d4d4d4 solid;
}

.you span p {
	color: #6d6e6f;
	font-size: 12px;
	margin: 0 0 0 10px;
	line-height: 30px;
	float: left;
}

.you span p.surplus {
	float: right;
	margin-right: 10px;
}

strong.number {
	color: #f44646;
	margin: 0 5px 0 5px;
}

.right {
	height: 400px;
	overflow-x: hidden;
	width: 100%;
}

.right ul {
	margin: 0;
	padding: 0;
}

.right p {
	color: #b3defa;
	display: block;
	overflow: hidden;
	text-overflow: ellipsis;
	margin: 0;
}

.item {
	height: 40px;
	color: #fff;
	font-size: 12px;
	text-align: center;
	display: block;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}

.item p {
	/* 	width:77px; */
	
}

.assigned {
	overflow: hidden;
	height: 30px;
	margin-top: -3px;
	padding-top: 3px;
	text-align: center;
	text-overflow: ellipsis;
}

.right ul li {
	margin: 10px 0 0 10px;
	padding-top: 5px;
	width: 80px;
	height: 45px;
}

.right .item {
	float: left;
}

.white {
	border: 0;
}

.set {
	max-width: 100px;
}

.left ul li .d {
	margin-left: 4%;
}

.left ul li .d p {
	width: 77px;
}

.left ul li span .l {
	margin: 0 1px 0 0;
	border-radius: 5px 0 0 5px;
}

.left ul li span .r {
	margin: 0;
	border-radius: 0 5px 5px 0;
}

.choice ul li {
	min-height: 50px;
	display: block;
	clear: both;
	float: left;
	width: 100%;
}

.choice {
	float: left;
	width: 100%;
}

.position {
	float: left;
}

.bj {
	min-height: 50px;
}

.item img {
	border-radius: 50%;
	width: 30px;
	margin-top: 4px;
}

.item p {
	margin: 0 8px 0 0;
	color: #fff;
	float: right;
}

#student li img {
	width: 30%;
	margin: 5px 0px 0 5px;
	float: left;
}

#student li p {
	margin: 7px 0 0 0;
	float: right;
width: 50px;
text-align: center;
}

#seat li a li img {
	width: 20px;
	float: left;
	margin: 5px 0 0 5px;
}

#seat li a li p {
	float: left;
	margin: -2px 0 0 0;
	display: block;
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
	width: 55px;
}
.choice{
	min-height:45px;background: #f0f0f0;border-bottom: 1px #ccc solid;
}
#team_chzn{
	margin: 10px;
}
.cishu{
	height:45px;background: #fff;
}
.cishu p{
	color:#333;line-height: 45px;font-size: 14px;font-family: ????????????;margin: 0;
}
.shu{
	color: #00a0e9;
font-family: '????????????';
font-size: 18px;
font-weight: bold;
margin-right: 5px;
}
.zp{
	float:left;
	margin:15px 18px 0 18px;
}
</style>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<!-- <div class="widget-head">
						<h3>?????????</h3>
					</div> -->
					<div class="setting">
						<div>
							<select id="team" onchange="search();" style="width:120px;"></select>
						</div>
						<div class="clear"></div>
						<div style="background: #fff;
width: 100%;
float: left;
margin-top: 20px;">
						<div class="install">
							<p id="className"></p>
						</div>
						<div class="position">
							<div class="left" style="width: 840px;margin: 0 auto;">
							
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
		$.BbxRoleTeamAccountSelector({
			   "selector" : "#team",
			   "condition" : {roleType:"SUBJECT_TEACHER"},
			   "selectedVal" : "",
			   "afterHandler" : function() {
				   var teamId = $("#team").find("option:selected").val();
				   var teamName = $("#team").find("option:selected").html();
				   $("#className").html(teamName+"????????????");
				   var val = {
						   teamId : teamId
				   };
				   //??????????????????
				   $(".left").load("${ctp}/clazz/seatChart/getDetail",val,function(){
					   var $request = {
							   teamId:teamId,
							   seatId:$("#seatId").val()
					   };
					   $(".you").load("${ctp}/clazz/seatChart/loadStudentDoNotHasSeat",$request);
				   });
				}	
		   });
	});
	
	function search(){
		var teamId = $("#team").val();
		var teamName = $("#team").find("option:selected").html()
		$("#className").html(teamName+"????????????");
		   var val = {
				   teamId : teamId
		   };
		   //??????????????????
		   $(".left").load("${ctp}/clazz/seatChart/getDetail",val,function(){
			   var $request = {
					   teamId:teamId,
					   seatId:$("#seatId").val()
			   };
			   $(".you").load("${ctp}/clazz/seatChart/loadStudentDoNotHasSeat",$request);
		   });
	}
	
	function easyDraggable(){
		$('.male,.female').draggable({
	        revert:true,
	    });
	    $('.left .drop').droppable({
	        onDrop:function(e,source){
	        	var aa=$(this).find('li').attr('class');
				if(aa=='male item assigned'||aa=='female item assigned'){
					$("#number").html($("#student li").length)
					$.alert('????????????');
	            } else {
	                var c = $(source).addClass('assigned');
	                $(this).empty().append(c);
	                c.draggable({
	                    revert:true
	                });
	                $("#number").html($("#student li").length);
	            }
	        }
	    });
	    $('.right').droppable({
	        onDrop:function(r,source){
	        	
	        	//alert($("#student div").length+1)
	            if ($(source).hasClass('set')){
	                $(this).children("ul").append(source);
	                $("#number").html($("#student li").length);
	            } else {
	                var c = $(source).addClass('set');
	                $(this).children("ul").append(c);
	                c.draggable({
	                    revert:true
	                });
	                $("#number").html($("#student li").length);
	            }
	        }
	    });
	}
	
	//????????????
	function setSeatChartSize(){
		var seatId = $("#seatId").val();
		$.confirm("???????????????????????????????????????????????????", function() {
			$.initWinOnTopFromLeft('????????????', '${pageContext.request.contextPath}/clazz/seatChart/setSeatSize?seatId='+seatId, '600', '300');
		});
	}
	
	//???????????????
	 function zd(){
       var total = $(".right div").length;
       var li = $(".left .li").length;
       var a = $(".left li:eq(0) a").length;
       var m = Math.ceil(total/a);
       var x = 0;
       var y = 0;
       var div = 0;
      $.each($("#student li"), function(i, value) {
       x = Math.floor(i/a);
       y = (i)%a;
       div = $(".left .li:eq("+x+") a:eq("+y+") li").length;
       if(div=='0'){
       	 $(".left .li:eq("+x+") a:eq("+y+")").append(value);
            $(".left .li:eq("+x+") a:eq("+y+") li").addClass('assigned');
            $("#number").html($("#student li").length);
       }
       });
      easyDraggable();
   }
	//????????????
	 function q(){
			$.each($(".li a li"), function(i, value) {
				$("#student").append(value);
				$("#number").html($("#student li").length);
		    });
			easyDraggable();
		}
	
	//????????????
		function save(){
			var jsonArray = [];
			var seatId = $("#seatId").val();
			var loader = new loadLayer();
			$(".li a li").each(function(){
				var curJson = {};
				curJson.studentId = $(this).attr("data-obj-id");
				curJson.position = $(this).parent().attr("id");
				jsonArray.push(curJson);
			});
			var result = {};
			result.items = jsonArray;
			var seatItems = JSON.stringify(result);
			var $request = {};
			$request.seatId = seatId;
			$request.seats = seatItems;
			//alert(JSON.stringify($request));
			loader.show();
			var url = "${pageContext.request.contextPath}/clazz/seatChart/saveSeatItem";
			$.post(url, $request, function(data, status) {
				if("success" === status) {
					$.success('????????????');
					loader.close();
				}else{
					$.error("????????????");
				}
			});
		}
</script>
</html>