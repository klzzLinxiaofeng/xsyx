<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/plugin/falgun/css/fullcalendar.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/sygb/new_default_index.css" rel="stylesheet" class="index_css">
<link href="${pageContext.request.contextPath}/res/qyjx/css/button.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/common/animate.min.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/res/js/common/jquery/jquery-1.11.0.min.js"></script>
<script src="${pageContext.request.contextPath}/res/plugin/falgun/js/fullcalendar.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/date/lunar_calendar.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
<!-- 窗体控件 -->
<%@include file="/views/embedded/plugin/layer.jsp" %>
<%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>
<title>个性化首页</title>
</head>
<body>
<!-- 窗体控件 -->
<%@include file="/views/embedded/plugin/layer.jsp" %>
<%@include file="/views/embedded/plugin/szxy_window_js.jsp" %>
<div class="content">
	<!-- 左侧开始 -->
	<div class="index_tjdiv">
		<div class="index_tj">
			<!-- 放左侧栏目 -->
			<div class="first_nav animated bounceInDown" id="first_nav">
				<ul>
					<c:forEach items="${desktop.groups}" var="group" varStatus="status">
						<c:choose>
							<c:when test="${status.index == 0}">
									<li class="on"><a href="#group${status.index+1}" >${group.name}</a></li>
								</c:when>
								<c:otherwise>
									<li ><a href="#group${status.index+1}" >${group.name}</a></li>
								</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
			<div class="help_text  animated bounceInDown">
				<div class="help_btn"><a href="javascript:void(0)">使用手册</a><a href="http://wpa.qq.com/msgrd?v=3&amp;uin=349955323&amp;site=qq&amp;menu=yes">？<br>帮助</a></div>
			</div>
			<!-- 中间应用 -->
			<div class="i_left" id="div_left">
				<c:forEach items="${desktop.groups}" var="group" varStatus="status">
					<div class="mk_list pt_list">
						<p class="title" id="group${status.index+1}">${group.name}</p>
						<ul>
							<c:forEach items="${group.applets}" var="applet">
								<li ><a href="javascript:void(0)" onclick="openApp('${applet.url}','${applet.appletKey}')"><img src="${applet.icon}"><span>${applet.name}</span></a></li>
							</c:forEach>
						</ul>
						<div class="clear"></div>
					</div>
				</c:forEach>
                <div class="mk_list" id="search_result" style="display: none">
					<a href="javascript:void(0)" class="return_index"><i></i>返回</a>
                    <p class="title">搜索结果</p>
                    <ul>
                    </ul>
                    <div class="clear"></div>
					<div class="search_empty"></div>
                </div>
			</div>
		</div>
	</div>
