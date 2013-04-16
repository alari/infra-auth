package infra.auth;

import infra.auth.domains.Role;
import infra.auth.domains.User;
import org.codehaus.groovy.runtime.StringGroovyMethods;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author  prostohz
 * @since 4/10/13 1:04 PM
 */
public interface AuthRepo<T extends User, S extends Role> {

    public Serializable getId(T user);
    public String getUsername(T user);
    public Collection<S> getRoles(T user);

    public T createUser(String username, String password);
    public T getUserByUsername(String username);

    public void save(T user);
    public void save(T user, Map params);

    public String getRoleName(S role);

    public S getRole(String name);

    public S createRole(String name, Collection<String> permissions);

    public boolean addToRoles(T user, S role);
    public boolean removeFromRoles(T user, S role);

    public boolean addPermissionsToRole(S role, Collection<String> permissions);
    public Collection<String> getRolePermissions(S role);
    public boolean removePermissionsFromRole(S role, Collection<String> permissions);
}
