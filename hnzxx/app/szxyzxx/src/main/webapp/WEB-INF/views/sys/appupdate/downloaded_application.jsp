<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/buttons.css" rel="stylesheet">
<title>应用下载</title>
</head>
<style type="text/css">
    .download{
        background: #fff;  margin: 0 auto;width: 520px;padding-bottom: 20px;
    }
    .z{
        float: left;margin: 20px 0 0 30px;background: #fff;
    }
    .z img{
    	width:70px;height:70px;
    	}
    .z p{
        color:#808080;font-family:'微软雅黑';font-size: 14px;margin: 9px 0 0 0;line-height:23px;width:170px;
    }
    h6{
        font-family:'微软雅黑';margin: 0;font-size: 16px;color: #323232;
    }
    .xian{
        width: 1px;height:150px;background:#ccc;float: left;margin: 20px 30px 0 33px;
    }
    .lj a{
       margin-right: 25px;margin-top: 20px;
    }
    .lj a img{
          margin-top: 20px;
    }
    .sm{
        margin-left: -10px;
    }
    span.sm img{
          margin:18px 12px 0; width:155px;height:155px;border:1px solid #eee;
    }
    .z a{
    	display:block;width:120px;height:40px;text-indent:-9999px;margin:25px auto 0;
    }
    .android{
    	background:url("../../res/images/appDownload.png") no-repeat;
    }
    .ios{
    	background:url("../../res/images/ios.png") no-repeat;
    }
</style>
<body >

    <c:choose>
        <c:when test='${param.tag != null and param.tag eq "zhjyapp"}'>
            <div class="download" >
                <div class="z">
                <img alt="" src="https://cdn.studyo.cn/education/edu/2016-8-25/c94dd9926e52915ef4a94be78ceb67ae.png">
                <div style="float:right;margin-left:10px;">
                    <h6>智慧教育云APP</h6>
                    <p ></p>
                </div>
                <div class="clear"></div>
                <%--<a href="${appReleaseVo.setupFile }" target="_blank" class="android">下载</a>--%>
            </div>
            <i class="xian"></i>
            <div class="y">
                <div class="lj">
                    <span class="sm"><img src='http:///cdn.studyo.cn/education/mqt/zgzhjyy.png'></span>
                </div>
            </div>
        </c:when>
        <c:when test='${param.tag != null and param.tag eq "qy"}'>
                <div class="download" >
                    <div class="z">
                        <img alt="" src="https://cdn.studyo.cn/education/edu/2016-8-25/c94dd9926e52915ef4a94be78ceb67ae.png">
                        <div style="float:right;margin-left:10px;">
                            <h6>奇云APP</h6>
                            <p ></p>
                        </div>
                        <div class="clear"></div>
                        <%--<a href="${appReleaseVo.setupFile }" target="_blank" class="android">下载</a>--%>
                    </div>
                    <i class="xian"></i>
                    <div class="y">
                        <div class="lj">
                            <span class="sm"><img src='http://cdn.studyo.cn/education/mqt/qiyun.png'></span>
                        </div>
                    </div>

        </c:when>
        <c:otherwise>
            <div class="download" >
                <div class="z">
                <img alt="" src="${appReleaseVo.trademark }">
                <div style="float:right;margin-left:10px;">
                    <h6>${appReleaseVo.name }</h6>
                    <p >${appReleaseVo.description }</p>
                </div>
                <div class="clear"></div>
                <a href="${appReleaseVo.setupFile }" target="_blank" class="android">下载</a>
            </div>
            <i class="xian"></i>
            <div class="y">
                <div class="lj">
                    <span class="sm"><img src='${appReleaseVo.qrCodeFile }'></span>
                </div>
            </div>
        </c:otherwise>

    </c:choose>




</div>
</body>
<script type="text/javascript">
</script>
</html>
