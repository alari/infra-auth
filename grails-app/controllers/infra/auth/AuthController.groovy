package infra.auth

import infra.auth.commands.SignInCommand
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

    def signIn(SignInCommand command) {
        String view = authUtilsService.config.signInView
        if (command.validate()) {
            authorizationService.signIn(command.username, command.password)
            if (!authUtilsService.isAuthenticated()) {
                flash.message = g.message(code: "infra.auth.signIn.status.failed")
            }
        }
        if(view) {
            render view: view
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

        redirect url: "/"
    }

    def unauthorized() {
        String view = authUtilsService.config.unauthorizedView
        if(view) {
            render view: view
        }
    }
}
