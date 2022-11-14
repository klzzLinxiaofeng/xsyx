<%@ page import="platform.education.service.model.Substitute" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:useBean id="approval" class="platform.education.service.model.Substitute"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/extra/my.css" rel="stylesheet">
    <title>代课管理</title>
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
        <jsp:param value="代课管理" name="title"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="oa_top">
                    <ul class="top_ul">
                        <li><a href="javascript:" class="on" data-status="wait" onclick="getData('wait');">待审批</a></li>
                        <li><a href="javascript:" data-status="yet" onclick="getData('yet');">已审批</a></li>
                    </ul>
                </div>
                <div class="sq_list">
                    <div class="select_b">
                        <div class="select_div">
                            <span>申请人：</span>
                            <input id="shenqingname" name="shenqingname" class=""  style="width:200px;padding-top: 4px;"/>
                        </div>
                        <div class="select_div">
                            <span>代课教师：</span>
                            <input id="daikename" name="daikename" class=""  style="width:200px;padding-top: 4px;"/>
                        </div>

                        <div class="select_div">
                            <span>活动时间：</span>
                            <input type="text" id="kaishiTime" name="kaishiTime" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"
                                   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                                   placeholder="xxxx-xx-xx xx:xx"/>-
                            <input type="text" id="kaishiTime2" name="kaishiTime2" class="chzn-select" autocomplete="off"
                                   style="width:200px;padding-top: 4px;"
                                   onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
                                   placeholder="xxxx-xx-xx xx:xx"/>
                        </div>

                    <span class="search_1">
                        <span id="spzt" style="display: none">
                            <span>审批状态：</span>
                            <select id="statusSelect">
                                <option value="">--全部--</option>
                                <option value="1">同意</option>
                                <option value="2">驳回</option>
                            </select>
                        </span>
                    </span>
                        <button type="button" class="btn btn-primary" onclick="ss2()">查询</button>
                        <button type="button" class="btn btn-primary" onclick="daochu()">导出</button>
                        <div class="clear"></div>
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
                        <jsp:param name="url" value="${pageContext.request.contextPath}/office/substitute/index?sub=list&dm=${param.dm}"/>
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

    function ss2() {
        myPagination("data_list_content", getPagingParam(), "${pageContext.request.contextPath}/office/substitute/index?sub=list&dm=${param.dm}");
    }

    function getData(type) {

        $("#statusSelect option:first").prop("selected", 'selected');
        $("#ss").val("");
        var param={"status": 0}
        if (type === 'yet') {
            $("#spzt").show();
            param.status=null;
            param.handled=true;
        } else {
            $("#spzt").hide();
        }
        $.ajaxSetup({
            async: false
        });
        myPagination("data_list_content", param, "${pageContext.request.contextPath}/office/substitute/index?sub=list&dm=${param.dm}");
    }

    function getPagingParam(){
        let type = $(".oa_top .top_ul li").find("a.on").attr("data-status");
        var param={"status": 0,"description": $("#ss").val()}

        var shenqign=$("#shenqingname").val();
        var daikename=$("#daikename").val();
        var kaishiTime=$("#kaishiTime").val();
        var kaishiTime2=$("#kaishiTime2").val();
        if(shenqign!=null && shenqign!=''){
            param.userName=shenqign;
        }
        if(daikename!=null && daikename!=''){
            param.daikename=daikename;
        }
        if(kaishiTime!=null && kaishiTime!=''){
            param.kaishiTime=kaishiTime;
        }if(kaishiTime2!=null && kaishiTime2!=''){
            param.kaishiTime2=kaishiTime2;
        }

        if (type === 'yet') {
            param.status=$("#statusSelect").val();
            param.handled=true;
        } else {
            $("#spzt").hide();
        }
        return param;
    }

</script>
</html>