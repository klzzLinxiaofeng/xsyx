<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
<title></title>
<style type="text/css">
.table {
    font-size: 12px !important;
    table-layout: fixed;
    word-break: break-all;
}
#code_nation table td, #code_nation table th, #code_nation1 table td, #code_nation1 table th, #code_nation2 table td, #code_nation2 table th {
    line-height: 30px;
    text-align: center;
    vertical-align: middle;
}
.table td {
    white-space: normal;
}
#stepy_tabby1 ul li, .stepy_tabby ul li {
    float: left;
    list-style: outside none none;
    margin-bottom: 10px;
    margin-right: 10px;
}
li {
    line-height: 20px;
}
#stepy_tabby1 ul li a:link, #stepy_tabby1 ul li a:visited, .stepy_tabby ul li a:link, .stepy_tabby ul li a:visited {
    background-color: white;
    color: black;
}
#stepy_tabby1 ul li a, .stepy_tabby ul li a {
    border-radius: 5px;
    display: block;
    padding: 5px 10px;
}
#stepy_tabby1 ul li a:link, #stepy_tabby1 ul li a:visited, .stepy_tabby ul li a:link, .stepy_tabby ul li a:visited {
    background-color: #FFF;
    color: #000;
}
#stepy_tabby1 ul li.active a, .stepy_tabby ul li.active a {
    background-color: #FFB401 !important;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div style="height: 10px;"></div>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="light_grey"></div>
					<div class="content-widgets" style="height: 420px;">
						<div class="widget-container" style="height: 100%">
							<div id="code_nation" style="height: 100%; overflow: auto;">
					<table class="responsive table table-hover table-bordered tbl-paper-theme">
						<thead>
						<c:if test="${!empty teacherListA}">
							<tr>
								<th width="20%">A-C</th>
								<td width="80%">
									<div class="stepy_tabby stepy_tabby_small " >
										<c:forEach items="${teacherListA}" var="teacherList" varStatus="status">
											<ul>
												<li id="${teacherList.userId}" tagged="${teacherList.tagged}">
													<a href="#" data-tid="${teacherList.userId}" data-username="${teacherList.userName}">${teacherList.name}</a>
												</li>
											</ul>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty teacherListD}">
							<tr>
								<th width="20%">D-F</th>
								<td width="80%">
									<div class="stepy_tabby stepy_tabby_small " >
										<c:forEach items="${teacherListD}" var="teacherList">
											<ul>
												<li id="${teacherList.userId}" tagged="${teacherList.tagged}">
													<a href="#" data-tid="${teacherList.userId}" data-username="${teacherList.userName}">${teacherList.name}</a>
												</li>
											</ul>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty teacherListG}">
							<tr>
								<th width="20%">G-I</th>
								<td width="80%">
									<div class="stepy_tabby stepy_tabby_small " >
										<c:forEach items="${teacherListG}" var="teacherList">
											<ul>
												<li id="${teacherList.userId}" tagged="${teacherList.tagged}">
													<a href="#" data-tid="${teacherList.userId}" data-username="${teacherList.userName}">${teacherList.name}</a>
												</li>
											</ul>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty teacherListJ}">
							<tr>
								<th width="20%">J-L</th>
								<td width="80%">
									<div class="stepy_tabby stepy_tabby_small " >
										<c:forEach items="${teacherListJ}" var="teacherList">
											<ul>
												<li id="${teacherList.userId}" tagged="${teacherList.tagged}">
													<a href="#" data-tid="${teacherList.userId}" data-username="${teacherList.userName}">${teacherList.name}</a>
												</li>
											</ul>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty teacherListM}">
							<tr>
								<th width="20%">M-O</th>
								<td width="80%">
									<div class="stepy_tabby stepy_tabby_small " >
										<c:forEach items="${teacherListM}" var="teacherList">
											<ul>
												<li id="${teacherList.userId}" tagged="${teacherList.tagged}">
													<a href="#" data-tid="${teacherList.userId}" data-username="${teacherList.userName}">${teacherList.name}</a>
												</li>
											</ul>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty teacherListP}">
							<tr>
								<th width="20%">P-R</th>
								<td width="80%">
									<div class="stepy_tabby stepy_tabby_small " >
										<c:forEach items="${teacherListP}" var="teacherList">
											<ul>
												<li id="${teacherList.userId}" tagged="${teacherList.tagged}">
													<a href="#" data-tid="${teacherList.userId}" data-username="${teacherList.userName}">${teacherList.name}</a>
												</li>
											</ul>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty teacherListS}">
							<tr>
								<th width="20%">S-U</th>
								<td width="80%">
									<div class="stepy_tabby stepy_tabby_small " >
										<c:forEach items="${teacherListS}" var="teacherList">
											<ul>
												<li id="${teacherList.userId}" tagged="${teacherList.tagged}">
													<a href="#" data-tid="${teacherList.userId}" data-username="${teacherList.userName}">${teacherList.name}</a>
												</li>
											</ul>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty teacherListV}">
							<tr>
								<th width="20%">V-X</th>
								<td width="80%">
									<div class="stepy_tabby stepy_tabby_small " >
										<c:forEach items="${teacherListV}" var="teacherList">
											<ul>
												<li id="${teacherList.userId}" tagged="${teacherList.tagged}">
													<a href="#" data-tid="${teacherList.userId}" data-username="${teacherList.userName}">${teacherList.name}</a>
												</li>
											</ul>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:if>
						<c:if test="${!empty teacherListY}">
							<tr>
								<th width="20%">Y-Z</th>
								<td width="80%">
									<div class="stepy_tabby stepy_tabby_small " >
										<c:forEach items="${teacherListY}" var="teacherList">
											<ul>
												<li id="${teacherList.userId}" tagged="${teacherList.tagged}">
													<a href="#" data-tid="${teacherList.userId}" data-username="${teacherList.userName}">${teacherList.name}</a>
												</li>
											</ul>
										</c:forEach>
									</div>
								</td>
							</tr>
						</c:if>
					</table>						
				</div>
						<%-- <div class="form-actions tan_bottom">
							<input type="hidden" id="id" name="id" value="${circleUser.circleId}" />
							<button class="btn btn-warning" type="button"
									onclick=";">确定</button>
								<button class="btn btn-warning" type="button"
									onclick="$.closeWindow();">取消</button>
						</div> --%>
						</div>
					</div>
					<div style="padding-bottom: 0px;">
							<p class="btn_link" style="float: right;">
								<a id="confirmBtn" href="javascript:void(0)" class="a3"><i class="fa"></i>确定</a>
								<a href="javascript:void(0)" class="a1"
									onclick="$.closeWindow();"><i class="fa"></i>取消</a>
							</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(function(){
	//还原已选择的教师
 	$('#code_nation').find("li[tagged='1']").toggleClass('active');
	//选择或取消选择一个教师
	$("#code_nation").on('click', 'a', function(event) {
		$(this).parent().toggleClass('active');
		return false;
	});
	
	//确定按钮
	$("#confirmBtn").click(function(event) {
		var selectedLis = $("#code_nation").find('.active');
		var userIds;
		if(selectedLis.length == 0){
			$.error("请至少选择一个教师");
			return false;
		}else{
			var teacherIds =[];
			selectedLis.each(function(index, el) {
				var id = $(this).attr("id");
				teacherIds.push(id);
			});
			userIds = teacherIds.join(',');
		}
		var loader = new loadLayer();
		loader.show();
		$.post('${pageContext.request.contextPath}/bbx/circle/user/saveCircleUsers?', {
			circleId : '${circleId}', userIds : userIds, 'roleCode' : 1
		}, function(data, status) {
			if("success" === status) {
				data = eval("(" + data + ")");
				if("success" === data.info) {
					$.success('操作成功');
					setTimeout(function(){
						//刷新父窗口, 祖父窗口
						var iframes = $(window.parent.document.body).find('iframe');
						var currentUrl = window.location.href;
						iframes.each(function(i){
							// 莫怪, 弹出窗口时, 生成的iframe都被append到首页, 而且不可设置iframe标识, 所以暂时只能根据iframe的src来判断
							if(this.src.indexOf('/circle/')!=-1 && this.src!=currentUrl){
								this.contentWindow.search();
							}
						});
						$.closeWindow();
					}, 1000);
				} else {
					$.error("操作失败");
				}
			}else{
				$.error("操作失败");
			}
			loader.close();
		});
	});	
});
	
</script>
</html>