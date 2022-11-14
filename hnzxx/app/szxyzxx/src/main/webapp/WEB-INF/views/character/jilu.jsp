<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <title></title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        评价记录
                        <input value="${studentId}" type="hidden" id="student">
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th><input type="checkbox" id="checkAlls"></th>
                                <th>序号</th>
                                <th>指标名称</th>
                                <th>时间</th>
                                <th>点评教师</th>
                                <th>点评分数</th>
                                <th>留言</th>
                                <th>图片</th>
                                <th>语音留言</th>
                            </tr>
                            </thead>
                            <tbody id="time_list_content">
                            <jsp:include page="./jiluList.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="time_list_content"/>
                            <jsp:param name="url" value="/character/cultivation/findBypingjiaAll?sub=list&studentId=${studentId}"/>
                            <jsp:param name="pageSize" value="${page.pageSize}"/>
                        </jsp:include>
                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    function bofan(id) {
        $("#audio_"+id).play();
    }

</script>
</html>
