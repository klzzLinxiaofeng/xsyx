<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <link href=" ${ctp}/res/css/extra/add.css" rel="stylesheet">
    <style>
        .select_b .select_div a{
            display: block;
            float: left;
            padding: 0 10px;
            color:grey;
            margin: 0 10px;
        }
        .select_b .select_div a.on{
            background-color: #00a0ea;
            color: #fff;
            border-radius: 5px;
        }
    </style>
    <title></title>
</head>
<body>
<div class="container-fluid">
    <div class="row-fluid">
        <div class="span12">
            <div class="content-widgets white" style="margin-top: 20px;">
                <div class="light_grey"></div>
                <div class="content-widgets">
                    <div class="widget-container">
                        <div class="select_b">
                            <div class="select_div">
                                <a id="micro_mes" data-id="1" href="javascript:void(0)" >微课</a>
                                <a id="design_mes" data-id="2" href="javascript:void(0)">课件</a>
                                <a id="homework_mes" data-id="3" href="javascript:void(0)">作业</a>
                                <a id="exam_mes" data-id="4" href="javascript:void(0)">试卷</a>
                                <a id="plan_mes" data-id="5" href="javascript:void(0)">教案</a>
                                <a id="material_mes" data-id="6" href="javascript:void(0)">素材</a>
                                <a id="learning_mes" data-id="7" href="javascript:void(0)">导学案</a>
                                <a id="other_mes" data-id="99" href="javascript:void(0)">其他</a>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <input type="hidden" value="${nodeId}" id="nId"/>
                        <input type="hidden" value="1" id="type"/>
                        <div id="data_knowledge" class="xkzy_list" style="padding-bottom: 30px;">
                            <jsp:include page="./detailedList.jsp" />
                        </div

                        <div class="clear"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(function(){
        var h = document.documentElement.clientHeight-140;
        $(".content-widgets").css("min-height",h)
        search();
        $(".select_b .select_div a").click(function(){
            var id=$(this).data("id");
            $("#type").val(id);
            $(".select_b .select_div a").removeClass("on");
            $(this).addClass("on");
            search();
        });
    });

    function search() {
        add_on();
        var val = {};
        val.knowledgeNodeId = $("#nId").val();
        val.ownerType = $("#type").val();
        var id = "data_knowledge";
        var url = "/generalcode/knowledgetree/getKnowledgeDetailed";
        myPagination(id, val, url);
    }

    function add_on(){
        var type=$("#type").val();
        var type_length=$(".select_b .select_div a").length;
        $(".select_b .select_div a").removeClass("on");
        for(var i=0;i<type_length;i++){
            if($(".select_b .select_div a").eq(i).data("id")==type){
                $(".select_b .select_div a").eq(i).addClass("on");
                break;
            }
        }
    }

    function previewLp(lpId) {
        var url = "${pageContext.request.contextPath}/learningDesign/learningPlan/edit?type=view&editable=false&id="+lpId;
        location.href=url;
        leftHidden();
    }

    function leftHidden() {
        $(window.parent.document).find(".leftbar").hide();
        $(window.parent.document).find(".left_mu").css({
            width : '15px'
        });
        $(window.parent.document).find(".man_right").css({
            marginLeft : '0px'
        });
        $(window.parent.document).find(".left_close").addClass("left_open");
    }
</script>
</html>