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
    <title>Spring4 websocket实例</title>
    <style type="text/css">

        div {
            margin: 5px 5px;
        }

        #console-container {
            margin-left: 15px;
            width: 40%;
        }

        #console {
            overflow-y: scroll;
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 400px;
            width: 100%;
        }

    </style>
    <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
</head>
<body>
<noscript>
    <h2 style="color: #ff0000">
        Seems your browser doesn't support Javascript! Websockets
        rely on Javascript being enabled. Please enable
        Javascript and reload this page!
    </h2>
</noscript>
<div>
    <h6>当前用户：${WEBSOCKET_USERNAME}</h6>
</div>
<div id="console-container">
    <div id="console"></div>
</div>
<div>
    <input type="button" id="connect" value="连接服务器" onclick="connect();">
    <input type="button" id="disconnect" value="断开连接" disabled="disabled" onclick="disconnect();">
    <input type="file" id="file">
</div>
<div>
    <textarea id="message" rows="2" cols="50">输入你要发送的内容!</textarea>
</div>
<div>
    <button id="echo" onclick="echo();">发送消息</button>
</div>
</body>
<script type="text/javascript" src="../js/chat.js"></script>
</html>
