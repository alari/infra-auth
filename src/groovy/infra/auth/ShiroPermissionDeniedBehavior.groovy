package infra.auth

import org.apache.shiro.SecurityUtils
import org.apache.shiro.ShiroException
import org.apache.shiro.authz.AuthorizationException
import org.apache.shiro.authz.UnauthenticatedException

/**
 * @author prostohz
 * @since 4/11/13 11:21 AM
 */
class ShiroPermissionDeniedBehavior implements PermissionDeniedBehavior {

    @Override
    void throwFail(String permission) {
        try {
            if (SecurityUtils.subject?.authenticated)
                throwAuthorizationException(permission)
            else
                throwUnauthenticatedException()
        } catch (ShiroException ex) {
            println ex
        }
    }


    private void throwAuthorizationException(String permission) throws AuthorizationException {
        throw new AuthorizationException("Not permitted: ${permission}")
    }

    private void throwUnauthenticatedException() throws UnauthenticatedException {
        throw new UnauthenticatedException("Error! Attempt to request forbidden resource by unauthenticated user!")
    }
}
