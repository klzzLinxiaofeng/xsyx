<%@ page language="java" import="platform.education.user.contants.AbandonedDefaultStatus"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="platform.szxyzxx.web.common.contants.SysContants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/views/embedded/common.jsp"%>
<link href="${pageContext.request.contextPath}/res/css/extra/oa.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/res/css/extra/coherent.css" rel="stylesheet">
<title>一卡通</title>
<script type="text/javascript">
    function show(index){
        document.getElementById("frame").style.top=(116+(index-1)*37)+'px';
         document.getElementById("frame").style.display="block";
    }
    function hide(){
        document.getElementById("frame").style.display="none";
    }
</script>
</head>
<body>
<div class="container-fluid">
		<jsp:include page="/views/embedded/navigation.jsp">
			<jsp:param value="fa-asterisk" name="icon"/>
			<jsp:param value="一卡通" name="title" />
			<jsp:param value="${param.dm}" name="menuId" />
		</jsp:include>
		<div class="row-fluid">
			<div class="span12">
				<div class="content-widgets white">
					<div class="oa_top">
						<ul class="top_ul">
				             <li><a href="javascript:void(0)" >总体统计</a></li>
				            <li><a href="javascript:void(0)" class="on">清单查询</a></li>
				        </ul>
					</div>
					 <div class="query">
        <p>查询结果</p><button class="btn btn-warning" type="button">重新查询</button>
    </div>
    <div class="while">
        <span>全部年级  >  一年级  >  一年级(1)班  >  张三的消费明细  时间：2015年3月1日至2015年3月22日</span>
        <table class="table-bordered">
            <tr style="font-weight: bold;">
                <td>序号</td>
                <td>时间</td>
                <td>消费地点</td>
                <td>充值金额</td>
                <td>消费金额</td>
                <td>提现金额</td>
                <td class="broad">操作</td>
            </tr>
            <tr class="odd">
                <td class="smock">1</td>
                <td>2015年3月1日 14:43</td>
                <td>小卖部</td>
                <td></td>
                <td class="orange_1">¥50.00</td>
                <td></td>
                <td><a href="#">查看</a><a href="javascript:void(0);" onclick="show(1);">发送短信</a></td>
            </tr>
            <tr>
                <td class="smock">2</td>
                <td>2015年3月1日 14:43</td>
                <td>小卖部</td>
                <td></td>
                <td class="orange_1">¥50.00</td>
                <td></td>
                <td><a href="#">查看</a><a href="javascript:void(0);" onclick="show(2);">发送短信</a></td>
            </tr>
            <tr class="odd">
                <td class="smock">3</td>
                <td>2015年3月1日 14:43</td>
                <td>小卖部</td>
                <td></td>
                <td class="orange_1">¥50.00</td>
                <td></td>
                <td><a href="#">查看</a><a href="javascript:void(0);" onclick="show(3);">发送短信</a></td>
            </tr>
            <tr>
                <td class="smock">4</td>
                <td>2015年3月1日 14:43</td>
                <td>食堂</td>
                <td></td>
                <td class="orange_1">¥50.00</td>
                <td></td>
                <td><a href="#">查看</a><a href="javascript:void(0);" onclick="show(4);">发送短信</a></td>
            </tr>
            <tr class="odd">
                <td class="smock">5</td>
                <td>2015年3月1日 14:43</td>
                <td>财务处</td>
                <td></td>
                <td></td>
                <td class="blove_1">¥100.00</td>
                <td><a href="#">查看</a><a href="javascript:void(0);" onclick="show(5);">发送短信</a></td>
            </tr>
            <tr>
                <td class="smock">6</td>
                <td>2015年3月1日 14:43</td>
                <td>财务处</td>
                <td class="bice_1">¥500.00</td>
                <td></td>
                <td></td>
                <td><a href="#">查看</a><a href="javascript:void(0);" onclick="show(6);">发送短信</a></td>
            </tr>
        </table>
        <div class="print">
            <div class="circle">
                <button type="button"class="btn btn-success">导出Excel</button>
                <button type="button"class="btn btn-link">打印</button>
            </div>
        </div>
        <div id="frame"style="display:none;">
            <div class="words">
                <textarea>张三同学 2015年3月25日 15:45:09 在财务室充值500.00元</textarea>
            </div>
            <button type="button" class="btn-warning">确定发送</button>
            <button type="button" class="btn btn-link" onclick="hide();">取消</button>
            <div class="clear"></div>
        </div>
    </div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>