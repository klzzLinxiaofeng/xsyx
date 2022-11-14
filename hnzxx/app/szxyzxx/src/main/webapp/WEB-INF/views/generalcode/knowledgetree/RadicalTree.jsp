<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>

        .node circle {
            fill: #999;
        }

        .node text {
            font: 10px sans-serif;
        }

        .node--internal circle {
            fill: #555;
        }

        .node--internal text {
            text-shadow: 0 1px 0 #fff, 0 -1px 0 #fff, 1px 0 0 #fff, -1px 0 0 #fff;
        }

        .link {
            fill: none;
            stroke: #555;
            stroke-opacity: 0.4;
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
</body>

<script src="/res/js/common/d3.js" charset="utf-8"></script>
<script>
    var body_width=$("body").width();
    var width = body_width-150;
    //图像区域大小
    var R = width;
    //动画持续时间
    var duration=1000;
    //节点编号
    var index=0;

    //根据JSON数据生成树
    var root;
    function getdata(){
        var url = "${ctp}/generalcode/knowledgetree/getDataOfTree";
        var $requestData = {};
        $requestData.treeId = ${treeId};
        $.post(url, $requestData, function (data, status) {
            if ("success" === status) {
                data = eval("(" + data + ")");
                root = data;
                createRadical(data);
            }
        });
    }

    $(function(){
        getdata();
    });

    //定义一个Tree对象,定义旋转角度和最大半径
    var tree = d3.layout.tree()
            .size([360,R/2-120])
            .children(function(a) { return (!a.childrens || a.childrens.length === 0)?null: a.childrens; });
    //定义布局方向

    var diagonal = d3.svg.diagonal()
            .projection(function(d) {
                var r = d.y, a = (d.x-90) / 180 * Math.PI;
                return [r * Math.cos(a), r * Math.sin(a)];
            });

    function createRadical(data) {
        //新建画布，移动到圆心位置
        var svg = d3.select("body").append("svg")
                .attr("width", R)
                .attr("height", R)
                .append("g")
                .attr("transform", function(d){ return "translate("+R/2+"," + R/2 + ")";});

        //根据数据生成nodes集合
        var nodes = tree.nodes(data);

        //记录现在的位置
        nodes.forEach(function(d){
            d.x0 = d.x;
            d.y0 = d.y;
        });

        //获取node集合的关系集合
        var links = tree.links(nodes);

        //根据node集合生成节点,添加id是为了区分是否冗余的节点
        var node = svg.selectAll(".node")
                .data(nodes,function(d){return d.id|| (d.id = ++index);});

        //为关系集合设置贝塞尔曲线连接
        var link=svg.selectAll(".link")
                .data(links, function(d) { return d.target.id;})
                .enter()
                .append("path")
                .attr("class", "link")
                .attr("d",diagonal);

        node.enter()
                .append("g")
                .attr("class", "node")
                .attr("transform",function(d){return "rotate(" + (d.x-90) + ")translate(" + d.y + ")"; })
                .on("click",nodeClick);

        node.enter()
                .append("g")
                .attr("class", "node")
                .attr("transform",function(d){return "rotate(" + (d.x-90) + ")translate(" + d.y + ")"; })
                .on("mouseover",getDescribe);

        //为节点添加圆形标记,如果有子节点为红色，否则绿色
        node.append("circle")
                .attr("fill",function(d){return d.children==null?"#0F0":"#F00";})
                .attr("r", 5);

        //为节点添加说明文字
        node.append("text")
                .attr("dy", ".4em")
                .text(function(d){return d.name;})
                .attr("text-anchor", function(d) { return d.x < 180 ? "start" : "end"; })
                .attr("transform", function(d) { return d.x < 180 ? "translate(8)" : "rotate(180)translate(-8)"; });

        //单击节点获取信息
        svg.selectAll("circle").on("click", function(d,i){
            getResourceByKnoledge(d,3);
            getNodeMessage(d);
        })
    }

    function getDescribe(d){
        var describe = d.describe;
        var name = d.name;
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

    //点击的话，隐藏或者显示子节点
    function nodeClick(d)
    {
        if (d.children)
        {
            d._children = d.children;
            d.children = null;
        }
        else
        {
            d.children = d._children;
            d._children = null;
        }
        update(d);
    }

    //更新显示
    function update(source)
    {
        //取得现有的节点数据,因为设置了Children属性，没有Children的节点将被删除
        var nodes = tree.nodes(root).reverse();
        var links = tree.links(nodes);

        //为节点更新数据
        var node = svg.selectAll("g.node")
                .data(nodes,function(d){return d.id|| (d.id = ++index);});

        //为链接更新数据
        var link = svg.selectAll("path.link").data(links, function(d) {return d.target.id;});

        //更新链接
        link.enter()
                .append("path")
                .attr("class", "link")
                .attr("d", function(d) {
                    var o = {x: source.x, y: source.y};
                    return diagonal({source: o, target: o});
                });
        link.transition()
                .duration(duration)
                .attr("d",diagonal);

        //移除无用的链接
        link.exit()
                .transition()
                .duration(duration)
                .attr("d", function(d) {
                    var o = {x: source.x, y: source.y};
                    return diagonal({source: o, target: o});
                })
                .remove();

        //更新节点集合
        var nodeEnter = node.enter()
                .append("g")
                .attr("class", "node")
                .attr("transform",function(d){return "rotate(" + (source.x0-90) + ")translate(" + source.y0 + ")"; })
                .on("click",nodeClick);

        //为节点添加圆形标记,如果有子节点为红色，否则绿色
        node.append("circle")
                .attr("fill",function(d){return d.children==null && d._children==null?"#0F0":"#F00";})
                .attr("r", 5);

        //为节点添加说明文字
        node.append("text")
                .attr("dy", ".4em")
                .text(function(d){
                    return d.name;})
                .attr("text-anchor", function(d) { return d.x < 180 ? "start" : "end"; })
                .attr("transform", function(d) { return d.x < 180 ? "translate(8)" : "rotate(180)translate(-8)"; });

        //节点动画
        var nodeUpdate = node.transition()
                .duration(duration)
                .attr("transform", function(d) { return "rotate(" + (d.x-90) + ")translate(" + d.y + ")"; });

        //将无用的子节点删除
        var nodeExit =node.exit()
                .transition()
                .duration(duration)
                .attr("transform", function(d){return "rotate(" + (source.x-90) + ")translate(" + source.y + ")"; })
                .remove();

        //记录下当前位置,为下次动画记录初始值
        nodes.forEach(function(d) {
            d.x0 = d.x;
            d.y0 = d.y;
        });
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

    //根据知识点获取资源，resourceNumber是需要获取的资源数量，当该值为空时默认获取三条资源
    function getResourceByKnoledge(d,resourceNumber){
        console.log("go");
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

    function loadResource(nodeId) {
        $.ajax({
            type : "post",
            url : "${ctp}/generalcode/knowledgetree/getResourceByKnoledge?nodeId="+nodeId,
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