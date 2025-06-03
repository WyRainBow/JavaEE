<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${pageTitle}" default="数组/集合绑定演示"/></title>
</head>
<body>
    <h1><c:out value="${pageTitle}" default="数组/集合绑定演示"/></h1>

    <p>Spring MVC 自动将多个同名请求参数绑定到一个列表 (List) 和一个数组 (Array):</p>

    <h3>绑定的字符串列表 (names):</h3>
    <c:if test="${not empty nameList}">
        <ul>
            <c:forEach var="name" items="${nameList}">
                <li><c:out value="${name}"/></li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty nameList}">
        <p>没有提供名称或列表为空。</p>
    </c:if>

    <h3>绑定的整数数组 (ids):</h3>
    <c:if test="${not empty idArray}">
        <ul>
            <c:forEach var="idVal" items="${idArray}">
                <li><c:out value="${idVal}"/></li>
            </c:forEach>
        </ul>
    </c:if>
    <c:if test="${empty idArray}">
        <p>没有提供ID或数组为空。</p>
    </c:if>

    <p><em>尝试以下URL (点击或复制到地址栏):</em></p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/collectionData.form?names=张三&names=李四&names=王五&ids=1&ids=2&ids=3">${pageContext.request.contextPath}/collectionData.form?names=张三&names=李四&names=王五&ids=1&ids=2&ids=3</a></li>
        <li><a href="${pageContext.request.contextPath}/collectionData.form?names=赵六&names=孙七">${pageContext.request.contextPath}/collectionData.form?names=赵六&names=孙七</a> (ids 将为空/null)</li>
        <li><a href="${pageContext.request.contextPath}/collectionData.form?ids=101&ids=102&ids=103">${pageContext.request.contextPath}/collectionData.form?ids=101&ids=102&ids=103</a> (names 将为空/null)</li>
        <li><a href="${pageContext.request.contextPath}/collectionData.form">${pageContext.request.contextPath}/collectionData.form</a> (两者都将为空/null)</li>
    </ul>

    <hr/>
    <p><a href="${pageContext.request.contextPath}/">返回首页</a></p>
</body>
</html> 