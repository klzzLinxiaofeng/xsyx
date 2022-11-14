<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<%@ include file="/views/embedded/plugin/avatar_js.jsp"%>
<link href="${ctp}/res/css/bbx/bbx.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="select_top">
				<div class="s1 s1_bg">
				<span style="font-size: 16px;font-family: 微软雅黑;line-height: 35px;margin: 0 0 0 10px;">${teamName }</span>
					<div class="search">
						<input id="name" type="text" placeholder="姓名" />
						<button class="btn btn-success" id="sosuo" onclick="find()">搜索</button>
						<button class="btn btn-primary" onclick="back()">返回</button>
					</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="ryxz">
				<div id="classMember_list_content">
					<div class="select_top" style="margin-top: -2px">
						<div class="s2">
							全班：<span class="a1">${fn:length(studentList)}</span> 人  &nbsp; 男：<span
								class="a2">${boyCount }</span> 人  &nbsp; 女：<span class="a3">${girlCount }</span>
							人  &nbsp; 其他：<span class="a1">${otherCount }</span> 人 &nbsp; 教师：<span class="a2">${fn:length(teacherListCopy)}</span> 人
						</div>
					</div>
					<div class="xs_list" id="studentList">
						<ul>
							<c:forEach items="${studentList}" var="student" varStatus="i">
								<li><img src="<avatar:avatar userId='${student.userId }'></avatar:avatar>"><span>${student.name}</span>
									<a href="javascript:void(0)" id="zp_modify_btn_${i.index}"
									class="tx_modify">修改头像</a> <input type="hidden"
									date-user-id="${student.name }" date-user-sex="${student.sex }" value="${student.userId }">
								</li>
							</c:forEach>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="js_list" id="teacherList">
						<ul>
							<c:forEach items="${teacherListCopy}" var="teacher">
								<li><img
									src="<avatar:avatar userId='${teacher.userId }'></avatar:avatar>"><span>${teacher.name}</span>
									<p>
										<c:if test="${not empty teacher.subjectName }">${teacher.subjectName }</c:if>
										老师
									</p></li>
							</c:forEach>
						</ul>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$(".tx_modify").click(function(){
			currentClickedUser = $(this)
		})
		$.createAvartarEditor({
			"btn" : ".tx_modify"
		});
	});
    
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
	
	//查询
	function find(){
		$("#studentList ul li").each(function(){
			if($(this).children("span").html().indexOf($("#name").val())==-1){
				$(this).hide();
			}
		})
		$("#teacherList ul li").each(function(){
			if($(this).children("span").html().indexOf($("#name").val())==-1){
				$(this).hide();
			}
		})
	}
	
	 function back(){
		 window.location.href="${ctp}/clazz/classMember/schoolMasterView";
	 }
</script>
</html>