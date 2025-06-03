<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${pageTitle}" default="Servlet 对象演示"/></title>
</head>
<body>
    <h1><c:out value="${pageTitle}" default="Servlet 对象演示"/></h1>

    <h2>HttpServletRequest 演示</h2>
    <p>请求参数 'requestParam' (通过 @RequestParam 获取): <strong><c:out value="${requestParamInjected}"/></strong></p>
    <p>请求参数 'requestParam' (通过 request.getParameter 获取): <strong><c:out value="${paramFromRequestObject}"/></strong></p>
    <p><em>例如，尝试访问: <a href="${pageContext.request.contextPath}/servletObjects.form?requestParam=中文测试值">${pageContext.request.contextPath}/servletObjects.form?requestParam=中文测试值</a></em></p>

    <h2>HttpSession 演示</h2>
    <p>在 HttpSession 中设置的值 (sessionTestAttribute): <strong><c:out value="${sessionAttributeValue}"/></strong></p>
    <p><em>此值在同一会话中的不同请求间保持不变。</em></p>

    <h2>Model/ModelMap 演示</h2>
    <p>在 Model 中设置的值 (modelAttributeValue): <strong><c:out value="${modelAttributeValue}"/></strong></p>

    <hr/>
    <p><a href="${pageContext.request.contextPath}/">返回首页</a></p>
</body>
</html> 