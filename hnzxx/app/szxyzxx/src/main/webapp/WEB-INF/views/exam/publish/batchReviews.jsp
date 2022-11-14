<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>一键写评语</title>
    </head>
    <body style="background-color: #F3F3F3 !important">
        <div class="row-fluid ">
            <div class="span12">
                <div style="margin-bottom: 0" class="content-widgets">
                    <div style="padding: 20px 0 0;" class="widget-container">
                        <div class="all_student">
                            <div class="a_top">
                                <ul>
                                    <c:if test="${allList!=null&&fn:length(allList)>0}">
                                        <li id="allDivLi" onclick="chooseDiv('allDiv')" class="active">
                                            <a href="javascript:void(0)"><input id="allDivC" checked onclick="checkStudent('allDiv')" type="checkbox">全部</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${finishedList!=null&&fn:length(finishedList)>0}">
                                        <li id="finishedDivLi" onclick="chooseDiv('finishedDiv')">
                                            <a href="javascript:void(0)"><input id="finishedDivC" onclick="checkStudent('finishedDiv')" type="checkbox">已完成</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${partFinishedList!=null&&fn:length(partFinishedList)>0}">
                                        <li id="partFinishedDivLi" onclick="chooseDiv('partFinishedDiv')">
                                            <a href="javascript:void(0)"><input id="partFinishedDivC" onclick="checkStudent('partFinishedDiv')" type="checkbox">部分完成</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${unFinishedList!=null&&fn:length(unFinishedList)>0}">
                                        <li id="unFinishedDivLi" onclick="chooseDiv('unFinishedDiv')">
                                            <a href="javascript:void(0)"><input id="unFinishedDivC" onclick="checkStudent('unFinishedDiv')" type="checkbox">未做</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>
                            <div class="a_bottom">
                                <div id="allDiv" class="tx">
                                    <ul>
                                        <c:if test="${allList== null || fn:length(allList) == 0}">
                                            <li>该班没有学员</li>
                                            </c:if>
                                            <c:forEach items="${allList}" var="st">
                                            <li>
                                                <img src="<avatar:avatar userId='${st.id}'></avatar:avatar>">
                                                <input value="${st.userId}" type="checkbox">
                                                <p class="name">${st.name}</p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div style="display:none;" id="finishedDiv" class="tx">
                                    <ul>
                                        <c:forEach items="${finishedList}" var="st">
                                            <li>
                                                <img src="<avatar:avatar userId='${st.id}'></avatar:avatar>">
                                                <input value="${st.userId}" type="checkbox">
                                                <p class="name">${st.name}</p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div style="display:none;" id="partFinishedDiv" class="tx">
                                    <ul>
                                        <c:forEach items="${partFinishedList}" var="st">
                                            <li>
                                                <img src="<avatar:avatar userId='${st.id}'></avatar:avatar>">
                                                <input value="${st.userId}" type="checkbox">
                                                <p class="name">${st.name}</p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div style="display:none;" id="unFinishedDiv" class="tx">
                                    <ul>
                                        <c:forEach items="${unFinishedList}" var="st">
                                            <li>
                                                <img src="<avatar:avatar userId='${st.id}'></avatar:avatar>">
                                                <input value="${st.userId}" type="checkbox">
                                                <p class="name">${st.name}</p>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>	
                        <div class="py">
                            <p class="p1">请输入评语</p>
                            <div class="p2">使用模板 
                                <select id="template" onchange="setReviews()">
                                    <option>请选择</option>
                                    <option>完成的不错</option>
                                    <option>继续努力</option>
                                </select>
                            </div>
                            <textarea id="reviews" rows="" cols="" ></textarea>
                             <p id="reviewscheck" class="p4">还可以输入100字</p>
                            <div class="clear"></div>
                            <div class="sz_jl"><span>请选择奖励</span>
                                <ul class="star">
                                    <li class="active">
                                        <a href="javascript:void(0)"></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0)"></a>
                                    </li>
                                    <li>
                                        <a href="javascript:void(0)"></a>
                                    </li>
                                </ul>
                            </div>
                        </div>			
                        <div class="form-actions tan_bottom">
                            <button onclick="sendReviews();" class="btn btn-warning" onclick="javascript:void(0)" type="button">发送</button>
                            <button class="btn" onclick="$.closeWindow();" type="button">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
                                            function chooseDiv(id) {
                                                $(".all_student .a_top ul li").removeClass("active");
                                                $("#" + id + "Li").addClass("active");
                                                $(".all_student .a_bottom .tx").hide();
                                                $("#" + id).toggle();
                                            }

                                             function checkStudent(id) {
                                                chooseDiv(id);
                                                $(".a_top ul li input[type='checkbox']").each(function(){
                                                    if($(this).attr("id")!=(id+"C")){
                                                        //not ie
                                                        $(this).prop('checked', false);
                                                        //ie
                                                        $(this).removeAttr("checked");
                                                    }
                                                });
                                                $(".a_bottom .tx").each(function(){
                                                    $(this).find("input[type='checkbox']").each(function(){
                                                        //not ie
                                                        $(this).prop('checked', false);
                                                        //ie
                                                        $(this).removeAttr("checked");
                                                    });
                                                });
                                                var cf = $("#" + id + "C").is(":checked");
                                                $("#" + id + " input[type='checkbox']").each(function() {
                                                    $(this).prop('checked', cf);
                                                    if(cf){
                                                        $(this).attr('checked',"checked");
                                                    }else{
                                                        $(this).removeAttr("checked");
                                                    }
                                                });   
                                            }

                                            function setReviews() {
                                                var text = $("#template option:selected").text();
                                                if (text != "请选择") {
                                                    $("#reviews").val(text);
                                                }
                                            }

                                            function getStudentArray() {
                                                var st = [];
                                                $(".a_bottom input[type='checkbox']:checked").each(function() {
                                                    st.push(parseInt($(this).val()));
                                                });
                                                //去重
                                                $.unique(st);
                                                return st;
                                            }

                                            function sendReviews() {
                                                var data = {};
                                                var st = getStudentArray();
                                                data["reviews"] = $.trim($("#reviews").val());
                                                data["studentList"] = st;
                                                data["microPublishId"] = "${microPublishId}";
                                                data["reward"] = $(".star li[class='active']").size();
                                                if (st.length <= 0) {
                                                    $.alert("请选择学生");
                                                    return;
                                                }
                                                if (data["reviews"] == null || data["reviews"] == "") {
                                                    $.alert("请输入评语");
                                                    return;
                                                }
                                                var loader = new loadLayer();
                                                loader.show();
                                                $.ajax({
                                                    url: "${pageContext.request.contextPath}/exampublish/saveReviews",
                                                    type: "POST",
                                                    data: {"reviewData": JSON.stringify(data)},
                                                    async: false,
                                                    success: function() {
                                                        $.alert("评语发送成功");
                                                        if (parent.core_iframe != null) {
                                                            parent.core_iframe.window.location.href = "${pageContext.request.contextPath}/exampublish/publishDetails?index=index&microPublishedId=${microPublishId}&relateId=${relateId}&relateType=${relateType}";
                                                        } else {
                                                            parent.window.location.href = "${pageContext.request.contextPath}/exampublish/publishDetails?index=index&microPublishedId=${microPublishId}&relateId=${relateId}&relateType=${relateType}";
                                                        }
                                                        $.closeWindow();
                                                        loader.close();
                                                    }
                                                });
                                            }
                                            
                                            function initText() {
                                                var limitNum = 500;
                                                var pattern = '还可以输入' + limitNum + '字符';
                                                $('#reviewscheck').html(pattern);
                                                $('#reviews').keyup(
                                                        function() {
                                                            var remain = $(this).val().length;
                                                            if (remain > limitNum) {
                                                                $(this).val($(this).val().substring(0,limitNum));
                                                                pattern = "<font color='red'>字数超过限制！</font>";
                                                            } else {
                                                                var result = limitNum - remain;
                                                                pattern = '还可以输入' + result + '字符';
                                                            }
                                                            $('#reviewscheck').html(pattern);
                                                        }
                                                );
                                            }

                                            $(function() {
                                                /* 评星 */
                                                $(".star li a").click(function() {
                                                    var i = $(this).parent().index();
                                                    $(".star li").removeClass("active");
                                                    for (j = 0; j <= i; j++) {
                                                        $(".star li").eq(j).addClass("active");
                                                    }
                                                });
                                                initText();
                                                checkStudent("allDiv");
                                            });
    </script>
</html>