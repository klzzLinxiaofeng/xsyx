<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/taglib.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<script type="text/javascript">

	function getMenu(id, accessUrl, icon, name, desc) {
		return "<li ui-sref-active='active' data-desc='" + desc + "' data-url='" + accessUrl + "' data-menu-id='" + id
				+ "' class=''><a ui-sref='app.dashboard' href='javascript:void(0);'><i class='" + icon
				+ "'></i><span>" + name
				+ "</span></a></li>";
	}

	function createLv2Menu(parentId, id, name, icon, accessUrl, desc) {
		var lv2Cotrainer = $("#left_head_" + parentId); 
		var lv2Ul = $(".panel-body").find("ul[data-parentid='" + parentId + "']");
		var lv2Menu = getMenu(id,accessUrl, icon, name, desc);
// 		var homeMenu = getMenu("", "/views/embedded/mannager_index.jsp", "fa fa-home", "首页");
		if(lv2Ul.length <= 0) {
			var startUl = $("<ul id='navigation' data-parentId='" + parentId + "' class='ng-scope navigation' ripple='' nav-collapse=''>" + lv2Menu + "</ul>");
// 			var startUl = $("<ul id='navigation' data-parentId='" + parentId + "' class='ng-scope navigation' ripple='' nav-collapse=''>" + homeMenu + lv2Menu + "</ul>");
			lv2Cotrainer.append(startUl);
		} else {
			lv2Ul.append(lv2Menu);
		}
	}


	function createLv3Menu(parentId, id, name, icon, accessUrl, desc) {
		var lv3Contrainer = $("li[data-menu-id='" + parentId + "']");
		var lv3Ul = lv3Contrainer.find("ul[data-parentid='" + parentId + "']");
		var lv3Menu = getMenu(id, accessUrl, "fa fa-caret-right", name, desc);
		if(lv3Ul.length <= 0) {
			var startUl = "<ul data-parentid = '" + parentId + "' style='display: none;'>" + lv3Menu + "</ul>";
			lv3Contrainer.append(startUl);
		} else {
			lv3Ul.append(lv3Menu);
		}
	}
	
	function createLv4Menu(parentId, id, name, icon, accessUrl, desc) {
		var lv4Contrainer = $("li[data-menu-id='" + parentId + "']");
		var lv4Ul = lv4Contrainer.find("ul[data-parentId='" + parentId + "']");
		var lv4Menu = getMenu(id, accessUrl, "fa fa-caret-right", name, desc);
		if(lv4Ul.length <= 0) {
			var startUl = "<ul data-parentid = '" + parentId + "' style='display: none;'>" + lv4Menu + "</ul>";
			lv4Contrainer.append(startUl);
		} else {
			lv4Ul.append(lv4Menu);
		}
	}
</script>
<div class="left_mu" style="display: none">
<div class="leftbar clearfix lef_menu"  style=" padding-top: 6px;z-index:0;left:0px;width:200px">
	<c:forEach items="${headModules}" var="module">
		<div class="panel-body" id="left_head_${module.code}" style="display: none">
		</div>
	</c:forEach>

