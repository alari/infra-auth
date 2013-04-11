package infra.auth.utils

import groovy.transform.CompileStatic

/**
 * @author alari
 * @since 3/20/13 12:07 PM
 */
@CompileStatic
class ShiroPermissionUtils implements PermissionUtils {

    static final String CREATE = "create"
    static final String READ = "read"
    static final String UPDATE = "update"
    static final String DELETE = "delete"

    static final String WILDCARD = "*"
    static final String ADMIN = "admin"

    @Override
    String getPermission(Class resourceClass, def id, String action) {
        getPermission(getResourceCode(resourceClass), "${id}:${action}")
    }

    @Override
    String getCreateActionName() {
        return CREATE
    }

    @Override
    String getReadActionName() {
        return READ
    }

    @Override
    String getUpdateActionName() {
        return UPDATE
    }

    @Override
    String getDeleteActionName() {
        return DELETE
    }

    @Override
    public String getWildcardActionName() {
        return WILDCARD
    }

    @Override
    public String getAdminActionName() {
        return ADMIN
    }

    @Override
    String getPermission(String prefix, String action) {
        prefix ? "${prefix}:${action}" : action
    }

    @Override
    String getAdminPermission() {
        ADMIN
    }

    @Override
    String getAdminPermission(Class resourceClass) {
        "${getResourceCode(resourceClass)}:${ADMIN}"
    }

    @Override
    String getCreatePermission(Class resourceClass) {
        "${getResourceCode(resourceClass)}:${CREATE}"
    }

    @Override
    String getReadPermission(Class resourceClass, def id) {
        getPermission(resourceClass, id, READ)
    }

    @Override
    String getUpdatePermission(Class resourceClass, def id) {
        getPermission(resourceClass, id, UPDATE)
    }

    @Override
    String getDeletePermission(Class resourceClass, def id) {
        getPermission(resourceClass, id, DELETE)
    }

    @Override
    String getWildcardPermission(Class resourceClass) {
        getWildcardPermission(resourceClass, WILDCARD)
    }

    @Override
    String getWildcardPermission(Class resourceClass, def id) {
        getPermission(resourceClass, id, WILDCARD)
    }

    @Override
    String getWildcardPermissionFor(Class resourceClass, String action) {
        getPermission(resourceClass, WILDCARD, action)
    }

    @Override
    List<String> getResourcePermissions(Class resourceClass, def id) {
        [
                getReadPermission(resourceClass, id),
                getUpdatePermission(resourceClass, id),
                getDeletePermission(resourceClass, id)
        ]
    }

    private String getResourceCode(Class resourceClass) {
        resourceClass.simpleName
    }
}
