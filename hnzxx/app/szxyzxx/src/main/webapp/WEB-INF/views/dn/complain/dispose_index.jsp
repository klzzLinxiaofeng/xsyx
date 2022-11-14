<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>投诉处理</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="投诉处理" name="title" />
        <jsp:param value="${param.dm}" name="menuId" />
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="oa_top">
                    <ul class="top_ul">
                        <li><a  href="javascript:void(0);" id="li0" onclick="toIndex(false, false);">待处理</a></li>
                        <li><a  href="javascript:void(0);" id="li1" onclick="toIndex(true, false);">已处理</a></li>
                        <li><a  href="javascript:void(0);" id="li2" onclick="toIndex(true, true);">处理统计</a></li>
                    </ul>
                </div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b sq_list">
                            <div class="select_div">
                                <span>部门：</span> <select id="dept" class="dept"></select>
                            </div>
                            <div class="search_1" style="float:left;margin:5px 0 0 15px;">
                                <input type="text" placeholder="问题描述关键字/问题类别" id="description">
                                <a class="sea_s" id="a0" ><i class="fa fa-search"></i></a>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div id="list_content">
                            <jsp:include page="./dispose_list.jsp" />
                        </div>
                        <div style="padding-right:14px;margin-top: 14px">
                            <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                <jsp:param name="id" value="list_content" />
                                <jsp:param name="url" value="/dn/complain/dispose/index?sub=list&dm=${param.dm}&isDispose=${isDispose}" />
                                <jsp:param name="pageSize" value="${page.pageSize}"/>
                            </jsp:include>
                        </div>
                        <div class="clear"></div>
                    </div>

                    <div class="widget-container" style="display:none">
                        <div class="select_b sq_list">

                            <div class="select_div">
                                <span>开始时间：</span> <input type="text" id="begin" onfocus="WdatePicker();" />
                            </div>
                            <div class="select_div">
                                <span>结束时间：</span> <input type="text" id="end"  onfocus="WdatePicker();" />
                            </div>
                            <div class="select_div">
                                <span>部门：</span> <select id="dept2" class="dept" ></select>
                            </div>
                            <div class="search_1" style="float:left;margin:5px 0 0 15px;">
                                <input type="text" placeholder="问题描述关键字/问题类别" id="description2">
                                <a class="sea_s" id="a2"><i class="fa fa-search"></i></a>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div id="list_content2"></div>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(function(){
//        $(".oa_top .top_ul li a").click(function () {
//            $(".oa_top .top_ul li a").removeClass("on");
//            $(this).addClass("on");
//            var i=$(this).parent().index();
//            $(".widget-container").hide();
//            $(".widget-container").eq(i).show();
//        });
        $("#a0").click(function () {
            search();
        });
        $("#a2").click(function () {
            search();
        });
        $("#begin").val(new Date().Format("yyyy-MM-dd"));
        $("#end").val(new Date().Format("yyyy-MM-dd"));
        addClassOn();
        getDept();
        search(false, false, 0);
    });

    function toIndex(isDispose, isStatPage){
        window.location.href="${ctp}/dn/complain/dispose/index?dm=${param.dm}&isDispose="+isDispose+"&isStatPage="+isStatPage;
    }

    function addClassOn() {
        var isDispose = ${isDispose};
        var isStatPage = ${isStatPage};
        if (isStatPage) {
            $("#li2").addClass("on");
            $(".widget-container").eq(1).show();
            $(".widget-container").eq(0).hide();
        } else{
            if (isDispose) {
                $("#li1").addClass("on");
            } else {
                $("#li0").addClass("on");
            }
            $(".widget-container").eq(0).show();
            $(".widget-container").eq(1).hide();
        }

    }


    function getDept() {
        $.get("${pageContext.request.contextPath}/teach/dept/list/json", function(data, status){
            if("success" == status){
                data = eval("(" + data + ")");
                $(".dept").html("");
                $(".dept").append("<option value=''>全部</option>");
                for(var i=0; i<data.length; i++){
                    $(".dept").append("<option value='" + data[i].id +"'>" + data[i].name + "</option>");
                }
            }
        });
    }

    function search() {
        var isDispose = "${isDispose}";
        var isStatPage = "${isStatPage}";
        var val = {};
        var description = "";
        var departId = null;
        var beginDate = null;
        var endDate = null;
        if (isStatPage == "true") {
            description = $("#description2").val();
            departId = $("#dept2").val();
            beginDate = $("#begin").val();
            endDate = $("#end").val();
        } else {
            description = $("#description").val();
            departId = $("#dept").val();
        }
        if(description != null && description != ""){
            description = description.trim();
        }
        val.isDispose = isDispose;
        val.isStatPage = isStatPage;
        val.description = description;
        val.departId = departId;
        val.beginDate = beginDate;
        val.endDate = endDate;
        var id = "list_content";
        var url = "/dn/complain/dispose/index?sub=list&dm=${param.dm}";
        if (isStatPage == "true") {
            $.post(url, val, function (data, status) {
                if(status === "success"){
                    $("#list_content2").html(data);
                }
            });
        } else {
            myPagination(id, val, url);
        }
    }

    function loadDisposePage(id) {
        $.initWinOnTopFromLeft('编辑', '${ctp}/dn/complain/editor?type=dispose&id=' + id , '600', '280');
    }


</script>

</body>
</html>