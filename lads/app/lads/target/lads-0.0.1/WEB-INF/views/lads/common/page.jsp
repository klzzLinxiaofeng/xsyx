<%-- 
    Document   : page
    Created on : 2011-3-31, 17:00:56
    Author     : 罗志明
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
            String loadDiv = request.getParameter("loadDiv");
            String url = request.getParameter("url");
%>
<s:i18n name="config/struts/locales/onlineClass">
<s:hidden name="pagination.count" value="%{pagination.count}" id="count"/>
<s:hidden name="pagination.current" value="%{pagination.current}" id="current"/>
<s:set name="dateCount" value="%{pagination.count}"></s:set>
<s:if test="#dateCount==0"><div class="nothing"></div></s:if>
<%--<s:set name="pageCount" value="%{(pagination.count-1) / pagination.pageCount+1}"></s:set>--%>
<s:set name="pageCount" value="%{pagination.getPages()}"></s:set>
<!--注释的是只有1页时不显示分页的代码-->
<%--<s:if test="#pageCount==1">&nbsp;</s:if>
<s:else>--%>
<div class="totalpage">
<s:text name="total"></s:text>&nbsp;<em><s:property value="%{pagination.count}" /></em>&nbsp;<s:text name="records"></s:text>，
<s:text name="total"></s:text>&nbsp;<em><s:property value="%{(pagination.count-1) / pagination.pageCount+1}" /></em>&nbsp;<s:text name="page"></s:text>，
<s:text name="nowThe"></s:text>&nbsp;<em><s:property value="%{pagination.current}" /></em>&nbsp;<s:text name="page"></s:text>　
    <s:if test="pagination.firstEnable()"><a href="javascript:page(1);"><s:text name="homePage"></s:text></a></s:if><s:else><a class="none"><s:text name="homePage"></s:text></a></s:else>
    <s:if test="pagination.previoEnable()"><a href="javascript:page(<s:property value="%{pagination.current-1}" />);"><s:text name="onPage"></s:text></a></s:if><s:else><a class="none"><s:text name="onPage"></s:text></a></s:else>
    <em><s:if test="%{#pageCount>5||pagination.current>5}">
        <s:bean name="org.apache.struts2.util.Counter" id="counter">
            <s:param name="first" value="%{pagination.current-5>0?pagination.current-5:1}" />
            <s:param name="last" value="%{pagination.current+5<#pageCount?pagination.current+5:#pageCount}" />
            <s:iterator>
                <s:if test="%{current-1==pagination.current}">
                    <a class="none"><s:property/></a>
                </s:if>
                <s:else>
                    <a href="javascript:page(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
        </s:bean>
    </s:if>
    <s:else>
        <s:bean name="org.apache.struts2.util.Counter" id="counter">
            <s:param name="first" value="1" />
            <s:param name="last" value="%{#pageCount}" />
            <s:iterator>
                <s:if test="%{current-1==pagination.current}">
                    <a class="none"><s:property/></a>
                </s:if>
                <s:else>
                    <a href="javascript:page(<s:property/>);"><s:property/></a>
                </s:else>
            </s:iterator>
        </s:bean>
    </s:else></em>
    <s:if test="pagination.nextEnable()"><a href="javascript:page(<s:property value="%{pagination.current==(pagination.count+pagination.pageCount-1)/pagination.pageCount?pagination.current:pagination.current+1}" />);"><s:text name="nextPage"></s:text></a></s:if><s:else><a class="none"><s:text name="nextPage"></s:text></a></s:else>
    <s:if test="pagination.lastEnable()"><a href="javascript:page(<s:property value="%{(pagination.count+pagination.pageCount-1)/pagination.pageCount}" />);"><s:text name="lastPage"></s:text></a></s:if><s:else><a class="none"><s:text name="lastPage"></s:text></a></s:else>
</div>
<%--</s:else>--%>

<script type="text/javascript">
    function page(index) {
        /* if (index == "") {
            alert("请输入跳转的页面数！");
            return;
        }
        if(isNaN(index)) {
            alert("跳转页面必须为数值型");
            return;
        } */
        if(index == 0){
            index = 1;
        }
        if (index > <s:property value="%{(pagination.count+pagination.pageCount-1)/pagination.pageCount}" />)
        document.getElementById("current").value = "" + <s:property value="%{(pagination.count+pagination.pageCount-1)/pagination.pageCount}" />;
       	else{document.getElementById("current").value = ""+index;}
        var url = "<%=url%>";
        if(url==null||url==""){
            url =  document.forms[0].action;
        }
        var count = document.getElementById("count").value;
        var current = document.getElementById("current").value

    <%
                String urlPara = request.getParameter("urlPara");
                if (urlPara == null || "".equals(urlPara)) {
                    urlPara = (String) request.getAttribute("urlPara");
                }
    %>
                var urlPara = <%=urlPara%>;
                $("<%=loadDiv%>").html('<img src="${pageContext.request.contextPath}/res/images/loading.gif" alt="加载中"/>文件加载中,请耐心等待');
                if(urlPara!=null&&urlPara!=""){
                    $("<%=loadDiv%>").load(url+"?pagination.count="+count+"&pagination.current="+current,urlPara);
                }else{
                    $("<%=loadDiv%>").load(url+"?pagination.count="+count+"&pagination.current="+current);
                }
            
        }

</script>
</s:i18n>