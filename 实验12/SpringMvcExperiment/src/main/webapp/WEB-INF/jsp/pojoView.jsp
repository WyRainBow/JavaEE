<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${pageTitle}" default="POJO 数据绑定演示"/></title>
</head>
<body>
    <h1><c:out value="${pageTitle}" default="POJO 数据绑定演示"/></h1>

    <p>Spring MVC 自动将请求参数绑定到 User POJO 对象:</p>
    
    <c:if test="${not empty boundUser}">
        <h3>用户详情:</h3>
        <ul>
            <li>ID: <strong><c:out value="${boundUser.id}" default="未提供"/></strong></li>
            <li>名称: <strong><c:out value="${boundUser.name}" default="未提供"/></strong></li>
            <li>邮箱: <strong><c:out value="${boundUser.email}" default="未提供"/></strong></li>
            <li>年龄: <strong><c:out value="${boundUser.age}" default="未提供 (或基本类型为0)"/></strong></li>
        </ul>
        <p>用户对象 (toString()): <code><c:out value="${boundUser}"/></code></p>
    </c:if>
    <c:if test="${empty boundUser}">
        <p>没有用户对象被绑定或传递到视图。</p>
    </c:if>

    <p><em>尝试以下URL (点击或复制到地址栏):</em></p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/pojoData.form?id=1&name=爱丽丝&email=alice@example.com&age=30">${pageContext.request.contextPath}/pojoData.form?id=1&name=爱丽丝&email=alice@example.com&age=30</a></li>
        <li><a href="${pageContext.request.contextPath}/pojoData.form?name=鲍勃&age=25">${pageContext.request.contextPath}/pojoData.form?name=鲍勃&age=25</a> (ID 和邮箱将为 null/默认值)</li>
        <li><a href="${pageContext.request.contextPath}/pojoData.form">${pageContext.request.contextPath}/pojoData.form</a> (所有字段将为 null/默认值)</li>
    </ul>

    <hr/>
    <p><a href="${pageContext.request.contextPath}/">返回首页</a></p>
</body>
</html> 