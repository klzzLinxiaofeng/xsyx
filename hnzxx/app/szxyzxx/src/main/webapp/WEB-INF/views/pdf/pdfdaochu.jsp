<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>成长档案</title>
    <%@ include file="/views/embedded/common.jsp"%>

    <style>
        body {
            background-color: #f8f8f8;
        }

        a {
            color: #000;
            text-decoration: none;
        }

        ul {
            list-style-type: none;
        }

        select,
        input {
            height: 30px;
            width: 120px;
            border: 1px solid #d9d9d9;
        }

        .find {
            display: flex;
            padding: 20px 0;
            width: 90%;
            min-width: 1300px;
            margin: 20px 5%;
            background-color: white;
        }

        .select {
            margin-left: 20px;
            margin-right: 7px;
        }

        input {
            height: 24px;
        }

        #find,
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
        #export:hover {
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }

        .recordContent {
            width: 90%;
            margin: 0 5%;
            background-color: #f8f8f8;
            /* padding: 400px 0;
            text-align: center; */
        }

        #plateList {
            height: 38px;
        }

        #plateList ul {
            list-style-type: none;
            width: 100%;
            min-width: 1300px;
            margin: 0;
            padding: 0;
            overflow: auto;
        }

        #plateList li {
            float: left;
            display: block;
            color: #333;
            background-color: white;
            border: 1px solid #d9d9d9;
            text-align: center;
            padding: 8px 32px;
        }

        #plateList li:hover {
            background-color: #0d7bd5;
            color: #fff;
        }

        .plateTitle {
            color: #777;
            font-size: 20px;
            font-weight: bold;
            margin: 20px 5% -20px 5%;
            padding-left: 10px;
            border-left: 5px solid #0d7bd5;
        }

        .module {
            min-width: 1300px;
            margin-top: 50px;
            width: 100%;
            background-color: white;
            border: 1px solid #d9d9d9;
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }

        #character {
            margin-top: 0;
        }

        .index {
            float: left;
            width: 50%;
            height: 300px;
            margin: 25px 5%;
            overflow: auto;
        }

        .index::-webkit-scrollbar {
            width: 0;
            height: auto;
        }

        .index ul li {
            float: left;
            width: 130px;
            height: 50px;
            border-left: 1px solid #d9d9d9;
            margin: 5px;
            padding: 3px 8px;
        }

        .indexName,
        .electiveTeacher,
        .electiveHour {
            font-size: 14px;
            color: #999;
        }

        .value,
        .electiveTitle {
            font-size: 18px;
            color: #777;
            line-height: 1.7;
            font-weight: bold;
        }

        .value .fen {
            font-size: 16px;
            color: #999;
            font-weight: lighter;
            margin-left: 3px;
        }

        .graph {
            display: inline-block;
            width: 400px;
            height: 300px;
            margin: 25px;
            margin-left: 2%;
        }

        .zTitle {
            width: 300px;
            margin: 10px 5%;
            font-weight: bold;
            color: #777;
        }

        .characterRecord {
            height: 400px;
            overflow-x: scroll;
        }

        .characterRecord::-webkit-scrollbar {
            width: 0;
            height: auto;
        }

        .characterRecord table {
            width: 90%;
            margin: 0 5%;
            text-align: center;
            border: 0px;
        }
        #tableId2{
            height: 400px;
            width: 90%;
            margin: 0 5%;
            text-align: center;
            border: 0px;
        }

        th {
            color: white;
            background-color: #0d7bd5;
            border: 1px solid #e0e4e4;
            font-size: 12px;
            padding: 8px;
            text-align: center;
        }

        td {
            background-color: white;
            border: 1px solid #d9d9d9;
            color: #777;
        }

        .subjectBar ul, .zhishidian ul {
            height: 50px;
            margin: 25px 5%;
            padding: 10px 0px;
            white-space: nowrap;
            overflow-y: hidden;
            overflow-x: scroll;
        }

        .subjectBar ul::-webkit-scrollbar {
            height: 10px;
        }
        .zhishidian ul::-webkit-scrollbar {
            height: 10px;
        }

        .subjectBar ul::-webkit-scrollbar-thumb {
            border-radius: 10px;
            background: #ebebeb;
            box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
        }
        .zhishidian ul::-webkit-scrollbar-thumb {
            border-radius: 10px;
            background: #ebebeb;
            box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
        }

        .subjectBar ul li {
            padding: 7px 25px;
            margin: 5px;
            text-align: center;
            background-color: #fff;
            border: 1px solid #d9d9d9;
            border-radius: 5px;
            display: inline-block;
        }
        .zhishidian ul li {
            padding: 7px 25px;
            margin: 5px;
            text-align: center;
            background-color: #fff;
            border: 1px solid #d9d9d9;
            border-radius: 5px;
            display: inline-block;
        }

        .subjectBar ul li:hover {
            color: #fff;
            border-color: #999;
            background-color: #0d7bd5;
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }
        .zhishidian ul li:hover {
            color: #fff;
            border-color: #999;
            background-color: #0d7bd5;
            box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);
        }

        #subject .chartDiv {
            height: 350px;
        }

        #subjectChart {
            width: 100%;
            height: 100%;
        }

        #physical .index {
            width: 90%;
            height: auto;
        }

        #physical .index .zTitle {
            margin-left: 25px;
        }
        .one {
            margin-left: 25px;
        }

        .report {
            width: 85%;
            margin: 30px 5% 20px 5%;
            padding-left: 25px;
        }

        .report .zTitle {
            margin-left: 0;
        }

        .report .reportContent {
            height: 80px;
            margin-left: 25px;
            padding: 10px;
            font-size: 16px;
            color: #555;
            font-weight: lighter;
            background-color: #f8f8f8;
            border: 1px solid #d9d9d9;
            border-radius: 5px;
        }

        .imageList {
            width: 90%;
            margin: 30px 5% 20px 5%;
        }

        .imageList .zTitle {
            margin-left: 25px;
        }

        .imageList .imgList {
            width: 90%;
            height: 110px;
            margin: 0 5%;
            overflow: scroll;
        }

        .imageList .imgList::-webkit-scrollbar {
            width: 0;
            height: 0;
        }

        .imageList:hover .imgList::-webkit-scrollbar {
            width: 5px;
            height: 0;
        }

        .imageList .imgList::-webkit-scrollbar-thumb {
            border-radius: 10px;
            background: #ebebeb;
            box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
        }

        .imgList img {
            float: left;
            margin: 5px;
            height: 100px;
        }

        .one span {
            font-weight: normal;
        }

        #elective ul li {
            display: inline-block;
            margin: 5px;
            width: 330px;
            border: 1px solid #d9d9d9;
        }

        #elective ul li img {
            float: left;
            height: 100px;
            width: 70px;
        }

        .message {
            margin-left: 90px;
            margin-top: 10px;
        }
    </style>
    <!-- Echarts 图表库 -->
    <script src="https://cdn.staticfile.org/echarts/4.3.0/echarts.min.js"></script>
   <%-- <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>--%>
   <%-- <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.0.272/jspdf.debug.js"></script>
    <script src="https://cdn.polyfill.io/v2/polyfill.min.js"></script>--%>
    <script src="${pageContext.request.contextPath}/res/js/jspdf.customfonts.min.js"></script>
    <script src="${pageContext.request.contextPath}/res/js/default_vfs.js"></script>
