<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<style>
.ke-table table td:nth-child(even){background:#f7fbfe}
.ke-table table tr td.on{background:#fff}
.ke-table table tr th.on{ background:#787878;border-top:none;}
.ke-table table th, .ke-table table td{ border:1px solid #e7e7e7; color:#999; width:14.2%;font-weight:bold;position: relative;}
.ke-table table tr td.on{color: #999;font-weight: bold;}
.ke-table table td:nth-child(odd){background:#fff}
.ke-table table td em,.ke-table table td.on em{ color:#c8cccf; font-weight: bold;font-size:13px;}
.img_img{ position: absolute;width:22px;top: 0;left: 10px;}
    table#data-table{border-collapse: initial ;}
   #data-table tbody tr td .dispaly_two:nth-child(2){display: none;}
</style>
<div>
	<%-- <div class="biao-title widget-container white" style="border:1px solid #fff">
		<span>调课次数：<em class="nan">${messageVoList.size() }</em>
			次
		</span>
	</div> --%>
	<!--课程表-->
	<div class="ke-table">

		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="data-table">
			<thead>
				<tr>
					<th style="width: 2% ;background:#fff !important"></th>
					<c:forEach items="${dayOfWeek}" var="day">
						<th data-xqdm="${day}" class="on">
							<jcgc:cache tableCode="XY-JY-XINGQI" value="${day}"></jcgc:cache>
						</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${lessons}" var="lesson" varStatus="status">
					<tr>
						<td data-kj="${lesson}" style="width:2% !important ;vertical-align: middle;" class="sum_title">${status.count}</td>
						<c:forEach items="${dayOfWeek}" var="day">
							<td data-xq="${day}"><span class="add" style="display: none;"></span></td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<style>
  .row-fluid [class*="span"] .left_span{ display: none ;}
   .ke-table table td:hover .left_span{display:block }
   .left_span span:hover{color:#da9f0a;cursor: pointer;font-size:13px;}
   .left_span span{ display: inline-block;padding:0 6px;font-size:13px;}
</style>
	<script>
		$(function(){
			//alert(1);
			var loader = new loadLayer("加载课表中");
// 			loader.show();
			var studentId = $("#studentId").attr("data-id");
			var $requestData = {"studentId":studentId};
			//$requestData.syllabusId = "${item.id}";
			ajax_l();
			function ajax_l(){
			$.get("${ctp}/bbx/StudentSyllabus/rkap/list/json", $requestData, function(data, status) {
				var html = '';
				if("success" === status) {
					data = eval("(" + data + ")");
					$.each(data, function(index, value) {
						var kj = value.lesson;
						var xq = value.dayOfWeek;
						var kbDm = value.syllabusLessonId;
						var kbCell = $("#data-table tbody tr").find("td[data-kj='" + kj + "']").parent().find("td[data-xq='" + xq + "']");
						//var jiaosMc = value.teacherName;
						//jiaosMc = jiaosMc != null ? jiaosMc : "";
						var subjectName = value.subjectName;
						var roomName = value.roomName;
						var type = value.type;
						var logol ="";
						if(type == 2){
							if(subjectName != "" && subjectName != null){
								logol = "<img class='img_img' src='${ctp}/res/images/bbx/bp/syllabus/tips.png'>";
								kbCell.html("");
								kbCell.html("<div style='min-height:50px;font-size:15px;' >" + subjectName  +"<em>" + roomName + "</em>" 
									+ logol + "</div>"
									+"<div class='left_span'><span class='edit new_pj evaluate' data-kbdm='"+kbDm+"'  ><i class='icon-edit icon-1x'></i>评价</span>"
									+"<span class='edit new_pj ' data-kbdm='"+kbDm+"' ><i class='icon-edit icon-1x'></i>考勤</span></div></div>");
							}					
						} else if ( type == 1 ) {
							if(subjectName != "" && subjectName != null){
								kbCell.html("");
								kbCell.html("<div style='min-height:50px;font-size:15px;' >" + subjectName  +"<em>" + roomName + "</em>" 
								+ logol + "</div>");
							}					
						}
					});		
				}
			});
			}
			var date = new Date().getDay();
			var i=date;
			$(".ke-table table th").eq(i).addClass("on");
			$(".ke-table table tr").each(function(){
				$(this).children().eq(i).addClass("on");
			})
		});
		
	</script>