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
    <style>
        h3 {
            text-align: center;
        }

        a {
            text-decoration: none;
            color: #000;
        }


        #platform {
            margin: auto auto 30px auto;
            width: 100px;
            padding: 0 20px;
            border: 1px solid #000;
        }

        #seat {
            margin: auto;
            width: 90%;
            display: flex;
            justify-content: space-between;
            text-align: right;
        }

        .form {
            margin: auto;
        }
        /*#div2{
            text-align:left;
        }*/
        .stuasd{
            left: 50%;
            top: 50%;
        }
        .line {
            float: left;
        }


        div#div2 {
            display: inline-block;
            width: 100%;
        }

        div#div2 div {
            display: inline-block;
            margin: 20px;
        }

        div#div2 input {
            width: 200px;
            height: 30px;
            margin: 5px;
        }

        div#div2 input#setup {
            width: auto;
            padding: 5px 12px;
            border: rgba(0, 0, 0, .1) 1px solid;
            border-radius: 3px;
            color: #ffffff;
            background-color: #0049a3;
        }

        div#seatTable {
            margin: 25px 10%;
            width: 80%;
            text-align: center;
        }
        .seat {
            border: 1px solid #000;
            text-align: right;
            margin-top: 2px
        }

        .stuName {
            text-align: center;
        }

        input.stuName {
            background-color: white;
            border: 0px;
            border-top: 1px solid;
        }
    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    ???????????????
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b" id="ddd">
                        <div class="select_div"><span>?????????</span>
                            <select id="xn" name="xn" class="chzn-select"
                                    style="width:200px;">
                            </select>
                        </div>
                        <div class="select_div"><span>?????????</span>
                            <select id="nj" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">
                                <option value="">?????????</option>
                            </select>
                        </div>
                        <div class="select_div"><span>?????????</span>
                            <select id="bj" name="teamId" class="chzn-select" style="width:200px;padding-top: 4px;">
                                <option value="">?????????</option>
                            </select>
                        </div>
                        <button type="button" class="btn btn-primary" onclick="chaxun()">??????</button>
                    </div>
                    <div>
                        <%--<iframe href="./zuoweiViews.jsp" id="chen"></iframe>
