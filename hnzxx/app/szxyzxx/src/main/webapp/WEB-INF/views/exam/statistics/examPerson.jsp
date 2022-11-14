<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        body{
            margin: 0;
            padding: 0;
        }
        a{
            text-decoration: none;
        }
        .clear{
            clear: both;
        }
        .manage {
            padding: 15px;
            background: #e6eaeb;
        }
        .count {
            height: 40px;
            line-height: 40px;
            background: #fff;
            color: #3f3e3e;
            font-family: '微软雅黑';
            font-size: 14px;
            padding-left: 15px;
            margin-bottom: 10px;
        }
        .count a {
            float: right;
            width: 60px;
            height: 25px;
            line-height: 25px;
            background: #efad4d;
            color: #fff;
            text-align: center;
            margin: 8px 15px;
        }
        .covariance {
            border-bottom: 1px #cfd0d0 solid;
            background: #fff;
            color: #3f3e3e;
            font-family: '微软雅黑';
            font-size: 16px;
            width: 100%;
            margin-bottom: 10px;
        }
        .statistics{
            width: 39%;
            float: left;
            margin: 1% 5% 3%;
            border: 1px #ccc solid;
        }
        .statistics h3{
            height: 50px;
            margin: 0;
            padding-left: 15px;
            background: #91c7ae;
            font-family: '微软雅黑';
            font-size: 16px;
            color: #fff;
            line-height: 50px;
        }
        .schedule{
            height: 417px;
            width: 100%;
        }
        .schedule table{
            border-collapse: collapse;
            border-left: 1px #ccc solid;
            border-top: 1px #ccc solid;
            width: 100%;
            text-align: center;
        }
        .schedule table tr td,.schedule table tr th{
            border-bottom: 1px #ccc solid;
            border-right: 1px #ccc solid;
            height: 37px;
            line-height: 37px;
            padding: 0;
        }
        .ld_chart{
            height: 417px;
            width: 100%;
        }
        .xzxs{line-height: 300px;text-align: center;font-size: 50px;font-family: "微软雅黑";color:#333;}
    </style>
</head>
<body>
<div id="body111" style="padding-top:30px;">
    <p class="xzxs">请选择学生</p>
</div>
</body>
<script type="text/javascript">
    $(function(){
        getData();
        /* $(window).resize(function(){
        	eval("myChart").resize();
        }) */
    })

    //定义用户用时与班级平均用时的比例，即table中等级
    var fine = 0.9;  //优秀等级
    var well = 1.2; //良好等级
    var medium = 1.5;   //中等等级，差的等级是大于1.5

    //传参：作图的div，标题JSON数据，具体JSON数据
    
    function initDataById(divId,titleData,deailData){
    	var myChart = echarts.init(document.getElementById(divId));
        option = {
            title: {
                text: '',
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                x: 'center',
                data:['个人用时（秒）','平均用时（秒）'],
                bottom: '3%',
            },
            radar: [
                {
                    indicator: titleData,
                    center: ['50%','45%'],
                    radius: 140,
                },
            ],
            series: [
                {
                    type: 'radar',
                    tooltip: {
                        trigger: 'item'
                    },
                    itemStyle: {normal: {
                        areaStyle: {type: 'default'},
                        label : {show: true}
                    }
                    },
                    data: deailData
                },
            ],color: ['#ffc107','#03A9F4'],
        };
        myChart.setOption(option);
    }

    //获取数据源
    function getData(){
        //每个图的长度
        var si = 10;
        //获取个人考试详细情况
        var loader = new loadLayer("请稍等...","100000");
        loader.show();
        $.post("${ctp}/statistic/getPersonExamDeail",{"examId":${exam.id},"studentId":$("#stu").val()}, function(returnData, status) {
        	if ("success" === status) {
        		
                if(returnData!=""){
                var leng = returnData.length%si;
                var l = 0;
                if(leng >= 3){
                    l = parseInt(returnData.length/si + 1);
                }else{
                    l = parseInt(returnData.length/si);
                }

                //标题
                var titles = createTitles(l,returnData.length);
                var divIds = createDivIds(l);

                //每个图的JSON数据结构，使用这个图完之后清空，存下一个图
                var dataJSON = "";
                var titleJSON = "";
                var i = 0;

                var dataArr = new Array(l);
                var dataArrForLD = new Array(l);
                var titleArr = new Array(l);

                //根据作图的数量 循环数据源
                $.each(returnData,function(n,value) {
                    //数据构造
                    if(dataJSON == ""){
                        dataJSON = createJSON(value);
                    }else{
                        dataJSON += "," + createJSON(value);
                    }

                    //数据构造
                    if(titleJSON == ""){
                        titleJSON = createLDTitle(value);
                    }else{
                        titleJSON += "," + createLDTitle(value);
                    }

                    //如果够十条数据，生成一个图
                    if((n+1)%si == 0 || (n+1) == returnData.length){
                        //将JSON数据存进数组，后面在组装到页面
                        dataJSON = JSON.parse("[" + dataJSON + "]");
                        dataArr[i] = dataJSON;

                        //装备雷达图数据
                        //个人用时
                        var valuePersonData = new Array(dataJSON.length);

                        //班级用时
                        var valueTeamData = new Array(dataJSON.length);

                        //将每一组数据存入数组中
                        for(var k = 0; k < dataJSON.length; k++){
                            valuePersonData[k] = dataJSON[k].answerTime;
                            valueTeamData[k] = dataJSON[k].avgAnswerTime;
                        }

                        dataArrForLD[i] = JSON.parse("[{\"name\":\"个人用时（秒）\",\"value\":["+valuePersonData+"]}," +
                                "{\"name\":\"平均用时（秒）\",\"value\":["+valueTeamData+"]}]");

                        titleArr[i] = JSON.parse("[" + titleJSON + "]");

                        //重置JSON
                        dataJSON = "";
                        titleJSON = "";
                        i++;
                    }
                });

                //组装数据之前清空数据内容
                emptyHtml();

                //将数据组装到雷达图及table
                for(var j = 0; j < dataArr.length; j++){
                    var htmlForLD = createLDHtml(divIds[j],titles[j]);
                    var htmlForTable = createTableHtml(titles[j],dataArr[j]);

                    $("#body111").append(htmlForLD).append(htmlForTable);

                    initDataById(divIds[j],titleArr[j],dataArrForLD[j]);
                }
                $("#body111").append("<div class='clear'></div>");

            }else{
            	 $("#body111").html('<h2 style="padding-left: 468px">暂无数据</h2>');
            }
                loader.close();
            }
        },'json');
    }

    function emptyHtml(){
        $("#body111").html("");
    }

    function createJSON(data){
        var json = "{" +
                "\"pos\":\"" + data.pos + "\"," +
                "\"knowledge\":\"" + data.knowledge + "\"," +
                "\"answerTime\":\"" + data.answerTime + "\"," +
                "\"avgAnswerTime\":\"" + data.avgAnswerTime + "\"," +
                "\"appraise\":\"" + initLevel(data) + "\"," +
                "\"isCorrect\":\"" + data.isCorrect + "\"" +
                "}";
        return json;
    }

    function createLDTitle(data){
        var json = "{" +
                "\"text\":\"第" + data.pos + "题\"," +
                "\"max\":\"" + data.maxAnswerTime + "\"" +
                "}";
        return json;
    }

    //创建测试数据
    function createTestData(){
        var data = "";
        data = "[" +
                "{\"pos\":\"1\",\"knowledge\":\"读音\",\"answerTime\":\"12\",\"avgAnswerTime\":\"15\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"2\",\"knowledge\":\"字形\",\"answerTime\":\"13\",\"avgAnswerTime\":\"15\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"3\",\"knowledge\":\"词汇\",\"answerTime\":\"16\",\"avgAnswerTime\":\"18\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"4\",\"knowledge\":\"读音\",\"answerTime\":\"11\",\"avgAnswerTime\":\"13\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"false\"}," +
                "{\"pos\":\"5\",\"knowledge\":\"作文\",\"answerTime\":\"5\",\"avgAnswerTime\":\"18\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"6\",\"knowledge\":\"古寺次\",\"answerTime\":\"10\",\"avgAnswerTime\":\"13\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"7\",\"knowledge\":\"多音字\",\"answerTime\":\"12\",\"avgAnswerTime\":\"14\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"8\",\"knowledge\":\"应用文\",\"answerTime\":\"9\",\"avgAnswerTime\":\"12\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"false\"}," +
                "{\"pos\":\"9\",\"knowledge\":\"阅读理解\",\"answerTime\":\"41\",\"avgAnswerTime\":\"12\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"10\",\"knowledge\":\"作文\",\"answerTime\":\"12\",\"avgAnswerTime\":\"30\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"11\",\"knowledge\":\"语音\",\"answerTime\":\"13\",\"avgAnswerTime\":\"21\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"12\",\"knowledge\":\"回升\",\"answerTime\":\"15\",\"avgAnswerTime\":\"22\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"false\"}," +
                "{\"pos\":\"13\",\"knowledge\":\"生化\",\"answerTime\":\"10\",\"avgAnswerTime\":\"23\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"14\",\"knowledge\":\"无意义\",\"answerTime\":\"19\",\"avgAnswerTime\":\"4\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"15\",\"knowledge\":\"生化\",\"answerTime\":\"10\",\"avgAnswerTime\":\"23\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"16\",\"knowledge\":\"生化\",\"answerTime\":\"10\",\"avgAnswerTime\":\"23\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"false\"}," +
                "{\"pos\":\"17\",\"knowledge\":\"生化\",\"answerTime\":\"10\",\"avgAnswerTime\":\"23\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}," +
                "{\"pos\":\"18\",\"knowledge\":\"生化\",\"answerTime\":\"10\",\"avgAnswerTime\":\"23\",\"maxAnswerTime\":\"30\",\"isCorrect\":\"true\"}" +
                "]"
        return data;
    }

    function createTitles(length,dataLength){
        var titles = new Array(length);
        //创建数据表图
        for(var i = 1; i <= length; i++){
            var title = "第" + (1+(i-1)*10) + " ~ " + (i*10) + "题";
            titles[i-1] = title;
        }
        if(length*10 > dataLength){
            titles[length-1] = "第" + ((length-1)*10 + 1) + " ~ " + dataLength + "题";
        }
        return titles;
    }

    function createDivIds(length){
        var ids = new Array(length);
        //创建数据表图
        for(var i = 1; i <= length; i++){
            var id = "question_" + i;
            ids[i-1] = id;
        }
        return ids;
    }

    //创建雷达图数据HTML
    function createLDHtml(id,title){
        var html = "";
        html = "<div class = 'statistics'>" +
                "<h3>" + title + "作答用时对比图</h3>" +
                "<div id='"+id+"' class='ld_chart'></div></div>"
        return html;
    }

    //创建表数据页面HTML
    function createTableHtml(title,data){
        var html = "";
        html =  "<div class='statistics'><h3>" + title + "作答用时详情表</h3>" +
                "<div class = 'schedule'>" +
                "<table>" +
                "<thead>" +
                "<tr>" +
                "<th>题号</th>" +
                "<th>知识点</th>" +
                "<th>答题用时</th>" +
                "<th>平均用时</th>" +
                "<th>评价</th>" +
                "</tr>" +
                "</thead>" +
                "<tbody>";

        for(var i = 0; i < data.length; i++){
            if(data[i].isCorrect == "true"){
                html += "<tr>" +
                        "<td>" + data[i].pos + "</td>" +
                        "<td>" + data[i].knowledge + "</td>" +
                        "<td>" + data[i].answerTime + "</td>" +
                        "<td>" + data[i].avgAnswerTime + "</td>" +
                        "<td>" + data[i].appraise + "</td>" +
                        "</tr>"
            }else{
                html += "<tr style='color: red'>" +
                        "<td>" + data[i].pos + "</td>" +
                        "<td>" + data[i].knowledge + "</td>" +
                        "<td>" + data[i].answerTime + "</td>" +
                        "<td>" + data[i].avgAnswerTime + "</td>" +
                        "<td>" + data[i].appraise + "</td>" +
                        "</tr>"
            }
        }

        html += "</tbody></table></div></div>";
        return html;
    }

    function initLevel(value){
        var level = "差";
        var num = Number(value.answerTime/value.avgAnswerTime).toFixed(1);
        if(parseFloat(num) <= 0.9){
            level = "优秀";
        }else if(0.9 < parseFloat(num) && parseFloat(num) <= 1.2){
            level = "良好";
        }else if(1.2 < parseFloat(num) && parseFloat(num) <= 1.5){
            level = "中等";
        }else{
            level = "差";
        }
        if(value.isCorrect == false){
            level = "弱";
        }
        return level;
    }
</script>
</html>
