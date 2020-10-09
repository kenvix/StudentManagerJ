<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>学生管理系统</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>人员列表</h1>
<p>
    <a href="/">首页</a>
    <a href="/person">全体人员</a>
    <a href="/person/?filter=type&type=teacher">仅教师</a>
    <a href="/person/?filter=type&type=student_undergraduate">仅本科生</a>
    <a href="/person/?filter=type&type=student_postgraduate">仅研究生</a>
</p>
<table>
    <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>类型</th>
            <th>班级</th>
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
            <td>${names.sex[item.sex]}</td>
            <td>${names.type[item.type]}</td>
            <td>${item.clazz}</td>
            <td>${names.intbool[item.isGraduable?c]}</td>
            <td>${names.status[item.status]}</td>
            <td>
                <a href="/class/?filter=class_id&class_id=${item.clazz}">班级</a>
                <a href="/person/?filter=class_id&class_id=${item.clazz}">筛选</a>
                <a href="/paper/${item.id}">论文</a>
                <a href="/course/${item.id}">课程</a>
                <a href="/person/edit/${item.id}">修改</a>
                <a href="/person/delete/${item.id}" onclick="return confirm('真要删？')">删</a>
            </td>
        </tr>
    </#list>
    </tbody>
</table>

<h1>添加人员</h1>
<form action="/person/add" method="post">
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
                <option value="unknown">${names.sex.unknown}</option>
                <option value="male">${names.sex.male}</option>
                <option value="female">${names.sex.female}</option>
            </select>
        </label>
    </p>

    <p>
        <label>
            类型
            <select name="type">
                <option value="teacher">${names.type.teacher}</option>
                <option value="student_undergraduate">${names.type.student_undergraduate}</option>
                <option value="student_postgraduate">${names.type.student_postgraduate}</option>
            </select>
        </label>
    </p>

    <p>
        <label>
            状态
            <select name="status">
                <option value="normal">${names.status.normal}</option>
                <option value="dropped">${names.status.dropped}</option>
                <option value="graduated">${names.status.graduated}</option>
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
            <input type="number" name="class_id">
        </label>
    </p>

    <p>
        <input type="submit">
    </p>
</form>

</body>
</html>