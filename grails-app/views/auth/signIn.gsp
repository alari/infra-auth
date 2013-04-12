<%--
  User: prostohz
  Date: 4/11/13 6:02 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title><g:message code="infra.auth.signIn.title" /></title>
  <link type="text/css" href="${createLinkTo(dir:'css',file:'auth.css')}" />
</head>
<body>

    <div class="auth-form">
        <shiro:isLoggedIn>
            <g:link controller="auth" action="signOut">Выйти</g:link>
        </shiro:isLoggedIn>
        <shiro:isNotLoggedIn>
            <g:form controller="auth" action="signIn">
                <input type="text" name="username" placeholder="${message(code: 'infra.auth.signIn.username.field.placeholder')}">
                <input type="text" name="password" placeholder="${message(code: 'infra.auth.signIn.password.field.placeholder')}">

                <input type="submit" value="${message(code: 'infra.auth.signIn.submit.field.placeholder')}">
            </g:form>
            <g:link controller="auth" action="signUp">Зарегистрироваться</g:link>
        </shiro:isNotLoggedIn>
    </div>

</body>
</html>