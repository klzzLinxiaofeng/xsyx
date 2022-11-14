<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@ page import="platform.education.message.contants.MessageContants"%>
<%@include file="/views/embedded/taglib.jsp"%>
<style>
	.school_name {
		font-size: 30px;
		line-height: 50px;
		color: #EDEDED;
		font-famlily: "微软雅黑";
		margin: 0;
		font-weight: bold;
	}

	.notification-nav .btn i {
		display: block !important;
		padding: 16px 0 !important;
		text-align: center;
		width: auto !important;
	}
	.dropdown-menu{
		width:120px;
		min-width:120px;
	}
	.nav-collapse{
		height:56px;
		float:left;
		/* overflow:hidden;  */
	}
	i.x{
		height:1px;
		width:142px;
		background: #e5e4e4;
		float: left;
		margin-left:-11px;
	}
	.top-nav{
		width:100%;
	}
</style>
<div class="navbar navbar-inverse top-nav">
	<div class="navbar-inner">
		<div class="container">
			<a class="brand" href="javascript:void(0)">
				<c:choose>
					<c:when test='${pageContext.request.serverName eq "school.x-bull.com" or pageContext.request.serverName eq "school.10niu.cn"}'>
						<img src="${pageContext.request.contextPath}/res/plugin/falgun/images/logo_10niu.png" alt="Falgun">
					</c:when>
					<c:otherwise>
						<img src="${pageContext.request.contextPath}/res/plugin/falgun/images/logo_new.png" alt="Falgun">
					</c:otherwise>
				</c:choose>

			</a>
			<div class="nav-collapse" id="main_head_menus">
				<a href="javascript:void(0)" class="go_left"></a>
				<div style="float:left;overflow:hidden;padding:0 20px;width:500px;" class="ul_div">
					<ul class="nav" style="margin:0;width:10000px;position:relative;">
						<li data-url="/index/mainPage" data-menu-id="" class="hover"><a href="javascript:void(0);">首页</a></li>
						<c:forEach items="${headModules}" var="module">
							<li data-url="${module.accessUrl}" data-menu-id="${module.code}" ><a href="javascript:void(0);">${module.name}</a></li>
						</c:forEach>
						<!--                     <li><a href="javascript:void(0)">基础数据</a></li> -->
						<!--                     <li class="hover"><a href="javascript:void(0)">校务管理</a></li> -->
					</ul>
				</div>
				<a href="javascript:void(0)" class="go_right"></a>
			</div>
			<div class="btn-toolbar pull-right notification-nav">
				<div class="btn-group" id="message">
					<div class="dropdown">
<!-- 						<a class="btn btn-notification dropdown-toggle"><i class="fa  fa-envelope-o" onclick="SHOW_PROFILE_MESSAGE();" style="font-size:24px;"> -->
<!-- 							<span class="notify-tip" id="count" style="color:#fff;"></span></i></a> -->
					</div>
				</div>
				<div class="btn-group">
					<div class="dropdown" >
						<a class="btn btn-notification dropdown-toggle drop_btn"
						   data-toggle="dropdown" style="width: 155px;line-height:50px;"><img
								src="<avatar:avatar userId='${sessionScope[sca:currentUserKey()].id}'></avatar:avatar>"
								style="width: 35px; height: 35px; border-radius: 50%" >
							<span>
								${sessionScope[sca:currentUserKey()].realName} <b class="fa  fa-angle-down"></b>
							</span></a>
						<div class="dropdown-menu pull-right " style="background: #fff;margin-right: 4px;widht:100px;border: 1px #b7d3ea solid; -moz-box-shadow:0 0 3px 0 #c5c5c5;-webkit-box-shadow:0 0 3px 0 #c5c5c5;box-shadow:0 0 3px 0 #c5c5c5;">
							<!-- 							<a -->
							<!-- 								style="width: 130px; color: #333; font-size: 18px; display: block; padding-left: 15px; line-height: 33px;" -->
							<!-- 								href="javascript:void(0)"> <i class="icon-lock" -->
							<!-- 								style="padding-right: 5px;"></i>Lock -->
							<!-- 							</a> -->
							<a style="font-family:宋体;width: 130px; color: #4a555b; font-size: 12px; display: block; padding:0; line-height: 33px;"
							   href="javascript:void(0)" onclick="SHOW_PROFILE();"> <i class="fa fa-user" style="padding-right: 5px;"></i>基本信息
							</a>

							<c:if test="${param.module == 'bbx' }">
								<a style="font-family:宋体;width: 130px; color: #4a555b; font-size: 12px; display: block; padding:0; line-height: 33px;"
								   href="javascript:void(0)" onclick="SHOW_ROLE_SWITCHER();"> <i class="fa fa-retweet" style="padding-right: 5px;"></i>角色切换
								</a>
							</c:if>

							<i class="x"></i>
							<a style="font-family:宋体;width: 130px; color: #4a555b; font-size: 12px; display: block; padding:0; line-height: 33px;"
							   href="javascript:void(0)" onclick="SHOW_PASSWORD_EDITOR();"> <i class="fa fa-lock" style="padding-right: 5px;"></i>修改密码
							</a>
							<a style="font-family:宋体;width: 130px; color: #4a555b; font-size: 12px; display: block; padding:0; line-height: 33px;"
							   href="javascript:void(0)" onclick="SYS_LOGOUT();"> <i class="fa fa-sign-out" style="padding-right: 5px;"></i>安全退出
							</a>
						</div>
					</div>
				</div>
				<!-- <div class="btn-group">
					<div>
						<a class="btn btn-notification" style="color:rgba(255, 255, 255, 0.7);background: #0E4878 !important;"><i class="fa fa-comments"
							style="padding: 14px 0 !important"></i></a>
					</div>
				</div> -->
			</div>
			<%-- 			<iframe id="comet-frame" style="display: none;" src="${pageContext.request.contextPath}/message/center/getMessageCount"></iframe> --%>
		</div>
	</div>
