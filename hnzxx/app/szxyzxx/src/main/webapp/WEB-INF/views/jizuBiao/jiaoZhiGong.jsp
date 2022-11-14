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
    <title>教职工工作安排表</title>

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
            width: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }

        th,
        td {
            padding: 10px 20px;
            width: 9%;
            height: 40px;
            text-align: center;
            font-size: 20px;
            border: 1px solid #d9d9d9;
        }

        th {
            background-color: #66ccff40;
            color: #002244;
            font-size: 24px;
        }

        td {
            font-weight: bolder;
        }

        .teacherSheet {
            width: 96%;
            margin: 10px 2% 50px 2%;
        }

        .teacherWork {
            width: 800px;
            height: 500px;
            border: 1px solid #002244;
            background-color: white;
            z-index: 999;
            position: fixed;
            top: 0px;
            margin: 200px 30%;
        }

        .workListTitle {
            background-color: #0d7bd5;
            height: 40px;
        }

        .workListTitle div {
            display: inline;
            float: left;
            height: 30px;
            margin: 8px 20px 0px;
            color: white;
            font-weight: bolder;
        }

        .workListTitle .off {
            float:right;
            cursor: pointer;
        }

        .addWork {
            float: right;
            margin: 5px 20px;
            background-color: #fa7000;
            border: 1px solid #fa7000 ;
            border-radius: 5px;
            box-shadow: 0 2px 3px 0 rgba(0, 0, 0, 0.24);
            color: white;
            font-weight: bolder;
        }

        .workList {
            width: 760px;
            margin: 20px;
        }

        .workList th,
        .workList td {
            padding: 0px;
            font-size: 14px;
            height: 35px;
            font-weight: normal;
        }

        .edit {
            width: 50px;
            height: 25px;
            background-color: #0d7bd5;
            border: 1px solid #0d7bd5;
            border-radius: 3px;
            color: white;
            margin: 0 5px;
        }

        .del {
            width: 50px;
            height: 25px;
            background-color: #ff3939;
            border: 1px solid #ff3939;
            border-radius: 3px;
            color: white;
            margin: 0 5px;
        }

        .save {
            background-color: #fa7000;
            border: 1px solid #fa7000;
            border-radius: 3px;
            color:white;
            width: 50px;
            height: 25px;
        }

        .workList .num {
            width: 60px;
        }

        .workList .workContent {
            width: 425px;
        }

        .workList .source {
            width: 125px;
        }

        .workList .handle {
            width: 150px;
        }

        .workContent textarea {
            margin-top: 5px;
            width: 90%;
            height: 25px;
            outline: none;
            resize: none;
            border: none;
            background: transparent;
        }

        .workContent textarea::-webkit-scrollbar {
            width: 0;
            height: 0;
        }
        .refresh {
            float: right;
            margin: 5px;
            background-color: #008000;
            border: 1px solid #008000;
            border-radius: 5px;
            box-shadow: 0 2px 3px 0 #00000024;
            color: white;
            font-weight: bolder;
        }

    </style>

</head>

<body>
<!-- 学年、学期选择框 -->
<div class="find">
    <div class="select">
        <span>学年：</span>
        <select id="year">
            <option value="" selected>请选择</option>
        </select>
    </div>
    <div class="select">
        <span>学期：</span>
        <select id="term">
            <option value="" selected>请选择</option>
        </select>
    </div>
    <div class="select">
        <span>教师名称：</span>
        <input id="teacherId"  type="text">
    </div>
    <button id="find" onclick="createTeacherList();">查询</button>
    <button id="export" onclick="daochu()">导出</button>
</div>

<!-- 标题 -->
<div class="title">
    第<input type="text" id="week">周 教师工作安排
</div>

<!-- 教师表 -->
<div id="teacherSheet" class="teacherSheet">
    <table>
        <tr>
            <th colspan="10">教师</th>
        </tr>
    </table>
</div>

<!-- 后勤表 -->
<div id="logisticSheet" class="teacherSheet">
    <table>
        <tr>
            <th colspan="10">后勤</th>
        </tr>
    </table>
</div>

