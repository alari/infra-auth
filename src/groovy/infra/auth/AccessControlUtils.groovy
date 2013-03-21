package infra.auth

import groovy.transform.CompileStatic
import org.apache.shiro.SecurityUtils
import org.apache.shiro.authz.AuthorizationException
import org.apache.shiro.authz.UnauthenticatedException

/**
 * @author alari
 * @since 3/20/13 12:21 PM
 */
@CompileStatic
class AccessControlUtils {
    static void canAdminOrFail() {
        failIfNotPermitted PermissionUtils.getAdminPermission()
    }

    static void canAdminOrFail(Class resourceClass) {
        failIfNotPermitted PermissionUtils.getAdminPermission(resourceClass)
    }

    static void canReadOrFail(Class resourceClass, def id) {
        failIfNotPermitted PermissionUtils.getReadPermission(resourceClass, id)
    }

    static void canUpdateOrFail(Class resourceClass, def id) {
        failIfNotPermitted PermissionUtils.getUpdatePermission(resourceClass, id)
    }

    static void canCreateOrFail(Class resourceClass) {
        failIfNotPermitted PermissionUtils.getCreatePermission(resourceClass)
    }

    static void canDeleteOrFail(Class resourceClass, def id) {
        failIfNotPermitted PermissionUtils.getDeletePermission(resourceClass, id)
    }

    static void canOrFail(Class resourceClass, def id, String permission) {
        failIfNotPermitted PermissionUtils.getPermission(resourceClass, id, permission)
    }

    static void canOrFail(String prefix, String permission) {
        failIfNotPermitted PermissionUtils.getPermission(prefix, permission)
    }

    static boolean canAdmin() {
        isPermitted PermissionUtils.getAdminPermission()
    }

    static boolean canRead(Class resourceClass, def id) {
        isPermitted PermissionUtils.getReadPermission(resourceClass, id)
    }

    static boolean canUpdate(Class resourceClass, def id) {
        isPermitted PermissionUtils.getUpdatePermission(resourceClass, id)
    }

    static boolean canCreate(Class resourceClass) {
        isPermitted PermissionUtils.getCreatePermission(resourceClass)
    }

    static boolean canDelete(Class resourceClass, def id) {
        isPermitted PermissionUtils.getDeletePermission(resourceClass, id)
    }

    static boolean can(Class resourceClass, def id, String permission) {
        isPermitted PermissionUtils.getPermission(resourceClass, id, permission)
    }

    static boolean can(String prefix, String permission) {
        isPermitted PermissionUtils.getPermission(prefix, permission)
    }

    static boolean isPermitted(String permission) {
        SecurityUtils.subject?.isPermitted(permission)
    }

    static void failIfNotPermitted(String permission) {
        if (!isPermitted(permission)) {
            if (SecurityUtils.subject?.authenticated)
                throwAuthorizationException(permission)
            else
                throwUnauthenticatedException()
        }
    }

    static void throwAuthorizationException(String permission) throws AuthorizationException {
        throw new AuthorizationException("Not permitted: ${permission}")
    }

    static void throwUnauthenticatedException() throws UnauthenticatedException {
        throw new UnauthenticatedException("Error! Attempt to request forbidden resource by unauthenticated user!")
    }
}
