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
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>学校工作安排</title>

    <style>
        .find {
            display: flex;
            padding: 20px 0;
            width: 96%;
            min-width: 1300px;
            margin: 20px 2%;
            background-color: #f8f8f8;
        }

        .select {
            margin-left: 20px;
            margin-right: 7px;
        }

        input {
            height: 24px;
        }

        #find,
        #enter,
        #export {
            margin-left: 10px;
            border: 1px solid #0d7bd5;
            margin-right: 5px;
            font-size: 14px;
            padding: 0 25px;
            border-radius: 3px;
            background-color: #0d7bd5;
            color: white;
        }

        #find:hover,
        #export:hover,
        #enter:hover {
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }

        select,
        input {
            height: 30px;
            width: 120px;
            border: 1px solid #d9d9d9;
        }

        .title {
            text-align: center;
            font-size: 20px;
        }

        #week {
            margin: 5px;
            text-align: center;
            width: 40px;
            height: 40px;
            font-size: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th,
        td {
            padding: 10px 20px;
            width: 12%;
            height: 40px;
            border: 1px solid #d9d9d9;
        }

        th {
            background-color: #66ccff40;
        }

        .workSheet {
            width: 96%;
            margin-left: 2%;
        }

        .gridContent {
            overflow: hidden;
            display: -webkit-box;
            -webkit-line-clamp: 2;
            -webkit-box-orient: vertical;
            width: 100%;
            height: 100%;
        }

        textarea {
            border: none;
            resize: none;
            background-color: #d9f2ff;
            color: #555;
            width: 300px;
            height: 300px;
            padding: 5px;
            outline: none
        }

        textarea::-webkit-scrollbar {
            width: 0;
            height: 0;
        }

        textarea:hover::-webkit-scrollbar {
            width: 5px;
            height: 0;
        }

        textarea::-webkit-scrollbar-thumb {
            border-radius: 10px;
            background: #ebebeb;
            box-shadow: inset 0 0 5px #66ccff;
        }

        .editorDiv {
            visibility: visible;
            width: 320px;
            height: 360px;
            background-color: #d9f2ff;
            text-align: center;
            padding: 5px 0;
            border-radius: 6px;
            position: absolute;
            z-index: 1;
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }
        /* 测试 */

        #Mon_14:hover .editorDiv {
            visibility: visible;
        }

        .Y,
        .X {
            margin: 5px;
            border-radius: 3px;
            color: #fff;
            width: 60px;
        }

        .Y {
            border: 2px solid #0d7bd5;
            background-color: #0d7bd5;
        }

        .X {
            border: 2px solid #d11736;
            border-radius: 3px;
            background-color: #d11736;
            color: #fff;
            width: 60px;
        }
    </style>

</head>

<body>
<!-- 学年、学期选择框 -->
<div class="find">
    <div class="select">
        <span>学年：</span>
        <select id="year">
        </select>
    </div>
    <div class="select">
        <span>学期：</span>
        <select id="term">
        </select>
    </div>
    <button id="find" onclick="seach()">查询</button>
    <!-- 【确认工作表】按钮需要设置仅【人事部】角色可用, 角色ID：4738 -->
    <button id="enter" onclick="queren()">确认工作表</button>
    <!-- 确认工作表后才能显示【导出】按钮 -->
    <button id="export" onclick="daochu();">导出</button>
    <%--<a id="export"  class="a2" href="#" onclick="daochu();" class="a2" >导出</a>--%>
</div>

<!-- 标题 -->
<div class="title">
    <!-- 失去焦点 或 键入Enter 提交查询   onkeydown="if(event.keyCode==13)createEditContent();" -->
    第<input type="text" id="week">周工作安排表
</div>

<!-- 工作表 -->
<div class="workSheet">
    <table>
        <thead>
            <tr>
                <th>时间</th>
                <th>星期一</th>
                <th>星期二</th>
                <th>星期三</th>
                <th>星期四</th>
                <th>星期五</th>
                <th>星期六</th>
                <th>星期天</th>
            </tr>
        </thead>
        <tbody id="biao">

        </tbody>
    </table>
</div>

