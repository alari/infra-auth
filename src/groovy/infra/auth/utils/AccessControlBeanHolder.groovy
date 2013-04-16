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
        instance.accessControlUtils.canAdminOrFail()
    }

    static public void canAdminOrFail(Class resourceClass) {
        instance.accessControlUtils.canAdminOrFail(resourceClass)
    }

    static public void canOrFail(Class resourceClass, def id, String permission) {
        instance.accessControlUtils.canOrFail(resourceClass, id, permission)
    }

    static public void canOrFail(String prefix, String permission) {
        instance.accessControlUtils.canOrFail(prefix, permission)
    }

    static public void canCreateOrFail(Class resourceClass) {
        instance.accessControlUtils.canCreateOrFail(resourceClass)
    }
}
