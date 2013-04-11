package infra.auth.domains;

import java.util.Collection;
import java.util.List;

/**
 * @author  prostohz
 * @since 4/10/13 12:20 PM
 */
public interface Role {

    public Collection<String> getPermissions();

}
