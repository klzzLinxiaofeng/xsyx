<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2022/1/8
  Time: 22:30
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2021/10/25
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp" %>
<html>
<head>
    <title>收作业</title>
    <style>
        .content-widgets {
            padding: 10px;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    作业管理
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"  class="a2" href="#" onclick="jieshu();" class="a2" >结束收作业</a>
                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <input type="text" style="display: none" value="${id}" id="jobid"/>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_div"><span>学生标识：</span>
                        <input type="text"  class="span4" id="studentId"/>
                    </div>
                </div>
            </div>
            <div class="content-widgets">
                A<input type="radio"  name="pingji" checked value="A"/>
                B<input type="radio"  name="pingji" value="B"/>
                C<input  type="radio" name="pingji" value="C"/>
                D<input  type="radio" name="pingji" value="D"/>
            </div>
                    <div class="clear"></div>

                <table class="responsive table table-striped" id="data-table">
                    <thead>
                    <tr role="row">
                        <th style="text-align:center;">序号</th>
                        <th style="text-align:center;">姓名</th>
                        <th style="text-align:center;">状态</th>
                    </tr>
                    </thead>
                    <tbody id="publicClass_list_content">
                    <jsp:include page="./shouzuoyeList.jsp"/>
                    </tbody>
                </table>
                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                    <jsp:param name="id" value="publicClass_list_content"/>
                    <jsp:param name="url" value=""/>
                    <jsp:param name="pageSize" value="${page.pageSize}"/>
                </jsp:include>
            <div class="clear"></div>
        </div>
    </div>

</div>

<script type="text/javascript">
    $(function () {
        search();
    })
    function search() {
        var id=$("#jobid").val();
        var ids = "publicClass_list_content";
        var val={}
        val.id=id;
        myPagination(ids , val, "/home/woke/shouye?sub=list");
       /* setTimeout(function() {
            search();
        },3000);*/
    }

    $("#studentId").bind("input propertychange",function(){
        var id=$("#jobid").val();
        var studentId=$("#studentId").val();
       var dengji=$('input[name="pingji"]:checked').val();
       if(dengji!=null && dengji!=""){
           if(studentId!=null && studentId!=null){
               $.get("/home/woke/saoMaQiang?studentId="+studentId+"&jobId="+id+"&dengji="+dengji,function (d) {
                   if(d==="success"){
                       search();
                       $("#studentId").val("")
                   }
               })
           }

       }

    });


   function jieshu(){
        var id=$("#jobid").val();
       $.get("/home/woke/jieshu?id="+id,function (d) {

       });
       history.back(-1);

    }
</script>

</body>
</html>
