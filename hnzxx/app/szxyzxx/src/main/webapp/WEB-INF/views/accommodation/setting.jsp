<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2022/1/7
  Time: 9:49
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title>设置水电价格</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <table class="responsive table table-striped" id="data-table">
                    <thead>
                    <tr role="row">
                        <th>名称</th>
                        <th>价格</th>
                    </tr>
                    </thead>
                    <tbody id="publicClass_list_content">

                    </tbody>
                </table> </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        seach();
    });
    function seach() {
        $.get("/HouseAmount/findByAll",function (d) {
            var arr=JSON.parse(d);
            $("#publicClass_list_content").html("");
            for(var i=0;i<arr.length;i++){
                var html="<tr><td>"+arr[i]['name']+"</td><td><input id='td_"+arr[i]['id']+"' value='"+arr[i]['jine']+"'/></td></tr>";
                $("#publicClass_list_content").append(html);
                $("#td_"+arr[i].id).attr("onchange","bianji('"+arr[i].id+"')");
            }
        })
    }

    function bianji(id) {
        var val={};
        var jine=$("#td_"+id).val();
        val.jine=jine;
        val.id=id;
        $.post("/HouseAmount/update",val,function (d) {
            if(d=="success"){
                $.success("修改成功");
                seach();
            }else{
                $.error("修改失败");
            }
        })
    }
</script>
</body>
</html>
