<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="shiro" var="shiro" />
<fmt:message key="xbull.app.id" var="xBullAppId" bundle="${shiro}"/>
<fmt:message key="xbull.app.sercer" var="xBullAppSecret" bundle="${shiro}"/>
<fmt:message key="xbull.wechat.message.url" var="messageUrl" bundle="${shiro}"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/res/js/common/md5/md5.js" ></script>
<script type="text/javascript">
    $(function () {

        $.teamScoresMessageSend = function (options) {
            //需传值school_id，className，first； appId和appSecret从配置文件获取
            var defOption = {
                "timestamp" : Math.floor((new Date().getTime())/1000),      //当前时间戳（秒）
                "xbull_appid" : "",                                 //第三方应用在十牛平台的唯一标识
                "secret" : "",                                      //签名, md5(xBull_appId + xBull_app_secret + $timestamp)
                "module_name" : "scoreAnalysis",                    //推送消息的模块名称, 固定值scoreAnalysis
                "school_id" : "${sessionScope[sca:currentUserKey()].schoolId}",                                   //学校Id  (需传参)
                "is_open_school" : "1",                             //school_id是第三方的学校ID, 固定为1
                "identity" : "0",                                   //身份  教师=1；家长=0
                "className" : "",                                   //班级名称
                "message_type" : "notice",                          //消息类型  固定值notice
                "first" : "您有新的成绩统计，请点击查看"                              //模板消息标题
            };
            options = $.extend({}, defOption, options || {});
            var appId = "${xBullAppId}";
            var appSecret = "${xBullAppSecret}";

            options.xbull_appid = appId;
            options.secret = hex_md5(appId + appSecret + options.timestamp);
            console.log(options);

            var url = "${messageUrl}";
            $.post(url, options, function(data, status) {
                console.log(data);
            });
        }
    });
    

</script>