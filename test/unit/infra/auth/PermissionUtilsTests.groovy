package infra.auth

import grails.test.mixin.*
import grails.test.mixin.support.*
import infra.auth.utils.ShiroPermissionUtils
import org.junit.Ignore

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@Ignore
@TestMixin(GrailsUnitTestMixin)
class PermissionUtilsTests {

    void setUp() {
        // Setup logic here
    }

    void tearDown() {
        // Tear down logic here
    }

    void testSomething() {
        assert ShiroPermissionUtils.getAdminPermission(PermissionUtilsTests) == "PermissionUtilsTests:admin"
    }
}
