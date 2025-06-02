<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title><c:out value="${pageTitle}" default="JSON 数据绑定演示"/></title>
    <style>
        body { font-family: sans-serif; }
        textarea { width: 100%; min-height: 100px; margin-bottom: 10px; }
        pre { background-color: #f0f0f0; padding: 10px; border: 1px solid #ccc; white-space: pre-wrap; word-wrap: break-word; }
        .container { max-width: 800px; margin: 0 auto; padding: 20px;}
    </style>
</head>
<body>
<div class="container">
    <h1><c:out value="${pageTitle}" default="JSON 数据绑定演示"/></h1>

    <p>本页演示向服务器发送 JSON 数据并接收 JSON 响应。</p>
    <p>控制器端点 <code>/jsonData.form</code> (POST) 需要一个表示 User 对象的 JSON 负载，并将返回一个修改后的 User 对象作为 JSON。</p>

    <h3>发送 JSON 用户数据 (POST 请求)</h3>
    <form id="jsonTestForm">
        <div>
            <label for="jsonDataInput">要发送的 JSON 数据 (User 对象):</label>
            <textarea id="jsonDataInput">{
    "id": 789,
    "name": "JSON 用户",
    "email": "json.user@example.com",
    "age": 40
}</textarea>
        </div>
        <button type="button" onclick="sendJsonData()">发送 JSON 数据</button>
    </form>

    <h3>服务器响应:</h3>
    <pre id="jsonResponseOutput">响应将在此处显示...</pre>

    <script>
        async function sendJsonData() {
            const jsonData = document.getElementById('jsonDataInput').value;
            const responseOutput = document.getElementById('jsonResponseOutput');
            responseOutput.textContent = '发送中...';

            try {
                const url = '${pageContext.request.contextPath}/jsonData.form'; 

                const response = await fetch(url, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                    },
                    body: jsonData
                });

                if (!response.ok) {
                    throw new Error(`HTTP 错误! 状态: ${response.status} ${response.statusText}`);
                }

                const responseData = await response.json();
                responseOutput.textContent = JSON.stringify(responseData, null, 2);

            } catch (error) {
                console.error('发送JSON数据时出错:', error);
                responseOutput.textContent = '错误: ' + error.message + '\n\n更多详情请查看控制台。';
            }
        }
    </script>

    <hr/>
    <p><a href="${pageContext.request.contextPath}/">返回首页</a></p>
</div>
</body>
</html> 