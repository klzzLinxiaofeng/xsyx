<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style>

    </style>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                    <div class="control-group" style="padding-left: 20px">
                        <span>
                                    <span style="font-size: 17px">统计时间：</span>

                                    <input type="text" id="beginDate" name="beginDate"
                                           onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"
                                           style="margin-bottom: 0;padding: 6px;width: 170px;height: 30px;"
                                           placeholder="2020-09-01 12:45"
                                           value="">
                            <span style="font-size: 17px">&nbsp;至&nbsp;</span>
                                    <input type="text" id="expiryDate" name="expiryDate"
                                           onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'});"
                                           style="margin-bottom: 0;padding: 6px;width: 170px;height: 30px;"
                                           placeholder="2020-09-05 14:25"
                                           value="">
                            </span>
                        <br/>
                        <span style="font-size: 15px">注：时间段内从开始到截止完成的选课会被汇总于下面的表格中</span>
                        <br/>
                        <span style="font-size: 15px">示意图：</span>
                        <br/>
                        <img src="${ctp}/res/images/export.png" style="width: 90%; height: 130px;" onclick="Change(this);">
                        <div class="control-group" style="margin-top: 30px; margin-left: 50px">
                            <button type="button" class="btn btn-primary" onclick="classCount()">导出选课统计</button>
                            <button type="button" class="btn btn-primary" onclick="choseStu()">导出已选学生</button>
                            <button type="button" class="btn btn-primary" onclick="noChoseStu()">导出未选学生</button>
                        </div>
                    </div>

            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">


    function Change(obj) {
        var imgSrc = $(obj).attr("src");
        window.open(imgSrc);
    }
    function classCount() {

        var beginDate = $("#beginDate").val();
        var endDate = $("#expiryDate").val();
        if (beginDate == null || beginDate == "") {
            alert("请输入完整的日期");
            return;
        }
        if (endDate == null || endDate == "") {
            alert("请输入完整的日期");
            return;
        }
        url = "${ctp}/teach/publicClass/download/classCount?beginDate=" + beginDate + "&endDate=" + endDate + "&type=1";
        document.location = url;
    }

    // 已选课学生
    function choseStu() {
        var beginDate = $("#beginDate").val();
        var endDate = $("#expiryDate").val();
        if (beginDate == null || beginDate == "") {
            alert("请输入完整的日期");
            return;
        }
        if (endDate == null || endDate == "") {
            alert("请输入完整的日期");
            return;
        }
        url = "${ctp}/teach/publicClass/download/classCount?beginDate=" + beginDate + "&endDate=" + endDate + "&type=2";
        document.location = url;
    }

    // 未选课学生
    function noChoseStu() {
        var beginDate = $("#beginDate").val();
        var endDate = $("#expiryDate").val();
        if (beginDate == null || beginDate == "") {
            alert("请输入完整的日期");
            return;
        }
        if (endDate == null || endDate == "") {
            alert("请输入完整的日期");
            return;
        }
        url = "${ctp}/teach/publicClass/download/classCount?beginDate=" + beginDate + "&endDate=" + endDate + "&type=3";
        document.location = url;
    }

</script>
</html>