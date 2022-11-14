<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <title>Title</title>
    <%--<%@ include file="/views/embedded/common.jsp"%>--%>
    <style type="text/css">
        .ctfx_div{height: 319px;overflow: auto;}
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/jquery.nicescroll.js"></script>
    <script type="text/javascript">
       /* $(document).ready(function() {
            $(".ctfx_div").niceScroll({"styler":"fb","cursorcolor":"#BDBDBD","cursorwidth":"5"});
        });*/
    </script>
</head>
<body>
<h3>典型错题分析</h3>
<div class="error">
    <table cellspacing="0">
        <thead>
        <tr>
            <td style="width: 60px">题号</td>
            <td style="width: 95px">题型</td>
            <td>知识点</td>
            <td style="width: 95px">丢分率</td>
        </tr>
        </thead>
    </table>
    <div class="zw_ts" id="ts">暂无数据</div>
    <div class="ctfx_div">
    <table>
        <tbody id="error_body">

        </tbody>
    </table>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function(){
        getGradedxctfx();
    });
   
    //var agr = [20,25,30,75,35,80,30];
    function getGradedxctfx(){
        $.post("${ctp}/statistic/teachingEvaluationError",{"examId":${exam.id}}, function(returnData, status) {
            if ("success" === status) {
                $("#error_body").html("");
                var html = "";
                if(returnData != ""){
                    $.each(returnData,function(n,value) {
                        if(typeof(value.errorPercent) == "number" && (1-value.errorPercent) > 0){
                            html = "<tr>"+
                                    "<td style='width: 60px'>"+value.pos+"</td>"+
                                    "<td style='width: 95px'>"+value.type+"</td>"+
                                    "<td>"+value.knowledgeName+"</td>"+
//                                     "<td style='width: 80px'>"+agr[n]+"%</td>"+
                                    "<td style='width: 80px'>"+Number((1-value.errorPercent) * 100).toFixed(2)+"%</td>"+ 
                                    "</tr>";
                            $("#ts").hide();
                            $("#error_body").append(html);
                        }
                    });
                }else{
                    $("#ts").show();
                }
            }
        },'json');
    }
</script>
</html>
