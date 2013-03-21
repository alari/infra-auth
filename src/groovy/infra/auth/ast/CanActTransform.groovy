package infra.auth.ast

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * @author alari
 * @since 3/20/13 12:38 PM
 */
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class CanActTransform extends AbstractTransform implements ASTTransformation {
    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        AnnotationNode annotation = (AnnotationNode) astNodes[0]
        AnnotatedNode body = (AnnotatedNode) astNodes[1]

        String permission = annotation.getMember("value").text
        String prefix = annotation.getMember("prefix")?.text ?: ""

        Statement statement = accessControlStatement("canOrFail", new ArgumentListExpression(
                keyExpression(prefix),
                keyExpression(permission)
        ))

        if (body instanceof MethodNode) {
            prependMethodStatement(body, statement)
        } else if (body instanceof ClassNode) {
            body.methods.each {
                prependMethodStatement(it, statement)
            }
        }
    }
}