</head>
<body id="exportPdf">

<!-- 搜索框 -->

<form id="signupForm">
    <!-- 搜索框 -->
    <div class="find" id="divx">
    <div class="select">
      <span>学年：</span>
        <select id="xn" name="xn" class="chzn-select"
                style="width:200px;">

        </select>
    </div>
     <div class="select">
         <span>学期：</span>
         <select id="xq" name="xq" class="chzn-select"
                 style="width:200px;padding-top: 4px;">
         </select>
     </div>
    <div class="select">
        <span>年级：</span>
        <select id="nj" name="gradeId" class="chzn-select"style="width:200px;padding-top: 4px;">
            <option value="">请选择</option>
        </select>
    </div>
    <div class="select">
        <span>班级：</span>
        <select id="bj" name="teamId" class="chzn-select" style="width:200px;padding-top: 4px;">
            <option value="">请选择</option>
        </select>
    </div>

    <div class="select"><span>姓名：</span>
        <select id="studentName" name="studentName" class="chzn-select" style="width:200px;padding-top: 4px;">
            <option value="">请选择</option>
        </select>
    </div>
    <button id="find" <%--onclick="findByAll()"--%>>查询</button>
        <button id="export" >导出</button>
</div>
</form>

<!-- 成长档案报告主体 -->
<div class="recordContent">
    <p style="color: #777;font-size: 32px;font-weight: bolder;display:none;">请选择学生</p>

    <!-- 模块导航栏 -->
    <div id="plateList">
        <ul id="plate">
            <li class="plate" id="characterPlate" onclick="location.href='#character';"
                style="background-color:#0d7bd5;color: #fff;">品格养成</li>
            <li class="plate" id="studyPlate" onclick="location.href='#study';">学习习惯</li>
            <li class="plate" id="knowEvalution" onclick="location.href='#zhishidians';">知识点</li>
            <li class="plate" id="studentScore" onclick="location.href='#chengjifenxi';">学生成绩分析</li>
            <li class="plate" id="subjectPlate" onclick="location.href='#subject';">学科素养</li>
            <li class="plate" id="physicalPlate" onclick="location.href='#physical';">体质健康</li>
            <li class="plate" id="practicePlate" onclick="location.href='#practice';">实践创新</li>
            <li class="plate" id="artPlate" onclick="location.href='#art';">艺术审美</li>
        </ul>
    </div>

    <!-- 品格养成 -->
    <div id="character" class="module">
        <p class="plateTitle">品格养成：</p>

        <!-- 指标分数 -->
        <div class="index">

            <div class="zTitle">品格养成指标得分：</div>

            <ul></ul>
        </div>

        <!-- 指标统计图 -->
        <div class="graphDiv">
            <div class="graph" id="characterGraph"></div>
        </div>

        <!-- 评价记录 -->
        <div class="record">
            <div class="zTitle">品格养成评价记录：</div>
            <div class="characterRecord">
                <table cellspacing="0" id="tableId" cellpadding="8" bordercolor="#d9d9d9">
                    <thead>
                    <tr>
                        <th>品格指标</th>
                        <th>点评教师</th>
                        <th>评分</th>
                        <th>评语</th>
                        <th>点评时间</th>
                    </tr>
                    </thead>
                   <tbody id="tableIdTbo">

                   </tbody>
                </table>

            </div>
            <%--<table id="tableId2" style="display: none">
                <thead>
                <tr>
                    <th>品格指标</th>
                    <th>点评教师</th>
                    <th>评分</th>
                    <th>评语</th>
                    <th>点评时间</th>
                </tr>
                </thead>
                <tbody id="tableIdTbo2">

                </tbody>
            </table>--%>
        </div>
    </div>

    <!-- 学习习惯 -->
    <div id="study" class="module">
        <p class="plateTitle">学习习惯：</p>

        <!-- 指标分数 -->
        <div class="index">

            <div class="zTitle">学习习惯指标得分：</div>

            <ul>
            </ul>
        </div>

        <!-- 指标统计图 -->
        <div class="graphDiv">
            <div class="graph" id="studyGraph"></div>
        </div>

    </div>

    <%--知识点--%>
    <div id="zhishidians" class="module">
        <p class="plateTitle">知识点：</p>

        <!-- 科目选项 -->
        <div class="zhishidian">
            <ul>
                <li id="li_1" class="subject" onclick="zhishidian(1)">语文</li>
                <li id="li_2" class="subject" onclick="zhishidian(2)">数学</li>
                <li id="li_3" class="subject" onclick="zhishidian(3)">英语</li>
            </ul>
        </div>
        <div class="characterRecord">
            <table cellspacing="0" id="zhishidianTable" cellpadding="8" bordercolor="#d9d9d9">
                <thead>
                <tr>
                    <th>课本名称</th>
                    <th>单元</th>
                    <th>知识点</th>
                    <th>评分</th>
                    <th>评语</th>
                </tr>
                </thead>
                <tbody id="tableZhiShi">

                </tbody>
            </table>

        </div>
    </div>

    <div id="chengjifenxi" class="module">
        <p class="plateTitle">学生成绩分析：</p>
        <div class="characterRecord">
            <table cellspacing="0" id="chengJiFenxiTable" cellpadding="8" bordercolor="#d9d9d9">
                <thead>
                <tr>
                    <th>期中方差值</th>
                    <th>期末方差值</th>
                </tr>
                </thead>
                <tbody id="tableScore">

                </tbody>
            </table>

        </div>
    </div>
    <!-- 学科素养 -->
    <div id="subject" class="module">
        <p class="plateTitle">学科素养：</p>

        <!-- 科目选项 -->
        <div class="subjectBar">
            <ul>
            </ul>
        </div>

        <!-- 指标分数 -->
        <div class="index">

            <div class="zTitle">学科素养指标得分：</div>

            <ul>
            </ul>
        </div>

        <!-- 指标统计图 -->
        <div class="graphDiv">
            <div class="graph" id="subjectGraph"></div>
        </div>

        <div class="chartDiv">
            <div class="chart" id="subjectChart"></div>
        </div>
    </div>

    <!-- 体质健康 -->
    <div id="physical" class="module">
        <p class="plateTitle">体质健康：</p>

        <!-- 体测数据 -->
        <div class="index">

            <div class="zTitle">体测数据：</div>

            <ul>
            </ul>
        </div>

        <!-- 请假记录 -->
        <div class="one" id="leave">
            <div class="zTitle">请假记录：<span id='leaveRecord' style="font-weight: normal;"></span></div>
            <!-- 本学期请假3次，共5天。 -->
        </div>

        <!-- 体测报告 -->
        <div id="physicalReport" class="report">
            <div class="zTitle">体测报告：</div>
            <div class="reportContent">
            </div>
        </div>

        <!-- 医务室记录 -->
        <div id="clinic" class="report">
            <div class="zTitle">医务室记录：</div>
            <div class="reportContent">
            </div>
        </div>

        <!-- 体质健康报告 -->
        <div id="healthReport" class="report">
            <div class="zTitle">体测健康报告：</div>
            <div class="reportContent">
            </div>

        </div>
    </div>

    <!-- 实践创新 -->
    <div id="practice" class="module">
        <p class="plateTitle">实践创新：</p>

        <!-- 班级节假日课程 -->
        <div class="report" id="holidayCourse">
            <div class="zTitle">班级节假日课程：</div>
            <div class="reportContent">
            </div>
        </div>

        <!-- 学生素质拓展课程 -->
        <div class="report" id="elective">
            <div class="zTitle">学生素质拓展课程：</div>
            <ul>
            </ul>
        </div>

        <!-- 社团评分 -->
        <div class="one" id="practiceScore">
            <div class="zTitle">社团评分：<span></span> 分</div>
        </div>

        <!-- 学习或比赛图片 -->
        <div class="imageList" id="practiceGame">
            <div class="zTitle">学习或比赛图片：</div>
            <div class="imgList">
            </div>
        </div>

        <!-- 个人奖状 -->
        <div class="imageList" id="practiceCertificate">
            <div class="zTitle">个人奖状：</div>
            <div class="imgList">
            </div>
        </div>

        <!-- 科创相关图书借阅 -->
        <div class="one" id="practiceBook">
            <div class="zTitle">科创相关图书借阅：<span></span> 本</div>
        </div>

        <!-- 学习评语 -->
        <div class="report" id="practiceRemark">
            <div class="zTitle">学习评语：</div>
            <div class="reportContent">
            </div>
        </div>

    </div>

    <!-- 艺术审美 -->
    <div id="art" class="module">
        <p class="plateTitle">艺术审美：</p>

        <!-- 个人优秀美术作品 -->
        <div class="imageList" id="artWork">
            <div class="zTitle">个人优秀美术作品：</div>
            <div class="imgList">
            </div>
        </div>

        <!-- 作品点评 -->
        <div class="report" id="artReview">
            <div class="zTitle">作品点评：</div>
            <div class="reportContent">
            </div>
        </div>

        <!-- 社团评分 -->
        <div class="one" id="artScore">
            <div class="zTitle">社团评分：<span></span> 分</div>
        </div>

        <!-- 学习或比赛图片 -->
        <div class="imageList" id="artGame">
            <div class="zTitle">学习或比赛图片：</div>
            <div class="imgList">
            </div>
        </div>

        <!-- 个人奖状 -->
        <div class="imageList" id="artCertificate">
            <div class="zTitle">个人奖状：</div>
            <div class="imgList">
            </div>
        </div>

        <!-- 学习评语 -->
        <div class="report" id="artRemark">
            <div class="zTitle">学习评语：</div>
            <div class="reportContent">
            </div>
        </div>

    </div>

