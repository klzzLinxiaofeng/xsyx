<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/views/embedded/common.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量设置年级科目</title>
<script type="text/javascript">
    $(function(){
        $("tr th input").click(function(){
            var i=$(this).parent().index();
            if($(this ).is(':checked' )){
                    $("tbody tr").each(function(){
                        if(($( this).children("td").eq(i).children("input").attr("readonly") != "readonly")){
                        $( this).children("td").eq(i).children("input").prop("checked" , "checked");
                        }
                    })
                }else{
                    $("tbody tr").each(function(){
                        $( this).children("td").eq(i).children("input").removeAttr("checked");
                    })
                }
           });
       $(".td_quanxuan").click(function(){
           if($(this ).is(':checked' )){
               $(this).parent().parent().children().each(function(){
                   if(($(this).children("input").attr("readonly") != "readonly")){
                       $(this).children("input").prop("checked" , "checked");
                   }
               })
           }else{
               $(this).parent().siblings().children("input").removeAttr("checked");
           }
      });
    })
</script>
<style type="text/css">
.table th,.table td{padding-left:10px;}
</style>
</head>
<body style="background-color:cdd4d7 !important">
	<div class="row-fluid">
		<div class="span12">
			<div class="content-widgets" style="margin-bottom:0">
				<div class="widget-container" style="padding:20px 0 0;">
					<div class="widget-container">
						<form class="form-horizontal">
						<input type="hidden" id="gradeId" value="${gradeId }">
							<table id="data-table" class="table white table-bordered">
								<thead>
									<tr>
										<th></th>
									<%-- <c:forEach items="${schoolSystemList }" var="schoolSystem">
										<th data-gcode="${schoolSystem.gradeCode }">${schoolSystem.gradeName }<input type="checkbox" name="" style="margin: 0 0 0 5px;"></th>
									</c:forEach> --%>
									<c:forEach items="${gradeList}" var="grade">
										<th data-gcode="${grade.code}">${grade.name}<input type="checkbox" name="" style="margin: 0 0 0 5px;"></th>
									</c:forEach>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${subjectList }" var="subject">
									<tr>
										<td data-scode="${subject.code }">${subject.name }<input type="checkbox" name="" class="td_quanxuan" style="margin:10px 5px 0 0;float: right;"></td>
										<%-- <c:forEach items="${schoolSystemList }" var="sy">
										<td data-stageCode="${sy.stageCode }" data-gcode="${sy.gradeCode }"><input type="checkbox" <c:if test="${not fn:contains(subject.stageCode, sy.stageCode)}">readonly="readonly" onclick="UnChoose(this);"</c:if>></td>
										</c:forEach> --%>
										
										<c:forEach items="${gradeList }" var="sy">
										<td data-stageCode="${sy.stageCode}" data-gcode="${sy.uniGradeCode}"><input type="checkbox" ></td>
										<%-- <c:if test="${not fn:contains(subject.stageCode, sy.stageCode)}">readonly="readonly" onclick="UnChoose(this);"</c:if> --%>
										</c:forEach>
									</tr>
									</c:forEach>
										
								</tbody>
							</table>
						</form>
					</div>
					<div class="form-actions tan_bottom" style="background-color:#297657;">
            	<div style="text-align:center;">
             		<button  class="btn btn-warning" type="button" onclick="saveOrUpdate();">保存</button>
<!--              		<button  class="btn" type="button">取消</button> -->
            	</div>
            </div>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		$(".add_class").on("click",".num",function(){
			$(this).toggleClass("on");
		});
		$(".add_class .plus").click(function(){
			var i=$(".first_num").val();
			var j=$(".num").length;
			var k=parseInt(i)+parseInt(j);
			$(".all_class").append('<a href="javascript:void(0)" class="num on"><span>'+k+'</span>班</a>');
		});
		$(".first_num").change( function() {
			var i=$(".first_num").val();
			var j=$(".num").length;
			var k=parseInt(i)+parseInt(j);
			$(".all_class").empty();
			for(var l=i;l<k;l++){
				$(".all_class").append('<a href="javascript:void(0)" class="num on"><span>'+l+'</span>班</a>');
			}
			});
		
		//获得本学校年级科目
		$.get("${ctp}/teach/subjectGrade/getSubjectGrade", function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				$.each(data, function(index, value) {
					var gradeCode = value.gradeCode;
					var subjectCode = value.subjectCode;
					var subjectGradeId = value.id;
// 					alert(subjectCode + ":" + gradeCode);
					var sgCell = $("#data-table tbody tr").find("td[data-scode='" + subjectCode + "']").parent().find("td[data-gcode='" + gradeCode + "']");
					sgCell.find("input").attr("checked", "checked");
					sgCell.find("input").attr("data-id", subjectGradeId);
				});
			}
		});
		
	});
	
	function UnChoose(obj){
		$(obj).prop('checked',false);
		$.alert("该科目不适用于这个年级");
	}
	
	function saveOrUpdate() {
		var loader = new loadLayer();
		var gradeId = $("#gradeId").val();
		var url = "${ctp}/teach/subjectGrade/saveSubjectGradeBatch";
		var $requestData = {};
// 		var originalData = [];  //用于保存原有的数据
		var originalData = "";  //用于保存原有的数据
		var selectedData = [];  //用于保存新增的数据
		$("#data-table tbody tr td").find(":checkbox:checked").each(function() {
			var subjectGradeId = $(this).attr("data-id");
			if(subjectGradeId == undefined) {
				var stageCode = $(this).parent().attr("data-stageCode");   //获得学段Code
				var gradeCode = $(this).parent().attr("data-gcode");       //获得年级Code
				var subjectCode = $(this).parent().parent().find("td:first").attr("data-scode");    //获得科目Code
				if(stageCode != undefined && gradeCode != undefined && subjectCode != undefined){
					selectedData.push({stageCode : stageCode, gradeCode : gradeCode, subjectCode : subjectCode});
				}
// 				alert("gradeCode:" + gradeCode + "+stageCode:" + stageCode + "+subjectCode:" + subjectCode );
			}else {
				originalData = originalData + subjectGradeId + ";";
// 				originalData.push({subjectGradeId : subjectGradeId});
// 				alert("保持：" + subjectGradeId);
			}
		});
		originalData = $.trim(originalData);
		originalData = originalData.substring(0,originalData.length-1);
		$requestData.selectedData = JSON.stringify(selectedData);
		$requestData.originalData = originalData;
// 		$requestData.originalData = JSON.stringify(originalData);
// 		alert($requestData.originalData);
// 		alert(JSON.stringify($requestData));
// 		return;
		loader.show();
		$.post(url, $requestData, function(data, status) {
			if("success" === status) {
				$.success('保存成功');
				data = eval("(" + data + ")");
				if("success" === data.info) {
					if (parent.core_iframe != null) {
						parent.core_iframe.window.ajaxFunction(gradeId, null);
// 						parent.core_iframe.window.location.reload();
					} else {
						parent.window.ajaxFunction(gradeId, null);
// 						parent.window.location.reload();
					}
					$.closeWindow();
				}else {
					
				}
			}else {
				$.error("保存失败");
			}
			loader.close();
		});
	}
	</script>
</body>
</html>
