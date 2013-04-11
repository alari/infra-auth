package infra.auth;

import infra.auth.domains.Role;
import infra.auth.domains.User;

import java.util.Collection;

/**
 * @author  prostohz
 * @since 4/10/13 1:04 PM
 */
public interface AuthRepo<T extends User> {

    /**
     *
     * @param username username
     * @param password password
     * @return returns
     */
    public T create(String username, String password);

    /**
     *
     * @param username username
     * @return returns
     */
    public T getUserByUsername(String username);
}
