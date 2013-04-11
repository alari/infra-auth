package infra.auth

import infra.auth.domains.Role
import infra.auth.domains.User

class ShiroUser implements Serializable, User {

    String username
    String passwordHash

    static hasMany = [roles: ShiroRole, oauthCredentials: OAuthCredentials, permissions: String]

    static constraints = {
        username nullable: false, blank: false, unique: true, size: 2..31
        passwordHash nullable: true
        oauthCredentials minSize: 0
    }

    @Override
    void removeFromRoles(Role role) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
