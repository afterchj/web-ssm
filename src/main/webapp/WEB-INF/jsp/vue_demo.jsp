<%--
  Created by IntelliJ IDEA.
  User: after
  Date: 2020/5/15
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Vue.JS</title>
    <style type="text/css">
        .class1{
            background: #777;
            color: #eee;
        }
        #list-1{
            font-weight: bolder;
        } 
    </style>
    <script type="text/javascript" src='/web-ssm/js/vue.js'></script>
</head>
<body>
<div id="app">
    <div v-html="message"></div>
    {{5+5}}<br>
    {{ use ? 'YES' : 'NO' }}<br>
    {{ str.split('').reverse().join('') }}
    <div v-bind:id="'list-' + id">菜鸟教程</div>
    <label for="r1">修改颜色</label><input type="checkbox" v-model="use" id="r1">
    <br><br>
    <div v-bind:class="{'class1': use}">
        v-bind:class 指令
    </div>
</div>
</body>

<script type="text/javascript">
    new Vue({
        el: '#app',
        data: {
            message: '<h1>vue入门教程</h1>',
            str:'dlroW olleH',
            use: true,
            id:1
        }
    })
</script>
</html>
