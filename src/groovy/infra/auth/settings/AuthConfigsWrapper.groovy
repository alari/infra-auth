package infra.auth.settings

/**
 * @author prostohz
 * @since 4/15/13 2:37 PM
 */
class AuthConfigsWrapper {

    private static volatile AuthConfigsWrapper instance

    public static AuthConfigsWrapper getInstance() {
        if (!instance) {
            synchronized (this.class) {
                if (!instance) {
                    instance = new AuthConfigsWrapper()
                }
            }
        }
        instance
    }

    private AuthConfigsWrapper() { }

    private AuthConfigs authConfigs

    public void setConfigs(final AuthConfigs authConfigs)  {
        this.authConfigs = authConfigs
    }

    public AuthConfigs getConfigs() {
        return authConfigs
    }
}
