<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 75%;
        }

        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
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
            border-bottom: 1px solid #ddd;
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
        svg{margin:0 40px;border:1px solid #ddd;}
    </style>
</head>
<body style="background-color: ffffff !important">
<%--统计框的引入--%>
<jsp:include page="./knowledgeCountMess.jsp" />
</body>

<style>
    .table th, .table td{text-align: center}
</style>
<script src="/res/js/common/d3.js" charset="utf-8"></script>
<script>
    var body_width=$("body").width()-245;
    var h = document.documentElement.clientHeight-256;
    $(function(){
        getdata();
    })

    function transformEdges(nodes,edges){
        var json = "";
        var bool1 = false;
        var bool2 = false;
        for(var i = 0; i < edges.length; i++){
            var begin = 0;
            var end = 0;
            for(var j = 0; j < nodes.length; j++){
                if(nodes[j].id == edges[i].source){
                    begin = j;
                    bool1 = true;
                }
                if(nodes[j].id == edges[i].target){
                    end = j;
                    bool2 = true;
                }
                if(bool1 && bool2){
                    //break;
                }
            }
            if(json == ""){
                json = "[{source:" + begin + ",target:" + end + "}";
            }else{
                json += ",{source:" + begin + ",target:" + end + "}";
            }
        }
        json = json + "]";
        return eval("(" + json + ")");
    }

    function create(data){
        var nodes = data.nodes;
        var edgesData = data.links;

        var edges = transformEdges(nodes,edgesData);

        var width = body_width;
        var height = h;

        var svg = d3.select("body")
                .append("svg")
                .attr("width",width)
                .attr("height",height);

        var force = d3.layout.force().nodes(nodes).links(edges).size([width,height]).linkDistance(150).charge(-400);

        force.start();

        //添加连线
        var svg_edges = svg.selectAll("line")
                .data(edges)
                .enter()
                .append("line")
                .style("stroke","#55f")
                .style("stroke-width",1);

        var color = d3.scale.category20();

        //添加节点
        var svg_nodes = svg.selectAll("circle")
                .data(nodes)
                .enter()
                .append("circle")
                .attr("r",20)
                .style("fill",function(d,i){
                    return color(i);
                })
                .call(force.drag);	//使得节点能够拖动

        //添加描述节点的文字
        var svg_texts = svg.selectAll("text")
                .data(nodes)
                .enter()
                .append("text")
                .style("fill", "black")
                .attr("dx", 20)
                .attr("dy", 8)
                .text(function(d){
                    return d.name;
                });


        force.on("tick", function(){	//对于每一个时间间隔

            //更新连线坐标
            svg_edges.attr("x1",function(d){ return d.source.x; })
                    .attr("y1",function(d){ return d.source.y; })
                    .attr("x2",function(d){ return d.target.x; })
                    .attr("y2",function(d){ return d.target.y; });

            //更新节点坐标
            svg_nodes.attr("cx",function(d){ return d.x; })
                    .attr("cy",function(d){ return d.y; });

            //更新文字坐标
            svg_texts.attr("x", function(d){ return d.x; })
                    .attr("y", function(d){ return d.y; });
        });

        //单击节点获取信息
        svg.selectAll("circle").on("click", function(d,i){
            getNodeMessage(d,i);
            getResourceByKnoledge(d,3);
        })

        //鼠标移过显示节点信息
        svg.selectAll("circle").on("mouseover",function(d){
            getDescribe(d);
        })
    }

    function getDescribe(d){
        var describe = d.describe;
        if(describe == "" || describe == null){
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

    function getdata(){
        var url = "${ctp}/generalcode/knowledgetree/getDataOfJSON";
        var $requestData = {};
        $requestData.treeId = ${treeId};
        $.post(url, $requestData, function (data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                create(data);
            }
        });
    }

    function getNodeMessage(d,i){
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
</script>
</html>
