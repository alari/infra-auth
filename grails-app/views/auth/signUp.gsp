<%--
  User: prostohz
  Date: 4/11/13 6:00 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="infra.auth.signUp.title" /></title>
    <link type="text/css" href="${createLinkTo(dir:'css',file:'auth.css')}" />
</head>
<body>

    <div class="auth-form">
        <shiro:isLoggedIn>
            <g:link controller="auth" action="signOut">Выйти</g:link>
        </shiro:isLoggedIn>
        <shiro:isNotLoggedIn>
            <g:form controller="auth" action="signUp">
                <input type="text" name="username" placeholder="${message(code: 'infra.auth.signUp.username.field.placeholder')}">
                <input type="text" name="password" placeholder="${message(code: 'infra.auth.signUp.password.field.placeholder')}">
                <input type="text" name="confirmedPassword" placeholder="${message(code: 'infra.auth.signUp.confirmedPassword.field.placeholder')}">

                <input type="submit" value="${message(code: 'infra.auth.signUp.submit.field.placeholder')}">
            </g:form>
        </shiro:isNotLoggedIn>
    </div>

</body>
</html>