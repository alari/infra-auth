package infra.auth.settings

/**
 * @author prostohz
 * @since 4/15/13 3:04 PM
 */
class AuthConfigs {

    private final String signInView
    private final String signUpView
    private final String unauthorizedView

    public AuthConfigs(String signInView, String signUpView, String unauthorizedView) {
        this.signInView = signInView
        this.signUpView = signUpView
        this.unauthorizedView = unauthorizedView
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
}
