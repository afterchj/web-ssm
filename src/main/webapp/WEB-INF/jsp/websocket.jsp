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
    <meta charset="utf-8">
    <style type="text/css">
        #connect-container {
            width: 20%
        }

        #connect-container div {
            margin: 5px 0;
        }

        #console-container {
            margin-left: 15px;
            width: 40%;
        }

        #console {
            border: 1px solid #CCCCCC;
            border-right-color: #999999;
            border-bottom-color: #999999;
            height: 300px;
            overflow-y: scroll;
            margin: 5px 0;
            width: 100%;
        }

        #console p {
            margin: 0 0 5px 0;
            /*margin: 0;*/
        }
    </style>
    <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
    <script src="../js/chat.js"></script>
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
<div id="connect-container">
    <div>
        <button id="connect" onclick="connect();">连接服务器</button>
        <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
    </div>
    <div>
        <textarea id="message" rows="2" cols="40">输入你要发送的内容!</textarea>
    </div>
    <div>
        <button id="echo" onclick="echo();" disabled="disabled">发送消息</button>
    </div>
</div>
</body>
</html>
