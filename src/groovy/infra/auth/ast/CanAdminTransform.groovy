package infra.auth.ast

import infra.auth.AccessControlUtils
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.ClassExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * @author alari
 * @since 3/20/13 12:38 PM
 */
@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class CanAdminTransform extends AbstractTransform implements ASTTransformation {
    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        AnnotationNode annotation= (AnnotationNode) astNodes[0]
        AnnotatedNode body = (AnnotatedNode) astNodes[1]

        ClassExpression resourceClass = (ClassExpression)annotation.getMember("value")
        Statement statement = resourceClass ? resourceAdminStatement(resourceClass) : adminStatement()

        if (body instanceof MethodNode) {
            prependMethodStatement(body, statement)
        } else if (body instanceof ClassNode){
            body.methods.each {
                prependMethodStatement(it, statement)
            }
        }
    }

    protected Statement resourceAdminStatement(ClassExpression resourceClass) {
        new ExpressionStatement(new MethodCallExpression(
                new ClassExpression(new ClassNode(AccessControlUtils)),
                new ConstantExpression("canAdminOrFail"),
                new ArgumentListExpression(
                        resourceClass,
                )  )
        )
    }

    protected Statement adminStatement() {
        new ExpressionStatement(new MethodCallExpression(
                new ClassExpression(new ClassNode(AccessControlUtils)),
                new ConstantExpression("canAdminOrFail"),
                new ArgumentListExpression(
                )  )
        )
    }
}
