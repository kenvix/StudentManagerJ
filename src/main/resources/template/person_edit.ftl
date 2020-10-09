<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <title>学生管理系统</title>
    <link rel="stylesheet" href="/style.css" />
</head>
<body>
<h1>编辑人员 #${person.id}</h1>
<p>
    <a href="/">首页</a>
    <a href="/person">人员列表</a>
</p>

<h2>人员基本信息</h2>
<form action="/person/edit/${person.id}" method="post">
    <p>
        <label>
            工号
            <input type="number" name="id" value="${person.id}">
        </label>
    </p>

    <p>
        <label>
            姓名
            <input type="text" name="name" value="${person.name}">
        </label>
    </p>

    <p>
        <label>
            性别
            <select name="sex">
                <option value="unknown" <#if person.sex == "unknown">selected</#if>>${names.sex.unknown}</option>
                <option value="male" <#if person.sex == "male">selected</#if>>${names.sex.male}</option>
                <option value="female" <#if person.sex == "female">selected</#if>>${names.sex.female}</option>
            </select>
        </label>
    </p>

    <p>
        <label>
            类型
            <select name="type">
                <option value="teacher" <#if person.type == "teacher">selected</#if>>${names.type.teacher}</option>
                <option value="student_undergraduate" <#if person.type == "student_undergraduate">selected</#if>>${names.type.student_undergraduate}</option>
                <option value="student_postgraduate" <#if person.type == "student_postgraduate">selected</#if>>>${names.type.student_postgraduate}</option>
            </select>
        </label>
    </p>

    <p>
        <label>
            状态
            <select name="status">
                <option value="normal" <#if person.status == "normal">selected</#if>>${names.status.normal}</option>
                <option value="dropped" <#if person.status == "dropped">selected</#if>>>${names.status.dropped}</option>
                <option value="graduated" <#if person.status == "graduated">selected</#if>>>${names.status.graduated}</option>
            </select>
        </label>
    </p>

    <p>
        <label>
            可毕业
            <input type="checkbox" name="is_graduable" <#if person.isGraduable == 1>selected</#if>>
        </label>
    </p>

    <p>
        <label>
            班级
            <input type="number" name="class_id" value="${person.clazz}">
        </label>
    </p>

    <p>
        <input type="submit">
    </p>
</form>

</body>
</html>