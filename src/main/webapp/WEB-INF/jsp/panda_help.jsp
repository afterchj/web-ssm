<%--
  Created by IntelliJ IDEA.
  User: hongjian.chen
  Date: 2018/4/26
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1"/>
    <title>熊猫赚常见问题</title>
    <link href="/web-ssm/css/panda.css" rel="stylesheet" type="text/css">
    <style type="text/css">

        .hidden {
            display: none;
        }

        /*.show {*/
        /*display: block;*/
        /*}*/
    </style>
    <script type="text/javascript" src="/web-ssm/js/jquery-1.7.min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#myButton").focus(function () {
                $(this).val('');
            });
            $("[id^=dt_]").click(function () {
                var str = $(this).html();
                var id = str.substring(0, 2).trim();
                var menu = document.getElementById("dd_" + id);
                var status = menu.style.display;
//                alert("status=" + status);
                if (status == 'block') {
                    menu.style.display = 'none';
                } else {
                    menu.style.display = 'block';
                }
            });
        });
    </script>
</head>
<body>

<div class="wrap">
    <div class="search">
        <hr style="margin-left: -20px">
        <form action="FAQ" method="post">
            <div class="part">
                <div class="ptit ptit1">
                    <c:choose>
                        <c:when test="${empty keyword}">
                            <input id="myButton" style="height:30px;color: gray;width: 200px;" type="text" name="keyword" value="请输入关键字">
                        </c:when>
                        <c:otherwise>
                            <input style="height:30px;color: gray;width: 200px;" type="text" name="keyword" value=" ${keyword}">
                        </c:otherwise>
                    </c:choose>
                    <input style="width:80px;font-size: larger" type="submit" value="搜索">
                </div>
            </div>
        </form>
        <hr style="margin-left: -20px;">
    </div>
    <div class="faq">
        <c:forEach items="${list}" var="faq" varStatus="state">
            <div class="part" style="margin-top: 20px;">
                <div class="ptit ptit1">
                    <dl>
                        <dt id="dt_${faq.id}">${faq.id} ${faq.question}</dt>
                        <dd class="hidden" id="dd_${faq.id}" onclick="window.location='${faq.url}'">${faq.answer}</dd>
                    </dl>
                </div>
            </div>
        </c:forEach>
    </div>
    <c:if test="${list ==null}">
        <c:if test="${flag== '0'}">
            <span style="color: red">抱歉，没有找到相关问题，以下是所有问题</span>
        </c:if>
        <iframe width="100%" height="80%" frameborder="0" src="../search.jsp"></iframe>
    </c:if>

    <!--  <div style="margin-top: 1px;">
            <div class="ptit2">在线咨询</div>
                <div class="ptit3"><p>客服时间：周一到周五 9:00到18:00 </p></div>
         </div>	 -->

    <!-- <div style="margin-top: 2px;">
        <ui>
            <li class="part1">
                <p>客服3群</p>
            </li>
            <li class="part2">
                <a class="bg" href="http://shang.qq.com/wpa/qunwpa?idkey=36584308a04fa22913cc8c293d26990a6231d1c8ccbca3d555f718900172f2a6">
                    <img src="image/QQ.png">
                    <p>472176713</p>
                </a>
            </li>
        </ui>
    </div> -->

    <!-- <div style="margin-top: 1px;">
        <ui>
            <li class="part1">
                <p>客服7群</p>
            </li>
            <li class="part2">
                <a class="bg" href="http://shang.qq.com/wpa/qunwpa?idkey=ae5ae59830b1776ea558a859b55f63536fb2511d9a36515ac72eedc30477f05d">
                    <img src="image/QQ.png">
                    <p>347362985</p>
                </a>
            </li>
        </ui>
    </div> -->
</div>

</body>
</html>