<!-- 左侧结束 -->
<!-- 右侧开始 -->
<div class="i_right  animated bounceInRight">
	<div class="time">
		<div class="zl">
			<img alt="img" src="<avatar:avatar userId='${sessionScope[sca:currentUserKey()].id}'></avatar:avatar>">
			<input type="hidden" id="userTypes" value="${sessionScope[sca:currentUserKey()].userTypes}">
			<input type="hidden" id="schoolTermCode" value="${sessionScope[sca:currentUserKey()].schoolTermCode}">
			<input type="hidden" id="teacherId" value="${sessionScope[sca:currentUserKey()].teacherId}">
			<input type="hidden" id="studentId" value="${sessionScope[sca:currentUserKey()].studentId}">
			<input type="hidden" id="schoolName" value="${sessionScope[sca:currentUserKey()].schoolName}">
			<input type="hidden" id="teamId" value="${teamId }">
			<div class="z_right">
				<p class="name"><span>${sessionScope[sca:currentUserKey()].schoolName}</span><b class="icon"></b></p>
				 <div class="school_select">
                    <p class="icon" style="left: 212px;"></p>
                    	<c:if test="${groupList== null || fn:length(groupList) == 0}">
                    		<a href="javascript:void(0)">${sessionScope[sca:currentUserKey()].schoolName}</a>
                    	</c:if>
                    	<c:if test="${groupList!= null && fn:length(groupList) == 1}">
                    		<a href="javascript:void(0)">${sessionScope[sca:currentUserKey()].schoolName}</a>
                    	</c:if>
                    	<c:if test="${groupList!= null && fn:length(groupList) > 1}">
                    		<c:forEach items ="${groupList}" var="item" varStatus="status">
                    			<a href="javascript:void(0)" onclick= "changeSchool('${item.ownerId}')">${item.name}</a>
                    		</c:forEach>
                    	</c:if>
				</div> 
				<div class="js">
                    <div class="role_div">
                        <div class="role_a">
							<c:forEach items="${userRoleList}" var="item">
								<a href="javascript:void(0)" class="js_a"><p class="p1">${item.role.name}</p><p class="p2">随意内容吧</p></a>
							</c:forEach>
                        </div>
                        <div class="cz_btn"><a href="javascript:void(0)" class="open_div">展开<i class="icon"></i></a></div>
                    </div>
				</div>
			</div>
		</div>
		<div class="clear"></div>
	</div>
	<div class="kebiao">
			<div class="kb_select">
				<a href="javascript:void(0)" class="on">我的日程</a>
				<c:choose>
					<c:when test="${fn:contains(sessionScope[sca:currentUserKey()].userTypes, 1) }">
						<a href="javascript:void(0)" onclick="getSyllabus();">课程表</a>
					</c:when>
					<c:when test="${fn:contains(sessionScope[sca:currentUserKey()].userTypes, 4) }">
						<a href="javascript:void(0)" onclick="getSyllabus();">课程表</a>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			</div>
			<div class="kb_list">
				<div class="wdkc" >
					<div id='calendar'>
					</div>
				</div>
				<div style="display:none;">
					<div class="kcb">
						<table class="responsive table  table-bordered table-striped">
							<thead>
							<tr>
								<th></th>
								<th>一</th>
								<th>二</th>
								<th>三</th>
								<th>四</th>
								<th>五</th>
								<th>六</th>
								<th>日</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<td>第1节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第2节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第3节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第4节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第5节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第6节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>第7节</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
