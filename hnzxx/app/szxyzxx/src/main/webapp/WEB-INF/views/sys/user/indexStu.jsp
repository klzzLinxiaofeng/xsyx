<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
    <%@ include file="/views/embedded/common.jsp" %>
    <%@ include file="/views/embedded/plugin/avatar_js.jsp" %>
    <link href="${pageContext.request.contextPath}/res/plugin/falgun/css/new_add.css" rel="stylesheet">
    <title>家长用户管理</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param name="title" value="家长用户管理"/>
        <jsp:param name="icon" value="icon-glass"/>
        <jsp:param name="menuId" value="${param.dm}"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        家长用户列表
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" onclick="$.refreshWin();"
                               class="a3"><i class="fa  fa-undo"></i>刷新列表</a>
<%--                            <c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 2)}">--%>
                                <a href="javascript:void(0)" class="a2" onclick="resetPwdBatch();"><i
                                        class="fa fa-plus"></i>批量重置密码</a>
                                <%--<shiro:hasRole name="${sca:getSuperAdminCode()}">--%>
                                <%--<a href="javascript:void(0)" class="a2" onclick="regPush();"><i class="fa fa-plus"></i>批量开通推送服务</a>--%>
                                <%--</shiro:hasRole> --%>
<%--                            </c:if>--%>
                            <%-- 									<c:if test="${acl:hasPermission(sessionScope[sca:currentUserKey()].id, param.dm, 0)}"> --%>
                            <!-- 										<a href="javascript:void(0)" -->
                            <!-- 										class="a4" onclick="loadCreatePage();"><i -->
                            <!-- 										class="fa fa-plus"></i>创建用户</a> -->
                            <%-- 									</c:if> --%>
                            <!-- <a href="javascript:void(0)" class="a3"><i class="fa fa-plus"></i>批量导入数据</a>
                            <a href="javascript:void(0)" class="a4"><i class="fa fa-plus"></i>添加教师</a> -->
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">

                        <div class="select_b">
                            <div class="select_div">
                                <span>账号：</span><input type="text" id="userName" name="userName"
                                                       data-id-container="userName"
                                                       style="margin-bottom: 0; padding: 6px; width: 120px; margin-right: 3px;"
                                                       placeholder="" value="">
                            </div>
                            <div class="select_div">
                                <span class="nickName2">家长姓名：</span><input type="text" id="name" name="name"
                                                                         data-id-container="name"
                                                                         style="margin-bottom: 0; padding: 6px; width: 120px; margin-right: 3px;">
                            </div>
                            <div class="select_div mobile">
                                <span>家长电话：</span><input type="text" id="mobile" name="mobile"
                                                         data-id-container="mobile"
                                                         style="margin-bottom: 0; padding: 6px; width: 120px; margin-right: 3px;">
                            </div>
                            <div class="select_div mobile" id="stuNames">
                                <span>学生姓名：</span><input type="text" id="stuName" name="stuName"
                                                         data-id-container="mobile"
                                                         style="margin-bottom: 0; padding: 6px; width: 120px; margin-right: 3px;">
                            </div>

                            <div class="select_div mobile" >
                                <span>家长车牌：</span><input type="text" id="carNo" name="carNo"
                                                         data-id-container="mobile"
                                                         style="margin-bottom: 0; padding: 6px; width: 120px; margin-right: 3px;">
                            </div>

                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th><input type="checkbox" id="checkAll"></th>
                                <th >家长姓名</th>
                                <th >电话号码</th>
                                <th >车牌</th>
                                <th>账号</th>
                                <th>账号状态</th>
                                <th >学生信息</th>
                                <th>创建时间</th>
                                <th class="caozuo">操作</th>
                            </tr>
                            </thead>
                            <tbody id="user_list_content">
                                <jsp:include page="./listStu.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="user_list_content"/>
                            <jsp:param name="url" value="/sys/user/indexStu?sub=list&dm=${param.dm}"/>
                            <jsp:param name="pageSize" value=" ${page.pageSize }"/>
                        </jsp:include>

                        <!-- 							<a href="javascript:void(0);" id="ry_xz" data-id-container="ry_xz" >选择</a> -->

                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function () {


        initCheckBtn("jc_subject");
    });

    function selectedHandler(data) {
        return false;
    }

    function search() {
        var val = {};
        val.type = 2;
        var userName = $("#userName").val();
        var name = $("#name").val();
        var mobile = $("#mobile").val();
        var stuName = $("#stuName").val();
        if (userName != null && userName != "") {
            val.userName = userName;
        }
        if (name != null && name != "") {
            val.name = name;
        }
        if (mobile != null && mobile != "") {
            val.mobile = mobile;
        }
        if (stuName != null && stuName != "") {
            val.stuName = stuName;
        }
        val.carNo=$("#carNo").val();
        var id = "user_list_content";
        var url = "/sys/user/indexStu?sub=list&dm=${param.dm}";
        myPagination(id, val, url);
    }

    // 	加载创建菜单对话框
    function loadCreatePage() {
        $.initWinOnTopFromLeft('创建账号',
            '${pageContext.request.contextPath}/sys/user/creator', '600',
            '310');
    }

    //  加载编辑菜单对话框  isCK 参数是 是否是参看
    function loadEditPage(id, isCK) {
        var mes = "编辑账号";
        if (isCK == 'disable') {
            mes = "账号详情";
        }
        $.initWinOnTopFromLeft(mes,
            '${pageContext.request.contextPath}/sys/user/editor?id=' + id
            + '&isCK=' + isCK, '600', '310');
    }


    function loadBindNamesPage(id) {
        $.initWinOnTopFromLeft("绑定的账号",
            '${pageContext.request.contextPath}/sys/user/bindingnames?id=' + id, '600', '310');
    }


    //加载角色分配页面
    function loadAssigningRolePage(id) {
        $.initWinOnTopFromTop('角色分配',
            '${pageContext.request.contextPath}/sys/ur/index?userId=' + id,
            '1000', ($(parent.window).height() - 50) + '');
    }

    function resetPwd(id) {
        $.confirm("确定重置当前账号密码？", function () {
            var ids = new Array();
            ids.push(id);
            var requestData = {};
            requestData.ids = ids;
            executeResetPwd(requestData);
        });
    }

    function resetPwdBatch() {
        var checked = $("#user_list_content input:checkbox[name='ids']:checked");
        if (checked != null && checked.length > 0) {
            $.confirm("确定重置选中的账号密码？", function () {
                var ids = new Array();
                var requestData = {};
                $.each(checked, function (index, value) {
                    ids.push($(value).val());
                });
                requestData.ids = ids;
                executeResetPwd(requestData);
            });
        } else {
            $.error("您未选中任何用户");
        }
    }

    function regPush() {
        $.initWinOnTopFromLeft("批量开通推送",
            '${pageContext.request.contextPath}/sys/user/toBatchReg', '900', '800');
// 		$.confirm("确定为选中的账号开通推送服务？", function() {
// 			var checked = $("#user_list_content input:checkbox[name='ids']:checked");
// 			var usernames = new Array();
// 			var requestData = {};
// 			$.each(checked, function(index, value) {
// 				usernames.push($(value).attr("data-username"));
// 			});
// 			requestData.usernames = usernames;
// 			executeRegPush(requestData);
// 		});
    }

    function executeResetPwd($requestData) {

        $.get("${pageContext.request.contextPath}/sys/user/editor/pwd",
            $requestData, function (data, status) {
                if ("success" === status) {
                    if ("success" === data) {
                        $.success("重置成功");
                    } else {
                        $.error("重置失败，系统异常");
                    }
                }
            });
    }

    function executeRegPush($requestData) {
        $.get("${pageContext.request.contextPath}/sys/user/push/register",
            $requestData, function (data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    var isSuc = true;
                    var usernames = "";
                    $.each(data, function (index, value) {
                        var username = value.pk;
                        var info = eval("(" + value.info + ")");
                        if (400 === info.statusCode) {
                            if ("duplicate_unique_property_exists" === info.error) {
                                usernames = usernames + (usernames != "" ? "," : "") + "【" + username + "】";
                                isSuc = false;
                            }
                        }
                    });
                    if (isSuc) {
                        $.alert("开通成功");
                    } else {
                        $.alert("账号" + usernames + "已开通过");
                    }
                } else {
                    $.error("注册失败，系统异常");
                }
            });
    }

    // 	删除菜单对话框
    function deleteObj(obj, id) {
        $.confirm("确定执行此次操作？", function () {
            executeDel(obj, id);
        });
    }

    // 	执行删除
    function executeDel(obj, id) {
        $.post("${pageContext.request.contextPath}/sys/user/" + id, {
            "_method": "delete"
        }, function (data, status) {
            if ("success" === status) {
                if ("success" === data) {
                    $.success("删除成功");
                    $("#" + id + "_tr").remove();
                    // 					$.refreshCache("zhxx", id, function() {});
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常");
                }
            }
        });
    }

    function initCheckBtn() {
        $("#checkAll").on("click", function () {
            $("#user_list_content input:checkbox[name='ids']").prop(
                "checked", this.checked);
        });
    }

</script>
</html>