package infra.auth.settings

/**
 * @author prostohz
 * @since 4/15/13 3:04 PM
 */
class AuthConfigs {

    private final String signInView
    private final String signUpView
    private final String unauthorizedView

    private final Collection<String> userPermissions

    public AuthConfigs(String signInView, String signUpView, String unauthorizedView, Collection<String> userPermissions) {
        this.signInView = signInView
        this.signUpView = signUpView
        this.unauthorizedView = unauthorizedView
        this.userPermissions = userPermissions
    }

    public String getSignInView() {
        signInView
    }

    public String getSignUpView() {
        signUpView
    }

    public String getUnauthorizedView() {
        unauthorizedView
    }

    public Collection<String> getUserPermissions() {
        userPermissions
    }
}
