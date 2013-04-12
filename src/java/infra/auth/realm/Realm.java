package infra.auth.realm;

import infra.auth.domains.Role;
import infra.auth.domains.User;
import infra.auth.tokens.AuthToken;

import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;

import java.util.List;

/**
 * @author prostohz
 * @since 4/10/13 12:24 PM
 */
public interface Realm {

    /**
     * Returns a SimpleAccount object
     *
     * @param authToken
     * @return
     */
    public SimpleAccount authenticate(UsernamePasswordToken authToken);

    /**
     * Returns <tt>true</tt> if specified pricipal has a role with roleName
     *
     * @param principal principal name
     * @param roleName role name
     * @return
     */
    public boolean hasRole(String principal, Role roleName);

    /**
     *
     * @param principal principal name
     * @param roles collection of roles
     * @return
     */
    public boolean hasAllRoles(String principal, List<Role> roles);

    /**
     * Returns <tt>true</tt> if user is permited with specified permission
     *
     * @param principal principal name
     * @param requiredPermission requiredPermission
     * @return
     */
    public boolean isPermitted(String principal, String requiredPermission);

    /**
     *
     * @param principal principal name
     * @return
     */
    public User getUserByPrincipal(String principal);

}
