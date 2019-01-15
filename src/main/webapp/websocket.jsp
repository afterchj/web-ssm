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
        #connect-container {
            float: left;
            width: 400px
        }

        #connect-container div {
            padding: 5px;
        }

        #console-container {
            float: left;
            margin-left: 15px;
            width: 400px;
        }

        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 170px;
            overflow-y: scroll;
            padding: 5px;
            width: 100%;
        }

        #console p {
            padding: 0;
            margin: 0;
        }
    </style>
    <script src="js/room.js"></script>
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
</html>
