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
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />
    <title>Spring4 websocket实例</title>
    <style type="text/css">
        #console p {
            padding: 0;
            margin: 0;
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
    <h6>当前用户：${WEBSOCKET_USERNAME}</h6><a href="http://localhost:8080/login?loginType=token&gh=${WEBSOCKET_USERNAME}"></a>
    <div>
        <button id="disconnect" onclick="disconnect()">disconnect</button>
        <button id="reconnect" onclick="reconnect()">reconnect</button>
        <button id="clean" onclick="clean()">clean</button>
        <!-- <br>
        <input type="button" value="sendFile" onclick="sendFile()"/> -->
        <input type="file" id="file"/>
    </div>
    <div>
        <input id="message" type="text" style="width: 350px"/>
    </div>
    <button id="send" onclick="doSend()">send</button>
    <div id="plane"></div>
</div>
</body>
<script type="text/javascript" src="js/room.js"></script>
</html>
