<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link
            href="${pageContext.request.contextPath}/res/css/extra/bannercommon.css"
            rel="stylesheet">
        <%@ include file="/views/embedded/common.jsp"%>
        <link
            href="${pageContext.request.contextPath}/res/css/extra/microcourse_style.css"
            rel="stylesheet">
        <link
            href="${pageContext.request.contextPath}/res/css/extra/microcourse_index.css"
            rel="stylesheet">
        <title>${param.isMicro ? '微课' : '学科资源'}</title>
        <style>
            .course-box {
                float: left;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon" />
                <jsp:param value="${param.isMicro ? '微课列表' : '学科资源列表'}" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-widgets white">
                        <c:choose>
                            <c:when test="${param.isMicro}">
                                <jsp:include page="/views/embedded/resource/resource_micro_nav.jsp">
                                    <jsp:param name="title" value="${param.title}"></jsp:param>
                                    <jsp:param name="isDef" value="false"></jsp:param>
                                    <jsp:param value="${personType}" name="personType"/>
                                </jsp:include>	
                            </c:when>
                            <c:otherwise>
                                <jsp:include page="/views/embedded/resource/resource_nav.jsp">
                                    <jsp:param name="title" value="${param.title}"></jsp:param>
                                    <jsp:param name="isDef" value="false"></jsp:param>
                                    <jsp:param value="${personType}" name="personType"/>
                                </jsp:include>
                            </c:otherwise>
                        </c:choose>


                        <%--                       <jsp:include page="/views/embedded/resource/resource_nav.jsp">
                                                   <jsp:param name="title" value="${param.title}"></jsp:param>
                                                   <jsp:param name="isDef" value="false"></jsp:param>
                                               </jsp:include>--%>
                        <div class="wrapper-right">
                            <div id="discover-courses-rows">
                                <div class="ud-coursecarousel course-list-wrapper collection">
                                    <div id="yourLike" style="margin-bottom: 10px;">
                                        <div class="rs_platformContainer_1">
                                            <div class="main" style="margin-left: -1px;">
                                                <div class="zy_list">
                                                    <div class="xkzy_list" id="zy_list">

                                                    </div>
                                                    <jsp:include page="/views/embedded/jqpagination.jsp"
                                                                 flush="true">
                                                        <jsp:param name="id" value="studentAid_list_content" />
                                                        <jsp:param name="url" value="/teach/studentaid/studentAidList?sub=list&dm=${param.dm}" />
                                                        <jsp:param name="pageSize" value="${page.pageSize}" />
                                                    </jsp:include>
                                                    <div class="clear"></div>
                                                </div>
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
        $(function() {
            $("#search_btn").on("click", function(event) {
                var val = {};
//                val.title = $.trim($("#search_key").val());
                val.title = $.trim("${title}");
                val.topType = "sort";
                val.resType = "${param.resType}";
                val.isMicro = "${param.isMicro}";
                val.personType="${personType}";
                val.title2 = $(this).prev().val();
                var id = "zy_list";
                var url = "/resource/searcher";
                myPagination(id, val, url, function() {
//                    $("#zy_list .item-title a").each(function() {
//                        var ptext = $.trim($(this).text().toLowerCase());
//                        var rtext = "<font color=\"red\">" + val.title + "</font>";
//                        var ftext = ptext.replace(val.title, rtext);
//                        $(this).html(ftext);
//                    });
                });
            });

            $("#search_btn").click();

            $("#search_key").on("keyup", function(event) {
                if (event.keyCode == 13) {
                    $("#search_btn").click();
                }
            });


        		$("#zy_list").on("click", ".cz_btn .shoucan", function() {
        			var $this = $(this);
        			var isFaved = $this.attr("data-isFaved");
                     var id=$this.attr("data-id");
        			//取消收藏
        			if("true" === isFaved) {
        				cancelFav(id, function() {
        					$this.attr("data-isFaved", "false");
        					$this.text("收藏");
        				});
        			} else {
        				//收藏
        				fav(id, function() {
        					$this.attr("data-isFaved", "true");
        					$this.text("取消收藏");
        				});
        			}
        		});
        	});
        	
        	function fav(id, callback) {
        		var requestData = {"id":id,"isFav" : true};
        		$.post("${pageContext.request.contextPath}/resource/fav", requestData, function(data, status) {
        			if("success" === status) {
        				if ("faved" === data) {
        					$.success("已收藏过");
        				} else if ("success" === data) {
        					$.success("收藏成功");
//         					alert(resId);
        					var favedCount = $("#faved_count_" + id).html();
        					$("#faved_count_" + id).html(parseInt(favedCount) + 1);
        					callback();
        				} else {
        					$.error("收藏失败");
        				}		
        			} else {
        				$.error("服务器响应异常!");
        			}
        		});
        	}
        	
        	function cancelFav(id, callback) {
        		var requestData = {"id":id, "isFav" : false};
        		$.post("${pageContext.request.contextPath}/resource/fav", requestData, function(data, status) {
        			if("success" === status) {
        				if ("faved" === data) {
        				} else if ("success" === data) {
        					$.success("取消收藏成功");
        					var favedCount = $("#faved_count_" + id).html();
        					$("#faved_count_" + id).html(parseInt(favedCount) - 1);
        					callback();
        				} else {
        					$.error("取消收藏失败");
        				}		
        			} else {
        				$.error("服务器响应异常!");
        			}
        		});
        	}
 
     
    </script>
</html>