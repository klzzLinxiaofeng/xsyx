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
            color:#fff;
        }

    </style>
</head>
<body>
<h3>知识点得分率统计</h3>
<svg id="initCircle" width="600" height="600" font-family="sans-serif" font-size="10" text-anchor="middle" style="color:#fff;display:block;margin:0 auto;"></svg>
<div class="zw_ts">暂无数据</div>
</body>
<script>


    $(function(){
        getDateForD3();
    })

    function getDateForD3(){
        $.post("${ctp}/statistic/teachingEvaluationTeamSubject", {"examId":${exam.id}}, function (returnData, status) {
            if ("success" === status) {
                returnData = JSON.parse(returnData);
                var classes = "";
                if(returnData != ""){
                    $.each(returnData,function(n,value) {
                        if(classes == ""){
                            if(typeof(value.percent) == "number" && value.percent > 0){
                                classes = "{\"name\":\""+value.knowledgeName+"\",\"value\":\""+Number(value.percent).toFixed(2)+"\"}";
                            }
                        }else{
                            if(typeof(value.percent) == "number" && value.percent > 0){
                                classes += ",{\"name\":\""+value.knowledgeName+"\",\"value\":\""+Number(value.percent).toFixed(2)+"\"}";
                            }
                        }
                    });

                    if(classes == ""){
                        $("#initCircle").hide();
                    }
                    initCircle(JSON.parse("["+classes+"]"));
                }
            }
        });
    }

    function initCircle(data){

//         var width  = 1200;	//SVG绘制区域的宽度
//         var height = 561;	//SVG绘制区域的高度

        d3.select("#initCircle").selectAll("g").remove();//清空作图区域
        var svg = d3.select("#initCircle"),
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
                    num = 100;
                        var id;
                    if (id = d.data.name) {
                        d.id = id;
                        d.class = id;
                        pid = num-(d.value);
                        /*d.colorPick = pid>80?5:(pid>60?4:(pid>40?3:(pid>20?2:1)));*/
                        d.colorPick = pid>90?9:(pid>80?8:(pid>70?7:(pid>60?6:(pid>50?5:(pid>40?4:(pid>30?3:(pid>20?2:(pid>10?1:(pid>=0?10:1)))))))));
                    }
                });

        var node = svg.selectAll(".node")
                .data(pack(root).leaves())
                .enter().append("g")
                .attr("class", "node")
                .attr("transform", function(d) { return "translate(" + d.x + "," + d.y + ")"; });

        node.append("circle")
                .attr("id", function(d) { return d.id; })
                .attr("r", function(d) { if(isNaN(d.r)){
            		return 2;
            	}
            	return d.r; })
                .style("fill", function(d) { return color[d.colorPick]; });

        node.append("clipPath")
                .attr("id", function(d) { return "clip-" + d.id; })
                .append("use")
                .attr("xlink:href", function(d) { return "#" + d.id; });

        node.append("text")
                .attr("clip-path", function(d) { return "url(#clip-" + d.id +'a'+ ")"; })
                .selectAll("tspan")
                .data(function(d) {
                    var arr = new Array();
                    arr.push(d.class);
                    arr.push(d.data.value +"%");
                    return arr;//圆内显示内容
                })
                .enter().append("tspan")
                .attr("x", 0)
                .attr("y", function(d, i, nodes) { return 25 + (i - nodes.length / 2 - 0.5) * 20; })
                .text(function(d) { return d; });

        //node.append("title").text(function(d) { return d.id + "\n" + format(d.value) +"%"; });
        node.append("title").text(function(d) { return d.id + "\n" + d.value +"%"; });
        node.on("click",function(d){
            clickBubble(d.id);//自定义点击事件
        })
    }
</script>

</html>
