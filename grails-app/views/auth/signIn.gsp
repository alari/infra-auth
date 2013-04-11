<%--
  User: prostohz
  Date: 4/11/13 6:02 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title><g:message code="infra.auth.signIn.title" /></title>
</head>
<body>

    <g:form controller="auth" action="signIn">
        <input type="text" name="username" placeholder="${message(code: 'infra.auth.signIn.username.field.placeholder')}">
        <input type="text" name="password" placeholder="${message(code: 'infra.auth.signIn.password.field.placeholder')}">

        <input type="submit" value="${message(code: 'infra.auth.signIn.submit.field.placeholder')}">
    </g:form>

</body>
</html>