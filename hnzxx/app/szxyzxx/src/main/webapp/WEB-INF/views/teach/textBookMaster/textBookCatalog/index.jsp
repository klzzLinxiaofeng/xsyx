<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<title></title>
</head>
<body style="background-color:#FFFFFF;">
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教材目录" name="title" /> 
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets">
					<div class="ts_top">
						书籍查看
						<p>
						<c:choose>
							<c:when test="${list == 'resList' }">
								<a href="${ctp}/teach/textBookMaster/textBook/index?sub=resFirst" >图书列表</a>
							</c:when>
							<c:otherwise>
								<a href="${ctp}/teach/textBookMaster/textBook/index" >图书列表</a>
							</c:otherwise>
						</c:choose>
						<a href="" id="loadViewerPage"  onclick="loadViewerPage('${catalogList[0].testBookId}');">基本信息</a>
						<a href="" id="loadViewerCatalogPage"  onclick="loadViewerCatalogPage('${catalogList[0].testBookId}');" class="on">教材目录</a>
						</p>
					</div>
					<div class="catalog">
						<div class="dl">
						<c:if test="${list != 'resList'}">
							<a  id="downLoadModel" class="a1"  href="" onclick="downLoadModel();"><i class="fa fa-download"></i>下载模板</a>
						</c:if>
							<a id="downLoadExcel"  class="a2" href="" onclick="downLoadExcel(${catalogList[0].testBookId});" class="a2" ><i class="fa fa-plus"></i>导出excel</a>
						<c:if test="${list != 'resList'}">	
							<a id="upLoadExcel" class="a3" href=""  onclick="upLoadExcel(${catalogList[0].testBookId});" class="a3"><i class="fa fa-plus"></i>导入excel</a>
						</c:if>
						</div>
					<div class="content-widgets">
						<div class="widget-container">
							<form id="catalogForm" style="margin:0;"></form>
							<jsp:include page="./list.jsp" />
							
						</div>
					</div>
					</div>
				</div>
			</div>
		</div>
		<%-- <div class="top">
			<div>
				<a href="${ctp}/teach/textBookMaster/textBook/index" >图书列表</a>
				<a href="" id="loadViewerPage"  onclick="loadViewerPage('${catalogList[0].testBookId}');">基本信息</a>
				<a href="" id="loadViewerCatalogPage"  onclick="loadViewerCatalogPage('${catalogList[0].testBookId}');">教材目录</a>
			</div>
			
		</div> --%>
		</div>
		<%-- <div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					
						<a  id="downLoadModel"  href="" onclick="downLoadModel();"><i class="fa fa-download"></i>下载模板</a>
						<a id="downLoadExcel"  href="" onclick="downLoadExcel(${catalogList[0].testBookId});" class="a2" ><i class="fa fa-plus"></i>导出excel</a>
						<a id="upLoadExcel" href=""  onclick="upLoadExcel(${catalogList[0].testBookId});" class="a3"><i class="fa fa-plus"></i>导入excel</a>
					
					<div class="light_grey"></div>
					<div class="content-widgets">
						<div class="widget-container">
							<form id="catalogForm"></form>
							<jsp:include page="./list.jsp" />
							
						</div>
					</div>
				</div>
			</div>
		</div> --%>

</body>
<script type="text/javascript">
    //导出对话框
	function downLoadModel(){
		
		var url = "${pageContext.request.contextPath}/teach/textBookMaster/textBookCatalog/downLoadModel";
		  $("#downLoadModel").attr("href", url);
	} 
	
	//导出对话框
	function downLoadExcel(id){
		  var url = "${pageContext.request.contextPath}/teach/textBookMaster/textBookCatalog/downLoadExcel?textBookId=";
		  url = url+id;
		  $("#downLoadExcel").attr("href", url);
		  
	}
	//导入对话框
	function upLoadExcel(id){
		var url = "${pageContext.request.contextPath}/teach/textBookMaster/textBookCatalog/upLoadInfoPage?textBookId=";
		  url = url+id;
		$.initWinOnTopFromLeft("导入excel", url, '800', '400');
	} 
	//目录内容
	function loadViewerCatalogPage(id) {
		var url = "${ctp}/teach/textBookMaster/textBookCatalog/catalogList?sub=${list}&textBookId=";
		 url = url+id;
		 $("#loadViewerCatalogPage").attr("href", url);
	}
	
	//教材基本信息显示
	function loadViewerPage(id) {
		
		var url = "${ctp}/teach/textBookMaster/textBook/viewer?sub=${list}&id=";
		 url = url+id;
		 $("#loadViewerPage").attr("href", url);
	}
	
	function loadViewerModelPage(id) {
		var url = "${ctp}/teach/textBookMaster/catalogmodel/model?textBookId="+id;
		$("#loadViewerModelPage").attr("href", url);
	}
</script>
</html>