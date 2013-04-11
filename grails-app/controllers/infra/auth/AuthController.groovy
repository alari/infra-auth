package infra.auth

import infra.auth.commands.SignInCommand
import infra.auth.commands.SignUpCommand

class AuthController {

    def authorizationService

    def signIn(SignInCommand command) {
        if (command.validate()) {
            authorizationService.signIn(command.username, command.password)
        } else {
            println "command didn`t validate"
        }
    }

    def signUp(SignUpCommand command) {
        if (command.validate()) {
            authorizationService.signUp(command.username, command.password)
        } else {
            println "command didn`t validate"
        }
    }
}
