<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <%@ include file="/views/embedded/common.jsp"%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${sca:getDefaultSchoolName()}</title>
        <style type="text/css">
            .form-horizontal .control-label{width:30px;}
            .form-horizontal .controls {
                margin-left: 40px;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <jsp:include page="/views/embedded/navigation.jsp">
                <jsp:param value="fa-calculator" name="icon"/>
                <jsp:param value="在线课堂" name="title" />
                <jsp:param value="${param.dm}" name="menuId" />
            </jsp:include>
            <div class="row-fluid ">
                <div class="span12">
                    <div class="content-widgets white" style="margin-bottom: 0;">
                        <div class="widget-container">
                            <form id="dept_form" class="form-horizontal" novalidate="novalidate">
                                <div class="control-group">
                                    <label class="control-label">
                                        <font style="color:red">*</font>时间
                                    </label>
                                    <div class="controls">
                                        <input id="startDate" type="text"  onclick="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate: '%y-%M-%d', maxDate: '#F{$dp.$D(\'finishedDate\')}'})" placeholder="开始日期" class="span2">
                                        <select id="startClock" class="span1">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                            <option>8</option>
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                            <option>13</option>
                                            <option>14</option>
                                            <option>15</option>
                                            <option>16</option>
                                            <option>17</option>
                                            <option>18</option>
                                            <option>19</option>
                                            <option>20</option>
                                            <option>21</option>
                                            <option>22</option>
                                            <option>23</option>
                                            <option>24</option>
                                        </select>
                                        <span style="margin:0 10px 0 5px;">点</span><span style="margin:0 5px 0 10px;">至</span>
                                        <input id="finishedDate" type="text" onclick="WdatePicker({dateFmt: 'yyyy-MM-dd', minDate: '#F{$dp.$D(\'startDate\')}', maxDate: ''})" placeholder="结束日期" class="span2">
                                        <select id="finishedClock" class="span1">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                            <option>6</option>
                                            <option>7</option>
                                            <option>8</option>
                                            <option>9</option>
                                            <option>10</option>
                                            <option>11</option>
                                            <option>12</option>
                                            <option>13</option>
                                            <option>14</option>
                                            <option>15</option>
                                            <option>16</option>
                                            <option>17</option>
                                            <option>18</option>
                                            <option>19</option>
                                            <option>20</option>
                                            <option>21</option>
                                            <option>22</option>
                                            <option>23</option>
                                            <option selected>24</option>
                                        </select>
                                        <span style="margin:0 10px 0 5px;">点</span>
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label"><font style="color:red">*</font>班级</label>
                                    <div id="gradeClassDiv" class="controls">
                                        <div class="banji">
                                            <c:choose>
                                                <c:when test="${fn:length(classGradeMap)<=0}">
                                                    <div class="tishi">该用户没有分配任何班级</div>  
                                                </c:when>
                                                <c:otherwise>
                                                    <ul class="nj">
                                                        <c:forEach varStatus="st" items="${classGradeMap}" var="cm">
                                                            <li>
                                                                <a onclick="changeClass('${st.index}');" href="javascript:void(0)">${fn:split(cm.key,"&&")[0]}</a>
                                                                <input type="hidden" value="${fn:split(cm.key,'&&')[1]}" />
                                                            </li>
                                                        </c:forEach>
                                                        <!--                                                <li><a href="javascript:void(0)">小组</a></li>-->
                                                    </ul>
                                                    <div class="clear"></div>
                                                    <c:forEach items="${classGradeMap}" var="cm">
                                                        <div class="fenban" style="display:none" >
                                                            <ul>
                                                                <c:forEach items="${cm.value}" var="teamMap">
                                                                    <c:forEach items="${teamMap}" var="team">
                                                                        <li>
                                                                            <input value="${team.key}" onclick="checkClassSubject(this)" type="checkbox" data-type="${fn:split(team.value,'&&')[1]}"/>
                                                                            ${fn:split(team.value,"&&")[0]}
                                                                            <input type="hidden" value="${fn:split(team.value,'&&')[1]}" />
                                                                        </li>
                                                                    </c:forEach>
                                                                </c:forEach>
                                                            </ul>
                                                        </div>
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                            <div class="tishi" ></div>
                                        </div>
                                    </div>
                                </div>
                                <div id="microMainDiv" class="control-group">
                                    <label class="control-label"><font style="color:red">*</font>布置</label>
                                    <div class="controls">
                                        <div class="banji">
                                            <ul class="nj">
                                                <!--                                                <li><a onclick="changeMicroTab($(this).attr('id'))" id="commonMicro" href="javascript:void(0)">公共课件库</a></li>-->
                                                <li><a onclick="changeMicroTab($(this).attr('id'))" id="myMicro" href="javascript:void(0)">我的课件</a></li>
                                                <li><a href="${pageContext.request.contextPath}/ladspublish/prepareLessonIndex" >我要备课</a></li>
                                                <!--                                                <li><a onclick="changeMicroTab($(this).attr('id'))" id="upload" href="javascript:void(0)">上传课件</a></li>-->
                                            </ul>
                                            <div class="ts">
                                                <!--                                                <div class="yx_weike">
                                                                                                    <div class="yx_num">
                                                                                                        <a id="clearButton" onclick="clearMicro();" href="javascript:void(0)" class="close_all" style="display:none" >
                                                                                                            <i class="fa fa-trash-o"></i>清空
                                                                                                        </a>
                                                                                                        <p class="yx">
                                                                                                            <a onclick="showChosenMicroDiv()" href="javascript:void(0)">已选课件<span id="chosenMicroSize">0</span></a>
                                                                                                        </p>
                                                                                                    </div>
                                                                                                    <div id="chosenMicroDiv" class="yx_wk" style="display:none">
                                                                                                        <ul>
                                                                                                        </ul>
                                                                                                    </div>
                                                                                                </div>-->

                                            </div>
                                            <div class="clear"></div>
                                            <div class="weike" >

                                            </div>
                                            <div class="tishi" ></div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            var chosenMicroJson = {"microList": []};
            //课件的来源
            var source;
            function changeMicroTab(type) {
                var loader = new loadDialog();
                loader.show();
                $("#microMainDiv .controls .banji .nj li a").each(function () {
                    if ($(this).attr("id") == type) {
                        $(this).parent().addClass("active")
                    } else {
                        $(this).parent().removeClass("active");
                    }
                });
                var url = "${pageContext.request.contextPath}";
                if (type == "myMicro") {
                    //我的课件
                    url = url + "/ladspublish/myLearningDesign?index=index";
                    source = "来自我的课件";
                } else if (type == "commonMicro") {
                    //公共课件库
                    var subjectCode = $(".fenban input[type='checkbox']").first().next().val();
                    var gradeCode = $(".nj .active input[type='hidden']").val();
                    url = url + "/resource/searcher/index?stageCode=2&subjectCode=" + subjectCode + "&gradeCode=" + gradeCode + "&resType=<%=ResourceType.LEARNING_DESIGN%>";
                    source = "来自公共课件库";
                } else if (type == "upload") {
                    //本地上传
                    url = url + "/learningDesignhouse/upload?publish=publish";
                    source = "来自我的课件";
                }

                $(".weike").load(url, function () {
                    loader.close();
                });
            }

            function chooseMicro(mid) {
                var removeFlag = null;
                if (chosenMicroJson.microList.length > 0) {
                    for (var i = 0; i < chosenMicroJson.microList.length; i++) {
                        var micro = chosenMicroJson.microList[i]
                        if (micro.id == mid) {
                            removeFlag = i;
                        }
                    }
                }
                if (removeFlag != null) {
                    chosenMicroJson.microList.splice(removeFlag, 1);
                    $("#chosenMicroLi_" + mid).remove();
                } else {
                    var title = $("#microId_" + mid).prev().text();
                    var resourceId=$("#resourceId_" + mid).val();
                    addMicro(mid, title,resourceId);
                }
                setSize();
            }

            function addMicro(mid, title,resourceId) {
                var micro = {"id": mid, "title": title,"resourceId":resourceId};
                chosenMicroJson.microList.push(micro);
                var html = "<li id=\"chosenMicroLi_" + mid + "\"><a onclick=\"previewMicro('" + mid + "')\" href=\"javascript:void(0)\" class=\"name\">" + title
                        + "</a><p>" + source + "</p><a onclick=\"clearMicro('" + mid + "')\" href=\"javascript:void(0)\" class=\"close_1\"></a></li>";
                $("#chosenMicroDiv ul").prepend(html);
            }

            function removeUploadMicro() {
                changeMicroTab("upload");
            }

            function clearMicro(mid) {
                if (mid != null && mid != "") {
                    for (var x = 0; x < chosenMicroJson.microList.length; x++) {
                        var id = chosenMicroJson.microList[x].id;
                        if (id == mid) {
                            chosenMicroJson.microList.splice(x, 1);
                            $("#chosenMicroLi_" + mid).remove();
                            //去除勾选
                            $("#microCheck_" + mid).attr('checked', false);
                            break;
                        }
                    }
                    setSize();
                } else {
                    $.confirm("确定清空已选课件吗?", function () {
                        chosenMicroJson.microList = [];
                        $("#chosenMicroDiv ul").html("");
                        //去除所有勾选
                        $("#microLessonDiv input[type='checkbox']").each(function () {
                            $(this).attr('checked', false);
                        });
                        setSize();
                    });
                }
            }

            function showChosenMicroDiv(always) {
                if (always != null) {
                    //持久显示,不toggle
                    $("#chosenMicroDiv").show();
                    $(".yx_weike").css("width", "300px");
                    $("#clearButton").show();
                } else {
                    if ($("#chosenMicroDiv").is(":hidden")) {
                        $("#chosenMicroDiv").show();
                        $(".yx_weike").css("width", "300px");
                    } else {
                        $("#chosenMicroDiv").hide();
                        $(".yx_weike").css("width", "117px");
                    }
                    $("#clearButton").toggle();
                }
            }

            function setSize() {
                $("#chosenMicroSize").text(chosenMicroJson.microList.length);
            }

            function checkSelected() {
                //检查跳页后是否有已选课件
                $("#microLessonDiv input[type='checkbox']").each(function () {
                    for (var i = 0; i < chosenMicroJson.microList.length; i++) {
                        var micro = chosenMicroJson.microList[i];
                        if (micro.id == $(this).attr("id").split("_")[1]) {
                            $(this).attr('checked', true);
                            break;
                        }
                    }
                });
            }

            function publishLesson(mid, ldId) {
                var data = {};
                data["startDate"] = $("#startDate").val();
                data["finishedDate"] = $("#finishedDate").val();
                data["startClock"] = $("#startClock option:selected").text();
                data["finishedClock"] = $("#finishedClock option:selected").text();
                data["title"] = $("#microId_" + mid).prev().text();
                data["classList"] = getSelectedClasses();
                if (data["startDate"] == null || data["startDate"] == "") {
                    $.alert("请输入开始日期");
                    return;
                }
                if (data["finishedDate"] == null || data["finishedDate"] == "") {
                    $.alert("请输入结束日期");
                    return;
                }
                if (data["classList"] == null || data["classList"].length <= 0) {
                    $.alert("请选择要发布的班级");
                    return;
                }
                var loader = new loadDialog();
                loader.show();
                //跨域
                $.getJSON("<%=SysContants.COMMON_RESOURCE_BASE_PATH%>/lads/common/copyLD?ldId=" + ldId + "&callback=?",
                        function (json) {
                            if (json!=null&&json.copyldId!= "fail") {
                                data["ldId"] = json.copyldId;
                                data["microId"] = mid;
                                $.ajax({
                                    url: "${pageContext.request.contextPath}/ladspublish/publishLesson",
                                    type: "POST",
                                    data: {"publishData": JSON.stringify(data)},
                                    async: false,
                                    success: function () {
                                        var relateId = data["classList"][0].relateId;
                                        location.href = "${pageContext.request.contextPath}/ladspublish/publishManagerIndex?relateId="+relateId;
                                    }
                                });
                            } else {
                                $.alert("后台错误,布置失败");
                            }
                        });
            }

            function getSubjectName(text) {
                var subjectName = text.substring(text.indexOf("[") + 1);
                subjectName = subjectName.substring(0, subjectName.length - 1);
                return subjectName;
            }

            function checkClassSubject(obj) {
                var text = $(obj).parent().text();
                var subjectName = getSubjectName(text);
                $("#gradeClassDiv .fenban li").each(function () {
                    var esn = getSubjectName($(this).text());
                    if (subjectName == esn) {
                        $(this).find("input[type='checkbox']").removeAttr("disabled");
                    } else {
                        $(this).find("input[type='checkbox']").attr("disabled", true);
                    }
                });
                if ($("#gradeClassDiv .fenban li input[type='checkbox']:checked").size() <= 0) {
                    $("#gradeClassDiv .fenban li input[type='checkbox']").each(function () {
                        $(this).removeAttr("disabled");
                    });
                }
            }

            function getSelectedClasses() {
                var classes = new Array();
                $("#gradeClassDiv .fenban input[type='checkbox']:checked").each(function () {
                    var cj = {"relateId": $(this).val(), "relateName": $.trim($(this).parent().text()),"relateType":$(this).attr("data-type")};
                    classes.push(cj);
                });
                return classes;
            }

            function changeClass(index) {
                $("#gradeClassDiv .nj li").each(function (x) {
                    if (index == x) {
                        $(this).addClass("active");
                    } else {
                        $(this).removeClass("active");
                    }
                });
                $("#gradeClassDiv .fenban").each(function (i) {
                    if (index == i) {
                        $(this).show();
                    } else {
                        $(this).hide();
                    }
                });
            }

            function previewMicro(mid) {
                var mes = "预览";
                $.initWinOnTopFromLeft(mes, '${pageContext.request.contextPath}/learningDesignpublish/preview?objId=' + mid, '700', '500');
            }

            $(function () {
                //默认开始时间为当前日期时间
                $("#startDate").val(new Date().Format("yyyy-MM-dd"))
                //默认结束时间为当前日期24点
                $("#finishedDate").val(new Date().Format("yyyy-MM-dd"))
                changeClass(0);
                changeMicroTab("myMicro");
            });

        </script>
    </body>
</html>
