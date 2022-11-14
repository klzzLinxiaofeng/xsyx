<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<div class="select_top" style="margin-top:-2px;position:relative;">
<div class="s2">
	值日总人次：<span class="a2">${count }</span> 人次
</div>
<button id="lastWeek" class="btn btn-warning" style="position:absolute;top:10px;left:45%;">上一周</button>
</div>
						<div class="content-widgets" style="margin-top: 10px;">
							<div>
								<div id="teamDutyUser_list_content"></div>
								<div class="clear"></div>
							</div>
</div>