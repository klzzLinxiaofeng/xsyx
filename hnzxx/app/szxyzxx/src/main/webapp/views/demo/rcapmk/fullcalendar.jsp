<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/fullcalendar.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/fullcalendar.min.js"></script>
<title>日历</title>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="日历" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
				<div class="span12">
					<div class="content-widgets white">
						<div class="widget-head orange">
							<h3><i class=" icon-calendar"></i>Calendar</h3>
						</div>
						<div class="ribbon-wrapper-green">
							<div class="ribbon-green">
								Events
							</div>
						</div>
						<div class="widget-container">
							<div id='calendar'>
							</div>
						</div>
					</div>
				</div>
			</div>
	</div>
	<script type='text/javascript'>
	/* $(document).ready(function () {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: ''
        },
        monthNames:['一月','二月', '三月', '四月', '五月', '六月', '七月',
                    '八月', '九月', '十月', '十一月', '十二月'],
        buttonText: {
            prev: 'Prev',
            next: 'Next',
            today: 'Today',
            month: 'Month',
            week: 'Week',
            day: 'Day'
        },
        dayClick: function(date, allDay, jsEvent, view) {

            if (allDay) {
                alert('Clicked on the entire day: ' + date);
            }else{
                alert('Clicked on the slot: ' + date);
            }

            alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);

            alert('Current view: ' + view.name);

            // change the day's background color just for fun
            $(this).css('background-color', 'red');

        },
        editable: true,
        events: [{
            title: 'All Day Event',
            start: new Date(y, m, 1)
        }, {
            title: 'Long Event',
            start: new Date(y, m, d - 5),
            end: new Date(y, m, d - 2)
        }, {
            id: 999,
            title: 'Repeating Event',
            start: new Date(y, m, d - 3, 16, 0),
            allDay: false
        }, {
            id: 999,
            title: 'Repeating Event',
            start: new Date(y, m, d + 4, 16, 0),
            allDay: false
        }, {
            title: 'Meeting',
            start: new Date(y, m, d, 10, 30),
            allDay: false
        }, {
            title: 'Lunch',
            start: new Date(y, m, d, 12, 0),
            end: new Date(y, m, d, 14, 0),
            allDay: false
        }, {
            title: 'Birthday Party',
            start: new Date(y, m, d + 1, 19, 0),
            end: new Date(y, m, d + 1, 22, 30),
            allDay: false
        }, {
            title: 'Click for Google',
            start: new Date(y, m, 28),
            end: new Date(y, m, 29),
            url: 'http://google.com/'
        }]
    });
}); */
	$(document).ready(function() {
		
		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();
		
		$('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			editable: true,
			dayClick: function(date, allDay, jsEvent, view) {

	            if (allDay) {
	                alert('Clicked on the entire day: ' + date);
	            }else{
	                alert('Clicked on the slot: ' + date);
	            }

	            //alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);

	           // alert('Current view: ' + view.name);

	            // change the day's background color just for fun
	            // $(this).css('background-color', 'red');
	            $(this).text(date);

	        },
	        eventClick: function(calEvent, jsEvent, view) {

	            alert('Event: ' + calEvent.title);
	            alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
	            alert('View: ' + view.name);

	            // change the border color just for fun
	            $(this).css('border-color', 'red');

	        },
			events: [
				{
					title: '今天',
					start: new Date(y, m, 1)
				},
				{
					title: 'Long Event',
					start: new Date(y, m, d-5),
					end: new Date(y, m, d-2)
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d-3, 16, 0),
					allDay: false
				},
				{
					id: 999,
					title: 'Repeating Event',
					start: new Date(y, m, d+4, 16, 0),
					allDay: false
				},
				{
					title: 'Meeting',
					start: new Date(y, m, d, 10, 30),
					allDay: false
				},
				{
					title: 'Lunch',
					start: new Date(y, m, d, 12, 0),
					end: new Date(y, m, d, 14, 0),
					allDay: false
				},
				{
					title: 'Birthday Party',
					start: new Date(y, m, d+1, 19, 0),
					end: new Date(y, m, d+1, 22, 30),
					allDay: false
				},
				{
					title: 'Click for Google',
					start: new Date(y, m, 28),
					end: new Date(y, m, 29),
					url: 'http://google.com/'
				}
			]
		});
		
	});
        </script>
</body>
</html>