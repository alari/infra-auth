package infra.auth

import grails.test.mixin.*
import grails.test.mixin.support.*
import infra.auth.utils.PermissionUtils
import infra.auth.utils.ShiroPermissionUtils
import org.junit.Ignore
import org.springframework.beans.factory.annotation.Autowired

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Ignore
@TestMixin(GrailsUnitTestMixin)
class PermissionUtilsTests {

    @Autowired
    PermissionUtils permissionUtils

    void setUp() {
        // Setup logic here
    }

    void tearDown() {
        // Tear down logic here
    }

    void testSomething() {
        assert permissionUtils.getAdminPermission(PermissionUtilsTests) == "PermissionUtilsTests:admin"
    }
}
