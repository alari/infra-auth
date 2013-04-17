import infra.auth.ShiroAuthRepo
import infra.auth.ShiroPermissionDeniedBehavior
import infra.auth.utils.AccessControlBeanHolder
import infra.auth.utils.ShiroAccessControlUtils
import infra.auth.utils.ShiroPermissionUtils

import org.apache.shiro.authc.credential.Sha1CredentialsMatcher
import org.apache.shiro.grails.ShiroSecurityService

class InfraAuthGrailsPlugin {

    def version = "0.2-SNAPSHOT"
    def grailsVersion = "2.2 > *"
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    def title       = "Infra Auth Plugin"
    def author      = "Dmitry Kurinskiy"
    def authorEmail = "name.alari@gmail.com"

    def description = ""

    def documentation = "http://grails.org/plugin/infra-auth"

    def license = "APACHE"

    def developers = [
            [ name: "Svyat Podmogayev", email: "s.podmogayev@gmail.com" ],
            [ name: "Dmitry Kurinskiy", email: "name.alari@gmail.com" ]
    ]

//
    def organization = [ name: "Taktika", url: "http://itaktika.ru/" ]
//
//    Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]
//
//    Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]
//

    def doWithSpring = {

        accessControlBeanHolder(AccessControlBeanHolder) {bean ->
            bean.factoryMethod = "getInstance"
            bean.singleton = true
        }
        accessControlUtils(ShiroAccessControlUtils)

        authRepo(ShiroAuthRepo)
        permissionUtils(ShiroPermissionUtils)
        permissionDeniedBehavior(ShiroPermissionDeniedBehavior)
        shiroSecurityService(ShiroSecurityService)

        credentialMatcher(Sha1CredentialsMatcher) {
            storedCredentialsHexEncoded = true
        }

    }
}
