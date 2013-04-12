package infra.auth

import infra.auth.commands.SignInCommand
import infra.auth.commands.SignUpCommand

class AuthController {

    def authorizationService

    def index() {
        redirect action: "signIn"
    }

    def signIn(SignInCommand command) {
        if (command?.validate()) {
            authorizationService.signIn(command.username, command.password)
        }
    }

    def signUp(SignUpCommand command) {
        if (command?.validate()) {
            authorizationService.signUp(command.username, command.password)
        }
    }

    def signOut() {
        authorizationService.signOut()
        redirect action: "signIn"
    }
}
