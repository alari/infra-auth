package infra.auth

class OAuthCredentials implements Serializable {

    String externalUserId

    String username

    String provider

    String token
    String secret

    Date dateCreated = new Date()
    Date lastUpdated

    static belongsTo =  [user: ShiroUser]

    static constraints = {
        externalUserId nullable: false, unique: "provider"
        username nullable: true
        provider nullable: false
        token nullable: false
        secret nullable: true
    }
}
