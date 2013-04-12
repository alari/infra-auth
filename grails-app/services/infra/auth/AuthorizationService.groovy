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

    /**
     *
     * @return
     */
    public Subject getSubject() {
        SecurityUtils.subject
    }

    /**
     *
     * @return
     */
    public String getPrincipal() {
        subject?.principal
    }

    /**
     *
     * @return
     */
    public boolean isAuthenticated() {
        subject?.isAuthenticated() ?: false
    }

    /**
     * @param user
     * @return
     */
    public Map<String, ?> signIn(User user) {
        if (isAuthenticated()) {
            return authStatus
        }
        subject.login(new AuthToken(username: user.username, passwordHash: user.passwordHash))
        authStatus
    }

    /**
     *
     * @param username
     * @param passwordHash
     * @return
     */
    public Map<String, ?> signIn(String username, String password) {
        signIn(username, password, false)
    }

    /**
     *
     * @param username
     * @param passwordHash
     * @param rememberMe
     * @return
     */
    public Map<String, ?> signIn(String username, String password, boolean rememberMe) {
        if (isAuthenticated()) {
            return authStatus
        }

        if (username && password) {
            UsernamePasswordToken authToken = new UsernamePasswordToken(username, password)
            authToken.rememberMe = rememberMe

            try {
                subject.login(authToken)

                println "User loged in successfully"
            } catch (AuthenticationException ex) {
                println "User loged in unsuccessfully"
                println "Exception: ${ex}"
            }
        }
        authStatus
    }

    /**
     *
     * @param command
     * @param userCreator
     * @return
     */
    public Map<String, ?> signUp(String username, String password) {
        if (SecurityUtils.subject?.isAuthenticated()) {
            return authStatus
        }

        User newUser = authRepo.createUser(username, password)
        if (newUser) {
            subject.login(new AuthToken(username: newUser.username, password: newUser.passwordHash))

            println "User created successfully"
        } else {
            println "User wasn`t created successfully"
        }
        authStatus
    }

    /**
     *
     * @return
     */
    public Map<String, ?> signOut() {
        def principal = getPrincipal()

        subject?.logout()
        ConfigUtils.removePrincipal(principal)

        authStatus
    }

    /**
     *
     * @return
     */
    public Map<String, ?> getAuthStatus() {
        [isAuthenticated: isAuthenticated(), username: principal]
    }

    private String message(Map params) {
        messageSource.getMessage(params.code, [] as Object[], null, LocaleContextHolder.locale)
    }
}
