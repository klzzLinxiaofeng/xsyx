<%@ page language="java"
         import="platform.education.user.contants.AbandonedDefaultStatus"
         contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%@ include file="/views/embedded/common.jsp" %>
  <link href="${pageContext.request.contextPath}/res/css/extra/oa.css"
        rel="stylesheet">
  <script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
  <title>调课</title>
  <script type="text/javascript">
    var dclickId;
    $(function () {
      /* $(".sq_list .clsq ul li .caozuo .delete").click(function() {
          $(this).parent().parent().remove()
      }); */
      $(".sq_list .entry ul li a").click(function () {
        $(".sq_list .entry ul li a").parent().removeClass("on");
        $(this).parent().addClass("on");
        var j = $(this).parent().index();
        dclickId = $(this).attr("data-id");
      });
      $(".oa_top .top_ul li a").click(function () {
        $(".oa_top .top_ul li a").removeClass("on");
        $(this).addClass("on");
        var i = $(this).parent().index();
      });

    })
  </script>
</head>
<body>
<div class="container-fluid">
  <jsp:include page="/views/embedded/navigation.jsp">
    <jsp:param value="fa-asterisk" name="icon"/>
    <jsp:param value="调课" name="title"/>
    <jsp:param value="${param.dm}" name="menuId"/>
  </jsp:include>
  <div class="row-fluid">
    <div class="span12">
      <div class="content-widgets white">
        <div class="oa_top">
          <ul class="top_ul">
            <li><a href="javascript:void(0)" class="on" onclick="getData('approval');">待审批</a></li>
            <li><a href="javascript:void(0)" onclick="getData('approved');">已审批</a></li>
          </ul>
        </div>
        <div class="sq_list">
          <div style="display: flex; align-items: center">
            <div class="search_1">
              <input type="text" placeholder="标题/申请人/详情" id="ss"> <a
                    class="sea_s" onclick="ss2();"><i class="fa fa-search"></i></a>
            </div>
            <div style="margin-left: 50px; display: none" class="select_div">
              <span>审核状态:  &nbsp;&nbsp;&nbsp;&nbsp;</span>
              <select name="indiaStatus" id="indiaStatus" style="margin-top: 10px">
                <option value="3">全部</option>
                <option value="1">已通过</option>
                <option value="2">已驳回</option>
              </select>
              <button type="button" class="btn btn-primary" onclick="search()">查询</button>
            </div>
          </div>
          <div class="clsq" id="adjust_list_content">
            <c:choose>
              <c:when test="${items.size()>0 }">
                <jsp:include page="./list.jsp"/>
              </c:when>
              <c:otherwise>
                <div class="empty">
                  <p>暂无调课申请</p>
                </div>
              </c:otherwise>
            </c:choose>
          </div>
          <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
            <jsp:param name="id" value="adjust_list_content"/>
            <jsp:param name="url"
                       value="/oa/adjustClass/index?sub=list&auditType=${auditType}&dm=${param.dm}"/>
            <jsp:param name="pageSize" value="${page.pageSize}"/>
          </jsp:include>
          <div class="clear"></div>
        </div>
      </div>
    </div>
  </div>

  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
            &times;
          </button>
          <h4 class="modal-title" id="myModalLabel">
            驳回调课
          </h4>
        </div>
        <div class="modal-body">
          <div class="form-group">
            <label for="rejectReason">驳回理由</label>
            <textarea class="form-control" rows="4" id="rejectReason" style="width: 98%"></textarea>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">取消
          </button>
          <button type="button" class="btn btn-primary">
            确定
          </button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal -->
  </div>
</div>
<input type="hidden" id="clickId"/>
</body>

<script type="text/javascript">
  $(function () {
    var all = $("#all").val();
    $(".all").text(all);
  });

  function addData() {
    var all = $("#all").val();
    $(".all").text(all);

    var dListLength = $("#dListLength").val();
    for (var m = 0; m < dListLength; m++) {
      if ($.trim($(".people_num" + m).text()) == '' || $.trim($(".people_num" + m).text()) == '0') {
        $(".people_num" + m).text('0');
      }
    }
    var oListLength = $("#oListLength").val();
    for (var j = 0; j < oListLength; j++) {
      if ($.trim($(".people_numo" + j).text()) == '' || $.trim($(".people_numo" + j).text()) == '0') {
        $(".people_numo" + j).text('0');
      }
    }

  }

  //	同意对话框
  function agreeObj(id) {
    $.confirm("确定执行此次操作？", function () {
      executeAgree(id);
    });
  }

  //	执行同意
  function executeAgree(id) {
    var paginationLoader = new loadDialog();
    paginationLoader.show();
    $.post("${pageContext.request.contextPath}/oa/adjustClass/agree", {
      "id": id
    }, function (data, status) {
      if ("success" === status) {
        if ("success" === data) {
          $.success("同意成功");
        } else if ("fail" === data) {
          $.error("同意失败，系统异常", 1);
        }
      }
      paginationLoader.close();
      window.location.reload();
    });
  }

  // 驳回对话框
  function rejectObj(id) {
    var reason = prompt("驳回理由", "请输入驳回理由");
    if (reason != null && reason != "") {
      executeReject(id, reason);
    }
  }

  //	执行驳回
  function executeReject(id, reason) {
    $.post("${pageContext.request.contextPath}/oa/adjustClass/reject", {
      "id": id,
      "rejectionReason": reason
    }, function (data, status) {
      if ("success" === status) {
        if ("success" === data) {
          $.success("驳回成功");
        } else if ("fail" === data) {
          $.error("驳回失败，系统异常", 1);
        }
      }
      window.location.reload();
    });
  }

  function ss2() {
    var val = {};
    val = {
      "searchWord": $("#ss").val()
    };
    var id = "adjust_list_content";
    var url = "/oa/adjustClass/index?sub=list&auditType=approval&dm=${param.dm}";
    myPagination(id, val, url);
  }

  function getData(type) {
    var val = {};
    var id = "adjust_list_content";
    var url = "";
    $('.select_div').hide();
    if (type === 'approval') {
      url = "/oa/adjustClass/index?sub=list&auditType=approval&dm=${param.dm}";
    } else if (type === 'approved') {
      $('.select_div').show();
      url = "/oa/adjustClass/index?sub=list&auditType=approved&dm=${param.dm}";
    } else {
      url = "/oa/adjustClass/index?sub=list&auditType=approval&dm=${param.dm}";
    }
    $.ajaxSetup({
      async: false
    });
    myPagination(id, val, url);
  }

  function search() {
    var val = {};
    var indiaStatus = $("#indiaStatus").val();
    if (indiaStatus != null && indiaStatus != "") {
      val.status = indiaStatus;
    }
    var id = "adjust_list_content";
    var url = "/oa/adjustClass/index?sub=list&auditType=approved&dm=${param.dm}";
    myPagination(id, val, url);
  }

</script>

</html>