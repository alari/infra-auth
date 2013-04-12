<%--
  User: prostohz
  Date: 4/11/13 6:00 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="infra.auth.signUp.title" /></title>
    <link type="text/css" rel="stylesheet" href="${createLinkTo(dir:'css',file:'auth.css')}" />
</head>
<body>

    <div class="auth-form">
        <div class="b-entrance__form-title m-entrance__form-title-position">
            <h2 class="b-entrance__form-title-h2"><g:message code="infra.auth.signUp.title" /></h2>
        </div>

        <shiro:isLoggedIn>
            <g:link controller="auth" action="signOut">Выйти</g:link>
        </shiro:isLoggedIn>
        <shiro:isNotLoggedIn>
            <g:form controller="auth" action="signUp">
                <input type="text" class="auth-form-field" name="username" placeholder="${message(code: 'infra.auth.signUp.form.fields.username.placeholder')}">
                <input type="text" class="auth-form-field" name="password" placeholder="${message(code: 'infra.auth.signUp.form.fields.password.placeholder')}">
                <input type="text" class="auth-form-field" name="confirmedPassword" placeholder="${message(code: 'infra.auth.signUp.form.fields.confirmedPassword.placeholder')}">

                <div>
                    <input type="submit" class="auth-form-submit" value="${message(code: 'infra.auth.signUp.form.submit.value')}">
                </div>
            </g:form>
            <div class="auth-form-error">
                <g:message code="${flash.message}" />
            </div>
        </shiro:isNotLoggedIn>
    </div>

</body>
</html>