</div>
</div>
<script type="text/javascript">

	//切换学校流程
	function changeSchool(schoolId){
        var loader = new load_xq(); //实例化对象
		loader.show(); //显示
	
	 $.ajax({
		    type: "GET",
		    url:  "${pageContext.request.contextPath}/change/logout?t="+new Date().getTime(),
		    data: {},
		    dataType: "text",
		    async: false,
		    success: function(data){
		    	logoutSubSys();
		    	loginCas(schoolId);
		    }
		});  
		
	}
	
	
	//登录CAS
	function loginCas(schoolId){
		
		var url = "";
		var casUrl = "<%= SysContants.CASBASEPATHURL%>";
		$.ajax({
		    type: "GET",
		    url: "${pageContext.request.contextPath}/mesosphere/mqtGetUserByUsernameAndSchoolId",
		    data: {'username':'${sessionScope[sca:currentUserKey()].userName}', 'schoolId':schoolId},
		    dataType: "json",
		    async: false,
		    success: function(data){
		    	delCookie("szxyzxx.clouds");
			    var password = data.password;
			    var username= data.userName;
			    url =  casUrl + "/MqtCookieGenerator?username="+username+"&encryptedPassword="+password+"&schoolId="+schoolId;
			    url = encodeURIComponent(url);
			    window.parent.location.href =  casUrl + "/logout?service=" + url;
		    }
		});
	}
	
	
	//退出子系统
	function logoutSubSys(){
		//CR
		var $requestData = {};
		var url = "${pageContext.request.contextPath}/cr/json/logout";
		$.ajax({
			url:url,
		    async : false,
		    jsonp: 'callback',
		    jsonpCallback:"jsonpcallback",
		    data: $requestData,
		    timeout: 5000,
		    complete : function(json) {
		    }
		});
		
		//有伴
		
		var $requestData = {};
		var url = "http://luucas.ubanjiaoyu.com:8080/organize/api/sign/cas_out?callback=redirecthttps://tuu.ubanjiaoyu.com/index.php?_org_=organize&s=api/sign/cas_out&callback=redirect";
		$.ajax({
			url:url,
		    async : false,
		    jsonp: 'callback',
		    jsonpCallback:"jsonpcallback",
		    data: $requestData,
		    timeout: 5000,
		    complete : function(json) {
		    }
		});
		
		//十牛
		
		
		
		
		//训云
		
		
		
		
		
	}
	
	
	function delCookie(name) {
		var exp = new Date();
		exp.setTime(exp.getTime() - 1);
		var cval=getCookie(name);
		if(cval!=null)
		document.cookie= name + "="+cval+";expires="+exp.toGMTString();
	}
	
	function getCookie(name){
		var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
		if(arr=document.cookie.match(reg))
			return unescape(arr[2]);
		else
			return null;
	}
	
	$(function(){
        		// var loader = new load_xq(); //实例化对象
        		// loader.show(); //显示
         		// loader.close();// 关闭
        $("body").niceScroll({"styler":"fb","cursorcolor":"#888888","cursorwidth":"8",cursorborder: "0"});
	    //搜索后点击返回
        $('.return_index').click(function () {
            $("#search_result").hide();
            $('.pt_list').show();
            $('.first_nav').show();
            $('.help_text').show();
            $('.i_right').show();
            $('.index_tj').css('margin-right','360px');
        })
	   // 学校跟角色的hover
        var t;
      $('.time .zl .z_right .name').hover(function () {
            clearTimeout(t);
            var name_length=$(this).children('span').width()+100;
            $('.school_select p.icon').css('left',name_length);
            $('.school_select').slideDown('fast');
        }, function () {
            t = setTimeout("$('.school_select').hide()", 500);
        });

        $('.school_select').hover(function () {
            clearTimeout(t);
        }, function () {
            $(this).hide();
        }); 
         $('.school_select a').click(function () {
            $('.time .zl .z_right .name span').text($(this).text());
            $('.school_select').hide();
        }); 
        if($('.role_a').height()<=55){
            $('.cz_btn').hide();
        }
        //角色的展开关闭
        var js_width=0;
        $('.js_a').each(function () {
            js_width=js_width+$(this).width();
            if(js_width>320){
                $(this).hide();
            }
        });
        $('body').on('click','.open_div',function () {
            $('.js_a').show();
            $(this).removeClass('open_div').addClass('close_div').html('收起<i class="icon"></i>');
        });
        $('body').on('click','.close_div',function () {
            var js_width=0;
            $('.js_a').each(function () {
                js_width=js_width+$(this).width();
                if(js_width>320){
                    $(this).hide();
                }
            });
            $(this).removeClass('close_div').addClass('open_div').html('展开<i class="icon"></i>');
        });


        $("#lunar").text(showCal());

		/*左侧栏目*/
		$('.first_nav ul li a').each(function(){
			var text_size=$(this).text().length;
			if(text_size==4){
				$(this).addClass('four');
				$(this).parent().addClass('radius_21')
			}else if(text_size>4){
				$(this).parent().addClass('radius_21')
			}
		});

            var date = new Date();
            var d = date.getDate();
            var m = date.getMonth();
            var y = date.getFullYear();

            $('#calendar').fullCalendar({
                height: 250,
                header: {
                    left: 'prev',
                    center: 'title',
                    right: 'next today'
                },
                editable: false,
                eventClick: function(calEvent, jsEvent, view) {
                    menu("YUN_BAN_GONG","OA_RI_CHENG_AN_PAI");

                },
                events: InItCalData()
            });




		$(".kebiao .kb_select a").click(function(){
            $(".kebiao .kb_select a").removeClass("on");
            $(this).addClass("on");
            var i=$(this).index();
            $(".kebiao .kb_list").children().hide();
            $(".kebiao .kb_list").children().eq(i).show();
        });
        /*左侧栏目*/
        $(".first_nav ul li a").click(function(){
        $(".first_nav ul li").removeClass("on");
        $(this).parent().addClass("on");
         $("html, body").animate({
            scrollTop: ($($(this).attr("href")).offset().top-35) + "px"
          }, {
            duration: 500,
            easing: "swing"
          });
          return false;
      });

        var div_top=[];
        $('.content .i_left .pt_list').each(function(){
            div_top.push($(this).offset().top-35);
        });
        var iframe_height=$(".man_right",window.parent.document).height()-60;
        $(window).resize(function(){
            iframe_height=$(".man_right",window.parent.document).height()-60;
		});
        var nav_top=0;
        var nav_height=$('#first_nav').height();
        var i=0;
        $(window).scroll(function(){
            var this_scrollTop = $(this).scrollTop();
            if(iframe_height<nav_height){
                if(i>=2){
                    nav_top=-48*(i-2);
                    if(iframe_height-nav_top<nav_height){
                        $(".first_nav ul").animate({top:nav_top},50);
                    }else{

					}
                }
            }
            for(i=(div_top.length-1);i>=0;i--){
                if(this_scrollTop>=div_top[i]){
                    console.log(i)
                    $('.first_nav ul li').removeClass('on');
                    $('.first_nav ul li').eq(i).addClass('on');
                    return;
                }
            }

        });


        <c:forEach items="${desktop.groups}" var="group" varStatus="status">
        var group${status.index+1}_top = $("#group${status.index+1}").offset().top;
        </c:forEach>

    var newWallpaperPath = '${newWallpaperPath}';
    if(newWallpaperPath != ''){
    	$('.index_css').attr('href','/res/css/sygb/'+newWallpaperPath+'_index.css');
    }
    
	})

	
	
    function getSyllabus() {
        var userTypes = $("#userTypes").val();
        var schoolTermCode = $("#schoolTermCode").val();
        var teacherId = $("#teacherId").val();
        var studentId = $("studentId").val();
        var teamId = $("#teamId").val();
        if(userTypes.indexOf("1") != -1) {
            if(teacherId != null && teacherId != "") {
                $(".kcb").load("${ctp}/teach/syllabus/searcher/teacher/list", {"_method" : "GET", "teachId" : teacherId, "termCode" : schoolTermCode, "type" : 1}, function(data, status) {
                    setTimeout(function () {
                        var gaodu = $(".kcb").height();
                        $(".shadeDiv2").height(gaodu);
                    },1300);
                });
            }
        }else if(userTypes == "4") {
            if(teamId != null && teamId != "") {
                $(".kcb").load("${ctp}/teach/syllabus/searcher/team/list", {"_method" : "GET", "teamId" : teamId, "termCode" : schoolTermCode, "type" : 1}, function(data, status) {
                    var gaodu = $(".kcb").height();
                    $(".shadeDiv2").height(gaodu);
                });
                setTimeout(function () {
                    var gaodu = $(".kcb").height();
                    $(".shadeDiv2").height(gaodu);
                },1300);
            }
        }else {

        }


    }
	
    function InItCalData(){
        var InItCalUrl = "${ctp}/oa/schedule/dataInIt";
        return InItCalUrl;
    }

    function openApp(url,key) {
	    if(url=='') {
            url="${ctp}/views/demo/zzkf/zzkf.jsp";
        }else if(url.indexOf('http')==-1){
            if(url.indexOf('app')==0){
                url="${ctp}" + url+"&key="+key;
            }
        };
        window.open(url, "_blank");
    }
</script>
</body>
</html>
