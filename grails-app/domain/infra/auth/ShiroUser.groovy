package infra.auth

abstract class ShiroUser implements Serializable {
    String username
    String passwordHash

    static hasMany = [roles: ShiroRole, oauthCredentials: OAuthCredentials, permissions: String]

    static constraints = {
        username nullable: false, blank: false, unique: true, size: 2..31
        passwordHash nullable: true
        oauthCredentials minSize: 0
    }
}
