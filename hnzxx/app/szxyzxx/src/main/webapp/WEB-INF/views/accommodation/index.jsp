<%--
  Created by IntelliJ IDEA.
  User: zhangyong
  Date: 2021/10/25
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/views/embedded/common.jsp" %>
<html>
<head>
    <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
    <title>Title</title>
</head>
<body>
<div class="row-fluid">
    <div class="span12">
        <div class="content-widgets white">
            <div class="widget-head">
                <h3>
                    住宿管理
                    <p class="btn_link" style="float: right;">
                        <a href="javascript:void(0)" class="a3"
                           onclick="$.refreshWin();"><i class="fa  fa-undo"></i>刷新列表</a>
                        <a id="downLoadExcel"   href="#" onclick="daochu();" class="a2" >导出住宿登记列表</a>
                        <a    href="#" onclick="settings();" class="a2" >设置金额</a>
                       <%-- <a    href="#" onclick="create();" class="a2" >添加金额</a>--%>
                    </p>
                </h3>
            </div>
            <div class="light_grey"></div>
            <div class="content-widgets">
                <div class="widget-container">
                    <div class="select_b">

                        <div class="select_div">
                            <span>上报时间：</span>
                            <input type="text" id="startDate" name="startDate" autocomplete="off"
                                   onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'endDate\')}'})" class="span4"
                                   style="width:200px;padding-top: 4px;" placeholder="2015-09-01"
                                   value="">
                            &nbsp;-&nbsp;
                            <input type="text" id="endDate" name="endDate" autocomplete="off"
                                   onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startDate\')}'})" class="span4"
                                   style="width:200px;padding-top: 4px;" placeholder="2015-09-05"
                                   value="">
                        </div>
                        <div class="select_div">
                            <span>上报人员：</span>
                            <input type="text" id="teacherId" name="teacherId"
                                   style="width:200px;padding-top: 4px;" class="span4" placeholder="老师姓名"
                                   value="">
                        </div>
                        <div class="select_div">
                            <span>房室号：</span>
                            <input type="text" id="fangshihao" name="fangshihao"
                                   style="width:200px;padding-top: 4px;" class="span4" placeholder="房室号"
                                   value="">
                        </div>
                        <button type="button" class="btn btn-primary" onclick="search()">查询</button>
                        <button type="button" class="btn btn-primary" onclick="loadCreatePage()">住宿信息登记</button>
                        <div class="clear"></div>
                    </div>
                    <table class="responsive table table-striped" id="data-table">
                        <thead>
                        <tr role="row">
                            <th>序号</th>
                            <th>上报人</th>
                            <th>房室号</th>
                            <th>上报时间</th>
                            <th>面积</th>
                            <th>热水度数</th>
                            <th>冷水度数</th>
                            <th>电表度数</th>
                            <th class="caozuo" style="max-width: 250px;">操作</th>
                        </tr>
                        </thead>
                        <tbody id="publicClass_list_content">
                        <jsp:include page="./list.jsp"/>
                        </tbody>
                    </table>
                    <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                        <jsp:param name="id" value="publicClass_list_content"/>
                        <jsp:param name="url" value="/accomm/zhusu/findByAll?sub=list"/>
                        <jsp:param name="pageSize" value="${page.pageSize}"/>
                    </jsp:include>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function search() {
        var val = {};
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        var teacherId = $("#teacherId").val();
        var fangshihao =$("#fangshihao").val();
        if (teacherId != null && teacherId != "") {
            val.teacherName = teacherId;
        }
        if (fangshihao != null && fangshihao != "") {
                val.fangshihao = fangshihao;
        }
        if (startDate != null && startDate != "") {
            val.startDate = startDate;
        }
        if (endDate != null && endDate != "") {
            var data=new Date(endDate);
            var year= data.getFullYear();
            var mounth=data.getMonth()+1;
            var day=data.getDate()+1;
            val.endTime=year+"-"+mounth+"-"+day;
        }
        var id = "publicClass_list_content";
        var url = "/accomm/zhusu/findByAll?sub=list";
        myPagination(id, val, url);
    }
    // 	加载创建对话框
    function loadCreatePage() {
        window.location.href="${ctp}/accomm/zhusu/createOrUpdate";
    }
    //  加载编辑对话框
    function bianji(id) {
        window.location.href = "${ctp}/accomm/zhusu/createOrUpdate?id=" + id;
    }
    //  加载详情对话框
    function xiangqing(id) {
        window.location.href = "${ctp}/accomm/zhusu/xiangqing?id=" + id;
    }
    //删除
    function ddelete(id) {
        $.get("${ctp}/accomm/zhusu/deleteAccommdation?id="+id, function (data) {
                if ("success" === data) {
                    $.success("删除成功");
                    search();
                } else if ("fail" === data) {
                    $.error("删除失败，系统异常", 1);
                }

        });
    }
    // 	删除对话框
    function shanchu(id) {
        $.confirm("确定执行此次操作？", function () {
            ddelete(id);
        });
    }

    //导出
    function daochu() {
        var url="/accomm/zhusu/daochu?sub=qwert"
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        var teacherId = $("#teacherId").val();
        var fangshihao =$("#fangshihao").val();
        if (teacherId != null && teacherId != "") {
            url+="teacherName ="+teacherId;
        }
        if (fangshihao != null && fangshihao != "") {
            url+="&fangshihao"+fangshihao
        }
        if (endDate != null && endDate != "") {
            url += "&endTime" + endDate;
        }
        if (startDate != null && startDate != "") {
            url += "&startDate" + startDate;
        }
        window.open(url);
    }
    /*
    * 设置金额
    */
    function settings() {
        $.initWinOnCurFromTop("设置金额","/HouseAmount/view","800","500")
    }


    function create() {
      var val=[{
          name:"住房面积租金单价",
          jine:"12.5"
          },{
          name:"热水费",
          jine:"4"
          },{
          name:"冷水费",
          jine:"12.5"
          },{
          name:"电费",
          jine:"12.5"
      }];
      for(var i=0;i<val.length;i++){
          $.post("/HouseAmount/create",val[i],function (d) {
            if(d=="success"){
                $.success("成功");
            }else{
                $.error("失败");
            }
          })
      }
    }
</script>

</body>
</html>
