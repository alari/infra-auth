package infra.auth.utils

/**
 * @author prostohz
 * @since 4/11/13 11:01 AM
 */
public interface AccessControlUtils {

    public boolean isPermitted(String permission);

    public boolean can(Class resourceClass, def id, String action)

    public boolean can(String prefix, String action)

    public boolean canCreate(Class resourceClass)

    public boolean canRead(Class resourceClass, def id)

    public boolean canUpdate(Class resourceClass, def id)

    public boolean canDelete(Class resourceClass, def id)

    public boolean canAdmin()

    public void canAdminOrFail()

    public void canAdminOrFail(Class resourceClass)

    public void canOrFail(Class resourceClass, def id, String permission)

    public void canOrFail(String prefix, String permission)

    public void canCreateOrFail(Class resourceClass)
}
