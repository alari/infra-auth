package infra.auth

class RoleUtilsService {

    def userPermissionsService

     String getRoleName(Class resourceClass, def id) {
        "${resourceClass.simpleName}:${id}"
    }

    String getRoleName(String prefix, def id) {
        "${prefix}:${id}"
    }

    ShiroRole getRole(Class resourceClass, def id) {
        getRole(getRoleName(resourceClass, id))
    }

    ShiroRole getRole(String name) {
        ShiroRole.findOrSaveByName(name)
    }

    ShiroRole getRole(String prefix, def id) {
        getRole(getRoleName(prefix, id))
    }

    ShiroRole createRole(Class resourceClass, def id, List<String> permissions = null) {
        createRole(getRoleName(resourceClass, id), permissions ? permissions : PermissionUtils.getResourcePermissions(resourceClass, id))
    }

    ShiroRole createRole(String prefix, def id, List<String> permissions) {
        createRole(getRoleName(prefix, id), permissions)
    }

    ShiroRole createRole(String name, List<String> permissions) {
        ShiroRole role = getRole(name)

        for (String p in permissions) {
            role.addToPermissions(p)
        }

        role.save(flush: true)
    }

    /*
     *TODO need for review
     */
    boolean hasRole(ShiroUser user, ShiroRole role) {
        user?.roles?.contains(role)
    }

    void addRole(ShiroUser user, ShiroRole role) {
        if(user.roles.any{it.name == role.name}) return;
        user.addToRoles(role)
        userPermissionsService.updateUserPermissions(user)
    }

    void removeRole(ShiroUser user, ShiroRole role) {
        if(hasRole(user, role)) {
            user.removeFromRoles(role)
            userPermissionsService.updateUserPermissions(user)
        }
    }

    void addPermissions(ShiroRole role, List<String> permissions = null) {
        for (String p in permissions) {
            role.addToPermissions(p)
        }

        role.save(flush: true)
    }

    void removePermissions(ShiroRole role, Collection<String> permissions) {
        for(String p in permissions) {
            role.removeFromPermissions(p)
        }
        role.save()
    }

    void initRoles(Closure closure) {
        closure.setDelegate(this)
        closure.call(this)
    }
}
