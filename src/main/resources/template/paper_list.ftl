<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>论文管理系统</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>论文列表</h1>
<p><a href="/">首页</a></p>

<table>
    <thead>
    <tr>
        <th>编号</th>
        <th>作者工号</th>
        <th>作者姓名</th>
        <th>论文标题</th>
        <th>操作</th>
    </tr>
    </thead>

    <tbody>
    <#list papers as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.personId}</td>
            <td>${item.personName}</td>
            <td>${item.title}</td>
            <td>
                <a href="/person/?filter=person_id&person_id=${item.personId}">人员</a>
                <a href="/paper/?filter=person_id&person_id=${item.personId}">筛选</a>
                <a href="/paper/edit/${item.id}">修改</a>
                <a href="/paper/delete/${item.id}" onclick="return confirm('真要删？')">删</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<h1>添加论文</h1>
<form action="/paper/add" method="post">
    <p>
        <label>
            编号
            <input type="number" name="id" value="">
        </label>
    </p>

    <p>
        <label>
            作者工号
            <input type="number" name="person_id" value="">
        </label>
    </p>

    <p>
        <label>
            论文标题
            <input type="text" name="title" value="">
        </label>
    </p>

    <p>
        <input type="submit">
    </p>
</form>

</body>
</html>