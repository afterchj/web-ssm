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

    <script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
    <script type="text/javascript">
        var ws = null;
        var url = null;
        var transports = [];

        function setConnected(connected) {
            document.getElementById('connect').disabled = connected;
            document.getElementById('disconnect').disabled = !connected;
            document.getElementById('echo').disabled = !connected;
        }

        function connect() {
            if (!url) {
                alert('请选择使用W3C的websocket还是SockJS');
                return;
            }

            ws = (url.indexOf('sockjs') != -1) ?
                new SockJS(url, undefined, {protocols_whitelist: transports}) : new WebSocket(url);

            ws.onopen = function () {
                setConnected(true);
                log('Info: 连接成功.');
            };
            ws.onmessage = function (event) {
                log(event.data);
            };
            ws.onclose = function (event) {
                setConnected(false);
                log('Info: 断开连接.');
                log(event);
            };
        }

        function disconnect() {
            if (ws != null) {
                ws.close();
                ws = null;
            }
            setConnected(false);
        }

        function echo() {
            if (ws != null) {
                var message = document.getElementById('message').value;
                ws.send(message);
            } else {
                alert('没有建立连接，请连接服务！');
            }
        }

        function updateUrl(urlPath) {
            if (urlPath.indexOf('sockjs') != -1) {
                url = urlPath;
                document.getElementById('sockJsTransportSelect').style.visibility = 'visible';
            }
            else {
                if (window.location.protocol == 'http:') {
                    url = 'ws://' + window.location.host + "/web-ssm" + urlPath;
                } else {
                    url = 'wss://' + window.location.host + urlPath;
                }
                document.getElementById('sockJsTransportSelect').style.visibility = 'hidden';
            }
        }

        function updateTransport(transport) {
            alert(transport);
            transports = (transport == 'all') ? ['websocket',
                    'xdr-streaming',
                    'xhr-streaming',
                    'iframe-eventsource',
                    'iframe-htmlfile',
                    'xdr-polling',
                    'xhr-polling',
                    'iframe-xhr-polling',
                    'jsonp-polling'] : [transport];
        }

        function log(message) {
            var console = document.getElementById('console');
            var p = document.createElement('p');
            p.style.wordWrap = 'break-word';
            p.appendChild(document.createTextNode(message));
            console.appendChild(p);
            while (console.childNodes.length > 25) {
                console.removeChild(console.firstChild);
            }
            console.scrollTop = console.scrollHeight;
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
<div>
    <h6>当前用户：${WEBSOCKET_USERNAME}</h6>
    <div id="connect-container">
        <input id="radio1" type="radio" name="group1" onclick="updateUrl('/websocket');">
        <label for="radio1">W3C WebSocket</label>
        <br>
        <input id="radio2" type="radio" name="group1" onclick="updateUrl('/sockjs/websocket');">
        <label for="radio2">SockJS</label>
        <div id="sockJsTransportSelect" style="visibility:hidden;">
            <span>SockJS transport:</span>
            <select onchange="updateTransport(this.value)">
                <option value="all">all</option>
                <option value="websocket">websocket</option>
                <option value="xdr-streaming">xdr-streaming</option>
                <option value="xhr-streaming">xhr-streaming</option>
                <option value="iframe-eventsource">iframe-eventsource</option>
                <option value="iframe-htmlfile">iframe-htmlfile</option>
                <option value="xdr-polling">xdr-polling</option>
                <option value="xhr-polling">xhr-polling</option>
                <option value="iframe-eventsource">iframe-eventsource</option>
                <option value="iframe-xhr-polling">iframe-xhr-polling</option>
                <option value="jsonp-polling">jsonp-polling</option>
            </select>
        </div>
        <div>
            <button id="connect" onclick="connect();">连接服务器</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">断开连接</button>
        </div>
        <div>
            <textarea id="message" style="width: 350px">输入你要发送的内容!</textarea>
        </div>
        <div>
            <button id="echo" onclick="echo();" disabled="disabled">发送消息</button>
        </div>
    </div>
    <div id="console-container">
        <div id="console"></div>
    </div>
</div>
</body>
</html>
