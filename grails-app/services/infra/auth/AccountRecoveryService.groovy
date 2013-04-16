package infra.auth

import org.apache.commons.lang.RandomStringUtils
import org.apache.shiro.SecurityUtils
import org.springframework.context.i18n.LocaleContextHolder

class AccountRecoveryService {

    public static final String RECOVER_SESSION_KEY = RandomStringUtils.randomAlphanumeric(20)


    private static final String USER_ID_SESSION_KEY = RandomStringUtils.randomAlphanumeric(20)

    def messageSource

    /**
     *
     * @param email
     * @return
     */
    public Map<String, ?> sendRecoveryConfirmation(String email) {

    }

    /**
     *
     * @param command
     * @return
     */
    public Map<String, ?> changePassword(def command) {

    }

    /**
     *
     * @return
     */
    public boolean checkFlag() {
        SecurityUtils.subject.session.getAttribute(RECOVER_SESSION_KEY) != null
    }

    /**
     *
     * @return
     */
    public void deleteFlag() {
        SecurityUtils.subject.session.removeAttribute(RECOVER_SESSION_KEY)
    }

    /**
     *
     */
    private static void addAccountRecoveryFlag() {
        SecurityUtils.subject.session.setAttribute(RECOVER_SESSION_KEY, "true")
    }

    /**
     *
     * @return
     */
    private Map<String, ?> getStatus() {
        [error: null]
    }

    /**
     *
     * @param params
     * @return
     */
    private String message(Map params) {
        messageSource.getMessage(params.code, [] as Object[], null, LocaleContextHolder.locale)
    }
}
