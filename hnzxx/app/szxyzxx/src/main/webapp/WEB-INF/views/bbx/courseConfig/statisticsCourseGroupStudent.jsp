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
    .twoneocn{transition:0.5s; padding-bottom:100px;}
    .twoneocn tr:hover td{ background: #f3f3f3; cursor: pointer}
    .twoneocn tr td{  text-align: right; color:#666;}
    .twoneocn tr td:first-child{ text-align: left}
    .twoneocn tr td{padding: 8px;padding-right: 40px ;border-bottom: 1px solid #ccc}
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
</style>
</head>
<body style="background-color: cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom: 0">
				<div class="widget-container" > 			
					<div class="twoneocn" >
					    <table width="100%" spellcheck="0" cellspacing="0">
					        <tr>
					            <td>
					            	<span>科目组</span>
					            	<span style=" display: inline-block;float: right">报名人数</span>   	
					            </td>
					        </tr>
							<c:forEach items="${items}" var="item">
					        <tr>
					            <td>
					                <div class="one_class">
					                	<span class=“class_sop”>${item.courseNames}</span>
					                	<span class="twoone_sun">${item.num}人</span>
					                </div>
					                <div class="twonecon_ul">
					                    <ul>
					                    <c:forEach items="${item.nameList}" var="v">
					                        <li>
					                            <span class="name_class">${v.studentName}</span>
					                            <span class="class_class">${v.teamName}</span>
					                            <span class="time_class"><fmt:formatDate value="${v.createDate}" pattern="yyyy/MM/dd HH:mm" /></span>
					                        </li>
            							</c:forEach>
					                    
					                    </ul>
					                </div>
					            </td>
					        </tr>
							</c:forEach>
					    </table>
					</div>
					
					<div class="form-actions tan_bottom">
						<a href="javascript:void(0)" class="btn btn-success right" onclick="downLoadData();" id="downLoadExcel" style="background: #e69100;color: #fff;">
										下载学生选科统计名单
						</a>
						<button class="btn btn-warning" type="button" onclick="$.closeWindow();">关闭</button>
						<input style="display: none;" type="text" id="courseConfigId" name="courseConfigId" placeholder="" value="${courseConfigId}">
					</div>			
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$('.twoneocn td').click( function () {
    $(this).parent().find('.twonecon_ul').stop().slideToggle()
})
function downLoadData() {
	var courseConfigId = $("#courseConfigId").val();
	var url = "${ctp}/bbx/courseConfig/downLoadStudentCourseData?courseConfigId="+courseConfigId;
	$("#downLoadExcel").attr("href", url);
}
</script>
</html>