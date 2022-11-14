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
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>分值设置</title>
    <style>
        .puting,.jixiang{
            margin-top: 25px;
        }
        .puting input,.jixiang input{
            float:right;
        }


    </style>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    分值设定
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                    </p>
                    <h3 class="x-head content-top">
                    </h3>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div>
                <div class="left" style="float: left;width: 40%;border-right: 2px solid black">
                    <div class="left1"  style="height: 40%;margin-left:30px;margin-top: 50px;margin-right: 30px">
                        <span style="font-size: 24px;margin-top: 15px">级别分值</span>
                        <div class="left1" id="left1" style="height: 50%;">
                        </div>

                    </div>

                    <div class="left2"  style="height: 50%;margin-left:30px;margin-top: 10px;margin-right: 30px" >
                        <span style="font-size: 24px;margin-top: 15px">奖项分值</span>
                        <div class="left2" id="left2" style="height: 50%;">
                        </div>

                    </div>
                </div>
                <div class="rigth" style="float: left;width: 40%;margin-top: 50px;margin-left: 30px">
                    <span style="font-size: 24px;">绩效对应分值</span>
                    <div id="rigth1" style="height: 40%;">

                    </div>
                    <div id="rigth2" style="height: 50%;margin-top: -5px;margin-left: 30px">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
        $(function () {
            chaxun();
        })
        function chaxun() {
            $.get("/jixiao/findByAll",function (d) {
                var objList=JSON.parse(d);
                for(var i=0 ;i<objList.length;i++){
                    if(objList[i]['jibie']==1){
                        var div="<div class='puting'><span class='span' style='font-size: 18px'>"+objList[i]['name']+"</span><input id='pu_"+objList[i]['id']+"' value='"+objList[i]['putongscore']+"'/></div>";
                        $("#left1").append(div);
                        $("#pu_"+objList[i]['id']).attr("onblur","xiugai('"+objList[i]['id']+"','pu_"+objList[i]['id']+"',1)");
                        var div2="<div class='jixiang'><span class='span' style='font-size: 18px'>"+objList[i]['name']+"</span><input id='ji_"+objList[i]['id']+"' value='"+objList[i]['jixiaoScore']+"'/></div>";
                        $("#rigth1").append(div2);
                        $("#ji_"+objList[i]['id']).attr("onblur","xiugai('"+objList[i]['id']+"','ji_"+objList[i]['id']+"',2)");

                    }if(objList[i]['jibie']==2){
                        var div="<div class='puting'><span class='span' style='font-size: 18px'>"+objList[i]['name']+"</span><input id='pu_"+objList[i]['id']+"' value='"+objList[i]['putongscore']+"'/></div>";
                        $("#left2").append(div);
                        $("#pu_"+objList[i]['id']).attr("onblur","xiugai('"+objList[i]['id']+"','pu_"+objList[i]['id']+"',1)");
                        var div2="<div class='jixiang'><span class='span' style='font-size: 18px'>"+objList[i]['name']+"</span><input id='ji_"+objList[i]['id']+"' value='"+objList[i]['jixiaoScore']+"'/></div>";
                        $("#rigth2").append(div2);
                        $("#ji_"+objList[i]['id']).attr("onblur","xiugai('"+objList[i]['id']+"','ji_"+objList[i]['id']+"',2)");
                    }
                }
            })
        }
        function xiugai(id,idd,leixing) {
            var input =$("#"+idd).val();
            var val={};
            val.id=id;
            if(leixing==1){
                val.putongscore=input;
            }else{
                val.jixiaoScore=input;
            }
            $.post("/jixiao/update",val,function (d) {
                if(d=="success") {
                    $.success("成功")
                }else{
                    $.error("失败")
                }
            })
        }
        function create() {
            var obj=[{
                name:"省",
                jibie:1
                },{
                name:"市",
                jibie:1
            },{
                name:"县",
                jibie:1
            },{
                name:"乡镇",
                jibie:1
            },{
                name:"校",
                jibie:1
            },{
                name:"一等奖",
                jibie:2
            },{
                name:"二等奖",
                jibie:2
            },{
                name:"三等奖",
                jibie:2
            },{
                name:"特等奖",
                jibie:2
            },{
                name:"发表刊物",
                jibie:2
            },{
                name:"结题",
                jibie:2
            },{
                name:"立项",
                jibie:2
            }]
            for(var i=0;i<obj.length;i++){
                $.post("/jixiao/create",obj[i],function (d) {
                    if(d=="sccess") {
                        $.success("成功")
                    }else{
                        $.error("失败")
                    }
                })
            }


        }
</script>

</body>
</html>
