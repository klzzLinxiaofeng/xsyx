<%@ page language="java"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/autocomplete/autocomplete.js"></script>
<!-- 顶部start -->
<div class="navbar navbar-inverse top-nav">
    <div class="navbar-inner">
        <div class="container">




            <c:choose>
                <c:when test='${pageContext.request.serverName eq "school.x-bull.com" or pageContext.request.serverName eq "school.10niu.cn"}'>
                    <a class="brand_10niu" href="/"></a>
                </c:when>
                <c:otherwise>
                    <a class="brand" href="/"></a>
                </c:otherwise>
            </c:choose>
            <!-- logo -->





            <!-- 右边 -->
            <div class="btn-toolbar pull-right notification-nav">
                <div class="btn-group" style="margin-right:25px;">
                    <div class="dropdown">
                        <div class="control-group">
                            <div class="controls input-icon"  id="search-form">
                                <%--<i class="" onclick="search_menu()"></i>
                                <input type="text" placeholder="请输入搜索" class="menu_name">--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="btn-group">
                    <div class="dropdown">
                        <a class="btn btn-notification dropdown-toggle">
                            <p class="change_skin"><b class="icon"></b>换肤</p>
                        </a>
                        <div class="dropdown-menu pull-right skin_content" style="">
                            <div class="skin_list">
                                <span class="span1 on"></span>
                                <span class="span2"></span>
                                <span class="span3"></span>
                                <span class="span4"></span>
                                <div class="clear"></div>
                            </div>
                            <%--<p class="caozuo" style="display: none">
                                <button class="btn btn-blue" onclick="change_skin()">保存</button>
                            </p>--%>
                        </div>
                    </div>
                </div>
                <div class="btn-group">
                    <div class="dropdown">
                        <a class="btn btn-notification dropdown-toggle">
                            <p class="view_message"><b class="icon"></b>消息</p>
                            <span class="notify-tip" id="count" style="color:#fff;">+54</span></a>
                        <div class="dropdown-menu pull-right  notice1" style="padding: 0 !important;">
                            <p class="p1" style="line-height: 46px;"><span class="on" >系统消息</span><span>教师消息</span></p>
                            <div class="notice_list">
                                <ul>
                                <c:if test="${empty unreadSysMes}">
									<dl class="x-list">
										<dd>
											<div  class="message_empty">
												目前暂时没有未读消息！
											</div>
										</dd>
									</dl>
								</c:if>
                                <c:forEach items="${unreadSysMes}" var="sysMes" begin="0" end="2" step="1" >
									<li>
                                        <span class="icon fl"></span>
                                        <input id="id" type="hidden" value="${sysMes.id}"/>
                                        <div class="fl text">
                                            <h4 class="h4">通知公告<i class="new"></i></h4>
                                            <p class="bold">
                                           		<input id="code" type="hidden" value="${sysMes.tag}"/>
                                                <span class="title">${sysMes.content }</span>
                                                <a href="javascript:void(0);" class="bold" onclick="changePage(this);">点击查看</a>
                                            </p>
                                        </div>
                                        <div class="fr m-time">
                                            <em>${sysMes.ago }</em>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
								</c:forEach>
									
                                    <!-- <li>
                                        <span class="icon fl"></span>
                                        <div class="fl text">
                                            <h4 class="h4">通知公告<i class="new"></i></h4>
                                            <p class="bold">
                                                <span class="title">您有新的通知公告！</span>
                                                <a href="javascript:void(0);" class="bold">点击查看</a>
                                            </p>
                                        </div>
                                        <div class="fr m-time">
                                            <em>25天前</em>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="icon fl"></span>
                                        <div class="fl text">
                                            <h4 class="h4">通知公告</h4>
                                            <p class="bold">
                                                <span class="title">您有新的通知公告！新的通知公告请查询！</span>
                                                <a href="javascript:void(0);" class="bold">点击查看</a>
                                            </p>
                                        </div>
                                        <div class="fr m-time">
                                            <em>25天前</em>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="icon fl"></span>
                                        <div class="fl text">
                                            <h4 class="h4">通知公告</h4>
                                            <p class="bold">
                                                <span class="title">您有新的通知公告！</span>
                                                <a href="javascript:void(0);" class="bold">点击查看</a>
                                            </p>
                                        </div>
                                        <div class="fr m-time">
                                            <em>25天前</em>
                                        </div>
                                        <div class="clear"></div>
                                    </li> -->
                                </ul>
                            </div>
                            
                            <c:if test="${empty isStudent}">
                            <div class="notice_list" style="display: none;">
                                <ul>
                                <c:if test="${empty unreadTeaMes}">
									<dl class="x-list">
										<dd>
											<div class="message_empty">
												目前暂时没有未读消息！
											</div>
										</dd>
									</dl>
								</c:if>
                                <c:forEach items="${unreadTeaMes}" var="teaMes" begin="0" end="2" step="1" >
									<li>
                                        <span class="icon fl"></span>
                                        <input id="id" type="hidden" value="${teaMes.id}"/>
                                        <div class="fl text">
                                            <h4 class="h4">通知公告<i class="new"></i></h4>
                                            <p class="bold">
                                            	<input id="code" type="hidden" value="${teaMes.tag}"/>
                                                <span class="title">${teaMes.content }</span>
                                                <a href="javascript:void(0);" class="bold" onclick="SHOW_PROFILE_TEA_MESSAGE();">点击查看</a>
                                            </p>
                                        </div>
                                        <div class="fr m-time">
                                            <em>${teaMes.ago }</em>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
								</c:forEach>
                                
                                    <!-- <li>
                                        <span class="icon fl"></span>
                                        <div class="fl text">
                                            <h4 class="h4">通知公告1</h4>
                                            <p class="bold">
                                                <span class="title">您有新的通知公告！</span>
                                                <a href="javascript:void(0);" class="bold">点击查看</a>
                                            </p>
                                        </div>
                                        <div class="fr m-time">
                                            <em>25天前</em>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="icon fl"></span>
                                        <div class="fl text">
                                            <h4 class="h4">通知公告2</h4>
                                            <p class="bold">
                                                <span class="title">您有新的通知公告！新的通知公告请查询！</span>
                                                <a href="javascript:void(0);" class="bold">点击查看</a>
                                            </p>
                                        </div>
                                        <div class="fr m-time">
                                            <em>25天前</em>
                                        </div>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="icon fl"></span>
                                        <div class="fl text">
                                            <h4 class="h4">通知公告3</h4>
                                            <p class="bold">
                                                <span class="title">您有新的通知公告！</span>
                                                <a href="javascript:void(0);" class="bold">点击查看</a>
                                            </p>
                                        </div>
                                        <div class="fr m-time">
                                            <em>25天前</em>
                                        </div>
                                        <div class="clear"></div>
                                    </li> -->
                                </ul>
                            </div>
							</c:if>
							
                            <p class="p2"><a href="javascript:void(0)" onclick="SHOW_PROFILE_MESSAGE();">查看更多</a></p>
                        </div>
                    </div>
                </div>
                <div class="btn-group">
                    <div class="dropdown">
                        <a class="btn btn-notification dropdown-toggle drop_btn" data-toggle="dropdown"
                           style="padding-left: 22px;line-height:50px;"><img
                                src="<avatar:avatar userId='${sessionScope[sca:currentUserKey()].id}'></avatar:avatar>"
                                style="width: 35px; height: 35px; border-radius: 50%;position: relative;top:3px;">
                            <span class="name">
								${sessionScope[sca:currentUserKey()].realName} <b class="icon"></b>
							</span></a>
                        <div class="dropdown-menu pull-right common_message" style="">
                            <a href="javascript:void(0)" onclick="SHOW_PROFILE();"> <i class="fa fa-user"
                                                                                       style="padding-right: 5px;"></i>基本信息
                            </a>
                            <%--<i class="x"></i>--%>
                            <a href="javascript:void(0)" onclick="SHOW_PASSWORD_EDITOR();"> <i class="fa fa-lock"
                                                                                               style="padding-right: 5px;"></i>修改密码
                            </a>
                            <a href="javascript:void(0)" onclick="SYS_LOGOUT();"> <i class="fa fa-sign-out"
                                                                                     style="padding-right: 5px;"></i>安全退出
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 顶部end -->
<script type="text/javascript">
    $(function () {
	
    	getMessage();
    	
        getCount();

        var h = $(parent.window).height();
        $('#core_iframe').height(h - 60);
        $(window).resize(function () {
            var h = $(parent.window).height();
            $('#core_iframe').height(h - 60);
        })

        $('.notice1 .p1 span').click(function () {
            $(this).siblings().removeClass('on');
            $(this).addClass('on');
            $('.notice_list').hide();
            $('.notice_list').eq($(this).index()).show();
        })

        $('.skin_list span').click(function () {
            $(this).siblings().removeClass('on');
            $(this).addClass('on');
            change_skin();
        })

        var t;
        $('.dropdown-toggle').hover(function () {
            clearTimeout(t);
            $('.dropdown-menu').hide();
            $(this).next('.dropdown-menu').slideDown('fast');
        }, function () {
            var _this = $(this);
            function close_next() {
                _this.next('.dropdown-menu').hide();
            }
            t = setTimeout(close_next, 500);
        })
        $('.dropdown-menu').hover(function () {
            clearTimeout(t);
        }, function () {
            $(this).hide();
        })
        
        var newWallpaperPath = '${newWallpaperPath}';
        if(newWallpaperPath != ''){
            $('.skin_list span').siblings().removeClass('on');
            if(newWallpaperPath == 'new_default'){
        	     $('.skin_list span.span1').addClass('on');
        	}else if(newWallpaperPath == 'new_night'){
	       	     $('.skin_list span.span2').addClass('on');
       		}else if(newWallpaperPath == 'new_three'){
	       	     $('.skin_list span.span3').addClass('on');
       		}else if(newWallpaperPath == 'new_four'){
	  	     	 $('.skin_list span.span4').addClass('on');
  			}
        	$('.skincsslittle').attr('href','/res/css/sygb/'+newWallpaperPath+'_head.css');
           // $('#core_iframe').contents().find('.index_css').attr('href','/res/css/sygb/'+newWallpaperPath+'_index.css');
        }
        //搜索enter
        $('body').on('keydown','.menu_name',function(event){
            if(event.keyCode==13){
                search_menu();
            }
        })
        //搜索提示
        var proposals = [];
        function get_menu() {
            if($("#core_iframe").contents().find('.content .i_left .mk_list:nth-last-child(2) .title').text()=='全部'){
                $("#core_iframe").contents().find('.i_left .mk_list:nth-last-child(2)  ul li').each(function() {
                    var menu_name = $(this).children().children('span').text();
                    proposals.push(menu_name)
                });
            }else{
                $("#core_iframe").contents().find('.i_left .mk_list  ul li').each(function () {
                    var menu_name = $(this).children().children('span').text();
                    proposals.push(menu_name)
                })
            }
        }
        var timer=setTimeout(get_menu, 1000);
        $(document).ready(function(){
            $('#search-form').autocomplete({
                hints: proposals,
                width: 212,
                height: 30,
                onSubmit: function(text){
                    search_menu();
                }
            });
        });

    })

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
            "url" : "${pageContext.request.contextPath}/user/center/index?module=message_center&newPage=true",
            "close" : function(index) {
            },
            "closeBtn" : false
        });
    }
    
    function SHOW_PROFILE_TEA_MESSAGE() {
        $.initWindow({
            "title": false,
            "maxmin" : false,
            "top" : "56",
            "shift" : "top",
            "height" : $(parent.window).height() - 60,
            "url" : "${pageContext.request.contextPath}/user/center/index?module=message_center&newPage=true&teaMes=true",
            "close" : function(index) {
            },
            "closeBtn" : false
        });
    }

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
    /*点击换肤*/
    function change_skin(){
        var i=$(".skin_list span.on").index();
        var newWallpaperPath='';
        if(i==0){
            $('.skincsslittle').attr('href','/res/css/sygb/new_default_head.css');
            $('#core_iframe').contents().find('.index_css').attr('href','/res/css/sygb/new_default_index.css');
            newWallpaperPath = 'new_default';
        }else if(i==1){
            $('.skincsslittle').attr('href','/res/css/sygb/new_night_head.css');
            $('#core_iframe').contents().find('.index_css').attr('href','/res/css/sygb/new_night_index.css');
            newWallpaperPath = 'new_night';
        }else if(i==2){
            $('.skincsslittle').attr('href','/res/css/sygb/new_three_head.css');
            $('#core_iframe').contents().find('.index_css').attr('href','/res/css/sygb/new_three_index.css');
            newWallpaperPath = 'new_three';
        }else if(i==3){
            $('.skincsslittle').attr('href','/res/css/sygb/new_four_head.css');
            $('#core_iframe').contents().find('.index_css').attr('href','/res/css/sygb/new_four_index.css');
            newWallpaperPath = 'new_four';
        }
        
        var url = "${ctp}/hnzxx/ui/setting/saveOrUpdate"
            $.post(url, {"_method" : "post", "newWallpaperPath" : newWallpaperPath}, function(data, status){})
        
    }
    function search_menu() {
        var menu_name=$('.menu_name').val();
        $("#core_iframe").contents().find("#search_result ul").empty();
        if(menu_name==''){
            $("#core_iframe").contents().find("#search_result").hide();
            $("#core_iframe").contents().find('.pt_list').show();
            $("#core_iframe").contents().find('.first_nav').show()
            $("#core_iframe").contents().find('.help_text').show();
            $("#core_iframe").contents().find('.i_right').show();
            $("#core_iframe").contents().find('.index_tj').css('margin-right','360px');
        }else{
            if($("#core_iframe").contents().find('.content .i_left .mk_list:nth-last-child(2) .title').text()=='全部'){
                $("#core_iframe").contents().find('.i_left .mk_list:nth-last-child(2)  ul li').each(function(){
                    var menu_html=$(this).children().children('span').text();
                        //menu_html = menu_html.replace(/<b[^>]*>([^>]*)<\/b[^>]*>/ig,"$1");
                    if(menu_html.indexOf(menu_name)!=-1){
                        var reg = new RegExp("("+menu_name +")","ig");
                        var  menu_html1 = menu_html.replace(reg,"<b style='color:#ff0000'>$1</b>");
                        $(this).find('span').html(menu_html1)
                        $(this).clone().appendTo($("#core_iframe").contents().find("#search_result ul"))
                        $(this).find('span').text(menu_html);
                    }
                })
            }else{
                $("#core_iframe").contents().find('.i_left .mk_list ul li').each(function(){
                    var menu_html=$(this).children().children('span').text();
                    if(menu_html.indexOf(menu_name)!=-1){
                        var reg = new RegExp("("+menu_name +")","ig");
                        var  menu_html1 = menu_html.replace(reg,"<b style='color:#ff0000'>$1</b>");
                        $(this).find('span').html(menu_html1)
                        $(this).clone().appendTo($("#core_iframe").contents().find("#search_result ul"))
                        $(this).find('span').text(menu_html);
                    }
                })
            }
            $("#core_iframe").contents().find("#search_result").show();
            $("#core_iframe").contents().find('.pt_list').hide();
            $("#core_iframe").contents().find('.first_nav').hide()
            $("#core_iframe").contents().find('.help_text').hide();
            $("#core_iframe").contents().find('.i_right').hide();
            $("#core_iframe").contents().find('.index_tj').css('margin-right',0);
            if( $("#core_iframe").contents().find("#search_result li").length==0){
                $("#core_iframe").contents().find('.search_empty').show();
            }else{
                $("#core_iframe").contents().find('.search_empty').hide();
            }
        }
    }

    function SYS_LOGOUT() {
        $.confirm("确定退出系统？", function() {
            <%-- 		var ennable = "<%= SysContants.COMMON_RESOURCE_ENABLE%>"; --%>
// 		if("true" === ennable) {
// 			var $requestData = {};
            <%-- 			var url = "<%= SysContants.COMMON_RESOURCE_BASE_PATH%>" + "<%= SysContants.COMMON_RESOURCE_LOGOUT_URL%>"; --%>
// 			$.ajax({
// 				async : false,
// 			    url: url,
// 			    type: "GET",
// 			    dataType: 'jsonp',
// 			    jsonp: 'callback',
// 			    jsonpCallback:"jsonpcallback",
// 			    data: $requestData,
// 			    timeout: 5000,
// 			    complete : function(json) {
// 			    	window.location = "${pageContext.request.contextPath}/logout";
// 			    }
// 			});
// 		} else {
// 			window.location = "${pageContext.request.contextPath}/logout";
// 		}
            if (top != null) {
                top.window.location.href = "${pageContext.request.contextPath}/logout";
            } else {
                window.location.href = "${pageContext.request.contextPath}/logout";
            }
        }, function() {});
    }
    
    
    function changePage(obj){
    	var code = $(obj).parent().children("#code").val();
    	//alert(code);
    	var urlCode = "/app?key=&targetDm="+code;
		window.open("${ctp}/app?key=&targetDm="+code);
    }
    
    function getMessage(){
    	
    	
    	
    }

</script>