--%>                    <div id="div2">
                        <div>??????<input type="text" name="form" id="form" value="4">???</div>
                        <div>??????<input type="text" name="line" id="line" value="2">???</div>
                        <div>??????<input type="text" name="row" id="row" value="6">???</div>
                        <div><input type="submit" id="setup" onclick="setupSeat()" value="??????"></div>
                        </div>
                         <div>
                            <div id="seatTable">
                                <div id="platform">
                                    <h3>??????</h3>
                                </div>

                                <div id="seat">

                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">
    function chaxun() {
        var asd=$("#bj").val();
        if (asd ===null || asd===""){
            $.error("???????????????")
        }else{
            chaxunzuowei(asd);
      /*  $.get("/study/habits/zuowei?teamId="+asd, function (d) {
            var score =[];
            $("div[name='seat']").each(function(){
            score.push($(this).attr("seatId"));
        })
           // var stuname =document.getElementsByName("stuName");
           var dd = JSON.parse(d);
        for(var c=0;c<score.length;c++){
            var f=c+1;
            $("#id"+f).val(null);
            $("#ids"+f).val(null);
            for (var i = 0; i < dd.length; i++) {
                var obj = dd[i];
                var a=score[c];
                var b=obj["haoMa"];
                if(a==b){
                    var ddd=obj["studentName"];
                    var db=obj["studentId"];
                    $("#id"+f).val(ddd);
                    $("#ids"+f).val(db);
                }
            }
        }
    })*/
        }
    }
    function setupSeat() {
        var asd = $("#bj").val();
        if (asd ===null || asd==="") {
            document.getElementById('seat').innerHTML = "";

            // ?????? ??? ??????
            var oForm = document.getElementById('form').value;
            // ?????? ??? ??????
            var oLine = document.getElementById('line').value;
            // ?????? ??? ??????
            var oRow = document.getElementById('row').value;
            // ?????? seat
            var oSeat = document.getElementById('seat');
            var a=1;
            for (var f = 0; f < oForm; f++) {
                // ?????? ??? ??????
                var lForm = document.createElement('div');
                lForm.setAttribute('id', String(f + 1) + 'form');
                lForm.setAttribute('class', 'form');
                lForm.style.width = ((90 - ((oForm - 1)) * 5) / oForm) + '%';
                // ?????? ??? ??????
                oSeat.appendChild(lForm);

                for (var l = 0; l < oLine; l++) {
                    // ?????? ???
                    var getForm = document.getElementById(String(f + 1) + 'form');
                    // ?????? ???
                    var dLine = document.createElement('div');
                    dLine.setAttribute('id', String(f + 1) + 'form-' + String(l + 1) + 'line');
                    dLine.setAttribute('class', 'line');
                    dLine.style.width = (95 / oLine) + '%';
                    // ?????? ???
                    getForm.appendChild(dLine);

                    for (var r = 0; r < oRow; r++) {
                        // ?????? ???
                        var getLine = document.getElementById(String(f + 1) + 'form-' + String(l + 1) + 'line');
                        // ?????? ??????
                        var dSeat = document.createElement('div');
                        dSeat.setAttribute('class', 'seat');
                        dSeat.setAttribute('name', 'seat');
                        //dSeat.style.height = '50px';
                        dSeat.setAttribute("value",a);
                        // ?????? ?????????
                        dSeat.setAttribute('seatId', a);
                        var button = document.createElement('button');
                        button.setAttribute('onclick', 'tianjia('+a+')');
                        button.innerText = "??????";
                        button.style.width = 'auto';
                        dSeat.appendChild(button);
                        dSeat.setAttribute('seatId', a);
                     /*   var button = document.createElement('button');
                        button.setAttribute('onclick', '??????('+a+')');
                        button.innerText = "??????";
                        button.style.width = 'auto';
                        dSeat.appendChild(button);*/
                        var dd=document.createElement('input');
                        dd.setAttribute('id', 'ids'+a);
                        dd.setAttribute('type','hidden');
                        dSeat.appendChild(dd);
                        // // ?????? ??????
                        // var getSeat = document.getElementById(String(f + 1) + 'form-' + String(l + 1) + 'line-' + String(r + 1) + 'row');
                        // // ?????? ????????????
                        var stuName = document.createElement('input')
                        stuName.setAttribute('class', 'stuasd');
                        stuName.setAttribute('name', 'stuName');
                        stuName.setAttribute('id', "id"+a);
                        stuName.style.height = '50px';
                        stuName.style.width ='100%';
                        stuName.innerText = "";
                        stuName.setAttribute("type","button");
                        /*stuName.setAttribute('onclick','dianpin('+a+')');*/
                        a++;
                        //     // ?????? ????????????
                        dSeat.appendChild(stuName);
                        // ?????? ??????
                        getLine.appendChild(dSeat);
                    }
                }
            }
        } else {
            getzuowei(asd);
        }
    }
    //??????????????????
    function getzuowei(asd) {
        var form=$("#form").val();
        var line=$("#line").val();
        var row=$("#row").val();
        $.get("/study/habits/zuoweiHangLie?teamId="+asd+"&zu="+form+"&hang="+line+"&lie="+row,function (d) {
            if(d==="success"){
                chaxunzuowei(asd);
            }
        })
    }
    //?????????????????????
    function chaxunzuowei(asd) {
        $.get("/study/habits/zuoweiHangList?teamId="+asd,function (ds) {
            var d=JSON.parse(ds);
            if(d['stats']==='success'){
                var zu=d['list'].zuNumber;
                var hang=d['list'].hangNumber;
                var lie=d['list'].lieNumber;
                zuoweiShow(asd,zu,hang,lie);
            }else{
                $.error("??????????????????")
            }
        })
    }
    //???????????????????????????
    function zuoweiShow(asd,zu,hang,lie) {
        $.get("/study/habits/zuowei?teamId="+asd, function (d) {
            var a=1;
            document.getElementById('seat').innerHTML = "";
            d = JSON.parse(d);
            $("#form").val(zu);
            var oForm =zu;
            // ?????? ??? ??????
            $("#line").val(hang);
            var oLine=hang;
            // ?????? ??? ??????
            $("#row").val(lie);
            var oRow=lie;
            // ?????? seat
            var oSeat = document.getElementById('seat');
            for (var f = 0; f < oForm; f++) {
                // ?????? ??? ??????
                var lForm = document.createElement('div');
                lForm.setAttribute('id', String(f + 1) + 'form');
                lForm.setAttribute('class', 'form');
                lForm.style.width = ((90 - ((oForm - 1)) * 5) / oForm) + '%';
                // ?????? ??? ??????
                oSeat.appendChild(lForm);

                for (var l = 0; l < oLine; l++) {
                    // ?????? ???
                    var getForm = document.getElementById(String(f + 1) + 'form');
                    // ?????? ???
                    var dLine = document.createElement('div');
                    dLine.setAttribute('id', String(f + 1) + 'form-' + String(l + 1) + 'line');
                    dLine.setAttribute('class', 'line');
                    dLine.style.width = (95 / oLine) + '%';
                    // ?????? ???
                    getForm.appendChild(dLine);

                    for (var r = 0; r < oRow; r++) {
                        // ?????? ???
                        var getLine = document.getElementById(String(f + 1) + 'form-' + String(l + 1) + 'line');
                        // ?????? ??????

                        var dSeat = document.createElement('div');
                        dSeat.setAttribute('id', String(f + 1) + 'form-' + String(l + 1) + 'line-' + String(r + 1) + 'row');
                        dSeat.setAttribute('class', 'seat');
                        dSeat.setAttribute('name', 'seat');
                        //dSeat.style.height = '60px';
                        // ?????? ?????????
                        dSeat.setAttribute('seatId', a);
                        var button = document.createElement('button');
                        button.setAttribute('onclick', 'tianjia('+a+')');

                        //button.setAttribute('value', '??????');
                        button.style.width = 'auto';
                        button.innerText = "??????";

                        dSeat.appendChild(button);
                        // ??????
                        dSeat.setAttribute('seatId', a);
                        var button2 = document.createElement('button');
                        button2.style.width = 'auto';
                        button2.innerText = "??????";

                        dSeat.appendChild(button2);

                        // // ?????? ????????????
                        var dd=document.createElement('input');
                        dd.setAttribute('id', 'ids'+a);
                        dd.setAttribute('type','hidden');
                        var stuName = document.createElement('input');
                        stuName.setAttribute('type','button')
                        stuName.setAttribute('class', 'stuName');
                        stuName.setAttribute('name', 'stuName');
                        stuName.setAttribute('id', 'id'+a);
                        /*  stuName.setAttribute('onclick','dianpin('+a+')');*/
                        stuName.style.height = '50px';
                        stuName.style.width ='100%';
                        var b=0;
                        for (var i = 0; i < d.length; i++) {
                            var obj = d[i];
                            if(a===obj["haoMa"]){
                                stuName.setAttribute('value',obj['studentName']);
                                dd.setAttribute('value',obj['studentId']);
                                button2.setAttribute('onclick', 'shanchu('+obj['id']+')');
                                b=1;
                            }
                        }
                        if(b==0){
                            stuName.setAttribute("value","");
                        }
                        a++;
                        dSeat.appendChild(dd);
                        //     // ?????? ????????????
                        dSeat.appendChild(stuName);
                        // ?????? ??????
                        getLine.appendChild(dSeat);
                    }
                }
            }
        })

    }
    function tianjia(obj) {

            $.initWinOnTopFromLeft('????????????', '/study/habits/zuoweitianjia?zuowei='+obj, '1000', '550');
        }
    $(function () {
        initSelect();
       // reSetSize();
        setupSeat();
      //  window.onresize = reSetSize;
    });

    function initSelect() {
        //????????????????????????????????????
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                //addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //??????????????????????????????????????????????????????????????????????????????
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })

        //???????????????????????????
        $("#xn").change(function(){
            $("#nj").html('<option value="">?????????</option>');
            $("#bj").html('<option value="">?????????</option>');
            //????????????
            addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
        })
        $("#nj").change(function(){
            $("#bj").html('<option value="">?????????</option>');
            //????????????
            if($(this).val()!="") {
                addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
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
    //??????
    function shanchu(id) {
        $.confirm("???????????????????????????", function () {
            deleteContonts(id);
        });
    }
    //??????????????????
    function deleteContonts(id) {
        // tid: ??????id
        $.get('/study/habits/zuoweishanchu?id='+id,function (d) {
            if(d==="success"){
                $.success("????????????");
                chaxun();
            }
        })
    }


</script>
</body>
</html>
