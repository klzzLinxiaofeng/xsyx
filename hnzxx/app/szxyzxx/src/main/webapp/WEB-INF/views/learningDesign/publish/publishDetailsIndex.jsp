<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="studystatus" uri="http://www.jiaoxueyun.com/studystatus"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>学校列表</title>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon"/>
                <jsp:param value="课件管理" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid">
                <div class="span12">
                    <div class="content-widgets white">
                        <div class="widget-head">
                            <h3>
                                查看详情
                                <p style="float:right;" class="btn_link">
                                    <a class="a4" href="${pageContext.request.contextPath}/learningDesignpublish/publishManagerIndex?relateId=${micro.relateId}&relateType=${relateType}"><i class="fa fa-reply"></i>返回列表</a>
                                </p>
                            </h3>
                        </div>
                        <div class="gray_1">
                            <p>
                                <b>班级：</b>${fn:substringBefore(micro.relateName, "[")}
                                <b>科目：</b>${fn:substring(micro.relateName, fn:indexOf(micro.relateName,"[")+1,fn:indexOf(micro.relateName,"]"))} 
                                <b>完成期限：</b>
                                <fmt:formatDate value="${micro.startDate}" pattern="yyyy年MM月dd日 "/> ~ <fmt:formatDate value="${micro.finishedDate}" pattern="yyyy年MM月dd日 HH"/>点
                            </p>
                        </div>
                        <div class="content-widgets">
                            <div class="widget-container">
                                <div class="zysj">
                                    <table class="table_1">
                                        <tbody>
                                            <tr>
                                                <td rowspan="2">
                                                    <!--<button onclick="location.href = '${pageContext.request.contextPath}/learningDesignpublish/play?microPublishedId=${micro.publishMicroLessonId}'" class="btn btn-warning">查看课件</button>-->
                                                </td>
                                                <td rowspan="2" class="a1">
                                                    总体完成情况
                                                </td>
                                                <td class="a2">已完成</td>
                                                <td class="a2">部分完成</td>
                                                <td class="a2">未做</td>
                                                <td rowspan="2" style="text-align:right">
                                                    <button onclick="batchReviews()" class="btn btn-blue">一键写评语</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td class="a3">${finishedCount}人</td>
                                                <td class="a4">${partFinishedCount}人</td>
                                                <td class="a4">${unFinishedCount}人</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                                <div style="max-height:500px;overflow:auto;display:block;margin-bottom:10px;">
                                    <table class="responsive table table-striped" id="data-table" >
                                        <thead>
                                            <tr role="row">
                                                <th>学籍号</th>
                                                <th>姓名</th>
                                                <th>完成情况</th>
                                                <th>完成时间</th>
                                                <th class="caozuo">操作</th>
                                            </tr>
                                        </thead>
                                        <tbody id="module_list_content" >
                                            <jsp:include page="./publishDetailsList.jsp" />
                                        </tbody>
                                    </table>
                                </div>
                               <%-- <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                    <jsp:param name="id" value="module_list_content" />
                                    <jsp:param name="url" value="/learningDesignpublish/publishDetails?microPublishedId=${micro.publishMicroLessonId}&relateId=${micro.relateId}" />
                                    <jsp:param name="pageSize" value="${page.pageSize }" />
                                </jsp:include>--%>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
                                        function editReviews(userId) {
                                            var mes = "写评语";
                                            $.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/learningDesignpublish/editReviews?microPublishedId=${micro.publishMicroLessonId}&userId='+userId, '800', '400');
                                        }

                                        function watchReviews(userId) {
                                            var mes = "查看评语";
                                            $.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/learningDesignpublish/watchReviews?microPublishedId=${micro.publishMicroLessonId}&userId='+userId, '800', '300');
                                        }

                                        function batchReviews() {
                                            var mes = "一键写评语";
                                            $.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/learningDesignpublish/batchReviews?microPublishedId=${micro.publishMicroLessonId}&relateId=${micro.relateId}&relateType=${relateType}', '800', '540');
                                        }
    </script>
</html>