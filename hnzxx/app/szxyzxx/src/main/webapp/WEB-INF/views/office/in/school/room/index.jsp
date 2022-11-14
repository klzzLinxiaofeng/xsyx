<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>活动室管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <script type="text/javascript">
        function search() {
            let id = "data_list_content";
            let val = {
                "name": $("#name").val()
            };
            let url = "${pageContext.request.contextPath}/office/in/school/room/index?sub=list&dm=${param.dm}";
            myPagination(id, val, url);
        }

        // 	加载创建对话框
        function loadCreatePage() {
            $.initWinOnTopFromLeft("创建", "${pageContext.request.contextPath}/office/in/school/room/input", "700", "250");
        }

        //  加载编辑对话框
        function loadEditPage(id) {
            $.initWinOnTopFromLeft("编辑", "${pageContext.request.contextPath}/office/in/school/room/" + id, "700", "250");
        }

        // 	删除对话框
        function deleteObj(obj, id) {
            $.confirm("确定执行此次操作？", function () {
                executeDel(obj, id);
            });
        }

        // 	执行删除
        function executeDel(obj, id) {
            $.post("${pageContext.request.contextPath}/office/in/school/room/" + id, {"_method": "delete"}, function (data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        $.success("删除成功");
                        $("#tr_" + id).remove();
                    } else {
                        if (data.responseData && data.responseData.msg) {
                            $.error(data.responseData.msg);
                        } else {
                            $.error("删除失败，系统异常", 1);
                        }
                    }
                } else {
                    $.error("提交失败");
                }
                loader.close();
            });
        }
    </script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="活动室管理" name="title"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>活动室列表
                        <p class="btn_link" style="float:right">
                            <a href="javascript:$.refreshWin()" class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
                            <a href="javascript:loadCreatePage()" class="a4"><i class="fa fa-plus"></i>添加活动室</a>
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div" style="padding:0 20px">
                                <input type="text" id="name" name="name" style="margin-bottom:0;padding:6px;width:150px" placeholder="请输入名称" value=""/>
                            </div>
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>名称</th>
                                <th style="text-align:center">是否禁用</th>
                                <th style="text-align:center">创建日期</th>
                                <th class="caozuo" style="text-align:center">操作</th>
                            </tr>
                            </thead>
                            <tbody id="data_list_content">
                            <c:forEach items="${items}" var="item">
                                <tr id="tr_${item.id}">
                                    <td>${item.name}</td>
                                    <td style="width:100px;text-align:center">
                                        <c:choose>
                                            <c:when test='${item.isDeleted}'>是</c:when>
                                            <c:otherwise>否</c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td style="width:150px;text-align:center"><fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd"/></td>
                                    <td class="caozuo" style="width:200px;text-align:center">
<%--                                        <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">--%>
                                            <button class="btn btn-blue" type="button" onclick="loadEditPage('${item.id}');">编辑</button>
<%--                                        </c:if>--%>
<%--                                        <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 3)}">--%>
                                            <button class="btn btn-red" type="button" onclick="deleteObj(this, '${item.id}');">删除</button>
<%--                                        </c:if>--%>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="data_list_content"/>
                            <jsp:param name="url" value="${pageContext.request.contextPath}/office/in/school/room/index?sub=list&dm=${param.dm}"/>
                            <jsp:param name="pageSize" value="${page.pageSize}"/>
                        </jsp:include>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