<script type="text/javascript">
    //全局变量
    var  isStats="";

    $(function () {
        innerset();
        initSelect();
        quanxian();
    })
    //确认是否有权限
    function quanxian() {
    $.get("/woke/findByRold",function (d) {
        if(d==="success"){

        }else{
            var ui =document.getElementById("enter");
            ui.style.display="none";
        }

    })
    }
    function initSelect() {
        //初始填充学年、学期
        addOption('/teach/schoolYear/list/json', "year", "year", "name", function (d) {
            if (d.length > 0) {
                addOptionxq('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "term", "code", "name");
            }
        })
    }
    //绑定下拉框改变事件
    $("#year").change(function(){
        $("#term").html('<option value="">请选择</option>');
        //添加学期
        addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "term", "code", "name")

    })
    function addOptionxq(url,id,valProperty,namePropety,callback){
        var defaultTerm = '${sessionScope[sca:currentUserKey()].schoolTermCode}';
        $.get(url,function(d){
            d=JSON.parse(d);
            for(var i=0;i<d.length;i++){
                var obj=d[i];
                if(defaultTerm==obj[valProperty]) {
                    $("#" + id).append("<option selected=selected value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
                }else{
                    $("#" + id).append("<option  value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");

                }
            }
            if(callback!=null && callback!=undefined) {
                callback(d);
            }
        })
    }

    function addOption(url, id, valProperty, namePropety, callback) {
        $.get(url, function (d) {
            d = JSON.parse(d);
            for (var i = 0; i < d.length; i++) {
                var obj = d[i];
                $("#" + id).append("<option value=" + obj[valProperty] + ">" + obj[namePropety] + "</option>");
            }
            if (callback != null && callback != undefined) {
                callback(d);
            }
        })
    }
    //初始化表格
    function  innerset() {
        for(var i=1;i<18;i++){
            var rr=asd(i);
            $("#biao").append(rr);
            for(var j=1;j<8;j++){
                if(i==9){
                    var html="<td class='grid' style='background-color: #66ccff40'> <div class='gridContent'></div></td>";
                    $("#tr_"+i).append(html);
                }else if(i==17){
                    var html="<td colspan='7' class='grid' style='background-color: #66ccff40'> <div class='gridContent'></div></td>";
                    $("#tr_"+i).append(html);
                    break;
                }else {
                    var html = "<td class='grid' > <div class='gridContent'></div></td>";
                    $("#tr_"+i).append(html);
                }
            }
        }
    }

    function asd(num) {
        var html="";
        if(num==1){
            html="<tr id='tr_"+num+"'><th><div class='class'>到校时间</div><div class='time'>7:20~8:00</div></th><tr>"
        }if(num==2){
            html="<tr id='tr_"+num+"'><th><div class='class'>早读</div><div class='time'>8:00~8:15</div></th><tr>"
        }if(num==3){
            html="<tr id='tr_"+num+"'><th><div class='class'>第一节</div><div class='time'>8:20~9:00</div></th><tr>"
        }if(num==4){
            html="<tr id='tr_"+num+"'><th><div class='class'>第二节</div><div class='time'>9:10~9:50</div></th><tr>"
        }if(num==5){
            html="<tr id='tr_"+num+"'><th><div class='class'>眼保健操</div><div class='time'>9:50~9:55</div></th><tr>"
        }if(num==6){
            html="<tr id='tr_"+num+"'><th><div class='class'>大课间活动</div><div class='time'>9:55~10:30</div></th><tr>"
        }if(num==7){
            html="<tr id='tr_"+num+"'><th><div class='class'>第三节</div><div class='time'>10:30~11:10</div></th><tr>"
        }if(num==8){
            html="<tr id='tr_"+num+"'><th><div class='class'>第四节</div><div class='time'>11:20~12:00</div></th><tr>"
        }if(num==9){
            html="<tr id='tr_"+num+"'><th><div class='class'>中午</div><div class='time'>12:00~14:00</div></th><tr>"
        }if(num==10){
            html="<tr id='tr_"+num+"'><th><div class='class'>到校时间</div><div class='time'>14:00~14:15</div></th><tr>"
        }if(num==11){
            html="<tr id='tr_"+num+"'><th><div class='class'>午读</div><div class='time'>14:15~14:30</div></th><tr>"
        }if(num==12){
            html="<tr id='tr_"+num+"'><th><div class='class'>第五节</div><div class='time'>14:40~15:20</div></th><tr>"
        }if(num==13){
            html="<tr id='tr_"+num+"'><th><div class='class'>眼保健操</div><div class='time'>15:30~15:35</div></th><tr>"
        }if(num==14){
            html="<tr id='tr_"+num+"'><th><div class='class'>第六节</div><div class='time'>15:35~16:15</div></th><tr>"
        }if(num==15){
            html="<tr id='tr_"+num+"'><th><div class='class'>放学时间</div><div class='time'>16:30~16:40</div></th><tr>"
        }if(num==16){
            html="<tr id='tr_"+num+"'><th><div class='class'>放学后</div><div class='time'>16:40</div></th><tr>"
        }if(num==17){
            html="<tr id='tr_"+num+"'><th><div class='class'>全周工作</div><div class='time'></div></th><tr>"
        }
        return html;
    }


    //查询23456
    function  seach(){
        var schoolYear=$("#year").val();
        var schoolTerm=$("#term").val();
        var weekNum=$("#week").val();
       if(schoolYear!==null && schoolTerm!==""){
           if(schoolTerm!==null && schoolTerm!==""){
               if(weekNum!==null && weekNum!==""){
                    $.get("/woke/wokeBiaoAll?zhouShu="+weekNum+"&schoolYear="+schoolYear+"&schoolTerm="+schoolTerm,function (d) {
                        tianchong(d);
                    })
               }else{
                   $.error("请输入周期");
               }
           }else {
               $.error("请选择学年");
           }
       }else{
           $.error("请选择学年");
       }

    }
    //根据查询信息填充表格
    function tianchong(d) {
        document.getElementById("biao").innerHTML="";
        var dd=JSON.parse(d);
        for(var i=0;i<dd.length;i++){
            var num = parseInt(i)+1;
            var rr=asd(i+1);
            $("#biao").append(rr);
            var list=dd[i]['wokeXiangQing'];
            for(var j=0;j<list.length;j++){
                var nug=parseInt(j)+1;
                var obj=list[j];
                var jiafa=parseInt(i*7)+parseInt(j);
                if(i+1===9){
                    if(obj.contont!=null){
                        var html="<td class='grid' id='td_"+jiafa+"' style='background-color: #66ccff40'> <div class='gridContent' onclick='chakan("+obj.zhouqiId+","+jiafa+","+num+","+nug+")'>"+obj.contont+"</div></td>";
                        $("#tr_"+num).append(html);
                    }else{
                        var html="<td class='grid' id='td_"+jiafa+"' style='background-color: #66ccff40'> <div class='gridContent' onclick='chakan("+obj.zhouqiId+","+jiafa+","+num+","+nug+")'></div></td>";
                        $("#tr_"+num).append(html);
                    }
                } else if(i+1===17){
                    if(obj.contont!=null && obj.contont!=""){
                        var html="<td colspan='7' class='grid' id='td_"+jiafa+"' style='background-color: #66ccff40'> <div class='gridContent' onclick='chakan("+obj.zhouqiId+","+jiafa+","+num+","+nug+")'>"+obj.contont+"</div></td>";
                        $("#tr_"+num).append(html);
                        break;
                    }else{
                        var html="<td colspan='7' class='grid' id='td_"+jiafa+"' style='background-color: #66ccff40'> <div class='gridContent' onclick='chakan("+obj.zhouqiId+","+jiafa+","+num+","+nug+")'></div></td>";
                        $("#tr_"+num).append(html);
                        break;
                    }
                }else{
                    if(obj.contont!=null){
                        var weqr="<td class='grid' id='td_"+jiafa+"'> <div class='gridContent' onclick='chakan("+obj.zhouqiId+","+jiafa+","+num+","+nug+")'>"+obj.contont+"</div></td>";
                        $("#tr_"+num).append(weqr);
                    }else{
                        var weqr="<td class='grid' id='td_"+jiafa+"'> <div class='gridContent' onclick='chakan("+obj.zhouqiId+","+jiafa+","+num+","+nug+")'></div></td>";
                        $("#tr_"+num).append(weqr);
                    }
                }
            }
        }
    }
    function chakan(wokeId, id,jieshu,zhoushu) {
        var tdObj = document.getElementById( 'td_' + id);
        $.get("/woke/wokeBiaoObject?id="+wokeId+"&zhouShu="+zhoushu+"&jieshu="+jieshu,function (d) {
            var dd=JSON.parse(d);
            // 删除已经打开的弹窗
            if (document.getElementsByClassName('editorDiv')[0] != undefined) {
                document.getElementsByClassName('editorDiv')[0].remove();
            }
              var editDiv = document.createElement('div');
              editDiv.setAttribute('class', 'editorDiv');
              tdObj.appendChild(editDiv);
              tdObj.getElementsByClassName('editorDiv')
            var eDiv = tdObj.getElementsByClassName('editorDiv')[0];
            // 创建多行文本框
            var textarea = document.createElement('textarea');
            textarea.setAttribute("id","content")

            if(dd.isStats==0){
                var y = document.createElement('input');
                y.setAttribute('class', 'Y');
                y.setAttribute('type', 'button');
                y.setAttribute('value', '保存');
                y.setAttribute('onclick', "saveEditContent("+dd.id+");")
                var x = document.createElement('input');
                x.setAttribute('class', 'X');
                x.setAttribute('type', 'button');
                x.setAttribute('value', '取消');
                x.setAttribute('onclick', 'offEditContent();')
                eDiv.appendChild(textarea);
                eDiv.appendChild(y);
                eDiv.appendChild(x);
                if(dd.contont!=null){
                    document.getElementById("content").innerHTML=dd.contont;
                }
            }else{
                textarea.setAttribute('disabled','disabled');
                var x = document.createElement('input');
                x.setAttribute('class', 'X');
                x.setAttribute('type', 'button');
                x.setAttribute('value', '取消');
                x.setAttribute('onclick', 'offEditContent();')
                eDiv.appendChild(textarea);
                eDiv.appendChild(x);
                if(dd.contont!=null){
                    document.getElementById("content").innerHTML=dd.contont;
                }
            }

        })

    }

    // 关闭弹窗
    function offEditContent() {
        if (document.getElementsByClassName('editorDiv')[0]) {
            document.getElementsByClassName('editorDiv')[0].remove();
        }
    }

    // 保存日程内容
    function saveEditContent(id) {
        var text=$("#content").val();
        var val={}
        val.contont=text;
        val.id=id;
        // 上传文本内容
        $.post("/woke/updateById",val,function (d) {
            if(d=="success"){
                seach();
                // 关闭弹窗
                offEditContent();
            }else{
                $.error("失败");
            }
        })

    }
    //确认日程
    function queren() {
        $.confirm("确定执行此次操作？", function () {
            queding();
        });
    }
    function queding() {
            var schoolYear=$("#year").val();
            var schoolTerm=$("#term").val();
            var weekNum=$("#week").val();
            if(schoolYear!==null && schoolTerm!==""){
                if(schoolTerm!==null && schoolTerm!==""){
                    if(weekNum!==null && weekNum!==""){
                        var val={};
                        val.schoolYear=schoolYear;
                        val.xueqi=schoolTerm;
                        val.zhoushu=weekNum;
                        $.post("/woke/updateByOk",val,function (d) {
                            if(d==="success"){
                                $.success("执行成功")
                            }else{
                                $.error("执行失败")
                            }
                        })
                    }else{
                        $.error("请输入周期");
                    }
                }else {
                    $.error("请选择学年");
                }
            }else{
                $.error("请选择学年");
            }
    }
    //导出
    function  daochu() {
        var schoolYear=$("#year").val();
        var schoolTerm=$("#term").val();
        var weekNum=$("#week").val();
        if(schoolYear!==null && schoolTerm!==""){
            if(schoolTerm!==null && schoolTerm!==""){
                if(weekNum!==null && weekNum!==""){
                    var val={};
                    var url="/woke/findByDaochu?schoolYear="+schoolYear+"&xueqi="+schoolTerm+"&zhoushu="+weekNum;
                    window.open(url);
                }else{
                    $.error("请输入周期");
                }
            }else {
                $.error("请选择学年");
            }
        }else{
            $.error("请选择学年");
        }
    }
</script>
</body>

</html>