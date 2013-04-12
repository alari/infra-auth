package infra.auth

import infra.auth.commands.SignInCommand
import infra.auth.commands.SignUpCommand

class AuthController {

    def authorizationService

    def index() {
        redirect action: "signIn"
    }

    def signIn(SignInCommand command) {
        if (command.validate()) {
            Map result = authorizationService.signIn(command.username, command.password)
            if (!result.isAuthenticated)
                flash.message = g.message(code: "infra.auth.signIn.status.failed")
        }
    }

    def signUp(SignUpCommand command) {
        if (command.validate()) {
            Map result = authorizationService.signUp(command.username, command.password)
            if (!result.isAuthenticated)
                flash.message = g.message(code: "infra.auth.signUp.status.failed")
        }
    }

    def signOut() {
        authorizationService.signOut()
        redirect action: "signIn"
    }
}
