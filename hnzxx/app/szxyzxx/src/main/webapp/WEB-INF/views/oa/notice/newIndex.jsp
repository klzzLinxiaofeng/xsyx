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
    <script type="text/javascript">
        $(function () {
            addClass_On();
        })

        function searchByCondition(type) {
            $("#ssWord").val("");
            window.location.href = "${ctp}/office/notice/newIndex?receiverType=" + type;
        }

        function schoolSearch(id, obj) {
            var type = "pj.school,pj.allTeacher,pj.allStudent";
            window.location.href = "${ctp}/office/notice/newIndex?dm=${param.dm}&schoolId=" + id + "&receiverType=" + type;
        }

        function ss() {
            var type = $("#receiverType").val();
            var val = {
                "title": $("#ssWord").val()
            };
            var id = "notice_newList";
            var url = "/office/notice/newIndex?sub=list&dm=${param.dm}&receiverType=" + type;
            myPagination(id, val, url);
        }

        function addClass_On() {
            var type = $("#receiverType").val();
            if (type == "dept") {
                $("#department_a").addClass("on");
            } else if (type == "own") {
                $("#own_a").addClass("on");
            } else if (type == "school") {
                $("#all_a").addClass("on");
            }else if(type == "team"){
                $("#team_a").addClass("on");
            } else {
                $("#person_a").addClass("on");
            }
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


    </script>
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
                <div class="oa_top">
                    <ul class="top_ul">
                        <li><a href="javascript:void(0)" id="all_a" onclick="searchByCondition('school')">学校通知</a></li>

                        <li><a href="javascript:void(0)" id="department_a" onclick="searchByCondition('dept')">部门通知</a>
                        </li>

                        <li><a href="javascript:void(0)" id="person_a" onclick="searchByCondition('person')">个人通知</a>
                        </li>
                        <li><a href="javascript:void(0)" id="own_a" onclick="searchByCondition('own')">我发表的</a></li>
                        <c:if test="${isTeacher}">
                            <li><a href="javascript:void(0)" id="team_a" onclick="searchByCondition('team')">班级通知</a></li>
                        </c:if>
                    </ul>



                </div>
                <div>


                </div>
                <div class="wy_all">
                    <div class="sq_list">
                        <div class="search_1" style="width: 400px;">
                            <input type="text" placeholder="标题" id="ssWord" maxlength="40">
                            <a class="sea_s" href="javascript:void(0)" onclick="ss();"><i class="fa fa-search"></i></a>
                        </div>
                        <div id="notice_newList">
                            <jsp:include page="./newList.jsp"/>
                        </div>
                        <!--分页 -->
                    </div>
                </div>
                <div style="padding-right:14px;">
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="notice_newList"/>
                        <jsp:param name="url"
                                   value="/office/notice/newIndex?sub=list&dm=${param.dm}&receiverType=${receiverType}&schoolId=${schoolId}"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>