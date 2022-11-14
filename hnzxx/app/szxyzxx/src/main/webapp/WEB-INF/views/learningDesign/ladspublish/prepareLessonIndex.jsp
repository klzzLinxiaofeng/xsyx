<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ include file="/views/embedded/common.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sca:getDefaultSchoolName()}</title>
        <style type="text/css">
            a{
                text-decoration: none;
            }
            h4{
                font-size:20px;font-family:'微软雅黑';color: #616f77;background: #fff;height: 45px;line-height: 45px;width: 100%;margin: 0;padding-left: 15px;border-bottom: 1px #d9dfe7 solid;
            }
            .assist{
                background:#456a8c;width: 230px;float: left;height: 790px;margin-right:40px;
            }
            .assist a{
                color:#b8d3ec;height: 60px;width: 100%;float: left;text-align: center;line-height: 60px;font-size:16px;font-weight: bold;
            }
            .assist a p{
                width: 180px;overflow: hidden;margin: 0 auto;display:block;white-space:nowrap; overflow:hidden; text-overflow:ellipsis;
            }
            .assist a:hover{
                background:#f0f0f0;color: #456a8c;font-size:16px;font-weight: bold;
            }
            a.hit{
                background:#f0f0f0;color: #456a8c;font-size:16px;font-weight: bold;
            }
            .way{
                width:42%;float: left;margin-top: 30px;
            }
            .explain{
                border:1px #d4d4d4 solid;min-height: 300px;background: #fff;
            }
            h6{
                color: #456a8c;font-size: 16px;text-align: center;font-weight: bold;margin: 0 0 10px 0;
            }
            .explain p{
                border:0px;margin: 10px;width: 96%;min-height: 300px;resize:none;color: #444;font-size:16px;
            }
            .but a{
                border:0px;background:#ef8c3c;color: #f0f0f0;font-size:14px;font-weight: bold;height: 35px;line-height: 35px;margin-top: 10px;float: right;width:18%;text-align: center;cursor:pointer;
            }
        </style>
        <script type="text/javascript">
            $(function () {
                $(".figure").hide();//隐藏wenben
                $(".figure:eq(0)").show();//显示第一个wenben
                $("#assist a").click(function () {
                    $(".hit").removeClass("hit");//移除样式
                    $(this).addClass("hit");//添加样式
                    var i = $(this).index();//获得下标
                    $(".figure").hide();//隐藏wenben
                    $(".figure:eq(" + i + ")").show();//显示第i个wenben
                });
            })
        </script>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-asterisk" name="icon" />
                <jsp:param value="备课" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div>
                <div style="overflow: hidden;">
                    <h4>备课&nbsp;&nbsp;&nbsp;&nbsp;(多种专业教学模式可选)</h4>
                </div>
                <div class="assist" id="assist">
                    <a href="javascript:void(0)" id="free" class="hit"><p>自由创作备课</p></a>
                    <a href="javascript:void(0)" id="cdjss" ><p>（一）传递--接受式</p></a>
                    <a href="javascript:void(0)" id="zxfds"><p>（二）自学--辅导式</p></a>
                    <a href="javascript:void(0)" id="tjsjx"><p>（三）探究式教学</p></a>
                    <a href="javascript:void(0)" id="gnhdms"><p>（四）概念获得模式</p></a>
                    <a href="javascript:void(0)" id="btldz"><p>（五）巴特勒学习模式</p></a>
                    <a href="javascript:void(0)" id="pmsjx"><p>（六）抛锚式教学</p></a>
                    <a href="javascript:void(0)" id="fljxms"><p>（七）范例教学模式</p></a>
                    <a href="javascript:void(0)" id="xxfsms"><p>（八）现象分析模式</p></a>
                    <a href="javascript:void(0)" id="jnms"><p>（九）加涅模式</p></a>
                    <a href="javascript:void(0)" id="asbems"><p>（十）奥苏贝尔模式</p></a>
                    <a href="javascript:void(0)" id="hzxxms"><p>（十一）合作学习模式</p></a>
