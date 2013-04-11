package infra.auth.domains;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author  prostohz
 * @since 4/10/13 12:19 PM
 */
public interface User {

    public String getPasswordHash();

    public String getUsername();
}
