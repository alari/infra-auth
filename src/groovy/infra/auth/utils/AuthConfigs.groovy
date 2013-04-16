package infra.auth.utils

/**
 * @author prostohz
 * @since 4/15/13 3:04 PM
 */
class AuthConfigs {

    final String signInView
    final String signUpView
    final String unauthorizedView

    final Collection<String> defaultRolePermissions
    final String defaultRoleName

    private final Map config

    public AuthConfigs(ConfigObject authPluginConfig) {

        config = authPluginConfig.flatten()

        signInView = config.signInView
        signUpView = config.signUpView
        unauthorizedView = config.unauthorizedView

        defaultRolePermissions = config.defaultRolePermissions
        defaultRoleName = config.defaultRoleName
    }
}
