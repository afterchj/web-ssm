var ws = null;
var url = null;
function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('echo').disabled = !connected;
}
function connect() {
    url = updateUrl();
    console.log("url=" + url);
    ws = new WebSocket(url);
    ws.onopen = function () {
        setConnected(true);
        log('Info： 连接成功.');
    };
    ws.onmessage = function (event) {
        log(event.data);
    };
    ws.onclose = function () {
        setConnected(false);
        log('Info： 断开连接.');
        log(event.data);
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
        log(message);
        ws.send(message);
    } else {
        alert('没有建立连接，请连接服务！');
    }
}

function updateUrl() {
    var urlPath;
    if (window.location.protocol == 'http:') {
        urlPath = 'ws://' + window.location.host + "/web-ssm/websocket";
    } else {
        urlPath = 'wss://' + window.location.host + "/web-ssm/websocket";
    }
    return urlPath;
}

function log(message) {
    var console = document.getElementById('console');
    var p = document.createElement('p');
    if (message.indexOf("：") != -1) {
        p.style.textAlign = 'left';
    } else {
        p.style.textAlign = 'right';
    }
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    console.appendChild(p);
    while (console.childNodes.length > 25) {
        console.removeChild(console.firstChild);
    }
    console.scrollTop = console.scrollHeight;
}