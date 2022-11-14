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
    <title>级组工作安排表</title>

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
        #group,
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
        #group:hover {
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
            position: relative;
        }

        td {
            position: relative;
            font-weight: bolder;
        }

        .dt {
            width: 15px;
            height: 15px;
            position: absolute;
            bottom: 3px;
            right: 3px;
            background-image: url('../../../images/删除.png');
            background-size: 15px;
            display: none;
            cursor: pointer;
            z-index: 99;
        }

        .sp {
            width: 15px;
            height: 15px;
            position: absolute;
            bottom: 3px;
            left: 3px;
            background-image: url('../../../images/管理人员.png');
            background-size: 15px;
            display: none;
            cursor: pointer;
            z-index: 99;
        }

        td:hover .dt,
        td:hover .sp {
            display: block;
        }

        .addTeacher {
            background-image: url('../../../images/添加人群.png');
            width: 40px;
            height: 40px;
            background-size: 40px;
            position: absolute;
            right: 20px;
            top: 10px;
            display: none;
        }

        th:hover .addTeacher {
            display: block;
        }

        .teacherSheet {
            width: 96%;
            margin: 10px 2% 50px 2%;
        }

        .teacherWork,
        .groupList {
            width: 800px;
            height: 500px;
            border: 1px solid #002244;
            background-color: white;
            z-index: 999;
            position: fixed;
            top: 0px;
            margin: 200px 30%;
        }

        .workListTitle,
        .groupListTitle {
            background-color: #0d7bd5;
            height: 40px;
        }

        .workListTitle div,
        .groupListTitle div {
            display: inline;
            float: left;
            height: 30px;
            margin: 8px 20px 0px;
            color: white;
            font-weight: bolder;
        }

        .workListTitle .off,
        .groupListTitle .off {
            float: right;
            cursor: pointer;
        }

        .addWork,
        .addGroup {
            float: right;
            margin: 5px 20px;
            background-color: #fa7000;
            border: 1px solid #fa7000;
            border-radius: 5px;
            box-shadow: 0 2px 3px 0 rgba(0, 0, 0, 0.24);
            color: white;
            font-weight: bolder;
        }

        .workList,
        .gList {
            width: 760px;
            margin: 20px;
        }

        .workList th,
        .workList td,
        .gList th,
        .gList td {
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
            color: white;
            width: 50px;
            height: 25px;
        }

        .workList .num,
        .gList .num {
            width: 60px;
        }

        .workList .workContent {
            width: 425px;
        }

        .workList .source,
        .gList .source {
            width: 125px;
        }

        .workList .handle,
        .gList .handle {
            width: 150px;
        }

        .workContent textarea,
        .gList textarea {
            margin-top: 5px;
            width: 90%;
            height: 25px;
            outline: none;
            resize: none;
            border: none;
            background: transparent;
        }

        .workContent textarea::-webkit-scrollbar,
        .gList textarea::-webkit-scrollbar {
            width: 0;
            height: 0;
        }

        .delTeacher,
        .setPrincipal {
            padding: 10px 10px;
            width: 350px;
            height: 150px;
            background-color: white;
            border: 1px solid #d9d9d9;
            box-shadow: 0 2px 3px 0 rgba(0, 0, 0, 0.24);
            text-align: center;
            z-index: 999;
            position: fixed;
            top: 0px;
            margin: 400px 40%;
        }

        .delTeacher p,
        .setPrincipal p {
            margin: 35px 40px;
        }

        .delTeacher .dt_no,
        .setPrincipal .sp_yes {
            background-color: #0d7bd5;
            border: 1px solid #0d7bd5;
            border-radius: 3px;
            color: white;
            margin: 20px;
        }

        .delTeacher .dt_yes {
            background-color: #d9d9d9;
            border: 1px solid #d9d9d9;
            border-radius: 3px;
            color: #444444;
            margin: 20px;
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
            <option value="" >请选择</option>
        </select>
    </div>
    <div class="select">
        <span>学期：</span>
        <select id="term">
            <option value="" >请选择</option>
        </select>
    </div>
    <button id="find" onclick="createTeacherList();">查询</button>
    <button id="group" onclick="openGroupSet();">级组设置</button>
    <button id="export" onclick="daochu()">导出</button>
</div>

<!-- 标题 -->
<div class="title">
    第<input  type="text" id="week">周 级组工作安排
</div>

<script type="text/javascript">
    var asd;
    var adss;
    var bbs;
    // 创建列表
    function createSheet(sheet, content, branch) {
        // sheet: 表元素
        // content: 教职工列表
        // branch: 部门名称  不用

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
            lName.setAttribute('onclick', 'openTeacherWork("' + branchName + '","'+content[l].teacherId+'","'+branch+'","'+content[l].zongshu+'");')
            // 根据工作量设置背景颜色
            if (content[l].zongshu == 1) {
                lName.setAttribute('style', 'background-color:#00ffdd50;');
            } else if (content[l].zongshu == 2) {
                lName.setAttribute('style', 'background-color:#33ff0050;');
            } else if (content[l].zongshu == 3) {
                ;
                lName.setAttribute('style', 'background-color:#ff660050;');
            } else if (content[l].zongshu == 4) {
                lName.setAttribute('style', 'background-color:#ff002250;');
            } else if (content[l].zongshu >= 5) {
                lName.setAttribute('style', 'background-color:#44007750;');
            }
            lTr.appendChild(lName);

            // 写入教师名称
            document.getElementById(branch + '_' + content[l].teacherId).innerHTML = content[l].teacherName + "<div class='dt' title='删除教师' onclick='openDelTeacher(" + content[l].teacherId + ","+content[l].id+","+branch+")'></div><div class='sp' title='设为级组负责人' onclick='setPrincipal("+content[l].teacherId+","+branch+")'></div>";

        }
    }

    // 创建教职工列表
    function createTeacherList() {
        //var goroupList = getGroupList().groupList;
        var schoolYear=$("#year").val();
        var schoolTrem=$("#term").val();
        var zhoushu=$("#week").val();
        if(schoolYear!=null && schoolYear!=""){
            if(schoolTrem!=null && schoolTrem !=""){
                if(zhoushu!=null && zhoushu!=""){
                    $.get('/jizu/AllJiZu?schoolYear='+schoolYear+"&schoolTrem="+schoolTrem+"&zhoushu="+zhoushu,function (d) {
                        var goroupList=JSON.parse(d);
                        // 创建级组
                        for (var g in goroupList) {
                            if (document.getElementById(goroupList[g].groupId)) {
                                document.getElementById(goroupList[g].groupId).remove();
                            }
                            var body = document.getElementsByTagName('body')[0];
                            var sheet = document.createElement('div');
                            sheet.setAttribute('class', 'teacherSheet');
                            sheet.setAttribute('id', goroupList[g].groupId);
                            body.appendChild(sheet);

                            var teacherSheet = document.getElementById(goroupList[g].groupId);

                            var table = document.createElement('table');
                            teacherSheet.appendChild(table);

                            var titleTr = document.createElement('tr');
                            teacherSheet.getElementsByTagName('table')[0].appendChild(titleTr);

                            var titleTh = document.createElement('th');
                            titleTh.setAttribute('colspan', 10);
                            teacherSheet.getElementsByTagName('table')[0].getElementsByTagName('tr')[0].appendChild(titleTh);

                            teacherSheet.getElementsByTagName('table')[0].getElementsByTagName('tr')[0].getElementsByTagName('th')[0].innerHTML = goroupList[g].groupName + '<div class="addTeacher" onclick="addTeacher('+goroupList[g].groupId+');"></div>';

                            createSheet(teacherSheet.getElementsByTagName('table')[0], goroupList[g]['teacherList'], goroupList[g]['groupId']);
                        }
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

    function addTeacher(id) {
        initWinOnTopFromLeft('添加教师', '/jizu/teacherAll?sub=asd&jiZuId='+id, '1200', '650');
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

    // 打开教师工作内容
    function openTeacherWork(branchName,tid,jid,nums) {
        // branchName: 部门名称

        $.get('/jizu/findByAllTeacherId?teacherId='+tid,function (d) {
            var work=JSON.parse(d);
            if (document.getElementsByClassName('teacherWork')[0]) {
                document.getElementsByClassName('teacherWork')[0].remove();
            }

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
            aw.setAttribute('onclick', 'addTeacherWork("' + branchName + '","'+tid+'","'+jid+'","'+nums+'");') // 添加工作方法
            teacherWork.appendChild(aw);
            var refresh = document.createElement('input');
            refresh.setAttribute('type','button');
            refresh.setAttribute('class','refresh');
            refresh.setAttribute('value','刷新列表');
            refresh.setAttribute('onclick','shuaxing("'+branchName+'","'+tid+'","'+jid+'","'+nums+'")'); // 刷新列表方法
            teacherWork.appendChild(refresh);
             asd=branchName;
             adss=tid;
             bbs=jid;


            var wl = document.createElement('table');
            wl.setAttribute('class', 'workList');
            teacherWork.appendChild(wl);

            var workList = document.getElementsByClassName('workList')[0];
            var tr = document.createElement('tr');
            workList.appendChild(tr);
            workList.getElementsByTagName('tr')[0].innerHTML = '<th class="num">序号</th><th class="workContent">工作内容</th><th class="source">部门级组</th><th class="handle">操作</th>';
            for (var n = 0; n < work.length; n++) {

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
                edit.setAttribute('onclick', 'bianjicontont("'+work[n].id+'","'+work[n].wokeContent+'")'); // 编辑方法
                var del = document.createElement('input');
                del.setAttribute('type', 'button');
                del.setAttribute('class', 'del');
                del.setAttribute('value', '删除');
                del.setAttribute('onclick', 'deleteContont("'+work[n].id+'")'); // 删除方法

                workList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(edit);
                workList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(del);
            }
            stopPropagation();
        })
    }

    //刷新
    function shuaxing(name,tid,id) {
        offTeacherWork();
        openTeacherWork(name,tid,id);
    }
    //修改
   function bianjicontont(idss,namsed){
        initWinOnTopFromLeft('修改工作内容', '/jizu/contontBianji?id='+idss+"&name="+namsed, '1000', '650');
    }

    function daochu() {
        var schoolYear=$("#year").val();
        var schoolTrem=$("#term").val();
        var zhoushu=$("#week").val();
        if(schoolYear!=null && schoolYear!=""){
            if(schoolTrem!=null && schoolTrem !=""){
                if(zhoushu!=null && zhoushu!=""){
                    var url="/jizu/daochuWoke?schoolYear="+schoolYear+"&schoolTerm="+schoolTrem+"&zhoushu="+zhoushu;
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
    function addTeacherWork(branchName,tid,jid,num){
        if(parseInt(num)>4){
            $.confirm("当前人员工作任务繁多，是否继续添加工作任务", function () {
                addTeacherWork2(branchName,tid,jid);
            });
        }else{
            addTeacherWork2(branchName,tid,jid);
        }
    }
    // 添加工作内容
    function addTeacherWork2(branchName,tid,jid) {

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
            aTr.getElementsByClassName('workContent')[0].innerHTML = '<textarea id="textCont"></textarea>';
            aTr.getElementsByClassName('source')[0].innerHTML = branchName;
             asd=branchName;
             adss=tid;
             bbs=jid;
            aTr.getElementsByClassName('handle')[0].innerHTML = "<input type='button' class='save' value='确定' onclick='qurdinga();'>";
        }

    }


    function qurdinga() {

        var contentt=$("#textCont").val();
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
        $.post('/jizu/addTeacherWoke',val,function (d) {
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
    // 关闭教师工作安排窗口
    function offTeacherWork() {
        createTeacherList();
        document.getElementsByClassName('teacherWork')[0].remove();
    }


    //-----------------级组streat------------------------------
    // 打开级组设置
    function openGroupSet() {

        if (document.getElementsByClassName('groupList')[0]) {
            document.getElementsByClassName('groupList')[0].remove();
        }

        var body = document.getElementsByTagName('body')[0];

        var gList = document.createElement('div');
        gList.setAttribute('class', 'groupList');
        body.appendChild(gList);

        var groupList = document.getElementsByClassName('groupList')[0];

        var glt = document.createElement('div');
        glt.setAttribute('class', 'groupListTitle');
        groupList.appendChild(glt);

        document.getElementsByClassName('groupListTitle')[0].innerHTML = '<div>级组设置</div><div class="off" onclick="offGroupSet();">X</div>'

        var ag = document.createElement('input');
        ag.setAttribute('type', 'button');
        ag.setAttribute('class', 'addGroup');
        ag.setAttribute('value', '添加级组');
        ag.setAttribute('onclick', 'addGroup();'); // 添加级组方法
        groupList.appendChild(ag);
        var refresh = document.createElement('input');
        refresh.setAttribute('type','button');
        refresh.setAttribute('class','refresh');
        refresh.setAttribute('value','刷新列表');
        refresh.setAttribute('onclick','shuaxinqwe();'); // 刷新列表方法
        groupList.appendChild(refresh);

        var gl = document.createElement('table');
        gl.setAttribute('class', 'gList');
        groupList.appendChild(gl);

        var grList = document.getElementsByClassName('gList')[0];
        var tr = document.createElement('tr');
        grList.appendChild(tr);
        grList.getElementsByTagName('tr')[0].innerHTML = '<th class="num">序号</th><th class="source">部门级组</th><th class="handle">操作</th>';

       // console.log(getGroupList())
        $.get("/jizu/getAllJiZu",function (d) {
            var list=JSON.parse(d);
            for (var n = 0; n < list.length; n++) {

                var glTr = document.createElement('tr');
                grList.appendChild(glTr);

                var num = n + 1;
                var gtr = grList.getElementsByTagName('tr')[num];

                var tn = document.createElement('td');
                tn.setAttribute('class', 'num');
                gtr.appendChild(tn);
                var tb = document.createElement('td');
                tb.setAttribute('class', 'source');
                tb.setAttribute('id','td_'+n);
                gtr.appendChild(tb);
                var th = document.createElement('td');
                th.setAttribute('class', 'handle');
                gtr.appendChild(th);

                gtr.getElementsByClassName('num')[0].innerHTML = num;
                //gtr.getElementsByClassName('source')[0].innerHTML = list[n].jizuName;
                var tds = $('#td_'+n);
                var str="<textarea  disabled>"+list[n].jizuName+"</textarea>";

                tds.append(str);
               /* var textarea=document.createElement('textarea');
                textarea.setAttribute('id','text_'+n);
                textarea.setAttribute('disabled','disabled');*/


                var edit = document.createElement('input');
                edit.setAttribute('type', 'button');
                edit.setAttribute('class', 'edit');
                edit.setAttribute('value', '编辑');
                edit.setAttribute('onclick', "jiZuPdate("+list[n].id+",'"+list[n].jizuName+"')"); // 编辑方法
                var del = document.createElement('input');
                del.setAttribute('type', 'button');
                del.setAttribute('class', 'del');
                del.setAttribute('value', '删除');
                del.setAttribute('onclick', 'deletes('+list[n].id+')');//删除方法

                grList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(edit);
                grList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(del);
            }
        })
    }
    //级组设置刷新
   function shuaxinqwe(){
       offGroupSet2();
       openGroupSet();
   }

    function offGroupSet2() {
        document.getElementsByClassName('groupList')[0].remove();
    }
    // 关闭级组设置弹窗
    function offGroupSet() {
        document.getElementsByClassName('groupList')[0].remove();
        createTeacherList();
    }

    // 添加级组
    function addGroup() {

       /* if (document.getElementsByClassName('gList')[0].getElementsByTagName('textarea')[0] == undefined) {*/
            var grList = document.getElementsByClassName('gList')[0];

            var trNum = grList.getElementsByTagName('tr').length;

            var atr = document.createElement('tr');
            grList.appendChild(atr);

            var aTr = grList.getElementsByTagName('tr')[trNum];


            var atn = document.createElement('td');
            atn.setAttribute('class', 'num');
            aTr.appendChild(atn);
            var atb = document.createElement('td');
            atb.setAttribute('class', 'source');
            aTr.appendChild(atb);
            var ath = document.createElement('td');
            ath.setAttribute('class', 'handle');
            aTr.appendChild(ath);

            aTr.getElementsByClassName('num')[0].innerHTML = trNum;
            aTr.getElementsByClassName('source')[0].innerHTML = '<textarea id="tontent"></textarea>';
            aTr.getElementsByClassName('handle')[0].innerHTML = '<input type="button" class="save" onclick="jizuAdd()" value="确定">';
       /* }else{
            alert("失败");
        }*/

    }
    /*
   * 级组添加
   * */
    function jizuAdd() {
        var tontent=$("#tontent").val();
        var val={};
        val.jizuName=tontent;
        $.post('/jizu/createJiZu',val,function (d) {
            if(d=="success"){
                $.success("添加成功");
                openGroupSet();
            }else{
                $.error("添加失败,请联系管理员");
            }
        })
    }
    /*
    * 级组修改
    * */
    function jiZuPdate(id,name) {
        initWinOnTopFromLeft('修改级组名称', '/jizu/jizuBian?id='+id+"&name="+name, '1000', '650');
    }

    //删除级组
    function deletes(id) {
        $.confirm("确定执行删除操作？", function () {
            queding(id);
        });
    }
   function queding(id) {
       $.get('/jizu/deleteJiZu?id='+id,function (d) {
            if(d=="success"){
                $.success("删除成功")
                shuaxinqwe();
            }else{
                $.error("删除失败")

            }       })
   }
   //-----------------级组end-----------教师streat------------------
    // 打开删除教师弹窗
    function openDelTeacher(tid,id,jid) {

        if (document.getElementsByClassName('delTeacher')[0]) {
            document.getElementsByClassName('delTeacher')[0].remove();
        }

        var body = document.getElementsByTagName('body')[0];

        var dt = document.createElement('div');
        dt.setAttribute('class', 'delTeacher');
        body.appendChild(dt);

        var dtDiv = document.getElementsByClassName('delTeacher')[0];
        var p = document.createElement('p');
        p.innerHTML = '是否确定要将该教师移出该级组？';
        var no = document.createElement('input');
        no.setAttribute('class', 'dt_no');
        no.setAttribute('type', 'button');
        no.setAttribute('value', '否');
        no.setAttribute('onclick', 'offDelTeacher();')
        var yes = document.createElement('input');
        yes.setAttribute('class', 'dt_yes');
        yes.setAttribute('type', 'button');
        yes.setAttribute('value', '是');
        yes.setAttribute('onclick', "delTeacher(" + tid + ","+id+","+jid+");") // 删除教师

        dtDiv.appendChild(p);
        dtDiv.appendChild(no);
        dtDiv.appendChild(yes);

        stopPropagation();
    }

    // 关闭删除教师弹窗
    function offDelTeacher() {
        document.getElementsByClassName('delTeacher')[0].remove();
        createTeacherList();
    }

    // 删除教师
    function delTeacher(tid,id,jid) {
        // tid: 教师id
        $.get('/jizu/updateTeacherJiZu?id='+id+"&teacherId="+tid+"&jizuId="+jid,function (d) {
            if(d==="success"){
                $.success("执行成功");
                offDelTeacher();
            }
        })
    }
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
    // 设为级组负责人
    function setPrincipal(tid,jid) {
        if (document.getElementsByClassName('setPrincipal')[0]) {
            document.getElementsByClassName('setPrincipal')[0].remove();
        }

        var body = document.getElementsByTagName('body')[0];

        var dt = document.createElement('div');
        dt.setAttribute('class', 'setPrincipal');
        body.appendChild(dt);

        var dtDiv = document.getElementsByClassName('setPrincipal')[0];
        var p = document.createElement('p');
        p.innerHTML = '成功将该教师设为级组负责人';
        var yes = document.createElement('input');
        yes.setAttribute('class', 'sp_yes');
        yes.setAttribute('type', 'button');
        yes.setAttribute('value', '确定');
        yes.setAttribute('onclick', 'principal('+tid+','+jid+');')

        dtDiv.appendChild(p);
        dtDiv.appendChild(yes);

        stopPropagation();
    }
        //设为组长
    function principal(tid,jid) {
        $.get('/jizu/updateTeacherJiZuZhang?id='+jid+"&teacherId="+tid,function (d) {
            if(d==="success"){
                $.success("执行成功");
                offSetPrincipal();
            }
        })
    }
    function offSetPrincipal() {
        document.getElementsByClassName('setPrincipal')[0].remove();
        createTeacherList();
    }

    // 阻止点击冒泡
    function stopPropagation(e) {
        e = e || window.event;
        if (e.stopPropagation) {
            e.stopPropagation();
        } else {
            e.cancelBubble = true;
        }
    }

</script>
</body>

</html>