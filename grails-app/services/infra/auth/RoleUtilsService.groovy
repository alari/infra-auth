package infra.auth

import infra.auth.domains.Role
import infra.auth.domains.User
import infra.auth.utils.PermissionUtils
import org.springframework.beans.factory.annotation.Autowired

class RoleUtilsService {

    def userPermissionsService

    @Autowired
    PermissionUtils permissionUtils

    @Autowired
    AuthRepo authRepo

    String getRoleName(Class resourceClass, def id) {
        "${resourceClass.simpleName}:${id}"
    }

    String getRoleName(String prefix, def id) {
        "${prefix}:${id}"
    }

    Role getRole(Class resourceClass, def id) {
        getRole(getRoleName(resourceClass, id))
    }

    Role getRole(String prefix, def id) {
        getRole(getRoleName(prefix, id))
    }

    Role getRole(String name) {
        authRepo.getRole(name)
    }

    Role createRole(Class resourceClass, def id, List<String> permissions = null) {
        createRole(getRoleName(resourceClass, id), permissions ? permissions : permissionUtils.getResourcePermissions(resourceClass, id))
    }

    Role createRole(String prefix, def id, List<String> permissions) {
        createRole(getRoleName(prefix, id), permissions)
    }

    Role createRole(String name, List<String> permissions) {
       authRepo.createRole(name, permissions)
    }


    boolean hasRole(User user, Role role) {
        Collection<Role> roles = authRepo.getRoles(user)
        if (roles && roles.size())
            roles.contains(role)
    }

    void addRole(User user, Role role) {
        if (!hasRole(user, role)) {
            authRepo.addToRoles(user, role)
            userPermissionsService.updateUserPermissions(user)
        }
    }

    void removeRole(User user, Role role) {
        if(hasRole(user, role)) {
            authRepo.removeFromRoles(user, role)
            userPermissionsService.updateUserPermissions(user)
        }
    }

    void addPermissions(Role role, List<String> permissions = null) {
        authRepo.addPermissionsToRole(role, permissions)
    }

    void removePermissions(Role role, Collection<String> permissions) {
        authRepo.removePermissionsFromRole(role, permissions)
    }

    void initRoles(Closure closure) {
        closure.setDelegate(this)
        closure.call(this)
    }
}
