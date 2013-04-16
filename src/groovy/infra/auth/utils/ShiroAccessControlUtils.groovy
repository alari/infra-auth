package infra.auth.utils

import groovy.transform.CompileStatic
import infra.auth.PermissionDeniedBehavior
import org.apache.shiro.SecurityUtils
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author alari
 * @since 3/20/13 12:21 PM
 */
@CompileStatic
class ShiroAccessControlUtils implements AccessControlUtils {

    @Autowired
    PermissionUtils permissionUtils

    @Autowired
    PermissionDeniedBehavior permissionDeniedBehavior


    @Override
    public boolean canAdmin() {
        isPermitted permissionUtils.getAdminPermission()
    }

    @Override
    public boolean canCreate(Class resourceClass) {
        isPermitted permissionUtils.getCreatePermission(resourceClass)
    }

    @Override
    public boolean canRead(Class resourceClass, def id) {
        isPermitted permissionUtils.getReadPermission(resourceClass, id)
    }

    @Override
    public boolean canUpdate(Class resourceClass, def id) {
        isPermitted permissionUtils.getUpdatePermission(resourceClass, id)
    }

    @Override
    public boolean canDelete(Class resourceClass, def id) {
        isPermitted permissionUtils.getDeletePermission(resourceClass, id)
    }

    @Override
    public boolean isPermitted(String permission) {
        SecurityUtils.subject?.isPermitted(permission)
    }

    @Override
    public boolean can(Class resourceClass, def id, String action) {
        isPermitted permissionUtils.getPermission(resourceClass, id, action)
    }

    @Override
    public boolean can(String prefix, String action) {
        isPermitted permissionUtils.getPermission(prefix, action)
    }


    public void canAdminOrFail() {
        failIfNotPermitted permissionUtils.getAdminPermission()
    }

    public void canAdminOrFail(Class resourceClass) {
        failIfNotPermitted permissionUtils.getAdminPermission(resourceClass)
    }

    public void canReadOrFail(Class resourceClass, def id) {
        failIfNotPermitted permissionUtils.getReadPermission(resourceClass, id)
    }

    public void canUpdateOrFail(Class resourceClass, def id) {
        failIfNotPermitted permissionUtils.getUpdatePermission(resourceClass, id)
    }

    public void canCreateOrFail(Class resourceClass) {
        failIfNotPermitted permissionUtils.getCreatePermission(resourceClass)
    }

    public void canDeleteOrFail(Class resourceClass, def id) {
        failIfNotPermitted permissionUtils.getDeletePermission(resourceClass, id)
    }

    public void canOrFail(Class resourceClass, def id, String permission) {
        failIfNotPermitted permissionUtils.getPermission(resourceClass, id, permission)
    }

    public void canOrFail(String prefix, String permission) {
        failIfNotPermitted permissionUtils.getPermission(prefix, permission)
    }

    public void failIfNotPermitted(String permission) {
        if (!isPermitted(permission)) {
            permissionDeniedBehavior.throwFail(permission)
        }
    }
}
