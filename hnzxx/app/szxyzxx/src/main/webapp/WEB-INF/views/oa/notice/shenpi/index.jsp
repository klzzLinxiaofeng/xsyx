<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
    <title>通知公告</title>

</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="通知" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="wy_all">
                    <div class="sq_list">
                        <div class="search_1" style="width: 400px;">
                            <input type="text" placeholder="标题" id="ssWord" maxlength="40">
                            <select id="zhuantai">
                                <option value="0">待审批</option>
                                <option value="1">已通过</option>
                                <option value="2">未通过</option>
                            </select>
                            <a class="sea_s" href="javascript:void(0)" onclick="ss();"><i class="fa fa-search"></i></a>
                        </div>
                        <div id="notice_newList">
                            <jsp:include page="./list.jsp"/>
                        </div>
                        <!--分页 -->
                    </div>
                </div>
                <div style="padding-right:14px;">
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="notice_newList"/>
                        <jsp:param name="url"
                                   value="/office/notice/newShenpiList?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function schoolSearch(id, obj) {
        var type = "pj.school,pj.allTeacher,pj.allStudent";
        window.location.href = "${ctp}/office/notice/newIndex?dm=${param.dm}&schoolId=" + id + "&receiverType=" + type;
    }

    function ss() {
        var val = {};
        var password=$("#ssWord").val();
        var zhuantai=$("#zhuantai").val();
        if(zhuantai!=null &&zhuantai!=""){
            val.zhuangtai=zhuantai;
        }
        if(password!=null &&password!=""){
            val.name=password;
        }
        var id = "notice_newList";
        var url = "/office/notice/newShenpiList?sub=list";
        myPagination(id, val, url);
    }


    //创建
    function createNotice() {
        window.location.href = "${ctp}/office/notice/createNotice";
    }

    //编辑
    function editNotice(id) {
        window.location.href = "${ctp}/office/notice/editNotice?id=" + id;
    }

    //标题中的内容
    function showContent(id) {

        window.location.href = "${ctp}/office/notice/readContext?id=" + id + "&receiverType=${receiverType}";
    }

    // 	删除对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }

    //浏览详情
    function read(obj, id) {
        var url = "${ctp}/office/notice/readDetail?noticeId=" + id;
        $.initWinOnTopFromTop('阅读情况', url, '700', '670');

    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${ctp}/office/notice/delNotice/" + id, {"_method": "delete"}, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    window.location.reload();
                    $.success("删除成功");
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            }
        });
    }
    function shenpishang(id,zhuangtai) {
        $.confirm("确定执行此次操作？", function () {
            shenpi(id,zhuangtai);
        });
    }

    function shenpi(id,zhuangtai) {
        $.get('/office/notice/shenpi?id='+id+"&zhuangtai="+zhuangtai,function (d) {
            if ("success" === d) {
                window.location.reload();
                $.success("审批成功");
            } else {
                $.error("审批失败，系统异常", 1);
            }
        })
    }
    function chongxin(id) {
        $.get('/office/notice/shenpi?id='+id+"&zhuangtai=0",function (d) {
            if ("success" === d) {
                window.location.reload();
                $.success("提交成功");
            } else {
                $.error("审批失败，系统异常", 1);
            }
        })
    }
    function xiugai(id) {
        window.location.href = "${ctp}/office/notice/updateNoticesView?id=" + id;
    }

</script>
</body>
</html>