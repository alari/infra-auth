package infra.auth

import infra.auth.domains.Role

class ShiroRole implements Serializable, Role {

    String name

    static hasMany = [users: ShiroUser, permissions: String]
    static belongsTo = ShiroUser

    static constraints = {
        name nullable: false, blank: false, unique: true
    }
}
