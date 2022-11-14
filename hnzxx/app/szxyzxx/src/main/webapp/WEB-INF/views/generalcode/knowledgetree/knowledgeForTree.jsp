<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>

        .node circle {
            fill: #fff;
            stroke: steelblue;
            stroke-width: 1.5px;
        }

        .node {
            font: 12px sans-serif;
        }

        .link {
            fill: none;
            stroke: #ccc;
            stroke-width: 1.5px;
        }
        .cr_style{
            border:1px solid #666;
            width:130px;
            position:absolute;
            right:0;
            padding: 5px 5px 0;
            top:20px;
        }
        .cr_style p{
            line-height:25px;
            text-align: center;
            border-bottom: 1px solid #666;
            margin-bottom: 5px;
        }
        .cr_style ul li{
            float: left;
            margin-right: 10px;
            margin-bottom: 10px;
        }
        .cr_style ul li span{
            color:#ff0000;
        }
    </style>
</head>

<body style="background-color: ffffff !important;padding-left:20px;">
<jsp:include page="./knowledgeCountMess.jsp" />
<svg width="1000" height="1600"></svg>
</body>
<script src="/res/js/common/d3.js" charset="utf-8"></script>
<script>
    var body_width=$("body").width();
    var width = body_width-500;
    var height = 1600;

    $(function(){
        getdata();
    });

    function create(root){
        var tree = d3.layout.tree()
                .size([height, width])
                .children(function(a) { return (!a.childrens || a.childrens.length === 0)?null: a.childrens; });

        var diagonal = d3.svg.diagonal()
                .projection(function(d) { return [d.y, d.x]; });

        var svg = d3.select("svg")
                .append("g")
                .attr("transform", "translate(60,0)");

            var nodes = tree.nodes(root);
            var links = tree.links(nodes);

            var link = svg.selectAll(".link")
                    .data(links)
                    .enter()
                    .append("path")
                    .attr("class", "link")
                    .attr("d", diagonal);

            var node = svg.selectAll(".node")
                    .data(nodes)
                    .enter()
                    .append("g")
                    .attr("class", "node")
                    .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; })

            node.append("circle")
                    .attr("r", 4.5);

            node.append("text")
                    .attr("dx", function(d) { return d.children ? -8 : 8; })
                    .attr("dy", 3)
                    .style("text-anchor", function(d) { return d.children ? "end" : "start"; })
                    .text(function(d) { return d.name; });

            //为每个rect元素绑定click事件
            svg.selectAll(".node")
                .on("click", function(d,i){
                    getNodeMessage(d);
                    getResourceByKnoledge(d,3);
                });

        //鼠标移过显示节点信息
        svg.selectAll(".node").on("mouseover",function(d){
            getDescribe(d);
        })

        nodes.forEach(function(d) {
            d.y = d.depth*0.1;  // 线条长度，也是作于方向
            d.x = d.depth * 63;
        });
    }

    function getDescribe(d){
        var describe = d.describe;
        if($.trim(describe) == "" || describe == "null" || describe == null){
            describe = "暂无相关描述";
        }
        if($.trim(name) == ""){
            name = "知识点描述";
        }else{
            name = d.name + " 知识点描述";
        }
        $("#describeTitle").text(name);
        $("#describeMess").text(describe);
    }

    function getNodeMessage(d){
        var url = "${ctp}/generalcode/knowledgetree/getNodeMessage";
        var $requestData = {};
        $requestData.nodeId = d.id;
        $.post(url, $requestData, function (data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                createCount(data, d);
            }
        });
    }

    //修改成只显示总数
    function createCount(data,d){
//        $("#micro").text(data.MICRO_LESSON);
//        $("#design").text(data.LEARNING_DESIGN);
//        $("#homework").text(data.HOMEWORK);
//        $("#exam").text(data.EXAM);
//        $("#plan").text(data.TEACHING_PLAN);
//        $("#material").text(data.MATERIAL);
//        $("#learning").text(data.LEARING_PLAN);
//        $("#other").text(data.OTHER);
        $("#title").text(d.name);
        $("#msgNodeId").val(d.id);
//        var allCount = 0;
//        if(typeof(data.MICRO_LESSON) == "number"){
//            allCount += data.MICRO_LESSON;
//        }
//        if(typeof(data.LEARNING_DESIGN) == "number"){
//            allCount += data.LEARNING_DESIGN;
//        }
//        if(typeof(data.HOMEWORK) == "number"){
//            allCount += data.HOMEWORK;
//        }
//        if(typeof(data.EXAM) == "number"){
//            allCount += data.EXAM;
//        }
//        if(typeof(data.TEACHING_PLAN) == "number"){
//            allCount += data.TEACHING_PLAN;
//        }
//        if(typeof(data.MATERIAL) == "number"){
//            allCount += data.MATERIAL;
//        }
//        if(typeof(data.LEARING_PLAN) == "number"){
//            allCount += data.LEARING_PLAN;
//        }
//        if(typeof(data.OTHER) == "number"){
//            allCount += data.OTHER;
//        }
//        $("#allCount").text(allCount);
        $("#allCount").text(data.ALL);
    }

    function getdata(){
        var url = "${ctp}/generalcode/knowledgetree/getDataOfTree";
        var $requestData = {};
        $requestData.treeId = ${treeId};
        $.post(url, $requestData, function (data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                create(data);
            }
        });
    }

    //根据知识点获取资源，resourceNumber是需要获取的资源数量，当该值为空时默认获取三条资源
    function getResourceByKnoledge(d,resourceNumber){
        if(resourceNumber == "" || resourceNumber == "undefined"){
            resourceNumber = 3;
        }
        $.ajax({
            type : "post",
            url : "${ctp}/generalcode/knowledgetree/getResourceByKnoledge?nodeId="+ d.id+"&resourceNumber="+resourceNumber,
            dataType : "html",
            success : function(data) {
                $("#zylb").empty();
                $("#zylb").append(data);
            }
        });
    }

</script>
</html>