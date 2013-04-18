package infra.auth.utils

import org.springframework.beans.factory.annotation.Autowired

/**
 * @author alari
 * @since 4/16/13 3:02 PM
 */
@Singleton
final class AccessControlBeanHolder {
    @Autowired AccessControlUtils accessControlUtils

    static public void canAdminOrFail() {
        getInstance().accessControlUtils.canAdminOrFail()
    }

    static public void canAdminOrFail(Class resourceClass) {
        getInstance().accessControlUtils.canAdminOrFail(resourceClass)
    }

    static public void canOrFail(Class resourceClass, def id, String permission) {
        getInstance().accessControlUtils.canOrFail(resourceClass, id, permission)
    }

    static public void canOrFail(String prefix, String permission) {
        getInstance().accessControlUtils.canOrFail(prefix, permission)
    }

    static public void canCreateOrFail(Class resourceClass) {
        getInstance().accessControlUtils.canCreateOrFail(resourceClass)
    }
}
