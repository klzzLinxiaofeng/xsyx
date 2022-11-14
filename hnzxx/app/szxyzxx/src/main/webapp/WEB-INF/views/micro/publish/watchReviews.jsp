<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file="/views/embedded/common.jsp"%>
        <title>查看评语</title>
    </head>
    <body style="background-color: #F3F3F3 !important">
        <div class="row-fluid ">
            <div class="span12">
                <div style="margin-bottom: 0" class="content-widgets">
                    <div style="padding: 20px 0 40px;" class="widget-container">
                        <div class="pingyu">
                            <c:choose>
                                <c:when test="${mpr.reward == 1}">
                                    <b class="yx"></b>
                                </c:when>
                                <c:when test="${mpr.reward == 2}">
                                    <b class="ex"></b>
                                </c:when>
                                <c:otherwise>
                                    <b class="sx"></b>
                                </c:otherwise>
                            </c:choose>
                            <p>${mpr.reviews}</p>
                        </div>			
                        <div class="form-actions tan_bottom">
                            <button class="btn" onclick="$.closeWindow();" type="button">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script type="text/javascript">
    </script>
</html>