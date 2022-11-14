<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="资源审核" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>

		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3>
							 资源审核列表
							<p class="btn_link" style="float: right;">
							<a href="javascript:void(0)" class="a3" onclick="$.refreshWin();" style="float:left;margin-top:2px;"><i class="fa  fa-undo"></i>刷新列表</a>
							 <a id = 'selectResource'></a>
							</p>
						</h3>
					</div>
					<div class="light_grey"></div>
					
					<div class="content-widgets">
						<div class="widget-container">
							<div class="select_b">
								<div class="select_div">
									<span>年级： </span>
									<select id="gradeCode" name="gradeCode" class="chzn-select" style="width: 160px;">
										
									</select>
								</div>
								<div class="select_div">
									<span>科目： </span>
									<select id="subjectCode" name="subjectCode" class="chzn-select" style="width: 160px;">
										
									</select>
								</div>
								
								<div class="select_div">
									<span>类型： </span><select id="resType" name="resType"class="chzn-select" style="width: 160px;">
										<option value="">全部</option>
										<option value="1">微课</option>
										<option value="11">书写型微课</option>
										<option value="2">课件</option>
										
										<option value="3">作业</option>
										<option value="4">试卷</option>
										<option value="5">教案</option>
										
										<option value="6">素材</option>
										<option value="7">其他</option>

										
									</select>
								</div>
								<div class="select_div">
									<span>状态： </span>
									<select id="verify" name="verify" class="chzn-select" style="width: 160px;">
										<option value="">全部</option>
										<option value="2">审核通过</option>
										<option value="6">待审核</option>
										<option value="5">审核拒绝</option>
									</select>
								</div>
								<div class="select_div">
									<span>关键字：</span> 
									<input type="text" id="title" name = "title" placeholder="名字或共享者"  style="width: 160px;"/>
								</div>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 1)}">
								<button type="button" class="btn btn-primary" onclick="search()">查询</button>
								</c:if>
								<div class="clear"></div>
							</div>
							<table class="responsive table table-striped" id="data-table">
								<thead>
									<tr role="row">
											<th>序号</th>
											<!-- <th>缩略图</th> -->
											<th>名称</th>
											<th>年级</th>
											<th>科目</th>
											<th>上传时间</th>
											<th>所属类型</th>
											<th>共享者</th>
											<th>资源状态</th>
											
										<th class="caozuo" style="max-width: 250px;">操作</th>
									</tr>
								</thead>
								<tbody id="resource_list_content">
									<jsp:include page="./list.jsp" />
								</tbody>
							</table>
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="resource_list_content" />
								<jsp:param name="url" value="/resource/indexVerify?sub=list&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

	$(function() {
		
		$.getGrade({
			"schoolId" : '${sessionScope[sca:currentUserKey()].schoolId}'
			
			
		}, function(data) {
			var $nj = $("#gradeCode");
			
			$nj.html("");
			$nj.append("<option value=''>全部</option>");
			$.each(data, function(index, value) {
				$nj.append("<option value='" + value.uniGradeCode + "'>" + value.name + "</option>");
				
			});
			
		});
		
		//var stageCode = '${sessionScope[sca:currentUserKey()].stageCode}';
		$.getPjSubject({
			"selector" : "#subjectCode",
			"schoolId" : '${sessionScope[sca:currentUserKey()].schoolId}',
			"subjectClass" : '1',
			
			
		}, function(data) {
			//alert(JSON.stringify(data));
			var $sc = $("#subjectCode");
			
			$sc.html("");
			$sc.append("<option value=''>全部</option>");
			$.each(data, function(index, value) {
				$sc.append("<option value='" + value.code + "'>" + value.name + "</option>");
				
			});
			
			
		});

	});


	function search() {
		var val = {};
		var title = $("#title").val();
		var resType = $("#resType").val();
		var verify = $("#verify").val();
		var gradeCode = $("#gradeCode").val();
		var subjectCode = $("#subjectCode").val();
		
		if (title != null && title != "") {
			val.title = title;
		}
		if (resType != null && resType != "") {
			val.resType = resType;
		}
		if (verify != null && verify != "") {
			val.verify = verify;
		}
		if (gradeCode != null && gradeCode != "") {
			val.gradeCode = gradeCode;
		}
		if (subjectCode != null && subjectCode != "") {
			val.subjectCode = subjectCode;
		}
		var id = "resource_list_content";
		var url = "/resource/indexVerify?sub=list&dm=${param.dm}";
		myPagination(id, val, url);
	}

	// 	查看对话框
	function selectObj(id) {
		alert(id);
		var url = '${pageContext.request.contextPath}/resource/viewer/'+id;
		 $("#selectResource").attr("href", url);
		 alert(2);
		 $("#selectResource").click();
		 alert(3);
	}
	
	// 	删除对话框
	function deleteObj( id,type) {
		$.confirm("确定执行此次操作？", function() {
			executeModifyVerify(id,type);
		});
	}
	
	// 	执行删除
	function executeModifyVerify(id,type) {
		var url;
		var message;
		if(type == 0){
			url = '${ctp}/resource/addVerify/';
			message = "审核通过";
		}else{
			url = '${ctp}/resource/deleteVerify/';
			message = "审核拒绝";
		}
		  $.post(url + id, {"_method" : "put"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success(message+"成功");
					$.refreshWin();
				} else if ("fail" === data) {
					$.error(message+"失败，系统异常", 1);
					$.refreshWin();
				}
			}
		});  
	}
	
	
</script>
</html>