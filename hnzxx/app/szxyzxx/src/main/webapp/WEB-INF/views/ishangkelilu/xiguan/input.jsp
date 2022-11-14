<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
   <%-- <%@ include file="/views/embedded/plugin/uploadify.jsp" %>--%>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <style>
        .control-group {
            display: inline-block;
        }

        .control-group button {
            padding: 5px 12px;
            margin: 40px;
            border: 1px solid #777;
            border-radius: 3px;
        }

        .control-group input {
            height: 35px;
            padding: 12px;
        }

        .control-group.jiajian {
            margin-left: 250px;
        }
    </style>
</head>
<body style="background-color: #cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_time_form" action="javascript:void(0);">
                    <div class="control-group">
                        <button id="pinfen1" value="1" onclick="bianse()" type="button" name="pinfen">
                            遵规守纪
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen2" value="2" onclick="bianse2()" type="button" name="pinfen">
                            准备到位
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen3" onclick="bianse3()" value="3" type="button" name="pinfen">
                            坐姿良好
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen4" value="4" onclick="bianse4()" type="button" name="pinfen">
                            写姿正确
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen5" value="5" onclick="bianse5()" type="button" name="pinfen">
                            认真听讲
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen6" value="6" onclick="bianse6()" type="button" name="pinfen">
                            积极发言
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen7" value="7" onclick="bianse7()" type="button" name="pinfen">
                            认真作业
                        </button>
                    </div>

                    <div class="control-group">
                        <button id="pinfen8" value="8" onclick="bianse8()" type="button" name="pinfen">
                            听写优秀
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen9" onclick="bianse9()" value="9" type="button" name="pinfen">
                            计算优秀
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen10" onclick="bianse10()" value="10" type="button" name="pinfen">
                            练习优秀
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen11" onclick="bianse11()" value="11" type="button" name="pinfen">
                            行为良好
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen12" value="12" onclick="bianse12()" type="button" name="pinfen">
                            违反纪律
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen13" value="13" onclick="bianse13()" type="button" name="pinfen">
                            未作准备
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen14" value="14" onclick="bianse14()" type="button" name="pinfen">
                            坐姿不端
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen15" value="15" onclick="bianse15()" type="button" name="pinfen">
                            写姿不良
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen16" value="16" onclick="bianse16()" type="button" name="pinfen">
                            精神不振
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen17" onclick="bianse17()" value="17" type="button" name="pinfen">
                            小嘴吵闹
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen18" value="18" onclick="bianse18()" type="button" name="pinfen">
                            不做作业
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen19" value="19" onclick="bianse19()" type="button" name="pinfen">
                            听写欠佳
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen20" value="20" onclick="bianse20()" type="button" name="pinfen">
                            计算欠佳
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen21" value="21" onclick="bianse21()" type="button" name="pinfen">
                            练习欠佳
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen22" value="22" onclick="bianse22()" type="button" name="pinfen">
                            行为失当
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen23" value="23" onclick="bianse23()" type="button" name="pinfen">
                            课堂纪律
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen24" value="24" onclick="bianse24()" type="button" name="pinfen">
                            学习态度
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen25" value="25" onclick="bianse25()" type="button" name="pinfen">
                            倾听应答
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen26" onclick="bianse26()" value="26" type="button" name="pinfen">
                            质疑点评
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen27" value="27" onclick="bianse27()" type="button" name="pinfen">
                            实践思考
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen28" value="28" onclick="bianse28()" type="button" name="pinfen">
                            合作创新
                        </button>
                    </div>
                    <div class="control-group">
                        <button id="pinfen29" value="29" onclick="bianse29()" type="button" name="pinfen">
                            其他
                        </button>
                    </div>


                    <div class="control-group">
                        <button onclick="jia()">+</button>
                        <input type="text" disabled name="score" id="score" value="0"/>
                        <button onclick="jian()">-</button>
                    </div>

                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="studentId" name="id" value="${studyHabits.studentId}"/>
                        <input type="hidden" id="teacherId" name="id" value="${studyHabits.teacherId}"/>
                        <input type="hidden" id="teamId" name="teamId" value="${studyHabits.teamId}"/>
                        <input type="hidden" id="leixing" name="leixing"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">

    function jia() {
    var score=$("#score").val();
    var d=Number(score)+1;
        $("#score").val(d)
    }
    function jian() {
        var score=$("#score").val();
        var d=Number(score)-1;
        $("#score").val(d)
    }
    var checker;
    $(function () {
    });
    //保存或更新修改
    function saveOrUpdate() {
        var teamId=$("#teamId").val();
            var loader = new loadLayer();
            var $id = $("#id").val();
            var data={
                studentId:$("#studentId").val(),
                score:$("#score").val(),
                teacherId:$("#teacherId").val(),
                leixing:$("#leixing").val(),
                teamId:teamId
            }
            var url = "${ctp}/study/habits/xiugaiOrAdd";
            loader.show();
            $.post(url, data, function (data, status) {
                if (status=="success") {
                    if ("success" === data) {
                        $.success('操作成功');
                        //HTMLIFrameElement
                       /* parent.$("iframe").each(function () {
                            //alert(  $(this).attr('src', $(this).attr('src')));
                            $(this).attr('src', $(this).attr('src'));//需要引用jquery
                        })*/
                        /*$.post("/study/habits/shangkejilu?teamId="+teamId,function () {

                        })*/

                        $.closeWindow();
                    } else {
                        $.error("操作失败");
                    }
                }
                loader.close();
            });

    }

   function bianse6(){
        $("#leixing").val(6);
       document.getElementById("pinfen1").style.backgroundColor="white";
       document.getElementById("pinfen5").style.backgroundColor="white";
       document.getElementById("pinfen4").style.backgroundColor="white";
       document.getElementById("pinfen3").style.backgroundColor="white";
       document.getElementById("pinfen2").style.backgroundColor="white";
       document.getElementById("pinfen6").style.backgroundColor="red";
       document.getElementById("pinfen7").style.backgroundColor="white";
       document.getElementById("pinfen8").style.backgroundColor="white";
       document.getElementById("pinfen9").style.backgroundColor="white";
       document.getElementById("pinfen10").style.backgroundColor="white";
       document.getElementById("pinfen11").style.backgroundColor="white";
       document.getElementById("pinfen12").style.backgroundColor="white";
       document.getElementById("pinfen13").style.backgroundColor="white";
       document.getElementById("pinfen14").style.backgroundColor="white";
       document.getElementById("pinfen15").style.backgroundColor="white";
       document.getElementById("pinfen16").style.backgroundColor="white";
       document.getElementById("pinfen17").style.backgroundColor="white";
       document.getElementById("pinfen18").style.backgroundColor="white";
       document.getElementById("pinfen19").style.backgroundColor="white";
       document.getElementById("pinfen20").style.backgroundColor="white";
       document.getElementById("pinfen21").style.backgroundColor="white";
       document.getElementById("pinfen22").style.backgroundColor="white";
       document.getElementById("pinfen23").style.backgroundColor="white";
       document.getElementById("pinfen24").style.backgroundColor="white";
       document.getElementById("pinfen25").style.backgroundColor="white";
       document.getElementById("pinfen26").style.backgroundColor="white";
       document.getElementById("pinfen27").style.backgroundColor="white";
       document.getElementById("pinfen28").style.backgroundColor="white";
       document.getElementById("pinfen29").style.backgroundColor="white";
   }
    function bianse5(){
        $("#leixing").val(5);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="red";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse4(){
        $("#leixing").val(4)
        document.getElementById("pinfen4").style.backgroundColor="red";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse3(){
        $("#leixing").val(3)
        document.getElementById("pinfen3").style.backgroundColor="red";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse2(){
        $("#leixing").val(2)
        document.getElementById("pinfen2").style.backgroundColor="red";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse(){
        $("#leixing").val(1);
        document.getElementById("pinfen1").style.backgroundColor="red";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse7(){
        $("#leixing").val(7);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="red";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse8(){
        $("#leixing").val(8);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="red";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse9(){
        $("#leixing").val(9);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="red";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse10(){
        $("#leixing").val(10);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="red";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse11(){
        $("#leixing").val(11);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="red";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse12(){
        $("#leixing").val(12);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="red";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse13(){
        $("#leixing").val(13);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="red";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse14(){
        $("#leixing").val(14);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="red";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse15(){
        $("#leixing").val(15);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="red";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse16(){
        $("#leixing").val(16);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="red";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse17(){
        $("#leixing").val(17);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="red";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse18(){
        $("#leixing").val(18);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="red";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse19(){
        $("#leixing").val(19);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="red";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse20(){
        $("#leixing").val(20);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="red";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse21(){
        $("#leixing").val(21);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="red";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse22(){
        $("#leixing").val(22);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="red";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse23(){
        $("#leixing").val(23);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="red";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse24(){
        $("#leixing").val(24);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="red";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse25(){
        $("#leixing").val(25);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="red";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse26(){
        $("#leixing").val(26);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="red";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse27(){
        $("#leixing").val(27);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="red";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse28(){
        $("#leixing").val(28);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="red";
        document.getElementById("pinfen29").style.backgroundColor="white";
    }
    function bianse29(){
        $("#leixing").val(29);
        document.getElementById("pinfen1").style.backgroundColor="white";
        document.getElementById("pinfen5").style.backgroundColor="white";
        document.getElementById("pinfen4").style.backgroundColor="white";
        document.getElementById("pinfen3").style.backgroundColor="white";
        document.getElementById("pinfen2").style.backgroundColor="white";
        document.getElementById("pinfen6").style.backgroundColor="white";
        document.getElementById("pinfen7").style.backgroundColor="white";
        document.getElementById("pinfen8").style.backgroundColor="white";
        document.getElementById("pinfen9").style.backgroundColor="white";
        document.getElementById("pinfen10").style.backgroundColor="white";
        document.getElementById("pinfen11").style.backgroundColor="white";
        document.getElementById("pinfen12").style.backgroundColor="white";
        document.getElementById("pinfen13").style.backgroundColor="white";
        document.getElementById("pinfen14").style.backgroundColor="white";
        document.getElementById("pinfen15").style.backgroundColor="white";
        document.getElementById("pinfen16").style.backgroundColor="white";
        document.getElementById("pinfen17").style.backgroundColor="white";
        document.getElementById("pinfen18").style.backgroundColor="white";
        document.getElementById("pinfen19").style.backgroundColor="white";
        document.getElementById("pinfen20").style.backgroundColor="white";
        document.getElementById("pinfen21").style.backgroundColor="white";
        document.getElementById("pinfen22").style.backgroundColor="white";
        document.getElementById("pinfen23").style.backgroundColor="white";
        document.getElementById("pinfen24").style.backgroundColor="white";
        document.getElementById("pinfen25").style.backgroundColor="white";
        document.getElementById("pinfen26").style.backgroundColor="white";
        document.getElementById("pinfen27").style.backgroundColor="white";
        document.getElementById("pinfen28").style.backgroundColor="white";
        document.getElementById("pinfen29").style.backgroundColor="red";
    }






</script>
</html>