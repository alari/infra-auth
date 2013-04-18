package infra.auth.ast

import infra.auth.AccessControlUtils
import org.codehaus.groovy.ast.AnnotatedNode
import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * @author alari
 * @since 3/20/13 1:59 PM
 */
@GroovyASTTransformation(phase = CompilePhase.CANONICALIZATION)
abstract class AbstractTransform implements ASTTransformation {
    protected static final String HASH_CODE = '#'
    protected static final String GSTRING = '$'
    protected static final String THIS = 'this'
    protected static final String PRINTLN = 'println'

    protected static final String CAN_OR_FAIL = 'canOrFail'
    protected static final String CAN_CREATE_OR_FAIL = 'canCreateOrFail'

    Expression keyExpression(String key) {
        if (key && key.contains(HASH_CODE)) {
            def ast = new AstBuilder().buildFromString("""
                "${key.replace(HASH_CODE, GSTRING).toString()}"
           """)
            return ast[0].statements[0].expression
        } else {
            return new ConstantExpression(key)
        }
    }

    protected String getId(AnnotationNode annotationNode) {
        annotationNode.getMember("id")?.text ?: "#{id}"
    }

    protected ClassExpression getResourceClass(AnnotationNode annotationNode) {
        (ClassExpression) annotationNode.getMember("value")
    }

    protected void transform(MethodNode methodNode, AnnotationNode annotationNode, String permission) {
        transformMethod(methodNode, getResourceClass(annotationNode), getId(annotationNode), permission)
    }

    protected void transform(ClassNode classNode, AnnotationNode annotationNode, String permission) {
        classNode.methods.each {
            transform(it, annotationNode, permission)
        }
    }

    protected void transform(AnnotatedNode unknownNode, AnnotationNode annotationNode, String permission) {
        println "Unknown node! ${unknownNode}"
    }

    protected void transformMethod(MethodNode methodNode, ClassExpression resourceClass, String id, String permission) {
        prependMethodStatement(methodNode, resourcePermittedStatement(resourceClass, id, permission))
    }

    protected void prependMethodStatement(MethodNode methodNode, Statement statement) {
        if(!methodNode.code instanceof BlockStatement) {
            System.err.println "Trying to prepend statement to compiled method: ${methodNode.declaringClass.name}:${methodNode.name}"
        }
        BlockStatement codeBlock = (BlockStatement) methodNode.code
        BlockStatement block = new BlockStatement();

        block.addStatement(statement)
        block.addStatements(codeBlock.statements)
        methodNode.code = block
    }

    protected Statement accessControlStatement(String method, ArgumentListExpression args = null) {
        new ExpressionStatement(new MethodCallExpression(
                new ClassExpression(new ClassNode(AccessControlUtils)),
                new ConstantExpression(method),
                args ?: new ArgumentListExpression(

                ))
        )
    }

    protected Statement resourcePermittedStatement(ClassExpression resourceClass, String id, String permission) {
        accessControlStatement(CAN_OR_FAIL, new ArgumentListExpression(
                resourceClass,
                keyExpression(id),
                keyExpression(permission)
        ))
    }
}
