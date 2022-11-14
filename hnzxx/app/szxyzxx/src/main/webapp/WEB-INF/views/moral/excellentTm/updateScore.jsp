<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"
          media="all">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <style>
        .control-group {
            width: 100%;
            margin-left: auto;
            margin-right: auto;
        }

        label {
            display: inline;
        }

        .form-horizontal .control-label {
            text-align: left;
            margin-left: 40px;
            width: 83px;
        }

        .form-horizontal .controls {
            margin-left: 10px !important;
        }

        /* 新增样式 */
        .box {
            float: left;
            position: relative;
        }

        .box button {
            position: absolute;
        }

        #commit {
            z-index: 99;
        }
    </style>
</head>
<body >
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="publicClass_form" action="javascript:void(0);">

                    <div class="control-group">
                        <label class="layui-form-label">汇报材料得分<span style="color: red">*</span></label>
                        <div class="layui-input-block">
                            <input type="number" style="margin-top: 8px" name="workDataScore" id="workDataScore">
                        </div>
                    </div>

<%--                    <div class="control-group">--%>
<%--                        <label class="layui-form-label">班级量化得分<span style="color: red">*</span></label>--%>
<%--                        <div class="layui-input-block">--%>
<%--                            <input type="number" style="margin-top: 8px" name="teamScore" id="teamScore">--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="control-group">--%>
<%--                        <label class="layui-form-label">学生评教得分<span style="color: red">*</span></label>--%>
<%--                        <div class="layui-input-block">--%>
<%--                            <input type="number" style="margin-top: 8px" name="studentEvaluateScore" id="studentEvaluateScore">--%>
<%--                        </div>--%>
<%--                    </div>--%>



                    <div class="form-actions tan_bottom">
                        <button class="btn btn-warning" lay-submit lay-filter="sub">确定</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>





<script type="text/javascript">
    layui.use(['form'],function(){
        var form = layui.form;

        form.on('submit(sub)', function(data){
            var param={
                workDataScore:$("#workDataScore").val(),
                teamScore:$("#teamScore").val(),
                studentEvaluateScore:$("#studentEvaluateScore").val(),
                id:${id}
            }

            if(param.workDataScore=="" ){
                layer.alert("请填写分数");
                return false;
            }
            param.sumScore=parseInt(param.workDataScore);
            layer.load(2);
            $.post('/dy/excellentTm/updateScore',param,function(d){
                layer.closeAll('loading');
                d=JSON.parse(d);
                if(d.code!=200){
                    layer.msg(d.msg);
                    return;
                }
                layer.msg('评分成功', {
                    icon: 1,
                    time: 2000
                }, function () {
                    parent.layer.closeAll();
                    parent.reloadTable();
                });
            })
            return false;
        });

    });

</script>
