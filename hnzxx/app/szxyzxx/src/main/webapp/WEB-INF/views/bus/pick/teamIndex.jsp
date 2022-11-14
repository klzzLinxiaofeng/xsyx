<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
    <style type="text/css">
        .ulss {
            display: flex;
        }

        .userhead{
            width: 60px; height: 60px;border-radius:50%;border:4px solid grey;
        }
        .wsc{
            border:4px solid grey;
        }

        .redStatus{
            border:4px solid red;
        }

        .ysc{
            border:4px solid blue;
        }

        .yxc{
            border:4px solid green;
        }

        .wcx{
            border:4px solid grey;
        }

        .ycx{
            border:4px solid green;
        }

        .ycxwj{
            border:4px solid orange;
        }

        .yjx{
            border:4px solid green;
        }

        .wjx{
            border:4px solid grey;
        }

        .ulss li {
            float: left;
            list-style: none; /* 将默认的列表符号去掉 */
            padding: 0; /* 将默认的内边距去掉 */
            margin: 0; /* 将默认的外边距去掉 */
            width: 70px;
            height: 100px;
            display: inline-table;
            text-align: center;
            line-height: 30px;
        }

        .cls {
            width: 90px !important;
            height: 70px !important;;
            padding-left: 0px !important;;
            box-shadow: 1px 0px 0px 0px #cdcdcd;    /*右边阴影  蓝色*/
        }

        .divs {
            display: block;
        }
        .sps{
            display: block;
            font-size: 19px;
            margin-bottom: 10px;
            margin-top: 10px;
        }

        .qj{
            width: 20px;position: absolute;margin-left: -9px;

        }

        .carStatus{
            position: absolute;
            top: 20px;left: 150px;
            width: 45px;
            height: 20px;
            text-align: center;
            display: block;
            border-radius: 20%;
            color: white;
        }

        .parentCarStatusAndHasQj{
            line-height: 20px;
            position: absolute;
            top: -1px;left: 119px;
            width: 64px;
            height: 20px;
            text-align: center;
            display: block;
            border-radius: 20%;
            color: white;
        }

        .parentCarStatus{
            line-height: 20px;
            position: absolute;
            top: -1px;left: 100px;
            width: 64px;
            height: 20px;
            text-align: center;
            display: block;
            border-radius: 20%;
            color: white;
        }

        .xcwjx{
            background-color: #aaaaaa;
        }

        .xcyjx{
            background-color: dodgerblue;
        }
        .xcycx{
            background-color: orange
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">

                        <div class="select_b">

                            <div class="select_div">
                                <span>日期：</span>
                                <input type="text" id="date"  autocomplete="off"
                                                       onFocus="WdatePicker({maxDate:'%y-%M-%d'})"
                                                       style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="${date}"
                                                       value="${date}">
                            </div>

                            <div class="select_div"><span>上学/放学：</span>
                                <select id="direction"   class="chzn-select" style="width:120px;">
                                    <option ${direction eq 0 ? 'selected' :''} value="0">上学</option>
                                    <option ${direction eq 1 ? 'selected' :''} value="1">放学</option>
                                </select>
                            </div>

                            <input type="hidden" name="gradeId" />
                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>

                            <div style="float: right;display: ${direction==0 ? 'block':'none'};" id="upNotice" >
                                <span  style="float: left">家长接送、未分配学生颜色说明：<span style="color: gray">灰色：未进校</span>、<span style="color: green">绿色：已进校</span></span>
                                </br>
                                <span style="float: left" >校车接送学生颜色说明：<span style="color: gray">灰色：未上车</span>、<span style="color: blue">蓝色：未下车</span>、<span style="color: green">绿色：已下车</span>、<span style="color: red">红色：校车已进校且未上车</span></span>
                            </div>

                            <div style="float: right;display: ${direction==1 ? 'block':'none'};" id="downNotice">
                                <span  style="float: left">校门口接送学生颜色说明：<span style="color: gray">灰色：未出校</span>、<span style="color: orange">橙色：手动设置出校</span>、<span style="color: green">绿色：已出校</span></span>
                                </br>
                                <span  style="float: left">停车场接送学生颜色说明：<span style="color: gray">灰色：未打卡</span>、<span style="color: orange">橙色：手动设置出校</span>、<span style="color: green">绿色：已出校</span></span>
                                </br>
                                <span style="float: left" >校车接送学生颜色说明：<span style="color: gray">灰色：未上车</span>、<span style="color: blue">蓝色：未下车</span>、<span style="color: green">绿色：已下车</span>、<span style="color: red">红色：校车已出进校且未上车</span></span>
                                </br>
                                <span  style="float: left">未分配学生颜色说明：<span style="color: gray">灰色：未出校</span>、<span style="color: green">绿色：已出校</span></span>
                            </div>

                            <div class="clear"></div>
                        </div>




                        <div id="stuContent">
                            <jsp:include page="teamList.jsp"></jsp:include>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    var lastInSchoolCars='${inSchoolCars}';

    var parentInSchoolStuNames='${parentInSchoolStuNames}';

    $(function(){
        //setInterval(taskFun,30000);
    });

    function showBusIn(car,inTime){
        layer.open({
            title: '校车进校信息'
            ,content: "车牌："+car+"</br>"+"进校时间："+inTime
        });
    }

    function showBusOut(car,inTime,outTime){
        layer.open({
            title: '校车出校信息'
            ,content: "车牌："+car+"</br>"+"进校时间："+inTime+"</br>"+"出校时间："+outTime
        });
    }

    function showSc(name,upTIme,upPlace){
        layer.open({
            title: '校车打卡信息'
            ,content: "学生姓名："+name+"</br>"+"上车打卡时间："+upTIme+"</br>"+"上车打卡地点："+upPlace
        });
    }

    function showPick(name,upTIme,upPlace,title){
        layer.open({
            title: title+'信息'
            ,content: "学生姓名："+name+"</br>"+title+"时间："+upTIme+"</br>"+title+"地点："+upPlace
        });
    }

    function showParentPick(name,upTIme,upPlace,title,carNo,inTime,outTime){
        layer.open({
            title: title+'信息'
            ,content: "学生姓名："+name+"</br>"+title+"时间："+upTIme+"</br>"+title+"地点："+upPlace+"</br>家长接送车辆："+carNo+"</br>车辆入校时间："+inTime+"</br>车辆出校时间："+outTime
        });
    }

    function showXc(name,upTIme,upPlace,downTime,downPlace){
        layer.open({
            title: '校车打卡信息'
            ,content: "学生姓名："+name+"</br>"+"上车打卡时间："+upTIme+"</br>"+"上车打卡地点："+upPlace+"<br/>"+"下车打卡时间："+downTime+"</br>"+"下车打卡地点："+downPlace
        });
    }



    function showParentIn(name,carNo,inTime){
        layer.open({
            title: '家长车辆入校信息'
            ,content: "学生姓名："+name+"</br>家长车辆："+carNo+"</br>入校时间："+inTime
        });
    }

    function showParentOut(name,carNo,inTime,outTime){
        layer.open({
            title: '家长车辆出校信息'
            ,content: "学生姓名："+name+"</br>家长车辆："+carNo+"</br>入校时间："+inTime+"</br>出校时间："+outTime
        });
    }

    function taskFun(){
        var now=new Date();
        var nowDateStr=now.format("yyyy-MM-dd");
        var date=$("#date").val();
        if(date==nowDateStr){
            var direction=$("#direction").val();
            if( (now.getHours()<12 && direction==0) || (now.getHours()>=12 && direction==1) ){
                $.get("/bus/pickData/teamList?teamId=${teamId}&date="+date+"&direction="+direction,function(d){
                    $("#stuContent").html(d);



                    var busNames=getAlertYdxCarNo();
                    var stuNames=getAlertParentInSchoolStu();

                    if(busNames=="" && stuNames==""){
                        return;
                    }

                    var msg="";
                    if(busNames==""){
                        msg="学生【"+stuNames+"】的家长车辆已进校！";
                    }else{
                        msg+="校车【"+busNames+"】已进校！";
                        if(stuNames!=""){
                            msg+="</br>学生【"+stuNames+"】的家长车辆已进校！";
                        }
                    }



                    layer.alert(msg, {
                        time: 30*1000
                        ,success: function(layero, index){
                            var timeNum = this.time/1000, setText = function(start){
                                layer.title((start ? timeNum : --timeNum) + ' 秒后关闭', index);
                            };
                            setText(!0);
                            this.timer = setInterval(setText, 1000);
                            if(timeNum <= 0) clearInterval(this.timer);
                        }
                        ,end: function(){
                            clearInterval(this.timer);
                        }
                    });

                })
            }
        }
    }
    //弹窗显示最近一道校车牌
    function getAlertYdxCarNo(){
        var nowYdx=$("#inSchoolCars").val();
        if(nowYdx=="" || nowYdx==lastInSchoolCars){
            return "";
        }
        var notifyCars="";
        if(lastInSchoolCars==""){
            notifyCars=nowYdx.replace(/,/g, '、');
        }else {
            var lastArr = lastInSchoolCars.split(",");
            var nowArr = nowYdx.split(",");
            for (var i = 0; i < nowArr.length; i++) {
                var val = nowArr[i];
                if (val != null && val != "" && !contain(lastArr, val)) {
                    if (notifyCars.length != 0) {
                        notifyCars += "、";
                    }
                    notifyCars += val;
                }
            }
        }
        if(notifyCars!=""){
            lastInSchoolCars=nowYdx;
        }
        return notifyCars;
    }


    //弹窗显示最近一家长车辆进校学生
    function getAlertParentInSchoolStu(){
        var nowParentInSchoolStuNames=$("#parentInSchoolStuNames").val();
        if(nowParentInSchoolStuNames=="" || nowParentInSchoolStuNames==parentInSchoolStuNames){
            return "";
        }
        var notifyCars="";
        if(parentInSchoolStuNames==""){
            notifyCars=nowParentInSchoolStuNames.replace(/,/g, '、');
        }else {
            var lastArr = parentInSchoolStuNames.split(",");
            var nowArr = nowParentInSchoolStuNames.split(",");
            for (var i = 0; i < nowArr.length; i++) {
                var val = nowArr[i];
                if (val != null && val != "" && !contain(lastArr, val)) {
                    if (notifyCars.length != 0) {
                        notifyCars += "、";
                    }
                    notifyCars += val;
                }
            }
        }
        if(notifyCars!=""){
            parentInSchoolStuNames=nowParentInSchoolStuNames;
        }
        return notifyCars;
    }



    function contain(arr,obj){
        for(var i=0;i<arr.length;i++){
            var val=arr[i];
            if(val!=null && val!="" && val==obj){
                return true;
            }
        }
        return false;
    }


    function showLeave(name,startDate,endDate){
        layer.open({
            title: '请假信息'
            ,content: "请假学生："+name+"</br>"+"请假时间："+startDate+" 至 "+endDate
        });
    }

    function showCarInfo(xlbh,cp,xcbh,sjxm,zgyxm,sjdh,zgydh,companyName){
        layer.open({
            title: '校车信息'
            ,content: "校车公司："+companyName+"<br/>线路编号："+xlbh+"</br>"+"车牌号码："+cp+"</br>"+"校车编号："+xcbh+"</br>"+"司机姓名："+sjxm+"</br>"+"照管员姓名："+zgyxm+"</br>"+"司机电话："+sjdh+"</br>"+"照管员电话："+zgydh+"</br>"
        });
    }


    function search(){
        var date=$("#date").val();
        var direction=$("#direction").val();

        $.get("/bus/pickData/teamList?teamId=${teamId}&date="+date+"&direction="+direction,function(d){
            $("#stuContent").html(d);
            if(direction==0){
                $("#upNotice").show();
                $("#downNotice").hide();
            }else{
                $("#downNotice").show();
                $("#upNotice").hide();
            }
        })
    }


    Date.prototype.format = function(fmt) {
        var o = {
            "M+" : this.getMonth()+1,                 //月份
            "d+" : this.getDate(),                    //日
            "h+" : this.getHours(),                   //小时
            "m+" : this.getMinutes(),                 //分
            "s+" : this.getSeconds(),                 //秒
            "q+" : Math.floor((this.getMonth()+3)/3), //季度
            "S"  : this.getMilliseconds()             //毫秒
        };
        if(/(y+)/.test(fmt)) {
            fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
        }
        for(var k in o) {
            if(new RegExp("("+ k +")").test(fmt)){
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
            }
        }
        return fmt;
    }

</script>
</html>