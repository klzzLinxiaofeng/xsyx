<%@page import="platform.education.resource.contants.ResourceType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ include file="/views/embedded/common.jsp"%>
    <%@ include file="/views/embedded/plugin/jwplayer.jsp"%>
    <title>资源播放</title>

    <script type="text/javascript">
        //调用JWPlayer
        function callJwplay(httpUrl, width, height) {
            currentPlayer = jwplayer("res_player").setup({
                width: width,
                height: height,
                primary: "flash",
                autostart: true,
                //image: "/image/teacher/music2.jpg",
                file: httpUrl,
                events: {
                    onPlay: function (event) {
                    },
                    onBeforePlay: function (event) {
                    },
                    onPause: function (event) {
                    },
                    onReady: function (event) {
                    },
                    onComplete: function (event) {
                    },
                    onSeek: function (event) {
                    }
                }
            });
        }
    </script>
</head>
<body>
    <div class="container-fluid">
        <jsp:include page="/views/embedded/navigation.jsp">
            <jsp:param value="fa-asterisk" name="icon" />
            <jsp:param value="资源查看" name="title" />
            <jsp:param value="${param.dm}" name="menuId" />
        </jsp:include>
        <div class="row-fluid">
            <div class="span12">
                <div class="content-widgets white">
                    <div class="widget-head">
                        <h3>资源查看
                            <p style="float: right;" class="btn_link">
                                <a class="a4" onclick="backFun()" href="#"><i class="fa fa-reply"></i> 返回 </a>
                            </p>
                        </h3>
                    </div>
                    <div class="content-widgets" style="margin-bottom: 0">
                        <div class="video_div">
                            <div class="title_1">
<%--                                <p>正在查看: &nbsp;</p>--%>
                                <div class="item-title">
                                    <span
                                        class="${iconFn:getClassName(res.iconType)} icon-file res-iconb"
                                        style="margin-top: 10px;"></span> <span
                                        style="overflow: hidden; width: 670px; float: left; display: block; white-space: nowrap; text-overflow: ellipsis; word-wrap: normal;">
                                        <a href="${res.thumbnailUrl}" target="_blank">
                                            ${res.title}
                                        </a>
                                    </span>
                                </div>
                            </div>
                            <div class="v_div">
                                <div class="fx_left">
                                    <div class="fx_1">
                                        <c:choose>
                                            <c:when test="${faved}">
                                                <a id="sca" href="javascript:void(0)"class="a1" style="background: url('/cr/res/images/ysc.png') no-repeat 10px center;" onclick="fav('${res.id}')">已收藏</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a id="sca" href="javascript:void(0)"class="a1" style="background: url('/cr/res/images/wsc.png') no-repeat 10px center;" onclick="fav('${res.id}')">收藏</a>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:choose>
                                            <c:when test="${liked}">
                                                <a id="dza" href="javascript:void(0)" class="a3" style="background: url('/cr/res/images/ydz.png') no-repeat 10px center;" onclick="like('${res.id}', this)" style="width:0;"></a>
                                            </c:when>
                                            <c:otherwise>
                                                <a id="dza" href="javascript:void(0)" class="a3" style="background: url('/cr/res/images/wdz.png') no-repeat 10px center;" onclick="like('${res.id}', this)" style="width:0;"></a>
                                            </c:otherwise>
                                        </c:choose>


                                            <a onclick="downloadRes(${res.integral}, ${res.id}, '${res.title}')" href="javascript:void(0)" class="a2" style="width:100px;">下载</a>
                                    </div>
                                    <div id="commentList">
                                        <jsp:include page="../comment.jsp" />
                                    </div>
                                    <div style="text-align:center">
                                        <jsp:include page="/views/embedded/jqpagination.jsp" flush="true">
                                            <jsp:param name="id" value="commentList" />
                                            <jsp:param name="url" value="/message/comment/index" />
                                            <jsp:param name="pageSize" value="${page.pageSize}" />
                                        </jsp:include>
                                    </div>
                                </div>
                                <div class="fx_right">
                                    <div class="g1">
                                    	<a href="${ctp}/resource/owner/list?ownerId=${res.userId}" style="display:block;height:100%">
                                        	<img src="<avatar:avatar userId='${res.userId}'></avatar:avatar>"/>
                                        	<p class="p1">资源所属 :${res.userName}</p>
                                        </a>
                                    </div>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript">
var objId = "${res.uuid}";
var resType = "${resType}";

var faved=${faved};
var liked=${liked};

function backFun(){
    history.back(-1);
}
function downloadRes(integral, resId, title) {
	if(integral==null || integral==0) {
		window.location.href="${pageContext.request.contextPath}/resource/download?Id=" + resId;
		return;
	}
    $.ajax({
        url: "${pageContext.request.contextPath}/resource/getFileSize",
        type: "POST",
        data: {"resId": resId},
        async: true,
        success: function(data) {
        	if(data==0) {
        		$.alert("资源已经被删除");
        	} else {
        		var url = "${pageContext.request.contextPath}/resource/integral?resId="+resId+"&integral="+integral;
        		$.initWinOnTopFromLeft_bbx(title, url, "380", "230")
        	}
        }
   });
}

