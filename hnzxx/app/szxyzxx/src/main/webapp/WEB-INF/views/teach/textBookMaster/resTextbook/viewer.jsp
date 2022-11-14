<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
<title></title>
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<style>

</style>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="书籍查看" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="ts_top">
						书籍查看
						<p>
						<a href="${ctp}/teach/textBookMaster/resTextBook/index" >图书列表</a>
						<a href="" id="loadViewerPage"  onclick="loadViewerPage('${resTextbook.id}');" class="on">基本信息</a>
						<a href="" id="loadViewerCatalogPage"  onclick="loadViewerCatalogPage('${resTextbook.id}');">教材目录</a>
						<a href="" id="loadViewerModelPage"  onclick="loadViewerModelPage('${resTextbook.id}');">目录管理</a>
						</p>
					</div>
					<div class="class_detail">
						<p class="title">
							<jc:cache tableName="jc_grade" echoField="name" value="${resTextbook.gradeCode}" paramName="code"></jc:cache>	
							${resTextbook.volumn}
							（
							<jc:cache tableName="jc_subject" echoField="name" value="${resTextbook.subjectCode}" paramName="code"></jc:cache>
							 ）
							--${resTextbook.name}
							<a href="" id="editTextBook" class="btn btn-green" onclick="editTextBook('${resTextbook.id}')" style="float:right"><i class="fa fa-edit"></i>编辑</a>
						</p>
						<div class="d1">
							<div class="cover">
										<c:if test="${empty  resTextbook.image}">
											<img src="${pageContext.request.contextPath}/res/images/cover_default_new.png" id="imgId"/>
										</c:if>
										<c:if test="${not empty resTextbook.image}">
										  
											<img src="${resTextbook.image}" id="imgId" />
										</c:if>
							</div>
							<div class="intro">
								<p>
									作者：<span><c:forEach items="${resTextbook.writerMain}" var="writerMain" varStatus="status">
								 <c:if test="${ status.index >0 }"></c:if>
									${writerMain.name} &nbsp;
								 </c:forEach>&nbsp;编</span>
								</p>
								<p>
									出版社：<span>${resTextbook.publishName}</span>
								</p>
								<p>
									副标题：<span>${resTextbook.subtitle}</span>
								</p>
								<p>
									出版年：<span><fmt:formatDate pattern="yyyy-MM-dd" value="${resTextbook.publishDate}"></fmt:formatDate></span>
								</p>
								<p>
									页数：<span>${resTextbook.pages}</span>
								</p>
								<p>
									定价：<span>RMB &nbsp; ${resTextbook.price}</span>
								</p>
								<p>
									装帧：<span id="bindingTemp"></span>
								</p>
								<p>
									丛书：<span>${resTextbook.name}</span>
								</p>
								<p>
									ISBN：<span>${resTextbook.isbn}</span>
								</p>
							</div>
						</div>
						<div class="d2">
							<p>书籍简介</p>
							<span>${resTextbook.description}</span>
						</div>
						<div class="d2">
							<p>作者简介</p>
							<span>${resTextbook.writerDescription}</span>
						</div>
					</div>
				</div>
			</div>
		</div>
</div>

</body>
<script type="text/javascript">

	$(function() {
		
		if('${isCK}' == 'disable'){
			$("jctextbook_form input").prop("readonly", true);
			//$('input,select,textarea',$('div[name="jctextbook_div"]')).attr('readonly',true);
		};
		 if('${resTextbook.binding}' == 1){
			 $("#bindingTemp").html('平装');
		}else if('${resTextbook.binding}' == 2){
			 $("#bindingTemp").html('精装');
		}else if('${resTextbook.binding}' == 3){
			 $("#bindingTemp").html('Pagerback');
		}else if('${resTextbook.binding}' == 4){
			 $("#bindingTemp").html('Hardcover');
		} else{
			 $("#bindingTemp").html('${resTextbook.binding}');
		};
		
		uploadImageFile();
		
	});
	
	function editTextBook(id) {
		 var url = "${ctp}/teach/textBookMaster/resTextBook/editor?id=";
		 url = url+id;
		 $("#editTextBook").attr("href", url);
	} 
	
	 function loadViewerCatalogPage(id) {
		var url = "${ctp}/teach/textBookMaster/resTextBookCatalog/resCatalogList?resTextBookId=";
		url = url+id;
		$("#loadViewerCatalogPage").attr("href", url);
	}
	 
	function loadViewerModelPage(id) {
		var url = "${ctp}/teach/textBookMaster/catalogmodel/structrueManager?textBookId="+id;
		$("#loadViewerModelPage").attr("href", url);
	}
	 
	//教材基本信息显示
	function loadViewerPage(id) {
		var url = "${ctp}/teach/textBookMaster/resTextBook/viewer?id=";
		 url = url+id;
		 $("#loadViewerPage").attr("href", url); 
	}
	
	
</script>
</html>