<!--                    <a href="javascript:void(0)" id="fxsms"><p>（十二）发现式模式</p></a>-->
                </div>
                <div id="free_content" class="figure">
                    <div class="way">
                        <h6>自由创作备课</h6>
                        <div class="explain">
                            <p>自由创作备课，适合对平台已经很熟悉的教师
                            </p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="cdjss_content" class="figure">
                    <div class="way">
                        <h6>（一）传递——接受式</h6>
                        <div class="explain">
                            <p>该教学模式源于赫尔巴特的四段教学法，后来由前苏联凯洛夫等人进行改造传入我国。在我国广为流行，很多教师在教学中自觉不自觉地都用这种方法教学。该模式以传授系统知识、培养基本技能为目标。其着眼点在于充分挖掘人的记忆力、推理能力与间接经验在掌握知识方面的作用，使学生比较快速有效地掌握更多的信息量。该模式强调教师的指导作用，认为知识是教师到学生的一种单向传递的作用，非常注重教师的权威性。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=03da1d3b9f43499c9d1e1d8162309fc4" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="zxfds_content" class="figure">
                    <div class="way">
                        <h6>（二）自学——辅导式</h6>
                        <div class="explain">
                            <p>自学辅导式的教学模式是在教师的指导下自己独立进行学习的模式。这种教学模式能够培养学生的独立思考能力，在教学实践中也有很多教师在运用它。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=d5cfe3881ee747c3a5786f98b3544a92" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="tjsjx_content" class="figure">
                    <div class="way">
                        <h6>（三）探究式教学</h6>
                        <div class="explain">
                            <p>探究式教学以问题解决为中心的，注重学生的独立活动，着眼于学生的思维能力的培养。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=4381b0696ed84478966aba94e92ad04c" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="gnhdms_content" class="figure">
                    <div class="way">
                        <h6>（四）概念获得模式</h6>
                        <div class="explain">
                            <p>该模式的目标是使学习者通过体验所学概念的形成过程来培养他们的思维能力。该模式主要反映了认知心理学的观点，强调学习是认知结构的组织与重组的观点。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=4edbf71448da4645a39325c3c961ce7a" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="btldz_content" class="figure">
                    <div class="way">
                        <h6>（五）巴特勒学习模式</h6>
                        <div class="explain">
                            <p>20世纪70年代美国教育心理学家巴特勒提出教学的7要素，并提出“七段”教学论，在国际上影响很大。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=887a02a60b6b4b1e98cc6f22f997d84f" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="pmsjx_content" class="figure">
                    <div class="way">
                        <h6>（六）抛锚式教学</h6>
                        <div class="explain">
                            <p>这种教学要求建立在有感染力的真实事件或真实问题的基础上。确定这类真实事件或问题被形象地比喻为“抛锚”，因为一旦这类事件或问题被确定了，整个教学内容和教学进程也就被确定了(就像轮船被锚固定一样)。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=a6fd30b137cb4adc9569bfeba3ef21c8" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="fljxms_content" class="figure">
                    <div class="way">
                        <h6>（七）范例教学模式</h6>
                        <div class="explain">
                            <p>范例教学模式比较适合原理、规律性的知识。是中学思想政治课教学最基础的内容之一。他是德国教育实践家M·瓦根舍因提出来的。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=fc257f78954b4b1fa75de8e7a9386434" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="xxfxms_content" class="figure">
                    <div class="way">
                        <h6>（八）现象分析模式</h6>
                        <div class="explain">
                            <p>它主要基于建构主义的认知理论，非常注意学生利用自己的先前经验对问题进行解释。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=9da19927b124454e94e6473567eef2ed" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="jnms_content" class="figure">
                    <div class="way">
                        <h6>（九）加涅模式</h6>
                        <div class="explain">
                            <p>依据信息加工理论，加涅认为学习的条件分为内部条件和外部条件，内部条件又进一步分为基本先决条件和支持性的先决条件。支持性的先决条件在学习过程中起辅助作用，但是没有这些条件学习也可以发生，而如果缺少基本先决条件则是不行的。不同的学习类别需要不同的学习条件，并能产生五种类型的学习结果：言语信息、智力技能、认知策略、动作技能、态度。言语信息包括名称、符号、事实和原则。为了使言语信息的学习得以发生，言语信息的内容对学习者必须是有意义的。考查言语信息是否掌握，必须对一些事实进行提问。 智慧技能，包括辨别、概念、规则、和高级规则。智慧技能的学习是通过呈现许多规则和例子以指导学习者找到正确的答案。可以通过要求学习者解决特定的问题来考查学习结果。认知策略，对这种技能的教学方法是演示或说明策略后，学习者练习，一旦学生熟悉了一个问题，新的问题要呈现，以帮助学生将策略迁移，或者评价学生对策略的掌握。动作技能，反复练习对这种技能的掌握是关键。可以通过完成任务的时间或者精确性来测试对动作技能的掌握。态度，强化相依原理在态度学习中起主要作用。
                                加涅的学习层级论主要适用于智慧技能的学习。学习层级论，也称累积学习理论，其基本观点是：学习任何新的智慧技能都需要某种先前的学习，学习是累积性的。按照复杂性程度的不同，由简单到复杂，加涅将智慧技能分为八个层次：信号学习、刺激－反应学习、连锁学习、言语联想、辨别学习、概念学习、规则学习和高级规则学习。其中前四类学习是学习的基础形式，总称联想学习。学校教育更关注的是后面四类的学习。
                                加涅把人的学习过程等同于电脑对信息的加工处理，在他的学习理论中要点是：注意、选择性知觉、复诵、语义编码、提取、反应组织、反馈。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=ac9b955bb4564d06b2520344a62d7b8a" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="asbems_content" class="figure">
                    <div class="way">
                        <h6>（十）奥苏贝尔模式</h6>
                        <div class="explain">
                            <p>它主要基于建构主义的认知理论，非常注意学生利用自己的先前经验对问题进行解释。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=4a97e0aea1694f3ca7a4b5aff12f9db1" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
                <div id="hzxxms_content" class="figure">
                    <div class="way">
                        <h6>（十一）合作学习模式</h6>
                        <div class="explain">
                            <p>它是一种通过小组形式组织学生进行学习的一种策略。小组取得的成绩与个体的表现是紧密联系的。约翰逊(D.W.Johnson，1989)认为合作式学习必须具备五大要素：①个体积极的相互依靠，②个体有直接的交流，③个体必须都掌握给小组的材料，④个体具备协作技巧，⑤群体策略。合作式学习有利于发展学生个体思维能力和动作技能，增强学生之间的沟通能力和包容能力，还能培养学生的团队精神，提高学生的学业成绩。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=8e34d050d2da444099def1ce8131d9e1" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>
<%--                <div id="fxsms_content" class="figure">
                    <div class="way">
                        <h6>（十二）发现式模式</h6>
                        <div class="explain">
                            <p>发现式学习是培养学生探索知识、发现知识为主要目标的一种教学模式。这种模式最根本的地方在于让学生像科学家的发现一样来体验知识产生的过程。布鲁纳(J.S.Bruner)认为发现式教学法有四个优点：
                                1．提高学生对知识的保持。
                                2．教学中提供了便于学生解决问题的信息，可增加学生的智慧潜能。
                                3．通过发现可以激励学生的内在动机，引发其对知识的兴趣。
                                4．学生获得了解决问题的技能。
                                根据许多心理学家对这种教学模式的研究，它更适合于低年级的教学，而且在课堂上运用太费时间，又难以掌握。</p>
                        </div>
                        <div class="but">
                            <a href="<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/resource/ladsAuthor?relateAppId=<%=SysContants.SYSTEM_APP_ID%>&templateId=" target="_blank">开始备课</a>
                        </div>
                    </div>
                </div>--%>
            </div>

        </div>
    </body>
</html>
