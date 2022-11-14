<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                </div>
                <div id="footer" class="fl-panel fl-note fl-bevel-white fl-font-size-80">
                	<!-- <a id="jasig" href="http://www.jasig.org" title="go to Jasig home page"></a> -->
                    <div id="copyright">
                    	<!-- <div class="jianjie"><a href="javascript:void(0)">公司简介</a> | <a href="javascript:void(0)">联系我们</a> | <a href="javascript:void(0)">...</a></div>
                        <p>Copyright &copy; 2014-2015 XunYunEDU.CCOM</p> -->
                       <%--  <p>Powered by <a href="http://www.jasig.org/cas">Jasig Central Authentication Service <%=org.jasig.cas.CasVersion.getVersion()%></a></p> --%>
                       	<p><a href="javascript:void(0)">关于我们</a> | <a href="javascript:void(0)">联系我们</a> | <a href="/text/yinsishengming.txt">隐私声明</a> | <a href="/text/banquanshengming.txt">版权声明</a> | <a href="/text/fuwutiaokuan.txt">服务条款</a></p>
                    	<!--<p>服务热线：400-6088260      客服QQ：<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=1474940045&amp;site=qq&amp;menu=yes" id="qq" style="position: relative;  top: 6px; width: 77px; height: 22px;"><img border="0" src="http://wpa.qq.com/pa?p=2:1474940045:41" alt="点击这里给我发消息" title="点击这里给我发消息"></a></p>
                    	 <p>广州迅云教育科技有限公司 版权所有</p>
        				<p>粤ICP备13048369号</p> -->
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/login/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/jquery.placeholder.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/bootstrap.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/prettify.min.js"></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/js/login/jquery.ua.js"></script>
		<script type="text/javascript">
			$(function(){
				$('input, textarea').placeholder();
			});
		</script>
		<script type="text/javascript">
		/*判断浏览器版本是否过低*/
		$(function(){
			var b_name = navigator.appName;
			var b_version = navigator.appVersion;
			var version = b_version.split(";");
			if (b_name == "Microsoft Internet Explorer") {
			var trim_version = version[1].replace(/[ ]/g, "");
				/*如果是IE6或者IE7*/
				if (trim_version == "MSIE7.0" || trim_version == "MSIE6.0"|| trim_version == "MSIE8.0") {
					$("#cas").load("${pageContext.request.contextPath}/views/download.jsp");
				}
			}
		 	if($.ua.is360se||$.ua.isLiebao||$.ua.isMaxthon||$.ua.isQQ||$.ua.isSougou){
		 		$("#cas").load("${pageContext.request.contextPath}/views/download.jsp");
		 	}
			
		});
		
		
		</script>
        <!-- <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js"></script>
        <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.8.5/jquery-ui.min.js"></script> -->
        <script type="text/javascript" src="<c:url value="/js/cas.js" />"></script>
    </body>
</html>

