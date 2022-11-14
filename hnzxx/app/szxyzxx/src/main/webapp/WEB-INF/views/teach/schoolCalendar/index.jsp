<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ include file="/views/embedded/common.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="${pageContext.request.contextPath}/res/plugin/falgun/js/stepy.jquery.js"></script>
        <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
        <title>校历管理</title>
        <style type="text/css">
            .table thead th, .table tbody td{text-align:center;vertical-align:middle}
            .table tbody .red{background-color:#df5a5a;color:#fff;}
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-calculator" name="icon"/>
                <jsp:param value="校历管理" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid ">
                <div class="span12"  style="height: 43px;">
                    <div class="content-widgets white" style="margin-bottom: 0;">
                        <div class="widget-head">
                            <h3>
                                <span>校历管理</span>
                                <c:if test="${fn:length(calendarList) > 0}">
                                    <span style="padding-left:20px;">选择：
                                        <select id="calendarSelect" onchange="loadCalendar($(this).val())" style="margin-bottom:0">
                                            <c:forEach  items="${calendarList}" var="ca" >
                                                <option value="${ca.id}">${ca.name}</option>
                                            </c:forEach>
                                        </select>
                                    </span>
                                </c:if>
                                <p style="float:right;" class="btn_link">
                                    <a class="a2" onclick="loadExportPage()"href="javascript:void(0)" ><i class="fa fa-plus"></i>导出EXCEL</a>
                                    <a class="a4" onclick="loadAddPage()" href="javascript:void(0)"><i class="fa fa-plus"></i>新建校历</a>
                                </p>
                            </h3>
                        </div>
                    </div>
                </div>
            </div>
            <div id="calendarDiv" class="row-fluid">
               
            </div>
        </div>
        <script>
        </script>
    </body>
    <script type="text/javascript">
        $(function(){
           loadCalendar($("#calendarSelect").val()); 
        });
        
        function loadCalendar(caId){
        	if(caId != null) {
	            $("#calendarDiv").load("${pageContext.request.contextPath}/teach/calendar/loadCalendar?id="+caId);
        	}
        }
        
        function loadAddPage() {
            var mes = "新建校历";
            $.initWinOnTopFromLeft(mes,'${pageContext.request.contextPath}/teach/calendar/add', '600', '300');
        }
        
        function loadExportPage(){
        	$.initWinOnTopFromLeft('导出EXCEL', '${pageContext.request.contextPath}/teach/calendar/loadExportPage', '600', '300');
        }
        
    </script>
</html>
