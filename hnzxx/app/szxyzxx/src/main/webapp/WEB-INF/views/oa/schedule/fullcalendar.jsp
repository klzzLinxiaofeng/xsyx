<%@ page language="java"
	import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link
	href="${pageContext.request.contextPath}/res/plugin/falgun/css/fullcalendar.css"
	rel="stylesheet">
<script
	src="${pageContext.request.contextPath}/res/plugin/falgun/js/fullcalendar.js"></script>
<title>日历</title>
<style>
.createAndEdit {
	position: absolute;
	width: 350px;
	height: 265px;
	background-color: #fff;
	border: 1px solid #ccc;
	z-index: 10001;
	
}
.createAndEdit .creat_title{
	height:32px;
	background-color:#D1EDFF;
	line-height:32px;
	padding-left:15px;
	color:#777;
	margin-bottom:10px;
}
.myerror {
	color: red ;
	width: 22%;
	display: inline-block;
	padding-left: 10px;
}
.form-horizontal .control-label {
	width: 75px;
}

.form-horizontal .controls {
	margin-left: 95px;
}

.row-fluid .span1 {
	width: 180px;
}
.solarday,.holiday{
	margin-right:5px;
}
.fc-grid .fc-day-number{
	padding-right:10px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon" />
			<jsp:param value="日历" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-container">
						<div id='calendar' style="position: relative"></div>
						
						<div style="display: none;" class="createAndEdit">
						
							<form class="form-horizontal tan_form" id="schedule_form"
								action="javascript:void(0);">
								<div class="creat_title">日程</div>
								<div class="control-group">
									<label class="control-label"> <span style="color:red">*</span>正文内容： </label>
									<div class="controls">
										<textarea id="content" name="content" placeholder="请输入今天要做的事件，例如：今天开会" 
											class="span1 {required : true,maxlength:20}">${schedule.content}</textarea>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"> <span style="color:red">*</span>开始时间： </label>
									<div class="controls">
										<input type="text" id="planStartTime" name="planStartTime"
											class="span1 {required : true}"
											value='<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${schedule.planStartTime}"></fmt:formatDate>'
											 onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'currenDate\')}',maxDate:'#F{$dp.$D(\'planFinishTime\')}'});"
											>
									</div>
								</div>
								<div class="control-group">
									<label class="control-label"> <span style="color:red">*</span>结束时间： </label>
									<div class="controls">
										<input type="text" id="planFinishTime" name="planFinishTime"
											class="span1 {required : true}"
											value='<fmt:formatDate pattern="yyyy-MM-dd  HH:mm:ss" value="${schedule.planFinishTime}"></fmt:formatDate>'
											 onclick="WdatePicker({minDate:'#F{$dp.$D(\'planStartTime\')}',maxDate:'#F{$dp.$D(\'planStartTime\',{d:5})}',dateFmt:'yyyy-MM-dd HH:mm:ss'});"
											>
									</div>
								</div>
								<div style="text-align: center">
									<button class="btn btn-success" onclick="submitData()">保存</button>
									<button class="btn" onclick="cancel()">取消</button>
									<button class="btn btn-danger" id="del" style="display: none;" onclick="delData()">删除</button>
								</div>
								<input type="hidden" id="id">
								<input type="hidden" id="currenDate" 
								value='<fmt:formatDate pattern="yyyy-MM-dd" value="${schedule.planFinishTime}"></fmt:formatDate>'/>
							</form>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script type='text/javascript'>
		var checker;
		
		$(function() {
			InItFullCalendar();
			checker = initValidator();
		});
		
		//数据源初始化
		function dataInIt(){
			var InItUrl = "${ctp}/oa/schedule/dataInIt";
			return InItUrl;
		}
		
		//弹出新建的时候给两个文本框默认赋值
		function defaultValueToCreate(date){
			var  clickBeginDate = $.fullCalendar.formatDate(date, "yyyy-MM-dd 00:00:00");
			var  clickEndDate = $.fullCalendar.formatDate(date, "yyyy-MM-dd 23:59:59");
			$("#currenDate").val(clickBeginDate);
			$("#planStartTime").val(clickBeginDate);
			$("#planFinishTime").val(clickEndDate);
		}
		
		//新建一个日程
		function createCal(date, allDay, jsEvent, view) {
			emptyContent();
			if(checkCalLimit(date)){
				$(".createAndEdit").hide();
				$(".fc-border-separate td").css("background-color","#fff");
				$.alert("每日日程最多为5条");
				return;
			}
			$("label.myerror").remove();
			$("#del").hide();
			 defaultValueToCreate(date);
			showPageByMouth(jsEvent);
		}
		
		//检查每日日程的限制条数
		function checkCalLimit(date){
			var myBoolean = false;
			var  begin = $.fullCalendar.formatDate(date, "yyyy-MM-dd 00:00:00");
			var  end = $.fullCalendar.formatDate(date, "yyyy-MM-dd 23:59:59");
			var url = "${ctp}/oa/schedule/checkCalLimit?begin="+begin+"&end="+end;
			//将Ajax进行同步，否则一直return FALSE 
			$.ajaxSetup({
			   async: false
			});
			$.post(url,null,function(returnData,status){
				if(returnData == "beyond"){
					myBoolean = true;
				}else{
					myBoolean = false;
				}
			});
			return myBoolean;
		}
		
		//根据鼠标确定页面位置
		function showPageByMouth(jsEvent){
			var x = jsEvent.clientX-18;
			var y = jsEvent.clientY-56;
			var clickIntLeft = clickLeft(jsEvent);
			var clickIntDown = clickDown(jsEvent);
			
				$(".createAndEdit").css({
					"top" : y,
					"left" : x
				});
			
			if(x > clickIntLeft){
				$(".createAndEdit").css({
					"left" : clickIntLeft
				});
			}
			
			if(y > clickIntDown){
				$(".createAndEdit").css({
					"top" : clickIntDown,
				});
			}
			if($(".createAndEdit").is(":hidden")){
				$(this).css('background-color', 'lightgreen');
				$(".createAndEdit").show();		
			}else{
				$(".fc-border-separate td").css('background-color', 'white');
				$(".createAndEdit").hide();	
			}
			return false;
			
		}
		
		function clickLeft(jsEvent){
			var max_width = $("body").width()-20;
			$(window).resize( function(){
				max_width = $("body").width();
			} );
			var clickInt = max_width - 395;
			return clickInt;
		}
		
		function clickDown(jsEvent){
			var max_height = $("body").height()-20;
			$(window).resize( function(){
				max_height = $("body").height();
			} );
			var clickInt = max_height - 295;
			return clickInt;
		}

		//修改一个日程
		function modifyCal(calEvent, jsEvent, view) {
			$("label.myerror").remove();
			$(".myerror").css("color","#111")
			var url = "${ctp}/oa/schedule/getCalDataById?id="+calEvent.id;
			$.post(url,null,function(returnData,status){
				addContent(calEvent, jsEvent,returnData);
				$("#del").show();
			},'json');
			showPageByMouth(jsEvent);
		}
		
		//清空文本内容
		function emptyContent(){
			$("#id").val("");
			$("#content").val("");
			$("#planStartTime").val("");
			$("#planFinishTime").val("");
			$("#currenDate").val("");
		}
		
		//给文本框赋值
		function addContent(calEvent, jsEvent,returnData){
			$("#id").val(calEvent.id);
			$("#content").val(returnData.content);
			$("#planStartTime").val(returnData.planStartTime);
			$("#planFinishTime").val(returnData.planFinishTime);
			$("#currenDate").val(returnData.planStartTime);
		}
		
		//设置日历的高度  ：用户屏幕高度
		function calHight() {
			var screenHight = $(window).height();
			return screenHight - 135;
		}

		//数据校验
		function initValidator() {
			return $("#schedule_form").validate({
				errorClass : "myerror",
				rules : {

				},
				messages : {

				}
			});
		}
		
		//新建、修改 保存
		function submitData(){
			if (checker.form()) {
				var loader = new loadLayer();
				var $id = $("#id").val();
				var $requestData = formData2JSONObj("#schedule_form");
				var url = "${ctp}/oa/schedule/creator";
				if ("" != $id) {
					$requestData._method = "put";
					url = "${ctp}/oa/schedule/" + $id;
				}
				loader.show();
				$.post(url, $requestData, function(data, status) {
					if("success" === status) {
						$.success('操作成功');
						data = eval("(" + data + ")");
						if("success" === data.info) {
							if(parent.core_iframe != null) {
	 							parent.core_iframe.window.location.reload();
	 							$(".createAndEdit").hide();
	 						} else {
	 							parent.window.location.reload();
	 						}
						}else {
							$.error("操作失败");
						}
					}else{
						$.error("操作失败");
					}
					loader.close();
				});
			}
		}
		
		//删除事件
		function delData(){
			var id = $("#id").val();
			var loader = new loadLayer();
				loader.show();
				$.post("${ctp}/oa/schedule/" + id, {"_method" : "delete"}, function(data, status) {
					if ("success" === status) {
						if ("success" === data) {
							$.success("删除成功");
							if(parent.core_iframe != null) {
	 							parent.core_iframe.window.location.reload();
	 							$(".createAndEdit").hide();
	 						} else {
	 							parent.window.location.reload();
	 						}
						} else if ("fail" === data) {
							$.error("删除失败，系统异常", 1);
						}
					}
					loader.close();
				});
		}

		function InItFullCalendar() {
			$('#calendar').fullCalendar({

				//头部
				header : {
					left : 'prev,next today',
					center : 'title',
// 					right : 'month,agendaWeek,agendaDay'   //暂时不提供
					right : 'month'
				},
				
				firstDay : 1,

				//是否可移动、改变大小
				editable : false,

				//事件源  可放 路径,数组,functions
				events : dataInIt(),
				
				//点击某个事件
				eventClick : function(calEvent, jsEvent, view) {
					modifyCal(calEvent, jsEvent, view);
				},

				//点击新建事件
				dayClick : function(date, allDay, jsEvent, view) {
					 $(".fc-border-separate td").css('background-color', 'white');
 					$(this).css('background-color', 'lightgreen'); 
					createCal(date, allDay, jsEvent, view);
				},

				//高度
				contentHeight : calHight(),
				
				monthNames: ['一','二','三','四','五','六','七','八','九','十','十一','十二'],
				
				monthNamesShort: ['一','二','三','四','五','六','七','八','九','十','十一','十二'],
				
				dayNames: ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
				
				dayNamesShort: ['周 日','周 一','周 二','周 三','周 四','周 五','周 六'],
				            
				allDayDefault:false,
				
				axisFormat : 'HH:mm:ss',
				
			    titleFormat: {
			          month: 'yyyy年 MMMM月',
			          week: "yyyy年MM月   d{'&#8212;' d}日",
			          day: 'yyyy年MM月dd日'
			        },
			        
			    columnFormat: {
			          month: 'ddd',
			          week: 'ddd M/d',
			          day: 'dddd M/d'
			        },
			        
			    timeFormat: {
			          '': 'HH:mm '
			        }
			});
		}
		
		//取消按钮事件
		function cancel(){
			$("#content").val("");
			$(".createAndEdit").hide();
		}
		
		$(function(){
			$(".createAndEdit").click(function (e) {
				e.stopPropagation();//阻止事件向上冒泡
				});
			$(".fc-content").click(function (e) {
				e.stopPropagation();//阻止事件向上冒泡
				});
			
			$(document).click(function(){
				$(".fc-border-separate td").css('background-color', 'white');
				$(".createAndEdit").hide();
				
				});
		})
	</script>
</body>
</html>