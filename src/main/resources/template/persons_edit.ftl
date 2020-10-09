<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>学生管理系统</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>编辑人员 #${person.id}</h1>
<p><a href="/">首页</a></p>

<h2>人员基本信息</h2>
<form action="/person/edit/${person.id}" method="post">
    <p>
        <label>
            工号
            <input type="number" name="id">
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
            <select name="sex">
                <option value="unknown">unknown</option>
                <option value="male">male</option>
                <option value="female">female</option>
            </select>
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
         <input type="submit">
    </p>
</form>

<h2>学生班级信息</h2>

</body>
</html>