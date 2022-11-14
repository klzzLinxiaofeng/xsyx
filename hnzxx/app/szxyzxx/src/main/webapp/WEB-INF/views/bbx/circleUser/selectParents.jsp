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
.btnFont {
    text-align: center;
    vertical-align: middle;
    display: inline-block;
}
input .middle {
	vertical-align: middle;
}

.stepy_tabby ul li {
    margin-top: 10px;
}
.stepy_tabby ul li, .stepy_tabby ul li {
    list-style: outside none none;
    float: left;
    margin-right: 10px;
    margin-bottom: 10px;
}
li {
    line-height: 20px;
}
.pull-left {
    float: left;
}
ul, ol {
    padding: 0px;
    margin: 0px 0px 10px 25px;
}
.stepy_tabby ul li {
    margin-top: 10px;
}
li {
    line-height: 20px;
}
.stepy_tabby ul li a {
    padding: 2px;
}
.stepy_tabby ul li a:link, .stepy_tabby ul li a:visited, .stepy_tabby ul li a:link, .stepy_tabby ul li a:visited {
    background-color: #FFF;
    color: #000;
}
.stepy_tabby ul li.active a, .stepy_tabby ul li.active a {
    background-color: #FFB401 !important;
}
.stepy_tabby ul li a, .stepy_tabby ul li a {
    display: block;
    padding: 5px 10px;
    border-radius: 5px;
}
a:focus {
    outline: thin dotted #333;
    outline-offset: -2px;
}
a {
    color: #08C;
    text-decoration: none;
}
a {
    transition: all 0.2s ease-out 0s;
}
.stepy_tabby ul li, .stepy_tabby ul li {
    list-style: outside none none;
}
li {
    line-height: 20px;
}


.cont_tabby ul li {
    margin-top: 10px;
}
.cont_tabby ul li, .cont_tabby ul li {
    list-style: outside none none;
    float: left;
    margin-right: 10px;
    margin-bottom: 10px;
}
.cont_tabby ul li {
    margin-top: 10px;
}
.cont_tabby ul li a {
    padding: 2px;
}
.cont_tabby ul li a:link, .cont_tabby ul li a:visited, .cont_tabby ul li a:link, .cont_tabby ul li a:visited {
    background-color: #FFF;
    color: #000;
}
.cont_tabby ul li.active a, .cont_tabby ul li.active a {
    background-color: #FFB401 !important;
}
.cont_tabby ul li a, .cont_tabby ul li a {
    display: block;
    padding: 5px 10px;
    border-radius: 5px;
}
.cont_tabby ul li, .cont_tabby ul li {
    list-style: outside none none;
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
						<div class="select_b">
							<div class="select_div" style="background-color: #F8F8F8; width: 100%">
								<div class="stepy_tabby pull-left" id="grade_stepy_tabby">
									<ul >
										<c:forEach items="${gradeList}" var="item" > 
											<li>
												<a href="#" draggable="false" id="${item.id}">${item.name}</a>
											</li>
										</c:forEach> 
									</ul>
								</div>	
							</div>
						</div>
							
						<div class="widget-container" style = "width:820px;height:350px;overflow:auto;"> 
							<table class="responsive table table-striped" id="selectParents_list_content" >
								<jsp:include page="./selectParentsList.jsp" />
							</table>
						</div>	
							<!-- <div id="searchButton" class="stepy_tabby pull-left" style="padding-top:10px;">
								<button type="button" class="btn btn-primary" style="float:right;" onclick="submitData()">增加</button>
							</div> -->
						  
				</div>
				<div style="padding-top: 0px;">
							<p class="btn_link" style="float: right;">
								<a id="confirmBtn" href="javascript:void(0)" onclick="submitData();" class="a3"><i class="fa"></i>确定</a>
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
		// 给年级班级选项绑定点击事件
		$("a",".stepy_tabby").click(function(){		
			if($(this).parent().hasClass('active')){
				return;
			}
			$(this).parent().addClass('active').siblings().removeClass("active");
			search();
		});
		
		
		

		//$("#grade_stepy_tabby a").first().click();
	});
	
	function selectName(obj){
			if($(obj).parent().hasClass('active')){
				$(obj).parent().removeClass('active');			
			}else{
				$(obj).parent().addClass('active');
			}
	}
	

	function search() {
		var id = "selectParents_list_content";
		var url = "/bbx/circle/user/selectParents?circleId="+${circleId};
		val = {};
		val.gradeId =  $("#grade_stepy_tabby li.active a").attr("id");
		myPagination(id, val, url);
		
	}

	
	function submitData() {
		var userIds = "";
		$("#name_cont_tabby li.active a").each(function(){
			userIds = userIds + $(this).attr("id") + ",";
		});
		
		if(userIds == ""){
			$.error("请选择家长");
			return;
		}
		
		var loader = new loadLayer();
		loader.show();
		$.post('${pageContext.request.contextPath}/bbx/circle/user/saveAddedCircleUsers?', {
			circleId : '${circleId}', userIds : userIds, roleCode : 2
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
		
	}

</script>
</html>