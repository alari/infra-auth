package infra.auth.ast

import infra.auth.AccessControlUtils
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
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
class CanCreateTransform extends AbstractTransform implements ASTTransformation {
    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        AnnotationNode annotation= (AnnotationNode) astNodes[0]
        AnnotatedNode body = (AnnotatedNode) astNodes[1]

        if (body instanceof MethodNode) {
            prependMethodStatement(body, resourcePermittedStatement(getResourceClass(annotation)))
        } else if (body instanceof ClassNode){
            body.methods.each {
                prependMethodStatement(it, resourcePermittedStatement(getResourceClass(annotation)))
            }
        }
    }

    protected Statement resourcePermittedStatement(ClassExpression resourceClass) {
        new ExpressionStatement(new MethodCallExpression(
                new ClassExpression(new ClassNode(AccessControlUtils)),
                new ConstantExpression("canCreateOrFail"),
                new ArgumentListExpression(
                        resourceClass,
                )  )
        )
    }
}
