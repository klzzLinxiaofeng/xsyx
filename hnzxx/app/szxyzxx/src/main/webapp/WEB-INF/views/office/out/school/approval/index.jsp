<%@ page import="platform.education.service.model.OutSchoolActivityApproval" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="approval" class="platform.education.service.model.OutSchoolActivityApproval"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>校外活动管理</title>
    <script type="text/javascript">
        $(function () {
            $(".sq_list .entry ul li a").click(function () {
                $(".sq_list .entry ul li a").parent().removeClass("on");
                $(this).parent().addClass("on");
            });
            $(".oa_top .top_ul li a").click(function () {
                $(".oa_top .top_ul li a").removeClass("on");
                $(this).addClass("on");
                $(".sq_list .entry ul li a").parent().removeClass("on");
            });
        })
    </script>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="活动审批" name="title"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="oa_top">
                    <ul class="top_ul">
                        <li><a href="javascript:" class="${state==0 ? "on":""}" data-status="wait" onclick="getData('wait');">待审批</a></li>
                        <li><a href="javascript:" class="${state==null ? "on":""}" data-status="yet" onclick="getData('yet');">已审批</a></li>
                    </ul>
                </div>
                <div class="sq_list">


                    <span class="search_1">

                        <c:if test="${state==null}">

                            <span>审批状态：</span>
                            <select id="state" name="state">
                                <option value="">--已通过--</option>
                                <option value="2">--待总结--</option>
                                <option value="3">--已总结--</option>
                                <option value="4">--已驳回--</option>
                            </select>

                        </c:if>

                        <input type="text" placeholder="活动名称" id="ss">
                        <a class="sea_s" onclick="ss2()"><i class="fa fa-search"></i></a>
                    </span>

                    <div class="clsq" id="data_list_content">
                        <c:choose>
                            <c:when test="${items.size() > 0}">
                                <jsp:include page="./list.jsp"/>
                            </c:when>
                            <c:otherwise>
                                <div class="empty">
                                    <p>暂无相关数据</p>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="data_list_content"/>
                        <jsp:param name="url" value="${pageContext.request.contextPath}/office/out/school/approval/index?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    let loader = new loadLayer();

    function getPagingParam(){


        let type = $(".oa_top .top_ul li").find("a.on").attr("data-status");

        var state=0;
        var handleUserId=-1;
        if (type === 'wait') {
            handleUserId=null;
        } else {
            state=$("#state").val();
        }

        var param={"state":state,"handleUserId":handleUserId,"name": $("#ss").val()};


        return param;
    }

    function ss2() {
        myPagination("data_list_content", getPagingParam(), "${pageContext.request.contextPath}/office/out/school/approval/index?sub=list");
    }

    function agreed(id) {
        $.confirm("确定执行《同意》操作？", function () {
            loader.show();
            $.ajax({
                url: "${pageContext.request.contextPath}/office/out/school/approval/agreed/" + id,
                type: "POST",
                contentType: 'application/json;charset=utf-8',
                dataType: "json",
                success: function (data, status) {
                    if ("success" === status) {
                        $.success('操作成功');
                        if ("success" === data.info) {
                            if (parent.core_iframe != null) {
                                parent.core_iframe.window.location.reload();
                            } else {
                                parent.window.location.reload();
                            }
                            $.closeWindow();
                        } else {
                            $.error("操作失败");
                        }
                    } else {
                        $.error("操作失败");
                    }
                    loader.close();
                },
                error: function (res) {
                    $.error("操作失败");
                }
            });
        });
    }

    function reject(id) {
        $.initWinOnTopFromLeft('驳回', '${pageContext.request.contextPath}/office/out/school/approval/reject/' + id, '560', '300');
    }

    function examine(id) {
        $.initWinOnTopFromLeft('查看总结', '${pageContext.request.contextPath}/office/out/school/approval/examine/' + id, '700', '500');
    }

    function getData(type) {
        if (type === 'wait') {
            location.href="${pageContext.request.contextPath}/office/out/school/approval/index?state=0"
        } else {
            location.href="${pageContext.request.contextPath}/office/out/school/approval/index?handleUserId=-1"
        }

    }

    function findData(status) {
        $.ajaxSetup({
            async: false
        });
        myPagination("data_list_content", {
            "name": $("#ss").val(),
            "status": status
        }, "${pageContext.request.contextPath}/office/out/school/approval/index?sub=list");
    }
</script>
</html>