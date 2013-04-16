package infra.auth

import infra.auth.domains.Role
import infra.auth.utils.AuthConfigs
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class AuthUtilsService implements ApplicationContextAware {

    def grailsApplication
    def authRoleService

    private AuthConfigs config

    @Override
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        config = new AuthConfigs(grailsApplication.config.infra?.auth as ConfigObject)
    }

    Role getDefaultRole() {
        config.defaultRoleName ? authRoleService.createRole(config.defaultRoleName, config.defaultRolePermissions ?: []) : null
    }

    AuthConfigs getConfig() {
        config
    }
}
