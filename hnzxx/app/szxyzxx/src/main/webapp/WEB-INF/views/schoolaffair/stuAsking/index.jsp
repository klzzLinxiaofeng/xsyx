<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div">
                                <span>班级： </span>
                                <select id="teamId" name="teamId" style="width:160px;">
                                </select>
                            </div>

                            <%--1：审核中 2：已通过 3：已驳回--%>
                            <div class="select_div">
                                <span>审核状态:  &nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <select name="indiaStatus" id="indiaStatus">
                                    <option value="">请选择</option>
                                    <option value="2">已通过</option>
                                    <option value="1">审核中</option>
                                    <option value="3">已驳回</option>
                                </select>
                            </div>
                            <div class="select_div">
                                <span>申请时间:  &nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <input type="text" id="createDate" name="createDate" style="width:187px"  autocomplete="off"
                                       class="span2 left_red {required:true}"
                                       placeholder="请输入开始时间" value="${meeting.starttime}"
                                       onclick="WdatePicker({maxDate:'#F{$dp.$D(\'createDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});">
                                &nbsp;&nbsp;-&nbsp;&nbsp;
                                <input type="text" id="endDate" name="endDate" style="width:187px"  autocomplete="off"
                                       class="span2 left_red {required:true}"
                                       placeholder="请输入结束时间" value="${meeting.starttime}"
                                       onclick="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}',dateFmt:'yyyy-MM-dd HH:mm'});">

                            </div>



                            <div class="select_div">
                                <span>学生:  &nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <input type="text" id="stuName" name="title"
                                       style="margin-bottom: 0; padding: 4px; width: 150px;" placeholder="请输入学生名称">
                            </div>
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>申请时间</th>
                                <th>班级</th>
                                <th>姓名</th>
                                <th>请假时长</th>
                                <th>班主任</th>
                                <th>审批状态</th>
                                <th class="caozuo" style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="publicClass_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="publicClass_list_content"/>
                            <jsp:param name="url" value="/stu/stuAsking/index?sub=list&dm=${param.dm}"/>
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
<script type="text/javascript">

    $(function () {
        //班级下拉
        $.TeamSelector({
            "selector": "#teamId",
            "condition": {},
            "afterHandler": function (selector) {
            }
        });
    });

    function loadViewerPage(id) {
        $.initWinOnTopFromLeft('详情', '${ctp}/stu/stuAsking/viewer?id=' + id, '1000', '550');
    }

    function search() {
        var val = {};
        var stuName = $("#stuName").val();
        var createDate = $("#createDate").val();
        var endDate = $("#endDate").val();
        var teamId = $("#teamId").val();
        var indiaStatus = $("#indiaStatus").val();
        if (teamId != null && teamId != "") {
            val.teamId = teamId;
        }
        if (stuName != null && stuName != "") {
            val.stuName = stuName;
        }
        if (indiaStatus != null && indiaStatus != "") {
            val.indiaStatus = indiaStatus;
        }
        if (createDate != null && createDate != "") {
            val.beginDate = createDate;
            if (endDate == null || endDate == "") {
                $.alert("请完善日期查询范围");
                return;
            }
        }
        if (endDate != null && endDate != "") {
            val.endDate = endDate;
            if (createDate == null || createDate == "") {
                $.alert("请完善日期查询范围");
                return;
            }
        }

        var id = "publicClass_list_content";
        var url = "/stu/stuAsking/index?sub=list&dm=${param.dm}";
        myPagination(id, val, url);
    }

</script>
</html>