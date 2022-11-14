<%@ page import="platform.education.service.model.InSchoolActivityApproval" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <script type="text/javascript" defer="defer" src="/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>校内活动管理</title>
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
        <jsp:param value="校内活动审批" name="title"/>
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

                    <div class="select_div" style="display: inline-block">
                        <span>开始时间：</span>
                        <input type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" id="startTime" name="startTime" style="margin-bottom: 0; padding: 6px; width: 100px;" value=""/>
                    </div>
                    <div class="select_div" style="display: inline-block">
                        <span>结束日期时间：</span>
                        <input type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});" id="endTime" name="endTime" style="margin-bottom: 0; padding: 6px; width: 100px;" value=""/>
                    </div>

                    <div class="select_div" style="border-style: solid;border-color: rgb(231, 231, 235);border-image: initial;display: inline-block;">
                        <input type="text" placeholder="活动名称" id="ss" style="margin:0px;">
                        <a class="sea_s" onclick="ss2()"><i class="fa fa-search" style="width: 15px"></i></a>
                    </div>
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
                        <jsp:param name="url" value="${pageContext.request.contextPath}/office/in/school/approval/index?sub=list&dm=${param.dm}"/>
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
    var currRoomId=null;

    function ss2() {
        let type = $(".oa_top .top_ul li").find("a.on").attr("data-status");

        var state=0;
        var handleUserId=-1;
        if (type === 'wait') {
            handleUserId=null;
        } else {
            state=null;
        }

        var param={"state":state,"handleUserId":handleUserId,"name": $("#ss").val(),"roomId":currRoomId,"startTime":$("#startTime").val(),"endTime":$("#endTime").val()};

        myPagination("data_list_content", param, "${pageContext.request.contextPath}/office/in/school/approval/index?sub=list");
    }


    function getPagingParam(){
        let type = $(".oa_top .top_ul li").find("a.on").attr("data-status");

        var state=0;
        var handleUserId=-1;
        if (type === 'wait') {
            handleUserId=null;
        } else {
            state=null;
        }

        var param={"state":state,"handleUserId":handleUserId,"name": $("#ss").val(),"startTime":$("#startTime").val(),"endTime":$("#endTime").val()};
    }

    function handle(id,state) {
        $.confirm("确定执行此次操作？", function () {

            var refuseCause=null;
            if(state==2) {
                refuseCause=prompt("请输入驳回理由");
                if (refuseCause == null) {
                    return;
                }
            }
            loader.show();
            $.ajax({
                url: "${pageContext.request.contextPath}/office/in/school/approval/handle",
                data:{"activityId":id,"state":state,"refuseCause":refuseCause},
                type: "POST",
                success: function (data, status) {
                    if (data=="success") {
                        $.success('操作成功');

                        location.reload();

                        $.closeWindow();

                    } else {
                        $.error(data);
                    }
                    loader.close();
                },
                error: function (res) {
                    $.error("操作失败");
                }
            });
        });
    }

    function getData(type) {
        if (type === 'wait') {
            location.href="${pageContext.request.contextPath}/office/in/school/approval/index?state=0"
        } else {
            location.href="${pageContext.request.contextPath}/office/in/school/approval/index?handleUserId=-1"
        }
    }

    function findData(roomId) {
        let type = $(".oa_top .top_ul li").find("a.on").attr("data-status");
        var state=0;
        var handleUserId=-1;
        if (type === 'wait') {
            handleUserId=null;
        } else {
            state=null;
        }
        currRoomId=roomId;
        var param={"state":state,"handleUserId":handleUserId,"name": $("#ss").val(),"roomId": roomId,"startTime":$("#startTime").val(),"endTime":$("#endTime").val()};
        $.ajaxSetup({
            async: false
        });
        myPagination("data_list_content", param, "${pageContext.request.contextPath}/office/in/school/approval/index?sub=list");
    }
</script>
</html>