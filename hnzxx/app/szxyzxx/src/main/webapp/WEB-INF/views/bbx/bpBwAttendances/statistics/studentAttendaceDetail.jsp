<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link rel="stylesheet" href="${ctp}/res/css/extra/add.css" >
<link rel="stylesheet" href="${ctp}/res/css/bbx/bbx.css">
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
    .twoneocn{transition:0.5s; padding-bottom:100px;}
    .twoneocn tr:hover td{ background: #f3f3f3; cursor: pointer}
    .twoneocn tr td{  text-align: right; color:#666;}
    .twoneocn tr td:first-child{ text-align: left}
    .twoneocn tr td{padding: 8px;padding-right: 30px ;border-bottom: 1px solid #ccc}
    .twoneocn tr:first-child td{ padding-left: 20px; padding-right: 30px; background: #7a7a7a; color: #Fff}
    .twoneocn span{ display: inline-block;}
    .twoone_sun{ text-align: right}
    .twonecon_ul ul{list-style: none; padding: 0; margin: 0; overflow: hidden;  width: 105%}
    .twonecon_ul ul li{ float: left; text-align: center; padding: 10px; margin: 8px}
    .twonecon_ul ul li span{ display: block; padding: 2px 0}
    .name_class{ font-size: 18px;}
    .time_class{ font-size: 12px; color: #a5a5a5}
    .class_class{font-size: 12px; color: #a5a5a5}
    .twonecon_ul{ display: none}
	.class_sop{}
    .one_class{
    font-size: 18px;
    font-weight: bold;}
   .twoone_sun { display: inline-block;float: right}
   .tan_bottom_1{left:0;}
</style>
</head>
<body style="background-color: #f3f3f3 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" >			
					<div class="twoneocn" >
						<span style="font-size: 20px;line-height: 1.2;padding-bottom: 10px;display: block;text-align: center;">${student.name}</span>
					    <table width="100%" spellcheck="0" cellspacing="0">
					        <tr>
					            <td><span>日期</span><span style=" display: inline-block;float: right">刷卡时间</span></td>
					        </tr>
							<c:forEach items="${items}" var="item">
					        <tr>
					            <td>
					                <div class="one_class">
					                	<span class=“class_sop”><fmt:formatDate value="${item.attendanceDay}" pattern="yyyy/MM/dd" /></span>
					                	<span class="twoone_sun">
					                		<c:choose>
					                			<c:when test="${type == '1'}">
					                				<fmt:formatDate value="${item.inTime}" pattern="HH:mm:ss" />
					                			</c:when>
					                			<c:when test="${type == '2'}">
					                				<fmt:formatDate value="${item.outTime}" pattern="HH:mm:ss" />
					                			</c:when>
					                		</c:choose>	
					                	</span>
					                </div>
					            </td>
					        </tr>
							</c:forEach>
					    </table>
					</div>		
					<div class="form-actions tan_bottom_1" >
						<a href="javascript:void(0)" onclick="$.closeWindow();" class="yellow">取消</a>
               		</div>		
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

</script>
</html>
