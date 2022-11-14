<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<style>
.pic{  border-radius: 100%;
    float: left;
    height: 58px;
    overflow:hiden;
    margin-left: 10px;
     margin-right:10px;
    width: 58px;}
    .title_{font-size:18px;}
    .js_list p{line-height:1.4; margin:0;
     text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
  word-wrap:normal;
  width:100px;}
    .js_list table{height:100%;}
</style>
<div class="select_top" style="margin-top:-2px">
<div class="s2">
	全班：<span class="a1">${fn:length(studentList)}</span> 人 男：<span class="a2">${boyCount }</span> 人 女：<span
		class="a3">${girlCount }</span>人其他：<span class="a1">${otherCount }</span> 人 教师：<span class="a2">${fn:length(teacherListCopy)}</span> 人
</div>
</div>
<div class="xs_list" id="studentList">
	<ul>
	<c:forEach items="${studentList}" var="student" varStatus="i">
		<li>
			<table>
			<tr>
				<td><div class="pic" style="overflow:hidden"> 
					<c:choose>
						<c:when test="${empty student.entityId}">
							<img src="${ctp}/res/images/no_pic.jpg"/> 
						</c:when>
						<c:otherwise>
							<img src="${student.entityId}"/> 
						</c:otherwise>
					</c:choose>
				
					
					<img src="<avatar:avatar userId='${teacher.userId }'></avatar:avatar>">
				</div></td>
				<td width='150px'>
					<p class="title_">${student.name}</p>
					<c:if test="${not empty student.position}"><p>(${student.position})</p></c:if>
				</td>
				<td>
					<div>
<!-- 					<a href="javascript:void(0)" id="zp_modify_btn_0" class="tx_modify" style="margin:0; margin-bottom:5px; margin-right:5px;">修改头像</a> -->
					<a href="javascript:void(0)" id="zp_modify_btn_0" style="margin:0; margin-top:5px; margin-right:5px" 
						onclick="monitor(${student.userId},${student.teamId })">设为班长</a>
					<input type="hidden" date-user-id="${student.userName }" date-user-sex="${student.sex }" value="${student.userId }">
					</div>
				</td>
			</tr>
			</table>
		</li>
	</c:forEach>
	</ul>
	<div class="clear"></div>
</div>

<div class="js_list pic_ing" id="teacherList">
	<ul>
<c:forEach items="${teacherListCopy}" var="teacher" varStatus="i">
		<li>
		<table>
			<tr>
				<td><div class="pic" style="overflow:hidden"><img src="<avatar:avatar userId='${teacher.userId }'></avatar:avatar>"></div></td>
				<td width='150px'>
					<p class="title_">${teacher.name}</p>
					<p><c:if test="${not empty teacher.subjectName }">${teacher.subjectName }</c:if>老师</p>
					<p>${teacher.characteristic}</p>
				</td>
				<td><div>
					<a href="javascript:void(0)" style=" margin:0;margin-right:5px;"
						onclick="setCharacteristic(${teacher.userId})">上课特色</a>
					</div>
				</td>
			</tr>
		</table>
	</li>
	</c:forEach>
	</ul>
	<div class="clear"></div>
</div>
<script>

var currentClickedUser = "";
$(function(){
	$(".tx_modify").click(function(){
		currentClickedUser = $(this)
	})
	$.createAvartarEditor({
		"btn" : ".tx_modify"
	});
	
})

function selectedImgHandler(data) {
	    currentClickedUser.prev().prev().attr("src", data.imgUrl);
		var $requestData = {};
		$requestData.icon = data.uuid;
		$requestData.userId = currentClickedUser.next().val();
		$requestData.userName = currentClickedUser.next().attr("date-user-id");
		$requestData.sex = currentClickedUser.next().attr("date-user-sex");
		var url = "${pageContext.request.contextPath}/user/center/profile/editor";
		$.post(url, $requestData, function(data, status) {
			if ("success" === status) {
				$.success('操作成功');
				data = eval("(" + data + ")");
				if ("success" === data.info) {
					$.success("操作成功");
					$.closeWindowByName(data.windowName);
				} else {
					$.error('操作失败');
				}
			} else {
				$.error('操作失败');
			}
		});
	}
	
function setCharacteristic(userId) {
	$.initWinOnTopFromLeft_bbx('上课特色', '${ctp}/clazz/classMember/characteristic?userId='+userId, '700', '300');
}
	
</script>