<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${pageTitle}" default="简单数据绑定演示"/></title>
</head>
<body>
    <h1><c:out value="${pageTitle}" default="简单数据绑定演示"/></h1>

    <p>接收到的用户ID (int): <strong><c:out value="${userId}"/></strong></p>
    <p>接收到的用户名 (String): <strong><c:out value="${userName}"/></strong></p>

    <p><em>尝试以下URL (点击或复制到地址栏):</em></p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/simpleData.form?id=101&userName=张三">${pageContext.request.contextPath}/simpleData.form?id=101&userName=张三</a></li>
        <li><a href="${pageContext.request.contextPath}/simpleData.form?userName=李四">${pageContext.request.contextPath}/simpleData.form?userName=李四</a> (ID 将默认为 0)</li>
        <li><a href="${pageContext.request.contextPath}/simpleData.form?id=202">${pageContext.request.contextPath}/simpleData.form?id=202</a> (名称将默认为 Anonymous)</li>
        <li><a href="${pageContext.request.contextPath}/simpleData.form">${pageContext.request.contextPath}/simpleData.form</a> (ID和名称都将使用默认值)</li>
    </ul>

    <hr/>
    <p><a href="${pageContext.request.contextPath}/">返回首页</a></p>
</body>
</html> 