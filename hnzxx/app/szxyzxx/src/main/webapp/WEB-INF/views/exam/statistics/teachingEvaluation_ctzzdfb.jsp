<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<html>
<head>
    <title>Title</title>
    <style>
        .bubble circle{
            stroke: black;
            stroke-width: 2px;
        }

        .bubble text{
            fill: black;
            font-size: 14px;
            font-family: arial;
            text-anchor: middle;
        }

    </style>
</head>
<body>
<h3>错题知识点分布统计</h3>
<svg id="initCircle1" width="600" height="600" font-family="sans-serif" font-size="10" text-anchor="middle" style="margin:0 auto;display:block;"></svg>
<div class="zw_ts">暂无数据</div>
</body>
<script>
    $(function(){
        getDateForD31();
    })

    function getDateForD31(){
        $.post("${ctp}/statistic/teachingEvaluationTeamSubject", {"examId":${exam.id}}, function (returnData, status) {
            if ("success" === status) {
                    returnData = JSON.parse(returnData);
                    var classes = "";
                if(returnData != ""){
                    $.each(returnData,function(n,value) {
                        if(typeof(value.percent) == "number"){
                            var p = Number(100- (value.percent)).toFixed(2);
                            if(p > 0){
                                if(classes == ""){
                                    classes = "{\"name\":\""+value.knowledgeName+"\",\"value\":\""+(p)+"\"}";
                                }else{
                                    classes += ",{\"name\":\""+value.knowledgeName+"\",\"value\":\""+(p)+"\"}";
                                }
                            }
                        }
                    });
                    if(classes == ""){
                        $("#initCircle1").hide();
                    }
                    initCircle1(JSON.parse("["+classes+"]"));
                }
            }
        });
    }

    function initCircle1(data){

//         var width  = 380;  //SVG绘制区域的宽度
//         var height = 461;  //SVG绘制区域的高度

        d3.select("#initCircle1").selectAll("g").remove();//清空作图区域
        var svg = d3.select("#initCircle1"),
                width = +svg.attr("width"),
                height = +svg.attr("height");
        var format = d3.format(",d");

//       var color = d3.scaleOrdinal(d3.schemeCategory20c);
        var color = ["#51c23a","#8e80e2","#ee4e46","#ffbb33","#4bb1fa","#3ac982","#2299ee","#48618d","#f98a24",'#206379','#EB624D'];//自定义颜色
        var pack = d3.pack()
                .size([width, height])
                .padding(1.5);

        var num,pid;

        var root = d3.hierarchy({children: data})
                .sum(function(d) { return d.value; })
                .each(function(d) {
                    if(d.parent == null) {num = d.value};
                    num=100;
                        var id;
                    if (id = d.data.name) {
                        d.id = id;
                        d.class = id;
                        pid = num-(d.value);
                        d.colorPick = pid>90?9:(pid>80?8:(pid>70?7:(pid>60?6:(pid>50?5:(pid>40?4:(pid>30?3:(pid>20?2:(pid>10?1:(pid>=0?10:1)))))))));
                    }
                });
        
        var node = svg.selectAll(".node")
                .data(pack(root).leaves())
                .enter().append("g")
                .attr("class", "node")
                .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });
        

        node.append("circle")
                .attr("id", function(d) { return d.id+'a'; })
                .attr("r", function(d) { 
                  if(typeof(d.r) == "number"){
                    return d.r;
                  }
                  return 0;
                  })
                .style("fill", function(d) { return color[d.colorPick]; });

        node.append("clipPath")
                .attr("id", function(d) { return "clipa-" + d.id; })
                .append("use")
                .attr("xlink:href", function(d) { return "#" + d.id+'a'; });

        node.append("text")
                .attr("clip-path", function(d) { return "url(#clip-" + d.id +'a'+ ")"; })
                .selectAll("tspan")
                .data(function(d) {
                    var arr = new Array();
                    arr.push(d.class.substring(0, d.r / 10));
                    if( d.r >20){
                      
                    arr.push(d.data.value +"%");
                    }
                    return arr;//圆内显示内容
                })
                .enter().append("tspan")
                .attr("x", 0)
                .style("color", "#fff")                                            //设置文本颜色 
            //.text(function(d) { return d.className.substring(0, d.r / 3); })         //根据半径的大小来截取对应长度字符串（很重要）  
                .attr("y", function(d, i, nodes) { 
                  var len = 2;
                  if(nodes.length != null){
                    len = nodes.length;
                  }
                  
                  return 25 + (i - len / 2 - 0.5) * 20; })
                .text(function(d) { return d; });

        
        
       // node.append("title").text(function(d) { return d.id + "\n" + format(d.value) +"%"; });
        node.append("title").text(function(d) { return d.id + "\n" + d.value +"%"; });
        node.on("click",function(d){
            clickBubble(d.id);//自定义点击事件
        })
    }
</script>

</html>