<!--     <div class="panel-body" ng-transclude="" id="txt"> -->
<!--         <ul id="navigation" class="ng-scope" ripple="" nav-collapse=""> -->
<!--             <li ui-sref-active="active" class="active"> -->
<!--             	<a ui-sref="app.dashboard" href="#/app/dashboard"> -->
<!--             		<i class="fa fa-dashboard" name="dashboard"></i>  -->
<!--             		<span>首页</span> -->
<!--             	</a> -->
<!--             </li> -->
<!--             <li ui-sref-active="active" class="dropdown">  -->
<!--            		<a href="#/app/mail"> -->
<!--                 	<i class="fa fa-envelope-o" name="envelope-o"></i>  -->
<!--                 	<span>后勤管理</span>  -->
<!--                 	<i class="fa fa-plus"></i> -->
<!--                 </a>  -->
<!--                 <ul style="display: block;">  -->
<!--                     <li ui-sref-active="active"> -->
<!--                     	<a href="#/app/mail/inbox"> -->
<!-- 	                    	<i class="fa fa-caret-right" ></i> -->
<!-- 	                    	<span>食堂管理</span> -->
<!-- 	                    	<i class="fa fa-plus"></i> -->
<!-- 	                    </a> -->
<!--                        	<ul style="display: block;">  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/inbox"><i class="fa fa-caret-right" ></i><span>食堂管理</span></a></li> -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/compose"><i class="fa fa-caret-right" ></i><span>商品分类</a></li>  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/single"><i class="fa fa-caret-right" ></i><span>食堂签收</a></li>  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/inbox"><i class="fa fa-caret-right" ></i><span>食堂管理</a></li> -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/compose"><i class="fa fa-caret-right" ></i> 商品分类</a></li>  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/single"><i class="fa fa-caret-right" ></i>食堂签收</a></li>  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/inbox"><i class="fa fa-caret-right" ></i> 食堂管理</a></li> -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/compose"><i class="fa fa-caret-right" ></i> 商品分类</a></li>  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/single"><i class="fa fa-caret-right" ></i>食堂签收</a></li>  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/inbox"><i class="fa fa-caret-right" ></i> 食堂管理</a></li> -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/compose"><i class="fa fa-caret-right" ></i> 商品分类</a></li>  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/single"><i class="fa fa-caret-right" ></i>食堂签收</a></li>  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/inbox"><i class="fa fa-caret-right" ></i> 食堂管理</a></li> -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/compose"><i class="fa fa-caret-right" ></i> 商品分类</a></li>  -->
<!--                			</ul>  -->
<!-- 					</li> -->
<!-- 		           	<li ui-sref-active="active"> -->
<!-- 		           		<a  href="#/app/mail/inbox"> -->
<!-- 			           		<i class="fa fa-caret-right" ></i>  -->
<!-- 			           		<span>食堂管理</span> -->
<!-- 			           		<i class="fa fa-plus_1"></i> -->
<!-- 		           		</a> -->
<!-- 		                <ul style="display: none;">  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/inbox"><i class="fa fa-caret-right" ></i>食堂管理</a></li> -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/compose"><i class="fa fa-caret-right" ></i> 商品分类</a></li>  -->
<!-- 		                    <li ui-sref-active="active"><a href="#/app/mail/single"><i class="fa fa-caret-right" ></i>食堂签收</a></li>  -->
<!-- 		                </ul>  -->
<!-- 		            </li> -->
<!-- 		            <li ui-sref-active="active"><a href="#/app/mail/single"><i class="fa fa-caret-right" ></i> 卫生管理</a></li>  -->
<!-- 		            <li ui-sref-active="active"><a href="#/app/mail/single"><i class="fa fa-caret-right" ></i> 维修信息</a></li>  -->
<!--          		</ul>  -->
<!--          	</li> -->
<!--         </ul> -->
<!--     </div> -->
</div>
	<div class="zg_btn"><a id="left_close" class="left_close left_open" href="javascript:void(0)"> <span></span> </a></div>
</div>

<c:forEach items="${modules}" var="module">
	<c:if test="${module.level == 1}">
		<script type="text/javascript">
			createLv2Menu('${module.parentCode}', '${module.code}', '${module.name}', '${module.icon}', '${module.accessUrl}', '${module.description}');
		</script>
	</c:if>
</c:forEach>

<script type="text/javascript">
	createLv2Menu('XUE_XIAO_TONG_JI', 'GE_REN_ZI_ZONG_WU_SHU_JU', '学校统计', 'fa fa-list-alt', '/res/bigScreenData/index.html', '');
	//createLv2Menu('XUE_XIAO_TONG_JI', 'GE_REN_ZI_ZONG_WU_SHU_JU', '', 'fa fa-list-alt', '/res/bigScreenData/index.html', '');
