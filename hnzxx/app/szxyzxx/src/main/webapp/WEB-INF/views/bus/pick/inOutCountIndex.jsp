<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <script type="text/javascript" defer="defer" src="/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <link href="${pageContext.request.contextPath}/res/css/extra/add.css" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/css/layui.css"  media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/res/plugin/layui-2.5.7/layui.all.js"></script>
    <title>校车接送统计</title>
    <style>
        .breadcrumb{
            display: none;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <jsp:include page="/views/embedded/navigation.jsp">
        <jsp:param value="fa-asterisk" name="icon" />
        <jsp:param value="kkkkkk" name="menuId" />
    </jsp:include>
    <div class="row-fluid">
        <div class="span12">
            <div class="layui-tab layui-tab-brief" lay-filter="docDemoTabBrief">
                <ul class="layui-tab-title">

                    <li  id="busPick">校车接送</li>
                    <li  id="parentPick">家长接送</li>
                    <li class="layui-this" >进出校统计</li>
                </ul>
            </div>
            <div class="layui-tab-content" style="height: 100px;">
                <div class="layui-tab-item layui-show">
                    <div class="content-widgets white">
                        <div class="widget-head">
                        </div>
                        <div class="light_grey"></div>
                        <div class="content-widgets">
                            <div class="widget-container">
                                <div class="select_b">
                                    <div class="select_div">
                                        <span>日期：</span>
                                        <input type="text" id="date"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="startDate" style="margin-bottom: 0; padding: 6px; width: 100px;" value="${date}"/>
                                    </div>

                                    <div class="select_div"  >
                                        <span>进出状态：</span>
                                        <select id="status" style="width: 120px;">
                                            <option value="">--全部--</option>
                                            <option value="0" >已进校</option>
                                            <option value="1">未进校</option>
                                            <option value="2">已出校</option>
                                            <option value="3">未出校</option>
                                        </select>
                                    </div>


                                    <div class="select_div"><span>学年：</span><select id="xn" name="xn" class="chzn-select"
                                                                                                          style="width:120px;"></select></div>
                                    <div class="select_div"><span>年级：</span><select id="nj" name="nj" class="chzn-select"
                                                                                    style="width:120px;"></select></div>
                                    <div class="select_div"><span>班级：</span><select id="bj" name="teamId" class="chzn-select"
                                                                                    style="width:120px;"></select></div>

                                    <div class="select_div">
                                        <span>姓名：</span>
                                        <input type="text" id="name" name="name" style="margin-bottom: 0; padding: 6px; width: 120px;" placeholder="姓名" value="">
                                    </div>

                                    <button type="button" class="btn btn-primary" onclick="search()">查询</button>

<%--                                    <button type="button" class="btn btn-normal" onclick="refresh()">刷新</button>--%>

                                    <div class="clear"></div>


                                    <div class="select_div">
                                        <span>已到校：</span><span id="ydxSpan">${ydxCount}</span>
                                        <span>未到校：</span><span id="wdxSpan">${wdxCount}</span>
                                        <span>已出校：</span><span id="ycxSpan">${ycxCount}</span>
                                        <span>未出校：</span><span id="wcxSpan">${wcxCount}</span>
                                    </div>

                                </div>
                                <table class="responsive table table-striped" id="data-table">
                                    <thead>
                                    <tr role="row">
                                        <th>序号</th>
                                        <th>日期</th>
                                        <th>姓名</th>
                                        <th>班级</th>
                                        <th>进校状态</th>
                                        <th>进校方式</th>
                                        <th>出校状态</th>
                                        <th>出校方式</th>
                                    </tr>
                                    </thead>
                                    <tbody id="pay_list_content">
                                    <jsp:include page="./inOutCountList.jsp" />
                                    </tbody>
                                </table>
                                <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                    <jsp:param name="id" value="pay_list_content" />
                                    <jsp:param name="url" value="/bus/pickData/inOutCountList" />
                                    <jsp:param name="pageSize" value="${page.pageSize }" />
                                </jsp:include>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="layui-tab-item layui-show">
                </div>
            </div>

        </div>
    </div>
</div>
</body>
<script type="text/javascript">


    function refresh(){
        var val = getPagingParam();
        val.refreshCache=true;
        var id = "pay_list_content";
        var url = "/bus/pickData/inOutCountList";

        myPagination(id, val, url,refreshCount);
    }


    function refreshCount(){
        $("#ydxSpan").html($("#ydxCount").val());
        $("#wdxSpan").html($("#wdxCount").val());
        $("#ycxSpan").html($("#ycxCount").val());
        $("#wcxSpan").html($("#wcxCount").val());
    }

    layui.use('element', function(){
        var $ = layui.jquery ,element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        $('#busPick').on('click', function() {
            location.href='/bus/pickData/busIndex';
            layer.load(2);
        })


        $('#parentPick').on('click', function() {
            location.href='/bus/pickData/parentIndex';
            layer.load(2);
        })


    });


    $(function(){
        $.initCascadeSelector({
            "type": "team",
            "yearCallback": function ($this) {
                //search()
            }
        });

    });

    function getPagingParam(){
        var val = {};
        var name = $("#name").val();
        var date=$("#date").val();
        var status=$("#status").val();

        val.name = name;
        val.date=date;
        val.status=status;
        val.teamId=$("#bj").val();
        val.gradeId=$("#nj").val();
        val.schoolYear=$("#xn").val();
        return val;
    }



    function search() {
        var val = getPagingParam();
        val.refreshCache=true;
        var id = "pay_list_content";
        var url = "/bus/pickData/inOutCountList";
        myPagination(id, val, url,refreshCount);
    }


</script>
</html>