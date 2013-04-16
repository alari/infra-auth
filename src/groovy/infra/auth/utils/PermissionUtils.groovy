package infra.auth.utils

/**
 * @author prostohz
 * @since 4/10/13 3:22 PM
 */
public interface PermissionUtils {

    public String getCreateActionName()

    public String getReadActionName()

    public String getUpdateActionName()

    public String getDeleteActionName()

    public String getWildcardActionName()

    public String getAdminActionName()

    public String getPermission(String prefix, String action)

    public String getPermission(Class resourceClass, def id, String action)

    public String getCreatePermission(Class resourceClass)

    public String getReadPermission(Class resourceClass, def id)

    public String getUpdatePermission(Class resourceClass, def id)

    public String getDeletePermission(Class resourceClass, def id)

    public String getAdminPermission()

    public String getAdminPermission(Class resourceClass)

    public String getWildcardPermission(Class resourceClass)

    public String getWildcardPermission(Class resourceClass, def id)

    public String getWildcardPermissionFor(Class resourceClass, String action)

    public List<String> getResourcePermissions(Class resourceClass, def id)
}

