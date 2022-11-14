<%-- 
    Document   : loadCalendar
    Created on : 2015-4-15, 14:24:58
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<!DOCTYPE html>
<style type="text/css">
    .table thead th, .table tbody td{text-align:center;vertical-align:middle}
    .table tbody .red{background-color:#df5a5a;color:#fff;}
    .table{margin-bottom:0}
    .table tbody td,.table thead th{width:29px;}
    .jiaqi{padding:5px;float:left;width:440px;}
    .yf{
        width: 29px;float:left;padding:3px;border-top:1px solid #dddddd;
    }
    .yf p{
        height: 76px;
        margin-bottom: 0;
        padding-top: 52px;
    } 
</style>
<div class="calculator" style="margin-bottom: 20px;">
    <div class="cal_top">
        <div class="cal_1">
            <p>${ca.name}</p>
            <p>本校历时间段：<fmt:formatDate pattern="yyyy-MM-dd" value="${ca.beginDate}" type="both"/>&nbsp;&nbsp;-&nbsp;&nbsp;<fmt:formatDate pattern="yyyy-MM-dd" value="${ca.finishDate}" type="both"/></p>
        </div>
        <div class="cal_2">
            <button id="saveBtn" onclick="saveCalendar('${ca.id}')" style="display: none;border-radius:0;" class="btn btn-warning">保存</button>
            <button id="editBtn" onclick="editCalendar('${ca.id}')" class="btn btn-blue" style="border-radius:0;" >编辑</button>
            <button id="deleteBtn" onclick="deleteCalendar('${ca.id}')" class="btn btn-danger" style="border-radius:0;" >删除</button>
        </div>
    </div> 
    <div id="97pickerDiv" class="cal_main">
        <table class="table responsive table-bordered">
            <thead>
                <tr><th >周序</th><th  >月份</th><th>一</th><th>二</th><th>三</th><th>四</th><th>五</th><th>六</th><th>日</th><th style="width:486px;">备注</th></tr>
            </thead>
        </table>
        <div class="white">
            <div id="97picker" style="float:left;width:311px;">
                <!--                <div class="yue">
                                    <table class="table responsive table-bordered" style="width: 35px;float:left">
                                        <tbody class="white">
                                            <tr><td >1</td></tr>
                                            <tr><td>2</td></tr>
                                            <tr><td>3</td></tr>
                                            <tr><td>4</td></tr>
                                            <tr><td>5</td></tr>
                                        </tbody>
                                    </table>
                                    <div class="yf" >
                                        <p>三月</p>
                                    </div>
                                    <table class="table responsive table-bordered" style="width:241px;float:right">
                                        <tbody class="white">
                                            <tr><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td></tr>
                                            <tr><td>8</td><td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td>14</td></tr>
                                            <tr><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td><td>20</td><td>21</td></tr>
                                            <tr><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td><td>27</td><td>28</td></tr>
                                            <tr><td>29</td><td>30</td><td>31</td><td></td><td></td><td></td><td></td></tr>
                                        </tbody>
                                    </table>
                                    <div class="clear"></div>
                                </div>-->
            </div>
            <div id="remark" class="jiaqi">
                ${ca.remark}
            </div>
            <div class="clear"></div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
                var chineseNo = ["零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"];
                var splitM = 0;
                var splitW = 0;

                String.prototype.endsWith = function(suffix) {
                    return this.indexOf(suffix, this.length - suffix.length) !== -1;
                };

                function loadDate() {
                    var beginDate = "<fmt:formatDate pattern="yyyy-MM-dd" value="${ca.beginDate}" type="both"/>";
                    var finishDate = "<fmt:formatDate pattern="yyyy-MM-dd" value="${ca.finishDate}" type="both"/>";
                    var bsd = beginDate.split("-");
                    var fsd = finishDate.split("-");
                    var bMonth = parseInt(bsd[1]);
                    var fMonth = parseInt(fsd[1]);
                    var splitMonth = getSplitMonth(bsd, fsd);
                    var msloop = new Array(splitMonth);
                    splitM = getCountWeeks(bsd[0], bMonth) - 1;
                    for (var i = 0; i < msloop.length; i++) {
                        var co = bMonth + i;
                        if (co > 12) {
                            msloop[i] = {"m": chineseNo[co - 12], "d": getCountDays(fsd[0], (co - 12), fsd[2])};
                        } else {
                            msloop[i] = {"m": chineseNo[co], "d": getCountDays(bsd[0], co, bsd[2])};
                        }
                    }
                    for (var x = 0; x < msloop.length; x++) {
                        $("#97picker").append(createDateHtml(x, msloop[x]));
                        //WdatePicker({eCont: '97picker_'+i, isShowWeek: true, isShowOthers: true});
                    }
                    setHoliday();
                    setToday();
                }

                function getCountDays(y, m, d) {
                    var curDate = new Date(parseInt(y), parseInt(m), parseInt(d));
                    /* 获取当前月份 */
                    var curMonth = curDate.getMonth();
                    /*  生成实际的月份: 由于curMonth会比实际月份小1, 故需加1 */
                    curDate.setMonth(curMonth);
                    /* 将日期设置为0, 这里为什么要这样设置, 我不知道原因, 这是从网上学来的 */
                    curDate.setDate(0);
                    /* 返回当月的天数 */
                    return curDate.getDate();
                }

                function getCountWeeks(y, m) {
                    var curDate = new Date(Date.parse((y + "-" + m + "-" + "01").replace(/-/g, '/')));
                    return curDate.getDay();
                }

                function createDateHtml(x, month) {
                    var dhtml = "<tr>";
                    for (var d = 0; d < splitM; d++) {
                        dhtml = dhtml + "<td></td>";
                    }
                    for (var h = 1; h <= month.d; h++) {
                        dhtml = dhtml + "<td>" + h + "</td>";
                        if ((dhtml.split("<td>").length - 1) % 7 == 0) {
                            dhtml = dhtml + "</tr><tr>";
                        }
                    }
                    //去除最后的tr
                    if(dhtml.endsWith("<tr>")){
                        dhtml = dhtml.substring(0,dhtml.length-4);
                    }
                    var whtml = "";
                    var wno = splitW + (dhtml.split("<tr>").length - 1);
                    for (var y = splitW; y < wno; y++) {
                        whtml = whtml + "<tr><td>" + (y + 1) + "</td></tr>";
                    }
                    splitW = splitW + (whtml.split("<tr>").length - 1);
                    var html = "<div class=\"yue\">"
                            + "<table class=\"table responsive table-bordered\" style=\"width: 35px;float:left\">"
                            + "<tbody class=\"white\">"
                            + whtml
                            + "</tbody>"
                            + "</table>"
                            + "<div class=\"yf\" >"
                            + "<p>" + month.m + "月</p>"
                            + "</div>"
                            + "<table name=\"dayTable\" class=\"table responsive table-bordered yuefen\" style=\"width:241px;float:right\">"
                            + "<tbody class=\"white\">"
                            + dhtml
                            + "</tbody>"
                            + "</table>"
                            + "<div class=\"clear\"></div>"
                            + "</div>";
                    var tdlength = 35;
                    if((dhtml.split("<tr>").length-1)>5){
                        tdlength = 42;
                    }
                    splitM = 7 - ((tdlength - splitM) - month.d);
                    if (splitM >= 7) {
                        splitM = 0;
                    }
                    return html;
                }

                function getSplitMonth(bsd, fsd) {
                    var bMonth = parseInt(bsd[0]) * 12 + parseInt(bsd[1]);
                    //得到开始日期的月数
                    var eMonth = parseInt(fsd[0]) * 12 + parseInt(fsd[1]);
                    //得到结束日期的月数
                    var totalMonth = Math.abs(eMonth - bMonth);
                    //获取月数
                    return totalMonth + 1;
                }

                function editCalendar() {
                    var retext = $.trim($("#remark").html());
                    $("#remark").html("<textarea style=\"width:97%;height:"+($("#97picker").height()-20)+"px;margin-bottom:0\"></textarea>");
                    retext = retext.replace(/<br>/gm, "\n");
                    $("#remark textarea").val(retext);
                    $("#remark textarea").focus();
                    $("#saveBtn").show();
                    $("#editBtn").hide();
                }
                
                function setHoliday(){
                    $("#97picker .yf").each(function(){
                        if($(this).find("p").text()=="一月"){
                            $(this).next().find("td").each(function(){
                                if($(this).text()=="1"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="2"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="3"){
                                    $(this).attr("class","red");
                                }
                            });
                        }else if($(this).find("p").text()=="二月"){
                            $(this).next().find("td").each(function(){
                                if($(this).text()=="18"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="19"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="20"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="21"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="22"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="23"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="24"){
                                    $(this).attr("class","red");
                                }
                            });
                        }else if($(this).find("p").text()=="四月"){
                            $(this).next().find("td").each(function(){
                                if($(this).text()=="4"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="5"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="6"){
                                    $(this).attr("class","red");
                                }
                            });
                        }else if($(this).find("p").text()=="五月"){
                            $(this).next().find("td").each(function(){
                                if($(this).text()=="1"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="2"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="3"){
                                    $(this).attr("class","red");
                                }
                            });
                        }else if($(this).find("p").text()=="六月"){
                            $(this).next().find("td").each(function(){
                                if($(this).text()=="20"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="21"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="22"){
                                    $(this).attr("class","red");
                                }
                            });
                        }else if($(this).find("p").text()=="九月"){
                            $(this).next().find("td").each(function(){
                                if($(this).text()=="26"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="27"){
                                    $(this).attr("class","red");
                                }
                            });
                        }else if($(this).find("p").text()=="十月"){
                            $(this).next().find("td").each(function(){
                                if($(this).text()=="1"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="2"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="3"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="4"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="5"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="6"){
                                    $(this).attr("class","red");
                                }else if($(this).text()=="7"){
                                    $(this).attr("class","red");
                                }
                            });
                        }
                    });
                    if($.trim($("#remark").html())==""){
                        var hdtml = "元旦 	1月1日~1月3日 	1月4日（周日）上班 	共3天"
                               +"<br>春节 	2月18日(除夕）~2月24日 	2月15日（周日）、2月28日（周六）上班 	共7天"
                               +"<br>清明节 	4月4日~4月6日 	4月6日（周一）补休、含周末公休 	共3天"
                               +"<br>劳动节 	5月1日~5月3日 	与5月2日（周六）、5月3日（周日）连休 	共3天"
                               +"<br>端午节 	6月20日~6月22日 	6月22日（周一）补休、含周末公休 	共3天"
                               +"<br>中秋节 	9月26日~9月27日 	含周末公休 	共2天"
                               +"<br>国庆节 	10月1日~10月7日 	10月10日（周六）上班 	共7天";
                        $("#remark").html(hdtml);
                    }
                }
                
                function setToday(){
                	var date = new Date();
                	var d = date.getDate();
                	var m = date.getMonth();
                	var y = date.getFullYear();
                	//判断当前日期是否在校历时间内
                    var begin = new Date("${ca.beginDate}");
                    var end = new Date("${ca.finishDate}");
                    end.setMonth(end.getMonth()+1, 1);
               		if(begin.getTime() > date.getTime() || date.getTime() >= end.getTime()){
               			return;
               		}
                    
               		var mlist = new Array(12);
                	for(var i=0;i<mlist.length;i++){
                 		mlist[i] = chineseNo[i+1]+"月";
                	}
                	var index = m-begin.getMonth()>=0 ? y-begin.getFullYear()+1 : y-begin.getFullYear();
                	var count = 0;
                	$("#97picker .yf").each(function(){
                		if($(this).find("p").text()==mlist[m]){
                			$(this).next().find("td").each(function(){
                				if($(this).text()==d){
                					count++;
                					if(count == index){
	                					$(this).attr("class","blue");
                					}
                				 }
                			 });
                		 }
                	});
                }
                
                function saveCalendar(caId) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/teach/calendar/saveOrUpdate",
                        type: "POST",
                        data: {"id": caId, "remark": $.trim($("#remark textarea").val())},
                        async: false,
                        success: function() {
                            $("#calendarDiv").load("${pageContext.request.contextPath}/teach/calendar/loadCalendar?id=" + caId);
                        }
                    });
                }

                function deleteCalendar(caId) {
                    $.confirm("确定删除校历吗?", function() {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/teach/calendar/delete",
                            type: "POST",
                            data: {"id": caId},
                            async: false,
                            success: function(html) {
                                location.href = "${pageContext.request.contextPath}/teach/calendar/index";
                            }
                        });
                    });
                }

                $(function() {
                    loadDate();
                    $(".yuefen tr").find("td:eq(5),td:eq(6)").css("color","#ff0000")
                  $(".red").css("color","#fff");
                });
</script>