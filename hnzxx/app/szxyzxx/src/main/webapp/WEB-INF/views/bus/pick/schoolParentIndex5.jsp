<%@ page import="com.sleepycat.je.tree.IN" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title>播报大屏5</title>
    <style>
        body {
            background-color: #f2f2f2;
        }

        .title {
            width: 100%;
            height: 100px;
            background-color: #0d7bd5;
            text-align: center;
        }

        .logo {
            position: absolute;
            padding-top: 20px;
            left: 20px;
        }

        .set {
            position: absolute;
            padding-top: 30px;
            right: 40px;
        }

        .set img {
            width: 40px;
        }

        h1 {
            display: inline-block;
            color: #fff;
        }

        .in_divs,
        .out_divs {
            width: 96%;
            margin-top: 30px;
            margin-left: 2%;
            background-color: #fff;
            border: 1px solid #000;
            transition: height .5s;
            overflow: hidden;
        }

        .icon {
            width: 200px;
            height: 100%;
            text-align: center;
        }

        .icon img {
            padding-top: 20px;
            width: 100px;
        }

        .in_divs .icon h2 {
            color: #4c91e2;
        }

        .out_divs .icon h2 {
            color: #e2504f;
        }

        .stuList {
            flex: 1;
            height: 100%;
            padding-left: 2%;
            overflow: hidden;
        }

        .content {
            float: left;
            width: 47%;
            height: 80px;
            margin-top: 15px;
            margin-right: 2%;
            border: 1px solid #777;
            border-radius: 15px;
            display: flex;
            justify-content: space-between;
        }

        .in_divs .content {
            background-color: #c0e9fb;
        }

        .out_divs .content {
            background-color: #f5bfc6;
        }

        .in_divs .content h2 {
            color: #4c91e2;
        }

        .out_divs .content h2 {
            color: #e2504f;
        }

        .classes {
            margin-left: 10%;
        }

        .plates {
            margin-right: 10%;
        }

        .setPath {
            position: fixed;
            margin: auto;
            top: 300px;
            left: 0;
            right: 0;
            width: 700px;
            height: 500px;
            border: 1px solid #777;
            background-color: #f2f2f2;
            z-index: 999;
        }

        .setPathTitle {
            width: 100%;
            height: 30px;
            background-color: #6cf;
        }

        .setPathTitle span {
            color: #fff;
            float: left;
            margin-top: 3px;
            margin-left: 5px;
        }

        #setColse {
            width: 20px;
            height: 20px;
            float: right;
            margin-top: 5px;
            margin-right: 5px;
        }

        .setPath form {
            height: 470px;
            width: 100%;
            overflow: auto;
        }

        .setPath form::-webkit-scrollbar {
            width: 0px;
            height: 300px;
        }

        .time {
            font-size: 14px;
            width: 200px;
        }

        .playSet {
            width: 650px;
            height: 100px;
            margin-left: 20px;
            margin-bottom: 20px;
            border-bottom: 1px solid #777;
        }

        .play {
            display: inline-block;
            margin-left: 50px;
            width: 540px;
            height: 80px;
        }

        .x,
        .y {
            display: inline-block;
            width: 30px;
        }

        .x img,
        .y img {
            width: 20px;
        }

        .btu {
            width: 200px;
            display: flex;
            margin-left: 250px;
            justify-content: space-between;
        }

        .submit {
            width: 80px;
            border-radius: 5px;
            background-color: #0dd068;
        }

        .add {
            width: 80px;
            border-radius: 5px;
            background-color: #6cf;
        }
    </style>
</head>
<body>

<a class="logo" href="https://www.dglbxsyx.net/home">
    <img src="${ctp}/images/logo_new.png" alt="logo_new">
</a>


<div class='title'>
    <h1>全校家长接送</h1>
</div>

<!--停车场接送-->
<div class="in_divs" id="parkIn" style="height: 200px;display: flex;" onclick="change('parkIn')">
    <div class="icon">
        <img src="${ctp}/images/停车场_parking.png" alt="停车场接送">
        <h2>停车场接送</h2>
    </div>
</div>

<!--停车场出校-->
<div class="out_divs" id="parkOut" style="height: 200px;display: flex;" onclick="change('parkOut')">
    <div class="icon">
        <img src="${ctp}/images/停车场_parking (1).png" alt="停车场出校">
        <h2>停车场出校</h2>
    </div>
</div>

<!--校门口接送-->
<div class="in_divs" id="gateIn" style="height: 200px;display: flex;" onclick="change('gateIn')">
    <div class="icon">
        <img src="${ctp}/images/校门.png" alt="校门口接送">
        <h2>校门口接送</h2>
    </div>
</div>

<!--校门口出校-->
<div class="out_divs" id="gateOut" style="height: 200px;display: flex;" onclick="change('gateOut')">
    <div class="icon">
        <img src="${ctp}/images/校门_parking (1).png" alt="校门口出校">
        <h2>校门口出校</h2>
    </div>
</div>


