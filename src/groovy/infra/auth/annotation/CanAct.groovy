package infra.auth.annotation

import org.codehaus.groovy.transform.GroovyASTTransformationClass

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

/**
 * @author alari
 * @since 3/20/13 12:34 PM
 */
@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.METHOD, ElementType.TYPE])
@GroovyASTTransformationClass(["infra.auth.ast.CanActTransform"])
public @interface CanAct {
    String prefix() default ''

    String value()
}