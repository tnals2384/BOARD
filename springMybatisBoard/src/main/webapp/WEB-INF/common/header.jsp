<%--
  Created by IntelliJ IDEA.
  User: sumin
  Date: 8/22/24
  Time: 9:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String memberId = (String) session.getAttribute("memberId");
    if (memberId != null) {
%>
<%=memberId%>님 환영합니다. <br>
<a href="<%=request.getContextPath()%>/member/logout">[로그아웃]</a>
<%
    } else {
%>
<a href="<%=request.getContextPath()%>/member/login">[로그인]</a>
<a href="<%=request.getContextPath()%>/member/join">[회원가입]</a>
<%}%>
<hr>