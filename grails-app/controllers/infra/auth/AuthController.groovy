package infra.auth

import infra.auth.commands.SignInCommand
import infra.auth.commands.SignUpCommand

class AuthController {

    def authorizationService
    def viewsResolvingService

    def index() {
        redirect action: "login"
    }

    def login() {
        if (authorizationService.isAuthenticated())
            redirect uri: "/"
        else
            redirect action: "signIn"
    }

    def signIn(SignInCommand command) {
        String view = viewsResolvingService.getSignInView()
        if (command.validate()) {
            authorizationService.signIn(command.username, command.password)
            if (!authorizationService.isAuthenticated()) {
                flash.message = g.message(code: "infra.auth.signIn.status.failed")
            }
        }
        if(view) {
            render view: view
        }
    }

    def signUp(SignUpCommand command) {
        String view = viewsResolvingService.getSignUpView()
        if (command.validate()) {
            authorizationService.signUp(command.username, command.password)
            if (!authorizationService.isAuthenticated()) {
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
        String view = viewsResolvingService.getUnauthorizedView()
        if(view) {
            render view: view
        }
    }
}
