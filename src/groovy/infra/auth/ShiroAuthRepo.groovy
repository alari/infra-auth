package infra.auth

import infra.auth.domains.User

/**
 * @author prostohz
 * @since 4/10/13 1:05 PM
 */
class ShiroAuthRepo implements AuthRepo<ShiroUser, ShiroRole> {

    def shiroSecurityService

    @Override
    ShiroUser createUser(String username, String password) {
        User user = null
        if (username && password) {
            String passwordHash = shiroSecurityService.encodePassword(password, username)
            user = new ShiroUser(username: username, passwordHash: passwordHash)
            user.save()
        }
        user
    }

    @Override
    ShiroUser getUserByUsername(String username) {
        username ? ShiroUser.findByUsername(username) : null
    }

    @Override
    String getRoleName(ShiroRole role) {
        role ? role.name : null
    }

    @Override
    Collection<ShiroRole> getRoles(ShiroUser user) {
        user ? user.roles : null
    }

    @Override
    ShiroRole findOrCreateRole(String name) {
        ShiroRole role = null
        if (name) {
            role = new ShiroRole()
            role.name = name
            role.save()
        }
        role
    }

    @Override
    ShiroRole createRole(String name, Collection<String> permissions) {
        ShiroRole role = null
        if (name && permissions) {
            role = new ShiroRole()
            if (permissions.size() > 0) {
                for (String permission : permissions) {
                    role.addToPermissions(permission)
                }
            }
            role.save()
        }
        role
    }

    @Override
    boolean addToRoles(ShiroUser user, ShiroRole role) {
        if (user && role) {
            user.addToRoles(role)
            user.save()
            true
        }
        false
    }

    @Override
    boolean removeFromRoles(ShiroUser user, ShiroRole role) {
        if (user && role) {
            user.removeFromRoles(role)
            true
        }
        false
    }

    @Override
    boolean addPermissionsToRole(ShiroRole role, Collection<String> permissions) {
        if (role && permissions) {
            for (String permission : permissions) {
                role.addToPermissions(permission)
            }
            true
        }
        false
    }

    @Override
    boolean removePermissionsFromRole(ShiroRole role, Collection<String> permissions) {
        if (role && permissions) {
            for (String permission : permissions) {
                role.removeFromPermissions(permission)
            }
            true
        }
        false
    }
}
