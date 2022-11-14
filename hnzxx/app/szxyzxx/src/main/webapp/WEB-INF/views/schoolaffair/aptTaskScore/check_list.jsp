<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath %>"/>
<c:set scope="request" var="ctp" value="${pageContext.request.contextPath}"></c:set>
<c:forEach items="${taskScores}" var="scores" varStatus="sta1">
	<table class="check_ordinal">
		<thead>
			<tr>
				<th style="width: 50px;">序号</th>
				<th style="width: 70px;">姓名</th>
				<th>分值</th>
			</tr>
		</thead>
		<tbody class="check_order">
			<c:forEach items="${scores}" var="score" varStatus="sta2">
				<tr>
					<td>${(sta1.index) * 18 + sta2.index + 1}</td>
					<td>${score.teacherName }</td>
					<td data-id="${score.id}"><input type="text" data-value="${score.score}" onclick="cleanValue(this,this.value);" <c:if test="${!edit}">readonly="readonly"</c:if> onblur="updateScore('${score.id}','${score.aptTaskItemId}','${score.teacherId}','${score.score}',this);" style="height: 10px;" value='<fmt:formatNumber type="number" value="${score.score}" />'>分</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:forEach>
<script type="text/javascript">
function cleanValue(obj,value){
	$(obj).val("");
}
function updateScore(id,taskItemId,teacherId,defValue,obj){
	defValue = $(obj).attr("data-value");
	var taskItemScore = "${taskItem.score}";
	var $requestData = {};
	var score = $(obj).val();
	if(isDouble(score)){
		$(obj).val(defValue);
		return;	
	}
	if(parseFloat(score) > parseFloat(taskItemScore)){
		$.alert("您填写的分数超过了该项的分值范围");
		$(obj).val(defValue);
		return;
	}
	var checkTime = $("#checkTime").val();
	$requestData.checkTime = checkTime;
	$requestData.score = score;
	$requestData.judgeId = "${judgeId}";
	$requestData.aptTaskItemId = taskItemId;
	$requestData.teacherId = teacherId;
	var id = $(obj).parent().attr("data-id");
	if(id == "" || id == null){
		var url = "${ctp}/schoolAffair/aptTaskScore/creator";
	}else{
		$requestData._method = "put";
		var url = "${ctp}/schoolAffair/aptTaskScore/" + id;
	}
	if("${edit}" == "false"){
		return;
	}
	var loader = new loadDialog();
	loader.show();
	$.post(url,$requestData,function(data,status){
		if("success" === status){
			$(obj).attr("data-value",score);
			data = eval("(" + data + ")");
			$(obj).parent().attr("data-id",data.responseData);
		}else{
			$.error("操作失败");
		}
		loader.close();
	});
}
//验证是否为分数（数字）
function isDouble(value){     
    var str = value.trim();    
    if(str.length!=0){
        reg = /^\d+(\.\d{1,2})?$/;
        if(!reg.test(str)){    
            $.alert("对不起，您输入的分数格式不正确!");
            return true;
        }    
    }else{
//     	$.alert("请输入分数！");
    	return true;
    } 
    return false;
}     
</script>