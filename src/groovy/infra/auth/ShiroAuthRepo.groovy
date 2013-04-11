package infra.auth

import infra.auth.domains.User

/**
 * @author prostohz
 * @since 4/10/13 1:05 PM
 */
class ShiroAuthRepo implements AuthRepo<ShiroUser> {

    def shiroSecurityService

    @Override
    ShiroUser create(String username, String password) {
        if (username && password) {
            String passwordHash = shiroSecurityService.encodePassword(password, username)
            User user = new ShiroUser(username: username, passwordHash: passwordHash)
            user.save()
        } else {
            null
        }
    }

    @Override
    ShiroUser getUserByUsername(String username) {
        if (username)
            ShiroUser.findByUsername(username)
        else
            null
    }
}
