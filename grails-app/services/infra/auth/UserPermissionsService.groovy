package infra.auth

import infra.auth.domains.User

class UserPermissionsService {

    static transactional = false

    def grailsCacheManager

    Collection<String> getUserPermissions(User user) {
        if(!user)
            return []
        def cache = grailsCacheManager?.getCache("permissions")?.get(user.id.toString())
        if (cache == null) {
            cache = (user.roles*.permissions?.flatten() ?: []).unique()
            grailsCacheManager?.getCache("permissions")?.put(user.id.toString(), cache)
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
        user.save()
    }

    void evictUserPermissions(final User user) {
        grailsCacheManager?.getCache("permissions")?.evict(user.id.toString())
    }
}
