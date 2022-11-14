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
#table_con tr td{ padding: 5px 0; font-size: 12px; text-align: center; border:1px solid #b5b5b5; }
.but_con{ margin-top: 15px;}
.but_con input{ margin-left: 10px;}
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" style="padding: 20px 0 0;">
					<div style="margin-left: 20px; ">
						未报名学生数：<span style="font-weight: bold;">${items.size()}</span>人
					</div>
				
					<table width="100%" cellspacing="0" id="table_con">
        				<tr style="background: #e6e6e6; border:1px solid #bbb">
	            			<td width="10%" style=" font-weight: bold;"> 姓名</td>
	           				<td width="30%" style=" font-weight: bold;"> 班级</td>
            			</tr>
    				
					<c:forEach items="${items}" var="item">
						<tr>
            				<td>${item.name}</td>
            				<td>${item.fullName}</td>
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