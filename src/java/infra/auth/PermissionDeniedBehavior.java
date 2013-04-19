package infra.auth;

/**
 * @author prostohz
 * @since 4/11/13 11:20 AM
 */
public interface PermissionDeniedBehavior {

    public void throwFail(String permission);
}
