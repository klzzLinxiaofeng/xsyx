<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <title></title>
    <%@ include file="/views/embedded/common.jsp" %>
    <style>
        .row-fluid .span13 {
            width: 75%;
        }

        .row-fluid .span4 {
            width: 75%;
        }

        .myerror {
            color: red !important;
            width: 22%;
            display: inline-block;
            padding-left: 10px;
        }
    </style>
</head>
<body style="background-color: cdd4d7 !important">
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets" style="margin-bottom: 0">
            <div class="widget-container" style="padding: 20px 0 0;">
                <form class="form-horizontal tan_form" id="knowledgetree_form" action="javascript:void(0);">
                    <div class="control-group">
                        <label class="control-label"> <span style="color: red">*</span>名称： </label>
                        <div class="controls">
                            <input type="text" id="name" name="name" class="span13" placeholder="" value="${knowledgeTree.name}">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"> 学段： </label>
                        <div class="controls">
                            <select id="stageCode" name="stageCode">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"> <span style="color: red">*</span>科目： </label>
                        <div class="controls">
                            <select id="subjectCode" name="subjectCode">
                            </select>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"> 说明： </label>
                        <div class="controls">
                            <textarea id="description" name="description">${knowledgeTree.description}</textarea>
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label"> 是否禁用 </label>
                        <div class="controls">
                           <select id="disable" name="disable">
                               <option value="0" <c:if test="${knowledgeTree.disable == false}">selected='selected'</c:if> >否</option>
                               <option value="1" <c:if test="${knowledgeTree.disable == true}">selected='selected'</c:if> >是</option>
                           </select>
                        </div>
                    </div>
                    <div class="form-actions tan_bottom">
                        <input type="hidden" id="id" name="id" value="${knowledgeTree.id}"/>
                        <button class="btn btn-warning" type="button"
                                onclick="saveOrUpdate();">确定
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    var checker;
    $(function () {
        //获取科目
        $.PjSubjectSelector({
            "selector" : "#subjectCode",
            "selectedVal":"${knowledgeTree.subjectCode}"
        });

        //获取学段，如果有两个及以上学段是data是以逗号隔开的串
        $.getCurSchoolStageCode(function(data) {
            for(var i = 0; i < data.length; i++){
                var code = data[i];
                var name = "小学";
                if(code == 3){
                    name = "初中";
                }else if(code == 4){
                    name = "高中";
                }
                $("#stageCode").append("<option value='"+code+"'>"+name+"</option>");

                //回显用户选中的值
                if(code == "${knowledgeTree.stageCode}"){
                    $("#stageCode").val(code);
                }
            }
        });

        checker = initValidator();
    });

    function checkName(){
        var url = "${ctp}/generalcode/knowledgetree/checkName";
        var $requestData = {};
        $requestData.treeId = $("#id").val();
        $requestData.stageCode = $("#stageCode").val();
        $requestData.subjectCode = $("#subjectCode").val();
        $.post(url, $requestData, function (data, status) {
            if ("success" === status) {
                if(data === 'true'){
                    $.alert("该学段下该科目已存在知识节点");
                    return true;
                }else{
                    return false;
                }
            }
        });
    }

    function initValidator() {
        return $("#knowledgetree_form").validate({
            errorClass: "myerror",
            rules: {
                "name" : {
                    required : true,
                    maxlength: 25
                },
                "stageCode":{
                    required : true
                },
                "subjectCode":{
                    required : true
                },
                "description":{
                    maxlength: 30
                }
            },
            messages: {
                "name":{
                	required : "知识树名称不能为空",
                	maxlength: "最多只能输入25字"
                },
                "stageCode":"学段必选",
                "subjectCode":"科目必选"
            }
        });
    }

    //保存或更新修改
    function saveOrUpdate(){
        if (checker.form()) {
            var loader = new loadLayer();
            var $id = $("#id").val();
            var $requestData = formData2JSONObj("#knowledgetree_form");
            var url = "${ctp}/generalcode/knowledgetree/creator";
            if ("" != $id) {
                $requestData._method = "put";
                url = "${ctp}/generalcode/knowledgetree/" + $id;
            }

            loader.show();
            $.post(url, $requestData, function (data, status) {
                if ("success" === status) {
                    data = eval("(" + data + ")");
                    if ("success" === data.info) {
                        $.success('操作成功');
                        if (parent.core_iframe != null) {
                            parent.core_iframe.window.location.reload();
                        } else {
                            parent.window.location.reload();
                        }
                        $.closeWindow();
                    } else if("fail" === data.info){
                        $.error("操作失败,该学段该科目知识点已存在");
                    }
                } else {
                    $.error("操作失败,该学段该科目知识点已存在");
                }
                loader.close();
            });
        }
    }

</script>
</html>