<%-- 
    Document   : quizResultPaper
    Created on : 2012-3-16, 16:01:19
    Author     : Administrator
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="pragma" content="no-cache" />
        <title>${vo.result.title} - ${vo.realName} 成绩单</title>
        <link type="text/css" rel="stylesheet" href="/css/common/quiz/report.css" />
        <!--[if IE]>
        <script type="text/javascript" src="/js/common/html5/excanvas.js"></script>
        <![endif]-->
        <script type="text/javascript" src="/js/common/jquery-1.4.2.min.js"></script>
    </head>
    <body onload="hotSpotSearch()">
        <div id="reportContainer">
            <div class="report_title">
                <h2>${vo.result.title}</h2>
                <div id="actionLinks" class="action_links">
                    <!--       <ul>
                               <li>
                                   <a id="printReportLink" href="javascript:void(0);"><img alt="" src="http://cdn.ispringonline.com/images/report/export/print_icon.gif"> Print</a>
                               </li>
                               <li>
                                   <a id="emailReportLink" href="javascript:void(0);"><img alt="" src="http://cdn.ispringonline.com/images/report/export/email_icon.gif"> Email</a>
                               </li>
                               <li class="menu_link">
                                   <div class="mouse_states" id="exportMenuContainer">
                                       <div class="closed_menu">
                                           <a class="export_link" href="javascript:void(0);"><img alt="" src="http://cdn.ispringonline.com/images/report/export/export_icon.gif"> Export</a>
                                       </div>
                                       <div class="opened_menu">
                                           <a class="export_link" href="javascript:void(0);"><img alt="" src="http://cdn.ispringonline.com/images/report/export/export_icon.gif"> Export</a>

                                           <form action="" id="exportReportForm" method="post">
                                               <div id="exportReportMenu" class="export_links">
                                                   <ul>
                                                       <li class="first">
                                                           <a target="_blank" href="/reports/csv/quiz_attempt_detail">
                                                               <img alt="" src="http://cdn.ispringonline.com/images/report/export/csv_icon.gif">
                                                               Export to CSV                    </a>
                                                       </li>
                                                       <li>
                                                           <a target="_blank" href="/reports/xml/quiz_attempt_detail">
                                                               <img alt="" src="http://cdn.ispringonline.com/images/report/export/xml_icon.gif">
                                                               Export to XML                    </a>
                                                       </li>
                                                       <li>
                                                           <a target="_blank" href="/reports/pdf/quiz_attempt_detail">
                                                               <img alt="" src="http://cdn.ispringonline.com/images/report/export/pdf_icon.gif">
                                                               Export to PDF                    </a>
                                                       </li>
                                                   </ul>
                                               </div>

                                               <input type="hidden" value="" name="order_field">
                                               <input type="hidden" value="" name="order_asc">
                                               <input type="hidden" value="2" name="item_id">
                                               <input type="hidden" value="8" name="item_type">
                                               <input type="hidden" value="" name="date_range">
                                               <input type="hidden" value="0" name="group">
                                               <input type="hidden" value="1" name="organization">
                                               <input type="hidden" value="2" name="user">
                                               <input type="hidden" value="2" name="quiz_attempt">

                                           </form>
                                       </div>
                                   </div>
                               </li>
                           </ul>-->
                </div>
                <div class="clear"></div>
            </div>

            <div class="corners white_on_white_corners">
                <div class="tl"></div><div class="tr"></div>
                <div class="corners_top_sep"></div>
                <div class="cornered_content">      <div class="report_summary">
                        <ul>
                            <li>
                                <span class="title">学生:</span>
                                <span>
                                    ${vo.realName}
                                </span>
                            </li>
                            <li>
                                <span class="title">得分:</span>
                                <span class="value">
                                    ${vo.result.score}            </span>

                                <s:if test='("true").equals(#request.score)'>
                                    <div class="button">
                                        <div class="main">
                                            <div class="left"></div>
                                            <div id="scoreButton" onclick="score()" class="center">
                                                我要评分
                                            </div>
                                            <div id="saveButton" onclick="saveScore()" class="center" style="display:none;">
                                                保存成绩
                                            </div>
                                            <div class="right"></div>
                                        </div>
                                    </div>
                                    <script type="text/javascript">
                                        function score(){
                                            $(".user_score").each(function(){
                                                var pre_score = $(this).find("span.value").html().replace(/[^0-9.]/ig,"");
                                                $(this).find("span.value").empty();
                                                $(this).find("span.value").append("<input name='score' value='"+pre_score+"' type='text' class='score_input'/>");
                                            });
                                            $("#scoreButton").hide();
                                            $("#saveButton").show();
                                        }
                                        function saveScore(){
                                            $("#scoreForm").find("input[type='text']").each(function(){
                                                if($(this).val()==""){
                                                    $(this).val("0");
                                                }
                                            });
                                            if(checkScore()){
                                                $("#scoreForm").submit();
                                            }
                                        }
                                        function checkScore(){
                                            var flag = true;
                                            $("#scoreForm").find("input[type='text']").each(function(){
                                                if(!(/^-?\d+\.?\d*$/.test($(this).val()))){
                                                    $(this).focus();
                                                    alert("请输入有效实数");
                                                    flag = false;
                                                    return flag;
                                                }
                                            });
                                            return flag;
                                        }
                                    </script>
                                </s:if>
                            </li>
                            <li>
                                <span class="title">用时:</span>
                                <span class="value"></span>
                            </li>
                        </ul>

                        <div class="clear"></div>
                    </div>

                    <div><div class="bl"></div><div class="br"></div></div>
                </div>
            </div>
            <form action="/common/lads/ladsQuizAction_scoreResult.action" onsubmit="return checkScore()" id="scoreForm">
                ${statistics}
                <input type="hidden" value="${sysType}" id="sysType" name="sysType"/>
                <input type="hidden" value="${vo.result.id}" name="resultId"/>
            </form>
            <div class="clear"></div>
            <script type="text/javascript">
                var canvas,cxt;
                function oval(x, y, w, h) {
                    var k = 0.5522848;
                    var ox = (w / 2) * k;
                    var oy = (h / 2) * k;
                    var xe = x + w;
                    var ye = y + h;
                    var xm = x + w / 2;
                    var ym = y + h / 2;
                    cxt.beginPath();
                    cxt.moveTo(x, ym);
                    cxt.bezierCurveTo(x, ym - oy, xm - ox, y, xm, y);
                    cxt.bezierCurveTo(xm + ox, y, xe, ym - oy, xe, ym);
                    cxt.bezierCurveTo(xe, ym + oy, xm + ox, ye, xm, ye);
                    cxt.bezierCurveTo(xm - ox, ye, x, ym + oy, x, ym);
                    cxt.fill();
                    cxt.stroke();
                }
                function freedom(points){
                    for(var v=0;v<points.length;v++){
                        var x = parseInt(points[v].split(",")[0]);
                        var y = parseInt(points[v].split(",")[1]);
                        if(v==0){
                            cxt.translate(x,y);
                        }else if(v==1){
                            cxt.moveTo(x,y);
                        }else{
                            cxt.lineTo(x,y);
                        }
                    }
                    cxt.fill();
                    cxt.stroke();
                }
                function hotSpotSearch(){
                    canvas = document.getElementsByTagName("canvas");
                    for(var i=0;i<canvas.length;i++){
                        var qid = canvas[i].id.split("_")[1];
                        canvas[i].width = $('#spotImg_'+qid).attr('width');
                        canvas[i].height = $('#spotImg_'+qid).attr('height');
                        cxt = canvas[i].getContext("2d");
                        cxt.fillStyle = "#00FF00";
                        cxt.globalAlpha = 0.5;
                        $("input[id^='shape_"+qid+"_'").each(function(){
                            var shape = $(this).val();
                            if(shape=="rectangle"){
                                var points = $(this).next().val().split(",");
                                cxt.fillRect(parseInt(points[0]),parseInt(points[1]),parseInt(points[2]),parseInt(points[3]));
                            }else if(shape=="freeform"){
                                var points = $(this).next().val().split(";");
                                freedom(points);
                            }else if(shape=="oval"){
                                var points = $(this).next().val().split(",");
                                oval(parseInt(points[0]),parseInt(points[1]),parseInt(parseInt(points[2])/2),parseInt(parseInt(points[3])/2));
                            }
                        });
                    }
                }

                <s:if test='#request.qid!=null&&!("".equals(#request.qid))'>
                    $(function(){
                        var qid = <s:property value="#request.qid"/>;
                        $("#question_div_"+qid).attr("class","quiz_question_choose");
                        $('html, body').animate({ scrollTop: $("#question_div_"+qid).offset().top }, 'slow');
                    });
                </s:if>
            </script>
        </div>
    </body>
</html>
