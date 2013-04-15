package infra.auth

import infra.auth.settings.AuthConfigs
import infra.auth.settings.AuthConfigsWrapper
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author prostohz
 * @since 4/15/13 3:24 PM
 */
class ViewsResolvingService {

    String getSignInView() {
        AuthConfigsWrapper.getInstance().getConfigs().signInView
    }

    String getSignUpView() {
        AuthConfigsWrapper.getInstance().getConfigs().signUpView
    }

    String getUnauthorizedView() {
        AuthConfigsWrapper.getInstance().getConfigs().unauthorizedView
    }
}
