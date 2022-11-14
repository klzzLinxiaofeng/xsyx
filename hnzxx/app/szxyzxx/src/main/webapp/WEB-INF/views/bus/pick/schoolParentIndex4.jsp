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
    <title>播报大屏4</title>
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

                $.get("/bus/pickData/schoolParentList?id=4", function (d) {
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

    //添加播报时间代码
    // 关闭【设置播放时间】对话框
    function quitSet() {
        document.getElementsByClassName('setPath')[0].style.display = "none";
    }

    // 打开【设置播放时间】对话框
    function openSet() {
        document.getElementsByClassName('setPath')[0].style.display = "block";
    }
    //初始化播报时间
    function getTime() {
        $.get("/GuangBo/bobaoTime",function (d) {
            var test=JSON.parse(d);
            document.getElementById("timeList").innerHTML="";
            for (var i=0; i<test.length;i++) {
                // 添加播放时间

                var obj=test[i];
                var form = document.getElementById("timeList");
                var playSet = document.createElement('div');
                var playLen = document.getElementsByClassName('playSet').length;
                playSet.setAttribute('id', 'playSet_' + (parseInt(playLen) + 1));
                playSet.setAttribute('class', 'playSet');
                playSet.setAttribute('playTimeId', obj['playTimeId']);
                form.appendChild(playSet);

                // 添加设备号多选框，播报时间段，关闭按钮div,保存按钮
                var getPlaySet = form.lastElementChild;
                var play = document.createElement('div');
                var x = document.createElement('div');
                var y = document.createElement('div');
                play.setAttribute('class', 'play');
                x.setAttribute('class', 'x');
                y.setAttribute('class','y')
                getPlaySet.appendChild(play);
                getPlaySet.appendChild(x);
                getPlaySet.appendChild(y);

                // 添加设备号，播报时间段内容
                var getPlay = getPlaySet.getElementsByClassName('play')[0];
                var deiviceId = document.createElement('p');
                //deiviceId.setAttribute('id', 'pDeiviceId');
                getPlay.appendChild(deiviceId);
                var time = document.createElement('p');
                //time.setAttribute('id', 'pTime');
                getPlay.appendChild(time);

                if (obj['deiviceList'].includes("20620")) {
                    var ioeeo = '<input type="checkbox" name="deviceId" class="deviceId" value="20620" checked>';
                } else {
                    var ioeeo = '<input type="checkbox" name="deviceId" class="deviceId" value="20620">';
                }

                if (obj['deiviceList'].includes("20570")) {
                    var ioelp = '<input type="checkbox" name="deviceId" class="deviceId" value="20570" checked>';
                } else {
                    var ioelp = '<input type="checkbox" name="deviceId" class="deviceId" value="20570">';
                }

                if (obj['deiviceList'].includes("20619")) {
                    var ioebo = '<input type="checkbox" name="deviceId" class="deviceId" value="20619" checked>';
                } else {
                    var ioebo = '<input type="checkbox" name="deviceId" class="deviceId" value="20619">';
                }

                var startTime = '<input type="datetime-local" name="startTime" class="startTime time" value="' + /*switchTime(*/obj['startTime']/*)*/ + '">';
                var overTime = '<input type="datetime-local" name="overTime" class="overTime time" value="' + /*switchTime(*/obj['overTime']/*) */+ '">';

                // 写入播报设备，播报时间段input
                getPlay.getElementsByTagName('p')[0].innerHTML = '播报设备号：' + ioeeo + '20620&nbsp;' + ioelp + '20570&nbsp;' + ioebo + '20619';
                getPlay.getElementsByTagName('p')[1].innerHTML = '播报时间段：' + startTime + '&nbsp;-&nbsp;' + overTime;

                // 创建关闭按钮
                var getX = getPlaySet.getElementsByClassName('x')[0];
                var img = document.createElement('img');
                img.setAttribute('src', '${ctp}/images/关闭_close-one.png');
                img.setAttribute('onmousedown', "deleteTime(" + "'playSet_" + (parseInt(playLen) + 1) + "');")
                getX.appendChild(img);
                var divId='playSet_'+ (parseInt(playLen) + 1);
                // 创建保存按钮
                var getY = getPlaySet.getElementsByClassName('y')[0];
                var img2 = document.createElement('img');
                img2.setAttribute('src', '${ctp}/images/baucun.png');
                img2.setAttribute('onmousedown', "baocunTime('"+divId+"')")
                getY.appendChild(img2);
            }
        })

    }

    // 添加播放时间
    function addTime() {

        // 添加播放时间
        var form = document.getElementById("timeList");
        var playSet = document.createElement('div');
        var playLen = document.getElementsByClassName('playSet').length;
        var divId='playSet_'+ (parseInt(playLen) + 1);
        playSet.setAttribute('id', 'playSet_' + (parseInt(playLen) + 1));
        playSet.setAttribute('class', 'playSet');
        playSet.setAttribute('playTimeId',"");
        form.appendChild(playSet);

        // 添加设备号多选框，播报时间段，关闭按钮div
        var getPlaySet = form.lastElementChild;
        var play = document.createElement('div');
        var x = document.createElement('div');
        var y = document.createElement('div');
        play.setAttribute('class', 'play');
        y.setAttribute('class','y');
        x.setAttribute('class', 'x');
        getPlaySet.appendChild(play);

        getPlaySet.appendChild(x);
        getPlaySet.appendChild(y);

        // 添加设备号，播报时间段内容
        var getPlay = getPlaySet.getElementsByClassName('play')[0];
        var deiviceId = document.createElement('p');
        deiviceId.setAttribute('id', 'pDeiviceId');
        getPlay.appendChild(deiviceId);
        var time = document.createElement('p');
        time.setAttribute('id', 'pTime');

        getPlay.appendChild(time);

        // 写入播报设备，播报时间段input
        getPlay.getElementsByTagName('p')[0].innerHTML = '播报设备号：<input type="checkbox" name="deviceId" class="deviceId" value="20620">20620&nbsp;<input type="checkbox" name="deviceId" class="deviceId" value="20570">20570&nbsp;<input name="deviceId" type="checkbox" class="deviceId" value="20619">20619';
        getPlay.getElementsByTagName('p')[1].innerHTML = '播报时间段：<input type="datetime-local" name="startTime" class="startTime time">&nbsp;-&nbsp;<input type="datetime-local" name="overTime" class="overTime time">';

        // 创建关闭按钮
        var getX = getPlaySet.getElementsByClassName('x')[0];
        var img = document.createElement('img');
        img.setAttribute('src', '${ctp}/images/关闭_close-one.png');
        img.setAttribute('onmousedown', "deleteTime(" + "'playSet_" + (parseInt(playLen) + 1) + "');")
        getX.appendChild(img);
        // 创建保存按钮
        var getY = getPlaySet.getElementsByClassName('y')[0];
        var img2 = document.createElement('img');
        img2.setAttribute('src', '${ctp}/images/baucun.png');
        img2.setAttribute('onmousedown', "baocunTime('"+divId+"')")
        getY.appendChild(img2);
    }

    // 删除时间段
    function deleteTime(elm) {
        var it = document.getElementById(elm).attributes['playtimeId'].nodeValue;
        document.getElementById(elm).remove();
        $.get("/GuangBo/deleteBoBaoTime?id="+it,function (d) {
            $.success(d)
        })
    }
    // 保存时间段
    function baocunTime(id) {
        //获取选中的设备号
        var arry = new Array()
        //开始时间
        var arry2 = new Array();
        //结束时间
        var arry3 = new Array();

        var it = document.getElementById(id).attributes['playtimeId'].nodeValue;
        var url="";
        //选中的开始时间
        $('#'+id+'  input[name="startTime"]').each(function(index, element) {
            //追加到数组中
            arry2.push($(this).val());
        });
        //选中的结束时间
        $('#'+id+'  input[name="overTime"]').each(function(index, element) {
            //追加到数组中
            arry3.push($(this).val());
        });

        $('#'+id+' div:first  input[name="deviceId"]:checked').each(function(index, element) {
            //追加到数组中
            arry.push($(this).val());
        });
        //将数组元素连接起来转化为字符串
        var arrystr = arry.join(',');
        //开始时间
        var arrystr2 = arry2.join(',');
        //结束时间
        var arrystr3 = arry3.join(',');
        if(it!=null && it!=""){
            url="/GuangBo/createBoBaoTime?haoma="+arrystr+"&startTime="+arrystr2+"&endTime="+arrystr3+"&id="+it;
        }else{
            url="/GuangBo/createBoBaoTime?haoma="+arrystr+"&startTime="+arrystr2+"&endTime="+arrystr3;
        }
        if(arrystr===null || arrystr===""){
            $.error("请选择设备号");
        }else {
            if(arrystr2!=null && arrystr2!=""){
                if(arrystr3!=null && arrystr3!=""){
                    /* var data={
                         "haoma":arrystr,
                         "startTime": arrystr2,
                         "endTime": arrystr3
                     }*/
                    $.get(url,function (d) {
                        $.success(d)
                    })
                }else{
                    $.error("请选择播报结束时间");
                }
            }else{
                $.error("请选择播报开始时间");
            }
        }

    }

    // 将时间戳转为 年-月-日T时:分:秒
    function a(m) {
        return m < 10 ? '0' + m : m
    }
    function b(m) {
        var date = new Date('2020-04-07 18:08:58');
        // 有三种方式获取
        var hour = m.split(":")[0];
        var min = m.split(":")[1];
        var  s = Number(hour * 3600) + Number(min * 60);
    }
    function switchTime(time) {
        var sTime = new Date(parseInt(time));
        return sTime.getFullYear() + '-' + a(sTime.getMonth() + 1) + '-' + a(sTime.getDate()) + 'T' + a(sTime.getHours()) + ':' + a(sTime.getMinutes()) + ':' + a(sTime.getSeconds());
    }
</script>
</body>
</html>
