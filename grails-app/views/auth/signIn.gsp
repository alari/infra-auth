<%--
  User: prostohz
  Date: 4/11/13 6:02 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title><g:message code="infra.auth.signIn.title" /></title>
    <link type="text/css" rel="stylesheet" href="${createLinkTo(dir:'css',file:'auth.css')}" />
</head>
<body>

    <div class="auth-form">
        <div class="b-entrance__form-title m-entrance__form-title-position">
            <h2 class="b-entrance__form-title-h2"><g:message code="infra.auth.signIn.title" /></h2>
        </div>

        <shiro:isLoggedIn>
            <g:link controller="auth" action="signOut">Выйти</g:link>
        </shiro:isLoggedIn>
        <shiro:isNotLoggedIn>
            <g:form controller="auth" action="signIn">
                <input type="text" class="auth-form-field" name="username" placeholder="${message(code: 'infra.auth.signIn.form.fields.username.placeholder')}">
                <input type="text" class="auth-form-field" name="password" placeholder="${message(code: 'infra.auth.signIn.form.fields.password.placeholder')}">

                <div class="auth-form-error">
                    <g:message code="${flash.message}" />
                </div>

                <div>
                    <input type="submit" class="auth-form-submit" value="${message(code: 'infra.auth.signIn.form.submit.value')}">
                <div>
            </g:form>

            <div class="auth-form-registration-link">
                <g:link controller="auth" action="signUp">
                    <g:message code="infra.auth.signUp.form.submit.value" />
                </g:link>
            </div>
        </shiro:isNotLoggedIn>
    </div>

</body>
</html>