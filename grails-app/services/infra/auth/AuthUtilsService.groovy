package infra.auth

import infra.auth.domains.Role
import infra.auth.utils.AuthConfigs
import org.apache.shiro.SecurityUtils
import org.apache.shiro.subject.Subject
import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

class AuthUtilsService implements ApplicationContextAware {

    def grailsApplication
    def authRoleService

    private AuthConfigs config

    public Subject getSubject() {
        SecurityUtils.subject
    }

    public String getPrincipal() {
        subject?.principal
    }

    public boolean isAuthenticated() {
        subject?.isAuthenticated() ?: false
    }

    public Map<String, ?> getAuthStatus() {
        [isAuthenticated: isAuthenticated(), username: principal]
    }

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
