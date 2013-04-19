package infra.auth.commands

import grails.validation.Validateable

/**
 * @author prostohz
 * @since 4/8/13 4:30 PM
 */
@Validateable
class SignUpCommand {

    String username

    String password
    String confirmedPassword

    static constraints = {
        username nullable: false, blank: false, size: 3..127
        password nullable: false, blank: false, size: 3..127

        confirmedPassword nullable: false, blank: false, size: 3..127, validator: { String value, SignUpCommand command ->
            value == command.password
        }
    }
}
