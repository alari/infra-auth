package infra.auth

import grails.plugin.spock.IntegrationSpec
import infra.auth.commands.SignUpCommand
import spock.lang.Ignore
import spock.lang.Stepwise

/**
 * @author prostohz
 * @since 4/8/13 5:29 PM
 */
@Ignore
@Stepwise
class AuthorizationServiceSpec extends IntegrationSpec {

    def authorizationService

    def setupSpec() { }

    def cleanupSpec() { }



    void "Can sign in user"() {

    }

    void "Can sign up user"() {

        given:
        String username = "username"
        String password = "password"

        SignUpCommand signUpToken = new SignUpCommand(
                username: username,
                password: password
        )

        Closure userCreator = {

            ShiroUser user = null

            user


        }

        when:
        authorizationService.signUp(signUpToken, userCreator)

        then:
        noExceptionThrown()
    }
}
