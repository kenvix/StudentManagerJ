<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>论文管理系统</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>编辑论文 #${paper.id}</h1>
<p><a href="/">首页</a></p>

<form action="/paper/edit/${paper.id}" method="post">
    <p>
        <label>
            编号
            <input type="number" name="id" value="${paper.id}">
        </label>
    </p>

    <p>
        <label>
            作者工号
            <input type="number" name="person_id" value="${paper.personId}">
        </label>
    </p>

    <p>
        <label>
            论文标题
            <input type="text" name="title" value="${paper.title}">
        </label>
    </p>

    <p>
        <input type="submit">
    </p>
</form>

</body>
</html>