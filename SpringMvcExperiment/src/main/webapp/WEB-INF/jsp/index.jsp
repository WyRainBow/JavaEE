<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Spring MVC Experiment</title>
</head>
<body>
    <h1>Hello from Spring MVC!</h1>
    <p>The name passed to the controller is: <strong><c:out value="${displayName}"/></strong></p>
    <p><em>(This value is retrieved from the Model)</em></p>
    <hr/>
    <p>Current Server Time: <%= new java.util.Date() %></p>
    <hr/>
    <h3>Test Links:</h3>
    <ul>
        <li><a href="showName.form?name=Freddy">Test with name 'Freddy'</a></li>
        <li><a href="showName.form?name=Alice">Test with name 'Alice'</a></li>
        <li><a href="showName.form">Test with default name ('Guest')</a></li>
    </ul>
</body>
</html> 