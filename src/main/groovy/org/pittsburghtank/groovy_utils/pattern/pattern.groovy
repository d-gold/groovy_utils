package org.pittsburghtank.groovy_utils.pattern


import java.lang.annotation.*
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.GroovyASTTransformationClass
import org.codehaus.groovy.transform.GroovyASTTransformation
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.stmt.*
import org.codehaus.groovy.ast.expr.*

@Retention(RetentionPolicy.SOURCE)
@Target([ElementType.METHOD])
@GroovyASTTransformationClass(["org.pittsburghtank.groovy_utils.pattern.LoggingASTTransformation"])
public @interface WithLogging {
    
}

@GroovyASTTransformation(phase=CompilePhase.SEMANTIC_ANALYSIS)
public class LoggingASTTransformation implements ASTTransformation {

    public void visit(ASTNode[] nodes, SourceUnit sourceUnit) {
        println "Visit"
        List methods = sourceUnit.getAST()?.getMethods()
        // find all methods annotated with @WithLogging
        methods.findAll { MethodNode method ->
            method.getAnnotations(new ClassNode(WithLogging))
        }.each { MethodNode method ->
            Statement startMessage = createPrintlnAst("Starting $method.name")
            Statement endMessage = createPrintlnAst("Ending $method.name")

            List existingStatements = method.getCode().getStatements()
            existingStatements.add(0, startMessage)
            existingStatements.add(endMessage)
        }
    }

    private Statement createPrintlnAst(String message) {
        return new ExpressionStatement(
            new MethodCallExpression(
                new VariableExpression("this"),
                new ConstantExpression("println"),
                new ArgumentListExpression(
                    new ConstantExpression(message)
                )
            )
        )
    }
}


@WithLogging
def test1() {
    println "Test"
}

println "Start"
test1()
println "Stop"
