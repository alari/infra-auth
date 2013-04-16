package infra.auth

import infra.auth.domains.User
import org.apache.shiro.grails.ShiroSecurityService
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author prostohz
 * @since 4/10/13 1:05 PM
 */
class ShiroAuthRepo implements AuthRepo<ShiroUser, ShiroRole> {
    @Autowired
    ShiroSecurityService shiroSecurityService

    @Autowired
    AuthUtilsService authUtilsService

    @Override
    ShiroUser createUser(String username, String password) {
        User user = null
        if (username && password) {
            if (!getUserByUsername(username)) {
                String passwordHash = shiroSecurityService.encodePassword(password, username)
                user = new ShiroUser(username: username, passwordHash: passwordHash)
                if (user.validate()) {
                    user.save()

                    ShiroRole userRole = (ShiroRole)authUtilsService.defaultRole
                    if(userRole) addToRoles(user, userRole)
                } else
                    user = null
            }
        }
        user
    }

    @Override
    ShiroUser getUserByUsername(String username) {
        username ? ShiroUser.findByUsername(username) : null
    }

    @Override
    void save(ShiroUser user) {
        user.save()
    }

    @Override
    String getRoleName(ShiroRole role) {
        role?.name ? role.name : null
    }

    @Override
    Serializable getId(ShiroUser user) {
        user ? user.id : null
    }

    @Override
    String getUsername(ShiroUser user) {
        user ? user.username : null
    }

    @Override
    Collection<ShiroRole> getRoles(ShiroUser user) {
        user ? user.roles : null
    }

    @Override
    ShiroRole getRole(String name) {
        ShiroRole.findOrSaveByName(name)
    }

    @Override
    ShiroRole createRole(String name, Collection<String> permissions) {
        ShiroRole role = null
        if (name && permissions) {
            role = getRole(name)
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
    Collection<String> getRolePermissions(ShiroRole role) {
        role?.permissions
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
