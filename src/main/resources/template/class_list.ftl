<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>班级管理系统</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>班级列表</h1>
<p><a href="/">首页</a></p>

<table>
    <thead>
    <tr>
        <th>班号</th>
        <th>班主任姓名</th>
        <th>班主任工号</th>
        <th>操作</th>
    </tr>
    </thead>

    <tbody>
    <#list classes as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.masterName}</td>
            <td>${item.masterId}</td>
            <td>
                <a href="/person/?filter=class_id&class_id=${item.id}">学生</a>
                <a href="/course/?filter=class_id&class_id=${item.id}">课程</a>
                <a href="/class/edit/${item.id}">修改</a>
                <a href="/person/delete/${item.id}" onclick="return confirm('真要删？')">删</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<h1>添加班级</h1>
<form action="/class/add" method="post">
    <p>
        <label>
            班号
            <input type="number" name="class_id" value="">
        </label>
    </p>

    <p>
        <label>
            班主任工号
            <input type="number" name="master_id" value="">
        </label>
    </p>

    <p>
        <input type="submit">
    </p>
</form>

</body>
</html>