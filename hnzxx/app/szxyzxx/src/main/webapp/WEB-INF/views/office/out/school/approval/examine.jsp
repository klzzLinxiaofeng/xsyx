<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>查看总结</title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>
        .row-fluid .span4 {
            width: 75%;
        }
    </style>
</head>
<body style="background-color:#cdd4d7 !important">
<div class="row-fluid">
    <div class="span4">
        <div class="content-widgets" style="margin-bottom:0">
            <div class="widget-container" style="padding:20px 0 0">
                <form class="form-horizontal tan_form" action="javascript:">
                    <div class="control-group">
                        <label class="control-label" style="width:80px">总结内容</label>
                        <div class="controls" style="margin-left:100px">
                            <textarea type="text" rows="7" style="width:500px;height:180px">${act.summary}</textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" style="width:80px">图片说明</label>
                        <div class="controls" style="margin-left:100px">
                            <c:forEach items="${items}" var="item">
                                <a href="${prefix}${item}" target="_blank"><img src="${prefix}${item}" width="120" ></a>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" type="button" onclick="saveOrUpdate()">确定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function saveOrUpdate() {
        $.closeWindow();
    }
</script>
</html>