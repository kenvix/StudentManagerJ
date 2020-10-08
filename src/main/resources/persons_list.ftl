<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>学生管理系统</title>
</head>
<body>
<h1>学生管理系统</h1>

<table>
    <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>类型</th>
            <th>可毕业</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
    </thead>

    <tbody>
    <#list persons as item>
        <tr>
            <td>${item.id}</td>
            <td>${item.name}</td>
            <td>${item.sex}</td>
            <td>${item.type}</td>
            <td>${item.isGraduable}</td>
            <td>${item.status}</td>
            <td>
                <a href="/class/${item.id}">班级</a>
                <a href="/paper/${item.id}">论文</a>
                <a href="/course/${item.id}">课程</a>
                <a href="/person/edit/${item.id}">修改</a>
                <a href="/person/delete/${item.id}" onclick="return confirm('真要删？')">删</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<h1>添加人</h1>
<form action="/person/add" method="post">
    <p>
        <label>
            学号
            <input type="text" name="id">
        </label>
    </p>

    <p>
        <label>
            姓名
            <input type="text" name="name">
        </label>
    </p>

    <p>
        <label>
            性别
            <input type="text" name="sex">
        </label>
    </p>

    <p>
        <label>
            类型
            <select name="type">
                <option value="teacher">teacher</option>
                <option value="student_undergraduate">student_undergraduate</option>
                <option value="student_postgraduate">student_postgraduate</option>
            </select>
        </label>
    </p>

    <p>
        <label>
            状态
            <select name="status">
                <option value="normal">normal</option>
                <option value="graduated">graduated</option>
                <option value="dropped">dropped</option>
            </select>
        </label>
    </p>

    <p>
        <label>
            可毕业
            <input type="checkbox" name="is_graduable">
        </label>
    </p>

    <p>
        <label>
            班级
            <input type="text" name="class_id">
        </label>
    </p>

    <p>
         <input type="submit">
    </p>
</form>

</body>
</html>