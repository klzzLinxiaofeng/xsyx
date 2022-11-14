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
<link href="${ctp}/res/css/extra/add.css" rel="stylesheet"/>
    <title>教职工事项交办</title>
    <style>
        .teacherWork { overflow: auto;}
        .teacherWork::-webkit-scrollbar { width: 0; height: 0;}
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
        #setGroup,
        #fagonggao,
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
        #fagonggao:hover,
        #setGroup:hover {
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }

        select,
        input {
            height: 30px;
            width: 120px;
            border: 1px solid #d9d9d9;
        }
        img:not([src]) {
            opacity: 0;

        }
        .sample {
            width: 600px;
            height: 30px;
            /*background: url('../../../images/图示.png') no-repeat;*/
            background-size: 600px 30px;
           /* border: none;*/
            margin-left: 2%;
        }

        .title {
            text-align: center;
            font-size: 20px;
        }


        #week{
            margin:5px;
            text-align:center;
            width:40px;
            height:40px;
            font-size:20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }

        th,
        td {
            position: relative;
            padding: 10px 20px;
            width: 9%;
            height: 40px;
            text-align: center;
            font-size: 20px;
            border: 1px solid #d9d9d9;
        }

        th {
            background-color: #8fc8fd;
            color: #002244;
            font-size: 24px;
        }

        .subGroup th {
            background-color: #d9f2ff;
        }

        td {
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

        .group,
        .teacherList,
        .staffList {
            width: 96%;
            margin: 10px 2% 50px 2%;
            border-left: 1px solid #d9d9d9;
        }

        .subGroup {
            width: 98%;
            margin: 30px 2%;
            display: none;
        }

        .tList {
            display: none;
        }

        .principal {
            background: url('../../../images/负责人.png') no-repeat;
        }

        .an {
            position: absolute;
            width: 40px;
            height: 40px;
            background: url('../../../images/右.png') no-repeat;
            background-size: 40px;
            top: 10px;
            transition: transform .2s;
        }

        .groupWork,
        .setSubGroup,
        .addTeacher {
            position: absolute;
            margin-left: 10px;
            margin-right: 5px;
            font-size: 14px;
            padding: 5px 10px;
            border: none;
            border-radius: 3px;
            color: white;
            top: 17px;
            display: none;
        }

        th:hover .groupWork,
        th:hover .setSubGroup,
        th:hover .addTeacher {
            display: block;
        }

        .groupWork {
            background-color: #0d7bd5;
            right: 110px;
        }

        .setSubGroup {
            background-color: #96b97d;
            right: 230px;
        }

        .addTeacher {
            background-color: #ff6c37;
            right: 20px;
        }

        .groupWork:hover,
        .setSubGroup:hover,
        .addTeacher:hover {
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }
    </style>

    <style>
        .teacherWork,
        .grList {
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
        .groupListTitle,
        .groupAllWork{
            background-color: #0d7bd5;
            height: 40px;
        }

        .workListTitle div,
        .groupListTitle div{
            display: inline;
            float: left;
            height: 30px;
            margin: 8px 20px 0px;
            color: white;
            font-weight: bolder;
        }
        .groupAllWork div{
            display: inline;
            float: left;
            height: 30px;
            margin: 8px 20px 0px;
            color: white;
            font-weight: bolder;
        }

        .workListTitle .off,
        .groupListTitle .off{
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
        .setPrincipal,
        .groupAllWork {
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

        .groupAllWork {
            padding: 0px;
        }

        .addAllWorkTitle{
            color: white;
            text-align: left;
            font-weight: bold;
            padding: 10px;
            height: 20px;
            background-color: #0d7bd5;
        }

        .delTeacher p,
        .setPrincipal p {
            margin: 35px 40px;
        }

        .groupAllWork .work_text {
            margin-top: 10px;
            height:30px;
            width: 300px;
            resize: none;
            border: 1px solid #d9d9d9;
        }

        .delTeacher .dt_no,
        .setPrincipal .sp_yes,
        .groupAllWork .work_yes {
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
    <button id="find" onmousedown="createList(event);">查询</button>
    <c:if test="${isGuanli=='true'}">
        <button id="setGroup" onclick="openGroupSet();">级组设置</button>
    </c:if>
    <c:if test="${isGuanli!='true'}">
        <button id="setGroup" disabled="disabled" onclick="openGroupSet();">级组设置</button>
    </c:if>


    <button id="export" onclick="daochu();">导出</button>
</div>

<!-- 图示 -->
<div ><img  class="sample" src="../../../images/图示.png"/>
    <button id="fagonggao" onclick="fagonggao();">发公告</button>
</div>

<!-- 周数查询框 -->
<div class="title">
    第<input type="text" id="week" maxlength="2" onblur="createList(event);">周 教职工事项交办
</div>

<!-- 级组 -->
<div class="groupList" id="groupList">
</div>

<script>
    function fagonggao() {
        window.open("/office/notice/newIndex?receiverType=pj.school,pj.allTeacher,pj.allStudent");
    }
    var asd;
    var adss;
    var bbs;
    var nummm;
    var asdaa=${isGuanli};
    // 展开与折叠
    function an(group_id) {
        // group_id: 元素id
        var list = document.getElementById(group_id);

        var tList = list.getElementsByClassName('tList');

        if (list.getElementsByClassName('an')[0].getAttribute('style') == 'transform:rotate(0deg);') {
            // 图标旋转
            list.getElementsByClassName('an')[0].setAttribute('style', 'transform:rotate(90deg);');
            // 展开
            for (var tl = 0; tl < tList.length; tl++) {
                var t = tList[tl].style.display = 'table-row';
            }

            for (var sg = 0; sg < list.getElementsByClassName('subGroup').length; sg++) {
                var s = list.getElementsByClassName('subGroup')[sg];
                s.style.display = 'block';
                // 折叠子级组的教师列表
                for (var stl = 0; stl < s.getElementsByClassName('tList').length; stl++) {
                    s.getElementsByClassName('tList')[stl].style.display = 'none';
                }
            }

        } else {
            // 图标旋转
            list.getElementsByClassName('an')[0].setAttribute('style', 'transform:rotate(0deg);');
            // 折叠
            for (var tl = 0; tl < tList.length; tl++) {
                var t = tList[tl].style.display = 'none';
            }
            // 折叠子级组
            for (var sg = 0; sg < list.getElementsByClassName('subGroup').length; sg++) {
                var t = list.getElementsByClassName('subGroup')[sg].style.display = 'none';
            }


        }
    }


    // 创建列表
    function createSheet(sheet, content, branch,teacherId,fuzeId,type) {
        // sheet: 表元素
        // content: 教职工列表
        // branch: 部门名称
        var lc = 0;
        for (var l = 0; l < content.length; l++) {
            // 创建行
            if (l % 10 == 0) {
                var lCon = document.createElement('tr');
                lCon.setAttribute('class', 'tList');
                sheet.appendChild(lCon);
                lc++;
            }
             //var branchName = sheet.getElementsByTagName('tr')[0].getElementsByTagName('th')[0].textContent;

            var lTr = sheet.getElementsByTagName('tr')[lc];
            var lName = document.createElement('td');
            if (content[l].isPrincipal==='true') {
                lName.setAttribute('class', branch + ' principal');
            } else {
                lName.setAttribute('class', branch);
            }
            lName.setAttribute('id', branch + '_' + content[l].teacherId);
            lName.setAttribute('onclick', 'openTeacherWork("' + branch + '","'+content[l].teacherId+'","'+content[l].jizuId+'","'+content[l].zongshu+'");')
            // 根据工作量设置背景颜色
            if (content[l].zongshu === 1) {
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
            var test=${isGuanli};
                if(test==='true'){
                    // 写入教师名称
                    if(teacherId==fuzeId){
                        document.getElementById(branch + '_' + content[l].teacherId).innerHTML = content[l].teacherName + '' +
                            '<div class="dt" title="删除教师" onclick="openDelTeacher(' + content[l].id + ','+content[l].teacherId+','+content[l].jizuId+');"></div>' +
                            '<div class="sp" title="设为级组负责人" onclick="setPrincipal('+content[l].teacherId+','+content[l].jizuId+');"></div>';
                    }else{
                        document.getElementById(branch + '_' + content[l].teacherId).innerHTML = content[l].teacherName + '' +
                            '<div class="dt" title="删除教师" disabled="disabled" onclick="openDelTeacher(' + content[l].id + ','+content[l].teacherId+','+content[l].jizuId+');"></div>' +
                            '<div class="sp" title="设为级组负责人" onclick="setPrincipal('+content[l].teacherId+','+content[l].jizuId+');"></div>';

                    }
                }else{
                    // 写入教师名称
                    if(teacherId==fuzeId){
                        document.getElementById(branch + '_' + content[l].teacherId).innerHTML = content[l].teacherName + '' +
                            '<div class="dt" title="删除教师" onclick="openDelTeacher(' + content[l].id + ','+content[l].teacherId+','+content[l].jizuId+');"></div>' +
                            '<div class="sp" title="设为级组负责人" disabled="disabled" onclick="setPrincipal('+content[l].teacherId+','+content[l].jizuId+');"></div>';
                    }else{
                        document.getElementById(branch + '_' + content[l].teacherId).innerHTML = content[l].teacherName + '' +
                            '<div class="dt" title="删除教师" disabled="disabled" onclick="openDelTeacher(' + content[l].id + ','+content[l].teacherId+','+content[l].jizuId+');"></div>' +
                            '<div class="sp" title="设为级组负责人" disabled="disabled" onclick="setPrincipal('+content[l].teacherId+','+content[l].jizuId+');"></div>';

                    }
                }

        }
    }

    // 生成级组列表列表
    function createGroupList() {
        var week=$("#week").val();
        var year=$("#year").val();
        var term=$("#term").val();
        var groupList = document.getElementById("groupList");
        groupList.innerHTML = '';
        $.get('/juzuAndjiaozhigong/AllJiZu?schoolYear='+year+'&schoolTrem='+term+'&zhoushu='+week,function (d) {
            var gList = JSON.parse(d);
            for (var g in gList) {
                var gid = gList[g].groupId;
                var gname = gList[g].groupName;
                var gt = gList[g].teacher;

                // 创建父级组
                var group = document.createElement('div');
                group.setAttribute('class', 'group');
                group.setAttribute('id', 'group_' + gid);
                groupList.appendChild(group);

                var dGroup = document.getElementById('group_' + gid);
                var table = document.createElement('table');
                dGroup.appendChild(table);

                var gTable = dGroup.getElementsByTagName('table')[0];
                var titleTr = document.createElement('tr');
                gTable.appendChild(titleTr);

                var tTr = gTable.getElementsByTagName('tr')[0];
                var titleTh = document.createElement('th');
                titleTh.setAttribute('colspan', '10');
                tTr.appendChild(titleTh);

                var tTh = tTr.getElementsByTagName('th')[0];
                var test=${isGuanli};
                if(gid==18 || gid==19){
                    if(gList[g].teacherId==gList[g].fuzeId || test==true){
                        tTh.innerHTML =gname  + '<button class="groupWork" onclick="groupAllWork('+gid+');">发布全组工作</button><button class="addTeacher"  onclick="addTeacher('+gid+');">添加教师</button>';

                    }else{
                        tTh.innerHTML =gname   +
                            '<button class="groupWork" disabled onclick="groupAllWork('+gid+');">发布全组工作</button><button disabled class="addTeacher" onclick="addTeacher('+gid+');">添加教师</button>';

                    }
                }else{
                    if(gList[g].teacherId==gList[g].fuzeId || test==true){
                        tTh.innerHTML =gname + '<button class="setSubGroup" onclick="openGroupSetzi('+gList[g].groupId+');">设置子级组</button>' +
                            '<button class="groupWork" onclick="groupAllWork('+gid+');">发布全组工作</button><button class="addTeacher"  onclick="addTeacher('+gid+');">添加教师</button>';

                    }else{
                        if(asdaa){
                               tTh.innerHTML =gname   + '<button class="setSubGroup"  onclick="openGroupSetzi('+gList[g].groupId+');">设置子级组</button>' +
                            '<button class="groupWork"  onclick="groupAllWork('+gid+');">发布全组工作</button><button  class="addTeacher" onclick="addTeacher('+gid+');">添加教师</button>';
                        }else {
                            tTh.innerHTML =gname   + '<button class="setSubGroup" disabled onclick="openGroupSetzi('+gList[g].groupId+');">设置子级组</button>' +
                                '<button class="groupWork" disabled onclick="groupAllWork('+gid+');">发布全组工作</button><button disabled  class="addTeacher" onclick="addTeacher('+gid+');">添加教师</button>';
                        }

                    }
                }
                   var an = document.createElement('div');
                an.setAttribute('class', 'an');
                an.setAttribute('style', 'transform:rotate(0deg);');
                an.setAttribute('onclick', 'an("group_' + gid + '");');
                tTh.appendChild(an);
                createSheet(gTable, gt, gname,gList[g].teacherId,gList[g].fuzeId,0);

                var gSG = gList[g].subGroup;

                for (var s in gSG) {
                    var sgid = gSG[s].subGroupId;
                    var sgname = gSG[s].subGroupName;
                    var sgt = gSG[s].teacher;
                    var sgtId = gSG[s].teacherId;
                    var fuzeId = gSG[s].fuzeId;

                    var sgroup = document.createElement('div');
                    sgroup.setAttribute('class', 'subGroup');
                    sgroup.setAttribute('id', 'subGroup_' + sgid);
                    dGroup.appendChild(sgroup);

                    var dsGroup = document.getElementById('subGroup_' + sgid);
                    var stable = document.createElement('table');
                    dsGroup.appendChild(stable);

                    var sgTable = dsGroup.getElementsByTagName('table')[0];
                    var titlesTr = document.createElement('tr');
                    sgTable.appendChild(titlesTr);

                    var stTr = sgTable.getElementsByTagName('tr')[0];
                    var titlesTh = document.createElement('th');
                    titlesTh.setAttribute('colspan', '10');
                    stTr.appendChild(titlesTh);

                    var stTh = stTr.getElementsByTagName('th')[0];
                    if(gSG[s].teacherId==gSG[s].fuzeId || test==true){
                        stTh.innerHTML = sgname + '<button class="groupWork" onclick="groupAllWork('+sgid+');">发布全组工作</button>' +
                            '<button class="addTeacher" onclick="addTeacher('+sgid+');">添加教师</button>';
                    }else{
                        stTh.innerHTML = sgname + '<button class="groupWork" disabled onclick="groupAllWork('+sgid+');">发布全组工作</button>' +
                            '<button class="addTeacher" onclick="addTeacher('+sgid+');">添加教师</button>';
                    }
                    var an = document.createElement('div');
                    an.setAttribute('class', 'an');
                    an.setAttribute('style', 'transform:rotate(0deg);');
                    an.setAttribute('onclick', 'an("subGroup_' + sgid + '");');
                    stTh.appendChild(an);

                    createSheet(sgTable, sgt, sgname,sgtId,fuzeId,1);
                }
            }

        })


    }
</script>
<script>
    // 查询与周期失去焦点事件
    function createList(event) {
        event.preventDefault();
        var week=$("#week").val();
        if(week!=null && week!=''){
            createGroupList();
        }else{
            $.error("请输入周期")
        }


       // createTeacherList();
    }
    //设置子级组
    function openGroupSetzi(jizuid){

        if (document.getElementsByClassName('grList')[0]) {
            document.getElementsByClassName('grList')[0].remove();
        }

        var body = document.getElementsByTagName('body')[0];

        var gList = document.createElement('div');
        gList.setAttribute('class', 'grList');
        body.appendChild(gList);

        var groupList = document.getElementsByClassName('grList')[0];

        var glt = document.createElement('div');
        glt.setAttribute('class', 'groupListTitle');
        groupList.appendChild(glt);

        document.getElementsByClassName('groupListTitle')[0].innerHTML = '<div>级组设置</div><div class="off" onclick="offGroupSetzi('+jizuid+');">X</div>'

        var ag = document.createElement('input');
        ag.setAttribute('type', 'button');
        ag.setAttribute('class', 'addGroup');
        ag.setAttribute('value', '添加级组');
        ag.setAttribute('onclick', 'addGroupzi("'+jizuid+'");'); // 添加级组方法
        groupList.appendChild(ag);
        var refresh = document.createElement('input');
        refresh.setAttribute('type','button');
        refresh.setAttribute('class','refresh');
        refresh.setAttribute('value','刷新列表');
        refresh.setAttribute('onclick','shuxinzi("'+jizuid+'");'); // 刷新列表方法
        groupList.appendChild(refresh);

        var gl = document.createElement('table');
        gl.setAttribute('class', 'gList');
        groupList.appendChild(gl);

        var grList = document.getElementsByClassName('gList')[0];
        var tr = document.createElement('tr');
        grList.appendChild(tr);
        grList.getElementsByTagName('tr')[0].innerHTML = '<th class="num">序号</th><th class="source">部门级组</th><th class="handle">操作</th>';


        $.get("/juzuAndjiaozhigong/getAllJiZu?jizuId="+jizuid, function (d) {
            var list = JSON.parse(d);
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
                tb.setAttribute('id', 'td_' + n);
                gtr.appendChild(tb);
                var th = document.createElement('td');
                th.setAttribute('class', 'handle');
                gtr.appendChild(th);

                gtr.getElementsByClassName('num')[0].innerHTML = num;
                gtr.getElementsByClassName('source')[0].innerHTML = list[n].jizuName;
                /*var tds = $('#td_'+n);
                var str="<textarea  disabled>"+list[n].jizuName+"</textarea>";
*/
                //  tds.append(str);


                var edit = document.createElement('input');
                edit.setAttribute('type', 'button');
                edit.setAttribute('class', 'edit');
                edit.setAttribute('value', '编辑');
                edit.setAttribute('onclick', "jiZuPdate(" + list[n].id + ",'" + list[n].jizuName+"')"); // 编辑方法
                var del = document.createElement('input');
                del.setAttribute('type', 'button');
                del.setAttribute('class', 'del');
                del.setAttribute('value', '删除');
                del.setAttribute('onclick', 'deleteszi(' + list[n].id + ',"'+jizuid+'")');//删除方法

                grList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(edit);
                grList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(del);
            }
        })
    }
    //删除级组 --已完成
    function deleteszi(id,jizuid) {
        $.confirm("确定执行删除操作？", function () {
            quedingzi(id,jizuid);
        });
    }
    //删除级组确定 --已完成
    function quedingzi(id,jizuId) {
        $.get('/juzuAndjiaozhigong/deleteJiZu?id='+id,function (d) {
            if(d=="success"){
                $.success("删除成功")
                shuxinzi(jizuId);
            }else{
                $.error("删除失败")

            }       })
    }

    // 添加子级组 --已完成
    function addGroupzi(jizuid) {

        if (document.getElementsByClassName('gList')[0].getElementsByTagName('textarea')[0] == undefined) {
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
            aTr.getElementsByClassName('handle')[0].innerHTML = '<input type="button" class="save" onclick="zijizuAdd('+jizuid+')"  value="确定">';
        }

    }
    /*
* 子级组添加
* */
    function zijizuAdd(jizuid) {
        var tontent=$("#tontent").val();
        var val={};
        val.jizuName=tontent;
        val.zijizu=jizuid
        $.post('/juzuAndjiaozhigong/createZiJiZu',val,function (d) {
            if(d=="success"){
                $.success("添加成功");
                openGroupSetzi(jizuid);
            }else{
                $.error("添加失败,请联系管理员");
            }
        })
    }
function offGroupSetzi(jizuid) {
    document.getElementsByClassName('grList')[0].remove();
    createGroupList();
}
    function shuxinzi(jizuid) {
        offGroupSet();
        createGroupList();
        openGroupSetzi(jizuid)
    }

</script>
<script>
    // 发布全组工作
    function groupAllWork(id) {
        if (document.getElementsByClassName('groupAllWork')[0]) {
            document.getElementsByClassName('groupAllWork')[0].remove();
        }

        var body = document.getElementsByTagName('body')[0];

        var dt = document.createElement('div');
        dt.setAttribute('class', 'groupAllWork');
        body.appendChild(dt);

        var dtDiv = document.getElementsByClassName('groupAllWork')[0];
        var addAllWork = document.createElement('div');
        addAllWork.setAttribute('class', 'addAllWorkTitle');
        addAllWork.innerHTML ='<div class="asd">添加全组工作安排</div><div class="off" onclick="offGroupAllWork2();">X</div>';

        var textarea = document.createElement('textarea');
        textarea.setAttribute('class', 'work_text');
        textarea.setAttribute('id', 'work_textId');
        var yes = document.createElement('input');
        yes.setAttribute('class', 'work_yes');
        yes.setAttribute('type', 'button');
        yes.setAttribute('value', '确定');
        yes.setAttribute('onclick', 'offGroupAllWork("'+id+'");')

        dtDiv.appendChild(addAllWork);
        dtDiv.appendChild(textarea);
        dtDiv.appendChild(yes);
    }

    function offGroupAllWork(id) {
        var contentt=$("#work_textId").val();
        var schoolYear=$("#year").val();
        var schoolTrem=$("#term").val();
        var zhoushu=$("#week").val();
        var val={};
        val.wokeContent=contentt;
        val.schoolYear=schoolYear;
        val.schoolTrem=schoolTrem;
        val.zhoushu=zhoushu;
        val.jizuId=id;
        $.post('/juzuAndjiaozhigong/addTeacherAllWoke',val,function (d) {
            if(d=="success"){
                $.success("添加成功");
                document.getElementsByClassName('groupAllWork')[0].remove();
            }else{
                $.error("添加失败,请联系管理员");
            }
        })

    }
    function offGroupAllWork2() {
        document.getElementsByClassName('groupAllWork')[0].remove();
    }
//--------------------教师工作内容strat----------------
    // 打开教师工作内容 ---已完成
    function openTeacherWork(branchName,tid,jid,nums) {
        // branchName: 部门名称
        var schoolYear=$("#year").val();
        var schoolTrem=$("#term").val();
        var zhoushu=$("#week").val();
        $.get('/juzuAndjiaozhigong/findByAllTeacherId?teacherId='+tid+"&schoolYear="+schoolYear+"&schoolTrem="+schoolTrem+"&zhoushu="+zhoushu,function (d) {
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
    //创建教师工作内容行 ---已完成
    function addTeacherWork(branchName,tid,jid,num){
        if(parseInt(num)>4){
            $.confirm("当前人员工作任务繁多，是否继续添加工作任务", function () {
                addTeacherWork2(branchName,tid,jid);
            });
        }else{
            addTeacherWork2(branchName,tid,jid);
        }
    }

    //刷新 ---已完成
    function shuaxing(name,tid,id,num) {
        offTeacherWork();
        openTeacherWork(name,tid,id,num);
    }
    // 添加工作内容 ------已完成
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
    //添加工作内容 ------已完成
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
        $.post('/juzuAndjiaozhigong/addTeacherWoke',val,function (d) {
            if(d=="success"){
                $.success("添加成功");
                offTeacherWork();
                openTeacherWork(asd,adss,bbs);
            }else{
                $.error("添加失败,请联系管理员");
            }
        })
    }
    //修改工作内容 ------已完成
    function bianjicontont(idss,namsed){
        initWinOnTopFromLeft('修改工作内容', '/juzuAndjiaozhigong/contontBianji?id='+idss+"&name="+namsed, '1000', '650');
    }
    //删除 ------已完成
    function deleteContont(id) {
        $.confirm("确定执行删除操作？", function () {
            deleteContontss(id);
        });
    }
    //删除工作内容 ------已完成
    function deleteContontss(id) {
        // tid: 教师id
        $.get('/juzuAndjiaozhigong/deleteTeacherWoke?id='+id,function (d) {
            if(d==="success"){
                $.success("执行成功");
                offTeacherWork();
                openTeacherWork(asd,adss,bbs);

            }
        })
    }
    // 关闭教师工作安排窗口 ------已完成
    function offTeacherWork() {
        createGroupList();
        document.getElementsByClassName('teacherWork')[0].remove();
    }
    function daochu() {
        var schoolYear=$("#year").val();
        var schoolTrem=$("#term").val();
        var zhoushu=$("#week").val();
        if(schoolYear!=null && schoolYear!=""){
            if(schoolTrem!=null && schoolTrem !=""){
                if(zhoushu!=null && zhoushu!=""){
                    var url="/juzuAndjiaozhigong/daochuWoke?schoolYear="+schoolYear+"&schoolTerm="+schoolTrem+"&zhoushu="+zhoushu;
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

    //--------------------教师工作内容end 级组设置start----------------
    // 打开级组设置 --已完成
    function openGroupSet() {

        if (document.getElementsByClassName('grList')[0]) {
            document.getElementsByClassName('grList')[0].remove();
        }

        var body = document.getElementsByTagName('body')[0];

        var gList = document.createElement('div');
        gList.setAttribute('class', 'grList');
        body.appendChild(gList);

        var groupList = document.getElementsByClassName('grList')[0];

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


        $.get("/juzuAndjiaozhigong/getAllJiZu", function (d) {
            var list = JSON.parse(d);
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
                tb.setAttribute('id', 'td_' + n);
                gtr.appendChild(tb);
                var th = document.createElement('td');
                th.setAttribute('class', 'handle');
                gtr.appendChild(th);

                gtr.getElementsByClassName('num')[0].innerHTML = num;
                gtr.getElementsByClassName('source')[0].innerHTML = list[n].jizuName;
                /*var tds = $('#td_'+n);
                var str="<textarea  disabled>"+list[n].jizuName+"</textarea>";
*/
                //  tds.append(str);

                    if(list[n].id==18 ||list[n].id==19){

                    }else{
                        var edit = document.createElement('input');
                        edit.setAttribute('type', 'button');
                        edit.setAttribute('class', 'edit');
                        edit.setAttribute('value', '编辑');
                        edit.setAttribute('onclick', "jiZuPdate(" + list[n].id + ",'" + list[n].jizuName + "')"); // 编辑方法
                        var del = document.createElement('input');
                        del.setAttribute('type', 'button');
                        del.setAttribute('class', 'del');
                        del.setAttribute('value', '删除');
                        del.setAttribute('onclick', 'deletes(' + list[n].id + ')');//删除方法

                        grList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(edit);
                        grList.getElementsByTagName('tr')[num].getElementsByClassName('handle')[0].appendChild(del);
                    }
            }
        })
    }

    //删除级组 --已完成
    function deletes(id) {
        $.confirm("确定执行删除操作？", function () {
            queding(id);
        });
    }
    //删除级组确定 --已完成
    function queding(id) {
        $.get('/juzuAndjiaozhigong/deleteJiZu?id='+id,function (d) {
            if(d=="success"){
                $.success("删除成功")
                shuaxinqwe();
            }else{
                $.error("删除失败")

            }       })
    }
    // 关闭级组设置弹窗 --已完成
    function offGroupSet() {
        document.getElementsByClassName('grList')[0].remove();
    }
    /*
        * 级组修改
        * *///--已完成
    function jiZuPdate(id,name) {
        initWinOnTopFromLeft('修改级组名称', '/juzuAndjiaozhigong/jizuBian?id='+id+"&name="+name, '1000', '650');
    }
    // 添加级组 --已完成
    function addGroup() {

        if (document.getElementsByClassName('gList')[0].getElementsByTagName('textarea')[0] == undefined) {
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
            aTr.getElementsByClassName('handle')[0].innerHTML = '<input type="button" class="save" onclick="jizuAdd()"  value="确定">';
        }

    }
    /*
* 级组添加
* */
    function jizuAdd() {
        var tontent=$("#tontent").val();
        var val={};
        val.jizuName=tontent;
        $.post('/juzuAndjiaozhigong/createJiZu',val,function (d) {
            if(d=="success"){
                $.success("添加成功");
                openGroupSet();
            }else{
                $.error("添加失败,请联系管理员");
            }
        })
    }
    //级组设置刷新
    function shuaxinqwe(){
        offGroupSet();
        openGroupSet();
    }
//------------------教师人员管理start 级组end----------------------------
    // 打开删除教师弹窗  ---- 已完成
    function openDelTeacher(id,tid,jid) {
        $.confirm("是否确定要将该教师移出该级组？", function () {
            deleteContonts(id,tid,jid);
        });
        stopPropagation();
    }

    // 删除教师  ---- 已完成
    function deleteContonts(id,tid,jid) {
        // tid: 教师id
        $.get('/juzuAndjiaozhigong/updateTeacherJiZu?id='+id+"&teacherId="+tid+"&jizuId="+jid,function (d) {
            if(d==="success"){
                $.success("执行成功");
                createList();
            }
        })
    }

    // 添加教师---- 已完成
    function addTeacher(id) {
        // 教师选择框
        initWinOnTopFromLeft('添加教师', '/juzuAndjiaozhigong/teacherAll?sub=asd&jiZuId='+id, '1200', '650');
    }

    // 设为级组负责人---- 已完成
    function setPrincipal(tid,jid) {
        $.confirm("是否确定要将该教师设为组长？", function () {
            principal(tid,jid);
        });
        stopPropagation();
    }

    //设为组长---- 已完成
    function principal(tid,jid) {
        $.get('/juzuAndjiaozhigong/updateTeacherJiZuZhang?id='+jid+"&teacherId="+tid,function (d) {
            if(d==="success"){
                $.success("执行成功");
                createList();
            }
        })
    }

//------------------教师人员管理end----------------------------
//---------------------------常量资源------------
    // 阻止点击冒泡
    function stopPropagation(e) {
        e = e || window.event;
        if (e.stopPropagation) {
            e.stopPropagation();
        } else {
            e.cancelBubble = true;
        }
    }
    $(function () {
        initSelect()
    });

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
        var groupList = document.getElementById("term");
        groupList.innerHTML = '';
        //添加学期
        addOptionxq('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "term", "code", "name")

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

</script>
</body>