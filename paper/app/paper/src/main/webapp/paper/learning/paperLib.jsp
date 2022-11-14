
<%@page import="com.gzxtjy.paper.constants.PaperType"%>
<%--
    Created on : 2013-5-1, 18:00:10
    Author     : huangjiangnan
--%>

<%@page import="com.gzxtjy.paper.constants.QuestionType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <title>题库选题</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width,initial-scale=1.0">
        <link rel="stylesheet" type="text/css" href="/css/common/resources/default.css" media="screen" />
        <link href="/css/common/resources/fileSearch.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" type="text/css" href="/css/teacher/paper/style.css" media="screen"/>
        <script type="text/javascript" src="/js/common/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="/js/common/jquery.cookie.js"></script>
        <style>
            .rs_platformTabArea2Content .subnav .cur {
                background-color: #009720;
                border-radius: 5px;
                color: white;
            }
        </style>
    </head>
    <body> 
        <div class="new_main" style="width:1131px" >
            <div class="question_select" style="width:1131px;">
                <div class="nav">
                    <ul>
                        <li ><a target="_self" href="/common/paper/paperAction_createPaper.action?paperId=<s:property value="#session.paper_temp_paper_vo.paperId"/>&paperType=<s:property value="#session.paper_temp_paper_vo.paperType"/>">常规</a></li>
                        <li class="on"><a href="javascript:void(0)">题库选题</a></li>
                        <li><a href="#">预览</a></li>
                    </ul>
                </div>
                <div class="tk_xuanti">
                    <div class="i1">
                        <a class="a1" href="#">题库</a>
                        <a class="a2" href="#">试卷库</a>
                        <a class="a3" href="#">作业库</a>
                        <p>公共题库</p>
                    </div>
                    <div class="i2">
                        <a class="b1" href="#">我的题库</a>
                        <a class="b2" href="#">我的试卷库</a>
                        <a class="b3" href="#">我的作业库</a>
                        <p>个人库</p>
                    </div>
                    <div class="i3">
                        <a class="c1" href="#">随机组题</a>
                        <a class="c2" href="#">随机选卷</a>
                        <p>智能推荐</p>
                    </div>


                </div>
            </div>
            <div monkey="selectArea" class="select-area mb10" id="select-area"  style="width:1118px;" >
                <div class="nav_1">
                    <ul>
                        <li><a href="/common/paper/paperAction_questionLib.action">平台题库</a></li>
                        <li class="on"><a href="#">作业库</a></li>
                        <li><a href="#">试卷库</a></li>
                    </ul>
                    <div class="number"><p>15</p></div>
                </div>
                <table>
                    <tbody>
                        <tr>
                            <td class="a" style="width:15%;">作业范围</td>
                            <td class="b" style="width:85%;">
                                <span><s:property value="#request.subject.name"/></span>
                                <span><s:property value="#request.publish.name"/></span>
                                <span><s:property value="#request.grade.name"/></span>
                                <span><s:property value="#request.volume.name"/></span>
                                <!--                                <span style="padding-left:15px;border-left:1px solid #E2E6E5;"><a class="alter" href="#">修改</a></span>-->
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="rs_platformContainer_1" style="width:1116px"  >
                <s:include value="/module/common/paper/catalog.jsp">
                </s:include>
                <div class="main" style=" float: left">
                    <div class="rs_Tab">
                        <span class="paper_list on">作业列表</span>
<!--                        <ul>
                            <li><a href="javascript:void(0)">人气</a></li>
                            <li><a href="javascript:void(0)">最新</a></li>
                            <li><a href="javascript:void(0)">正确率</a></li>
                        </ul>-->
                    </div>
                    <div class="paper_list_1">
                        <ul id="paperUl">
                        </ul>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="gotop">
        <a class="top" href="javascript:void();" onclick="goTop();"></a>
        <a><div class="number_2"><p>15</p></div></a>
    </div>
    <footer>
        <jsp:include page="/module/teacher/foot.jsp"></jsp:include>
    </body>
</html>
<script type="text/javascript">
            var subjectCode = "<s:property value="#request.subject.code"/>";
            var publishCode = "<s:property value="#request.publish.code"/>";
            var gradeCode = "<s:property value="#request.grade.code"/>";
            var volumeCode = "<s:property value="#request.volume.code"/>";
            
            function createPaper(paper) {
                var ul = $("#paperUl");
                var html = "<li>"
                        + "<div class=\"title\">"
                        + "<span class=\"score\">" + paper.score + "</span>"
//                        + "<a href=\"#\" class=\"num\">( <b id=\"docValueCount-2\">108</b> 人评价)</a>"
                        + "<p class=\"add\">"
//                        + "<a href=\"#\" class=\"a1\">报错</a>"
//                        + "<a href=\"#\" class=\"a2\">收藏</a>"
                        + "<a href=\"#\" class=\"a3\">预览</a>"
                        + "<a onclick=\"\" href=\"javascript:void();\" class=\"edit\">加入组卷</a>"
                        + "</p>"
                        + "</div>"
                        + "<div class=\"paper\">"
                        + "<div class=\"left\">"
                        + "<p class=\"title_1\">" + paper.paperTitle + "</p>"
                        + "<span class=\"num\">[" + paper.questionIds.length + "题]</span>"
                        + "<span>科目：" + paper.subjectName + "</span>"
                        + "<span>年级：" + paper.gradeName + "</span>"
                        + "<span>册次：" + paper.volumeName + "</span>"
                        + "<span>教材目录：" + paper.unitName + " " + paper.sectionName + "</span>"
                        + "</div>"
                        + "<div class=\"right\">"
                        + "<img src=\"/image/login/noPhoto.jpg\" />"
                        + "<span class=\"name\">欧阳买买提</span>"
                        + "<span class=\"time\">创建于：" + paper.createTime + "</span>"
                        + "</div>"
                        + "</div>"
                        + "</li>";
                ul.append(html);
            }


            function searchQuestion(data) {
                if (data == null) {
                    data = {};
                }
                data["paperType"] = "<%=PaperType.HOMEWORD%>";
                data["subjectCode"] = subjectCode;
                data["publishCode"] = publishCode;
                data["gradeCode"] = gradeCode;
                data["volumeCode"] = volumeCode;
                data["userId"] = "<s:property value="@com.gzxtjy.portal.session.constants.SessionConstants@SYS_ADMIN_ID"/>";
                $("#paperUl").html("<div id=\"loading\" ><img src=\"/image/teacher/loading.gif\" alt=\"加载中\"/>文件加载中,请耐心等待</div>");
                $.ajax({
                    url: "/teacher/paperLib/paperAction_loadPaperListJson.action",
                    type: "POST",
                    data: data,
                    dataType: "json",
                    async: true,
                    success: function(json) {
                        var papers = json.papers;
                        if (papers.length <= 0) {
                            $("#paperUl").html("本章节没有相关作业");
                        } else {
                            $("#paperUl").html("");
                        }
                        for (var i = 0; i < papers.length; i++) {
                            var paper = papers[i];
                            createPaper(paper);
                        }
                    }
                });
            }
</script>

