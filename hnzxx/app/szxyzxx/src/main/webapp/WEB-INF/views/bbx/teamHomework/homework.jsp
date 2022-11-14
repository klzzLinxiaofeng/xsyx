<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/views/embedded/taglib.jsp" %>
<%@ taglib prefix="entity" uri="http://www.jiaoxueyun.com/entity"%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage}" />
<input type="hidden" id="totalPages" name="totalPages" value="${page.totalPages}" />
<style>
/* /*值日生 课程样式*/ */
.number{
    font-family: '微软雅黑';font-size: 18px;font-weight: bold;
}
.number{
    color:#707579;font-family: '微软雅黑';font-size: 18px;
}
.number13{
    color:#967bdc;
}
.number14{
    color:#00a1cb;
}
.number41{
    color:#967bdc;
}
.number21{
    color:#6e3307;
}
.number20{
    color:#ee7800;
}
.number18{
    color:#F275B4;
}
.number16{
    color:#ACD271;
}
.number17{
    color:#7ea7c3;
}
.number12{
    color:#ea0f0a;
}
.trends ul li .subject41{
    background: #967bdc;
}
.trends ul li .subject13{
    background: #18a360;
}
.trends ul li .subject14{
    background: #00a1cb;
}
.trends ul li .subject21{
    background: #6e3307;
}
.trends ul li .subject20{
    background: #ee7800;
}
.trends ul li .subject18{
    background: #F275B4;
}
.trends ul li .subject16{
    background: #ACD271;
}
.trends ul li .subject17{
    background: #7ea7c3;
}
.trends ul li .subject12{
    background: #ea0f0a;
}
.subject{
    background:#02a0e9;
}
.case img{
    border:2px #02a0e9 solid;
}
.case41 img{
    border:2px #967bdc solid;
}
.case13 img{
    border:2px #18a360 solid;
}
.case14 img{
    border:2px #00a1cb solid;
}
.case21 img{
    border:2px #6e3307 solid;
}
.case20 img{
    border:2px #ee7800 solid;
}
.case18 img{
    border:2px #F275B4 solid;
}
.case16 img{
    border:2px #ACD271 solid;
}
.case17 img{
    border:2px #7ea7c3 solid;
}
.case12 img{
    border:2px #ea0f0a solid;
}
#content{
	margin:-22px 0 0 0;
}
.entry img{
	width:90px;height:76px;
}
.fa{
	margin-right: 8px;
}
</style>
<!-- <div class="zuoye_gs"> -->
	<%-- <img src="${ctp }/res/css/bbx/images/icon.jpg" class="zp_zy">
	<p style="font-size:14px;padding-left: 55px;">全部：<span  class="number">${page.totalRows }</span> 次 &nbsp;
		<c:forEach items="${subjectVoList}" var="subjectVo">
			&nbsp;${subjectVo.subjectName }：<span  class="number number${subjectVo.subjectCode }">${subjectVo.count }</span> 次 &nbsp;
		</c:forEach>
	</p> --%>
	<!-- &nbsp;语文：<span  class="number2">10</span> 张&nbsp;数学：<span  class="number">10</span> 张&nbsp;语文：<span  class="number3">10</span> 张 -->
