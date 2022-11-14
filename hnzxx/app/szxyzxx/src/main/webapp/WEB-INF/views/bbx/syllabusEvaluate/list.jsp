<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<!DOCTYPE html>
<%-- <%@include file="/WEB-INF/views/common/permission.jsp" %> 
<tr>
	<td style="padding:0;border:0 none;">
		<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
		<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
	</td>
</tr> 
--%>
<c:forEach items="${items}" var="item">
	<tr id="${item.id}_tr">
				<td style="display: none;">${item.id}</td>
				<td style="display: none;">${item.lessionId}</td>
				<td style="display: none;">${item.week}</td>
				<%-- <td>${item.postUserId}</td> --%>
				<td>${item.studentName}</td>
				<td>
			        <div class="content">
			        <ul class="show_number clearfix">
			            <li>
			                <div class="atar_Show">
			                    <p tip="${item.score}"></p>
			                </div>
			                <span style="margin-left: 13px;margin-top: 3px;">${item.score}</span>
			            </li>
			        </ul>
			        </div>
			    </td>
			    <td>
			        <p style="width:260px;padding-top:4px;margin-bottom:0;">${item.content}</p>
			    </td>
			    <td>
			        <p class="titme_pj" style="margin-bottom: -9px;">
			        <fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd"/>
			        <a class="btn btn-blue" style="padding:1px 7px;" type="button" onclick="viewer('${item.id}');">详情</a></p>
			    </td>
				<%-- <td>${item.score}</td> --%>
				<%-- <td>${item.content}</td> --%>
	    		<%-- <td><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" /></td>
	    		<td><fmt:formatDate value="${item.modifyDate}" pattern="yyyy/MM/dd" /></td>
				<td>${item.isDeleted}</td> --%>
		
		<%-- <td class="caozuo">
			<button class="btn btn-green permission_check" type="button" onclick="loadViewerPage('${item.id}');">详情</button>
			<button class="btn btn-blue permission_update" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
			<button class="btn btn-red permission_del" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
		</td> --%>
	</tr>
</c:forEach>
<input type="hidden" id="aver" value="${average }"/>			
<script> 
//显示分数

	$(function() {
		var average = $("#aver").val();
		$("#average").html(average);
	});
	
$(".show_number li p").each(function(index, element) {
     var num=$(this).attr("tip");
    var www=num*2*16;//
    $(this).css("width",www);
    $(this).parent(".atar_Show").siblings("span").text(num+"分"); 
  /*  var num=$(this).attr("tip");
    var www;//
    var num_p =num .toString().replace(/\d+\.(\d*)/,"$1");
    var ab_str = num.toString()
    var ab_num = parseInt(ab_str.substring(0,ab_str.indexOf('.')));

    if(num_p>0){
//        num_p = 5;
        ab_num+=0.5;
    }
    www =ab_num*2*12;
    $(this).css("width",www);
    $(this).parent(".atar_Show").siblings("span").text(num+"分");*/
});

function viewer(id) {
	$.initWinOnTopFromLeft('详情', '${ctp}/bbx/syllabusEvaluate/viewer?id=' + id, '650', '530');
}

	
</script>
