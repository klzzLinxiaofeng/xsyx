<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>历史记录</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="历史记录" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-widgets white">
                        <div class="widget-head">
                            <h3>
                                历史记录
                            </h3>
                        </div>
                        <div class="content-widgets">
                            <div class="widget-container">
                                <div class="kemu">
                                    <a onclick="changeSubject('全部')" href="javascript:void(0)" class="active">全部</a>
                                    <c:forEach items="${subjectList}" var="subject">
                                        <a onclick="changeSubject('${subject}')" href="javascript:void(0)">
                                            ${subject}
                                        </a>
                                    </c:forEach>
                                </div>
                                <table class="responsive table table-striped" id="data-table">
                                    <thead>
                                        <tr role="row">
                                            <th>上课时间</th>
                                            <th>布置教师</th>
                                            <th>班级科目</th>
                                            <th>完成进度</th>
                                            <th class="caozuo">操作</th>
                                        </tr>
                                    </thead>
                                    <tbody id="module_list_content">
                                        <jsp:include page="./studyHistoryList.jsp" />
                                    </tbody>
                                </table>
                                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                    <jsp:param name="id" value="module_list_content" />
                                    <jsp:param name="url" value="/homeworkpublish/studyHistory" />
                                    <jsp:param name="pageSize" value="${page.pageSize }" />
                                </jsp:include>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
                                            function watchReviews(publishId, userId) {
                                                var mes = "查看解答";
                                                $.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/homeworkpublish/watchReviews?microPublishedId=' + publishId + '&userId=' + userId, '800', '300');
                                            }

                                            function changeSubject(subjectName) {
                                                $(".kemu a").each(function() {
                                                    if (subjectName == $.trim($(this).text())) {
                                                        $(this).addClass("active");
                                                    } else {
                                                        $(this).removeClass("active");
                                                    }
                                                });
                                                var url = "/homeworkpublish/studyHistory";
                                                var data = {"subjectName": subjectName=="全部"?"all":subjectName};
                                                myPagination("module_list_content", data, url);
                                            }

    </script>
</html>