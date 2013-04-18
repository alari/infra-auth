package infra.auth

import grails.converters.JSON
import infra.auth.commands.SignUpCommand

class AuthController {

    def authorizationService

    def authUtilsService

    def index() {
        redirect action: "login"
    }

    def login() {
        if (authUtilsService.isAuthenticated())
            redirect uri: "/"
        else
            redirect action: "signIn"
    }

    def signIn(String username, String password, boolean rememberMe) {

        if(request.JSON) {
            username = request.JSON.username
            password = request.JSON.password
            rememberMe = request.JSON.rememberMe
        }

        if (!authUtilsService.isAuthenticated() && username && password) {
            authorizationService.signIn(username, password, rememberMe)
            if (!authUtilsService.isAuthenticated()) {
                flash.message = g.message(code: "infra.auth.signIn.status.failed")
            }
        }

        withFormat {
            html {
                String view = authUtilsService.config.signInView
                if(view) {
                    render view: view, model: [username: username, rememberMe: rememberMe]
                }
                [username: username, rememberMe: rememberMe]
            }
            json {
                render authUtilsService.authStatus as JSON
            }
        }
    }

    def signUp(SignUpCommand command) {
        String view = authUtilsService.config.signUpView
        if (command.validate()) {
            authorizationService.signUp(command.username, command.password)
            if (!authUtilsService.isAuthenticated()) {
                flash.message = g.message(code: "infra.auth.signUp.status.failed")
            }
        }
        if(view) {
            render view: view
        }
    }

    def signOut() {
        authorizationService.signOut()

        withFormat {
            html {
                redirect url: "/"
            }
            json {
                render authUtilsService.authStatus as JSON
            }
        }

    }

    def checkAuth() {
        render authUtilsService.authStatus as JSON
    }

    def unauthorized() {
        String view = authUtilsService.config.unauthorizedView
        if(view) {
            render view: view
        }
    }
}
