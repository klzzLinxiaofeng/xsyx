<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="studystatus" uri="http://www.jiaoxueyun.com/studystatus"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>写评语</title>
    </head>
    <body style="background-color: #F3F3F3 !important">
        <div class="row-fluid ">
            <div class="span12">
                <div style="margin-bottom: 0" class="content-widgets">
                    <div style="padding: 20px 0 0;" class="widget-container">
                        <div style="padding:0 20px;">
                            <table class="table table-bordered table-striped">
                                <thead>
                                    <tr>
                                        <th>学籍号</th>
                                        <th>姓名</th>
                                        <th>完成情况</th>
                                        <th>完成时间</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>${mpr.studentNumber}</td>
                                        <td>${mpr.userName}</td>
                                        <td><p class="<studystatus:getFinishedFlag finishedFlag="${mpr.finishedFlag}"/>"></p></td>
                                        <td><fmt:formatDate value="${mpr.finishedDate}" pattern="yyyy年MM月dd日 HH:mm"/></td>
                                    </tr>
                                </tbody>
                            </table>
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
                            <textarea id="reviews" rows="" cols="">${mpr.reviews}</textarea>
                            <p id="reviewscheck" class="p4">还可以输入100字</p>
                            <div class="clear"></div>
                            <div class="sz_jl">
                                <span>请选择奖励</span>
                                <ul class="star">
                                    <c:choose>
                                        <c:when test="${mpr.reward == 1}">
                                            <li class="active">
                                                <a href="javascript:void(0)"></a>
                                            </li>
                                            <li>
                                                <a href="javascript:void(0)"></a>
                                            </li>
                                            <li>
                                                <a href="javascript:void(0)"></a>
                                            </li>
                                        </c:when>
                                        <c:when test="${mpr.reward == 2}">
                                            <li class="active">
                                                <a href="javascript:void(0)"></a>
                                            </li>
                                            <li class="active">
                                                <a href="javascript:void(0)"></a>
                                            </li>
                                            <li>
                                                <a href="javascript:void(0)"></a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="active">
                                                <a href="javascript:void(0)"></a>
                                            </li>
                                            <li class="active">
                                                <a href="javascript:void(0)"></a>
                                            </li>
                                            <li class="active">
                                                <a href="javascript:void(0)"></a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </ul>
                            </div>
                        </div>			
                        <div class="form-actions tan_bottom">
                            <button onclick="sendReviews();" class="btn btn-warning" type="button">发送</button>
                            <button class="btn" onclick="$.closeWindow();" type="button">取消</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">

                                    function setReviews() {
                                        var text = $("#template option:selected").text();
                                        if (text != "请选择") {
                                            $("#reviews").val(text);
                                        }
                                    }

                                    function getStudentArray() {
                                        var st = [];
                                        st.push(${mpr.userId});
                                        return st;
                                    }

                                    function sendReviews() {
                                        var data = {};
                                        var st = getStudentArray();
                                        data["reviews"] = $.trim($("#reviews").val());
                                        data["studentList"] = st;
                                        data["microPublishId"] = "${mpr.publishedMicroId}";
                                        data["reward"] = $(".star li[class='active']").size();
                                        if (data["reviews"] == null || data["reviews"] == "") {
                                            $.alert("请输入评语");
                                            return;
                                        }
                                        var loader = new loadLayer();
                                        loader.show();
                                        $.ajax({
                                            url: "${pageContext.request.contextPath}/learningDesignpublish/saveReviews",
                                            type: "POST",
                                            data: {"reviewData": JSON.stringify(data)},
                                            async: false,
                                            success: function() {
                                                $.alert("评语发送成功");
                                                var html = "<a onclick=\"editReviews('${mpr.userId}')\" href=\"javascript:void(0)\">修改评语</a>"
                                                          +"<a onclick=\"watchReviews('${mpr.userId}')\" href=\"javascript:void(0)\">查看评语</a>";
                                                $(parent.core_iframe.document.body).find("#userTr_${mpr.userId} .caozuo").html(html);
                                                $.closeWindow();
                                                loader.close();
                                            }
                                        });
                                    }
                                    
                                    function initText() {
                                        var limitNum = 500;
                                        var remain = $("#reviews").val().length;
                                        var result = limitNum - remain;
                                        var pattern = '还可以输入' + result + '字符';
                                        $('#reviewscheck').html(pattern);
                                        $('#reviews').keyup(
                                                function() {
                                                    var remain = $(this).val().length;
                                                    if (remain > limitNum) {
                                                        $(this).val($(this).val().substring(0, limitNum));
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
                                    });
    </script>
</html>