<script type="text/javascript">
    $(function () {
        initSelect()
    });

    function initSelect() {
        //初始填充学年、学期
        addOption('/teach/schoolYear/list/json', "year", "year", "name", function (d) {
            if (d.length > 0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "term", "code", "name");
            }
        })
    }
    //绑定下拉框改变事件
    $("#year").change(function(){
        $("#term").html('<option value="">请选择</option>');
        //添加学期
        addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "term", "code", "name")

    })
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
    //不提供调用，请勿调用（绿色title）
    initWindowBase = function (onWhere, title, url, width, height, topVal, shift) {
        if ("top" === onWhere) {
            return window.top.layer.open({
                id:'wer',
                skin: 'layui-layer-lvse', //样式类名
                type: 2,
                title: title,
                //closeBtn: false, //显示关闭按钮
                shadeClose: true,
                shade: 0.8,
                area: [width + 'px', height + 'px'],
                /*  offset:[ topVal + 'px', '' ], */
                maxmin: false, //开启最大化最小化按钮
                shift: shift,
                content: url //iframe的url，no代表不显示滚动条
                // time: 2000, //2秒后自动关闭
            });
        } else if ('cur' === onWhere) {
            return layer.open({
                /* extend: ['skin/myskin/style.css'], //加载您的扩展样式
                skin: 'layer-ext-yourskin', //一旦设定，所有弹层风格都采用此主题。 */
                skin: 'layui-layer-lvse', //样式类名
                type: 2,
                title: title,
                //closeBtn: false, //显示关闭按钮
                shadeClose: true,
                shade: 0.8,
                area: [width + 'px', height + 'px'],
                /*  offset:[ top + 'px', '' ], */
                maxmin: false, //开启最大化最小化按钮
                shift: shift,
                content: url //iframe的url，no代表不显示滚动条
            });
        }
    }

    //在最顶层窗口的左侧显示窗口 title:窗口标题；url：加载的页面url；width：窗口宽度；height：窗口高度；top：距离顶部的距离
    initWinOnTopFromLeft = function (title, url, width, height, top) {
        if (width === undefined) {
            width = $(parent.window).width() - 50;
        }
        if (height == undefined) {
            height = $(parent.window).height() - 50;
        }
        if (top == undefined) {
            /* top = '20'; */
        }
        return initWindowBase('top', title, url, width, height, top, 'left');
    }

    // 创建列表
    function createSheet(sheet,content,branch,num) {
        // sheet: 表元素
        // content: 教职工列表
        // branch: 部门名称

        var lc = 0;
        for (var l = 0; l < content.length; l++) {
            // 创建行
            if (l % 10 == 0) {
                var lCon = document.createElement('tr');
                sheet.appendChild(lCon);
                lc++;
            }

            var branchName = sheet.getElementsByTagName('tr')[0].getElementsByTagName('th')[0].textContent;

            var lTr = sheet.getElementsByTagName('tr')[lc];
            var lName = document.createElement('td');
            lName.setAttribute('class', branch);
            lName.setAttribute('id', branch + '_' + content[l].teacherId);
            lName.setAttribute('onclick', 'openTeacherWork("'+branchName+'","'+content[l].teacherId+'","'+num+'","'+content[l].zongshu+'");')
            // 根据工作量设置背景颜色
            if (content[l].zongshu == 1) {
                lName.setAttribute('style', 'background-color:#00ffdd50;');
            } else if (content[l].zongshu == 2) {
                lName.setAttribute('style', 'background-color:#33ff0050;');
            } else if (content[l].zongshu == 3) {
                lName.setAttribute('style', 'background-color:#ff660050;');
            } else if (content[l].zongshu == 4) {
                lName.setAttribute('style', 'background-color:#ff002250;');
            } else if (content[l].zongshu >= 5) {
                lName.setAttribute('style', 'background-color:#44007750;');
            }
            lTr.appendChild(lName);

            // 写入教师名称
            document.getElementById(branch + '_' + content[l].teacherId).innerHTML = content[l].teacherName;
        }
    }

    // 创建教职工列表
    function createTeacherList() {
        var schoolYear=$("#year").val();
        var schoolTrem=$("#term").val();
        var zhoushu=$("#week").val();
        var name=$("#teacherId").val();
        if(schoolYear!=null && schoolYear!=""){
            if(schoolTrem!=null && schoolTrem !=""){
                if(zhoushu!=null && zhoushu!=""){
                    $.get('/jiaozhigong/taecherAll?schoolYaer='+schoolYear+'&schoolTrem='+schoolTrem+'&zhoushu='+zhoushu+'&name='+name,function (d) {
                        var dd=JSON.parse(d);
                        var teacher = dd['keren'];
                        var logistic = dd['houqin'];

                        var tSheet = document.getElementById('teacherSheet').getElementsByTagName('table')[0];
                        var lSheet = document.getElementById('logisticSheet').getElementsByTagName('table')[0];
                        document.getElementById('teacherSheet').getElementsByTagName('table')[0].innerHTML="<th colspan='10'>教师</th>";
                        document.getElementById('logisticSheet').getElementsByTagName('table')[0].innerHTML="<th colspan='10'>后勤</th>";
                        // 创建教师表
                        createSheet(tSheet, teacher, 'teacher',1);
                        // 创建后勤表
                        createSheet(lSheet, logistic, 'logistic',2);
                    })
                }else{
                    $.error("请输入周数")
                }
            }else{
                $.error("请选择学期")
            }
        }else{
            $.error("请选择学年")
        }
    }

    var asd;
    var adss;
    var bbs;
    // 打开教师工作内容
    function openTeacherWork(branchName,tid,id,nums) {
        // branchName: 部门名称
        $.get('/jizu/findByAllTeacherId?teacherId='+tid,function (d) {
            var work=JSON.parse(d);
            var body = document.getElementsByTagName('body')[0];

            var tWork = document.createElement('div');
            tWork.setAttribute('class', 'teacherWork');
            body.appendChild(tWork);

            var teacherWork = document.getElementsByClassName('teacherWork')[0];

            var wlt = document.createElement('div');
            wlt.setAttribute('class', 'workListTitle');
            teacherWork.appendChild(wlt);

            document.getElementsByClassName('workListTitle')[0].innerHTML = '<div>教师工作安排</div><div class="off" onclick="offTeacherWork();">X</div>';

            var aw = document.createElement('input');
            aw.setAttribute('type', 'button');
            aw.setAttribute('class', 'addWork');
            aw.setAttribute('value', '添加工作内容');
            aw.setAttribute('onclick', 'addTeacherWork("'+branchName+'","'+tid+'","'+id+'","'+nums+'");') // 添加工作方法
            teacherWork.appendChild(aw);
            var refresh = document.createElement('input');
            refresh.setAttribute('type','button');
            refresh.setAttribute('class','refresh');
            refresh.setAttribute('value','刷新列表');
            refresh.setAttribute('onclick','shuaxin("'+branchName+'","'+tid+'","'+id+'");'); // 刷新列表方法
            teacherWork.appendChild(refresh);
            asd=branchName;
            adss=tid;
            bbs=id;
            var wl = document.createElement('table');
            wl.setAttribute('class', 'workList');
            teacherWork.appendChild(wl);

            var workList = document.getElementsByClassName('workList')[0];
            var tr = document.createElement('tr');
            workList.appendChild(tr);
            workList.getElementsByTagName('tr')[0].innerHTML = '<th class="num">序号</th><th class="workContent">工作内容</th><th class="source">部门级组</th><th class="handle">操作</th>';


            for (var n = 0; n<work.length;n++ ) {

                var wlTr = document.createElement('tr');
                workList.appendChild(wlTr);

                var num = n + 1;
                var wtr = workList.getElementsByTagName('tr')[num];

                var tn = document.createElement('td');
                tn.setAttribute('class', 'num');
                wtr.appendChild(tn);
                var tw = document.createElement('td');
                tw.setAttribute('class', 'workContent');
                wtr.appendChild(tw);
                var tb = document.createElement('td');
                tb.setAttribute('class', 'source');
                wtr.appendChild(tb);
                var th = document.createElement('td');
                th.setAttribute('class', 'handle');
                wtr.appendChild(th);

                wtr.getElementsByClassName('num')[0].innerHTML = num;
                wtr.getElementsByClassName('workContent')[0].innerHTML = work[n]['wokeContent'];
                wtr.getElementsByClassName('source')[0].innerHTML = work[n]['numenjizuName'];

                var edit = document.createElement('input');
                edit.setAttribute('type', 'button');
                edit.setAttribute('class', 'edit');
                edit.setAttribute('value', '编辑');
                edit.setAttribute('onclick', 'bianjicontont("'+work[n].id+'","'+work[n]['wokeContent']+'")'); // 编辑方法
                var del = document.createElement('input');
                del.setAttribute('type', 'button');
                del.setAttribute('class', 'del');
                del.setAttribute('value', '删除');
                del.setAttribute('onclick', 'deleteContont("'+work[n].id+'")'); // 删除方法

                workList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(edit);
                workList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(del);
            }
        })


    }
    //刷新
   function shuaxin(nanme,tid,id){
       offTeacherWork();
       openTeacherWork(nanme,tid,id);
   }

    function addTeacherWork(branchName,tid,id,num) {
        if(parseInt(num)>4){
            $.confirm("当前人员工作任务繁多，是否继续添加工作任务?", function () {
                addTeacherWork2(branchName,tid,id);
            });
        }else{
            addTeacherWork2(branchName,tid,id);
        }

    }
    // 添加工作内容
    function addTeacherWork2(branchName,tid,id) {

        if (document.getElementsByClassName('workList')[0].getElementsByTagName('textarea')[0] == undefined) {
            var workList = document.getElementsByClassName('workList')[0];

            var trNum = workList.getElementsByTagName('tr').length;

            var atr = document.createElement('tr');
            workList.appendChild(atr);

            var aTr = workList.getElementsByTagName('tr')[trNum];

            var atn = document.createElement('td');
            atn.setAttribute('class', 'num');
            aTr.appendChild(atn);
            var atw = document.createElement('td');
            atw.setAttribute('class', 'workContent');
            aTr.appendChild(atw);
            var atb = document.createElement('td');
            atb.setAttribute('class', 'source');
            aTr.appendChild(atb);
            var ath = document.createElement('td');
            ath.setAttribute('class', 'handle');
            aTr.appendChild(ath);

            aTr.getElementsByClassName('num')[0].innerHTML = trNum;
            aTr.getElementsByClassName('workContent')[0].innerHTML = '<textarea id="contont"></textarea>';
            aTr.getElementsByClassName('source')[0].innerHTML = branchName;
            aTr.getElementsByClassName('handle')[0].innerHTML = '<input type="button" onclick="queren()" class="save" value="确定">';
        }

    }

    // 关闭教师工作安排窗口
    function offTeacherWork() {
        document.getElementsByClassName('teacherWork')[0].remove();
        createTeacherList();
    }
    //确认添加
    function queren() {

        var contentt=$("#contont").val();
        var schoolYear=$("#year").val();
        var schoolTrem=$("#term").val();
        var zhoushu=$("#week").val();
        var val={};
        val.numenjizuName=asd;
        val.wokeContent=contentt;
        val.schoolYear=schoolYear;
        val.schoolTrem=schoolTrem;
        val.zhoushu=zhoushu;
        val.jizuId=bbs;
        val.teacherId=adss;
        $.post('/jiaozhigong/createAll',val,function (d) {
            if(d=="success"){
                $.success("添加成功");
                offTeacherWork();
                openTeacherWork(asd,adss,bbs);
            }else{
                $.error("添加失败,请联系管理员");
            }
        })
    }

    //删除
    function deleteContont(id) {
        $.confirm("确定执行删除操作？", function () {
            deleteContonts(id);
        });
    }
    //删除工作内容
    function deleteContonts(id) {
        // tid: 教师id
        $.get('/jizu/deleteTeacherWoke?id='+id,function (d) {
            if(d==="success"){
                $.success("执行成功");
                openTeacherWork(asd,adss,bbs);
                offTeacherWork();

            }
        })
    }
    //修改
    function bianjicontont(idss,namsed){
        initWinOnTopFromLeft('修改工作内容', '/jizu/contontBianji?id='+idss+"&name="+namsed, '1000', '650');
    }
    function daochu() {
        var schoolYear=$("#year").val();
        var schoolTrem=$("#term").val();
        var zhoushu=$("#week").val();
        var name=$("#teacherId").val();
        if(schoolYear!=null && schoolYear!=""){
            if(schoolTrem!=null && schoolTrem !=""){
                if(zhoushu!=null && zhoushu!=""){
                    var url="/jiaozhigong/daochuWoke?schoolYear="+schoolYear+"&schoolTerm="+schoolTrem+"&zhoushu="+zhoushu+"&name="+name;
                    window.open(url);
                }else{
                    $.error("请输入周数")
                }
            }else{
                $.error("请选择学期")
            }
        }else{
            $.error("请选择学年")
        }
    }
</script>
</body>

</html>
