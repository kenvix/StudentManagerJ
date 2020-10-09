<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>班级管理系统</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>编辑班级 ${clazz.id}</h1>
<p><a href="/">首页</a></p>

<form action="/class/edit/${clazz.id}" method="post">
    <p>
        <label>
            班号
            <input type="number" name="class_id" value="${clazz.id}">
        </label>
    </p>

    <p>
        <label>
            班主任工号
            <input type="number" name="master_id" value="${clazz.masterId}">
        </label>
    </p>

    <p>
        <input type="submit">
    </p>
</form>

</body>
</html>