</script>

<c:forEach items="${modules}" var="module">
	<c:if test="${module.level == 2}">
		<script type="text/javascript">
			createLv3Menu('${module.parentCode}', '${module.code}', '${module.name}', '${module.icon}', '${module.accessUrl}', '${module.description}');
		</script>
	</c:if>
</c:forEach>

<c:forEach items="${modules}" var="module">
	<c:if test="${module.level == 3}">
		<script type="text/javascript">
			createLv4Menu('${module.parentCode}', '${module.code}', '${module.name}', '${module.icon}', '${module.accessUrl}', '${module.description}');
		</script>
	</c:if>
</c:forEach>


<script type="text/javascript">



	$(function(){
		var ifm = document.getElementById("core_iframe");
	    $(".lef_menu").on("click", " li a", function(event){

			var $this = $(this);
			var url = $this.parent().attr("data-url");
			var moduleId = $this.parent().attr("data-menu-id");
			if(moduleId=="KAO_QING_GUAN_LI"){
				//http://campus.test.seewo.com/mis-banpai-data-sync/api/sso/v1/dgxsyx/login?redirectUrl=https%3A%2F%2Fcampus.seewo.com%2F%23%2Fmanage%2Festhesia&token=ae97f02d-eea2-4970-af1a-a9d6de92c7f8
				window.open("http://campus.seewo.com/mis-banpai-data-sync/api/sso/v1/dgxsyx/login?redirectUrl=https%3A%2F%2Fcampus.seewo.com%2F%23%2Fmanage%2Festhesia&token=fa929aae-525a-4f5d-8d80-45e2f73e3ba0","_blank");
				return;
			}else if(moduleId=="WOKEBIAO"){
				window.open(url,"_blank");
				return;
			}else if(moduleId=="JIZUWOKE"){
				window.open(url,"_blank");
				return;
			}else if(moduleId=="CHENGZHANGBAOGAO"){
				window.open(url,"_blank");
				return;
			}else if(moduleId=="JIAOZHIGONG"){
				window.open(url,"_blank");
				return;
			}else if(moduleId=="GE_REN_ZI_ZONG_WU_SHU_JU"){
				window.open(url);
				return;
			}


	        if($(this).parent().siblings().children("a").children().filter(".fa-minus")){
	            $(this).parent().siblings().children("a").children().filter(".fa-minus").removeClass("fa-minus").addClass("fa-plus");
	        } 
	        $(this).parent().siblings().children("ul").stop().slideUp(300);
	        $(this).next().slideToggle(700);
	        if($(this).children().eq(2).hasClass("fa-minus")){
	            $(this).children().eq(2).removeClass("fa-minus").addClass("fa-plus");
	        } else{
	            $(this).children().eq(2).removeClass("fa-plus").addClass("fa-minus");
	        }
	        

			
			toAccessUrl(url, moduleId, ifm);
			
			event.stopPropagation();
	    });
	    
	    $(".lef_menu").on("click", ".navigation>li>a", function(){
	    	$(".navigation>li").removeClass("active");
	        $(this).parent().addClass("active");
	    });
	    
	    $(".lef_menu").on("click", ".navigation>li>ul a", function(){
	    	
	    	$(".navigation>li>ul a").removeClass("blue_1");
	        $(this).addClass("blue_1");
	    });
	    
	    var menus = $(".panel-body li");
	    $.each(menus, function(index, value) {
	    	var $this = $(value);
	    	if($this.find("ul").length > 0) {
	    		$this.find("a:first").append("<i class='fa fa-plus'></i>");
	    	}
	    });
	    
	});
	
	/* var h=$(parent.window).height()-76;
	$(function(){
		$(".leftbar").css("height",h);
	}) ; */
	$(document).ready(function() {  
		$(".leftbar").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
		var h = document.documentElement.clientHeight;
		$(".left_mu").css("height",h);
	}); 

</script>
