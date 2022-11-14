<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${ctp}/res/css/extra/jcml.css" rel="stylesheet">
<title></title>
<style>
.select_b .select_div select{
	margin-bottom:0;
}
#keyword{height:16px;line-height:16px;margin:0 0 0 15px;}
.select_b{background-color:#fff;border-bottom:1px solid #d9dfe7}
.book{background:none;margin:8px 15px;border:1px solid #9ccfef}
/* .book ul li .intro{width:160px;} */
/* .book ul li{width:290px;} */
</style>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="教材列表" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="widget-head">
						<h3 class="jc_top">
							教材列表
							<p class="btn_link" style="float: right;margin:0">
								<a href="javascript:void(0)" class="a2"
									onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
								<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}">
								</c:if>
								<c:choose>
									<c:when test="${list == 'resList'}">
										<a href="javascript:void(0)" id="loadCreatePage" class="a3" onclick="setToSchool();"><i class="fa fa-plus"></i>设为校本</a>
									</c:when>
									<c:otherwise>
										<a href="javascript:void(0)" id="loadCreatePage" class="a7" onclick="loadCreatePage();"><i class="fa fa-plus"></i>添加书籍</a>
									</c:otherwise>
								</c:choose>
							</p>
						</h3>
					</div>
					
					<div class="content-widgets">
							<div class="select_b">
									<div class="select_div">
									<span>学段:
										<select id="stageCode" name="stageCode" class="chzn-select"style="width:160px;" onchange="findTextBook('subjectCode');">
										<c:forEach items="${stageCodeMap}" var="map">
											<option value="${map.value }">${map.key }</option>
										</c:forEach>
										</select>
										</span>
										</div>
										
										<div class="select_div">
										<span>科目:<select id="subjectCode" name="subjectCode" class="chzn-select"style="width:160px;"  onchange="findTextBook('publisherId');">
										<option value="">请选择</option>
										</select></span>
										</div>
										
										<div class="select_div">
										<span>版本:<select id="publisherId" name="publisherId" class="chzn-select"style="width:160px;"  onchange="findTextBook('gradeCodeVolumn');">
										<option value="">请选择</option>
										</select></span>
										</div>
										<div class="select_div">
										<span>年级册次:<select id="gradeCodeVolumn" name="gradeCodeVolumn" class="chzn-select"style="width:160px;">
										
										<option value="">请选择</option>
										</select></span>
								</div>
								<div class="select_div">
										<span>书籍类型:
										<select id="type" name="type" class="chzn-select"style="width:160px;">
											<option value="">全部书籍类型</option>
											<option value="1">教课书</option>
											<option value="2">教辅书</option>
											<option value="3">其他</option>
										</select></span>
								</div>
									
								<div class="select_div">
									<input type="text" name ="keyword" id="keyword" placeholder="请输入关键字"></input>
								</div>
								  <button type="button" class="btn btn-primary" onclick="search()" style="float:left;">查询</button>
								
								
								<div class="clear"></div>
							</div>
							
							<div class="book" style="margin-bottom:15px;">
								<ul id="jcTextbook_list_content">
								<jsp:include page="./list.jsp" />
								</ul>
								<div class="clear"></div>
							</div>
							
							<jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
								<jsp:param name="id" value="jcTextbook_list_content" />
								<jsp:param name="url" value="/teach/textBookMaster/textBook/index?sub=${list }&dm=${param.dm}" />
								<jsp:param name="pageSize" value="${page.pageSize}" />
							</jsp:include>
							<div class="clear"></div>
							
							<form id="jctextbook_form" class="form-horizontal">
								<input type="hidden"  id="isHidden" name ="isHidden" value=""></input>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script type="text/javascript">
	var totalAdd = 0;
	function search() {
		var val = {};
		
		var subjectCode = $("#subjectCode").val();
		if (subjectCode != null && subjectCode != "") {
			val.subjectCode = subjectCode;
		}
		
		var gradeCodeVolumn = $("#gradeCodeVolumn").val();
		if (gradeCodeVolumn != null && gradeCodeVolumn != "") {
			val.gradeCodeVolumn = gradeCodeVolumn;
		}
		
		var version = $("#publisherId").val();
		if (version != null && version != "") {
			val.version = version;
		}
		var type = $("#type").val();
		if (type != null && type != "") {
			val.type = type;
		}
		
		var stageCode = $("#stageCode").val();
		if (stageCode != null && stageCode != "") {
			val.stageCode = stageCode;
		}
		
		var keyword = $("#keyword").val();
		if (keyword != null && keyword != "") {
			val.keyword = keyword;
		}
		var id = "jcTextbook_list_content";
		var url = "/teach/textBookMaster/textBook/index?sub=${list}&dm=${param.dm}";
		myPagination(id, val, url);
	}

	function setToSchool() {
 		
		var data="";
		$(":checkbox:checked").each(function(index,element) {
			var textbookId = $(element).val();
			if(index==$(":checkbox:checked").length-1) {
				data+="{\"id\":"+textbookId+"}";
			} else {
				data+="{\"id\":"+textbookId+"},";
			}
		});
		if(data=="") {
			return;
		}
		
		$.ajax({  
			type:"post",
			async:false,
			url:"${ctp}/teach/textBookMaster/resTextBook/bantch/creator",
			data:{"data":"["+data+"]"},
			success:function(data) {
				$.refreshWin();
			}  
		}); 
	}
	//添加教材
	function loadCreatePage() {
		
		// var url = "${ctp}/teach/textBookMaster/textBook/creator";
		 
		 //$("#loadCreatePage").attr("href", url); 
		
		$.initWinOnTopFromLeft('创建', '${ctp}/teach/textBookMaster/textBook/creator', '800', '600');
	}
	//  加载编辑对话框
	function loadEditPage(id) {
		
		$.initWinOnTopFromLeft('教材编辑', '${ctp}/teach/textBookMaster/textBook/editor?id=' + id, '700', '700');
	}
	//教材基本信息显示
	function loadViewerPage(id,aid) {
		var url = "${ctp}/teach/textBookMaster/textBook/viewer?sub=${list}&id=";
		url = url+id;
		$("#"+aid).attr("href", url);
	}

	//查看目录内容
	function loadViewerCatalogPage(id) {
		var url = "${ctp}/teach/textBookMaster/textBookCatalog/catalogList?sub=${list}&textBookId=";
		 url = url+id;
		 $("#loadViewerCatalogPage").attr("href", url);
	} 
	function loadEditCatalogPage(id) {
		$.initWinOnTopFromLeft('目录编辑', '${ctp}/teach/textBookMaster/textBookCatalog/editor?id=' + id, '700', '300');
	}
	function uploadCatalogPage(id) {
		upLoadExcel(id);
	}
	
	//导入对话框
	function upLoadExcel(id){
		var url = "${pageContext.request.contextPath}/teach/textBookMaster/textBookCatalog/upLoadInfoPage?textBookId=";
		  url = url+id;
		$.initWinOnTopFromLeft("导入excel", url, '800', '400');
	} 
	
	// 	删除对话框
	function deleteObj(obj,id,hasLink) {
		
		if(hasLink == 'true'){
			$.error("要删除的教材有内容，不允许删除！");
		}else{
			$.confirm("确定执行此次操作？", function() {
				executeDel(obj, id);
			});
		}
		
	}

	// 	执行删除
	function executeDel(obj, id) {
		$.post("${ctp}/teach/textBookMaster/textBook/" + id, {"_method" : "delete"}, function(data, status) {
			if ("success" === status) {
				if ("success" === data) {
					$.success("删除成功");
					$("#" + id + "_tr").remove();
				} else if ("fail" === data) {
					$.error("删除失败，系统异常", 1);
				}
			}
		});
	}
	
	
	function findTextBook(name) {
		 var selectVal = '#'+name;
		  $(selectVal).empty();
			 $.ajax({  
				type:"post",  
				url:"${ctp}/teach/textBookMaster/textBook/findTextBookNoHidden",  
				data:{
					'stageCode':$('#stageCode').val(),
					'subjectCode':$('#subjectCode').val(),
					'gradeCodeVolumn':$('#gradeCodeVolumn').val(),
					'publisherId':$('#publisherId').val(),
					'type':name 
				},
					
				success:function(data) {  
				var map =  eval("("+data+")");
				
				$.each(map,function(key,values){  
				$("<option value="+values+">"+key+"</option>").appendTo(selectVal);   
				}  
				);   
				}  
			});   
		}
	
	function changDisplay(id,isHidden){
		//$("#id").val(id);
		$("#isHidden").val(isHidden);
		saveOrUpdate(id);
		
	}
	
	//保存或更新修改
	function saveOrUpdate(id) {
			
			var loader = new loadLayer();
			var $id = id;
			var $requestData = formData2JSONObj("#jctextbook_form");
			var url = "${ctp}/teach/textBookMaster/textBook/creator";
			if ("" != $id) {
				
				$requestData._method = "put";
				url = "${ctp}/teach/textBookMaster/textBook/" + $id;
			}
			loader.show();
			$.post(url, $requestData, function(data, status) {
				if("success" === status) {
					$.success('操作成功');
					data = eval("(" + data + ")");
					if("success" === data.info) {
						if(parent.core_iframe != null) {
 							parent.core_iframe.window.location.reload();
 						} else {
 							parent.window.location.reload();
 						}
						$.closeWindow();
					} else {
						$.error("操作失败");
					}
				}else{
					$.error("操作失败");
				}
				loader.close();
			});
		}
	
</script>
</html>