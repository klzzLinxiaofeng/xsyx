<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.education.resource.contants.ResourceType"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <%
                String httpUrl = (String) request.getParameter("httpUrl");
        %>
        <link href="${pageContext.request.contextPath}/res/css/extra/xkzy.css"
              rel="stylesheet">
        <script type="text/javascript"
        src="${pageContext.request.contextPath}/res/js/slider.js"></script>
        <title>微课云资源</title>
        <style>
            .course-box {
                float: left;
            }

            .zy_list ul {
                height: 440px;
                overflow: hidden;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid" style="margin-top:20px;">
            <%-- <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon" />
                <jsp:param value="微课资源" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include> --%>
            <div class="row-fluid" style="margin:0 auto;width: 1082px;">
                <div class="span12">
                    <div class="content-widgets white">
                        <div class="div2new1">
                            <div class="div2left">
                                <div class="zydh">
                                    <div class="title">
                                        <span>微课资源导航</span>
                                    </div>
                                    <div class="wk_list">
                                        <ul>
                                            <li class="fl_list">
                                                <div class="li_hover">
                                                    <p class="title_1">小学微课</p>
                                                    <div class="wk_div">
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=21">一年级</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=22">二年级</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=23">三年级</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=24">四年级</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=25">五年级</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&gradeCode=26">六年级</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13">语文</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14">数学</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41">英语</a>
                                                    </div>
                                                </div>
                                                <div class="njkm" style="display: none;">
                                                    <ul class="nj_ul">
                                                        <li>
                                                            <p>一年级</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=21">语文</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=21">数学</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=21">英语</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        <li>
                                                            <p>二年级</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=22">语文</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=22">数学</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=22">英语</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        <li>
                                                            <p>三年级</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=23">语文</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=23">数学</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=23">英语</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        <li>
                                                            <p>四年级</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=24">语文</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=24">数学</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=24">英语</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        <li>
                                                            <p>五年级</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=25">语文</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=25">数学</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=25">英语</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                             <li>
                                                            <p>六年级</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=13&gradeCode=26">语文</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=14&gradeCode=26">数学</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=2&subjectCode=41&gradeCode=26">英语</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </li>
                                            <li class="fl_list">
                                                <div class="li_hover ">
                                                    <p class="title_1">初中微课</p>
                                                    <div class="wk_div">

                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&gradeCode=31">七年级</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&gradeCode=32">八年级</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&gradeCode=33">九年级</a>
                                                        <%--   <a href="<%= SysContants.COMMON_RESOURCE_BASE_PATH %>/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=13">语文</a>
                                                           <a href="<%= SysContants.COMMON_RESOURCE_BASE_PATH %>/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=14">数学</a>
                                                            <a href="<%= SysContants.COMMON_RESOURCE_BASE_PATH %>/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=41">英语</a>
                                                        --%>
                                                    </div>
                                                </div>
                                                <div class="njkm" style="display: none;">
                                                    <ul class="nj_ul">
                                                        <li>
                                                            <p>初一</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=13&gradeCode=31">语文</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=14&gradeCode=31">数学</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=41&gradeCode=31">英语</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        <li>
                                                            <p>初二</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=13&gradeCode=32">语文</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=14&gradeCode=32">数学</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=41&gradeCode=32">英语</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        <li>
                                                            <p>初三</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=13&gradeCode=33">语文</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=14&gradeCode=33">数学</a>
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=3&subjectCode=41&gradeCode=33">英语</a>
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </li>
              								
              								 <c:forEach items="${sessionScope[sca:currentUserKey()].stageCodes}" var="item" varStatus="sta">
										<c:if test="${item == 4}">
										
										 <li class="fl_list">
                                                <div class="li_hover ">
                                                    <p class="title_1">高中微课</p>
                                                    <div class="wk_div">

                                                          <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=13">语文</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=14">数学</a>
                                                        <a
                                                            href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=41">英语</a>
                                                       
                                                    </div>
                                                </div>
                                                <div class="njkm" style="display: none;">
                                                    <ul class="nj_ul">
                                                         <li>
                                                            <p>语文</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=13">必修一</a>
                                                                <a
                                                               href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=13">必修二</a>
                                                                <a
                                                                  href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=13">必修三</a>
                                                               
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        
                                                        <li>
                                                            <p>数学</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=14">必修一</a>
                                                                <a
                                                               href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=14">必修二</a>
                                                                <a
                                                                  href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=14">必修三</a>
                                                               
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        
                                                        <li>
                                                            <p>英语</p>
                                                            <div class="km">
                                                               <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=41">必修一</a>
                                                                <a
                                                               href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=41">必修二</a>
                                                                <a
                                                                  href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=41">必修三</a>
                                                               
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        
                                                        <li>
                                                            <p>物理</p>
                                                            <div class="km">
                                                                <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=16">必修一</a>
                                                                <a
                                                               href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=16">必修二</a>
                                                           
                                                               
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        
                                                        <li>
                                                            <p>化学</p>
                                                            <div class="km">
                                                               <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=17">必修一</a>
                                                                <a
                                                               href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=17">必修二</a>
                                                                                 
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        
                                                        <li>
                                                            <p>地理</p>
                                                            <div class="km">
                                                               <a
                                                                    href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=20">必修一</a>
                                                                <a
                                                               href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=20">必修二</a>
                                                                <a
                                                                  href="${pageContext.request.contextPath}/resource/searcher/index?resType=1&isMicro=true&stageCode=4&subjectCode=20">必修三</a>
                                                               
                                                            </div>
                                                            <div class="clear"></div>
                                                        </li>
                                                        
                                                    </ul>
                                                </div>
                                            </li>
              								
										</c:if>
									</c:forEach>
                                        </ul>
                                    </div>
                                </div>
                                <div class="paihang">
                                    <div class="phtitle">
                                        <span>本周浏览排行版</span>
                                    </div>
                                    <div class="phjoin">
                                        <ul>
                                            <li class="first"><p>1</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12553">04.梅花魂</a><span></span></li>
                                            <li class="second"><p>2</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12583">11.七律-长征</a><span></span></li>
                                            <li class="three"><p>3</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12589">12.开国大典</a><span></span></li>
                                            <li><p>4</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12605">08.地震中的父与子</a><span></span></li>
                                            <li><p>5</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/14951">04.理想</a><span></span></li>
                                            <li><p>6</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/14977">11-2.羚羊木雕</a><span></span></li>
                                            <li><p>7</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/14978">11-1.羚羊木雕</a><span></span></li>
                                            <li><p>8</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/15004">12.郭沫若诗两首</a><span></span></li>
                                            <li><p>9</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/15030">10.看云识天气</a><span></span></li>
                                            <li><p>10</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/15051">01-3.从百草园到三味书屋</a><span></span></li>
                                        </ul>
                                    </div>
                                </div>
                                <div style="height: 487px;" class="paihang">
                                    <div class="phtitle">
                                        <span>最新上传</span>
                                    </div>
                                    <div class="scjoin">
                                        <ul>
                                            <li>
                                                <p class="time">2015-5-11日</p> <%-- 											<img src="${httpUrl}/ggzyk_6/2015-07/d191ffd45ed993cdae4a1a4c2f234372_thumbImg.png"> --%>
                                                <img
                                                    src="${pageContext.request.contextPath}/res/images/ckk.png">
                                                <p class="name">参考库</p>
                                                <p class="upload">
                                                    上传了<a
                                                        href="${pageContext.request.contextPath}/resource/viewer/4680">《亿以内数的读写》</a>
                                                </p>
                                            </li>
                                            <li>
                                                <p class="time">2015-5-11日</p> <%-- 											<img src="${httpUrl}/ggzyk_6/2015-07/4304fc83bb4b53f2702de7651d804e9a_thumbImg.png"> --%>
                                                <img
                                                    src="${pageContext.request.contextPath}/res/images/ckk.png">
                                                <p class="name">参考库</p>
                                                <p class="upload">
                                                    上传了<a
                                                        href="${pageContext.request.contextPath}/resource/viewer/4691">《角度量的应用》</a>
                                                </p>
                                            </li>
                                            <li>
                                                <p class="time">2015-5-11日</p> <%-- 											<img src="${httpUrl}/ggzyk_6/2015-07/5069bbe98729f1335a4480a3465f081d_thumbImg.png"> --%>
                                                <img
                                                    src="${pageContext.request.contextPath}/res/images/ckk.png">
                                                <p class="name">参考库</p>
                                                <p class="upload">
                                                    上传了<a
                                                        href="${pageContext.request.contextPath}/resource/viewer/4692">《画角》</a>
                                                </p>
                                            </li>
                                            <li>
                                                <p class="time">2015-5-11日</p> <%-- 											 	<img src="${httpUrl}/ggzyk_6/2015-07/9860ac35bd9e87f7f11356e6ae1a5584_thumbImg.png"> --%>
                                                <img
                                                    src="${pageContext.request.contextPath}/res/images/ckk.png">
                                                <p class="name">参考库</p>
                                                <p class="upload">
                                                    上传了<a
                                                        href="${pageContext.request.contextPath}/resource/viewer/4693">《角的分类》</a>
                                                </p>
                                            </li>

                                        </ul>
                                    </div>
                                </div>
                                <div class="paihang">
                                    <div class="phtitle">
                                        <span>本周点赞排行版</span>
                                    </div>
                                    <div class="phjoin">
                                        <ul>
                                            <li class="first"><p>1</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4543">3-5.比的应用</a><span></span></li>
                                            <li class="second"><p>2</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4544">3-4.比例的基本性质</a><span></span></li>
                                            <li class="three"><p>3</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4545">3-3.比例的意义</a><span></span></li>
                                            <li><p>4</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4546">3-2.比的认识</a><span></span></li>
                                            <li><p>5</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4547">3-1.分数除法</a><span></span></li>
                                            <li><p>6</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4557">4-2圆的面积</a><span></span></li>
                                            <li><p>7</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4558">4-1.圆的认识</a><span></span></li>
                                            <li><p>8</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4568">5.百分数</a><span></span></li>
                                            <li><p>9</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4607">1.负数</a><span></span></li>
                                            <li><p>10</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4616">2-4.圆锥的体积</a><span></span></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="paihang">
                                    <div class="phtitle">
                                        <span>本周收藏排行版</span>
                                    </div>
                                    <div class="phjoin">
                                        <ul>
                                            <li class="first"><p>1</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12811">01.山中访友</a><span></span></li>
                                            <li class="second"><p>2</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12822">02.草虫的村落</a><span></span></li>
                                            <li class="three"><p>3</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12833">10.老人与海鸥</a><span></span></li>
                                            <li><p>4</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12845">11.最后一头战象</a><span></span></li>
                                            <li><p>5</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12864">05.唯一的听众</a><span></span></li>
                                            <li><p>6</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12875">04.穷人</a><span></span></li>
                                            <li><p>7</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12881">03.詹天佑</a><span></span></li>
                                            <li><p>8</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12902">08.少年闰土</a><span></span></li>
                                            <li><p>9</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/4721">4-3.画垂线</a><span></span></li>
                                            <li><p>10</p> <a href="javascript:void(0)">函数的概念</a><span></span></li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="paihang">
                                    <div class="phtitle">
                                        <span>本周下载排行版</span>
                                    </div>
                                    <div class="phjoin">
                                        <ul>
                                            <li class="first"><p>1</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12506">10.狼牙山五壮士</a><span></span></li>
                                            <li class="second"><p>2</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12527">06.新型玻璃</a><span></span></li>
                                            <li class="three"><p>3</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12538">05.鲸</a><span></span></li>
                                            <li><p>4</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12546">03.古诗词三首—思乡</a><span></span></li>
                                            <li><p>5</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12553">04.梅花魂</a><span></span></li>
                                            <li><p>6</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12583">11.七律-长征</a><span></span></li>
                                            <li><p>7</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12589">12.开国大典</a><span></span></li>
                                            <li><p>8</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12605">08.地震中的父与子</a><span></span></li>
                                            <li><p>9</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12626">07.钓鱼的启示</a><span></span></li>
                                            <li><p>10</p> <a
                                                    href="${pageContext.request.contextPath}/resource/viewer/12647">01.草原</a><span></span></li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                            <div class="slideshow">
                                <div class="s_div1">
                                    <div class="div1_left">
                                        <div class="select"> 
                                            <form action="${pageContext.request.contextPath}/resource/searcher/result/index?isMicro=true&resType=<%=ResourceType.MICRO%>" method="post" id="searchForm" style="margin: 0"> 
                                                <input name="title" id="title" type="text" placeholder="请输入微课标题" /> 
                                                <a onclick='$("#searchForm").submit();' href="javascript:void(0)"></a> 
                                            </form> 
                                        </div> 
                                        <div class="a">
                                            <div id="banner" class="pic1">
                                                <ul id="pic" class="pic">
                                                    <li><img
                                                            src="${pageContext.request.contextPath}/res/images/banner_1.jpg"></li>
                                                    <li><img
                                                            src="${pageContext.request.contextPath}/res/images/banner_2.jpg"></li>
                                                    <li><img
                                                            src="${pageContext.request.contextPath}/res/images/banner_3.jpg"></li>
                                                </ul>
                                            </div>
                                            <ul class="points" id="banner_btn">
                                                <li class="current" num="0">1</li>
                                                <li class="" num="1">2</li>
                                                <li class="" num="2">3</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="div1_right">
                                        <div class="zyzs">
                                            <p class="p1">微课资源总数</p>
                                            <p class="p2">7,048</p>
                                        </div>
                                        <div class="grwk">
                                            <div class="g1">
                                                <img src="<avatar:avatar userId='${sessionScope[sca:currentUserKey()].userId}'></avatar:avatar>">
                                                <p>${sessionScope[sca:currentUserKey()].realName}</p>
                                                <a href="${pageContext.request.contextPath}/micro/myMicro?index=index">>进入我的微课</a>
                                            </div>
                                            <div class="g2">
                                                <!-- 											<div class="wktj" style="border-right: 1px solid #E8E8E8;"> -->
                                                <%-- 												<p class="p1">${uploadSize}</p> --%>
                                                <!-- 												<p class="p2">上传微课</p> -->
                                                <!-- 											</div> -->
                                                <!-- 											<div class="wktj"> -->
                                                <%-- 												<p class="p1">${favSize}</p> --%>
                                                <!-- 												<p class="p2">收藏微课</p> -->
                                                <!-- 											</div> -->
                                                <!-- 											<div class="clear"></div> -->
                                                <a href="${pageContext.request.contextPath}/micro/uploadIndex"></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="s_div2">
                                    <div class="zy_list">
                                        <ul>
                                            <li>
                                                <div class="mask">
                                                    <div class="gxqwk"></div>
                                                </div>
                                            </li>
                                            <jsp:include page="interest/interest.jsp"></jsp:include>


                                            </ul>
                                            <div class="clear"></div>
                                            <div class="l_more">
                                                <!-- <!-- <a href="javascript:void(0)">查看更多</a> -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="s_div2">
                                        <!--                                    <div class="zy_list wkx">
                <ul>
                    <li>
                        <div class="mask">
                            <div class="wkx"></div>
                        </div>
                    </li>
                    <li>
                        <div class="mask">
                            <span class="thumbSize">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/weike.png"></a>
                            </span>
                            <p class="from">来自微课星</p>
                            <div class="title">
                                <span title="甘南藏族自治州合作中学中学八年级生物《动物的运动》" class="ellipsis">甘南藏族自治州合作中学中学八年级生物《动物的运动》</span>
                                <span class="time">15:23</span>
                            </div>
                            <div class="details">
                                <span class="d1">语文>高一年级</span>
                                <span class="d2"><a href="javascript:void(0)">收藏</a></span>
                            </div>
                            <div class="num">
                                <span class="n1">已有27人评价</span>
                                <span class="n2">16</span>
                            </div>
                            <div class="instructors">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
                                </a>
                                <span class="name">李丽</span>
                                <span class="day">2013-11-13上传  </span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="mask">
                            <span class="thumbSize">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/weike.png"></a>
                            </span>
                            <p class="from">来自微课星</p>
                            <div class="title">
                                <span title="甘南藏族自治州合作中学中学八年级生物《动物的运动》" class="ellipsis">甘南藏族自治州合作中学中学八年级生物《动物的运动》</span>
                                <span class="time">15:23</span>
                            </div>
                            <div class="details">
                                <span class="d1">语文>高一年级</span>
                                <span class="d2"><a href="javascript:void(0)">收藏</a></span>
                            </div>
                            <div class="num">
                                <span class="n1">已有27人评价</span>
                                <span class="n2">16</span>
                            </div>
                            <div class="instructors">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
                                </a>
                                <span class="name">李丽</span>
                                <span class="day">2013-11-13上传  </span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="mask">
                            <span class="thumbSize">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/weike.png"></a>
                            </span>
                            <p class="from">来自微课星</p>
                            <div class="title">
                                <span title="甘南藏族自治州合作中学中学八年级生物《动物的运动》" class="ellipsis">甘南藏族自治州合作中学中学八年级生物《动物的运动》</span>
                                <span class="time">15:23</span>
                            </div>
                            <div class="details">
                                <span class="d1">语文>高一年级</span>
                                <span class="d2"><a href="javascript:void(0)">收藏</a></span>
                            </div>
                            <div class="num">
                                <span class="n1">已有27人评价</span>
                                <span class="n2">16</span>
                            </div>
                            <div class="instructors">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
                                </a>
                                <span class="name">李丽</span>
                                <span class="day">2013-11-13上传  </span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="mask">
                            <span class="thumbSize">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/weike.png"></a>
                            </span>
                            <p class="from">来自微课星</p>
                            <div class="title">
                                <span title="甘南藏族自治州合作中学中学八年级生物《动物的运动》" class="ellipsis">甘南藏族自治州合作中学中学八年级生物《动物的运动》</span>
                                <span class="time">15:23</span>
                            </div>
                            <div class="details">
                                <span class="d1">语文>高一年级</span>
                                <span class="d2"><a href="javascript:void(0)">收藏</a></span>
                            </div>
                            <div class="num">
                                <span class="n1">已有27人评价</span>
                                <span class="n2">16</span>
                            </div>
                            <div class="instructors">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
                                </a>
                                <span class="name">李丽</span>
                                <span class="day">2013-11-13上传  </span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="mask">
                            <span class="thumbSize">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/weike.png"></a>
                            </span>
                            <p class="from">来自微课星</p>
                            <div class="title">
                                <span title="甘南藏族自治州合作中学中学八年级生物《动物的运动》" class="ellipsis">甘南藏族自治州合作中学中学八年级生物《动物的运动》</span>
                                <span class="time">15:23</span>
                            </div>
                            <div class="details">
                                <span class="d1">语文>高一年级</span>
                                <span class="d2"><a href="javascript:void(0)">收藏</a></span>
                            </div>
                            <div class="num">
                                <span class="n1">已有27人评价</span>
                                <span class="n2">16</span>
                            </div>
                            <div class="instructors">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
                                </a>
                                <span class="name">李丽</span>
                                <span class="day">2013-11-13上传  </span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="mask">
                            <span class="thumbSize">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/weike.png"></a>
                            </span>
                            <p class="from">来自微课星</p>
                            <div class="title">
                                <span title="甘南藏族自治州合作中学中学八年级生物《动物的运动》" class="ellipsis">甘南藏族自治州合作中学中学八年级生物《动物的运动》</span>
                                <span class="time">15:23</span>
                            </div>
                            <div class="details">
                                <span class="d1">语文>高一年级</span>
                                <span class="d2"><a href="javascript:void(0)">收藏</a></span>
                            </div>
                            <div class="num">
                                <span class="n1">已有27人评价</span>
                                <span class="n2">16</span>
                            </div>
                            <div class="instructors">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
                                </a>
                                <span class="name">李丽</span>
                                <span class="day">2013-11-13上传  </span>
                            </div>
                        </div>
                    </li>
                    <li>
                        <div class="mask">
                            <span class="thumbSize">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/weike.png"></a>
                            </span>
                            <p class="from">来自微课星</p>
                            <div class="title">
                                <span title="甘南藏族自治州合作中学中学八年级生物《动物的运动》" class="ellipsis">甘南藏族自治州合作中学中学八年级生物《动物的运动》</span>
                                <span class="time">15:23</span>
                            </div>
                            <div class="details">
                                <span class="d1">语文>高一年级</span>
                                <span class="d2"><a href="javascript:void(0)">收藏</a></span>
                            </div>
                            <div class="num">
                                <span class="n1">已有27人评价</span>
                                <span class="n2">16</span>
                            </div>
                            <div class="instructors">
                                <a href="javascript:void(0)">
                                    <img src="${pageContext.request.contextPath}/res/images/noPhoto.jpg">
                                </a>
                                <span class="name">李丽</span>
                                <span class="day">2013-11-13上传  </span>
                            </div>
                        </div>
                    </li>
                </ul>
                <div class="clear"></div>
                <div class="l_more"><!-- <a href="javascript:void(0)">查看更多</a> -->
                                </div>
                                <!-- 						</div> -->
                                <!-- 					</div> -->
                                <div class="wkzy yuwen">
                                    <div class="title">
                                        <p>语文</p>
                                        <div style="position: relative">
                                            <ul>
                                                <li><a href="javascript:void(0)">四年级</a></li>
                                                <li class="on"><a href="javascript:void(0)">五年级</a></li>
                                                <li><a href="javascript:void(0)">六年级</a></li>
                                                <li><a href="javascript:void(0)">七年级</a></li>
                                                <li><a href="javascript:void(0)">八年级</a></li>
                                                <li><a href="javascript:void(0)">九年级</a></li>
                                            </ul>
                                            <div class="clear"></div>
                                            <!-- <a href="javascript:void(0)" class="shouqi">收起</a> -->
                                        </div>
                                    </div>
                                    <div class="zy_list">
                                        <ul style="display: none">
                                        </ul>
                                        <ul>
                                            <jsp:include page="yuwen/wu.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="yuwen/liu.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="yuwen/qi.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="yuwen/ba.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="yuwen/jiu.jsp"></jsp:include>
                                            </ul>
                                            <div class="clear"></div>
                                            <div class="l_more">
                                                <!-- <a href="javascript:void(0)">查看更多</a> -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="wkzy shuxue">
                                        <div class="title">
                                            <p>数学</p>
                                            <div style="position: relative">
                                                <ul style="height: 33px; overflow: hidden">
                                                    <li class="on"><a href="javascript:void(0)">四年级</a></li>
                                                    <li><a href="javascript:void(0)">五年级</a></li>
                                                    <li><a href="javascript:void(0)">六年级</a></li>
                                                    <li><a href="javascript:void(0)">七年级</a></li>
                                                    <li><a href="javascript:void(0)">八年级</a></li>
                                                    <li><a href="javascript:void(0)">九年级</a></li>
                                                </ul>
                                                <div class="clear"></div>
                                                <!-- <a href="javascript:void(0)" class="zhankai">展开</a> -->
                                            </div>
                                        </div>
                                        <div class="zy_list">
                                            <ul>
                                            <jsp:include page="shuxue/si.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="shuxue/wu.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="shuxue/liu.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="shuxue/qi.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="shuxue/ba.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="shuxue/jiu.jsp"></jsp:include>
                                            </ul>
                                            <div class="clear"></div>
                                            <div class="l_more">
                                                <!-- <a href="javascript:void(0)">查看更多</a> -->
                                            </div>
                                        </div>
                                    </div>
                                    <div class="wkzy yingyu">
                                        <div class="title">
                                            <p>英语</p>
                                            <div style="position: relative">
                                                <ul style="height: 33px; overflow: hidden">
                                                    <li class="on"><a href="javascript:void(0)">四年级</a></li>

                                                    <li><a href="javascript:void(0)">五年级</a></li>
                                                    <li><a href="javascript:void(0)">六年级</a></li>
                                                    <li><a href="javascript:void(0)">七年级</a></li>
                                                    <li><a href="javascript:void(0)">八年级</a></li>
                                                    <li><a href="javascript:void(0)">九年级</a></li>
                                                </ul>
                                                <div class="clear"></div>
                                                <!-- <a href="javascript:void(0)" class="zhankai">展开</a> -->
                                            </div>
                                        </div>
                                        <div class="zy_list">
                                            <ul>
                                            <jsp:include page="yingyu/siyingyu.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="yingyu/wuyingyu.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="yingyu/liuyingyu.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="yingyu/qiyingyu.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="yingyu/bayingyu.jsp"></jsp:include>
                                            </ul>
                                            <ul style="display: none">
                                            <jsp:include page="yingyu/jiuyingyu.jsp"></jsp:include>
                                            </ul>
                                            <div class="clear"></div>
                                            <div class="l_more">
                                                <!-- <a href="javascript:void(0)">查看更多</a> -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </body>
        <script type="text/javascript">
                                                    var objs = document.getElementById("banner_btn").getElementsByTagName("li");
                                                    var tv = new TransformView("banner", "pic", 573, objs.length, {
                                                        onStart: function() {
                                                            Each(objs, function(o, i) {
                                                                o.className = tv.Index === i ? "current" : "";
                                                            });
                                                        }, //按钮样式
                                                        Up: false
                                                    });
                                                    tv.Start();
                                                    Each(objs, function(o, i) {
                                                        o.onmouseover = function() {
                                                            o.className = "current";
                                                            tv.Auto = false;
                                                            tv.Index = i;
                                                            tv.Start();
                                                        };
                                                        o.onmouseout = function() {
                                                            o.className = "";
                                                            tv.Auto = true;
                                                            tv.Start();
                                                        };
                                                    });
                                                    $(function() {
                                                        var timer;
                                                        $(".li_hover").hover(function() {
                                                            clearInterval(timer);
                                                            $(".li_hover").removeClass("on");
                                                            $(".njkm").hide();
                                                            $(this).parent().addClass("on");
                                                            $(this).next().show();
                                                        }, function() {
                                                            timer = setTimeout(function() {
                                                                $(".li_hover").parent().removeClass("on");
                                                                $(".njkm").hide();
                                                            }, 1000);
                                                        });
                                                        $(".njkm").hover(function() {
                                                            clearInterval(timer);
                                                        }, function() {
                                                            $(".li_hover").parent().removeClass("on");
                                                            $(".njkm").hide();
                                                        });

                                                        $(".wkzy .title").on("click", ".shouqi", function() {
                                                            $(this).removeClass("shouqi").addClass("zhankai");
                                                            /* $(this).html("展开"); */
                                                            $(this).prev().prev().css("height", "33px");
                                                        });
                                                        $(".wkzy .title").on("click", ".zhankai", function() {
                                                            $(this).removeClass("zhankai").addClass("shouqi");
                                                            /* $(this).html("收起"); */
                                                            $(this).prev().prev().css("height", "auto");
                                                        });
                                                        $(".wkzy .title ul li a").click(
                                                                function() {
                                                                    var i = $(this).parent().index();
                                                                    $(this).parent().siblings().removeClass("on");
                                                                    $(this).parent().addClass("on");
                                                                    $_this = $(this).parent().parent().parent().parent().next()
                                                                            .children();
                                                                    $_this.hide();
                                                                    $_this.eq(i).show();
                                                                });
                                                    });
    </script>
</html>