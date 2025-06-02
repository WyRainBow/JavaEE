<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${pageTitle}" default="自定义类型绑定演示"/></title>
</head>
<body>
    <h1><c:out value="${pageTitle}" default="自定义类型绑定演示"/></h1>

    <p>Spring MVC 使用了自定义转换器 (StringToPhoneNumberConverter) 将请求参数绑定到 PhoneNumber 对象:</p>

    <c:if test="${not empty boundPhoneNumber}">
        <h3>绑定的电话号码:</h3>
        <p>号码: <strong><c:out value="${boundPhoneNumber.numberString}"/></strong></p>
        <p>对象 (toString()): <code><c:out value="${boundPhoneNumber}"/></code></p>
    </c:if>
    <c:if test="${empty boundPhoneNumber}">
        <p>没有电话号码对象被绑定 (可能参数缺失或为空)。</p>
    </c:if>

    <p><em>尝试以下URL:</em></p>
    <ul>
        <li><code>customType.form?phone=123-456-7890</code></li>
        <li><code>customType.form?phone=5551234</code></li>
        <li><code>customType.form</code> (电话号码将为 null)</li>
    </ul>

    <hr/>
    <p><a href="${pageContext.request.contextPath}/">返回首页</a></p>
</body>
</html> 