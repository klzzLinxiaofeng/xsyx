<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>
        .row-fluid .span4 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 227px;
        }

        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }

        .table th {
            font-weight: normal
        }

        .table th, .table td {
            padding-left: 8px;
            font-size: 12px;
        }
    </style>
</head>
<body style="background-color: #fff !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 30px;">
                <div style="padding:0 20px 0;">
                    <table border="1" class="table table-bordered nr_table">
                        <thead>
                        <tr>
                            <th>序号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>班级</th>
                            <th>学籍号</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${voList }" var="vo" varStatus="i">
                            <tr id="${vo.studentId}_tr">
                                <td>${i.index + 1 }</td>
                                <td>${vo.studentName }</td>
                                <td><jcgc:cache tableCode="GB-XB" value="${vo.sex}"></jcgc:cache></td>
                                <td>${vo.teamName }</td>
                                <td>${vo.studentNumber }</td>
                                <td class="caozuo">
                                    <button class="btn btn-red" type="button"
                                            onclick="deletePublicStu(this, '${vo.studentId}', '${vo.publicClassId}');">
                                        删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                <div class="form-actions tan_bottom">
                    <button class="btn btn-warning" type="button" onclick="download('${publicClassId}')">导出数据</button>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function download(publicClassId) {
        url = "${ctp}/teach/publicClass/download?publicClassId=" + publicClassId;
        document.location = url;
// 		$.post(url, {"_method" : "get", "publicClassId" : publicClassId}, function() {});
    }

    // 删除已经选课的学生
    function deletePublicStu(obj, stuId,publicId) {
        $.confirm("确定执行此次操作？", function () {
            $.post("${ctp}/teach/publicClass/delPublicClassStu", {studentId: stuId,id: publicId}, function (data, status) {
                if ("success" === status) {
                    $.success("删除成功");
                    $("#" + stuId + "_tr").remove();
                }else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }
            });
        });
    }
</script>
</html>