</div>



<!-- 品格养成 -->
<script type="text/javascript">
    //全局变量
    var studentObje=[];
    //初始化页面
    $(function () {
        //--------------------------选择条件------------------------
        initSelect();
    })

    function initSelect() {
        //初始填充学年、学期、年级
        addOption('/teach/schoolYear/list/json', "xn", "year", "name", function (d) {
            if (d.length > 0) {
                addOption('/teach/schoolTerm/list/json?schoolYear='+d[0].year, "xq", "code", "name");
                //因查询年级不需学期，所以不需在学期填充后的回调中执行
                addOption('/teach/grade/list/json?schoolYear=' + d[0].year, "nj", "id", "name")
            }
        })

        //绑定下拉框改变事件
        $("#xn").change(function(){
            $("#nj").html('<option value="">请选择</option>');
            $("#bj").html('<option value="">请选择</option>');
            $("#xq").html('');
            addOption('/teach/schoolTerm/list/json?schoolYear='+$(this).val(), "xq", "code", "name");
            //添加年级
            addOption('/teach/grade/list/json?schoolYear='+$(this).val(), "nj", "id", "name")
        })
        $("#nj").change(function(){
            $("#bj").html('<option value="">请选择</option>');
            //添加班级
            if($(this).val()!="") {
                addOption('/teach/team/list/json?enableRole=false&gradeId=' + $(this).val(), "bj", "id", "name")
            }
        })

        $("#bj").change(function(){
            $("#studentName").html('<option value="">请选择</option>');
            //添加班级
            if($(this).val()!="") {
                addOption('/KnowEvaluation/findByStudent?gradeId='+ $("#nj").val()+'&teamId='+$("#bj").val()+'&schoolYear='+$("#xn").val(), "studentName", "id", "name")
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
   //判断条件不为空，才提交
    $("#signupForm").validate({
        submitHandler : function() {
            findByAll();
        },
        rules: {
            nj: {
                required:true,
                minlength: 2
            },
            bj : {
                required : true
            },
            xn : {
                required : true
            },
            xq : {
                required : true
            },
            studentName : {
                required : true
            }
        },
        messages: {
            studentName: {
                required: '请输入姓名'
            },
            nj : {
                required : "请选择年级",
            },
            bj : {
                required : '请选择班级'
            }
        }
    });
   //查询
   function findByAll(){
       var year=$("#xn").val();
       var xq=$("#xq").val();
       var gradeId=$("#nj").val();
       var teamId=$("#bj").val();
       var studentName=$("#studentName").find("option:selected").text();
       var data = {"year":year, "gradeId":gradeId, "teamId":teamId,
           "xq":xq, "studentName":studentName};
       // ------------------------------ 品格养成 ------------------------------
       addCharacter(data);
       zhishidian(1);
       // ------------------------------ 学习习惯 ------------------------------
       addStudy(data);

              // ------------------------------ 学科素养 ------------------------------
              addSubject(data);
             // getSubjectContent(oneId,listStudent);

              // ------------------------------ 体质健康 ------------------------------
              addPhyIndex(data);

              // ------------------------------ 实践创新 ------------------------------
              addPractice(data);

              // ------------------------------ 艺术审美 ------------------------------
              addArt(data);

       chengjifenxi();
    }
</script>
<!-- 品格养成 ok -->
<script type="text/javascript">
    function addCharacter(data) {
        $.ajax({
            url:"/pdf/dowerd/zhibiaoScore?year="+data.year+"&xq="+data.xq+"&gradeId="+data.gradeId+"&teamId="+data.teamId+"&studentName="+data.studentName,
            type: "GET",//方法类型
            cache : false,//
            processData: false,
            contentType: false,
            success: function(d){
                var dd = JSON.parse(d);
                if(dd['stats']==false){
                    $.error("该班级查无此人")
                }else{
                    var pingjia=dd['tupiao'];
                    var chaIndexList = []; //指标列表
                    var chaStudentNum = []; //学生分数
                    var chaTeamAvg = []; //班级平均分
                    var chaGradeAvg = []; //年级平均分
                    // 创建品格养成指标列表
                    var characterIndex = document.getElementById('character').getElementsByClassName('index')[0].getElementsByTagName('ul')[0];
                    characterIndex.innerHTML="";
                    for (var i=0;i<pingjia.length;i++) {
                        var lCha = pingjia[i];
                        var name = lCha['zhibiao'];
                        var student = lCha['zongfen'];
                        var team = lCha['bjSvg'];
                        var grade = lCha['njSvg'];
                        var id = lCha['zhibiaoId'];

                        getIndex(characterIndex, 'characterIndexx', id, name, student, '分');

                        chaIndexList.push(name);
                        chaStudentNum.push(student);
                        chaTeamAvg.push(team);
                        chaGradeAvg.push(grade);

                    }

                    // 创建品格养成条形统计图
                    // 初始化echarts实例
                    var myChart = echarts.init(document.getElementById('characterGraph'));

                    // 使用指定的配置项和数据显示图表。
                    myChart.setOption({
                        "color": [
                            "#4f81bd",
                            "#c0504d",
                            "#9bbb59",
                            "#ffc15e",
                            "#3dc1d3",
                            "#574b90"
                        ],
                        tooltip: {
                            trigger: 'item'
                        },
                        legend: {},
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: {
                            type: 'value',
                            boundaryGap: [0, 0.8]
                        },
                        yAxis: {
                            type: 'category',
                            data: chaIndexList
                        },
                        series: [{
                            name: '个人得分',
                            type: 'bar',
                            data: chaStudentNum
                        }, {
                            name: '班级平均分',
                            type: 'bar',
                            data: chaTeamAvg
                        }, {
                            name: '年级平均分',
                            type: 'bar',
                            data: chaGradeAvg
                        },]
                    });

                    // 创建评价记录
                    var table = document.getElementById('tableIdTbo');
                    table.innerHTML="";
                    var  characterRecord=dd['pingjia'];
                    studentObje=dd['pingjia'];
                    for (var iChaR in characterRecord) {
                        var t = characterRecord[iChaR];
                        var evName = t.evName;
                        var teacher = t.teaName;
                        var score = t.score;
                        var message = t.message;
                        var createTime = t.createTime;
                        var id = table.getElementsByTagName('tr').length;

                        // 创建记录
                        var tr = document.createElement('tr');
                        tr.setAttribute('id', 'characterRecord_' + id);
                        table.appendChild(tr);

                        var otr = document.getElementById('characterRecord_' + id);
                        for (var d = 0; d < 5; d++) {
                            var td = document.createElement('td');
                            otr.appendChild(td);
                        }

                        // 写入记录信息
                        otr.getElementsByTagName('td')[0].innerHTML = evName;
                        otr.getElementsByTagName('td')[1].innerHTML = teacher;
                        otr.getElementsByTagName('td')[2].innerHTML = score;
                        otr.getElementsByTagName('td')[3].innerHTML = message;
                        otr.getElementsByTagName('td')[4].innerHTML = createTime;

                    }

                }


            }
        });
    }

</script>
<%--知识点--%>
<script type="text/javascript">
    function zhishidian(subjectId) {
        Array.from(document.getElementsByClassName('zhishidian')[0].getElementsByTagName('ul')[0].children).forEach(function(item) { item.setAttribute('style', ''); })
        document.getElementById('li_' + subjectId).setAttribute('style', 'color: #fff;border-color: #999;background-color: #0d7bd5;box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);');
        // 创建评价记录
        var table = document.getElementById('tableZhiShi');
        table.innerHTML="";
        var gradeId=$("#nj").val();
        var studentId=$("#studentName").val();
        $.get("/KnowEvaluation/findByZhiShiPdf?gradeId="+gradeId+"&subjectId="+subjectId+"&studentId="+studentId,function (d) {
            var  characterRecord=JSON.parse(d);
             //循环课本
            for (var a=0;a<characterRecord.length;a++) {
                var cc=0;
                var t = characterRecord[a];

                var evName = t.bookName;
                var knowEvalution = t.list;
                //循环单元
                for (var b = 0; b < knowEvalution.length; b++) {
                    var obj = knowEvalution[b];
                    //循环知识点
                    var  dd=0;
                    for (var c = 0; c < obj.knowEvaluationList.length; c++) {
                        var obj2 = obj.knowEvaluationList[c];
                        // 创建记录
                        var tr = "<tr id='knowEvaluationList_" + obj.knowEvaluationList[c].knowMenuId+"'>";
                        if(b===0){
                            if(cc===0) {
                              tr+="<td rowspan='"+t.hangNumber+"'>"+evName+"</td>";
                               cc++;
                            }
                        }
                        if(c===0){
                            if(dd===0) {
                                tr+="<td rowspan='"+obj.knowEvaluationList.length+"'>"+obj.knowMenuName+"</td>";
                                dd++;
                            }
                        }
                        tr+="<td>"+obj2.knowMenuName+"</td>";
                        if(obj2.pingfen!=null){
                            if(obj2.pingfen<60){
                            tr+="<td style='background:red;'></td>";
                            }
                            if(obj2.pingfen>=60 && 80>=obj2.pingfen){
                                tr+="<td style='background:yellow;'></td>";
                            }
                            if(obj2.pingfen>80){
                                tr+="<td style='background:green;'></td>";
                            }
                        }else{
                            tr+="<td></td>";
                        }
                        if(obj2.pingyu!=null){
                            tr+="<td>"+obj2.pingyu+"</td></tr>";
                        }else{
                            tr+="<td></td></tr>";
                        }
                        $("#tableZhiShi").append(tr);
                    }
                }
            }
        })

    }
</script>
<%--成绩分析--%>
<script type="text/javascript">
    function chengjifenxi() {
       // 创建记录
        var table = document.getElementById('tableScore');
        table.innerHTML="";
        var gradeId=$("#nj").val();
        var studentId=$("#studentName").val();
        var year=$("#xn").val();
        var trem=$("#xq").val();
        var teamId=$("#bj").val();
        var str="";
        $.get("/pdf/dowerd/chnegjifenxi?gradeId="+gradeId+"&teamId="+teamId+"&studentId="+studentId+"&year="+year+"&xq="+trem,function (d) {
            var dd=JSON.parse(d);
            for(var i=0;i<dd.length;i++){
                str+="<tr>" +
                    "<td>"+dd[i].qizhong+"</td>" +
                    "<td>"+dd[i].qimo+"</td></tr>";
            }
            $("#tableScore").append(str);
        })
    }
</script>

<!-- 学习习惯 ok -->
<script>
    function addStudy(data) {
        $.ajax({
            url:"/pdf/dowerd/studyScore?year="+data.year+"&xq="+data.xq+"&gradeId="+data.gradeId+"&teamId="+data.teamId+"&studentName="+data.studentName,
            type: "GET",//方法类型
            cache : false,//
            processData: false,
            contentType: false,
            success: function(d){
                var dd = JSON.parse(d);
                var indexList = ['课堂纪律', '学习态度', '倾听应答', '质疑点评', '实践思考', '合作思考'];
                var stuStudentNum = []; //学生分数
                var stuTeamAvg = []; //班级平均分
                var stuGradeAvg = []; //年级平均分
                for (var i=0;i<dd.length;i++) {
                    var obj=dd[i];
                    stuStudentNum.push(obj['studentScore']);
                    stuTeamAvg.push(obj['teamSvgScore']);
                    stuGradeAvg.push(obj['gradeSvgScore']);
                }

                // 创建学习习惯指标列表
                var studyIndex = document.getElementById('study').getElementsByClassName('index')[0].getElementsByTagName('ul')[0];
                studyIndex.innerHTML="";
                for (var iStu in indexList) {
                    var name = indexList[iStu];
                    var value = stuStudentNum[iStu];

                    getIndex(studyIndex, 'studyIndex', iStu, name, value, '分');

                }

                // 创建学习习惯条形统计图
                // 初始化echarts实例
                var myChart = echarts.init(document.getElementById('studyGraph'));

                // 使用指定的配置项和数据显示图表。
                myChart.setOption({
                    "color": [
                        "#4f81bd",
                        "#c0504d",
                        "#9bbb59",
                        "#ffc15e",
                        "#3dc1d3",
                        "#574b90"
                    ],
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {},
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        boundaryGap: [0, 1.2]
                    },
                    yAxis: {
                        type: 'category',
                        data: indexList
                    },
                    series: [{
                        name: '个人得分',
                        type: 'bar',
                        data: stuStudentNum
                    }, {
                        name: '班级平均分',
                        type: 'bar',
                        data: stuTeamAvg
                    }, {
                        name: '年级平均分',
                        type: 'bar',
                        data: stuGradeAvg
                    }]
                });

            }
        })
    }

</script>

<!-- 学科素养 -->
<script>

    // 学科素养实现流程 有点乱(ˉ▽ˉ；)...
    // 1.将【/applets/literacy/subject/subjectObj】返回值作为参数传入【subjectList()】;
    // 2.在【subjectList()】中的循环内请求【/applets/literacy/subject/subStudentSuyang】获取对应学科的返回值;
    // 3.将处理好的对象传入全局变量【xuekesuyang】；
    // 4.在【addSubject()】中获取全局变量【xuekesuyang】；
    // 5.在【addSubject()】的循环中判断该学科是否存在学科素养指标。有，则显示该指标按钮；无，则不显示；
    // 6.点击对应学科的按钮，执行【getSubjectContent()】，将该学科id传入，生成报告内容。
    // 7.将第一个学科按钮的id传入全局变量【oneId】，默认显示第一个学科的学科素养报告；

    var oneId = 0; // 全局变量，第一个学科按钮的id
    var listStudent=[]; // 全局变量，第一个学科按钮的id

    function addSubject(data) {
        $.ajax({
            url: "/pdf/dowerd/subjectKu?year="+data.year+"&xq="+data.xq+"&gradeId="+data.gradeId+"&teamId="+data.teamId+"&studentName="+data.studentName,
            type: "GET",//方法类型
            cache: false,//
            processData: false,
            contentType: false,
            success: function (d) {
                var dd = JSON.parse(d)
                var subBar = document.getElementsByClassName('subjectBar')[0].getElementsByTagName('ul')[0];
                subBar.innerHTML="";
                for(var i=0;i<dd.length;i++){
                    var sub = dd[i];
                        var name = sub['name'];
                    if(name==null){

                    }else{
                        var subjectId = sub['subjectId'];
                        var li = document.createElement('li');
                        li.setAttribute('class', 'subject');
                        li.setAttribute('id', 'subjectId_' + i);
                        var wvbwa=JSON.stringify(data);
                        li.setAttribute('onclick', "getSubjectContent('" + subjectId + "','"+wvbwa+"',"+i+")");
                        // 创建学科素养科目内容
                        subBar.appendChild(li);
                        /*  if (oneId == '') { // 获取第一个学科id
                              oneId = i;
                          }*/
                        document.getElementById('subjectId_' + i).innerHTML = name;
                    }
                    }
                var wvbw=JSON.stringify(data);
                getSubjectContent(dd[0]['subjectId'],wvbw,0);
                }
        })
    }

</script>



<!-- 创建学科素养模块内容 -->
<script>

    // 传入学科素养列表[id]
    // 根据对应学科的指标生产模块内容
    function getSubjectContent(ids,ddss,iddd) {
        var dd=JSON.parse(ddss)
        $.ajax({
            url: "/pdf/dowerd/subjectScore?year="+dd.year+"&xq="+dd.xq+"&gradeId="+dd.gradeId+"&teamId="+dd.teamId+"&studentName="+dd.studentName+"&subjectId="+ids,
            type: "GET",//方法类型
            cache: false,//
            processData: false,
            contentType: false,
            success: function (d) {
                var vv = JSON.parse(d);
                Array.from(document.getElementsByClassName('subjectBar')[0].getElementsByTagName('ul')[0].children).forEach(function(item) {
                    item.setAttribute('style', '');
                })
                document.getElementById('subjectId_' + iddd).setAttribute('style', 'color: #fff;border-color: #999;background-color: #0d7bd5;box-shadow: 0 3px 4px 0 rgba(0, 0, 0, 0.24), 0 4px 12px 0 rgba(0, 0, 0, 0.19);');

                var subStuList = [];
                var subTeamList = [];
                var subGradeList = [];
                // 获取各项指标分数
                for (var i=0;i<vv.length;i++) {
                    var subStuListObj = {};
                    var subTeamListObj = {};
                    var subGradeListObj = {};
                    var obj=vv[i];
                    subStuListObj.literacyName=obj["studentlist"].literacyName;
                    subStuListObj.fenshu=obj["studentlist"].fenshu;
                    subTeamListObj.literacyName=obj["teamlist"].literacyName;
                    subTeamListObj.fenshu=obj["teamlist"].fenshu;
                    subGradeListObj.literacyName=obj["gradelist"].literacyName;
                    subGradeListObj.fenshu=obj["gradelist"].fenshu;
                    subStuList.push(subStuListObj);
                    subTeamList.push(subTeamListObj);
                    subGradeList.push(subGradeListObj);
                }

                // 创建品格养成指标列表
                var subjectIndex = document.getElementById('subject').getElementsByClassName('index')[0].getElementsByTagName('ul')[0];
                subjectIndex.innerHTML="";
                var id = 0;
                for (var i=0;i<subStuList.length;i++) {
                    getIndex(subjectIndex, 'subjectIndex', id, subStuList[i].literacyName, subStuList[i].fenshu, '分');
                    id++
                }

                var grapgPolarIndicator = [];
                var grapgPolarValue = [];
                var chartSeriesXAxis = [];
                var chartSeriesTeam = [];
                var chartSeriesGrade = [];
                var zhibiao=[];
                for (var t=0;t<subStuList.length;t++) {
                    var ind = {};
                    ind.text = subStuList[t].literacyName;
                    ind.max = 100;
                    grapgPolarIndicator.push(ind);
                    grapgPolarValue.push(subStuList[t].fenshu);
                    chartSeriesXAxis.push(t);
                    chartSeriesTeam.push(subTeamList[t].fenshu);
                    chartSeriesGrade.push(subGradeList[t].fenshu);
                    zhibiao.push(subStuList[t].literacyName)
                }

                // 学科素养雷达图
                // 基于准备好的dom，初始化echarts实例
                var subjectGraph = echarts.init(document.getElementById('subjectGraph'));

                // 使用指定的配置项和数据显示图表。
                subjectGraph.setOption({
                    "color": [
                        "#4f81bd",
                        "#c0504d",
                        "#9bbb59",
                        "#ffc15e",
                        "#3dc1d3",
                        "#574b90"
                    ],
                    tooltip: {
                        trigger: 'item'
                    },
                    polar: [{
                        indicator: grapgPolarIndicator
                    }],
                    series: [{
                        type: 'radar',
                        data: [{
                            value: grapgPolarValue,
                            name: '个人得分'
                        }]
                    }]
                });

                // 学科素养柱状图
                // 基于准备好的dom，初始化echarts实例
                var subjectChart = echarts.init(document.getElementById('subjectChart'));

                // 使用刚指定的配置项和数据显示图表。
                subjectChart.setOption({
                    "color": [
                        "#4f81bd",
                        "#c0504d",
                        "#9bbb59",
                        "#ffc15e",
                        "#3dc1d3",
                        "#574b90"
                    ],
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        data: ['个人得分', '班级平均分', '年级平均分']
                    },
                    xAxis: {
                        data: zhibiao
                    },
                    yAxis: {},
                    series: [{
                        name: '个人得分',
                        type: 'bar',
                        data: grapgPolarValue
                    }, {
                        name: '班级平均分',
                        type: 'bar',
                        data: chartSeriesTeam
                    }, {
                        name: '年级平均分',
                        type: 'bar',
                        data: chartSeriesGrade
                    },]
                });



            }
        })

    }

</script>

<!-- 体质健康 ok-->
<script>
    function addPhyIndex(data) {
        $.ajax({
            url:"/pdf/dowerd/tizhiScore?year="+data.year+"&xq="+data.xq+"&gradeId="+data.gradeId+"&teamId="+data.teamId+"&studentName="+data.studentName,
            type: "GET",//方法类型
            cache : false,//
            processData: false,
            contentType: false,
            success: function(d) {
                var dd = JSON.parse(d);
                var phyIndex = dd.indicatorsStudents; //体测指标得分列表

                // 创建体测指标列表
                var physicalIndex = document.getElementById('physical').getElementsByClassName('index')[0].getElementsByTagName('ul')[0];
                physicalIndex.innerHTML="";
                for (var iPhy in phyIndex) {
                    var name = phyIndex[iPhy].indicatorsName;
                    var score = phyIndex[iPhy].score;
                    var dw = phyIndex[iPhy].danwei;

                    getIndex(physicalIndex, 'physicalIndex', iPhy, name, score, dw);

                }

                // 请假数据
                document.getElementById('leaveRecord').innerHTML = '本学期请假' + dd.jiaDay + '次，共' + dd.tianshu + '天。';

                // 体测报告
                document.getElementById('physicalReport').getElementsByClassName('reportContent')[0].innerHTML = dd.cervixReport;
                // 医务室记录
                document.getElementById('clinic').getElementsByClassName('reportContent')[0].innerHTML = dd.yiWu;
                // 体质健康报告
                document.getElementById('healthReport').getElementsByClassName('reportContent')[0].innerHTML = dd.healthReport;
            }
        })
    }

</script>

<!-- 实践创新 完成-->
<script type="text/javascript">
    function addPractice(data) {
        $.ajax({
            url: "/pdf/dowerd/innovationScore?year="+data.year+"&xq="+data.xq+"&gradeId="+data.gradeId+"&teamId="+data.teamId+"&studentName="+data.studentName,
            type: "GET",//方法类型
            cache: false,//
            processData: false,
            contentType: false,
            success: function (d) {
                var dd = JSON.parse(d);
                // 班级节假日课程
                document.getElementById('holidayCourse').getElementsByClassName('reportContent')[0].innerHTML = dd.teamJiaDay;

                // 学生素质拓展课程
                var practiceElective = document.getElementById('elective').getElementsByTagName('ul')[0];
                practiceElective.innerHTML="";
                for (var iPra=0;iPra<dd.publicClassList.length;iPra++) {
                    var praClass = dd.publicClassList[iPra];
                    var classCover = praClass.coverUrl; // 课程封图片地址
                    var classTitle = praClass.name;    // 课程标题
                    var classTeacher = praClass.teacgerName;  // 授课教师
                    var classNum = praClass.classNmber; // 课时

                    var li = document.createElement('li');
                    li.setAttribute('id', 'practiceElective_' + (iPra));
                    practiceElective.appendChild(li);

                    var oli = document.getElementById('practiceElective_' + (iPra));
                    // 创建封面div
                    var dCover = document.createElement('div');
                    dCover.setAttribute('class', 'cover');
                    oli.appendChild(dCover);

                    // 创建课程信息div
                    var dMessage = document.createElement('div');
                    dMessage.setAttribute('class', 'message');
                    oli.appendChild(dMessage);

                    // 写入课程封面
                    var cover = oli.getElementsByClassName('cover')[iPra];
                    var coverImg = document.createElement('img');
                    coverImg.setAttribute('src', classCover);
                    cover.appendChild(coverImg);

                    // 写入课程信息
                    var message = oli.getElementsByClassName('message')[iPra];
                    var electiveTitle = document.createElement('div');  // 课程标题
                    electiveTitle.setAttribute('class', 'electiveTitle');
                    var electiveTeacher = document.createElement('div'); // 授课教师
                    electiveTeacher.setAttribute('class', 'electiveTeacher');
                    var electiveHour = document.createElement('div'); // 课时
                    electiveHour.setAttribute('class', 'electiveHour');
                    message.appendChild(electiveTitle);
                    message.appendChild(electiveTeacher);
                    message.appendChild(electiveHour);

                    message.getElementsByClassName('electiveTitle')[iPra].innerHTML = classTitle;
                    message.getElementsByClassName('electiveTeacher')[iPra].innerHTML = classTeacher;
                    message.getElementsByClassName('electiveHour')[iPra].innerHTML = '共 ' + classNum + ' 节课';

                }

                // 社团评分
                document.getElementById('practiceScore').getElementsByTagName('span')[0].innerHTML = dd.score;
                // 学习或比赛图片
                var practiceGame = document.getElementById('practiceGame').getElementsByClassName('imgList')[0];
                practiceGame.innerHTML="";
                imgList(dd.pctreUrl, 'practiceGame', practiceGame);

                // 个人奖状
                var practiceCertificate = document.getElementById('practiceCertificate').getElementsByClassName('imgList')[0];

                imgList(dd.jiangzhuanUrl, 'practiceCertificate', practiceCertificate);
                // 科创相关图书借阅数据
                if (dd.bookNumer == null) {
                    document.getElementById('practiceBook').getElementsByTagName('span')[0].innerHTML = 0;
                } else {
                    document.getElementById('practiceBook').getElementsByTagName('span')[0].innerHTML = dd.bookNumer;
                }
                // 学习评语
                document.getElementById('practiceRemark').getElementsByClassName('reportContent')[0].innerHTML = dd.pingyu;

            }
        })
    }

</script>

<!-- 艺术审美 ok -->
<script>

    function addArt(data) {
        //var art = window.art; // 测试数据
        $.ajax({
            url: "/pdf/dowerd/ArtScore?year="+data.year+"&xq="+data.xq+"&gradeId="+data.gradeId+"&teamId="+data.teamId+"&studentName="+data.studentName,
            type: "GET",//方法类型
            cache: false,//
            processData: false,
            contentType: false,
            success: function (d) {

                var dd = JSON.parse(d);
                // 个人优秀美术作品
                var artWork = document.getElementById('artWork').getElementsByClassName('imgList')[0];

                imgList(dd.fineArtUrlarr, 'artWork', artWork);

                // 作品点评
                document.getElementById('artReview').getElementsByClassName('reportContent')[0].innerHTML = dd.comments;

                // 社团评分
                document.getElementById('artScore').getElementsByTagName('span')[0].innerHTML = dd.reviewSore;
                // 学习或比赛图片
                var artGame = document.getElementById('artGame').getElementsByClassName('imgList')[0];

                imgList(dd.gameWorksUrlarr, 'artGame', artGame);

                // 个人奖状
                var artCertificate = document.getElementById('artCertificate').getElementsByClassName('imgList')[0];

                imgList(dd.jiangzhuanUrlarr, 'artCertificate', artCertificate);

                // 学习评语
                document.getElementById('artRemark').getElementsByClassName('reportContent')[0].innerHTML = dd.review;
            }
        })
    }

</script>
<!-- 创建指标列表项 -->
<script>

    function getIndex(index, className, id, name, value, dw) {
        // index：指标列表元素, classNmae：类型名称， id：指标id，name：指标名称，value：指标得分，dw：指标单位
        // 创建指标列表项
        var li = document.createElement('li');
        li.setAttribute('id', className + id);
        index.appendChild(li);

        var oli = document.getElementById(className + id);

        // 写入指标名
        var span = document.createElement('span');
        span.setAttribute('class', 'indexName');
        oli.appendChild(span);
        oli.getElementsByTagName('span')[0].innerHTML = name;

        // 写入指标分数
        var div = document.createElement('div');
        div.setAttribute('class', 'value');
        oli.appendChild(div);

        var odiv = oli.getElementsByTagName('div')[0];

        var vspan = document.createElement('span');
        odiv.appendChild(vspan);
        var fspan = document.createElement('span');
        fspan.setAttribute('class', 'fen');
        odiv.appendChild(fspan);
        odiv.getElementsByTagName('span')[0].innerHTML = value;
        odiv.getElementsByTagName('span')[1].innerHTML = dw;
    }

</script>

<!-- 创建图片列表 -->
<script>
    function imgList(list, name, listClass) {
        listClass.innerHTML="";
        if(list==null){
            var div = document.createElement('div');
            div.setAttribute('id', name + '_' + i);
            div.setAttribute('class', 'img');
            div.innerHTML="暂无图片";
            listClass.appendChild(div);
        }else {
            for (var i = 0; i < list.length; i++) {
                var iUrl = list[i]; // 图片地址
                // 写入图片
                var div = document.createElement('div');

                div.setAttribute('id', name + '_' + i);
                div.setAttribute('class', 'img');
                listClass.appendChild(div);

                var dImg = document.getElementById(name + '_' + i);
                var img = document.createElement('img');
                img.setAttribute('src', iUrl);
                dImg.appendChild(img);
            }
        }
    }
</script>
<img id="logo" src="${ctp}/images/logo_new.png" style="display: none"/>
<script>
    var downPdf = document.getElementById("export");
    downPdf.onclick = function() {

        html2canvas(document.body, {
            onrendered:function(canvas) {
                /*var contentWidth = canvas.width;
               var contentHeight = canvas.height;

               //一页pdf显示html页面生成的canvas高度;
               var pageHeight = contentWidth / 592.28 * 841.89;
               //未生成pdf的html页面高度
               var leftHeight = contentHeight;
               //pdf页面偏移
               var position = 0;*/
                var pdf = new jsPDF('p', 'mm', 'a4');//A4纸，纵向
                pdf.addFont('NotoSansCJKtc-Regular.ttf', 'NotoSansCJKtc', 'normal');
                pdf.setFont('NotoSansCJKtc');
                var ctx = canvas.getContext('2d'),
                    //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
                    a4w = 190, a4h = 257;
                var imgWidth = 595.28;
                imgHeight = Math.floor(a4h * canvas.width / a4w),//按A4显示比例换算一页图像的像素高度
                    renderedHeight = 0;

                var logo = document.getElementById("logo");//放在页眉的图标
                var aa=0;
                var cc=1;
                while (renderedHeight < canvas.height) {
                    var page = document.createElement("canvas");
                    page.width = canvas.width;
                    page.height = Math.min(imgHeight, canvas.height - renderedHeight);//可能内容不足一页

                    //用getImageData剪裁指定区域，并画到前面创建的canvas对象中
                    page.getContext('2d').putImageData(ctx.getImageData(0, renderedHeight, canvas.width, Math.min(imgHeight, canvas.height - renderedHeight)), 0, 0);
                    //添加图像到页面，保留10mm/20mm边距
                    pdf.addImage(page.toDataURL('image/jpeg', 1.0), 'JPEG', 10, 20, a4w, Math.min(a4h, a4w * page.height / page.width));
                    //添加页眉logo
                    pdf.setFontSize(8);
                    pdf.text(95,285,"香市第一小学");
                    var obj=cc+"页";
                    pdf.text(190,285,obj);
                    if(aa==0){
                        pdf.setFontSize(23);
                        pdf.text(90,10,"成长报告");
                        pdf.addImage(logo, 'PNG', 5, 3);
                    }

                    aa++;
                    cc++;
                    renderedHeight += imgHeight;
                    if (renderedHeight < canvas.height)
                        pdf.addPage();//如果后面还有内容，添加一个空页
                    delete page;
                }
                pdf.save('成长报告.pdf');
            }
        });




             /*   var imgHeight = 592.28/contentWidth * contentHeight;

                var pageData = canvas.toDataURL('image/jpeg', 1.0);
                document.getElementById("divx").hidden=false;
                var logo = document.getElementById("logo");//放在页眉的图标
                var pdf = new jsPDF('p', 'pt', 'a4');
                var options = {
                    pagesplit: true
                };
                pdf.text(50,40,"成长报告");
                //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
                //当内容未超过pdf一页显示的范围，无需分页
             /!*   if (leftHeight < pageHeight) {*!/
                    pdf.addImage(pageData, 'JPEG', 0, 0, imgWidth, imgHeight );
                     pdf.addImage(logo, 'PNG', 5, 3);
                /!*} else {*!/
                 /!*   while(leftHeight > 0) {
                        pdf.addImage(pageData, 'JPEG', 0, position, imgWidth, imgHeight)
                        //添加页眉logo
                        //pdf.addImage("成长报告", 'text', 5, 3);

                        leftHeight -= pageHeight;
                        position -= 841.89;
                        //避免添加空白页
                        if(leftHeight > 0) {
                            pdf.addPage();
                        }
                   // }
                }*!/
                pdf.save('成长报告.pdf');
                document.getElementById("divx").hidden=true;
               /!* $("#tableId").attr("style","display:block;");
                $("#tableId2").attr("style","display:none;");*!/
            }
        })*/

}
// 按白色间隙动态为A4纸分页


</script>

</body>
</html>