function fav(id) {
	var requestData = {"id":id, "isFav" : !faved};
	$.post("${pageContext.request.contextPath}/resource/fav", requestData, function(data, status) {
		if("success" === status) {
            var msg="收藏";
            var img="/cr/res/images/ysc.png";
            var name="已收藏";
            if(faved){
                msg="取消收藏";
                img="/cr/res/images/wsc.png";
                name="收藏";
            }
			if ("faved" === data) {
				$.success("已收藏过");
			} else if ("success" === data) {
			    $("#sca").css("background","url('"+img+"') no-repeat 10px center");
                $("#sca").text(name);
			    faved=!faved;
				$.success(msg+"成功");
			} else {
				$.error(msg+"失败");
			}		
		} else {
			$.error("服务器响应异常!");
		}
	});
}

function like(id, obj) {
    var requestData = {"Id":id, "isLike": !liked};
    $.post("${pageContext.request.contextPath}/resource/like", requestData, function (data, status) {
        if ("success" === status) {
            var msg="点赞";
            var img="/cr/res/images/ydz.png";
            if(liked){
                msg="取消点赞";
                img="/cr/res/images/wdz.png";
            }
            if ("faved" === data) {
                $.success("已赞过");
            } else if ("success" === data) {
                $("#dza").css("background","url('"+img+"') no-repeat 10px center");
                liked=!liked;
                $.success(msg+"成功");
                // var likeCount = $(obj).html();
                // $(obj).html(parseInt(likeCount) + 1);
            } else {
                $.error(msg+"失败");
            }
        } else {
            $.error("服务器响应异常!");
        }
    });
}

$(function () {
     <c:if test="${res==null}">
         $.alert("该资源已被删除");
     </c:if>
	$.getConversionResult({"entityFileUUID":"${res.uuid}"},function(data){
	var result = data.result;
	var defUrl = "${ctp}/res/plugin/pdf/ROOT/web/viewer.html?file=";
	if(result != null){
		if(result == 4 || result == 8||result == 7){
			var pdfUrl = data.pdfUrl;
			if(pdfUrl != null && pdfUrl != ""){
				$("#pdfPlayer").attr("src",defUrl + pdfUrl);
				$("#pdfPlayer").show();
			}
		}else if(result == 3){
			$("#error_message").show()
			$("#download_img").show();
			$("#download_a").show();
		}else{
			$(".download_img").show();
			$(".download").show();
		}
	}else{
		$(".download_img").show();
		$(".download").show();
	}
});
    initComment();
});
function comment() {
    var content = $("#content1").val() + "";
    if (content === "" || content === "undefined") {
        $.alert("请输入评论再发表！");
        return;
    }
    var requestData = {"objectId": "-1","appId":666, "resType": "-1", "content": content,"resourceId":"${res.id}"};
    $.post("${pageContext.request.contextPath}/message/comment/creator", requestData, function (data, status) {
        if ("success" === status) {
            data = eval("(" + data + ")");
            if ("success" === data.info) {
                $.success("发表成功");
            } else {
                $.error("发表失败");
            }
            initComment();
        } else {
            $.error("服务器响应异常!");
        }
    });
}
     
function initComment() {
	var val = {};
	val.resourceId = "${res.id}";
    val.appId=666;
	var id = "commentList";
	var url = "/message/comment/index";
	myPagination(id, val, url);
}
     
function previewLp(lpId) {
	var url = "${pageContext.request.contextPath}/learningPlan/edit?type=view&editable=false&id="+lpId;
	window.open(url, "预览");
}

var tab = 0;

function updateScoring(resId, score) {
	if(tab==0) {
		$.ajax({
	        url: "${pageContext.request.contextPath}/resource/updateScore",
	        type: "POST",
	        data: {"resId": resId, "score":score},
	        async: false,
	        success: function(data) {
	        	tab = 1;
	        }
	   });
	}
}

var num=finalnum = tempnum= 0;
var lis = $(".pf_flower li");

function fnShow(num) {
	if(tab==0) {
		finalnum= num || tempnum;//如果传入的num为0，则finalnum取tempnum的值
		for (var i = 0; i < lis.length; i++) {
			lis[i].className = i < finalnum? "light" : "";//点亮星星就是加class为light的样式
		}
	}
}

for (var i = 1; i <= lis.length; i++) {
	lis[i - 1].index = i;
	lis[i - 1].onmouseover = function() { //鼠标经过点亮星星。
		fnShow(this.index);//传入的值为正，就是finalnum
	}
	lis[i - 1].onmouseout = function() { //鼠标离开时星星变暗
		fnShow(0);//传入值为0，finalnum为tempnum,初始为0
	}
	lis[i - 1].onclick = function() { //鼠标点击,同时会调用onmouseout,改变tempnum值点亮星星
		tempnum= this.index;
	}
}


</script>
</html>