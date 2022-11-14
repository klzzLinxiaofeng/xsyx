<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<style>
.row-fluid .span13 {
	width: 75%;
}

.row-fluid .span4 {
	width: 75%;
}

.conscc{font-size: 12px;}
#table_con{ border: 1px solid #bbbbbb}
#table_con tr:first-child td{ border-bottom:1px solid #bbbbbb }
#table_con tr td:last-child{ text-align: center;padding-right:30px;}
#table_con tr td{ padding: 10px 0; font-size: 12px; text-align: center}
.but_con{ margin-top: 15px;}
.but_con input{ margin-left: 10px; }
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<div style="margin-left: 20px; ">
						年级学生总数：<span style="font-weight: bold;">${studentNum}</span>人，
						已报名学生数：<span style="font-weight: bold;">${courseStudentNum}</span>人
					</div>
				
					<table width="100%" cellspacing="0" id="table_con">
        				<tr style="background: #e6e6e6; border:1px solid #bbb">
	            			<td width="30%"> 科目</td>
	            			<td width="30%">目前报名人数</td>
	            			<c:if test="${courseConfig.isLimited == true}">
					       		<td>名额</td>
					        </c:if>
	            			
            			</tr>
    				
					<c:forEach items="${items}" var="item">
						<tr>
            				<td>${item.courseNames}</td>
            				<td>${item.num}人</td>
            				<c:if test="${courseConfig.isLimited == true}">
            					<td>${item.maxNum}人</td>
            				</c:if>		
        				</tr>		
					</c:forEach>
					</table>
					<p style="width:100%; height:30px;"></p>
					<div class="form-actions tan_bottom">
						<button class="btn btn-warning" type="button" onclick="$.closeWindow();">关闭</button>
					</div>			
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
</script>
</html>