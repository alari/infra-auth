package infra.auth;

import infra.auth.domains.Role;
import infra.auth.domains.User;

import java.util.Collection;
import java.util.List;

/**
 * @author  prostohz
 * @since 4/10/13 1:04 PM
 */
public interface AuthRepo<T extends User, S extends Role> {

    public T createUser(String username, String password);
    public T getUserByUsername(String username);

    public String getRoleName(S role);

    public Collection<S> getRoles(T user);

    public S findOrCreateRole(String name);
    public S createRole(String name, Collection<String> permissions);

    public boolean addToRoles(T user, S role);
    public boolean removeFromRoles(T user, S role);

    public boolean addPermissionsToRole(S role, Collection<String> permissions);
    public boolean removePermissionsFromRole(S role, Collection<String> permissions);
}
