<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<div>
<style>
table{border-collapse: inherit !important;}
</style>
	<div class="biao-title widget-container white" style="border:1px solid #fff">
		<span>调课次数：<em class="nan">${messageVoList.size() }</em>
			次
		</span>
	</div>
	<!--课程表-->
	<div class="ke-table">

		<table width="100%" border="0" cellspacing="0" cellpadding="0" id="data-table">
			<thead>
				<tr>
					<th style="width: 9%" hidden></th>
					<c:forEach items="${xqs}" var="xq">
						<th data-xqdm="${xq}"><jcgc:cache tableCode="XY-JY-XINGQI"
								value="${xq}"></jcgc:cache>
						</th>
					</c:forEach>
				</tr>
			</thead>
			<tbody>

				<!-- 上午 -->
				<c:forEach items="${morningLessons}" var="morning"
					varStatus="morningStatus">
					<tr>
						<td data-kj="${morning}" hidden>${morningStatus.count}</td>
						<c:forEach items="${xqs}" var="xq">
							<td data-xq="${xq}"><span class="add" style="display: none;"></span></td>
						</c:forEach>
					</tr>
				</c:forEach>

			

				<c:forEach items="${afternoonLessons}" var="afternoon"
					varStatus="status">
					<tr>
						<td data-kj="${afternoon}" hidden>
							 ${afternoon - (sca:getMorningMaxLessonCount() - fn:length(morningLessons))}
						</td>
						<c:forEach items="${xqs}" var="xq">
							<td data-xq="${xq}"><span class="add" style="display: none;"></span></td>
						</c:forEach>
					</tr>
				</c:forEach>

			
				<c:forEach items="${eveningLessons}" var="evening"
					varStatus="status">
					<tr>
						<td data-kj="${evening}" hidden>
					         ${evening - (sca:getAfternoonMaxLessonCount() - fn:length(afternoonLessons)) - (sca:getMorningMaxLessonCount() - fn:length(morningLessons))}
						</td>
						<c:forEach items="${xqs}" var="xq">
							<td data-xq="${xq}"><span class="add" style="display: none;"></span></td>
						</c:forEach>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<%-- <table width="100%" border="0" cellspacing="0" cellpadding="0" id="data-table">
			<thead>
				<tr>
				   <c:forEach items="${xqs}" var="xq">
						<th data-xqdm="${xq}">
							<jcgc:cache tableCode="XY-JY-XINGQI" value="${xq}"></jcgc:cache>
						</th>
				   </c:forEach>
				    <c:forEach items="${dayPlan}" var="day">
				    	<th data-xqdm="${day}">
							<jcgc:cache tableCode="XY-JY-XINGQI" value="${day}"></jcgc:cache>
						</th>
				    </c:forEach>
				</tr>
			</thead>
			<tbody>
			
						<tr>
						     <c:forEach items="${xqs}" var="xq">
							   <td data-xq="${xq}" style="padding:0">
								 <div hidden class="add"></div>
	   						   </td>
						     </c:forEach>
						     
						</tr>
			       
			   
				<c:forEach items="${sllist}" var="list" varStatus="i">
					<tr data-obj-id="${i.index}">
						<c:forEach items="${dayPlan}" var="day" varStatus="j">
						 <td>
						 <div style="min-height:50px;">
						 	<c:forEach items="${list }" var="lesson">
								<c:if test="${lesson.lesson == i.index and lesson.dayOfWeek == day}">
								${lesson.subjectName }<em>${lesson.teacherName }</em>
								</c:if>
							</c:forEach>
						</div>
						 </td>
						</c:forEach>
					</tr>
				</c:forEach>
				
			</tbody>
		</table> --%>
	</div>
	<div class="biao-ul">
		<ul>
		
			<c:forEach items="${messageVoList }" var="message" >
			<li id="${message.id}_li">
				<div class="item">
					<div class="item-left left">
						<span class="sign_green ban-ke">调课通知</span>
					</div>

					<div class="item-right">
						<p class="textb">${message.content }</p>
					</div>
				</div>
				<div class="item-bottom">
					<span>${message.visualTime }</span>
					<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">
					 <a href="javascript:void(0)" onclick="deleteObj(this, '${message.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
					 </c:if>
					 <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">
                     <a href="javascript:void(0)" onclick="loadEditPage('${message.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑</a>
                     </c:if>
				</div>
			</li>
			</c:forEach>
			
		
			<!-- <li>
				<div class="item">
					<div class="item-left left">
						<span class="sign_green ban-ke">调课通知</span>
					</div>

					<div class="item-right">
						<p class="textb">星期一上午语文课改成数学课</p>
					</div>
				</div>
				<div class="item-bottom">
					<span>三年级二班 2小时前</span><a href="./icons/trash-o"><i
						class="fa fa-trash-o"></i> 删除</a><a href="./icons/pencil-square-o"><i
						class="fa fa-pencil-square-o"></i>编辑</a>
				</div>
			</li>

			<li>
				<div class="item">
					<div class="item-left left">
						<span class="sign_green ban-ke">调课通知</span>
					</div>

					<div class="item-right">
						<p class="textb">星期一上午语文课改成数学课</p>
					</div>
				</div>
				<div class="item-bottom">
					<span>三年级二班 2小时前</span><a href="./icons/trash-o"><i
						class="fa fa-trash-o"></i> 删除</a><a href="./icons/pencil-square-o"><i
						class="fa fa-pencil-square-o"></i>编辑</a>
				</div>
			</li>
 -->
		</ul>
	</div>
	<style>
		td{position: relative;};
	</style>
	<script>
		$(function(){
			var loader = new loadLayer("加载课表中");
// 			loader.show();
			var $requestData = {};
			$requestData.syllabusId = "${item.id}";
			$requestData.schoolId = "${item.schoolId}";
			$requestData.teamId = "${item.teamId}";
			$.get("${ctp}/teach/syllabus/rkap/list/json", $requestData, function(data, status) {
				if("success" === status) {
					data = eval("(" + data + ")");
					$.each(data, function(index, value) {
						/* var kbDm = value.lessonId;
						var xq = value.dayOfWeek;
						var kbCell = $("#data-table tbody tr").find("td[data-xq='" + xq + "']");
						var jiaosMc = value.teacherName;
						jiaosMc = jiaosMc != null ? jiaosMc : "";
						kbCell.find(".add").before("<table style='border='0' cellspacing='0' cellpadding='0' '><tr><td><div style='min-height:50px;'>"+value.subjectName+"<em>"+jiaosMc+"</em></div></table></tr></td>"); */
						var kj = value.lesson;
						var xq = value.dayOfWeek;
						var kbDm = value.lessonId;
						var kbCell = $("#data-table tbody tr").find("td[data-kj='" + kj + "']").parent().find("td[data-xq='" + xq + "']");
						var jiaosMc = value.teacherName;
						jiaosMc = jiaosMc != null ? jiaosMc : "";
						logol = "<img style='position: absolute; width: 22px; top: 0px; left: 10px;' src='${ctp}/res/images/bbx/bp/syllabus/tips.png'>";
						
						var subjectName =  value.subjectName;
						if(subjectName == null){
							subjectName = ""
						}
							
						if ( value.type == 1 ) { // 设置行政课信息
							kbCell.html("");
							if(subjectName != ""){
								kbCell.html("<div style='min-height:50px;'>" + value.subjectName  +
									"<em>" + jiaosMc + "</em>" + "</div>");
							}
							
							
						} else if (value.type == 2) { // 设置走班课信息
							kbCell.html("");
							if(subjectName != ""){
								kbCell.html("<div style='min-height:50px;'>" + value.subjectName  +
									"<em>" + jiaosMc + "</em>" + logol + "</div>");
							}
							
						}
					});
					
				}
				
				loader.close();
				
			});
			
			
			var date = new Date().getDay();
			var i=date;
			$(".ke-table table th").eq(i).addClass("on");
			/* $(".ke-table table td").each(function(){
				$(this).eq(i).addClass("on");
			}) */
			$(".ke-table table tr").each(function(){
				$(this).children().eq(i).addClass("on");
			})
		});
		
		
		
		
		
	</script>