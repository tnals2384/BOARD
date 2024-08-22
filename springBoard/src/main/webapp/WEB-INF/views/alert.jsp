<%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/22/24
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
 <script>
     const msg = "<%=request.getAttribute("msg")%>";
     const path = "<%=request.getAttribute("path")%>";

     alert(msg);
     location.href = path;
 </script>
</html>
