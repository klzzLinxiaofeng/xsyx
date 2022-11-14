<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp" %>
    <link href="${ctp}/res/css/extra/add.css" rel="stylesheet">
    <script type="text/javascript" defer="defer" src="/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>补卡管理</title>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon"/>
        <jsp:param value="补卡管理" name="title"/>
        <jsp:param value="${param.dm}" name="menuId"/>
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white">
                <div class="widget-head">
                    <h3>
                        补卡管理
                        <p class="btn_link" style="float: right;">
                            <a href="javascript:void(0)" class="a3"
                               onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        </p>
                    </h3>
                </div>
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div">
                                <span>名称：</span>
                                <input type="text" id="name" name="name"
                                       style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="请输入学生或者教师名称"
                                       value="">
                            </div>


                            <div class="select_div">
                                <span>提交开始日期：</span>
                                <input type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="startDateStr" id="startDateStr" style="margin-bottom: 0; padding: 6px; width: 100px;" value=""/>
                            </div>
                            <div class="select_div">
                                <span>提交结束日期：</span>
                                <input type="text"   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="endDateStr" id="endDateStr" style="margin-bottom: 0; padding: 6px; width: 100px;" value=""/>
                            </div>

                            <button type="button" class="btn btn-primary" onclick="search()">查询</button>

                            <button type="button" class="btn btn-normal" onclick="exportData()">导出</button>

                            <div class="clear"></div>
                        </div>
                        <table class="responsive table table-striped" id="data-table">
                            <thead>
                            <tr role="row">
                                <th>序号</th>
                                <th>姓名</th>
                                <th>班级/部门</th>
                                <th>补卡理由</th>
                                <th>原卡号</th>
                                <th>补卡卡号</th>
                                <th>提交时间</th>
                                <th style="max-width: 250px;">操作</th>
                            </tr>
                            </thead>
                            <tbody id="canteen_list_content">
                            <jsp:include page="./list.jsp"/>
                            </tbody>
                        </table>
                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                            <jsp:param name="id" value="canteen_list_content"/>
                            <jsp:param name="url" value="/canteen/management/index?sub=list&dm=${param.dm}"/>
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
    function search() {
        var val = {
            "name": $("#name").val(),
        };
        val.startDateStr=$("#startDateStr").val();

        val.endDateStr=$("#endDateStr").val();

        var id = "canteen_list_content";
        var url = "/canteen/management/index?sub=list";
        myPagination(id, val, url);

    }

    function exportData(){

        layer.confirm('确定导出当前全部数据吗？',function(){
            layer.closeAll();
            var val = {
                "name": $("#name").val(),
            };
            val.startDateStr=$("#startDateStr").val();

            val.endDateStr=$("#endDateStr").val();


            location.href="/canteen/management/exportData?name="+val.name+"&startDateStr="+val.startDateStr+"&endDateStr="+val.endDateStr;
        })




    }


function del(id){
    layer.confirm('确定删除吗？', function(index){
        var li=layer.load(2);
        $.post('/canteen/management/delete',{id:id},function(d){
            layer.close(li);
            layer.close(index);
            if(d==0) {
                layer.msg('删除成功', {
                    icon: 1,
                    time: 2000
                }, function () {
                    location.reload();
                });
            }else{
                layer.alert('删除失败');
                layer.close(index);
            }
        })
    });

}



    function loadEditPage(id, isCK) {
        var mes = "编辑";
        if (isCK == 'disable') {
            mes = "详情";
        }
        $.initWinOnTopFromTop(mes, '${pageContext.request.contextPath}/canteen/management/editor?id=' + id, '600', '500');
    }

</script>
</html>