<%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/19/24
  Time: 11:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>message</title>
</head>
<body>
<script>
    alert('<%=request.getAttribute("msg")%>');

    if ('<%=request.getAttribute("path")%>') {
        location.href = "<%=request.getAttribute("path")%>";
    } else {
        location.href = "/";
    }
</script>
</body>
</html>