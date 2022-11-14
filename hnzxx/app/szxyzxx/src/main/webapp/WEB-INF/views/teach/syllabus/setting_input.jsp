<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
  #daysPlansDiv input {
    margin: 0 7px;
    position: relative;
    top: -1px;
  }

  .lessonDiv {
    margin: 5px 0;
  }

  .form-horizontal .control-label {
    width: 300px;
  }

  .form-horizontal .controls {
    margin-left: 320px;
  }
</style>
<div class="row-fluid" id="setting_input_pane">
  <div class="span12">
    <div class="content-widgets" style="margin-bottom:0;">
      <div class="widget-container" style="background-color:#F8F8F8">
        <form class="form-horizontal" id="setting_form" style="padding-bottom:0;margin-bottom:0">
          <div class="control-group">
            <label class="control-label">每周天数(天/周)：</label>
            <div class="controls">
              <input type="text" id="days" name="days" class="span1"
                     disabled="disabled" placeholder="请输入每周安排天数" value='${empty item.days ? 0:item.days}'>
            </div>
          </div>

          <div class="control-group">
            <label class="control-label">勾选需要上课的星期：</label>
            <div class="controls" id="daysPlansDiv">
              <input id="daysPlan" name="daysPlan" type="checkbox" value="1">星期一
              <input id="daysPlan" name="daysPlan" type="checkbox" value="2">星期二
              <input id="daysPlan" name="daysPlan" type="checkbox" value="3">星期三
              <input id="daysPlan" name="daysPlan" type="checkbox" value="4">星期四
              <input id="daysPlan" name="daysPlan" type="checkbox" value="5">星期五
              <input id="daysPlan" name="daysPlan" type="checkbox" value="6">星期六
              <input id="daysPlan" name="daysPlan" type="checkbox" value="0">星期日
            </div>
          </div>

          <div class="control-group">
            <label class="control-label">上午节数：</label>
            <div class="controls">
              <input id="lessonOfMorning" type="text" name="lessonOfMorning" class="span1 lesson" placeholder="请输入上午节数"
                     value='${item.lessonOfMorning}'
                     data-val="${item.lessonOfMorning}">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">下午节数：</label>
            <div class="controls">
              <input id="lessonOfAfternoon" type="text" id="lessonOfAfternoon"
                     name="lessonOfAfternoon" class="span1 lesson"
                     placeholder="请输入下午节数" value='${item.lessonOfAfternoon}'
                     data-val="${item.lessonOfAfternoon}">
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">晚上节数：</label>
            <div class="controls">
              <input id="lessonOfEvening" type="text" id="lessonOfEvening"
                     name="lessonOfEvening" class="span1 lesson"
                     placeholder="请输入晚上节数" value='${item.lessonOfEvening}'
                     data-val="${item.lessonOfEvening}">
            </div>
          </div>
          <div class="form-actions"
               style="border-top:0 none;background-color:#f8f8f8;margin-left: 300px;margin-bottom:0px;margin-top:0px;padding-top:0;">
            <button class="btn btn-warning" type="button"
                    onclick="editorSetting();">确定
            </button>
          </div>
        </form>
        <input type="hidden" id="schoolSystemId" name="schoolSystemId" value="${item.id}"/>
        <input type="hidden" id="syllabusId" name="syllabusId" value="${item.id}">
        <input type="hidden" id="isSetted" name="isSetted" value="${isSetted}">
      </div>
    </div>
  </div>
</div>
<div class="close_sz" id="open_close_btn">
  <a href="javascript:void(0)" onclick="showOrHide(this)" data-isopen="false"></a>
</div>
<div class="row-fluid" style="margin-top: 15px;">
  <div id="syllabusArrangementPane"></div>
</div>
<script type="text/javascript" defer="defer" src="${ctp}/res/plugin/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
  $(function () {
    echoDaysPlan("${item.daysPlan}");
    echoLessonTimes("${item.lessonTimes}");
    var syllabusId = $("#syllabusId").val();
    var isSetted = "${isSetted}";
    if (syllabusId != "" && syllabusId != "undefind" && isSetted == "all_true") {
      $("#open_close_btn").removeClass("close_sz").addClass("open_sz");
      $("#setting_input_pane").hide();
      showSyllabusArrangementPane(syllabusId);
    } else {
      $("#syllabusArrangementPane").html("");
    }
    $("#daysPlansDiv input").click(function () {
      var num = $("#days").val();
      if ($(this).prop("checked") == true) {
        $("#days").val(parseInt(num) + 1);
      } else {
        $("#days").val(parseInt(num) - 1);
      }
    })
  });

  function initValidator() {
    return $("#setting_form").validate({
      errorClass: "myerror",
      rules: {
        "days": {
          required: true,
          digits: true
        },
        "lessonOfMorning": {
          required: true,
          digits: true
        },
        "lessonOfAfternoon": {
          required: true,
          digits: true
        },
        "lessonOfEvening": {
          required: true,
          digits: true
        }
      },
      messages: {}
    });
  }
</script>