<script type="text/javascript">
    var cacheDateStr ;
    $(function () {
        cacheDateStr = getDateStr(new Date())
        getStuCentont();
    })

    // 点击改变列表高度 并 隐藏其他列表
    function change(elm) {
        var divs = ['parkIn', 'parkOut', 'gateIn', 'gateOut']
        // 获取元素高度
        var div = document.getElementById(elm);
        var h = parseInt(div.style.height);

        if (h == 200) {
            // 如果为200px
            // 则 放大至1000px
            div.style.height = "900px";
            // 并且将其他列表隐藏
            for (var i in divs) {
                if (divs[i] == elm) {
                    continue;
                } else {
                    document.getElementById(divs[i]).style.display = 'none';
                }
            }
        } else if (h == 900) {
            // 如果为1000px
            // 则 缩小至200px
            div.style.height = "200px";
            // 并且显示其他列表
            for (var i in divs) {
                if (divs[i] == elm) {
                    continue;
                } else {
                    document.getElementById(divs[i]).style.display = 'flex';;
                }
            }
        }
    }


    function getDateStr(date) {
        return date.getFullYear() + "-" + date.getMonth() + "-" + date.getDate();
    }
    function getStuCentont() {
        var now = new Date();
        var nowDateStr = getDateStr(now);
        var nowHour = now.getHours();
        if (nowDateStr == cacheDateStr) {
            if (nowHour >= 12 && nowHour < 20) {
                var divs = ['parkIn', 'parkOut', 'gateIn', 'gateOut'];
                //测试数据
                $.get("/bus/pickData/schoolParentList?id=5", function (d) {
                    var schoolParentList = JSON.parse(d);

                    for (var i = 0; i < divs.length; i++) {
                        var div = document.getElementById(divs[i]);
                        // 判断是否有数据
                        var obj;
                        if (schoolParentList[divs[i]].length>0){
                            obj = schoolParentList[divs[i]];
                        }else{
                            obj = [];
                        }
                        if (obj.length <= 0) {
                            if (document.getElementById(divs[i] + "_onStu")) {
                                continue;
                            } else {
                                // 没有学生
                                // 先删除滚动
                                if (document.getElementById(divs[i] + "Marquee")){
                                    document.getElementById(divs[i] + "Marquee").remove();
                                }
                                // 创建列表
                                var oList = document.createElement('div');
                                oList.setAttribute("id", divs[i] + "StuList");
                                oList.setAttribute('class', 'stuList');
                                // 插入列表
                                div.appendChild(oList);

                                var getList = document.getElementById(divs[i] + "StuList");

                                var nStu = document.createElement('h2');
                                nStu.setAttribute('class', 'onStu');
                                nStu.setAttribute('id', divs[i] + "_onStu")
                                nStu.setAttribute("style", "text-align:center;margin-top:80px;");
                                getList.appendChild(nStu);
                                document.getElementById(divs[i] + "_onStu").innerHTML = "暂无学生";
                            }
                        } else {
                            if (document.getElementById(divs[i] + "Marquee")) {
                                document.getElementById(divs[i] + "Marquee").innerHTML = '';
                            } else {
                                // 先删除【暂无学生】
                                if (document.getElementById(divs[i] + "StuList")) {
                                    document.getElementById(divs[i] + "StuList").remove();
                                }
                                // 创建滚动
                                var mar = document.createElement('marquee');
                                mar.setAttribute("id", divs[i] + "Marquee");
                                mar.setAttribute("direction", "up");
                                mar.setAttribute("scrollAmount", "2");
                                // 插入滚动
                                div.appendChild(mar);
                            }
                                // 获取滚动
                                var getMar = document.getElementById(divs[i] + "Marquee");

                                // 创建列表
                                var oList = document.createElement('div');
                                oList.setAttribute("id", divs[i] + "StuList");
                                oList.setAttribute('class', 'stuList');

                                // 插入列表
                                getMar.appendChild(oList);

                                var getList = document.getElementById(divs[i] + "StuList");
                                getList.innerHTML = "";
                                for (var v in obj) {

                                    // 创建学生信息
                                    var dContent = document.createElement('div');
                                    dContent.setAttribute('id', divs[i] + "_" + obj[v].id);
                                    dContent.setAttribute('class', 'content');
                                    // 插入学生信息
                                    getList.appendChild(dContent);

                                    // 获取学生信息
                                    var getCentent = document.getElementById(divs[i] + "_" + obj[v].id);

                                    // 创建班级
                                    var hClass = document.createElement('h2');
                                    hClass.setAttribute('class', 'classes');
                                    hClass.setAttribute('id', divs[i] + "_" + obj[v].id + "_calsses");
                                    // 插入班级
                                    getCentent.appendChild(hClass);

                                    // 创建姓名
                                    var hName = document.createElement('h2');
                                    hName.setAttribute('class', 'stuName');
                                    hName.setAttribute('id', divs[i] + "_" + obj[v].id + "_stuName");
                                    // 插入姓名
                                    getCentent.appendChild(hName);
                                    if(divs[i]=='parkIn' || divs[i]=='parkOut'){
                                        // 创建车牌
                                        var hPlate = document.createElement('h2');
                                        hPlate.setAttribute('class', 'plates');
                                        hPlate.setAttribute('id', divs[i] + "_" + obj[v].id + "_plates");
                                        // 插入车牌
                                        getCentent.appendChild(hPlate);

                                        // 生成学生信息
                                        document.getElementById(divs[i] + "_" + obj[v].id + "_calsses").innerHTML = obj[v].teamName;
                                        document.getElementById(divs[i] + "_" + obj[v].id + "_stuName").innerHTML = obj[v].name;
                                        if (obj[v].license_plate != null) {
                                            document.getElementById(divs[i] + "_" + obj[v].id + "_plates").innerHTML = obj[v].license_plate.split(',')[0];
                                        } else {
                                            document.getElementById(divs[i] + "_" + obj[v].id + "_plates").innerHTML = "";
                                        }
                                    }else{
                                        document.getElementById(divs[i] + "_" + obj[v].id + "_calsses").innerHTML = obj[v].teamName;
                                        document.getElementById(divs[i] + "_" + obj[v].id + "_stuName").innerHTML = obj[v].name;

                                    }

                                }
                            }
                        }
                        setTimeout(function () {
                            getStuCentont();
                        }, 20000);
                })
            } else {
                setTimeout(function() {
                    getStuCentont();
                },20000);
            }
        } else {
            setTimeout(function() {
                getStuCentont();
            },20000);
        }
    }

</script>
</body>
</html>
