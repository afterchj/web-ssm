var ws = null;
//
// if ('ws' in window) {
//     ws = new WebSocket('ws://' + window.location.host + "/web-ssm/chat.do");
// } else {
//     ws = new SockJS('http://' + window.location.host + '/web-ssm/sockjs/chat.do');
// }

if ('ws' in window) {
    ws = new WebSocket('ws://' + window.location.host + "/web-ssm/websocket");
} else {
    ws = new SockJS('http://' + window.location.host + '/web-ssm/sockjs/websocket');
}

ws.onopen = open;
ws.onmessage = message;
ws.onclose = close;

function message(event) {
    var plan = document.getElementById("console");
    var p = document.createElement('p');
    var img = document.createElement("img");
    if (typeof event.data == 'string') {
        if (event.data.indexOf("：") != -1) {
            p.style.textAlign = 'left';
            // img.style.textAlign = 'right';
        } else {
            p.style.textAlign = 'right';
            img.style.textAlign = 'left';
        }
        p.style.lineHeight = '5px';
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(event.data));
        plan.appendChild(p);
    } else {
        var reader = new FileReader();
        reader.onload = function (eve) {
            if (eve.target.readyState == FileReader.DONE) {
                img.src = this.result;
                img.style.textAlign = 'right';
                plan.appendChild(img);
                // console.log("data=" + reader.result);验证this指的是reader对象
            }
        };
        reader.readAsDataURL(event.data);
    }
    plan.scrollTop = console.scrollHeight;
    // log(event, "");
}

function setConnected(connected) {
    document.getElementById('connect').disabled = !connected;
    document.getElementById('disconnect').disabled = connected;
    document.getElementById('echo').disabled = connected;
}

function open() {
    var plan = document.getElementById("console");
    setConnected(false);
    var p = document.createElement("p");
    p.appendChild(document.createTextNode("Info： 连接成功"));
    plan.appendChild(p);
}
function close() {
    var plan = document.getElementById("console");
    setConnected(true);
    var element = document.createElement("p");
    element.appendChild(document.createTextNode("info：退出！"));
    plan.appendChild(element);
}
function connect() {

    if ('ws' in window) {
        ws = new WebSocket('ws://' + window.location.host + "/web-ssm/websocket");
    } else {
        ws = new SockJS('http://' + window.location.host + '/web-ssm/sockjs/websocket');
    }

    ws.onopen = open();
    ws.onmessage = message();
    ws.onclose = close();

    setConnected(true);
}

function disconnect() {
    setConnected(true);
    if (ws != null) {
        ws.close();
        ws = null;
    }
    var plan = document.getElementById("console");
    var element = document.createElement("p");
    element.appendChild(document.createTextNode("info：断开连接！"));
    plan.appendChild(element);
}

function echo() {
    if (ws.readyState == 1) { //0-CONNECTING;1-OPEN;2-CLOSING;3-CLOSED
        var message = document.getElementById('message').value;
        if (message) {
            var element = document.createElement("p");
            element.style.textAlign = 'right';
            element.appendChild(document.createTextNode(message));
            document.getElementById('console').appendChild(element);
            ws.send(message);
        }
        sendFile(message);
        document.getElementById('message').value = "";
    } else {
        alert('没有建立连接，请连接服务！');
    }
}


function sendFile(isWithText) {
    console.log("File send starting...");
    var inputElement = document.getElementById("file");
    var fileList = inputElement.files;
    var file = fileList[0];
    if (!file) return;
    ws.send(file.name + ":fileStart");
    var reader = new FileReader();
    //以二进制形式读取文件
    reader.readAsArrayBuffer(file);
    //文件读取完毕后该函数响应
    reader.onload = function loaded(evt) {
        var blob = evt.target.result;
        //发送二进制表示的文件
        ws.send(blob);
        if (isWithText) {
            ws.send(file.name + ":fileFinishWithText");
        } else {
            ws.send(file.name + ":fileFinishSingle");
        }
        console.log("File send finish!");
    }
    inputElement.outerHTML = inputElement.outerHTML; //清空<input type="file">的值
}
// function log(event, file) {
//     var message = event.data;
//     var console = document.getElementById('console');
//     var p = document.createElement('p');
//     var img = document.createElement("img");
//     if (typeof message == 'string') {
//         if (message.indexOf("：") != -1) {
//             p.style.textAlign = 'left';
//             img.style.textAlign = 'left';
//         } else {
//             p.style.textAlign = 'right';
//             img.style.textAlign = 'right';
//         }
//         p.style.lineHeight = '5px';
//         p.style.wordWrap = 'break-word';
//         p.appendChild(document.createTextNode(message));
//         console.appendChild(p);
//     }
//
//     while (console.childNodes.length > 25) {
//         console.removeChild(console.firstChild);
//     }
//     console.scrollTop = console.scrollHeight;
// }