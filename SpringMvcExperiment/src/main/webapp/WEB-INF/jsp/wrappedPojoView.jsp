<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${pageTitle}" default="包装 POJO 数据绑定演示"/></title>
</head>
<body>
    <h1><c:out value="${pageTitle}" default="包装 POJO 数据绑定演示"/></h1>

    <p>Spring MVC 自动将请求参数绑定到 Order POJO及其内嵌的 User 对象:</p>

    <c:if test="${not empty boundOrder}">
        <h3>订单详情:</h3>
        <ul>
            <li>订单 ID: <strong><c:out value="${boundOrder.orderId}" default="未提供"/></strong></li>
            <li>商品名称: <strong><c:out value="${boundOrder.itemName}" default="未提供"/></strong></li>
        </ul>

        <h4>关联用户信息 (内嵌 POJO):</h4>
        <c:if test="${not empty boundOrder.user}">
            <ul>
                <li>用户 ID: <strong><c:out value="${boundOrder.user.id}" default="未提供"/></strong></li>
                <li>用户名: <strong><c:out value="${boundOrder.user.name}" default="未提供"/></strong></li>
                <li>用户邮箱: <strong><c:out value="${boundOrder.user.email}" default="未提供"/></strong></li>
                <li>用户年龄: <strong><c:out value="${boundOrder.user.age}" default="未提供 (或0)"/></strong></li>
            </ul>
            <p>内嵌用户对象 (toString()): <code><c:out value="${boundOrder.user}"/></code></p>
        </c:if>
        <c:if test="${empty boundOrder.user}">
            <p>没有提供或绑定内嵌的用户对象。</p>
        </c:if>
        <p>订单对象 (toString()): <code><c:out value="${boundOrder}"/></code></p>
    </c:if>
    <c:if test="${empty boundOrder}">
        <p>没有订单对象被绑定或传递到视图。</p>
    </c:if>

    <p><em>尝试以下URL (点击或复制到地址栏):</em></p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/wrappedPojo.form?orderId=ORD123&itemName=笔记本电脑&user.id=10&user.name=查理&user.email=charlie@example.com&user.age=28">${pageContext.request.contextPath}/wrappedPojo.form?orderId=ORD123&itemName=笔记本电脑&user.id=10&user.name=查理&user.email=charlie@example.com&user.age=28</a></li>
        <li><a href="${pageContext.request.contextPath}/wrappedPojo.form?orderId=ORD456&itemName=键盘">${pageContext.request.contextPath}/wrappedPojo.form?orderId=ORD456&itemName=键盘</a> (用户信息将未绑定/默认)</li>
        <li><a href="${pageContext.request.contextPath}/wrappedPojo.form?user.name=丹娜&user.age=35">${pageContext.request.contextPath}/wrappedPojo.form?user.name=丹娜&user.age=35</a> (订单信息将未绑定/默认, 用户ID和邮箱将为默认值)</li>
    </ul>

    <hr/>
    <p><a href="${pageContext.request.contextPath}/">返回首页</a></p>
</body>
</html> 