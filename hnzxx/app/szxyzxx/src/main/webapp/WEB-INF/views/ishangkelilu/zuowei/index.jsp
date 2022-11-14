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
    <title></title>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
</head>
<body>
<div class="row-fluid">
    <input id="zuoweihao" type="hidden" value="${zuowei}"/>
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    人员选择
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b" id="ddd">
                        <div class="select_div"><span>姓名：</span>
                            <input id="stuname" name="stuname" class=""  style="width:200px;padding-top: 4px;"/>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                    </div>
                    <div>
                        <label>已选：</label>

                        <button style="text-align:right;" type="button" onclick="asal()">添加</button>
                        <div id="asd">

                        </div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>姓名</th>
                            <th>性别</th>
                            <th>联系方式</th>
                            <th>家长联系方式</th>
                            <th>所在班级</th>
                            <th>在读状况</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/study/habits/zhaoren"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                </div>
            </div>

        </div>
    </div>
</div>
</div>

<script type="text/javascript">
    
    function queren(studentId,stuName,teamId) {

        var score =[];
        $("input[name='studentId']").each(function(){
            score.push($(this).val());
        })
        if(score.length>0){
           $("#hahha").remove();
        }
        $("#asd").append("<div id='hahha'><label>"+stuName+"</lable><input STYLE='display: none' id='studentId' name='studentId' value=" + studentId + ">" +
            "<input STYLE='display: none' id='teamId' name='teamId' value=" + teamId + "></div>");
    }

    function search() {
        var stuName=$("#stuname").val();
        var id = "publicClass_list_content";
        var url = "/study/habits/zhaoren";
        var val={};
        val.stuName=stuName;
        myPagination(id, val,url);
    }
    function asal() {
        var stuName=$("#studentId").val();
        var zuoweihao=$("#zuoweihao").val();
        var teamId=$("#teamId").val();
        $.post("/study/habits/addZuowei?studentId="+stuName+"&teamId="+teamId+"&zuoweihao="+zuoweihao,function (d) {
            if(d==="success"){
                $.success("添加成功");
               /* parent.$("iframe").each(function () {
                    $(this).attr('src', $(this).attr('src'));//需要引用jquery
                })*/
                $.closeWindow();
            }else {
                $.error("失败");
            }
        })
    }

    function setupSeat() {
        var asd = $("#bj").val();
        if (asd ===null || asd==="") {
            document.getElementById('seat').innerHTML = "";

            // 获取 组 数量
            var oForm = document.getElementById('form').value;
            // 获取 列 数量
            var oLine = document.getElementById('line').value;
            // 获取 排 数量
            var oRow = document.getElementById('row').value;
            // 获取 seat
            var oSeat = document.getElementById('seat');
            var a=1;
            for (var f = 0; f < oForm; f++) {
                // 创建 组 列表
                var lForm = document.createElement('div');
                lForm.setAttribute('id', String(f + 1) + 'form');
                lForm.setAttribute('class', 'form');
                lForm.style.width = ((90 - ((oForm - 1)) * 5) / oForm) + '%';
                // 插入 组 列表
                oSeat.appendChild(lForm);

                for (var l = 0; l < oLine; l++) {
                    // 获取 组
                    var getForm = document.getElementById(String(f + 1) + 'form');
                    // 创建 列
                    var dLine = document.createElement('div');
                    dLine.setAttribute('id', String(f + 1) + 'form-' + String(l + 1) + 'line');
                    dLine.setAttribute('class', 'line');
                    dLine.style.width = (95 / oLine) + '%';
                    // 插入 列
                    getForm.appendChild(dLine);

                    for (var r = 0; r < oRow; r++) {
                        // 获取 列
                        var getLine = document.getElementById(String(f + 1) + 'form-' + String(l + 1) + 'line');
                        // 创建 座位
                        var dSeat = document.createElement('div');
                        dSeat.setAttribute('class', 'seat');
                        dSeat.setAttribute('name', 'seat');
                        //dSeat.style.height = '50px';
                        dSeat.setAttribute("value",a);
                        // 生成 座位号
                        dSeat.setAttribute('seatId', a);
                        var button = document.createElement('button');
                        button.setAttribute('onclick', 'tianjia('+a+')');
                        button.innerText = "添加";
                        button.style.width = 'auto';
                        dSeat.appendChild(button);
                        var dd=document.createElement('input');
                        dd.setAttribute('id', 'ids'+a);
                        dd.setAttribute('type','hidden');
                        dSeat.appendChild(dd);
                        // // 获取 座位
                        // var getSeat = document.getElementById(String(f + 1) + 'form-' + String(l + 1) + 'line-' + String(r + 1) + 'row');
                        // // 创建 学生姓名
                        var stuName = document.createElement('input')
                        stuName.setAttribute('class', 'stuasd');
                        stuName.setAttribute('name', 'stuName');
                        stuName.setAttribute('id', "id"+a);
                        stuName.style.height = '50px';
                        stuName.style.width ='100%';
                        stuName.innerText = "";
                        stuName.setAttribute("type","button");
                        stuName.setAttribute('onclick','dianpin('+a+')');
                        a++;
                        //     // 插入 学生姓名
                        dSeat.appendChild(stuName);
                        // 插入 座位
                        getLine.appendChild(dSeat);
                    }
                }
            }
        } else {
            $.get("/study/habits/zuowei?teamId="+asd, function (d) {
                var a=1;
                document.getElementById('seat').innerHTML = "";
                d = JSON.parse(d);
                // 获取 组 数量
                var oForm = document.getElementById('form').value;
                // 获取 列 数量
                var oLine = document.getElementById('line').value;
                // 获取 排 数量
                var oRow = document.getElementById('row').value;
                // 获取 seat
                var oSeat = document.getElementById('seat');

                for (var f = 0; f < oForm; f++) {
                    // 创建 组 列表
                    var lForm = document.createElement('div');
                    lForm.setAttribute('id', String(f + 1) + 'form');
                    lForm.setAttribute('class', 'form');
                    lForm.style.width = ((90 - ((oForm - 1)) * 5) / oForm) + '%';
                    // 插入 组 列表
                    oSeat.appendChild(lForm);

                    for (var l = 0; l < oLine; l++) {
                        // 获取 组
                        var getForm = document.getElementById(String(f + 1) + 'form');
                        // 创建 列
                        var dLine = document.createElement('div');
                        dLine.setAttribute('id', String(f + 1) + 'form-' + String(l + 1) + 'line');
                        dLine.setAttribute('class', 'line');
                        dLine.style.width = (95 / oLine) + '%';
                        // 插入 列
                        getForm.appendChild(dLine);

                        for (var r = 0; r < oRow; r++) {
                            // 获取 列
                            var getLine = document.getElementById(String(f + 1) + 'form-' + String(l + 1) + 'line');
                            // 创建 座位

                            var dSeat = document.createElement('div');
                            dSeat.setAttribute('id', String(f + 1) + 'form-' + String(l + 1) + 'line-' + String(r + 1) + 'row');
                            dSeat.setAttribute('class', 'seat');
                            dSeat.setAttribute('name', 'seat');
                            //dSeat.style.height = '60px';
                            // 生成 座位号
                            dSeat.setAttribute('seatId', a);
                            var button = document.createElement('button');
                            button.setAttribute('onclick', 'tianjia('+a+')');

                            //button.setAttribute('value', '添加');
                            button.style.width = 'auto';
                            button.innerText = "添加";

                            dSeat.appendChild(button);

                            // // 创建 学生姓名
                            var dd=document.createElement('input');
                            dd.setAttribute('id', 'ids'+a);
                            dd.setAttribute('type','hidden');


                            var stuName = document.createElement('input');
                            stuName.setAttribute('type','button')
                            stuName.setAttribute('class', 'stuName');
                            stuName.setAttribute('name', 'stuName');
                            stuName.setAttribute('id', 'id'+a);
                            stuName.setAttribute('onclick','dianpin('+a+')');
                            stuName.style.height = '50px';
                            stuName.style.width ='100%';
                            var b=0;
                            for (var i = 0; i < d.length; i++) {
                                var obj = d[i];
                                if(a===obj["haoMa"]){
                                    stuName.setAttribute('value',obj['studentName']);
                                    dd.setAttribute('value',obj['studentId']);
                                    // stuName.innerHTML=obj['studentName'];
                                    b=1;
                                }
                            }
                            if(b==0){
                                stuName.setAttribute("value","");
                            }
                            a++;
                            dSeat.appendChild(dd);
                            //     // 插入 学生姓名
                            dSeat.appendChild(stuName);
                            // 插入 座位
                            getLine.appendChild(dSeat);
                        }
                    }
                }

            })

        }
    }




</script>
</body>
</html>
