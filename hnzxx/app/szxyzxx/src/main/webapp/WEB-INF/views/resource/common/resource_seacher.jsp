<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@include file="/views/embedded/taglib.jsp" %>
<%--         <%@ include file="/views/embedded/common.jsp"%> --%>
        <title>云资源</title>
        <style>
            .course-box {
                float:left;
            }
          

        </style>
        <script>

        // 	$(function(){
        // 		$(".km_table td ").on("click",".shouqi",function(){
        // 			$(this).prev().css("height","24px");
        // 			$(this).removeClass("shouqi").addClass("zhankai");
        // 			$(this).html("展开 <i class='fa fa-angle-up'></i>")
        // 		});
        // 		$(".km_table td ").on("click",".zhankai",function(){
        // 			$(this).prev().css("height","auto");
        // 			$(this).removeClass("zhankai").addClass("shouqi");
        // 			$(this).html("收起 <i class='fa fa-angle-down'></i>")
        // 		});
        // 	});
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <%-- 		<jsp:include page="/views/embedded/navigation.jsp"> --%>
            <%-- 			<jsp:param value="fa-asterisk" name="icon"/> --%>
            <%-- 			<jsp:param value="学科列表" name="title" /> --%>
            <%-- 			<jsp:param value="${param.dm}" name="menuId" /> --%>
            <%-- 		</jsp:include> --%>
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-widgets white" style="border:0 none;">
                        <%-- 					<jsp:include page="/views/embedded/resource/resource_nav.jsp"></jsp:include> --%>
                        <div class="wrapper-right">
                            <div id="discover-courses-rows">
                                <div class="ud-coursecarousel course-list-wrapper collection">
                                    <div id="yourLike" style="margin-bottom: 10px;">
                                        <div class="courses-header">
                                            <jsp:include page="/views/embedded/resource/resource_textbox.jsp">
                                                <jsp:param value="catalog_div" name="catalog_div"/>
                                                <jsp:param value="catalogCallback" name="callback"/>
                                                <jsp:param value="${condition.stageCode}" name="stageCode"/>
                                                <jsp:param value="${condition.subjectCode}" name="subjectCode"/>
                                                <jsp:param value="${condition.gradeCode}" name="gradeCode"/>
                                                <jsp:param value="${condition.versionCode}" name="versionCode"/>
                                                <jsp:param value="${condition.volumnCode}" name="volumnCode"/>
                                            </jsp:include>
                                        </div>
                                        <div class="rs_platformContainer_1">
                                            <div class="unit-area xkzy" id="catalog_div">
                                            </div>
                                            <div class="main">
                                                <div class="zy_list">
                                                    <div class="xkzy_list" id="microLessonDiv">
                                                        <%-- 													<jsp:include page="./resource_list.jsp" /> --%>
                                                    </div>
                                                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                                        <jsp:param name="id" value="studentAid_list_content" />
                                                        <jsp:param name="url" value="/resource/searcher?dm=${param.dm}" />
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

        function catalogCallback(id, obj) {
            $(obj).parents("#acatalogList_div").find("a").removeClass("on");
            $(obj).parent().addClass("on");
            search(id);
        }

        function search(data) {
            var val = {};
            if (data != null && data != "" && data != "-1") {
                val.catalogCode = data;
            }
            val.stageCode = $("#stageCode_td .on").attr("data-code");
            val.subjectCode = $("#subjectCode_td .on").attr("data-code");
            val.versionCode = $("#publisherId_div .on").attr("data-code");
            var gradeDiv = $("#gradeCode_div .on");
            val.gradeCode = gradeDiv.attr("data-code");
            val.volumnCode = gradeDiv.attr("data-volumn-code")    ;
            val.resType = "${condition.resType}";
            val.isPublish="${condition.isPublish}";
    // 		alert(data);
     		//alert(val.stageCode + "_" + val.subjectCode + "_" + val.versionCode + "_" + val.gradeCode + "_" + val.volumnCode);
            var id = "microLessonDiv";
            var url = "/resource/searcher";
            myPagination(id, val, url);
        }

//        $(function() {
//            checkSelected();
//        });
    </script>
</html>