<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1" />
    <title>响应式布局</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            text-decoration: none;
            list-style: none;
            font-size: 14px;
            font-family: "微软雅黑";
            text-align: center;
            color: #fff;
        }

        .clear {
            clear: both;
        }

        #header, #content, #footer {
            margin: 0 auto;
            margin-top: 10px;
        }

        #header, #footer {
            height: 100px;
        }

        #header, #footer, .left, .right, .center {
            margin-top: 10px;
            height: 50px;
            background: #333;
        }

        /*通用样式*/
        /*手机*/
        @media screen and (max-width: 1200px) {
            #header, #content, #footer {
                width: 800px;
            }

            .right, .center {
                margin-top: 10px;
            }

            .left, .right {
                width: 800px;
                height: 100px;
            }

            .center {
                background: red;
                height: 200px;
            }

        }

        /*平板*/
        @media screen and (min-width: 1200px) and (max-width: 1600px) {
            #header, #content, #footer {
                width: 600px;
            }

            .left, .center {
                margin-top: 10px;
                width: 600px;
            }

            .right {
                display: none;
            }

            .center {
                background: green;
                height: 200px;
            }
        }

        /*PC*/
        @media screen and (min-width: 1400px) {
            #header, #content, #footer {
                margin-top: 10px;
                width: 100%;
            }

            .left, .center, .right {
                margin: 5px auto;
                width: 1000px;
                height: 100px;

            }

            .center {
                background: blue;
                width: 1000px;
            }

            #content {
                border: 1px solid black;
            }
        }
    </style>
</head>
<body>
<!--header start-->
<div id="header">header</div>
<!--end header-->
<!--content start-->
<div id="content">
    <div class="left">left</div>
    <div class="center">center</div>
    <div class="right">right</div>
    <div></div>
</div>
<!--end content-->
<!--footer-->
<div id="footer">footer</div>
<!--end footer-->
</body>
</html>
