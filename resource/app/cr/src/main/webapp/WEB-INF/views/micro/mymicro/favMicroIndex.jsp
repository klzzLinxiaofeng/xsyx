<%@page import="platform.education.resource.contants.ResourceType"%>
<%@page import="platform.education.commonResource.web.common.contants.SysContants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/res/css/extra/bannercommon.css" rel="stylesheet">
        <%@ include file="/views/embedded/common.jsp"%>
        <link href="${pageContext.request.contextPath}/res/css/extra/microcourse_style.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/res/css/extra/microcourse_index.css" rel="stylesheet">
        <title>我的微课</title>
        <style>
            .course-box {
                float:left;
            }
            .course-card-wide{
                margin-top:0;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="我的微课" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-widgets white" >
                        <div class="weike_top">
                            <p>
                                <span>
                                	<a style="color:#808080" href="${pageContext.request.contextPath}/micro/index">首页</a>
                                </span>
                                <span class="s2">
                                	<a style="color:#808080" href="${pageContext.request.contextPath}/resource/searcher/index?resType=<%=ResourceType.MICRO%>&isMicro=true">分类微课</a>
                                </span>
                                <span class="big">我的微课</span>
                            </p>
                            <div class="gjz_select">
                                <input id="keyword" type="text" style="width:200px;" placeholder="请输入微课标题" value="">
                                <button onclick="search();" class="btn btn-primary">搜索</button>
                                <a href="${pageContext.request.contextPath}/micro/uploadIndex" class="upload">
                                    <i class="fa fa-plus"></i> &nbsp; 上传微课
                                </a>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="kemu_nav">
                            <ul>
                                <!--							<li><a class="active" href="javascript:void(0)">看过的资源</a></li>-->
                                <li><a href="${pageContext.request.contextPath}/micro/myMicro?index=index">我上传的微课</a></li>
                                <li><a class="active" href="javascript:void(0)">我收藏的微课</a></li>
                            </ul>
                        </div> 
                        <div class="wrapper-right">
                            <div id="discover-courses-rows">
                                <div class="ud-coursecarousel course-list-wrapper collection">
                                    <div id="yourLike" style="margin-bottom: 10px;">
                                        <div class="rs_platformContainer_1">
                                            <div class="main" style="margin-left:-1px;">
                                                <div class="top">
                                                    <p class="left"><b>搜索结果</b>（${page.totalRows}个）
                                                    </p>
<!--                                                    <p class="right">排序：&#12288;<a class="on" href="javascript:void(0)">评分<i class="fa fa-arrow-down"></i></a><a href="javascript:void(0)">浏览量<i class="fa fa-arrow-down"></i></a><a href="javascript:void(0)">最新<i class="fa fa-arrow-down"></i></a></p>-->
                                                    <div class="clear"></div>
                                                </div>
                                                <div id="courses">
                                                    <jsp:include page="./favMicroList.jsp" />
                                                </div>
                                                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                                    <jsp:param name="id" value="courses" />
                                                    <jsp:param name="url" value="/micro/favMicro" />
                                                    <jsp:param name="pageSize" value="${page.pageSize }" />
                                                </jsp:include>
                                            </div>
                                                <div class="clear"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
                                    function search() {
                                        var title = $.trim($("#keyword").val());
                                        if (title == null || title == "") {
                                            $.alert("请输入要搜索的标题");
                                            return;
                                        }
                                        var url = "/micro/favMicro";
                                        var data = {"title": title};
                                        myPagination("courses", data, url,function(){
                                            $("#courses .item-title a").each(function(){
                                                var ptext = $.trim($(this).text().toLowerCase());
                                                var rtext = "<font color=\"red\">"+title+"</font>";
                                                var ftext = ptext.replace(title,rtext);
                                                $(this).html(ftext);
                                            });
                                        });
                                    }

                                     function removeFav(mid) {
//                                        $.confirm("确定取消收藏此资源吗？", function() {
                                            $.ajax({
                                                url: "${pageContext.request.contextPath}/resource/fav?resType=<%=ResourceType.MICRO%>&isFav=false",
                                                type: "POST",
                                                data: {"resId": mid},
                                                async: false,
                                                success: function() {
                                                	$.success("取消收藏成功！");
                                                    location.href = "${pageContext.request.contextPath}/micro/favMicro?index=index";
                                                }
                                            });
//                                        });
                                    }
                                    
                                    $(function() {
                                        $("#keyword").on("keyup", function(event) {
                                            if (event.keyCode == 13) {
                                                search();
                                            }
                                        });
                                    });

    </script>
</html>