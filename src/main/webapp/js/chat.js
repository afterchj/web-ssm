var url = window.location.host + "/web-ssm";
var ws = null;

if ('ws' in window) {
    ws = new WebSocket('ws://' + url + "/websocket");
} else {
    ws = new SockJS('http://' + url + '/sockjs/websocket');
}
ws.onopen = open;
ws.onmessage = message;
ws.onclose = close;
function message(event) {
    var result = event.data;
    var user = document.getElementById("user").value;
    var plan = document.getElementById("console");
    // var img = document.createElement("img");
    if (typeof result == 'string') {
        var p = document.createElement('p');
        p.classList.add('p');
        var self = result.split("：", 1);
        var info = result.substr(result.indexOf("：") + 1, result.length);
        if (self != user) {
            p.style.textAlign = 'left';
            // img.style.clean = 'both';
            // img.style.float = 'left';
            info = result;
        } else {
            p.style.textAlign = 'right';
            // img.style.clean = 'both';
            // img.style.float = 'right';
        }
        p.appendChild(document.createTextNode(info));
        plan.appendChild(p);
        document.getElementById("console").scrollTop = document.getElementById("console").scrollHeight;
    } else {
        var reader = new FileReader();
        reader.onload = function (eve) {
            if (eve.target.readyState == FileReader.DONE) {
                var img = document.createElement("img");
                var br = document.createElement("br");
                img.style.clean = 'both';
                img.classList.add('img');
                img.src = this.result;
                plan.appendChild(img);
                plan.appendChild(br);
                document.getElementById("console").scrollTop = document.getElementById("console").scrollHeight;
                // console.log("data=" + reader.result);验证this指的是reader对象
            }
        };
        reader.readAsDataURL(event.data);
    }
}

function setConnected(connected) {
    document.getElementById('connect').disabled = !connected;
    document.getElementById('disconnect').disabled = connected;
    document.getElementById('echo').disabled = connected;
}

function open() {
    setConnected(false);
}

function close() {
    ws.send('已退出！');
    disconnect();
    setConnected(true);
    // var plan = document.getElementById("console");
    // var element = document.createElement("p");
    // element.appendChild(document.createTextNode("info：退出！"));
    // plan.appendChild(element);
}
function connect() {
    if ('ws' in window) {
        ws = new WebSocket('ws://' + url + "/websocket");
    } else {
        ws = new SockJS('http://' + url + '/sockjs/websocket');
    }
    ws.onopen = open;
    ws.onmessage = message;
    ws.onclose = close;
    setConnected(false);
}

function disconnect() {
    setConnected(true);
    if (ws != null) {
        ws.close();
        ws = null;
    }
}

function echo() {
    if (ws.readyState == 1) { //0-CONNECTING;1-OPEN;2-CLOSING;3-CLOSED
        var message = document.getElementById('message').value;
        if (message) {
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