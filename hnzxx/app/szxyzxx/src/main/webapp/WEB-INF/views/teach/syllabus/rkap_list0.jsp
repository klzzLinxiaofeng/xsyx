<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/views/embedded/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
  .table tbody td p {
    margin: 3px 0;
  }

  .kb_cell {
    position: relative;
    padding-bottom: 10px;
    margin-bottom: 5px;
  }

  .kb_cell:hover {
    border: 1px solid #00859D;
    background-color: #eee;
  }
</style>
<table id="data-table" class="responsive table  table-bordered table-striped">
  <thead>
  <tr>
    <th style="width: 9%">课节\星期</th>
    <c:forEach items="${xqs}" var="xq">
      <th data-xqdm="${xq}">
        <jcgc:cache tableCode="XY-JY-XINGQI" value="${xq}"></jcgc:cache>
      </th>
    </c:forEach>
    <th style="display: none;">
      <!-- 				<input type="button" value="重新设置课表结构" onclick='$("#setting_input_pane").show("slow")'> -->
    </th>
  </tr>
  </thead>
  <tbody>
  <c:if test="${fn:length(morningLessons) > 0}">
    <tr>
      <td colspan="${fn:length(xqs) + 2}">
        上午
      </td>
    </tr>
  </c:if>

  <!-- 上午 -->
  <c:forEach items="${morningLessons}" var="morning" varStatus="morningStatus">
    <tr>
      <c:choose>
        <c:when test="${morningStatus.count == 1}">
          <td data-kj="${morning}">入校活动</td>
        </c:when>
        <c:otherwise>
          <td data-kj="${morning}">第${morningStatus.count - 1}节</td>
        </c:otherwise>
      </c:choose>
      <c:forEach items="${xqs}" var="xq">
        <td data-xq="${xq}">
          <span class="add" style="display :none;"><i class="icon-edit icon-1x"></i> 添加</span>
        </td>
      </c:forEach>
      <td style="display: none;"></td>
    </tr>
  </c:forEach>

  <c:if test="${fn:length(afternoonLessons) > 0}">
    <tr>
      <td colspan="${fn:length(xqs) + 2}">
        下午
      </td>
    </tr>
  </c:if>

  <c:forEach items="${afternoonLessons}" var="afternoon" varStatus="status">
    <tr>
      <c:choose>
        <c:when test="${fn:length(morningLessons) == (afternoon - 1)}">
          <td data-kj="${afternoon}">午读</td>
        </c:when>
        <c:otherwise>
          <td data-kj="${afternoon}">第${afternoon - 2}节</td>
        </c:otherwise>
      </c:choose>
      <c:forEach items="${xqs}" var="xq">
        <td data-xq="${xq}">
          <span class="add" style="display :none;"><i class="icon-edit icon-1x"></i> 添加</span>
        </td>
      </c:forEach>
      <td style="display: none;"></td>
    </tr>
  </c:forEach>

  <c:if test="${fn:length(eveningLessons) > 0}">
    <tr>
      <td colspan="${fn:length(xqs) + 2}">
        晚上
      </td>
    </tr>
  </c:if>

  <c:forEach items="${eveningLessons}" var="evening" varStatus="status">
    <tr>
      <td data-kj="${evening}">第${evening - 2}节</td>
      <c:forEach items="${xqs}" var="xq">
        <td data-xq="${xq}">
          <span class="add" style="display :none;"><i class="icon-edit icon-1x"></i> 添加</span>
        </td>
      </c:forEach>
      <td style="display: none;"></td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<!-- <div class="reset"><a href="javascript:void(0)" onclick='$("#setting_input_pane").show("slow")'><i class="fa fa-undo"></i>重新设置课表结构</a></div> -->
<script type="text/javascript">
  $(function () {
    var loader = new loadLayer("加载课表中");
    loader.show();
    var lessonTimes = "${item.lessonTimes}";
    if (lessonTimes != "" && lessonTimes != "undefind") {
      lessonTimes = eval("(" + lessonTimes + ")");
      $.each(lessonTimes, function (index, value) {
        var kj = parseInt(value.lesson);
        $("#data-table tbody tr td[data-kj='" + kj + "']").nextAll("td:last").append("<span>" + value.startTime + " ~ " + value.endTime + "</span>")
      });
    }

    var $requestData = {};
    $requestData.syllabusId = "${item.id}";
    $.get("${ctp}/teach/syllabus/rkap/list/json2", $requestData, function (data, status) {
      if ("success" === status) {
        data = eval("(" + data + ")");
        $.each(data, function (index, value) {
          var kj = value.lesson;
          var xq = value.dayOfWeek;
          var kbDm = value.lessonId;
          var kbCell = $("#data-table tbody tr").find("td[data-kj='" + kj + "']").parent().find("td[data-xq='" + xq + "']");
          var jiaosMc = value.teacherName;
          jiaosMc = jiaosMc != null ? jiaosMc : "";
          /* kbCell.find(".add").before*/
          kbCell.html("<div data-kbDm='" + kbDm + "' class='kb_cell'>" +
              "<p class='kc_mc'>" + value.subjectName + "</p>" +
              "<p class='rk_js'>" + jiaosMc + "</p>" +
              "<span class='delete' style='display :none;'><i class='icon-trash icon-1x'></i> 清除</span>" +
              "<span class='edit' style='display :none;'><i class='icon-edit icon-1x'></i> 编辑</span></div>");
        });
      }
      loader.close();
    });
  });
</script>
