package infra.auth

import infra.auth.domains.Role
import infra.auth.domains.User
import infra.auth.realm.Realm
import infra.auth.tokens.AuthToken

import org.apache.shiro.authc.AccountException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.UsernamePasswordToken
import org.springframework.beans.factory.annotation.Autowired

class ShiroDbRealm implements Realm {
    static authTokenClass = org.apache.shiro.authc.UsernamePasswordToken

    @Autowired
    AuthRepo authRepo

    def roleUtilsService
    def userPermissionsService

    def shiroPermissionResolver
    def credentialMatcher

    @Override
    SimpleAccount authenticate(UsernamePasswordToken authToken) {

        if (authToken instanceof AuthToken) {
            return new SimpleAccount(authToken.username, authToken.passwordHash, "ShiroDbRealm")
        }

        String username = authToken.username
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.")
        }

        User user = authRepo.getUserByUsername(username)
        if (!user) {
            throw new UnknownAccountException("No account found for user [${username}]")
        }

        SimpleAccount account = new SimpleAccount(user.username, user.passwordHash, "ShiroDbRealm")

        if (!credentialMatcher.doCredentialsMatch(authToken, account)) {
            throw new IncorrectCredentialsException("Invalid password for user '${username}'")
        }

        return account
    }

    @Override
    boolean hasRole(String principal, Role roleName) {
        return null != roleUtilsService.hasRole(getUserByPrincipal(principal), roleName)
    }

    @Override
    boolean hasAllRoles(String principal, List<Role> roles) {
        User user = getUserByPrincipal(principal)

        if (user == null)
            return false
        else {
            return roles.size() == roles.intersect(authRepo.getRoles(user)).size()
        }
    }

    @Override
    boolean isPermitted(String principal, String requiredPermission) {
        Collection<String> permissions = null

        ShiroUser.withNewSession {
            permissions = userPermissionsService.getUserPermissions(getUserByPrincipal(principal))
        }

        def retval = permissions?.find { permString ->

            def perm = shiroPermissionResolver.resolvePermission(permString)

            if (perm.implies(requiredPermission)) {
                return true
            } else {
                return false
            }
        }
        return retval != null
    }

    User getUserByPrincipal(String principal) {
        authRepo.getUserByUsername(principal)
    }
}
