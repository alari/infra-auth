package infra.auth

import infra.auth.domains.User
import infra.auth.tokens.AuthToken

import org.apache.shiro.SecurityUtils
import org.apache.shiro.authc.AuthenticationException
import org.apache.shiro.authc.UsernamePasswordToken
import org.apache.shiro.subject.Subject
import org.apache.shiro.grails.ConfigUtils

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.i18n.LocaleContextHolder

class AuthorizationService {

    @Autowired
    AuthRepo authRepo

    def messageSource
    def authUtilsService


    public Map<String, ?> signIn(User user) {
        if (authUtilsService.isAuthenticated()) {
            return authUtilsService.authStatus
        }
        authUtilsService.subject.login(new AuthToken(username: authRepo.getUsername(user), passwordHash: authRepo.getPasswordHash(user)))
        authUtilsService.authStatus
    }

    public Map<String, ?> signIn(String username, String password) {
        signIn(username, password, false)
    }

    public Map<String, ?> signIn(String username, String password, boolean rememberMe) {
        if (authUtilsService.isAuthenticated()) {
            return authUtilsService.authStatus
        }

        if (username && password) {
            UsernamePasswordToken authToken = new UsernamePasswordToken(username, password)
            authToken.rememberMe = rememberMe

            try {
                authUtilsService.subject.login(authToken)
            } catch (AuthenticationException ex) {
                println "Exception: ${ex}"
            }
        }
        authUtilsService.authStatus
    }

    public Map<String, ?> signUp(String username, String password) {
        if (SecurityUtils.subject?.isAuthenticated()) {
            return authUtilsService.authStatus
        }

        User newUser = authRepo.createUser(username, password)
        if (newUser) {
            authUtilsService.subject.login(new AuthToken(username: authRepo.getUsername(newUser), password: password))
        } else {
            println "User wasn`t created successfully"
        }
        authUtilsService.authStatus
    }

    public Map<String, ?> signOut() {
        def principal = authUtilsService.getPrincipal()

        authUtilsService.subject?.logout()
        ConfigUtils.removePrincipal(principal)

        authUtilsService.authStatus
    }

    private String message(Map params) {
        messageSource.getMessage(params.code, [] as Object[], null, LocaleContextHolder.locale)
    }
}
