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
    <title>多人聊天室</title>
    <style type="text/css">

        li {
            float: left;
            list-style: none;
        }

        input {
            margin: 5px 5px;
        }

        div {
            margin: 0 5px;
        }

        .text {
            margin-top: 5px;
            height: 30px;
            width: 350px;
        }

        .p {
            word-break: break-all;
            white-space: pre-wrap;
            word-wrap: break-word;
        }

        @media screen and (max-width: 500px) {
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

            .text {
                width: 340px;
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

            .common {
                float: left;
                width: 450px;
            }

            #console {
                float: left;
                overflow-y: scroll;
                border: 1px solid #CCCCCC;
                border-right-color: #999999;
                border-bottom-color: #999999;
                height: 600px;
                width: 40%;
            }

            .text {
                width: 400px;
            }

            .img {
                margin: 5px 10px;
                max-width: 400px;
            }
        }


    </style>
    <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.7.min.js"></script>
    <script type="text/javascript">
        window.onload = function () {
            var ls = document.getElementsByTagName("li");
            ls[0].innerHTML += document.body.clientWidth;
            ls[1].innerHTML += document.body.clientHeight;
            document.getElementById("message").onfocus = function () {
                document.getElementById('message').value = "";
            }
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
    <input type="button" id="disconnect" value="断开连接" disabled="disabled" onclick="disconnect();"><br>
    <textarea id="message" type="text" class="text">输入你要发送的内容</textarea>
    <input type="button" id="echo" onclick="echo();" value="发送消息"/>
    <input type="file" id="file">
</div>
<%--<div class="send"></div>--%>
<div id="console"></div>
</body>
<script type="text/javascript" src="../js/chat.js"></script>
</html>
