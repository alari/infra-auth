package infra.auth.ast

import infra.auth.PermissionUtils
import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * @author alari
 * @since 3/20/13 12:38 PM
 */
@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
class CanReadTransform extends AbstractTransform implements ASTTransformation {
    @Override
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        AnnotationNode annotation= (AnnotationNode) astNodes[0]
        AnnotatedNode body = (AnnotatedNode) astNodes[1]

        transform(body, annotation, PermissionUtils.READ)
    }
}
