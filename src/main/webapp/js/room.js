var url = window.location.host + "/web-ssm";
var websocket = null;

if ('websocket' in window) {
    websocket = new WebSocket("ws://" + url + "/chat.do");
} else {
    websocket = new SockJS("http://" + url + "/sockjs/chat.do");
}
//
// if ('websocket' in window) {
//     websocket = new WebSocket('ws://' + window.location.host + "/web-ssm/websocket");
// } else {
//     websocket = new SockJS('http://' + window.location.host + '/web-ssm/sockjs/websocket');
// }

websocket.onopen = onOpen;
websocket.onmessage = onMessage;
websocket.onerror = onError;
websocket.onclose = onClose;

function onOpen() {
    console.log("opening...");
    document.getElementById("plane").innerHTML = document.getElementById("plane").innerHTML + "OPEN<br/>";
}

function onMessage(event) {
    if (typeof event.data == 'string') {
        var element = document.createElement("p");
        element.innerHTML = event.data;
        document.getElementById("plane").appendChild(element);
    } else {
        var reader = new FileReader();
        reader.onload = function (eve) {
            if (eve.target.readyState == FileReader.DONE) {
                var img = document.createElement("img");
                img.src = this.result;
                document.getElementById("plane").appendChild(img);
                // console.log("data=" + reader.result);验证this指的是reader对象
            }
        };
        reader.readAsDataURL(event.data);
    }
}
function onError() {
    console.log("未知错误！");
}
function onClose(event) {
    console.log("关闭连接！");
    document.getElementById("plane").innerHTML = document.getElementById("plane").innerHTML + "CLOSE<br/>";
}

function doSend() {
    if (websocket.readyState == 1) {  //0-CONNECTING;1-OPEN;2-CLOSING;3-CLOSED
        var msg = document.getElementById("message").value;
        if (msg) {
            websocket.send(msg);
        }
        sendFile(msg);
        document.getElementById("message").value = "";
    } else {
        alert("connect fail!");
    }
}

function sendFile(isWithText) {
    console.log("File send starting...");
    var inputElement = document.getElementById("file");
    var fileList = inputElement.files;
    var file = fileList[0];
    if (!file) return;
    websocket.send(file.name + ":fileStart");
    var reader = new FileReader();
    //以二进制形式读取文件
    reader.readAsArrayBuffer(file);
    //文件读取完毕后该函数响应
    reader.onload = function loaded(evt) {
        var blob = evt.target.result;
        //发送二进制表示的文件
        websocket.send(blob);
        if (isWithText) {
            websocket.send(file.name + ":fileFinishWithText");
        } else {
            websocket.send(file.name + ":fileFinishSingle");
        }
        console.log("finnish");
    }
    inputElement.outerHTML = inputElement.outerHTML; //清空<input type="file">的值
}

function disconnect() {
    if (websocket != null) {
        websocket.close();
        websocket = null;
    }
}

function reconnect() {
    if (websocket != null) {
        websocket.close();
        websocket = null;
    }
    if ('websocket' in window) {
        websocket = new WebSocket("ws://" + url + "/chat.do");
    } else {
        websocket = new SockJS("http://" + url + "/sockjs/chat.do");
    }
    websocket.onopen = onOpen;
    websocket.onmessage = onMessage;
    websocket.onerror = onError;
    websocket.onclose = onClose;
}
function clean() {
    document.getElementById("plane").innerHTML = "";
}