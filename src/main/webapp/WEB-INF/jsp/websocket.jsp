<%--
  Created by IntelliJ IDEA.
  User: zhm
  Date: 2015/7/14
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no"/>
    <title>Spring4 websocket实例</title>
    <style type="text/css">

        li {
            float: left;
            list-style: none;
        }

        div {
            margin: 0 5px;
        }

        @media screen and (max-width: 1000px) {
            .ul {
                margin-left: -30px;
                width: 100%;
            }

            #console {
                overflow-y: scroll;
                /*border: 1px solid #CCCCCC;*/
                /*border-right-color: #999999;*/
                /*border-bottom-color: #999999;*/
                height: 500px;
                width: 350px;
            }

            .common {
                width: 350px;
            }

            .context {
                margin: 5px 5px;
                width: 350px;
            }

            .text {
                width: 350px;
            }

            .send {
                width: 350px;
            }

            .img {
                margin: 5px 10px;
                max-width: 200px;
            }
        }

        @media screen and (min-width: 1200px) {
            .ul {
                height: 30px;
                width: 100%;
                margin: 10px 0;;
            }

            .context {
                margin: 5px 5px;
                height: 30px;
                width: 400px;
            }

            #console {
                overflow-y: scroll;
                border: 1px solid #CCCCCC;
                border-right-color: #999999;
                border-bottom-color: #999999;
                height: 800px;
                width: 45%;
            }

            .text {
                width: 400px;
            }

            .send {
                margin: 5px 5px;
            }

            .img {
                margin: 5px 10px;
                max-width: 400px;
            }
        }

        .p {
            word-break: break-all;
            white-space: pre-wrap;
            word-wrap: break-word;
        }

    </style>
    <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            var ls = document.getElementsByTagName("li");
            ls[0].innerHTML += document.body.clientWidth;
            ls[1].innerHTML += document.body.clientHeight;
        }
    </script>
</head>
<body>
<noscript>
    <h2 style="color: #ff0000">
        Seems your browser doesn't support Javascript! Websockets
        rely on Javascript being enabled. Please enable
        Javascript and reload this page!
    </h2>
</noscript>
<ul class="ul">
    <li>宽度：</li>
    <li>高度：</li>
</ul>
<input type="hidden" id="user" value="${WEBSOCKET_USERNAME}">
<div class="common">
    <input type="button" id="connect" value="连接服务器" onclick="connect();">
    <input type="button" id="disconnect" value="断开连接" disabled="disabled" onclick="disconnect();">
</div>
<div class="context">
    <input id="message" type="text" class="text" value="输入你要发送的内容!"/><br>
</div>
<div class="send">
    <input type="button" id="echo" onclick="echo();" value="发送消息"/>
    <input type="file" id="file">
</div>
<div id="console"></div>
</body>
<script type="text/javascript" src="../js/chat.js"></script>
</html>
