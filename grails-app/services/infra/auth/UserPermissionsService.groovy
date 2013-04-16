package infra.auth

import infra.auth.domains.User
import org.springframework.beans.factory.annotation.Autowired

class UserPermissionsService {

    static transactional = false

    def grailsCacheManager

    @Autowired
    AuthRepo authRepo

    Collection<String> getUserPermissions(User user) {
        if(!user)
            return []
        def cache = grailsCacheManager?.getCache("permissions")?.get(authRepo.getId(user).toString())
        if (cache == null) {
            cache = (authRepo.getRoles(user)*.permissions?.flatten() ?: []).unique()
            grailsCacheManager?.getCache("permissions")?.put(authRepo.getId(user).toString(), cache)
        } else cache = cache.get()
        return cache
    }

    void updateUserPermissions(ShiroUser user, Collection<String> permissions) {
        evictUserPermissions(user)
        // Take the simple approach: clear the list and re-add all declared permissions.
        if (user.permissions == null) {
            user.permissions = permissions
        }
        else {
            user.permissions.clear()
            user.permissions.addAll permissions
        }
        user.save()
    }

    void updateUserPermissions(User user) {
        evictUserPermissions(user)
        authRepo.save(user)
    }

    void evictUserPermissions(final User user) {
        grailsCacheManager?.getCache("permissions")?.evict(authRepo.getId(user).toString())
    }
}
