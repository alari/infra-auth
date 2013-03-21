package infra.auth

import groovy.transform.CompileStatic

/**
 * @author alari
 * @since 3/20/13 12:07 PM
 */
@CompileStatic
class PermissionUtils {
    static final String CREATE = "create"
    static final String UPDATE = "update"
    static final String READ = "read"
    static final String DELETE = "delete"

    static final String WILDCARD = "*"
    static final String ADMIN = "admin"

    static String getPermission(Class resourceClass, def id, String action) {
        getPermission(getResourceCode(resourceClass), "${id}:${action}")
    }

    static String getPermission(String prefix, String action) {
        prefix ? "${prefix}:${action}" : action
    }

    static String getResourceCode(Class resourceClass) {
        resourceClass.simpleName
    }

    static String getAdminPermission() {
        ADMIN
    }

    static String getAdminPermission(Class resourceClass) {
        "${getResourceCode(resourceClass)}:${ADMIN}"
    }

    static String getCreatePermission(Class resourceClass) {
        "${getResourceCode(resourceClass)}:${CREATE}"
    }

    static String getReadPermission(Class resourceClass, def id) {
        getPermission(resourceClass, id, READ)
    }

    static String getUpdatePermission(Class resourceClass, def id) {
        getPermission(resourceClass, id, UPDATE)
    }

    static String getDeletePermission(Class resourceClass, def id) {
        getPermission(resourceClass, id, DELETE)
    }

    static String getWildcardPermission(Class resourceClass) {
        getWildcardPermission(resourceClass, WILDCARD)
    }

    static String getWildcardPermission(Class resourceClass, def id) {
        getPermission(resourceClass, id, WILDCARD)
    }

    static String getWildcardPermissionFor(Class resourceClass, String action) {
        getPermission(resourceClass, WILDCARD, action)
    }

    static List<String> getResourcePermissions(Class resourceClass, def id) {
        [
                getReadPermission(resourceClass, id),
                getUpdatePermission(resourceClass, id),
                getDeletePermission(resourceClass, id)
        ]
    }
}