</div>
<script type="text/javascript">
    var loader = new loadDialog();
    $(function() {

        getCount();
        $("#main_head_menus ul li", window.top.document).click(function(){
        });
        var time;
        $(".drop_btn").hover(function(){
            clearTimeout(time);
            $(this).parent().addClass("open");
        },function(){
            time=setTimeout("$('.drop_btn').parent().removeClass('open')",1000)
            /* $(".drop_btn").parent().removeClass("open") */
        });
        $(".dropdown-menu").hover(function(){
            clearTimeout(time);
        },function(){
            $(".drop_btn").parent().removeClass("open");
        })
        var ifm = document.getElementById("core_iframe");
        $("#main_head_menus .nav").on("click", "li", function(event) {
            var $this = $(this);
            var url = $this.attr("data-url");
            var moduleId = $this.attr("data-menu-id");
            if(moduleId!="BAN_BAN_XING"){
                toAccessUrl(url, moduleId, ifm);
            }else{
                url=url.replace(/@.{1,}?$/,"");
                window.parent.location="${pageContext.request.contextPath}"+url;
            }

            $("#main_head_menus .nav li").removeClass("hover");
            $this.addClass("hover");
            switchLeftMenu(moduleId);
            event.stopPropagation();
        });

        $(ifm).load(function() {
            loader.close();
        });
        /* 顶部滚动 */
        var window_w=$("body").width();
        var logo_w=$(".navbar .brand img").css("width");
        var right_w=$(".pull-right").width();
        var nav_w=window_w-183-231-192;
        $(".nav-collapse .ul_div").css("width",nav_w);
        var num=$(".navbar .nav li").length;
        /* 总的ul有多长 */
        var w1=0
        for(var i=0;i<num;i++){
            var width = $(".navbar .nav li").eq(i).width();
            w1=w1+parseInt(width);
        }
        if(nav_w<w1){
            $(".nav-collapse .go_left,.nav-collapse .go_right").show();
            var w2=0,i2;
            for(i2=0;i2<num;i2++){
                var width = $(".navbar .nav li").eq(i2).width();
                w2=w2+parseInt(width);
                if(w2>nav_w){
                    break;
                }
            }
            $(".nav-collapse .go_right").click(function(){
                var i1=$(".top-nav .nav > li:hidden").length;
                /* i1=5;num=13;i2=6 */
                if(i1<num-8){

                    for(var j=0;j<4;j++) {
                        $(".top-nav .nav > li").eq(i1+j).hide(500);
                    }
                }else{
                    var k=num-i1-i2;
                    for(var j=0;j<k;j++) {
                        $(".top-nav .nav > li").eq(i1+j).hide(500);
                    }
                }
            });
        }
        $(".nav-collapse .go_left").click(function(){
            var i3=$(".top-nav .nav > li:hidden").length;
            for(var j=0;j<4;j++) {
                $(".top-nav .nav > li").eq(i3-1-j).show(500);
            }
        });
        $(window).resize(function(){
            var window_w=$(".top-nav").css("width");
            var right_w=$(".pull-right").css("width");
// 			var logo_w=$(".navbar .brand img").css("width");
// 			var nav_w=parseInt(window_w)-parseInt(logo_w)-parseInt(right_w)-172;
            var nav_w=parseInt(window_w)-183-parseInt(right_w)-192;
            $(".nav-collapse .ul_div").css("width",nav_w);


            /* 如果位置够的话，左右箭头不出来 */
            if(nav_w<w1){

                $(".nav-collapse .go_left,.nav-collapse .go_right").show();
                /* 就进来看到的ul有多长，有几个 */
                var w2=0,i2;
                for(i2=0;i2<num;i2++){
                    var width = $(".navbar .nav li").eq(i2).width();
                    w2=w2+parseInt(width);
                    if(w2>nav_w){
                        break;
                    }
                }
                $(".nav-collapse .go_right").click(function(){
                    var i1=$(".top-nav .nav > li:hidden").length;
                    /* i1=5;num=13;i2=6 */
                    if(i1<num-8){

                        for(var j=0;j<4;j++) {
                            $(".top-nav .nav > li").eq(i1+j).hide(500);
                        }
                    }else{
                        var k=num-i1-i2;
                        for(var j=0;j<k;j++) {
                            $(".top-nav .nav > li").eq(i1+j).hide(500);
                        }
                    }
                });

            }
            else{
                $(".nav-collapse .go_left,.nav-collapse .go_right").hide();
                $(".top-nav .nav > li").show(500)
            }

        });

    });

    function pushMessage(data) {
        data = data.split(",");
        var currentUser = "${sessionScope[sca:currentUserKey()].id}";
        if(currentUser == data[1]){
            $("#count").html("");
            if(data[0] != 0){
                $("#count").html("+" + data[0]);
            }else{
                $("#count").html("");
            }
        }
    }
    function getCount(){
        var url = "${pageContext.request.contextPath}/message/center/getCount";
        $.post(url,function(data,status){
            if(status == "success"){
                if(data != 0){
                    $("#count").html("+" + data);
                }else{
                    $("#count").html("");
                }
            }
        });
    }
    function toAccessUrl(url, moduleId, ifm) {
        if(url != null && "" != url && url != "undefind") {

            if(url.indexOf("@(oa_url)") == -1 && url.indexOf("@(rs_url)") == -1) {
                if(url.indexOf("http") == -1 && url.indexOf("HTTP") == -1) {
                    url = "${pageContext.request.contextPath}" + url;
                }
            }

            if(url.indexOf("?") != -1) {
                url = url + "&dm=" + moduleId;
            } else {
                url = url + "?dm=" + moduleId;
            }

            if(url.indexOf("@(rs_url)") != -1) {

                var serverName = "${pageContext.request.serverName}";
                var basePath = "<%= SysContants.COMMON_RESOURCE_BASE_PATH%>";
                if (basePath.indexOf(";") != -1) {
                    var basePaths = basePath.split(";");
                    for (var i = 0;i < basePaths.length; i++)
                    {
                        if (basePaths[i].indexOf(serverName) != -1) {
                            basePath = basePaths[i].substring(basePaths[i].indexOf("=") + 1, basePaths[i].length);
                        }

                    }
                }

                url = url.replace("@(rs_url)", basePath);
                var schoolId = "${sessionScope[sca:currentUserKey()].schoolId}";
                if(url.indexOf("?")!=-1){
                    url = url + "&relateAppId="+"<%= SysContants.SYSTEM_APP_ID%>";
                }else{
                    url = url + "?relateAppId="+"<%= SysContants.SYSTEM_APP_ID%>";
                }
                url = url + "&relateSchoolId="+ schoolId +"";
            }

            if(url.indexOf("@(username)") != -1) {
                url = url.replace("@(username)", "${sessionScope[sca:currentUserKey()].userName}")
            }
            if(url.indexOf("@(target=_blank)") != -1) {
                url = url.replace("@(target=_blank)", "");
                window.open(url, "_blank");
            } else if (url.indexOf("@(target=_self)") != -1) {
                url = url.replace("@(target=_self)", "");
                window.open(url, "_self");
            } else {
                if (moduleId != "") {
                    loader.show();
                }
                $(ifm).attr("src", url);
            }
        }
    }

    function switchLeftMenu(id) {
        $(".panel-body").hide();
        $("#left_head_" + id).fadeIn();
    }

    $(function() {
        var moduleId = $("#main_head_menus .nav li:first").attr("data-menu-id");
        $("#left_head_" + moduleId).fadeIn();
    });

    function SHOW_PROFILE() {
        $.initWindow({
            "title": false,
            "maxmin" : false,
            "top" : "56",
            "shift" : "top",
            "height" : $(parent.window).height() - 60,
            "url" : "${pageContext.request.contextPath}/user/center/index",
            "close" : function(index) {
            },
            "closeBtn" : false
        });
    }

    function SHOW_PASSWORD_EDITOR() {
        $.initWindow({
            "title": false,
            "maxmin" : false,
            "top" : "56",
            "shift" : "top",
            "height" : $(parent.window).height() - 60,
            "url" : "${pageContext.request.contextPath}/user/center/index?module=password_editor",
            "close" : function(index) {
            },
            "closeBtn" : false
        });
    }

    function SHOW_PROFILE_MESSAGE() {
        $.initWindow({
            "title": false,
            "maxmin" : false,
            "top" : "56",
            "shift" : "top",
            "height" : $(parent.window).height() - 60,
            "url" : "${pageContext.request.contextPath}/user/center/index?module=message_center",
            "close" : function(index) {
            },
            "closeBtn" : false
        });
    }

    function SHOW_ROLE_SWITCHER() {
        $.initWinOnTopFromLeft('角色切换', '${pageContext.request.contextPath}/bbx/role/switcher', '700', '480');
    }
</script>