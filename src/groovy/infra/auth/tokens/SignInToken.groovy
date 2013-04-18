package infra.auth.tokens

import groovy.transform.CompileStatic
import org.apache.shiro.authc.UsernamePasswordToken

/**
 * @author prostohz
 * @since 4/8/13 4:15 PM
 */
@CompileStatic
class AuthToken extends UsernamePasswordToken {

    String username
    String passwordHash
}
