<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Spring MVC 实验</title>
</head>
<body>
    <h1>来自 Spring MVC 的问候！</h1>
    <p>传递给控制器的名称是：<strong><c:out value="${displayName}"/></strong></p>
    <p><em>(此值从Model中获取)</em></p>
    <hr/>
    <p>当前服务器时间：<%= new java.util.Date() %></p>
    <hr/>
    <h3>测试链接：</h3>
    <ul>
        <li><a href="showName.form?name=Freddy">测试名称 'Freddy'</a></li>
        <li><a href="showName.form?name=Alice">测试名称 'Alice'</a></li>
        <li><a href="showName.form">测试默认名称 ('Guest')</a></li>
        <li><a href="servletObjects.form?requestParam=HelloRequest">测试 Servlet 对象 (Request, Session, Model)</a></li>
        <li><a href="simpleData.form?id=101&userName=JohnDoe">测试简单数据绑定 (int, String)</a></li>
        <li><a href="pojoData.form?id=1&name=Alice&email=alice@example.com&age=30">测试 POJO 数据绑定</a></li>
        <li><a href="wrappedPojo.form?orderId=ORD123&itemName=Laptop&user.id=10&user.name=Charlie&user.email=charlie@example.com&user.age=28">测试包装 POJO 数据绑定</a></li>
        <li><a href="customType.form?phone=123-456-7890">测试自定义类型绑定 (PhoneNumber)</a></li>
        <li><a href="collectionData.form?names=Alice&names=Bob&ids=1&ids=2">测试数组/集合绑定</a></li>
        <li><a href="jsonData.form">测试 JSON 数据绑定 (查看页面)</a></li>
    </ul>
</body>
</html> 