<!-- </div> -->
                    
            <div class="trends">
                <ul>
                <c:forEach items="${voList}" var="vo">
                	<input type="hidden" id="homeworkId" name="homeworkId" value="${vo.homeworkId }"/>
                	<li id="${vo.id}_li">
                        <span class="subject subject${vo.subjectCode }">${vo.subjectName }作业
                        </span>
                         <span class="subject sub_bj">${team.name }
                        </span>
                        <div class="entry">
                            <p>${vo.content }</p>
                            <div class="case case${vo.subjectCode }" id="layer-photos-demo_${vo.id}">
                            <c:if test="${!empty vo.fileUuid }">
                            	<c:forEach items="${vo.fileUuid}" var="file">
                           			<%-- <c:if test="${file.imageState }"> --%>
                           				<img src="${file.imageSrc }" >
                           			<%-- </c:if> --%>
                            	</c:forEach>
                            	<%-- <c:forEach items="${vo.fileUuid}" var="file">
                           			<c:if test="${not file.imageState }">
                           				<p style='padding:0;margin-bottom:0;width:400px;overflow:hidden;'><a target="_blank" href='<entity:getHttpUrl uuid="${file.fileId}"/>'>${file.fileName}</a></p>
                           			</c:if>
                            	</c:forEach> --%>
                            </c:if>
                            </div>
                        </div>
                        <div class="revise">
                            <p>
                                <c:choose>
                                    <c:when test="${fn:contains(vo.visualTime, '-')}">
                                        <fmt:formatDate value="${vo.modifyDate}" pattern="MM-dd HH:mm" />
                                    </c:when>
                                    <c:otherwise>
                                        ${vo.visualTime}
                                    </c:otherwise>
                                </c:choose>
                            </p>
                            <%-- <c:if test="${currentRole=='CLASS_MASTER'  || currentRole=='SUBJECT_TEACHER'}"> --%>
                            <a href="javascript:void(0)" onclick="deleteObj(this, '${vo.id}');"><i class="fa fa-trash-o" style="font-size:16px;"></i> &nbsp;删除</a>
                            <a href="javascript:void(0)" onclick="loadEditPage('${vo.id}');"><i class="fa fa-pencil-square-o" style="font-size:16px;"></i> &nbsp;编辑</a>
                           <%--  </c:if> --%>
<%--                             <c:if test="${currentRole=='CLASS_MASTER' }"> --%>
                            	<%-- <a href="javascript:void(0)" onclick="loadViewPage('${vo.homeworkId }');"><i class="fa fa-eye" style="font-size:16px;"></i> &nbsp;浏览</a> --%>
<%--                             </c:if>     --%>
                        </div>
                    </li>
                </c:forEach>
                   <!--  <li>
                        <span class="subject">英语作业</span>
                        <div class="entry">
                            <p>我在邹城图书馆办了两个图书证。这样一次可以借两本书，两本看完可以一块还。不用花钱买书。也可以去新华书店看书。同学们是不是省钱又增长知识。</p>
                            <div class="case"><img src="res/css/bbx/images/print.jpg" ><img src="res/css/bbx/images/print.jpg" ></div>
                        </div>
                        <div class="revise">
                            <p>2小时前</p>
                            <a href="#"onclick="delDiv(this.id)" id="st"><i class="fa fa-trash-o"></i>删除</a>
                            <a href="#"><i class="fa fa-pencil-square-o"></i>编辑</a>
                        </div>
                    </li>
                    <li>
                        <span class="subject1">语文作业</span>
                        <div class="entry">
                            <p>我在邹城图书馆办了两个图书证。这样一次可以借两本书，两本看完可以一块还。不用花钱买书。也可以去新华书店看书。同学们是不是省钱又增长知识。</p>
                            <div class="case1"><img src="res/css/bbx/images/print.jpg" ><img src="res/css/bbx/images/print.jpg" ></div>
                        </div>
                        <div class="revise">
                            <p>2小时前</p>
                            <a href="#"onclick="delDiv(this.id)" id="ss"><i class="fa fa-trash-o"></i>删除</a>
                            <a href="#"><i class="fa fa-pencil-square-o"></i>编辑</a>
                        </div>
                    </li>
                    <li>
                        <span class="subject2">数学作业</span>
                        <div class="entry">
                            <p>我在邹城图书馆办了两个图书证。这样一次可以借两本书，两本看完可以一块还。不用花钱买书。也可以去新华书店看书。同学们是不是省钱又增长知识。</p>
                            <div class="case2"><img src="res/css/bbx/images/print.jpg" ><img src="res/css/bbx/images/print.jpg" ></div>
                        </div>
                        <div class="revise">
                            <p>2小时前</p>
                            <a href="#"onclick="delDiv(this.id)" id="se"><i class="fa fa-trash-o"></i>删除</a>
                            <a href="#"><i class="fa fa-pencil-square-o"></i>编辑</a>
                        </div>
                    </li> -->
                </ul>
            </div>
            <script>
//	图片展示
	
layer.ready(function(){ //为了layer.ext.js加载完毕再执行
	var s=$(".trends ul li").size();
	for(var i=0;i<s;i++){
		var id=$(".trends ul li").eq(i).children().children(".case").attr("id"); 		
    layer.photos({
        photos: '#'+id,
	 	area: 'auto'
    });
	}
});  
</script>