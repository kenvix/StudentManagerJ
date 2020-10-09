<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>学生管理系统</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>操作失败</h1>

<p>${simpleName}: <b>${message}</b></p>

<p><a href="javascript:window.history.back();">返回上一页</a></p>

<h3>高级信息</h3>
<button onclick="document.getElementById('advanced').style='display:;'; this.style='display:none;'">显示</button>

<div style="display: none" id="advanced">
    <p><small>${name}</small>: ${message}</p>
    <pre>${trace}</pre>
</div>

</body>
</html>